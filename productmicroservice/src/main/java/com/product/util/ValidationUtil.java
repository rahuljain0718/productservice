package com.product.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.product.exception.ValidationException;
import com.product.vo.Auction;
import com.product.vo.Product;

import io.micrometer.common.util.StringUtils;

@Component
public class ValidationUtil {

	@Autowired
	 private NamedParameterJdbcTemplate demoDBTemplate; 
	
	
	public void productInputValidations(Product product) throws ValidationException {

		StringBuffer validationString = new StringBuffer();

		if (StringUtils.isEmpty(product.getProductId())) {
			validationString.append(Constants.PRODUCT_ID_NOT_EXISTS).append("\n");
		}

		if (StringUtils.isEmpty(product.getProductName())) {
			validationString.append(Constants.PRODUCT_NOT_EMPTY).append("\n");
		}

		if (StringUtils.isEmpty(product.getProductCompany())) {
			validationString.append(Constants.PRODUCTCOMPANY_NOT_EMPTY).append("\n");
		}
		if (StringUtils.isEmpty(product.getUserId())) {
			validationString.append(Constants.USER_ID_NOT_EMPTY1).append("\n");
		}

		if (!validationString.isEmpty()) {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, validationString.toString());
		}
		
	}

	public void auctionInputValidations(Auction auction) throws ValidationException {
		
		StringBuffer validationString = new StringBuffer();

		if (StringUtils.isEmpty(auction.getAuctionId())) {
			validationString.append(Constants.AUCTION_ID_NOT_EXISTS).append("\n");
		}

		if (StringUtils.isEmpty(auction.getProductName())) {
			validationString.append(Constants.PRODUCT_NOT_EMPTY).append("\n");
		}

		if (StringUtils.isEmpty(auction.getUserId())) {
			validationString.append(Constants.USER_NOT_EMPTY).append("\n");
		}
		
		if (StringUtils.isEmpty(auction.getBid())){
			validationString.append(Constants.INPUT_FAILED).append("\n");
		}

		if (!validationString.isEmpty()) {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, validationString.toString());
		}
	
	}

	public void productIdExistsValidation(String productId) {
		
		String query = URLConstants.PRODUCT_EXISTS;
		
		Map<String, String> param = new HashMap<>();
		param.put("productId", productId);
		List<Product> productList = demoDBTemplate.query(query, param, new BeanPropertyRowMapper<Product>(Product.class));
		if (productList.isEmpty()) {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, Constants.PRODUCT_ID_NOT_EXISTS);
		}	
	}
	
	
public void productNameValidation(String productId,String productName) {
		
		String query = URLConstants.PRODUCT_NAME_EXISTS;
		
		Map<String, String> param = new HashMap<>();
		param.put("productId", productId);
		param.put("productName", productName);
		List<Product> productList = demoDBTemplate.query(query, param, new BeanPropertyRowMapper<Product>(Product.class));
		if (productList.size()>0) {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, Constants.PRODUCT_ID_NAME_ALREADY_EXISTS);
		}	
	}



public void auctionIdExistsValidation(String auctionId) {
	
	String query = URLConstants.AUCTIONID_EXISTS;
	
	Map<String, String> param = new HashMap<>();
	param.put("auctionId", auctionId);
	List<Product> productList = demoDBTemplate.query(query, param, new BeanPropertyRowMapper<Product>(Product.class));
	if (productList.size()>0) {
		throw new ValidationException(HttpStatus.EXPECTATION_FAILED, Constants.AUCTION_ALREADY_EXIST );
	}	
	
	
}
	
	
	
	
	
}
