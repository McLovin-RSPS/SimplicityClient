package com.simplicity.client.cache.maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Load oldschool maps & objects from the cache directly from this file.
 * It will grab the oldschool map files from the osrs maps cache index.
 * Depending on what regions are listed here, oldschool objects will be loaded (reg id) + 100,000
 * Region ids are used to load both the objects and maps
 * @author Jonny
 */
public class OldschoolMaps {

    private static final Map<Integer, Integer> OLDSCHOOL_REGIONS = new HashMap<>();

    public static int[] OLDSCHOOL_REGION_IDS = {
            12695, 14895,
            14650, 14651, 14652,
            14906, 14907, 14908,
            15162, 15163, 15164,
            12598, 12854, 13110,//varrock
            12597, 12853, 13109,//varrock
            12596, 12852, 13108,//varrock
            13105,//alkharid
            12339, 12338,//draynor
            5947, // South of blast mine
            8536, //Demonic gorillas
            8280, // Demonic gorillas
            8789,
            11837,//Wildy agility course
            6457, // Great Kourand
            5179,  //Mount karuulm
            9772, // myth's guild
            13205, 13460, 13204, // kalphite lair
            
            6974, // Christmass event boss 2021
            
            
            
            9823, 9824, // new infernal dungeon
            
            7501, 6473, 9870, 9358, 9360, // seren map test
            
            14496, 14495, // Wyvern cave
            9634, 9890, 9631, // caves
            5277, // cave next to farming boss
            
            11675, // Warrior's guild basement
            
            11164, 10908, 10907, // Fremennik slayer dungeon
            
            14906, 14650, 14652, 14651, 14908, 14907, 15163,  // Hunter location Fossil Island OSRS
                   
            5432, 5433, // Lake Molch
            11316, // Entrana maybe use for donator zone
            
            8757, 8754, 9010, 9265, 9013, 7483, 7482, // Elven fishing locations

            14645, 14901, 14644, 14900, 14643, 14899, 13104,
            
            5789, // CHASM OF FIRE
            
            12951, 13461, 13462, // OSRS fishing place 
            10131, // dungeon
            
            6206, 6462, 6718, 6461, // Wintertodt
            5945, 6201, // Lizardman Canyon 
            
            10894, // Monkey skeletons
            11150, // Monkey skeletons
            12857, // wilderness dungeon
            6715, // Elite zone
            6714, // Elite zone
            10835, 10834, // New MMA
            13372, // Fountain of rune

            // ent dungeon in wc guild
            6298,6223,

            11153,

            // full kourend and lowlands
            4662, 4663, 4664, 4665, 4666, 4667, 4668,
            4918, 4919, 4920, 4921, 4922, 4923, 4924,
            5174, 5175, 5176, 5177, 5178, 5179, 5180,
            5430, 5431, 5432, 5433, 5434, 5435, 5436, 5437,
            5684, 5685, 5686, 5687, 5688, 5689, 5690, 5691, 5692, 5693, 5694,
            5940, 5941, 5942, 5943, 5944, 5945, 5946, 5947, 5948, 5949, 5950, 5951,
            6196, 6197, 6198, 6199, 6200, 6201, 6202, 6203, 6204, 6205, 6206, 6207,
            6452, 6453, 6454, 6455, 6456, 6457, 6458, 6459, 6460, 6461, 6462, 6463,
            6708, 6709, 6710, 6711, 6712, 6713, 6714, 6715, 6716, 6717, 6718, 6719,
            6964, 6965, 6966, 6967, 6968, 6969, 6970, 6971, 6972, 6973, 6974, 6975,
            7220, 7221, 7222, 7223, 7224, 7225, 7226, 7227, 7228, 7229, 7230, 7231,
            7476, 7477, 7478, 7479, 7480, 7481, 7482, 7483, 7484, 7485, 7486, 7487,
            
            12613, 12612, 12869, 13125, 13123, 12611, 13122,
            12867, // Tob treasure

            9519, 10833, 9625, 9624, 9881, 9880,
            9526, 9782, 9525, 9781, 9783, 9527, 13623, 13114, 13102, 12190,
            8253, 8509, 8252, 8508,

            9619, 9363, // Elite dungeon
            14386, 14642,
            4919, 5022, 5023, 5278, 5279, 5280, 5535, 5945,
            5946, 6201, 5536, 4663, 6810, 9023, 9043, 11850, 11851, 12090, 12106, 12362, 12363, 12347, 12605,
            12701, 12702, 12703, 12861, 12889, 12957, 12958, 12959, 12961,
            /* Cerberus regions */
            4626, 4627, 46728, 4882, 4883, 4884, 5138, 5139, 5140,
            11673, 11161, 11417, 11673, 11929, 11928,
            6198, 6454, // Woodcutting guild
            
            11157, // Brimhaven Agility Arena

            6971, 7227, 6970, 7226, 6969, 7225,

            // wilderness slayer dungeon
            13469, 13470, 13726, 13725,

            //slayer basement
            13723,

            //tournament
            13133,
            
            10063,// tzhaar inferno
            
            4922, // Farming Guild
            6967, // Zeah
            6556, 6557, 6813, 6812, // Cataboms of Kourand

            // cave horror cave
            14994, 14995, 15251,

            // new infernal rank donator dungeon
            9824, 9823,

            // mos le harmless
            14381, 14382, 14383, 14384,
            14637, 14638, 14639, 14640,
            14893, 14894, 14895, 14896,
            15149, 15150, 15151, 15152,
            15405, 15406, 15407, 15408,

            // osrs wilderness gwd
            12190,

            //ffa
            12621, 12622, 12623, 6729,
            //slashbash
            9272, 9528, 9271, 9527,
            //zalcano
            12126,
            
            //Overlord zone & new donator zone
            7501, 15262, 12563, 12187,
            
            // Hunter OSRS maps
            11662,
            
            //Prifddinas
            12894, 12895, 13150, 13151,
            
            9116, // Kraken
            13900, // Elite minigame
            
            // Ironman Guild
            8773,
            
            // Charcoal burners & Tithe farm
           6709, 6965, 7221, 6710, 6966, 7222,
            
            // KBD Lever Room
            12192,
            7513,
            7514,
            7769,
            7770,
            8751, 9007, 9008, // Zulrah
            
            5022, 5023, 5279, 5280, 5535, // Alchemical Hydra

            13393, 13394, 13395, 13397, 13136, 13137, 13138, 13139, 13140, 13141, 13145, // Chambers of Xeric
            13379,
            10645, 10644, 10900, 10901, // Brimhaven Dungeon
            12183, 12184,
            14936,
            12948,
            9781,
            5021, // Hespori
            12126, // Zalcano
            15256, 15515, //Nightmare
            12597, 12598, 12852, 12854, 13108, 13109, 13110,
            8755, 8756, 9011, 9012, // Priffdinas
            6455, 10819, // Mimic
            13914, 13915, 13658, 13659, 13617, 12344, 12600, 13918, 13919, 13920, 14174, 14175, 14176, 14430, 14431, 14432, // LMS
            8259,
            12588, 12332, 12078 // tempoross
    };
    static {
        for(Integer region : OLDSCHOOL_REGION_IDS) {
            OLDSCHOOL_REGIONS.put(region, -1);
        }
    }

    public static boolean isOldschoolRegion(int regionId) {
        return OLDSCHOOL_REGIONS.get(regionId) != null;
    }
}
