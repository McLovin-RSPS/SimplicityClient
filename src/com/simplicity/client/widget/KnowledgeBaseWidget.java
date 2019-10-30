package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

public class KnowledgeBaseWidget extends RSInterface {
	
	public static final int INTERFACE_ID = 68_000;
	
	public static final int CATEGORY_LINE_START = INTERFACE_ID + 18;
	
	public static final int CATEGORY_COUNT = 50;
	
	public static final int CATEGORY_LINE_END = CATEGORY_LINE_START + CATEGORY_COUNT;
	
	public static void unpack(TextDrawingArea[] tda) {
		int id = INTERFACE_ID;
		
		RSInterface rsi = addInterface(id++);
		
		rsi.totalChildren(10);
		
		int child = 0;
		
		addClosableWindow(id, 484, 304, true, "Knowledgebase");
		rsi.child(child++, id, 14, 16);
		id+=9;
		
		addVerticalSeparator(id, 239 + 24, true);
		rsi.child(child++, id, 20 + 149, 51);
		id+=2;
		
		addRectangle(id, 120, 0, true, 149, 20);
		rsi.child(child++, id, 20, 52);
		id++;
		
		addRectangle(id, 120, 0, true, 314, 20);
		rsi.child(child++, id, 20 + 150 + 6, 52);
		id++;
		
		addHorizontalLine(id, 150, 0xFF9811, 150);
		rsi.child(child++, id, 20, 53 + 19);
		id++;
		
		addHorizontalLine(id, 314, 0xFF9811, 150);
		rsi.child(child++, id, 20 + 150 + 6, 53 + 19);
		id++;
		
		addText(id, "Categories", tda, 2, 0xFF9811);
		rsi.child(child++, id, 24, 55);
		id++;
		
		addText(id, "Select an article...", tda, 2, 0xFF9811);
		rsi.child(child++, id, 24 + 157, 55);
		id++;
		
		RSInterface categories = addInterface(id++);
		categories.width = 134;
		categories.height = 239;
		categories.scrollMax = 350;
		
		int lines = 50;
		
		categories.totalChildren(lines);
		
		int y = -10;
		
		for (int i = 0; i < lines; i++) {
			addClickableText(id, "", "View", tda, 0, 0xFF9811, false, true, 140);
			categories.child(i, id, 4, y);
			y += 14;
			id++;
		}
		
		rsi.child(child++, categories.id, 20, 53 + 20);
		
		RSInterface content = addInterface(id++);
		content.width = 296;
		content.height = 239;
		content.scrollMax = 2107;
		content.totalChildren(150);
		
		y = 0;
		
		for (int i = 0; i < 150; i++) {
			addText(id, "", tda, 0, 0xFF9811, true);
			content.child(i, id, 148, 4 + y);
			y += 14;
			id++;
		}
		
		rsi.child(child++, content.id, 16 + 308 - 146, 53 + 20);
	}

}
