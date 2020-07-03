package com.simplicity.client.widget.raids.tob;

import static com.simplicity.client.RSInterface.interfaceCache;
import static com.simplicity.client.widget.raids.tob.TheatrePerformersWidget.CENTER_STRING_ID;

import java.util.HashSet;
import java.util.Set;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.Widget;
import com.simplicity.client.widget.listener.WidgetStringListener;

/**
 * A class that handles the container for the {@link TheatrePartyWidget}.
 * 
 * @author Blake
 *
 */
public class PartyContainerWidget extends CustomWidget implements WidgetStringListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 76_450;
	
	/**
	 * The rectangle height.
	 */
	private static final int RECT_HEIGHT = 13;
	
	/**
	 * The width of the container.
	 */
	private static final int WIDTH = 476;
	
	/**
	 * The height of the container.
	 */
	private static final int HEIGHT = 65;
	
	/**
	 * The start id.
	 */
	private static int START_ID;
	
	/**
	 * The ids of the names.
	 */
	public static final Set<Integer> ids = new HashSet<>();
	
	/**
	 * Constructs a new {@link PartyContainerWidget}.
	 */
	public PartyContainerWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Party container widget";
	}
	
	/**
	 * The size of the container.
	 */
	public static final int ROWS = 5;
	
	@Override
	public void init() {
		addStringListener(this);
		getInterface().width = WIDTH;
		getInterface().height = HEIGHT;
		
		int x = 0;
		int y = 0;
		
		START_ID = id;
		
		System.out.println("party cont start: " + id);
		
		for (int i = 0; i < ROWS; i++) {
			boolean odd = i % 2 == 0;
			RSInterface rect = addRectangleClickable(odd ? 235 : 255, odd ? 0 : 0x534a3f, true, WIDTH, RECT_HEIGHT, new String[2]);
			rect.hovers = false;
    		rect.hoverType = id - 1;
    		rect.enabledOpacity = odd ? 225 : 225;
    		rect.enabledMouseOverColor = odd ? 0 : 0x534a3f;
    		add(rect, x, y);
    		ids.add(id);
    		add(addCenteredText("-", 0, 0x9f9f9f), 5 + 80, 1 + y);
    		add(addCenteredText("-", 0, 0x9f9f9f), 11 + 167, 1 + y);
    		add(addCenteredText("-", 0, 0x9f9f9f), 11 + 200, 1 + y);
    		add(addCenteredText("-", 0, 0x9f9f9f), 11 + 225, 1 + y);
    		add(addCenteredText("-", 0, 0x9f9f9f), 11 + 250, 1 + y);
    		add(addCenteredText("-", 0, 0x9f9f9f), 11 + 275, 1 + y);
    		add(addCenteredText("-", 0, 0x9f9f9f), 11 + 300, 1 + y);
    		add(addCenteredText("-", 0, 0x9f9f9f), 11 + 325, 1 + y);
    		add(addCenteredText("-", 0, 0x9f9f9f), 11 + 350, 1 + y);
    		add(addCenteredText("-", 0, 0x9f9f9f), 375 + 50, 1 + y);
			y += RECT_HEIGHT;
		}
	}

	@Override
	public void onStringUpdate(int id, String string) {
		if (!ids.contains(id)) {
			return;
		}
		
		int index = id - START_ID;
		
		if (index > 0) {
			index %= 5;
		}
		
		RSInterface rect = interfaceCache[id - 1];
		
		if (string.isEmpty()) {
			rect.actions = null;
		} else if (string.contains("@whi@")) {
			rect.actions = new String[] { "Quit party" };
		}
		rect.hovers = !string.isEmpty();
	}
	
}
