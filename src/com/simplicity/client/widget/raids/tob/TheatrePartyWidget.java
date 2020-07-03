package com.simplicity.client.widget.raids.tob;

import java.io.IOException;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetButtonListener;
import com.simplicity.client.widget.listener.WidgetStateListener;
import com.simplicity.client.widget.listener.WidgetStringListener;
import com.simplicity.task.Task;
import com.simplicity.task.TaskManager;

/**
 * A class that represents the Theatre of Blood party widget.
 * 
 * @author Blake
 *
 */
public class TheatrePartyWidget extends CustomWidget implements WidgetButtonListener, WidgetStateListener, WidgetStringListener {

	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 76_300;
	
	/**
	 * The title string id.
	 */
	public static int TITLE_STRING_ID;
	
	/**
	 * The progress sprite id.
	 */
	public static int PROGRESS_SPRITE_ID;
	
	/**
	 * The refresh button id.
	 */
	public static int REFRESH_BUTTON_ID;
	
	/**
	 * The back button id.
	 */
	public static int BACK_BUTTON_ID;
	
	/**
	 * The unblock button id.
	 */
	public static int UNBLOCK_BUTTON_ID;
	
	/**
	 * The disband button id.
	 */
	public static int DISBAND_BUTTON_ID;
	
	/**
	 * The sort box id.
	 */
	public static int SORT_BOX_ID;
	
	/**
	 * The center string id.
	 */
	public static int CENTER_STRING_ID;
	
	/**
	 * The sort button start id.
	 */
	public static int SORT_BUTTON_START;
	
	/**
	 * The preferred size id.
	 */
	public static int PREFERRED_SIZE_ID;
	
	/**
	 * The preferred level id.
	 */
	public static int PREFERRED_LEVEL_ID;
	
