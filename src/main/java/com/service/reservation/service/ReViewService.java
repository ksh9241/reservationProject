package com.service.reservation.service;

import java.util.Map;

public interface ReViewService {
	int insertFileInfo(Map<String,Object> map);

	int insertReservationInfoUserComment(Map<String, Object> commentMap);

	void insertReservationInfoUserCommentImage(Map<String, Object> commentImageMap);
}
