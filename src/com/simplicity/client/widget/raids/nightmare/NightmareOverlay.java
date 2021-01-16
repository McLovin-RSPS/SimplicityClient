package com.simplicity.client.widget.raids.nightmare;

import com.simplicity.client.Client;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.RSInterface;

public class NightmareOverlay extends CustomWidget {
	
	public static final int WIDGET_ID = 74_000;

	public static int PROGRESS_WIDGET_ID;

	public NightmareOverlay() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Nightmare Overlay Widget";
	}

	@Override
	public void init() {
		int x = 128;
		int y = 25;
		int width = 250;
		int height = 46;
		add(addBox(width, height, 1, 0, 0, 250), x, y);
		add(addRectangle(width - 1, height - 3, 0x3d3327, 0, true), x + 1, y + 1);
		add(addCenteredText("The Nightmare", 0, 0xff9800), width / 2 + x, y + 5);

		RSInterface progress = addProgressBar(width - 5, 20, 2221, 10400);
		progress.rsFont = Client.getClient().newSmallFont;
		progress.fillColor = 0x00FFFF;
		add(progress, x + 6, y + 20);

		PROGRESS_WIDGET_ID = progress.id;
	}

	public static void onVarpChange(int id, int value) {
		if (id == 1313) {
			int hp_amount = value & 0xFFFF;
			int stage = value >> 16 & 0xFFFF;
			RSInterface progress = RSInterface.interfaceCache[PROGRESS_WIDGET_ID];
			progress.fillColor = stage == 0 ? 0x00FFFF : 0X00FF00;
			progress.message = hp_amount + "/24000";
		}
	}
}
