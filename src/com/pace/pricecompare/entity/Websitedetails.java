package com.pace.pricecompare.entity;

import com.pace.pricecompare.senders.IWebsiteConnect;

public class Websitedetails {
	private String baseUrl;
	private String derivedUrl;
	private IWebsiteConnect connection;
	private String price;
	private String productName;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getDerivedUrl() {
		return derivedUrl;
	}

	public void setDerivedUrl(String derivedUrl) {
		this.derivedUrl = derivedUrl;
	}

	public IWebsiteConnect getConnection() {
		return connection;
	}

	public void setConnection(IWebsiteConnect connection) {
		this.connection = connection;
	}

}
