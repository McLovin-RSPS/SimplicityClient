package net.runelite.client.plugins.donate;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.google.inject.Inject;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.TitleToolbar;
import net.runelite.client.util.LinkBrowser;

@PluginDescriptor(
		name = "Store",
		description = "Open Store",
		tags = {"store"},
		loadWhenOutdated = true
	)
public class DonatePlugin extends Plugin {
	
	@Inject
	TitleToolbar toolBar;
	
	private NavigationButton donateButton;
	
	@Override
	protected void startUp() throws Exception
	{
		
		BufferedImage icon;
		synchronized (ImageIO.class)
		{
			icon = ImageIO.read(getClass().getResourceAsStream("donate.png"));
		}
		
		donateButton = NavigationButton.builder().tooltip("Open Store").icon(icon).onClick(() -> LinkBrowser.browse("https://simplicityps.org/store/")).build();
		toolBar.addNavigation(donateButton);
	}
	
	@Override
	protected void shutDown() throws Exception
	{
		toolBar.removeNavigation(donateButton);
	}

}
