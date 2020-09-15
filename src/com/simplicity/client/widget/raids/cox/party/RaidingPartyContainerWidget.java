package com.simplicity.client.widget.raids.cox.party;

import static com.simplicity.client.RSInterface.interfaceCache;

import java.util.HashSet;
import java.util.Set;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetStringListener;
import com.simplicity.client.widget.raids.cox.RaidingPartiesWidget;

/**
 * A class that handles the container for the {@link RaidingPartiesWidget}.
 * 
 * @author Blake
 *
 */
public class RaidingPartyContainerWidget extends CustomWidget implements WidgetStringListener  {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 78_700;
	
	/**
	 * The rectangle height.
	 */
	private static final int RECT_HEIGHT = 13;
	
	/**
	 * The width of the container.
	 */
	private static final int WIDTH = 214;
	
	/**
	 * The height of the container.
	 */
	private static final int HEIGHT = 65;
	
	/**
	 * The size of the container.
	 */
	public static final int ROWS = 20;
	
	/**
	 * The start id.
	 */
	private static int START_ID;
	
	/**
	 * The ids of the names.
	 */
	public static final Set<Integer> ids = new HashSet<>();
	
	@Override
	public String getName() {
		return "Raiding party container";
	}

	public RaidingPartyContainerWidget() {
		super(WIDGET_ID);
	}
	
	@Override
	public void init() {
		addStringListener(this);
		getInterface().width = WIDTH;
		getInterface().height = HEIGHT;
		
		int x = 0;
		int y = 0;
		
		START_ID = id;
		
		for (int i = 0; i < ROWS; i++) {
			boolean odd = i % 2 == 0;
			RSInterface rect = addRectangleClickable(odd ? 235 : 255, odd ? 0 : 0x534a3f, true, WIDTH, RECT_HEIGHT, new String[2]);
			rect.hovers = false;
    		rect.hoverType = id - 1;
    		rect.enabledOpacity = odd ? 225 : 225;
    		rect.enabledMouseOverColor = odd ? 0 : 0xffffff;
    		add(rect, x, y);
    		ids.add(id);
    		RSInterface name = addCenteredText("", 0, 0x9f9f9f);
    		name.useNewFonts = true;
    		add(name, 5 + 45, 1 + y);
    		add(addCenteredText("", 0, 0x9f9f9f), 5 + 45 + 65, 1 + y);
    		add(addCenteredText("", 0, 0x9f9f9f), 5 + 45 + 95, 1 + y);
    		add(addCenteredText("", 0, 0x9f9f9f), 5 + 45 + 130, 1 + y);
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
			rect.actions = new String[] { "Stats" };
		}
		rect.hovers = !string.isEmpty();
	}

}
