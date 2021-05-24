package com.simplicity.client.widget.ge;

public class SelectedOffer {
	
	private int itemId;
	private int amount;
	private int price;
	
	public SelectedOffer(int itemId, int amount, int price) {
		this.itemId = itemId;
		this.amount = amount;
		this.price = price;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public int getPrice() {
		return price;
	}

}
