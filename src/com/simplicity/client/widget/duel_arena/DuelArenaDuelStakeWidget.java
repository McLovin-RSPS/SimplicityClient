package com.simplicity.client.widget.duel_arena;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetStateListener;

/**
 * Duel Arena 2nd Duel Stake Interface Widget
 * 
 * @author Ali/Leonidas https://www.rune-server.ee/members/_ali/
 *
 */
public class DuelArenaDuelStakeWidget extends CustomWidget implements WidgetStateListener {

	private static final int ID = 83_700;
	
	private static int INFO_TAB_WIDGET_ID;
	
	private static int INVENTORY_TAB_WIDGET_ID;
	
	private static int EQUIPMENT_TAB_WIDGET_ID;
	
	public static int DUEL_CONFIRMATION_WIDGET_ID;

	public static int PLATINUM_MODIFIED;

	public static int COINS_MODIFIED;
	
	public DuelArenaDuelStakeWidget() {
		super(ID);
	}

	@Override
	public String getName() {
		return "Duel Stake";
	}

	@Override
	public void init() {
		
		RSInterface window = addWindow(509, 311, true);
		
		add(window, 3, 12);
		
		add(addHorizontalSeparator(494,true), 10, 45);
		add(addVerticalSeparator(265, true), 175, 51);
		add(addVerticalSeparator(217, true), 340, 51);
		add(addHorizontalSeparator(323,true), 181, 263);
		
		add(addCenteredText(getName(), 2, 0xFF981F), 260, 22);
		add(addButton(996, 997, "Close", 0, 3), 483, 21);
		
		int xPosition = 10;
		final int[] icons = { 1965, 976, 977};
		for (int i = 0; i < icons.length; i++) {
			add(addDynamicButton("", 1, 0x00C000, 42, 37), xPosition, 52);
			add(addSprite(icons[i]), xPosition + 7, i == 2 ? 53 : 57);
			xPosition += 43;
		}
		
		add(addHorizontalLine(164, 0x000000, 255), 10, 89);
		add(addHorizontalLine(164, 0x000000, 255), 10, 90);
		
		int yPosition = 70;
		for (int i = 0; i < 3; i++) {
			add(addHorizontalLine(160, 0x000000, 255), 180, yPosition);
			add(addHorizontalLine(160, 0x000000, 255), 180, yPosition + 1);
			add(addHorizontalLine(160, 0x000000, 255), 345, yPosition);
			add(addHorizontalLine(160, 0x000000, 255), 345, yPosition + 1);
			yPosition += i == 1 ? 80 : 92;
		}
		
		add(addText("Your Stake:", 1, 0xFF981F, false), 185, 53);
		add(addText("Big Dick's Stake:", 1, 0xFF981F, false), 350, 53);
		
		add(addText("Waiting for other player ...", 1, 0xFF0000, true), 310, 285); //0xFF0000
		
		add(addDynamicButton("Accept", 1, 0x00C000, 85, 22), 415, 271);
		add(addDynamicButton("Decline", 1, 0xC00000, 85, 22), 415, 293);
		
		add(addRightAlignedText("200,000,000 gp", 1, 0xFF981F), 336, 246);
		add(addRightAlignedText("200,000,000 gp", 1, 0xFF981F), 501, 246);
		
		add(addButton(1947, 1947, "Save", 5, 1), 320, 53);
		
			
		add(addSprite(1809), 219 - 4, 108 - 4);
		add(addSpriteRepeatX(1810, 85), 223 - 4, 108 - 4);
		
		add(addSprite(1811), 308 - 4, 108 - 4);

		add(addText("200,000", 0, 0xffb83f, true).setSize(221, 20), 156 - 4, 108);
		
		add(addHoverOpacityButton(1802, 100, "-1"), 193 - 4, 109 - 4);
		add(addHoverOpacityButton(1803, 100, "+1"), 316 - 4, 109 - 4);

		
		add(addSprite(1809), 219 - 4, 190 - 4);
		add(addSpriteRepeatX(1810, 85), 223 - 4, 190 - 4);
		
		add(addSprite(1811), 308 - 4, 190 - 4);

		add(addText("200,000", 0, 0xffb83f, true).setSize(221, 20), 156 - 4, 190);
		
		add(addHoverOpacityButton(1802, 100, "-1"), 193 - 4, 191 - 4);
		add(addHoverOpacityButton(1803, 100, "+1"), 316 - 4, 191 - 4);
		
		
		String[] buttons = new String[] { "+10", "+100", "+1k", "..." };
		
		int[] textX = new int[] { 4, 0, 4, 8 };

		for (int i = 0; i < buttons.length; i++) {
			String action = i == buttons.length - 1 ? "Enter quantity" : buttons[i];
			add(hoverButton(1796, 1797, action), 187 + (i * 40) - 4, 132 - 4);
			add(addText(buttons[i], 0, 0xff981f), 187 + (i * 40) + textX[i], 143 - 4);
			
			add(hoverButton(1796, 1797, action), 187 + (i * 40) - 4, 212 - 4);
			add(addText(buttons[i], 0, 0xff981f), 187 + (i * 40) + textX[i], 223 - 4);
		}
		
		add(addText("200,000", 1, 0xffb83f, true).setSize(221, 20), 320 - 4, 108);
		add(addText("200,000", 1, 0xffb83f, true).setSize(221, 20), 320 - 4, 190);
		
		add(addText("Platinum", 1, 0xffb83f, false), 250, 80);
		add(addText("Platinum", 1, 0xffb83f, false), 421, 80);
		add(addText("Gold", 1, 0xffb83f, false), 250, 167);
		add(addText("Gold", 1, 0xffb83f, false), 421, 167);
		
		xPosition = 211;
		yPosition = 71;
		int itemId = 43205;
		for (int i = 0; i < 2; i++) {
			RSInterface item_container = addItemContainer(1, 1, 1, 1, null, "");
			
			item_container.inv[0] = itemId;
			item_container.invStackSizes[0] = 3;
			
			add(item_container, xPosition, yPosition);
			add(item_container, xPosition + 171, yPosition);
			xPosition = 211;
			yPosition = 156;
			itemId = 996;
		}
		
		INFO_TAB_WIDGET_ID = id;		
		add(duelStakeInformationTabWidget(), 0, 0);
		
		INVENTORY_TAB_WIDGET_ID = id;
		add(duelStakeInventoryTabWidget(), 0, 0);
				
		EQUIPMENT_TAB_WIDGET_ID = id;
		add(duelStakeEquipmentTabWidget(), -336, 50);
		
		RSInterface.interfaceCache[INFO_TAB_WIDGET_ID].hidden = false;
		RSInterface.interfaceCache[INVENTORY_TAB_WIDGET_ID].hidden = false;
		RSInterface.interfaceCache[EQUIPMENT_TAB_WIDGET_ID].hidden = false;
		
		DUEL_CONFIRMATION_WIDGET_ID = id;
		System.out.println("3rd duel arena interface: "+id);
		id = 83_900;

		PLATINUM_MODIFIED = id;
		add(addRectangle(25, 93, 0x800000, 0, true).flicker(true).hide(true), 346, 70);

		COINS_MODIFIED = id;
		add(addRectangle(25, 80, 0x800000, 0, true).flicker(true).hide(true), 346, 162);
	}
	
