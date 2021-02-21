package com.service.reservation.dao;

public class CommentImageDaoSql {
	public static final String COMMENTIMAGE_BY_COMMENTID = "SELECT r.id imageId,r.reservation_info_id reservationInfoId ,r.reservation_user_comment_id  reservationUserCommentId, " + 
			"r.file_id fileId, f.file_name fileName, f.save_file_name saveFileName, f.content_type contentType, " + 
			"f.delete_flag deleteFlag, f.create_date createDate, f.modify_date modifyDate " + 
			"FROM reservation_user_comment_image r JOIN file_info f " + 
			"ON r.file_id = f.id " + 
			"WHERE r.reservation_user_comment_id = :id";
}
