package com.service.reservation.dto;

import java.sql.Timestamp;

public class Product {
	private int id;
	private int categoryId;
	private String description;
	private String content;
	private String event;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	// mainpage column
	private int displayInfoId;
	private int productId;
	private String productDescription;
	private String placeName;
	private String productContent;
	private String saveFileName;
	
	public Product() {}

	public Product(int id, int categoryId, String description, String content, String event, Timestamp createDate,
			Timestamp modifyDate, int displayInfoId, int productId, String productDescription, String placeName,
			String productContent, String saveFileName) {
		this.id = id;
		this.categoryId = categoryId;
		this.description = description;
		this.content = content;
		this.event = event;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.displayInfoId = displayInfoId;
		this.productId = productId;
		this.productDescription = productDescription;
		this.placeName = placeName;
		this.productContent = productContent;
		this.saveFileName = saveFileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
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

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", categoryId=" + categoryId + ", description=" + description + ", content="
				+ content + ", event=" + event + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", displayInfoId=" + displayInfoId + ", productId=" + productId + ", productDescription="
				+ productDescription + ", placeName=" + placeName + ", productContent=" + productContent
				+ ", saveFileName=" + saveFileName + "]";
	}
}
