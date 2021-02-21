package com.service.reservation.service;

import java.util.Map;

public interface ReViewService {
	int fileInfoInsert(Map<String,Object> map);

	int reservationInfoUserCommentInsert(Map<String, Object> commentMap);

	void reservationInfoUserCommentImageInsert(Map<String, Object> commentImageMap);
}
