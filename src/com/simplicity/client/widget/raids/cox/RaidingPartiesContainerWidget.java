package com.simplicity.client.widget.raids.cox;

import static com.simplicity.client.RSInterface.interfaceCache;
import static com.simplicity.client.widget.raids.cox.RaidingPartiesWidget.CENTER_STRING_ID;

import java.util.HashSet;
import java.util.Set;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetStringListener;

/**
 * A class that handles the container for the {@link RaidingPartyWidget}.
 * 
 * @author Blake
 *
 */
public class RaidingPartiesContainerWidget extends CustomWidget implements WidgetStringListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 78_400;
	
	/**
	 * The rectangle height.
	 */
	private static final int RECT_HEIGHT = 18;
	
	/**
	 * The width of the container.
	 */
	private static final int WIDTH = 424;
	
	/**
	 * The height of the container.
	 */
	private static final int HEIGHT = 227;
	
	/**
	 * The ids of the names.
	 */
	public static final Set<Integer> ids = new HashSet<>();
	
	/**
	 * Constructs a new {@link RaidingPartiesContainerWidget}.
	 */
	public RaidingPartiesContainerWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Raiding parties container";
	}
	
	/**
	 * The size of the container.
	 */
	public static final int ROWS = 20;
	
	@Override
	public void init() {
		addStringListener(this);
		getInterface().width = WIDTH;
		getInterface().height = HEIGHT;
		getInterface().scrollMax = Math.max(HEIGHT + 1, ROWS * (RECT_HEIGHT + 1));
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < ROWS; i++) {
			RSInterface rect = addRectangleClickable(255, 0, true, WIDTH - 1, RECT_HEIGHT, new String[] { "View party: @lre@Blake" });
			rect.hovers = true;
    		rect.hoverType = id - 1;
    		rect.enabledOpacity = 220;
    		rect.enabledMouseOverColor = 0xffffff;
    		rect.hidden = true;
    		add(rect, x, y);
    		RSInterface members = addCenteredText("", 1, 0xff981f);
    		members.useNewFonts = true;
    		add(members, 18, 1 + y);
    		RSInterface targetSize = addCenteredText("", 1, 0x9f9f9f);
    		targetSize.useNewFonts = true;
    		add(targetSize, 18 + 42, 1 + y);
    		ids.add(id);
    		add(addCenteredText("", 1, 0xffffff), 18 + 137, 1 + y);
    		
    		RSInterface preferredLevel = addCenteredText("", 1, 0x9f9f9f);
    		preferredLevel.useNewFonts = true;
    		add(preferredLevel, 18 + 138 + 94, 1 + y);
    		
    		RSInterface preferredTotalLevel = addCenteredText("", 1, 0x9f9f9f);
    		preferredTotalLevel.useNewFonts = true;
    		add(preferredTotalLevel, 18 + 138 + 94 + 41, 1 + y);
    		
    		add(addText("", 0), 18 + 138 + 94 + 41 + 26, 1 + y);
    		
    		add(addTimer(25, 14, 0, 0x9f9f9f, ""), 18 + 139 + 94 + 77 + 53, 3 + y);
			y += RECT_HEIGHT + 1;
		}
	}
	
	@Override
	public void onStringUpdate(int id, String string) {
		if (!ids.contains(id)) {
			return;
		}
		
		RSInterface rect = interfaceCache[id - 3];
		
		if (!string.isEmpty()) {
			rect.actions = new String[] { "View party: @lre@" + string };
		}
		
		rect.hidden = string.isEmpty();
		
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
	
}
