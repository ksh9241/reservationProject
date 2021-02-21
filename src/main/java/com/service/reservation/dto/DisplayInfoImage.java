package com.service.reservation.dto;

import java.sql.Timestamp;

public class DisplayInfoImage {
	private int displayInfoId;
	private int fileId;
	
	private int displayInfoImageId;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private boolean deleteFlag;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	public DisplayInfoImage() {}

	public DisplayInfoImage(int displayInfoId, int fileId, int displayInfoImageId, String fileName, String saveFileName,
			String contentType, boolean deleteFlag, Timestamp createDate, Timestamp modifyDate) {
		super();
		this.displayInfoId = displayInfoId;
		this.fileId = fileId;
		this.displayInfoImageId = displayInfoImageId;
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.contentType = contentType;
		this.deleteFlag = deleteFlag;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getDisplayInfoImageId() {
		return displayInfoImageId;
	}

	public void setDisplayInfoImageId(int displayInfoImageId) {
		this.displayInfoImageId = displayInfoImageId;
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
