package com.service.reservation.dto;

import java.sql.Timestamp;
import java.util.List;

public class Comment {
	private String comment;
	private int commentId;
	private List<CommentImage> commentImages;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private int productId;
	private Timestamp reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private float score;
	
	public Comment() {}

	public Comment(String comment, int commentId, List<CommentImage> commentImages, Timestamp createDate,
			Timestamp modifyDate, int productId, Timestamp reservationDate, String reservationEmail,
			int reservationInfoId, String reservationName, String reservationTelephone, int score) {
		this.comment = comment;
		this.commentId = commentId;
		this.commentImages = commentImages;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.productId = productId;
		this.reservationDate = reservationDate;
		this.reservationEmail = reservationEmail;
		this.reservationInfoId = reservationInfoId;
		this.reservationName = reservationName;
		this.reservationTelephone = reservationTelephone;
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public List<CommentImage> getCommentImages() {
		return commentImages;
	}

	public void setCommentImages(List<CommentImage> commentImages) {
		this.commentImages = commentImages;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Timestamp getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", commentId=" + commentId + ", commentImages=" + commentImages
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productId=" + productId
				+ ", reservationDate=" + reservationDate + ", reservationEmail=" + reservationEmail
				+ ", reservationInfoId=" + reservationInfoId + ", reservationName=" + reservationName
				+ ", reservationTelephone=" + reservationTelephone + ", score=" + score + "]";
	}
}
