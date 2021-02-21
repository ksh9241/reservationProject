package com.service.reservation.dao;

public class ProductDaoSql {
	public static final String PRODUCT_BY_CATEGORYID = "SELECT subsub.display_info_id,subsub.product_id, subsub. product_description, " + 
			" subsub.place_name, subsub.product_content, f.save_file_name" + 
			" FROM file_info f JOIN " + 
			" (SELECT sub.*, image.file_id " + 
			" FROM product_image image JOIN " + 
			"(SELECT d.id display_info_id, p.id product_id, p.description product_description, " + 
			" d.place_name, p.content product_content,p.category_id " + 
			"FROM product p JOIN display_info d " + 
			"ON p.id = d.product_id) sub " + 
			"ON sub.product_id = image.product_id " + 
			"WHERE image.type = 'th') subsub " + 
			"ON subsub.file_id = f.id WHERE category_id = :categoryId LIMIT :start, :limit";
	
	public static final String PRODUCT_ALL = "SELECT subsub.display_info_id,subsub.product_id, subsub. product_description, " + 
			" subsub.place_name, subsub.product_content, f.save_file_name" + 
			" FROM file_info f JOIN " + 
			" (SELECT sub.*, image.file_id " + 
			" FROM product_image image JOIN " + 
			"(SELECT d.id display_info_id, p.id product_id, p.description product_description, " + 
			" d.place_name, p.content product_content,p.category_id " + 
			"FROM product p JOIN display_info d " + 
			"ON p.id = d.product_id) sub " + 
			"ON sub.product_id = image.product_id " + 
			"WHERE image.type = 'th') subsub " + 
			"ON subsub.file_id = f.id LIMIT :start, :limit";
	
}
