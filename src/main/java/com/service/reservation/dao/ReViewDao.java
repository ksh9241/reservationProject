package com.service.reservation.dao;

import java.util.HashMap;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.FileInfo;
import com.service.reservation.dto.Test;

import static com.service.reservation.dao.ReViewDaoSql.*;

@Repository
public class ReViewDao {
	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert fileInfoInsertAction;
	SimpleJdbcInsert reservationInfoUserCommentInsertAction;
	SimpleJdbcInsert reservationInfoUserCommentImageInsertAction;
	RowMapper<FileInfo> rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);
	RowMapper<Test> testRowMapper = BeanPropertyRowMapper.newInstance(Test.class);
	
	public ReViewDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		fileInfoInsertAction = new SimpleJdbcInsert(dataSource).withTableName("file_info").usingGeneratedKeyColumns("id");
		reservationInfoUserCommentInsertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment").usingGeneratedKeyColumns("id");
		reservationInfoUserCommentImageInsertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment_image").usingGeneratedKeyColumns("id");
	}
	
	public int insertFileInfo(Map<String,Object>map) {
		return fileInfoInsertAction.executeAndReturnKey(map).intValue();
	}

	public int insertReservationInfoUserComment(Map<String, Object> commentMap) {
		return reservationInfoUserCommentInsertAction.executeAndReturnKey(commentMap).intValue();
	}

	public void insertReservationInfoUserCommentImage(Map<String, Object> commentImageMap) {
		reservationInfoUserCommentImageInsertAction.executeAndReturnKey(commentImageMap);
	}

	public Test selectComment(int commentId) {
		Map<String,Object> map = new HashMap<>();
		map.put("commentId", commentId);
		return jdbc.queryForObject(SELECT_COMMENT_BY_COMMENTID, map,testRowMapper);
	}

	public int checkImage(int commentId) {
		Map<String,Object> map = new HashMap<>();
		map.put("commentId", commentId);
		
		return jdbc.queryForObject(SELECT_COMMENT_IMAGE_BY_COMMENTID, map, Integer.class); 
	}

	public Test selectNotImgComment(int commentId) {
		Map<String,Object> map = new HashMap<>();
		map.put("commentId", commentId);
		return jdbc.queryForObject(SELECT_NOT_IMG_COMMENT_BY_COMMENTID, map, testRowMapper);
	}

	public void deleteComment(int commentId) {
		Map<String,Object> map = new HashMap<>();
		map.put("commentId", commentId);
		jdbc.update(DELETE_COMMENT, map);
	}

	public void deleteFileInfo(int fileId) {
		Map<String,Object> map = new HashMap<>();
		map.put("fileId", fileId);
		jdbc.update(DELETE_FILE_INFO,map);
	}

	public void deleteCommentImage(int commentId) {
		Map<String,Object> map = new HashMap<>();
		map.put("commentId", commentId);
		jdbc.update(DELETE_COMMENT_IMAGE, map);
	}
}
