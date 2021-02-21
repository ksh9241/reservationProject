package com.service.reservation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.ProductPriceDao;
import com.service.reservation.dto.ProductPrice;
import com.service.reservation.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService{

	@Autowired
	ProductPriceDao productPriceDao;
	
	@Override
	public List<ProductPrice> productPriceById(int displayInfoId) {
		return productPriceDao.productPriceById(displayInfoId);
	}

}
