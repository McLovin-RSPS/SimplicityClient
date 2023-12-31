/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package net.runelite.api;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of right-click menu actions.
 */
public enum MenuAction
{

	CLICK_TAB(1076),
	/**
	 * Menu action for using an item in your inventory on a tile object (GameObject or GroundObject).
	 */
	ITEM_USE_ON_GAME_OBJECT(62),
	/**
	 * Menu action for casting a spell on a tile object (GameObject or GroundObject).
	 */
	SPELL_CAST_ON_GAME_OBJECT(956),
	/**
	 * First menu action for a game object.
	 */
	GAME_OBJECT_FIRST_OPTION(502),
	/**
	 * Second menu action for a game object.
	 */
	GAME_OBJECT_SECOND_OPTION(900),
	/**
	 * Third menu action for a game object.
	 */
	GAME_OBJECT_THIRD_OPTION(113),
	/**
	 * Fourth menu action for a game object.
	 */
	GAME_OBJECT_FOURTH_OPTION(6),
	/**
	 * Fifth menu action for a game object.
	 */
	GAME_OBJECT_FIFTH_OPTION(1001),

	/**
	 * Menu action for using an item in your inventory on an NPC.
	 */
	ITEM_USE_ON_NPC(582),
	/**
	 * Menu action for casting a spell on an NPC.
	 */
	SPELL_CAST_ON_NPC(8),
	/**
	 * First menu action for an NPC.
	 */
	NPC_FIRST_OPTION(20),
	/**
	 * Second menu action for an NPC.
	 */
	NPC_SECOND_OPTION(412),
	/**
	 * Third menu action for an NPC.
	 */
	NPC_THIRD_OPTION(225),
	/**
	 * Fourth menu action for an NPC.
	 */
	NPC_FOURTH_OPTION(965),
	/**
	 * Fifth menu action for an NPC.
	 */
	NPC_FIFTH_OPTION(478),

	/**
	 * Menu action for using an item on a player.
	 */
	ITEM_USE_ON_PLAYER(14),
	/**
	 * Menu action for casting a spell on a player.
	 */
	SPELL_CAST_ON_PLAYER(15),

	/**
	 * Menu action for using an item on an item on the ground.
	 */
	ITEM_USE_ON_GROUND_ITEM(511),
	/**
	 * Menu action for casting a spell on an item on the ground.
	 */
	SPELL_CAST_ON_GROUND_ITEM(17),
	/**
	 * First menu action for an item on the ground.
	 */
	GROUND_ITEM_FIRST_OPTION(18),
	/**
	 * Second menu action for an item on the ground.
	 */
	GROUND_ITEM_SECOND_OPTION(19),
	/**
	 * Third menu action for an item on the ground.
	 */
	GROUND_ITEM_THIRD_OPTION(22220),
	/**
	 * Fourth menu action for an item on the ground.
	 */
	GROUND_ITEM_FOURTH_OPTION(21),
	/**
	 * Fifth menu action for an item on the ground.
	 */
	GROUND_ITEM_FIFTH_OPTION(22),

	/**
	 * Interaction with widget (type 1).
	 */
	WIDGET_TYPE_1(24),
	/**
	 * Interaction with widget (type 2).
	 */
	WIDGET_TYPE_2(25),
	/**
	 * Interaction with widget (type 3).
	 */
	WIDGET_TYPE_3(26),
	/**
	 * Interaction with widget (type 4).
	 */
	WIDGET_TYPE_4(28),
	/**
	 * Interaction with widget (type 5).
	 */
	WIDGET_TYPE_5(29),
	/**
	 * Interaction with widget (type 6).
	 */
	WIDGET_TYPE_6(30),
	/**
	 * Menu action when using an item on another item inside a widget (inventory).
	 */
	ITEM_USE_ON_WIDGET_ITEM(870),
	/**
	 * Menu action when using an item on a widget.
	 */
	ITEM_USE_ON_WIDGET(32),

