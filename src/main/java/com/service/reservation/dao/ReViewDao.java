package com.service.reservation.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.FileInfo;

@Repository
public class ReViewDao {
	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert fileInfoInsertAction;
	SimpleJdbcInsert reservationInfoUserCommentInsertAction;
	SimpleJdbcInsert reservationInfoUserCommentImageInsertAction;
	RowMapper<FileInfo> rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);
	
	public ReViewDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		fileInfoInsertAction = new SimpleJdbcInsert(dataSource).withTableName("file_info").usingGeneratedKeyColumns("id");
		reservationInfoUserCommentInsertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment").usingGeneratedKeyColumns("id");
		reservationInfoUserCommentImageInsertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment_image").usingGeneratedKeyColumns("id");
	}
	
	public int fileInfoInsert(Map<String,Object>map) {
		return fileInfoInsertAction.executeAndReturnKey(map).intValue();
	}

	public int reservationInfoUserCommentInsert(Map<String, Object> commentMap) {
		return reservationInfoUserCommentInsertAction.executeAndReturnKey(commentMap).intValue();
	}

	public void reservationInfoUserCommentImageInsert(Map<String, Object> commentImageMap) {
		reservationInfoUserCommentImageInsertAction.executeAndReturnKey(commentImageMap);
	}
}
