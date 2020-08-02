package com.simplicity.client.widget.raids.tob;

import com.simplicity.client.widget.CustomWidget;

/**
 * A class that handles the Performance Details widget.
 * 
 * @author Blake
 *
 */
public class PartyHiscores extends CustomWidget {

	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 77_300;

	/**
	 * Constructs a new {@link PartyHiscores}.
	 */
	public PartyHiscores() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Theatre of Blood Top 10 runs";
	}

	@Override
	public void init() {
		int x = 28;
		int y = 16;
		int width = 175;
		add(addClosableWindow(457, 301, false, getName()), x, y);
		addWidget(PartyHiscoresContainer.WIDGET_ID, 56, 58);
	}
	
}
