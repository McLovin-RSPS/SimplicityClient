package net.runelite.client.game;

import lombok.Value;
import net.runelite.api.coords.LocalPoint;

@Value
public class ItemStack
{
	private final int id;
	private final int quantity;
	private final LocalPoint location;
}

