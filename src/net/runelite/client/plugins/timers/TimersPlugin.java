/*
 * Copyright (c) 2017, Seth <Sethtroll3@gmail.com>
 * Copyright (c) 2018, Jordan Atwood <jordan.atwood423@gmail.com>
 * Copyright (c) 2019, winterdaze
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

import com.google.inject.Provides;
import java.time.Duration;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;

import com.simplicity.client.Entity;
import com.simplicity.client.Item;
import com.simplicity.client.NPC;
import com.simplicity.client.Player;
import com.simplicity.client.container.item.ItemContainer;
import com.simplicity.client.content.EffectTimer;
import com.simplicity.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.AnimationID;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemID;
import static net.runelite.api.ItemID.FIRE_CAPE;
import static net.runelite.api.ItemID.INFERNAL_CAPE;

import net.runelite.api.NpcID;
import net.runelite.api.Skill;
import net.runelite.api.VarPlayer;
import net.runelite.api.Varbits;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import static net.runelite.client.plugins.timers.GameIndicator.VENGEANCE_ACTIVE;
import static net.runelite.client.plugins.timers.GameTimer.*;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.RSTimeUnit;
import org.apache.commons.lang3.ArrayUtils;

@PluginDescriptor(
	name = "Timers",
	description = "Show various timers in an infobox",
	tags = {"combat", "items", "magic", "potions", "prayer", "overlay", "abyssal", "sire", "inferno", "fight", "caves", "cape", "timer", "tzhaar", "thieving", "pickpocket"}
)
@Slf4j
public class TimersPlugin extends Plugin
{
	private static final String ABYSSAL_SIRE_STUN_MESSAGE = "The Sire has been disorientated temporarily.";
	private static final String ANTIFIRE_DRINK_MESSAGE = "You drink some of your antifire potion.";
	private static final String ANTIFIRE_EXPIRED_MESSAGE = "<col=7f007f>Your antifire potion has expired.</col>";
	private static final String CANNON_BASE_MESSAGE = "You place the cannon base on the ground.";
	private static final String CANNON_STAND_MESSAGE = "You add the stand.";
	private static final String CANNON_BARRELS_MESSAGE = "You add the barrels.";
	private static final String CANNON_FURNACE_MESSAGE = "You add the furnace.";
	private static final String CANNON_PICKUP_MESSAGE = "You pick up the cannon. It's really heavy.";
	private static final String CANNON_REPAIR_MESSAGE = "You repair your cannon, restoring it to working order.";
	private static final String CANNON_DESTROYED_MESSAGE = "Your cannon has been destroyed!";
	private static final String CANNON_BROKEN_MESSAGE = "<col=ef1020>Your cannon has broken!";
	private static final String CHARGE_EXPIRED_MESSAGE = "<col=ef1020>Your magical charge fades away.</col>";
	private static final String CHARGE_MESSAGE = "<col=ef1020>You feel charged with magic power.</col>";
	private static final String EXTENDED_ANTIFIRE_DRINK_MESSAGE = "You drink some of your extended antifire potion.";
	private static final String EXTENDED_SUPER_ANTIFIRE_DRINK_MESSAGE = "You drink some of your extended super antifire potion.";
	private static final String FROZEN_MESSAGE = "You have been frozen!";
	private static final String GAUNTLET_ENTER_MESSAGE = "You enter the Gauntlet.";
	private static final String GOD_WARS_ALTAR_MESSAGE = "you recharge your prayer.";
	private static final String MAGIC_IMBUE_EXPIRED_MESSAGE = "Your Magic Imbue charge has ended.";
	private static final String MAGIC_IMBUE_MESSAGE = "You are charged to combine runes!";
	private static final String STAFF_OF_THE_DEAD_SPEC_EXPIRED_MESSAGE = "Your protection fades away";
	private static final String STAFF_OF_THE_DEAD_SPEC_MESSAGE = "Spirits of deceased evildoers offer you their protection";
	private static final String STAFF_OF_LIGHT_SPEC_MESSAGE = "You are shielded by the spirits of the Staff of light!";
	private static final String STAMINA_DRINK_MESSAGE = "You drink some of your stamina potion.";
	private static final String STAMINA_SHARED_DRINK_MESSAGE = "You have received a shared dose of stamina potion.";
	private static final String STAMINA_EXPIRED_MESSAGE = "<col=8f4808>Your stamina potion has expired.</col>";
	private static final String SUPER_ANTIFIRE_DRINK_MESSAGE = "You drink some of your super antifire potion";
	private static final String SUPER_ANTIFIRE_EXPIRED_MESSAGE = "<col=7f007f>Your super antifire potion has expired.</col>";
	private static final String KILLED_TELEBLOCK_OPPONENT_TEXT = "Your Tele Block has been removed because you killed ";
	private static final String PRAYER_ENHANCE_EXPIRED = "<col=ff0000>Your prayer enhance effect has worn off.</col>";
	private static final String ENDURANCE_EFFECT_MESSAGE = "Your Ring of endurance doubles the duration of your stamina potion's effect.";
	private static final String SHADOW_VEIL_MESSAGE = ">Your thieving abilities have been enhanced.</col>";
	private static final String DEATH_CHARGE_MESSAGE = ">Upon the death of your next foe, some of your special attack energy will be restored.</col>";
	private static final String DEATH_CHARGE_ACTIVATE_MESSAGE = ">Some of your special attack energy has been restored.</col>";
	private static final String RESURRECT_THRALL_MESSAGE_START = ">You resurrect a ";
	private static final String RESURRECT_THRALL_MESSAGE_END = " thrall.</col>";
	private static final String RESURRECT_THRALL_DISAPPEAR_MESSAGE_START = ">Your ";
	private static final String RESURRECT_THRALL_DISAPPEAR_MESSAGE_END = " thrall returns to the grave.</col>";
	private static final String WARD_OF_ARCEUUS_MESSAGE = ">Your defence against Arceuus magic has been strengthened.</col>";
	private static final String PICKPOCKET_FAILURE_MESSAGE = "You fail to pick the ";
	private static final String DODGY_NECKLACE_PROTECTION_MESSAGE = "Your dodgy necklace protects you.";

	private static final Pattern TELEBLOCK_PATTERN = Pattern.compile("A Tele Block spell has been cast on you(?: by .+)?\\. It will expire in (?<mins>\\d+) minutes?(?:, (?<secs>\\d+) seconds?)?\\.");
	private static final Pattern DIVINE_POTION_PATTERN = Pattern.compile("You drink some of your divine (.+) potion\\.");
	private static final int VENOM_VALUE_CUTOFF = -40; // Antivenom < -40 <= Antipoison < 0
	private static final int POISON_TICK_LENGTH = 30;

	static final int FIGHT_CAVES_REGION_ID = 9551;
	static final int INFERNO_REGION_ID = 9043;
	private static final int NMZ_MAP_REGION_ID = 9033;
	private static final Pattern TZHAAR_WAVE_MESSAGE = Pattern.compile("Wave: (\\d+)");
	private static final String TZHAAR_DEFEATED_MESSAGE = "You have been defeated!";
	private static final Pattern TZHAAR_COMPLETE_MESSAGE = Pattern.compile("Your (?:TzTok-Jad|TzKal-Zuk) kill count is:");
	private static final Pattern TZHAAR_PAUSED_MESSAGE = Pattern.compile("The (?:Inferno|Fight Cave) has been paused. You may now log out.");

	private TimerTimer freezeTimer;
	private int freezeTime = -1; // time frozen, in game ticks

	private TimerTimer staminaTimer;
	private boolean wasWearingEndurance;

	private int lastRaidVarb;
	private int lastVengCooldownVarb;
	private int lastIsVengeancedVarb;
	private int lastPoisonVarp;
	private int lastPvpVarb;
	private int lastImbuedHeartVarb;
	private boolean imbuedHeartTimerActive;
	private int nextPoisonTick;
	private WorldPoint lastPoint;
	private int lastAnimation;
	private ElapsedTimer tzhaarTimer;

	@Inject
	private ItemManager itemManager;

	@Inject
	private SpriteManager spriteManager;

	@Inject
	private Client client;

	@Inject
	private TimersConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Provides
	TimersConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TimersConfig.class);
	}

	@Override
	public void startUp()
	{
	}

	@Override
	protected void shutDown() throws Exception
	{
		infoBoxManager.removeIf(t -> t instanceof TimerTimer);
		lastRaidVarb = -1;
		lastPoint = null;
		lastAnimation = -1;
		lastPoisonVarp = 0;
		nextPoisonTick = 0;
		removeTzhaarTimer();
		staminaTimer = null;
		imbuedHeartTimerActive = false;
		lastImbuedHeartVarb = 0;
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		if(event.getIndex() > Varbits.CUSTOM_EFFECT_TIMER.getId()) {
			int effectIndex = event.getIndex() - Varbits.CUSTOM_EFFECT_TIMER.getId();
			if (effectIndex < EffectTimer.EffectType.values().length) {
				EffectTimer.EffectType type = EffectTimer.EffectType.values()[effectIndex];
				boolean addTimer = false;
				switch (type) {
					case ANTI_VENOM:
					case ANTIPOISON_POTION:
					case SUPER_ANTIPOISON_POTION:
						addTimer = config.showAntiPoison();
						break;
					case EXPERIENCE:
						addTimer = config.showExperience();
						break;
					case PRAYER_RENEWAL:
						addTimer = config.showPrayerRenewal();
						break;
					case ANTIFIRE_POTION:
					case SUPER_ANTIFIRE_POTION:
						addTimer = config.showAntiFire();
						break;
					case DOUBLE_DONATION:
						addTimer = config.showDoubleDonation();
						break;
					case FAMILIAR_SPECIAL:
						addTimer = config.showFamiliarSpecial();
						break;
					case SPECIAL_RESTORE_FLASK:
					case SPECIAL_RESTORE_POTION:
						addTimer = config.showSpecialRestore();
						break;
				}
				int seconds = client.getVar(Varbits.CUSTOM_EFFECT_TIMER.getId() + type.ordinal());
				if(addTimer)
					createCustomEffectTimer(type, seconds);
			}
		}
		int raidVarb = client.getVar(Varbits.IN_RAID);
		int vengCooldownVarb = client.getVar(Varbits.VENGEANCE_COOLDOWN);
		int isVengeancedVarb = client.getVar(Varbits.VENGEANCE_ACTIVE);
		int poisonVarp = client.getVar(VarPlayer.POISON);
		int pvpVarb = client.getVar(Varbits.PVP_SPEC_ORB);
		int imbuedHeartCooldownVarb = client.getVar(Varbits.IMBUED_HEART_COOLDOWN);

		if (lastRaidVarb != raidVarb)
		{
			removeGameTimer(OVERLOAD_RAID);
			removeGameTimer(PRAYER_ENHANCE);
			lastRaidVarb = raidVarb;
		}

		if (lastVengCooldownVarb != vengCooldownVarb && config.showVengeance())
		{
			if (vengCooldownVarb == 1)
			{
				createGameTimer(VENGEANCE);
			}
			else
			{
				removeGameTimer(VENGEANCE);
			}

			lastVengCooldownVarb = vengCooldownVarb;
		}

		if (lastIsVengeancedVarb != isVengeancedVarb && config.showVengeanceActive())
		{
			if (isVengeancedVarb == 1)
			{
				createGameIndicator(VENGEANCE_ACTIVE);
			}
			else
			{
				removeGameIndicator(VENGEANCE_ACTIVE);
			}

			lastIsVengeancedVarb = isVengeancedVarb;
		}

		if (lastPvpVarb != pvpVarb)
		{
			if (pvpVarb == 0)
			{
				log.debug("Left a PVP zone, clearing teleblock timer");
				removeGameTimer(TELEBLOCK);
			}

			lastPvpVarb = pvpVarb;
		}

		if (lastImbuedHeartVarb != imbuedHeartCooldownVarb && config.showImbuedHeart())
		{
			if (imbuedHeartCooldownVarb == 0)
			{
				removeGameTimer(IMBUEDHEART);
				imbuedHeartTimerActive = false;
			}
			else if (!imbuedHeartTimerActive)
			{
				createGameTimer(IMBUEDHEART, Duration.of(700, RSTimeUnit.GAME_TICKS));
				imbuedHeartTimerActive = true;
			}

			lastImbuedHeartVarb = imbuedHeartCooldownVarb;
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals(TimersConfig.GROUP))
		{
			return;
		}

		if (!config.showAntiFire())
		{
			removeGameTimer(ANTIFIRE);
			removeGameTimer(EXANTIFIRE);
			removeGameTimer(SUPERANTIFIRE);
		}

		if (!config.showStamina())
		{
			removeGameTimer(STAMINA);
		}

		if (!config.showOverload())
		{
			removeGameTimer(OVERLOAD);
			removeGameTimer(OVERLOAD_RAID);
		}

		if (!config.showDivine())
		{
			removeGameTimer(DIVINE_SUPER_ATTACK);
			removeGameTimer(DIVINE_SUPER_STRENGTH);
			removeGameTimer(DIVINE_SUPER_DEFENCE);
			removeGameTimer(DIVINE_SUPER_COMBAT);
			removeGameTimer(DIVINE_RANGING);
			removeGameTimer(DIVINE_MAGIC);
		}

		if (!config.showImbuedHeart())
		{
			removeGameTimer(IMBUEDHEART);
			imbuedHeartTimerActive = false;
		}

		if (!config.showStaffOfTheDead())
		{
			removeGameTimer(STAFF_OF_THE_DEAD);
		}

		if (!config.showVengeance())
		{
			removeGameTimer(VENGEANCE);
		}

		if (!config.showVengeanceActive())
		{
			removeGameIndicator(VENGEANCE_ACTIVE);
		}

		if (!config.showTeleblock())
		{
			removeGameTimer(TELEBLOCK);
		}

		if (!config.showFreezes())
		{
			removeGameTimer(BIND);
			removeGameTimer(SNARE);
			removeGameTimer(ENTANGLE);
			removeGameTimer(ICERUSH);
			removeGameTimer(ICEBURST);
			removeGameTimer(ICEBLITZ);
			removeGameTimer(ICEBARRAGE);
		}

		if (!config.showAntiPoison())
		{
			removeGameTimer(ANTIPOISON);
			removeGameTimer(ANTIVENOM);
			removeEffectTimer(EffectTimer.EffectType.ANTI_VENOM);
			removeEffectTimer(EffectTimer.EffectType.ANTIPOISON_POTION);
			removeEffectTimer(EffectTimer.EffectType.SUPER_ANTIPOISON_POTION);
		}

		if (!config.showTzhaarTimers())
		{
			removeTzhaarTimer();
		}
		else
		{
			createTzhaarTimer();
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (config.showStamina()
				&& event.getMenuOption().contains("Drink")) {
			System.out.println("TimersPluginDebug: " + event.toString());
		}
		if (config.showStamina()
			&& event.getMenuOption().contains("Drink")
			&& (event.getId() == ItemID.STAMINA_MIX1
			|| event.getId() == ItemID.STAMINA_MIX2
			|| event.getId() == ItemID.EGNIOL_POTION_1
			|| event.getId() == ItemID.EGNIOL_POTION_2
			|| event.getId() == ItemID.EGNIOL_POTION_3
			|| event.getId() == ItemID.EGNIOL_POTION_4
				|| event.getId() == ItemID.STAMINA_POTION4
				|| event.getId() == ItemID.STAMINA_POTION3
				|| event.getId() == ItemID.STAMINA_POTION2
				|| event.getId() == ItemID.STAMINA_POTION1))
		{
			// Needs menu option hook because mixes use a common drink message, distinct from their standard potion messages
			createStaminaTimer();
			return;
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		final String message = event.getMessage();
		if (event.getType() != ChatMessageType.SPAM && event.getType() != ChatMessageType.GAMEMESSAGE)
		{
			return;
		}

		if (message.contains(DODGY_NECKLACE_PROTECTION_MESSAGE))
		{
			removeGameTimer(PICKPOCKET_STUN);
		}

		if (message.equals(ABYSSAL_SIRE_STUN_MESSAGE) && config.showAbyssalSireStun())
		{
			createGameTimer(ABYSSAL_SIRE_STUN);
		}

		if (message.equals(ENDURANCE_EFFECT_MESSAGE))
		{
			wasWearingEndurance = true;
		}

		if (config.showStamina() && (message.equals(STAMINA_DRINK_MESSAGE) || message.equals(STAMINA_SHARED_DRINK_MESSAGE)))
		{
			createStaminaTimer();
		}

		if (message.equals(STAMINA_EXPIRED_MESSAGE) || message.equals(GAUNTLET_ENTER_MESSAGE))
		{
			removeGameTimer(STAMINA);
			staminaTimer = null;
		}

		if (config.showOverload() && message.startsWith("You drink some of your") && message.contains("overload"))
		{
			if (client.getVar(Varbits.IN_RAID) == 1)
			{
				createGameTimer(OVERLOAD_RAID);
			}
			else
			{
				createGameTimer(OVERLOAD);
			}

		}

		if (message.equals(MAGIC_IMBUE_EXPIRED_MESSAGE))
		{
			removeGameTimer(MAGICIMBUE);
		}

		if (config.showTeleblock())
		{
			Matcher m = TELEBLOCK_PATTERN.matcher(message);
			if (m.find())
			{
				String minss = m.group("mins");
				String secss = m.group("secs");
				int mins = Integer.parseInt(minss);
				int secs = secss != null ? Integer.parseInt(secss) : 0;
				createGameTimer(TELEBLOCK, Duration.ofSeconds(mins * 60 + secs));
			}
			else if (message.contains(KILLED_TELEBLOCK_OPPONENT_TEXT))
			{
				removeGameTimer(TELEBLOCK);
			}
		}

		if (config.showStaffOfTheDead() && (message.contains(STAFF_OF_THE_DEAD_SPEC_MESSAGE) || message.contains(STAFF_OF_LIGHT_SPEC_MESSAGE)))
		{
			createGameTimer(STAFF_OF_THE_DEAD);
		}

		if (config.showStaffOfTheDead() && message.contains(STAFF_OF_THE_DEAD_SPEC_EXPIRED_MESSAGE))
		{
			removeGameTimer(STAFF_OF_THE_DEAD);
		}

		if (config.showFreezes() && message.equals(FROZEN_MESSAGE))
		{
			freezeTimer = createGameTimer(ICEBARRAGE);
			freezeTime = client.getTickCount();
		}

		if (config.showDivine())
		{
			Matcher mDivine = DIVINE_POTION_PATTERN.matcher(message);
			if (mDivine.find())
			{
				switch (mDivine.group(1))
				{
					case "super attack":
						createGameTimer(DIVINE_SUPER_ATTACK);
						break;

					case "super strength":
						createGameTimer(DIVINE_SUPER_STRENGTH);
						break;

					case "super defence":
						createGameTimer(DIVINE_SUPER_DEFENCE);
						break;

					case "combat":
						createGameTimer(DIVINE_SUPER_COMBAT);
						break;

					case "ranging":
						createGameTimer(DIVINE_RANGING);
						break;

					case "magic":
						createGameTimer(DIVINE_MAGIC);
						break;

					case "bastion":
						createGameTimer(DIVINE_BASTION);
						break;

					case "battlemage":
						createGameTimer(DIVINE_BATTLEMAGE);
						break;
				}
			}
		}

		if (message.equals(TZHAAR_DEFEATED_MESSAGE) || TZHAAR_COMPLETE_MESSAGE.matcher(message).matches())
		{
			log.debug("Stopping tzhaar timer");
			removeTzhaarTimer();
			config.tzhaarStartTime(null);
			config.tzhaarLastTime(null);
			return;
		}

		if (TZHAAR_PAUSED_MESSAGE.matcher(message).find())
		{
			log.debug("Pausing tzhaar timer");
			config.tzhaarLastTime(Instant.now());
			if (config.showTzhaarTimers())
			{
				createTzhaarTimer();
			}
			return;
		}

		Matcher matcher = TZHAAR_WAVE_MESSAGE.matcher(message);
		if (matcher.find())
		{
			int wave = Integer.parseInt(matcher.group(1));
			if (wave == 1)
			{
				log.debug("Starting tzhaar timer");

				Instant now = Instant.now();
				if (isInInferno())
				{
					// The first wave message of the inferno comes six seconds after the ingame timer starts counting
					config.tzhaarStartTime(now.minus(Duration.ofSeconds(6)));
				}
				else
				{
					config.tzhaarStartTime(now);
				}
				config.tzhaarLastTime(null);

				if (config.showTzhaarTimers())
				{
					createTzhaarTimer();
				}
			}
			else if (config.tzhaarStartTime() != null && config.tzhaarLastTime() != null)
			{
				log.debug("Unpausing tzhaar timer");

				// Advance start time by how long it has been paused
				Instant tzhaarStartTime = config.tzhaarStartTime();
				tzhaarStartTime = tzhaarStartTime.plus(Duration.between(config.tzhaarLastTime(), Instant.now()));
				config.tzhaarStartTime(tzhaarStartTime);

				config.tzhaarLastTime(null);
				if (config.showTzhaarTimers())
				{
					createTzhaarTimer();
				}
			}
		}
	}
	@Subscribe
	public void onRegionChanged(RegionChanged event) {
		if(isInFightCaves() || isInInferno()) {
			createTzhaarTimer();
		} else {
			removeTzhaarTimer();
		}
	}
	private boolean isInFightCaves()
	{
		return client.getMapRegions() != null && ArrayUtils.contains(client.getMapRegions(), FIGHT_CAVES_REGION_ID);
	}

	private boolean isInInferno()
	{
		return client.getMapRegions() != null && ArrayUtils.contains(client.getMapRegions(), INFERNO_REGION_ID);
	}

	private boolean isInNightmareZone()
	{
		return client.getLocalPlayer() != null && client.getLocalPlayer().getWorldLocation().getPlane() > 0 && ArrayUtils.contains(client.getMapRegions(), NMZ_MAP_REGION_ID);
	}

	private void createTzhaarTimer()
	{
		removeTzhaarTimer();

		int imageItem = isInFightCaves() ? FIRE_CAPE : (isInInferno() ? INFERNAL_CAPE : -1);
		if (imageItem == -1 || tzhaarTimer != null)
		{
			return;
		}
		config.tzhaarStartTime(Instant.now());
		tzhaarTimer = new ElapsedTimer(itemManager.getImage(imageItem), this, config.tzhaarStartTime(), config.tzhaarLastTime());
		infoBoxManager.addInfoBox(tzhaarTimer);
	}

	private void removeTzhaarTimer()
	{
		if (tzhaarTimer != null)
		{
			infoBoxManager.removeInfoBox(tzhaarTimer);
			tzhaarTimer = null;
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		Player player = client.getLocalPlayer();
		WorldPoint currentWorldPoint = player.getWorldLocation();

		if (freezeTimer != null)
		{
			// assume movement means unfrozen
			if (freezeTime != client.getTickCount()
				&& !currentWorldPoint.equals(lastPoint))
			{
				removeGameTimer(freezeTimer.getTimer());
				freezeTimer = null;
			}
		}

		lastPoint = currentWorldPoint;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		switch (gameStateChanged.getGameState())
		{
			case LOADING:
				if (!isInNightmareZone())
				{
					removeGameTimer(OVERLOAD);
				}

				if (tzhaarTimer != null && !isInFightCaves() && !isInInferno())
				{
					removeTzhaarTimer();
					config.tzhaarStartTime(null);
					config.tzhaarLastTime(null);
				}
				break;
			case LOGIN_SCREEN:
				// pause tzhaar timer if logged out without pausing
				if (config.tzhaarStartTime() != null && config.tzhaarLastTime() == null)
				{
					config.tzhaarLastTime(Instant.now());
					log.debug("Pausing tzhaar timer");
				}

				removeTzhaarTimer(); // will be readded by the wave message
				removeGameTimer(TELEBLOCK);
				break;
		}
	}


	@Subscribe
	public void onAnimationChanged(AnimationChanged event)
	{
		Entity actor = event.getActor();

		if (actor != client.getLocalPlayer())
		{
			return;
		}

		if (config.showDFSSpecial() && lastAnimation == AnimationID.DRAGONFIRE_SHIELD_SPECIAL)
		{
			createGameTimer(DRAGON_FIRE_SHIELD);
		}

		lastAnimation = client.getLocalPlayer().anim;
	}

	@Subscribe
	public void onGraphicChanged(GraphicChanged event)
	{
		Entity actor = event.getActor();

		if (actor != client.getLocalPlayer())
		{
			return;
		}

		if (config.showFreezes())
		{
			if (actor.getGraphic() == BIND.getGraphicId())
			{
				createGameTimer(BIND);
			}

			if (actor.getGraphic() == SNARE.getGraphicId())
			{
				createGameTimer(SNARE);
			}

			if (actor.getGraphic() == ENTANGLE.getGraphicId())
			{
				createGameTimer(ENTANGLE);
			}

			// downgrade freeze based on graphic, if at the same tick as the freeze message
			if (freezeTime == client.getTickCount())
			{
				if (actor.getGraphic() == ICERUSH.getGraphicId())
				{
					removeGameTimer(ICEBARRAGE);
					freezeTimer = createGameTimer(ICERUSH);
				}

				if (actor.getGraphic() == ICEBURST.getGraphicId())
				{
					removeGameTimer(ICEBARRAGE);
					freezeTimer = createGameTimer(ICEBURST);
				}

				if (actor.getGraphic() == ICEBLITZ.getGraphicId())
				{
					removeGameTimer(ICEBARRAGE);
					freezeTimer = createGameTimer(ICEBLITZ);
				}
			}
		}
	}

	/**
	 * Remove SOTD timer and update stamina timer when equipment is changed.
	 */
	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged itemContainerChanged)
	{
		if (itemContainerChanged.getContainerId() != InventoryID.EQUIPMENT.getId())
		{
			return;
		}

		ItemContainer container = itemContainerChanged.getItemContainer();

		com.simplicity.client.Item weapon = container.getItems()[EquipmentInventorySlot.WEAPON.getSlotIdx()];
		if (weapon == null ||
			(weapon.ID != ItemID.STAFF_OF_THE_DEAD &&
				weapon.ID != ItemID.TOXIC_STAFF_OF_THE_DEAD &&
				weapon.ID != ItemID.STAFF_OF_LIGHT &&
				weapon.ID != ItemID.TOXIC_STAFF_UNCHARGED))
		{
			// remove sotd timer if the staff has been unwielded
			removeGameTimer(STAFF_OF_THE_DEAD);
		}

		if (wasWearingEndurance)
		{
			Item ring = container.getItems()[EquipmentInventorySlot.RING.getSlotIdx()];

			// when using the last ring charge the ring changes to the uncharged version, ignore that and don't
			// halve the timer
			if (ring == null || (ring.ID != ItemID.RING_OF_ENDURANCE && ring.ID != ItemID.RING_OF_ENDURANCE_UNCHARGED_24844))
			{
				wasWearingEndurance = false;
				if (staminaTimer != null)
				{
					// Remaining duration gets divided by 2
					Duration remainingDuration = Duration.between(Instant.now(), staminaTimer.getEndTime()).dividedBy(2);
					// This relies on the chat message to be removed, which could be after the timer has been culled;
					// so check there is still remaining time
					if (!remainingDuration.isNegative() && !remainingDuration.isZero())
					{
						log.debug("Halving stamina timer");
						staminaTimer.setDuration(remainingDuration);
					}
				}
			}
		}
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned npcDespawned)
	{
		NPC npc = npcDespawned.getNpc();

		if (!npc.isDead())
		{
			return;
		}

		int npcId = npc.getId();

		if (npcId == NpcID.ZOMBIFIED_SPAWN || npcId == NpcID.ZOMBIFIED_SPAWN_8063)
		{
			removeGameTimer(ICEBARRAGE);
		}
	}

	@Subscribe
	public void onActorDeath(ActorDeath actorDeath)
	{
		if (actorDeath.getActor() == client.getLocalPlayer())
		{
			infoBoxManager.removeIf(t -> t instanceof TimerTimer && ((TimerTimer) t).getTimer().isRemovedOnDeath());
		}
	}

	private void createStaminaTimer()
	{
		Duration duration = Duration.ofMinutes(wasWearingEndurance ? 4 : 2);
		staminaTimer = createGameTimer(STAMINA, duration);
	}

	private TimerTimer createGameTimer(final GameTimer timer)
	{
		if (timer.getDuration() == null)
		{
			throw new IllegalArgumentException("Timer with no duration");
		}
		return createGameTimer(timer, timer.getDuration());
	}
	private EffectTime createCustomEffectTimer(EffectTimer.EffectType effect, int seconds) {
		if(seconds <= 0) {
			removeEffectTimer(effect);
			return null;
		}
		removeEffectTimer(effect);
		EffectTime t = new EffectTime(effect, Duration.ofSeconds(seconds), this);
		if(effect.isItem()) {
			t.setImage(itemManager.getImage(effect.getSprite()));
		} else {
			t.setImage(spriteManager.getSprite(1, effect.getSprite()));
		}
		t.setTooltip(StringUtils.capitalizeFirst(effect.name()));
		infoBoxManager.addInfoBox(t);
		return t;
	}
	private TimerTimer createGameTimer(final GameTimer timer, Duration duration)
	{
		removeGameTimer(timer);

		TimerTimer t = new TimerTimer(timer, duration, this);
		switch (timer.getImageType())
		{
			case SPRITE:
				t.setImage(spriteManager.getSprite(1, timer.getImageId()));
				break;
			case ITEM:
				t.setImage(itemManager.getImage(timer.getImageId()));
				break;
		}
		t.setTooltip(timer.getDescription());
		infoBoxManager.addInfoBox(t);
		return t;
	}

	private void removeGameTimer(GameTimer timer)
	{
		infoBoxManager.removeIf(t -> t instanceof TimerTimer && ((TimerTimer) t).getTimer() == timer);
	}
	private void removeEffectTimer(EffectTimer.EffectType effectTimer) {
		infoBoxManager.removeIf(t -> t instanceof EffectTime && ((EffectTime) t).getTimer() == effectTimer);
	}

	private IndicatorIndicator createGameIndicator(GameIndicator gameIndicator)
	{
		removeGameIndicator(gameIndicator);

		IndicatorIndicator indicator = new IndicatorIndicator(gameIndicator, this);
		switch (gameIndicator.getImageType())
		{
			case SPRITE:
				indicator.setImage(spriteManager.getSprite(1, gameIndicator.getImageId()));
				break;
			case ITEM:
				indicator.setImage(itemManager.getImage(gameIndicator.getImageId()));
				break;
		}
		indicator.setTooltip(gameIndicator.getDescription());
		infoBoxManager.addInfoBox(indicator);

		return indicator;
	}

	private void removeGameIndicator(GameIndicator indicator)
	{
		infoBoxManager.removeIf(t -> t instanceof IndicatorIndicator && ((IndicatorIndicator) t).getIndicator() == indicator);
	}
}
