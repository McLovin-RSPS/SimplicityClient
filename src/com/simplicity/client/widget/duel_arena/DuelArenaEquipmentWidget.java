package com.simplicity.client.widget.duel_arena;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.impl.SetVarp;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetStateListener;

/**
 * Duel Arena 1st Duel Options Interface Helper Widget containing equipment info to replica OSRS
 * 
 * @author Ali/Leonidas https://www.rune-server.ee/members/_ali/
 *
 */
public class DuelArenaEquipmentWidget extends CustomWidget implements WidgetStateListener {

	public static final int ID = 83_659;

	private static int EQUIPMENT_RULE;
	
	public DuelArenaEquipmentWidget() {
		super(ID);
	}

	@Override
	public String getName() {
		return "Duel Arena Equipment";
	}

	@Override
	public void init() {
	    addStateListener(this);
		RSInterface equip = RSInterface.addInterface(id++);
		equip.totalChildren(23 + 11);
		
		add(equip, 0, 0);
		
		frame = 0;

		int xoff = 3, yoff = 2;

		RSInterface.setBounds(13813, 410, 54, frame++, equip);
		RSInterface.setBounds(13814, 369, 93, frame++, equip);
		RSInterface.setBounds(13815, 410, 93, frame++, equip);
		RSInterface.setBounds(13816, 451, 93, frame++, equip);
		RSInterface.setBounds(13817, 355, 132, frame++, equip);
		RSInterface.setBounds(13818, 410, 132, frame++, equip);
		RSInterface.setBounds(13819, 466, 132, frame++, equip);
		RSInterface.setBounds(13820, 410, 172, frame++, equip);
		RSInterface.setBounds(13821, 466, 211, frame++, equip);
		RSInterface.setBounds(13822, 410, 211, frame++, equip);
		RSInterface.setBounds(13823, 355, 211, frame++, equip);

		RSInterface.setBounds(13824, 357, 93, frame++, equip);

		RSInterface.setBounds(13825, 410 + xoff, 54 + yoff, frame++, equip);
		RSInterface.setBounds(13826, 369 + xoff, 93 + yoff, frame++, equip);
		RSInterface.setBounds(13827, 410 + xoff, 93 + yoff, frame++, equip);
		RSInterface.setBounds(13828, 451 + xoff, 93 + yoff, frame++, equip);
		RSInterface.setBounds(13829, 355 + xoff, 132 + yoff, frame++, equip);
		RSInterface.setBounds(13830, 410 + xoff, 132 + yoff, frame++, equip);
		RSInterface.setBounds(13831, 466 + xoff, 132 + yoff, frame++, equip);
		RSInterface.setBounds(13832, 410 + xoff, 172 + yoff, frame++, equip);
		RSInterface.setBounds(13833, 355 + xoff, 211 + yoff, frame++, equip);
		RSInterface.setBounds(13834, 410 + xoff, 211 + yoff, frame++, equip);
		RSInterface.setBounds(13835, 466 + xoff, 211 + yoff, frame++, equip);

		int start = EQUIPMENT_RULE = id;

		for (int i = 0; i < 11; i++) {
			RSInterface.addSprite(id,1983);
			RSInterface.interfaceCache[id].hidden = true;
			id++;
		}

		RSInterface.setBounds(start++, 410 + 2, 54 + 2, frame++, equip);
		RSInterface.setBounds(start++, 369 + 2, 93 + 2, frame++, equip);
		RSInterface.setBounds(start++, 410 + 2, 93 + 2, frame++, equip);
		RSInterface.setBounds(start++, 451 + 2, 93 + 2, frame++, equip);
		RSInterface.setBounds(start++, 355 + 2, 132 + 2, frame++, equip);
		RSInterface.setBounds(start++, 410 + 2, 132 + 2, frame++, equip);
		RSInterface.setBounds(start++, 466 + 2, 132 + 2, frame++, equip);
		RSInterface.setBounds(start++, 410 + 2, 172 + 2, frame++, equip);
		RSInterface.setBounds(start++, 466 + 2, 211 + 2, frame++, equip);
		RSInterface.setBounds(start++, 410 + 2, 211 + 2, frame++, equip);
		RSInterface.setBounds(start++, 355 + 2, 211 + 2, frame++, equip);


	}

	@Override
	public void onDisplay() {
		for (int i = 0; i < 11; i++) {
			RSInterface.interfaceCache[EQUIPMENT_RULE + i].hidden = true;
		}
	}

	@Override
	public void onClose() {

	}
}
