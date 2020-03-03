package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

public class QuestTab extends RSInterface {
	
	public static final int INTERFACE_ID = 73_000;
	
	public static final int TITLE_STRING_ID = INTERFACE_ID + 1;
	
	public static final int INFO_BUTTON_ID = INTERFACE_ID + 3;
	
	public static final int INFORMATION_TAB_ID = INTERFACE_ID + 11;
	
	public static final int FIRST_SCROLL_ID = INFORMATION_TAB_ID + 1;
	
	public static final int ACHIEVEMENT_TAB_ID = INTERFACE_ID + 300;
	
	public static final int ACTIVITY_TAB_ID = INTERFACE_ID + 500;
	
	public static final int MISC_TAB_ID = INTERFACE_ID + 700;

	public static void unpack(TextDrawingArea[] tda) {
		navigation(tda);
		questTab(tda);
		achievementsTab(tda);
		activityTab(tda);
		miscTab(tda);
	}

	private static void navigation(TextDrawingArea[] tda) {
		int id = INTERFACE_ID;
		
		RSInterface rsi = addInterface(id++);
		
		rsi.totalChildren(12 - 2);
		
		int child = 0;
		
		addText(id, "", tda, 2, 0xFF9800, false);
		rsi.child(child++, id, 9, 26);
		id++;
		
		addSprite(id, 1324);
		rsi.child(child++, id, 6, 23);
		id++;
		
		configHoverButton(id, "Information", 1326, 1326, 1325, 1325, true, new int[] { id + 1, id + 2, id + 3 });
		rsi.child(child++, id, 6, 3);
		
		configHoverButton(id + 1, "Achievements", 1325, 1325, 1326, 1326, false, new int[] { id, id + 2, id + 3 });
		rsi.child(child++, id + 1, 6 + 44, 3);
		
		configHoverButton(id + 2, "Activity", 1325, 1325, 1326, 1326, false, new int[] { id, id + 1, id + 3 });
		rsi.child(child++, id + 2, 6 + 44 * 2, 3);
		
		configHoverButton(id + 3, "Miscellaneous", 1325, 1325, 1326, 1326, false, new int[] { id, id + 1, id + 2 });
		rsi.child(child++, id + 3, 6 + 44 * 3, 3);
		
		id += 4;
		
		addSprite(id, 1327);
		rsi.child(child++, id, 19, 5);
		id++;
		
		addSprite(id, 1328);
		rsi.child(child++, id, 63, 5);
		id++;
		
		addSprite(id, 1329);
		rsi.child(child++, id, 107, 5);
		id++;
		
		addSprite(id, 1330);
		rsi.child(child++, id, 151, 5);
		id++;
		
		addRectangle(73221, 150, 0, true, 156, 195);
	}
	
	private static void questTab(TextDrawingArea[] tda) {
		int id = INFORMATION_TAB_ID;
		
		RSInterface tab = addTab(id++, 2);
		
		int child = 2;
		
		int width = 164 + 5;
		
		int height = 232 - 25;
		
		RSInterface scroll = addInterface(id++);
		scroll.totalChildren(200);
		scroll.width = width - 7;
		scroll.height = height;
		scroll.scrollMax = scroll.children.length * 20 + 9;
		
		int y = 9;
		
		for (int i = 0; i < scroll.children.length; i++) {
			addText(id, "" + id, tda, 0, 0xFFB000);
			scroll.child(i, id, 0, y);
			y += 17;
			id++;
		}
		
		y = 37 + 8;
		tab.child(child++, scroll.id, 9, y);
		
		addWindow(id, width - 1, height, false, true, true);
		tab.child(child++, id, 3, y);
		id+=8;
	}
	
	private static void achievementsTab(TextDrawingArea[] tda) {
		int id = ACHIEVEMENT_TAB_ID;
		
		RSInterface tab = addTabInterface(id);
		tab.totalChildren(2);
		tab.child(0, INTERFACE_ID, 0, 0);
		tab.child(1, 67_000, 0, 21);
	}
	
	private static void activityTab(TextDrawingArea[] tda) {
		int id = ACTIVITY_TAB_ID;
		
		RSInterface tab = addTab(id++, 2);
		
		int child = 2;
		
		int width = 164 + 5;
		
		int height = 232 - 25;
		
		RSInterface scroll = addInterface(id++);
		scroll.totalChildren(20);
		scroll.width = width - 7;
		scroll.height = height;
		scroll.scrollMax = scroll.children.length * 20 + 9;
		
		int y = 9;
		
		for (int i = 0; i < scroll.children.length; i++) {
			addText(id, "" + id, tda, 0, 0xFFB000);
			scroll.child(i, id, 0, y);
			y += 17;
			id++;
		}
		
		y = 37 + 8;
		tab.child(child++, scroll.id, 9, y);
		
		addWindow(id, width - 1, height, false, true, true);
		tab.child(child++, id, 3, y);
		id+=8;
	}
	
	private static void miscTab(TextDrawingArea[] tda) {
		int id = MISC_TAB_ID;
		
		RSInterface tab = addTab(id++, 2);
		
		int child = 2;
		
		int width = 164 + 5;
		
		int height = 232 - 25;
		
		RSInterface scroll = addInterface(id++);
		scroll.totalChildren(10);
		scroll.width = width - 7;
		scroll.height = height;
		scroll.scrollMax = scroll.children.length * 20 + 9;
		
		int y = 9;
		
		for (int i = 0; i < scroll.children.length; i++) {
			addText(id, "" + id, tda, 0, 0xFFB000);
			scroll.child(i, id, 0, y);
			y += 17;
			id++;
		}
		
		y = 37 + 8;
		tab.child(child++, scroll.id, 9, y);
		
		addWindow(id, width - 1, height, false, true, true);
		tab.child(child++, id, 3, y);
		id+=8;
	}
	
	private static RSInterface addTab(int id, int children) {
		RSInterface tab = addTabInterface(id);
		tab.totalChildren(2 + children);
		tab.child(0, 73221, 9, 51);
		tab.child(1, INTERFACE_ID, 0, 0);
		return tab;
	}
	
	public static boolean isQuestTabId(int id) {
		return id >= QuestTab.INTERFACE_ID && id <= QuestTab.INTERFACE_ID + 1000;
	}
	
}
