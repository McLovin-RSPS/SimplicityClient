package com.simplicity.client.widget.alchemy;

import com.simplicity.client.RSInterface;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.Animation;
import com.simplicity.client.widget.CustomWidget;

/**
 * Handles the AlchemyTable Interface
 * 
 * @author Ali/Leonidas https://www.rune-server.ee/members/_ali/ - 14.05.2022
 *
 */
public class AlchemyTable extends CustomWidget {

	public static final int ID = 103_000;
	
	public AlchemyTable() {
		super(ID);
	}
	
	@Override
	public String getName() {
		return "Alchemy Table";
	}
	
	@Override
	public void init() {
		
		RSInterface window = addWindow(500, 315, true);
		
		add(window, 5, 10);
		
		add(addHorizontalSeparator(488,true), 10, 45);
		add(addVerticalSeparator(267, true), 145, 51);
		
		add(addButton(1068, 1069, "Close Window", 0, 3), 472, 20);
		
		add(addCenteredText(getName(), 2, 0xFF981F), 260, 18);
		add(addHorizontalLine(99, 0xFF981F, 0), 211, 34);
		
		//add(addButton(1024, 1024, "Search a Monster", 0, 1), 80, 250);
		add(addDynamicButton("Search a Result", 1, 0xff981f, 130, 23), 13, 54);
		add(addDynamicButton("Search Ingredient", 1, 0xff981f, 130, 23), 13, 79);
		add(addText("Showing Results For:", 0, 0xffffff, true), 78, 105);
		System.out.println("ALCHEMY SEARCH RESULT ID: "+id);
		add(addText("Results here...", 0, 0xff981f, true), 78, 120);
		
		System.out.println("alchemy results scroll list parent id: "+id);
		final RSInterface alchemy_result_list_widget = RSInterface.addTabInterface(id);
		alchemy_result_list_widget.componentId = id;
		id++;
		alchemy_result_list_widget.height = 180;
		alchemy_result_list_widget.width = 122;
		alchemy_result_list_widget.scrollMax = 2250; //3210

		alchemy_result_list_widget.totalChildren(150);
		
		int yPosition = 0;
        for (int index = 0; index < 150; index++) {
            final int childId = id;
            RSInterface.addClickableText(childId, "# "+childId, "Check Results", RSInterface.fonts, 0, 0xFFFFFF, 70, 13);
            RSInterface.setBounds(childId, 10, yPosition, index, alchemy_result_list_widget);
            yPosition += 15;
            id++;
        }
        
        add(alchemy_result_list_widget, 5, 135);
        
        //Header for results item group
        System.out.println("idddd  : "+id);
        add(addCenteredText("Result", 2, 0xFF981F), 323, 51);
        add(addHorizontalLine(42, 0xFF981F, 255), 302, 65);
        
        //Box holding result items
        add(addBox(148, 42, 3, 0xFF981F, 0, 255), 247, 69);
        add(addVerticalLine(36, 0xFF981F, 255), 298, 71);
        add(addVerticalLine(36, 0xFF981F, 255), 299, 71);
        add(addVerticalLine(36, 0xFF981F, 255), 347, 71);
        add(addVerticalLine(36, 0xFF981F, 255), 348, 71);
        //addRectangle(150, 200, 0x897661, 0, false)
        
        //Header for inventory item group
        add(addCenteredText("Result Ingredients", 2, 0xFF981F), 323, 173);
        add(addHorizontalLine(131, 0xFF981F, 255), 258, 187);
        
        //Box holding inventory items
        add(addBox(326, 128, 3, 0xFF981F, 0, 255), 160, 190);
        
        System.out.println("ALCHEMY TABLE INVENTORY ITEM CONTAINER ID: "+id);
        RSInterface inventory_item_container = RSInterface.addToItemGroup(id, 7, 4, 15, -2, false, null, null, null);
        id++;
        
        add(inventory_item_container, 165, 193);
        
        /*RSInterface item = RSInterface.interfaceCache[id - 1];

        for (int idx = 0; idx < item.inv.length; idx++) {
        	item.inv[idx] = 4152;
        	item.invStackSizes[idx] = 1;
        }*/
        
        System.out.println("ALCHEMY TABLE RESULT ITEM CONTAINER ID: "+id);
        RSInterface result_item_container = RSInterface.addToItemGroup(id, 3, 1, 15, 0, false, null, null, null);
        id++;
        
        add(result_item_container, 259, 75);
        
        /*item = RSInterface.interfaceCache[id - 1];

        for (int idx = 0; idx < item.inv.length; idx++) {
        	item.inv[idx] = 4152;
        	item.invStackSizes[idx] = 1;
        }*/
        
        RSInterface model = addModelSprite(30, 40, 4612, 2500, 0, 0, DataType.OLDSCHOOL);
        model.disabledAnimationId = 7385 + Animation.OSRS_ANIM_OFFSET;
		model.enabledAnimationId = 7385 + Animation.OSRS_ANIM_OFFSET;
		add(model, 213, 70);
		add(model, 401, 70);
		
		System.out.println("IDDDDDDD recipe: "+id);
		//Warning message about missing recipe scroll
        add(addCenteredText("", 0, 0xFF0000), 278, 32);
        
        add(addDynamicButton("Craft", 1, 0xff981f, 65, 23), 13, 19);
        
        //Header for required items item group
        System.out.println("idddd for required items section  : "+id);
        add(addCenteredText("Required Items", 2, 0xFF981F), 323, 115);
        add(addHorizontalLine(103, 0xFF981F, 255), 272, 129);
        
        //Box holding required items
        add(addBox(326, 42, 3, 0xFF981F, 0, 255), 160, 132);
        add(addVerticalLine(36, 0xFF981F, 255), 204, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 205, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 250, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 251, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 297, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 298, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 344, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 345, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 392, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 393, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 438, 134);
        add(addVerticalLine(36, 0xFF981F, 255), 439, 134);
        
        System.out.println("ALCHEMY TABLE REQUIRED ITEMS CONTAINER ID: "+id);
        RSInterface required_items_container = RSInterface.addToItemGroup(id, 7, 1, 15, 0, false, null, null, null);
        id++;
        
        add(required_items_container, 165, 138);
        
        /*item = RSInterface.interfaceCache[id - 1];

        for (int idx = 0; idx < item.inv.length; idx++) {
        	item.inv[idx] = 4152;
        	item.invStackSizes[idx] = 1;
        }*/
        
        RSInterface close = RSInterface.hoverButton(id, 1068, 1069, "Close Window").setLayer(ID);
        RSInterface.interfaceCache[id].atActionType = 3;
        add(close, 472, 20);
        id++;
        System.out.println("IDDD for chance of success text: "+id);
		//Warning message about missing recipe scroll
        add(addCenteredText("Success Rate:", 0, 0x00FF00), 461, 80);
        
        System.out.println("success rate text: "+id);
		//Warning message about missing recipe scroll
        add(addCenteredText("Guaranteed", 0, 0xFF981F), 461, 92);
	}

}
