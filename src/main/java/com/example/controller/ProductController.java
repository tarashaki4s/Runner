package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.constant.ProductSortType;
import com.example.service.CategoryService;
import com.example.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Product;
import com.example.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	RateService rateService;

	@GetMapping("/product/detail/{id}")
	public String detailproduct(@PathVariable("id") Integer id, Model model) {
		Product sp = productService.findSanPhamById(id);
		int page = 0;
		int pageSize = 20;
		boolean SORT_BY_DESC = false;
		var rates = rateService.findByProductDESC(id, PageRequest.of(page,pageSize));
		model.addAttribute("rates", rates);
		model.addAttribute("product", sp);
		return "home/detailproduct";
	}

	@GetMapping("/product/list")
	public String product(Model model,
						  @RequestParam("page") Optional<Integer> page,
						  @RequestParam("category") Optional<Integer> categoryId,
	 					  @RequestParam("sortType") Optional<Integer> sortType) {

		Pageable pageable = PageRequest.of(page.orElse(0), 8);
		Page<Product> list = Page.empty();

		if(!sortType.isEmpty()) {
			if(sortType.get() == ProductSortType.NEWEST)  {
				pageable = PageRequest.of(page.orElse(0), 8, Sort.by(Sort.Direction.DESC, "createDate"));
			} else if (sortType.get() == ProductSortType.BESTSELLER) {
				pageable = PageRequest.of(page.orElse(0), 8, Sort.by(Sort.Direction.DESC, "Quantitysold"));
			} else if (sortType.get() == ProductSortType.PRICE_ASCENDING) {
				pageable = PageRequest.of(page.orElse(0), 8, Sort.by(Sort.Direction.ASC, "Price"));
			} else if (sortType.get() == ProductSortType.PRICE_DESCENDING) {
				pageable = PageRequest.of(page.orElse(0), 8, Sort.by(Sort.Direction.DESC, "Price"));
			}
			model.addAttribute("sortType", sortType.get());
		}

		if(categoryId.isEmpty()) {
			list = productService.findAll(pageable);
		} else {
			list = productService.findSanPhamByLSP(categoryId.get(), pageable);
			model.addAttribute("category", categoryId.get());
		}
		var totalPage = list.getTotalPages();
		var cuttentPage = page.isEmpty() ? 0 : page.get();
		var categories = categoryService.findAll();

		model.addAttribute("currentPage", cuttentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("items", list);
		model.addAttribute("categories", categories);


		return "home/product";
	}



}
