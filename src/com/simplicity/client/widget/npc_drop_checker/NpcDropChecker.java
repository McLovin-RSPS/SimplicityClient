package com.simplicity.client.widget.npc_drop_checker;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

/**
 * Handles the Npc Drop Table Checker Interface
 * 
 * @author Ali/Leonidas https://www.rune-server.ee/members/_ali/
 *
 */
public class NpcDropChecker extends CustomWidget {

	public static final int ID = 83000;
	
	public NpcDropChecker() {
		super(ID);
	}

	@Override
	public String getName() {
		return "Npc Drop Checker";
	}

	@Override
	public void init() {
		
		RSInterface window = addWindow(500, 315, true);
		
		add(window, 5, 10);
		
		//add(addSprite(1942), 10, 2);
		//add(addBackground(1942), 0, 0);
		
		add(addHorizontalSeparator(488,true), 10, 45);
		add(addVerticalSeparator(267, true), 145, 51);
		
		add(addButton(1068, 1069, "Close Window", 0, 3), 472, 20);
		
		add(addCenteredText(getName(), 2, 0xFF981F), 260, 22);
		
		//add(addButton(1024, 1024, "Search a Monster", 0, 1), 80, 250);
		add(addDynamicButton("Search a Monster", 1, 0xff981f, 130, 23), 13, 54);
		add(addDynamicButton("Search an Item", 1, 0xff981f, 130, 23), 13, 79);
		add(addText("Showing Results For:", 0, 0xffffff, true), 78, 105);
		//System.out.println("NPC SEARCH RESULT ID: "+id);
		add(addText("", 0, 0xff981f, true), 78, 120);
	
		//System.out.println("npc scroll list parent id: "+id);
		final RSInterface npc_list_widget = RSInterface.addTabInterface(id);
		npc_list_widget.componentId = id;
        id++;
		npc_list_widget.height = 180;
		npc_list_widget.width = 122;
		npc_list_widget.scrollMax = 7000; //3210

		npc_list_widget.totalChildren(350);
		
        int yPosition = 0;
        for (int index = 0; index < 350; index++) {
            final int childId = id;
            RSInterface.addClickableText(childId, "# "+childId, "Check NPC Drops", RSInterface.fonts, 0, 0xFFFFFF, 70, 13);
            RSInterface.setBounds(childId, 10, yPosition, index, npc_list_widget);
            yPosition += 15;
            id++;
        }
        
        add(npc_list_widget, 5, 135);
        
        add(addRectangle(343, 261, 0x897661, 0, false), 153, 54);
        add(addRectangle(343, 20, 0x897661, 0, false), 153, 54);
        add(addTextList(new String[] { "Name", "Qty", "Chance" }, 1, 0xff981f, false, 90, false), 240, 57);
        
        //System.out.println("npc DROP RESULTS SCROLL WIDGET ID: "+id);
        final RSInterface result_list_widget = RSInterface.addTabInterface(id);
        result_list_widget.componentId = id;
        id++;
        result_list_widget.height = 234;
        result_list_widget.width = 320;
        result_list_widget.scrollMax = 3500;
        
        result_list_widget.totalChildren(305);
        
        yPosition = 0;
        for (int index = 0; index < 4; index++) {
           RSInterface.addSprite(id, 1944);
           RSInterface.setBounds(id++, 0, yPosition, index, result_list_widget);
           yPosition += 875;
        }
        
        //System.out.println("ITEM CONTAINER ID: "+id);
        RSInterface.addToItemGroup(id, 1, 100, 10, 3, false, null, null, null);
        RSInterface.setBounds(id++, 20, 3, 4, result_list_widget);
        
        //RSInterface item = RSInterface.interfaceCache[id - 1];
        
        int frame = 5;
        yPosition = 9;
        int xPosition = 110;
        id = 104000;
        for (int index = 0; index < 100; index++) {
        	
        	RSInterface.addText(id, "", 0xFFFFFF, true, true, -1, 1);
        	RSInterface.setBounds(id++, xPosition, yPosition, frame++, result_list_widget);
        	
        	RSInterface.addText(id, "", 0xFFFFFF, true, true, -1, 1);
        	RSInterface.setBounds(id++, xPosition + 75, yPosition, frame++, result_list_widget);
        	
        	RSInterface.addText(id, "", 0xFFFFFF, true, true, -1, 1);
        	RSInterface.setBounds(id++, xPosition + 175, yPosition, frame++, result_list_widget);
        	
        	yPosition += 35;
        }
                
        /*for (int idx = 0; idx < item.inv.length; idx++) {
        	item.inv[idx] = 4152;
        	item.invStackSizes[idx] = 100;
        }*/
        
        add(result_list_widget, 156, 77);
	}

}