	private RSInterface duelStakeInformationTabWidget() {
		
		RSInterface info_tab_widget = RSInterface.addInterface(INFO_TAB_WIDGET_ID);
		info_tab_widget.componentId = INFO_TAB_WIDGET_ID;
        id++;
        int frame = 0;
        
        info_tab_widget.totalChildren(9);
        
		RSInterface.addText(id, "To stake @whi@platinum @or1@or", 0xffb83f, false, false, -1, 1);
    	RSInterface.setBounds(id++, 22, 97, frame++, info_tab_widget);
    	
    	RSInterface.addText(id, "@yel@coins @or1@use the buttons on", 0xffb83f, false, false, -1, 1);
    	RSInterface.setBounds(id++, 22, 115, frame++, info_tab_widget);
    	
    	RSInterface.addText(id, "the panel in the middle.", 0xffb83f, false, false, -1, 1);
    	RSInterface.setBounds(id++, 22, 133, frame++, info_tab_widget);
    	
    	RSInterface.addText(id, "Tax is deducted from the", 0xffb83f, false, false, -1, 1);
    	RSInterface.setBounds(id++, 22, 169, frame++, info_tab_widget);
    	
    	RSInterface.addText(id, "combined stake.", 0xffb83f, false, false, -1, 1);
    	RSInterface.setBounds(id++, 22, 187, frame++, info_tab_widget);
    	
    	RSInterface.addText(id, "@gre@Total Stake           @cya@Rate", 0xffb83f, false, false, -1, 2);
    	RSInterface.setBounds(id++, 22, 223, frame++, info_tab_widget);
    	
    	RSInterface.addText(id, "@gr1@0 - <10m gp               @cya@0.25%", 0xffb83f, false, false, true, -1, 1);
    	RSInterface.setBounds(id++, 169, 241, frame++, info_tab_widget);
    	
    	RSInterface.addText(id, "@gr2@10m - <100m gp       @cya@0.50%", 0xffb83f, false, false, true, -1, 1);
    	RSInterface.setBounds(id++, 169, 259, frame++, info_tab_widget);
    	
    	RSInterface.addText(id, "@gr3@>=100m gp                @cya@1.00%", 0xffb83f, false, false, true, -1, 1);
    	RSInterface.setBounds(id++, 169, 277, frame++, info_tab_widget);
    	
		return info_tab_widget;
	}
	
