package com.simplicity.client.widget.raids.nightmare;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

public class TotemsOverlay extends CustomWidget {

	public static final int WIDGET_ID = 74_020;

	public static int PROGRESS_WIDGET_START;

	public TotemsOverlay() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Totems Overlay Widget";
	}

	@Override
	public void init() {
		int x = 2;
		int y = 18;
		int width = 135;
		int height = 70;

		add(addBox(width, height, 1, 0, 0, 250), x, y);
		add(addRectangle(width - 1, height - 3, 0x3d3327, 0, true), x + 1, y + 1);
		add(addCenteredText("North West", 0, 0xff9800), x + 35, y + 5);
		add(addCenteredText("North East", 0, 0xff9800), x + 101, y + 5);
		add(addCenteredText("South West", 0, 0xff9800), x + 35, y + 37);
		add(addCenteredText("South East", 0, 0xff9800), x + 101, y + 37);

		width = 65;
		height = 10;

		PROGRESS_WIDGET_START = id;

		add(progress(width, height), x + 5, y + 20);
		add(progress(width, height), x + 5 + 66, y + 20);
		add(progress(width, height), x + 5, y + 52);
		add(progress(width, height), x + 5 + 66, y + 52);
	}

	private RSInterface progress(int width, int height) {
		RSInterface progress = addProgressBar(width, height, 25, 100);
		progress.drawProgressText = false;
		progress.fillColor = 0xFFFFFF;
		progress.progressBackAlpha = 50;
		return progress;
	}

	public static void onVarpChange(int id, int value) {
		if (id == 1314) {
			int hp_amount = value & 0xFFFF;
			int index = value >> 16 & 0xFFFF;
			RSInterface progress = RSInterface.interfaceCache[PROGRESS_WIDGET_START + index];
			progress.message = hp_amount + "/5000";
			progress.fillColor = hp_amount == 5000 ? 0xFFFFFF : 0xFFFF00;
		}
	}
}
