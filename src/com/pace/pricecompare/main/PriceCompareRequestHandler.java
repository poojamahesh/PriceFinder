package com.pace.pricecompare.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pace.pricecompare.entity.OnlineStores;
import com.pace.pricecompare.senders.IWebsiteConnect;
import com.pace.pricecompare.util.WebsiteFactory;

public class PriceCompareRequestHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9028056630178081802L;

	// Method to handle GET method request.
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String searchValue = request.getParameter("Item_Name");

		//System.out.println("The search value retreived is:" + searchValue);

		WebsiteFactory factory = WebsiteFactory.getInstance();
		IWebsiteConnect connection;
		Double[] price = new Double[OnlineStores.values().length];
		int i = 0;
		StringBuffer output = new StringBuffer();
		Double leastPrice=null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
				+ "transitional//en\">\n";

		output.append(docType
				+ "<html>\n"
				+ "<head><title> PriceCompare </title></head><body> <center> <h3>\n");
		output.append("The Toy Store Offering Reasonable Price </h3> ");

		try {
			String reasonableStore = "Not Available In These Stores!";
			
			for (OnlineStores store : OnlineStores.values()) {
				System.out.println("The Online Toy Store : "
						+ request.getParameter(store.toString()));
				if (request.getParameter(store.toString()) != null
						&& request.getParameter(store.toString()).equals("on")) {
					connection = factory.getSiteInstance(store);

					price[i] = connection.getPrice(searchValue);

					System.out.println(price[i]);
					if (price[i] != null) {

						if (leastPrice == null) {
							leastPrice = price[i];
							reasonableStore = store.toString();
							System.out.println("The least price is : " + leastPrice + " Offered By The store: "+ reasonableStore);
						} 
						if (price[i] < leastPrice) {
							leastPrice = price[i];
							reasonableStore = store.toString();
							//System.out.println(" Least Price Is Offered By The store "+ reasonableStore);
							System.out.println("The least price is : " + leastPrice + " Offered By The store: "+ reasonableStore);
						}

					}

				}
				
				}
			//output.append("<html><font color=\"red\">"+"Please checkin atleast 2 checkbox"+"</font></html>");
			output.append("<h3><html><font color=\"blue\"> The Store : " + reasonableStore + "</br> Offers Least Price :" + leastPrice
					+ "</font></html></h3> </br>");
			
			//output.append("<h3>" + reasonableStore 
				//	+ "</h3> </br>");
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		output.append("</h3> </center> </form> </html>");
		out.println(output);

	}

	

}
