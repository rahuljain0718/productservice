package com.product.service;

import com.product.util.DemoResponseBean;
import com.product.vo.Auction;
import com.product.vo.Product;

public interface ProductServiceImpl {

	public String addProduct(Product product);
	
	public String updateProduct(Product product);
	
	public String deleteProduct(String productId);
	
	public String placeBid(Auction auction);

	Product getProductById(String ProductId);
}
