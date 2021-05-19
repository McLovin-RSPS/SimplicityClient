package com.simplicity.client.widget;

import java.awt.Point;
import java.util.ArrayList;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.Sprite;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.widget.dropdown.Dropdown;
import com.simplicity.client.widget.listener.*;

public abstract class CustomWidget {
    public static final int OR1 = 0xFFB000;

    public int frame;

    public int id;
    
    public WidgetButtonListener buttonListener;
    public WidgetStateListener stateListener;
    public WidgetStringListener stringListener;
    public WidgetAnimationListener animListener;
    public WidgetContainerListener containerListener;
    public WidgetEnterInputListener enterInputListener;
    public WidgetInputFieldListener inputFieldListener;
    
    public ArrayList<WidgetComponent> components;

    public int mainId;

    public CustomWidget(int id) {
        this.mainId = id;
        this.id = id + 1;
        this.components = new ArrayList<WidgetComponent>();
    }
    
    public void addButtonListener(WidgetButtonListener listener) {
    	this.buttonListener = listener;
    }
    
    public void addStateListener(WidgetStateListener listener) {
    	this.stateListener = listener;
    }
    
    public void addStringListener(WidgetStringListener listener) {
    	this.stringListener = listener;
    }
    
    public void addAnimListener(WidgetAnimationListener listener) {
    	this.animListener = listener;
    }

    public void addContainerListener(WidgetContainerListener listener) {
        this.containerListener = listener;
    }

    public void addEnterInputListener(WidgetEnterInputListener listener) {
        this.enterInputListener = listener;
    }

    public void addInputFieldListener(WidgetInputFieldListener listener) {
        this.inputFieldListener = listener;
    }
    
    public abstract String getName();

    public abstract void init();
    
    public void add(RSInterface widget, int x, int y) {
        widget.componentId = widget.id;

        if (RSInterface.interfaceCache[widget.componentId] != null) {
            if (Widget.componentForMain.get(widget.componentId) != null) {
                // System.out.println("CustomWidget: Conflicting ID " +
                // widget.componentId + " @ " + getName() + " with: "
                // + Widget.componentForMain.get(widget.componentId));
            } else {
                // System.out.println(
                // "CustomWidget: Conflicting ID " + widget.componentId + " @ "
                // + getName() + " " + widget.type);
            }
            // return;
        }

        Widget.componentForMain.put(widget.componentId, mainId);

        WidgetComponent component = new WidgetComponent(new Point(x, y), widget);
        component.componentId = widget.componentId;
        component.parentId = mainId;
        components.add(component);
    }
    
    public void add(WidgetComponent component, int x, int y) {
    	Widget.componentForMain.put(component.componentId, mainId);
    	components.add(component);
    }

    public void addCenteredX(RSInterface widget, int y) {
        int x = 256 - (widget.width / 2);

        add(widget, x, y);
    }

    public void addWidget(int id, int x, int y) {
        Widget.componentForMain.put(id, mainId);

        WidgetComponent component = new WidgetComponent(new Point(x, y), RSInterface.interfaceCache[id]);
        component.componentId = id;
        component.parentId = mainId;
        components.add(component);
    }
    
    public RSInterface addTimer(int width, int height, int fontId, int textColor, String defaultText) {
    	RSInterface rsi = RSInterface.addTimer(id, width, height, fontId, textColor, defaultText);
    	rsi.componentId = id++;
    	return rsi;
    }
    
    public RSInterface addBox(int width, int height, int border, int borderColor, int color, int transparency) {
    	RSInterface tab = RSInterface.addTabInterface(id);
        tab.componentId = id;
        tab.parentID = id;
        tab.id = id;
        tab.type = 34;
		tab.width = width;
		tab.height = height;
		tab.borderWidth = border;
		tab.borderColor = borderColor;
		tab.disabledColor = color;
		tab.transparency = transparency;
		id++;
    	return tab;
    }
    
    public RSInterface addBox(int width, int height, int border, int borderColor, int color, int transparency, int fillColor, int fillTransparency) {
    	RSInterface tab = RSInterface.addTabInterface(id);
        tab.componentId = id;
        tab.parentID = id;
        tab.id = id;
        tab.type = 34;
		tab.width = width;
		tab.height = height;
		tab.borderWidth = border;
		tab.borderColor = borderColor;
		tab.disabledColor = color;
		tab.transparency = transparency;
		tab.fillColor = fillColor;
		tab.customOpacity = fillTransparency;
		tab.filled = true;
		id++;
    	return tab;
    }
    
