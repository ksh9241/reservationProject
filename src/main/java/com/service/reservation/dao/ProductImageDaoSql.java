package com.service.reservation.dao;

public class ProductImageDaoSql {
	public static final String PRODUCT_IMAGES_BY_ID ="SELECT sub.* FROM display_info JOIN " + 
			"(SELECT p.product_id productId, p.id productImageId, p.type type, p.file_id fileInfoId, " + 
			"f.file_name fileName, f.save_file_name saveFileName, f.content_type contentType, " + 
			"f.delete_flag deleteFlag, f.create_date createDate, f.modify_date modifyDate " + 
			"FROM product_image p JOIN file_info f " + 
			"ON p.file_id = f.id " + 
			"WHERE type IN('ma','et')) sub " + 
			"ON sub.productId = display_info.product_id " + 
			"WHERE display_info.id = :id LIMIT 2";
}
