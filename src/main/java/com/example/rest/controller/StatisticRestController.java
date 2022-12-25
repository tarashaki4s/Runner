package com.example.rest.controller;

import com.example.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/statistics")
public class StatisticRestController {
    @Autowired
    StatisticService statisticService;

    @GetMapping("/sales")
    public List<Integer> getSalesBy12Month() { return statisticService.getSalesBy12Month(); }

    @GetMapping("/revenue")
    public List<Integer> getRevenueBy12Month() { return statisticService.getRevenueBy12Month(); }
}
