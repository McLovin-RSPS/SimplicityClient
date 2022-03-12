package com.simplicity.client.widget.raids.cox;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.widget.SettingOld;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A class that handles all of the client's settings.
 * 
 * @author Blake
 *
 */
public class StorageUnitBuildingWidget extends RSInterface {

	private static final int INTERFACE_ID = 54800;

	public static void unpack(TextDrawingArea[] tda) {
		buildingMenu(tda);
	}

	private static void buildingMenu(TextDrawingArea[] tda) {
		int id = INTERFACE_ID;
		
		RSInterface tab = addInterface(id++);

		tab.totalChildren(17);
		int child = 0;

		addSpriteLoader(id, 948);
		tab.child(child++, id, 6, 9);
		id++;

		addText(id, "Furniture Creation Menu", tda, 2, 0xff9933);
		tab.child(child++, id, 186, 13);
		id++;
		
		addText(id, "Select a piece of furniture to build.", tda, 1, 0xff9933);
		tab.child(child++, id, 38, 42);
		id++;

		addHoverText(id, "Small Storage Unit", "Small Storage Unit", tda, 0, 0xff9933, false, true, 100, 16);
		tab.child(child++, id, 94, 78);
		id++;

		addText(id, "Mallignum root plank: 2", tda, 0, 0xcdcaff);
		tab.child(child++, id, 94, 93);
		id++;

		addModel(id, 32514, 1900, 140, 1900, DataType.OLDSCHOOL);
		tab.child(child++, id, 53, 110);
		id++;

		addText(id, "Level 30", tda, 1, 0xff9933);
		tab.child(child++, id, 30, 118);
		id++;

		addHoverText(id, "Medium Storage Unit", "Medium Storage Unit", tda, 0, 0xff9933, false, true, 100, 16);
		tab.child(child++, id, 94, 162);
		id++;

		addText(id, "Mallignum root plank: 4", tda, 0, 0xcdcaff);
		tab.child(child++, id, 94, 177);
		id++;

		addModel(id, 32524, 1900, 140, 1900, DataType.OLDSCHOOL);
		tab.child(child++, id, 53, 190);
		id++;

		addText(id, "Level 60", tda, 1, 0xff9933);
		tab.child(child++, id, 30, 203);
		id++;

		addHoverText(id, "Large Storage Unit", "Large Storage Unit", tda, 0, 0xff9933, false, true, 100, 16);
		tab.child(child++, id, 94, 246);
		id++;

		addText(id, "Mallignum root plank: 6", tda, 0, 0xcdcaff);
		tab.child(child++, id, 94, 261);
		id++;

		addModel(id, 32519, 1900, 140, 1900, DataType.OLDSCHOOL);
		tab.child(child++, id, 53, 275);
		id++;

		addText(id, "Level 90", tda, 1, 0xff9933);
		tab.child(child++, id, 30, 284);
		id++;
		
		addCloseButton(id, id + 1, id + 2, true);
        tab.child(child++, id, 481, 12);
        tab.child(child++, id + 1, 481, 12);
		id+=3;
	}
}
