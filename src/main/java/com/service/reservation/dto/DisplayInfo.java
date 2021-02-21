package com.service.reservation.dto;

import java.sql.Timestamp;

public class DisplayInfo {
	private int productId;
	private String openingHours;
	private String placeName;
	private String placeLot;
	private String placeStreet;
	private String homepage;
	private String email;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	private int categoryId;
	private int displayInfoId;
	private String categoryName;
	private String productDescription;
	private String productContent;
	private String productEvent;
	private String telephone;
	
	public DisplayInfo() {}

	public DisplayInfo(int productId, String openingHours, String placeName, String placeLot, String placeStreet,
			String homepage, String email, Timestamp createDate, Timestamp modifyDate, int categoryId,
			int displayInfoId, String categoryName, String productDescription, String productContent,
			String productEvent, String telephone) {
		super();
		this.productId = productId;
		this.openingHours = openingHours;
		this.placeName = placeName;
		this.placeLot = placeLot;
		this.placeStreet = placeStreet;
		this.homepage = homepage;
		this.email = email;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.categoryId = categoryId;
		this.displayInfoId = displayInfoId;
		this.categoryName = categoryName;
		this.productDescription = productDescription;
		this.productContent = productContent;
		this.productEvent = productEvent;
		this.telephone = telephone;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductEvent() {
		return productEvent;
	}

	public void setProductEvent(String productEvent) {
		this.productEvent = productEvent;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
