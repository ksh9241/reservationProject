package com.service.reservation.dao;

public class CommentDaoSql {
	public static final String COMMENTS_BY_ID = "SELECT sub.* FROM display_info JOIN " + 
			"(SELECT r.id commentId, r.product_id productId, r.reservation_info_id reservationInfoId, " + 
			"r.score score, r.comment comment, i.reservation_name reservationName, i.reservation_tel reservationTelephone, " + 
			"i.reservation_email reservationEmail, i.reservation_date reservationDate, i.create_date createDate, " + 
			"i.modify_date modifyDate " + 
			"FROM reservation_user_comment r JOIN reservation_info i " + 
			"ON r.reservation_info_id = i.id) sub " + 
			"ON sub.productId = display_info.id " + 
			"WHERE display_info.id = :id " + 
			"ORDER BY commentId DESC";
}