	private RSInterface duelStakeInventoryTabWidget() {
		
		RSInterface item_tab_widget = RSInterface.addTabInterface(INVENTORY_TAB_WIDGET_ID);
		item_tab_widget.componentId = INVENTORY_TAB_WIDGET_ID;
        id++;
        int frame = 0;
        
        item_tab_widget.totalChildren(1);
                
        RSInterface.addToItemGroup(id, 4, 7, 7, 0, false, null, null, null);
        RSInterface.setBounds(id++, 13, 93, frame++, item_tab_widget);
                
        RSInterface item = RSInterface.interfaceCache[id - 1];

        for (int idx = 0; idx < item.inv.length; idx++) {
        	item.inv[idx] = 4152;
        	item.invStackSizes[idx] = 1;
        }
    	
		return item_tab_widget;
	}
	
	private RSInterface duelStakeEquipmentTabWidget() {

		RSInterface equipment_tab_widget = RSInterface.addInterface(EQUIPMENT_TAB_WIDGET_ID);
		equipment_tab_widget.componentId = EQUIPMENT_TAB_WIDGET_ID;
        id++;
        int frame = 0;
        
		equipment_tab_widget.totalChildren(28);
				

		int xoff = 3, yoff = 2;

		RSInterface.addVerticalSeparator(id, 180, true);
		equipment_tab_widget.child(frame++, id, 425, 63);
		id += 2;
		
		RSInterface.addVerticalSeparator(id, 100, true);
		equipment_tab_widget.child(frame++, id, 371, 140);
		id += 2;
		
		RSInterface.addVerticalSeparator(id, 100, true);
		equipment_tab_widget.child(frame++, id, 481, 140);
		id += 2;
		
		RSInterface.addHorizontalSeparator(id, 100, true);
		equipment_tab_widget.child(frame++, id, 380, 107);
		id += 2;
		
		RSInterface.addHorizontalSeparator(id, 100, true);
		equipment_tab_widget.child(frame++, id, 380, 146);
		id += 2;
		
		RSInterface.setBounds(13813, 410, 54, frame++, equipment_tab_widget);
		RSInterface.setBounds(13814, 369, 93, frame++, equipment_tab_widget);
		RSInterface.setBounds(13815, 410, 93, frame++, equipment_tab_widget);
		RSInterface.setBounds(13816, 451, 93, frame++, equipment_tab_widget);
		RSInterface.setBounds(13817, 355, 132, frame++, equipment_tab_widget);
		RSInterface.setBounds(13818, 410, 132, frame++, equipment_tab_widget);
		RSInterface.setBounds(13819, 466, 132, frame++, equipment_tab_widget);
		RSInterface.setBounds(13820, 410, 172, frame++, equipment_tab_widget);
		RSInterface.setBounds(13821, 466, 211, frame++, equipment_tab_widget);
		RSInterface.setBounds(13822, 410, 211, frame++, equipment_tab_widget);
		RSInterface.setBounds(13823, 355, 211, frame++, equipment_tab_widget);

		RSInterface.setBounds(13824, 357, 93, frame++, equipment_tab_widget);

		RSInterface.setBounds(13825, 410 + xoff, 54 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13826, 369 + xoff, 93 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13827, 410 + xoff, 93 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13828, 451 + xoff, 93 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13829, 355 + xoff, 132 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13830, 410 + xoff, 132 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13831, 466 + xoff, 132 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13832, 410 + xoff, 172 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13833, 355 + xoff, 211 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13834, 410 + xoff, 211 + yoff, frame++, equipment_tab_widget);
		RSInterface.setBounds(13835, 466 + xoff, 211 + yoff, frame++, equipment_tab_widget);
		
    	
		return equipment_tab_widget;
	}

	@Override
	public void onDisplay() {
		RSInterface.interfaceCache[PLATINUM_MODIFIED].hide(true);
		RSInterface.interfaceCache[COINS_MODIFIED].hide(true);
	}

	@Override
	public void onClose() {

	}
}
