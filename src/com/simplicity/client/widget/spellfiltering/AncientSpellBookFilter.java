package com.simplicity.client.widget.spellfiltering;

import com.simplicity.client.RSInterface;

import java.util.Arrays;

public class AncientSpellBookFilter extends SpellBookFilter {

    public static final int PARENT_ID = 12855;
    public static final AncientSpellBookFilter INSTANCE = new AncientSpellBookFilter();

    @Override
    public void onVarpChange(int varp) {
        if (varp < 670 || varp > 674) {
            return;
        }
        RSInterface parent = RSInterface.interfaceCache[PARENT_ID];
        int[] spells = parent.originalChildren;
        int slot = 1;
        final int[] filtered = new int[spells.length];
        Arrays.fill(filtered, -1);
        for (int index = 1; index < spells.length; index++) {
            RSInterface widget = RSInterface.interfaceCache[spells[index]];
            if (canFilter(widget)) {
                widget.hidden = true;
                continue;
            }
            if (!showCombatSpells() && combatSpell(widget)) {
                widget.hidden = true;
                continue;
            }
            if (!showTeleports() && !combatSpell(widget)) {
                widget.hidden = true;
                continue;
            }
            if (widget.hidden)
                widget.hidden = false;
            filtered[slot] = widget.id;
            slot++;
        }
        filtered[0] = 31330;
        parent.children = filtered;
    }

    private boolean combatSpell(RSInterface widget) {
        return widget.spellUsableOn == 8 || widget.spellUsableOn == 10;
    }
}
