package com.simplicity.client.widget.raids.tob;

import com.simplicity.client.RSInterface;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.widget.CustomWidget;

/**
 * A class that handles the Theatre of Blood supplies widget.
 * 
 * @author Blake
 */
public class TobSuppliesWidget extends CustomWidget {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 79_500;

	/**
	 * Constructs a new {@link TobSuppliesWidget}.
	 */
	public TobSuppliesWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Theatre of Blood Supplies";
	}

	@Override
	public void init() {
		int x = 64;
		int y = 40;
		
		add(addClosableWindow(366, 220, false, getName()), x, y);
		add(addModel(1048, 600, 512, 450, DataType.OLDSCHOOL), x + 92 + 56, y + 102);
		add(addModel(1070, 780, 512, 800, DataType.OLDSCHOOL), x + 89, y + 145);
		RSInterface container = addItemContainer(4, 2, 18, 36, new String[] { "Buy", "Buy 1", "Buy 5", "Buy All", "Buy X" }, getName() + " #");
		
		for (int i = 0; i < container.inv.length; i++) {
			add(addCenteredText("1", 0, 0xFF981F), x + 46 + (i % 4 * 50), y + 88 + (i > 3 ? 72 : 0));
		}
		
		add(container, x + 32, y + 50);
		add(addCenteredText("Points Available: @whi@11", 0, 0xFF981F), x + 180, y + 197);
		add(addSprite(1452), x + 230, y + 44);
		
	}

}
