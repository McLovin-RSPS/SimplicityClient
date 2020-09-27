package com.simplicity.client.content.overlay;

import java.util.ArrayList;
import java.util.List;

import com.simplicity.client.Client;
import com.simplicity.client.content.overlay.impl.AmmunitionOverlay;
import com.simplicity.client.content.overlay.impl.FadeOverlay;
import com.simplicity.client.content.overlay.impl.HitpointsOverlay;
import com.simplicity.client.content.overlay.impl.KDROverlay;
import com.simplicity.client.content.overlay.impl.TobOrbsOverlay;
import com.simplicity.client.content.overlay.impl.XericPointsOverlay;
import com.simplicity.client.content.overlay.impl.TobPartyOverlay;

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
			
			int baseX = getBaseX(overlay);
			int baseY = getBaseY(overlay);
			
			ScreenOverlayGroup group = overlay.getOverlayGroup();
			
			int drawX = overlay.getX() + positionX[group.ordinal()];
			int drawY = overlay.getY() + positionY[group.ordinal()];
			
			try {
				if (overlay.draw(Client.instance, baseX + drawX, baseY + drawY)) {
					if (group.equals(ScreenOverlayGroup.TOP_LEFT_VERTICAL) || group.equals(ScreenOverlayGroup.TOP_RIGHT_VERTICAL)) {
						positionY[group.ordinal()] += overlay.getHeight() + 1;
					}
				}
				
			} catch (Exception e) {
				System.out.println("Error drawing overlay: " + overlay.getClass().getSimpleName());
				
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Gets the base x-coordinate to start drawing the overlay from.
	 * 
	 * @param overlay The overlay.
	 * @return The base x-coordinate.
	 */
	public static int getBaseX(ScreenOverlay overlay) {
		if (overlay.getOverlayGroup().equals(ScreenOverlayGroup.TOP_RIGHT_VERTICAL)) {
			if (Client.clientSize == 0) {
				return Client.instance.getGameAreaWidth() - overlay.getWidth() - 3;
			}
			
			return Client.instance.getGameAreaWidth() - 224 - overlay.getWidth() - 3;
		}

		return 2;
	}

	/**
	 * Gets the base y-coordinate to start drawing the overlay from.
	 * 
	 * @param overlay The overlay.
	 * @return The base y-coordinate.
	 */
	public static int getBaseY(ScreenOverlay overlay) {
		if (overlay instanceof TobOrbsOverlay) {
			return 4;
		}
		
		if (overlay.getOverlayGroup().equals(ScreenOverlayGroup.TOP_RIGHT_VERTICAL)) {
			return Client.instance.showXP ? 35 : 2;
		}

		return 20;
	}

	/**
	 * Initializes the overlays.
	 */
	public static void init() {
		OVERLAYS.add(new TobOrbsOverlay());
		OVERLAYS.add(new TobPartyOverlay());
		OVERLAYS.add(new KDROverlay());
		// OVERLAYS.add(new HitpointsOverlay());
		OVERLAYS.add(new AmmunitionOverlay());
		OVERLAYS.add(new XericPointsOverlay());
		
		OVERLAYS.add(new FadeOverlay());
	}

}
