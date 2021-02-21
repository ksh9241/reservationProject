package com.service.reservation.dao;

public class PromotionDaoSql {
	public static final String PROMOTION_IMG_ALL = "SELECT sub.id, sub.product_id, f.save_file_name " + 
			" FROM file_info f join " + 
			" (SELECT p.id, p.product_id, pimg.file_id " + 
			" FROM promotion p join product_image pimg " + 
			" ON p.product_id = pimg.product_id " + 
			" WHERE pimg.type = 'th') as sub " + 
			" ON f.id = sub.file_id";
}
