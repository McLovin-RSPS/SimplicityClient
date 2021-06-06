package com.simplicity.client.widget.ge;

import java.text.NumberFormat;
import java.util.Locale;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.WidgetButtonListener;
import com.simplicity.client.widget.listener.WidgetContainerListener;
import com.simplicity.client.widget.listener.WidgetStateListener;
import com.simplicity.client.widget.listener.WidgetStringListener;

public class GrandExchangeStatusWidget extends CustomWidget implements WidgetStringListener, WidgetStateListener, WidgetButtonListener, WidgetContainerListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 95_200;
	
	/**
	 * The selected container id.
	 */
	public static final int SELECTED_CONTAINER = WIDGET_ID + 28;
	
	public static final int QUANTITY_DECREMENT = WIDGET_ID + 33;
	public static final int QUANTITY_INCREMENT = WIDGET_ID + 34;
	public static final int QUANTITY_1 = WIDGET_ID + 45;
	public static final int QUANTITY_10 = WIDGET_ID + 47;
	public static final int QUANTITY_100 = WIDGET_ID + 49;
	public static final int QUANTITY_1000 = WIDGET_ID + 51;
	
	public static final int PRICE_DECREMENT = WIDGET_ID + 43;
	public static final int PRICE_INCREMENT = WIDGET_ID + 44;
	public static final int PRICE_DECREMENT_PERCENTAGE = WIDGET_ID + 55;
	public static final int PRICE_SET_GUIDE = WIDGET_ID + 56;
	public static final int PRICE_SET_CUSTOM = WIDGET_ID + 57;
	public static final int PRICE_INCREMENT_PERCENTAGE = WIDGET_ID + 58;
	
	private static int CONTAINER_ID;

	private int OFFER_TYPE_ID;
	private int GUIDE_PRICE_ID;
	private int ITEM_NAME_ID;
	private int ITEM_DESC_ID;
	private int QUANTITY_ID;
	private int PRICE_ID;
	private int TOTAL_ID;
	private int STATUS_STRING_ID;
	private int PROGRESS_BAR_ID;
	private int ABORT_OFFER_ID;
	public static int COLLECT_CONTAINER_ID;
	public static int COLLECT_AMOUNT_1;
	public static int COLLECT_AMOUNT_2;

	/**
	 * Constructs a new {@link GrandExchangeStatusWidget}.
	 */
	public GrandExchangeStatusWidget() {
		super(WIDGET_ID);
	}

	@Override
	public void init() {
		addStringListener(this);
		addStateListener(this);
		addButtonListener(this);
		addContainerListener(this);
		
		add(addClosableWindow(484, 304, false, getName()), 14, 15);
		add(addHoverOpacityButton(1808, 150, "Back"), 35 - 4, 276 - 4);
		add(addSpriteRepeatX(1814, 472), 24 - 4, 237 - 4);
		add(drawBox(149, 91, 1, 0x5a5245,0x5a5245, 250), 40 - 4, 60 - 4);
		add(drawBox(149, 91, 1, 0x5a5245,0x383023, 250), 39 - 4, 59 - 4);
		add(drawBox(149, 66, 1, 0x5a5245,0x5a5245, 250), 40 - 4, 85 - 4);
		add(drawBox(149, 66, 1, 0x5a5245,0x383023, 250), 39 - 4, 84 - 4);
		add(drawBox(293, 91, 1, 0x5a5245,0x5a5245, 250), 189 - 4, 60 - 4);
		add(drawBox(293, 91, 1, 0x5a5245,0x383023, 250), 188 - 4, 59 - 4);
		add(drawBox(221, 77, 1, 0x5a5245,0x5a5245, 250), 40 - 4, 153 - 4);
		add(drawBox(221, 77, 1, 0x5a5245,0x383023, 250), 39 - 4, 152 - 4);
		add(drawBox(221, 77, 0, 0x5a5245,0x5a5245, 250), 261 - 4, 153 - 4);
		add(drawBox(221, 77, 0, 0x5a5245,0x383023, 250), 260 - 4, 152 - 4);
		OFFER_TYPE_ID = id;
		add(addText("Buy offer", 2, 0xff981f, true), 110, 61 - 1);
		
		add(configButton("-", 1804, 1805).setActionType(0), 164 - 4, 64 - 4);
		
		GUIDE_PRICE_ID = id;
		add(addText("<spr=1798:-5> 14,955", 0, 0xffb83f, true).setSize(114, 18), 39 + 4, 129);
		
		add(addSprite(1806), 94 - 4, 90 - 4);
		
		CONTAINER_ID = id;
		add(addItemContainer(1, 1, 1, 1, null, null), 96 - 4, 92 - 4);
		
		ITEM_NAME_ID = id;
		add(addText("Choose an item...", 2, 0xff981f), 198 - 4, 60);
		
		ITEM_DESC_ID = id;
		add(addText("Click the icon on the left to search for items.", 0, 0xffb83f), 199 - 4, 84);
		add(addText("Quantity:", 2, 0xff981f), 114, 154 - 1);
		add(addText("Price per item:", 2, 0xff981f), 318, 154 - 1);
		
		add(addSprite(1809), 69 - 4, 177 - 4);
		add(addSpriteRepeatX(1810, 154), 73 - 4, 177 - 4);
		add(addSprite(1811), 227 - 4, 177 - 4);
		
		QUANTITY_ID = id;
		add(addText("", 2, 0xffb83f, true).setSize(222, 20), 39 - 4, 177 - 1);
		
		add(addSprite(1809), 290 - 4, 177 - 4);
		add(addSpriteRepeatX(1810, 154), 294 - 4, 177 - 4);
		
		add(addSprite(1811), 447 - 4, 177 - 4);
		
		PRICE_ID = id;
		add(addText("", 2, 0xffb83f, true).setSize(221, 20), 260 - 4, 177 - 1);
		
		add(addSprite(1809), 154 - 4, 231 - 4);
		add(addSpriteRepeatX(1810, 204), 158 - 4, 231 - 4);
		add(addSprite(1811), 362 - 4, 231 - 4);
		TOTAL_ID = id;
		add(addText("", 2, 0xffffff, true).setSize(472, 20), 24 - 4, 231 - 1);
		
		STATUS_STRING_ID = id;
		add(addText("", 0, 0xff981f, true).setSize(292, 44), 77 - 4, 254 + 10);
		
		PROGRESS_BAR_ID = id;
		add(addGeProgress(292, 15), 73, 231 + 63);
		
		ABORT_OFFER_ID = id;
		add(hoverButton(1812, 1813, "Abort offer"), 349 - 4, 266 - 4);
		add(addSpriteRepeatY(1840, 61), 364, 252);
		add(addSpriteRepeatBoth(1848, 116 - 8, 63 - 9), 380 + 4, 254 + 6);
		add(addSprite(1806), 394 - 2, 269 - 2);
		add(addSprite(1806), 445 - 2, 269 - 2);
		
		COLLECT_CONTAINER_ID = id;
		add(addItemContainer(2, 1, 19, 0, new String[] { "Collect-item", "Collect-note", "Bank" }, "status 1"), 396 - 2, 271 - 2);
		
		System.out.println("OFFER: " + OFFER_TYPE_ID);
		System.out.println("CONTAINER_ID: " + CONTAINER_ID);
		System.out.println("GUIDE_PRICE_ID: " + GUIDE_PRICE_ID);
		System.out.println("ITEM_NAME_ID: " + ITEM_NAME_ID);
		System.out.println("ITEM_DESC_ID: " + ITEM_DESC_ID);
		System.out.println("QUANTITY_ID: " + QUANTITY_ID);
		System.out.println("PRICE_ID: " + PRICE_ID);
		System.out.println("TOTAL_ID: " + TOTAL_ID);
		System.out.println("STATUS_STRING_ID: " + STATUS_STRING_ID);
		System.out.println("COLLECT_CONTAINER_ID: " + COLLECT_CONTAINER_ID);

		COLLECT_AMOUNT_1 = id;
		add(addText("", 0), -100, -100);
		COLLECT_AMOUNT_2 = id;
		add(addText("", 0), -100, -100);
	}

	@Override
	public void onStringUpdate(int id, String string) {
		if (id == PROGRESS_BAR_ID) {
			getWidget(ABORT_OFFER_ID).hidden = string.contains("-1") || string.equals("100/100");
		}
	}
	
	@Override
	public void onDisplay() {
		RSInterface offerType = RSInterface.interfaceCache[GrandExchangeOfferWidget.OFFER_TYPE_ID];
		boolean sell = offerType.message.startsWith("Sell");
		
		RSInterface.interfaceCache[WIDGET_ID + 24].active = !sell;
	}
	
	@Override
	public void onClose() {
		if (Client.getClient().isSearchingGe()) {
			Client.getClient().endInputDialogue();
		}
	}
	
	@Override
	public boolean onClick(int id) {
		if (id == WIDGET_ID + 26) {
			GrandExchange.showSearch();
			return false;
		}

		if (id == QUANTITY_DECREMENT) {
			decrementQuantity(1);
			return true;
		}
		
		if (id == QUANTITY_INCREMENT || id == QUANTITY_1) {
			incrementQuantity(1);
			return true;
		}
		
		if (id == QUANTITY_10) {
			incrementQuantity(10);
			return true;
		}
		
		if (id == QUANTITY_100) {
			incrementQuantity(100);
			return true;
		}
		
		if (id == QUANTITY_1000) {
			incrementQuantity(1000);
			return true;
		}
		
		if (id == PRICE_DECREMENT) {
			decrementPrice(1);
			return true;
		}
		
		if (id == PRICE_INCREMENT) {
			incrementPrice(1);
			return true;
		}
		
		if (id == PRICE_DECREMENT_PERCENTAGE) {
			setPrice((int) (getPrice() * 0.95));
			return true;
		}
		
		if (id == PRICE_INCREMENT_PERCENTAGE) {
			int price = getPrice();
			
			int incr = (int) (price * 1.05);
			
			if (incr == price) {
				incr++;
			}
			
			setPrice(incr);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onContainerUpdate(int id) {
	}
	
	private int getPrice() {
		String message = getWidget(WIDGET_ID + 42).message.replaceAll(",", "");
		
		if (message.isEmpty()) {
			return -1;
		}
		
		return Integer.parseInt(message.substring(0, message.indexOf(" ")));
	}
	
	private void incrementPrice(int amount) {
		setPrice(getPrice() + amount);
	}
	
	private void decrementPrice(int amount) {
		setPrice(getPrice() - amount);
	}
	
	private void setPrice(int price) {
		if (price <= 0) {
			return;
		}
		
		getWidget(WIDGET_ID + 42).message = formatCoins(price);
		setTotal(getQuantity() * price);
	}
	
	private int getQuantity() {
		String message = getWidget(WIDGET_ID + 38).message.replaceAll(",", "");
		
		if (message.isEmpty()) {
			return -1;
		}
		
		return Integer.parseInt(message);
	}
	
	private void incrementQuantity(int amount) {
		setQuantity(getQuantity() + amount);
	}
	
	private void decrementQuantity(int amount) {
		setQuantity(getQuantity() - amount);
	}
	
	private void setQuantity(int amount) {
		if (amount <= 0) {
			return;
		}
		
		getWidget(WIDGET_ID + 38).message = format(amount);
		setTotal(getPrice() * amount);
	}
	
	private void setTotal(int total) {
		int price = getPrice();
		int quantity = getQuantity();
		
		getWidget(TOTAL_ID).message = formatCoins(price * quantity);
	}
	
	private String format(int amount) {
		return NumberFormat.getInstance(Locale.UK).format(amount);
	}
	
	private String formatCoins(int amount) {
		return format(amount) + " coins";
	}
	
	@Override
	public String getName() {
		return "Grand Exchange: Offer status";
	}

}
