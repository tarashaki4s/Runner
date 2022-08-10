package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.AuthorityService;

@Controller
public class SecurityController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	AuthorityService authorityService;
	
    @RequestMapping("/security/login/form")
    public String loginForm(Model model){
        model.addAttribute("msg","Vui lòng đăng nhập!");
        return "home/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model){
        model.addAttribute("msg","Đăng nhập thành công");
        return "home/index";
    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model){
        model.addAttribute("msg","Sai thông tin đăng nhập");
        return "home/login";
    }

    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model){
        model.addAttribute("msg","Không có quyền truy xuất");
        return "home/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model){
        model.addAttribute("msg","Bạn đã đăng xuất!");
        return "home/login";
    }
    
    @GetMapping("/home/register")
	public String SignUp() {
		return "home/register";
	}
	
	
	@PostMapping("/home/signUp")
	public String SignUp(Account account, Model model, @RequestParam("txtUserName") String username,
			@RequestParam("txtFullName") String fullname,
			@RequestParam("txtPassword") String password,
			@RequestParam("txtEmail") String email,@RequestParam("Gender") Boolean gender) {
		List<Account> list = accountService.findAll();
		for(Account i:list) {
			if(i.getUsername().equals(username)) {
				 model.addAttribute("msg", "Tài khoản đã tồn tại!");
				 return "/home/register";	
			}
			else if(username.equals("")) {
				return "/home/register";	
			}
			else {
				
				account.setFullname(fullname);
				account.setPassword(password);
				account.setUsername(username);
				account.setEmail(email);
				account.setGender(gender);
				accountService.create(account);
				return "redirect:/home/login";
			}
		}
		return "redirect:/home/login";
	}
}
