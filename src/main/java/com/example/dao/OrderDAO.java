package com.example.dao;

import com.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.account.id=?1")
    List<Order> findByAccountId(Long id);

    @Query("SELECT o FROM Order o WHERE o.status=false")
    List<Order> findByStatus();

    @Query("SELECT o FROM Order o WHERE o.status=true")
    List<Order> findByStatusTrue();
}
