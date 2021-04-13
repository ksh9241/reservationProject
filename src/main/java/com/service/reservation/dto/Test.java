package com.service.reservation.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class Test {
	int id;
	int product_id;
	int reservation_info_id;
	float score;
	String comment;
	Timestamp create_date;
	Timestamp modify_date;
	int file_id;
	String file_name;
	String save_file_name;
	MultipartFile imageData;
	
	public Test() {}

	public Test(int id, int product_id, int reservation_info_id, float score, String comment, Timestamp create_date,
			Timestamp modify_date, int file_id, String file_name, String save_file_name, MultipartFile imageData) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.reservation_info_id = reservation_info_id;
		this.score = score;
		this.comment = comment;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.file_id = file_id;
		this.file_name = file_name;
		this.save_file_name = save_file_name;
		this.imageData = imageData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getReservation_info_id() {
		return reservation_info_id;
	}

	public void setReservation_info_id(int reservation_info_id) {
		this.reservation_info_id = reservation_info_id;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public Timestamp getModify_date() {
		return modify_date;
	}

	public void setModify_date(Timestamp modify_date) {
		this.modify_date = modify_date;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getSave_file_name() {
		return save_file_name;
	}

	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}

	public MultipartFile getImageData() {
		return imageData;
	}

	public void setImageData(MultipartFile imageData) {
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", product_id=" + product_id + ", reservation_info_id=" + reservation_info_id
				+ ", score=" + score + ", comment=" + comment + ", create_date=" + create_date + ", modify_date="
				+ modify_date + ", file_id=" + file_id + ", file_name=" + file_name + ", save_file_name="
				+ save_file_name + ", imageData=" + imageData + "]";
	}

}
