package com.simplicity.client.widget.ge;

public class SearchItem {
	
	private int itemId;
	private boolean stackable;
	private String name;
	
	public SearchItem(int itemId, boolean stackable, String name) {
		this.itemId = itemId;
		this.stackable = stackable;
		this.name = name;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public boolean isStackable() {
		return stackable;
	}
	
	public String getName() {
		return name;
	}

}
