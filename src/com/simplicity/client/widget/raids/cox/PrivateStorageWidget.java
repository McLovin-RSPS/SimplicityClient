package com.simplicity.client.widget.raids.cox;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

import java.util.Arrays;

public class PrivateStorageWidget extends CustomWidget {

	public PrivateStorageWidget() {
		super(55800);
	}

	@Override
	public String getName() {
		return "Private Storage";
	}

	@Override
	public void init() {
		int x = 40;
		int y = 18;
		int mainBoxWidth = 430;
		int mainBoxHeight = 300;

		// os sprite start 1271
		int spriteId = 1276;

		add(addClosableWindow(mainBoxWidth, mainBoxHeight, false, getName()), x, y);
		add(addScrollbarWithItem(7, 10, 20, 15,
			new String[] { "Remove 1", "Remove 5", "Remove 10", "Remove X", "Remove All" },
			180, 380), 65, 57);
		add(addSpriteRepeatX(spriteId, mainBoxWidth), x, mainBoxHeight - 60);

		add(addText("0 / 30", 1, 0xff981f), 55, 28);

		add(addStoneButton(70, 20, 0xff981f, 0xffffff, "Shared"), 110, 25);

		add(addStoneButton(100, 22, 0xff981f, 0xffffff, "Deposit All"), 140, 252);

		add(addStoneButton(100, 22, 0xff981f, 0xffffff, "Withdraw All"), 260, 252);

		add(addText("Items stored here cannot be taken by other players.", 1, 0xff981f), 110, 284);
		add(addText("Any non-raiding items left in here can be retrieved outside.", 1, 0xff981f), 90, 298);
	}

	public RSInterface addScrollbarWithItem(int columnAmount, int h, int x, int y, String[] action, int scrollHeight,
											int scrollWidth) {
		RSInterface scroll = RSInterface.addTabInterface(id);
		scroll.componentId = id;
		id++;

		scroll.totalChildren(1);
		scroll.height = scrollHeight;
		scroll.width = scrollWidth;
		scroll.scrollMax = 35 * h;
		int scroll_frame = 0;

		RSInterface item = RSInterface.addToItemGroup(id, columnAmount, h, x, y, action != null, "", "", "");

		item.actions = new String[5];

		Arrays.fill(item.actions, null);

		if (action != null) {
			for (int i = 0; i < action.length; i++) {
				item.actions[i] = action[i];
			}
		}

		RSInterface.setBounds(id, 0, y, scroll_frame, scroll);
		scroll_frame++;
		id++;
		return scroll;
	}
}
