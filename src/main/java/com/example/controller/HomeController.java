package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Product;
import com.example.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;

	@GetMapping(value = {"/home/index", ""})
	public String index(Model model) {
		List<Product> list = productService.findTheBestProduct();
		List<Product> listnewproduct = productService.findTheNewProduct();
		model.addAttribute("itemsindex", list);
		model.addAttribute("newitems", listnewproduct);
		return "home/index";
	}




	@GetMapping("/home/blog")
	public String blog(Model model) {
		return "home/blog";
	}

	@GetMapping("/home/contact")
	public String contact(Model model) {
		return "home/contact";
	}

	@GetMapping("/home/detailblog")
	public String detailblog(Model model) {
		return "home/detailblog";
	}

	@GetMapping("/home/introduce")
	public String introduce(Model model) {
		return "home/introduce";
	}
	
	
	@RequestMapping({ "/admin", "/admin/home/index" })
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}

}
