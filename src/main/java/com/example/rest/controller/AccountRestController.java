//package com.example.rest.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import com.example.service.UserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.dao.AccountDAO;
//import com.example.entity.Account;
//import com.example.service.AccountService;
//
//@CrossOrigin("*")
//@RestController
//@RequestMapping("/rest/accounts")
//public class AccountRestController {
//	@Autowired
//	AccountDAO accountService;
//	@Autowired
//	AccountService accService;
//	@GetMapping
//	public List<Account>getAccounts(@RequestParam("admin") Optional<Boolean> admin){
//		if(admin.orElse(false)) {
//			return UserDetailService.getAdministrators();
//		}
//		return accountService.findAll();
//	}
//
////
//
//}
