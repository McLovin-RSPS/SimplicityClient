package com.simplicity.client.cache.definitions;


import java.io.PrintWriter;
import java.util.Arrays;

import com.simplicity.client.CacheArchive;
import com.simplicity.client.Client;
import com.simplicity.client.FrameReader;
import com.simplicity.client.MemCache;
import com.simplicity.client.Model;
import com.simplicity.client.OnDemandFetcher;
import com.simplicity.client.Stream;
import com.simplicity.client.cache.DataType;


@SuppressWarnings("all")
public final class ObjectDefinition {

    public static void dump(int totalObjects, int totalObjects667, int totalObjectsOSRS) {
        try {
            PrintWriter writer = new PrintWriter("./667objects.txt");
            for (int i = 0; i < totalObjects667; i++) {
                ObjectDefinition entityDef = forID(i);
                
                String name = entityDef.name == null ? "null" : entityDef.name;
                
                if (entityDef == null)
                    continue;

                String build = i + " " + name + " ";

                if (entityDef.actions != null && entityDef.actions.length > 0) {
                    build += "[Actions=";
                    for (int index = 0; index < entityDef.actions.length; index++) {
                        if (entityDef.actions[index] != null && !entityDef.actions[index].equalsIgnoreCase("null")) {
                            build += entityDef.actions[index];
                        }
                        if (index == entityDef.actions.length - 1) {
                            build += "] ";
                        }
                    }
                }

                if (entityDef.objectModelIDs != null && entityDef.objectModelIDs.length > 0) {
                    build += "[Models=";
                    for (int index = 0; index < entityDef.objectModelIDs.length; index++) {
                        build += entityDef.objectModelIDs[index] + (index < entityDef.objectModelIDs.length - 1 ? ", " : "] ");
                    }
                }

                if (entityDef.objectModelTypes != null && entityDef.objectModelTypes.length > 0) {
                    build += "[Model Types=";
                    for (int index = 0; index < entityDef.objectModelTypes.length; index++) {
                        build += entityDef.objectModelTypes[index] + (index < entityDef.objectModelTypes.length - 1 ? ", " : "] ");
                    }
                }

                if (entityDef.configObjectIDs != null && entityDef.configObjectIDs.length > 0) {
                    build += "[Config Ids=";
                    for (int index = 0; index < entityDef.configObjectIDs.length; index++) {
                        build += entityDef.configObjectIDs[index] + (index < entityDef.configObjectIDs.length - 1 ? ", " : "] ");
                    }
                }

                if (entityDef.varbitIndex != -1) {
                    build += "[Varbit=" + entityDef.varbitIndex + "] ";
                }
                if (entityDef.configID != -1) {
                    build += "[Config=" + entityDef.configID + "] ";
                }
                writer.println(build);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            PrintWriter writer = new PrintWriter("./osrsobjects.txt");
            for (int i = 100_000; i < 100_000 + totalObjectsOSRS; i++) {
                ObjectDefinition entityDef = getDefOldschool(i);
                
                String name = entityDef.name == null ? "null" : entityDef.name;

                if (entityDef == null)
                    continue;

                String build = i + " " + name + " ";

                if (entityDef.actions != null && entityDef.actions.length > 0) {
                    build += "[Actions=";
                    for (int index = 0; index < entityDef.actions.length; index++) {
                        if (entityDef.actions[index] != null && !entityDef.actions[index].equalsIgnoreCase("null")) {
                            build += entityDef.actions[index];
                        }
                        if (index == entityDef.actions.length - 1) {
                            build += "] ";
                        }
                    }
                }

                if (entityDef.objectModelIDs != null && entityDef.objectModelIDs.length > 0) {
                    build += "[Models=";
                    for (int index = 0; index < entityDef.objectModelIDs.length; index++) {
                        build += entityDef.objectModelIDs[index] + (index < entityDef.objectModelIDs.length - 1 ? ", " : "] ");
                    }
                }

                if (entityDef.objectModelTypes != null && entityDef.objectModelTypes.length > 0) {
                    build += "[Model Types=";
                    for (int index = 0; index < entityDef.objectModelTypes.length; index++) {
                        build += entityDef.objectModelTypes[index] + (index < entityDef.objectModelTypes.length - 1 ? ", " : "] ");
                    }
                }

                if (entityDef.configObjectIDs != null && entityDef.configObjectIDs.length > 0) {
                    build += "[Config Ids=";
                    for (int index = 0; index < entityDef.configObjectIDs.length; index++) {
                        build += entityDef.configObjectIDs[index] + (index < entityDef.configObjectIDs.length - 1 ? ", " : "] ");
                    }
                }

                if (entityDef.varbitIndex != -1) {
                    build += "[Varbit=" + entityDef.varbitIndex + "] ";
                }
                if (entityDef.configID != -1) {
                    build += "[Config=" + entityDef.configID + "] ";
                }

                writer.println(build);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObjectDefinition getDefOldschool(int i) {
        for (int j = 0; j < 200; j++) {
            if (cacheOSRS[j].type == i) {
                return cacheOSRS[j];
            }
        }
        osrsCacheIndex = (osrsCacheIndex + 1) % 200;
        ObjectDefinition objectDef = cacheOSRS[osrsCacheIndex];
        
        //System.out.println("i: " + i);
 
        streamOSRS.currentOffset = streamIndicesOSRS[i - 100_000];
        objectDef.type = i;
        objectDef.setDefaults();
        objectDef.dataType = DataType.OLDSCHOOL;
        try {
        	objectDef.readValuesOSRS(streamOSRS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 132688) {
        	objectDef.canRandomAnimStart = false;
        }
        if(i == 134512) {
            objectDef.animationID = 23328;
        }
        if (i == 129878) {
        	objectDef.modelSizeX = 128;
        	objectDef.modelSizeY = 128;
        	objectDef.modelSizeH = 128;
        	objectDef.animationID = -1;
        }
        if (i == 116553) {
        	objectDef.mapSceneID = -1;
        }
        if (i == 130087) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Use";
            objectDef.actions[1] = "View-presets";
        }
        if (i == 122819) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Use";
            objectDef.actions[1] = "View-presets";
        }
        if (i == 132666) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Use";
            objectDef.actions[1] = "View-presets";
        }
        if (i == 131626) {
            objectDef.actions = new String[]{"Touch", null, null, null, null};
            objectDef.name = "@yel@Statue of Sezy";
        }
        if (i == 126073) {
            objectDef.actions = new String[]{"Pray-at", null, null, null, null};
            objectDef.name = "@yel@Statue of Sezy";
        }
        if (i == 132957) {
            objectDef.description = new String("Chamber").getBytes();
        }
        if (i == 132968) {
            objectDef.description = new String("Arthur promised to come and save him one day..").getBytes();
        }
        if (i == 132938) {
            objectDef.description = new String("Theatre of Blood they said. It will be fun, they said.").getBytes();
        }
        if (i == 132755) {
            objectDef.description = new String("The barriers").getBytes();
        }
        if (i == 134573) {
            objectDef.isUnwalkable = false;
        }
        if (i == 110087) {
            objectDef.actions = new String[]{"Cage", null, null, null, null};
            objectDef.name = "@yel@Dark crab";
        }
        
        boolean debug = false;
        if (i == 132660) {
        	objectDef.animationID = -1;
        }

		if (debug) {
			objectDef.name = ""+i;
			objectDef.hasActions = true;
			objectDef.actions = new String[] {"lol" , null, null, null, null, null};
		}
        return objectDef;
    }

    private static boolean isOSRSObject(int objectId) {
        for (int i = 0; i < OBJECTS_OSRS.length; i++) {
            if (objectId == OBJECTS_OSRS[i]) {
                return true;
            }
        }
        return false;
    }

    private static final int[] OBJECTS_OSRS = {

            732, 4451, 6926, 7823, 7824, 7825, 7826, 7827, 7828, 7829, 7830, 7834,
            11853, 14645, 14674, 14675, 17118, 20196, 21696, 21697, 21698, 21699, 21700, 21701, 21702, 21703, 21704,
            21705, 21706, 21707, 21708, 21709, 21710, 21711, 21712, 21713, 21714, 21715, 21716, 21717, 21718, 21748,
            21749, 21750, 21751, 21752, 21753, 21754, 21755, 21756, 21757, 21758, 21759, 21760, 21761, 21762, 21763,
            21765, 21766, 21767, 21768, 21769, 21770, 21772, 21773, 21775, 21776, 21777, 21779, 21780, 21946, 21947,
            22494, 22495, 23100, 23101, 26571, 26572, 1502, 12930, 12931, 12932, 20737, 23102, 23104, 23106, 23107,
            23108, 23109, 23112, 23610, 26294, 6775, 27059, 27059, 26765,

            /**Kraken**/
            316, 324, 536, 538, 655, 816, 1457, 1459, 1460, 2745, 4909, 5456, 5587, 12299, 14390, 14456, 14457, 14459,
            14460, 14468, 26529, 26530, 26534, 26552, 26555,

            11698, 11699, 11700,

            26765,
            /**
             * Inferno
            */
            30310, 30302
    };
    private final static int[] hotSpotIDs = new int[]{13374, 13375, 13376, 13377, 13378, 39260, 39261, 39262, 39263, 39264, 39265, 2715, 13366, 13367, 13368, 13369, 13370, 13371, 13372, 15361, 15362, 15363, 15366, 15367, 15364, 15365, 15410, 15412, 15411, 15414, 15415, 15413, 15416, 15416, 15418, 15419, 15419, 15419, 15419, 15419, 15419, 15419, 15419, 15402, 15405, 15401, 15398, 15404, 15403, 15400, 15400, 15399, 15302, 15302, 15302, 15302, 15302, 15302, 15304, 15303, 15303, 15301, 15300, 15300, 15300, 15300, 15299, 15299, 15299, 15299, 15298, 15443, 15445, 15447, 15446, 15444, 15441, 15439, 15448, 15450, 15266, 15265, 15264, 15263, 15263, 15263, 15263, 15263, 15263, 15263, 15263, 15267, 15262, 15260, 15261, 15268, 15379, 15378, 15377, 15386, 15383, 15382, 15384, 34255, 15380, 15381, 15346, 15344, 15345, 15343, 15342, 15296, 15297, 15297, 15294, 15293, 15292, 15291, 15290, 15289, 15288, 15287, 15286, 15282, 15281, 15280, 15279, 15278, 15277, 15397, 15396, 15395, 15393, 15392, 15394, 15390, 15389, 15388, 15387, 44909, 44910, 44911, 44908, 15423, 15423, 15423, 15423, 15420, 48662, 15422, 15421, 15425, 15425, 15424, 18813, 18814, 18812, 18815, 18811, 18810, 15275, 15275, 15271, 15271, 15276, 15270, 15269, 13733, 13733, 13733, 13733, 13733, 13733, 15270, 15274, 15273, 15406, 15407, 15408, 15409, 15368, 15375, 15375, 15375, 15375, 15376, 15376, 15376, 15376, 15373, 15373, 15374, 15374, 15370, 15371, 15372, 15369, 15426, 15426, 15435, 15438, 15434, 15434, 15431, 15431, 15431, 15431, 15436, 15436, 15436, 15436, 15436, 15436, 15437, 15437, 15437, 15437, 15437, 15437, 15350, 15348, 15347, 15351, 15349, 15353, 15352, 15354, 15356, 15331, 15331, 15331, 15331, 15355, 15355, 15355, 15355, 15330, 15330, 15330, 15330, 15331, 15331, 15323, 15325, 15325, 15324, 15324, 15329, 15328, 15326, 15327, 15325, 15325, 15324, 15324, 15330, 15330, 15330, 15330, 15331, 15331, 34138, 15330, 15330, 34138, 34138, 15330, 34138, 15330, 15331, 15331, 15337, 15336, 39230, 39231, 36692, 39229, 36676, 34138, 15330, 15330, 34138, 34138, 15330, 34138, 15330, 15331, 15331, 36675, 36672, 36672, 36675, 36672, 36675, 36675, 36672, 15331, 15331, 15330, 15330, 15257, 15256, 15259, 15259, 15327, 15326};

    public static ObjectDefinition forID(int i) {
        boolean loadNew = (
                /*i == 8550 || i == 8551 || i == 7847 || i == 8150 ||*/ i == 32159 || i == 32157 || i == 36672 || i == 36675 || i == 36692 || i == 34138 || i >= 39260 && i <= 39271 || i == 39229
                || i == 39230 || i == 39231 || i == 36676 || i == 36692 || i > 11915 && i <= 11929 || i >= 11426 && i <= 11444 || i >= 14835 && i <= 14845 || i >= 11391 && i <= 11397
                || i >= 12713 && i <= 12715);
        
        boolean loadOSRS = i == 3077;
        
        if (i == 101761) {
        	i = 1306;
        }
        
        if (i == 12010) { // Soul wars object
        	i = 33416;
        }
        
        if (loadOSRS) {
        	i += 100_000;
        }
        
        if (i >= 100_000)
            return getDefOldschool(i);

        for (int j = 0; j < 200; j++) {
            if (cache[j].type == i) {
                return cache[j];
            }
        }

        cacheIndex = (cacheIndex + 1) % 200;
        ObjectDefinition objectDef = cache[cacheIndex];

        try {
            if (i >= streamIndices.length || loadNew)
                stream667.currentOffset = streamIndices667[i];
            else
                stream.currentOffset = streamIndices[i];
        } catch (Exception e) {
            e.printStackTrace();
        }

        objectDef.type = i;
        objectDef.setDefaults();
        if (i > streamIndices.length || loadNew) {
            objectDef.readValues(stream667);
        } else {
            objectDef.readValues(stream);
        }
		/*if (objectDef.objectModelIDs != null)
		for (int d = 0; d < objectDef.objectModelIDs.length; d++) {
			objectDef.objectModelIDs[d] = 4086;
		}*/
        /*Removing doors etc*/
        boolean removeObject = i == 5244 || i == 1116 || i == 2956 || i == 463 || i == 462 || i == 10527 || i == 10529 || i == 40257 || i == 296 || i == 300 || i == 1747 || i == 7332 || i == 7326 || i == 7325 || i == 7385 || i == 7331 || i == 7385 || i == 7320 || i == 7317 || i == 7323 || i == 7354 || i == 1536 || i == 1537 || i == 5126 || i == 1551 || i == 1553 || i == 1516 || i == 1519 || i == 7126 || i == 733 || i == 14233 || i == 14235 || i == 1596 || i == 1597 || i == 14751 || i == 14752 || i == 14923 || i == 36844 || i == 30864 || i == 2514 || i == 1805 || i == 15536 || i == 2399 || i == 14749 || i == 29315 || i == 29316 || i == 29319 || i == 29320 || i == 29360 || i == 1528 || i == 36913 || i == 36915 || i == 15516 || i == 35549 || i == 35551 || i == 26808 || i == 26910 || i == 26913 || i == 24381 || i == 15514 || i == 25891 || i == 26082 || i == 26081 || i == 1530 || i == 16776 || i == 16778 || i == 28589 || i == 1533 || i == 17089 || i == 1600 || i == 1601 || i == 11707 || i == 24376 || i == 24378 || i == 40108 || i == 59 || i == 2069 || i == 36846;
//        boolean removeObject = false;
        if (removeObject) {
            for (int i4 = 0; i4 < objectDef.objectModelIDs.length; i4++)
                objectDef.objectModelIDs[i4] = 0;
            objectDef.isUnwalkable = false;
            return objectDef;
        }
        if (objectDef.varbitIndex <= 484 && objectDef.varbitIndex >= 469) {
            objectDef.configID = objectDef.varbitIndex;
            objectDef.varbitIndex = -1;
        }
        if (objectDef.name != null && i != 591) {
            String s = objectDef.name.toLowerCase();
            if ((s.contains("bank") && !s.contains("closed")) || i == 30087) {
                objectDef.actions = new String[5];
                objectDef.actions[0] = "Use";
                objectDef.actions[1] = "View-presets";
            }
        }
		
        if (i == 9293) { // Tav dung pipe
			objectDef.offsetX = 64;
			objectDef.adjustToTerrain = true;
		}
        if (i == 3485) {
            objectDef.actions = new String[5];
            objectDef.name = "Magic well";
        }
        if (i == 36263) {
            objectDef.actions = new String[5];
            objectDef.name = "POS Gold Bars";
        }
        if (i == 5259) {
            objectDef.actions = new String[5];
            objectDef.name = "Ghost Town Barrier";
            objectDef.actions[0] = "Pass";
        }
        if (i == 10805 || i == 10806 || i == 10807) {
            objectDef.name = "Grand Exchange clerk";
            objectDef.hasActions = true;
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Use";
        }
        if (i == 10091) {
            objectDef.actions = new String[]{"Bait", null, null, null, null};
            objectDef.name = "@yel@Rocktail spot";
        }
        if (i == 10089) {
            objectDef.actions = new String[]{"Bait", null, null, null, null};
            objectDef.name = "@yel@Anglerfish";
        }
        if (i == 13569) {
            objectDef.actions = new String[]{"Fish", null, null, null, null};
            objectDef.name = "@yel@Westerfish";
        }
        if (i == 13570) {
            objectDef.actions = new String[]{"Fish", null, null, null, null};
            objectDef.name = "@yel@Westerfish";
        }
        if (i == 26414) {
            objectDef.actions = new String[]{"Fill-at", null, null, null, null};
            objectDef.name = "@yel@AFK Fountain";
        }
        if (i == 56839) {
            objectDef.actions = new String[]{"Pickup", null, null, null, null};
            objectDef.name = "@yel@Mysterious plant";
        }
        if (i == 61551) {
            objectDef.actions = new String[]{"Cut", null, null, null, null};
            objectDef.name = "@yel@Arthur's tree";
        }
        if (i == 15969) {
            objectDef.actions = new String[]{"AFK-Cut", null, null, null, null};
            objectDef.name = "@yel@Bitternut Tree";
        }
        if (i == 13292) {
            objectDef.actions = new String[]{"Search", null, null, null, null};
            objectDef.name = "@yel@Magic Chest";
        }
        if (i == 40960) {
            objectDef.actions = new String[]{"Search", null, null, null, null};
            objectDef.name = "@gre@Prize Chest";
        }
        if (i == 54408) {
            objectDef.actions = new String[]{"Search", null, null, null, null};
            objectDef.name = "@gre@Halloween Chest";
        }
        if (i == 48661) {
            objectDef.actions = new String[]{"Touch", null, null, null, null};
            objectDef.name = "@yel@Statue of Arthur";
        }
        if (i == 29826) {
            objectDef.actions = new String[]{"Jump-into", null, null, null, null};
            objectDef.name = "@yel@Rabbit hole";
        }
        if (i == 59956) {
            objectDef.actions = new String[]{"Touch", null, null, null, null};
            objectDef.name = "@yel@Statue of Supreme";
        }
        if (i == 28194) {
            objectDef.actions = new String[]{"Touch", null, null, null, null};
            objectDef.name = "@yel@Completionist orb";
        }
        if (i == 46984) {
            objectDef.actions = new String[]{"Pray-at", null, null, null, null};
            objectDef.name = "@yel@Statue of Hellraty";
        }
        if (i == 49653) {
            objectDef.actions = new String[]{"Touch", null, null, null, null};
            objectDef.name = "@yel@Statue of Axemurdera";
        }
        if (i == 41026) {
            objectDef.actions = new String[]{"Touch", null, null, null, null};
            objectDef.name = "@yel@Statue of Risen Siren";
        }
        if (i == 42601) {
            objectDef.actions = new String[]{"Jump-in", null, null, null, null};
            objectDef.name = "@yel@Elite Dungeon";
        }
        if (i == 53979) {
            objectDef.actions = new String[]{"Teleport", null, null, null, null};
            objectDef.name = "@yel@Crystal Boss Zone";
        }
        if (i == 16284) {
            objectDef.actions = new String[]{"Mine", null, null, null, null};
            objectDef.name = "@yel@Large Rock";
        }
        if (i == 10780) {
            objectDef.actions = new String[]{"Enter", null, null, null, null};
            objectDef.name = "@yel@Karuulm Dungeon";
        }
        if (i == 7836 || i == 7808 || i == 7837 || i == 7839) {
            objectDef.hasActions = true;
            objectDef.actions = new String[]{"Dump-weeds", null, null, null, null};
            objectDef.name = "Compost bin";
        }
        if (i == 884) {
            objectDef.actions = new String[]{"Investigate", "Contribute", "Status", null, null};
            objectDef.name = "Well of Goodwill";
        }
        if (i == 11398) {
            objectDef.actions = new String[]{"Leave ", null, null, null, null};
            objectDef.name = "Arthur's Dream";
        }
        if (i == 48669) {
            objectDef.actions = new String[]{"Visit", null, null, null, null};
            objectDef.name = "Sapphire zone";
        }
        if (i == 48673) {
            objectDef.actions = new String[]{"Teleport", null, null, null, null};
            objectDef.name = "Ruby zone";
        }
        if (i == 48675) {
            objectDef.actions = new String[]{"Teleport", null, null, null, null};
            objectDef.name = "Diamond demons";
        }
        if (i == 25014 || i == 25026 || i == 25020 || i == 25019 || i == 25024 || i == 25025 || i == 25016 || i == 5167 || i == 5168) {
            objectDef.actions = new String[5];
        }
        if (i == 1948) {
            objectDef.name = "Wall";
        }
        if (i == 25029) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Go-through";
        }
        if (i == 19187 || i == 19175) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Dismantle";
        }
        if (i == 6434) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Enter";
        }
        if (i == 2182) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Buy-Items";
            objectDef.name = "Culinaromancer's chest";
        }
        if (i == 10177) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Climb-down";
            objectDef.actions[1] = "Climb-up";
        }
        if (i == 11700) {
            objectDef.name = null;
            objectDef.objectModelIDs = new int[]{4086};
            objectDef.description = null;
            objectDef.sizeX = 1;
            objectDef.sizeY = 1;
            objectDef.hasActions = false;
            objectDef.animationID = 1261;
            objectDef.anInt775 = 16;
            objectDef.brightness = 0;
            objectDef.contrast = 0;
            objectDef.mapFunctionID = -1;
            objectDef.modelSizeX = 160;
            objectDef.modelSizeH = 160;
            objectDef.modelSizeY = 160;
            objectDef.mapSceneID = -1;
            objectDef.offsetX = -1;
            objectDef.offsetH = 0;
            objectDef.offsetY = -1;
            objectDef.isUnwalkable = false;
        }
        if (i == 39515) {
            objectDef.name = "Frost Dragon Portal";
        }
        if (i == 2026) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Net";
        }
        if (i == 2029) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Lure";
            objectDef.actions[1] = "Bait";
        }
        if (i == 2030) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Cage";
            objectDef.actions[1] = "Harpoon";
        }
        if (i == 7352) {
            objectDef.name = "Gatestone portal";
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Enter";
        }
        if (i == 11356) {
            objectDef.name = "Training Portal";
        }
        if (i == 47120) {
            objectDef.name = "Altar";
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Craft-rune";
        }
        if (i == 20331) {
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Steal-from";
        }
        if (i == 8799) {
            objectDef.name = "Grand Exchange";
            objectDef.hasActions = true;
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Access";
        }
        if (i == 47180) {
            objectDef.name = "Frost Dragon Portal Device";
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Activate";
        }
        if (i == 8702) {
            objectDef.name = "Rocktail Barrel";
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Fish-from";
        }
        if (i == 2783) {
            objectDef.hasActions = true;
            objectDef.name = "Anvil";
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Smith-on";
        }
        if (i == 172) {
            objectDef.name = "Crystal chest";
        }
        if (i == 134585) {
            objectDef.name = "Stone chest";
        }
        if (i == 37010) {
            objectDef.name = "Wilderness chest";
            objectDef.actions[0] = "Open";
        }
        if (i == 6714) {
            objectDef.hasActions = true;
            objectDef.name = "Door";
            objectDef.actions[0] = "Open";
        }
        if (i == 42151 || i == 42160) {
            objectDef.name = "Rocks";
            objectDef.hasActions = true;
            objectDef.mapSceneID = 11;
        }
        if (i == 42158 || i == 42157) {
            objectDef.name = "Rocks";
            objectDef.hasActions = true;
            objectDef.mapSceneID = 12;
        }
        if (i == 42123 || i == 42124 || i == 42119 || i == 42120 || i == 42118 || i == 42122) {
            objectDef.name = "Tree";
            objectDef.hasActions = true;
            objectDef.actions = new String[]{"Cut", null, null, null, null};
            objectDef.mapSceneID = 0;
        }
        if (i == 42127 || i == 42131 || i == 42133 || i == 42129 || i == 42134) {
            objectDef.name = "Tree";
            objectDef.hasActions = true;
            objectDef.actions = new String[]{"Cut", null, null, null, null};
            objectDef.mapSceneID = 6;
        }
        if (i == 42082 || i == 42083)
            objectDef.mapSceneID = 0;
        if (i >= 42087 && i <= 42117)
            objectDef.mapSceneID = 4;
        if (i > 30000 && objectDef.name != null && objectDef.name.toLowerCase().contains("gravestone"))
            objectDef.mapSceneID = 34;
        if (i == 36676) {
            objectDef.objectModelIDs = new int[]{17374, 17383};
            objectDef.objectModelTypes = null;
        }
        if (i == 34255) {
            objectDef.configID = 8002;
            objectDef.configObjectIDs = new int[]
                    {
                            15385
                    };
        }
        if (i == 13830) {
            //objectDef.objectModelIDs = new int[] {12199};
            objectDef.configID = 8003;
            objectDef.configObjectIDs = new int[]
                    {
                            13217, 13218, 13219, 13220, 13221, 13222, 13223
                    };
        }
        if (i == 21634) {
            objectDef.hasActions = true;
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Sail";
        }
        if (i == 10284) {
            objectDef.name = "Chest";
            objectDef.hasActions = true;
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Open";
        }
        if (i == 22721) {
            objectDef.hasActions = true;
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Smelt";
        }
        if (i == 7837) {
            objectDef.hasActions = true;
            objectDef.actions = new String[5];
        }
        if (i == 26280) {
            objectDef.hasActions = true;
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Study";
        }
        if (i == 27339 || i == 27306) {
            objectDef.hasActions = true;
            objectDef.name = "Mystical Monolith";
            objectDef.actions = new String[5];
            objectDef.actions[0] = "Travel";
            objectDef.actions[1] = "Pray-at";
        }

        for (int i1 : hotSpotIDs) {
            if (i1 == i) {
                objectDef.configID = 8000;
                objectDef.configObjectIDs = new int[]{i, 0 - 1};
            }
        }
        if (i == 15314 || i == 15313) {
            objectDef.configID = 8000;
            objectDef.configObjectIDs = new int[]{i, -1};
        }
        if (i == 15306) {
            objectDef.configID = 8001;
            objectDef.configObjectIDs = new int[]{i, -1, 13015};
        }
        if (i == 15305) {
            objectDef.configID = 8001;
            objectDef.configObjectIDs = new int[]{i, -1, 13016};
        }
        if (i == 15317) {
            objectDef.configID = 8001;
            objectDef.configObjectIDs = new int[]{i, -1, 13096};
        }
        if (i >= 8550 && i <= 8553) {
            objectDef.configObjectIDs = new int[]
                    {
                            8576, 8575, 8574, 8573, 8576, 8576, 8558, 8559, 8560, 8561, 8562, 8562, 8562, 8580, 8581, 8582, 8583, 8584, 8584, 8584, 8535, 8536, 8537, 8538, 8539, 8539, 8539, 8641, 8642, 8643, 8644, 8645, 8645, 8645, 8618, 8619, 8620, 8621, 8622, 8623, 8624, 8624, 8624, 8595, 8596, 8597, 8598, 8599, 8600, 8601, 8601, 8601, 8656, 8657, 8658, 8659, 8660, 8661, 8662, 8663, 8664, 8664, 8664, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8563, 8564, 8565, 8566, 8576, 8576, 8576, 8585, 8586, 8587, 8588, 8576, 8576, 8576, 8540, 8541, 8542, 8543, 8576, 8576, 8576, 8646, 8647, 8648, 8649, 8576, 8576, 8576, 8625, 8626, 8627, 8628, 8629, 8630, 8576, 8576, 8576, 8602, 8603, 8604, 8605, 8606, 8607, 8576, 8576, 8576, 8665, 8666, 8667, 8668, 8669, 8670, 8671, 8672, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8567, 8568, 8569, 8576, 8576, 8576, 8576, 8589, 8590, 8591, 8576, 8576, 8576, 8576, 8544, 8545, 8546, 8576, 8576, 8576, 8576, 8650, 8651, 8652, 8576, 8576, 8576, 8576, 8631, 8632, 8633, 8634, 8635, 8576, 8576, 8576, 8576, 8608, 8609, 8610, 8611, 8612, 8576, 8576, 8576, 8576, 8673, 8674, 8675, 8676, 8677, 8678, 8679, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8570, 8571, 8572, 8576, 8576, 8576, 8576, 8592, 8593, 8594, 8576, 8576, 8576, 8576, 8547, 8548, 8549, 8576, 8576, 8576, 8576, 8653, 8654, 8655, 8576, 8576, 8576, 8576, 8636, 8637, 8638, 8639, 8640, 8576, 8576, 8576, 8576, 8613, 8614, 8615, 8616, 8617, 8576, 8576, 8576, 8576, 8680, 8681, 8682, 8683, 8684, 8685, 8686, 8576, 8576, 8576, 8576
                    };
        }
        if (i == 8551) {
            objectDef.configObjectIDs = new int[]
                    {
                            8576, 8575, 8574, 8573, 8576, 8576, 8558, 8559, 8560, 8561, 8562, 8562, 8562, 8580, 8581, 8582, 8583, 8584, 8584, 8584, 8535, 8536, 8537, 8538, 8539, 8539, 8539, 8641, 8642, 8643, 8644, 8645, 8645, 8645, 8618, 8619, 8620, 8621, 8622, 8623, 8624, 8624, 8624, 8595, 8596, 8597, 8598, 8599, 8600, 8601, 8601, 8601, 8656, 8657, 8658, 8659, 8660, 8661, 8662, 8663, 8664, 8664, 8664, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8563, 8564, 8565, 8566, 8576, 8576, 8576, 8585, 8586, 8587, 8588, 8576, 8576, 8576, 8540, 8541, 8542, 8543, 8576, 8576, 8576, 8646, 8647, 8648, 8649, 8576, 8576, 8576, 8625, 8626, 8627, 8628, 8629, 8630, 8576, 8576, 8576, 8602, 8603, 8604, 8605, 8606, 8607, 8576, 8576, 8576, 8665, 8666, 8667, 8668, 8669, 8670, 8671, 8672, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8567, 8568, 8569, 8576, 8576, 8576, 8576, 8589, 8590, 8591, 8576, 8576, 8576, 8576, 8544, 8545, 8546, 8576, 8576, 8576, 8576, 8650, 8651, 8652, 8576, 8576, 8576, 8576, 8631, 8632, 8633, 8634, 8635, 8576, 8576, 8576, 8576, 8608, 8609, 8610, 8611, 8612, 8576, 8576, 8576, 8576, 8673, 8674, 8675, 8676, 8677, 8678, 8679, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8570, 8571, 8572, 8576, 8576, 8576, 8576, 8592, 8593, 8594, 8576, 8576, 8576, 8576, 8547, 8548, 8549, 8576, 8576, 8576, 8576, 8653, 8654, 8655, 8576, 8576, 8576, 8576, 8636, 8637, 8638, 8639, 8640, 8576, 8576, 8576, 8576, 8613, 8614, 8615, 8616, 8617, 8576, 8576, 8576, 8576, 8680, 8681, 8682, 8683, 8684, 8685, 8686, 8576, 8576, 8576, 8576
                    };
        }
        if (i >= 7847 && i <= 7849) {
            objectDef.configObjectIDs = new int[]
                    {
                            7843, 7842, 7841, 7840, 7843, 7843, 7843, 7843, 7867, 7868, 7869, 7870, 7871, 7899, 7900, 7901, 7902, 7903, 7883, 7884, 7885, 7886, 7887, 7919, 7920, 7921, 7922, 7923, 7851, 7852, 7853, 7854, 7855, 7918, 7917, 7916, 7915, 41538, 41539, 41540, 41541, 41542, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7872, 7873, 7874, 7875, 7843, 7904, 7905, 7906, 7907, 7843, 7888, 7889, 7890, 7891, 7843, 7924, 7925, 7926, 7927, 7843, 7856, 7857, 7858, 7859, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7876, 7877, 7878, 7843, 7843, 7908, 7909, 7910, 7843, 7843, 7892, 7893, 7894, 7843, 7843, 7928, 7929, 7930, 7843, 7843, 7860, 7861, 7862, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7879, 7880, 7881, 7882, 7843, 7911, 7912, 7913, 7914, 7843, 7895, 7896, 7897, 7898, 7843, 7931, 7932, 7933, 7934, 7843, 7863, 7864, 7865, 7866, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843
                    };
        }

        if (i == 7850) {
            objectDef.configObjectIDs = new int[]
                    {
                            7846, 7845, 7844, 7840, 7846, 7846, 7846, 7846, 7867, 7868, 7869, 7870, 7871, 7899, 7900, 7901, 7902, 7903, 7883, 7884, 7885, 7886, 7887, 7919, 7920, 7921, 7922, 7923, 7851, 7852, 7853, 7854, 7855, 9340, 9339, 9338, 7915, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7872, 7873, 7874, 7875, 7846, 7904, 7905, 7906, 7907, 7846, 7888, 7889, 7890, 7891, 7846, 7924, 7925, 7926, 7927, 7846, 7856, 7857, 7858, 7859, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7846, 7876, 7877, 7878, 7846, 7846, 7908, 7909, 7910, 7846, 7846, 7892, 7893, 7894, 7843, 7843, 7928, 7929, 7930, 7843, 7843, 7860, 7861, 7862, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7879, 7880, 7881, 7882, 7843, 7911, 7912, 7913, 7914, 7843, 7895, 7896, 7897, 7898, 7843, 7931, 7932, 7933, 7934, 7843, 7863, 7864, 7865, 7866, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843
                    };
        }

        if (i == 8150 || i == 8151 || i == 8152) {
            objectDef.configObjectIDs = new int[] {
                    8135, 8134, 8133, 8132, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8147, 8148, 8149, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 9044, 9045, 9046, 9047, 9048, 9048, 9049, 9050, 9051, 9052, 9053, 9054, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, -1, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135
            };
        }

        if (i == 8153) {
            objectDef.configObjectIDs = new int[] {
                    8138, 8137, 8136, 8132, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8147, 8148, 8149, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 9044, 9045, 9046, 9047, 9048, 9048, 9049, 9050, 9051, 9052, 9053, 9054, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, -1, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138, 8138
            };
        }

        if (i == 8554 || i == 8555) {
            objectDef.configObjectIDs = new int[] {
                    8576, 8575, 8574, 8573, 8576, 8576, 8558, 8559, 8560, 8561, 8562, 8562, 8562, 8580, 8581, 8582, 8583, 8584, 8584, 8584, 8535, 8536, 8537, 8538, 8539, 8539, 8539, 8641, 8642, 8643, 8644, 8645, 8645, 8645, 8618, 8619, 8620, 8621, 8622, 8623, 8624, 8624, 8624, 8595, 8596, 8597, 8598, 8599, 8600, 8601, 8601, 8601, 8656, 8657, 8658, 8659, 8660, 8661, 8662, 8663, 8664, 8664, 8664, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8563, 8564, 8565, 8566, 8576, 8576, 8576, 8585, 8586, 8587, 8588, 8576, 8576, 8576, 8540, 8541, 8542, 8543, 8576, 8576, 8576, 8646, 8647, 8648, 8649, 8576, 8576, 8576, 8625, 8626, 8627, 8628, 8629, 8630, 8576, 8576, 8576, 8602, 8603, 8604, 8605, 8606, 8607, 8576, 8576, 8576, 8665, 8666, 8667, 8668, 8669, 8670, 8671, 8672, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8567, 8568, 8569, 8576, 8576, 8576, 8576, 8589, 8590, 8591, 8576, 8576, 8576, 8576, 8544, 8545, 8546, 8576, 8576, 8576, 8576, 8650, 8651, 8652, 8576, 8576, 8576, 8576, 8631, 8632, 8633, 8634, 8635, 8576, 8576, 8576, 8576, 8608, 8609, 8610, 8611, 8612, 8576, 8576, 8576, 8576, 8673, 8674, 8675, 8676, 8677, 8678, 8679, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8576, 8570, 8571, 8572, 8576, 8576, 8576, 8576, 8592, 8593, 8594, 8576, 8576, 8576, 8576, 8547, 8548, 8549, 8576, 8576, 8576, 8576, 8653, 8654, 8655, 8576, 8576, 8576, 8576, 8636, 8637, 8638, 8639, 8640, 8576, 8576, 8576, 8576, 8613, 8614, 8615, 8616, 8617, 8576, 8576, 8576, 8576, 8680, 8681, 8682, 8683, 8684, 8685, 8686, 8576, 8576, 8576, 8576
            };
        }

        if (i == 8556 || i == 8557) {
            objectDef.configObjectIDs = new int[] {
                    8579, 8578, 8577, 8573, 8579, 8579, 8558, 8559, 8560, 8561, 8562, 8562, 8562, 8580, 8581, 8582, 8583, 8584, 8584, 8584, 8535, 8536, 8537, 8538, 8539, 8539, 8539, 8641, 8642, 8643, 8644, 8645, 8645, 8645, 8618, 8619, 8620, 8621, 8622, 8623, 8624, 8624, 8624, 8595, 8596, 8597, 8598, 8599, 8600, 8601, 8601, 8601, 8656, 8657, 8658, 8659, 8660, 8661, 8662, 8663, 8664, 8664, 8664, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8563, 8564, 8565, 8566, 8579, 8579, 8579, 8585, 8586, 8587, 8588, 8579, 8579, 8579, 8540, 8541, 8542, 8543, 8579, 8579, 8579, 8646, 8647, 8648, 8649, 8579, 8579, 8579, 8625, 8626, 8627, 8628, 8629, 8630, 8579, 8579, 8579, 8602, 8603, 8604, 8605, 8606, 8607, 8579, 8579, 8579, 8665, 8666, 8667, 8668, 8669, 8670, 8671, 8672, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8567, 8568, 8569, 8579, 8579, 8579, 8579, 8589, 8590, 8591, 8579, 8579, 8579, 8579, 8544, 8545, 8546, 8579, 8579, 8579, 8579, 8650, 8651, 8652, 8579, 8579, 8579, 8579, 8631, 8632, 8633, 8634, 8635, 8579, 8579, 8579, 8579, 8608, 8609, 8610, 8611, 8612, 8579, 8579, 8579, 8579, 8673, 8674, 8675, 8676, 8677, 8678, 8679, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8579, 8570, 8571, 8572, 8579, 8579, 8579, 8579, 8592, 8593, 8594, 8579, 8579, 8579, 8579, 8547, 8548, 8549, 8579, 8579, 8579, 8579, 8653, 8654, 8655, 8579, 8579, 8579, 8579, 8636, 8637, 8638, 8639, 8640, 8579, 8579, 8579, 8579, 8613, 8614, 8615, 8616, 8617, 8579, 8579, 8579, 8579, 8680, 8681, 8682, 8683, 8684, 8685, 8686, 8579, 8579, 8579, 8579
            };
        }

        if (i >= 8388 && i <= 8391) {
            objectDef.configObjectIDs = new int[] {
                    8395, 8394, 8393, 8392, 8395, 8395, 8395, 8395, 8462, 8463, 8464, 8465, 8466, 8467, 8468, 8481, 8482, 8483, 8484, 8485, 8486, 8487, 8488, 8489, 8435, 8436, 8437, 8438, 8439, 8440, 8441, 8442, 8443, 8444, 8445, 8502, 8503, 8504, 8505, 8506, 8507, 8508, 8509, 8510, 8511, 8512, 8513, 8514, 8396, 8397, 8398, 8399, 8400, 8401, 8402, 8403, 8404, 8405, 8406, 8407, 8408, 8409, 8410, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8473, 8474, 8475, -1, 8476, 8395, 8395, 8490, 8491, 8492, 8493, 8494, -1, 8495, 8395, 8395, 8446, 8447, 8448, 8449, 8450, 8451, 8452, -1, 8453, 8395, 8395, 8515, 8516, 8517, 8518, 8519, 8520, 8521, 8522, 8523, -1, 8524, 8395, 8395, 8411, 8412, 8413, 8414, 8415, 8416, 8417, 8418, 8419, 8420, 8421, -1, 8422, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8477, 8478, 8479, -1, 8480, 8395, 8395, 8496, 8497, 8498, 8499, 8500, -1, 8501, 8395, 8395, 8454, 8455, 8456, 8457, 8458, 8459, 8460, -1, 8461, 8395, 8395, 8525, 8526, 8527, 8528, 8529, 8530, 8531, 8532, 8533, -1, 8534, 8395, 8395, 8423, 8424, 8425, 8426, 8427, 8428, 8429, 8430, 8431, 8432, 8433, -1, 8434, 8314, 8395, 8488, 8488, 8488, 8488, 8488, 8488, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395, 8395
            };
        }

        switch (i) {
            case 47748:
                objectDef.actions = new String[]{"Cut", null, null, null, null};
                objectDef.name = "Christmas AFK Tree";
                break;
            /*case 14309:
                objectDef.actions = new String[]{"Cut", null, null, null, null};
                objectDef.name = "AFK Tree";
                break; */
            case 35469:
            case 36695:
            case 21764:
                objectDef.actions = new String[]{"Drink-from", null, null, null, null};
                objectDef.name = "Fountain of Life";
                break;
            case 11435:
                objectDef.actions = new String[]{"Cut", null, null, null, null};
                objectDef.name = "Gold Evil Tree";
                break;
            /**
             * Treasure Chests
             */
            case 10621:
            case 24204:
                objectDef.name = "Treasure chest";
                objectDef.actions = new String[]{"Search", null, null, null, null};
                break;
            case 29577:
            case 46243:
                objectDef.name = "Treasure chest";
                objectDef.actions = new String[]{"Open", null, null, null, null};
                break;

            case 6725:
            case 6714:
            case 6734:
            case 6730:
            case 6749:
            case 6742:
            case 6723:
            case 6728:
            case 6747:
            case 6744:
            case 6741:
            case 6779:
            case 6743:
            case 6719:
            case 6717:
            case 6731:
            case 6716:
            case 6720:
            case 6738:
            case 6726:
            case 6740:
            case 6721:
            case 6748:
            case 6729:
            case 6745:
            case 6718:
            case 6780:
            case 6746:
            case 6750:
            case 6722:
            case 6715:
                objectDef.name = "Door";
                objectDef.hasActions = true;
                break;
            case 4875:
                objectDef.name = "Banana Stall";
                break;
            case 4874:
                objectDef.name = "Ring Stall";
                break;
            case 13493:
                objectDef.actions = new String[5];
                objectDef.actions[0] = "Steal from";
                break;
            case 2152:
                objectDef.actions = new String[5];
                objectDef.actions[0] = "Infuse Pouches";
                objectDef.actions[1] = "Renew Points";
                objectDef.name = "Summoning Obelisk";
                break;
            case 4306:
                objectDef.actions = new String[5];
                objectDef.actions[0] = "Use";
                break;
            case 2732:
            case 52709:
                objectDef.actions = new String[5];
                objectDef.actions[0] = "Add logs";
                break;
            case 2:
                objectDef.name = "Entrance";
                break;
        }

        if (i == 8151) {
            objectDef.configObjectIDs = new int[] {
                    8135, 8134, 8133, 8132, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, 8139, 8140, 8141, 8142, 8143, 8143, 8143, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8144, 8145, 8146, 8147, 8148, 8149, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 9044, 9045, 9046, 9047, 9048, 9048, 9049, 9050, 9051, 9052, 9053, 9054, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, -1, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135, 8135
            };
        }

        if (i == 7848) {
            objectDef.configObjectIDs = new int[] {
                    7843, 7842, 7841, 7840, 7843, 7843, 7843, 7843, 7867, 7868, 7869, 7870, 7871, 7899, 7900, 7901, 7902, 7903, 7883, 7884, 7885, 7886, 7887, 7919, 7920, 7921, 7922, 7923, 7851, 7852, 7853, 7854, 7855, 7918, 7917, 7916, 7915, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7872, 7873, 7874, 7875, 7843, 7904, 7905, 7906, 7907, 7843, 7888, 7889, 7890, 7891, 7843, 7924, 7925, 7926, 7927, 7843, 7856, 7857, 7858, 7859, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7876, 7877, 7878, 7843, 7843, 7908, 7909, 7910, 7843, 7843, 7892, 7893, 7894, 7843, 7843, 7928, 7929, 7930, 7843, 7843, 7860, 7861, 7862, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7879, 7880, 7881, 7882, 7843, 7911, 7912, 7913, 7914, 7843, 7895, 7896, 7897, 7898, 7843, 7931, 7932, 7933, 7934, 7843, 7863, 7864, 7865, 7866, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843, 7843
            };
        }
	
		boolean debug = false;

		if (debug) {
			objectDef.name = ""+i;
			objectDef.hasActions = true;
			objectDef.actions = new String[] {"lol" , null, null, null, null, null};
		}
        return objectDef;
    }

    private void setDefaults() {
        objectModelIDs = null;
        objectModelTypes = null;
        name = null;
        description = null;
        modifiedModelColors = null;
        originalModelColors = null;
        sizeX = 1;
        sizeY = 1;
        isUnwalkable = true;
        aBoolean757 = true;
        hasActions = false;
        adjustToTerrain = false;
        nonFlatShading = false;
        aBoolean764 = false;
        animationID = -1;
        anInt775 = 16;
        brightness = 0;
        contrast = 0;
        actions = null;
        mapFunctionID = -1;
        mapSceneID = -1;
        aBoolean751 = false;
        aBoolean779 = true;
        modelSizeX = 128;
        modelSizeH = 128;
        modelSizeY = 128;
        plane = 0;
        offsetX = 0;
        offsetH = 0;
        offsetY = 0;
        aBoolean736 = false;
        isSolidObject = false;
        anInt760 = -1;
        varbitIndex = -1;
        configID = -1;
        configObjectIDs = null;
    }

    public void method574(OnDemandFetcher fetcher) {
        System.out.println("shud do this one " + type);
        if (objectModelIDs == null)
            return;
        System.out.println("shud do this one " + type);
        for (int j = 0; j < objectModelIDs.length; j++) {
            fetcher.insertExtraFilesRequest(objectModelIDs[j] & 0xffff, 0);
        }
    }

    public static void nullLoader() {
        modelCache = null;
        osrsModelCache = null;
        completedModelCache = null;
        completedOSRSModelCache = null;
        streamIndices = null;
        cache = null;
        cacheOSRS = null;
        stream = null;
        stream667 = null;
        streamOSRS = null;
    }
    
    public static int totalObjects;
    public static int totalObjects667;
    public static int totalObjectsOSRS;

    public static void unpackConfig(CacheArchive streamLoader) {
        stream = new Stream(streamLoader.getDataForName("loc.dat"));
        Stream stream = new Stream(streamLoader.getDataForName("loc.idx"));
        stream667 = new Stream(streamLoader.getDataForName("loc2.dat"));
        Stream streamIdx667 = new Stream(streamLoader.getDataForName("loc2.idx"));
        streamOSRS = new Stream(streamLoader.getDataForName("loc3.dat"));
        Stream streamIdxOSRS = new Stream(streamLoader.getDataForName("loc3.idx"));

        //FileOperations.WriteFile("C:\\Users\\te12ga8\\Dropbox\\Source\\data\\clipping\\objects\\loc.dat", ObjectDefinition.stream.buffer);
        //FileOperations.WriteFile("C:\\Users\\te12ga8\\Dropbox\\Source\\data\\clipping\\objects\\loc.idx", stream.buffer);
        //FileOperations.WriteFile("C:\\Users\\te12ga8\\Dropbox\\Source\\data\\clipping\\objects\\loc2.dat", stream667.buffer);
        //FileOperations.WriteFile("C:\\Users\\te12ga8\\Dropbox\\Source\\data\\clipping\\objects\\loc2.idx", streamIdx667.buffer);

        totalObjects = stream.readUnsignedWord();
        totalObjects667 = streamIdx667.readUnsignedWord();
        totalObjectsOSRS = streamIdxOSRS.readUnsignedWord();

        streamIndices = new int[totalObjects];
        streamIndices667 = new int[totalObjects667];
        streamIndicesOSRS = new int[totalObjectsOSRS];

        int i = 2;

        for (int j = 0; j < totalObjects; j++) {
            streamIndices[j] = i;
            i += stream.readUnsignedWord();
        }

        i = 2;

        for (int j = 0; j < totalObjects667; j++) {
            streamIndices667[j] = i;
            i += streamIdx667.readUnsignedWord();
        }

        i = 2;

        for (int j = 0; j < totalObjectsOSRS; j++) {
            streamIndicesOSRS[j] = i;
            i += streamIdxOSRS.readUnsignedWord();
        }

        cache = new ObjectDefinition[200];
        cacheOSRS = new ObjectDefinition[200];

        for (int k = 0; k < 200; k++) {
            cache[k] = new ObjectDefinition();
            cacheOSRS[k] = new ObjectDefinition();
        }

        //dump(totalObjects, totalObjects667, totalObjectsOSRS);
    }

    public boolean allModelsFetched(int i) {
        if (objectModelTypes == null) {
            if (objectModelIDs == null)
                return true;
            if (i != 10)
                return true;
            boolean flag1 = true;
            for (int k = 0; k < objectModelIDs.length; k++) {
                flag1 &= Model.modelIsFetched(objectModelIDs[k] & 0xffff, dataType);
            }
            return flag1;
        }
        for (int j = 0; j < objectModelTypes.length; j++)
            if (objectModelTypes[j] == i)
                return Model.modelIsFetched(objectModelIDs[j] & 0xffff, dataType);

        return true;
    }

    public Model renderObject(int objectType, int face, int zA, int zB, int zD, int zC, int k1) {
        Model model = getAnimatedModel(objectType, k1, face);
        if (model == null)
            return null;
        if (adjustToTerrain || nonFlatShading)
            model = new Model(adjustToTerrain, nonFlatShading, model);
        if (adjustToTerrain) {
            int l1 = (zA + zB + zD + zC) / 4;
            for (int i2 = 0; i2 < model.numberOfVerticeCoordinates; i2++) {
                int vertexX = model.verticesXCoordinate[i2];
                int vertexZ = model.verticesZCoordinate[i2];
                int l2 = zA + ((zB - zA) * (vertexX + 64)) / 128;
                int i3 = zC + ((zD - zC) * (vertexX + 64)) / 128;
                int j3 = l2 + ((i3 - l2) * (vertexZ + 64)) / 128;
                model.verticesYCoordinate[i2] += j3 - l1;
            }
            model.normalise();
        }
        return model;
    }

    public boolean isAllModelsFetched() {
        if (objectModelIDs == null)
            return true;
        boolean flag1 = true;
        for (int i = 0; i < objectModelIDs.length; i++)
            flag1 &= Model.modelIsFetched(objectModelIDs[i] & 0xffff, dataType);
        return flag1;
    }

    public ObjectDefinition getTransformedObject() {
        int configIdx = -1;
        if (varbitIndex != -1) {
            VarBit varBit = VarBit.cache[varbitIndex];
            int j = varBit.configId;
            int k = varBit.leastSignificantBit;
            int l = varBit.mostSignificantBit;
            int i1 = Client.anIntArray1232[l - k];
            configIdx = clientInstance.variousSettings[j] >> k & i1;
        } else if (configID != -1)
            configIdx = clientInstance.variousSettings[configID];
        if (configIdx < 0 || configIdx >= configObjectIDs.length || configObjectIDs[configIdx] == -1)
            return null;
        else {
            return forID(configObjectIDs[configIdx]);
        }
    }

    private Model getAnimatedModel(int objectType, int animId, int face) {
        Model model = null;
        long hash;
        if (objectModelTypes == null) {
            if (objectType != 10)
                return null;
            hash = (long) ((type << 8) + face) + ((long) (animId + 1) << 32);
            Model model_1 = dataType == DataType.OLDSCHOOL ? (Model) completedOSRSModelCache.get(hash) : (Model) completedModelCache.get(hash);
            if (model_1 != null)
                return model_1;
            if (objectModelIDs == null)
                return null;
            boolean mirror = aBoolean751 ^ (face > 3);
            int modelAmt = objectModelIDs.length;
            for (int ptr = 0; ptr < modelAmt; ptr++) {
                int subModelID = objectModelIDs[ptr];
                if (mirror)
                    subModelID += 0x10000;
                model = dataType == DataType.OLDSCHOOL ? (Model) osrsModelCache.get(subModelID) : (Model) modelCache.get(subModelID);
                if (model == null) {
                    model = Model.fetchModel(subModelID & 0xffff, dataType);
                    if (model == null)
                        return null;
                    if (mirror)
                        model.mirrorModel();
                    if (dataType == DataType.OLDSCHOOL)
                        osrsModelCache.put(model, subModelID);
                    else
                        modelCache.put(model, subModelID);
                }
                if (modelAmt > 1)
                    modelParts[ptr] = model;
            }
            if (modelAmt > 1)
                model = new Model(modelAmt, modelParts);
        } else {
            int i1 = -1;
            for (int j1 = 0; j1 < objectModelTypes.length; j1++) {
                if (objectModelTypes[j1] != objectType)
                    continue;
                i1 = j1;
                break;
            }
            if (i1 == -1)
                return null;
            hash = (long) ((type << 8) + (i1 << 3) + face) + ((long) (animId + 1) << 32);
            Model model_2 = dataType == DataType.OLDSCHOOL ? (Model) completedOSRSModelCache.get(hash) : (Model) completedModelCache.get(hash);
            if (model_2 != null)
                return model_2;
            int subModelId = objectModelIDs[i1];
            boolean mirror = aBoolean751 ^ (face > 3);
            if (mirror)
                subModelId += 0x10000;
            model = dataType == DataType.OLDSCHOOL ? (Model) osrsModelCache.get(subModelId) : (Model) modelCache.get(subModelId);
            if (model == null) {
                model = Model.fetchModel(subModelId & 0xffff, dataType);
                if (model == null)
                    return null;
                if (mirror)
                    model.mirrorModel();
                
                if (dataType == DataType.OLDSCHOOL)
                    osrsModelCache.put(model, subModelId);
                else
                    modelCache.put(model, subModelId);
            }
        }
        boolean rescale;
        rescale = modelSizeX != 128 || modelSizeH != 128 || modelSizeY != 128;
        boolean hasOffsets;
        hasOffsets = offsetX != 0 || offsetH != 0 || offsetY != 0;
        Model model_3 = new Model(modifiedModelColors == null, FrameReader.isNullFrame(animId), face == 0 && animId == -1 && !rescale && !hasOffsets, model);
        if (animId != -1) {
            model_3.createBones();
            model_3.applyTransform(animId, dataType);
            model_3.triangleSkin = null;
            model_3.vertexSkin = null;
        }
        while (face-- > 0)
            model_3.rotateBy90();
        if (modifiedModelColors != null) {
            for (int k2 = 0; k2 < modifiedModelColors.length; k2++)
                model_3.recolour(modifiedModelColors[k2], originalModelColors[k2]);
        }
        if (rescale)
            model_3.scaleT(modelSizeX, modelSizeY, modelSizeH);
        if (hasOffsets)
            model_3.translate(offsetX, offsetH, offsetY);
        //model_3.light(74, 1000, -90, -580, -90, !nonFlatShading);
        model_3.light(64 + brightness, 768 + contrast * 5, -50, -10, -50, !nonFlatShading);
        //64 + aByte737, 768 + aByte742 * 5, -50, -10, -50, !aBoolean769
        if (anInt760 == 1)
            model_3.myPriority = model_3.modelHeight;
        if (dataType == DataType.OLDSCHOOL)
            completedOSRSModelCache.put(model_3, hash);
        else
            completedModelCache.put(model_3, hash);
        return model_3;
    }

    private void readValues(Stream stream) {
        boolean osrs = stream == streamOSRS;
        int i = -1;
        label0:
        do {
            int opcode;
            do {
                opcode = stream.readUnsignedByte();
                if (opcode == 0)
                    break label0;
                if (opcode == 1) {
                    int k = stream.readUnsignedByte();
                    if (k > 0)
                        if (objectModelIDs == null) {
                            objectModelTypes = new int[k];
                            objectModelIDs = new int[k];
                            for (int k1 = 0; k1 < k; k1++) {
                                objectModelIDs[k1] = stream.readUnsignedWord();
                                objectModelTypes[k1] = stream.readUnsignedByte();
                            }
                        } else {
                            stream.currentOffset += k * 3;
                        }
                } else if (opcode == 2)
                    name = stream.readString();
                else if (opcode == 3)
                    description = stream.readBytes();
                else if (opcode == 5) {
                    int l = stream.readUnsignedByte();
                    if (l > 0)
                        if (objectModelIDs == null) {
                            objectModelTypes = null;
                            objectModelIDs = new int[l];
                            for (int l1 = 0; l1 < l; l1++) {
                                objectModelIDs[l1] = stream.readUnsignedWord();
                            }
                        } else {
                            stream.currentOffset += l * 2;
                        }
                } else if (opcode == 14)
                    sizeX = stream.readUnsignedByte();
                else if (opcode == 15)
                    sizeY = stream.readUnsignedByte();
                else if (opcode == 17)
                    isUnwalkable = false;
                else if (opcode == 18)
                    aBoolean757 = false;
                else if (opcode == 19) {
                    i = stream.readUnsignedByte();
                    if (i == 1)
                        hasActions = true;
                } else if (opcode == 21)
                    adjustToTerrain = true;
                else if (opcode == 22)
                    nonFlatShading = false;
                else if (opcode == 23)
                    aBoolean764 = true;
                else if (opcode == 24) {
                    animationID = stream.readUnsignedWord();
                    if (osrs) {
                        animationID += Animation.OSRS_ANIM_OFFSET;
                    }
                    if (animationID == 65535)
                        animationID = -1;
                } else if (opcode == 28)
                    anInt775 = stream.readUnsignedByte();
                else if (opcode == 29)
                    brightness = stream.readSignedByte();
                else if (opcode == 39)
                    contrast = stream.readSignedByte();
                else if (opcode >= 30 && opcode < 39) {
                    if (actions == null)
                        actions = new String[10];
                    actions[opcode - 30] = stream.readString();
					if (actions[opcode - 30] != null && actions[opcode - 30].equalsIgnoreCase("hidden"))
                        actions[opcode - 30] = null;
                } else if (opcode == 40) {
                    int i1 = stream.readUnsignedByte();
                    modifiedModelColors = new int[i1];
                    originalModelColors = new int[i1];
                    for (int i2 = 0; i2 < i1; i2++) {
                        modifiedModelColors[i2] = stream.readUnsignedWord();
                        originalModelColors[i2] = stream.readUnsignedWord();
                    }
                } else if (opcode == 60 && !osrs)
                    mapFunctionID = stream.readUnsignedWord();
                else if (opcode == 62)
                    aBoolean751 = true;
                else if (opcode == 64)
                    aBoolean779 = false;
                else if (opcode == 65)
                    modelSizeX = stream.readUnsignedWord();
                else if (opcode == 66)
                    modelSizeH = stream.readUnsignedWord();
                else if (opcode == 67)
                    modelSizeY = stream.readUnsignedWord();
                else if (opcode == 68)
                    mapSceneID = stream.readUnsignedWord();
                else if (opcode == 69)
                    plane = stream.readUnsignedByte();
                else if (opcode == 70)
                    offsetX = stream.readSignedWord();
                else if (opcode == 71)
                    offsetH = stream.readSignedWord();
                else if (opcode == 72)
                    offsetY = stream.readSignedWord();
                else if (opcode == 73)
                    aBoolean736 = true;
                else if (opcode == 74) {
                    isSolidObject = true;
                } else if (osrs && opcode == 82) {
    				mapFunctionID = stream.readUnsignedWord();

    				if (mapFunctionID == 0xFFFF) {
    					mapFunctionID = -1;
    				}
                } else {
                    if (opcode != 75)
                        continue;
                    anInt760 = stream.readUnsignedByte();
                }
                continue label0;
            } while (opcode != 77);
            varbitIndex = stream.readUnsignedWord();
            if (varbitIndex == 65535)
                varbitIndex = -1;
            configID = stream.readUnsignedWord();
            if (configID == 65535)
                configID = -1;
            int j1 = stream.readUnsignedByte();
            configObjectIDs = new int[j1 + 1];
            for (int j2 = 0; j2 <= j1; j2++) {
                configObjectIDs[j2] = stream.readUnsignedWord();
                if (configObjectIDs[j2] == 65535)
                    configObjectIDs[j2] = -1;
            }

        } while (true);
        if (i == -1) {
            hasActions = objectModelIDs != null && (objectModelTypes == null || objectModelTypes[0] == 10);
            if (actions != null)
                hasActions = true;
        }
        if (isSolidObject) {
            isUnwalkable = false;
            aBoolean757 = false;
        }
        if (anInt760 == -1)
            anInt760 = isUnwalkable ? 1 : 0;
    }
    
	public void readValuesOSRS(Stream stream) {
		int flag = -1;
		do {
			int opcode = stream.readUnsignedByte();
			if (opcode == 0)
				break;
			if (opcode == 1) {
				int len = stream.readUnsignedByte();
				if (len > 0) {
					objectModelTypes = new int[len];
					objectModelIDs = new int[len];
						
					for (int k1 = 0; k1 < len; k1++) {
						objectModelIDs[k1] = stream.readUnsignedWord();
						objectModelTypes[k1] = stream.readUnsignedByte();
					}
				}
			} else if (opcode == 2)
				name = stream.readString();
			else if (opcode == 5) {
				int len = stream.readUnsignedByte();
				if (len > 0) {
					objectModelTypes = null;
					objectModelIDs = new int[len];
					for (int l1 = 0; l1 < len; l1++)
						objectModelIDs[l1] = stream.readUnsignedWord();
				}
			} else if (opcode == 14)
				sizeX = stream.readUnsignedByte();
			else if (opcode == 15)
				sizeY = stream.readUnsignedByte();
			else if (opcode == 17)
				isUnwalkable = false;
			else if (opcode == 18)
				aBoolean757 = false;
			else if (opcode == 19)
				hasActions = (stream.readUnsignedByte() == 1);
			else if (opcode == 21)
				adjustToTerrain = true;
			else if (opcode == 22)
				nonFlatShading = false;
			else if (opcode == 23)
				aBoolean764 = true;
			else if (opcode == 24) {
				animationID = stream.readUnsignedWord();
				
				animationID += Animation.OSRS_ANIM_OFFSET;
				
				if (animationID == 65535)
					animationID = -1;
			} else if (opcode == 27) {
				
			} else if (opcode == 28)
				anInt775 = stream.readUnsignedByte();
			else if (opcode == 29)
				brightness = stream.readSignedByte();
			else if (opcode == 39)
				contrast = (byte) (stream.readSignedByte() * 25);
			else if (opcode >= 30 && opcode < 35) {
				if (actions == null)
					actions = new String[5];
				actions[opcode - 30] = stream.readString();
				if (actions[opcode - 30].equalsIgnoreCase("hidden"))
					actions[opcode - 30] = null;
			} else if (opcode == 40) {
				int i1 = stream.readUnsignedByte();
				modifiedModelColors = new int[i1];
				originalModelColors = new int[i1];
				for (int i2 = 0; i2 < i1; i2++) {
					modifiedModelColors[i2] = stream.readUnsignedWord();
					originalModelColors[i2] = stream.readUnsignedWord();
				}
			} else if (opcode == 41) {
				int i1 = stream.readUnsignedByte();
                short[] modifiedModelTexture = new short[i1];
                short[] originalModelTexture = new short[i1];
				for (int i2 = 0; i2 < i1; i2++) {
					modifiedModelTexture[i2] = (short) stream.readUnsignedWord();
					originalModelTexture[i2] = (short) stream.readUnsignedWord();
				}
			} else if (opcode == 62)
				aBoolean751 = true;
			else if (opcode == 64)
				aBoolean779 = false;
			else if (opcode == 65)
				modelSizeX = stream.readUnsignedWord();
			else if (opcode == 66)
				modelSizeH = stream.readUnsignedWord();
			else if (opcode == 67)
				modelSizeY = stream.readUnsignedWord();
			else if (opcode == 68)
				mapSceneID = stream.readUnsignedWord();
			else if (opcode == 69)
				plane = stream.readUnsignedByte();
			else if (opcode == 70)
				offsetX = stream.readSignedWord();
			else if (opcode == 71)
				offsetH = stream.readSignedWord();
			else if (opcode == 72)
				offsetY = stream.readSignedWord();
			else if (opcode == 73)
				aBoolean736 = true;
			else if (opcode == 74)
				isSolidObject = true;
			else if (opcode == 75)
				anInt760 = stream.readUnsignedByte();
			else if (opcode == 78) {
				stream.readUnsignedWord(); // ambient sound id
				stream.readUnsignedByte();
			} else if (opcode == 79) {
				stream.readUnsignedWord();
				stream.readUnsignedWord();
				stream.readUnsignedByte();
				int len = stream.readUnsignedByte();

				for (int i = 0; i < len; i++) {
					stream.readUnsignedWord();
				}
			} else if (opcode == 81) {
				int adjustToTerrain = stream.readUnsignedByte() * 256; // adjustToTerrain?
			} else if (opcode == 82) {
				mapFunctionID = stream.readUnsignedWord();

				if (mapFunctionID == 0xFFFF) {
					mapFunctionID = -1;
				}
			} else if (opcode == 77 || opcode == 92) {
				varbitIndex = stream.readUnsignedWord();
				
				if (varbitIndex == 65535)
					varbitIndex = -1;
				
				configID = stream.readUnsignedWord();
				
				if (configID == 65535)
					configID = -1;
				
				int value = -1;

				if (opcode == 92) {
		            value = stream.readUnsignedWord();

		            if (value == 0xFFFF) {
		                value = -1;
		            }
		        }
				
				int len = stream.readUnsignedByte();
				
				configObjectIDs = new int[len + 2];
				
				for (int j2 = 0; j2 <= len; j2++) {
					configObjectIDs[j2] = stream.readUnsignedWord();
					if (configObjectIDs[j2] == 65535)
						configObjectIDs[j2] = -1;
				}
				
				configObjectIDs[len + 1] = value;
			} else if (opcode == 249) {
				
			}
		} while (true);
		if (flag == -1 && name != "null" && name != null) {
			hasActions = objectModelIDs != null && (objectModelTypes == null || objectModelTypes[0] == 10);
			if (actions != null)
				hasActions = true;
		}
		if (isSolidObject) {
			isUnwalkable = false;
			aBoolean757 = false;
		}
		if (anInt760 == -1)
			anInt760 = isUnwalkable ? 1 : 0;
	}

    private ObjectDefinition() {
        type = -1;
    }

    public boolean aBoolean736;
    public byte brightness;
    private int offsetX;
    public String name;
    private int modelSizeY;
    private static final Model[] modelParts = new Model[4];
    public byte contrast;
    public int sizeX;
    private int offsetH;
    public int mapFunctionID;
    private int[] originalModelColors;
    private int modelSizeX;
    public int configID;
    public boolean aBoolean751;
    private static Stream stream;
    private static Stream stream667;
    private static Stream streamOSRS;
    public int type;
    private static int[] streamIndices;
    private static int[] streamIndices667;
    private static int[] streamIndicesOSRS;
    public boolean aBoolean757;
    public int mapSceneID;
    public int configObjectIDs[];
    private int anInt760;
    public int sizeY;
    public boolean adjustToTerrain;
    public boolean aBoolean764;
    public static Client clientInstance;
    private boolean isSolidObject;
    public boolean isUnwalkable;
    public int plane;
    private boolean nonFlatShading;
    private static int cacheIndex;
    private static int osrsCacheIndex;
    private int modelSizeH;
    public int[] objectModelIDs;
    public int varbitIndex;
    public int anInt775;
    public int[] objectModelTypes;
    public byte description[];
    public boolean hasActions;
    public boolean aBoolean779;
    public static MemCache completedModelCache = new MemCache(30);//30
    public static MemCache completedOSRSModelCache = new MemCache(30);//30
    public int animationID;
    private static ObjectDefinition[] cache;
    private static ObjectDefinition[] cacheOSRS;
    private int offsetY;
    private int[] modifiedModelColors;
    public static MemCache modelCache = new MemCache(500);
    public static MemCache osrsModelCache = new MemCache(500);
    public boolean canRandomAnimStart = true;
    public String actions[];
    public DataType dataType = DataType.REGULAR;

}
