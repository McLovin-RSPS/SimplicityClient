package com.simplicity.client.widget.ge;

import java.text.NumberFormat;
import java.util.Locale;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.listener.*;
import com.simplicity.util.MiscUtils;

public class GrandExchangeOfferWidget extends CustomWidget implements WidgetStringListener, WidgetStateListener, WidgetButtonListener, WidgetContainerListener, WidgetEnterInputListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 93_500;
	
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
	public static final int QUANTITY_SET_CUSTOM = WIDGET_ID + 53;
	public static final int PRICE_DECREMENT_PERCENTAGE = WIDGET_ID + 55;
	public static final int PRICE_SET_GUIDE = WIDGET_ID + 56;
	public static final int PRICE_SET_CUSTOM = WIDGET_ID + 57;
	public static final int PRICE_INCREMENT_PERCENTAGE = WIDGET_ID + 58;

	/**
	 * The confirm button id.
	 */
	public static int CONFIRM;

	public static int OFFER_TYPE_ID;

	/**
	 * Constructs a new {@link GrandExchangeOfferWidget}.
	 */
	public GrandExchangeOfferWidget() {
		super(WIDGET_ID);
	}

	@Override
	public void init() {
		addStringListener(this);
		addStateListener(this);
		addButtonListener(this);
		addContainerListener(this);
		addEnterInputListener(this);
		
		add(addClosableWindow(484, 304, false, getName()), 14, 15);
		add(addHoverOpacityButton(1836, 150, "Back"), 35 - 4, 276 - 4);
		add(addSpriteRepeatX(1814, 472), 24 - 4, 237 - 4);
		add(drawBox(149, 91, 1, 0x5a5245,0x5a5245, 250), 40 - 4, 60 - 4);
		add(drawBox(149, 91, 1, 0x5a5245,0x383023, 250), 39 - 4, 59 - 4);
		add(drawBox(149, 66, 1, 0x5a5245, 0x5a5245, 250), 40 - 4, 85 - 4);
		add(drawBox(149, 66, 1, 0x5a5245, 0x383023, 250), 39 - 4, 84 - 4);
		add(drawBox(293, 91, 1, 0x5a5245,0x5a5245, 250), 189 - 4, 60 - 4);
		add(drawBox(293, 91, 1, 0x5a5245,0x383023, 250), 188 - 4, 59 - 4);
		add(drawBox(221, 77, 1, 0x5a5245,0x5a5245, 250), 40 - 4, 153 - 4);
		add(drawBox(221, 77, 1, 0x5a5245,0x383023, 250), 39 - 4, 152 - 4);
		add(drawBox(221, 77, 0, 0x5a5245,0x5a5245, 250), 261 - 4, 153 - 4);
		add(drawBox(221, 77, 0, 0x5a5245,0x383023, 250), 260 - 4, 152 - 4);

		OFFER_TYPE_ID = id;
		add(addText("Buy offer", 2, 0xff981f, true), 110, 61 - 1);
		
		add(configButton("-", 1804, 1805).setActionType(0), 164 - 4, 64 - 4);
		
		add(addText("<spr=1798:-5> 14,955", 0, 0xffb83f, true).setSize(114, 18), 39 + 4, 129);
		
		add(hoverButton(1806, 1807, "Choose item"), 94 - 4, 90 - 4);
		add(addSprite(1799), 94 - 4, 90 - 4);
		
		add(addItemContainer(1, 1, 1, 1, null, null), 96 - 4, 92 - 4);
		
		add(addText("Choose an item...", 2, 0xff981f), 198 - 4, 60);
		add(addText("Click the icon on the left to search for items.", 0, 0xffb83f), 199 - 4, 84);
		add(addText("Quantity:", 2, 0xff981f), 114, 154 - 1);
		add(addText("Price per item:", 2, 0xff981f), 318, 154 - 1);
		add(addHoverOpacityButton(1802, 100, "-1"), 44 - 4, 178 - 4);
		add(addHoverOpacityButton(1803, 100, "+1"), 235 - 4, 178 - 4);
		add(addSprite(1809), 69 - 4, 177 - 4);
		add(addSpriteRepeatX(1810, 154), 73 - 4, 177 - 4);
		add(addSprite(1811), 227 - 4, 177 - 4);

		add(addText("", 0, 0xffb83f, true).setSize(222, 20), 39 - 4, 177);
		
		add(addSprite(1809), 290 - 4, 177 - 4);
		add(addSpriteRepeatX(1810, 154), 294 - 4, 177 - 4);
		
		add(addSprite(1811), 447 - 4, 177 - 4);

		add(addText("", 0, 0xffb83f, true).setSize(221, 20), 260 - 4, 177);
		
		add(addHoverOpacityButton(1802, 100, "-1"), 265 - 4, 178 - 4);
		add(addHoverOpacityButton(1803, 100, "+1"), 456 - 4, 178 - 4);
		
		String[] buttons = new String[] { "+1", "+10", "+100", "+1k", "..." };
		
		int[] textX = new int[] { 7, 4, 0, 4, 8 };

		for (int i = 0; i < buttons.length; i++) {
			String action = i == buttons.length - 1 ? "Enter quantity" : buttons[i];
			add(hoverButton(1796, 1797, action), 49 + (i * 42) - 4, 195 - 4);
			add(addText(buttons[i], 0, 0xff981f), 49 + (i * 42) + textX[i], 206 - 4);
		}
		
		buttons = new String[] { "-5%", "Guide price", "Enter price", "+5%" };
		
		int[] buttonX = new int[] { 275, 332, 374, 431 };
		
		for (int i = 0; i < buttons.length; i++) {
			add(hoverButton(1796, 1797, buttons[i]), buttonX[i] - 4, 195 - 4);
		}
		
		
		add(addSprite(1800), 283 - 4, 203 - 4);
		add(addSprite(1798),  340 - 4, 203 - 4);
		add(addText("...", 0, 0xff981f), 374 + 8, 206 - 4);
		add(addSprite(1801), 439 - 4, 203 - 4);
		
		add(addSprite(1809), 154 - 4, 231 - 4);
		add(addSpriteRepeatX(1810, 204), 158 - 4, 231 - 4);
		add(addSprite(1811), 362 - 4, 231 - 4);
		add(addText("", 0, 0xffffff, true).setSize(472, 20), 24 - 4, 231);
		
		CONFIRM = id;
		add(hoverButton(1834, 1835, "Confirm"), 184 - 4, 268 - 4);
		add(addText("<col=ffffff>Confirm</col>", 2, 0xff981f, true).setSize(152, 40), 184 - 4, 268 + 12 - 4);
		
		/*addStoneButton(id, 47, 20, 0xff981f, 0xffffff, "History");
		rsi.child(child++, id++, 21, 22);*/
	}
	
	private boolean itemSelected = false;
	
	@Override
	public void onStringUpdate(int id, String string) {
		if (id == WIDGET_ID + 25) {
			RSInterface.interfaceCache[WIDGET_ID + 25].hidden = false;
		}
	}
	
	@Override
	public void onDisplay() {
		setItemSelected(false);
		
		boolean sell = getOfferType() == 1;
		
		if (!sell) {
			GrandExchange.showSearch();
		} else {
			int overlayId = GrandExchangeOverlayWidget.WIDGET_ID;
			
			RSInterface inventory = getWidget(3214);
			RSInterface overlay = getWidget(overlayId + 2);
			
			for (int i = 0; i < inventory.inv.length; i++) {
				overlay.inv[i] = inventory.inv[i];
				overlay.invStackSizes[i] = inventory.invStackSizes[i];
			}
			
			Client.getClient().tabInterfaceIDs[3] = overlayId;
		}
		
		RSInterface.interfaceCache[WIDGET_ID + 24].active = !sell;
		
		/** init **/
		RSInterface.interfaceCache[WIDGET_ID + 29].message = "Choose an item...";
		RSInterface.interfaceCache[WIDGET_ID + 30].message = "Click the icon on the left to search for items.";
		RSInterface.interfaceCache[WIDGET_ID + 26].atActionType = sell ? 0 : 1; // Action text of choose item
		RSInterface.interfaceCache[WIDGET_ID + 26].hoverDisabled = sell ? true : false;
		RSInterface.interfaceCache[WIDGET_ID + 27].hidden = sell ? true : false;
		RSInterface.interfaceCache[WIDGET_ID + 25].hidden = true; // Guide price
		RSInterface.interfaceCache[WIDGET_ID + 38].message = "";
		RSInterface.interfaceCache[WIDGET_ID + 42].message = "";
		RSInterface.interfaceCache[WIDGET_ID + 66].message = "";
		RSInterface.interfaceCache[WIDGET_ID + 52].message = sell ? " All" : "+1k";
	}
	
	public int getOfferType() {
		RSInterface offerType = RSInterface.interfaceCache[OFFER_TYPE_ID];
		boolean sell = offerType.message.startsWith("Sell");
		return sell ? 1 : 0;
	}
	
	@Override
	public void onClose() {
		if (Client.getClient().isSearchingGe()) {
			Client.getClient().endInputDialogue();
		}
		
		if (Client.getClient().getInventoryInterface() == 5063 && getOfferType() == 1) {
			Client.getClient().setInventoryInterface(-1);
		}

		Client.getClient().tabInterfaceIDs[3] = 3213;
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
			incrementQuantity(getOfferType() == 1 ? getSelectedInvAmount() : 1000);
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
		
		if (!itemSelected && (id == QUANTITY_SET_CUSTOM || id == PRICE_SET_CUSTOM || id == PRICE_SET_GUIDE)) {
			return true;
		}
		
		if (id == CONFIRM) {
			if (!itemSelected) {
				Client.getClient().pushMessage("Please choose an item.", 0, "");
				GrandExchange.toggleSearch(false);
				return true;
			}
			
			onConfirm();
			return true;
		}
		
		if (id == QUANTITY_SET_CUSTOM || id == PRICE_SET_CUSTOM) {
			Client.getClient().setWidgetInputDialog(1, id);
			return false;
		}
		
		return false;
	}
	
	@Override
	public void onContainerUpdate(int id) {
		if (id == SELECTED_CONTAINER) {
			if (!itemSelected) {
				setItemSelected(true);
			}
			
			ItemDefinition def = ItemDefinition.forID(RSInterface.interfaceCache[SELECTED_CONTAINER].inv[0] - 1);
			String desc = def == null || def.name == null ? "N/A" : "It's " + MiscUtils.getAOrAn(def.name) + " " + def.name + ".";
			RSInterface.interfaceCache[WIDGET_ID + 30].message = desc;
			
			GrandExchange.toggleSearch(false);
		}
	}
	
	@Override
	public void onEnterAmount(int widgetId, int amount) {
		if (widgetId == QUANTITY_SET_CUSTOM) {
			setQuantity(amount);
			return;
		}
		
		if (widgetId == PRICE_SET_CUSTOM) {
			setPrice(amount);
			return;
		}
	}
	
	@Override
	public String getName() {
		return "Grand Exchange: Set up offer";
	}
	
	public void makeBuyOffer(int itemId, int amount, int price) {
		setItemSelected(true);
		RSInterface.interfaceCache[OFFER_TYPE_ID].message = "Buy offer";
		getWidget(SELECTED_CONTAINER).inv[0] = itemId + 1;
		getWidget(SELECTED_CONTAINER).invStackSizes[0] = amount;
		setQuantity(amount);
		setPrice(price);
	}
	
	/**
	 * Gets the price.
	 * 
	 * @return The price.
	 */
	private int getPrice() {
		String message = getWidget(WIDGET_ID + 42).message.replaceAll(",", "");
		
		if (message.isEmpty()) {
			return -1;
		}
		
		return Integer.parseInt(message.substring(0, message.indexOf(" ")));
	}

	/**
	 * Increments the price by the specified amount.
	 * 
	 * @param amount The amount.
	 */
	private void incrementPrice(int amount) {
		setPrice(getPrice() + amount);
	}

	/**
	 * Decrements the price by the specified amount.
	 * 
	 * @param amount The amount.
	 */
	private void decrementPrice(int amount) {
		setPrice(getPrice() - amount);
	}

	/**
	 * Sets the price to the specified amount.
	 * 
	 * @param amount The amount.
	 */
	private void setPrice(int amount) {
		if (!itemSelected || amount <= 0) {
			return;
		}
		
		getWidget(WIDGET_ID + 42).message = formatCoins(amount);
		setTotal(getQuantity() * amount);
	}

	/**
	 * Gets the quantity.
	 * 
	 * @return The quantity.
	 */
	private int getQuantity() {
		String message = getWidget(WIDGET_ID + 38).message.replaceAll(",", "");
		
		if (message.isEmpty()) {
			return -1;
		}
		
		return Integer.parseInt(message);
	}
	
	/**
	 * Increments the quantity by the specified amount.
	 * 
	 * @param amount The amount.
	 */
	private void incrementQuantity(int amount) {
		setQuantity(getQuantity() + amount);
	}
	
	/**
	 * Decrements the quantity by the specified amount.
	 * 
	 * @param amount The amount.
	 */
	private void decrementQuantity(int amount) {
		setQuantity(getQuantity() - amount);
	}
	
	/**
	 * Sets the quantity to the specified amount.
	 * 
	 * @param amount The amount.
	 */
	private void setQuantity(int amount) {
		if (!itemSelected || amount <= 0) {
			return;
		}
		
		if (getOfferType() == 1) {
			int invAmount = getSelectedInvAmount();
			
			if (amount > invAmount) {
				amount = invAmount;
			}
		}
		
		getWidget(WIDGET_ID + 38).message = format(amount);
		setTotal(getPrice() * amount);
		getWidget(SELECTED_CONTAINER).invStackSizes[0] = amount;
	}
	
	/**
	 * Gets the total.
	 * @return The total.
	 */
	private long getTotal() {
		String message = getWidget(WIDGET_ID + 66).message.replaceAll(",", "");
		
		if (message.startsWith("<col=ff0000>")) {
			return -1;
		}
		
		return Long.parseLong(message.substring(0, message.indexOf(" ")));
	}
	
	/**
	 * Sets the total to the specified value.
	 * 
	 * @param total The total.
	 */
	private void setTotal(int total) {
		int price = getPrice();
		int quantity = getQuantity();
		long t = (long) price * (long) quantity;
		
		boolean limit = t > Integer.MAX_VALUE;
		
		if (limit) {
			if (confirmEnabled) {
				setConfirmButton(false);
			}
		} else {
			if (!confirmEnabled) {
				setConfirmButton(true);
			}
		}
		
		getWidget(WIDGET_ID + 66).message = limit ? "<col=ff0000>Too much money!</col>" : formatCoins(price * quantity);
	}
	
	/**
	 * Formats the specified amount.
	 * 
	 * @param amount The amount.
	 * @return The formatted string.
	 */
	private String format(int amount) {
		return NumberFormat.getInstance(Locale.UK).format(amount);
	}

	/**
	 * Formats the specified amount as coins.
	 * 
	 * @param amount The amount.
	 * @return The formatted string.
	 */
	private String formatCoins(int amount) {
		return format(amount) + " coins";
	}
	
	/**
	 * Toggles the selected item flag.
	 * 
	 * @param selected If the item was selected.
	 */
	private void setItemSelected(boolean selected) {
		itemSelected = selected;
		
		if (!selected) {
			RSInterface.interfaceCache[SELECTED_CONTAINER].inv[0] = 0;
		}
		
		setConfirmButton(selected);
	}
	
	/**
	 * A flag which indicates if the confirm button is enabled.
	 */
	private boolean confirmEnabled;
	
	/**
	 * Toggles the confirm button.
	 * 
	 * @param enabled if enabled.
	 */
	private void setConfirmButton(boolean enabled) {
		confirmEnabled = enabled;
		RSInterface.interfaceCache[CONFIRM].buttonDown = !enabled;
		RSInterface.interfaceCache[CONFIRM + 1].message = enabled ? "<col=ffffff>Confirm</col>" : "---";
	}
	
	/**
	 * Handles the confirm button action.
	 */
	private void onConfirm() {
		long total = getTotal();
		
		if (total < 0) {
			return;
		}
		
		if (total >= Integer.MAX_VALUE) {
			Client.getClient().pushMessage("Offers cannot have a total cost of exceeding 2,147,483,647 coins.", 0, "");
			return;
		}
		
		Client.getClient().stream.createFrame(204);
		Client.getClient().stream.writeByte(getOfferType());
		Client.getClient().stream.writeDWord(getPrice());
		Client.getClient().stream.writeDWord(getQuantity());
	}
	
	/**
	 * Gets the selected item.
	 * 
	 * @return The selected item.
	 */
	private int getSelectedItem() {
		return RSInterface.interfaceCache[SELECTED_CONTAINER].inv[0];
	}
	
	/**
	 * Gets the inventory amount for a selected sell offer.
	 * 
	 * @return The inventory amount.
	 */
	private int getSelectedInvAmount() {
		int amount = 0;
		
		RSInterface inventory = getWidget(3214);
		int[] itemIds = inventory.inv;
		int[] amounts = inventory.invStackSizes;
		
		for (int i = 0; i < itemIds.length; i++) {
			if (itemIds[i] == getSelectedItem()) {
				amount += amounts[i];
			}
		}
		
		return amount;
	}
	
}
