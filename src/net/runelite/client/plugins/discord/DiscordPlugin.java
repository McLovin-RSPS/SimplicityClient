package net.runelite.client.plugins.discord;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import net.runelite.client.eventbus.Subscribe;
import com.google.inject.Inject;

import net.runelite.api.events.GameTick;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientTitleToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.TitleToolbar;
import net.runelite.client.util.LinkBrowser;

@PluginDescriptor(
		name = "Discord",
		description = "Join Discord",
		tags = {"discord"},
		loadWhenOutdated = true
	)
public class DiscordPlugin extends Plugin {
	
	@Inject
	TitleToolbar toolBar;
	
	private NavigationButton discordButton;
	
	@Override
	protected void startUp() throws Exception
	{
		
		BufferedImage icon;
		synchronized (ImageIO.class)
		{
			icon = ImageIO.read(getClass().getResourceAsStream("discord.png"));
		}
		
		discordButton = NavigationButton.builder().tooltip("Join Discord").icon(icon).onClick(() -> LinkBrowser.browse("https://discord.gg/u2BPNYw")).build();
		toolBar.addNavigation(discordButton);
	}
	
	@Override
	protected void shutDown() throws Exception
	{
		toolBar.removeNavigation(discordButton);
	}
}
