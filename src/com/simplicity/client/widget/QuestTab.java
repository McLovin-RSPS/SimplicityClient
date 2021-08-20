package com.simplicity.client.widget;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;
import com.simplicity.client.widget.raids.cox.tab.RaidingTab;

public class QuestTab extends RSInterface {
	
	public static final int INTERFACE_ID = 73_000;
	
	public static final int TITLE_STRING_ID = INTERFACE_ID + 1;
	
	public static final int INFO_BUTTON_ID = INTERFACE_ID + 3;
	
	public static final int INFORMATION_TAB_ID = INTERFACE_ID + 11;
	
	public static final int FIRST_SCROLL_ID = INFORMATION_TAB_ID + 1;
	
	public static final int QUEST_TAB_ID = INTERFACE_ID + 300;
	
	public static final int ACTIVITY_TAB_ID = INTERFACE_ID + 500;
	
	public static final int MISC_TAB_ID = INTERFACE_ID + 700;
	
	public static final int RAID_TAB_ID = INTERFACE_ID + 800;

	public static void unpack(TextDrawingArea[] tda) {
		navigation(tda);
		characterSummaryTab(tda);
		//informationTab(tda);
		questTab(tda);
		activityTab(tda);
		miscTab(tda);
		raidTab(tda);
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
		
		addSprite(id, 1330);
		rsi.child(child++, id, 19, 5);
		id++;
		
		addSprite(id, 1327);
		rsi.child(child++, id, 63, 5);
		id++;
		
		addSprite(id, 1329);
		rsi.child(child++, id, 107, 5);
		id++;
		
		addSprite(id, 1336);
		rsi.child(child++, id, 151, 5);
		id++;
		
		addRectangle(73221, 150, 0, true, 156, 195);
	}

	private static void characterSummaryTab(TextDrawingArea[] tda) {
		int id = INFORMATION_TAB_ID;

		RSInterface tab = addTabInterface(id++);
		tab.totalChildren(10 + 6);
		tab.child(0, INTERFACE_ID, 0, 0);

		int child = 1;

		int width = 164 + 5;
		int height = 232 - 25;
		int baseX = 3;
		int baseY = 0;

		addText(id, "", 2, 0xff9800);
		RSInterface.interfaceCache[id].width = 183;
		RSInterface.interfaceCache[id].height = 20;
		RSInterface.interfaceCache[id].centerText = true;
		RSInterface.interfaceCache[id].useNewFonts = true;
		tab.child(child++, id++, baseX, baseY + 26);

		int blockWidth = 90 + 1;
		int blockHeight = 42;
		int mBlockH = 54;
		int lBlockW = 184;
		int lBlockH = 28;
		int gap = 2;

		int yPos = baseY + blockHeight + 2;

		RSInterface cmb = addBlock(0, id, blockWidth, blockHeight, "Combat Level:", "70");
		tab.child(child++, cmb.id, baseX, yPos);
		id += cmb.children.length + 1;
        addSprite(id, 1972);
        tab.child(child++, id++, baseX + 30, yPos + (blockHeight / 2) + 1);

		RSInterface total = addBlock(1, id, blockWidth, blockHeight, "Total Level:", "1220");
		tab.child(child++, total.id, baseX + blockWidth + gap, yPos);
		id += total.children.length + 1;
		addSprite(id, 1973);
        tab.child(child++, id++, baseX + blockWidth + 26, yPos + (blockHeight / 2));
		yPos += blockHeight + 2;

		RSInterface xp = addBlock(2, id, lBlockW, lBlockH, "<spr=1974:-3> Total XP: <col=0dc10d>8,019,799</col>", "");
		tab.child(child++, xp.id, baseX, yPos);
		id += xp.children.length + 1;
		/*addSprite(id, 1974);
		tab.child(child++, id++, baseX + 32, yPos + 5);*/
		id++;
		yPos += lBlockH + 2;

		RSInterface quests = addBlock(3, id, blockWidth, mBlockH, "Quests\\nCompleted:", "33/150");
		tab.child(child++, quests.id, baseX, yPos);
		id += quests.children.length + 1;
        addSprite(id, 1327);
        tab.child(child++, id++, baseX + 15, yPos + 32);

		RSInterface ach = addBlock(4, id, blockWidth, mBlockH, "Achievements\\nCompleted:", "69/492");
		tab.child(child++, ach.id, baseX + blockWidth + gap, yPos);
		id += ach.children.length + 1;
        addSprite(id, 1328);
        tab.child(child++, id++, baseX + blockWidth + gap + 15, yPos + 32);
		yPos += mBlockH + 2;

		RSInterface cmbTasks = addBlock(5, id, blockWidth, mBlockH, "M Achievements\\nCompleted:", "1/410");
		tab.child(child++, cmbTasks.id, baseX, yPos);
		id += cmbTasks.children.length + 1;
        addSprite(id, 1975);
        tab.child(child++, id++, baseX + 22, yPos + 32);

		RSInterface collections = addBlock(6, id, blockWidth, mBlockH, "Collections\\nLogged:", "10/1365");
		tab.child(child++, collections.id, baseX + blockWidth + gap, yPos);
		id += collections.children.length + 1;
        addSprite(id, 1976);
        tab.child(child++, id++, baseX + blockWidth + gap + 14, yPos + 32);
		yPos += mBlockH + 2;

		RSInterface played = addBlock(7, id, lBlockW, lBlockH, "Time Played: <col=0dc10d>21 days, 4 hours</col>", "");
		tab.child(child++, played.id, baseX, yPos);
		id += played.children.length + 1;
	}

