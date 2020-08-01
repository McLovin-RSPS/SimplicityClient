package net.runelite.client.plugins.vote;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.google.inject.Inject;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.TitleToolbar;
import net.runelite.client.util.LinkBrowser;

@PluginDescriptor(
		name = "Vote",
		description = "Vote",
		tags = {"vote"},
		loadWhenOutdated = true
	)
public class VotePlugin extends Plugin {
	
	@Inject
	TitleToolbar toolBar;
	
	private NavigationButton voteButton;
	
	@Override
	protected void startUp() throws Exception
	{
		
		BufferedImage icon;
		synchronized (ImageIO.class)
		{
			icon = ImageIO.read(getClass().getResourceAsStream("vote.png"));
		}
		
		voteButton = NavigationButton.builder().tooltip("Vote").icon(icon).onClick(() -> LinkBrowser.browse("https://simplicityps.org/vote")).build();
		toolBar.addNavigation(voteButton);
	}
	
	@Override
	protected void shutDown() throws Exception
	{
		toolBar.removeNavigation(voteButton);
	}

}
