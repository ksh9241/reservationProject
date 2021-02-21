package com.service.reservation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.CommentDao;
import com.service.reservation.dto.Comment;
import com.service.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<Comment> commentsById(int id) {
		return commentDao.commentsById(id);
	}

}
