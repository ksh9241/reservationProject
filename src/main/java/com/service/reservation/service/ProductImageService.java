package com.service.reservation.service;

import java.util.List;

import com.service.reservation.dto.ProductImage;

public interface ProductImageService {
	List<ProductImage> productsImageById(int displayInfoId);
}
