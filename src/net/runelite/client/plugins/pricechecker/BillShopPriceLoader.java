package net.runelite.client.plugins.pricechecker;

import java.util.ArrayList;
import java.util.List;

public class BillShopPriceLoader {
	/**
	 * List Bill Prices
	 */
	public static List<BillShopData> BILL_PRICES = new ArrayList<>();

	/**
	 * Path to bills file
	 */
	private final String PATH = "./Data/cfg/bills_prices.txt";
}

class BillShopData {
	
	
	private int itemID;
	private int price;
	private String name;
	
	public BillShopData(String name, int itemID, int price) {
		this.name = name;
		this.itemID = itemID;
		this.price = price;
	}
	
}
