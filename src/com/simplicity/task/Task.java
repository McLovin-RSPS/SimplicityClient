package com.simplicity.task;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * A class that represents a Task.
 * 
 * @author Blake
 *
 */
public abstract class Task {

	/**
	 * The delay.
	 */
	private long delay;

	/**
	 * The time unit.
	 */
	private TimeUnit unit;

	/**
	 * The running flag.
	 */
	private boolean running = true;

	/**
	 * Constructs a new {@link Task} with the specified delay in milliseconds.
	 *
	 * @param delay The delay.
	 */
	public Task(long delay) {
		this(delay, TimeUnit.MILLISECONDS);
	}

	/**
	 * Constructs a new {@link Task} with the specified delay and time unit.
	 * 
	 * @param delay The delay.
	 * @param unit  The time unit.
	 */
	public Task(long delay, TimeUnit unit) {
		this.delay = delay;
		this.unit = unit;
	}

	/**
	 * Gets the event delay.
	 *
	 * @return The delay.
	 */
	public long getDelay() {
		return delay;
	}

	/**
	 * Gets the time unit.
	 * 
	 * @return The time unit.
	 */
	public TimeUnit getUnit() {
		return unit;
	}

	/**
	 * Checks if the task is running.
	 *
	 * @return <code>true</code> if the task is running, <code>false</code> if not.
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * The execute method is called when the task is run.
	 */
	public abstract void execute() throws IOException;

	/**
	 * Stops the task from running.
	 */
	public void stop() {
		running = false;

		onStop();
	}

	/**
	 * Called after this task has been stopped.
	 */
	public void onStop() {

	}

}