    public RSInterface addRectangle(int width, int height, int colour, int alpha, boolean filled) {
    	RSInterface rsi = RSInterface.addRectangle(id, width, height, colour, alpha, filled);
    	rsi.componentId = id++;
    	return rsi;
    }

    public RSInterface fillRectangle(int width, int height, int colour, int alpha) {
        RSInterface rsi = RSInterface.fillRectangle(id, width, height, colour, alpha);
        rsi.componentId = id++;
        return rsi;
    }
    
	public RSInterface addVerticalLine(int h, int color, int transparency) {
		RSInterface r = RSInterface.addInterface(id);
		r.componentId = id;
		r.id = id;
		r.parentID = id;
		r.type = 27;
		r.width = 1;
		r.height = h;
		r.disabledColor = color;
		r.transparency = transparency;
		id++;
		return r;
	}

	public RSInterface addHorizontalLine(int w, int color, int transparency) {
		RSInterface r = RSInterface.addInterface(id);
		r.componentId = id;
		r.id = id;
		r.parentID = id;
		r.type = 28;
		r.width = w;
		r.height = 1;
		r.disabledColor = color;
		r.transparency = transparency;
		id++;
		return r;
	}
    
    public RSInterface addBackground(int background) {
        return addBackground(background, getName());
    }

    public RSInterface addBackground(int background, String title) {

        RSInterface tab = RSInterface.addTabInterface(id);
        tab.componentId = id;
        id++;

        Sprite sprite = Client.cacheSprite[background];

        int x = (512 - sprite.myWidth) / 2;
        int y = (334 - sprite.myHeight) / 2;

        int frame = 0;

        tab.totalChildren(3);

        RSInterface.addSprite(id, background);
        tab.child(frame++, id, x, y);
        id++;

        int closeX = sprite.myWidth - 73;

        if (closeX < 260) {
            closeX = 330;
        }

        RSInterface.addTextClose(40000, RSInterface.fonts);
        tab.child(frame++, 40000, closeX, y + 12);
        id++;

        RSInterface.addText(id, "@or1@" + title, RSInterface.fonts, 2, 0xFFFFFF, true, true);
        tab.child(frame++, id, 256, y + 11);
        id++;
        return tab;
    }
    
	/**
	 * Adds a closable window
	 * 
	 * @param width        The width.
	 * @param height       The height.
	 * @param modernBorder Whether to use a modern border or not.
	 * @param title        The title.
	 * @return The closable window.
	 */
    public RSInterface addClosableWindow(int width, int height, boolean modernBorder, String title) {
    	RSInterface tab = RSInterface.addInterface(id, width, height);
    	tab.componentId = id++;
    	
    	tab.totalChildren(4);
    	
    	int child = 0;
    	
    	RSInterface window = addWindow(width, height, false);
    	RSInterface titles = addCenteredText(title, 2, 0xFF981F);
    	RSInterface close = addButton(1068, 1069, "Close Window", 250, 3);
    	RSInterface separator = addHorizontalSeparator(width - 10, modernBorder);
    	
    	tab.child(child++, window.id, 0, 0);
    	tab.child(child++, titles.id, width / 2, 10);
    	tab.child(child++, close.id, width - (modernBorder ? 26 : 28), modernBorder ? 10 : 7);
    	tab.child(child++, separator.id, 5, 29);
    	return tab;
    }
    
	/**
	 * Adds a window.
	 * 
	 * @param width        The width.
	 * @param height       The height.
	 * @param modernBorder Whether to use the modern border or not.
	 * @return The window inside a wrapper.
	 */
    public RSInterface addWindow(int width, int height, boolean modernBorder) {
    	RSInterface window = RSInterface.addWindow(id, width, height, modernBorder);
    	window.componentId = id++;
    	return addWrapper(width, height, window);
    }

