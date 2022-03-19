package com.simplicity.client.widget.settings;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.Widget;
import com.simplicity.client.widget.listener.WidgetButtonListener;
import com.simplicity.client.widget.listener.WidgetInputFieldListener;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SettingsWidget extends CustomWidget implements WidgetButtonListener, WidgetInputFieldListener {

    public static final int WIDGET_ID = 101_000;

    private static int searchField;

    private int containerIndex;
    private int searchWidgetId;

    public SettingsWidget() {
        super(WIDGET_ID);
    }

    private static int[] buttonIds;
    private static int[] settingGroupWidgets;

    @Override
    public void init() {
        addButtonListener(this);
        addInputFieldListener(this);
        add(addClosableWindow(512, 331, false, true, true, getName()), 0, 1);
        add(addDynamicButton("Show <col=ffffff>less</col> information", 1, 0xff981f, 150, 20), 7, 8);
        add(addText("Search: ", 1, 0xff981f).setSize(85, 35), 11 + 18, 41 + 5);
        add(addSprite(1988), 92, 42);
        add(addSpriteRepeatX(1989, 389), 96, 42);
        add(addSprite(1990), 485, 42);

        add(addRectangle(85, 253, 0, 0, false), 7, 72);
        add(addRectangle(398, 253, 0, 0, false), 91, 72);

        buttonIds = IntStream.range(id, id + Settings.values().length).toArray();

        for (int i = 0; i < Settings.values().length; i++) {
            int[] buttonsToDisable = Arrays.stream(buttonIds).filter(buttonId -> buttonId != id).toArray();
            add(button(Settings.values()[i].toString(), false, buttonsToDisable), 11, 76 + i * 26);
        }

        searchField = id;
        add(addTextInput(399, 25, "Search", 32), 92, 42);

        settingGroupWidgets = new int[Settings.values().length];

        for (int i = 0; i < settingGroupWidgets.length; i++) {
            int groupWidgetId = 105_000 + (i * 1000);
            Widget.init(new SettingGroupWidget(Settings.values()[i], groupWidgetId));
            settingGroupWidgets[i] = groupWidgetId;
        }

        searchWidgetId = 105_000 + (settingGroupWidgets.length * 1000);
        Widget.init(new SettingGroupWidget(null, searchWidgetId));

        containerIndex = components.size();
        addWidget(settingGroupWidgets[0], 0, 72);
    }

    private RSInterface button(String text, boolean active, int[] buttonsToDisable) {
        return configHoverButton(text, 1991, 1991, 1992, 1992, active, text, Client.getClient().newRegularFont, 0xff981f, 0xffffff, true, buttonsToDisable);
    }

    @Override
    public String getName() {
        return "Settings";
    }

    @Override
    public boolean onClick(int id) {
        for (int i = 0; i < buttonIds.length; i++) {
            if (id != buttonIds[i]) {
                continue;
            }

            setComponent(containerIndex, settingGroupWidgets[i]);
            Client.getClient().resetTextInputField();
        }

        return true;
    }

    @Override
    public void onUpdateInputField(int widgetId, String text) {
        if (widgetId == searchField) {
            searchWidget().handleSearch(text.toLowerCase());
        }
    }

    @Override
    public void onResetInputField(int widgetId) {

    }

    @Override
    public void onInputFocus(int widgetId) {
        for (int button : buttonIds) {
            RSInterface rsi = getWidget(button);

            if (rsi.active) {
                rsi.active = false;
                RSInterface.configHoverButtonSwitch(rsi);
            }
        }

        getWidget(widgetId).active = true;
        setComponent(containerIndex, searchWidgetId);
        searchWidget().displaySearch(null, true);
    }

    private SettingGroupWidget searchWidget() {
        return Widget.get(searchWidgetId);
    }

}
