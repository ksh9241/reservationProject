package com.service.reservation.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.ProductImage;
import static com.service.reservation.dao.ProductImageDaoSql.*;

@Repository
public class ProductImageDao {

	NamedParameterJdbcTemplate jdbc;
	RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	
	public ProductImageDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductImage> productsImageById(int displayInfoId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("id", displayInfoId);
		return jdbc.query(PRODUCT_IMAGES_BY_ID,map,rowMapper);
	}
}
