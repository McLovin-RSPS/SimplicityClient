package com.simplicity.client.widget.raids.cox;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetButtonListener;
import com.simplicity.task.TaskManager;

/**
 * A class that handles the Raiding parties widget.
 * 
 * @author Blake
 *
 */
public class RaidingPartiesWidget extends CustomWidget implements WidgetButtonListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 78_300;
	
	/**
	 * The refresh button id.
	 */
	public static int REFRESH_BUTTON_ID;
	
	/**
	 * The make party button id.
	 */
	public static int MAKE_PARTY_BUTTON_ID;
	
	/**
	 * The sort button start id.
	 */
	public static int SORT_BUTTON_START;
	
	/**
	 * The center string id.
	 */
	public static int CENTER_STRING_ID;
	
	/**
	 * Constructs a new {@link RaidingPartiesWidget}.
	 */
	public RaidingPartiesWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Raiding Parties";
	}

	@Override
	public void init() {
		addButtonListener(this);
		
		int xoff = 6;
		int yoff = 4;
		add(addClosableWindow(464, 320, false, getName()), 25, 6);
		
		REFRESH_BUTTON_ID = id + 1;
		add(addDynamicButton("Refresh", 1, 0xff981f, 100, 25), 119, 291);
		RSInterface makeParty = addDynamicButton("Make Party", 1, 0xff981f, 100, 25);
		
		MAKE_PARTY_BUTTON_ID = makeParty.id + 1;
		System.out.println("PARTY BUTTON:  " + MAKE_PARTY_BUTTON_ID);
		
		add(makeParty, 303, 291);
		add(addBox(443, 14, 2, 4671301, 0, 250), 41 - xoff, 49 - yoff);
		add(addBox(443, 243, 2, 4671301, 0, 250), 41 - xoff, 49 - yoff);
		addWidget(RaidingPartiesContainerWidget.WIDGET_ID, xoff + 31, 58);
		
		CENTER_STRING_ID = id;
		add(addCenteredText("No parties are currently listed.", 1, 16750623), 250, 160);
		
		SORT_BUTTON_START = id;
		add(addSortButton(35, 9, true, "Sort by members"), 43 - xoff, 51 - yoff);
		add(addSortButton(45, 9, false, "Sort by target size"), 43 - xoff + 36, 51 - yoff);
		add(addSortButton(145, 9, false, "Sort by leader name"), 43 - xoff + 82, 51 - yoff);
		add(addSortButton(40, 9, false, "Sort by preferred combat level"), 43 - xoff + 228, 51 - yoff);
		add(addSortButton(45, 9, false, "Sort by preferred skill total"), 43 - xoff + 269, 51 - yoff);
		add(addSortButton(20, 9, false, "Sort by challenge mode"), 43 - xoff + 269 + 45, 51 - yoff);
		add(addSortButton(94, 9, false, "Sort by age"), 43 - xoff + 269 + 45 + 20, 51 - yoff);
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
		
		int[] selectable = new int[6];
		
		for (int i = SORT_BUTTON_START, ptr = 0; i <= SORT_BUTTON_START + 6; i++) {
			if (i != id - 1) {
				selectable[ptr++] = i;
			}
		}
		
		button.selectableInterfaces = selectable;
		return button;
	}
	
	@Override
	public boolean onClick(int id) {
		if (id == REFRESH_BUTTON_ID || id == MAKE_PARTY_BUTTON_ID) {
			RSInterface button = RSInterface.interfaceCache[id];
			
			if (button.buttonDown) {
				return true;
			}
			
			TaskManager.submit(new RaidButtonTask(button, 300));
			return true;
		}
		
		return false;
	}

}
