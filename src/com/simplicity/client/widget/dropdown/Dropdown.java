package com.simplicity.client.widget.dropdown;

import com.simplicity.Configuration;
import com.simplicity.client.RSInterface;
import com.simplicity.client.content.Keybinding;

public enum Dropdown {
	
	ITEM_STATS() {
		@Override
        public void selectOption(int selected, RSInterface dropdown) {
			System.out.println("selected: " + selected);
			if (selected == 0) {
				Configuration.enableItemStats = 2;
			} else if (selected == 1) {
				Configuration.enableItemStats = 1;
			} else if (selected == 2) {
				Configuration.enableItemStats = 0;
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
        	Configuration.playerAttackOptionPriority = selected;
        }
    },

    NPC_ATTACK_OPTION_PRIORITY() {
        @Override
        public void selectOption(int selected, RSInterface dropdown) {
        	Configuration.npcAttackOptionPriority = selected;
        }
    };

    private Dropdown() {
    }
    
    public abstract void selectOption(int selected, RSInterface dropdown);
}
