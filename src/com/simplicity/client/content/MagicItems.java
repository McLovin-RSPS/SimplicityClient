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
	private int runeId;

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
	private MagicItems(int itemId, int runeId) {
		this.itemId = itemId;
		this.runeId = runeId;
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
	public int getRuneId() {
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
