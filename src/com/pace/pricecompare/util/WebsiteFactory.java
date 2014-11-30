package com.pace.pricecompare.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.pace.pricecompare.entity.OnlineStores;
import com.pace.pricecompare.entity.Websitedetails;
import com.pace.pricecompare.senders.AmazonWebsiteConnect;
import com.pace.pricecompare.senders.IWebsiteConnect;
import com.pace.pricecompare.senders.ToysRUsWebsiteConnect;
import com.pace.pricecompare.senders.WalmartWebsiteConnect;

public class WebsiteFactory {

	private static WebsiteFactory instance = null;

	private WebsiteFactory() {
	}

	public static WebsiteFactory getInstance() {
		if (instance == null)
			instance = new WebsiteFactory();

		return instance;

	}

	public IWebsiteConnect getSiteInstance(OnlineStores sitename) {

		switch (sitename) {

		case AMAZON:
			return new AmazonWebsiteConnect();

		case TOYSRUS:

			return new ToysRUsWebsiteConnect();

		case WALMART:

			return new WalmartWebsiteConnect();
		}

		return null;
	}

}
