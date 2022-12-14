package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import com.example.DTO.ChangePasswordDTO;
import com.example.DTO.LoginDTO;
import com.example.DTO.ResetPassDTO;
import com.example.DTO.SignUpDTO;
import com.example.DTO.response.JwtResponse;
import com.example.DTO.response.MessageResponse;
import com.example.controller.JWT.jwt.JwtUtils;
import com.example.dao.AccountDAO;
import com.example.dao.RoleDAO;
import com.example.entity.*;
import com.example.service.ProductService;
import com.example.service.UserDetailService;
import com.example.service.impl.AccountServiceImpl;
import com.example.service.impl.UserDetailServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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

  @Autowired
  private AccountServiceImpl accountService;
  @Autowired
  ProductService productService;

  UserDetailServiceImpl userDetailServiceImpl;
  @Value("${server.frontend}")
  private String getSiteURL;

  @RequestMapping("/verify")
  public String verifySignUp(@Param("code") String code) {
    accountService.verify(code);
    return "home/verify";
  }

  @RequestMapping("/security/login/form")
  public String loginForm(Model model) {
    model.addAttribute("msg", "Vui l??ng ????ng nh???p!");
    return "home/login";
  }

  @GetMapping("/security/home")
  public String loginSuccess(Model model) {
    Account account = getPrincipal();
    if (account != null)
      model.addAttribute("msg", "????ng nh???p th??nh c??ng");
    return "home/index";

  }

  private Account getPrincipal() {
    Account account = null;
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Account) {
      account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    return account;
  }

  @RequestMapping("/security/login/error")
  public String loginError(Model model) {
    model.addAttribute("msg", "Sai th??ng tin ????ng nh???p");
    return "home/login";
  }

  @RequestMapping("/security/unauthoried")
  public String unauthoried(Model model) {
    model.addAttribute("msg", "Kh??ng c?? quy???n truy xu???t");
    return "home/login";
  }

  @RequestMapping("/security/logoff/success")
  public String logoffSuccess(Model model){
    model.addAttribute("msg","B???n ???? ????ng xu???t!");
    return "home/login";
  }

  @GetMapping("/home/register")
  public String SignUp() {
    return "home/register";
  }


  @GetMapping("home/forgetpassword")
  public String forgetPassword() {
    return "home/forgetpassword";
  }

  @GetMapping("/home/changepassword")
  public String changPass() {
    return "home/changepassword";
  }


  @PostMapping("/home/changepassword")
  public String changePassword(@Valid ChangePasswordDTO changePasswordRequest, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      model.addAttribute("message", "Error: M???t kh???u kh??ng tr??ng kh???p!");
    }
    if (authentication != null) {
      UserDetailService userDetails = (UserDetailService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Account account = accountService.findByUserName(userDetails.getUsername());
      if (!(encoder.matches(changePasswordRequest.getCurrentPassword(), account.getPassword()))) {
        model.addAttribute("message", "Sai m???t kh???u hi???n t???i!");
      } else {
        accountService.updatePassword(account, changePasswordRequest.getNewPassword());
        model.addAttribute("message", "?????i m???t kh???u th??nh c??ng!");
      }
      return "home/changepassword";
    }

    return "redirect:/home/index";

  }


  @PostMapping("/home/forgetpassword")
  public String processForgotPassword(@Valid @ModelAttribute ForgetPassword forgetPassword, Model model) {
    try {
      System.out.println(forgetPassword.getEmail());
      String token = RandomString.make(30);
      accountService.updateResetPasswordToken(token, forgetPassword.getEmail());
      String resetPasswordLink = getSiteURL + "/home/reset-password?token=" + token;
      accountService.sendEmail(forgetPassword.getEmail(), resetPasswordLink);
      model.addAttribute("message", "Vui l??ng check email c???a b???n ????? l???y l???i m???t kh???u!");
    } catch (UsernameNotFoundException ex) {
      model.addAttribute("error", ex.getMessage());
    } catch (UnsupportedEncodingException | MessagingException e) {
      model.addAttribute("error", "B???n ch??a nh???p email!");
    }
    return "home/forgetpassword";
  }


  @GetMapping("/home/reset-password")
  public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
    Account account = accountService.getByResetPasswordToken(token);
    model.addAttribute("token", token);
    if (account == null) {
      model.addAttribute("message", "Invalid Token");
      return "message";
    }

    return "home/resetpassword";
  }

  @PostMapping("/home/reset-password")
  public String processResetPassword(@Valid @ModelAttribute ResetPassDTO resetPasswordRequest, Model model) {
    Account account = accountService.getByResetPasswordToken(resetPasswordRequest.getToken());
    if (account == null) {
      model.addAttribute("message", "Invalid Token");
    } else {
      accountService.updatePassword(account, resetPasswordRequest.getPassword());
      model.addAttribute("message", "?????i m???t kh???u th??nh c??ng!");
    }
    return "home/resetpassword";
  }

  @PostMapping("security/login")

  public String authenticateUser(@Valid LoginDTO loginRequest, Model model) {
    try {
      if(loginRequest.getUsername().equals("") && loginRequest.getPassword().equals("")){
        model.addAttribute("message", "Vui l??ng ??i???n ????? th??ng tin ????ng nh???p!");
        return "home/login";
      }
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailService userDetails = (UserDetailService) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream()
          .map(item -> item.getAuthority())
          .collect(Collectors.toList());
      if (userDetails.getActive() == false) {
        model.addAttribute("message", "T??i kho???n c???a b???n kh??ng c?? quy???n ????ng nh???p");
        return "home/login";
      } else {
        model.addAttribute("id",userDetails.getId());
        model.addAttribute("userName", userDetails.getUsername());
        model.addAttribute("email", userDetails.getEmail());
        model.addAttribute("fullName", userDetails.getFullName());
        model.addAttribute("gender", userDetails.getGender());
        model.addAttribute("active", userDetails.getActive());
        model.addAttribute("token", jwt);
        model.addAttribute("role", roles);
        return "/home/index";
      }
    } catch (Exception ex) {
      model.addAttribute("message", "Sai th??ng tin ????ng nh???p!");
      return "home/login";
    }

//    return "redirect:/home/index";
  }

  @PostMapping("/home/signUp")
  public String registerUser(@Valid  SignUpDTO signUpRequest ,Model model) throws MessagingException, UnsupportedEncodingException {
    if(signUpRequest.getUsername().equals("") && signUpRequest.getPassword().equals("") && signUpRequest.getFullName().equals("")&&signUpRequest.getEmail().equals("")){
      model.addAttribute("message", "Vui l??ng ??i???n ?????y ????? th??ng tin!");
      return "home/register";
    }
    if (accountDAO.existsByUsername(signUpRequest.getUsername())) {
      model.addAttribute("message", "Error: Username ???? t???n t???i!");
      return "home/register";
    }

    if (accountDAO.existsByEmail(signUpRequest.getEmail())) {
      model.addAttribute("message", "Error: Email ???? t???n t???i!");
      return "home/register";
    }
    if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())){
      model.addAttribute("message", "M???t kh???u kh??ng tr??ng kh???p!");
      return "home/register";
    }

    // Create new user's account
    Account account = new Account();
    account.setUsername(signUpRequest.getUsername());
    account.setEmail(signUpRequest.getEmail());
    account.setFullName(signUpRequest.getFullName());
    account.setGender(signUpRequest.getGender());
    account.setIsActive(signUpRequest.getActive());

    account.setPassword(encoder.encode(signUpRequest.getPassword()));

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
    account.setRole(roles);
    accountService.registerVerify(account, getSiteURL);

    model.addAttribute("messageSuccess", "????ng k?? th??nh c??ng vui l??ng ki???m tra email ????? x??c th???c!");
    return "home/register";
  }
}
