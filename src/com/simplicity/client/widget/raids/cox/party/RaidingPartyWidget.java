package com.simplicity.client.widget.raids.cox.party;

import com.simplicity.client.RSInterface;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.Animation;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetAnimationListener;
import com.simplicity.client.widget.listener.WidgetButtonListener;
import com.simplicity.client.widget.listener.WidgetStateListener;
import com.simplicity.client.widget.listener.WidgetStringListener;
import com.simplicity.client.widget.raids.cox.RaidButtonTask;
import com.simplicity.task.TaskManager;

/**
 * A class that represents the Raiding Party widget.
 * 
 * @author Blake
 *
 */
public class RaidingPartyWidget extends CustomWidget implements WidgetButtonListener, WidgetStateListener, WidgetStringListener, WidgetAnimationListener {

	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 78_600;
	
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
	
	public static int TIMER_ID;
	
	/**
	 * The hourglass animation id.
	 */
	private static final int HOURGLASS_ANIM = 7385 + Animation.OSRS_ANIM_OFFSET;
	
	/**
	 * Constructs a new {@link RaidingPartyWidget}.
	 */
	public RaidingPartyWidget() {
		super(WIDGET_ID);
	}

	@Override
	public String getName() {
		return "Raiding Party";
	}

	@Override
	public void init() {
		addButtonListener(this);
		addStateListener(this);
		addStringListener(this);
		addAnimListener(this);
		
		int x = 16;
		int y = 44;
		
		RSInterface window = addClosableWindow(464, 320, false, "Raiding Party of Gold Lining (1)");
		
		add(window, 24, 7);
		
		TITLE_STRING_ID = window.id + 3;
		
		PROGRESS_SPRITE_ID = id;
		REFRESH_BUTTON_ID = id + 1;
		add(addDynamicButton("Refresh", 1, 0xff981f, 110, 29), 372, 292);
		BACK_BUTTON_ID = id + 1;
		add(addDynamicButton("Back", 1, 0xff981f, 110, 29), 260, 292);
		UNBLOCK_BUTTON_ID = id + 1;
		add(addDynamicButton("Advertise (15)", 1, 0xff981f, 110, 29), 260, 261);
		DISBAND_BUTTON_ID = id + 1;
		add(addDynamicButton("Disband", 1, 0xff0000, 110, 29), 372, 261);
		
		int containerFrameX = 38 - 4;
		int containerFrameY = 51 - 4;
		
		add(addBox(218 - 1, 268, 2, 4671301, 0, 250, 0x3e3529, 256), containerFrameX, containerFrameY);
		
		SORT_BOX_ID = id;
		add(addBox(218 - 1, 14, 2, 4671301, 0, 250, 0, 0), containerFrameX, containerFrameY);
		
		addWidget(RaidingPartyContainerWidget.WIDGET_ID, x + 20, y + 17);
		
		PREFERRED_SIZE_ID = id;
		add(addField(222, 20, "Preferred party size: ---", "Set @lre@Preferred party size"), 260 - 4, containerFrameY);
		
		PREFERRED_LEVEL_ID = id;
		add(addField(222, 20, "Preferred combat level: ---", "Set @lre@Preferred combat level"), 260 - 4, containerFrameY + 22);
		add(addField(222, 20, "Preferred skill total: ---", "Set @lre@Preferred skill total"), 260 - 4, containerFrameY + 44);
		add(addField(222, 20, "Challenge mode", "Set @lre@Challenge mode"), 260 - 4, containerFrameY + 66);
		
		SORT_BUTTON_START = id;
		add(addSortButton(102, 9, true, "Sort by name"), containerFrameX, containerFrameY + 2);
		add(addSortButton(30, 9, false, "Sort by combat level"), containerFrameX + 102, containerFrameY + 2);
		add(addSortButton(30, 9, false, "Sort by skill total"), containerFrameX + 102 + 30, containerFrameY + 2);
		add(addSortButton(40, 9, false, "Sort by kill count"), containerFrameX + 102 + 30 + 30, containerFrameY + 2);

		RSInterface remains = addModel(32506, 1630, 0, 1536, DataType.OLDSCHOOL);
		remains.enabledAnimationId = -1;
		remains.disabledAnimationId = -1;
		add(remains, x + 254 + 100, 30 + 88 + 142);
		
		TIMER_ID = id;
		RSInterface model = addModelSprite(30, 70, 4612, 2500, 0, 0, DataType.OLDSCHOOL);
		model.disabledAnimationId = 7385 + Animation.OSRS_ANIM_OFFSET;
		model.enabledAnimationId = 7385 + Animation.OSRS_ANIM_OFFSET;
		add(model, 452 - 5, 148 + 15);
		
		UNBLOCK_BUTTON_ID = id + 1;
		RSInterface join = addDynamicButton("@gre@Join", 1, 0xff981f, 222, 29);
		join.hidden = false;
		add(join, 260, 261);
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
			
			TaskManager.submit(new RaidButtonTask(button, id == REFRESH_BUTTON_ID || id == BACK_BUTTON_ID ? 300 : 600, id != DISBAND_BUTTON_ID && id != BACK_BUTTON_ID) {
				@Override
				public void clicked() {
					if (id == REFRESH_BUTTON_ID) {
						RSInterface.interfaceCache[TIMER_ID].currentFrame = 0;
						
						if (hourGlassReset) {
							hourGlassReset = false;
						}
					}
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
		RSInterface.interfaceCache[BACK_BUTTON_ID].buttonDown = false;
		RSInterface.interfaceCache[BACK_BUTTON_ID + 1].message = "Back";
		
		RSInterface.interfaceCache[DISBAND_BUTTON_ID].buttonDown = false;
		RSInterface.interfaceCache[DISBAND_BUTTON_ID + 1].message = "Disband";
		
		RSInterface.interfaceCache[REFRESH_BUTTON_ID].buttonDown = false;
		RSInterface.interfaceCache[REFRESH_BUTTON_ID + 1].message = "Refresh";
		
		boolean hidden = RSInterface.interfaceCache[UNBLOCK_BUTTON_ID - 1].hidden;
		RSInterface.interfaceCache[PREFERRED_SIZE_ID].tooltip = hidden ? null : "Set @lre@Preffered size";
		RSInterface.interfaceCache[PREFERRED_LEVEL_ID].tooltip = hidden ? null : "Set @lre@Preffered level";
	}
	
	/**
	 * A flag which determines if the hourglass anim was reset.
	 */
	private boolean hourGlassReset;

	@Override
	public void onAnimFinish(int id, int animId) {
		if (id == TIMER_ID && animId == HOURGLASS_ANIM && !hourGlassReset) {
			onClick(REFRESH_BUTTON_ID);
			hourGlassReset = true;
		}
	}

}
