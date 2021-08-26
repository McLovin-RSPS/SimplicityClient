package com.simplicity.client.widget.raids.nightmare;

import com.simplicity.client.Client;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.RSInterface;
import lombok.Getter;

public class HealthHud extends CustomWidget {
	
	public static final int WIDGET_ID = 74_000;

	public static final int VARP_TYPE = 1312;
	
	public static final int VARP_HEALTH = 1313;
	
	public static int HEADER_ID;

	public static int PROGRESS_WIDGET_ID;
	
	public HealthHud() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Health HUD Widget";
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
		add(addCenteredText("?", 0, 0xff9800), width / 2 + x, y + 5);

		RSInterface progress = addProgressBar(width - 5, 20, 2221, 10400);
		progress.rsFont = Client.getClient().newSmallFont;
		progress.fillColor = 0x00FFFF;
		add(progress, x + 6, y + 20);

		PROGRESS_WIDGET_ID = progress.id;
	}

	public enum HudType {
		REGULAR(0x00ff00, 0xff0000, 255),
		ORANGE_SHIELD(0xff8a00, 0, 150),
		CYAN_SHIELD(0x00FFFF, 0, 150);

		@Getter private int mainColor;
		@Getter private int backColor;
		@Getter private int backAlpha;

		HudType(int mainColor, int backColor, int backAlpha) {
			this.mainColor = mainColor;
			this.backColor = backColor;
			this.backAlpha = backAlpha;
		}
	}

	public static void onVarpChange(int id, int value) {
		if (id == VARP_HEALTH) {
			int currentHealth = value & 0xFFFF;
			int maxHealth = value >> 16 & 0xFFFF;

			RSInterface progress = RSInterface.interfaceCache[PROGRESS_WIDGET_ID];
			progress.message = currentHealth + "/" + maxHealth;
		} else if (id == VARP_TYPE) {
			setHudType(HudType.values()[value]);
		}
	}

	public static void setHudType(HudType type) {
		RSInterface progress = RSInterface.interfaceCache[PROGRESS_WIDGET_ID];
		progress.fillColor = type.getMainColor();
		progress.progressBackColor = type.getBackColor();
		progress.progressBackAlpha = type.getBackAlpha();
	}

	public static HudType getType() {
		return RSInterface.interfaceCache[PROGRESS_WIDGET_ID].hudType;
	}

}
