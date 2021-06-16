package com.simplicity.client.widget.ge;

import static com.simplicity.client.widget.ge.GrandExchange.BUY_BUTTONS;
import static com.simplicity.client.widget.ge.GrandExchange.SELL_BUTTONS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.Widget;
import com.simplicity.client.widget.listener.WidgetButtonListener;
import com.simplicity.client.widget.listener.WidgetContainerListener;

public class GrandExchangeMainWidget extends CustomWidget implements WidgetButtonListener, WidgetContainerListener {

	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 93_000;
	
	/**
	 * The offset from empty to a valid offer.
	 */
	private static final int EMPTY_OFFER_OFFSET = 90;
	
	/**
	 * A hash collection of header strings.
	 */
	private Map<Integer, Integer> HEADER_STRINGS = new HashMap<>();
	
	/**
	 * A list of container ids.
	 */
	public static final List<Integer> CONTAINER_IDS = new ArrayList<>();
	
	private static int HISTORY_BUTTON_ID;
	private static int LISTINGS_BUTTON_ID;
	
	/**
	 * Constructs a new {@link GrandExchangeMainWidget}.
	 */
	public GrandExchangeMainWidget() {
		super(WIDGET_ID);
	}
	
	@Override
	public void init() {
		addButtonListener(this);
		addContainerListener(this);
		add(addClosableWindow(484, 304, false, "Grand Exchange"), 14, 15);
		add(addText("Select an offer slot to set up or view an offer.", 0, 0xff981f, true), 248, 59);

		int xPos = 23;
		int yPos = 79;

		int emptyStart = id + 1;

		for (int i = 0; i < 8; i++) {
			add(addSprite(1791), xPos, yPos);
			add(addText("Empty", 2, 0xff981f, true), xPos + 55, yPos + 4);

			xPos += 117;

			if (i == 3) {
				xPos = 23;
				yPos += 120;
			}
		}

		xPos = 23;
		yPos = 79;

		for (int i = 0; i < 8; i++) {
			HEADER_STRINGS.put(id, emptyStart + i * 2);
			add(addEmpty(i), xPos, yPos);

			id += 7;
			xPos += 117;

			if (i == 3) {
				xPos = 23;
				yPos += 120;
			}
		}

		xPos = 23; // 27
		yPos = 79; // 111

		for (int i = 0; i < 8; i++) {
			add(addOffer(), xPos, yPos);

			id += 7;
			xPos += 117;

			if (i == 3) {
				xPos = 23;
				yPos += 119; // 119
			}
		}

		HISTORY_BUTTON_ID = id;
		add(addStoneButton(47, 20, 0xff981f, 0xffffff, "History"), 21, 22);

		LISTINGS_BUTTON_ID = id;
		add(addStoneButton(47, 20, 0xff981f, 0xffffff, "Listings"), 21 + 47 + 3, 22);
	}
	
	/**
	 * Adds an offer container.
	 * 
	 * @return The container.
	 */
	private RSInterface addOffer() {
		RSInterface rsi = RSInterface.addInterface(id);
		rsi.hidden = true;
		rsi.totalChildren(4);
		
		RSInterface.addRectangleClickable(id + 1, 255, 0xffffff, true, 115, 110, "View offer");
		RSInterface.interfaceCache[id + 1].setLayer(mainId);
		RSInterface.interfaceCache[id + 1].parentID = rsi.id;
		RSInterface.interfaceCache[id + 1].hovers = true;
		RSInterface.interfaceCache[id + 1].hoverType = id + 1;
		RSInterface.interfaceCache[id + 1].enabledOpacity = 245;
		RSInterface.interfaceCache[id + 1].enabledColor = 0xffffff;
		RSInterface.interfaceCache[id + 1].enabledMouseOverColor = 0xffffff;
		
		RSInterface container = RSInterface.addContainer(id + 2, 0, 1, 1, new String[0]);
		container.componentId = container.id;
		container.itemExamine = false;
		container.invBack = Client.cacheSprite[1806];
		Widget.componentForMain.put(container.componentId, mainId);
		CONTAINER_IDS.add(container.componentId);
		
		RSInterface.addGeProgress(id + 3, 107, 15);
		RSInterface.addText(id + 4, "", RSInterface.fonts, 0, 0xff981f, true, true).setSize(109, 18);
		RSInterface.interfaceCache[id + 4].parentID = rsi.id;
		
		int xPos = 0;
		int yPos = 0;
		int child = 0;
		
		rsi.child(child++, id + 1, xPos, yPos);
		
		xPos += 4;
		yPos += 32;
		
		rsi.child(child++, id + 2, xPos + 2, yPos + 2);
		rsi.child(child++, id + 3, xPos, yPos + 42);
		rsi.child(child++, id + 4, xPos, yPos + 60);
		id += 4;
		return rsi;
	}
	
	/**
	 * Adds an empty slot container.
	 * 
	 * @param slot The slot.
	 * @return The container.
	 */
	private RSInterface addEmpty(int slot) {
		RSInterface rsi = RSInterface.addInterface(id);
		rsi.totalChildren(4);
		
		RSInterface.drawBox(id + 2, 46, 45, 2, 0x5a5245, 0x383023, 255);
		RSInterface.drawBox(id + 3, 46, 45, 2, 0x5a5245, 0x383023, 255);
		RSInterface.hoverButton(id + 4, 1794, 1795, "Create <col=ff9040>Buy</col> Offer").setLayer(mainId);
		RSInterface.hoverButton(id + 5, 1792, 1793, "Create <col=ff9040>Sell</col> Offer").setLayer(mainId);
		
		Widget.componentForMain.put(id + 4, mainId);
		Widget.componentForMain.put(id + 5, mainId);
		BUY_BUTTONS.add(id + 4);
		SELL_BUTTONS.add(id + 5);
		
		int xPos = 6;
		int yPos = 43;
		
		int child = 0;
		rsi.child(child++, id + 2, xPos, yPos);
		rsi.child(child++, id + 3, xPos + 55, yPos);
		rsi.child(child++, id + 4, xPos + 7, yPos + 4);
		rsi.child(child++, id + 5, xPos + 59, yPos + 4);
		id += 4;
		return rsi;
	}
	
	@Override
	public boolean onClick(int id) {
		if (id == LISTINGS_BUTTON_ID) {
			//Client.getClient().setOpenInterfaceID(GrandExchangeListingsWidget.WIDGET_ID);
			return false;
		}
		
		if (BUY_BUTTONS.contains(id)) {
			RSInterface.interfaceCache[GrandExchangeOfferWidget.OFFER_TYPE_ID].message = "Buy offer";
			return false;
		} else if (SELL_BUTTONS.contains(id)) {
			RSInterface.interfaceCache[GrandExchangeOfferWidget.OFFER_TYPE_ID].message = "Sell offer";
			return false;
		}
		
		return false;
	}
	
	@Override
	public void onContainerUpdate(int id) {
		if (CONTAINER_IDS.contains(id)) {
			RSInterface container = getWidget(id);
			boolean contains = container.inv[0] > 0;
			
			RSInterface offer = getWidget(id - 2);
			offer.hidden = !contains;
			
			RSInterface empty = getWidget(id - EMPTY_OFFER_OFFSET);
			empty.hidden = contains;
			
			RSInterface header = getWidget(HEADER_STRINGS.get(empty.id));
			header.message = "Empty";
		}
	}
	
	@Override
	public String getName() {
		return "Grand Exchange";
	}

}
