package com.simplicity.client.widget.dropdown;

import static com.simplicity.Configuration.enableOldFrame;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.content.Keybinding;
import com.simplicity.client.widget.ge.GrandExchange;
import com.simplicity.client.widget.ge.GrandExchangeListingsWidget;
import com.simplicity.client.widget.settings.Setting;
import com.simplicity.client.widget.settings.Settings;

public enum Dropdown {

	GE_OFFER_TYPES() {
		@Override
		public void selectOption(int selected, RSInterface dropdown) {
			GrandExchangeListingsWidget.setTypeFilter(selected);
		}
	},
	
	;
    
    private Dropdown() {
    }
    
    public abstract void selectOption(int selected, RSInterface dropdown);
}
