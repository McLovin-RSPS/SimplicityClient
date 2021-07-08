package com.simplicity.client.widget.raids.tob;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

/**
 * A class that handles the Theatre rewards widget.
 * 
 * @author Blaketon
 */
public class TheatreRewardsWidget extends CustomWidget {

	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 77_000;

	/**
	 * Constructs a new @link {TheatreRewardsWidget}.
	 */
	public TheatreRewardsWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Theatre Rewards";
	}

	@Override
	public void init() {
		int x = 136;
		int y = 77;
		add(addClosableWindow(240, 180, false, "Theatre of Blood"), x, y);
		add(addSprite(1449), x + 11, y + 45);
		add(addSprite(1446), x + 104, y + 42);
		add(addHoverOpacityButton(1450, 1448, 4, 8, 150, "Bank all"), x + 10, y + 134);
		add(addHoverOpacityButton(1450, 1447, 2, 8, 150, "Take all"), x + 10 + 36 + 4, y + 134);
		RSInterface container = addItemContainer(2, 2, 20, 16, new String[] { "Take", "Take 5", "Take All", "Take X" }, "Theatre rewards container #");
		/*for (int i = 0; i < container.inv.length; i++) {
			container.inv[i] = 1113 + 1;
			container.invStackSizes[i] = 1;
		}*/
		
		add(container, x + 10, y + 40);
		
	}

}
