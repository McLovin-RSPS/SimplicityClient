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
            5947, // South of blast mine
            8536, //Demonic gorillas
            8280, // Demonic gorillas
            8789,
            6457, // Great Kourand
            5179,  //Mount karuulm
            
            5789, // CHASM OF FIRE
            
            6206, 6462, 6718, 6461, // Wintertodt
            5945, 6201, // Lizardman Canyon 
            
            10894, // Monkey skeletons
            11150, // Monkey skeletons

            6715, // Elite zone
            6714, // Elite zone
            10835, 10834, // New MMA
            13372, // Fountain of rune
            
            12613, 12612, 12869, 13125, 13123, 12611, 13122,
            12867, // Tob treasure
            
            9619, 9363, // Elite dungeon
            14386, 14642,
            4919, 5022, 5023, 5279, 5280, 5535, 5945,
            5946, 6201, 5536, 4663, 6810, 9023, 9043, 11850, 11851, 12090, 12106, 12362, 12363, 12347, 12605,
            12701, 12702, 12703, 12861, 12889, 12957, 12958, 12959, 12961,
            /* Cerberus regions */
            4626, 4627, 46728, 4882, 4883, 4884, 5138, 5139, 5140,

            6198, 6454, // Woodcutting guild
            
            11157, // Brimhaven Agility Arena
            
            //tournament
            13133,
            
            10063,// tzhaar inferno
            
            4922, // Farming Guild
            6967, // Zeah
            6556, 6557, 6813, 6812, // Cataboms of Kourand

            //ffa
            12621, 12622, 12623, 6729,
            //slashbash
            9272, 9528, 9271, 9527,
            //zalcano
            12126,
            
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

            13379,

            13393, 13394, 13395, 13397, 13136, 13137, 13138, 13139, 13140, 13141, 13145 // Chambers of Xeric
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
