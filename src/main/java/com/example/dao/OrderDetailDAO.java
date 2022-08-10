package com.example.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.OrderDetail;


public interface OrderDetailDAO extends JpaRepository<OrderDetail,Integer>{
	@Query("SELECT o FROM OrderDetail o WHERE o.order.id=?1")
	Page<OrderDetail> findAllByHoaDon(int maHD, Pageable page); 
}
