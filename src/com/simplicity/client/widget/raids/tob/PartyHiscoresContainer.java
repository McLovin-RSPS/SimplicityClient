package com.simplicity.client.widget.raids.tob;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetStringListener;

import java.util.HashSet;
import java.util.Set;

import static com.simplicity.client.RSInterface.interfaceCache;
import static com.simplicity.client.widget.raids.tob.TheatrePerformersWidget.CENTER_STRING_ID;

/**
 * A class that handles the Performance Details widget.
 * 
 * @author Blake
 *
 */
public class PartyHiscoresContainer extends CustomWidget implements WidgetStringListener {

	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 77_400;
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
	 * The size of the container.
	 */
	public static final int ROWS = 10;

	/**
	 * The start id.
	 */
	private static int START_ID;

	/**
	 * The ids of the names.
	 */
	public static final Set<Integer> ids = new HashSet<>();

	/**
	 * Constructs a new {@link PartyHiscoresContainer}.
	 */
	public PartyHiscoresContainer() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Theatre of Blood Top 10 runs";
	}

	@Override
	public void init() {
		addStringListener(this);
		getInterface().width = WIDTH;
		getInterface().height = HEIGHT;
		getInterface().scrollMax = Math.max(HEIGHT + 1, ROWS * (RECT_HEIGHT + 10));

		int x = 0;
		int y = 0;

		START_ID = id;

		for (int i = 0; i < ROWS; i++) {
			RSInterface rect = addRectangleClickable(255, 0, true, WIDTH - 1, RECT_HEIGHT, new String[]{"View party: @lre@Blake"});
			rect.hovers = true;
			rect.hoverType = id - 1;
			rect.enabledOpacity = 220;
			rect.enabledMouseOverColor = 0xffffff;
			rect.hidden = true;
			add(rect, x, y);
			RSInterface members = addCenteredText("N. 1", 1, 0xff981f);
			members.useNewFonts = true;
			add(members, 18, 1 + y);
			RSInterface targetSize = addText("Benberi545's Party", 1, 0x9f9f9f);
			targetSize.useNewFonts = true;
			add(targetSize, 18 + 38, 1 + y);
			ids.add(id);
			add(addCenteredText("", 1, 0xffffff), 18 + 137, 1 + y);
			RSInterface preferredLevel = addText("32:58", 1, 0x9f9f9f);
			preferredLevel.useNewFonts = true;
			add(preferredLevel, 18 + 90 + 100, 1 + y);
			add(addClickText("Party Details", 1, 0xFFBD6C, true, false), 18 + 90 + 94 + 77, 3 + y);
			y += RECT_HEIGHT + 10;
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
