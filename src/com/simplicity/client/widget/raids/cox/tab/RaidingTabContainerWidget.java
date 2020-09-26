package com.simplicity.client.widget.raids.cox.tab;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

public class RaidingTabContainerWidget extends CustomWidget {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 73_850;
	
	/**
	 * The amount of members.
	 */
	private static final int AMOUNT = 20;
	
	/**
	 * Constructs a new {@link RaidingTabContainerWidget}.
	 */
	public RaidingTabContainerWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Raiding tab container";
	}

	@Override
	public void init() {
		int y = 0;
		
		for (int i = 0; i < AMOUNT; i++) {
			RSInterface name = addCenteredText("", 0, 0xff981f);
			name.useNewFonts = true;
			add(name, 43, y);
			add(addCenteredText("", 0, 0xff981f), 43 + 61, y);
			add(addCenteredText("", 0, 0xff981f), 43 + 96, y);
			y += 12;
		}
		
		getInterface().scrollMax = 145;
		getInterface().width = 156;
		getInterface().height = 144;
	}
	
}
