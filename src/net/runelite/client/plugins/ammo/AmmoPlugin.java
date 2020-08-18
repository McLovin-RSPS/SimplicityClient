/*
 * Copyright (c) 2019 Hydrox6 <ikada@protonmail.ch>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.ammo;

import java.awt.image.BufferedImage;

import javax.inject.Inject;

import com.simplicity.client.Item;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.client.container.item.ItemContainer;

import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

@PluginDescriptor(
	name = "Ammo",
	description = "Shows the current ammo the player has equipped",
	tags = {"bolts", "darts", "chinchompa", "equipment"}
)
public class AmmoPlugin extends Plugin
{
	@Inject
	private ClientThread clientThread;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ItemManager itemManager;

	private AmmoCounter counterBox;

	@Override
	protected void startUp() throws Exception
	{
		clientThread.invokeLater(() ->
		{
			
			final ItemContainer equipment = client.getEquipment();
			
			if (equipment != null && equipment.getItems() != null) {
				checkEquipment(equipment.getItems());
			}
		});
	}

	@Override
	protected void shutDown() throws Exception
	{
		infoBoxManager.removeInfoBox(counterBox);
		counterBox = null;
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getItemContainer() != client.getEquipment()) {
			return;
		}
		
		checkEquipment(event.getItemContainer().getItems());
	}

	private void checkEquipment(final Item[] items)
	{
		// Check for weapon slot items. This overrides the ammo slot,
		// as the player will use the thrown weapon (eg. chinchompas, knives, darts)
		final Item weapon = items[EquipmentInventorySlot.WEAPON.getSlotIdx()];
		
		if (weapon.ID > 0) {
			final ItemDefinition def = ItemDefinition.forID(weapon.ID);
			
			boolean add = def.stackable || def.name.contains(" dart") || def.name.contains(" knife") || def.name.endsWith(" chinchompa");
			
			if (add) {
				updateInfobox(weapon, def);
				return;
			}
		}

		if (items[EquipmentInventorySlot.AMMO.getSlotIdx()].ID < 0) {
			removeInfobox();
			return;
		}

		final Item ammo = items[EquipmentInventorySlot.AMMO.getSlotIdx()];
		
		final ItemDefinition def = ItemDefinition.forID(ammo.ID);

		if (!def.stackable) {
			removeInfobox();
			return;
		}
		
		updateInfobox(ammo, def);
	}

	private void updateInfobox(final Item item, final ItemDefinition comp)
	{
		if (counterBox != null && counterBox.getItemID() == item.ID)
		{
			counterBox.setCount(item.amount);
			return;
		}
		
		removeInfobox();
		final BufferedImage image = itemManager.getImage(item.ID, 5, false);
		counterBox = new AmmoCounter(this, item.ID, item.amount, comp.name, image);
		infoBoxManager.addInfoBox(counterBox);
	}

	private void removeInfobox()
	{
		infoBoxManager.removeInfoBox(counterBox);
		counterBox = null;
	}
}
