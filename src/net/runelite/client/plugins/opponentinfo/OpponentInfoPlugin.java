/*
 * Copyright (c) 2016-2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2018, Jordan Atwood <jordan.atwood423@gmail.com>
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
package net.runelite.client.plugins.opponentinfo;

import java.time.Duration;
import java.time.Instant;

import javax.inject.Inject;

import com.google.inject.Provides;
import com.simplicity.client.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.InteractingChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.http.api.hiscore.HiscoreEndpoint;

@PluginDescriptor(
	name = "Opponent Information",
	description = "Show name and hitpoints information about the NPC you are fighting",
	tags = {"combat", "health", "hitpoints", "npcs", "overlay"}
)
public class OpponentInfoPlugin extends Plugin
{
	private static final Duration WAIT = Duration.ofSeconds(5);
	
	@Inject
	private Client client;

	@Inject
	private OpponentInfoConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private OpponentInfoOverlay opponentInfoOverlay;

	@Getter(AccessLevel.PACKAGE)
	private HiscoreEndpoint hiscoreEndpoint = HiscoreEndpoint.NORMAL;

	@Getter(AccessLevel.PACKAGE)
	private Entity lastOpponent;

	private Instant lastTime;

	@Provides
	OpponentInfoConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(OpponentInfoConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(opponentInfoOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		lastOpponent = null;
		lastTime = null;
		overlayManager.remove(opponentInfoOverlay);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}
	}

	@Subscribe
	public void onInteractingChanged(InteractingChanged event)
	{
		if (event.getSource() != client.getLocalPlayer())
		{
			return;
		}

		Entity opponent = event.getTarget();

		if (opponent == null)
		{
			lastTime = Instant.now();
			return;
		}

		lastOpponent = opponent;
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		if (lastOpponent != null
			&& lastTime != null
			&& client.getLocalPlayer().interactingEntity == -1)
		{
			if (Duration.between(lastTime, Instant.now()).compareTo(WAIT) > 0)
			{
				lastOpponent = null;
			}
		}
	}

}
