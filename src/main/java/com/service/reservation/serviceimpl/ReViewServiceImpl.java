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
	public int fileInfoInsert(Map<String, Object> map) {
		return reViewDao.fileInfoInsert(map);
	}

	@Override
	public int reservationInfoUserCommentInsert(Map<String, Object> commentMap) {
		return reViewDao.reservationInfoUserCommentInsert(commentMap);
	}

	@Override
	public void reservationInfoUserCommentImageInsert(Map<String, Object> commentImageMap) {
		reViewDao.reservationInfoUserCommentImageInsert(commentImageMap);
	}

}
