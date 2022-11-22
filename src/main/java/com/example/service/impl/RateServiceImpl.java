package com.example.service.impl;

import com.example.constant.SortConstant;
import com.example.dao.RateDao;
import com.example.entity.Rate;
import com.example.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    RateDao dao;

    @Override
    public Page<Rate> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Page<Rate> sortByDate(boolean sortType, Pageable pageable) {
        String sortString = "";
        if(sortType == SortConstant.ASC) {
            return dao.sortByDateASC(pageable);
        } else {
            return dao.sortByDateDESC(pageable);
        }
    }

    @Override
    public Page<Rate> findByUserName(String username, Pageable pageable) {
        return dao.findByUserName(username, pageable);
    }

    @Override
    public  Page<Rate> findByProductDESC(int productId, Pageable pageable) {
        return dao.findByProductDESC(productId, pageable);
    }

    @Override
    public Rate create(Rate rate) {
        return dao.save(rate);
    }

    @Override
    public Rate update(Rate rate) {
        return dao.save(rate);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

}
