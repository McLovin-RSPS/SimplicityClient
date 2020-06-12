package com.simplicity.client.widget;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;

/**
 * A class that represents the raid points widget.
 * 
 * @author Blake
 *
 */
public class RaidPointsWidget extends CustomWidget {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 55_500;
	
	/**
	 * The box id.
	 */
	public static final int BOX_ID = WIDGET_ID + 1;
	
	/**
	 * The total points string id.
	 */
	public static final int TOTAL_POINTS_ID = WIDGET_ID + 2;
	
	/**
	 * The player points string id.
	 */
	public static final int PLAYER_POINTS_ID = WIDGET_ID + 3;

	/**
	 * Constructs a new {@link RaidPointsWidget}.
	 */
	public RaidPointsWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Raid Points Widget";
	}

	@Override
	public void init() {
		int x = 0;
		int y = 0;
		
		add(addBox(102, 40, 2, 0x5a5245, 0x383023, 255, 0x494034, 255), 0, 0);
		
		x += 5;
		y += 5;
		
		add(addText("Total: @whi@1,234,567", 1, 0xff9040), x, y);
		
		y += 12;
		
		add(addText("Blake: @whi@234,567", 1, 0xff9040), x, y);
	}
	
	/**
	 * Sets the bounds of this widget when required.
	 */
	public static void setBounds() {
		int totalWidth = Client.instance.newRegularFont.getTextWidth(RSInterface.interfaceCache[TOTAL_POINTS_ID].message);
		
		int playerWidth = Client.instance.newRegularFont.getTextWidth(RSInterface.interfaceCache[PLAYER_POINTS_ID].message);
		
		int max = Math.max(totalWidth, playerWidth) - 34;
		
		if (max <= 102) {
			return;
		}
		
		RSInterface.interfaceCache[BOX_ID].width = max;
	}

}
