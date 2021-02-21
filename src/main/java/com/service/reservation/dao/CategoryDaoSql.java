package com.service.reservation.dao;

public class CategoryDaoSql {
	public static final String SELECT_ALL = "SELECT category.id, category.name, count(sub.category_id) AS count " + 
			" FROM category JOIN " + 
			" (SELECT product.id, product.category_id " + 
			" FROM product JOIN display_info " + 
			" ON product.id = display_info.product_id) AS sub " + 
			" ON category.id = sub.category_id " + 
			" GROUP BY category_id";
}
