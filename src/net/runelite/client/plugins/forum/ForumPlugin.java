package net.runelite.client.plugins.forum;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.google.inject.Inject;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.TitleToolbar;
import net.runelite.client.util.LinkBrowser;

@PluginDescriptor(
		name = "Forums",
		description = "Join The Forums",
		tags = {"forum", "forums"},
		loadWhenOutdated = true
	)
public class ForumPlugin extends Plugin {
	
	@Inject
	TitleToolbar toolBar;
	
	private NavigationButton forumButton;
	
	@Override
	protected void startUp() throws Exception
	{
		
		BufferedImage icon;
		synchronized (ImageIO.class)
		{
			icon = ImageIO.read(getClass().getResourceAsStream("forum.png"));
		}
		
		forumButton = NavigationButton.builder().tooltip("Join The Forums").icon(icon).onClick(() -> LinkBrowser.browse("https://simplicityps.org/community")).build();
		toolBar.addNavigation(forumButton);
	}
	
	@Override
	protected void shutDown() throws Exception
	{
		toolBar.removeNavigation(forumButton);
	}

}
