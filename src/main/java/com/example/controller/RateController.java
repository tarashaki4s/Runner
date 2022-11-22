package com.example.controller;

import com.example.entity.Rate;
import com.example.service.AccountService;
import com.example.service.ProductService;
import com.example.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class RateController {

    @Autowired
    RateService rateService;

    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @GetMapping("/rate/{productId}/{userName}")
    public String helloGet() {
        return "redirect:/product/detail/1";
    }

    @PostMapping("/rate/{productId}/{userName}")
    public String createRate(@PathVariable("productId") int productId,
                             @PathVariable("userName") String userName,
                             @RequestParam("comment") String comment,
                             @RequestParam("stars") int stars) {

        var product = productService.findSanPhamById(productId);
        var account = accountService.findByUserName(userName);

        var rate = new Rate();

        rate.setComment(comment);
        rate.setStars(stars);
        rate.setRatedDate(new Date());

        rate.setProduct(product);
        rate.setAccount(account);

        rateService.create(rate);
        return "redirect:/product/detail/" + productId;
    }

}
