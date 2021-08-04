package com.simplicity.client.widget.duel_arena;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

/**
 * Duel Arena 1st Duel Options Interface to replica OSRS
 * 
 * @author Ali/Leonidas https://www.rune-server.ee/members/_ali/
 *
 */
public class DuelArenaDuelOptionsWidget extends CustomWidget {

	private static final int ID = 83_600;
	
	public DuelArenaDuelOptionsWidget() {
		super(ID);
	}

	@Override
	public String getName() {
		return "Duel Options";
	}

	@Override
	public void init() {

		final int[] rules = {6725, 6726, 6727, 7816, 6721, 6728, 6729, 6730, 6722, 6732, 670,};
		final int[] rule_text = {6698, 6699, 6697, 7817, 6696, 6701, 6702, 6703, 6704, 6731, 669};

		add(addSprite(1962), 3, 12);
		
		addWidget(DuelArenaEquipmentWidget.ID, 0, 0);

		int yPosition = 53;

		for (int i = 0; i < rules.length; i++) {
			//add(RSInterface.interfaceCache[rules[i]], 33, y_rules);
			add(addConfigButton(RSInterface.interfaceCache[rule_text[i]].message, 1039, 1040, 5, i, 286), 25, yPosition);
			add(RSInterface.interfaceCache[rule_text[i]], 43, yPosition);
			yPosition += 20;
		}

		add(addConfigButton("No Weapon Switch", 1039, 1040, 5, 11, 1650), 25, yPosition);
		add(addText("No Weapon Switch", 1, 0xEE9021, false), 43, yPosition);
		
		yPosition += 20;
		
		add(addConfigButton("Show Inventories", 1039, 1040, 5, 11, 1650), 25, yPosition);
		add(addText("Show Inventories", 1, 0xEE9021, false), 43, yPosition);
		
		add(addDynamicButton("Accept", 1, 0x00C000, 80, 33), 170, 272);
		add(addDynamicButton("Decline", 1, 0xC00000, 80, 33), 260, 272);
		
		add(addButton(996, 997, "Close", 0, 3), 483, 21);
		
		add(addText("Duel Arena", 1, 0xEE9021, false), 256, 58);
		add(addText("Combat level: 126", 1, 0xff0000, true), 250, 79);
		
		final int[][] skillLevelXYStringCoords = { 
				{192, 104}, {192, 117}, 
				{235, 104}, {235, 117}, 
				{286, 104}, {286, 117}, 
				{334, 104}, {334, 117}, 
				{215, 133}, {215, 146}, 
				{267, 133}, {267, 146}, 
				{315, 133}, {315, 146} 
		};
		
		for (int i = 0; i < skillLevelXYStringCoords.length; i++) {
			add(addText("99", 0, 0xffff00, true), skillLevelXYStringCoords[i][0], skillLevelXYStringCoords[i][1]);
		}
		
		yPosition = 258;
		for (int i = 0; i < 3; i++) {
			add(addButton(i == 0 ? 1963 : 1964, i == 0 ? 1963 : 1964, "Save", 5, 1), 180 + 185, yPosition);
			yPosition += i == 1 ? 18 : 17;
		}
		
		add(addText("Other player has accepted...", 1, 0xFF0000, true), 255, 230);
		
		final String[] actionButtons = { "Save as preset", "Load preset", "Load last duel" };
		
		yPosition = 258;
		for (int i = 0; i < actionButtons.length; i++) {
			add(addClickText(actionButtons[i], 1, 0xFF8C00, false), 386, yPosition);
			yPosition += 18;
		}
		System.out.println("lol id: "+id);
	}

}
