/*
 * Copyright (c) 2020, Jordan Zomerlei <https://github.com/JZomerlei>
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
package net.runelite.client.plugins.mining;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.simplicity.client.Player;
import lombok.Getter;
import static net.runelite.api.AnimationID.MINING_3A_PICKAXE;
import static net.runelite.api.AnimationID.MINING_ADAMANT_PICKAXE;
import static net.runelite.api.AnimationID.MINING_BLACK_PICKAXE;
import static net.runelite.api.AnimationID.MINING_BRONZE_PICKAXE;
import static net.runelite.api.AnimationID.MINING_DRAGON_PICKAXE;
import static net.runelite.api.AnimationID.MINING_INFERNAL_PICKAXE;
import static net.runelite.api.AnimationID.MINING_IRON_PICKAXE;
import static net.runelite.api.AnimationID.MINING_MITHRIL_PICKAXE;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_3A;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_ADAMANT;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_BLACK;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_BRONZE;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_DRAGON;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_INFERNAL;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_IRON;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_MITHRIL;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_RUNE;
import static net.runelite.api.AnimationID.MINING_MOTHERLODE_STEEL;
import static net.runelite.api.AnimationID.MINING_RUNE_PICKAXE;
import static net.runelite.api.AnimationID.MINING_STEEL_PICKAXE;
import static net.runelite.api.ItemID.ADAMANT_PICKAXE;
import static net.runelite.api.ItemID.BLACK_PICKAXE;
import static net.runelite.api.ItemID.BRONZE_PICKAXE;
import static net.runelite.api.ItemID.CRYSTAL_PICKAXE;
import static net.runelite.api.ItemID.DRAGON_PICKAXE;
import static net.runelite.api.ItemID.DRAGON_PICKAXE_OR;
import static net.runelite.api.ItemID.DRAGON_PICKAXE_12797;
import static net.runelite.api.ItemID.GILDED_PICKAXE;
import static net.runelite.api.ItemID.INFERNAL_PICKAXE;
import static net.runelite.api.ItemID.IRON_PICKAXE;
import static net.runelite.api.ItemID.MITHRIL_PICKAXE;
import static net.runelite.api.ItemID.RUNE_PICKAXE;
import static net.runelite.api.ItemID.STEEL_PICKAXE;
import static net.runelite.api.ItemID._3RD_AGE_PICKAXE;

@Getter
enum Pickaxe
{
	BRONZE(BRONZE_PICKAXE, MINING_BRONZE_PICKAXE, MINING_MOTHERLODE_BRONZE),
	IRON(IRON_PICKAXE, MINING_IRON_PICKAXE, MINING_MOTHERLODE_IRON),
	STEEL(STEEL_PICKAXE, MINING_STEEL_PICKAXE, MINING_MOTHERLODE_STEEL),
	BLACK(BLACK_PICKAXE, MINING_BLACK_PICKAXE, MINING_MOTHERLODE_BLACK),
	MITHRIL(MITHRIL_PICKAXE, MINING_MITHRIL_PICKAXE, MINING_MOTHERLODE_MITHRIL),
	ADAMANT(ADAMANT_PICKAXE, MINING_ADAMANT_PICKAXE, MINING_MOTHERLODE_ADAMANT),
	RUNE(RUNE_PICKAXE, MINING_RUNE_PICKAXE, MINING_MOTHERLODE_RUNE),
	GILDED(GILDED_PICKAXE),
	DRAGON(DRAGON_PICKAXE, MINING_DRAGON_PICKAXE, MINING_MOTHERLODE_DRAGON),
	DRAGON_OR(DRAGON_PICKAXE_OR),
	DRAGON_UPGRADED(DRAGON_PICKAXE_12797),
	INFERNAL(INFERNAL_PICKAXE, MINING_INFERNAL_PICKAXE, MINING_MOTHERLODE_INFERNAL),
	THIRDAGE(_3RD_AGE_PICKAXE, MINING_3A_PICKAXE, MINING_MOTHERLODE_3A),
	CRYSTAL(CRYSTAL_PICKAXE);

	private final int itemId;
	private final int[] animIds;

	private static final Map<Integer, Pickaxe> PICKAXE_ANIM_IDS;

	static
	{
		ImmutableMap.Builder<Integer, Pickaxe> builder = new ImmutableMap.Builder<>();

		for (Pickaxe pickaxe : values())
		{
			for (int animId : pickaxe.animIds)
			{
				builder.put(animId, pickaxe);
			}
		}

		PICKAXE_ANIM_IDS = builder.build();
	}

	Pickaxe(int itemId, int ... animIds)
	{
		this.itemId = itemId;
		this.animIds = animIds;
	}

	boolean matchesMiningAnimation(final Player player)
	{
		return player != null && fromAnimation(player.getAnimation()) == this;
	}

	static Pickaxe fromAnimation(int animId)
	{
		return PICKAXE_ANIM_IDS.get(animId);
	}

	public static Set<Integer> all()
	{
		return Arrays.stream(values())
			.map(pickaxe -> pickaxe.itemId)
			.collect(Collectors.toSet());
	}
}
