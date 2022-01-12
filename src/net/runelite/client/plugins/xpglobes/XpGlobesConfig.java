/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.plugins.xpglobes;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup(value="xpglobes")
public interface XpGlobesConfig
extends Config {
    @ConfigItem(keyName="enableTooltips", name="Enable Tooltips", description="Configures whether or not to show tooltips", position=0)
    default boolean enableTooltips() {
        return true;
    }

    @ConfigItem(keyName="hideMaxed", name="Hide maxed skills", description="Stop globes from showing up for level 99 skills ", position=1)
    default boolean hideMaxed() {
        return false;
    }

    @ConfigItem(keyName="enableCustomArcColor", name="Enable custom arc color", description="Enables the custom coloring of the globe's arc instead of using the skill's default color.", position=2)
    default boolean enableCustomArcColor() {
        return false;
    }

    @ConfigItem(keyName="Progress arc color", name="Progress arc color", description="Change the color of the progress arc in the xp orb", position=3)
    default Color progressArcColor() {
        return Color.ORANGE;
    }

    @ConfigItem(keyName="Progress orb outline color", name="Progress orb outline color", description="Change the color of the progress orb outline", position=4)
    default Color progressOrbOutLineColor() {
        return Color.BLACK;
    }

    @ConfigItem(keyName="Progress orb background color", name="Progress orb background color", description="Change the color of the progress orb background", position=5)
    default Color progressOrbBackgroundColor() {
        return new Color(128, 128, 128, 127);
    }

    @ConfigItem(keyName="Progress arc width", name="Progress arc width", description="Change the stroke width of the progress arc", position=6)
    default int progressArcStrokeWidth() {
        return 2;
    }

    @ConfigItem(keyName="Orb size", name="Size of orbs", description="Change the size of the xp orbs", position=7)
    default int xpOrbSize() {
        return 40;
    }

    @ConfigItem(keyName="Orb duration", name="Duration of orbs", description="Change the duration the xp orbs are visible", position=8)
    default int xpOrbDuration() {
        return 10;
    }
}

