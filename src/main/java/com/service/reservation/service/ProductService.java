package com.service.reservation.service;

import java.util.List;

import com.service.reservation.dto.Product;

public interface ProductService {
	List<Product> productListAll(int startNum);

	List<Product> productListByCategoryId(int categoryId, int start);
}
