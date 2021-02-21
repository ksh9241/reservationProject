package com.service.reservation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.PromotionDao;
import com.service.reservation.dto.Promotion;
import com.service.reservation.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	PromotionDao promotionDao;
	
	@Override
	public List<Promotion> promotionImg() {
		return promotionDao.promotionImgList();
	}

}
