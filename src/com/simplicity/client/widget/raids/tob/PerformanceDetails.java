package com.simplicity.client.widget.raids.tob;

import com.simplicity.client.widget.CustomWidget;

/**
 * A class that handles the Performance Details widget.
 * 
 * @author Blake
 *
 */
public class PerformanceDetails extends CustomWidget {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 76_800;
	
	/**
	 * Constructs a new {@link PerformanceDetails}.
	 */
	public PerformanceDetails() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Performance Details";
	}

	@Override
	public void init() {
		int x = 28;
		int y = 16;
		int width = 175;
		add(addClosableWindow(457, 301, false, getName()), x, y);
		add(addText("Death Counts", 2, 0xFF981F), x + 52, y + 46);
		add(addBox(width, 209, 2, 4671301, 0, 250), x + 10, y + 68);
		for (int i = 0; i < 6; i++) {
			add(addRectangle(width - 3, 34, i % 2 != 0 ? 0x544b40 : 0x3e3529, 0, true), x + 12, y + 70 + (i * 34));
		}
		
		for (int i = 0; i < 5; i++) {
			add(addText("-", 0, 0xff981f), x + 22, y + 82 + (i * 34));
			add(addRightAlignedText("-", 0, 0xffffff), x + width - 2, y + 81 + (i * 34));
		}
		
		add(addText("Total Deaths", 2, 0xff981f), x + 18, y + 80 + (5 * 34));
		add(addRightAlignedText("-", 2, 0xffffff), x + width - 2, y + 80 + (5 * 34));
		
		add(addBox(132, 26, 2, 4671301, 0, 255, 0x544b40, 255), x + 248, y + 48);
		add(addText("MVP", 2, 0xff981f), x + 260, y + 52);
		add(addRightAlignedText("-", 0, 0xffffff), x + 370, y + 54);
		
		add(addBox(220, 145, 2, 4671301, 0, 255, 0x544b40, 255), x + 248 - 36, y + 85);
		
		for (int i = 0; i < 6; i++) {
			add(addRectangle(217, 23, i % 2 != 0 ? 0x534a3e : 0x3e3529, 0, true), x + 248 - 36 + 2, y + 87 + (i * 23));
		}
		
		String[] data = new String[] { "The Maiden of Sugadinti", "The Pestilent Bloat", "The Nylocas", "Sotetseg", "Xarpus", "The Final Challenge" };
		
		for (int i = 0; i < 6; i++) {
			add(addText(data[i], 0, 0xff981f), x + 223, y + (i == 5 ? 95 : 93) + (i * 23));
			add(addRightAlignedText("-", 0, 0xffffff), x + 422, y + (i == 5 ? 94 : 93) + (i * 23));
		}
		
		add(addBox(110, 50, 2, 4671301, 0, 255, 0x3e3529, 255), x + 230 - 36, y + 240);
		add(addText("Challenge Time", 2, 0xff981f), x + 230 - 29, y + 240 + 5);
		add(addCenteredText("-", 0, 0xffffff), x + 230 + 19, y + 240 + 30);
		
		add(addBox(110, 50, 2, 4671301, 0, 255, 0x3e3529, 255), x + 230 - 36 + 135, y + 240);
		add(addText("Overall Time", 2, 0xff981f), x + 230 - 20 + 135, y + 240 + 5);
		add(addCenteredText("-", 0, 0xffffff), x + 230 + 19 + 135, y + 240 + 30);
	}
	
}
