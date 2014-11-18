package com.pace.pricecompare.senders;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WalmartWebsiteConnect implements IWebsiteConnect
{
	private static final String WALMART_URL = "http://www.walmart.com/";
	private String derivedURL;

	public Double getPrice(String toyName) throws IOException
	{
		derivedURL = "search/?query=" + toyName.replaceAll("\\s+", "\\%20");

		Document document = Jsoup.connect(WALMART_URL + derivedURL).get();
		Map<String, Double> walmartItems = new HashMap<String, Double>();
		for (Element walmartElement : document.select(".tile-content-wrapper"))
		{
			final String productName = walmartElement.getElementsByClass("js-product-title").text();
			final String priceString = walmartElement.getElementsByClass("price").text().replaceAll("\\$", "");
			if (!priceString.equals(""))
			{
				final Double price = Double.parseDouble(priceString.split(" ")[0]);
				walmartItems.put(productName, price);
			}

		}

		System.out.println("The Walmart item price is :" + walmartItems.get(toyName));

		return walmartItems.get(toyName);

	}

}
