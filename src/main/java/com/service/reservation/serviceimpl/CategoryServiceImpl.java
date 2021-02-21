package com.service.reservation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.reservation.dao.CategoryDao;
import com.service.reservation.dto.Category;
import com.service.reservation.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	@Transactional
	public List<Category> categoryList() {
		return categoryDao.CategoryAll();
	}

}
