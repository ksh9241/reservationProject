package com.service.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.service.reservation.dto.Product;
import static com.service.reservation.dao.ProductDaoSql.*;

@Repository
public class ProductDao {
	
	private static final int LIMIT = 4;
	
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	public ProductDao(DataSource dataSource){
		jdbc= new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Product> productListByCategoryId(int categoryId, int startNum){
		Map<String,Integer> map = new HashMap<>();
		map.put("categoryId", categoryId);
		map.put("start", startNum);
		map.put("limit", LIMIT);
		return jdbc.query(PRODUCT_BY_CATEGORYID ,map , rowMapper);
	}
	
	public List<Product> productListAll(int startNum){
		Map<String,Integer> map = new HashMap<>();
		map.put("start", startNum);
		map.put("limit", LIMIT);
		return jdbc.query(PRODUCT_ALL ,map , rowMapper);
	}
}