	/**
	 * Adds a horizontal separator.
	 * 
	 * @param width        The width.
	 * @param modernBorder Whether to use the modern border or not.
	 * @return The separator inside a wrapper.
	 */
    public RSInterface addHorizontalSeparator(int width, boolean modernBorder) {
    	RSInterface rsi = RSInterface.addInterface(id);
    	rsi.componentId = id++;
    	rsi.type = 25;
    	rsi.width = width;
    	rsi.height = 6;
    	rsi.modernWindow = modernBorder;
    	return addWrapper(width, 6, rsi);
    }
    
	/**
	 * Adds a wrapper around the specified interface.
	 * 
	 * @param width  The width.
	 * @param height The height.
	 * @param rsi    The interface.
	 * @return The wrapper.
	 */
    public RSInterface addWrapper(int width, int height, RSInterface rsi) {
    	RSInterface wrapper = RSInterface.addInterface(id, width, height);
    	wrapper.componentId = id++;
    	wrapper.totalChildren(1);
    	wrapper.child(0, rsi.componentId, 0, 0);
    	return wrapper;
    }
    
    public RSInterface addRectangleClickable(int opacity, int color, boolean filled, int width, int height,
                                             String... actions) {
        RSInterface tab = RSInterface.addTabInterface(id);
        tab.componentId = id;
        tab.parentID = id;
        tab.id = id;
        tab.disabledColor = color;
        tab.filled = filled;
        tab.type = 3;
        tab.atActionType = 5;
        tab.contentType = 0;
        tab.opacity = (byte) opacity;
        tab.width = width;
        tab.height = height;
        tab.atActionType = 1;
        tab.actions = actions;
        id++;
        return tab;
    }

    public RSInterface addNpc(int entityId) {
        RSInterface tab = RSInterface.addInterface(id);
        tab.componentId = id;
        tab.id = id;
        tab.parentID = id;
        tab.type = 6;
        tab.atActionType = 0;
        tab.contentType = 3291;
        tab.mediaType = 5;
        tab.enabledMediaType = 2;
//        tab.modelFileId = entityId;
        tab.mediaID = entityId;
        tab.width = 136;
        tab.height = 168;
        tab.opacity = 0;
        tab.hoverType = 0;
        tab.modelZoom = 575;
        tab.modelRotation1 = 150;
        tab.modelRotation2 = 0;
        tab.disabledAnimationId = -1;
        tab.enabledAnimationId = -1;
        id++;
        return tab;
    }

    public RSInterface addSprite(int spriteId) {
        RSInterface tab = RSInterface.addInterface(id);
        tab.componentId = id;
        tab.id = id;
        tab.parentID = id;
        tab.componentId = id;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.hoverType = 52;

        Sprite sprite = Client.cacheSprite[spriteId];

        tab.enabledSprite = sprite;
        tab.disabledSprite = sprite;
        tab.width = 512;
        tab.height = 334;
        id++;
        return tab;
    }
    
	/**
	 * Adds an opaque hover button.
	 * 
	 * @param iconId           The icon sprite id.
	 * @param hoverOpacity     The hover opacity.
	 * @param tooltip          The tooltip.
	 * @return The RSInterface.
	 */
    public RSInterface addHoverOpacityButton(int spriteId, int hoverOpacity, String tooltip) {
    	RSInterface rsi = RSInterface.addInterface(id);
    	rsi.componentId = id;
    	rsi.id = id;
    	rsi.type = 47;
    	rsi.disabledSprite = Client.cacheSprite[spriteId];
    	rsi.enabledSprite = Client.cacheSprite[spriteId];
    	rsi.width = rsi.disabledSprite.myWidth;
    	rsi.height = rsi.disabledSprite.myHeight;
    	rsi.hoverOpacity = hoverOpacity;
    	rsi.hoverType = id;
    	rsi.tooltip = tooltip;
    	rsi.contentType = 0;
    	rsi.atActionType = 1;
    	return rsi;
    }
    
