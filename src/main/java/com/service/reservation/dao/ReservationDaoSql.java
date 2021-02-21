package com.service.reservation.dao;

public class ReservationDaoSql {
	public static final String RESERVATIONS_BY_EMAIL = "SELECT r.id reservationInfoId,r.product_id productId, r.display_info_id displayInfoId, r.reservation_name reservationName, " + 
			"r.reservation_tel reservationTelephone, r.reservation_email reservationEmail, r.cancel_flag cancelYn, r.reservation_date reservationDate, " + 
			"r.create_date createDate, r.modify_date modifyDate, sub.totalPrice FROM reservation_info r JOIN " + 
			" (SELECT info_price.reservation_info_id, sum(price * count) totalPrice FROM reservation_info_price info_price JOIN product_price price " + 
			" ON info_price.product_price_id = price.id GROUP BY reservation_info_id) sub " + 
			" ON r.id = sub.reservation_info_id " + 
			" WHERE r.reservation_email = :email";
	
	public static final String RESERVATION_EDIT_BY_ID = "UPDATE reservation_info SET cancel_flag = 1 WHERE id = :id";
}
