package com.simplicity.client.content.overlay.impl;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.DrawingArea;
import com.simplicity.client.Item;
import com.simplicity.client.Sprite;
import com.simplicity.client.content.overlay.ScreenOverlay;
import com.simplicity.client.content.overlay.ScreenOverlayGroup;
import com.simplicity.util.MiscUtils;

/**
 * A class that handles the ammunition overlay.
 * 
 * @author Blake
 *
 */
public class AmmunitionOverlay extends ScreenOverlay {

	/**
	 * Constructs a new {@link AmmunitionOverlay}.
	 */
	public AmmunitionOverlay() {
		super(0, 0, 35, 35);
	}

	@Override
	public boolean draw(Client client, int x, int y) throws Exception {
		Item ammo = client.getEquipment(13);

		if (ammo == null) {
			return false;
		}

		Sprite sprite = ammo.getSprite();

		if (sprite == null) {
			return false;
		}

		DrawingArea.drawBox(x, y + 1, getWidth(), getHeight(), 2, 0x383023, 0x5a5245, 250);
		DrawingArea.fillRectangle(0, y + 2, getWidth() - 1, getHeight() - 3, 100, x + 1);

		sprite.drawSprite(x + 2, y + 2);

		client.newRegularFont.drawCenteredString(MiscUtils.formatCoins(ammo.amount), x + 17, y + 30, 0xffffff, 0);
		return true;
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_LEFT;
	}

	@Override
	public boolean enabled() {
		return Configuration.enableAmmunitionOverlay;
	}

}
