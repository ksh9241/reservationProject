package com.service.reservation.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.ReViewDao;
import com.service.reservation.service.ReViewService;

@Service
public class ReViewServiceImpl implements ReViewService {
	@Autowired
	ReViewDao reViewDao; 
	
	@Override
	public int insertFileInfo(Map<String, Object> map) {
		return reViewDao.insertFileInfo(map);
	}

	@Override
	public int insertReservationInfoUserComment(Map<String, Object> commentMap) {
		return reViewDao.insertReservationInfoUserComment(commentMap);
	}

	@Override
	public void insertReservationInfoUserCommentImage(Map<String, Object> commentImageMap) {
		reViewDao.insertReservationInfoUserCommentImage(commentImageMap);
	}

}
