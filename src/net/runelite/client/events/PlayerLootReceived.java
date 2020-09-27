package net.runelite.client.events;

import java.util.Collection;

import com.simplicity.client.Player;

import lombok.Value;
import net.runelite.client.game.ItemStack;

@Value
public class PlayerLootReceived
{
	private final Player player;
	private final Collection<ItemStack> items;
}
