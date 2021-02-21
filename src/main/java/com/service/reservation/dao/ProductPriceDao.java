package com.service.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.ProductPrice;
import static com.service.reservation.dao.ProductPriceDaoSql.*;

@Repository
public class ProductPriceDao {
	
	NamedParameterJdbcTemplate jdbc;
	RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	
	public ProductPriceDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductPrice> productPriceById(int displayInfoId) {
		Map<String,Integer> map = new HashMap<>();
		map.put("id", displayInfoId);
		return jdbc.query(PRODUCT_PRICE_BY_ID,map, rowMapper);
	}
}
