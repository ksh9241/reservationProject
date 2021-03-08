package com.service.reservation.dto;

import java.util.List;

public class Detail {
	private DisplayInfo displayInfo;
	private List<ProductImage> productImages;
	private DisplayInfoImage displayInfoImage;
	private List<ProductPrice> productPrices;
	private List<Comment> comments;
	private float averageScore;
	
	public Detail() {}

	public Detail(DisplayInfo displayInfo, List<ProductImage> productImages, DisplayInfoImage displayInfoImage,
			List<ProductPrice> productPrices, List<Comment> comments, float averageScore) {
		super();
		this.displayInfo = displayInfo;
		this.productImages = productImages;
		this.displayInfoImage = displayInfoImage;
		this.productPrices = productPrices;
		this.comments = comments;
		this.averageScore = averageScore;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public DisplayInfoImage getDisplayInfoImage() {
		return displayInfoImage;
	}

	public void setDisplayInfoImage(DisplayInfoImage displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public float getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}
}
