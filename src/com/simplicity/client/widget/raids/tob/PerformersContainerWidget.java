package com.simplicity.client.widget.raids.tob;

import static com.simplicity.client.RSInterface.interfaceCache;
import static com.simplicity.client.widget.raids.tob.TheatrePerformersWidget.CENTER_STRING_ID;

import java.util.HashSet;
import java.util.Set;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

/**
 * A class that handles the container for the {@link TheatrePerformersWidget}.
 * 
 * @author Blake
 *
 */
public class PerformersContainerWidget extends CustomWidget {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 76_100;
	
	/**
	 * The rectangle height.
	 */
	private static final int RECT_HEIGHT = 18;
	
	/**
	 * The width of the container.
	 */
	private static final int WIDTH = 380;
	
	/**
	 * The height of the container.
	 */
	private static final int HEIGHT = 227;
	
	/**
	 * The start id.
	 */
	private static int START_ID;
	
	/**
	 * The ids of the names.
	 */
	public static final Set<Integer> ids = new HashSet<>();
	
	/**
	 * Constructs a new {@link PerformersContainerWidget}.
	 */
	public PerformersContainerWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Performers container widget";
	}
	
	/**
	 * The size of the container.
	 */
	public static final int ROWS = 20;
	
	@Override
	public void onStringUpdate(int id, String str) {
		if (!ids.contains(id)) {
			return;
		}
		
		int index = id - START_ID;
		
		if (index > 0) {
			index %= 5;
		}
		
		RSInterface rect = interfaceCache[id - 3];
		
		if (!str.isEmpty()) {
			rect.actions = new String[] { "View party: @lre@" + str };
		}
		
		rect.hidden = str.isEmpty();
		
		if (rect.hidden) {
			boolean visible = false;
			
			for (int i : ids) {
				if (!interfaceCache[i - 3].hidden) {
					visible = true;
					break;
				}
			}
			
			interfaceCache[CENTER_STRING_ID].hidden = visible;
			
			if (!visible) {
				getInterface().scrollMax = HEIGHT + 1;
			}
		}
	}
	
	@Override
	public void init() {
		getInterface().width = WIDTH;
		getInterface().height = HEIGHT;
		getInterface().scrollMax = Math.max(HEIGHT + 1, ROWS * (RECT_HEIGHT + 1));
		
		int x = 0;
		int y = 0;
		
		START_ID = id;
		
		for (int i = 0; i < ROWS; i++) {
			RSInterface rect = addRectangleClickable(255, 0, true, WIDTH - 1, RECT_HEIGHT, new String[] { "View party: @lre@Blake" });
			rect.hovers = true;
    		rect.hoverType = id - 1;
    		rect.enabledOpacity = 220;
    		rect.enabledMouseOverColor = 0xffffff;
    		rect.hidden = true;
    		add(rect, x, y);
    		add(addCenteredText("", 1, 0xff981f), 18, 1 + y);
    		add(addCenteredText("", 1, 0xff981f), 18 + 42, 1 + y);
    		ids.add(id);
    		add(addCenteredText("", 1, 0xffffff), 18 + 137, 1 + y);
    		add(addCenteredText("", 1, 0x9f9f9f), 18 + 138 + 94, 1 + y);
    		add(addCenteredText("", 0, 0x9f9f9f), 18 + 139 + 94 + 77, 3 + y);
			y += RECT_HEIGHT + 1;
		}
	}
	
}
