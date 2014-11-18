package com.pace.pricecompare.senders;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ToysRUsWebsiteConnect implements IWebsiteConnect
{

	private static final String TOYSRUS_URL = "http://www.toysrus.com/";
	private String derivedURL;

	public ToysRUsWebsiteConnect()
	{
		// TODO Auto-generated constructor stub
	}

	public Double getPrice(String toyName) throws IOException
	{
		derivedURL = String.format("search/index.jsp?kwCatId=&kw=", toyName.replaceAll("\\s+", "\\+"), toyName.replaceAll("\\s+", "\\+"));
		Document document = Jsoup.connect(TOYSRUS_URL + derivedURL).get();

		Map<String, Double> items = new HashMap<String, Double>();

		for (Element element : document.select(".prodloop_cont"))
		{
			final String name = element.getElementsByClass("prodtitle").text();
			final String priceString = element.getElementsByClass("ourPrice2").text().replaceAll("\\$", "");
			if(!priceString.equals(""))
			{			
			final Double price = Double.parseDouble(priceString.split(" ")[0]);
			items.put(name, price);
			}

		}
		System.out.println("The ToysRUs item price is :" + items.get(toyName));

		return items.get(toyName);

	}

	}
