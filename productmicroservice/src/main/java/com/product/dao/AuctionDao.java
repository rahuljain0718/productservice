package com.product.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.product.exception.ValidationException;
import com.product.util.Constants;
import com.product.vo.Auction;

@Component
public class AuctionDao{
	
	@Autowired
	private NamedParameterJdbcTemplate demodBTemplate;
	
	public List<Auction> getAuctiondetails() {
		
		String query = "select * from auction";

		List<Auction> auctionList = demodBTemplate.query(query, new BeanPropertyRowMapper<Auction>(Auction.class));
		if (auctionList.isEmpty()) {
			throw new ValidationException(HttpStatus.EXPECTATION_FAILED,Constants.PRODUCT_ID_NOT_EXISTS);
		}
		// dBTemplate.update(query, param);
		return auctionList;
	}

}
