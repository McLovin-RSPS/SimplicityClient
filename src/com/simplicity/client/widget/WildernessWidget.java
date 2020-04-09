package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

/**
 * A class that handles the wilderness widget.
 * 
 * @author Blake
 *
 */
public class WildernessWidget extends RSInterface {

	/**
	 * The interface id.
	 */
	public static final int INTERFACE_ID = 25347;
	
	/**
	 * The target string id.
	 */
	public static int TARGET_NAME;
	
	/**
	 * The level string id.
	 */
	public static int TARGET_LEVEL;
	
	/**
	 * The wilderness level id.
	 */
	public static int LEVELS_RANGE; 
	
	/**
	 * The skip target button id.
	 */
	public static int SKIP_TARGET_ID;
	
	/**
	 * The minimize button id.
	 */
	public static int MINIMIZE_ID;
	
	/**
	 * Unpacks the interface.
	 */
	public static void unpack() {
    	int id = INTERFACE_ID;
    	
        RSInterface tab = addInterface(id++);
        tab.totalChildren(11);
        
        int y = 35;
        
        int child = 0;
        
        drawBox(id, 175, 50, 2, 0x69605b, 0x383023, 200, 0x484224, 100);
        tab.child(child++, id++, 334, y + 2);
        
        addText(id, "", fonts, 0, 0xff9933, true, true);
        tab.child(child++, id++, 356, y + 10);
        
        TARGET_NAME = id;
        addText(id, "---", fonts, 0, 0xff9933, true, true);
        tab.child(child++, id++, 445 - 30, y + 5);
        
        TARGET_LEVEL = id;
        addText(id, "Level 1", fonts, 0, 0xffffff, false, true);
        tab.child(child++, id++, 340 + 10, y + 28 - 6);
        
        addSprite(id, 1367);
        tab.child(child++, id++, 338, y + 28 - 7);
        
        addText(id, "Target Percentage:", fonts, 0, 0xff9933, false, true);
        tab.child(child++, id++, 340, y + 36);
        
        addText(id, "99%", fonts, 0, 0xff9933, true, true);
        tab.child(child++, id++, 492, y + 36);
        
        LEVELS_RANGE = id; 
        addText(id, "118-126", fonts, 1, 0xffff00, true, true);
        tab.child(child++, id++, 411 + 73, 315 - 32);
        
        SKIP_TARGET_ID = id;
        hoverButton(id, "Skip target", new String[] {  }, 1365, 1366);
        interfaceCache[id].positionX = 445 + 48;
        interfaceCache[id].positionY = y + 5;
        tab.child(child++, id++, 445 + 48, y + 5);
        
        MINIMIZE_ID = id;
        hoverButton(id, "Minimize", new String[] {  }, 1368, 1369, 1370, 1371);
        interfaceCache[id].positionX = 340 - 3;
        interfaceCache[id].positionY = y + 5;
        tab.child(child++, id++, 340 - 3, y + 5);
        
        tab.child(child++, 197, 0, -10); // Skull
	}

	public static void toggle() {
		for (int childId : interfaceCache[INTERFACE_ID].children) {
			RSInterface child = interfaceCache[childId];
			
			if (child == null || childId == LEVELS_RANGE || childId == MINIMIZE_ID || childId == 197) {
				continue;
			}
			
			child.hidden = !child.hidden;
		}
		
		configHoverButtonSwitch(interfaceCache[MINIMIZE_ID]);
	}
	
	public static boolean isHidden() {
		return interfaceCache[SKIP_TARGET_ID].hidden;
	}

}
