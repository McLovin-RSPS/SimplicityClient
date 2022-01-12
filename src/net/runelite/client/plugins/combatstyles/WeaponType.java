package net.runelite.client.plugins.combatstyles;

import java.util.HashMap;
import java.util.Map;

public enum WeaponType {
    TYPE_0(5855, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.DEFENSIVE, null }),
    TYPE_1(1698, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.AGGRESSIVE, CombatStyle.DEFENSIVE }),
    TYPE_2(425, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, null, CombatStyle.DEFENSIVE }),
    TYPE_3(1764, new CombatStyle[] { CombatStyle.RANGING, CombatStyle.RANGING, CombatStyle.LONGRANGE, null }),
    TYPE_4(7762, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.CONTROLLED, CombatStyle.DEFENSIVE }),
    TYPE_5(-1, new CombatStyle[] { CombatStyle.RANGING, CombatStyle.RANGING, null, CombatStyle.LONGRANGE }),
    TYPE_6(-1, new CombatStyle[] { CombatStyle.AGGRESSIVE, CombatStyle.RANGING, CombatStyle.DEFENSIVE_CASTING, null }),
    TYPE_7(4446, new CombatStyle[] { CombatStyle.RANGING, CombatStyle.RANGING, null, CombatStyle.LONGRANGE }),
    TYPE_8(-1, new CombatStyle[] { CombatStyle.OTHER, CombatStyle.AGGRESSIVE, null, null }),
    TYPE_9(-1, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.CONTROLLED, CombatStyle.DEFENSIVE }),
    TYPE_10(2423, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.AGGRESSIVE, CombatStyle.DEFENSIVE }),
    TYPE_11(5570, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.AGGRESSIVE, CombatStyle.DEFENSIVE }),
    TYPE_12(8460, new CombatStyle[] { CombatStyle.CONTROLLED, CombatStyle.AGGRESSIVE, null, CombatStyle.DEFENSIVE }),
    TYPE_13(-1, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, null, CombatStyle.DEFENSIVE }),
    TYPE_14(-1, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.AGGRESSIVE, CombatStyle.DEFENSIVE }),
    TYPE_15(4679, new CombatStyle[] { CombatStyle.CONTROLLED, CombatStyle.CONTROLLED, CombatStyle.CONTROLLED, CombatStyle.DEFENSIVE }),
    TYPE_16(3796, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.CONTROLLED, CombatStyle.DEFENSIVE }),
    TYPE_17(2276, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.AGGRESSIVE, CombatStyle.DEFENSIVE }),
    TYPE_18(-1, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, null, CombatStyle.DEFENSIVE, CombatStyle.CASTING, CombatStyle.DEFENSIVE_CASTING }),
    TYPE_19(-1, new CombatStyle[] { CombatStyle.RANGING, CombatStyle.RANGING, null, CombatStyle.LONGRANGE }),
    TYPE_20(12290, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.CONTROLLED, null, CombatStyle.DEFENSIVE }),
    TYPE_21(-1, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, null, CombatStyle.DEFENSIVE, CombatStyle.CASTING, CombatStyle.DEFENSIVE_CASTING }),
    TYPE_22(-1, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.AGGRESSIVE, CombatStyle.DEFENSIVE }),
    TYPE_23(328, new CombatStyle[] { CombatStyle.CASTING, CombatStyle.CASTING, null, CombatStyle.DEFENSIVE_CASTING }),
    TYPE_24(-1, new CombatStyle[] { CombatStyle.ACCURATE, CombatStyle.AGGRESSIVE, CombatStyle.CONTROLLED, CombatStyle.DEFENSIVE }),
    TYPE_25(-1, new CombatStyle[] { CombatStyle.CONTROLLED, CombatStyle.AGGRESSIVE, null, CombatStyle.DEFENSIVE }),
    TYPE_26(-1, new CombatStyle[] { CombatStyle.AGGRESSIVE, CombatStyle.AGGRESSIVE, null, CombatStyle.AGGRESSIVE }),
    TYPE_27(-1, new CombatStyle[] { CombatStyle.ACCURATE, null, null, CombatStyle.OTHER }),
    TYPE_28(28500, new CombatStyle[] { CombatStyle.CASTING, CombatStyle.CASTING, null, CombatStyle.DEFENSIVE_CASTING });

    private static final Map<Integer, WeaponType> weaponTypes;

    private final CombatStyle[] attackStyles;

    public final int interfaceId;

    static {
        weaponTypes = new HashMap<>();
        for (WeaponType weaponType : values())
            weaponTypes.put(Integer.valueOf(weaponType.interfaceId), weaponType);
    }

    WeaponType(int interfaceId, CombatStyle... attackStyles) {
        this.interfaceId = interfaceId;
        this.attackStyles = attackStyles;
    }

    public CombatStyle[] getAttackStyles() {
        return this.attackStyles;
    }

    public static WeaponType getWeaponType(int id) {
        return weaponTypes.get(Integer.valueOf(id));
    }
}
