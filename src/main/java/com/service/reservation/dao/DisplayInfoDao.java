package com.service.reservation.dao;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.DisplayInfo;
import static com.service.reservation.dao.DisplayInfoDaoSql.*;

@Repository
public class DisplayInfoDao {
	NamedParameterJdbcTemplate jdbc;
	RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	
	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public DisplayInfo displayInfoById(int displayInfoId) {
		Map<String,Integer> map = new HashMap<>();
		map.put("id", displayInfoId);
		return jdbc.queryForObject(DISPLAY_INFO_BY_ID, map, rowMapper);
		 
	}
}
