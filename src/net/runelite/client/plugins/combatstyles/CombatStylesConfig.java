package net.runelite.client.plugins.combatstyles;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("combatstyles")
public interface CombatStylesConfig extends Config {
    @ConfigItem(keyName = "alwaysShowStyle", name = "Always show style", description = "Show attack style indicator at all times", position = 1)
    default boolean alwaysShowStyle() {
        return true;
    }
}