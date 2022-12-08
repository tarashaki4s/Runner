package com.example.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Product;


public interface ProductDAO extends JpaRepository<Product,Integer>{
	@Query("SELECT sp FROM Product sp WHERE sp.id = ?1 and sp.Active = true")
	Product findSanPhamById(Integer id);
	
	@Query("SELECT sp FROM Product sp where sp.Name like %?1%")
	Page<Product> findSanPhamByName(String keywords, Pageable pageable);
	
	@Query("SELECT sp FROM Product sp WHERE sp.Active = true")
	Page<Product> findAllSanPham(Pageable pageable);
	
	@Query("SELECT sp FROM Product sp WHERE sp.Active = true AND sp.category.Id =?1")
	Page<Product> findAllSanPhamByLSP(Integer CategoryId,Pageable pageable);
	
	@Query("SELECT sp FROM Product sp WHERE sp.Active = true AND sp.Price <=?1")
	Page<Product> findByPrice(Double Price,Pageable pageable);
	
	@Query("SELECT sp FROM Product sp WHERE sp.Active = true Order by sp.Price")
	Page<Product> SoftByPriceTang(Pageable pageable);
	
	@Query("SELECT sp FROM Product sp WHERE sp.Active = true Order by sp.Price DESC")
	Page<Product> SoftByPriceGiam(Pageable pageable);
	
	@Query("SELECT sp FROM Product sp WHERE sp.Active = true Order by sp.Name")
	Page<Product> SoftByNameAZ(Pageable pageable);
	
	@Query("SELECT sp FROM Product sp WHERE sp.Active = true Order by sp.Name DESC")
	Page<Product> SoftByNameZA(Pageable pageable);
	
	@Query(value="SELECT TOP(4) * FROM Products WHERE Active = 1 ORDER BY Quantitysold DESC", nativeQuery = true)
	List<Product> findTheBestProduct();
	
	@Query(value="SELECT TOP(4) *  FROM Products WHERE Active = 1 ORDER BY Create_date DESC", nativeQuery = true)
	List<Product> findThenewProduct();

	@Query("SELECT sp FROM Product sp where sp.Name like %?1% and sp.Active=true")
	List<Product> findByName(String keywords);
}
