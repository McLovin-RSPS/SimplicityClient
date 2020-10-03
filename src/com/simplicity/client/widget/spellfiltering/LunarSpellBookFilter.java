package com.simplicity.client.widget.spellfiltering;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;

import java.util.Arrays;

public class LunarSpellBookFilter extends SpellBookFilter {

    public static final int PARENT_ID = 11800;
    public static final LunarSpellBookFilter INSTANCE = new LunarSpellBookFilter();

    @Override
    public void onVarpChange(int varp) {
        if (varp < 670 || varp > 674) {
            return;
        }
        RSInterface parent = RSInterface.interfaceCache[PARENT_ID];
        final int spellContainerId = 29999;
        if (varp == 671) {
            final boolean show = Client.instance.variousSettings[671] == 1;
            final int[] children = parent.originalChildren;
            for (int child : children) {
                if (child >= 11001 && child <= 11021) {
                    RSInterface.interfaceCache[child].hidden = !show;
                }
            }
            return;
        }
        RSInterface spellContainer = RSInterface.interfaceCache[spellContainerId];
        final int[] spells = spellContainer.originalChildren;
        int slot = 0;
        final int[] filtered = new int[spells.length];
        Arrays.fill(filtered, -1);
        for (int spellId : spells) {
            RSInterface widget = RSInterface.interfaceCache[spellId];
            if (widget == null)
                continue;
            if (canFilter(widget)) {
                widget.hidden = true;
                continue;
            }
            if (!showCombatSpells() && combatSpell(widget)) {
                widget.hidden = true;
                continue;
            }
            if (!showUtilitySpells() && !combatSpell(widget)) {
                widget.hidden = true;
                continue;
            }
            if (widget.hidden)
                widget.hidden = false;
            filtered[slot] = widget.id;
            slot++;
        }
        spellContainer.children = filtered;
    }

    private boolean combatSpell(RSInterface widget) {
        if (widget.spellName == null)
            return false;
        return widget.spellName.toLowerCase().contains("vengeance");
    }
}
