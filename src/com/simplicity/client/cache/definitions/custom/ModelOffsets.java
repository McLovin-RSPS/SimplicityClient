package com.simplicity.client.cache.definitions.custom;

import java.util.HashMap;
import java.util.Map;

import com.simplicity.client.Model;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.util.Vector3;

/**
 * An enumerated type that contains data for model offset translations based on
 * gender.
 * 
 * @author Blake
 *
 */
public enum ModelOffsets {
	
	FEMALE_BOW(1, DataType.REGULAR, new Vector3(4, -12, 5), 512, 563, 16846),
	GENERIC_FEMALE(1, DataType.REGULAR, 486, 490, 493, 505, 509, 510, 517, 518, 519, 533, 539, 540, 541, 542, 546, 550, 555, 559, 564, 565, 3885, 6232, 11225, 26424, 38275, 62348),
	DRAGON_DEFENDER(1, DataType.REGULAR, new Vector3(-3, -12, 7), 62367),
	DRAGONFIRE_SHIELD(1, DataType.REGULAR, new Vector3(0, 10, 0), 26423),
	;
	
	/**
	 * The gender.
	 */
	private int gender;
	
	/**
	 * The data type.
	 */
	private DataType type;
	
	/**
	 * The model id.
	 */
	private int[] modelIds;
	
	/**
	 * The offsets.
	 */
	private Vector3 offsets;
	
	/**
	 * Constructs a new {@link ModelOffsets}.
	 * 
	 * @param gender  The gender.
	 * @param type    The data type.
	 * @param modelId The model id.
	 */
	private ModelOffsets(int gender, DataType type, int...modelIds) {
		this(gender, type, new Vector3(4, -12, 7), modelIds);
	}

	/**
	 * Constructs a new {@link ModelOffsets}.
	 * 
	 * @param gender  The gender.
	 * @param type    The data type.
	 * @param offsets The offsets.
	 * @param modelId The model id.
	 */
	private ModelOffsets(int gender, DataType type, Vector3 offsets, int...modelIds) {
		this.gender = gender;
		this.type = type;
		this.modelIds = modelIds;
		this.offsets = offsets;
	}

	/**
	 * A hash collection of male model offsets for each data type.
	 */
	private static final Map<DataType, Map<Integer, Vector3>> MALE_OFFSETS = new HashMap<>();

	/**
	 * A hash collection of female model offsets for each data type.
	 */
	private static final Map<DataType, Map<Integer, Vector3>> FEMALE_OFFSETS = new HashMap<>();

	/**
	 * Initializes the data.
	 */
	public static void init() {
		for (DataType type : DataType.values()) {
			MALE_OFFSETS.put(type, new HashMap<>());
			FEMALE_OFFSETS.put(type, new HashMap<>());
		}

		for (ModelOffsets f : ModelOffsets.values()) {
			for (int id : f.modelIds) {
				if (f.gender == 0) {
					MALE_OFFSETS.get(f.type).put(id, f.offsets);
				} else if (f.gender == 1) {
					FEMALE_OFFSETS.get(f.type).put(id, f.offsets);
				}
			}
		}
	}

	/**
	 * Checks if an offset needs to be applied.
	 * 
	 * @param gender The gender.
	 * @param def    The item definition.
	 * @param model  The model.
	 */
	public static void check(int gender, ItemDefinition def, Model model) {
		int modelId = gender == 0 ? def.maleEquip1 : def.femaleEquip1;
		
		Vector3 v = gender == 0 ? MALE_OFFSETS.get(def.dataType).get(modelId) : FEMALE_OFFSETS.get(def.dataType).get(modelId);
		
		if (v != null) {
			model.translate(v.getX(), v.getY(), v.getZ());
		}
	}
	
}
