package com.simplicity.client.widget.raids.cox;

import java.io.IOException;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.task.Task;

public class RaidButtonTask extends Task {
	
	private RSInterface button;
	private RSInterface text;
	private String cached;
	private int count = 0;
	private boolean resetButton;

	public RaidButtonTask(RSInterface button, long delay) {
		this(button, delay, true);
	}
	
	public RaidButtonTask(RSInterface button, long delay, boolean resetButton) {
		super(delay);
		this.button = button;
		this.text = RSInterface.interfaceCache[button.id + 1];
		this.resetButton = resetButton;
		cached = new String(text.message);
		text.message = "...";
		button.buttonDown = true;
	}

	@Override
	public void execute() throws IOException {
		if (count == 0) {
			Client.instance.sendButtonClick(button.id);
		} else if (count == 1) {
			if (resetButton) {
				button.buttonDown = false;
				
				if (text.message.equals("...")) {
					text.message = cached;
				}
			}
			
			clicked();
		} else if (count == 2) {
			stop();						
		}
		
		count++;
	}
	
	public void clicked() {
		
	}

}
