package com.service.reservation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.CommentImageDao;
import com.service.reservation.dto.CommentImage;
import com.service.reservation.service.CommentImageService;

@Service
public class CommentImageServiceImpl implements CommentImageService {
	@Autowired
	CommentImageDao commentImageDao;
	
	@Override
	public List<CommentImage> commentImgByCommentId(int id) {
		return commentImageDao.commentImgByCommentId(id);
	}

}
