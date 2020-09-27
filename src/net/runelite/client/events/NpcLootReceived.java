package net.runelite.client.events;

import java.util.Collection;

import com.simplicity.client.NPC;

import lombok.Value;
import net.runelite.client.game.ItemStack;

@Value
public class NpcLootReceived
{
	private final NPC npc;
	private final Collection<ItemStack> items;
}
