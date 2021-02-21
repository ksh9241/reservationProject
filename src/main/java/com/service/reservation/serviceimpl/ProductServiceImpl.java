package com.service.reservation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.ProductDao;
import com.service.reservation.dto.Product;
import com.service.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao ProductDao;
	
	@Override
	public List<Product> productListAll(int startNum) {
		return this.ProductDao.productListAll(startNum);
	}

	@Override
	public List<Product> productListByCategoryId(int categoryId, int start) {
		return this.ProductDao.productListByCategoryId(categoryId, start);
	}

}
