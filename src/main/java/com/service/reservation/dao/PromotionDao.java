package com.service.reservation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.Promotion;
import static com.service.reservation.dao.PromotionDaoSql.*;

@Repository
public class PromotionDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	
	public PromotionDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Promotion> promotionImgList(){
		List<Promotion> list = jdbc.query(PROMOTION_IMG_ALL, rowMapper);
		return list;
	}
}
