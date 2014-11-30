package com.pace.pricecompare.senders;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AmazonWebsiteConnect implements IWebsiteConnect {
	private static final String AMAZON_URL = "http://www.amazon.com/";
	private String derivedURL;

	public Double getPrice(String toyName) throws IOException {
		derivedURL = "s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords="
				+ toyName.replaceAll("\\s+", "\\+");

		Document document = Jsoup.connect(AMAZON_URL + derivedURL).get();
		Map<String, Double> items1 = new HashMap<String, Double>();
		for (Element element1 : document.select(".a-fixed-left-grid-col")) {
			final String name1 = element1.getElementsByClass("a-size-medium")
					.text();
			final String priceString1 = element1
					.getElementsByClass("a-size-base").text()
					.replaceAll("\\$", "");
			// replaceAll("\\$", "");
			if (!priceString1.equals("")) {
				final Double price1 = Double.parseDouble(priceString1
						.split(" ")[0]);
				items1.put(name1, price1);
			}

		}

		System.out.println("The Amazon item price is :" + items1.get(toyName));

		return items1.get(toyName);

	}

}
