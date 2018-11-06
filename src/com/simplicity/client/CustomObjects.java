package com.simplicity.client;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Gabriel Hannason
 */
public class CustomObjects {

	public static void init() {
		for(int i = 0; i < CUSTOM_OBJECTS.length; i++) {
			int id = CUSTOM_OBJECTS[i][0];
			int x = CUSTOM_OBJECTS[i][1];
			int y = CUSTOM_OBJECTS[i][2];
			int z = CUSTOM_OBJECTS[i][3];
			int face = CUSTOM_OBJECTS[i][4];
			CUSTOM_OBJECT_LIST.add(new GameObject(id, x, y, z, face));
		}
	}
	
	public static final int[][] CUSTOM_OBJECTS = {

			//Well for upgradable at home
			{3485, 3089, 3495, 0, 0},

			 //Treasure island chests
			  { 18804, 3039, 2912, 0, 2 },

			/**Donator Zone**/
			{ 11758, 3035, 4413, 0, 0},//bank
			{ 11758, 3036, 4413, 0, 0},//bank
			{ 11758, 3037, 4413, 0, 0},//bank
			{ 11758, 3038, 4413, 0, 0},//bank
			{ 11758, 3039, 4413, 0, 0},//bank
			{ 11758, 3040, 4413, 0, 0},//bank
			{ 11758, 3041, 4413, 0, 0},//bank
			{ 11758, 3042, 4413, 0, 0},//bank
			{ 11758, 3043, 4413, 0, 0},//bank
			{-1, 3037, 4402, 0, 0}, //Pipes around rocktail
			{-1, 3038, 4402, 0, 0}, //Pipes around rocktail
			{-1, 3037, 4398, 0, 0}, //Pipes around rocktail
			{-1, 3038, 4398, 0, 0}, //Pipes around rocktail
			{-1, 3040, 4398, 0, 0}, //Pipes around rocktail
			{-1, 3041, 4398, 0, 0}, //Pipes around rocktail
			{-1, 3041, 4401, 0, 0}, //Pipes around rocktail
			{-1, 3041, 4402, 0, 0}, //Pipes around rocktail
			{-1, 3041, 4399, 0, 0}, //Pipes around rocktail
			{-1, 3037, 4399, 0, 0}, //Pipes around rocktail
			{-1, 3037, 4401, 0, 0}, //Pipes around rocktail
			{-1, 3040, 4402, 0, 0}, //Pipes around rocktail
			{-1, 3043, 4391, 0, 0}, //cages
			{-1, 3043, 4393, 0, 0}, //cages
			{-1, 3041, 4391, 0, 0}, //cages
			
			{-1, 2573, 9876, 0, 0}, //cages
			{-1, 2575, 9876, 0, 0}, //cages
			{-1, 2578, 9875, 0, 0}, //cages
			{-1, 2576, 9873, 0, 0}, //cages
			{-1, 2577, 9893, 0, 0}, //cages
			{-1, 2567, 9886, 0, 0}, //cages
			{-1, 2566, 9889, 0, 0}, //cages
			{-1, 2577, 9894, 0, 0}, //cages
			{-1, 2580, 9894, 0, 0}, //cages

			
			{-1, 3041, 4393, 0, 0}, //cages
			{-1, 3039, 4391, 0, 0}, //cages
			{-1, 3039, 4393, 0, 0}, //cages
			{-1, 3037, 4393, 0, 0}, //cages
			{-1, 3037, 4391, 0, 0}, //cages
			{-1, 3042, 4388, 0, 0}, //Expiriment Tables
			{-1, 3037, 4388, 0, 0}, //Expiriment Tables
			{-1, 3036, 4385, 0, 0}, //Expiriment Tables
			{-1, 3044, 4385, 0, 0}, //Expiriment Tables
			{-1, 3037, 4384, 0, 0}, //Expiriment Tables
			{-1, 3042, 4384, 0, 0}, //Expiriment Tables
			{-1, 3036, 4388, 0, 0}, //Expiriment Tables
			{-1, 3044, 4388, 0, 0}, //Expiriment Tables
			{-1, 3041, 4385, 0, 0}, //Expiriment Tables
			{-1, 3039, 4385, 0, 0}, //Expiriment Tables
			
			
			//onyx city
			
			// onyxrocks
			{54613, 2716, 5312, 0, 0}, // Onyx city
			{54613, 2717, 5312, 0, 0}, // Onyx city			
			{54613, 2717, 5311, 0, 0}, // Onyx city		
			{54613, 2717, 5310, 0, 0}, // Onyx city		
			{54613, 2717, 5309, 0, 0}, // Onyx city		
			{54613, 2716, 5311, 0, 0}, // Onyx city		
			{54613, 2716, 5310, 0, 0}, // Onyx city		
			{54613, 2716, 5309, 0, 0}, // Onyx city	
			
			// Arthur's dream
			{10089 , 1826, 5168, 2, 0}, // anglerfish spot fishworld
			{10089 , 1827, 5168, 2, 0}, // anglerfish spot fishworld
			{10089 , 1823, 5168, 2, 0}, // anglerfish spot fishworld
			{10089 , 1822, 5168, 2, 0}, // anglerfish spot fishworld
			
			{61551 , 1820, 5160, 2, 0}, // white tree arthur's dream
			{61551 , 1827, 5159, 2, 0}, // white tree arthur's dream
			
			{52709, 1824, 5161, 2, 0}, // arthur's dream campfire
			
			// Edge arthur's dream teleporter
			{48661, 3083, 3488, 0, 3}, // Arthur's dream teleporter edgeville
			
			{14859, 3099, 3499, 0, 0}, // rune ore edge bank
			{14859, 3099, 3498, 0, 0}, // rune ore edge bank
			{14859, 3099, 3497, 0, 0}, // rune ore edge bank
			{14859, 3099, 3496, 0, 0}, // rune ore edge bank
			{14859, 3099, 3495, 0, 0}, // rune ore edge bank
			{14859, 3099, 3494, 0, 0}, // rune ore edge bank
			
			/*{54613, 3089, 3483, 0, 0}, // Onyx ore edge bank		
			{54613, 3088, 3483, 0, 0}, // Onyx ore edge bank	
			{54613, 3087, 3483, 0, 0}, // Onyx ore edge bank	
			{54613, 3086, 3483, 0, 0}, // Onyx ore edge bank		
			{54613, 3085, 3483, 0, 0}, // Onyx ore edge bank	
			{54613, 3084, 3483, 0, 0}, // Onyx ore edge bank	
			{54613, 3083, 3483, 0, 0}, // Onyx ore edge bank */
			
			//dzone
			
			//runerocks
			{14859, 2331, 9798, 0, 0},
			{14859, 2331, 9799, 0, 0},
			{14859, 2331, 9800, 0, 0},
			{14859, 2331, 9801, 0, 0},
			{14859, 2331, 9802, 0, 0},
			
			{14859, 2330, 9796, 0, 0},
			{14859, 2330, 9795, 0, 0},
			{14859, 2330, 9794, 0, 0},
			
			//banks
			{2213, 2323, 9800, 0, 0},
			{2213, 2322, 9800, 0, 0},
			
			{2213, 2320, 9800, 0, 0},
			{2213, 2319, 9800, 0, 0},
			{2213, 2318, 9800, 0, 0},
			
			//magetrees
			{1306, 2326, 9795, 0, 0},
			
			

			

			{6189, 2328, 9798, 0, 0},

			{-1, 2324, 9799, 0, 0},
			{-1, 2324, 9798, 0, 0},
			{-1, 2325, 9798, 0, 0},
			{-1, 2326, 9798, 0, 0},
			{-1, 2328, 9799, 0, 0},
			{-1, 2327, 9799, 0, 0},
			{-1, 2327, 9800, 0, 0},
			{-1, 2327, 9798, 0, 0},
			
			{-1, 2321, 9800, 0, 0},
			{-1, 2320, 9798, 0, 0},
			{-1, 2319, 9799, 0, 0},
			{-1, 2318, 9801, 0, 0},
			{-1, 2321, 9798, 0, 0},
			{-1, 2318, 9798, 0, 0},
			{-1, 2316, 9798, 0, 0},
			{-1, 2309, 9799, 0, 0},
			
			{-1, 2309, 9806, 0, 0},
			
			{8135, 2309, 9804, 0, 0},
			{8135, 2310, 9804, 0, 0},
			{8135, 2308, 9804, 0, 0},
			{8135, 2307, 9804, 0, 0},
			{8135, 2309, 9805, 0, 0},
			{8135, 2310, 9805, 0, 0},
			{8135, 2308, 9805, 0, 0},
			{8135, 2307, 9805, 0, 0},
			
			{-1, 2319, 9798, 0, 0},

			{2213, 2321, 9800, 0, 0},
			
			{4306, 2325, 9798, 0, 0},
			{2644, 2326, 9798, 0, 0},
			
			{2732, 2323, 9806, 0, 0},

			{2646, 2328, 9804, 0, 0},
			{2646, 2329, 9804, 0, 0},
			{2646, 2327, 9804, 0, 0},
			{2646, 2328, 9803, 0, 0},
			{2646, 2329, 9803, 0, 0},
			{2646, 2327, 9803, 0, 0},
			{2646, 2330, 9803, 0, 0},
			
			{170, 3294, 3946, 0, 0}, // rogue castel crates
			{1, 3293, 3946, 0, 0},
			{1, 3295, 3945, 0, 0},
			{1, 3294, 3945, 0, 0},

			
			//rocktail fishing
			{8702, 2327, 9801, 0, 0},
			//frost drags portal
			//rune armour stealing
			{13493, 2316, 9801, 0, 2},
			//special altar
			{8749, 2342, 9805, 0, 3},
			
			//dzone thieve altars
			{4875, 2342, 9799, 0, 0},
			{4874, 2342, 9800, 0, 0},
			{4876, 2342, 9801, 0, 0},
			{4877, 2342, 9802, 0, 0},
			{4878, 2342, 9803, 0, 0},
			
			
			//dzone
			{2213, 2340, 9808, 0, 0},
			{2213, 2339, 9808, 0, 0},
			{2213, 2341, 9808, 0, 0},
			{2213, 2338, 9808, 0, 0},
			{2213, 2337, 9808, 0, 0},
			{2213, 2336, 9808, 0, 0},
			{2213, 2335, 9808, 0, 0},
			{2213, 2334, 9808, 0, 0},
			{2213, 2313, 9798, 0, 0},
			/*End of new Donator Zone*/
			/*Misc*/
		{-1, 3091, 3495, 0, 0},	
		{-1, 2268, 3067, 0, 0},
		{401, 3503, 3567, 0, 0},
		{401, 3504, 3567, 0, 0},
		{1309, 2715, 3465, 0, 0},
		{1309, 2709, 3465, 0, 0},
		{1309, 2709, 3458, 0, 0},
		{1306, 2705, 3465, 0, 0},
		{1306, 2705, 3458, 0, 0},
		{-1, 2715, 3466, 0, 0},
		{-1, 3213, 3430, 0, 0},
		{-1, 3213, 3426, 0, 0},
		{13405, 3083, 3492, 0, 1},
		{-1, 2712, 3466, 0, 0},
		{-1, 2713, 3464, 0, 0},
		{-1, 2711, 3467, 0, 0},
		{-1, 2710, 3465, 0, 0},
		{-1, 2709, 3464, 0, 0},
		{-1, 2708, 3466, 0, 0},
		{-1, 2707, 3467, 0, 0},
		{-1, 2704, 3465, 0, 0},
		{-1, 2714, 3466, 0, 0},
		{-1, 2705, 3457, 0, 0},
		{-1, 2709, 3461, 0, 0},
		{-1, 2709, 3459, 0, 0},
		{-1, 2708, 3458, 0, 0},
		{-1, 2710, 3457, 0, 0},
		{-1, 2711, 3461, 0, 0},
		{-1, 2713, 3461, 0, 0},
		{-1, 2712, 3459, 0, 0},
		{-1, 2712, 3457, 0, 0},
		{-1, 2714, 3458, 0, 0},
		{-1, 2705, 3459, 0, 0},
		{-1, 2705, 3464, 0, 0},
		{2274, 2912, 5300, 2, 0},
		{2274, 2914, 5300, 1, 0},
		{2274, 2919, 5276, 1, 0},
		{2274, 2918, 5274, 0, 0},
		{2274, 3001, 3931, 0, 0},
		{-1, 2884, 9797, 0, 2},
		{29942, 3090, 3499, 0, 2},
		{4483, 2909, 4832, 0, 3},
		{4483, 2901, 5201, 0, 2},
		{4483, 2902, 5201, 0, 2},
		{9326, 3001, 3960, 0, 0},
		{1662, 3112, 9677, 0, 2},
		{1661, 3114, 9677, 0, 2},
		{1661, 3122, 9664, 0, 1},
		{1662, 3123, 9664, 0, 2},
		{1661, 3124, 9664, 0, 3},
		{4483, 2918, 2885, 0, 3},
		{12356, 3081, 3500, 0, 0},
		{2182, 3081, 3497, 0, 0},
		{1746, 3090, 3492, 0, 1},
		{2644, 2737, 3444, 0, 0},
		{-1, 2608, 4777, 0, 0},
		{-1, 2601, 4774, 0, 0},
		{-1, 2611, 4776, 0, 0},
		/**New Member Zone*/
		{2344, 3421, 2908, 0, 0}, //Rock blocking
        {2345, 3438, 2909, 0, 0},
        {2344, 3435, 2909, 0, 0},
        {2344, 3432, 2909, 0, 0},
        {2345, 3431, 2909, 0, 0},
        {2344, 3428, 2921, 0, 1},
        {2344, 3428, 2918, 0, 1},
        {2344, 3428, 2915, 0, 1},
        {2344, 3428, 2912, 0, 1},
        {2345, 3428, 2911, 0, 1},
        {2344, 3417, 2913, 0, 1},
        {2344, 3417, 2916, 0, 1},
        {2344, 3417, 2919, 0, 1},
        {2344, 3417, 2922, 0, 1},
        {2345, 3417, 2912, 0, 0},
        {2346, 3418, 2925, 0, 0},
        {10378, 3426, 2907, 0, 0},
        {8749, 3426, 2923, 0, 2}, //Altar
        {-1, 3420, 2909, 0, 10}, //Remove crate by mining
        {-1, 3420, 2923, 0, 10}, //Remove Rockslide by Woodcutting
        {14859, 3421, 2909, 0, 0}, //Mining
        {14859, 3419, 2909, 0, 0},
        {14859, 3418, 2910, 0, 0},
        {14859, 3418, 2911, 0, 0},
        {14859, 3422, 2909, 0, 0},
        {1306, 3418, 2921, 0, 0}, //Woodcutting
        {1306, 3421, 2924, 0, 0},
        {1306, 3420, 2924, 0, 0},
        {1306, 3419, 2923, 0, 0},
        {1306, 3418, 2922, 0, 0},
		{-1, 3430, 2912, 0, 2}, 
		{13493, 3424, 2916, 0, 1},//Armour  stall
        /**New Member Zone end*/
		{-1, 3098, 3496, 0, 1},
		{-1, 3095, 3498, 0, 1},
		{-1, 3088, 3509, 0, 1},
		{-1, 3095, 3499, 0, 1},
		{-1, 3085, 3506, 0, 1},
	
		{-1, 3206, 3263, 0, 0},
		{-1, 2794, 2773, 0, 0},
		{2, 2692, 3712, 0, 3},
		{2, 2688, 3712, 0, 1},
		{4483, 3081, 3496, 0, 1},
		{4483, 3081, 3495, 0, 1},
		{409, 3085, 3508, 0, 1},
		{6552, 3085, 3510, 0, 1},
		{411, 3090, 3508, 0, 3},
		{13179, 3090, 3510, 0, 3},
		{-1, 3084, 3487, 0, 2},
		{4875, 3094, 3500, 0, 0},
		{4874, 3095, 3500, 0, 0},
		{4876, 3096, 3500, 0, 0},
		{4877, 3097, 3500, 0, 0},
		{4878, 3098, 3500, 0, 0},
		{ 11758, 3019, 9740, 0, 0},
		{ 11758, 3020, 9739, 0, 1},
		{ 11758, 3019, 9738, 0, 2},
		{ 11758, 3018, 9739, 0, 3},
		{ 11933, 3028, 9739, 0, 1},
		{ 11933, 3032, 9737, 0, 2},
		{ 11933, 3032, 9735, 0, 0},
		{ 11933, 3035, 9742, 0, 3},
		{ 11933, 3034, 9739, 0, 0},
		{ 11936, 3028, 9737, 0, 1},
		{ 11936, 3029, 9734, 0, 2},
		{ 11936, 3031, 9739, 0, 0},
		{ 11936, 3032, 9741, 0, 3},
		{ 11936, 3035, 9734, 0, 0},
		{ 11954, 3037, 9739, 0, 1},
		{ 11954, 3037, 9735, 0, 2},
		{ 11954, 3037, 9733, 0, 0},
		{ 11954, 3039, 9741, 0, 3},
		{ 11954, 3039, 9738, 0, 0},
		{ 11963, 3039, 9733, 0, 1},
		{ 11964, 3040, 9732, 0, 2},
		{ 11965, 3042, 9734, 0, 0},
		{ 11965, 3044, 9737, 0, 3},
		{ 11963, 3042, 9739, 0, 0},
		{ 11963, 3045, 9740, 0, 1},
		{ 11965, 3043, 9742, 0, 2},
		{ 11964, 3045, 9744, 0, 0},
		{ 11965, 3048, 9747, 0, 3},
		{ 11951, 3048, 9743, 0, 0},
		{ 11951, 3049, 9740, 0, 1},
		{ 11951, 3047, 9737, 0, 2},
		{ 11951, 3050, 9738, 0, 0},
		{ 11951, 3052, 9739, 0, 3},
		{ 11951, 3051, 9735, 0, 0},
		{ 11947, 3049, 9735, 0, 1},
		{ 11947, 3049, 9734, 0, 2},
		{ 11947, 3047, 9733, 0, 0},
		{ 11947, 3046, 9733, 0, 3},
		{ 11947, 3046, 9735, 0, 0},
		{ 11941, 3053, 9737, 0, 1},
		{ 11939, 3054, 9739, 0, 2},
		{ 11941, 3053, 9742, 0, 0},
		{ 14859, 3038, 9748, 0, 3},
		{ 14859, 3044, 9753, 0, 0},
		{ 14859, 3048, 9754, 0, 1},
		{ 14859, 3054, 9746, 0, 2},
		{ 4306, 3026, 9741, 0, 0},
		{ 6189, 3022, 9742, 0, 1},
		{ 172 , 3083, 3497, 0, 1},
		{3192, 3084, 3485, 0, 4},
		{ -1, 3090, 3503, 0, 0},
		
		


		{ 75 , 2914, 3452, 0, 2},
		
		
	//	{ 47947 , 2720, 5352, 0, 2}, // waterfall o city
	//	{ 47947 , 2720, 5355, 0, 2}, // waterfall o city
		
		
		
		
		{ 11758, 3449, 3722, 0, 0},
		{ 11758, 3450, 3722, 0, 0},
		{ 50547, 3445, 3717, 0, 3},
		{-1, 3085, 3512, 0, 0},
		{ -1, 3090, 3496, 0, 0},
		{ -1, 3090, 3494, 0, 0},
		{ -1, 3092, 3496, 0, 0},
		{ -1, 3659, 3508, 0, 0},
		{ 4053, 3660, 3508, 0, 0},
		{ 4051, 3659, 3508, 0, 0},
		{ 1, 3649, 3506, 0, 0},
		{ 1, 3650, 3506, 0, 0},
		{ 1, 3651, 3506, 0, 0},
		{ 1, 3652, 3506, 0, 0},
		{ 8702, 3423, 2911, 0, 0},
		{ 47180, 3422, 2918, 0, 0}, 
		{ 11356, 3418, 2917, 0, 1},
		{ -1, 2860, 9734, 0, 1},
		{ -1, 2857, 9736, 0, 1},
		{ 664, 2859, 9742, 0, 1},
		{ 1167, 2860, 9742, 0, 1},
		{ 5277, 3691, 3465, 0, 2},
		{ 5277, 3690, 3465, 0, 2},
		{ 5277, 3688, 3465, 0, 2},
		{ 5277, 3687, 3465, 0, 2},
	};
	
	public static List<GameObject> CUSTOM_OBJECT_LIST = new ArrayList<GameObject>();
}
