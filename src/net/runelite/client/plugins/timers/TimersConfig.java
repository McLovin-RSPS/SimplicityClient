/*
 * Copyright (c) 2017, Seth <Sethtroll3@gmail.com>
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
package net.runelite.client.plugins.timers;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import java.time.Instant;

@ConfigGroup(TimersConfig.GROUP)
public interface TimersConfig extends Config
{
	String GROUP = "timers";
	@ConfigItem(
			keyName = "showSpecialRestore",
			name = "Special Restore",
			description = "Configures whether timers for special restore potions are displayed"
	)
	default boolean showSpecialRestore()
	{
		return true;
	}
	@ConfigItem(
			keyName = "showDoubleDonation",
			name = "Double Donation",
			description = "Configures whether timers for double donations are displayed"
	)
	default boolean showDoubleDonation()
	{
		return true;
	}
	@ConfigItem(
			keyName = "showFamiliarSpecial",
			name = "Familiar Special",
			description = "Configures whether timers for familiar specials are displayed"
	)
	default boolean showFamiliarSpecial()
	{
		return true;
	}
	@ConfigItem(
			keyName = "showPrayerRenewal",
			name = "Prayer Renewal",
			description = "Configures whether timers for prayer renewal are displayed"
	)
	default boolean showPrayerRenewal()
	{
		return true;
	}
	@ConfigItem(
			keyName = "showExperience",
			name = "Experience",
			description = "Configures whether timers for bonus experience are displayed"
	)
	default boolean showExperience()
	{
		return true;
	}
	@ConfigItem(
		keyName = "showAntipoison",
		name = "Antipoison/Venom timers",
		description = "Configures whether timers for poison and venom protection are displayed"
	)
	default boolean showAntiPoison()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showAntiFire",
		name = "Antifire timer",
		description = "Configures whether antifire timer is displayed"
	)
	default boolean showAntiFire()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showStamina",
		name = "Stamina timer",
		description = "Configures whether stamina timer is displayed"
	)
	default boolean showStamina()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showOverload",
		name = "Overload timer",
		description = "Configures whether overload timer is displayed"
	)
	default boolean showOverload()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showDivine",
		name = "Divine potion timer",
		description = "Configures whether divine potion timer is displayed"
	)
	default boolean showDivine()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showImbuedHeart",
		name = "Imbued heart timer",
		description = "Configures whether imbued heart timer is displayed"
	)
	default boolean showImbuedHeart()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showVengeance",
		name = "Vengeance timer",
		description = "Configures whether vengeance and vengeance other timer is displayed"
	)
	default boolean showVengeance()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showVengeanceActive",
		name = "Vengeance active",
		description = "Configures whether an indicator for vengeance being active is displayed"
	)
	default boolean showVengeanceActive()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showTeleblock",
		name = "Teleblock timer",
		description = "Configures whether teleblock timer is displayed"
	)
	default boolean showTeleblock()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showFreezes",
		name = "Freeze timer",
		description = "Configures whether freeze timer is displayed"
	)
	default boolean showFreezes()
	{
		return true;
	}


	@ConfigItem(
		keyName = "showTzhaarTimers",
		name = "Fight Caves and Inferno timers",
		description = "Display elapsed time in the Fight Caves and Inferno"
	)
	default boolean showTzhaarTimers()
	{
		return true;
	}

	@ConfigItem(
		keyName = "tzhaarStartTime",
		name = "",
		description = "",
		hidden = true
	)
	Instant tzhaarStartTime();

	@ConfigItem(
		keyName = "tzhaarStartTime",
		name = "",
		description = ""
	)
	void tzhaarStartTime(Instant tzhaarStartTime);

	@ConfigItem(
		keyName = "tzhaarLastTime",
		name = "",
		description = "",
		hidden = true
	)
	Instant tzhaarLastTime();

	@ConfigItem(
		keyName = "tzhaarLastTime",
		name = "",
		description = ""
	)
	void tzhaarLastTime(Instant tzhaarLastTime);

	@ConfigItem(
		keyName = "showStaffOfTheDead",
		name = "Staff of the Dead timer",
		description = "Configures whether staff of the dead timer is displayed"
	)
	default boolean showStaffOfTheDead()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showAbyssalSireStun",
		name = "Abyssal Sire stun timer",
		description = "Configures whether Abyssal Sire stun timer is displayed"
	)
	default boolean showAbyssalSireStun()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showDfsSpecial",
		name = "Dragonfire Shield special timer",
		description = "Configures whether the special attack cooldown timer for the Dragonfire Shield is displayed"
	)
	default boolean showDFSSpecial()
	{
		return true;
	}
}
