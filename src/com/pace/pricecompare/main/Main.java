package com.pace.pricecompare.main;

import com.pace.pricecompare.entity.OnlineStores;
import com.pace.pricecompare.senders.IWebsiteConnect;
import com.pace.pricecompare.util.WebsiteFactory;

public class Main {

	public static void main(String[] args) throws Exception {
		WebsiteFactory factory = WebsiteFactory.getInstance();
		String toyName = " ";
		IWebsiteConnect connection;
		Double[] price = new Double[OnlineStores.values().length];
		int i = 0;

		for (OnlineStores store : OnlineStores.values()) {
			connection = factory.getSiteInstance(store);
			price[i] = connection.getPrice(toyName);

			System.out.println(price[i]);

		}

	}

}
