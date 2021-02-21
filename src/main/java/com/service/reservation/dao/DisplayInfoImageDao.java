package com.service.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.DisplayInfoImage;
import static com.service.reservation.dao.DisplayInfoImageDaoSql.*;

@Repository
public class DisplayInfoImageDao {
	NamedParameterJdbcTemplate jdbc;
	RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);

	public DisplayInfoImageDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	public DisplayInfoImage displayInfoImageById(int displayInfoId) {
		Map<String,Integer> map = new HashMap<>();
		map.put("id", displayInfoId);
		return jdbc.queryForObject(DISPLAY_INFO_IMAGE_BY_ID, map, rowMapper);
	}
}
