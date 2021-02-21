package com.service.reservation.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.DisplayInfoDao;
import com.service.reservation.dto.DisplayInfo;
import com.service.reservation.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	DisplayInfoDao displayInfoDao;

	@Override
	public DisplayInfo displayInfoById(int displayInfoId) {
		return this.displayInfoDao.displayInfoById(displayInfoId);
	}
	
	
}
