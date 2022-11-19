package com.example.service;

import java.util.List;

import com.example.entity.Rate;

public interface RateService {
	public List<Rate> findAll();

	public Rate findById(String username);

	public Rate update(Rate Rate);

	public void deleteById(int id);

	public Rate create(Rate Rate);
}
