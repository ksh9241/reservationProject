package com.service.reservation.dao;

public class DisplayInfoImageDaoSql {
	public static final String DISPLAY_INFO_IMAGE_BY_ID ="SELECT d.id displayInfoImageId, d.display_info_id displayInfoId, d.file_id fileId, " + 
			"f.file_name fileName, f.save_file_name saveFileName, f.content_type contentType, " + 
			"f.delete_flag deleteFlag, f.create_date createDate, f.modify_date modifyDate " + 
			"FROM display_info_image d JOIN file_info f " + 
			"ON d.file_id = f.id " + 
			"WHERE d.display_info_id = :id";
}
