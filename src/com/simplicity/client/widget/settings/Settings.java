package com.simplicity.client.widget.settings;

import com.simplicity.client.ClientSettings;
import com.simplicity.client.widget.settings.groups.SettingGroup;
import com.simplicity.client.widget.settings.groups.impl.*;
import com.simplicity.client.widget.settings.objects.SettingObject;
import com.simplicity.util.MiscUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Settings {

    AUDIO(new Audio()),
    CONTROLS(new Controls()),
    DISPLAY(new Display()),
    GAMEPLAY(new Gameplay()),
    INTERFACES(new Interfaces());

    public static final Map<String, Object> settings = new HashMap<>();

    /**
     * The group instance.
     */
    private SettingGroup group;

    /**
     * Gets the setting as an integer.
     *
     * @param key The key.
     * @return The setting as an integer.
     */
    public int getInt(String key) {
        return (int) settings.getOrDefault(key, 0);
    }

    /**
     * Gets the setting as string.
     *
     * @param key The key.
     * @return The setting as string.
     */
    public static String getString(String key) {
        return (String) settings.getOrDefault(key, "");
    }

    /**
     * Gets the setting as boolean.
     *
     * @param key The key.
     * @return The setting as boolean.
     */
    public static boolean getBoolean(String key) {
        return (boolean) settings.getOrDefault(key, false);
    }

    /**
     * Gets the setting.
     *
     * @param key The key.
     * @return The setting.
     */
    private static Object get(String key) {
        Object value = settings.get(key);

        if (value == null) {
            System.out.println("The key: " + key + " is not associated with any setting.");
            return null;
        }

        return value;
    }

    /**
     * Sets the specified setting.
     *
     * @param key   The key.
     * @param value The value to set.
     */
    public static void set(String key, Object value) {
        if (settings.get(key) == null) {
            System.out.println("The key: " + key + " is not associated with any setting.");
            return;
        }

        settings.put(key, value);
        ClientSettings.save();
    }

    /**
     * Loads the settings.
     */
    public void load() {

    }

    /**
     * Saves the settings.
     */
    public void save() {

    }

    @Override
    public String toString() {
        return MiscUtils.capitalize(name().toLowerCase());
    }

}
