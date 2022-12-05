package com.example.dao;

import com.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Long> {
    @Query(value=("SELECT o FROM Order o WHERE o.account.username=?1"),nativeQuery = true)
    List<Order> findByUsername(String username);
}
