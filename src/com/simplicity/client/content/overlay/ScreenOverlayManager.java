package com.simplicity.client.content.overlay;

import java.util.ArrayList;
import java.util.List;

import com.simplicity.client.Client;
import com.simplicity.client.content.overlay.impl.AmmunitionOverlay;
import com.simplicity.client.content.overlay.impl.HitpointsOverlay;

/**
 * A class that handles the processing of {@link ScreenOverlay}'s.
 * 
 * @author Blake
 *
 */
public class ScreenOverlayManager {

	/**
	 * A list of all {@link ScreenOverlay}'s.
	 */
	private static final List<ScreenOverlay> OVERLAYS = new ArrayList<>();

	/**
	 * Processes the overlays.
	 */
	public static void process() {
		int[] positionX = new int[ScreenOverlayGroup.values().length];
		int[] positionY = new int[ScreenOverlayGroup.values().length];

		for (ScreenOverlay overlay : OVERLAYS) {
			if (!overlay.enabled()) {
				continue;
			}

			ScreenOverlayGroup group = overlay.getOverlayGroup();

			int x = overlay.getX() + positionX[group.ordinal()];
			int y = overlay.getY() + positionY[group.ordinal()];
			
			try {
				if (overlay.draw(Client.instance, x, y)) {
					if (group == ScreenOverlayGroup.TOP_LEFT) {
						positionY[group.ordinal()] += overlay.getHeight() + 2;
					}
				}
			} catch (Exception e) {
				System.out.println("Error drawing overlay: " + overlay.getClass().getSimpleName());
				
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initializes the overlays.
	 */
	public static void init() {
		OVERLAYS.add(new HitpointsOverlay());
		OVERLAYS.add(new AmmunitionOverlay());
	}

}
