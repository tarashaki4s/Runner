package com.example.service;

import java.util.List;

import com.example.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	Order create(JsonNode orderData);

    Order findById(Long id);

    List<Order> findByAccountId(Long accountId);

    List<Order> findByStatus();

    Order updateStatus(Order order);

}
