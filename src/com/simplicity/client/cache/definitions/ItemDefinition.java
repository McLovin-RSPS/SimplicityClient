package com.simplicity.client.cache.definitions;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.simplicity.Configuration;
import com.simplicity.client.CacheArchive;
import com.simplicity.client.DrawingArea;
import com.simplicity.client.Items;
import com.simplicity.client.MemCache;
import com.simplicity.client.Model;
import com.simplicity.client.Rasterizer;
import com.simplicity.client.Sprite;
import com.simplicity.client.Stream;
import com.simplicity.client.signlink;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.custom.CustomRecolor;
import com.simplicity.client.cache.definitions.custom.CustomRecolor2;
import com.simplicity.client.cache.definitions.custom.ModelOffsets;
import com.simplicity.client.widget.ge.GrandExchange;
import com.simplicity.client.widget.ge.SearchItem;

public final class ItemDefinition {
	
    /**
     * A hash collection of item names accessed by item ids.
     */
    public static final Map<Integer, String> items = new HashMap<>();

    /**
     * A hash collection used for removing duplicates from the
     * items map above.
     */
    private static final Set<String> names = new HashSet<>();
	
    /**
     * A hash collection of item ids which should be forced to load using OSRS data yet retain their ids.
     */
    public static final Set<Integer> FORCE_OSRS_ITEMS = new HashSet<>(Arrays.asList(554, 555, 556, 560, 565, 566));
	
	/**
	 * A hash collection of custom recolored items.
	 */
	public static final Map<Integer, ItemDefinition> CUSTOM_RECOLORS = new HashMap<>();

    public static int[] prices;
    public static List<Integer> untradeableItems = new ArrayList<>();
    /**
     * The models that should be loaded with a older model header.
     */
    public static List<Integer> osrsModels = new ArrayList<>();

    /**
     * The models the need priority fix.
     */
    public static List<Integer> priorityModels = new ArrayList<>();

    /**
     * The models that need the infernal texture.
     */
    public static List<Integer> infernalModels = new ArrayList<>();

    public static final int OSRS_ITEMS_START = 10603;
    public static final int OSRS_ITEMS_OFFSET = 30_000;

    public static void nullLoader() {
        modelCache = null;
        modelCacheOSRS = null;
        modelCacheCustom = null;
        spriteCache = null;
        streamIndices = null;
        streamIndicesOSRS = null;
        cache = null;
        cacheOSRS = null;
        stream = null;
        streamOSRS = null;
    }

    public boolean dialogueModelFetched(int j) {
        int k = maleDialogue;
        int l = maleDialogueModel;
        if (j == 1) {
            k = femaleDialogue;
            l = femaleDialogueModel;
        }
        if (k == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.modelIsFetched(k, dataType)) {
            flag = false;
        }
        if (l != -1 && !Model.modelIsFetched(l, dataType)) {
            flag = false;
        }
        return flag;
    }

    public Model getDialogueModel(int gender) {
        int k = maleDialogue;
        int l = maleDialogueModel;
        if (gender == 1) {
            k = femaleDialogue;
            l = femaleDialogueModel;
        }
        if (k == -1) {
            return null;
        }
        Model model = Model.fetchModel(k, dataType);
        if (l != -1) {
            Model model_1 = Model.fetchModel(l, dataType);
            Model models[] = {model, model_1};
            model = new Model(2, models);
        }
        if (editedModelColor != null) {
            for (int i1 = 0; i1 < editedModelColor.length; i1++) {
                model.recolour(editedModelColor[i1], newModelColor[i1]);
            }
        }
        return model;
    }

    public boolean equipModelFetched(int gender) {
        int fistModel = maleEquip1;
        int secondModel = maleEquip2;
        int thirdModel = maleEquip3;
        if (gender == 1) {
            fistModel = femaleEquip1;
            secondModel = femaleEquip2;
            thirdModel = femaleEquip3;
        }
        if (fistModel == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.modelIsFetched(fistModel, dataType)) {
            flag = false;
        }
        if (secondModel != -1 && !Model.modelIsFetched(secondModel, dataType)) {
            flag = false;
        }
        if (thirdModel != -1 && !Model.modelIsFetched(thirdModel, dataType)) {
            flag = false;
        }
        return flag;
    }

    public Model getEquipModel(int gender) {
        int j = maleEquip1;
        int k = maleEquip2;
        int l = maleEquip3;
        if (gender == 1) {
            j = femaleEquip1;
            k = femaleEquip2;
            l = femaleEquip3;
        }
        if (j == -1) {
            return null;
        }
        Model model = Model.fetchModel(j, dataType);
        if (k != -1) {
            if (l != -1) {
                Model model_1 = Model.fetchModel(k, dataType);
                Model model_3 = Model.fetchModel(l, dataType);
                Model model_1s[] = {model, model_1, model_3};
                model = new Model(3, model_1s);
            } else {
                Model model_2 = Model.fetchModel(k, dataType);
                Model models[] = {model, model_2};
                model = new Model(2, models);
            }
        }
        //if (j == 62367)
        //model.translate(68, 7, -8);
        if (gender == 0 && (maleYOffset != 0 || maleXOffset != 0  || maleZOffset != 0)) {
            model.translate(maleXOffset, maleYOffset, maleZOffset);
        } else if (gender == 1 && (femaleXOffset != 0 || femaleYOffset != 0 || femaleZOffset != 0) ) {
            model.translate(femaleXOffset, femaleYOffset, femaleZOffset);
        }
        
        ModelOffsets.check(gender, this, model);
        
		if (editedModelColor != null && newModelColor != null) {
			for (int i1 = 0; i1 < editedModelColor.length; i1++) {
				model.recolour(editedModelColor[i1], newModelColor[i1]);
			}
		}

        return model;
    }

    public void setDefaults() {
        untradeable = false;
        modelID = 0;
        name = null;
        description = null;
        editedModelColor = null;
        newModelColor = null;
        modelZoom = 2000;
        rotationY = 0;
        rotationX = 0;
        modelOffsetX = 0;
        modelOffset1 = 0;
        modelOffsetY = 0;
        stackable = false;
        value = 0;
        membersObject = false;
        groundActions = null;
        actions = null;
        maleEquip1 = -1;
        maleEquip2 = -1;
        femaleYOffset = 0;
        femaleXOffset = 0;
        femaleZOffset = 0;
        femaleEquip1 = -1;
        femaleEquip2 = -1;
        maleZOffset = 0;
        maleXOffset = 0;
        maleYOffset = 0;
        maleEquip3 = -1;
        femaleEquip3 = -1;
        maleDialogue = -1;
        maleDialogueModel = -1;
        femaleDialogue = -1;
        femaleDialogueModel = -1;
        stackIDs = null;
        stackAmounts = null;
        certID = -1;
        certTemplateID = -1;
        sizeX = 128;
        sizeY = 128;
        sizeZ = 128;
        shadow = 0;
        lightness = 0;
        team = 0;
        lendID = -1;
        lentItemID = -1;
    }

