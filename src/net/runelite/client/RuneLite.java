/*
 * Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
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
package net.runelite.client;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Provider;
import javax.inject.Singleton;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.simplicity.Configuration;
import com.simplicity.client.content.RichPresence;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.util.EnumConverter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.callback.Hooks;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.CommandManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.game.ClanManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.LootManager;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.rs.ClientUpdateCheckMode;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.DrawManager;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayRenderer;
import net.runelite.client.ui.overlay.WidgetOverlay;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxOverlay;
import net.runelite.client.ui.overlay.tooltip.TooltipOverlay;
import net.runelite.client.ui.overlay.worldmap.WorldMapOverlay;


@Singleton
@Slf4j
public class RuneLite
{
	public static final File RUNELITE_DIR = new File(System.getProperty("user.home"), ".simplicity");
	public static final File PROFILES_DIR = new File(RUNELITE_DIR, "profiles");
	public static final File SCREENSHOT_DIR = new File(RUNELITE_DIR, "screenshots");
	private static final File LOGS_DIR = new File(RUNELITE_DIR, "logs");
	public static final File LOGS_FILE_NAME = new File(LOGS_DIR, "application");
	public static final RichPresence RICH_PRESENCE = new RichPresence();
	
	@Getter
	private static Injector injector;
	
	@Inject
	public PluginManager pluginManager;

	@Inject
	private MenuManager menuManager;

	@Inject
	private EventBus eventBus;

	@Inject
	private ConfigManager configManager;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private CommandManager commandManager;

	@Inject
	private OverlayRenderer overlayRenderer;

	@Inject
	private DrawManager drawManager;

	@Inject
	private ClientSessionManager clientSessionManager;

	@Inject
	public ClientUI clientUI;

	@Inject
	private Provider<ItemManager> itemManager;

	@Inject
	private ClanManager clanManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private InfoBoxOverlay infoBoxOverlay;

	@Inject
	private TooltipOverlay tooltipOverlay;

	@Inject
	private WorldMapOverlay worldMapOverlay;
	
	@Inject
	private Provider<LootManager> lootManager;
	
	@Inject
	private Provider<Hooks> hooks;

	//@Inject
	//@Nullable
	//private Client client;
	
	@Getter
	private static final Client client = new ClientRuneLite();
	
	public static void main(String[] args) throws Exception
	{
		RICH_PRESENCE.initiate();
        if (args.length > 0) {
            Configuration.HOST = args[0];
            com.simplicity.client.Client.fromLauncher = true;

            if (args.length > 1) {
            	try {
            		com.simplicity.client.Client.fromLauncher = Boolean.parseBoolean(args[1]);
            	} catch (Exception e) {

            	}
            }
        }
        
		Locale.setDefault(Locale.ENGLISH);

		final OptionParser parser = new OptionParser();
		parser.accepts("developer-mode", "Enable developer tools");
		parser.accepts("debug", "Show extra debugging output");

		final ArgumentAcceptingOptionSpec<ClientUpdateCheckMode> updateMode = parser
			.accepts("rs", "Select client type")
			.withRequiredArg()
			.ofType(ClientUpdateCheckMode.class)
			.defaultsTo(ClientUpdateCheckMode.AUTO)
			.withValuesConvertedBy(new EnumConverter<ClientUpdateCheckMode>(ClientUpdateCheckMode.class)
			{
				@Override
				public ClientUpdateCheckMode convert(String v)
				{
					return super.convert(v.toUpperCase());
				}
			});

		parser.accepts("help", "Show this text").forHelp();
		OptionSet options = parser.parse(args);

		if (options.has("help"))
		{
			parser.printHelpOn(System.out);
			System.exit(0);
		}

		final boolean developerMode = options.has("developer-mode");

		if (developerMode && RuneLiteProperties.getLauncherVersion() == null)
		{
			boolean assertions = false;
			assert assertions = true;
			if (!assertions)
			{
				throw new RuntimeException("Developers should enable assertions; Add `-ea` to your JVM arguments`");
			}
		}

		PROFILES_DIR.mkdirs();

		// Setup logger
		MDC.put("logFileName", LOGS_FILE_NAME.getAbsolutePath());

		if (options.has("debug"))
		{
			final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
			logger.setLevel(Level.DEBUG);
		}

		Thread.setDefaultUncaughtExceptionHandler((thread, throwable) ->
		{
			log.error("Uncaught exception:", throwable);
			if (throwable instanceof AbstractMethodError)
			{
				log.error("Classes are out of date; Build with maven again.");
			}
		});

		injector = Guice.createInjector(new RuneLiteModule(
			options.valueOf(updateMode),
			developerMode));
		
		injector.getInstance(RuneLite.class).start();
	}

	public void start() throws Exception
	{
		
		long start = System.currentTimeMillis();
		
		ExecutorService startup = Executors.newCachedThreadPool();
		
		// Load RuneLite or Vanilla client
		final boolean isOutdated = /*client == null*/true;
		
		if (!isOutdated)
		{
			// Inject members into client
			injector.injectMembers(client);
		}

		// Load user configuration
		configManager.load();

		// Tell the plugin manager if client is outdated or not
		//pluginManager.setOutdated(isOutdated);

		// Load the plugins, but does not start them yet.
		// This will initialize configuration

		pluginManager.loadCorePlugins();

		// Plugins have provided their config, so set default config
		// to main settings
		pluginManager.loadDefaultPluginConfiguration();

		// Initialize UI
		clientUI.open(this);
		
		startup.execute(new Runnable() {

			public void run() {
				// Register event listeners
				eventBus.register(clientUI);
				eventBus.register(pluginManager);
				eventBus.register(overlayRenderer);
				eventBus.register(overlayManager);
				eventBus.register(drawManager);
				eventBus.register(menuManager);
				//eventBus.register(chatMessageManager);
				//eventBus.register(commandManager);
				eventBus.register(lootManager.get());
				//eventBus.register(clanManager);
				eventBus.register(infoBoxManager);
				eventBus.register(hooks.get());
			}

		});
		
		startup.execute(new Runnable() {

			public void run() {
				if (!isOutdated)
				{
					eventBus.register(itemManager.get());
					WidgetOverlay.createOverlays(client).forEach(overlayManager::add);
				}
			}

		});
		
		startup.execute(new Runnable() {

			public void run() {
				// Add core overlays after configuration has been loaded so their properties will be
				// loaded properly
				overlayManager.add(infoBoxOverlay);
				overlayManager.add(worldMapOverlay);
				overlayManager.add(tooltipOverlay);
			}

		});

		startup.execute(new Runnable() {

			public void run() {
				// Start plugins
				pluginManager.startCorePlugins(); 
			}

		});
		
		long end = System.currentTimeMillis();
		
		System.out.println("Took: " + (end - start) + " ms.");
	}

	public void shutdown()
	{
		//clientSessionManager.shutdown();
	}
	
	public void post(Object event) {
		eventBus.post(event);
	}
	
	@VisibleForTesting
	public static void setInjector(Injector injector)
	{
		RuneLite.injector = injector;
	}

}
