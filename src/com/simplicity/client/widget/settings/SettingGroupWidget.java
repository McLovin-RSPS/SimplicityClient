package com.simplicity.client.widget.settings;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.settings.objects.SettingObject;
import com.simplicity.client.widget.settings.objects.impl.SliderSetting;
import com.simplicity.util.Alignment;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SettingGroupWidget extends CustomWidget {

    private Settings settings;

    public SettingGroupWidget(Settings settings, int id) {
        super(id);
        this.settings = settings;
        this.settings.getGroup().init();
    }

    @Override
    public void init() {
        getWidget(mainId).setSize(489, 253);

        int y = 0;

        Map<String, List<SettingObject>> settings = this.settings.getGroup().getData();

        int idx = 0;

        for (Map.Entry<String, List<SettingObject>> entry : settings.entrySet()) {
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

                if (setting instanceof SliderSetting) {
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

                setting.draw(idx, y, width, height, this);
                add(text, 102, 17 + y);
                add(desc, 102, 31 + y + descYOffset);
                y += 27 + height;
                idx++;
            }

            y += 8;
        }

        getWidget(mainId).scrollMax = Math.max(y + 10, 254);
    }

    @Override
    public String getName() {
        return settings.name();
    }
}
