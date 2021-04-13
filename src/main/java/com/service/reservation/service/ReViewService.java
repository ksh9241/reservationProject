package com.service.reservation.service;

import java.util.Map;

import com.service.reservation.dto.Test;

public interface ReViewService {
	int insertFileInfo(Map<String,Object> map);

	int insertReservationInfoUserComment(Map<String, Object> commentMap);

	void insertReservationInfoUserCommentImage(Map<String, Object> commentImageMap);

	Test selectComment(int commentId);

	int checkImage(int commentId);

	Test selectNotImgComment(int commentId);

	void deleteComment(int commentId);

	void deleteFileInfo(int fileId);

	void deleteCommentImage(int commentId);
}
