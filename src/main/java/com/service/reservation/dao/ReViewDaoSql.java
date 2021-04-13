package com.service.reservation.dao;

public class ReViewDaoSql {
	public static final String SELECT_COMMENT_BY_COMMENTID ="select sub.*, f.file_name, f.save_file_name from file_info f join\r\n" + 
			"(select c.*, i.file_id from reservation_user_comment c join reservation_user_comment_image i\r\n" + 
			"on c.id = i.reservation_user_comment_id) sub\r\n" + 
			"on f.id = sub.file_id\r\n" + 
			"where sub.id = :commentId "; 
			
	public static final String SELECT_COMMENT_IMAGE_BY_COMMENTID = "SELECT count(file_id) FROM reservation_user_comment_image WHERE " +
								"reservation_user_comment_id = :commentId";
	
	public static final String SELECT_NOT_IMG_COMMENT_BY_COMMENTID = "SELECT * FROM reservation_user_comment WHERE id = :commentId";
	
	public static final String DELETE_COMMENT = "DELETE FROM reservation_user_comment WHERE id = :commentId";
	
	public static final String DELETE_COMMENT_IMAGE = "DELETE FROM reservation_user_comment_image WHERE reservation_user_comment_id = :commentId";
	
	public static final String DELETE_FILE_INFO = "DELETE FROM file_info WHERE id = :fileId";
}
