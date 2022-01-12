package net.runelite.client.plugins.combatstyles;

import net.runelite.api.Skill;

enum CombatStyle {
    ACCURATE("Accurate", new Skill[] { Skill.ATTACK }),
    AGGRESSIVE("Aggressive", new Skill[] { Skill.STRENGTH }),
    DEFENSIVE("Defensive", new Skill[] { Skill.DEFENCE }),
    CONTROLLED("Controlled", new Skill[] { Skill.ATTACK, Skill.STRENGTH, Skill.DEFENCE }),
    RANGING("Ranging", new Skill[] { Skill.RANGED }),
    LONGRANGE("Longrange", new Skill[] { Skill.RANGED, Skill.DEFENCE }),
    CASTING("Casting", new Skill[] { Skill.MAGIC }),
    DEFENSIVE_CASTING("Casting", new Skill[] { Skill.MAGIC, Skill.DEFENCE }),
    OTHER("Defensive", new Skill[0]);

    private final String name;

    private final Skill[] skills;

    CombatStyle(String name, Skill... skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return this.name;
    }

    public Skill[] getSkills() {
        return this.skills;
    }
}
