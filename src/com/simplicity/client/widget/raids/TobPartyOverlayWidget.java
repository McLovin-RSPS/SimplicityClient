package com.simplicity.client.widget.raids;

import com.simplicity.client.widget.CustomWidget;

/**
 * A class that handles the party overlay widget for the Theatre of Blood.
 * 
 * @author Blake
 *
 */
public class TobPartyOverlayWidget extends CustomWidget {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 76_000;
	
	/**
	 * Constructs a new {@link TobPartyOverlayWidget}.
	 */
	public TobPartyOverlayWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Tob Party Widget";
	}

	@Override
	public void init() {
		int x = 5;
		int y = 0;
		
		add(addBox(120, 100, 3, 0x5a5245, 0x383023, 255, 0x494034, 175), x, y);
		
		x += 58;
		y += 5;
		
		add(addCenteredText("No party", 2, 0xff9040), x, y);
		
		x += 2;
		y += 15;
		
		for (int i = 0; i < 5; i++) {
			add(addCenteredText("-", 1, 0xff9040), x, y);
			y += 15;
		}
	}

}
