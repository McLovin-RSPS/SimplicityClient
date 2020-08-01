package net.runelite.client.content.gametimers;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import com.simplicity.client.DrawingArea;
import com.simplicity.client.Sprite;

/**
 * https://rune-server.ee/_Ali
 * 
 * @author Justin?? HAHA MY ASS?! You sleep bitch.
 * @author Ali (Sinans Husband)
 *
 * Created using Eclipse: 01.05.2019 23:40pm
 */
public class GameTimerManager {
	
	/**
	 * Determines the maximum amount of timers.
	 */
	public static final int TIMER_AMOUNT = 8;
	
	/**
	 * {@link Path} to the images location.
	 */
	public static final String SPRITE_LOCATION = "Gametimers";
	
	/**
	 * Formats the time to minutes and seconds.
	 */
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("mm:ss");
	
	/**
	 * Stack of {@link Sprite}s being used for the timers.
	 */
	public Sprite[] gameTimerSprites = new Sprite[TIMER_AMOUNT];
	
	/**
	 * The Queue that holds the processed game timers.
	 */
	private Queue<GameTimer> gameTimers = new LinkedList<GameTimer>();
	
	/**
	 * A single instance of the {@link GameTimerManager} class.
	 */
	private final static GameTimerManager SINGLETON = new GameTimerManager();
	
	/**
	 * Adds a {@link GameTimer} to the game timer queue.
	 * @param timerId	The identification of the timer.
	 * @param duration	The duration of the timer in seconds.
	 */
	public void addGameTimer(int timerId, int duration) {
		if(timerId < 0 || timerId >= TIMER_AMOUNT) {
			System.out.println("[GameTimer] Incorrect timerId");
			return;
		}
		GameTimer timerToAdd = new GameTimer(timerId, duration);
		for(GameTimer gameTimer : gameTimers) {
			if(gameTimer == null) {
				continue;
			}
			if(gameTimer.getId() == timerId) {
				gameTimers.remove(gameTimer);
				break;
			}
		}
		System.out.println("Gametimer added!");
		gameTimers.add(timerToAdd);
	}

	/**
	 * Processes and draws the timers.
	 * @param client	The client that is drawing the game timers.
	 * @param xPosition	The x starting location on screen.
	 * @param yPosition	The y starting location on screen.
	 */
	public void processGameTimers(final com.simplicity.client.Client client, int xPosition, int yPosition) {
		/*if(!client.showGameTimers) {
			return;
		}*/ //TODO:
		if(gameTimers.isEmpty()) {
			return;
		}
		xPosition -= 32;
		yPosition -= 32;
		Collection<GameTimer> toRemove = new ArrayList<GameTimer>();
		for(GameTimer gameTimer : gameTimers) {
			if(gameTimer == null) {
				continue;
			}
			if(gameTimer.isStopped()) {
				continue;
			}
			if(gameTimer.isCompleted()) {
				gameTimer.stop();
				toRemove.add(gameTimer);
				continue;
			}
			DrawingArea.fillPixels(xPosition, 37, 39, 0x00000, yPosition);
			//DrawingArea.drawRectangle(xPosition, yPosition, 37, 39, 0x00000);
			//DrawingArea.fillRectangle(xPosition, yPosition, 37, 39, 0x330800);
			gameTimer.getSprite().drawCenteredARGBImage(xPosition + 18, yPosition + 16);
			client.newSmallFont.drawCenteredString(FORMAT.format(gameTimer.getTimeRemaining() * 1000), xPosition + 19, yPosition + 37, 0xFFFFFF, 0x00000);
			xPosition -= 40;
		}
		gameTimers.removeAll(toRemove);
	}
	
	/**
	 * Clears all the timers on the {@code gameTimers} queue.
	 */
	public void resetAllTimers() {
		gameTimers.clear();
	}
	
	/**
	 * Gets the Singleton of the {@link GameTimerManager} class.
	 * @return The Singleton of the {@link GameTimerManager} class.
	 */
	public static GameTimerManager getSingleton() {
		return SINGLETON;
	}

}
