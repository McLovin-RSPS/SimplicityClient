package com.simplicity.client.widget.dropdown;

import com.simplicity.client.Client;
import lombok.Getter;

import java.util.function.Consumer;

public class DropdownMenu {

    private final int height;
    private final int width;
    private final String[] options;
    private final Dropdown dropdown;
    private final boolean split;
    private boolean open;
    @Getter private String optionSelected;
    public Consumer<Integer> onSelect;

    public DropdownMenu(int width, boolean split, int defaultOption, String[] options, Dropdown d) {
        this(width, split, defaultOption, options, d, null);
    }

    public DropdownMenu(int width, boolean split, int defaultOption, String[] options, Dropdown d, Consumer<Integer> onSelect) {
        this.width = width;
        this.height = split ? ((14 * options.length) / 2) + 3 : (14 * options.length) + 3;
        this.options = options;
        this.optionSelected = defaultOption == -1 ? "Select an option" : options[defaultOption];
        this.open = false;
        this.dropdown = d;
        this.split = split;
        this.onSelect = onSelect;
    }

    public DropdownMenu(boolean split, int defaultOption, String[] options, Dropdown d, Consumer<Integer> onSelect) {
        this.width = getMaxWidth(options);
        this.height = split ? ((14 * options.length) / 2) + 3 : (14 * options.length) + 3;
        this.options = options;
        this.optionSelected = defaultOption == -1 ? "Select an option" : options[defaultOption];
        this.open = false;
        this.dropdown = d;
        this.split = split;
        this.onSelect = onSelect;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public String[] getOptions() {
        return this.options;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean b) {
        this.open = b;
    }
    
	public int getSelectedIndex() {
		for (int i = 0; i < options.length; i++) {
			if (options[i] == this.optionSelected) {
				return i;
			}
		}

		return -1;
	}

    public String getSelected() {
        return this.optionSelected;
    }

    public void setSelected(String s) {
        this.optionSelected = s;
    }

    public Dropdown getDrop() {
        return this.dropdown;
    }

    public boolean doesSplit() {
        return this.split;
    }

    public void reset() {
        this.setOpen(false);
        this.optionSelected = options[0];
    }

    private int getMaxWidth(String[] options) {
        int maxWidth = 0;

        for (String option: options) {
            int width = Client.getClient().newSmallFont.getTextWidth(option);
            maxWidth = Math.max(width, maxWidth);
        }

        return 36 + maxWidth;
    }
}
