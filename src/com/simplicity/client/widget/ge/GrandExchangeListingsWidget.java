package com.simplicity.client.widget.ge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.Widget;
import com.simplicity.client.widget.dropdown.Dropdown;
import com.simplicity.client.widget.listener.*;

public class GrandExchangeListingsWidget extends CustomWidget implements WidgetStringListener, WidgetStateListener, WidgetButtonListener, WidgetContainerListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 95_600;
	public static final int LISTINGS_AMOUNT = 50;
	public static final int RECT_HEIGHT = 40;
	private static final int WINDOW_TITLE = WIDGET_ID + 4;
	private static int LISTINGS_START;
	private static int LISTINGS_END;

	public static int EXCHANGE_BUTTON_ID;
	public static int SEARCH_ITEM_BUTTON;
	public static int RECENT_LISTINGS_BUTTON;

	public static int ITEM_CONTAINER_START;
	
	public static Set<Integer> OFFER_BUTTONS = new HashSet<>();

	public static int TYPE_FILTER = 0;
	public static RSInterface typeMenu;

	/**
	 * Constructs a new {@link GrandExchangeListingsWidget}.
	 */
	public GrandExchangeListingsWidget() {
		super(WIDGET_ID);
	}

	@Override
	public void init() {
		addStringListener(this);
		addStateListener(this);
		addButtonListener(this);
		addContainerListener(this);

		ITEM_CONTAINER_START = 96_800;

		add(addClosableWindow(484, 304, false, getName()), 14, 15);
		add(drawBox(149 + 293, 91 + 77 + 75, 1, 0x5a5245, 0x5a5245, 250), 40 - 4, 60 - 4);
		add(drawBox(149 + 293, 91 + 77 + 75, 1, 0x5a5245, 0x383023, 250), 39 - 4, 59 - 4);
		add(drawBox(149 + 293, 66 + 77 + 75, 1, 0x5a5245, 0x5a5245, 250), 40 - 4, 85 - 4);
		add(drawBox(149 + 293, 66 + 77 + 75, 1, 0x5a5245, 0x383023, 250), 39 - 4, 84 - 4);
		
		SEARCH_ITEM_BUTTON = id + 1;
		add(addDynamicButton("Search Item", 1, 0xff9800, 120, 22), 37, 61 - 4);
		add(addSprite(1799), 37 + 6, 61 - 3);

		add(addItemContainer(1, 1, 1, 1, null, null), 96 - 4, 92 - 4);
		
		add(addText("Type:", 2, 0xff981f), 198 - 4 + 90, 60);
		
		add(addText("Type", 0, 0xff981f), 55, 84);
		add(addText("Item", 0, 0xff981f), 223 - 4, 84);
		add(addText("Price", 0, 0xff981f), 378, 84);
		add(addSprite(1416), 82 - 1, 87);
		add(addSprite(1416), 230 - 1 + 20, 87);
		add(addSprite(1416), 386 - 1 + 22, 87);
		add(addListingsContainer(), 38, 87 - 4 + 15);
		
		add(addText("", 2, 0xffb83f, true).setSize(221, 20), 260 - 4, 177 - 1);
		typeMenu = dropdownMenu(150, 0, new String[] { "All offers", "Buy offers", "Sell offers" }, Dropdown.GE_OFFER_TYPES, new int[] { 0x383023, 0x5a5245, 0x473d32, 0x383023, 0x787169 }, false);
		add(typeMenu, 194 + 80 + 51, 59);
		
		EXCHANGE_BUTTON_ID = id;
		add(addStoneButton(57, 20, 0xff981f, 0xffffff, "Exchange"), 21, 22);

		RECENT_LISTINGS_BUTTON = id + 1;
		add(addDynamicButton("Show Recent", 1, 0xff9800, 115, 22), 37 + 123, 61 - 4);
		add(addSprite(1857), 37 + 7 + 123, 61);


		Widget.componentForMain.put(WINDOW_TITLE, mainId);
	}

	public RSInterface addListingsContainer() {
		int scrollWidth = 422;
		int scrollHeight = 197;

        RSInterface scroll = RSInterface.addInterface(id);
        scroll.componentId = id;
        id++;
        scroll.totalChildren(LISTINGS_AMOUNT * 8);
        scroll.height = scrollHeight;
        scroll.width = scrollWidth;
        scroll.scrollMax = LISTINGS_AMOUNT * (RECT_HEIGHT - 1);
        int scroll_frame = 0;

		int xPos = 0;
		int yPos = 0;

		LISTINGS_START = id;
		
		for (int i = 0; i < LISTINGS_AMOUNT; i++) {
			int parent = id;
			RSInterface.addRectangleClickable(id, 100, 0x5a5245, false, scrollWidth - 1, RECT_HEIGHT, new String[] { "Make Offer" });
			RSInterface.setBounds(id++, xPos, yPos, scroll_frame++, scroll);
			
			RSInterface.fillRectangle(id, scrollWidth - 3, RECT_HEIGHT - 2, 0, i % 2 == 0 ? 30 : 60);
			getWidget(id).parentID = parent;
			RSInterface.setBounds(id++, xPos + 1, yPos + 1, scroll_frame++, scroll);

			RSInterface.addText(id, "", 1, 0xff981f);
			getWidget(id).parentID = parent;
			getWidget(id).useNewFonts = true;
			RSInterface.setBounds(id++, xPos, yPos + 6, scroll_frame++, scroll);

			RSInterface.addText(id, "", RSInterface.fonts, 1, 0xff981f, true);
			getWidget(id).parentID = parent;
			RSInterface.setBounds(id++, xPos + 220, yPos + 4, scroll_frame++, scroll);

			RSInterface.addText(id,"", RSInterface.fonts, 0, 0xff981f, true);
			getWidget(id).parentID = parent;
			RSInterface.setBounds(id++, xPos + 360, yPos + 15, scroll_frame++, scroll);

			RSInterface.addText(id, "", 0, 0xff981f);
			getWidget(id).parentID = parent;
			RSInterface.setBounds(id++, xPos + 4, yPos + 25, scroll_frame++, scroll);

			RSInterface.addGeProgress(id, 127, 15, true);
			getWidget(id).parentID = parent;
			RSInterface.setBounds(id++, xPos + 156, yPos + 22, scroll_frame++, scroll);

			RSInterface.addItemContainer(ITEM_CONTAINER_START + i, new int[] {1, 1}, new int[] {1,1}, new String[] {}, false);
			getWidget(ITEM_CONTAINER_START + i).parentID = parent;
			RSInterface.setBounds(ITEM_CONTAINER_START + i, xPos + 116, yPos + 5, scroll_frame++, scroll);
			
			yPos += RECT_HEIGHT - 1;
		}

		LISTINGS_END = id;
        return scroll;
	}

	@Override
	public void onStringUpdate(int id, String string) {
		if (id == WINDOW_TITLE && string.contains("results")) {
			resetTypeFilter();
		}
	}
	
	@Override
	public void onDisplay() {

	}
	
	@Override
	public void onClose() {
		if (Client.getClient().isSearchingGe()) {
			Client.getClient().endInputDialogue();
		}

		getWidget(WIDGET_ID + 4).message = getName();
		clearText(LISTINGS_START, LISTINGS_END);
		resetTypeFilter();
	}
	
	@Override
	public boolean onClick(int id) {
		if (id == RECENT_LISTINGS_BUTTON) {
			resetTypeFilter();
		}

		if (id == WIDGET_ID + 26) {
			GrandExchange.showSearch();
			return false;
		}

		return false;
	}
	
	@Override
	public void onContainerUpdate(int id) {
		
	}

	@Override
	public String getName() {
		return "Grand Exchange: Recent Listings";
	}

	public static void resetTypeFilter() {
		typeMenu.dropdown.reset();

		int child = LISTINGS_START;

		for (int i = 0; i < LISTINGS_AMOUNT; i++) {
			for (int idx = 0; idx < 7; idx++) {
				RSInterface widget = getWidget(child + idx);
				widget.yOffset = 0;
				widget.hidden = false;
			}

			getWidget(ITEM_CONTAINER_START + i).yOffset = 0;
			getWidget(ITEM_CONTAINER_START + i).hidden = false;
			child += 7;
		}
	}

	public static void setTypeFilter(int selected) {
		TYPE_FILTER = selected;

		int child = LISTINGS_START;

		List<Integer> hiddenIdxs = new ArrayList<>();

		for (int i = 0; i < LISTINGS_AMOUNT; i++) {
			String type = getWidget(child + 2).message;
			boolean hide;

			if (TYPE_FILTER == 1) {
				hide = !type.contains("Buying");
			} else if (TYPE_FILTER == 2) {
				hide = !type.contains("Selling");
			} else {
				hide = type.isEmpty();
			}

			getWidget(child).hidden = hide;
			getWidget(ITEM_CONTAINER_START + i).hidden = hide;

			if (hide) {
				hiddenIdxs.add(i);
			}

			child += 7;
		}

		child = LISTINGS_START;

		int nextY = 0;

		for (int i = 0; i < LISTINGS_AMOUNT; i++) {
			if (hiddenIdxs.contains(i)) {
				nextY += 39;
			}

			for (int idx = 0; idx < 7; idx++) {
				getWidget(child + idx).yOffset = -nextY;
			}

			getWidget(ITEM_CONTAINER_START + i).yOffset = -nextY;
			child += 7;
		}

		setScroll(hiddenIdxs.size());
	}

	private static void setScroll(int amount) {
		int scroll = 198;
		int visible = LISTINGS_AMOUNT - amount;

		if (visible > 5) {
			scroll = visible * RECT_HEIGHT - 1;
		}

		getWidget(95627).scrollMax = scroll;
	}

}
