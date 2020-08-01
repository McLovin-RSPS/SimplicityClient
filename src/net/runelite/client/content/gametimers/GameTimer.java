package net.runelite.client.content.gametimers;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import com.simplicity.client.Sprite;

/**
 * https://rune-server.ee/_Ali
 * 
 * @author Justin?? HAHA MY ASS?! You sleep bitch.
 * @author Ali (Sinans Husband)
 *
 * Created using Eclipse: 01.05.2019 23:40pm
 */
public class GameTimer extends StopWatch {
	
	/**
	 * The unique identification number of the timer.
	 */
	private final int id;
	
	/**
	 * The duration of the timer in milliseconds.
	 */
	private final long duration;
	
	/**
	 * The in-game {@link Sprite} shown with the timer.
	 */
	private final Sprite sprite;
	
	/**
	 * Creates a new {@link GameTimer} object.
	 * @param id	Timer id.
	 * @param duration	Duration in seconds.
	 * @param sprite	Sprite.
	 */
	public GameTimer(final int id, final long duration) {
		this.id = id;
		this.duration = TimeUnit.SECONDS.toMillis(duration);
		this.sprite = GameTimerManager.getSingleton().gameTimerSprites[id];
		this.start();
	}
	
	/**
	 * Checks if the timer is completed.
	 * @return {@code true} if the timer is completed, otherwise {@code false}.
	 */
	public boolean isCompleted() {
		return getTime() >= duration && !isStopped();
	}
	
	/**
	 * The amount of seconds remaining on the timer.
	 * @return Time remaining in seconds.
	 */
	public final int getTimeRemaining() {
		long elapsed = getTime();
		if (elapsed > duration) {
			return 0;
		}
		return (int) TimeUnit.MILLISECONDS.toSeconds(duration - elapsed);
	}
	
	/**
	 * Gets the identification number of the timer.
	 * @return The identification number of the timer.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the sprite of the timer.
	 * @return The sprite of the timer.
	 */
	public Sprite getSprite() {
		return sprite;
	}
	
}
