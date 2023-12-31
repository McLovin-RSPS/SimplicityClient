/*
 * Copyright (c) 2018 kulers
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
package net.runelite.client.plugins.inventorytags;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.ConfigChanged;
import net.runelite.api.events.MenuOpened;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.events.WidgetMenuOptionClicked;
import net.runelite.api.util.Text;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.menus.WidgetMenuOption;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.util.List;

@PluginDescriptor(
	name = "Inventory Tags",
	description = "Add the ability to tag items in your inventory",
	tags = {"highlight", "items", "overlay", "tagging"},
	enabledByDefault = false
)
@Singleton
public class InventoryTagsPlugin extends Plugin
{
	private static final String ITEM_KEY_PREFIX = "item_";

	private static final String SETNAME_GROUP_1 = "Group 1";
	private static final String SETNAME_GROUP_2 = "Group 2";
	private static final String SETNAME_GROUP_3 = "Group 3";
	private static final String SETNAME_GROUP_4 = "Group 4";
	private static final String SETNAME_GROUP_5 = "Group 5";
	private static final String SETNAME_GROUP_6 = "Group 6";
	private static final String SETNAME_GROUP_7 = "Group 7";
	private static final String SETNAME_GROUP_8 = "Group 8";


	private static final String CONFIGURE = "Configure";
	private static final String SAVE = "Save";
	private static final String MENU_TARGET = "Inventory Tags";
	private static final String MENU_SET = "Mark";
	private static final String MENU_REMOVE = "Remove";

	private static final WidgetMenuOption FIXED_INVENTORY_TAB_CONFIGURE = new WidgetMenuOption(CONFIGURE,
		MENU_TARGET, WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB);
	private static final WidgetMenuOption FIXED_INVENTORY_TAB_SAVE = new WidgetMenuOption(SAVE,
		MENU_TARGET, WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB);
	private static final WidgetMenuOption RESIZABLE_INVENTORY_TAB_CONFIGURE = new WidgetMenuOption(CONFIGURE,
		MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB);
	private static final WidgetMenuOption RESIZABLE_INVENTORY_TAB_SAVE = new WidgetMenuOption(SAVE,
		MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB);
	private static final WidgetMenuOption RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_CONFIGURE = new WidgetMenuOption(CONFIGURE,
		MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB);
	private static final WidgetMenuOption RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_SAVE = new WidgetMenuOption(SAVE,
		MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB);

	private static final List<String> GROUPS = ImmutableList.of(SETNAME_GROUP_8, SETNAME_GROUP_7, SETNAME_GROUP_6,
		SETNAME_GROUP_5, SETNAME_GROUP_4, SETNAME_GROUP_3, SETNAME_GROUP_2, SETNAME_GROUP_1);

	@Inject
	private ConfigManager configManager;

	@Inject
	private InventoryTagsConfig config;

	@Inject
	private MenuManager menuManager;

	@Inject
	private InventoryTagsOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private EventBus eventBus;

	@Inject
	private Client client;

	private boolean editorMode;

	private InventoryTagsConfig.amount amount;
	private Color group1Color;
	private Color group2Color;
	private Color group3Color;
	private Color group4Color;
	private Color group5Color;
	private Color group6Color;
	private Color group7Color;
	private Color group8Color;

	@Provides
	InventoryTagsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(InventoryTagsConfig.class);
	}

	String getTag(int itemId)
	{
		String tag = configManager.getConfiguration(InventoryTagsConfig.GROUP, ITEM_KEY_PREFIX + itemId);
		if(itemId == 335)
			System.out.println(tag == null ? "" : "");
		if (tag == null || tag.isEmpty())
		{
			if(itemId == 335)
				System.out.println("tag empty/null");
			return null;
		}

		return tag;
	}

	public void setTag(int itemId, String tag)
	{
		System.out.println("Settings " + itemId + " with tag=" + tag);
		configManager.setConfiguration(InventoryTagsConfig.GROUP, ITEM_KEY_PREFIX + itemId, tag);
	}

	public void unsetTag(int itemId)
	{
		configManager.unsetConfiguration(InventoryTagsConfig.GROUP, ITEM_KEY_PREFIX + itemId);
	}

	@Override
	protected void startUp() throws Exception
	{
		updateConfig();

		refreshInventoryMenuOptions();
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{

		removeInventoryMenuOptions();
		overlayManager.remove(overlay);
		editorMode = false;
	}
	@Subscribe
	public void onWidgetMenuOptionClicked(final WidgetMenuOptionClicked event)
	{
		System.out.println(event.getMenuOption());
		if (event.getWidget() == WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB
			|| event.getWidget() == WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB
			|| event.getWidget() == WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB)
		{
			System.out.println(event.getMenuOption());
			editorMode = event.getMenuOption().equalsIgnoreCase(CONFIGURE);
			System.out.println(editorMode);
			refreshInventoryMenuOptions();
		}
	}

	@Subscribe
	public void onMenuOptionClicked(final MenuOptionClicked event)
	{
		if(com.simplicity.client.Client.instance.isMenuOpen())
			System.out.println(event.toString() + "\n" + event.getMenuAction().toString() + "\n");
		if (event.getMenuAction() != MenuAction.RUNELITE)
		{
			return;
		}


		final String selectedMenu = Text.removeTags(event.getMenuTarget());
		System.out.println("selectedMenu=" + selectedMenu + "\n original=" + event.getMenuTarget() + "\n MenuOption =" + event.getMenuOption());
		if (event.getMenuOption().equals(MENU_SET))
		{
			setTag(event.getId(), selectedMenu);
		}
		else if (event.getMenuOption().equals(MENU_REMOVE))
		{
			unsetTag(event.getId());
		}
	}

	@Subscribe
	public void onMenuOpened(final MenuOpened event)
	{
		final MenuEntry firstEntry = client.getMenuEntries()[client.getMenuEntries().length - 1];

		if (firstEntry == null)
		{
			return;
		}

		final int widgetId = firstEntry.getParam1();
		// Inventory item menu
		if (widgetId == WidgetInfo.INVENTORY.getId() && editorMode)
		{
			int itemId = firstEntry.getIdentifier();

			if (itemId == -1)
			{
				return;
			}

			MenuEntry[] menuList = new MenuEntry[amount.toInt() + 1];
			int num = 0;
			List<String> groups = GROUPS.subList(Math.max(GROUPS.size() - amount.toInt(), 0), GROUPS.size());

			for (final String groupName : groups)
			{
				final String group = getTag(itemId);
				final MenuEntry newMenu = new MenuEntry();
				final Color color = getGroupNameColor(groupName);
				newMenu.setOption(groupName.equals(group) ? MENU_REMOVE : MENU_SET);
				newMenu.setTarget(ColorUtil.prependColorTag(groupName, MoreObjects.firstNonNull(color, Color.WHITE)));
				newMenu.setIdentifier(itemId);
				newMenu.setParam1(widgetId);
				newMenu.setType(1500);
				menuList[num++] = newMenu;
			}
			event.setMenuEntries(menuList);
			event.setModified();
		}
	}
	private void removeInventoryMenuOptions()
	{
		menuManager.removeManagedCustomMenu(FIXED_INVENTORY_TAB_CONFIGURE);
		menuManager.removeManagedCustomMenu(FIXED_INVENTORY_TAB_SAVE);
		menuManager.removeManagedCustomMenu(RESIZABLE_INVENTORY_TAB_CONFIGURE);
		menuManager.removeManagedCustomMenu(RESIZABLE_INVENTORY_TAB_SAVE);
		menuManager.removeManagedCustomMenu(RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_CONFIGURE);
		menuManager.removeManagedCustomMenu(RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_SAVE);
	}

	private void refreshInventoryMenuOptions()
	{
		FIXED_INVENTORY_TAB_CONFIGURE.setDeprioritized(true);
		FIXED_INVENTORY_TAB_SAVE.setDeprioritized(true);
		removeInventoryMenuOptions();
		if (editorMode)
		{
			menuManager.addManagedCustomMenu(FIXED_INVENTORY_TAB_SAVE);
			menuManager.addManagedCustomMenu(RESIZABLE_INVENTORY_TAB_SAVE);
			menuManager.addManagedCustomMenu(RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_SAVE);
		}
		else
		{
			menuManager.addManagedCustomMenu(FIXED_INVENTORY_TAB_CONFIGURE);
			menuManager.addManagedCustomMenu(RESIZABLE_INVENTORY_TAB_CONFIGURE);
			menuManager.addManagedCustomMenu(RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_CONFIGURE);
		}
	}
	Color getGroupNameColor(final String name)
	{
		switch (name)
		{
			case SETNAME_GROUP_1:
				return this.group1Color;
			case SETNAME_GROUP_2:
				return this.group2Color;
			case SETNAME_GROUP_3:
				return this.group3Color;
			case SETNAME_GROUP_4:
				return this.group4Color;
			case SETNAME_GROUP_5:
				return this.group5Color;
			case SETNAME_GROUP_6:
				return this.group6Color;
			case SETNAME_GROUP_7:
				return this.group7Color;
			case SETNAME_GROUP_8:
				return this.group8Color;
		}
		return null;
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals("inventorytags"))
		{
			updateConfig();
		}
	}

	public void updateConfig()
	{
		this.amount = config.getAmount();
		this.group1Color = config.getGroup1Color();
		this.group2Color = config.getGroup2Color();
		this.group3Color = config.getGroup3Color();
		this.group4Color = config.getGroup4Color();
		this.group5Color = config.getGroup5Color();
		this.group6Color = config.getGroup6Color();
		this.group7Color = config.getGroup7Color();
		this.group8Color = config.getGroup8Color();
	}
}
