package com.simplicity.client.widget.duel_arena;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

/**
 * Duel Arena 1st Duel Options Interface Helper Widget containing equipment info to replica OSRS
 * 
 * @author Ali/Leonidas https://www.rune-server.ee/members/_ali/
 *
 */
public class DuelArenaEquipmentWidget extends CustomWidget {

	public static final int ID = 83_659;
	
	public DuelArenaEquipmentWidget() {
		super(ID);
	}

	@Override
	public String getName() {
		return "Duel Arena Equipment";
	}

	@Override
	public void init() {
		RSInterface equip = RSInterface.addInterface(id);
		equip.totalChildren(23);
		
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
		RSInterface.setBounds(13835, 466 + xoff, 211 + yoff, frame, equip);
	}

}
