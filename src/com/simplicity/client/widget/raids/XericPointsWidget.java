package com.simplicity.client.widget.raids;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.util.MiscUtils;

/**
 * A class that represents the points widget for the Chambers of Xeric.
 * 
 * @author Blake
 *
 */
public class XericPointsWidget extends CustomWidget {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 55_500;
	
	/**
	 * The box id.
	 */
	public static final int BOX_ID = WIDGET_ID + 1;
	
	/**
	 * The total string id.
	 */
	public static final int TOTAL_ID = WIDGET_ID + 2;
	
	/**
	 * The total points string id.
	 */
	public static final int TOTAL_POINTS_ID = WIDGET_ID + 3;
	
	/**
	 * The player name string id.
	 */
	public static final int PLAYER_NAME_ID = WIDGET_ID + 4;
	
	/**
	 * The player points string id.
	 */
	public static final int PLAYER_POINTS_ID = WIDGET_ID + 5;
	
	/**
	 * The time string id.
	 */
	public static final int TIME_ID = WIDGET_ID + 6;
	
	/**
	 * The time amount string id.
	 */
	public static final int TIME_AMOUNT_ID = WIDGET_ID + 7;

	/**
	 * Constructs a new {@link XericPointsWidget}.
	 */
	public XericPointsWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Xeric Points Widget";
	}

	@Override
	public void init() {
		int x = 0;
		int y = 0;
		
		add(addBox(102, 52, 2, 0x5a5245, 0x383023, 255, 0x494034, 255), 0, 0);
		
		x += 5;
		y += 5;
		
		add(addText("Total:", 1, 0xff9040), x, y);
		add(addRightAlignedText("0", 1, 0xffffff), x + 80, y);
		
		y += 12;
		
		add(addText("Blake:", 1, 0xff9040), x, y);
		add(addRightAlignedText("0", 1, 0xffffff), x + 80, y);
		
		y += 12;
		
		add(addText("Time:", 1, 0xff9040), x, y);
		add(addRightAlignedText("0:00", 1, 0xffffff), x + 80, y);
	}
	
	/**
	 * Sets the bounds of this widget.
	 */
	public static void setBounds() {
		int maxWidth = (int) MiscUtils.max(
			RSInterface.getMessageWidth(TOTAL_ID) + RSInterface.getMessageWidth(TOTAL_POINTS_ID),
			RSInterface.getMessageWidth(PLAYER_NAME_ID) + RSInterface.getMessageWidth(PLAYER_POINTS_ID),
			RSInterface.getMessageWidth(TIME_ID) + RSInterface.getMessageWidth(TIME_AMOUNT_ID)
		);
		
		RSInterface.interfaceCache[BOX_ID].width = maxWidth + 11;
		RSInterface.interfaceCache[TOTAL_POINTS_ID].xOffset = maxWidth - 77;
		RSInterface.interfaceCache[PLAYER_POINTS_ID].xOffset = maxWidth - 77;
		RSInterface.interfaceCache[TIME_AMOUNT_ID].xOffset = maxWidth - 77;
	}
	
}
