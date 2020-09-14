package com.simplicity.client.widget.spellfiltering;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;

public abstract class SpellBookFilter {

    private void init() {

    }

    public void sync() {
        onVarpChange(670);
        onVarpChange(671);
    }

    public abstract void onVarpChange(int varp);

    public boolean canFilter(RSInterface widget) {
        boolean filtered = false;
        if (!showMissingMaterials() && missingRunesFor(widget)) {
            filtered = true;
        }
        if (!showMissingLevels() && missingLevelFor(widget)) {
            filtered = true;
        }
        return filtered;
    }

    public boolean missingRunesFor(RSInterface widget) {
        if (widget.requiredValues == null)
            return false;
        boolean missing = false;
        for (int i = 0; i < widget.requiredValues.length; i++) {
            if (Client.instance.extractInterfaceValues(widget, 0) < widget.requiredValues[i]) {
                missing = true;
                break;
            }
        }
        return missing;
    }

    public boolean missingLevelFor(RSInterface widget) {
        if (widget.requiredValues == null)
            return false;
        return Client.instance.currentStats[6] < widget.requiredValues[2];
    }

    public boolean showCombatSpells() {
        return Client.instance.variousSettings[670] == 1;
    }

    public boolean showTeleports() {
        return Client.instance.variousSettings[671] == 1;
    }

    public boolean showUtilitySpells() {
        return Client.instance.variousSettings[672] == 1;
    }

    public boolean showMissingLevels() {
        return Client.instance.variousSettings[673] == 1;
    }

    public boolean showMissingMaterials() {
        return Client.instance.variousSettings[674] == 1;
    }
}
