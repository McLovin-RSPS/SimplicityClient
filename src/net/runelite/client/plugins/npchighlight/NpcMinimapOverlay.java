package net.runelite.client.plugins.npchighlight;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.annotation.Nullable;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

public class NpcMinimapOverlay extends Overlay
{
	private final Client client;
	private final NpcIndicatorsConfig config;
	private final NpcIndicatorsPlugin plugin;

	@Inject
	NpcMinimapOverlay(@Nullable Client client, NpcIndicatorsConfig config, NpcIndicatorsPlugin plugin)
	{
		this.client = client;
		this.config = config;
		this.plugin = plugin;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		for (com.simplicity.client.NPC npc : plugin.getHighlightedNpcs())
		{
			renderNpcOverlay(graphics, npc, npc.desc.name, config.getHighlightColor());
		}

		return null;
	}

	private void renderNpcOverlay(Graphics2D graphics, com.simplicity.client.NPC actor, String name, Color color)
	{
		/*NPCComposition npcComposition = actor.getTransformedComposition();
		if (npcComposition == null || !npcComposition.isInteractible()
			|| (actor.isDead() && config.ignoreDeadNpcs()))
		{
			return;
		}*/

		/*Point minimapLocation = actor.getMinimapLocation();
		if (minimapLocation != null)
		{
			OverlayUtil.renderMinimapLocation(graphics, minimapLocation, color.darker());

			if (config.drawMinimapNames())
			{
				OverlayUtil.renderTextLocation(graphics, minimapLocation, name, color);
			}
		}*/
	}
}
