package com.simplicity.client.widget.spellfiltering;

import com.simplicity.client.RSInterface;

import java.util.Arrays;

public class ModernSpellBookFilter extends SpellBookFilter {

    private static final int[] IGNORED_CHILDREN = new int[] {1189, 1592, 1562, 1193, 12435, 12445, 6003, 12455};
    public static final int PARENT_ID = 11000;

    public static final ModernSpellBookFilter INSTANCE = new ModernSpellBookFilter();

    @Override
    public void onVarpChange(int varp) {
        if (varp < 670 || varp > 674) {
            return;
        }
        // TODO Disabled for now.
        RSInterface parent = RSInterface.interfaceCache[PARENT_ID];
        /*if (varp == 671) {
            final boolean show = showTeleports();
            final int[] children = parent.originalChildren;
            RSInterface.interfaceCache[1151].childY[54] = show ? 0 : -20;
            for (int child : children) {
                if (child >= 11001 && child <= 11021) {
                    RSInterface.interfaceCache[child].hidden = !show;
                }
            }
            return;
        }*/
        if (varp == 670 || varp == 673 || varp == 674) {
            /*RSInterface spellContainer = RSInterface.interfaceCache[12424];
            int[] spells = spellContainer.originalChildren;
            int slot = 0;
            final int[] filtered = new int[spells.length];
            Arrays.fill(filtered, -1);
            for (int spellId : spells) {
                RSInterface widget = RSInterface.interfaceCache[spellId];
                boolean ignored = false;
                for (int ignoreId : IGNORED_CHILDREN) {
                    if (widget.id == ignoreId) {
                        ignored = true;
                        break;
                    }
                }
                if (ignored)
                    continue;
                if (canFilter(widget)) {
                    widget.hidden = true;
                    continue;
                }
                if (!showCombatSpells() && combatSpell(widget)) {
                    widget.hidden = true;
                    continue;
                }
                if (widget.id == 0) {
                    widget.hidden = true;
                    continue;
                }
                System.out.println("Adding "+ widget.id +": hidden="+ widget.hidden +", "+ widget.spellUsableOn +", "+ widget.spellName);
                if (widget.hidden)
                    widget.hidden = false;
                filtered[slot] = widget.id;
                slot++;
            }
            spellContainer.children = filtered;
            System.out.println(Arrays.toString(spellContainer.children));*/
        }
    }

    private boolean combatSpell(RSInterface widget) {
        return widget.spellUsableOn == 10 || widget.spellUsableOn == 8;
    }
}
