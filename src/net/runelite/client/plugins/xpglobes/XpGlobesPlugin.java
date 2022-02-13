/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.plugins.xpglobes;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import net.runelite.api.Skill;
import net.runelite.api.events.ExperienceChanged;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.xptracker.XpTrackerPlugin;
import net.runelite.client.task.Schedule;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@PluginDescriptor(name="XP Globes", description="Show XP globes for the respective skill when gaining XP", tags={"experience", "levels", "overlay"}, enabledByDefault=true)
@PluginDependency(value= XpTrackerPlugin.class)
public class XpGlobesPlugin
extends Plugin {
    private static final int MAXIMUM_SHOWN_GLOBES = 5;
    private XpGlobe[] globeCache;
    private final List<XpGlobe> xpGlobes = new ArrayList<XpGlobe>();
    @Inject
    private Client client;
    @Inject
    private XpGlobesConfig config;
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private XpGlobesOverlay overlay;

    @Provides
    XpGlobesConfig getConfig(ConfigManager configManager) {
        return configManager.getConfig(XpGlobesConfig.class);
    }

    @Override
    protected void startUp() throws Exception {
        this.overlayManager.add(this.overlay);
        globeCache= new XpGlobe[Skill.values().length - 1];
    }

    @Override
    protected void shutDown() throws Exception {
        this.overlayManager.remove(this.overlay);
        globeCache = null;
    }

    @Subscribe
    public void onExperienceChanged(ExperienceChanged event) {
        Skill skill = event.getSkill();
        int currentXp = this.client.getSkillExperience(skill);
        int currentLevel = Experience.getLevelForXp(currentXp);
        int skillIdx = skill.ordinal();
        XpGlobe cachedGlobe = this.globeCache[skillIdx];
        if (cachedGlobe != null && cachedGlobe.getCurrentXp() >= currentXp) {
            return;
        }
        if (this.config.hideMaxed() && currentLevel >= 99) {
            return;
        }
        if (client().getRegionId() == 12611) { // Disabled in Verzik's room
            return;
        }
        if (cachedGlobe != null) {
            cachedGlobe.setSkill(skill);
            cachedGlobe.setCurrentXp(currentXp);
            cachedGlobe.setCurrentLevel(currentLevel);
            cachedGlobe.setTime(Instant.now());
            this.addXpGlobe(this.globeCache[skillIdx], 5);
        } else {
            this.globeCache[skillIdx] = new XpGlobe(skill, currentXp, currentLevel, Instant.now());
        }
    }

    private void addXpGlobe(XpGlobe xpGlobe, int maxLength) {
        this.xpGlobes.remove(xpGlobe);
        if (this.getXpGlobesSize() >= maxLength) {
            this.xpGlobes.remove(0);
        }
        this.xpGlobes.add(xpGlobe);
    }

    int getXpGlobesSize() {
        return this.xpGlobes.size();
    }

    @Schedule(period=1L, unit= ChronoUnit.SECONDS)
    public void removeExpiredXpGlobes() {
        if (!this.xpGlobes.isEmpty()) {
            Instant currentTime = Instant.now();
            Iterator<XpGlobe> it = this.xpGlobes.iterator();
            while (it.hasNext()) {
                XpGlobe globe = it.next();
                Instant globeCreationTime = globe.getTime();
                if (currentTime.isBefore(globeCreationTime.plusSeconds(this.config.xpOrbDuration()))) {
                    return;
                }
                it.remove();
            }
        }
    }

    private void resetGlobeState() {
        this.xpGlobes.clear();
        this.globeCache = new XpGlobe[Skill.values().length - 1];
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event) {
        switch (event.getGameState()) {
            case LOGGING_IN: {
                this.resetGlobeState();
            }
        }
    }

    public List<XpGlobe> getXpGlobes() {
        return this.xpGlobes;
    }
}

