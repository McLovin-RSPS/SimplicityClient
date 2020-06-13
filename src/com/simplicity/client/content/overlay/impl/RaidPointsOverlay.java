package com.simplicity.client.content.overlay.impl;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.content.overlay.ScreenOverlay;
import com.simplicity.client.content.overlay.ScreenOverlayGroup;
import com.simplicity.client.widget.RaidPointsWidget;

/**
 * A class that handles the points overlay for raids.
 * 
 * @author Blake
 *
 */
public class RaidPointsOverlay extends ScreenOverlay {
	
	/**
	 * Constructs a new {@link RaidPointsOverlay}.
	 */
	public RaidPointsOverlay() {
		super(0, 0, 102, 38);
	}

	@Override
	public boolean draw(Client client, int x, int y) throws Exception {
		RaidPointsWidget.setBounds();
		
		if (getWidth() != RSInterface.interfaceCache[RaidPointsWidget.BOX_ID].width) {
			setWidth(RSInterface.interfaceCache[RaidPointsWidget.BOX_ID].width);
		}
		
		client.drawInterface(RSInterface.interfaceCache[RaidPointsWidget.WIDGET_ID], x, y);
		return true;
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_LEFT;
	}

	@Override
	public boolean enabled() {
		return Client.instance.parallelWidgetList.contains(RSInterface.interfaceCache[RaidPointsWidget.WIDGET_ID]);
	}

}
