package com.simplicity.client.content.overlay.impl;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.content.overlay.ScreenOverlay;
import com.simplicity.client.content.overlay.ScreenOverlayGroup;
import com.simplicity.client.widget.raids.tob.TobPartyOverlayWidget;

/**
 * A class that represents the party overlay for the Theatre of Blood.
 * 
 * @author Blake
 *
 */
public class TobPartyOverlay extends ScreenOverlay {

	/**
	 * Constructs a new {@link TobPartyOverlay}.
	 */
	public TobPartyOverlay() {
		super(0, 0, 120 + 5, 100);
	}

	@Override
	public boolean draw(Client client, int x, int y) throws Exception {
		client.drawInterface(RSInterface.interfaceCache[TobPartyOverlayWidget.WIDGET_ID], x, y);
		return true;
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_LEFT_VERTICAL;
	}

	@Override
	public boolean enabled() {
		return Client.instance.parallelWidgetList.contains(RSInterface.interfaceCache[TobPartyOverlayWidget.WIDGET_ID]);
	}

}
