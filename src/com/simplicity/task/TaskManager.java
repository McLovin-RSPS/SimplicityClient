package com.simplicity.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.simplicity.client.Client;

/**
 * A class that handles and schedules a {@link Task}.
 * 
 * @author Blake
 *
 */
public class TaskManager {

	/**
	 * The logic service used to schedule the tasks.
	 */
	private final static ScheduledExecutorService logicService = Executors.newScheduledThreadPool(1);

	/**
	 * Submits a new task.
	 *
	 * @param task The task to submit.
	 */
	public static void submit(final Task task) {
		logicService.schedule(new Runnable() {
			public void run() {
				long start = System.currentTimeMillis();

				if (!task.isRunning()) {
					return;
				}

				try {
					if (!Client.loggedIn) {
						task.stop();
						return;
					}
					
					task.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}

				long elapsed = System.currentTimeMillis() - start;
				long remaining = task.getDelay() - elapsed;

				if (remaining <= 0) {
					remaining = 0;
				}

				submit(task);
			}
		}, task.getDelay(), task.getUnit());
	}

}
