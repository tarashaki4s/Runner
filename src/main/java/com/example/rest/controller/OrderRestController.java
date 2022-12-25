package com.example.rest.controller;

import com.example.entity.Order;
import com.example.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping("")
    public Order create(@RequestBody JsonNode orderData){
        return orderService.create(orderData);
    }

    @GetMapping("/list")
    public List<Order> findAllOrder() {
        return  orderService.findByStatus();
    }

    @GetMapping("/list/orderconfirm")
    public List<Order> findAllOrderTrue() {
        return  orderService.findByStatusTrue();
    }

    @PutMapping("/updateStatus/{id}")
    public Order update(@PathVariable("id") Integer id, @RequestBody Order order) {
        return orderService.updateStatus(order);
    }
}
