package com.service.reservation.dto;

import java.sql.Timestamp;

public class ProductImage {
	private int productId;
	private String type;
	private int productImageId;
	private int fileInfoId;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private boolean deleteFlag;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	public ProductImage() {}

	public ProductImage(int productId, String type, int productImageId, int fileInfoId, String fileName,
			String saveFileName, String contentType, boolean deleteFlag, Timestamp createDate, Timestamp modifyDate) {
		super();
		this.productId = productId;
		this.type = type;
		this.productImageId = productImageId;
		this.fileInfoId = fileInfoId;
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.contentType = contentType;
		this.deleteFlag = deleteFlag;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}

	public int getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(int fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
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
}
