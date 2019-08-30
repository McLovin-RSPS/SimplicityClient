package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

/**
 * A class that handles the achievements tab.
 * 
 * @author Blake
 *
 */
public class AchievementsWidget extends RSInterface {
	
	/**
	 * The interface id.
	 */
	public static final int INTERFACE_ID = 67_000;
	
	/**
	 * The achievement text color.
	 */
	public static final int ACHIEVEMENT_TEXT_COLOR = 0xffff00;
	
	/**
	 * The amount of achievement space per page.
	 */
	public static final int ACHIEVEMENT_COUNT = 50;
	
	public static void unpack(TextDrawingArea[] tda) {
		int id = INTERFACE_ID;
		
		RSInterface tab = addTabInterface(id++);
		
		tab.totalChildren(15);
		
		int scrollHeight = 201;
		
		int child = 0;
		
		addText(id, "Achievements", tda, 2, 0xFF9900);
		tab.child(child++, id, /*32*/4, 4);
		id++;
		
		addText(id, "(0/106)", tda, 1, 0xFF9900, true, true);
		tab.child(child++, id, 163, 4);
		id++;
		
		addSpriteLoader(id, 723); // Background
		tab.child(child++, id, 0, 25);
		id++;
		
		int separator = id;
		
		addSpriteLoader(separator, 722); // First separator
		tab.child(child++, id, 0, 22);
		id+=2;
		
		tab.child(child++, separator, 0, 37 + 5); // Middle separator
		id++;
		
		int startX = 8;
		
		int startY = 28;
		
		int rectWidth = 48;
		
		for (int i = 0; i < 4; i++) {
			addRectangleClickable(id, 50, 0x50483d, true, rectWidth, 17);
			interfaceCache[id].enabledOpacity = 200;
			interfaceCache[id].enabledColor = 0x00ff00;
			interfaceCache[id].selectableInterfaces = new int[] { 67007, 67008, 67009, 67010 };
			tab.child(child++, id, startX - 8 + (rectWidth * i) , startY - 3);
			id++;
		}
		
		addSpriteLoader(id, 718);
		tab.child(child++, id, startX + 11, startY);
		id++;
		
		addSpriteLoader(id, 719);
		tab.child(child++, id, startX + 59, startY);
		id++;
		
		addSpriteLoader(id, 717);
		tab.child(child++, id, startX + 11 + 96, startY);
		id++;
		
		addSpriteLoader(id, 1255);
		tab.child(child++, id, startX + 11 + 144, startY);
		id++;
		
		tab.child(child++, id, 2, 39 + 5); // scroll position
		
		// Bottom separator
		tab.child(child++, separator, 0, scrollHeight + 44);
		
		RSInterface scroll = addTabInterface(id++);
		
        scroll.width = 172;
        scroll.height = scrollHeight;
        scroll.scrollMax = 3500;
		scroll.totalChildren(ACHIEVEMENT_COUNT * 4);
		
		child = 0;
		
		int xPos = 0;
		
		int yPos = 1;
		
		int width = 170;
		
		int height = 32;
		
		for (int i = 0; i < ACHIEVEMENT_COUNT; i++) {
			addRectangle(id, 50, 0x322a20, true, width - 2, height - 2); // 50
			scroll.child(child++, id, xPos + 1, yPos + 1);
			id++;
			
			addRectangle(id, 130, 0xffff00, false, width, height); // 130
			scroll.child(child++, id, xPos, yPos);
			id++;
			
			addText(id, "", tda, 0, ACHIEVEMENT_TEXT_COLOR, false);
			scroll.child(child++, id, 4 + 12, yPos + 3); // 83
			id++;
			
			addProgressBar(id, width - 1, 11, 0, 1);
			scroll.child(child++, id, xPos + 4, yPos + 17);
			id++;
			
			yPos += height + 1;
		}
		
	}

}
