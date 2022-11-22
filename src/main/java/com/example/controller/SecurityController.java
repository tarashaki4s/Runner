package com.example.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.DTO.LoginDTO;
import com.example.DTO.SignUpDTO;
import com.example.DTO.response.JwtResponse;
import com.example.DTO.response.MessageResponse;
import com.example.controller.JWT.jwt.JwtUtils;
import com.example.dao.AccountDAO;
import com.example.dao.RoleDAO;
import com.example.entity.ERole;
import com.example.entity.Role;
import com.example.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.AuthorityService;

import javax.validation.Valid;

@Controller
public class SecurityController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  AccountDAO accountDAO;

  @Autowired
  RoleDAO roleDAO;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @RequestMapping("/security/login/form")
  public String loginForm(Model model) {
    model.addAttribute("msg", "Vui lòng đăng nhập!");
    return "home/login";
  }

  @RequestMapping("/security/login/success")
  public String loginSuccess(Model model) {
    model.addAttribute("msg", "Đăng nhập thành công");
    return "home/index";
  }

  @RequestMapping("/security/login/error")
  public String loginError(Model model) {
    model.addAttribute("msg", "Sai thông tin đăng nhập");
    return "home/login";
  }

  @RequestMapping("/security/unauthoried")
  public String unauthoried(Model model) {
    model.addAttribute("msg", "Không có quyền truy xuất");
    return "home/login";
  }

  @RequestMapping("/security/logoff/success")
  public String logoffSuccess(Model model) {
    model.addAttribute("msg", "Bạn đã đăng xuất!");
    return "home/login";
  }

  @GetMapping("/home/register")
  public String SignUp() {
    return "home/register";
  }


  @PostMapping("security/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailService userDetails = (UserDetailService) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
        userDetails.getUsername(),
        userDetails.getEmail(),
        userDetails.getFullName(),
        userDetails.getActive(),
        userDetails.getGender(),
        encoder.encode(userDetails.getPassword()),
        roles));


  }

  @PostMapping("/home/signUp")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDTO signUpRequest) {
    if (accountDAO.existsByUsername(signUpRequest.getUserName())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (accountDAO.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    Account acc = new Account();
    acc.setUsername(signUpRequest.getUserName());
    acc.setEmail(signUpRequest.getEmail());
    acc.setFullName(signUpRequest.getFullName());
    acc.setGender(signUpRequest.getGender());
    acc.setActive(signUpRequest.getActive());
    acc.setPassword(encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleDAO.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleDAO.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            break;
          case "moderator":
            Role moderatorRole = roleDAO.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(moderatorRole);
            break;
          case "user":
            Role userRole = roleDAO.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }
    acc.setRole(roles);
    accountDAO.save(acc);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
