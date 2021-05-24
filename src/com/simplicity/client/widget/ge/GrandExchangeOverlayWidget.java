package com.simplicity.client.widget.ge;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

public class GrandExchangeOverlayWidget extends CustomWidget {

	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 93_900;

	/**
	 * Constructs a new {@link GrandExchangeOverlayWidget}.
	 */
	public GrandExchangeOverlayWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Grand Exchange Overlay";
	}

	@Override
	public void init() {
		RSInterface rect = fillRectangle(184 + 6, 255 + 8, 0xffff00, 0);
		rect.fading = true;
		rect.minFade = 15;
		rect.maxFade = 40;
		rect.fadeSpeed = 2;
		rect.fadeStep = 1;
		add(rect, 0, 0);
		add(addItemContainer(4, 7, 10, 4, new String[] { "Offer" }, "Grand Exchange Overlay"), 16, 8);
	}

}
