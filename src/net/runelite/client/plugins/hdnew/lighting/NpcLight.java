/*
 * Copyright (c) 2021, 117 <https://twitter.com/117scape>
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
package net.runelite.client.plugins.hdnew.lighting;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.NpcID;
import net.runelite.client.plugins.hdnew.HDUtils;
import net.runelite.client.plugins.hdnew.lighting.LightManager.Alignment;
import net.runelite.client.plugins.hdnew.lighting.LightManager.LightType;

import static net.runelite.api.NpcID.*;

@AllArgsConstructor
@Getter
enum NpcLight
{
	HELLCAT(20, Alignment.CENTER, 220, 7.5f , rgb(255, 0, 0), LightType.STATIC, 0, 0, NpcID.HELLCAT, HELLCAT_6668, LAZY_HELLCAT, OVERGROWN_HELLCAT, WILY_HELLCAT, LAZY_HELLCAT_6689, OVERGROWN_HELLCAT_6682, WILY_HELLCAT_6696, HELLKITTEN),
	HELLRAT(20, Alignment.CENTER, 220, 7.5f , rgb(255, 0, 0), LightType.STATIC, 0, 0, NpcID.HELLRAT, HELLRAT_BEHEMOTH),
	HELLPUPPY(20, Alignment.CENTER, 220, 10f, rgb(255, 208, 54), LightType.PULSE, 5000, 20, NpcID.HELLPUPPY, HELLPUPPY_3099),

	TZREK_ZUK(10, Alignment.CENTER, 100, 10.0f , rgb(252, 128, 45), LightType.STATIC, 0, 0, TZREKZUK, TZREKZUK_8011),

	MIDNIGHT(10, Alignment.CENTER, 100, 10.0f , rgb(252, 128, 45), LightType.STATIC, 0, 0, NpcID.MIDNIGHT, MIDNIGHT_7893),

	RIFT_GUARDIAN_FIRE(50, Alignment.CENTER, 100, 12.5f , rgb(255, 0, 0), LightType.PULSE, 2100, 10, RIFT_GUARDIAN, RIFT_GUARDIAN_7354),
	RIFT_GUARDIAN_AIR(50, Alignment.CENTER, 100, 12.5f , rgb(255, 255, 255), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7338, RIFT_GUARDIAN_7355),
	RIFT_GUARDIAN_MIND(50, Alignment.CENTER, 100, 12.5f , rgb(255, 163, 59), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7339, RIFT_GUARDIAN_7356),
	RIFT_GUARDIAN_WATER(50, Alignment.CENTER, 100, 12.5f , rgb(18, 172, 255), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7340, RIFT_GUARDIAN_7357),
	RIFT_GUARDIAN_EARTH(50, Alignment.CENTER, 100, 12.5f , rgb(184, 124, 81), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7341, RIFT_GUARDIAN_7358),
	RIFT_GUARDIAN_BODY(50, Alignment.CENTER, 100, 12.5f , rgb(37, 71, 194), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7342, RIFT_GUARDIAN_7359),
	RIFT_GUARDIAN_COSMIC(50, Alignment.CENTER, 100, 12.5f , rgb(255, 255, 0), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7343, RIFT_GUARDIAN_7360),
	RIFT_GUARDIAN_CHAOS(50, Alignment.CENTER, 100, 12.5f , rgb(255, 196, 0), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7344, RIFT_GUARDIAN_7361),
	RIFT_GUARDIAN_NATURE(50, Alignment.CENTER, 100, 12.5f , rgb(0, 255, 0), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7345, RIFT_GUARDIAN_7362),
	RIFT_GUARDIAN_LAW(50, Alignment.CENTER, 100, 12.5f , rgb(21, 63, 232), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7346, RIFT_GUARDIAN_7363),
	RIFT_GUARDIAN_DEATH(50, Alignment.CENTER, 100, 12.5f , rgb(255, 239, 235), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7347, RIFT_GUARDIAN_7364),
	RIFT_GUARDIAN_SOUL(50, Alignment.CENTER, 100, 12.5f , rgb(115, 77, 255), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7348, RIFT_GUARDIAN_7365),
	RIFT_GUARDIAN_ASTRAL(50, Alignment.CENTER, 100, 12.5f , rgb(245, 153, 255), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7349, RIFT_GUARDIAN_7366),
	RIFT_GUARDIAN_BLOOD(50, Alignment.CENTER, 100, 12.5f , rgb(255, 0, 0), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_7350, RIFT_GUARDIAN_7367),
	RIFT_GUARDIAN_WRATH(50, Alignment.CENTER, 100, 12.5f , rgb(212, 58, 47), LightType.PULSE, 2100, 10, RIFT_GUARDIAN_8024, RIFT_GUARDIAN_8028),

	CHAOS_ELEMENTAL_JR(250, Alignment.CENTER, 150, 12.5f , rgb(255, 255, 255), LightType.FLICKER, 0, 80, NpcID.CHAOS_ELEMENTAL_JR, CHAOS_ELEMENTAL_JR_5907),

	SKOTOS(70, Alignment.CENTER, 150, 12.5f , rgb(157, 0, 255), LightType.PULSE, 3000, 20, NpcID.SKOTOS, SKOTOS_7671),

	VANGUARD_PET(20, Alignment.CENTER, 100, 12.5f , rgb(255, 153, 0), LightType.PULSE, 2100, 10, VANGUARD_8198, VANGUARD_8203),

	SMOKE_DEVIL_PET(20, Alignment.CENTER, 100, 12.5f , rgb(255, 242, 0), LightType.PULSE, 3000, 15, SMOKE_DEVIL_6639, SMOKE_DEVIL_6655),

	PYREFIEND(60, Alignment.CENTER, 280, 7.5f , rgb(252, 122, 3), LightType.FLICKER, 0, 20, NpcID.PYREFIEND, PYREFIEND_434, PYREFIEND_435, PYREFIEND_436, PYREFIEND_3139),

	PHASMATYS_GHOSTS(80, Alignment.CENTER, 300, 12.5f , rgb(100, 255, 100), LightType.FLICKER, 0, 10, GHOST_DISCIPLE, NECROVARUS, GHOST_GUARD, TORTURED_SOUL, GHOST_VILLAGER, GRAVINGAS, GHOST_INNKEEPER, GHOST_SHOPKEEPER, DROALAK, DROALAK_3494, SARAH_8134, GHOST_BANKER, GHOST_CAPTAIN, GHOST_CAPTAIN_3006, GHOST_SAILOR, VELORINA, GHOST_FARMER, GHOST_GUARD_6698),

	BARROWS_BROTHERS(50, Alignment.CENTER, 220, 12.5f , rgb(255, 150, 100), LightType.PULSE, 1, 0.7f, AHRIM_THE_BLIGHTED, KARIL_THE_TAINTED, DHAROK_THE_WRETCHED, TORAG_THE_CORRUPTED, VERAC_THE_DEFILED, GUTHAN_THE_INFESTED),

	// Cerberus
	CERBERUS(100, Alignment.CENTER, 800, 30f, rgb(255, 208, 54), LightType.PULSE, 5000, 20, NpcID.CERBERUS, CERBERUS_5863, CERBERUS_5866),

	// Morytania
	// Hallowed Sepulchre
	CROSSBOWMAN_STATUE_BOLT(220, Alignment.CENTER, 400, 10.0f , rgb(0, 190, 252), LightType.STATIC, 0, 0, 9672),
	CROSSBOWMAN_STATUE_BOLT2(220, Alignment.CENTER, 400, 4 , rgb(130, 224, 255), LightType.STATIC, 0, 0, 9673),
	CROSSBOWMAN_STATUE_BOLT3(220, Alignment.CENTER, 400, 4 , rgb(252, 214, 0), LightType.STATIC, 0, 0, 9674),
	// Theatre of Blood
	NYCOLAS_HAGIOS_SMALL(5, Alignment.CENTER, 300, 10.0f , rgb(0, 200, 255), LightType.FLICKER, 0, 40, 8344),
	NYCOLAS_HAGIOS_MEDIUM(30, Alignment.CENTER, 500, 10.0f , rgb(0, 200, 255), LightType.FLICKER, 0, 40, 8347, 8383),
	NYCOLAS_VASILIAS(30, Alignment.CENTER, 1000, 12.5f , rgb(0, 200, 255), LightType.FLICKER, 0, 40, 7988, 8356),

	// Zanaris
	ZANARIS_FAIRY(200, Alignment.CENTER, 200, 15.0f, rgb(255, 255, 255), LightType.PULSE, 4500, 10, CHAELDAR, FAIRY_QUEEN, FAIRY_NUFF, FAIRY_GODFATHER, FAIRY_QUEEN_1842, FAIRY_VERY_WISE, FAIRY, FAIRY_1849, FAIRY_1850, FAIRY_1851, FAIRY_2829, FAIRY_SHOP_KEEPER, FAIRY_SHOP_ASSISTANT, FAIRY_AERYKA, COORDINATOR, FAIRY_NUFF_5836, FAIRY_GODFATHER_5837, SLIM_LOUIE, FAT_ROCCO, FAIRY_CHEF, FAIRY_FIXIT, FAIRY_7748),

	// Chambers of Xeric
	COX_GLOWING_CRYSTAL(180, Alignment.CENTER, 900, 15.0f , rgb(76, 0, 255), LightType.PULSE, 2400, 20, 7568),
	VASA_NISTIRIO(300, Alignment.CENTER, 1200, 15.0f , rgb(76, 0, 255), LightType.PULSE, 2400, 30, NpcID.VASA_NISTIRIO, VASA_NISTIRIO_7567),
	TEKTON(100, Alignment.CENTER, 600, 15.0f , rgb(255, 162, 41), LightType.STATIC, 2400, 0, NpcID.TEKTON, TEKTON_7541, TEKTON_7542, TEKTON_7545),
	TEKTON_ENRAGED(100, Alignment.CENTER, 600, 20.0f , rgb(255, 95, 41), LightType.STATIC, 2400, 0, NpcID.TEKTON_ENRAGED, TEKTON_ENRAGED_7544),
	VANGUARD(20, Alignment.CENTER, 600, 15.0f , rgb(255, 162, 41), LightType.STATIC, 2400, 0, NpcID.VANGUARD, VANGUARD_7526, VANGUARD_7527, VANGUARD_7528, VANGUARD_7529),
	JEWELLED_CRAB_GREEN(60, Alignment.CENTER, 300, 15.0f , rgb(128, 255, 0), LightType.PULSE, 2400, 20, NpcID.JEWELLED_CRAB_GREEN),
	JEWELLED_CRAB_RED(60, Alignment.CENTER, 300, 15.0f , rgb(255, 0, 0), LightType.PULSE, 2400, 20, NpcID.JEWELLED_CRAB_RED),
	JEWELLED_CRAB_BLUE(60, Alignment.CENTER, 300, 15.0f , rgb(0, 255, 255), LightType.PULSE, 2400, 20, NpcID.JEWELLED_CRAB_BLUE),

	// Nightmare of Ashihama
	FIRE_GIANT(370, Alignment.CENTER, 400, 12.5f , rgb(255, 124, 0), LightType.FLICKER, 0, 20, NpcID.FIRE_GIANT, FIRE_GIANT_2076, FIRE_GIANT_2077, FIRE_GIANT_2078, FIRE_GIANT_2079, FIRE_GIANT_2080, FIRE_GIANT_2081, FIRE_GIANT_2082, FIRE_GIANT_2083, FIRE_GIANT_2084, FIRE_GIANT_7251, FIRE_GIANT_7252),

	// Pest Control
	PEST_CONTROL_PURPLE_PORTAL(120, Alignment.CENTER, 400, 15.0f , rgb(150, 75, 255), LightType.PULSE, 2000, 25, PORTAL, PORTAL_1743, PORTAL_1747, PORTAL_1751),
	PEST_CONTROL_BLUE_PORTAL(120, Alignment.CENTER, 400, 12.5f , rgb(0, 175, 255), LightType.PULSE, 2000, 25, PORTAL_1740, PORTAL_1744, PORTAL_1748, PORTAL_1752),
	PEST_CONTROL_YELLOW_PORTAL(120, Alignment.CENTER, 400, 12.5f , rgb(230, 255, 90), LightType.PULSE, 2000, 25, PORTAL_1741, PORTAL_1745, PORTAL_1749, PORTAL_1753),
	PEST_CONTROL_RED_PORTAL(120, Alignment.CENTER, 400, 12.5f , rgb(255, 0, 90), LightType.PULSE, 2000, 25, PORTAL_1742, PORTAL_1746, PORTAL_1750, PORTAL_1754),
	PEST_CONTROL_KNIGHT(15, Alignment.CENTER, 200, 7.5f , rgb(255, 0, 30), LightType.PULSE, 1500, 10, VOID_KNIGHT_2950, VOID_KNIGHT_2951, VOID_KNIGHT_2952, VOID_KNIGHT_2953),

	;


	private final int[] id;
	private final int height;
	private final Alignment alignment;
	private final int size;
	private final float strength;
	private final float[] color;
	private final LightType lightType;
	private final float duration;
	private final float range;

	NpcLight(int height, Alignment alignment, int size, float strength, float[] color, LightType lightType, float duration, float range, int... ids)
	{
		this.height = height;
		this.alignment = alignment;
		this.size = size;
		this.strength = strength;
		this.color = color;
		this.lightType = lightType;
		this.duration = duration;
		this.range = range;
		this.id = ids;
	}

	private static final Map<Integer, NpcLight> LIGHTS;

	static
	{
		ImmutableMap.Builder<Integer, NpcLight> builder = new ImmutableMap.Builder<>();
		for (NpcLight npcLight : values())
		{
			for (int id : npcLight.id)
			{
				builder.put(id, npcLight);
			}
		}
		LIGHTS = builder.build();
	}

	static NpcLight find(int id)
	{
		return LIGHTS.get(id);
	}

	private static float[] rgb(int r, int g, int b)
	{
		return new float[]{
			HDUtils.gammaToLinear(r / 255f),
			HDUtils.gammaToLinear(g / 255f),
			HDUtils.gammaToLinear(b / 255f)
		};
	}
}
