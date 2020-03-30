package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

/**
 * A class that handles the skilling quantity interface.
 * 
 * @author Blake
 *
 */
public class SkillQuantityWidget extends RSInterface {

	/**
	 * The interface id.
	 */
	public static final int INTERFACE_ID = 34_000;
	
	/**
	 * The primary text color.
	 */
	private static final int TEXT_COLOR = 0x403020;
	
	/**
	 * The button string start id.
	 */
	private static int BUTTON_STRING_START;
	
	/**
	 * The button start id.
	 */
	private static int BUTTON_START;
	
	/**
	 * The quantity button start id.
	 */
	private static int QUANTITY_BUTTON_START;
	
	/**
	 * The quantity string start id.
	 */
	private static int QUANTITY_STRING_START;

	/**
	 * Unpacks the {@link SkillQuantityWidget} interface.
	 * 
	 * @param tda The text drawing area.
	 */
	public static void unpack(TextDrawingArea[] tda) {
		int id = INTERFACE_ID;

		RSInterface rsi = addInterface(id++);

		rsi.totalChildren(27);

		int child = 0;
		
		addText(id, "What would you like to make?", tda, 2, TEXT_COLOR, true, false);
		rsi.child(child++, id++, 150, 0);
		id++;
		
		addText(id, "Choose a quantity, then click an item to begin.", tda, 0, TEXT_COLOR, true, false);
		rsi.child(child++, id++, 150, 16);
		id++;
		
		int width = 93;
		
		int height = 72;
		
		int startX = width / 2;
		
		int startY = 108;
		
		BUTTON_STRING_START = id;
		
		for (int i = 0; i < 5; i++) {
			addText(id, "" + (i + 1), tda, 0, TEXT_COLOR, true, false);
			rsi.child(child++, id, startX + (width * i + 7 * i), startY);
			id++;
		}
		
		
		startX = 0;
		
		startY = 35;
		
		BUTTON_START = id;
		
		for (int i = 0; i < 5; i++) {
			addDynamicButton(id, width, height);
			rsi.child(child++, id, startX + (width * i + 7 * i), startY);
			id++;
		}
		
		startX = 308;
		startY = 0;
		width = height = 30;
		
		QUANTITY_BUTTON_START = id;
		
		for (int i = 0; i < 5; i++) {
			addDynamicButton(id, width, height);
			interfaceCache[id].selectableInterfaces = new int[] { QUANTITY_BUTTON_START, QUANTITY_BUTTON_START + 1, QUANTITY_BUTTON_START + 2, QUANTITY_BUTTON_START + 3, QUANTITY_BUTTON_START + 4 };
			rsi.child(child++, id, startX + (width * i + 7 * i), startY);
			id++;
		}
		
		String[] text = new String[] { "1", "5", "10", "X", "All" };
		
		QUANTITY_STRING_START = id;
		
		for (int i = 0; i < 5; i++) {
			addText(id, text[i], tda, 0, TEXT_COLOR, true, false);
			interfaceCache[id].enabledColor = 0xffffff;
			rsi.child(child++, id, startX + (width * i + 7 * i) + 14, startY + 10);
			id++;
		}
		
		startX = 6;
		startY = 32;
		
		for (int i = 0; i < 5; i++) {
			addItemModel(id, 857, 93, 72, 185);
			rsi.child(child++, id, startX + (93 * i + 7 * i), startY);
			id++;
		}
	}
	
	public static boolean isSkillButton(int id) {
		return id >= BUTTON_START && id < BUTTON_START + 5;
	}
	
	public static void handleSkillButton(int id) {
		if (!isSkillButton(id) || interfaceCache[INTERFACE_ID].clickedChildId != -1) {
			return;
		}
		
		interfaceCache[INTERFACE_ID].clickedChildId = id;
	}
	
	public static void onInit() {
		interfaceCache[INTERFACE_ID].clickedChildId = -1;
	}
	
	public static boolean clickedSkillButton(int id) {
		return interfaceCache[INTERFACE_ID].clickedChildId == id;
	}
	
	public static boolean isQuantitySelected(int id) {
		if (!isQuantityString(id)) {
			return false;
		}
		
		return interfaceCache[id - 5].selected;
	}
	
	private static boolean isQuantityString(int id) {
		for (int i = QUANTITY_STRING_START; i < QUANTITY_STRING_START + 5; i++) {
			if (id == i) {
				return true;
			}
		}
		
		return false;
	}

}
