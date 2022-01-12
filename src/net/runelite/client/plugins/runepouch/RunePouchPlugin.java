/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.plugins.runepouch;

import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
        name="Rune Pouch",
        description="Show the contents of your rune pouch",
        tags={"combat", "magic", "overlay", "rune", "pouch"},
        loadWhenOutdated=true
)
public class RunePouchPlugin
extends Plugin {
    @Inject
    public RunePouchOverlay overlay;
    @Inject
    public OverlayManager overlayManager;

    @Provides
    RunePouchConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RunePouchConfig.class);
    }

    @Override
    protected void startUp() throws Exception {
        this.overlayManager.add(this.overlay);
    }

    @Override
    protected void shutDown() throws Exception {
        this.overlayManager.remove(this.overlay);
    }
}

