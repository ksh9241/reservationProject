package com.service.reservation.service;

import java.util.List;

import com.service.reservation.dto.ProductPrice;

public interface ProductPriceService {
	List<ProductPrice> productPriceById(int displayInfoId);
}
