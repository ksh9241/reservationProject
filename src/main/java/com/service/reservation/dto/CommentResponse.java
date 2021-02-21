package com.service.reservation.dto;

import org.springframework.web.multipart.MultipartFile;

public class CommentResponse {
	private int productId;
	private int reservationInfoId;
	private int Score;
	private String Comment;
	private MultipartFile imageData;
	
	public CommentResponse() {}

	public CommentResponse(int productId, int reservationInfoId, int score, String comment, MultipartFile imageData) {
		super();
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		Score = score;
		Comment = comment;
		this.imageData = imageData;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public MultipartFile getImageData() {
		return imageData;
	}

	public void setImageData(MultipartFile imageData) {
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "CommentResponse [productId=" + productId + ", reservationInfoId=" + reservationInfoId + ", Score="
				+ Score + ", Comment=" + Comment + ", imageData=" + imageData + "]";
	}
}
