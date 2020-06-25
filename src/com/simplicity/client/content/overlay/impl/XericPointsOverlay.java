package com.simplicity.client.content.overlay.impl;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.content.overlay.ScreenOverlay;
import com.simplicity.client.content.overlay.ScreenOverlayGroup;
import com.simplicity.client.widget.raids.XericPointsWidget;

/**
 * A class that handles the points overlay for the Chambers of Xeric.
 * 
 * @author Blake
 *
 */
public class XericPointsOverlay extends ScreenOverlay {
	
	/**
	 * Constructs a new {@link XericPointsOverlay}.
	 */
	public XericPointsOverlay() {
		super(0, 0, 102, 50);
	}

	@Override
	public boolean draw(Client client, int x, int y) throws Exception {
		XericPointsWidget.setBounds();
		
		if (getWidth() != RSInterface.interfaceCache[XericPointsWidget.BOX_ID].width) {
			setWidth(RSInterface.interfaceCache[XericPointsWidget.BOX_ID].width);
		}
		
		client.drawInterface(RSInterface.interfaceCache[XericPointsWidget.WIDGET_ID], x, y);
		return true;
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_RIGHT_VERTICAL;
	}

	@Override
	public boolean enabled() {
		return Client.instance.parallelWidgetList.contains(RSInterface.interfaceCache[XericPointsWidget.WIDGET_ID]);
	}

}
