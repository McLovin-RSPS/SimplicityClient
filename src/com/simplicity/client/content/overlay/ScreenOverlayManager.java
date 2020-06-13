package com.simplicity.client.content.overlay;

import java.util.ArrayList;
import java.util.List;

import com.simplicity.client.Client;
import com.simplicity.client.DrawingArea;
import com.simplicity.client.content.overlay.impl.AmmunitionOverlay;
import com.simplicity.client.content.overlay.impl.FadeOverlay;
import com.simplicity.client.content.overlay.impl.HitpointsOverlay;
import com.simplicity.client.content.overlay.impl.KDROverlay;
import com.simplicity.client.content.overlay.impl.RaidPointsOverlay;

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
		
		int minX = 2;
		
		int minY = 20;
		
		for (ScreenOverlay overlay : OVERLAYS) {
			if (!overlay.enabled()) {
				continue;
			}
			
			ScreenOverlayGroup group = overlay.getOverlayGroup();
			
			int drawX = overlay.getX() + positionX[group.ordinal()];
			int drawY = overlay.getY() + positionY[group.ordinal()];
			
			try {
				if (overlay.draw(Client.instance, minX + drawX, minY + drawY)) {
					
					if (group == ScreenOverlayGroup.TOP_LEFT) {
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
	 * Initializes the overlays.
	 */
	public static void init() {
		OVERLAYS.add(new KDROverlay());
		OVERLAYS.add(new HitpointsOverlay());
		OVERLAYS.add(new AmmunitionOverlay());
		OVERLAYS.add(new RaidPointsOverlay());
		
		OVERLAYS.add(new FadeOverlay());
	}

}
