/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.plugins.runepouch;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup(value="runepouch")
public interface RunePouchConfig
extends Config {
    @ConfigItem(keyName="fontcolor", name="Font Color", description="Color of the font for the number of runes in pouch", position=1)
    default public Color fontColor() {
        return Color.yellow;
    }

    @ConfigItem(keyName="runeicons", name="Show Rune Icons", description="Show the rune icons next to the number of runes in pouch", position=2)
    default public boolean showIcons() {
        return true;
    }

    @ConfigItem(keyName="runePouchOverlayMode", name="Display mode", description="Configures where rune pouch overlay is displayed", position=3)
    default public RunePouchOverlayMode runePouchOverlayMode() {
        return RunePouchOverlayMode.BOTH;
    }
}

