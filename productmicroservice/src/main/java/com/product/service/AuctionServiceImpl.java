package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.dao.AuctionDao;
import com.product.vo.Auction;

@Component
public class AuctionServiceImpl implements AuctionService{

	@Autowired
	AuctionDao auctionDao;
	
	@Override
	public List<Auction> getAuctiondetails() {
		
		
		
		return this.auctionDao.getAuctiondetails();
	}

}
