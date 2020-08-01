package net.runelite.client.plugins.screenshot;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

import javax.imageio.ImageIO;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;

import net.runelite.client.RuneLite;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.DrawManager;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.TitleToolbar;

@PluginDescriptor(
		name = "Screenshot",
		description = "Enable the manual taking of screenshots",
		tags = {"external", "images", "intergration"},
		loadWhenOutdated = true
	)
public class ScreenshotPlugin extends Plugin {
	
	private static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	
	static String format(Date date) {
		synchronized (TIME_FORMAT) {
			return TIME_FORMAT.format(date);
		}
	}
	
	@Inject
	TitleToolbar toolBar;
	
	@Inject
	ScheduledExecutorService executor;
	
	@Inject
	DrawManager drawManager;
	
	private NavigationButton screenshotButton;
	
	@Override
	protected void startUp() throws Exception
	{
		
		RuneLite.SCREENSHOT_DIR.mkdirs();
		
		BufferedImage icon;
		synchronized (ImageIO.class)
		{
			icon = ImageIO.read(getClass().getResourceAsStream("screenshot.png"));
		}
		
		screenshotButton = NavigationButton.builder().tooltip("Take screenshot").icon(icon).onClick(() -> takeScreenshot(format(new Date()))).popup(ImmutableMap.<String, Runnable>builder().put("Open screenshot folder...", () -> {
			try {
				Desktop.getDesktop().open(RuneLite.SCREENSHOT_DIR);
			} catch (IOException ex) {
				
			}
		}).build()).build();
		toolBar.addNavigation(screenshotButton);
	}
	
	@Override
	protected void shutDown() throws Exception
	{
		toolBar.removeNavigation(screenshotButton);
	}
	
	private void takeScreenshot(String fileName) {
		try {
			ClientUI clientUI = RuneLite.getInjector().getInstance(RuneLite.class).clientUI;
			Robot robot = new Robot(clientUI.getGraphicsConfiguration().getDevice());
			Rectangle captureSize = new Rectangle(clientUI.frame.getX(), clientUI.frame.getY(), clientUI.getWidth(), clientUI.getHeight());
			BufferedImage bufferedimage = robot.createScreenCapture(captureSize);
			File playerFolderFile = new File(RuneLite.SCREENSHOT_DIR, com.simplicity.client.Client.instance.myUsername);
			playerFolderFile.mkdirs();
			File screenshotfile = new File(playerFolderFile, fileName + ".png");
			ImageIO.write(bufferedimage, "PNG", screenshotfile);
		} catch (AWTException | IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