	/**
	 * Adds a button with an opaque icon on it when hovered.
	 * 
	 * @param backgroundSprite The background sprite id.
	 * @param iconId           The icon sprite id.
	 * @param iconX            The icon x.
	 * @param iconY            The icon y.
	 * @param hoverOpacity     The hover opacity.
	 * @param tooltip          The tooltip.
	 * @return The RSInterface.
	 */
    public RSInterface addHoverOpacityButton(int backgroundSprite, int iconId, int iconX, int iconY, int hoverOpacity, String tooltip) {
    	RSInterface rsi = RSInterface.addInterface(id);
    	rsi.componentId = id;
    	rsi.id = id;
    	rsi.type = 45;
    	rsi.disabledSprite = Client.cacheSprite[backgroundSprite];
    	rsi.enabledSprite = Client.cacheSprite[iconId];
    	rsi.width = rsi.disabledSprite.myWidth;
    	rsi.height = rsi.disabledSprite.myHeight;
    	rsi.hoverIconX = iconX;
    	rsi.hoverIconY = iconY;
    	rsi.hoverOpacity = hoverOpacity;
    	rsi.hoverType = id++;
    	rsi.tooltip = tooltip;
    	rsi.contentType = 0;
    	rsi.atActionType = 1;
    	return rsi;
    }

    public RSInterface addSpriteRepeatX(int spriteId, int width) {
        return RSInterface.addSpriteRepeatX(id++, spriteId, width);
    }

    public RSInterface addSpriteRepeatY(int spriteId, int height) {
        return RSInterface.addSpriteRepeatY(id++, spriteId, height);
    }

    public RSInterface addSpriteRepeatBoth(int spriteId, int width, int height) {
        return RSInterface.addSpriteRepeatBoth(id++, spriteId, width, height);
    }

    public RSInterface drawBox(int width, int height, int border, int color, int transparency) {
        return RSInterface.drawBox(id++, width, height, border, 0, color, transparency);
    }

    public RSInterface drawBox(int width, int height, int border, int borderColor, int color, int transparency) {
        return RSInterface.drawBox(id++, width, height, border, borderColor, color, transparency);
    }

    public RSInterface configButton(String tooltip, int enabledSprite, int disabledSprite) {
        return RSInterface.configButton(id++, tooltip, enabledSprite, disabledSprite);
    }

    public RSInterface hoverButton(int disabledSprite, int enabledSprite, String[] actions) {
        return RSInterface.hoverButton(id++, disabledSprite, enabledSprite, actions);
    }

    public RSInterface addConfigButton(String tooltip, int sprite1, int sprite2, int aT, int configSlot, int configId) {
        RSInterface tab = RSInterface.addInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.type = 5;
        tab.atActionType = aT;
        tab.componentId = id;
        tab.contentType = 0;
        Sprite sprite = Client.cacheSprite[sprite1];
        tab.width = sprite.myWidth;
        tab.height = sprite.myHeight;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.valueCompareType = new int[1];
        tab.requiredValues = new int[1];
        tab.valueCompareType[0] = 1;
        tab.requiredValues[0] = configSlot;
        tab.valueIndexArray = new int[1][3];
        tab.valueIndexArray[0][0] = 5;
        tab.valueIndexArray[0][1] = configId;
        tab.valueIndexArray[0][2] = 0;
        tab.disabledSprite = sprite;
        tab.enabledSprite = Client.cacheSprite[sprite2];
        tab.tooltip = tooltip;
        return tab;
    }

    public RSInterface addPercentageBar(int percentageDimension, int percentageTotal, int percentageSpriteEmpty,
                                        int percentageSpriteFull, boolean verticleBar) {
        System.out.println(getName() + " percentage bar: " + id);
        RSInterface tab = RSInterface.addInterface(id);
        tab.id = id;
        tab.parentID = id;
        tab.componentId = id;
        tab.type = 20;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = 0;
        tab.hoverType = 0;
        tab.percentageCompleted = 100;
        tab.percentageDimension = percentageDimension;
        tab.percentageSpriteFull = percentageSpriteFull;
        tab.percentageSpriteEmpty = percentageSpriteEmpty;
        tab.percentageTotal = percentageTotal;
        tab.verticleBar = verticleBar;
        tab.width = 0;
        tab.height = 0;
        id++;
        return tab;
    }

    public RSInterface addProgressBar(int width, int height) {
        return addProgressBar(width, height, 0, 100);
    }

    public RSInterface addProgressBar(int width, int height, int current, int max) {
        RSInterface rsi = RSInterface.addProgressBar(id, width, height, current, max);
        rsi.componentId = id++;
        return rsi;
    }

    public RSInterface addProgressSprite(int backgroundSprite, int fillSprite, int current, int max) {
    	RSInterface rsi = RSInterface.addProgressSprite(id, backgroundSprite, fillSprite, current, max);
    	rsi.componentId = id++;
    	return rsi;
    }

