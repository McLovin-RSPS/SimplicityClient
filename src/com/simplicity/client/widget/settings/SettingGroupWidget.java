package com.simplicity.client.widget.settings;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.Widget;
import com.simplicity.client.widget.settings.objects.SettingObject;
import com.simplicity.client.widget.settings.objects.impl.KeybindingSetting;
import com.simplicity.client.widget.settings.objects.impl.LegacySliderSetting;
import com.simplicity.client.widget.settings.objects.impl.SliderSetting;
import com.simplicity.util.Alignment;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SettingGroupWidget extends CustomWidget {

    private final Settings settings;

    public SettingGroupWidget(Settings settings, int id) {
        super(id);
        this.settings = settings;

        if (settings != null) {
            this.settings.getGroup().data.clear();
            this.settings.getGroup().init();
        }
    }

    @Override
    public void init() {
        getWidget(mainId).setSize(489, 253);

        if (settings != null) {
            displaySearch(settings.getGroup().data, false);
        }
    }

    public void handleSearch(String term) {
        if (term.isEmpty()) {
            displaySearch(null, true);
            return;
        }

        final Map<String, List<SettingObject>> settings = new HashMap<>();

        for (Settings setting : Settings.values()) {
            for (Map.Entry<String, List<SettingObject>> entry : setting.getGroup().data.entrySet()) {
                final String section = entry.getKey();
                final List<SettingObject> objects = entry.getValue();

                if (section.toLowerCase().startsWith(term)) {
                    settings.put(section, objects);
                } else {
                    objects.forEach(obj -> {
                        final String name = obj.getName().toLowerCase();

                        if (name.startsWith(term) || name.contains(term)) {
                            final List<SettingObject> resultList = settings.getOrDefault(section, new ArrayList<>());
                            resultList.add(obj);
                            settings.put(section, resultList);
                        }
                    });
                }
            }
        }

        displaySearch(settings, true);
    }

    public void displaySearch(Map<String, List<SettingObject>> results, boolean init) {
        if (init) {
            clear();
        }

        int y = 42;
        int w = getWidget(mainId).width / 2 + 46;

        if (results == null) {
            add(addText("To search for a setting, type what setting you are looking for.", 1, 0xff981f, true), w, y);
            add(addText("The menu will update based on what you have typed into the", 1, 0xff981f, true), w, y += 16);
            add(addText("search bar.", 1, 0xff981f, true), w, y += 14);
            add(addText("You can press the DEL key to clear your current search text.", 1, 0xff981f, true), w, y += 32);
            add(addText("To cancel the search, select one of the settings tabs.", 1, 0xff981f, true), w, y += 32);
        } else if (results.isEmpty()) {
            y = 42;
            add(addText("Could not find any settings that match what you are looking for.", 1, 0xff981f, true), w, y);
            add(addText("Try searching for something else.", 1, 0xff981f, true), w, y += 16);
        } else {
            y = 0;
            int idx = 0;
            for (Map.Entry<String, List<SettingObject>> entry : results.entrySet()) {
                String section = entry.getKey();
                List<SettingObject> list = entry.getValue();

                add(addRectangle(387, 24, 0, idx % 2 == 0 ? 200 : 225, true), 97, 4 + y);
                add(addText(section, 2, 0xffffff, true), 289, 8 + y);
                y += 16;

                idx++;

                for (SettingObject setting : list) {
                    int width = 387;
                    int height = 12;
                    int lineBreaks = StringUtils.countMatches(setting.getDescription(), "\\n");

                    if (lineBreaks > 0) {
                        height += lineBreaks * 13;
                    }

                    if (setting instanceof SliderSetting || setting instanceof LegacySliderSetting) {
                        height += 24;
                    }

                    int descYOffset = 0;

                    final RSInterface text = addText(setting.getName(), 2, 0xff981f).setSize(width, 14);
                    final RSInterface desc = addText(setting.getDescription(), 1, 0x9f9f9f).setSize(width, height);

                    if (setting.getTextAlignment() == Alignment.RIGHT) {
                        text.rightAlignText = true;
                    } else if (setting.getTextAlignment() == Alignment.CENTER) {
                        text.centerText = true;
                        desc.centerText = true;
                        descYOffset = 6;
                    }

                    if (setting instanceof KeybindingSetting) {
                        y += setting.draw(idx, y, width, height, this);
                    } else {
                        setting.draw(idx, y, width, height, this);
                        add(text, 102, 17 + y);
                        add(desc, 102, 31 + y + descYOffset);
                        y += 27 + height;
                    }


                    idx++;
                }

                y += 8;
            }
        }

        if (init) {
            Widget.init(this);
        }

        getWidget(mainId).scrollMax = Math.max(y + 10, 254);
    }

    @Override
    public String getName() {
        return settings != null ? settings.name() : "Search";
    }

}
