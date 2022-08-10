package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.Product;


public interface ProductService {
	Page<Product> findAll(Pageable page);
	
	List<Product> findAlltoList();
	
	Product create(Product sp);
	
	void delete(Product sp);
	
	Product update(Product sp);
	
	Product findSanPhamById(Integer id);
	
	Page<Product> findSanPhamByName(String keywords, Pageable pageable);
	
	Page<Product> findSanPhamByLSP(Integer maLSP, Pageable pageable);
	
	Page<Product> findPrice(Integer price, Pageable pageable);
	
	Page<Product> SoftByPriceTang( Pageable pageable);
	
	Page<Product> SoftByNameAZ( Pageable pageable);
	
	Page<Product> SoftByPriceGiam( Pageable pageable);
	
	Page<Product> SoftByNameZA( Pageable pageable);
	
	List<Product> findTheBestProduct();
	
	Page<Product> findTheBestProductSale( Pageable pageable);
	
	List<Product> findProduct(String request);

}