    public RSInterface addItemContainer(int w, int h, int x, int y, String[] actions, String string) {
        System.out.println(getName() + " container: " + string + ": " + id);
        RSInterface tab = RSInterface.addInterface(id);
        tab.componentId = id;
        tab.width = w;
        tab.height = h;
        tab.inv = new int[w * h];
        tab.invStackSizes = new int[w * h];
        for (int i = 0; i < tab.inv.length; i++) {
            tab.inv[i] = 996;
            tab.invStackSizes[i] = Integer.MAX_VALUE;
        }
        tab.usableItemInterface = false;
        tab.invSpritePadX = x;
        tab.invSpritePadY = y;
        tab.spritesX = new int[20];
        tab.spritesY = new int[20];
        tab.sprites = new Sprite[20];
        if (actions != null) {
            tab.actions = new String[5];
            tab.actions = actions;
        }
        tab.type = 2;
        id++;
        return tab;
    }
    
    public RSInterface addModel(int modelID, int zoom, int rotation1, int rotation2, DataType dataType) {
    	RSInterface rsi = RSInterface.addModel(id, modelID, zoom, rotation1, rotation2, dataType);
    	rsi.componentId = id;
    	id++;
    	return rsi;
    }
    
    public RSInterface addModelSprite(int width, int height, int modelID, int zoom, int rotation1, int rotation2, DataType dataType) {
    	RSInterface rsi = RSInterface.addModelSprite(id, width, height, modelID, zoom, rotation1, rotation2, dataType);
    	rsi.componentId = id;
    	id++;
    	return rsi;
    }
    
    public RSInterface addTooltip(String message, int width, int height, int offsetX, int offsetY) {
    	RSInterface rsi = RSInterface.addInterface(id);
    	rsi.componentId = id++;
    	rsi.tooltipBox = message;
    	rsi.tooltipOffsetX = offsetX;
    	rsi.tooltipOffsetY = offsetY;
    	rsi.width = width;
    	rsi.height = height;
    	return rsi;
    }
    
   public RSInterface addToggleButton(String tooltip, int enabledSprite, int disabledSprite) {
		RSInterface tab = RSInterface.addTabInterface(id);
		tab.componentId = id;
		tab.tooltip = tooltip;
		tab.atActionType = 1;
		tab.type = 19;
		tab.enabledSprite = Client.cacheSprite[enabledSprite];
		tab.disabledSprite = Client.cacheSprite[disabledSprite];
		tab.width = tab.enabledSprite.myWidth;
		tab.height = tab.enabledSprite.myHeight;
		tab.active = false;
		id++;
		return tab;
    }
   
	public RSInterface addToggleButton(String tooltip, int enabledSprite, int disabledSprite, int width, int height,
			boolean center) {
		RSInterface tab = RSInterface.addTabInterface(id);
		tab.componentId = id;
		tab.tooltip = tooltip;
		tab.atActionType = 1;
		tab.type = 19;
		tab.enabledSprite = Client.cacheSprite[enabledSprite];
		tab.disabledSprite = Client.cacheSprite[disabledSprite];
		tab.centerButton = center;
		tab.width = width;
		tab.height = height;
		tab.active = false;
		id++;
		return tab;
	}
	
	public RSInterface addSelectableToggleButton(String tooltip, int enabledSprite, int disabledSprite, int width, int height,
			boolean center, boolean active, boolean selected) {
		RSInterface tab = RSInterface.addTabInterface(id);
		tab.componentId = id;
		tab.tooltip = tooltip;
		tab.atActionType = 1;
		tab.type = 40;
		tab.enabledSprite = Client.cacheSprite[enabledSprite];
		tab.disabledSprite = Client.cacheSprite[disabledSprite];
		tab.centerButton = center;
		tab.width = width;
		tab.height = height;
		tab.active = active;
		tab.selected = selected;
		id++;
		return tab;
	}
    
    public RSInterface addButton(int disabledSprite, int enabledSprite, String tooltip, int contentType, int actionType) {
    	RSInterface tab = RSInterface.addTabInterface(id);
    	tab.componentId = id;
    	id++;
    	
    	int frame = 0;
    	tab.totalChildren(2);
    	
        Sprite sprite = Client.cacheSprite[disabledSprite];

        int width = sprite.myWidth;
        int height = sprite.myHeight;

        tab.width = width;
        tab.height = height;
        
        RSInterface.addHoverButton(id, disabledSprite, width, height, tooltip, contentType, id + 1, actionType);
        RSInterface.addHoveredButton(id + 1, enabledSprite, width, height, id + 2);
        tab.child(frame++, id, 0, 0);
        tab.child(frame++, id + 1, 0, 0);
        id += 3;
        
        return tab;
    }

