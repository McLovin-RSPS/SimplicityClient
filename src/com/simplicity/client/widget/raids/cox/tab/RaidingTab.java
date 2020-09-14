package com.simplicity.client.widget.raids.cox.tab;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

public class RaidingTab extends CustomWidget {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 73_820;
	
	/**
	 * The sort button start id.
	 */
	public static int SORT_BUTTON_START;

	/**
	 * Constructs a new {@link RaidingTab}.
	 */
	public RaidingTab() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Raid Party tab";
	}

	@Override
	public void init() {
		add(addHoverOpacityButton(1460, 150, "Refresh"), 160, 0);
		id++;
		
		int x = 3;
		int y = 18;
		
		int boxWidth = 176;
		int boxHeight = 160;
		
		add(addBox(boxWidth - 1, boxHeight, 2, 4671301, 0, 256, 0x3e3529, 256), x, y);
		add(addHorizontalLine(boxWidth - 3, 4671301, 256), x + 1, y + 11);
		add(addHorizontalLine(boxWidth - 4, 0, 256), x + 2, y + 12);
		addWidget(RaidingTabContainerWidget.WIDGET_ID, 5, y + 13);
		
		SORT_BUTTON_START = id;
		add(addSortButton(90, 9, true, "Sort by name"), x, y + 2);
		add(addSortButton(35, 9, false, "Sort by combat level"), x + 90, y + 2);
		add(addSortButton(35, 9, false, "Sort by skill total"), x + 90 + 35, y + 2);
		
		y += boxHeight + 1;
		add(addBox(boxWidth - 1, 50, 2, 4671301, 0, 256, 0x483e33, 256), x, y);
		add(addCenteredText("Party size: 0", 0, 0xff981f), x + 87, y + 4);
		add(addDynamicButton("Start raid", 1, 0xff981f, 74, 25), x + 50, y + 18);
	}
	
	public RSInterface addSortButton(int width, int height, boolean isActive, String tooltipBox) {
		RSInterface button = addSelectableToggleButton("", 1417, 1416, width, height, true, isActive, isActive);
		
		button.hovers = true;
		button.filled = true;
		button.hoverType = id - 1;
		button.hoverOpacity = 100;
		button.defaultOpacity = 150;
		button.selectedOpacity = 256;
		button.tooltipBox = tooltipBox;
		button.tooltipOffsetX = 5;
		button.tooltipOffsetY = 5;
		
		int[] selectable = new int[9];
		
		for (int i = SORT_BUTTON_START, ptr = 0; i <= SORT_BUTTON_START + 3; i++) {
			if (i != id - 1) {
				selectable[ptr++] = i;
			}
		}
		
		button.selectableInterfaces = selectable;
		return button;
	}

}
