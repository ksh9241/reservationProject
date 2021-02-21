package com.service.reservation.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.DisplayInfoImageDao;
import com.service.reservation.dto.DisplayInfoImage;
import com.service.reservation.service.DisplayInfoImageService;

@Service
public class DisplayInfoImageServiceImpl implements DisplayInfoImageService {
	@Autowired
	DisplayInfoImageDao displayInfoImageDao;
	
	@Override
	public DisplayInfoImage displayInfoImageById(int displayInfoId) {
		return displayInfoImageDao.displayInfoImageById(displayInfoId);
	}

}
