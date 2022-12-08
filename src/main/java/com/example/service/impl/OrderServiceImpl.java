package com.example.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dao.ProductDAO;
import com.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.OrderDAO;
import com.example.dao.OrderDetailDAO;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
    OrderDAO oDao;

    @Autowired
    OrderDetailDAO detailDAO;

    @Autowired
    ProductDAO productDAO;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();

        Order order = mapper.convertValue(orderData,Order.class);
        oDao.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"),type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        detailDAO.saveAll(details);
        return order;
    }

//    public Product updateProduct(Product product) {
//        product.setAmount(product.getAmount()-1);
//        product.setQuantitysold(product.getQuantitysold()+1);
//        return product;
//    }

    @Override
    public Order findById(Long id) {
        return oDao.findById(id).get();
    }

    @Override
    public List<Order> findByAccountId(Long accountId) {
        return oDao.findByAccountId(accountId);
    }

    @Override
    public List<Order> findByStatus() {
        return oDao.findByStatus();
    }

    @Override
    public Order updateStatus(Order order) {
        // TODO Auto-generated method stub
        order.setStatus(true);
        return oDao.save(order);
    }

}
