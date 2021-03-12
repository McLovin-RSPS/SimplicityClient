package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

/**
 * A class that handles the starter interface.
 * 
 * @author Blake
 *
 */
public class StarterWidget extends RSInterface {

	/**
	 * The interface id.
	 */
	public static final int INTERFACE_ID = 71_000;
	
	public static int NORMAL_ID;

	/**
	 * Unpacks the interface.
	 * 
	 * @param tda
	 */
	public static void unpack(TextDrawingArea[] tda) {
		int id = INTERFACE_ID;

		RSInterface rsi = addInterface(id++);

		rsi.totalChildren(45);
		
		int child = 0;
		
		addWindow(id, 500, 304, true, false, true);
		rsi.child(child++, id, 6, 16);
		id += 9;
		
		addHorizontalSeparator(id, 488, true);
		rsi.child(child++, id, 12, 52);
		id+=2;
		
		addHorizontalSeparator(id, 488, true);
		rsi.child(child++, id, 12, 58 + 155);
		id+=2;
		
		addVerticalSeparator(id, 156, true);
		rsi.child(child++, id, 140, 58);
		id+=2;
		
		addVerticalSeparator(id, 95, true);
		rsi.child(child++, id, 140 + 135 + 18, 219);
		id+=2;
		
		addText(id, "Welcome to Simplicity!", tda, 2, 0xff9800, true, true);
		rsi.child(child++, id, 255, 30);
		id++;
		
		addRectangle(id, 150, 0, true, 127, 154);
		rsi.child(child++, id, 13, 84 - 25);
		id++;
		
		/*addText(id, "Game Mode", tda, 2, 0xffffff, true, true);
		rsi.child(child++, id, 75, 64);
		id++;*/
		
		String[] text = new String[] { "Normal", "Standard Iron", "Hardcore Iron", "Ultimate Iron", "Group Iron", "Realism" };
		
		int[] icon = new int[] { 712, 1142, 711, 1316, 1727 };
		
		int boxWidth = 127;

		int baseY = 59;
		
		NORMAL_ID = id;
		
		configHoverButton(id, "Select Normal", 1040, 1040, 1039, 1039, true, new int[] { id + 1, id + 2, id + 3, id + 4, id + 5 });
		rsi.child(child++, id, 20, baseY + 5);
		id++;
		
		configHoverButton(id, "Select Standard", 1039, 1039, 1040, 1040, false, new int[] { id - 1, id + 1, id + 2, id + 3, id + 4 });
		rsi.child(child++, id, 20, baseY + 5 + 26);
		id++;
		
		configHoverButton(id, "Select Hardcore", 1039, 1039, 1040, 1040, false, new int[] { id - 2, id - 1, id + 1, id + 2, id + 3 });
		rsi.child(child++, id, 20, baseY + 5 + 2 * 26);
		id++;
		
		configHoverButton(id, "Select Ultimate", 1039, 1039, 1040, 1040, false, new int[] { id - 3, id - 2, id - 1, id + 1, id + 2 });
		rsi.child(child++, id, 20, baseY + 5 + 3 * 26);
		id++;
		
		configHoverButton(id, "Select Group", 1039, 1039, 1040, 1040, false, new int[] { id - 4, id - 3, id - 2, id - 1, id + 1 });
		rsi.child(child++, id, 20, baseY + 5 + 4 * 26);
		id++;

		configHoverButton(id, "Select Realism", 1039, 1039, 1040, 1040, false, new int[] { id - 5, id - 4, id - 3, id - 2, id - 1 });
		rsi.child(child++, id, 20, baseY + 5 + 5 * 26);
		id++;

		for (int i = 0; i < text.length; i++) {
			if (i != 0) {
				addSprite(id, icon[i - 1]);
				rsi.child(child++, id, 50 - (i == text.length - 1 ? 10 : 8), baseY + 5 + i * 26);
				id++;
			}
			
			addRectangle(id, 160, 0, true, boxWidth - 2, 24 - 2);
			rsi.child(child++, id, 14, baseY + 1 + i * 26);
			id++;
			
			addRectangle(id, 110, 0x4a4333, false, boxWidth, 24);
			rsi.child(child++, id, 13, baseY + i * 26);
			id++;
			
			addText(id, text[i], tda, 1, 0xffb000, false, true);
			rsi.child(child++, id, i == 0 ? 42 : 56, baseY + 5 + i * 26);
			id++;
		}

		addItemContainer(id, new int[] {16, 6}, new int[] {7, 4}, null, false);
		rsi.child(child++, id, 160, 64);
		id++;
		
		addRectangle(id, 180, 0, true, 262 + 18, 93);
		rsi.child(child++, id, 13, 220);
		id++;
		
		addText(id, "Description:", tda, 2, 0xff9800, false, true);
		rsi.child(child++, id, 20 + 90 + 9, 224);
		id++;
		
		addHorizontalLine(id, 73, 0xff9800, 100);
		rsi.child(child++, id, 111 + 9, 224 + 16);
		id++;

		addText(id, "As a normal player you will be able to play the game \\nwithout any restrictions.", tda, 0, 0xa8a8a8, true, true);
		rsi.child(child++, id, 151, 224 + 16 + 5);
		id++;
		
		addHoverButton_sprite_loader(id, 1313, 90 + 18, 28, "Confirm", -1, id + 1, 5);
        addHoveredImageWSpriteLoader(id + 1, 1314, 90 + 18, 28, id + 2);
        
        rsi.child(child++, id, 209 + 134 + 9, 250 + 29 - 8);
        rsi.child(child++, id + 1, 209 + 134 + 9, 250 + 29 - 8);
        id += 3;

		addText(id, "Confirm", tda, 2, 0x00ff00, false, true);
		rsi.child(child++, id, 227 + 134 + 9, 234 + 49 - 6);
		id++;
		
		addText(id, "Click '@gre@Confirm'@or1@ to proceed.", tda, 2, 0xffb000, false, true);
		rsi.child(child++, id, 227 + 134 - 52 + 4, 234 + 49 - 41 - 5);
		id++;
	}
	
	public static void onDisplay() {
		RSInterface.setSelectedInterface(NORMAL_ID);
	}

}
