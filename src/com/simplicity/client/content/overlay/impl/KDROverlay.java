package com.simplicity.client.content.overlay.impl;

import java.util.Locale;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.content.overlay.ScreenOverlay;
import com.simplicity.client.content.overlay.ScreenOverlayGroup;

/**
 * A class that handles the KDR overlay.
 * 
 * @author Blake
 *
 */
public class KDROverlay extends ScreenOverlay {
	
	/**
	 * The kills string id.
	 */
	private static final int KILLS_STRING = 73033;
	
	/**
	 * The deaths string id.
	 */
	private static final int DEATHS_STRING = 73034;

	public KDROverlay() {
		super(1, 12, 50, 12);
	}

	@Override
	public boolean draw(Client client, int x, int y) throws Exception {
		String k = RSInterface.interfaceCache[KILLS_STRING].message;
		String d = RSInterface.interfaceCache[DEATHS_STRING].message;
		
		int kills = Integer.parseInt(k.substring(k.lastIndexOf("@") + 1));
		
		int deaths = Integer.parseInt(d.substring(d.lastIndexOf("@") + 1));
		
		String kdr = deaths == 0 ? "0.00" : String.format(Locale.US, "%.2f", (double) kills / (double) deaths);
		
		client.newSmallFont.drawBasicString("Kills: " + kills, x, y, 0xffff00, 0);
		client.newSmallFont.drawBasicString("Deaths: " + kills, x, y + 10, 0xffff00, 0);
		client.newSmallFont.drawBasicString("K/D Ratio: " + kdr, x, y + 20, 0xffff00, 0);
		return true;
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_LEFT;
	}

	@Override
	public boolean enabled() {
		return Configuration.enableKDROverlay;
	}

}
