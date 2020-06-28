package com.simplicity.client.widget.raids.tob;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetButtonListener;
import com.simplicity.task.Task;

/**
 * A class that handles the Theatre performers widget.
 * 
 * @author Blake
 *
 */
public class TheatrePerformersWidget extends CustomWidget implements WidgetButtonListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 76_050;
	
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
	 * The no parties message.
	 */
	public static final String NO_PARTIES = "No parties are currently listed.";

	/**
	 * Constructs a new {@link TheatrePerformersWidget}.
	 */
	public TheatrePerformersWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Performers for the Theatre";
	}

	@Override
	public void init() {
		addButtonListener(this);
		int xoff = 6;
		int yoff = 4;
		add(addClosableWindow(420, 325, false, getName()), 45, 4);
		add(addDynamicButton("Refresh", 1, 0xff981f, 100, 25), 45 + 85, 293);
		RSInterface makeParty = addDynamicButton("Make Party", 1, 0xff981f, 100, 25);
		
		MAKE_PARTY_BUTTON_ID = makeParty.id + 1;
		
		add(makeParty, 135 + 140, 293);
		add(addBox(398, 14, 2, 4671301, 0, 250), 61 - xoff, 49 - yoff);
		add(addBox(398, 243, 2, 4671301, 0, 250), 61 - xoff, 49 - yoff);
		add(addModel(1048, 600, 512, 0, DataType.OLDSCHOOL), 143 - xoff, 95 - yoff + 2);
		add(addModel(1070, 600, 512, 512, DataType.OLDSCHOOL), 195 - xoff, 198 - yoff + 2);
		add(addModel(1097, 800, 512, 256, DataType.OLDSCHOOL), 281 - xoff, 141 - yoff + 2);
		add(addModel(1070, 500, 512, 113, DataType.OLDSCHOOL), 382 - xoff, 159 - yoff + 2);
		add(addModel(1048, 700, 512, 682, DataType.OLDSCHOOL), 326 - xoff, 280 - yoff + 2);
		addWidget(PerformersContainerWidget.WIDGET_ID, 56, 58);
		
		CENTER_STRING_ID = id;
		add(addCenteredText("No parties are currently listed.", 1, 16750623), 250, 160);
		
		SORT_BUTTON_START = id;
		add(addSortButton(35, 9, true, "Sort by members"), 63 - xoff, 51 - yoff);
		add(addSortButton(45, 9, false, "Sort by target size"), 63 - xoff + 36, 51 - yoff);
		add(addSortButton(145, 9, false, "Sort by leader name"), 63 - xoff + 82, 51 - yoff);
		add(addSortButton(40, 9, false, "Sort by preferred combat level"), 63 - xoff + 228, 51 - yoff);
		add(addSortButton(115, 9, false, "Sort by age"), 63 - xoff + 269, 51 - yoff);
	}
	
	/**
	 * Handles the button clicking for this interface.
	 * 
	 * @param buttonId The button id.
	 */
	public static void clickButton(int buttonId) {
		if (!(buttonId >= SORT_BUTTON_START && buttonId <= SORT_BUTTON_START + 4)) {
			return;
		}
		
		RSInterface.setSelectedInterface(buttonId, true);
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
		
		int[] selectable = new int[4];
		
		for (int i = SORT_BUTTON_START, ptr = 0; i <= SORT_BUTTON_START + 4; i++) {
			if (i != id - 1) {
				selectable[ptr++] = i;
			}
		}
		
		button.selectableInterfaces = selectable;
		return button;
	}

	@Override
	public void onClick(int id) {
		if (id == MAKE_PARTY_BUTTON_ID) {
			RSInterface text = RSInterface.interfaceCache[MAKE_PARTY_BUTTON_ID + 1];
			String cached = new String(text.message);
			text.message = "---";
			Client.getTaskManager().submit(new Task(2, false) {
				
				@Override
				protected void execute() {
					text.message = cached;
					stop();
				}
				
			});
		}
	}

}
