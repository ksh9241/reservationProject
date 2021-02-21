package com.service.reservation.service;

import java.util.List;

import com.service.reservation.dto.Comment;

public interface CommentService {
	List<Comment> commentsById(int id);
}
