package com.example.service;

import com.example.entity.statistic.IRevenueByMonth;
import com.example.entity.statistic.ISalesByMonth;

import java.util.List;

public interface StatisticService {
    List<Integer> getSalesBy12Month();
    List<Integer> getRevenueBy12Month();
}
