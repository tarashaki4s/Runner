package com.example.service;

import java.util.List;

import com.example.constant.SortConstant;
import com.example.entity.Rate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RateService {
	public Page<Rate> findAll(Pageable pageable);

	public Page<Rate> sortByDate(boolean sortType, Pageable pageable);

	public Page<Rate> findByUserName(String username, Pageable pageable);

	public Page<Rate> findByProductDESC(int productId, Pageable pageable);

	public Rate update(Rate Rate);

	public void deleteById(int id);

	public Rate create(Rate Rate);
}
