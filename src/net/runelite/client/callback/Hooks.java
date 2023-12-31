/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package net.runelite.client.callback;

import static net.runelite.api.widgets.WidgetInfo.WORLD_MAP_VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.Injector;
import com.simplicity.client.DrawingArea;
import com.simplicity.client.RSImageProducer;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.GameTick;
import net.runelite.api.hooks.Callbacks;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.Notifier;
import net.runelite.client.RuneLite;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.input.KeyManager;
import net.runelite.client.input.MouseManager;
import net.runelite.client.task.Scheduler;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.DrawManager;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayRenderer;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.DeferredEventBus;

/**
 * This class contains field required for mixins and runelite hooks to work.
 * All remaining method hooks in this class are performance-critical or contain client-specific logic and so they
 * can't just be placed in mixins or sent through event bus.
 */
@Singleton
@Slf4j
public class Hooks implements Callbacks
{
	private static final long CHECK = 600; // ms - how often to run checks

	private static final Injector injector = RuneLite.getInjector();
	
	private static final OverlayRenderer renderer = injector.getInstance(OverlayRenderer.class);
	
	static com.simplicity.client.Client client() {
		return com.simplicity.client.Client.instance;
	}

	private static final ClientTick CLIENT_TICK = new ClientTick();
	private static final GameTick GAME_TICK = new GameTick();
	
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;
	@Inject
	private EventBus eventBus;

	@Inject
	private DeferredEventBus deferredEventBus;

	@Inject
	private Scheduler scheduler;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private MouseManager mouseManager;

	@Inject
	private KeyManager keyManager;

	@Inject
	private ClientThread clientThread;

	@Inject
	private DrawManager drawManager;

	@Inject
	private Notifier notifier;

	@Inject
	private ClientUI clientUi;

	private Dimension lastStretchedDimensions;
	private VolatileImage stretchedImage;
	private Graphics2D stretchedGraphics;

	private long lastCheck;

	@Override
	public void post(Object event)
	{
		eventBus.post(event);
	}

	@Override
	public void postDeferred(Object event)
	{
		deferredEventBus.post(event);
	}
	@Override
	public void drawItem(int itemId, WidgetItem widgetItem)
	{
		overlayManager.getItemWidgets().add(widgetItem);

	}
	@Override
	public void clientMainLoop()
	{
		post(CLIENT_TICK);
		clientThread.invoke();

		long now = System.currentTimeMillis();

		if (now - lastCheck < CHECK)
		{
			return;
		}

		lastCheck = now;

		try
		{
			// tick pending scheduled tasks
			scheduler.tick();

			// cull infoboxes
			infoBoxManager.cull();

			chatMessageManager.process();

			//checkWorldMap();
		}
		catch (Exception ex)
		{
			log.warn("error during main loop tasks", ex);
		}
	}

	/**
	 * When the world map opens it loads about ~100mb of data into memory, which
	 * represents about half of the total memory allocated by the client.
	 * This gets cached and never released, which causes GC pressure which can affect
	 * performance. This method reinitailzies the world map cache, which allows the
	 * data to be garbage collecged, and causes the map data from disk each time
	 * is it opened.
	 */
	private void checkWorldMap()
	{
		Widget widget = client.getWidget(WORLD_MAP_VIEW);

		if (widget != null)
		{
			return;
		}

		RenderOverview renderOverview = client.getRenderOverview();

		if (renderOverview == null)
		{
			return;
		}

		WorldMapManager manager = renderOverview.getWorldMapManager();

		if (manager != null && manager.isLoaded())
		{
			log.debug("World map was closed, reinitializing");
			renderOverview.initializeWorldMap(renderOverview.getWorldMapData());
		}
	}

	@Override
	public MouseEvent mousePressed(MouseEvent mouseEvent)
	{
		
		return mouseManager.processMousePressed(mouseEvent);
	}

	@Override
	public MouseEvent mouseReleased(MouseEvent mouseEvent)
	{
		return mouseManager.processMouseReleased(mouseEvent);
	}

	@Override
	public MouseEvent mouseClicked(MouseEvent mouseEvent)
	{
		return mouseManager.processMouseClicked(mouseEvent);
	}

	@Override
	public MouseEvent mouseEntered(MouseEvent mouseEvent)
	{
		return mouseManager.processMouseEntered(mouseEvent);
	}

	@Override
	public MouseEvent mouseExited(MouseEvent mouseEvent)
	{
		return mouseManager.processMouseExited(mouseEvent);
	}

	@Override
	public MouseEvent mouseDragged(MouseEvent mouseEvent)
	{
		return mouseManager.processMouseDragged(mouseEvent);
	}

	@Override
	public MouseEvent mouseMoved(MouseEvent mouseEvent)
	{
		return mouseManager.processMouseMoved(mouseEvent);
	}

	@Override
	public MouseWheelEvent mouseWheelMoved(MouseWheelEvent event)
	{
		return mouseManager.processMouseWheelMoved(event);
	}

	@Override
	public void keyPressed(KeyEvent keyEvent)
	{
		keyManager.processKeyPressed(keyEvent);
	}

