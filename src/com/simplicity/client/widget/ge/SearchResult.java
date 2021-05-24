package com.simplicity.client.widget.ge;

public class SearchResult {
	
	private int itemId;
	private String name;
	
	public SearchResult(int itemId, String name) {
		this.itemId = itemId;
		this.name = name;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public String getName() {
		return name;
	}
	
}
