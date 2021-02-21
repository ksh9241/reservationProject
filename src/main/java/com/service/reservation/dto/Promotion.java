package com.service.reservation.dto;

public class Promotion {
	private int id;
	private int productId;
	private String saveFileName;
	
	public Promotion() {}

	public Promotion(int id, int productId, String saveFileName) {
		this.id = id;
		this.productId = productId;
		this.saveFileName = saveFileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", saveFileName=" + saveFileName + "]";
	}
}
