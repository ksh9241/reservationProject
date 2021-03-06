package com.service.reservation.dto;

import java.sql.Timestamp;
import java.util.List;

public class ReservationInfo {
	private int reservationInfoId;
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private boolean cancelYn;
	private String reservationDate;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private DisplayInfo displayInfo;
	private int totalPrice;
	
	public ReservationInfo() {}

	public ReservationInfo(int reservationInfoId, int productId, int displayInfoId, String reservationName,
			String reservationTelephone, String reservationEmail, boolean cancelYn, String reservationDate,
			Timestamp createDate, Timestamp modifyDate, DisplayInfo displayInfo, int totalPrice) {
		super();
		this.reservationInfoId = reservationInfoId;
		this.productId = productId;
		this.displayInfoId = displayInfoId;
		this.reservationName = reservationName;
		this.reservationTelephone = reservationTelephone;
		this.reservationEmail = reservationEmail;
		this.cancelYn = cancelYn;
		this.reservationDate = reservationDate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.displayInfo = displayInfo;
		this.totalPrice = totalPrice;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
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

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public boolean getCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
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

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Reservation [reservationInfoId=" + reservationInfoId + ", productId=" + productId + ", displayInfoId="
				+ displayInfoId + ", reservationName=" + reservationName + ", reservationTelephone="
				+ reservationTelephone + ", reservationEmail=" + reservationEmail + ", cancelYn=" + cancelYn
				+ ", reservationDate=" + reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", displayInfo=" + displayInfo + ", totalPrice=" + totalPrice + "]";
	}
}
