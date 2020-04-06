package com.simplicity.client.content.overlay.impl;

import com.simplicity.client.Client;
import com.simplicity.client.content.overlay.ScreenOverlay;
import com.simplicity.client.content.overlay.ScreenOverlayGroup;

/**
 * A class that handles the hitpoints overlay.
 * 
 * @author Blake
 *
 */
public class HitpointsOverlay extends ScreenOverlay {

	public HitpointsOverlay() {
		super(0, 0, 200, 38);
	}

	@Override
	public boolean draw(Client client, int x, int y) {
		return client.drawHpBar();
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_LEFT;
	}

	@Override
	public boolean enabled() {
		return Client.instance.getRegionId() != 12611;
	}

}
