package com.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.product.exception.ValidationException;
import com.product.util.Constants;
import com.product.util.DemoResponseBean;
import com.product.util.URLConstants;
import com.product.vo.Auction;
import com.product.vo.Product;

@Component
public class ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate dBTemplate;

	/*
	 * Connect to DB Execute the query If the userid and password exists we will get
	 * a record back with all details of the User
	 * 
	 * If the userid and password does not exist we will get null
	 *********/
	
	public String AddProduct(Product prod) throws ValidationException{

		String query = URLConstants.REGISTER_PRODUCT;
		Map<String, String> param = new HashMap<>();
		param.put("productId", prod.getProductId());
		param.put("productName", prod.getProductName());
		param.put("productCompany", prod.getProductCompany());
		param.put("minBid", prod.getMinBid());
		param.put("userId", prod.getUserId());

		int userList=dBTemplate.update(query, param);

		if(userList==0) {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED,Constants.ADD_PRODUCT_FAILED);
		}
		
		return Constants.ADDED_SUCCESSFUL;
	}

	public String updateProduct(Product product) throws ValidationException{

		String query = URLConstants.UPDATE_PRODUCT;

		Map<String, String> param = new HashMap<>();
		param.put("productId", product.getProductId());
		param.put("productName", product.getProductName());
		param.put("productCompany", product.getProductCompany());
		param.put("userId", product.getUserId());

		int userList=dBTemplate.update(query, param);
		if(userList==0) {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED,Constants.UPDATE_PRODUCT_FAILED);
		}
		return Constants.UPDATED_SUCCESSFUL;
	}
	
	

	public String deleteProduct(String ProductId) throws ValidationException{

		String query = URLConstants.DELETE_PRODUCT;

		Map<String, String> param = new HashMap<>();
		param.put("productId", ProductId);

		int userList=dBTemplate.update(query, param);
		
		if(userList==0) {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED,Constants.DELETE_PRODUCT_FAILED);
		}
		
		return Constants.DELETED_SUCCESSFUL;

	}



	public String placeBid(Auction auction) throws ValidationException {
		
		Product product =  getProductById(auction.getProductId());
		
		if (Integer.parseInt(auction.getBid()) > Integer.parseInt(product.getMinBid())) {
		
			String query = URLConstants.ADD_AUCTION;
			Map<String, String> param = new HashMap<>();
			param.put("auctionId", auction.getAuctionId());
			param.put("productId", auction.getProductId());
			param.put("productName", auction.getProductName());
			param.put("userId", auction.getUserId());
			param.put("bid", auction.getBid());
			param.put("Minbid", product.getMinBid());
			dBTemplate.update(query, param);
			
			return Constants.AUCTION_ADDED_SUCCESSFUL ;
		}
		else {
			
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED,Constants.PLACE_BID_FAIL);
		}
	}
	
	
	public Product getProductById(String ProductId) {
		String query = URLConstants.PRODUCT_EXISTS;
		Map<String,Object> params = new HashMap<>();
		params.put("productId", ProductId);
		Product product = dBTemplate.queryForObject(query,params,new BeanPropertyRowMapper<Product>(Product.class));
		
		if(product.getProductId()==null) {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED,Constants.PRODUCT_ID_NOT_EXISTS);
		}
		
		return product;
	}
	
}
