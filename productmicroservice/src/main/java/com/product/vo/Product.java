package com.product.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	private String productId;

	private String productName;

	private String productCompany;

	private String minBid;
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Product() {
	}

	public Product(String productId, String productName, String productCompany, String minBid) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCompany = productCompany;
		this.minBid = minBid;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCompany() {
		return productCompany;
	}

	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}

	public String getMinBid() {
		return minBid;
	}

	public void setMinBid(String minBid) {
		this.minBid = minBid;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productCompany=" + productCompany
				+ "]";
	}

}
