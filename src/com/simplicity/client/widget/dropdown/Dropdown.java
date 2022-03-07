package com.simplicity.client.widget.dropdown;

import static com.simplicity.Configuration.enableOldFrame;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.content.Keybinding;
import com.simplicity.client.widget.ge.GrandExchange;
import com.simplicity.client.widget.ge.GrandExchangeListingsWidget;

public enum Dropdown {

	GE_OFFER_TYPES() {
		@Override
		public void selectOption(int selected, RSInterface dropdown) {
			GrandExchangeListingsWidget.setTypeFilter(selected);
		}
	},
	
	ITEM_STATS() {
		@Override
        public void selectOption(int selected, RSInterface dropdown) {
			if (selected == 0) {
				Configuration.enableItemStats = 2;
			} else if (selected == 1) {
				Configuration.enableItemStats = 1;
			} else if (selected == 2) {
				Configuration.enableItemStats = 0;
			}
        }
	},
	
	GAMEFRAME() {
		@Override
        public void selectOption(int selected, RSInterface dropdown) {
			if (selected != 2) {
				Configuration.enableOldschoolFrame = false;
			}
			
			if (selected == 0) {
				Configuration.enableOldFrame = true;
				
				if (Client.tabID == 13) {
                	Client.tabID = 15;
                }
			} else if (selected == 1) {
				Configuration.enableOldFrame = false;
				
				if (Client.tabID == 15) {
                	Client.tabID = 13;
                }
			} else if (selected == 2) {
				Configuration.enableOldFrame = true;
				Configuration.enableOldschoolFrame = true;
				
				if (Client.tabID == 15) {
                	Client.tabID = 13;
                }
			}
        }
	},

	HITMARKERS() {
		@Override
        public void selectOption(int selected, RSInterface dropdown) {
			if (selected == 0) {
				Configuration.enableOldHitmarkers = false;
			} else if (selected == 1) {
				Configuration.enableOldHitmarkers = true;
			}
        }
	},
	
	HPBARS() {
		@Override
        public void selectOption(int selected, RSInterface dropdown) {
			if (selected == 0) {
				Configuration.enableNewHpBars = true;
			} else if (selected == 1) {
				Configuration.enableNewHpBars = false;
			}
        }
	},

    KEYBIND_SELECTION() {
        @Override
        public void selectOption(int selected, RSInterface dropdown) {
        	Keybinding.bind((dropdown.id - Keybinding.MIN_FRAME) / 3, selected);
        }
    },
    
    PLAYER_ATTACK_OPTION_PRIORITY() {
        @Override
        public void selectOption(int selected, RSInterface dropdown) {
        }
    },

    NPC_ATTACK_OPTION_PRIORITY() {
        @Override
        public void selectOption(int selected, RSInterface dropdown) {
        }
    };

    private Dropdown() {
    }
    
    public abstract void selectOption(int selected, RSInterface dropdown);
}
