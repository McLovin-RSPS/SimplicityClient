/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.plugins.xpglobes;

import net.runelite.api.Skill;

import java.time.Instant;

class XpGlobe {
    private Skill skill;
    private int currentXp;
    private int currentLevel;
    private Instant time;

    public Skill getSkill() {
        return this.skill;
    }

    public int getCurrentXp() {
        return this.currentXp;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public Instant getTime() {
        return this.time;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public XpGlobe(Skill skill, int currentXp, int currentLevel, Instant time) {
        this.skill = skill;
        this.currentXp = currentXp;
        this.currentLevel = currentLevel;
        this.time = time;
    }
}

