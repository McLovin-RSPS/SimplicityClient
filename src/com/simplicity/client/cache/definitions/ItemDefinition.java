package com.simplicity.client.cache.definitions;

import com.simplicity.Configuration;
import com.simplicity.client.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class ItemDefinition {

    private static int[] prices;
    private static List<Integer> untradeableItems = new ArrayList<Integer>();

    public static void nullLoader() {
        modelCache = null;
        spriteCache = null;
        streamIndices = null;
        cache = null;
        stream = null;
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
        if (!Model.modelIsFetched(k)) {
            flag = false;
        }
        if (l != -1 && !Model.modelIsFetched(l)) {
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
        Model model = Model.fetchModel(k);
        if (l != -1) {
            Model model_1 = Model.fetchModel(l);
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
        if (!Model.modelIsFetched(fistModel)) {
            flag = false;
        }
        if (secondModel != -1 && !Model.modelIsFetched(secondModel)) {
            flag = false;
        }
        if (thirdModel != -1 && !Model.modelIsFetched(thirdModel)) {
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
        Model model = Model.fetchModel(j);
        if (k != -1) {
            if (l != -1) {
                Model model_1 = Model.fetchModel(k);
                Model model_3 = Model.fetchModel(l);
                Model model_1s[] = {model, model_1, model_3};
                model = new Model(3, model_1s);
            } else {
                Model model_2 = Model.fetchModel(k);
                Model models[] = {model, model_2};
                model = new Model(2, models);
            }
        }
        //if (j == 62367)
        //model.translate(68, 7, -8);
        if (gender == 0 && maleYOffset != 0) {
            model.translate(0, maleYOffset, 0);
        } else if (gender == 1 && femaleYOffset != 0) {
            model.translate(0, femaleYOffset, 0);
        }
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
        maleYOffset = 0;
        maleXOffset = 0;
        femaleEquip1 = -1;
        femaleEquip2 = -1;
        femaleYOffset = 0;
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

    public static void unpackConfig(CacheArchive streamLoader) {
        /*
         * stream = new Stream(FileOperations.ReadFile("./Cache/obj.dat"));
		 * Stream stream = new
		 * Stream(FileOperations.ReadFile("./Cache/obj.idx"));
         */
        stream = new Stream(streamLoader.getDataForName("obj.dat"));
        Stream stream = new Stream(streamLoader.getDataForName("obj.idx"));
        totalItems = stream.readUnsignedWord();
        streamIndices = new int[totalItems + 1000];
        int i = 2;
        for (int j = 0; j < totalItems; j++) {
            streamIndices[j] = i;
            i += stream.readUnsignedWord();
        }
        cache = new ItemDefinition[10];
        for (int k = 0; k < 10; k++) {
            cache[k] = new ItemDefinition();
        }
        setSettings();
    }

    public static ItemDefinition forID(int i) {
        for (int j = 0; j < 10; j++) {
            if (cache[j].id == i) {
            	if(Configuration.DISCO_ITEMS) {
	            	if (i == 5572 || i == 5573 || i == 640 || i == 650 || i == 630) {
	            		ItemDefinition.cache[j].newModelColor[0] = RandomColor.currentColour;
	                }
            	}
                return cache[j];
            }
        }
        cacheIndex = (cacheIndex + 1) % 10;
        ItemDefinition itemDef = cache[cacheIndex];
        if (i >= streamIndices.length) {
            itemDef.id = 1;
            itemDef.setDefaults();
            return itemDef;
        }
        stream.currentOffset = streamIndices[i];
        itemDef.id = i;
        itemDef.setDefaults();
        itemDef.readValues(stream);
        if (itemDef.certTemplateID != -1) {
            itemDef.toNote();
        }
        if (itemDef.lentItemID != -1) {
            itemDef.toLend();
        }
        if (itemDef.id == i && itemDef.editedModelColor == null) {
            itemDef.editedModelColor = new int[1];
            itemDef.newModelColor = new int[1];
            itemDef.editedModelColor[0] = 0;
            itemDef.newModelColor[0] = 1;
        }
        if (untradeableItems.contains(itemDef.id)) {
            itemDef.untradeable = true;
        }
        itemDef.value = prices[itemDef.id];
        switch (i) {
        case 15420:
        	itemDef.actions = new String[5];
        	itemDef.actions[0] = "Open";
        	break;
		case 8421:
			itemDef.setDefaults();
			itemDef.name = "Dragonbone spirit shield";
			itemDef.modelZoom = 1616; // Model Zoom
			itemDef.maleEquip1 = 38941; // Male Equip 1
			itemDef.femaleEquip1 = 38941; // Male Equip 2
			itemDef.modelID = 38942; // Model ID
			itemDef.rotationY = 396; // Model Rotation 1
			itemDef.rotationX = 1050; // Model Rotation 2
			itemDef.modelOffset1 = -3; // Model Offset 1
			itemDef.modelOffsetY = 16; // Model Offset 2
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
     case 5573:
			ItemDefinition def = forID(1019);
			itemDef.name = "Disco cape";
			itemDef.description = def.description;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.editedModelColor = new int[1];
			itemDef.newModelColor = new int[1];
			itemDef.editedModelColor[0] = 7700;
			itemDef.newModelColor[0] = RandomColor.currentColour; 
			itemDef.modelID = def.modelID;
			itemDef.modelZoom = def.modelZoom;
			itemDef.rotationY = def.rotationY;
			itemDef.rotationX = def.rotationX;
			itemDef.modelOffset1 = def.modelOffset1;
			itemDef.modelOffsetY = def.modelOffsetY;
			itemDef.maleEquip1 = def.maleEquip1;
			itemDef.femaleEquip1 = def.femaleEquip1;
		//	System.out.print("model "+def.maleEquip1);
			break;

        /**
         * Treasure Island Keys
         */
        case 18689:
            itemDef.name = "Key of blitz";
            itemDef.actions = new String[5];
            itemDef.actions[4] = "Drop";
            itemDef.description = "I wonder what this does..?";
            break;
        case 14678:
            itemDef.name = "Key of cobra";
            itemDef.actions = new String[5];
            itemDef.actions[4] = "Drop";
            itemDef.description = "I wonder what this does..?";
            break;
        case 13158:
            itemDef.name = "Key of fear";
            itemDef.actions = new String[5];
            itemDef.actions[4] = "Drop";
            itemDef.description = "I wonder what this does..?";
            break;
        case 13758:
            itemDef.name = "Key of death";
            itemDef.actions = new String[5];
            itemDef.actions[4] = "Drop";
            itemDef.description = "I wonder what this does..?";
            break;
      
        case 640:
        	itemDef.name = "Disco Robe Top";
        	itemDef.newModelColor[0] = RandomColor.currentColour; 
        	break;
        case 630:
        	itemDef.name = "Disco Boots";
        	itemDef.newModelColor[0] = RandomColor.currentColour; 
        	break;
        case 650:
        	itemDef.name = "Disco Robe Bottom";
        	itemDef.newModelColor[0] = RandomColor.currentColour; 
        	break;
		case 5572:
			itemDef.name = "Disco partyhat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.editedModelColor = new int[1];
			itemDef.newModelColor = new int[1];
			itemDef.editedModelColor[0] = 926;
			itemDef.newModelColor[0] = RandomColor.currentColour; 
			itemDef.modelID = 2635;
			itemDef.modelZoom = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = 1;
			itemDef.maleEquip1 = 187;
			itemDef.femaleEquip1 = 363;
			break;
			
            /** OSRS && NEW ITEMS **/
            case 13247:
                itemDef.modelID = 29240;
                itemDef.name = "Hellpuppy";
                itemDef.description = "It's a Hellpuppy.";
                itemDef.modelZoom = 1616;
                itemDef.rotationY = 3;
                itemDef.rotationX = 213;
                //itemDef.modelOffset1 = 5;
                //itemDef.modelOffsetY = 38;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;

            case 12646:
                itemDef.modelID = 12073;
                itemDef.name = "Baby mole";
                itemDef.description = "It's a Baby mole.";
                itemDef.modelZoom = 2256;
                itemDef.rotationY = 369;
                itemDef.rotationX = 1874;
                //itemDef.modelOffset1 = 5;
                //itemDef.modelOffsetY = 38;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;

            case 12655:
                itemDef.modelID = 28869;
                itemDef.name = "Pet kraken";
                itemDef.description = "It's a Pet kraken.";
                itemDef.modelZoom = 1744;
                itemDef.rotationY = 330;
                itemDef.rotationX = 1940;
                //itemDef.modelOffset1 = 5;
                //itemDef.modelOffsetY = 38;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;

            case 20061:
                itemDef.modelID = 10247;
                itemDef.name = "Abyssal vine whip";
                itemDef.description = "Abyssal vine whip";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1808;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = 38;
                itemDef.maleEquip1 = 10253;
                itemDef.femaleEquip1 = 10253;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 20010:
                itemDef.name = "Trickster robe";
                itemDef.description = "Its a Trickster robe";
                itemDef.maleEquip1 = 44786;
                itemDef.femaleEquip1 = 44786;
                itemDef.modelID = 45329;
                itemDef.rotationY = 593;
                itemDef.rotationX = 2041;
                itemDef.modelZoom = 1420;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20011:
                itemDef.name = "Trickster robe legs";
                itemDef.description = "Its a Trickster robe";
                itemDef.maleEquip1 = 44770;
                itemDef.femaleEquip1 = 44770;
                itemDef.modelID = 45335;
                itemDef.rotationY = 567;
                itemDef.rotationX = 1023;
                itemDef.modelZoom = 2105;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20012:
                itemDef.name = "Trickster helm";
                itemDef.description = "Its a Trickster helm";
                itemDef.maleEquip1 = 44764;
                itemDef.femaleEquip1 = 44764;
                itemDef.modelID = 45328;
                itemDef.rotationY = 5;
                itemDef.rotationX = 1889;
                itemDef.modelZoom = 738;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20013:
                itemDef.modelID = 44633;
                itemDef.name = "Vanguard helm";
                itemDef.modelZoom = 855;
                itemDef.rotationY = 1966;
                itemDef.rotationX = 5;
                itemDef.modelOffsetY = 4;
                itemDef.modelOffset1 = -1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 44769;
                itemDef.femaleEquip1 = 44769;
                break;
            case 20014:
                itemDef.modelID = 44627;
                itemDef.name = "Vanguard body";
                itemDef.modelZoom = 1513;
                itemDef.rotationX = 2041;
                itemDef.rotationY = 593;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -11;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 44812;
                itemDef.femaleEquip1 = 44812;
                break;
          
            case 14062: 
                itemDef.modelID = 50011;
		        itemDef.name = "Vanguard legs";
		        itemDef.modelZoom = 1711;
		        itemDef.rotationX = 0;
		        itemDef.rotationY = 360;
		        itemDef.modelOffset1 = 3;
		        itemDef.modelOffsetY = -11;
	            itemDef.groundActions = new String[] { null, null, "Take", null, null };
		        itemDef.actions = new String[5];
		        itemDef.actions[1] = "Wear";
		        itemDef.maleEquip1 = 44771;
		        itemDef.femaleEquip1 = 44771;
		        break;
            case 20016:
                itemDef.modelID = 44704;
                itemDef.name = "Battle-mage helm";
                itemDef.modelZoom = 658;
                itemDef.rotationX = 1898;
                itemDef.rotationY = 2;
                itemDef.modelOffset1 = 12;
                itemDef.modelOffsetY = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 44767;
                itemDef.femaleEquip1 = 44767;
                break;
            case 20017:
                itemDef.modelID = 44631;
                itemDef.name = "Battle-mage robe";
                itemDef.modelZoom = 1382;
                itemDef.rotationX = 3;
                itemDef.rotationY = 488;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 44818;
                itemDef.femaleEquip1 = 44818;
                break;
            case 20018:
                itemDef.modelID = 44672;
                itemDef.name = "Battle-mage robe legs";
                itemDef.modelZoom = 1842;
                itemDef.rotationX = 1024;
                itemDef.rotationY = 498;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = -1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 44775;
                itemDef.femaleEquip1 = 44775;
                break;
            case 20019:
                itemDef.modelID = 45316;
                itemDef.name = "Trickster boots";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 44757;
                itemDef.femaleEquip1 = 44757;
                break;
            case 20020:
                itemDef.modelID = 45317;
                itemDef.name = "Trickster gloves";
                itemDef.modelZoom = 830;
                itemDef.rotationX = 150;
                itemDef.rotationY = 536;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 44761;
                itemDef.femaleEquip1 = 44761;
                break;
            case 20021:
                itemDef.modelID = 44662;
                itemDef.name = "Battle-mage boots";
                itemDef.modelZoom = 987;
                itemDef.rotationX = 1988;
                itemDef.rotationY = 188;
                itemDef.modelOffset1 = -8;
                itemDef.modelOffsetY = 5;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 44755;
                itemDef.femaleEquip1 = 44755;
                break;
            case 20022:
                itemDef.modelID = 44573;
                itemDef.name = "Battle-mage gloves";
                itemDef.modelZoom = 1053;
                itemDef.rotationX = 0;
                itemDef.rotationY = 536;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 44762;
                itemDef.femaleEquip1 = 44762;
                break;
            case 11554:
                itemDef.name = "Abyssal tentacle";
                itemDef.modelZoom = 840;
                itemDef.rotationY = 280;
                itemDef.rotationX = 121;
                itemDef.modelOffsetY = 56;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 28439;
                itemDef.maleEquip1 = 45006;
                itemDef.femaleEquip1 = 43500;
                break;
            case 11926:
                itemDef.name = "Odium ward";
                itemDef.modelZoom = 1200;
                itemDef.rotationY = 568;
                itemDef.rotationX = 1836;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 3;
                itemDef.newModelColor = new int[]{15252};
                itemDef.editedModelColor = new int[]{908};
                itemDef.modelID = 9354;
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.maleEquip1 = 9347;
                itemDef.femaleEquip1 = 9347;
                break;
            case 11290:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 689484;
                itemDef.modelID = 2438;
                itemDef.modelZoom = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                itemDef.maleEquip1 = 3188;
                itemDef.femaleEquip1 = 3192;
                itemDef.name = "Sky Blue h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11291:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelID = 2438;
                itemDef.modelZoom = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                itemDef.maleEquip1 = 3188;
                itemDef.femaleEquip1 = 3192;
                itemDef.name = "Orange h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11292:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 11199;
                itemDef.modelID = 2438;
                itemDef.modelZoom = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                itemDef.maleEquip1 = 3188;
                itemDef.femaleEquip1 = 3192;
                itemDef.name = "Yellow h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11293:
                itemDef.name = "Yellow Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare yellow santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 11191;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 11294:
                itemDef.name = "Sky Blye Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare sky blue santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 689484;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12426:
                itemDef.modelID = 28633;
                itemDef.name = "3rd age longsword";
                itemDef.description = "3rd age longsword";
                itemDef.modelZoom = 1726;
                itemDef.rotationY = 1576;
                itemDef.rotationX = 242;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = 4;
                itemDef.maleEquip1 = 28618;
                itemDef.femaleEquip1 = 28618;
                itemDef.femaleYOffset = 4;
                itemDef.maleYOffset = 4;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                break;

            case 12437:
                itemDef.modelID = 28648;
                itemDef.name = "3rd age cloak";
                itemDef.description = "3rd age cloak";
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 282;
                itemDef.rotationX = 962;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 28483;
                itemDef.femaleEquip1 = 28560;
                itemDef.maleXOffset = -3;
                itemDef.maleYOffset = -3;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                break;
            case 11295:
                itemDef.name = "White Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare white santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 11289:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 9583;
                itemDef.modelID = 2438;
                itemDef.modelZoom = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                itemDef.maleEquip1 = 3188;
                itemDef.femaleEquip1 = 3192;
                itemDef.name = "White h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11288:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 196608;
                itemDef.modelID = 2438;
                itemDef.modelZoom = 730;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                itemDef.maleEquip1 = 3188;
                itemDef.femaleEquip1 = 3192;
                itemDef.name = "Black h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11924:
                itemDef.name = "Malediction ward";
                itemDef.modelZoom = 1200;
                itemDef.rotationY = 568;
                itemDef.rotationX = 1836;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 3;
                itemDef.newModelColor = new int[]{-21608};
                itemDef.editedModelColor = new int[]{908};
                itemDef.modelID = 9354;
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.maleEquip1 = 9347;
                itemDef.femaleEquip1 = 9347;
                break;
            case 12282:
                itemDef.name = "Serpentine helm";
                itemDef.modelID = 19220;
                itemDef.modelZoom = 700;
                itemDef.rotationX = 1724;
                itemDef.rotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.femaleEquip1 = 14398;
                itemDef.maleEquip1 = 14395;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12279:
                itemDef.name = "Magma helm";
                itemDef.modelID = 29205;
                itemDef.modelZoom = 700;
                itemDef.rotationX = 1724;
                itemDef.rotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.femaleEquip1 = 14426;
                itemDef.maleEquip1 = 14424;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12278:
                itemDef.name = "Tanzanite helm";
                itemDef.modelID = 29213;
                itemDef.modelZoom = 700;
                itemDef.rotationX = 1724;
                itemDef.rotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.femaleEquip1 = 23994;
                itemDef.maleEquip1 = 14421;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case -1:
                itemDef.name = "Pet King black dragon";
                ItemDefinition itemDef2 = ItemDefinition.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 13239:
                itemDef.name = "Primordial boots";
                itemDef.modelID = 29397;
                itemDef.modelZoom = 976;
                itemDef.rotationY = 147;
                itemDef.rotationX = 279;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 5;
                itemDef.maleEquip1 = 29250;
                itemDef.femaleEquip1 = 29255;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 12708:
                itemDef.name = "Pegasian boots";
                itemDef.modelID = 29396;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.groundActions = new String[5];
                itemDef.groundActions[1] = "Take";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 279;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = 29252;
                itemDef.femaleEquip1 = 29253;
                break;
            case 13235:
                itemDef.name = "Eternal boots";
                itemDef.modelID = 29394;
                itemDef.modelZoom = 976;
                itemDef.rotationY = 147;
                itemDef.rotationX = 279;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 5;
                itemDef.maleEquip1 = 29249;
                itemDef.femaleEquip1 = 29254;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 20059:
                itemDef.name = "Drygore rapier";
                itemDef.modelZoom = 1145;
                itemDef.rotationX = 2047;
                itemDef.rotationY = 254;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -45;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check-charges", null, "Drop"};
                itemDef.modelID = 14000;
                itemDef.maleEquip1 = 14001;
                itemDef.femaleEquip1 = 14001;
                break;
            case 20057:
                itemDef.name = "Drygore longsword";
                itemDef.modelZoom = 1645;
                itemDef.rotationX = 377;
                itemDef.rotationY = 444;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check-charges", null, "Drop"};
                itemDef.modelID = 14022;
                itemDef.maleEquip1 = 14023;
                itemDef.femaleEquip1 = 14023;
                break;
            case 20058:
                itemDef.name = "Drygore mace";
                itemDef.modelZoom = 1118;
                itemDef.rotationX = 28;
                itemDef.rotationY = 235;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = -47;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check-charges", null, "Drop"};
                itemDef.modelID = 14024;
                itemDef.maleEquip1 = 14025;
                itemDef.femaleEquip1 = 14025;
                break;
            /**END OF OSRS ITEMS**/
            case 19670:
                itemDef.name = "Vote scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                break;
            case 6821:
                itemDef.name = "Magic Coin Orb";
                break;
            case 10034:
            case 10033:
                itemDef.actions = new String[]{null, null, null, null, "Drop"};
                break;
            case 13727:
                itemDef.actions = new String[]{null, null, null, null, "Drop"};
                break;
            case 6500:
                itemDef.setDefaults();
                itemDef.imitate(forID(9952));
                itemDef.name = "Charming imp";
                itemDef.stackable = false;
                // itemDef.rotationX = 85;
                // itemDef.rotationY = 1867;
                itemDef.actions = new String[]{null, null, "Check", "Config", "Drop"};
                break;
            case 13045:
                itemDef.name = "Abyssal bludgeon";
                itemDef.modelZoom = 2611;
                itemDef.rotationY = 552;
                itemDef.rotationX = 1508;
                itemDef.modelOffsetY = 3;
                itemDef.modelOffset1 = -17;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Uncharge", "Drop"};
                itemDef.modelID = 29597;
                itemDef.maleEquip1 = 29424;
                itemDef.femaleEquip1 = 29424;
                break;
            case 13047:
                itemDef.name = "Abyssal dagger";
                itemDef.modelZoom = 1347;
                itemDef.rotationY = 1589;
                itemDef.rotationX = 781;
                itemDef.modelOffsetY = 3;
                itemDef.modelOffset1 = -5;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Uncharge", "Drop"};
                itemDef.modelID = 29598;
                itemDef.maleEquip1 = 29425;
                itemDef.femaleEquip1 = 29425;
                break;
            case 500:
                itemDef.modelID = 2635;
                itemDef.name = "Black Party Hat";
                itemDef.description = "Black Party Hat";
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1845;
                itemDef.rotationY = 121;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 1;
                itemDef.stackable = false;
                itemDef.value = 1;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor = new int[]{926};
                break;
            case 11551:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor[0] = 6020;
                itemDef.editedModelColor[0] = 933;
                itemDef.modelID = 2537;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 72;
                itemDef.rotationY = 136;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.name = "Black santa hat";
                itemDef.description = "It's a Black santa hat.";
                break;
            case 13655:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.name = "Red Dragon Kiteshield";
                itemDef.description = "A rare, protective kiteshield.";
                itemDef.modelID = 13701;
                itemDef.modelZoom = 1560;
                itemDef.rotationY = 344;
                itemDef.rotationX = 1104;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = -6;
                itemDef.modelOffsetY = -14;
                itemDef.maleEquip1 = 13700;
                itemDef.femaleEquip1 = 13700;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                //itemDef.anInt188 = -1;
                //itemDef.anInt164 = -1;
                itemDef.maleDialogue = -1;
                itemDef.femaleDialogue = -1;
                break;
            case 12284:
                itemDef.name = "Toxic staff of the dead";
                itemDef.modelID = 19224;
                itemDef.modelZoom = 2150;
                itemDef.rotationX = 1010;
                itemDef.rotationY = 512;
                itemDef.femaleEquip1 = 14402;
                itemDef.maleEquip1 = 49001;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                itemDef.actions[4] = "Uncharge";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.editedModelColor = new int[1];
                itemDef.editedModelColor[0] = 17467;
                itemDef.newModelColor = new int[1];
                itemDef.newModelColor[0] = 21947;
                break;
            case 12605:
                itemDef.name = "Treasonous ring";
                itemDef.modelZoom = 750;
                itemDef.rotationY = 342;
                itemDef.rotationX = 252;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 43900;
                break;

            /** clue scrolls **/
            case 2714:
                itemDef.name = "Casket";
                break;
            case 6183:
                itemDef.name = "@red@Donation Box";
                break;
            case 12632:
                itemDef.name = "100m Note";
                itemDef.actions = new String[]{"Claim", null, null, null, "Drop"};
                break;
            case 4202:
                itemDef.name = "Ring of Coins";
            	break;
            case 2568:
            	itemDef.name = "Ring of Wealthier";
            	break;
            
            case 2677:
            case 2678:
            case 2679:
            case 2680:
            case 2681:
            case 2682:
            case 2683:
            case 2684:
            case 2685:
            case 2686:
            case 2687:
            case 2688:
            case 2689:
            case 2690:
            case 2691:
                itemDef.name = "Clue Scroll";
                break;


            case 13051:
                itemDef.name = "Armadyl crossbow";
                itemDef.modelZoom = 1325;
                itemDef.rotationY = 240;
                itemDef.rotationX = 110;
                itemDef.modelOffset1 = -6;
                itemDef.modelOffsetY = -40;
                itemDef.newModelColor = new int[]{115, 107, 10167, 10171};
                itemDef.editedModelColor = new int[]{5409, 5404, 6449, 7390};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Drop"};
                itemDef.modelID = 19967;
                itemDef.maleEquip1 = 19839;
                itemDef.femaleEquip1 = 19839;
                break;
            case 12927:
                itemDef.name = "Magma blowpipe";
                itemDef.modelZoom = 1158;
                itemDef.rotationY = 768;
                itemDef.rotationX = 189;
                itemDef.modelOffset1 = -7;
                itemDef.modelOffsetY = 4;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Unload", "Uncharge"};
                itemDef.newModelColor = new int[]{8134, 5058, 926, 957, 3008, 1321, 86, 41, 49, 7110, 3008, 1317};
                itemDef.editedModelColor = new int[]{48045, 49069, 48055, 49083, 50114, 33668, 29656, 29603, 33674, 33690, 33680, 33692};
                itemDef.modelID = 19219;
                itemDef.maleEquip1 = 14403;
                itemDef.femaleEquip1 = 14403;
                break;
            case 12926:
                itemDef.modelID = 25000;
                itemDef.name = "Toxic blowpipe";
                itemDef.description = "It's a Toxic blowpipe";
                itemDef.modelZoom = 1158;
                itemDef.rotationY = 768;
                itemDef.rotationX = 189;
                itemDef.modelOffset1 = -7;
                itemDef.modelOffsetY = 4;
                itemDef.maleEquip1 = 14403;
                itemDef.femaleEquip1 = 14403;
                itemDef.actions = new String[]{null, "Wield", "Check", "Unload", "Drop"};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                break;
            case 13058:
                itemDef.name = "Trident of the seas";
                itemDef.description = "A weapon from the deep.";
                itemDef.femaleEquip1 = 1052;
                itemDef.maleEquip1 = 1052;
                itemDef.modelID = 1051;
                itemDef.rotationY = 420;
                itemDef.rotationX = 1130;
                itemDef.modelZoom = 2755;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[3] = "Check";
                break;
            case 12601:
                itemDef.name = "Ring of the gods";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 393;
                itemDef.rotationX = 1589;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 33009;
                break;
            case 12603:
                itemDef.name = "Tyrannical ring";
                itemDef.modelZoom = 592;
                itemDef.rotationY = 285;
                itemDef.rotationX = 1163;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 28823;
                break;
            case 20555:
                itemDef.name = "Dragon warhammer";
                itemDef.modelID = 4041;
                itemDef.rotationY = 1450;
                itemDef.rotationX = 1900;
                itemDef.modelZoom = 1605;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Drop"};
                itemDef.maleEquip1 = 4037;
                itemDef.femaleEquip1 = 4038;
                break;
            case 11613:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 13701;
                itemDef.modelZoom = 1560;
                itemDef.rotationY = 344;
                itemDef.rotationX = 1104;
                itemDef.modelOffsetY = -14;
                itemDef.modelOffsetX = 0;
                itemDef.maleEquip1 = 13700;
                itemDef.femaleEquip1 = 13700;
                itemDef.name = "Dragon Kiteshield";
                itemDef.description = "A Dragon Kiteshield!";
                break;
            case 4151:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.name = "Abyssal whip";
                itemDef.description = "Lowest powered whip";
                itemDef.modelID = 5412;//Inv & Ground
                itemDef.modelZoom = 840;
                itemDef.rotationY = 280;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 56;
                itemDef.maleEquip1 = 5409;//Male Wield View
                itemDef.femaleEquip1 = 5409;//Female Wield View

                break;
            case 11614:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 3288;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleEquip1 = 3287;
                itemDef.femaleEquip1 = 3287;
                itemDef.name = "Death Cape";
                break;
            case 11995:
                itemDef.name = "Pet Chaos elemental";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1284;
                itemDef.rotationX = 175;
                itemDef.rotationY = 0;
                itemDef.modelID = 40852;
                itemDef.modelOffset1 = -66;
                itemDef.modelOffsetY = 75;
                itemDef.modelOffsetX = 1939;
                break;
            case 11996:
                itemDef.name = "Pet King black dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2000;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 40858;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11997:
                itemDef.name = "Pet General graardor";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1872;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 40853;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11978:
                itemDef.name = "Pet TzTok-Jad";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 10000;
                itemDef.rotationX = 553;
                itemDef.rotationY = 0;
                itemDef.modelID = 40854;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -30;
                itemDef.modelOffsetX = 0;
                break;
            case 12001:
                itemDef.name = "Pet Corporeal beast";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 10000;
                itemDef.rotationX = 553;
                itemDef.rotationY = 0;
                itemDef.modelID = 40955;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -30;
                itemDef.modelOffsetX = 0;
                break;
            case 12002:
                itemDef.name = "Pet Kree'arra";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 976;
                itemDef.rotationX = 1892;
                itemDef.rotationY = 2042;
                itemDef.modelID = 40855;
                itemDef.modelOffset1 = -20;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12003:
                itemDef.name = "Pet K'ril tsutsaroth";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1168;
                itemDef.rotationX = 0;
                itemDef.rotationY = 2012;
                itemDef.modelID = 40856;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12004:
                itemDef.name = "Pet Commander zilyana";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1000;
                itemDef.rotationX = 1931;
                itemDef.rotationY = 9;
                itemDef.modelID = 40857;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12005:
                itemDef.name = "Pet Dagannoth supreme";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 9941;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12006:
                itemDef.name = "Pet Dagannoth prime";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 9941;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.newModelColor = new int[]{5931, 1688, 21530, 21534};
                itemDef.editedModelColor = new int[]{11930, 27144, 16536, 16540};
                break;
            case 11990:
                itemDef.name = "Pet Dagannoth rex";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 9941;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.newModelColor = new int[]{7322, 7326, 10403, 2595};
                itemDef.editedModelColor = new int[]{16536, 16540, 27144, 2477};
                break;
            case 11991:
                itemDef.name = "Pet Frost dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 56767;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11992:
                itemDef.name = "Pet Tormented demon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 44733;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11993:
                itemDef.name = "Pet Kalphite queen";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 24597;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11994:
                itemDef.name = "Pet Slash bash";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 46141;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11989:
                itemDef.name = "Pet Phoenix";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 45412;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11988:
                itemDef.name = "Pet Bandos avatar";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 6060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 46058;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11987:
                itemDef.name = "Pet Nex";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5000;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 62717;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11986:
                itemDef.name = "Pet Jungle strykewyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 51852;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11985:
                itemDef.name = "Pet Desert strykewyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 51848;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11984:
                itemDef.name = "Pet Ice strykewyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 51847;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11983:
                itemDef.name = "Pet Green dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 49142;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11982:
                itemDef.name = "Pet Baby blue dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 3060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 57937;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11981:
                itemDef.name = "Pet Blue dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 49137;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11979:
                itemDef.name = "Pet Black dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 14294;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;

            case 11967:
                itemDef.name = "Pet Skotizo";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 13000;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 31653;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11969:
                itemDef.name = "Pet Lizardman Shaman";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 8060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 4039;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11970:
                itemDef.name = "Pet WildyWyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 6060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 63604;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11971:
                itemDef.name = "Pet Bork";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 6560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 40590;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11972:
                itemDef.name = "Pet Barrelchest";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 22790;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11973:
                itemDef.name = "Pet Abyssal Sire";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 12060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 29477;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11974:
                itemDef.name = "Pet Rock Crab";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 4400;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11975:
                itemDef.name = "Pet Scorpia";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 3347;
                itemDef.rotationX = 189;
                itemDef.rotationY = 121;
                itemDef.modelID = 28293;
                itemDef.modelOffset1 = 12;
                itemDef.modelOffsetY = -100;
                itemDef.modelOffsetX = 0;
                break;

            case 11976:
                itemDef.name = "Pet Venenatis";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4080;
                itemDef.rotationX = 67;
                itemDef.rotationY = 67;
                itemDef.modelID = 28294;
                itemDef.modelOffset1 = 9;
                itemDef.modelOffsetY = -4;
                itemDef.modelOffsetX = 0;
                break;
            case 14667:
                itemDef.name = "Zombie fragment";
                itemDef.modelID = ItemDefinition.forID(14639).modelID;
                break;
            case 15182:
                itemDef.actions[0] = "Bury";
                break;
            case 15084:
                itemDef.actions[0] = "Roll";
                itemDef.name = "Dice (up to 100)";
                itemDef2 = ItemDefinition.forID(15098);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 2996:
                itemDef.name = "Agility ticket";
                break;
            case 5510:
            case 5512:
            case 5509:
                itemDef.actions = new String[]{"Fill", null, "Empty", "Check", null, null};
                break;
            case 11998:
                itemDef.name = "Scimitar";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 11999:
                itemDef.name = "Scimitar";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 700;
                itemDef.rotationX = 0;
                itemDef.rotationY = 350;
                itemDef.modelID = 2429;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 11998;
                itemDef.certTemplateID = 799;
                break;
            case 10025:
                itemDef.name = "Charm Box";
                itemDef.actions = new String[]{"Open", null, null, null, null};
                break;
            case 1389:
                itemDef.name = "Staff";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 1390:
                itemDef.name = "Staff";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 17401:
                itemDef.name = "Damaged Hammer";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 17402:
                itemDef.name = "Damaged Hammer";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelID = 2429;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 17401;
                itemDef.certTemplateID = 799;
                break;
            case 15009:
                itemDef.name = "Gold Ring";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 20787:
    			itemDef.modelID = 35118;
    			itemDef.name = "Golden mining gloves";
    			itemDef.modelZoom = 1049;
    			itemDef.rotationX = 377;
    			itemDef.rotationY = 970;
    			itemDef.modelOffset1 = -1;
    			itemDef.modelOffsetY = -1;
    			itemDef.maleEquip1 = 35119;
    			itemDef.femaleEquip1 = 35120;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
    		case 20788:
    			itemDef.modelID = 35121;
    			itemDef.name = "Golden mining boots";
    			itemDef.modelZoom = 848;
    			itemDef.rotationX = 177;
    			itemDef.rotationY = 195;
    			itemDef.modelOffset1 = 7;
    			itemDef.modelOffsetY = -20;
    			itemDef.maleEquip1 = 35122;
    			itemDef.femaleEquip1 = 35123;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
    		case 20789:
    			itemDef.modelID = 35124;
    			itemDef.name = "Golden mining helmet";
    			itemDef.modelZoom = 976;
    			itemDef.rotationX = 132;
    			itemDef.rotationY = 165;
    			itemDef.modelOffset1 = 0;
    			itemDef.modelOffsetY = 0;
    			itemDef.maleEquip1 = 35125;
    			itemDef.femaleEquip1 = 35126;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
    		case 15001:
                 itemDef.modelID = 35739;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.name = "Ghrazi rapier";
                 itemDef.description = "It is the Ghrazi Rapier";
                 itemDef.modelZoom = 2200;
                 itemDef.stackable = false;
                 itemDef.rotationX = 1603;
                 itemDef.rotationY = 552;
                 itemDef.maleEquip1 = 35374;
                 itemDef.femaleEquip1 = 35369;
                 break;
             case 15000:
                 itemDef.modelID = 35742;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.name = "@or2@Scythe Of Vitur";
                 itemDef.description = "It is the Scythe Of Vitur";
                 itemDef.modelZoom = 2200;
                 itemDef.stackable = false;
                 itemDef.rotationX = 23;
                 itemDef.rotationY = 327;
                 itemDef.maleEquip1 = 35371;
                 itemDef.femaleEquip1 = 35371;
                 break;
    		case 20790:
    			itemDef.modelID = 35127;
    			itemDef.name = "Golden mining trousers";
    			itemDef.modelZoom = 1616;
    			itemDef.rotationX = 276;
    			itemDef.rotationY = 1829;
    			itemDef.modelOffset1 = 0;
    			itemDef.modelOffsetY = 11;
    			itemDef.maleEquip1 = 35128;
    			itemDef.femaleEquip1 = 35129;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
    		case 13356:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.modelID = 65495;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65496;
    			itemDef.femaleEquip1 = 65496;
    			itemDef.stackable = false;
    			itemDef.name = "Master agility cape";
    			itemDef.description = "Master agility cape";
    			break;
    		 case 11350: 
   		      ItemDefinition itemDef21 = forID(11732);
   		      itemDef.modelID = itemDef21.modelID;
   		      itemDef.maleEquip1 = itemDef21.maleEquip1;
   		      itemDef.femaleEquip1 = itemDef21.femaleEquip1;
   		      itemDef.modelOffset1 = itemDef21.modelOffset1;
   		      itemDef.modelOffsetX = itemDef21.modelOffsetX;
   		      itemDef.modelOffsetY = itemDef21.modelOffsetY;
   		      itemDef.rotationY = itemDef21.rotationY;
   		      itemDef.rotationX = itemDef21.rotationX;
   		      itemDef.modelZoom = itemDef21.modelZoom;
   		      itemDef.name = "Green Dragon boots ";
   		      itemDef.actions = itemDef21.actions;
   		     
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor = new int[] { 419770 };
   		     
   		      itemDef.stackable = false;
   		   
   		      break;
   		 case 11352: 
   		      ItemDefinition itemDef2111 = forID(11732);
   		      itemDef.modelID = itemDef2111.modelID;
   		      itemDef.maleEquip1 = itemDef2111.maleEquip1;
   		      itemDef.femaleEquip1 = itemDef2111.femaleEquip1;
   		      itemDef.modelOffset1 = itemDef2111.modelOffset1;
   		      itemDef.modelOffsetX = itemDef2111.modelOffsetX;
   		      itemDef.modelOffsetY = itemDef2111.modelOffsetY;
   		      itemDef.rotationY = itemDef2111.rotationY;
   		      itemDef.rotationX = itemDef2111.rotationX;
   		      itemDef.modelZoom = itemDef2111.modelZoom;
   		      itemDef.name = "Blue Dragon boots ";
   		      itemDef.actions = itemDef2111.actions;
   		     
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 302770 };
   		      itemDef.stackable = false;
   		    
   		      break;
   		 case 11351: 
   		      ItemDefinition itemDef211 = forID(11732);
   		      itemDef.modelID = itemDef211.modelID;
   		      itemDef.maleEquip1 = itemDef211.maleEquip1;
   		      itemDef.femaleEquip1 = itemDef211.femaleEquip1;
   		      itemDef.modelOffset1 = itemDef211.modelOffset1;
   		      itemDef.modelOffsetX = itemDef211.modelOffsetX;
   		      itemDef.modelOffsetY = itemDef211.modelOffsetY;
   		      itemDef.rotationY = itemDef211.rotationY;
   		      itemDef.rotationX = itemDef211.rotationX;
   		      itemDef.modelZoom = itemDef211.modelZoom;
   		      itemDef.name = "Lava Dragon boots ";
   		      itemDef.actions = itemDef211.actions;
   		     
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 461770 };
   		      itemDef.stackable = false;
   		    
   		      break;
   
   		 case 11353: 
   		      ItemDefinition itemDef211111 = forID(11732);
   		      itemDef.modelID = itemDef211111.modelID;
   		      itemDef.maleEquip1 = itemDef211111.maleEquip1;
   		      itemDef.femaleEquip1 = itemDef211111.femaleEquip1;
   		      itemDef.modelOffset1 = itemDef211111.modelOffset1;
   		      itemDef.modelOffsetX = itemDef211111.modelOffsetX;
   		      itemDef.modelOffsetY = itemDef211111.modelOffsetY;
   		      itemDef.rotationY = itemDef211111.rotationY;
   		      itemDef.rotationX = itemDef211111.rotationX;
   		      itemDef.modelZoom = itemDef211111.modelZoom;
   		      itemDef.name = "Pink Dragon boots ";
   		      itemDef.actions = itemDef211111.actions;		 
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 123770 };
   		      itemDef.stackable = false;		    
   		      break;
   		case 19481:
			itemDef.name = "Heavy ballista";
			itemDef.description = "It's a Heavy ballista!";
			itemDef.modelZoom = 1284;
			itemDef.rotationY = 189;
			itemDef.rotationX = 148;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.modelID = 31523;
			itemDef.maleEquip1 = 31236;
			itemDef.femaleEquip1 = 31236;
			break;
   	/*	case 13329:
			itemDef.name = "Fire max cape";
			itemDef.modelZoom = 2232;
			itemDef.rotationY = 687;
			itemDef.rotationX = 27;
			itemDef.modelOffsetY = 0;
			itemDef.modelOffset1 = 27;
			
			itemDef.newModelColor = new int[] { 668,675,673,815,784 };
	        itemDef.editedModelColor = new int[] { 947,960,7104,8146,0 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", "Teleports", "Features", "Drop", };
			itemDef.modelID = 10888;
			itemDef.maleEquip1 = 10889;
			itemDef.femaleEquip1 = 11080; 
			break; */
   		case 20998:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelID = 32799;
			itemDef.name = "Twisted bow";
			itemDef.modelZoom = 2000;
			itemDef.rotationY = 720;
			itemDef.rotationX = 1500;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 1;
			itemDef.femaleEquip1 = 32674;
			itemDef.maleEquip1 = 32674;
			itemDef.description = "A mystical bow carved from the twisted remains of the Great Olm.";
			break;
        case 4706:
            itemDef.modelID = 62692;
            itemDef.name = "Zaryte bow";
            itemDef.modelZoom = 1703;
            itemDef.rotationY = 221;
            itemDef.rotationX = 404;
            itemDef.modelOffsetX = 0;
            itemDef.modelOffsetY = -13;
            itemDef.maleEquip1 = 62750;
            itemDef.femaleEquip1 = 62750;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.actions[4] = "Drop";
            break;
   		case 8465:
		    itemDef.name = "Red slayer helmet";
		    itemDef.description = "You really don't want to wear it inside-out.";
		    itemDef.modelID = 20861;
		    itemDef.maleEquip1 = 20862;
		    itemDef.femaleEquip1 = 20862;
		    itemDef.modelZoom = 750;
		    itemDef.actions = new String[] { null, "Wear", "Revert", null, "Drop" };
		    itemDef.groundActions = new String[] {null, null, "Take", null, null, };
		    itemDef.rotationX = 1743;
		    itemDef.rotationY = 69;
		    itemDef.modelOffset1 = -4;
		    itemDef.modelOffsetY = -3;
		
		    break;
		    
		case 8467:
		    itemDef.name = "Green slayer helmet";
		    itemDef.description = "You really don't want to wear it inside-out.";
		    itemDef.modelID = 20859;
		    itemDef.maleEquip1 = 20860;
		    itemDef.femaleEquip1 = 20860;
		    itemDef.modelZoom = 750;
		    itemDef.actions = new String[] { null, "Wear", "Revert", null, "Drop" };
		    itemDef.groundActions = new String[] {null, null, "Take", null, null, };
		    itemDef.rotationX = 1743;
		    itemDef.rotationY = 69;
		    itemDef.modelOffset1 = -4;
		    itemDef.modelOffsetY = -3;
		   
		    break;
		case 8469:
		    itemDef.name = "Black slayer helmet";
		    itemDef.description = "You really don't want to wear it inside-out.";
		    itemDef.modelID = 20863;
		    itemDef.maleEquip1 = 20864;
		    itemDef.femaleEquip1 = 20864;
		    itemDef.modelZoom = 750;
		    itemDef.actions = new String[] { null, "Wear", "Revert", null, "Drop" };
		    itemDef.groundActions = new String[] {null, null, "Take", null, null, };
		    itemDef.rotationX = 1743;
		    itemDef.rotationY = 69;
		    itemDef.modelOffset1 = -4;
		    itemDef.modelOffsetY = -3;
	
		    break;
   		 case 11354: 
   		      ItemDefinition itemDef2111111 = forID(11732);
   		      itemDef.modelID = itemDef2111111.modelID;
   		      itemDef.maleEquip1 = itemDef2111111.maleEquip1;
   		      itemDef.femaleEquip1 = itemDef2111111.femaleEquip1;
   		      itemDef.modelOffset1 = itemDef2111111.modelOffset1;
   		      itemDef.modelOffsetX = itemDef2111111.modelOffsetX;
   		      itemDef.modelOffsetY = itemDef2111111.modelOffsetY;
   		      itemDef.rotationY = itemDef2111111.rotationY;
   		      itemDef.rotationX = itemDef2111111.rotationX;
   		      itemDef.modelZoom = itemDef2111111.modelZoom;
   		      itemDef.name = "Yellow Dragon boots ";
   		      itemDef.actions = itemDef2111111.actions;		 
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 76770 };
   		      itemDef.stackable = false;		    
   		      break;
   		 case 11355: 
   		      ItemDefinition itemDef21111111 = forID(11732);
   		      itemDef.modelID = itemDef21111111.modelID;
   		      itemDef.maleEquip1 = itemDef21111111.maleEquip1;
   		      itemDef.femaleEquip1 = itemDef21111111.femaleEquip1;
   		      itemDef.modelOffset1 = itemDef21111111.modelOffset1;
   		      itemDef.modelOffsetX = itemDef21111111.modelOffsetX;
   		      itemDef.modelOffsetY = itemDef21111111.modelOffsetY;
   		      itemDef.rotationY = itemDef21111111.rotationY;
   		      itemDef.rotationX = itemDef21111111.rotationX;
   		      itemDef.modelZoom = itemDef21111111.modelZoom;
   		    //itemDef.name = "White Dragon boots ";
   		      itemDef.actions = itemDef21111111.actions;		 
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 266770 };
   		      itemDef.stackable = false;		    
   		      break;
   		 case 11356: 
   		      ItemDefinition itemDef211111111 = forID(11732);
   		      itemDef.modelID = itemDef211111111.modelID;
   		      itemDef.maleEquip1 = itemDef211111111.maleEquip1;
   		      itemDef.femaleEquip1 = itemDef211111111.femaleEquip1;
   		      itemDef.modelOffset1 = itemDef211111111.modelOffset1;
   		      itemDef.modelOffsetX = itemDef211111111.modelOffsetX;
   		      itemDef.modelOffsetY = itemDef211111111.modelOffsetY;
   		      itemDef.rotationY = itemDef211111111.rotationY;
   		      itemDef.rotationX = itemDef211111111.rotationX;
   		      itemDef.modelZoom = itemDef211111111.modelZoom;
   		      itemDef.name = "White Dragon boots ";
   		      itemDef.actions = itemDef211111111.actions;		 
   		      itemDef.editedModelColor = new int[] { 926 };
   		      itemDef.newModelColor  = new int[] { 100 };
   		      itemDef.stackable = false;		    
   		      break;
    		case 13657:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65497;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65498;
    			itemDef.femaleEquip1 = 65498;
    			itemDef.stackable = false;
    			   itemDef.rotationY = 0;
    			itemDef.name = "Master attack cape";
    			itemDef.description = "Master attack cape";
    			break;
    		case 13994:
    			   itemDef.modelID = 44699;
    			   itemDef.name = "Vanguard gloves";
    			   itemDef.modelZoom = 830;
    			   itemDef.rotationX = 536;
    			   itemDef.rotationY = 0;
    			   itemDef.modelOffset1 = 9;
    			   itemDef.modelOffsetY = 3;
    			   itemDef.stackable = false;
    			   itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
    			   itemDef.maleEquip1 = 44758;
    			   itemDef.femaleEquip1 = 44758;
    			   break;

    			  case 13995:
    			   itemDef.modelID = 44700;
    			   itemDef.name = "Vanguard boots";
    			   itemDef.modelZoom = 848;
    			   itemDef.rotationY = 141;
    			   itemDef.rotationX = 141;
    			   itemDef.modelOffset1 = 4;
    			   itemDef.modelOffsetY = 0;
    			   itemDef.stackable = false;
    			   itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
    			   itemDef.maleEquip1 = 44752;
    			   itemDef.femaleEquip1 = 44752;
    			   break;
    		case 13658:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65499;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65500;
    			itemDef.femaleEquip1 = 65500;
    			itemDef.stackable = false;
    			itemDef.name = "Master const. cape";
    			itemDef.description = "Master const. cape";
    			break;
    		case 13359:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65501;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65502;
    			itemDef.femaleEquip1 = 65502;
    			itemDef.stackable = false;
    			itemDef.name = "master cooking cape";
    			itemDef.description = "Master cooking cape";
    			break;
    		case 13360:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65503;
                itemDef.modelZoom = 1385;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
    			itemDef.maleEquip1 = 65504;
    			itemDef.femaleEquip1 = 65504;
    			itemDef.stackable = false;
    			itemDef.name = "master crafting cape";
    			itemDef.description = "Master crafting cape";
    			break;
    		case 13361:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65505;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65506;
    			itemDef.femaleEquip1 = 65506;
    			itemDef.stackable = false;
    			itemDef.name = "master defence cape";
    			itemDef.description = "Master defence cape";
    			break;
    		case 13662:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65507;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65508;
    			itemDef.femaleEquip1 = 65508;
    			itemDef.stackable = false;
    			itemDef.name = "master farming cape";
    			itemDef.description = "Master farming cape";
    			break;
    		case 13664:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65509;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65510;
    			itemDef.femaleEquip1 = 65510;
    			itemDef.stackable = false;
    			itemDef.name = "master firemaking cape";
    			itemDef.description = "Master firemaking cape";
    			break;
    		case 13665:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65511;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65512;
    			itemDef.femaleEquip1 = 65512;
    			itemDef.stackable = false;
    			itemDef.name = "master fishing cape";
    			itemDef.description = "Master fishing cape";
    			break;
    		case 13666:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65513;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65514;
    			itemDef.femaleEquip1 = 65514;
    			itemDef.stackable = false;
    			itemDef.name = "master fletching cape";
    			itemDef.description = "Master fletching cape";
    			break;
    		case 13667:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65515;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65516;
    			itemDef.femaleEquip1 = 65516;
    			itemDef.stackable = false;
    			itemDef.name = "master herblore cape";
    			itemDef.description = "Master herblore cape";
    			break;
    		case 13668:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65517;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65518;
    			itemDef.femaleEquip1 = 65518;
    			itemDef.stackable = false;
    			itemDef.name = "master hitpoints cape";
    			itemDef.description = "Master hitpoints cape";
    			break;
    		case 13669:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65519;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65520;
    			itemDef.femaleEquip1 = 65520;
    			itemDef.stackable = false;
    			itemDef.name = "master hunter cape";
    			itemDef.description = "Master hunter cape";
    			break;
    		case 13670:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65521;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65522;
    			itemDef.femaleEquip1 = 65522;
    			itemDef.stackable = false;
    			itemDef.name = "master magic cape";
    			itemDef.description = "Master magic cape";
    			break;
    		case 13671:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65523;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65524;
    			itemDef.femaleEquip1 = 65524;
    			itemDef.stackable = false;
    			itemDef.name = "master mining cape";
    			itemDef.description = "Master mining cape";
    			break;
    		case 13672:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65525;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65526;
    			itemDef.femaleEquip1 = 65526;
    			itemDef.stackable = false;
    			itemDef.name = "master prayer cape";
    			itemDef.description = "Master prayer cape";
    			break;
    		case 13673:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65527;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65528;
    			itemDef.femaleEquip1 = 65528;
    			itemDef.stackable = false;
    			itemDef.name = "master ranged cape";
    			itemDef.description = "Master ranged cape";
    			break;
    		case 13674:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65529;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65530;
    			itemDef.femaleEquip1 = 65530;
    			itemDef.stackable = false;
    			itemDef.name = "master runecrafting cape";
    			itemDef.description = "Master runecrafting cape";
    			break;
    		case 13675:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65531;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65532;
    			itemDef.femaleEquip1 = 65532;
    			itemDef.stackable = false;
    			itemDef.name = "master slayer cape";
    			itemDef.description = "Master slayer cape";
    			break;
    		case 13676:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65533;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 65534;
    			itemDef.femaleEquip1 = 65534;
    			itemDef.stackable = false;
    			itemDef.name = "master smithing cape";
    			itemDef.description = "Master smithing cape";
    			break;
    		case 13690:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 65535;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 64536;
    			itemDef.femaleEquip1 = 64536;
    			itemDef.stackable = false;
    			itemDef.name = "Master strength cape";
    			itemDef.description = "Master strength cape";
    			break;
    		case 13678:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 64537;
    			itemDef.modelZoom = 2203;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 64538;
    			itemDef.femaleEquip1 = 64538;
    			itemDef.stackable = false;
    			itemDef.name = "master summoning cape";
    			itemDef.description = "Master summoning cape";
    			break;
    		case 13679:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 64539;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 64540;
    			itemDef.femaleEquip1 = 64540;
    			itemDef.stackable = false;
    			itemDef.name = "master thieving cape";
    			itemDef.description = "Master thieving cape";
    			break;
    		case 13680:
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wield";
    			itemDef.modelID = 64541;
    			itemDef.modelZoom = 2200;
    			itemDef.rotationY = 504;
    			itemDef.rotationX = 1000;
    			itemDef.modelOffset1 = 30;
    			itemDef.modelOffsetY = 35;
    			itemDef.maleEquip1 = 64542;
    			itemDef.femaleEquip1 = 64542;
    			itemDef.stackable = false;
    			itemDef.name = "master woodcutting cape";
    			itemDef.description = "Master woodcutting cape";
    			break;
    		case 20791:
    			itemDef.modelID = 35130;
    			itemDef.name = "Golden mining top";
    			itemDef.modelZoom = 1360;
    			itemDef.rotationX = 609;
    			itemDef.rotationY = 0;
    			itemDef.modelOffset1 = 0;
    			itemDef.modelOffsetY = -1;
    			itemDef.maleEquip1 = 35131;
    			itemDef.femaleEquip1 = 351329;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    		break;
            case 15010:
                itemDef.modelID = 2429;
                itemDef.name = "Gold Ring";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 15009;
                itemDef.certTemplateID = 799;
                break;
            //start osrs pets
            case 13321:
                itemDef.name = "Rock Golem";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2640;
                itemDef.rotationX = 1829;
                itemDef.rotationY = 42;
                itemDef.modelID = 29755;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 13320:
                itemDef.name = "Heron";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 1757;
                itemDef.rotationY = 381;
                itemDef.modelID = 29756;
                itemDef.modelOffsetX = 10;
                itemDef.modelOffset1 = 25;
                break;
            case 13322:
                itemDef.name = "Beaver";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.modelID = 29754;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 13323:
                itemDef.name = "Tangleroot";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.modelID = 32202;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 13324:
                itemDef.name = "Rocky";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.modelID = 32203;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 13325:
                itemDef.name = "Giant squirrel";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.modelID = 32205;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 13326:
                itemDef.name = "Rift Guardian";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1872;
                itemDef.rotationX = 333;
                itemDef.rotationY = 195;
                itemDef.modelID = 32204;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 13327:
                itemDef.name = "Olmlet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 968;
                itemDef.rotationX = 1778;
                itemDef.rotationY = 67;
                itemDef.modelID = 32798;
                itemDef.modelOffsetY = 16;
                itemDef.modelOffset1 = 1;
                break;
            //end

            case 11884:
                itemDef.actions = new String[]{"Open", null, null, null, null, null};
                break;

            /**
             * Flasks
             */
            case 14136:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super antipoison flask (4)";
                itemDef.description = "4 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14134:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super antipoison flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14132:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super antipoison flask (2)";
                itemDef.stackable = false;
                itemDef.description = "2 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14130:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super antipoison flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14428:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14427;
                itemDef.certTemplateID = 799;
                break;
            case 14427:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (6)";
                itemDef.stackable = false;
                itemDef.description = "6 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14426:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14425;
                itemDef.certTemplateID = 799;
                break;
            case 14425:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (5)";
                itemDef.stackable = false;
                itemDef.description = "5 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14424:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14423;
                itemDef.certTemplateID = 799;
                break;
            case 14423:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (4)";
                itemDef.stackable = false;
                itemDef.description = "4 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14422:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14421;
                itemDef.certTemplateID = 799;
                break;
            case 14421:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14420:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14419;
                itemDef.certTemplateID = 799;
                break;
            case 14419:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (2)";
                itemDef.stackable = false;
                itemDef.description = "2 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14418:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14417;
                itemDef.certTemplateID = 799;
                break;
            case 14417:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14416:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14415;
                itemDef.certTemplateID = 799;
                break;
            case 14415:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (6)";
                itemDef.stackable = false;
                itemDef.description = "6 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14414:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14413;
                itemDef.certTemplateID = 799;
                break;
            case 14413:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (5)";
                itemDef.stackable = false;
                itemDef.description = "5 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14412:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14411;
                itemDef.certTemplateID = 799;
                break;
            case 14411:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (4)";
                itemDef.stackable = false;
                itemDef.description = "4 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14410:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14409;
                itemDef.certTemplateID = 799;
                break;
            case 14409:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14408:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14407;
                itemDef.certTemplateID = 799;
                break;
            case 14407:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (2)";
                itemDef.stackable = false;
                itemDef.description = "2 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14406:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14405;
                itemDef.certTemplateID = 799;
                break;
            case 14405:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super restore flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14404:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14403;
                itemDef.certTemplateID = 799;
                break;
            case 14403:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Magic flask (6)";
                itemDef.description = "6 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 3046:
            case 3044:
            case 3042:
            case 3040:
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{61};
                break;
            case 14402:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14401;
                itemDef.certTemplateID = 799;
                break;
            case 14401:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Magic flask (5)";
                itemDef.stackable = false;
                itemDef.description = "5 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14400:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14399;
                itemDef.certTemplateID = 799;
                break;
            case 14399:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Magic flask (4)";
                itemDef.stackable = false;
                itemDef.description = "4 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14398:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14397;
                itemDef.certTemplateID = 799;
                break;
            case 14397:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Magic flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14396:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14395;
                itemDef.certTemplateID = 799;
                break;
            case 14395:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Magic flask (2)";
                itemDef.description = "2 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14394:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14393;
                itemDef.certTemplateID = 799;
                break;
            case 14393:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Magic flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{2524};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14385:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Recover special flask (6)";
                itemDef.stackable = false;
                itemDef.description = "6 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14383:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Recover special flask (5)";
                itemDef.stackable = false;
                itemDef.description = "5 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14381:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Recover special flask (4)";
                itemDef.description = "4 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14379:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Recover special flask (3)";
                itemDef.description = "3 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14377:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Recover special flask (2)";
                itemDef.description = "2 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14375:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Recover special flask (1)";
                itemDef.description = "1 dose of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14373:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (6)";
                itemDef.description = "6 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14371:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (5)";
                itemDef.description = "5 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14369:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (4)";
                itemDef.description = "4 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14367:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (3)";
                itemDef.description = "3 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14365:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (2)";
                itemDef.description = "2 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14363:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme attack flask (1)";
                itemDef.description = "1 dose of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14361:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (6)";
                itemDef.description = "6 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14359:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (5)";
                itemDef.description = "5 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14357:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (4)";
                itemDef.description = "4 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14355:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (3)";
                itemDef.description = "3 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14353:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (2)";
                itemDef.description = "2 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14351:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme strength flask (1)";
                itemDef.description = "1 dose of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14349:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (6)";
                itemDef.description = "6 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14347:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (5)";
                itemDef.description = "5 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14345:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (4)";
                itemDef.description = "4 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14343:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme defence flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14341:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (2)";
                itemDef.description = "2 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14339:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme defence flask (1)";
                itemDef.description = "1 dose of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14337:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (6)";
                itemDef.description = "6 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14335:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (5)";
                itemDef.description = "5 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14333:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (4)";
                itemDef.description = "4 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14331:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (3)";
                itemDef.stackable = false;
                itemDef.description = "3 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14329:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Extreme magic flask (2)";
                itemDef.stackable = false;
                itemDef.description = "2 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14327:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme magic flask (1)";
                itemDef.stackable = false;
                itemDef.description = "1 dose of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14325:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (6)";
                itemDef.stackable = false;
                itemDef.description = "6 doses of extreme ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14323:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (5)";
                itemDef.description = "5 doses of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14321:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (4)";
                itemDef.description = "4 doses of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14319:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (3)";
                itemDef.description = " 3 doses of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14317:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (2)";
                itemDef.description = "2 doses of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14315:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Extreme ranging flask (1)";
                itemDef.description = "1 dose of extreme ranging potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14313:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (6)";
                itemDef.description = "6 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14311:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (5)";
                itemDef.description = "5 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14309:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (4)";
                itemDef.description = "4 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14307:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (3)";
                itemDef.description = "3 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14305:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (2)";
                itemDef.description = "2 doses of super prayer potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14303:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Super prayer flask (1)";
                itemDef.description = "1 dose of super prayer potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14301:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (6)";
                itemDef.description = "6 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14299:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (5)";
                itemDef.description = "5 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14297:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (4)";
                itemDef.description = "4 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14295:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (3)";
                itemDef.description = "3 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14293:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (2)";
                itemDef.description = "2 doses of overload potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14291:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Overload flask (1)";
                itemDef.description = "1 dose of overload potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.groundActions[2] = "Take";

                itemDef.modelID = 61812;
                break;
            case 14289:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (6)";
                itemDef.description = "6 doses of prayer renewal.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14287:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (5)";
                itemDef.description = "5 doses of prayer renewal.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 15123:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (4)";
                itemDef.description = "4 doses of prayer renewal potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 15121:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (3)";
                itemDef.description = "3 doses of prayer renewal potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 15119:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (2)";
                itemDef.description = "2 doses of prayer renewal potion.";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 7340:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Prayer renewal flask (1)";
                itemDef.description = "1 dose of prayer renewal potion";
                itemDef.stackable = false;
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14196:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Prayer flask (4)";
                itemDef.description = "4 doses of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14194:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Prayer flask (3)";
                itemDef.description = "3 doses of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14192:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Prayer flask (2)";
                itemDef.description = "2 doses of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14190:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Prayer flask (1)";
                itemDef.description = "1 dose of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14189:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14188;
                itemDef.certTemplateID = 799;
                break;
            case 14188:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (6)";
                itemDef.description = "6 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14187:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14186;
                itemDef.certTemplateID = 799;
                break;
            case 14186:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (5)";
                itemDef.description = "5 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14185:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14184;
                itemDef.certTemplateID = 799;
                break;
            case 14184:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (4)";
                itemDef.description = "4 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14183:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14182;
                itemDef.certTemplateID = 799;
                break;
            case 14182:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (3)";
                itemDef.description = "3 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14181:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14180;
                itemDef.certTemplateID = 799;
                break;
            case 14180:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (2)";
                itemDef.description = "2 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14179:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14178;
                itemDef.certTemplateID = 799;
                break;
            case 14178:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super attack flask (1)";
                itemDef.description = "1 dose of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14177:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14176;
                itemDef.certTemplateID = 799;
                break;
            case 14176:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (6)";
                itemDef.description = "6 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14175:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14174;
                itemDef.certTemplateID = 799;
                break;
            case 14174:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (5)";
                itemDef.description = "5 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14173:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14172;
                itemDef.certTemplateID = 799;
                break;
            case 14172:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (4)";
                itemDef.description = "4 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14171:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14170;
                itemDef.certTemplateID = 799;
                break;
            case 14170:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (3)";
                itemDef.description = "3 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14169:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14168;
                itemDef.certTemplateID = 799;
                break;
            case 14168:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (2)";
                itemDef.description = "2 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14167:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14166;
                itemDef.certTemplateID = 799;
                break;
            case 14166:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super strength flask (1)";
                itemDef.description = "1 dose of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14164:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (6)";
                itemDef.description = "6 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14162:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (5)";
                itemDef.description = "5 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14160:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (4)";
                itemDef.description = "4 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61764;
                break;
            case 14158:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (3)";
                itemDef.description = "3 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14156:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (2)";
                itemDef.description = "2 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14154:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super defence flask (1)";
                itemDef.description = "1 dose of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14153:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14152;
                itemDef.certTemplateID = 799;
                break;
            case 14152:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (6)";
                itemDef.description = "6 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14151:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14150;
                itemDef.certTemplateID = 799;
                break;
            case 14150:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (5)";
                itemDef.description = "5 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 14149:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14148;
                itemDef.certTemplateID = 799;
                break;
            case 14148:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (4)";
                itemDef.description = "4 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.groundActions[2] = "Take";
                itemDef.modelID = 61764;
                break;
            case 14147:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14146;
                itemDef.certTemplateID = 799;
                break;
            case 14146:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (3)";
                itemDef.description = "3 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61727;
                break;
            case 14145:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14144;
                itemDef.certTemplateID = 799;
                break;
            case 14144:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (2)";
                itemDef.description = "2 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61731;
                break;
            case 14143:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14142;
                itemDef.certTemplateID = 799;
                break;
            case 14142:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Ranging flask (1)";
                itemDef.description = "1 dose of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61812;
                break;
            case 14140:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super antipoison flask (6)";
                itemDef.description = "6 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                break;
            case 14138:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.name = "Super antipoison flask (5)";
                itemDef.description = "5 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61729;
                break;
            case 4705:
                itemDef.modelID = 6701;
                itemDef.name = "Abyssal vine whip";
                itemDef.description = "A weapon from the Abyss, interlaced with a vicious jade vine.";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1808;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 3;
                itemDef.maleEquip1 = 6700;
                itemDef.femaleEquip1 = 6700;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                break;
            case 15262:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.actions[2] = "Open-All";
                break;
            case 6570:
                itemDef.actions[2] = "Upgrade";
                break;
            case 4155:
                itemDef.name = "Slayer gem";
                itemDef.actions = new String[]{"Activate", null, "Social-Slayer", null, "Destroy"};
                break;
            case 13663:
                itemDef.name = "Stat reset cert.";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Open";
                break;
            case 13653:
                itemDef.name = "Energy fragment";
                break;
            case 292:
                itemDef.name = "Ingredients book";
                break;
            case 15707:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[0] = "Create Party";
                break;
        /*    case 14044:
                itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.editedModelColor = new int[1];
    			itemDef.newModelColor = new int[1];
    			itemDef.editedModelColor[0] = 926;
    			itemDef.newModelColor[0] = 0;
    			itemDef.modelID = 2635;
    			itemDef.modelZoom = 440;
    			itemDef.rotationX = 76;
    			itemDef.rotationY = 1850;
    			
    			itemDef.modelOffsetX = 1;
    			itemDef.modelOffsetY = 1;
    			itemDef.maleEquip1 = 187;
    			itemDef.femaleEquip1 = 363;
    			itemDef.anInt175 = 29;
    			itemDef.stackable = false;
    			itemDef.anInt197 = 87;
    			itemDef.name = "Black Party Hat";
    			itemDef.description = "A Party Hat.";
    			break; */
            case 14044:
                itemDef.name = "Black Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare black party hat";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 0;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14045:
                itemDef.name = "Lime Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare lime party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 17350;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14046:
                itemDef.name = "Pink Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare pink party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 57300;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14047:
                itemDef.name = "Sky Blue Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare sky blue party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 689484;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14048:
                itemDef.name = "Lava Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare lava party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14049:
                itemDef.name = "Pink Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare pink santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 57300;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14050:
                itemDef.name = "Black Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare black santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 0;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14051:
                itemDef.name = "Lime Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare lime santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 17350;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14052:
                itemDef.name = "Lava Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare lava santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14053:
                itemDef.name = "Lava Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare lava santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 15152:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 17350;
                itemDef.modelID = 2635;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 76;
                itemDef.rotationY = 1850;

                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;

                itemDef.femaleEquip1 = 363;

                itemDef.name = "Lime Party Hat";
                itemDef.description = "A Party Hat.";
            case 14501:
                itemDef.modelID = 44574;
                itemDef.maleEquip1 = 43693;
                itemDef.femaleEquip1 = 43693;
                break;
            case 19111:
                itemDef.name = "TokHaar-Kal";
                itemDef.value = 60000;
                itemDef.maleEquip1 = 62575;
                itemDef.femaleEquip1 = 62582;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.modelOffset1 = -4;
                itemDef.modelID = 62592;
                itemDef.stackable = false;
                itemDef.description = "A cape made of ancient, enchanted rocks.";
                itemDef.modelZoom = 1616;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelOffset1 = 0;
                itemDef.rotationY = 339;
                itemDef.rotationX = 192;
                break;
            case 13262:
                itemDef2 = ItemDefinition.forID(20072);
                itemDef.modelID = itemDef2.modelID;
                itemDef.maleEquip1 = itemDef2.maleEquip1;
                itemDef.femaleEquip1 = itemDef2.femaleEquip1;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.rotationY = itemDef2.rotationY;
                itemDef.rotationX = itemDef2.rotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.name = itemDef2.name;
                itemDef.actions = itemDef2.actions;
                break;
            case 10942:
                itemDef.name = "$10 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 10934:
                itemDef.name = "$20 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 10935:
                itemDef.name = "$50 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 10943:
                itemDef.name = "$100 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 995:
                itemDef.name = "Coins";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[3] = "Add-to-pouch";
                break;
            case 17291:
                itemDef.name = "Blood necklace";
                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                break;
            case 20084:
                itemDef.name = "Golden Maul";
                break;
            case 6199:
                itemDef.name = "Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 15501:
                itemDef.name = "Legendary Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 6568: // To replace Transparent black with opaque black.
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 996:
            case 997:
            case 998:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            case 1003:
            case 1004:
                itemDef.name = "Coins";
                break;

            case 14017:
                itemDef.name = "Brackish blade";
                itemDef.modelZoom = 1488;
                itemDef.rotationY = 276;
                itemDef.rotationX = 1580;
                itemDef.modelOffsetY = 1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Drop"};
                itemDef.modelID = 64593;
                itemDef.maleEquip1 = 64704;
                itemDef.femaleEquip2 = 64704;
                break;

            case 15220:
                itemDef.name = "Berserker ring (i)";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1916;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -15;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 7735; // if it doesn't work try 7735
                itemDef.maleEquip1 = -1;
                // itemDefinition.maleArm = -1;
                itemDef.femaleEquip1 = -1;
                // itemDefinition.femaleArm = -1;
                break;

            case 14019:
                itemDef.modelID = 65262;
                itemDef.name = "Max Cape";
                itemDef.description = "A cape worn by those who've achieved 99 in all skills.";
                itemDef.modelZoom = 1385;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65300;
                itemDef.femaleEquip1 = 65322;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Customize";
                itemDef.editedModelColor = new int[4];
                itemDef.newModelColor = new int[4];
                itemDef.editedModelColor[0] = 65214; //red
                itemDef.editedModelColor[1] = 65200; // darker red
                itemDef.editedModelColor[2] = 65186; //dark red
                itemDef.editedModelColor[3] = 62995; //darker red
                itemDef.newModelColor[0] = 65214;//cape
                itemDef.newModelColor[1] = 65200;//cape
                itemDef.newModelColor[2] = 65186;//outline
                itemDef.newModelColor[3] = 62995;//cape
                break;
            case 14020:
                itemDef.name = "Veteran hood";
                itemDef.description = "A hood worn by Chivalry's veterans.";
                itemDef.modelZoom = 760;
                itemDef.rotationY = 11;
                itemDef.rotationX = 81;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 65271;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65314;
                break;
            case 14021:
                itemDef.modelID = 65261;
                itemDef.name = "Veteran Cape";
                itemDef.description = "A cape worn by Chivalry's veterans.";
                itemDef.modelZoom = 760;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65305;
                itemDef.femaleEquip1 = 65318;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 14022:
                itemDef.modelID = 65270;
                itemDef.name = "Completionist Cape";
                itemDef.description = "We'd pat you on the back, but this cape would get in the way.";
                itemDef.modelZoom = 1385;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65297;
                itemDef.femaleEquip1 = 65297;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Customize";
                itemDef.editedModelColor = new int[4];
                itemDef.newModelColor = new int[4];
                itemDef.editedModelColor[0] = 65214; //red
                itemDef.editedModelColor[1] = 65200; // darker red
                itemDef.editedModelColor[2] = 65186; //dark red
                itemDef.editedModelColor[3] = 62995; //darker red
                itemDef.newModelColor[0] = 65214;//cape
                itemDef.newModelColor[1] = 65200;//cape
                itemDef.newModelColor[2] = 65186;//outline
                itemDef.newModelColor[3] = 62995;//cape
                break;
            case 9666:
            case 11814:
            case 11816:
            case 11818:
            case 11820:
            case 11822:
            case 11824:
            case 11826:
            case 11828:
            case 11830:
            case 11832:
            case 11834:
            case 11836:
            case 11838:
            case 11840:
            case 11842:
            case 11844:
            case 11846:
            case 11848:
            case 11850:
            case 11852:
            case 11854:
            case 11856:
            case 11858:
            case 11860:
            case 11862:
            case 11864:
            case 11866:
            case 11868:
            case 11870:
            case 11874:
            case 11876:
            case 11878:
            case 11882:
            case 11886:
            case 11890:
            case 11894:
            case 11898:
            case 11902:
            case 11904:
            case 11906:
            case 11928:
            case 11930:
            case 11938:
            case 11942:
            case 11944:
            case 11946:
            case 14525:
            case 14527:
            case 14529:
            case 14531:
            case 19588:
            case 19592:
            case 19596:
            case 11908:
            case 11910:
            case 11912:
            case 11914:
            case 11916:
            case 11618:
            case 11920:
            case 11922:
            case 11960:
            case 11962:

            case 19586:
            case 19584:
            case 19590:
            case 19594:
            case 19598:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;

            case 14004:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 38310;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 38310;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 38310;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 38310;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 38310;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 38310;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 38310;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 38310;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 38310;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 38310;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 38310;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset1 = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14005:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 432;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 432;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 432;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 432;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 432;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 432;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 432;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 432;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 432;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 432;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 432;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset1 = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14006:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 24006;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 24006;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 24006;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 24006;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 24006;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 24006;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 24006;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 24006;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 24006;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 24006;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 24006;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset1 = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14007:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 14285;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 14285;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 14285;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 14285;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 14285;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 14285;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 14285;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 14285;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 14285;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 14285;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 14285;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset1 = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14003:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 30847;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 32895;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 30847;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14001:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 10015;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 7730;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 7973;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14002:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 35489;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 37774;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 35732;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14000:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 3745;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 3982;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 3988;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -5;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                break;

            /*case 19111:
			itemDef.name = "TokHaar-Kal";
			// itemDef.femaleOffset = 0;
			itemDef.value = 60000;
			itemDef.maleEquip1 = 62575;
			itemDef.femaleEquip1 = 62582;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.modelOffset1 = -4;
			itemDef.modelID = 62592;
			itemDef.stackable = false;
			itemDef.description = "A cape made of ancient, enchanted obsidian.";
			itemDef.modelZoom = 2086;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelOffsetY = 0;
			itemDef.rotationY = 533;
			itemDef.rotationX = 333;
			break;
             */
            case 20000:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 53835;
                itemDef.name = "Steadfast boots";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = 53327;
                itemDef.femaleEquip1 = 53643;
                itemDef.description = "A pair of Steadfast boots.";
                break;

            case 20001:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 53828;
                itemDef.name = "Glaiven boots";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
                itemDef.femaleEquip1 = 53309;
                itemDef.maleEquip1 = 53309;
                itemDef.description = "A pair of Glaiven boots.";
                break;

            case 20002:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.description = "A pair of Ragefire boots.";
                itemDef.modelID = 53897;
                itemDef.name = "Ragefire boots";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = 53330;
                itemDef.femaleEquip1 = 53651;
                break;

            case 14018:
                itemDef.modelID = 5324;
                itemDef.name = "Ornate katana";
                itemDef.modelZoom = 1520;
                itemDef.rotationY = 1570;
                itemDef.rotationX = 157;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = 1;
                itemDef.value = 50000;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 5324;
                itemDef.femaleEquip1 = 5324;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Destroy";
                break;
            case 14008:
                itemDef.modelID = 62714;
                itemDef.name = "Torva full helm";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 62738;
                itemDef.femaleEquip1 = 62754;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 14009:
                itemDef.modelID = 62699;
                itemDef.name = "Torva platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 62746;
                itemDef.femaleEquip1 = 62762;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 14010:
                itemDef.modelID = 62701;
                itemDef.name = "Torva platelegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 62743;
                itemDef.femaleEquip1 = 62760;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 14011:
                itemDef.modelID = 62693;
                itemDef.name = "Pernix cowl";
                itemDef.description = "Pernix cowl";
                itemDef.modelZoom = 800;
                itemDef.rotationY = 532;
                itemDef.rotationX = 14;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 62739;
                itemDef.femaleEquip1 = 62756;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62731;
                itemDef.femaleDialogue = 62727;
                itemDef.editedModelColor = new int[2];
                itemDef.newModelColor = new int[2];
                itemDef.editedModelColor[0] = 4550;
                itemDef.newModelColor[0] = 0;
                itemDef.editedModelColor[1] = 4540;
                itemDef.newModelColor[1] = 0;
                break;

            case 14012:
                itemDef.modelID = 62709;
                itemDef.name = "Pernix body";
                itemDef.description = "Pernix body";
                itemDef.modelZoom = 1378;
                itemDef.rotationY = 485;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = 7;
                itemDef.maleEquip1 = 62744;
                itemDef.femaleEquip1 = 62765;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 14013:
                itemDef.modelID = 62695;
                itemDef.name = "Pernix chaps";
                itemDef.description = "Pernix chaps";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 504;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = 3;
                itemDef.maleEquip1 = 62741;
                itemDef.femaleEquip1 = 62757;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 14014:
                itemDef.modelID = 62710;
                itemDef.name = "Virtus mask";
                itemDef.description = "Virtus mask";
                itemDef.modelZoom = 928;
                itemDef.rotationY = 406;
                itemDef.rotationX = 2041;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 62736;
                itemDef.femaleEquip1 = 62755;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62728;
                itemDef.femaleDialogue = 62728;
                break;

            case 14015:
                itemDef.modelID = 62704;
                itemDef.name = "Virtus robe top";
                itemDef.description = "Virtus robe top";
                itemDef.modelZoom = 1122;
                itemDef.rotationY = 488;
                itemDef.rotationX = 3;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 62748;
                itemDef.femaleEquip1 = 62764;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 14016:
                itemDef.modelID = 62700;
                itemDef.name = "Virtus robe legs";
                itemDef.description = "Virtus robe legs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 498;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = 4;
                itemDef.maleEquip1 = 62742;
                itemDef.femaleEquip1 = 62758;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 9924:
            case 9923:
            case 9925:
            case 9921:
            case 9922:
                itemDef.editedModelColor = new int[]{1};
                break;
        }
        return itemDef;
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

    private void readValues(Stream stream) {
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
            } else if (i == 10) {
                stream.readUnsignedWord();
            } else if (i == 11) {
                stackable = true;
            } else if (i == 12) {
                value = stream.readUnsignedWord();
            } else if (i == 16) {
                membersObject = true;
            } else if (i == 23) {
                maleEquip1 = stream.readUnsignedWord();
                maleYOffset = stream.readSignedByte();
            } else if (i == 24) {
                maleEquip2 = stream.readUnsignedWord();
            } else if (i == 25) {
                femaleEquip1 = stream.readUnsignedWord();
                femaleYOffset = stream.readSignedByte();
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
                    editedModelColor[k] = stream.readUnsignedWord();
                    newModelColor[k] = stream.readUnsignedWord();
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
                certID = stream.readUnsignedWord();
            } else if (i == 98) {
                certTemplateID = stream.readUnsignedWord();
            } else if (i >= 100 && i < 110) {
                if (stackIDs == null) {
                    stackIDs = new int[10];
                    stackAmounts = new int[10];
                }
                stackIDs[i - 100] = stream.readUnsignedWord();
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
        char c = itemDef_1.name.charAt(0);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            s = "an";
        }
        description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
        stackable = true;
    }

    private void toLend() {
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
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
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
        if (k == 0 && i != 5572 && i != 5573 && i != 640 && i != 650 && i != 630) {
            spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
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
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.setDefaultBounds();
        int k3 = itemDef.modelZoom;
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
        if (k == 0 && i != 5572 && i != 5573 && i != 640 && i != 650 && i != 630) {
          spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
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
        Model model = (Model) modelCache.get(id);
        if (model != null) {
            return model;
        }
        model = Model.fetchModel(modelID);
        if (model == null) {
            return null;
        }
        if (sizeX != 128 || sizeY != 128 || sizeZ != 128) {
            model.scaleT(sizeX, sizeZ, sizeY);
        }
        if (editedModelColor != null) {
            for (int l = 0; l < editedModelColor.length; l++) {
                model.recolour(editedModelColor[l], newModelColor[l]);
            }
        }
        model.light(64 + shadow, 768 + lightness, -50, -10, -50, true);
        model.rendersWithinOneTile = true;
        if (id != 5572 && id != 5573 && id != 640 && id != 650 && id != 630) {
        modelCache.put(model, id);
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
        Model model = Model.fetchModel(modelID);
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

    public static final int[] UNTRADEABLE_ITEMS
            = {13661, 13262,
            6529, 6950, 1464, 2996, 2677, 2678, 2679, 2680, 2682,
            2683, 2684, 2685, 2686, 2687, 2688, 2689, 2690,
            6570, 12158, 12159, 12160, 12163, 12161, 12162,
            19143, 19149, 19146, 19157, 19162, 19152, 4155,
            8850, 10551, 8839, 8840, 8842, 11663, 11664,
            11665, 3842, 3844, 3840, 8844, 8845, 8846, 8847,
            8848, 8849, 8850, 10551, 7462, 7461, 7460,
            7459, 7458, 7457, 7456, 7455, 7454, 7453, 8839,
            8840, 8842, 11663, 11664, 11665, 10499, 9748,
            9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808,
            9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772,
            9778, 9787, 9811, 9766, 9749, 9755, 9752, 9770,
            9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806,
            9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812,
            9767, 9747, 9753, 9750, 9768, 9756, 9759, 9762,
            9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792,
            9774, 9771, 9777, 9786, 9810, 9765, 9948, 9949,
            9950, 12169, 12170, 12171, 20671, 14641, 14642,
            6188, 10954, 10956, 10958,
            3057, 3058, 3059, 3060, 3061,
            7594, 7592, 7593, 7595, 7596,
            14076, 14077, 14081,
            10840, 10836, 6858, 6859, 10837, 10838, 10839,
            9925, 9924, 9923, 9922, 9921,
            4084, 4565, 20046, 20044, 20045,
            1050, 14595, 14603, 14602, 14605, 11789,
            19708, 19706, 19707,
            4860, 4866, 4872, 4878, 4884, 4896, 4890, 4896, 4902,
            4932, 4938, 4944, 4950, 4908, 4914, 4920, 4926, 4956,
            4926, 4968, 4994, 4980, 4986, 4992, 4998,
            18778, 18779, 18780, 18781,
            13450, 13444, 13405, 15502,
            10548, 10549, 10550, 10551, 10555, 10552, 10553, 2412, 2413, 2414,
            20747,
            18365, 18373, 18371, 15246, 12964, 12971, 12978, 14017,
            757, 8851,
            13855, 13848, 13857, 13856, 13854, 13853, 13852, 13851, 13850, 5509, 13653, 14021, 14020, 19111, 14019, 14022,
            19785, 19786, 18782, 18351, 18349, 18353, 18357, 18355, 18359, 18335
    };

    public ItemDefinition() {
        id = -1;
    }

    public byte femaleYOffset;
    public int value;
    public int[] editedModelColor;
    public int id;
    public static MemCache spriteCache = new MemCache(100);
    public static MemCache modelCache = new MemCache(50);
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
    public int femaleDialogueModel;
    public int modelID;
    public int maleDialogue;
    public boolean stackable;
    public String description;
    public int certID;
    public static int cacheIndex;
    public int modelZoom;
    public static Stream stream;
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
    public int shadow;
    public int femaleDialogue;
    public int rotationX;
    public int femaleEquip1;
    public int[] stackAmounts;
    public int team;
    public static int totalItems;
    public int modelOffsetX;
    public byte maleYOffset;
    public byte maleXOffset;
    public int lendID;
    public int lentItemID;
    public boolean untradeable;
}