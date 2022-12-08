package com.example.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Product;
import com.example.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	ProductService productService;

	@GetMapping()
	public List<Product> getAll() {
		return productService.findAlltoList();
	}

	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.findSanPhamById(id);
	}

	@PostMapping()
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}

	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
		return productService.update(product);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		Product sp=productService.findSanPhamById(id);
		productService.delete(sp);
	}

	@PutMapping("/updateByOrder")
	public Product updateByOrder(@RequestParam("id") Integer id) {
		Product product=productService.findSanPhamById(id);
		return productService.updateByOrder(product);
	}


	@GetMapping("/search")
	public List<Product> findProductByName(@RequestParam("name")Optional<String> request) {
		String kwords = request.orElse("");
		return productService.findProduct("%"+kwords+"%");
	}

}