    public RSInterface addButton(String name, int sprite1, int sprite2, int textSize, int colour) {
        RSInterface tab = RSInterface.addTabInterface(id);
        tab.componentId = id;
        id++;

        int frame = 0;

        tab.totalChildren(3);

        Sprite sprite = Client.cacheSprite[sprite1];

        int width = sprite.myWidth;
        int height = sprite.myHeight;

        tab.width = width;
        tab.height = height;

        RSInterface.addHoverButton(id, sprite1, width, height, "Select @lre@" + name, -1, id + 1, 5);
        RSInterface.addHoveredButton(id + 1, sprite2, width, height, id + 2);
        tab.child(frame++, id, 0, 0);
        tab.child(frame++, id + 1, 0, 0);
        id += 3;

        int textHeight = 13 + textSize;

        if (name.contains("#")) {
            name = "" + id;
        }

        int textDecrease = textSize > 1 ? -1 : 1;

        RSInterface.addText(id, textSize == -1 ? "" : name, RSInterface.fonts, textSize == -1 ? 0 : textSize, colour,
                true, true);
        tab.child(frame++, id, width / 2,
                ((height / 2) - (textHeight / 2)) + (textSize == 0 ? +textDecrease : -textDecrease));
        id++;
        return tab;
    }
    
	/**
	 * Adds a dynamic button with a text attached to it.
	 * 
	 * @param name      The name.
	 * @param textSize  The text size.
	 * @param textColor The text color.
	 * @param width     The width.
	 * @param height    The height.
	 * @return RSInterface for chaining.
	 */
    public RSInterface addDynamicButton(String name, int textSize, int textColor, int width, int height) {
    	RSInterface rsi = RSInterface.addInterface(id, width, height);
    	rsi.componentId = id++;
    	rsi.totalChildren(2);
    	
    	RSInterface button = RSInterface.addInterface(id, width, height);
    	button.componentId = id++;
    	button.type = 41;
    	button.atActionType = 1;
    	button.contentType = 0;
    	button.tooltip = name;
    	button.hovers = true;
    	
    	int textHeight = 13 + textSize;
    	
    	int textDecrease = textSize > 1 ? -1 : 1;
    	
    	RSInterface text = addText(name, textSize, textColor, true, false, false, true);
    	rsi.child(0, button.id, 0, 0);
    	rsi.child(1, text.id, width / 2, ((height / 2) - (textHeight / 2)) + (textSize == 0 ? +textDecrease : -textDecrease));

    	Widget.componentForMain.put(button.id, mainId);
    	Widget.componentForMain.put(text.id, mainId);
    	return rsi;
    }
    
    public RSInterface addButtonList(String[] list, int sprite1, int sprite2, int textSize, int colour, int offset,
                                     boolean verticle) {
        RSInterface tab = RSInterface.addTabInterface(id);
        tab.componentId = id;
        id++;

        tab.totalChildren(list.length);

        Sprite sprite = Client.cacheSprite[sprite1];

        int height = sprite.myHeight + 1;
        int width = sprite.myWidth + 1;

        int frame = 0;

        int y = 0;
        int x = 0;

        for (String s : list) {
            RSInterface button = addButton(s, sprite1, sprite2, textSize, colour);
            tab.child(frame++, button.componentId, x, y);
            if (verticle) {
                y += height + offset;
            } else {
                x += width + offset;
            }
        }
        id++;
        return tab;
    }
    
    public RSInterface addField(int width, int height, String text, String tooltip) {
    	RSInterface rsi = RSInterface.addInterface(id);
    	rsi.componentId = id++;
    	rsi.type = 42;
    	rsi.width = width;
    	rsi.height = height;
    	rsi.message = text;
    	rsi.atActionType = 5;
    	rsi.contentType = 0;
    	rsi.atActionType = 1;
    	rsi.tooltip = tooltip;
    	return rsi;
    }
    
