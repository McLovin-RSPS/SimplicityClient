package com.simplicity.client.content.overlay.impl;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.content.overlay.ScreenOverlay;
import com.simplicity.client.content.overlay.ScreenOverlayGroup;
import com.simplicity.client.widget.TobPlayerOrbsWidget;

public class TobOrbsOverlay extends ScreenOverlay {

	public TobOrbsOverlay() {
		super(0, 0, 0, 40);
	}

	@Override
	public boolean draw(Client client, int x, int y) throws Exception {
		int activeOrbs = TobPlayerOrbsWidget.getActiveOrbs();
		
		if (activeOrbs < 1) {
			return false;
		}
		
		int height = activeOrbs * 35;
		
		setHeight(height);
		client.drawInterface(RSInterface.interfaceCache[TobPlayerOrbsWidget.INTERFACE_ID], x, y);
		return true;
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_LEFT_VERTICAL;
	}

	@Override
	public boolean enabled() {
		return Client.instance.parallelWidgetList.contains(RSInterface.interfaceCache[TobPlayerOrbsWidget.INTERFACE_ID]);
	}

}