	@Override
	public void keyReleased(KeyEvent keyEvent)
	{
		keyManager.processKeyReleased(keyEvent);
	}

	@Override
	public void keyTyped(KeyEvent keyEvent)
	{
		keyManager.processKeyTyped(keyEvent);
	}

	@Override
	public void draw(RSImageProducer mainBufferProvider, Graphics graphics, int x, int y, GraphicsBufferType graphicsBufferType)
	{
		if (graphics == null)
		{
			return;
		}
		
		Image image = mainBufferProvider.image;
		final Image finalImage;
		final Graphics2D graphics2d = (Graphics2D) image.getGraphics();

		try
		{
			renderer.render(graphics2d, OverlayLayer.ALWAYS_ON_TOP, graphicsBufferType);
		}
		catch (Exception ex)
		{
			log.warn("Error during overlay rendering", ex);
		}

		notifier.processFlash(graphics2d);
		
		// Stretch the game image if the user has that enabled
		if (!client.isResized() && client.isStretchedEnabled())
		{
			GraphicsConfiguration gc = clientUi.getGraphicsConfiguration();
			Dimension stretchedDimensions = client.getStretchedDimensions();

			if (lastStretchedDimensions == null || !lastStretchedDimensions.equals(stretchedDimensions)
				|| (stretchedImage != null && stretchedImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE))
			{
				/*
					Reuse the resulting image instance to avoid creating an extreme amount of objects
				 */
				stretchedImage = gc.createCompatibleVolatileImage(stretchedDimensions.width, stretchedDimensions.height);

				if (stretchedGraphics != null)
				{
					stretchedGraphics.dispose();
				}
				stretchedGraphics = (Graphics2D) stretchedImage.getGraphics();

				lastStretchedDimensions = stretchedDimensions;

				/*
					Fill Canvas before drawing stretched image to prevent artifacts.
				*/
				graphics.setColor(Color.BLACK);
				graphics.fillRect(0, 0, client.getCanvas().getWidth(), client.getCanvas().getHeight());
			}

			stretchedGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				client.isStretchedFast()
					? RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
					: RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			stretchedGraphics.drawImage(image, 0, 0, stretchedDimensions.width, stretchedDimensions.height, null);

			finalImage = stretchedImage;
		} else {
			finalImage = image;
		}
		
		// Draw the image onto the game canvas
		graphics.drawImage(finalImage, 0, 0, mainBufferProvider.component);
		
		drawManager.processDrawComplete(() -> copy(finalImage));
	}
	
	/**
	 * Copy an image
	 * @param src
	 * @return
	 */
	private static Image copy(Image src)
	{
		final int width = src.getWidth(null);
		final int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.drawImage(src, 0, 0, width, height, null);
		graphics.dispose();
		return image;
	}


	@Override
	public void drawScene(GraphicsBufferType graphicsBufferType)
	{
		RSImageProducer bufferProvider = client().getGameScreenIP();
		BufferedImage image = bufferProvider.image;
		Graphics2D graphics2d = image.createGraphics();

		try
		{
			renderer.render(graphics2d, OverlayLayer.ABOVE_SCENE, graphicsBufferType);
		}
		catch (Exception ex)
		{
			log.warn("Error during overlay rendering", ex);
		}
		finally
		{
			graphics2d.dispose();
		}
	}

	@Override
	public void drawAboveOverheads(GraphicsBufferType graphicsBufferType)
	{
		RSImageProducer bufferProvider = client().getGameScreenIP();
		BufferedImage image = bufferProvider.image;
		Graphics2D graphics2d = image.createGraphics();

		try
		{
			renderer.render(graphics2d, OverlayLayer.UNDER_WIDGETS, graphicsBufferType);
		}
		catch (Exception ex)
		{
			log.warn("Error during overlay rendering", ex);
		}
		finally
		{
			graphics2d.dispose();
		}
	}

	public static void drawAfterWidgets(RSImageProducer bufferProvider, GraphicsBufferType graphicsBufferType)
	{
		BufferedImage image = bufferProvider.image;
		
		Graphics2D graphics2d = image.createGraphics();

		try
		{
			renderer.render(graphics2d, OverlayLayer.ABOVE_WIDGETS, graphicsBufferType);
		}
		catch (Exception ex)
		{
			log.warn("Error during overlay rendering", ex);
		}
		finally
		{
			graphics2d.dispose();
		}
		RuneLite.getInjector().getInstance(OverlayManager.class).getItemWidgets().clear();
	}
	
	public static void drawAfterTabArea(RSImageProducer bufferProvider, GraphicsBufferType graphicsBufferType)
	{
		BufferedImage image = bufferProvider.image;
		
		Graphics2D graphics2d = image.createGraphics();

		try
		{
			renderer.render(graphics2d, OverlayLayer.TAB_AREA, graphicsBufferType);
		}
		catch (Exception ex)
		{
			log.warn("Error during overlay rendering", ex);
		}
		finally
		{
			graphics2d.dispose();
		}
	}
	
	@Override
	public void onGameTick() {
		eventBus.post(GAME_TICK);
	}

}
