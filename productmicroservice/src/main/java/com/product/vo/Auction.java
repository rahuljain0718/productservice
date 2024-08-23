package com.product.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Auction {

	@Id
	private String auctionId;
	
	private String productId;

	private String productName;

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	private String userId;


	public String getMin_bid() {
		return Min_bid;
	}

	public void setMin_bid(String min_bid) {
		Min_bid = min_bid;
	}

	private String bid;
	
	private String Min_bid;

	public Auction() {
	}

	public Auction(String auctionId, String productName, String userId, String bid) {
		super();
		this.auctionId = auctionId;
		this.productName = productName;
		this.userId = userId;
		this.bid = bid;
	}

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

}