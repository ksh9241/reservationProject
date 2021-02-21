package com.service.reservation.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductPrice {
	private int productId;
	private String priceTypeName;
	private int price;
	private BigDecimal discountRate;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private int productPriceId;
	
	public ProductPrice() {}

	public ProductPrice(int productId, String priceTypeName, int price, BigDecimal discountRate, Timestamp createDate,
			Timestamp modifyDate, int productPriceId) {
		super();
		this.productId = productId;
		this.priceTypeName = priceTypeName;
		this.price = price;
		this.discountRate = discountRate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.productPriceId = productPriceId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
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

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}
}
