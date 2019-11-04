package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

/**
 * A class that handles the collection log interface.
 * 
 * @author Blake
 *
 */
public class CollectionLogWidget extends RSInterface {

	/**
	 * The interface id.
	 */
	public static final int INTERFACE_ID = 70_000;

	/**
	 * The amount of nodes in the list.
	 */
	public static final int LIST_SIZE = 100;

	/**
	 * The item container id.
	 */
	public static final int ITEM_CONTAINER = INTERFACE_ID + LIST_SIZE * 2 + 28;

	/**
	 * Unpacks the interface.
	 * 
	 * @param tda
	 */
	public static void unpack(TextDrawingArea[] tda) {
		int id = INTERFACE_ID;

		RSInterface rsi = addInterface(id++);

		rsi.totalChildren(19);

		int child = 0;

		addClosableWindow(id, 500, 304, true, "Collection Log");
		rsi.child(child++, id, 6, 16);
		id += 9;

		String[] text = new String[] { "Bosses", "Raids", "Clues", "Minigames", "Other" };

		for (int i = 0; i < 5; i++) {

			addTab(id + i, 58, "View");
			rsi.child(child++, id + i, 16 + (i * 96), 16 + 37);
			interfaceCache[id + i].selectableInterfaces = new int[] { 70010, 70011, 70012, 70013, 70014 };

			addText(id + i + 5, text[i], tda, 1, 0xff981f);
			rsi.child(child++, id + i + 5, 16 + (i * 96) + 4, 16 + 37 + 2);
		}

		id += 10;

		addRectangle(id, 204, 237, 0x585040, 0, false);
		rsi.child(child++, id, 16, 73);
		id++;

		addRectangle(id, 276, 41, 0x585040, 0, false);
		rsi.child(child++, id, 16 + 203, 73);
		id++;

		addRectangle(id, 276, 197, 0x585040, 0, false);
		rsi.child(child++, id, 16 + 203, 73 + 40);
		id++;

		addText(id, "Zulrah", tda, 2, 0xff981f);
		rsi.child(child++, id, 16 + 203 + 10, 73 + 1);
		id++;

		addText(id, "Obtained: @gre@11/11", tda, 0, 0xff981f);
		rsi.child(child++, id, 16 + 203 + 10, 73 + 24);
		id++;

		addText(id, "Kills: @whi@5063", tda, 0, 0xff981f);
		rsi.child(child++, id, 203 + 200, 73 + 24);
		id++;

		RSInterface scroll = addInterface(id++);

		scroll.width = 186;
		scroll.height = 235;
		scroll.scrollMax = LIST_SIZE * 16;
		scroll.totalChildren(LIST_SIZE * 2);

		int scrollChild = 0;

		int[] selectable = new int[LIST_SIZE];

		for (int i = 0; i < LIST_SIZE; i++) {
			selectable[i] = id + i;
		}

		for (int i = 0; i < LIST_SIZE; i++) {
			int color = i % 2 == 0 ? 0x564c42 : 0x483f33;

			addRectangleClickable(id + i, 50, color, true, 186, 16);
			interfaceCache[id + i].hovers = true;
			interfaceCache[id + i].hoverType = id + i;
			interfaceCache[id + i].enabledOpacity = 200;
			interfaceCache[id + i].enabledColor = 0xffffff;
			interfaceCache[id + i].selectableInterfaces = selectable;
			interfaceCache[id + i].enabledMouseOverColor = 0xffffff;
			scroll.child(scrollChild++, id + i, 0, (i * 16));

			addText(id + i + (LIST_SIZE), "" + (id + i + (LIST_SIZE)), tda, 1, 0xff981f);
			scroll.child(scrollChild++, id + i + (LIST_SIZE), 0, 1 + (i * 16));
		}

		id += LIST_SIZE * 2;

		rsi.child(child++, scroll.id, 17, 74);

		scroll = addInterface(id++);
		scroll.totalChildren(1);
		scroll.width = 250;
		scroll.height = 195;
		scroll.scrollMax = 500;

		addItemContainer(id, new int[] { 8, 0 }, new int[] { 6, 15 }, new String[] {}, false);
		scroll.child(0, id, 0, 0);
		id++;

		rsi.child(child++, scroll.id, 228, 114);
	}

}
