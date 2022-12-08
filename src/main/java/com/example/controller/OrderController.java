package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    AccountService accountService;

    @GetMapping("/order/checkout")
    public String checkout(){
        return "home/checkout";
    }

    @RequestMapping("/order/list")
    public String list(Model model, HttpServletRequest request){
        String username = request.getRemoteUser();
        Account account =accountService.findByUserName(username);
        model.addAttribute("orders",orderService.findByAccountId(account.getId()));
        return "home/listorder";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(@PathVariable("id")Long id, Model model){
        model.addAttribute("order",orderService.findById(id));
        return "home/detailorder";
    }
}
