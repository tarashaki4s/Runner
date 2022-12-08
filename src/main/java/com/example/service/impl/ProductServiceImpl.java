package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dao.ProductDAO;
import com.example.entity.Product;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO dao;

	@Override
	public Page<Product> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return dao.findAllSanPham(page);
	}

	@Override
	public List<Product> findAlltoList() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Product create(Product sp) {
		// TODO Auto-generated method stub
		return dao.save(sp);
	}

	@Override
	public void delete(Product sp) {
		Product sanpham = dao.findSanPhamById(sp.getId());
		sp.setActive(false);
		dao.save(sp);
		
	}

	@Override
	public Product update(Product sp) {
		// TODO Auto-generated method stub
		return dao.save(sp);
	}

	@Override
	public Page<Product> findSanPhamByName(String keywords, Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findSanPhamByName(keywords, pageable);
	}

	@Override
	public Page<Product> findSanPhamByLSP(Integer maLSP, Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findAllSanPhamByLSP(maLSP, pageable);
	}

	@Override
	public Page<Product> findByPrice(Double price, Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findByPrice(price, pageable);
	}

	@Override
	public Page<Product> SoftByPriceTang(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.SoftByPriceTang(pageable);
	}

	@Override
	public Page<Product> SoftByNameAZ(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.SoftByNameAZ(pageable);
	}

	@Override
	public List<Product> findTheBestProduct() {
		// TODO Auto-generated method stub
		return dao.findTheBestProduct();
	}

	@Override
	public List<Product> findTheNewProduct() {
		return dao.findThenewProduct();
	}

	@Override
	public Page<Product> SoftByPriceGiam(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.SoftByPriceGiam(pageable);
	}

	@Override
	public Page<Product> SoftByNameZA(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.SoftByNameZA(pageable);
	}



	@Override
	public Product findSanPhamById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findSanPhamById(id);
	}

	@Override
	public List<Product> findProduct(String request) {
		// TODO Auto-generated method stub
		return dao.findByName(request);
	}

	@Override
	public Product updateByOrder(Product product) {
		product.setQuantitysold(product.getQuantitysold()+1);
		product.setAmount(product.getAmount()-1);
		return dao.save(product);
	}

}
