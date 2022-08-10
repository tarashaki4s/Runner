package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.CategoryDAO;
import com.example.entity.Category;


public interface CategoryService {
	List<Category> findAll();

	

}
