package com.simplicity.client.widget.raids.nightmare;

import com.simplicity.client.Client;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.RSInterface;

public class BossHealthOverlay extends CustomWidget {
	
	public static final int WIDGET_ID = 74_000;
	
	public static final int VARP_NIGHTMARE = 1313;
	
	public static final int VARP_ZALCANO = 1315;
	
	public static int HEADER_ID;

	public static int PROGRESS_WIDGET_ID;
	
	public BossHealthOverlay() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Boss Health Overlay Widget";
	}

	@Override
	public void init() {
		int x = 128 + 20;
		int y = 25;
		int width = 250 - 40;
		int height = 46;
		add(addBox(width, height, 1, 0, 0, 250), x, y);
		add(addRectangle(width - 1, height - 3, 0x3d3327, 0, true), x + 1, y + 1);
		HEADER_ID = id;
		add(addCenteredText("The Nightmare", 0, 0xff9800), width / 2 + x, y + 5);

		RSInterface progress = addProgressBar(width - 5, 20, 2221, 10400);
		progress.rsFont = Client.getClient().newSmallFont;
		progress.fillColor = 0x00FFFF;
		add(progress, x + 6, y + 20);

		PROGRESS_WIDGET_ID = progress.id;
	}
	
	public static int getStage(int varp) {
		int value = Client.getClient().variousSettings[varp];
		
		return value >> 16 & 0xFFFF;
	}

	public static void onVarpChange(int id, int value) {
		if (id == VARP_NIGHTMARE) {
			setProgress(value, 24000);
			setHeader("The Nightmare");
		} else if (id == VARP_ZALCANO) {
			setProgress(value, 3000);
			RSInterface progress = RSInterface.interfaceCache[PROGRESS_WIDGET_ID];
			progress.fillColor = 0xff8a00;
			setHeader("Zalcano");
		}
	}
	
	private static void setProgress(int value, int max) {
		int hp_amount = value & 0xFFFF;
		int stage = value >> 16 & 0xFFFF;
		RSInterface progress = RSInterface.interfaceCache[PROGRESS_WIDGET_ID];
		progress.fillColor = stage == 0 ? 0x00FFFF : 0X00FF00;
		progress.message = hp_amount + "/" + max;
	}
	
	private static void setHeader(String name) {
		RSInterface.interfaceCache[HEADER_ID].message = name;
	}
}
