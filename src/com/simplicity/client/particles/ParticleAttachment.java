package com.simplicity.client.particles;

import java.util.*;

import com.simplicity.client.cache.DataType;

public class ParticleAttachment {
	
	private static final Map<Integer, int[][]> customAttachments = new HashMap<Integer, int[][]>();

	private static final Map<Integer, int[][]> attachments = new HashMap<Integer, int[][]>();

	public static int[][] getAttachments(int model, DataType dataType) {
		if (dataType.equals(DataType.CUSTOM)) {
			return customAttachments.get(model);
		}
		
        return attachments.get(model);
    }

	static {
		// Overlord cape
		customAttachments.put(97, new int[][] { { 439, 12 }, { 302, 12 }, { 324, 12 }, });
		customAttachments.put(111, new int[][] { { 439, 15 }, { 302, 15 }, { 324, 15 }, });

		// TokHaar-Kal-Ket
		customAttachments.put(89, new int[][] { { 243, 7 }, { 164, 7 }, { 191, 7 }, { 100, 7 } });
		customAttachments.put(87, new int[][] { { 243, 13 }, { 164, 13 }, { 191, 13 }, { 100, 13 } });
		customAttachments.put(91, new int[][] { { 243, 14 }, { 164, 14 }, { 191, 14 }, { 100, 14 } });
		
		//Completionist cape
		attachments.put(65297, new int[][] { { 494, 0 }, { 488, 0 }, { 485, 0 }, { 476, 0 }, { 482, 0 }, { 479, 0 }, { 491, 0 } });
		attachments.put(65316, new int[][] { { 494, 0 }, { 488, 0 }, { 485, 0 }, { 476, 0 }, { 482, 0 }, { 479, 0 }, { 491, 0 } });

		//Trimmed Completionist Cape
		attachments.put(65295, new int[][] { { 494, 1 }, { 488, 1 }, { 485, 1 }, { 476, 1 }, { 482, 1 }, { 479, 1 }, { 491, 1 } });
		attachments.put(65328, new int[][] { { 494, 1 }, { 488, 1 }, { 485, 1 }, { 476, 1 }, { 482, 1 }, { 479, 1 }, { 491, 1 } });
		
		//attachments.put(323, new int[][] { { 113, 2 }, { 116, 2 }, { 109, 2 }, { 189, 2 }, { 100, 2 }, { 129, 2 }, { 128, 2 }, { 199, 2 }, { 191, 2 }, { 150, 2 }, { 98, 2 }, { 148, 2 } });

		/*//Max Cape
		attachments.put(65300, new int[][] { { 113, 2 }, { 116, 2 }, { 109, 2 }, { 189, 2 }, { 100, 2 }, { 129, 2 }, { 128, 2 }, { 199, 2 }, { 191, 2 }, { 150, 2 }, { 98, 2 }, { 148, 2 } });
		attachments.put(65322, new int[][] { { 113, 2 }, { 116, 2 }, { 109, 2 }, { 189, 2 }, { 100, 2 }, { 129, 2 }, { 128, 2 }, { 199, 2 }, { 191, 2 }, { 150, 2 }, { 98, 2 }, { 148, 2 } });
	*/
		//Master Dung. Cape
		attachments.put(59885, new int[][] { {136, 2 }, { 125, 2 }, { 124, 2 }, { 131, 2 },  {172, 2 }, {144, 2 }, { 143, 2 }, { 142, 2 }, { 161, 2 }, { 123, 2 }, { 89, 2 } });
		attachments.put(59887, new int[][] { {136, 2 }, { 125, 2 }, { 124, 2 }, { 131, 2 },  {172, 2 }, {144, 2 }, { 143, 2 }, { 142, 2 }, { 161, 2 }, { 123, 2 }, { 89, 2 } });

		// Infernal max Cape
		attachments.put(33102, new int[][] { { 204, 5 }, { 40, 5 }, { 55, 5 }, { 18, 5 }, { 19, 5 }, { 257, 5 },
				{ 271, 5 } });
		attachments.put(33114, new int[][] { { 188, 5 }, { 40, 5 }, { 54, 5 }, { 18, 5 }, { 19, 5 }, { 232, 5 },
				{ 247, 5 } });

		// Max Cape
		attachments.put(29616, new int[][] { { 272, 2 }, { 49, 2 }, { 37, 2 }, { 17, 2 }, { 41, 2 }, { 283, 2 },
				{ 315, 2 } });
		attachments.put(29624, new int[][] { { 249, 2 }, { 49, 2 }, { 37, 2 }, { 17, 2 }, { 41, 2 }, { 279, 2 },
				{ 313, 2 } });

		// Sled
		attachments.put(4946, new int[][] { { 85, 9 }, { 167, 9 } });
		
		// Dark Sled
		attachments.put(62923, new int[][] { { 62, 12 }, { 120, 12 } });
		
		// Godsword
		attachments.put(2606, new int[][] { { 188, 10 }, { 181, 11 }, { 187, 10 }, { 223, 10 } });
	}
}