    public static void writeOutOsrsItems(int total667, int totalOSRS) {
        try {
            PrintWriter writer = new PrintWriter("../667Items.txt");
            for (int i = 0; i < total667; i++) {
                ItemDefinition entityDef = forID(i);

                if (entityDef == null || entityDef.name == null)
                    continue;

                writer.print(i + " " + entityDef.name + " " + " [Inv Model=" + entityDef.modelID + "], [Male Model="
                        + (entityDef.maleEquip1) + " " + (entityDef.maleEquip2) + " " + (entityDef.maleEquip3) + "], [Female Model="+
                        (entityDef.femaleEquip1) + " " + (entityDef.femaleEquip2) + " " + (entityDef.femaleEquip3) + "]");

                if(entityDef.editedModelColor != null && entityDef.editedModelColor.length > 0) {
                    writer.print(", [Old Model Colour=");
                    for (int i1 : entityDef.editedModelColor) {
                        writer.print(i1 + " ");
                    }
                    writer.print("]");
                }

                if(entityDef.newModelColor != null && entityDef.newModelColor.length > 0) {
                    writer.print(", [Model Colour=");
                    for (int i1 : entityDef.newModelColor) {
                        writer.print(i1 + " ");
                    }
                    writer.print("]");
                }

                writer.println();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("dumping " + totalOSRS + " dataType items");

        try {
            PrintWriter writer = new PrintWriter("../osrsItems.txt");
            for (int i = OSRS_ITEMS_START + OSRS_ITEMS_OFFSET; i < OSRS_ITEMS_OFFSET + totalOSRS; i++) {
                ItemDefinition entityDef = forID(i);

                if (entityDef == null || entityDef.name == null)
                    continue;

                writer.print(i + " " + entityDef.name + " " + " [Inv Model=" + entityDef.modelID + "], [Male Model="
                        + (entityDef.maleEquip1) + " " + (entityDef.maleEquip2) + " " + (entityDef.maleEquip3) + "], [Female Model="+
                        (entityDef.femaleEquip1) + " " + (entityDef.femaleEquip2) + " " + (entityDef.femaleEquip3) + "]");

                if(entityDef.editedModelColor != null && entityDef.editedModelColor.length > 0) {
                    writer.print(", [Old Model Colour=");
                    for (int i1 : entityDef.editedModelColor) {
                        writer.print(i1 + " ");
                    }
                    writer.print("]");
                }

                if(entityDef.newModelColor != null && entityDef.newModelColor.length > 0) {
                    writer.print(", [Model Colour=");
                    for (int i1 : entityDef.newModelColor) {
                        writer.print(i1 + " ");
                    }
                    writer.print("]");
                }

                writer.println();

            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postOsrsItemDef(ItemDefinition itemDef) {
        switch(itemDef.id) {
            case 50405:
                itemDef.copy(forID(4151));
                break;
            case 50407:
                itemDef.copy(forID(1215));
                break;
            case 50423:
                itemDef.copy(forID(2503));
                break;
            case 50424:
                itemDef.copy(forID(2497));
                break;
            case 50578:
                itemDef.copy(forID(3105));
                break;
            case 50586:
                itemDef.copy(forID(1704));
                break;
            case 53593:
                itemDef.copy(forID(7462));
                break;
            case 53601:
                itemDef.copy(forID(9185));
                break;
            case 53653:
                itemDef.copy(forID(4710));
                break;
            case 53642:
                itemDef.copy(forID(13736));
                break;
            case 50425:
                itemDef.copy(forID(4091));
                break;
            case 50426:
                itemDef.copy(forID(4093));
                break;
            case 50579:
                itemDef.copy(forID(4097));
                break;
        case 52296:
        	itemDef.femaleXOffset = 0;
            itemDef.femaleYOffset = -12;
            itemDef.femaleZOffset = 4;
            itemDef.maleYOffset = 6;
        	break;
        case 43191: // Bond textured
        	itemDef.copy(forID(43190));
        	itemDef.dataType = DataType.CUSTOM;
        	itemDef.modelID = 95;
        	itemDef.editedModelColor = new int[] { 40 };
        	itemDef.newModelColor = new int[] { 61 };
        	itemDef.animateInventory = true;
        	break;
        	
        case 51030: // Dark relic textured
        	itemDef.copy(forID(51027));
            itemDef.name = "@red@Overlord relic";
            itemDef.actions = new String[] { null, null , null, null, null };
        	itemDef.dataType = DataType.CUSTOM;
        	itemDef.modelID = 96;
        	itemDef.editedModelColor = new int[] { 40 };
        	itemDef.newModelColor = new int[] { 61 };
        	itemDef.animateInventory = true;
        	break;
        	
            case 55087:
                itemDef.name = "@red@Mystery Donation Scroll";
                break;
                
	        case 52323:
	        case 5085:
	        case 12150:
	        case 13840:
	        case 13991:
	        case 17835:
	        case 20082:
	        case 20085:
	        case 21027:
	        case 21540:
	        	itemDef.femaleYOffset = -11;
	        	itemDef.femaleXOffset = 6;
	            itemDef.femaleZOffset = 11;
	            itemDef.maleZOffset = -11;
	        	break;
	       
	        	
		    case 54422:
	        case 54423:
	        case 54424:
	        case 54425:
	            itemDef.femaleYOffset = -10;
	            itemDef.femaleXOffset = 5;
	            itemDef.femaleZOffset = 5;
	            itemDef.modelOffsetY = 13;
	            itemDef.rotationY = 404; 
	            itemDef.rotationX = 1050;
	            break;
	        	
	        case 53995:
	        case 53677: // dragon pickaxe or
	            itemDef.maleYOffset = 8;
	            itemDef.femaleYOffset = -10;
	            break;

            case 52981:
                itemDef.femaleYOffset += 5;
                itemDef.maleYOffset += 5;
                break;

            // Crystal halberd full
            case 10033://chin
            case 10034://red chin
            case 43081:
                itemDef.actions = new String[] { null, "Wield", null, null, null };
                break;

            // Rainbow partyhat
            case 41863:
                itemDef.femaleZOffset += 4;
                itemDef.femaleYOffset -= 10;
                itemDef.maleZOffset += 4;
                itemDef.maleYOffset -= 6;
                break;

            // OSRS necklaces
            case 49553:
            case 49547:
            case 52249:
            case 49535:
            case 50366:
            case 49720:
            case 42002:
            case 1704:
                itemDef.femaleZOffset += 4;
                itemDef.femaleYOffset -= 10;
                itemDef.maleYOffset -= 10;
                itemDef.modelOffsetY = 16;
                break;


            // Team cape x and i
            case 50214:
            case 50217:
                itemDef.maleYOffset -= 7;
                itemDef.maleZOffset += 4;
                itemDef.femaleYOffset -= 7;
                itemDef.maleZOffset += 4;
                break;

            case 43300:
                itemDef.name = "Pet Dracula";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelID = MobDefinition.forID(21332).models[0];
                itemDef.modelZoom = 1200;
                break;
                
	        case 54975:
	            itemDef.name = "Headless beast pet";
	            itemDef.groundActions = new String[]{null, null, "Take", null, null};
	            itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
	            break;



            case 43299:
                itemDef.name = "Kura";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelID = MobDefinition.forID(22405).models[0];
                itemDef.modelZoom = 10000;
                break;
                
            case 43305:
                itemDef.name = "Sugi";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelID = MobDefinition.forID(23360).models[0];
                itemDef.modelZoom = 10000;
                break;
                
            case 43306:
                itemDef.name = "Blo";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelID = MobDefinition.forID(23359).models[0];
                itemDef.modelZoom = 10000;
                break;
                
            case 21504:
                itemDef.name = "Nylo";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelID = MobDefinition.forID(23355).models[0];
                itemDef.modelZoom = 10000;
                break;
                
            case 21505:
                itemDef.name = "Tet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelID = MobDefinition.forID(23388).models[0];
                itemDef.modelZoom = 10000;
                break;
                
            case 21506:
                itemDef.name = "Arpus";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelID = MobDefinition.forID(23338).models[0];
                itemDef.modelZoom = 10000;
                break;
                
            case 50911:
                itemDef.name = "Endarkened juice";
                break;

            case 51791:
            case 51793:
            case 51795:
            case 52954:
                itemDef.femaleZOffset += 4;
                itemDef.maleZOffset += 4;
                itemDef.maleYOffset -= 7;
                itemDef.femaleYOffset -= 7;
                break;

            case 51400: // Red Inf cape
                itemDef.copy(forID(51295));
                itemDef.femaleZOffset += 3;
                itemDef.maleZOffset += 3;
                itemDef.maleYOffset -= 3;
                itemDef.femaleYOffset -= 3;
                itemDef.editedModelColor = new int[] { 59, 3005, 5056, 5066, 924 };
                itemDef.newModelColor = new int[] { 61, 946, 946, 946, 946 };
                break;
            case 51401: // Bloody cape
                itemDef.copy(forID(51295));
                itemDef.femaleZOffset += 3;
                itemDef.maleZOffset += 3;
                itemDef.maleYOffset -= 3;
                itemDef.femaleYOffset -= 3;
                itemDef.name = "Bloody cape";
                itemDef.editedModelColor = new int[] { 59, 3005, 5056, 5066, 924 };
                itemDef.newModelColor = new int[] { 62, 921, 921, 921, 0 };
                break;
            case 51402: // Stone Cape
                itemDef.copy(forID(51295));
                itemDef.femaleZOffset += 3;
                itemDef.maleZOffset += 3;
                itemDef.maleYOffset -= 3;
                itemDef.femaleYOffset -= 3;
                itemDef.name = "Stone cape";
                itemDef.editedModelColor = new int[] { 59, 3005, 5056, 5066, 924 };
                itemDef.newModelColor = new int[] { 63, 25, 25, 25, 25 };
                break;
            case 51403: // Ocean Cape
                itemDef.copy(forID(51295));
                itemDef.femaleZOffset += 3;
                itemDef.maleZOffset += 3;
                itemDef.maleYOffset -= 3;
                itemDef.femaleYOffset -= 3;
                itemDef.name = "Ocean cape";
                itemDef.editedModelColor = new int[] { 59, 3005, 5056, 5066, 924 };
                itemDef.newModelColor = new int[] { 64, 32665, 32665, 32665, 32665 };
                break;
            case 51404: // Pink Inf Cape
                itemDef.copy(forID(51295));
                itemDef.femaleZOffset += 3;
                itemDef.maleZOffset += 3;
                itemDef.maleYOffset -= 3;
                itemDef.femaleYOffset -= 3;
                itemDef.editedModelColor = new int[] { 59, 3005, 5056, 5066, 924 };
                itemDef.newModelColor = new int[] { 65, 54194, 54194, 54194, 54194 };
                break;
            case 51405: // Green inf Cape
                itemDef.copy(forID(51295));
                itemDef.femaleZOffset += 3;
                itemDef.maleZOffset += 3;
                itemDef.maleYOffset -= 3;
                itemDef.femaleYOffset -= 3;
                itemDef.editedModelColor = new int[] { 59, 3005, 5056, 5066, 924 };
                itemDef.newModelColor = new int[] { 66, 16306, 16306, 16306, 16306 };
                break;
            case 51406: // Frozen cape
                itemDef.copy(forID(51295));
                itemDef.femaleZOffset += 3;
                itemDef.maleZOffset += 3;
                itemDef.maleYOffset -= 3;
                itemDef.femaleYOffset -= 3;
                itemDef.name = "Frozen cape";
                itemDef.editedModelColor = new int[] { 59, 3005, 5056, 5066, 924 };
                itemDef.newModelColor = new int[] { 67, 43263, 43263, 43263, 37247 };
                break;
            case 51407: // Yellow inf Cape
                itemDef.copy(forID(51295));
                itemDef.femaleZOffset += 3;
                itemDef.maleZOffset += 3;
                itemDef.maleYOffset -= 3;
                itemDef.femaleYOffset -= 3;
                itemDef.editedModelColor = new int[] { 59, 3005, 5056, 5066, 924 };
                itemDef.newModelColor = new int[] { 68, 11212, 11212, 11212, 11212 };
                break;
                
                // 
                
            case 51295:
            case 51285:
            case 51776:
            case 51780:
            case 51784:
            case 51898:
            case 43280:
            case 43282:
            case 43329:
            case 43331:
            case 43333:
            case 43335:
            case 43337:
            case 43342:
            case 50760:
            case 51186:
                itemDef.femaleZOffset += 3;
                itemDef.maleZOffset += 3;
                itemDef.maleYOffset -= 3;
                itemDef.femaleYOffset -= 3;
                break;
            case 51018:
                itemDef.femaleYOffset -= 10;
                break;
                
            case 50851:
                itemDef.name = "Superior Olmlet";
                break;
                
            case 50852:
                ItemDefinition def2 = forID(52319);
                itemDef.name = "Superior " + def2.name;
                itemDef.modelID = def2.modelID;
                itemDef.modelOffset1 = def2.modelOffset1;
                itemDef.modelOffsetX = def2.modelOffsetX;
                itemDef.modelOffsetY = def2.modelOffsetY;
                itemDef.modelZoom = def2.modelZoom;
                break;
                
            case 50854:
                ItemDefinition defVorki = forID(51992);
                itemDef.name = "Superior " + defVorki.name;
                itemDef.modelID = defVorki.modelID;
                itemDef.modelOffset1 = defVorki.modelOffset1;
                itemDef.modelOffsetX = defVorki.modelOffsetX;
                itemDef.modelOffsetY = defVorki.modelOffsetY;
                itemDef.sizeX = defVorki.sizeX;
                itemDef.sizeY = defVorki.sizeY;
                itemDef.sizeZ = defVorki.sizeZ;
                itemDef.rotationX = defVorki.rotationX;
                itemDef.rotationY = defVorki.rotationY;
                itemDef.actions = defVorki.actions;
                itemDef.modelID = defVorki.modelID;
                itemDef.sizeX = defVorki.sizeX;
                itemDef.sizeY = defVorki.sizeY;
                itemDef.sizeZ = defVorki.sizeZ;
                itemDef.maleEquip1 = defVorki.maleEquip1;
                itemDef.femaleEquip1 = defVorki.femaleEquip1;
                itemDef.modelZoom = defVorki.modelZoom;
                itemDef.modelOffsetY = defVorki.modelOffsetY;
                itemDef.editedModelColor = defVorki.editedModelColor;
                itemDef.newModelColor = defVorki.newModelColor;
                break;

            case 50884:
                itemDef.name = "@or2@Great Olm's crystal";
                break;

            case 51427:
                itemDef.name = "@or2@Gold logs";
                break;
            case 51802:
                itemDef.name = "Revenant teleport";
                itemDef.actions[0] = "Break";
                break;
            case 51803:
                ItemDefinition def = forID(51802);
                itemDef.dataType = DataType.OLDSCHOOL;
                itemDef.modelID = def.modelID;
                itemDef.modelOffset1 = def.modelOffset1;
                itemDef.modelOffsetX = def.modelOffsetX;
                itemDef.modelOffsetY = def.modelOffsetY;
                itemDef.modelZoom = def.modelZoom;
                itemDef.rotationX = def.rotationX;
                itemDef.rotationY = def.rotationY;
                itemDef.description = def.description;
                itemDef.actions = def.actions;
                itemDef.name = "Lava dragon teleport";
                break;
            case 42603:
                itemDef.name = "Ring of Bosses";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                itemDef.actions[3] = "Toggle Config";
                break;
            case 52993:
                itemDef.copy(forID(6199));
                itemDef.name = "Raids Mystery Box";
                itemDef.newModelColor[0] = 27570;
                break;
            case 49480:
                itemDef.copy(forID(6199));
                itemDef.name = "Infinity Box";
                itemDef.newModelColor[0] = 35260;
                break;
            case 49485:
                itemDef.copy(forID(6199));
                itemDef.name = "One Year Box";
                itemDef.newModelColor[0] = 25560;
                break;
            case 52191:
                itemDef.name = "Master Stone";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Hold";
                break;
            case 52609:
                itemDef.name = "Olm's Fire";
                break;
            case 49473:
                itemDef.name = "Six Month Anniversary Bag";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 42789:
                itemDef.name = "@gre@Vote Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 51695:
                itemDef.name = "Simpfest shield";
                break;
            case 49643:
                itemDef.copy(forID(8467));
                itemDef.name = "Great Olm's slayer helmet";
                break;
            case 42897:
                itemDef.name = "Christmas Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 52316:
                itemDef.name = "Emerald rapier";
                break;
            case 41860:
                itemDef.name = "Angelic Boots";
                break;
            case 52366:
                itemDef.name = "Cyan crystal";
                break;
            case 52410:
                itemDef.name = "1000m Note";
                itemDef.actions = new String[]{"Claim", null, null, null, "Drop"};
                break;
        }
    }

    public static void unpackConfig(CacheArchive streamLoader) {
        stream = new Stream(streamLoader.getDataForName("obj.dat"));
        Stream streamIdx = new Stream(streamLoader.getDataForName("obj.idx"));
        streamOSRS = new Stream(streamLoader.getDataForName("obj3.dat"));
        Stream streamOSRS = new Stream(streamLoader.getDataForName("obj3.idx"));

        totalItems = streamIdx.readUnsignedWord();
        totalItemsOSRS = streamOSRS.readUnsignedWord();

        streamIndices = new int[totalItems + 3000];
        streamIndicesOSRS = new int[totalItemsOSRS];

        int i = 2;

        for (int j = 0; j < totalItems; j++) {
            streamIndices[j] = i;
            i += streamIdx.readUnsignedWord();
        }

        i = 2;

        for (int j = 0; j < totalItemsOSRS; j++) {
            streamIndicesOSRS[j] = i;
            i += streamOSRS.readUnsignedWord();
        }

        cache = new ItemDefinition[10];
        cacheOSRS = new ItemDefinition[10];

        for (int k = 0; k < 10; k++) {
            cache[k] = new ItemDefinition();
            cacheOSRS[k] = new ItemDefinition();
        }
        
        setSettings();

        for (int i1 = 1; i1 < totalItems + totalItemsOSRS; i1++) {
            try {
                ItemDefinition def = ItemDefinition.forID(i1);

                if (def.lentItemID != -1 || def.certTemplateID != -1) {
                    continue;
                }

                if (def.id == 1 || def.name == null) {
                    continue;
                }

                items.put(def.id, def.name);

                String trimmedName = def.name.toLowerCase().replaceAll(" ", "");

                if (isTradableOnGE(def) && (def.dataType != DataType.OLDSCHOOL || !names.contains(trimmedName))) {
                    GrandExchange.items.put(def.id, new SearchItem(def.id, def.stackable, def.name));
                }

                names.add(trimmedName);
            } catch (Exception e) {
            }
        }

        names.clear();

        osrsModels.add(34148);
        osrsModels.add(34152);

        osrsModels.add(34149);
        osrsModels.add(34151);

        osrsModels.add(34146);
        osrsModels.add(34150);

        osrsModels.add(35349);
        osrsModels.add(35361);

        osrsModels.add(35359);
        osrsModels.add(35368);

        osrsModels.add(35356);
        osrsModels.add(35367);

        osrsModels.add(36338);
        osrsModels.add(36406);
        osrsModels.add(36585);
        osrsModels.add(36588);

        //Dharok platebody
        priorityModels.add(6670);
        priorityModels.add(6639);
        priorityModels.add(6698);
        priorityModels.add(6681);

        priorityModels.add(62736);
        priorityModels.add(62755);

        priorityModels.add(62748);
        priorityModels.add(62764);

        priorityModels.add(62742);
        priorityModels.add(62758);

        priorityModels.add(29249);
        priorityModels.add(29254);

        priorityModels.add(29250);
        priorityModels.add(29255);

        priorityModels.add(29252);
        priorityModels.add(29253);

        //Ancestral hat
        priorityModels.add(32655);
        priorityModels.add(32663);

        //Ancestral robe top
        priorityModels.add(32657);
        priorityModels.add(32658);
        priorityModels.add(32664);
        priorityModels.add(32665);

        //Ancestral robe bottom
        priorityModels.add(32653);
        priorityModels.add(32662);

        //Infernal Cape
        priorityModels.add(33103);
        priorityModels.add(33111);

        //Infernal max cape
        priorityModels.add(33102);
        priorityModels.add(33114);

        //Max Cape
        priorityModels.add(29616);
        priorityModels.add(29624);

        //Max Hood
        priorityModels.add(29614);
        priorityModels.add(29623);

        //Fire Max Cape
        priorityModels.add(32820);
        priorityModels.add(32821);

        //Fire Max Hood
        priorityModels.add(32808);
        priorityModels.add(32810);

        //Infernal Cape
        infernalModels.add(33144);
        infernalModels.add(33103);
        infernalModels.add(33111);

        //Infernal max cape
        infernalModels.add(33145);
        infernalModels.add(33102);
        infernalModels.add(33114);
        
        
        //Imbued god capes
        priorityModels.add(34261);
        priorityModels.add(33145); 
        priorityModels.add(34149);
        priorityModels.add(34151);
        priorityModels.add(34167);
        priorityModels.add(34146);
        priorityModels.add(34150);
        priorityModels.add(34166);
        priorityModels.add(34148);
        priorityModels.add(34150);
        
        /**
         * Master capes
         */
        priorityModels.add(64536);
        priorityModels.add(64538);
        priorityModels.add(64540);
        priorityModels.add(64542);
        priorityModels.add(65496);
        priorityModels.add(65498);
        priorityModels.add(65500);
        priorityModels.add(65502);
        priorityModels.add(65504);
        priorityModels.add(65506);
        priorityModels.add(65508);
        priorityModels.add(65510);
        priorityModels.add(65512);
        priorityModels.add(65514);
        priorityModels.add(65516);
        priorityModels.add(65518);
        priorityModels.add(65520);
        priorityModels.add(65522);
        priorityModels.add(65524);
        priorityModels.add(65526);
        priorityModels.add(65528);
        priorityModels.add(65530);
        priorityModels.add(65532);
        priorityModels.add(65534);
        priorityModels.add(65533);

        // Spiritbloom hood
        priorityModels.add(55748);
        priorityModels.add(56438);

        // Spiritbloom boots
        priorityModels.add(55672);
        priorityModels.add(56366);

        // Celestial hood
        priorityModels.add(55746);
        priorityModels.add(56453);

        // Celestial boots
        priorityModels.add(55681);
        priorityModels.add(56367);

        // Guadian boots
        priorityModels.add(34233);
        priorityModels.add(34234);

        // Angelic boots
        priorityModels.add(28881);
        priorityModels.add(28888);

        //Necklace of anguish (or)
        priorityModels.add(12778);
        priorityModels.add(12817);
               
        //Hween mask skyblue
        priorityModels.add(3188);
        priorityModels.add(3192);
        
        //Tuxedo jackets
        priorityModels.add(31810);
        priorityModels.add(31801);
        priorityModels.add(31873);
        priorityModels.add(31865);
        priorityModels.add(31867);
        
        priorityModels.add(31812);
        priorityModels.add(31799);
        priorityModels.add(31872);
        priorityModels.add(31871);
        
        priorityModels.add(28507);
        priorityModels.add(28507);
        priorityModels.add(31871);
        // Mummy outfit
        priorityModels.add(31808);
        priorityModels.add(31803);
        priorityModels.add(31878);
        priorityModels.add(31871);
        priorityModels.add(31853);
        priorityModels.add(31853);
        priorityModels.add(31784);
        priorityModels.add(31855);
        priorityModels.add(31815);
        priorityModels.add(31758);
        priorityModels.add(31831);
        
        // Wizard outfit (g)
        priorityModels.add(10984);
        priorityModels.add(11348);
        priorityModels.add(11359);
        
        priorityModels.add(10979);
        priorityModels.add(11331);
        priorityModels.add(11354);
        
        //Hood of darkness + set
        priorityModels.add(31752);
        priorityModels.add(31836);
        
        priorityModels.add(31814);
        priorityModels.add(31802);
        priorityModels.add(31836);
        priorityModels.add(31865);
        
        //Bunnyman mask
        priorityModels.add(21860);
        priorityModels.add(37215);
        
        //Slayer helmets
        priorityModels.add(36006);
        priorityModels.add(36007);
        priorityModels.add(20860);
        priorityModels.add(20862);
        priorityModels.add(14395);
        
        //Assembler max cape
        priorityModels.add(34629);
        priorityModels.add(34641);
        
        //Serp helm
        priorityModels.add(14395);
        priorityModels.add(14398);
        
        //Magma helm
        priorityModels.add(14424);
        priorityModels.add(14398);
        
        
        //Infernal max hood
        priorityModels.add(33101);
        priorityModels.add(33110);
        
        //Staff of light
        priorityModels.add(34508);
        priorityModels.add(34507);
        
        //Necklaces
        priorityModels.add(31524);
        priorityModels.add(31227);
        priorityModels.add(31233);
        priorityModels.add(31510);
        priorityModels.add(31228);
        priorityModels.add(31232);
        
        //Dragon boots (g)
        priorityModels.add(34486);
        priorityModels.add(12783);
        priorityModels.add(12818);

        priorityModels.add(36324); //devout boots
        priorityModels.add(36333); //devout boots
        
        priorityModels.add(28285); //elder chaos hood
        priorityModels.add(32073); //elder chaos hood
        
        priorityModels.add(32063); //elder chaos hood
        priorityModels.add(32066); //elder chaos hood
        
        priorityModels.add(32062); //elder chaos hood
        priorityModels.add(32066); //elder chaos hood
        
        // Neitiznot faceguard
        priorityModels.add(38857);
        priorityModels.add(38858);
        
        // Rainbow partyhat
        priorityModels.add(16246);
        priorityModels.add(16248);
        
        // Twisted slayer helmet
        priorityModels.add(38960);
        priorityModels.add(38970);
        
        // Graceful set
        priorityModels.add(31005);
        priorityModels.add(31012);
        
        priorityModels.add(31023);
        priorityModels.add(31009);
        priorityModels.add(31008);
        
        priorityModels.add(31021);
        priorityModels.add(31015);
        priorityModels.add(31012);
        
        priorityModels.add(31007);
        priorityModels.add(31014);
        
        priorityModels.add(31022);
        priorityModels.add(31006);
        
        priorityModels.add(31024);
        priorityModels.add(31010);
        
        // Team cape x
        priorityModels.add(31773);
        
        // Team cape i
        priorityModels.add(31770);
        
        // Justiciar faceguard
        priorityModels.add(35349);
        priorityModels.add(35361);
        
        // Dragonstone full helm
        priorityModels.add(38020);
        priorityModels.add(38156);
        
        //writeOutOsrsItems(totalItems, totalItemsOSRS);
        //dumpInterface(totalItems, totalItemsOSRS);
        
        CustomRecolor.values();
        CustomRecolor2.values();
        ModelOffsets.init();
        GrandExchange.init(totalItems + totalItemsOSRS);
    }

    private static boolean isTradableOnGE(ItemDefinition def) {
        String name = def.name;

        if (name.isEmpty() || name.equals("null") || untradeableItems.contains(def.id)) {
            return false;
        }

        if (def.id == 4178 || def.id == 14661) {
            return false;
        }

        if (def.id != 590 && name.contains("inderbox")) {
            return false;
        }

        if (name.startsWith("Guam in a") || name.startsWith("Casket (")) {
            return false;
        }

        if (name.equals("Bauble box") || name.equals("Box bauble") || name.equals("Clue Scroll") || name.equals("Challenge scroll") || name.equals("Champion's scroll") || name.equals("Puzzle box")) {
            return false;
        }

        if (name.contains("scroll (") || name.contains("box (")) {
            return false;
        }

        if (name.endsWith("(b)") || name.endsWith("(nz)") || name.endsWith("(o)")) {
            return false;
        }

        return true;
    }
    
    /*public static void dumpInterface(int totalItems, int totalItemsOSRS) {

        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(
                    "../Items.java"), 1024);

            Multiset<String> addedNames = ConcurrentHashMultiset.create();

            String[] unwanted_chars = { "'", "(", ")", ".", "%", "?", "@red@", ",", "�" };

            String[] underline_chars = { " ", "-", "/" };

            String[][] replace_chars = {
                    { "1/2", "half"},
                    { "1/3", "one_third"}, { "2/3", "two_thirds"},
                    { "1/5", "one_fifth"}, { "2/5", "two_fifths"},  { "3/5", "three_fifths"},  { "4/5", "four_fifths"},
                    { "3rd", "third"}, { "4th", "fourth"},
                    { "100m", "one_hundred_million"},{ "1000m", "one_billion"},
                    {"+", "plus"}, {"++", "plus_plus"}, {"+++", "plus_plus_plus"}, {"&", "and"}};

            for (int i = 0; i < totalItems + totalItemsOSRS; i++) {

                int add = 0;

                if(i >= totalItems) {
                    add += OSRS_ITEMS_OFFSET;
                }

                ItemDefinition definition = forID(i + add);

                if(definition == null || definition.name == null || definition.name.equalsIgnoreCase("null")) {
                    continue;
                }

                String name = definition.name;

                for (int i1 = 0; i1 < replace_chars.length; i1++) {
                    name = name.replace(replace_chars[i1][0], replace_chars[i1][1]);
                }

                for (String unwanted_char : unwanted_chars) {
                    name = name.replace(unwanted_char, "");
                }

                for (String underline_char : underline_chars) {
                    name = name.replace(underline_char, "_");
                }

                if (definition.certTemplateID != -1) {
                    name = name + "_noted";
                }

                if(name.isEmpty() || name.equalsIgnoreCase(" ") || name.equalsIgnoreCase("_")) {
                    continue;
                }

                name = name.toUpperCase();

                int count = addedNames.count(name);

                if(count < 0) {
                    count = addedNames.count(name + "_1");
                }

                if(count > 0) {
                    file.write("int " + name + "_" + count + " = " + definition.id + ";");
                } else {
                    file.write("int " + name + " = " + definition.id + ";");
                }

                addedNames.add(name);

                file.newLine();
            }

            file.close();

        } catch(Throwable t) {
            t.printStackTrace();
        }
    }*/

    public static ItemDefinition forID(int id) {
        return Items.forID(id);
    }

    public void imitate(ItemDefinition other) {
        name = other.name;
        description = other.description;
        editedModelColor = other.editedModelColor;
        newModelColor = other.newModelColor;
        sizeX = other.sizeX;
        sizeY = other.sizeY;
        sizeZ = other.sizeZ;
        rotationY = other.rotationY;
        rotationX = other.rotationX;
        modelOffset1 = other.modelOffset1;
        modelOffsetY = other.modelOffsetY;
        modelOffsetX = other.modelOffsetX;
        modelZoom = other.modelZoom;
        modelID = other.modelID;
        actions = other.actions;
        maleEquip1 = other.maleEquip1;
        maleEquip2 = other.maleEquip2;
        maleEquip3 = other.maleEquip3;
        femaleEquip1 = other.femaleEquip1;
        femaleEquip2 = other.femaleEquip2;
        femaleEquip3 = other.femaleEquip3;
        maleDialogue = other.maleDialogue;
        maleDialogueModel = other.maleDialogueModel;
        femaleDialogue = other.femaleDialogue;
        femaleDialogue = other.femaleDialogueModel;
    }

    public void readValues(Stream stream) {
        boolean osrs = stream == streamOSRS;

        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                modelID = stream.readUnsignedWord();
            } else if (i == 2) {
                name = stream.readString();
            } else if (i == 3) {
                description = stream.readString();
            } else if (i == 4) {
                modelZoom = stream.readUnsignedWord();
            } else if (i == 5) {
                rotationY = stream.readUnsignedWord();
            } else if (i == 6) {
                rotationX = stream.readUnsignedWord();
            } else if (i == 7) {
                modelOffset1 = stream.readUnsignedWord();
                if (modelOffset1 > 32767) {
                    modelOffset1 -= 0x10000;
                }
            } else if (i == 8) {
                modelOffsetY = stream.readUnsignedWord();
                if (modelOffsetY > 32767) {
                    modelOffsetY -= 0x10000;
                }
            } else if (i == 10 && osrs) {
                stream.readUnsignedWord();
            } else if (i == 11) {
                stackable = true;
            } else if (i == 12) {
                value = osrs ? stream.readInt() : stream.readUnsignedWord();
            } else if (i == 16) {
                membersObject = true;
            } else if (i == 23) {
                maleEquip1 = stream.readUnsignedWord();
                femaleYOffset = stream.readSignedByte();
            } else if (i == 24) {
                maleEquip2 = stream.readUnsignedWord();
            } else if (i == 25) {
                femaleEquip1 = stream.readUnsignedWord();
                maleYOffset = stream.readSignedByte();
            } else if (i == 26) {
                femaleEquip2 = stream.readUnsignedWord();
            } else if (i >= 30 && i < 35) {
                if (groundActions == null) {
                    groundActions = new String[5];
                }
                groundActions[i - 30] = stream.readString();
                if (groundActions[i - 30].equalsIgnoreCase("hidden")) {
                    groundActions[i - 30] = null;
                }
            } else if (i >= 35 && i < 40) {
                if (actions == null) {
                    actions = new String[5];
                }
                actions[i - 35] = stream.readString();
                if (actions[i - 35].equalsIgnoreCase("null")) {
                    actions[i - 35] = null;
                }
            } else if (i == 40) {
                int j = stream.readUnsignedByte();
                editedModelColor = new int[j];
                newModelColor = new int[j];
                for (int k = 0; k < j; k++) {
                    if(osrs) {
                        newModelColor[k] = stream.readUnsignedWord();
                        editedModelColor[k] = stream.readUnsignedWord();
                    } else {
                        editedModelColor[k] = stream.readUnsignedWord();
                        newModelColor[k] = stream.readUnsignedWord();
                    }
                }
            } else if (i == 78) {
                maleEquip3 = stream.readUnsignedWord();
            } else if (i == 79) {
                femaleEquip3 = stream.readUnsignedWord();
            } else if (i == 90) {
                maleDialogue = stream.readUnsignedWord();
            } else if (i == 91) {
                femaleDialogue = stream.readUnsignedWord();
            } else if (i == 92) {
                maleDialogueModel = stream.readUnsignedWord();
            } else if (i == 93) {
                femaleDialogueModel = stream.readUnsignedWord();
            } else if (i == 95) {
                modelOffsetX = stream.readUnsignedWord();
            } else if (i == 97) {
                certID = osrs ? OSRS_ITEMS_OFFSET + stream.readUnsignedWord() : stream.readUnsignedWord();
            } else if (i == 98) {
                certTemplateID = stream.readUnsignedWord();
            } else if (i >= 100 && i < 110) {
                if (stackIDs == null) {
                    stackIDs = new int[10];
                    stackAmounts = new int[10];
                }
                stackIDs[i - 100] = stream.readUnsignedWord() + (osrs ? OSRS_ITEMS_OFFSET : 0);
                stackAmounts[i - 100] = stream.readUnsignedWord();
            } else if (i == 110) {
                sizeX = stream.readUnsignedWord();
            } else if (i == 111) {
                sizeY = stream.readUnsignedWord();
            } else if (i == 112) {
                sizeZ = stream.readUnsignedWord();
            } else if (i == 113) {
                shadow = stream.readSignedByte();
            } else if (i == 114) {
                lightness = stream.readSignedByte() * 5;
            } else if (i == 115) {
                team = stream.readUnsignedByte();
            } else if (i == 116) {
                lendID = stream.readUnsignedWord();
            } else if (i == 117) {
                lentItemID = stream.readUnsignedWord();
            } else if (i == 139) {
            	stream.readUnsignedWord();
            } else if (i == 140) {
            	stream.readUnsignedWord();
            } else if (i == 148) {
            	stream.readUnsignedWord();
            } else if (i == 149) {
            	stream.readUnsignedWord();
            }
        } while (true);
    }

    public static void setSettings() {
        try {
            prices = new int[22694];
            int index = 0;
            BufferedReader in = new BufferedReader(new FileReader(new File(signlink.findcachedir() + "data/data.txt")));
            String line;
            while((line = in.readLine()) != null) {
                prices[index] = Integer.parseInt(line);
                index++;
            }
            for (int i : UNTRADEABLE_ITEMS) {
                untradeableItems.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toNote() {
        ItemDefinition itemDef = forID(certTemplateID);
        modelID = itemDef.modelID;
        modelZoom = itemDef.modelZoom;
        rotationY = itemDef.rotationY;
        rotationX = itemDef.rotationX;
        modelOffsetX = itemDef.modelOffsetX;
        modelOffset1 = itemDef.modelOffset1;
        modelOffsetY = itemDef.modelOffsetY;
        editedModelColor = itemDef.editedModelColor;
        newModelColor = itemDef.newModelColor;
        ItemDefinition itemDef_1 = forID(certID);
        name = itemDef_1.name;
        membersObject = itemDef_1.membersObject;
        value = itemDef_1.value;
        String s = "a";
        char c = (itemDef_1.name == null || itemDef_1.name.isEmpty()) ? 'S' : itemDef_1.name.charAt(0);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            s = "an";
        }
        description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
        stackable = true;
    }

    public void toCustomNote(int copyId) {
        certTemplateID = 799;
        certID = copyId;
        ItemDefinition itemDef = forID(certTemplateID);
        modelID = itemDef.modelID;
        modelZoom = itemDef.modelZoom;
        rotationY = itemDef.rotationY;
        rotationX = itemDef.rotationX;
        modelOffsetX = itemDef.modelOffsetX;
        modelOffset1 = itemDef.modelOffset1;
        modelOffsetY = itemDef.modelOffsetY;
        editedModelColor = itemDef.editedModelColor;
        newModelColor = itemDef.newModelColor;
        ItemDefinition itemDef_1 = forID(copyId);
        name = itemDef_1.name;
        membersObject = itemDef_1.membersObject;
        value = itemDef_1.value;
        String s = "a";
        char c = itemDef_1.name == null ? 'S' : itemDef_1.name.charAt(0);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            s = "an";
        }
        actions = itemDef.actions;
        description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
        stackable = true;
    }

    public void toLend() {
        ItemDefinition itemDef = forID(lentItemID);
        actions = new String[5];
        modelID = itemDef.modelID;
        modelOffset1 = itemDef.modelOffset1;
        rotationX = itemDef.rotationX;
        modelOffsetY = itemDef.modelOffsetY;
        modelZoom = itemDef.modelZoom;
        rotationY = itemDef.rotationY;
        modelOffsetX = itemDef.modelOffsetX;
        value = 0;
        ItemDefinition itemDef_1 = forID(lendID);
        maleDialogueModel = itemDef_1.maleDialogueModel;
        editedModelColor = itemDef_1.editedModelColor;
        maleEquip3 = itemDef_1.maleEquip3;
        maleEquip2 = itemDef_1.maleEquip2;
        femaleDialogueModel = itemDef_1.femaleDialogueModel;
        maleDialogue = itemDef_1.maleDialogue;
        groundActions = itemDef_1.groundActions;
        maleEquip1 = itemDef_1.maleEquip1;
        name = itemDef_1.name;
        femaleEquip1 = itemDef_1.femaleEquip1;
        membersObject = itemDef_1.membersObject;
        femaleDialogue = itemDef_1.femaleDialogue;
        femaleEquip2 = itemDef_1.femaleEquip2;
        femaleEquip3 = itemDef_1.femaleEquip3;
        newModelColor = itemDef_1.newModelColor;
        team = itemDef_1.team;
        if (itemDef_1.actions != null) {
            for (int i_33_ = 0; i_33_ < 4; i_33_++) {
                actions[i_33_] = itemDef_1.actions[i_33_];
            }
        }
        actions[4] = "Discard";
    }

    public static Sprite getSprite(int i, int j, int k, int zoom) {
        if (k == 0 && zoom != -1) {
            Sprite sprite = (Sprite) spriteCache.get(i);
            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDefinition itemDef = forID(i);
        if (itemDef.stackIDs == null) {
            j = -1;
        }
        if (j > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
                    i1 = itemDef.stackIDs[j1];
                }
            }

            if (i1 != -1) {
                itemDef = forID(i1);
            }
        }
        Model model = itemDef.getItemModelFinalised(1);
        if (model == null) {
            return null;
        }
        Sprite sprite = null;
        if (itemDef.certTemplateID != -1) {
            sprite = getSprite(itemDef.certID, 10, -1);
            if (sprite == null) {
                return null;
            }
        }
        if (itemDef.lendID != -1) {
            sprite = getSprite(itemDef.lendID, 50, 0);
            if (sprite == null) {
                return null;
            }
        }
        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        float depthBuffer[] = DrawingArea.depthBuffer;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels, new float[32 * 32]);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.setDefaultBounds();
        int k3 = itemDef.modelZoom;
        if (zoom != -1 && zoom != 0) {
            k3 = (itemDef.modelZoom * 100) / zoom;
        }
        if (k == -1) {
            k3 = (int) ((double) k3 * 1.5D);
        }
        if (k > 0) {
            k3 = (int) ((double) k3 * 1.04D);
        }
        int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
        model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);
        for (int i5 = 31; i5 >= 0; i5--) {
            for (int j4 = 31; j4 >= 0; j4--) {
                if (sprite2.myPixels[i5 + j4 * 32] != 0) {
                    continue;
                }
                if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                    continue;
                }
                if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                    continue;
                }
                if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                    continue;
                }
                if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                }
            }

        }

        if (k > 0) {
            for (int j5 = 31; j5 >= 0; j5--) {
                for (int k4 = 31; k4 >= 0; k4--) {
                    if (sprite2.myPixels[j5 + k4 * 32] != 0) {
                        continue;
                    }
                    if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                        continue;
                    }
                    if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                        continue;
                    }
                    if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                        continue;
                    }
                    if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                    }
                }

            }

        } else if (k == 0) {
            for (int k5 = 31; k5 >= 0; k5--) {
                for (int l4 = 31; l4 >= 0; l4--) {
                    if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0) {
                        sprite2.myPixels[k5 + l4 * 32] = 0x302020;
                    }
                }

            }

        }
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (itemDef.lendID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (k == 0 && !itemDef.animateInventory && i != 5572 && i != 5573 && i != 640 && i != 650 && i != 630) {
            spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1, depthBuffer);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        sprite2.maxWidth = itemDef.stackable ? 33 : 32;
        sprite2.maxHeight = j;
        return sprite2;
    }

    public static Sprite getSprite(int i, int j, int k) {
        if (k == 0) {
            Sprite sprite = (Sprite) spriteCache.get(i);
            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDefinition itemDef = forID(i);
        if (itemDef.stackIDs == null) {
            j = -1;
        }
        if (j > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
                    i1 = itemDef.stackIDs[j1];
                }
            }
            if (i1 != -1) {
                itemDef = forID(i1);
            }
        }
        Model model = null;
        try {
            model = itemDef.getItemModelFinalised(1);
        } catch(Exception e) {
            System.out.println("Error with item : "+itemDef.name+", "+itemDef.dataType+", i="+i);
            e.printStackTrace();
        }
        if (model == null) {
            return null;
        }
        Sprite sprite = null;
        if (itemDef.certTemplateID != -1) {
//        	System.out.println(i + " Fetchng sprte for " + itemDef.certID + " for " + itemDef.certTemplateID);
        	if (itemDef.certID != i) {
        		sprite = getSprite(itemDef.certID, 10, -1);
        	}
            if (sprite == null) {
                return null;
            }
        }
        if (itemDef.lentItemID != -1) {
            sprite = getSprite(itemDef.lendID, 50, 0);
            if (sprite == null) {
                return null;
            }
        }
        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        float depthBuffer[] = DrawingArea.depthBuffer;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels, new float[32 * 32]);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.setDefaultBounds();
        int k3 = itemDef.modelZoom;

        if (k == -1) {
            k3 = (int) ((double) k3 * 1.5D);
        }
        if (k > 0) {
            k3 = (int) ((double) k3 * 1.04D);
        }
        
        if (itemDef.rotationY > Rasterizer.anIntArray1470.length || itemDef.rotationY > Rasterizer.anIntArray1471.length) {
        	return null;
        }

        int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
        model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);
        for (int i5 = 31; i5 >= 0; i5--) {
            for (int j4 = 31; j4 >= 0; j4--) {
                if (sprite2.myPixels[i5 + j4 * 32] == 0) {
                    if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    } else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    } else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    } else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    }
                }
            }
        }
        if (k > 0) {
            for (int j5 = 31; j5 >= 0; j5--) {
                for (int k4 = 31; k4 >= 0; k4--) {
                    if (sprite2.myPixels[j5 + k4 * 32] == 0) {
                        if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        } else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        } else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        } else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        }
                    }
                }
            }
        } else if (k == 0) {
            for (int k5 = 31; k5 >= 0; k5--) {
                for (int l4 = 31; l4 >= 0; l4--) {
                    if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0) {
                        sprite2.myPixels[k5 + l4 * 32] = 0x302020;
                    }
                }
            }
        }
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (itemDef.lentItemID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (k == 0 && !itemDef.animateInventory && i != 5572 && i != 5573 && i != 640 && i != 650 && i != 630) {
            spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1, depthBuffer);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        if (itemDef.stackable) {
            sprite2.maxWidth = 33;
        } else {
            sprite2.maxWidth = 32;
        }
        sprite2.maxHeight = j;
        return sprite2;
    }

    public Model getItemModelFinalised(int amount) {
        if (stackIDs != null && amount > 1) {
            int stackId = -1;
            for (int k = 0; k < 10; k++) {
                if (amount >= stackAmounts[k] && stackAmounts[k] != 0) {
                    stackId = stackIDs[k];
                }
            }
            if (stackId != -1) {
                return forID(stackId).getItemModelFinalised(1);
            }
        }
        
        int modelId = modelID;
        DataType type = dataType;
        
        /**
         * Override amulet of glory inv model.
         */
        if (id == 1704 || id == 1706 || id == 1708 || id == 1710 || id == 1712) {
        	modelId = 2796;
        	type = DataType.OLDSCHOOL;
        }
        
        int itemForModel = id;
        
        Model model = type == DataType.CUSTOM ? (Model) modelCacheCustom.get(itemForModel) : type == DataType.OLDSCHOOL ? (Model) modelCacheOSRS.get(itemForModel) : (Model) modelCache.get(itemForModel);
        if (model != null) {
            return model;
        }

        model = Model.fetchModel(modelId, type);
        if (model == null) {
            return null;
        }
        if (sizeX != 128 || sizeY != 128 || sizeZ != 128) {
            model.scaleT(sizeX, sizeZ, sizeY);
        }
        if (Configuration.debuggingModels) {
        	Set<Integer> colors = new HashSet<>();
        	
            for (int i : model.face_color) {
                colors.add(i);
            }
            
            System.out.print(id + " Model colours: ");
            // colors.forEach(c -> System.out.print((c + ":#" +Integer.toHexString(RS2HSB_to_RGB(c)).substring(2) + ", ")));
        colors.forEach(c -> System.out.print((c + ", ")));
        System.out.println();
        //System.out.print(id + " Modified colours: ");
        //colors.forEach(c -> System.out.print((RGB_to_RS2HSB(RS2HSB_to_RGB_MODIFIED(c, 0, 0, 200).getRed(), RS2HSB_to_RGB_MODIFIED(c, 0, 0, 200).getGreen(), RS2HSB_to_RGB_MODIFIED(c, 0, 0, 200).getBlue()) + ", ")));
            System.out.println();
        }
        if (editedModelColor != null) {
            for (int l = 0; l < editedModelColor.length; l++) {
                model.recolour(editedModelColor[l], newModelColor[l]);
            }
        }
        model.light(64 + shadow, 768 + lightness, -50, -10, -50, true);
        model.rendersWithinOneTile = true;
        if (type == DataType.CUSTOM) {
        	modelCacheCustom.put(model, id);
        } else if (type == DataType.OLDSCHOOL) {
        	modelCacheOSRS.put(model, id);
        } else {
        	if (id != 5572 && id != 5573 && id != 640 && id != 650 && id != 630) {
                modelCache.put(model, id);
            }
        }
        return model;
    }

    public Model getItemModel(int i) {
        if (stackIDs != null && i > 1) {
            int j = -1;
            for (int k = 0; k < 10; k++) {
                if (i >= stackAmounts[k] && stackAmounts[k] != 0) {
                    j = stackIDs[k];
                }
            }
            if (j != -1) {
                return forID(j).getItemModel(1);
            }
        }
        Model model = Model.fetchModel(modelID, dataType);
        if (model == null) {
            return null;
        }
        if (editedModelColor != null) {
            for (int l = 0; l < editedModelColor.length; l++) {
                model.recolour(editedModelColor[l], newModelColor[l]);
            }
        }
        return model;
    }
    
    /* COLOR MODIFIER TOOLS */
    public static int RS2HSB_to_RGB(int RS2HSB) {
		int decode_hue = (RS2HSB >> 10) & 0x3f;
		int decode_saturation = (RS2HSB >> 7) & 0x07;
		int decode_brightness = (RS2HSB & 0x7f);
		// System.out.println((float)decode_brightness/127*(50.0f/100.0f) + "");
		return Color.HSBtoRGB((float)decode_hue/63, (float)decode_saturation/7, ((float)decode_brightness/127));
	}
    
    public static Color RS2HSB_to_RGB_MODIFIED(int RS2HSB, int cHue, int cSaturation, int cBrightness) {
		int decode_hue = (RS2HSB >> 10) & 0x3f;
		int decode_saturation = (RS2HSB >> 7) & 0x07;
		int decode_brightness = (RS2HSB & 0x7f);
		// System.out.println((float)decode_brightness/127*(50.0f/100.0f) + "");
		return Color.getHSBColor((float)decode_hue/63*((float)cHue/100.0f), (float)decode_saturation/7*((float)cSaturation/100.0f), ((float)decode_brightness/127)*((float)cBrightness/100.0f));
	}
    
    public static int RGB_to_RS2HSB(int red, int green, int blue)
    {
      float[] HSB = Color.RGBtoHSB(red, green, blue, null);
      float hue = HSB[0];
      float saturation = HSB[1];
      float brightness = HSB[2];
      int encode_hue = (int)(hue * 63.0F);
      int encode_saturation = (int)(saturation * 7.0F);
      int encode_brightness = (int)(brightness * 127.0F);
      int RS2 = (encode_hue << 10) + (encode_saturation << 7) + encode_brightness;
      return RS2;
    }
	/**
	 * Gets the custom recolors.
	 * 
	 * @return The custom recolors.
	 */
	public static Map<Integer, ItemDefinition> getCustomRecolors() {
		return CUSTOM_RECOLORS;
    }

    public static final int[] UNTRADEABLE_ITEMS
            = {                    54423, 54424, 54425, 22100,
            43300, 10933, 10939, 10940, 10941, 10945, 14936, 14937, 14938, 14939, 13612, 13613, 13614, 13615, 13616, 13617, 13618, 13619, 13620, 54975, 22076,
            13621, 13622, 13623, 13624, 13625, 13626, 13627, 13628, 11296, 11297, 11298, 11299, 3070, 3071, 3072, 3073, 3074, 3075, 3076, 3077, 3078, 21560, 22101,
            43241, 52316, 21004, 13661, 13262, 20072, 16956, 16426, 16910, 605, 13320, 17273, 17274, 13321, 13322, 13323, 13324, 13325, 13326, 13327, 21540,
            11995, 11996, 11997, 11978, 12001, 12002, 12003, 12004, 21024, 21025, 21026, 21087, 51748, 14935, 51791, 51793, 51795, 20085, 11951, 42653, 22046,
            12005, 12006, 11990, 11991, 11992, 11993, 11994, 11989, 11988, 11987, 11986, 11985, 11984, 11983, 14924, 14482, 22013, 54491, 53446, 53448, 51216, 51217, 51218, 51219, 51220, 51221, 51222,
            11982, 11981, 11980, 11979, 11967, 11969, 11970, 11971, 11972, 11973, 11974, 11975, 11976, 1590, 993, 6529, 6950, 1464, 2996,
            6570, 51214, 12158, 12159, 12160, 7986, 7981, 7980, 12163, 13247, 12655, 12646, 12161, 12162, 52473, 14924, 16580, 51027, 1961,
            19143, 19149, 19146, 6500, 19157, 19162, 19152, 4155, 1543, 51992, 50851, 52319, 50851, 50852, 50854, 56018, 16430, 16429, 16428,
            8850, 10551, 8839, 16910, 19669, 8840, 16909, 8842, 11663, 11664, 19712, 52191, 52378, 19864, 11949, 2022, 3706, 21223,
            11665, 3842, 3844, 3840, 8844, 8845, 8846, 8847, 51507, 52441, 52746, 52747, 52748, 52749, 52750, 52751, 52752,
            8848, 8849, 8850, 10551, 7462, 7461, 7460, 51637, 52284, 10637, 52607, 50445, 14019, 50447, 43329, 52606, 21222,
            7459, 7458, 7457, 7456, 7455, 7454, 7453, 11665, 10499, 9748, 52325, 14548, 14549, 14550, 14551, 14552,
            9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808, 14433, 14434, 14435, 14436, 14437, 14438, 43299,
            9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772, 43081, 6927, 6928, 6929, 6930, 6931, 53448,
            9778, 9787, 9811, 9766, 9749, 9755, 9752, 9770, 14037, 14036, 14035, 14033, 14034, 43305, 43306,
            9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806, 14023, 14024, 14025, 12635, 12636, 12637, 12638, 12639, 12640, 12641,
            9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812, 52_109, 19538, 11951, 20208,
            9767, 9747, 9753, 9750, 9768, 9756, 9759, 9762, 810, 51285, 9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792,
            9774, 9771, 9777, 9786, 9810, 9765, 9948, 9949, 9950, 12169, 12170, 12171, 20671, 14641, 14642,
            6188, 10954, 10956, 10958, 52316, 3057, 3058, 3059, 3060, 3061,
            7594, 7592, 7593, 7595, 7596, 14076, 14077, 14081, 11951, 53597,
            10840, 10836, 6858, 6859, 10837, 16045, 16184, 10838, 10839,
            9925, 9924, 9923, 9922, 9921, 20046, 20044, 20045, 49722, 53230,
            14595, 14603, 14602, 14605, 11789, 19708, 19706, 19707,
            4860, 4866, 4872, 4878, 4884, 4896, 4890, 4896, 4902, 20208,
            4932, 4938, 4944, 4950, 4908, 4914, 4920, 4926, 4956, 8869,
            4926, 4968, 4994, 4980, 4986, 4992, 4998,
            18778, 18779, 18780, 18781, 53448, 20202, 20203, 20204, 20205, 20206, 20207,
            13450, 13444, 13405, 15502, 54034, 54037, 54040, 54043, 54046,
            10548, 10549, 10550, 10551, 10555, 10552, 10553, 2412, 2413, 2414,
            20747, 42921, 42939, 42940, 17860, 3459, 5519,
            18365, 18373, 18371, 15246, 12964, 12971, 12978, 14017, 21004,
            757, 8851,
            13855, 13848, 13849, 13857, 13856, 13854, 13853, 13852, 13851, 13850, 5509, 13653, 14021, 14020, 19111, 14019, 14022,
            19785, 19786, 18782, 18351, 16955, 18349, 18361, 18363, 18353, 18357, 18355, 18359, 18335, 16425,
            7453, 7454, 7455, 7456, 7457, 7458, 7459, 7460, 7461, 7462, 19709, 51295, 21012, 7928, 7929, 7930, 7931, 7932, 7933,
            41941, 52586,
            42810, 42811, 42812, 42813, 42814, 42815, 50792, 50794, 50796, 56005, 56006, 56007,
            42651, 42816, 42648, 42703
    };

    public void copy(ItemDefinition copy) {
        maleZOffset = copy.maleZOffset;
        maleXOffset = copy.maleXOffset;
        maleYOffset = copy.maleYOffset;

        femaleYOffset = copy.femaleYOffset;
        femaleXOffset = copy.femaleXOffset;
        femaleZOffset = copy.femaleZOffset;

        modelOffsetX = copy.modelOffsetX;
        modelOffsetY = copy.modelOffsetY;
        modelOffset1 = copy.modelOffset1;

        value = copy.value;

        editedModelColor = copy.editedModelColor == null ? null : new int[copy.editedModelColor.length];

        if(editedModelColor != null) {
            for (int i = 0; i < editedModelColor.length; i++) {
                editedModelColor[i] = copy.editedModelColor[i];
            }
        }

        newModelColor = copy.newModelColor == null ? null : new int[copy.newModelColor.length];

        if(newModelColor != null) {
            for (int i = 0; i < newModelColor.length; i++) {
                newModelColor[i] = copy.newModelColor[i];
            }
        }

        femaleEquip1 = copy.femaleEquip1;
        femaleEquip3 = copy.femaleEquip3;
        femaleEquip2 = copy.femaleEquip2;

        maleEquip1 = copy.maleEquip1;
        maleEquip3 = copy.maleEquip3;
        maleEquip2 = copy.maleEquip2;

        rotationX = copy.rotationX;
        rotationY = copy.rotationY;

        maleDialogueModel = copy.maleDialogueModel;
        femaleDialogueModel = copy.femaleDialogueModel;

        sizeX = copy.sizeX;
        sizeZ = copy.sizeZ;
        sizeY = copy.sizeY;

        groundActions = copy.groundActions;
        actions = copy.actions;
        name = copy.name;
        description = copy.description;

        modelID = copy.modelID;
        certID = copy.certID;
        modelZoom = copy.modelZoom;
        lightness = copy.lightness;
        shadow = copy.shadow;
        femaleDialogue = copy.femaleDialogue;
        maleDialogue = copy.maleDialogue;
        stackIDs = copy.stackIDs;
        stackAmounts = copy.stackAmounts;
        team = copy.team;

        certTemplateID = copy.certTemplateID;
        lendID = copy.lendID;
        lentItemID = copy.lentItemID;

        untradeable = copy.untradeable;
        dataType = copy.dataType;
        membersObject = copy.membersObject;
        stackable = copy.stackable;
    }

    public ItemDefinition() {
        id = -1;
    }

    public boolean animateInventory;
    public byte femaleZOffset;
    public byte femaleXOffset;
    public byte femaleYOffset;
    public int femaleXScale;
    public int femaleYScale;
    public int femaleZScale;
    public int value;
    public int[] editedModelColor;
    public int id;
    public static MemCache spriteCache = new MemCache(100);
    public static MemCache modelCache = new MemCache(50);
    public static MemCache modelCacheOSRS = new MemCache(50);
    public static MemCache modelCacheCustom = new MemCache(50);
    public int[] newModelColor;
    public boolean membersObject;
    public int femaleEquip3;
    public int certTemplateID;
    public int femaleEquip2;
    public int maleEquip1;
    public int maleDialogueModel;
    public int sizeX;
    public String groundActions[];
    public int modelOffset1;
    public String name;
    public static ItemDefinition[] cache;
    public static ItemDefinition[] cacheOSRS;
    public int femaleDialogueModel;
    public int modelID;
    public int maleDialogue;
    public boolean stackable;
    public String description;
    public int certID;
    public static int cacheIndex;
    public static int cacheIndexOSRS;
    public int modelZoom;
    public static Stream stream;
    public static Stream streamOSRS;
    public int lightness;
    public int maleEquip3;
    public int maleEquip2;
    public String actions[];
    public int rotationY;
    public int sizeZ;
    public int sizeY;
    public int[] stackIDs;
    public int modelOffsetY;
    public static int[] streamIndices;
    public static int[] streamIndicesOSRS;
    public int shadow;
    public int femaleDialogue;
    public int rotationX;
    public int femaleEquip1;
    public int[] stackAmounts;
    public int team;
    public static int totalItems;
    public static int totalItemsOSRS;
    public int modelOffsetX;
    public byte maleYOffset;
    public byte maleXOffset;
    public byte maleZOffset;
    public int maleXScale;
    public int maleYScale;
    public int maleZScale;
    public int lendID;
    public int lentItemID;
    public boolean untradeable;
    public DataType dataType = DataType.REGULAR;
}