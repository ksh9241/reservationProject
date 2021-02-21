package com.service.reservation.service;

import java.util.List;

import com.service.reservation.dto.CommentImage;

public interface CommentImageService {
	List<CommentImage> commentImgByCommentId(int id);
}
