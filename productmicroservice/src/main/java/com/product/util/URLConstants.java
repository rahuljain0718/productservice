package com.product.util;

public class URLConstants {

	
	public static final String REGISTER_PRODUCT="insert into product (PRODUCT_ID,PRODUCT_NAME,PRODUCT_COMPANY,MIN_BID, USER_ID) values(:productId,:productName,:productCompany,:minBid,:userId)";
	
	public static final String UPDATE_PRODUCT="UPDATE product SET PRODUCT_NAME =:productName, PRODUCT_COMPANY=:productCompany WHERE PRODUCT_ID =:productId and USER_ID=:userId;";
	
	public static final String DELETE_PRODUCT="DELETE FROM product WHERE PRODUCT_ID=:productId;";
	
	public static final String PRODUCT_EXISTS="select * from product where PRODUCT_ID=:productId";
	
	public static final String AUCTIONID_EXISTS="select * from auction where AUCTION_ID=:auctionId";
	
	
	public static final String PRODUCT_NAME_EXISTS="select * from product where PRODUCT_ID=:productId and PRODUCT_NAME=:productName";
	
	public static final String ADD_AUCTION="insert into auction (AUCTION_ID,PRODUCT_ID,PRODUCT_NAME,USER_ID,BID,MIN_BID)values(:auctionId,:productId,:productName,:userId,:bid,:Minbid)";
}

