package com.product.util;

import com.product.vo.Product;

public class DemoResponseBean {
	
	private String message;
	
	private Product product;

	public DemoResponseBean() {
	}

	public DemoResponseBean(String message, Product product) {
		super();
		this.message = message;
		this.product = product;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
