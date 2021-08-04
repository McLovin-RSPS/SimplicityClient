package com.simplicity.client.widget.duel_arena;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

/**
 * Duel Arena 3rd Duel Confirmation Interface to replica OSRS
 * 
 * @author Ali/Leonidas https://www.rune-server.ee/members/_ali/
 *
 */
public class DuelArenaDuelConfirmationWidget extends CustomWidget {

	private static final int ID = DuelArenaDuelStakeWidget.DUEL_CONFIRMATION_WIDGET_ID; //83_816
	
	public DuelArenaDuelConfirmationWidget() {
		super(ID);
	}

	@Override
	public String getName() {
		return "Duel Confirmation";
	}

	@Override
	public void init() {

		add(addSprite(1966), 3, 12);
		
		add(addButton(996, 997, "Close", 0, 3), 468, 21);
		
		addWidget(DuelArenaEquipmentWidget.ID, -171, -8);
		
		add(addText("Waiting for other player...", 0, 0x8F8F8F, true), 260, 250);
		
		add(addRightAlignedText("200,000,000 gp", 1, 0xFF981F), 174, 158);
		add(addRightAlignedText("200,000,000 gp", 1, 0xFF981F), 174, 288);

		add(addDynamicButton("Decline", 1, 0xC00000, 78, 30), 180, 274);
		add(addDynamicButton("Check...", 1, 0x00C000, 78, 30), 257, 274);
		
		add(addHorizontalLine(163, 0x000000, 255), 12, 110);
		add(addHorizontalLine(165, 0x000000, 255), 12, 242);
		
		int xPosition = 17;
		int yPosition = 64;
		for (int i = 0; i < 2; i++) {
			RSInterface item_container = addItemContainer(1, 1, 1, 1, null, "");
			
			item_container.inv[0] = 43205;
			item_container.invStackSizes[0] = 3;
			
			add(item_container, xPosition, yPosition);
			
			add(addText("Platinum", 1, 0xffb83f, false), xPosition + 40, yPosition + 10);
			add(addRightAlignedText("200,000", 1, 0xFF981F), xPosition + 154, yPosition + 28);
			
			item_container = addItemContainer(1, 1, 1, 1, null, "");
			
			item_container.inv[0] = 996;
			item_container.invStackSizes[0] = 3;
			
			add(item_container, xPosition, yPosition += 43);
			add(addText("Coins", 1, 0xffb83f, false), xPosition + 40, yPosition + 10);
			add(addRightAlignedText("0", 1, 0xFF981F), xPosition + 154, yPosition + 31);
			yPosition = 195;
		}
		
		final RSInterface scroll_list_widget = RSInterface.addTabInterface(id);
		scroll_list_widget.componentId = id;
        id++;
        scroll_list_widget.height = 256;
        scroll_list_widget.width = 131;
        scroll_list_widget.scrollMax = 650;
        
        int frame = 0;
        scroll_list_widget.totalChildren(36);
        
        RSInterface.addText(id, "Options match preset", 0xFFFFFF, false, true, -1, 1);
        RSInterface.setBounds(id++, 0, 2, frame++, scroll_list_widget);
        
        RSInterface.addText(id, "Opponent Details", 0xFFFFFF, false, true, -1, 1);
        RSInterface.setBounds(id++, 0, 20, frame++, scroll_list_widget);
        
        RSInterface.addText(id, "Before the duel starts:", 0xFFFFFF, false, true, -1, 1);
        RSInterface.setBounds(id++, 0, 135, frame++, scroll_list_widget);
        
        RSInterface.addText(id, "During the duel:", 0xFFFFFF, false, true, -1, 1);
        RSInterface.setBounds(id++, 0, 215, frame++, scroll_list_widget);
        
		String[] text = {"Billyjoe", "Combat level: 126", "Attack: 99/99", "Defence: 99/99",
			"Strength: 99/99", "Hitpoints: 99/99", "Prayer: 99/99", "Ranged: 99/99", "Magic: 99/99",};

		int y_text = 37;
		for (int i = 0; i < text.length; i++) {
			RSInterface.addText(id, text[i], 0x8F8F8F, false, true, true, -1, 0);
			RSInterface.setBounds(id++, 100, y_text, frame++, scroll_list_widget);
			y_text += 10;
		}
		
		String[] text2 = { "Some worn items will be\\ntaken off.", "Boosted stats will be\\nrestored.", "Existing prayers will be\\nstopped." };
		
		y_text = 153;
		for (int i = 0; i < 3; i++) {
			RSInterface.addText(id, text2[i], 0x8F8F8F, false, true, -1, 0);
			RSInterface.setBounds(id++, 0, y_text, frame++, scroll_list_widget);
			y_text += 19;
		}
		
		y_text = 230;
		for (int i = 0; i < 20; i++) {
			RSInterface.addText(id, i +") You cannot X and Y\\nblah.", 0x8F8F8F, false, true, -1, 0);
			RSInterface.setBounds(id++, 0, y_text, frame++, scroll_list_widget);
			y_text += 19;
		}
        
        add(scroll_list_widget, 340, 47);
        
	}

}
