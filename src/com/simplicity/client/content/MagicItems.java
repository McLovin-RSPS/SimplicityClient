package com.simplicity.client.content;

import java.util.HashMap;
import java.util.Map;

import com.simplicity.client.RSInterface;

/**
 * An enumerated type that represents magic items which provide the player a
 * specific type and amount of rune.
 * 
 * @author Blake
 *
 */
public enum MagicItems {

	RUNE_POUCH(42791) {
		@Override
		public int getReturnValue(int runeId) {
			RSInterface runePouchRunes = RSInterface.interfaceCache[49010];

			if (runePouchRunes != null && runePouchRunes.inv != null && runePouchRunes.invStackSizes != null) {
				for (int j3 = 0; j3 < runePouchRunes.inv.length; j3++) {
					int id = runePouchRunes.inv[j3];

					if (id - 1 == runeId) {
						return runePouchRunes.invStackSizes[j3];
					}
				}
			}

			return 0;
		}
	},

	TOME_OF_FROST(18346, 555) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	KODAI_WAND(51006, 555) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_AIR(1381, 556) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_AIR_2(1397, 556) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_AIR_3(1405, 556) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_WATER(1383, 555) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_WATER_2(1395, 555) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_WATER_3(1403, 555) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_WATER_4(18346, 555) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_WATER_5(51006, 555) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_EARTH(1385, 557) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_EARTH_2(1399, 557) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_EARTH_3(1407, 557) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_FIRE(1387, 554) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_FIRE_2(1393, 554) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_FIRE_3(1401, 554) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_MUD(6562, new int[]{555, 557}) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_MUD_2(6563, new int[]{555, 557}) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_LAVA(3053, new int[]{554, 557}) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},

	STAFF_OF_LAVA_2(3054, new int[]{554, 557}) {
		@Override
		public int getReturnValue(int runeId) {
			return UNLIMITED;
		}
	},
	IBAN_STAFF(1409, new int[]{1409}) {
		@Override
		public int getReturnValue(int staffId) {
			return 1;
		}
	},
	SLAYER_STAFF(4170, new int[]{4170}) {
		@Override
		public int getReturnValue(int staffId) {
			return 1;
		}
	},
	SARADOMIN_STAFF(2415, new int[]{2415}) {
		@Override
		public int getReturnValue(int staffId) {
			return 1;
		}
	},
	GUTHIX_STAFF(2416, new int[]{2416}) {
		@Override
		public int getReturnValue(int staffId) {
			return 1;
		}
	},
	ZAMORAK_STAFF(2417, new int[]{2417}) {
		@Override
		public int getReturnValue(int staffId) {
			return 1;
		}
	};

	/**
	 * A value for unlimited runes.
	 */
	public static final int UNLIMITED = 999999999;

	/**
	 * The item id.
	 */
	private int itemId;

	/**
	 * The rune id.
	 */
	private int[] runeId;

	/**
	 * Constructs a new {@link MagicItems}.
	 */
	private MagicItems(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * Constructs a new {@link MagicItems}.
	 * 
	 * @param itemId The item id.
	 * @param runeId The rune id.
	 */
	MagicItems(int itemId, int[] runeId) {
		this.itemId = itemId;
		this.runeId = runeId;
	}

	MagicItems(int itemId, int runeId) {
		this(itemId, new int[]{runeId});
	}

	/**
	 * Gets the item id.
	 * 
	 * @return The item id.
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Gets the rune id.
	 * 
	 * @return The rune id.
	 */
	public int[] getRuneId() {
		return runeId;
	}

	/**
	 * Gets the return value for the specified rune id.
	 * 
	 * @param runeId The rune id.
	 * 
	 * @return The return value.
	 */
	public abstract int getReturnValue(int runeId);

	/**
	 * A hash collection of the equipment with item ids as the keys.
	 */
	public static final Map<Integer, MagicItems> EQUIPMENT = new HashMap<>();

	/**
	 * Initializes the class.
	 */
	public static void init() {
		for (MagicItems eq : values()) {
			if (eq.equals(RUNE_POUCH)) {
				continue;
			}

			EQUIPMENT.put(eq.getItemId(), eq);
		}
	}

}
