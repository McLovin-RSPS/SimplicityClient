package com.simplicity.client;
// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import com.simplicity.client.cache.DataType;

public final class IDK {

	/*
	 * New hairstyles will start from index 98. The max indice for a style in this
	 * engine is 256. Newer engines do not have this limitation hence why the ID's
	 * are higher.
	 */
	private static final int OVERRIDE_INDEX_START = 98;

	public static void unpackConfig(CacheArchive streamLoader) {
		Stream stream = new Stream(streamLoader.getDataForName("idk.dat"));
		length = stream.readUnsignedWord();
		if (cache == null)
			cache = new IDK[length];
		for (int j = 0; j < length; j++) {
			if (cache[j] == null)
				cache[j] = new IDK();
			
			cache[j].readValues(stream);
			
			/**
			 * The code below fixes the pink on some of the styles.
			 */
			cache[j].recolourOriginal[0] = 55232;
			cache[j].recolourTarget[0] = 6798;
			
			if (j >= OVERRIDE_INDEX_START) { // Set higher indices as not selectable by default as we can't enable them all.
				cache[j].notSelectable = true;
			}
		}
		
		if (length <= OVERRIDE_INDEX_START) {
			return;
		}
		
		int[][] newStyleRanges = new int[][] { { 169, 198 }, { 245, 268 }, { 269, 303 }, { 309, 316 }, { 317, 326 }, { 327, 334 }, { 335, 344 }, { 345, 352 }, { 353, 362 } };
		
		int start = OVERRIDE_INDEX_START;
		
		/**
		 * Code below moves the styles from higher indices into lower ones to be usable.
		 */
		for (int i = 0; i < newStyleRanges.length; i++) {
			for (int j = newStyleRanges[i][0]; j <= newStyleRanges[i][1]; j++) {
				cache[start].copy(j);
				cache[start].notSelectable = false;
				start++;
			}
		}
	}
	
	private void copy(int from) {
		IDK idk = cache[from];
		bodyPartID = idk.bodyPartID;
		bodyModelIDs = new int[idk.bodyModelIDs.length];
		
		for (int i = 0; i < idk.bodyModelIDs.length; i++) {
			bodyModelIDs[i] = idk.bodyModelIDs[i];
		}
		
		recolourOriginal = new int[idk.recolourOriginal.length];
		
		for (int i = 0; i < idk.recolourOriginal.length; i++) {
			recolourOriginal[i] = idk.recolourOriginal[i];
		}
		
		recolourTarget = new int[idk.recolourTarget.length];
		
		for (int i = 0; i < idk.recolourTarget.length; i++) {
			recolourTarget[i] = idk.recolourTarget[i];
		}
	}

	private void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1)
				bodyPartID = stream.readUnsignedByte();
			else if (i == 2) {
				int j = stream.readUnsignedByte();
				bodyModelIDs = new int[j];
				for (int k = 0; k < j; k++)
					bodyModelIDs[k] = stream.readUnsignedWord();

			} else if (i == 3)
				notSelectable = true;
			else if (i >= 40 && i < 50)
				recolourOriginal[i - 40] = stream.readUnsignedWord();
			else if (i >= 50 && i < 60)
				recolourTarget[i - 50] = stream.readUnsignedWord();
			else if (i >= 60 && i < 70)
				headModelIDs[i - 60] = stream.readUnsignedWord();
			else
				System.out.println("Error unrecognised config code: " + i);
		} while (true);
	}

	public boolean bodyModelIsFetched() {
		if (bodyModelIDs == null)
			return true;
		boolean flag = true;
		for (int j = 0; j < bodyModelIDs.length; j++)
			if (!Model.modelIsFetched(bodyModelIDs[j], DataType.REGULAR))
				flag = false;

		return flag;
	}

	public Model fetchBodyModel() {
		if (bodyModelIDs == null)
			return null;
		Model aclass30_sub2_sub4_sub6s[] = new Model[bodyModelIDs.length];
		for (int i = 0; i < bodyModelIDs.length; i++)
			aclass30_sub2_sub4_sub6s[i] = Model.fetchModel(bodyModelIDs[i]);

		Model model;
		if (aclass30_sub2_sub4_sub6s.length == 1)
			model = aclass30_sub2_sub4_sub6s[0];
		else
			model = new Model(aclass30_sub2_sub4_sub6s.length,
					aclass30_sub2_sub4_sub6s);
		for (int j = 0; j < 6; j++) {
			if (recolourOriginal[j] == 0)
				break;
			model.recolour(recolourOriginal[j], recolourTarget[j]);
		}

		return model;
	}

	public boolean headModelFetched() {
		boolean flag1 = true;
		for (int i = 0; i < 5; i++)
			if (headModelIDs[i] != -1 && !Model.modelIsFetched(headModelIDs[i], DataType.REGULAR))
				flag1 = false;

		return flag1;
	}

	public Model fetchHeadModel() {
		Model aclass30_sub2_sub4_sub6s[] = new Model[5];
		int j = 0;
		for (int k = 0; k < 5; k++)
			if (headModelIDs[k] != -1)
				aclass30_sub2_sub4_sub6s[j++] = Model
						.fetchModel(headModelIDs[k]);

		Model model = new Model(j, aclass30_sub2_sub4_sub6s);
		for (int l = 0; l < 6; l++) {
			if (recolourOriginal[l] == 0)
				break;
			model.recolour(recolourOriginal[l], recolourTarget[l]);
		}

		return model;
	}

	private IDK() {
		bodyPartID = -1;
		recolourOriginal = new int[6];
		recolourTarget = new int[6];
		notSelectable = false;
	}

	public static int length;
	public static IDK cache[];
	public int bodyPartID;
	private int[] bodyModelIDs;
	private int[] recolourOriginal;
	private int[] recolourTarget;
	private final int[] headModelIDs = { -1, -1, -1, -1, -1 };
	public boolean notSelectable;
}
