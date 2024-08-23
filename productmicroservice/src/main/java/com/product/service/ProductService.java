package com.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.dao.ProductDao;

import com.product.exception.ValidationException;
import com.product.util.DemoResponseBean;
import com.product.vo.Auction;
import com.product.vo.Product;
import com.product.util.Constants;

@Component
public class ProductService implements ProductServiceImpl{

	@Autowired
	ProductDao productDao;
	
	
	
//	@Autowired
//	AuctionDao auctionDao;

	@Override
	public String addProduct(Product product) {
		
		return this.productDao.AddProduct(product);
	}
	@Override
	public Product getProductById(String ProductId) {
		return productDao.getProductById(ProductId);
	}
	
	@Override
	public String updateProduct(Product product) {
		
		return this.productDao.updateProduct(product);
	}
	
	@Override
	public String deleteProduct(String productId) {

		return this.productDao.deleteProduct(productId);
	}


	public String placeBid(Auction auction) {
		
		

		return this.productDao.placeBid(auction);
	}
	
	
}