	private static RSInterface addBlock(int index, int id, int width, int height, String title, String value) {
		int x = 0;
		int y = 0;

		RSInterface block = addInterface(id++);
		block.totalChildren(13);

		int child = 0;

		addSprite(id, 1968);
		block.child(child++, id++, x, y);

		addSprite(id, 1969);
		block.child(child++, id++, x + width - 8, y);

		addSprite(id, 1970);
		block.child(child++, id++, x, y + height - 8);

		addSprite(id, 1971);
		block.child(child++, id++, x + width - 8, y + height - 8);

		addSprite(id, 1977);
		block.child(child++, id++, x + 8, y);

		addSpriteRepeatX(id,1978, width - 16);
		block.child(child++, id++, x + 8, y + height - 4);

		addSpriteRepeatX(id,1977, width - 16);
		block.child(child++, id++, x + 8, y);

		addSpriteRepeatY(id, 1979, height - 16);
		block.child(child++, id++, x, y + 8);

		addSpriteRepeatY(id, 1980, height - 16);
		block.child(child++, id++, x + width - 4, y + 8);

		addSpriteRepeatBoth(id,1981, width - 8, height - 16);
		block.child(child++, id++, x + 4, y + 8);

		addSpriteRepeatBoth(id,1981, width - 16, height - 8);
		block.child(child++, id++, x + 8, y + 4);

		addText(id, title, 0, 0xff9933);
		interfaceCache[id].width = width - 8;
		interfaceCache[id].height = height;
		interfaceCache[id].centerText = true;
		interfaceCache[id].useNewFonts = true;

		if (index >= 3 && index <= 6) {
			interfaceCache[id].atActionType = 1;
			interfaceCache[id].contentType = 0;
			interfaceCache[id].disabledMouseOverColor = 0xffffff;
			interfaceCache[id].tooltip = "View " + title.replaceAll(":", "").replace("\\n", " ");
		}

        int textXOff = 0;
		int textYOff = 5;

		switch (index) {
			case 2:
			    textXOff = 0;
			    textYOff = 9;
			    break;
			case 7:
				textYOff = 9;
				break;
			case 3:
			case 4:
			case 5:
			case 6:
				textYOff = 11;
				break;
		}

		block.child(child++, id++, x + 5 + textXOff, y + textYOff);

		textXOff = textYOff = 0;

		switch (index) {
            case 0:
                textXOff = 17;
                break;
            case 1:
                textXOff = 11;
                break;
            case 3:
            case 4:
                textXOff = 5;
                textYOff = 12;
                break;
            case 5:
                textXOff = 10;
                textYOff = 12;
                break;
            case 6:
                textXOff = 2;
                textYOff = 12;
                break;
        }

		addText(id, value, 0, 0x0dc10d);
		interfaceCache[id].width = width - 8;
		interfaceCache[id].height = height;
		interfaceCache[id].useNewFonts = true;
		block.child(child++, id++, x + 34 + textXOff, y + 23 + textYOff);
		return block;
	}
	
	private static void informationTab(TextDrawingArea[] tda) {
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
	
	private static void questTab(TextDrawingArea[] tda) {
		int id = QUEST_TAB_ID;
		
		RSInterface tab = addTab(id++, 2);
		
		int child = 2;
		
		int width = 164 + 5;
		
		int height = 232 - 25;
		
		RSInterface scroll = addInterface(id++);
		scroll.totalChildren(50);
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
	
	private static void raidTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(RAID_TAB_ID);
		tab.totalChildren(2 + 0);
		tab.child(0, INTERFACE_ID, 0, 0);
		tab.child(1, RaidingTab.WIDGET_ID, 4, 26);
	}
	
	private static RSInterface addTab(int id, int children) {
		RSInterface tab = addTabInterface(id);
		tab.totalChildren(2 + children);
		tab.child(0, 73221, 9, 51);
		tab.child(1, INTERFACE_ID, 0, 0);
		return tab;
	}

	public static void setTab(int buttonId) {
		int tab = buttonId - 73003;
		String[] titles = { "", "Player Information", "Activity", "Miscellaneous" };
		int[] tabs = new int[] { INFORMATION_TAB_ID, QUEST_TAB_ID, ACTIVITY_TAB_ID, MISC_TAB_ID };
		Client.getClient().tabInterfaceIDs[2] = tabs[tab];
		interfaceCache[INTERFACE_ID + 1].message = titles[tab];
	}
	
	public static boolean isQuestTabId(int id) {
		return id >= QuestTab.INTERFACE_ID && id <= QuestTab.INTERFACE_ID + 1000;
	}
	
}
