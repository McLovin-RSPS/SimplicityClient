package net.runelite.api.kit;

import net.runelite.api.PlayerComposition;

/**
 * Represents an equipment slot in a players composition.
 * <p>
 * These values are intended for use with {@link PlayerComposition} equipment
 * slots. For obtaining information about equipment in the local players
 * equipment {@link net.runelite.api.ItemContainer}, use
 * {@link net.runelite.api.EquipmentInventorySlot}.
 */
public enum KitType {
	CAPE(1), AMULET(2), WEAPON(3), TORSO(4), SHIELD(5), LEGS(7), HEAD(8), HANDS(9), BOOTS(10), JAW(11);

	/**
	 * Raw equipment index.
	 */
	private final int index;

	KitType(int index) {
		this.index = index;
	}

	/**
	 * Gets the raw equipment index for use in
	 * {@link PlayerComposition#getEquipmentIds()}.
	 *
	 * @return raw equipment index
	 */
	public int getIndex() {
		return index;
	}
}
