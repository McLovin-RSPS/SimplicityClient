package com.simplicity.client.cache.definitions.custom;

import com.simplicity.client.cache.definitions.ItemDefinition;

public enum AlchemyScrolls {

	SCYTHE_OF_VITUR_X(29000, "Scythe Of Vitur X"),
	ANGELIC_BOOTS(29001, "Angelic Boots"),
	RING_OF_BOSSES(29002, "Ring Of Bosses"),
	HAND_CANNON_S(29003, "Hand Cannon X"),
	DARK_TWISTED_BOW(29004, "Dark Twisted Bow"),
	FLAME_GLOVES_E(29005, "Flame Gloves (e)"),
	GUARDIAN_BOOTS(29006, "Guardian Boots"),
	SUPERIOR_COMBAT_BOX(29007, "Superior Combat Box"),
	ONYX_BOX(29008, "Onyx Box"),
	CRYSTAL_BOX(29009, "Crystal Box"),
	SCYTHE_OF_VITUR_XI(29010, "Scythe Of Vitur XI"),
	BANDOS_SET(29011, "Bandos Set"),
	SAGITTARIAN_SET(29012, "Sagittarian Set"),
	NEITZNOT_FACEGUARD(29013, "Neitznot Faceguard"),
	;

	private AlchemyScrolls(int itemId, String scrollName) {
		ItemDefinition def = new ItemDefinition();

		def.id = itemId;
		def.setDefaults();
		def.copy(ItemDefinition.forID(55481));
		def.name = "@red@Formula Scroll: "+scrollName;
		def.editedModelColor = new int[] { 8121, 5035 };
		def.newModelColor = new int[] { 127, 127 };

		ItemDefinition.getCustomRecolors().put(itemId, def);
	}

}
