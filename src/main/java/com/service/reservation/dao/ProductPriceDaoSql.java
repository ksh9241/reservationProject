package com.service.reservation.dao;

public class ProductPriceDaoSql {
	public static final String PRODUCT_PRICE_BY_ID = "SELECT sub.* FROM display_info JOIN " + 
			"(SELECT id productPriceId, product_id productId, price_type_name priceTypeName, " + 
			"price, discount_rate discountRate, create_date createDate, modify_date modifyDate " + 
			"FROM product_price " + 
			"ORDER BY id DESC) sub " + 
			"ON sub.productId = display_info.product_id " + 
			"WHERE display_info.id = :id";
}
