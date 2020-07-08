package com.simplicity.client.widget.raids.tob;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

public class TobFadeText extends CustomWidget {

	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 77_500;
	
	/**
	 * The string id.
	 */
	public static int STRING_ID;

	/**
	 * Constructs a new {@link TobFadeText}.
	 */
	public TobFadeText() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Theatre of Blood Fade Text";
	}

	@Override
	public void init() {
		int x = 0;
		int y = 0;
		
		STRING_ID = id;
		
		RSInterface text = addCenteredText("The Theatre awaits...", 4, 0x800000);
		
		text.width = 512;
		text.height = 100;
		
		add(text, 50, y);
	}

}
