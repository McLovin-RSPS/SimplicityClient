package com.simplicity.client.widget.raids.tob;

import static com.simplicity.client.RSInterface.interfaceCache;

import java.util.HashSet;
import java.util.Set;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetStringListener;

/**
 * A class that handles the container for the {@link TheatrePerformersWidget}.
 * 
 * @author Blake
 *
 */
public class ApplicantsContainerWidget extends CustomWidget implements WidgetStringListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 76_550;
	
	/**
	 * The rectangle height.
	 */
	private static final int RECT_HEIGHT = 13;
	
	/**
	 * The width of the container.
	 */
	private static final int WIDTH = 460;
	
	/**
	 * The height of the container.
	 */
	private static final int HEIGHT = 123;
	
	/**
	 * The start id.
	 */
	private static int START_ID;
	
	/**
	 * The ids of the names.
	 */
	public static final Set<Integer> ids = new HashSet<>();
	
	/**
	 * Constructs a new {@link ApplicantsContainerWidget}.
	 */
	public ApplicantsContainerWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Applicants container widget";
	}
	
	/**
	 * The size of the container.
	 */
	public static final int ROWS = 10;
	
	@Override
	public void init() {
		addStringListener(this);
		getInterface().width = WIDTH;
		getInterface().height = HEIGHT;
		getInterface().scrollMax = Math.max(HEIGHT, ROWS * (RECT_HEIGHT + 1));
		
		int x = 0;
		int y = 0;
		
		START_ID = id;
		
		for (int i = 0; i < ROWS; i++) {
			RSInterface rect = addRectangleClickable(i % 2 == 0 ? 225 : 235, 0, true, WIDTH + 3, RECT_HEIGHT, new String[] { });
			rect.hovers = true;
    		rect.hoverType = id - 1;
    		rect.enabledOpacity = 200;
    		rect.enabledMouseOverColor = 0;
    		rect.hidden = true;
    		add(rect, x, y);
    		ids.add(id);
    		add(addCenteredText("", 0, 0xff981f), 5 + 80, 1 + y);
    		add(addCenteredText("", 0, 0xff981f), 11 + 167, 1 + y);
    		add(addCenteredText("", 0, 0xff981f), 11 + 200, 1 + y);
    		add(addCenteredText("", 0, 0xff981f), 11 + 225, 1 + y);
    		add(addCenteredText("", 0, 0xff981f), 11 + 250, 1 + y);
    		add(addCenteredText("", 0, 0xff981f), 11 + 275, 1 + y);
    		add(addCenteredText("", 0, 0xff981f), 11 + 300, 1 + y);
    		add(addCenteredText("", 0, 0xff981f), 11 + 325, 1 + y);
    		add(addCenteredText("", 0, 0xff981f), 11 + 350, 1 + y);
    		add(addCenteredText("", 0, 0xff981f), 375 + 50, 1 + y);
			y += RECT_HEIGHT;
		}
	}

	@Override
	public void onStringUpdate(int id, String string) {
		if (!ids.contains(id)) {
			return;
		}
		
		RSInterface rect = interfaceCache[id - 1];
		
		rect.hidden = string.isEmpty();
		
		boolean visible = false;
		
		for (int i : ids) {
			if (!interfaceCache[i - 1].hidden) {
				visible = true;
				break;
			}
		}
		
		interfaceCache[TheatrePartyWidget.SORT_BOX_ID].hidden = !visible;
		
		for (int i = 0; i < 10; i++) {
			interfaceCache[TheatrePartyWidget.SORT_BUTTON_START + i].hidden = !visible;
		}
		
		interfaceCache[TheatrePartyWidget.CENTER_STRING_ID].hidden = visible;
		
		if (!visible) {
			getInterface().scrollMax = HEIGHT;
		}
	}
	
}