    public RSInterface addScrollbarWithText(String text, String tooltip, int size, int colour, int scrollHeight,
                                            int scrollWidth, int lineAmount) {
        RSInterface scroll = RSInterface.addInterface(id);
        scroll.componentId = id;
        id++;
        scroll.totalChildren(lineAmount);
        scroll.height = scrollHeight;
        scroll.width = scrollWidth;
        scroll.scrollMax = scrollHeight + (lineAmount * (11 + size));
        int scroll_frame = 0;

        int y = 5;

        for (int i = 0; i < lineAmount; i++) {
            RSInterface.addHoverText(id, text + (text.contains("#") ? "" + id : ""), tooltip, RSInterface.fonts, size,
                    colour, false, true, 150);
            RSInterface.setBounds(id, 5, y, scroll_frame, scroll);
            scroll_frame++;
            id++;
            y += 12 + size;
        }
        return scroll;
    }

    public RSInterface addScrollbarWithItem(int w, int h, int x, int y, String[] action, int scrollHeight,
                                            int scrollWidth) {
        RSInterface scroll = RSInterface.addInterface(id);
        scroll.componentId = id;
        id++;

        scroll.totalChildren(1);
        scroll.height = scrollHeight;
        scroll.width = scrollWidth;
        scroll.scrollMax = 35 * h;
        int scroll_frame = 0;

        System.out.println(getName() + " container: " + id);
        RSInterface item = RSInterface.addToItemGroup(id, w, h, x, y, action == null ? false : true, "", "", "");

        for (int i = 0; i < item.inv.length; i++) {
            item.inv[i] = 996;
            item.invStackSizes[i] = Integer.MAX_VALUE;
        }

        item.actions = new String[5];

        for (int i = 0; i < item.actions.length; i++) {
            item.actions[i] = null;
        }

        if (action != null) {
            for (int i = 0; i < action.length; i++) {
                item.actions[i] = action[i];
            }
        }

        RSInterface.setBounds(id, 5, 2 + y, scroll_frame, scroll);
        scroll_frame++;
        id++;
        return scroll;
    }

    public RSInterface addClickText(String text, int idx, int color, boolean center, boolean shadowed) {
        RSInterface tab = RSInterface.addInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.componentId = id;
        tab.type = 4;
        tab.atActionType = 1;
        tab.width = RSInterface.fonts[idx].getTextWidth(text);
        tab.height = idx == 0 ? 12 : 15;
        tab.contentType = 0;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.centerText = center;
        tab.shadowed = shadowed;
        tab.textDrawingAreas = RSInterface.fonts[idx];
        tab.message = text + " " + (text.contains("#") ? id : "");
        tab.tooltip = "Select @lre@" + text;
        tab.disabledColor = color;
        id++;
        return tab;
    }

    public RSInterface addClickText(String text, int idx, int color) {
        return addClickText(text, idx, color, true, true);
    }

    public RSInterface addClickText(String text, int idx, int color, boolean center) {
        return addClickText(text, idx, color, center, true);
    }

    public RSInterface addText(String text, int size, int color) {
        return addText(text, size, color, false, false, false, true);
    }

    public RSInterface addText(String text, int size, int color, boolean center) {
        return addText(text, size, color, center, false, false, true);
    }

    public RSInterface addCenteredText(String text, int size, int color) {
        return addText(text, size, color, true, false, false, true);
    }

    public RSInterface addText(String text, int size) {
        return addText(text, size, CustomWidget.OR1, false, false, false, true);
    }

    public RSInterface addCenteredText(String text, int size) {
        return addText(text, size, CustomWidget.OR1, true, false, false, true);
    }
    
    public RSInterface addRightAlignedText(String text, int size) {
        return addText(text, size, CustomWidget.OR1, true, true, false, true);
    }
    
    public RSInterface addRightAlignedText(String text, int size, int color) {
        return addText(text, size, color, false, true, false, true);
    }

    private RSInterface addText(String text, int idx, int color, boolean center, boolean rightAligned,
                               boolean rollingText, boolean shadow) {
        RSInterface tab = RSInterface.addTabInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.componentId = id;
        tab.type = 4;
        tab.atActionType = 0;
        tab.width = 0;
        tab.height = idx == 0 ? 12 : 15;
        tab.contentType = 0;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.centerText = center;
        tab.rightAlignText = rightAligned;
        tab.shadowed = shadow;
        tab.textDrawingAreas = RSInterface.fonts[idx];
        tab.message = text.contains("#") ? text + ":" + id : text;
        tab.disabledColor = color;
        id++;
        return tab;
    }

