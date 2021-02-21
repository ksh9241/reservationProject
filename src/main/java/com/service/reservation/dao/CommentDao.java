package com.service.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.Comment;
import static com.service.reservation.dao.CommentDaoSql.*;

@Repository
public class CommentDao {
	NamedParameterJdbcTemplate jdbc;
	RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	
	public CommentDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Comment> commentsById(int id){
		Map<String,Integer> map = new HashMap<>();
		map.put("id", id);
		List<Comment> list =jdbc.query(COMMENTS_BY_ID,map ,rowMapper);
		return list;
	}
}
