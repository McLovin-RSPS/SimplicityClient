package com.simplicity.client;


import com.simplicity.client.cache.definitions.ItemDefinition;

public final class Item extends Animable {

	public final Model getRotatedModel() {
		ItemDefinition itemDef = ItemDefinition.forID(ID);
		return itemDef.getItemModelFinalised(amount);
	}

	public Item() {
	}
	
	public Item(int itemId, int amount) {
		this.ID = itemId;
		this.amount = amount;
	}
	
	public ItemDefinition getDefinition() {
		return ItemDefinition.forID(ID);
	}
	
	public Sprite getSprite() {
		return ItemDefinition.getSprite(ID, amount, 0);
	}
	
	public int ID;
	public int amount;
}
