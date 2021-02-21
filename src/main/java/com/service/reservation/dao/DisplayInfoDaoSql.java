package com.service.reservation.dao;

public class DisplayInfoDaoSql {
	public static final String DISPLAY_INFO_BY_ID ="SELECT sub.*, c.name categoryName FROM category c JOIN " + 
			" (SELECT p.id productId, p.category_id categoryId, d.id displayInfoId, " + 
			" p.description productDescription, p.content productContent, p.event productEvent, " + 
			" d.opening_hours openingHours, d.place_name placeName, d.place_lot placeLot, " + 
			" d.place_street placeStreet, d.tel telephone, d.homepage homepage, d.email email, " + 
			" d.create_date createDate, d.modify_date modifyDate FROM display_info d JOIN product p " + 
			" ON p.id = d.product_id) sub " + 
			" ON sub.categoryId = c.id " + 
			" WHERE sub.displayInfoId = :id";
}
