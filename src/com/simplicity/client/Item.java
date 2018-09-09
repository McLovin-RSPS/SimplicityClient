package com.simplicity.client;


import com.simplicity.client.cache.definitions.ItemDefinition;

public final class Item extends Animable {

	public final Model getRotatedModel() {
		ItemDefinition itemDef = ItemDefinition.forID(ID);
		return itemDef.getItemModelFinalised(amount);
	}

	public Item() {
	}

	public int ID;
	public int x;
	public int y;
	public int amount;
}