	/**
	 * First menu action for an item.
	 */
	ITEM_FIRST_OPTION(74),
	/**
	 * Second menu action for an item.
	 */
	ITEM_SECOND_OPTION(454),
	/**
	 * Third menu action for an item.
	 */
	ITEM_THIRD_OPTION(539),
	/**
	 * Fourth menu action for an item.
	 */
	ITEM_FOURTH_OPTION(493),
	/**
	 * Fifth menu action for an item.
	 */
	ITEM_FIFTH_OPTION(37),
	/**
	 * Menu action to drop an item (identical to ITEM_FIFTH_OPTION).
	 */
	ITEM_DROP(847),
	/**
	 * Menu action to use an item.
	 */
	ITEM_USE(447),

	/**
	 * First menu action for a widget.
	 */
	WIDGET_FIRST_OPTION(39),
	/**
	 * Second menu action for a widget.
	 */
	WIDGET_SECOND_OPTION(40),
	/**
	 * Third menu action for a widget.
	 */
	WIDGET_THIRD_OPTION(41),
	/**
	 * Fourth menu action for a widget.
	 */
	WIDGET_FOURTH_OPTION(42),
	/**
	 * Fifth menu action for a widget.
	 */
	WIDGET_FIFTH_OPTION(43),

	PLAYER_FIRST_OPTION(561),
	PLAYER_SECOND_OPTION(779),
	PLAYER_THIRD_OPTION(27),
	PLAYER_FOURTH_OPTION(577),
	PLAYER_FIFTH_OPTION(729),
	PLAYER_SIXTH_OPTION(829),
	PLAYER_SEVENTH_OPTION(929),
	PLAYER_EIGTH_OPTION(51),

	/**
	 * Menu action for normal priority child component actions.
	 */
	CC_OP(57),

	/**
	 * Casting a spell / op target on a widget
	 */
	SPELL_CAST_ON_WIDGET(58),
	
	/**
	 * Menu action for walking.
	 */
	WALK(516),
	
	/**
	 * Menu action triggered by tile marker.
	 */
	TILE_MARK(517),

	/**
	 * Menu action triggered by examining an object.
	 */
	EXAMINE_OBJECT(1226),
	/**
	 * Menu action triggered by examining an NPC.
	 */
	EXAMINE_NPC(1025),
	/**
	 * Menu action triggered by examining item on ground.
	 */
	EXAMINE_ITEM_GROUND(1448),
	/**
	 * Menu action triggered by examining item in inventory.
	 */
	EXAMINE_ITEM(1125),
	/**
	 * Menu action triggered by canceling a menu.
	 */
	CANCEL(1107),
	/**
	 * Menu action for low priority child component actions.
	 */
	CC_OP_LOW_PRIORITY(1007),

	/**
	 * Menu action injected by runelite for its menu items.
	 */
	RUNELITE(1500),
	/**
	 * Menu action injected by runelite for overlay menu items.
	 */
	RUNELITE_OVERLAY(1501),
	/**
	 * Menu action for configuring runelite overlays.
	 */
	RUNELITE_OVERLAY_CONFIG(1502),
	/**
	 * Menu action injected by runelite for menu items which target
	 * a player and have its identifier set to a player index.
	 */
	RUNELITE_PLAYER(1503),
	/**
	 * Menu action for InfoBox menu entries
	 */
	RUNELITE_INFOBOX(1504),

	/**
	 * Menu action triggered when the id is not defined in this class.
	 */
	UNKNOWN(-1);

	public static final int MENU_ACTION_DEPRIORITIZE_OFFSET = 2000;

	private static final Map<Integer, MenuAction> map = new HashMap<>();

	static
	{
		for (MenuAction menuAction : values())
		{
			map.put(menuAction.getId(), menuAction);
		}
	}

	private final int id;

	MenuAction(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public static MenuAction of(int id)
	{
		return map.getOrDefault(id, UNKNOWN);
	}
}
