package com.simplicity.client.widget;

import java.awt.event.KeyEvent;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;
import com.simplicity.client.cache.definitions.ItemDefinition;

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
	public static final int INTERFACE_ID = 53_000;
	
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
	public static int BUTTON_START;
	
	/**
	 * The quantity button start id.
	 */
	private static int QUANTITY_BUTTON_START;
	
	/**
	 * The quantity string start id.
	 */
	private static int QUANTITY_STRING_START;
	
	/**
	 * The item model start id.
	 */
	public static int ITEM_MODEL_START;
	
	/**
	 * The item child start.
	 */
	public static int ITEM_CHILD_START;

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
			addDynamicButton(id, "Make", width, height);
			rsi.child(child++, id, startX + (width * i + 7 * i), startY);
			id++;
		}
		
		startX = 315;
		startY = 0;
		width = height = 30;
		
		QUANTITY_BUTTON_START = id;
		
		for (int i = 0; i < 5; i++) {
			addDynamicButton(id, "Select", width, height);
			interfaceCache[id].selectableInterfaces = new int[] { QUANTITY_BUTTON_START, QUANTITY_BUTTON_START + 1, QUANTITY_BUTTON_START + 2, QUANTITY_BUTTON_START + 3, QUANTITY_BUTTON_START + 4 };
			rsi.child(child++, id, startX + (width * i + 5 * i), startY);
			id++;
		}
		
		String[] text = new String[] { "1", "5", "10", "X", "All" };
		
		QUANTITY_STRING_START = id;
		
		for (int i = 0; i < 5; i++) {
			addText(id, text[i], tda, 0, TEXT_COLOR, true, false);
			interfaceCache[id].enabledColor = 0xffffff;
			rsi.child(child++, id, startX + (width * i + 5 * i) + 14, startY + 10);
			id++;
		}
		
		startX = 6;
		startY = 32;
		
		ITEM_MODEL_START = id;
		ITEM_CHILD_START = child;
		
		for (int i = 0; i < 5; i++) {
			addItemModel(id, 0, 93, 72, 185);
			rsi.child(child++, id, startX + (93 * i + 7 * i), startY);
			id++;
		}
	}
	
	/**
	 * Called upon the initialization of this interface.
	 */
	public static void onInit() {
		interfaceCache[INTERFACE_ID].clickedChildId = -1;
		
		int amount = 0;
		
		for (int i = 0; i < 5; i++) {
			if (interfaceCache[ITEM_MODEL_START + i].mediaType == 0) {
				interfaceCache[BUTTON_START + i].hidden = true;
				interfaceCache[BUTTON_STRING_START + i].hidden = true;
				continue;
			}
			
			interfaceCache[BUTTON_START + i].hidden = false;
			interfaceCache[BUTTON_STRING_START + i].hidden = false;
			amount++;
		}
		
		shiftOptionButtons(amount);
	}

	/**
	 * Checks if the specified id is of an option button.
	 * 
	 * @param id The id.
	 * @return <code>true</code> if is.
	 */
	public static boolean isOptionButton(int id) {
		return id >= BUTTON_START && id < BUTTON_START + 5;
	}

	/**
	 * Handles the option buttons.
	 * 
	 * @param id The id.
	 */
	public static void handleOptionButton(int id) {
		if (!isOptionButton(id) || interfaceCache[INTERFACE_ID].clickedChildId != -1) {
			return;
		}

		interfaceCache[INTERFACE_ID].clickedChildId = id;
	}

	/**
	 * Shifts the option buttons based on the amount of material available.
	 * 
	 * @param amount The amount.
	 */
	private static void shiftOptionButtons(int amount) {
		for (int i = 0; i < amount; i++) {
			interfaceCache[ITEM_MODEL_START + i].xOffset = (5 - amount) * 99 / 2;
			interfaceCache[BUTTON_START + i].xOffset = (5 - amount) * 99 / 2;
			interfaceCache[BUTTON_STRING_START + i].xOffset = (5 - amount) * 99 / 2;
		}
	}
	
	/**
	 * Shifts the quantity buttons based on the amount of material available.
	 * 
	 * @param amount The amount.
	 */
	public static void shiftQuantities(int amount) {
		toggleQuantity(1, amount >= 5);
		
		toggleQuantity(2, amount >= 10);
		
		toggleQuantity(3, amount >= 2);
		
		int total = 0;
		
		for (int i = 0; i < 4; i++) {
			if (!interfaceCache[QUANTITY_BUTTON_START + i].hidden) {
				total++;
			}
		}
		
		int shift = 4 - total;
		
		for (int i = 0; i < 4 - shift; i++) {
			interfaceCache[QUANTITY_BUTTON_START + i].xOffset = shift * 35; 
			interfaceCache[QUANTITY_STRING_START + i].xOffset = shift * 35; 
		}
		
		interfaceCache[INTERFACE_ID + 1].xOffset = shift * 21;
		interfaceCache[INTERFACE_ID + 3].xOffset = shift * 21;
	}
	
	/**
	 * Toggles the specified quantity by it's index.
	 * 
	 * @param index   The index.
	 * @param visible The visibility flag.
	 */
	private static void toggleQuantity(int index, boolean visible) {
		interfaceCache[QUANTITY_STRING_START + index].hidden = !visible;
		interfaceCache[QUANTITY_BUTTON_START + index].hidden = !visible;
	}

	/**
	 * Checks if the specified skill button has been clicked on.
	 * 
	 * @param id
	 * @return <code>true</code> if clicked.
	 */
	public static boolean clickedSkillButton(int id) {
		return interfaceCache[INTERFACE_ID].clickedChildId == id;
	}

	/**
	 * Checks if the specified quantity is selected.
	 * 
	 * @param id The id.
	 * @return <code>true</code> if is.
	 */
	public static boolean isQuantitySelected(int id) {
		if (!isQuantityString(id)) {
			return false;
		}
		
		return interfaceCache[id - 5].selected;
	}
	
	/**
	 * Checks if the specified id is an item model.
	 * 
	 * @param id The id.
	 * @return <code>true</code> if is.
	 */
	public static boolean isItemModel(int id) {
		return id >= ITEM_MODEL_START && id < ITEM_MODEL_START + 5;
	}
	
	/**
	 * Gets the item name of the model for the specified button id.
	 * 
	 * @param childId The child id.
	 * @return The item name.
	 */
	public static String getItemName(int childId) {
		int index = 15 - (ITEM_MODEL_START - childId);
		
		int itemId = interfaceCache[ITEM_MODEL_START + index].mediaID;
		
		return ItemDefinition.forID(itemId).name;
	}
	
	/**
	 * Gets the visible options.
	 * 
	 * @return The visible options.
	 */
	public static int getVisibleOptions() {
		int amount = 0;
		
		for (int i = 0; i < 5; i++) {
			if (!interfaceCache[BUTTON_START + i].hidden) {
				amount++;
			}
		}
		
		return amount;
	}
	
	/**
	 * Handles the hotkeys.
	 * 
	 * @param key The key.
	 */
	public static void handleHotkey(int key) {
		if (key != 32 && (key < KeyEvent.VK_0 || key > KeyEvent.VK_9)) {
			return;
		}
		
		if (key == KeyEvent.VK_SPACE && getVisibleOptions() == 1) {
			handleOptionButton(SkillQuantityWidget.BUTTON_START);
			Client.instance.sendButtonClick(SkillQuantityWidget.BUTTON_START);
			return;
		} else {
			int index = key - 49;
			
			if (index <= getVisibleOptions()) {
				handleOptionButton(SkillQuantityWidget.BUTTON_START + index);
				Client.instance.sendButtonClick(SkillQuantityWidget.BUTTON_START + index);
			}
		}
	}
	
	/**
	 * Checks if the specified string is of a quantity string.
	 * 
	 * @param id The id.
	 * @return <code>true</code> if is.
	 */
	private static boolean isQuantityString(int id) {
		for (int i = QUANTITY_STRING_START; i < QUANTITY_STRING_START + 5; i++) {
			if (id == i) {
				return true;
			}
		}
		
		return false;
	}
	
}
