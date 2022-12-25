package com.example.service.impl;

import com.example.dao.OrderDAO;
import com.example.entity.statistic.IRevenueByMonth;
import com.example.entity.statistic.ISalesByMonth;
import com.example.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    OrderDAO orderDao;

    @Override
    public List<Integer> getSalesBy12Month() {
        var SalesInMonth = orderDao.getSalesByMonth();
        // set 0 value for other months
        List<Integer> revenueIn12Month = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        SalesInMonth.forEach(i -> revenueIn12Month.set(i.getMonth() - 1, i.getSales()));
        return revenueIn12Month;
    }

    @Override
    public List<Integer> getRevenueBy12Month() {
        var revenueInMonth = orderDao.getRevenueByMonth();
        // set 0 value for other months
        List<Integer> revenueIn12Month = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        revenueInMonth.forEach(i -> revenueIn12Month.set(i.getMonth() - 1, i.getRevenue()));
        return revenueIn12Month;
    }
}