    public RSInterface addTextList(String[] list, int textSize, int colour, boolean center, int offset,
                                   boolean verticle) {
        RSInterface tab = RSInterface.addTabInterface(id);
        tab.componentId = id;
        id++;

        tab.totalChildren(list.length);

        int frame = 0;

        int y = 0;
        int x = 0;

        int length = offset;

        for (String s : list) {

            if(offset == -1) {
                length = RSInterface.fonts[textSize].getTextWidth(s) + 4;
            }

            RSInterface button = addText(s, textSize, colour, center, false, false, true);
            tab.child(frame++, button.componentId, x, y);

            if (verticle) {
                y += length;
            } else {
                x += length;
            }
        }
        id++;
        return tab;
    }

    public RSInterface addGroup(WidgetGroup group) {
        RSInterface scroll = RSInterface.addInterface(id);
        scroll.componentId = id;

        id++;

        int single = 0;

        if (group.singleComponents != null) {
            single = group.singleComponents.size();
        }

        scroll.totalChildren(single + (group.lines * group.multipleComponents.size()));

        scroll.height = group.scrollHeight;
        scroll.width = group.scrollWidth;

        int scrollMax = (group.difference / 2) + (group.difference * group.lines);

        if (scrollMax < group.scrollHeight) {
            scrollMax = group.scrollMax;
        }

        scroll.scrollMax = scrollMax;

        int scroll_frame = 0;

        int y = 0;

        for (int i = 0; i < group.lines; i++) {
            for (WidgetComponent w : group.multipleComponents) {
                RSInterface rs = RSInterface.addInterface(id);
                rs.copy(w.componentId);
                RSInterface.setBounds(rs.id, w.point.x + group.offsetX, w.point.y + y + group.offsetY, scroll_frame,
                        scroll);
                scroll_frame++;
                id++;
            }
            y += group.difference;
        }

        id++;

        if (group.singleComponents != null) {
            for (WidgetComponent w : group.singleComponents) {
                RSInterface rs = RSInterface.addInterface(id);
                rs.copy(w.componentId);
                RSInterface.setBounds(rs.id, w.point.x + group.offsetX, w.point.y + group.offsetY, scroll_frame,
                        scroll);
                scroll_frame++;
                id++;
            }
        }
        return scroll;
    }

    public RSInterface addStoneButton(int width, int height, int color, int hoverColor, String text) {
        return RSInterface.addStoneButton(id++, width, height, color, hoverColor, text);
    }

    public RSInterface dropdownMenu(int width, int defaultOption, String[] options, Dropdown d) {
        return RSInterface.dropdownMenu(id++, width, defaultOption, options, d);
    }

    public RSInterface dropdownMenu(int width, int defaultOption, String[] options, Dropdown d, int[] dropdownColours, boolean centerText) {
        return RSInterface.dropdownMenu(id++, width, defaultOption, options, d, dropdownColours, centerText);
    }

    public RSInterface addInputField(int characterLimit, int defaultColor, int defaultHoverColor, int selectedColor, int selectedHoverColor, String text, int width, int height, boolean onlyNumbers, boolean asterisks, String defaultText) {
        return RSInterface.addInputField(mainId, id++, characterLimit, defaultColor, defaultHoverColor, selectedColor, selectedHoverColor, text, width, height, onlyNumbers, asterisks, defaultText);
    }

    public RSInterface addInputField(int characterLimit, int defaultColor, int defaultHoverColor, int selectedColor, int selectedHoverColor, String text, int width, int height, boolean onlyNumbers, boolean asterisks, String defaultText, String[] actions) {
        return RSInterface.addInputField(mainId, id++, characterLimit, defaultColor, defaultHoverColor, selectedColor, selectedHoverColor, text, width, height, onlyNumbers, asterisks, defaultText, actions);
    }
    
    public RSInterface getInterface() {
        return RSInterface.interfaceCache[mainId];
    }

    public static RSInterface getWidget(int widgetId) {
        return RSInterface.interfaceCache[widgetId];
    }
    
}
