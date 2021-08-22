package com.simplicity.client.widget.raids.cox;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

import java.util.Arrays;

public class SharedStorageWidget extends CustomWidget {

	public SharedStorageWidget() {
		super(59000);
	}

	@Override
	public String getName() {
		return "Shared Storage";
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
		add(addScrollbarWithItem(7, 60, 20, 15,
			new String[] { "Remove 1", "Remove 5", "Remove 10", "Remove X", "Remove All" },
			220, 380), 65, 90);

		add(addText("0 / 250", 1, 0xff981f), 55, 28);

		add(addStoneButton(70, 20, 0xff981f, 0xffffff, "Private"), 110, 25);

		add(addText("Items donated here may be retrieved by other party-members,", 1, 0xff981f), 90, 54);
		add(addText("or may be lost if the part dissolves!", 1, 0xff981f), 160, 70);
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
