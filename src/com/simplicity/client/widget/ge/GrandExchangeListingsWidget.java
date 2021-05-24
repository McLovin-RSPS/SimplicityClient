package com.simplicity.client.widget.ge;

import java.util.HashSet;
import java.util.Set;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.Widget;
import com.simplicity.client.widget.dropdown.Dropdown;
import com.simplicity.client.widget.listener.*;

public class GrandExchangeListingsWidget extends CustomWidget implements WidgetStringListener, WidgetStateListener, WidgetButtonListener, WidgetContainerListener, WidgetInputFieldListener {
	
	/**
	 * The widget id.
	 */
	public static final int WIDGET_ID = 95_600;
	
	public static int EXCHANGE_BUTTON_ID;
	public static int INPUT_FIELD_ID;
	
	public static Set<Integer> OFFER_BUTTONS = new HashSet<>();

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
		addInputFieldListener(this);
		
		add(addClosableWindow(484, 304, false, getName()), 14, 15);
		add(drawBox(149 + 293, 91 + 77 + 75, 1, 0x5a5245, 0x5a5245, 250), 40 - 4, 60 - 4);
		add(drawBox(149 + 293, 91 + 77 + 75, 1, 0x5a5245, 0x383023, 250), 39 - 4, 59 - 4);
		add(drawBox(149 + 293, 66 + 77 + 75, 1, 0x5a5245, 0x5a5245, 250), 40 - 4, 85 - 4);
		add(drawBox(149 + 293, 66 + 77 + 75, 1, 0x5a5245, 0x383023, 250), 39 - 4, 84 - 4);
		
		add(addText("Search item:", 2, 0xff981f, true), 110 - 27 - 1, 61 - 1);
		
		int inputWidth = 135;
		
		INPUT_FIELD_ID = id;
		add(addInputField(16, 0x473d32, 0x383023, 0x383023, 0x383023, "", inputWidth, 18, false, false, "Click to edit...", new String[] { "Edit" }), 131, 63 - 4);
		add(addHoverOpacityButton(1833, 150, "Clear"), 229 + 22, 63);
		
		add(addItemContainer(1, 1, 1, 1, null, null), 96 - 4, 92 - 4);
		
		add(addText("Type:", 2, 0xff981f), 198 - 4 + 90, 60);
		
		add(addText("Type", 0, 0xff981f), 55, 84);
		add(addText("Name", 0, 0xff981f), 223 - 4, 84);
		add(addText("Price", 0, 0xff981f), 378, 84);
		add(addSprite(1416), 82 - 1, 87);
		add(addSprite(1416), 230 - 1 + 20, 87);
		add(addSprite(1416), 386 - 1 + 22, 87);
		add(addListingsContainer(), 38, 87 - 4 + 15);
		
		add(addText("", 2, 0xffb83f, true).setSize(221, 20), 260 - 4, 177 - 1);
		
		add(dropdownMenu(150, 0, new String[] { "All offers", "Buy offers", "Sell offers" }, Dropdown.PLAYER_ATTACK_OPTION_PRIORITY, new int[] { 0x383023, 0x5a5245, 0x473d32, 0x383023, 0x787169 }, false), 194 + 80 + 51, 59);
		
		EXCHANGE_BUTTON_ID = id;
		add(addStoneButton(57, 20, 0xff981f, 0xffffff, "Exchange"), 21, 22);
	}

	public RSInterface addListingsContainer() {
		int lineAmount = 10;
		int scrollWidth = 422;
		int scrollHeight = 197;
		
		int rectHeight = 29;
		
        RSInterface scroll = RSInterface.addInterface(id);
        scroll.componentId = id;
        id++;
        scroll.totalChildren(lineAmount * 5);
        scroll.height = scrollHeight;
        scroll.width = scrollWidth;
        scroll.scrollMax = lineAmount * (rectHeight - 1);
        int scroll_frame = 0;

		int xPos = 0;
		int yPos = 0;
		
		for (int i = 0; i < lineAmount; i++) {
			OFFER_BUTTONS.add(id);
			Widget.componentForMain.put(id, mainId);
			RSInterface.addRectangleClickable(id, 100, 0x5a5245, false, scrollWidth - 1, rectHeight, new String[] { "Make Buy-Offer" });
			RSInterface.setBounds(id++, xPos, yPos, scroll_frame++, scroll);
			
			RSInterface.fillRectangle(id, scrollWidth - 3, rectHeight - 2, 0, i % 2 == 0 ? 30 : 60);
			RSInterface.setBounds(id++, xPos + 1, yPos + 1, scroll_frame++, scroll);

			RSInterface.addText(id, "<spr=" + (i % 2 == 0 ? 1804 : 1805) + ":-2> " + (i % 2 == 0 ? "Buying" : "Selling"), 1, 0xff981f);
			RSInterface.interfaceCache[id].useNewFonts = true;
			RSInterface.setBounds(id++, xPos + 3, yPos + 6, scroll_frame++, scroll);
			
			RSInterface.addText(id, "1 x Abyssal whip", 1, 0xff981f);
			RSInterface.setBounds(id++, xPos + 150, yPos + 6, scroll_frame++, scroll);
			
			RSInterface.addText(id, "120,000 coins", 1, 0xff981f);
			RSInterface.setBounds(id++, xPos + 310, yPos + 6, scroll_frame++, scroll);
			
			yPos += rectHeight - 1;
		}

        return scroll;
	}

	@Override
	public void onStringUpdate(int id, String string) {
		
	}
	
	@Override
	public void onDisplay() {
		getWidget(WIDGET_ID + 4).message = getName();
	}
	
	@Override
	public void onClose() {
		if (Client.getClient().isSearchingGe()) {
			Client.getClient().endInputDialogue();
		}
	}
	
	@Override
	public boolean onClick(int id) {
		if (id == EXCHANGE_BUTTON_ID || id == WIDGET_ID + 11) {
			Client.getClient().setOpenInterfaceID(GrandExchangeMainWidget.WIDGET_ID);
			return false;
		}
		
		if (id == WIDGET_ID + 26) {
			GrandExchange.showSearch();
			return false;
		}
		
		if (id == WIDGET_ID + 17) {
			if (RSInterface.currentInputField != null && !RSInterface.currentInputField.message.isEmpty()) {
				getWidget(WIDGET_ID + 4).message = getName();
				RSInterface.currentInputField.message = "";
				RSInterface.currentInputField = null;
			}
			return true;
		}
		
		if (OFFER_BUTTONS.contains(id)) {
			String type = getWidget(id + 2).message;
			String item = getWidget(id + 3).message;
			String price = getWidget(id + 4).message;
			
			Client.getClient().setOpenInterfaceID(GrandExchangeOfferWidget.WIDGET_ID);
			GrandExchangeOfferWidget widget = Widget.get(GrandExchangeOfferWidget.WIDGET_ID);
			widget.makeBuyOffer(4151, 1, 120_000);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onContainerUpdate(int id) {
		
	}
	
	@Override
	public void onUpdateInputField(int widgetId, String text) {
		if (widgetId == INPUT_FIELD_ID) {
			getWidget(WIDGET_ID + 4).message = (text.isEmpty() ? getName() : "<col=ff981f>Showing results for: <col=ff0000>" + text);
		}
	}
	
	@Override
	public void onResetInputField(int widgetId) {
		if (widgetId == INPUT_FIELD_ID) {
			getWidget(WIDGET_ID + 4).message = getName();
		}
	}
	
	@Override
	public String getName() {
		return "Grand Exchange: Recent Listings";
	}

	@Override
	public void onInputFocus(int widgetId) {
		// TODO Auto-generated method stub
		
	}

}
