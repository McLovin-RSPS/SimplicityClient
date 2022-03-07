package com.simplicity.client.widget.settings.groups;

import com.simplicity.client.widget.settings.Settings;
import com.simplicity.client.widget.settings.objects.SettingObject;
import lombok.Getter;

import java.util.*;

public abstract class SettingGroup {

    @Getter public Map<String, List<SettingObject>> data = new HashMap<>();

    public abstract void init();

    public void add(String section, SettingObject setting) {
        final List<SettingObject> objects = data.getOrDefault(section, new ArrayList<>());
        objects.add(setting);
        data.put(section, objects);
        Settings.settings.put(setting.getKey(), setting.getValue());
    }

}
