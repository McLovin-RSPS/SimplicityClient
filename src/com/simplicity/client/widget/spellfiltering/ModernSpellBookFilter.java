package com.simplicity.client.widget.spellfiltering;

import com.simplicity.client.RSInterface;

import java.util.Arrays;

public class ModernSpellBookFilter extends SpellBookFilter {

    public static final int PARENT_ID = 101218;

    public static final ModernSpellBookFilter INSTANCE = new ModernSpellBookFilter();

    @Override
    public void onVarpChange(int varp) {
        if (varp < 670 || varp > 674) {
            return;
        }
        final int spellContainerId = 101219;
        RSInterface spellContainer = RSInterface.interfaceCache[spellContainerId];
        final int[] spells = spellContainer.children;
        for (int i = 0; i < spellContainer.children.length / 2; i++) {
            RSInterface widget = RSInterface.interfaceCache[spells[i]];
            if (widget == null)
                continue;
            /*if (canFilter(widget)) {
                widget.hidden = true;
                continue;
            }*/
            if (!showCombatSpells() && combatSpell(widget)) {
                widget.hidden = true;
                continue;
            }
            if (!showTeleports() && teleportSpell(widget)) {
                widget.hidden = true;
                continue;
            }
            if (!showUtilitySpells() && !combatSpell(widget) && !teleportSpell(widget)) {
                widget.hidden = true;
                continue;
            }
            if (widget.hidden)
                widget.hidden = false;
        }
        RSInterface.rebuildVisibleSpells(spellContainer, 25, 24);
    }

    private boolean combatSpell(RSInterface widget) {
        if (widget.spellName == null)
            return false;
        String spellName = widget.spellName.toLowerCase();
        return spellName.endsWith("strike") || spellName.endsWith("bolt")
                || spellName.equalsIgnoreCase("Crumble Undead") || spellName.endsWith("blast")
                || spellName.endsWith("wave") || spellName.endsWith("surge") || spellName.equalsIgnoreCase("Claws of Guthix")
                || spellName.equalsIgnoreCase("Flames of Zamorak") || spellName.equalsIgnoreCase("Magic Dart");
    }
}
