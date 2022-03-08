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

    public void updateSettings() {
        try {
            data.values().forEach(list -> list.forEach(SettingObject::update));
        } catch (Exception e) {
            System.out.println("Failed to update settings for " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    public void updateSetting(String key) {
        try {
            data.values().forEach(list -> {
                list.forEach(obj -> {
                    if (obj.getKey().equals(key)) {
                        obj.update();
                    }
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
