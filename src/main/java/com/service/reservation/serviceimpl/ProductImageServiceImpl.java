package com.service.reservation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.ProductImageDao;
import com.service.reservation.dto.ProductImage;
import com.service.reservation.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	ProductImageDao productImageDao;
	
	@Override
	public List<ProductImage> productsImageById(int displayInfoId) {
		return productImageDao.productsImageById(displayInfoId);
	}

}