	/**
	 * Constructs a new {@link TheatrePartyWidget}.
	 */
	public TheatrePartyWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Theatre of Blood Party";
	}

	@Override
	public void init() {
		addButtonListener(this);
		addStateListener(this);
		addStringListener(this);
		
		int x = 16;
		int y = 44;
		
		RSInterface window = addClosableWindow(500, 325, false, "Party of Not Athos");
		
		add(window, 6, 4);
		
		TITLE_STRING_ID = window.id + 3;
		
		add(addBox(480 - 1, 96, 2, 4671301, 0, 250, 0x534a3f, 255), x, y);
		add(addBox(480 - 1, 30, 2, 4671301, 0, 250, 0x534a3f, 255), x, y);
		PROGRESS_SPRITE_ID = id;
		add(addProgressSprite(1444, 1445, 100, 100), x + 87, y + 2);
		add(addModel(1048, 1800, 512, 0, DataType.OLDSCHOOL), x + 80 + 16, y + 260);
		add(addModel(1070, 1500, 512, 617, DataType.OLDSCHOOL), x + 80 + 16 + 25, y + 260 + 1);
		add(addModel(1048, 1900, 512, 840, DataType.OLDSCHOOL), x + 80 + 16 + 15 + 12 + 17, y + 255 + 15);
		REFRESH_BUTTON_ID = id + 1;
		add(addDynamicButton("Refresh", 1, 0xff981f, 78, 25), x + 2, y + 2);
		BACK_BUTTON_ID = id + 1;
		add(addDynamicButton("Back", 1, 0xff981f, 75, 25), x, y + 250);
		UNBLOCK_BUTTON_ID = id + 1;
		add(addDynamicButton("Unblock", 1, 0xffff00, 75, 25), x + 80, y + 250);
		DISBAND_BUTTON_ID = id + 1;
		add(addDynamicButton("Disband", 1, 0x9f9f9f, 85, 25), x + 395, y + 250);
		add(addSprite(972), 22 + 168, y + 6);
		add(addSprite(1434), 16 + 202, y + 2);
		add(addSprite(1435), 14 + 227, y + 2);
		add(addSprite(1437), 15 + 252, y + 2);
		add(addSprite(1439), 15 + 277, y + 2);
		add(addSprite(1436), 15 + 302, y + 2);
		add(addSprite(1440), 15 + 327, y + 2);
		add(addSprite(1438), 15 + 352, y + 2);
		addWidget(PartyContainerWidget.WIDGET_ID, x + 2, y + 29);
		
		add(addBox(480 - 1, 152, 2, 4671301, 0, 250, 0, 0), x, y + 97);
		
		SORT_BOX_ID = id;
		add(addBox(480 - 1, 14, 2, 4671301, 0, 250, 0, 0), x, y + 97);
		
		CENTER_STRING_ID = id;
		add(addCenteredText("No current applicants.", 1, 0x9f9f9f), 250, 160 + 50);
		
		addWidget(ApplicantsContainerWidget.WIDGET_ID, x + 2, y + 111);
		
		SORT_BUTTON_START = id;
		add(addSortButton(145, 9, true, "Sort by name"), x + 16, y + 99);
		add(addSortButton(9, 9, false, "Sort by combat level"), x + 70 + 106, y + 99);
		add(addSortButton(9, 9, false, "Sort by attack"), x + 70 + 139, y + 99);
		add(addSortButton(9, 9, false, "Sort by strength"), x + 70 + 164, y + 99);
		add(addSortButton(9, 9, false, "Sort by ranged"), x + 70 + 190, y + 99);
		add(addSortButton(9, 9, false, "Sort by magic"), x + 70 + 189 + 26, y + 99);
		add(addSortButton(9, 9, false, "Sort by defence"), x + 70 + 189 + 51, y + 99);
		add(addSortButton(9, 9, false, "Sort by hitpoints"), x + 70 + 189 + 76, y + 99);
		add(addSortButton(9, 9, false, "Sort by prayer"), x + 70 + 189 + 101, y + 99);
		add(addSortButton(9, 9, false, "Sort by completed"), x + 70 + 189 + 165, y + 99);
		
		System.out.println("PREFFERED " + id);
		PREFERRED_SIZE_ID = id;
		add(addField(104, 20, "Preferred size: ---", "Set @lre@Preferred size"), x + 160, y + 253);
		
		System.out.println("PREFFERED lvl " + id);
		PREFERRED_LEVEL_ID = id;
		add(addField(119, 20, "Preferred level: ---", "Set @lre@Preferred level"), x + 270, y + 253);

		add(addTooltip("Combat level", 32, 36, 8, -1), x + 171, y);
		add(addTooltip("Attack level", 25, 25, 8, 9), x + 204, y);
		add(addTooltip("Strength level", 25, 25, 8, 9), x + 229, y);
		add(addTooltip("Ranged level", 25, 25, 8, 9), x + 254, y);
		add(addTooltip("Magic level", 25, 25, 8, 9), x + 279, y);
		add(addTooltip("Defence level", 25, 25, 8, 9), x + 304, y);
		add(addTooltip("Hitpoints level", 25, 25, 8, 9), x + 329, y);
		add(addTooltip("Prayer level", 25, 25, 8, 10), x + 354, y);
		add(addTooltip("Performance counter", 101, 29, -14, 10), x + 380, y);
		add(addModel(1070, 1245, 512, 1700, DataType.OLDSCHOOL), x + 377 + 41, y + 16);
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
		
		for (int i = SORT_BUTTON_START, ptr = 0; i <= SORT_BUTTON_START + 9; i++) {
			if (i != id - 1) {
				selectable[ptr++] = i;
			}
		}
		
		button.selectableInterfaces = selectable;
		return button;
	}
	
	@Override
	public boolean onClick(int id) {
		if (id == REFRESH_BUTTON_ID || id == BACK_BUTTON_ID || id == UNBLOCK_BUTTON_ID || id == DISBAND_BUTTON_ID) {
			RSInterface button = RSInterface.interfaceCache[id];
			
			if (button.buttonDown) {
				return true;
			}
			
			RSInterface text = RSInterface.interfaceCache[id + 1];
			String cached = new String(text.message);
			text.message = "...";
			button.buttonDown = true;
			
			TaskManager.submit(new Task(id == REFRESH_BUTTON_ID || id == BACK_BUTTON_ID ? 300 : 600) {
				
				int count = 0;

				@Override
				public void execute() throws IOException {
					if (count == 0) {
						Client.instance.sendButtonClick(id);
						
						
					} else if (count == 1) {
						if (id == REFRESH_BUTTON_ID) {
							initProgress();
						}
						
						if (id != DISBAND_BUTTON_ID && id != BACK_BUTTON_ID) {
							button.buttonDown = false;
							
							if (text.message.equals("...")) {
								text.message = cached;
							}
						}
						
					} else if (count == 2) {
						
						stop();						
					}
					
					count++;
				}
				
			});
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onStringUpdate(int id, String string) {
		if (id == DISBAND_BUTTON_ID + 1) { // Disband string updated
			RSInterface button = RSInterface.interfaceCache[DISBAND_BUTTON_ID];
			
			button.buttonDown = false;
		}
	}

	@Override
	public void onDisplay() {
		initProgress();
		
		RSInterface.interfaceCache[BACK_BUTTON_ID].buttonDown = false;
		RSInterface.interfaceCache[BACK_BUTTON_ID + 1].message = "Back";
		
		boolean hidden = RSInterface.interfaceCache[UNBLOCK_BUTTON_ID - 1].hidden;
		RSInterface.interfaceCache[PREFERRED_SIZE_ID].tooltip = hidden ? null : "Set @lre@Preffered size";
		RSInterface.interfaceCache[PREFERRED_LEVEL_ID].tooltip = hidden ? null : "Set @lre@Preffered level";
	}
	
	/**
	 * The progress amount.
	 */
	private int progressAmount = 100;
	
	/**
	 * The progress task.
	 */
	private Task progressTask;
	
	/**
	 * Initializes the progress event.
	 */
	private void initProgress() {
		progressAmount = 100;
		
		if (progressTask == null) {
			progressTask = new Task(95) {
				
				@Override
				public void execute() throws IOException {
					if (Client.openInterfaceID != WIDGET_ID) {
						stop();
						return;
					}
					
					RSInterface.interfaceCache[PROGRESS_SPRITE_ID].message = progressAmount + "/100";
					
					if (progressAmount-- == 0) {
						onClick(REFRESH_BUTTON_ID);
						stop();
					}
				}
				
				@Override
				public void onStop() {
					progressTask = null;
				}
			};
			
			TaskManager.submit(progressTask);
		}
	}

}
