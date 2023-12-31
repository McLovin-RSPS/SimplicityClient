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
		super(0, 0, 124, 38);
	}

	@Override
	public boolean draw(Client client, int x, int y) throws Exception {
		return client.drawHpBar(x, y);
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_LEFT_VERTICAL;
	}

	@Override
	public boolean enabled() {
		return Client.instance.getRegionId() != 12611;
	}

}
