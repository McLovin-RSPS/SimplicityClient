package com.simplicity.client.widget.ge;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetStateListener;

public class GrandExchangeSearchWidget extends CustomWidget implements WidgetStateListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 94_000;
	
	/**
	 * Constructs a new {@link GrandExchangeSearchWidget}.
	 */
	public GrandExchangeSearchWidget() {
		super(WIDGET_ID);
	}

	@Override
	public void init() {
		addStateListener(this);
		add(addScrollbarWithItem(3, 100, 161 - 31 - 2, 0, new String[] { "Select" }, 104, 492- 10), 0, 0);
	}
	
	public RSInterface addScrollbarWithItem(int w, int h, int x, int y, String[] action, int scrollHeight,
											int scrollWidth) {
        RSInterface scroll = RSInterface.addTabInterface(id);
        scroll.componentId = id;
        id++;

        scroll.totalChildren(1);
        scroll.height = scrollHeight;
        scroll.width = scrollWidth;
        scroll.scrollMax = 35 * h;
        int scroll_frame = 0;

        RSInterface item = RSInterface.addToItemGroup(id, w, h, x, y, action == null ? false : true, "", "", "");

        item.actions = new String[5];

        for (int i = 0; i < item.actions.length; i++) {
            item.actions[i] = null;
        }

        if (action != null) {
            for (int i = 0; i < action.length; i++) {
                item.actions[i] = action[i];
            }
        }

        RSInterface.setBounds(id, 0, 0 + y, scroll_frame, scroll);
        scroll_frame++;
        id++;
        return scroll;
	}
	
	@Override
	public void onDisplay() {
		
	}
	
	@Override
	public void onClose() {
		
	}
	
	@Override
	public String getName() {
		return "Grand Exchange Search";
	}
	
}
