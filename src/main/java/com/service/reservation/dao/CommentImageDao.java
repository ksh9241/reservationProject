package com.service.reservation.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.CommentImage;
import static com.service.reservation.dao.CommentImageDaoSql.*;

@Repository
public class CommentImageDao {
	NamedParameterJdbcTemplate jdbc;
	RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	
	public CommentImageDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<CommentImage> commentImgByCommentId(int id){
		Map<String,Integer> map = new HashMap<>();
		map.put("id", id);
		return jdbc.query(COMMENTIMAGE_BY_COMMENTID,map, rowMapper);
	}
}
