package com.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.product.exception.ValidationException;
import com.product.service.AuctionServiceImpl;
import com.product.service.ProductServiceImpl;
import com.product.util.RequestResponse;
import com.product.util.Constants;
import com.product.util.DemoResponseBean;
import com.product.util.ValidationUtil;
import com.product.vo.Auction;
import com.product.vo.Product;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping(path = "/product/")
public class ProductController {

	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	AuctionServiceImpl auctionService;

	@Autowired
	ValidationUtil validationUtil;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping(path = "addproduct")
	public ResponseEntity<String> addProductDetails(@RequestBody Product product) throws ValidationException {
		validationUtil.productInputValidations(product);
		validationUtil.productNameValidation(product.getProductId(), product.getProductName());
		String role = userole(product.getUserId());
		String response=null;
		if (role.equalsIgnoreCase("user")) {
		     response = productService.addProduct(product);;
		}
		else {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, Constants.ACCESS_DENIED);
		}
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PutMapping(path = "Update")
	public ResponseEntity<String> updateProductDetails(@RequestBody Product product) throws ValidationException {
		validationUtil.productInputValidations(product);
		
		validationUtil.productIdExistsValidation(product.getProductId());
		String role = userole(product.getUserId());
		String response=null;
		if (role.equalsIgnoreCase("user")) {
			response = productService.updateProduct(product);
		}
		else {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, Constants.ACCESS_DENIED);
		}
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@DeleteMapping(path="deleteuser")
	public ResponseEntity<RequestResponse> deleteUser(@RequestParam(name = "productId", required = true) String productId,@RequestParam(name = "userId", required = true) String userId) throws ValidationException {
		
		validationUtil.productIdExistsValidation(productId);
		String role = userole(userId);
		String response=null;
		if (role.equalsIgnoreCase("admin")) {
			response = this.productService.deleteProduct(productId);
		}
		else {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, Constants.ACCESS_DENIED);
		}
		return new ResponseEntity<RequestResponse>(new RequestResponse(response, true), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "placebid")
	public ResponseEntity<String> placeAuction(@RequestBody Auction auction) throws ValidationException {

		String response = null;
		String role=userole(auction.getUserId());
		if (role.equalsIgnoreCase("user")) {
			validationUtil.auctionInputValidations(auction);
			//validationUtil.productNameValidation(auction.getProductId(), auction.getProductName());
			validationUtil.auctionIdExistsValidation(auction.getAuctionId());
			response = productService.placeBid(auction);
			
		}
		else
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, Constants.ACCESS_DENIED);
        
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "getauction/{userId}")
	public ResponseEntity<List<Auction>> getAuction(@PathVariable String userId) throws ValidationException {
		
		String role = userole(userId);
		List<Auction> response=null;
		if (role.equalsIgnoreCase("admin")) {
			response = this.auctionService.getAuctiondetails();
		}
		else {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, Constants.ACCESS_DENIED);
        }
		
		return new ResponseEntity<List<Auction>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	public String userole(String userId) throws ValidationException{

		String serverUrl = "http://localhost:8082/demoService/login/getrole/{userId}";
		
		ResponseEntity<String> res=null;
		try {
		res = restTemplate.getForEntity(serverUrl, String.class, userId);
		}
		catch(HttpClientErrorException er) {
			System.out.println(er.getMessage());
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED, Constants.USER_ID_NOT_EXISTS);

		}
		
		String role = res.getBody();
		
		return role;
		
	}
	
	
	
	
}
