package com.simplicity.client.widget.spellfiltering;

import com.simplicity.client.RSInterface;

public class LunarSpellBookFilter extends SpellBookFilter {

    public static final int PARENT_ID = 98783;
    public static final LunarSpellBookFilter INSTANCE = new LunarSpellBookFilter();

    @Override
    public void onVarpChange(int varp) {
        if (varp < 670 || varp > 674) {
            return;
        }
        final int spellContainerId = 98784;
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
        RSInterface.rebuildVisibleSpells(spellContainer, 38, 25);
    }

    private boolean combatSpell(RSInterface widget) {
        if (widget.spellName == null)
            return false;
        return widget.spellName.toLowerCase().contains("vengeance");
    }
}
