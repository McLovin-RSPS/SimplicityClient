package com.simplicity.client.cache.definitions.custom;

import com.simplicity.client.RandomColor;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.client.cache.definitions.MobDefinition;

public class CustomItems2 {
	
	public static void loadDefinition(ItemDefinition itemDef) {
		switch (itemDef.id) {
	    case 15426:
	    case 52719:
	    	itemDef.femaleEquip1 = itemDef.maleEquip1;
	    	itemDef.femaleYOffset = -14;
	    	itemDef.femaleXOffset = 6;
	    	break;
        case 50997:
	    	itemDef.femaleYOffset = -14;
	    	itemDef.femaleXOffset = 8;
            break;
	    case 21040:
	        itemDef.copy(ItemDefinition.forID(50997));
	        itemDef.actions[1] = "Wear";
	        itemDef.actions[4] = "Drop";
	        itemDef.name = "Sirenic twisted bow";
	    	itemDef.femaleYOffset = -14;
	    	itemDef.femaleXOffset = 6;
	        itemDef.description = "A mystical bow carved from the twisted remains of higher dimensions.";
	        itemDef.editedModelColor = new int[] { 16, 24, 33, 43223, 44236 };
	        itemDef.newModelColor = new int[] { 90, 90, 90, 90, 90 };
	        break;
	        case 11154:
	            itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
	            break;
	        case 4964:
	        case 4965:
	        case 4966:
	        case 4967:
	            String name = itemDef.name;
	            itemDef.copy(ItemDefinition.forID(4749));
	            itemDef.name = name;
	            break;
	        case 18351:
	            itemDef.femaleYOffset = -10;
	            itemDef.femaleXOffset = 5;
	            itemDef.femaleZOffset = 5;
	            break;
	
	        case 18346:
	            itemDef.femaleEquip1 = itemDef.maleEquip1;
	            break;
	
	        case 22000:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.name = "Masterwork full helm";
	            itemDef.maleEquip1 = 2;
	            itemDef.femaleEquip1 = 2;
	            itemDef.modelID = 1;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.rotationY = 50;
	            break;
	
	        case 22001:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.name = "Masterwork platebody";
	            itemDef.maleEquip1 = 4;
	            itemDef.femaleEquip1 = 4;
	            itemDef.modelID = 3;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            break;
	
	        case 22002:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.name = "Masterwork platelegs";
	            itemDef.maleEquip1 = 6;
	            itemDef.femaleEquip1 = 6;
	            itemDef.modelID = 5;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom = 1800;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            break;
	
	        case 22003:
	            itemDef.copy(ItemDefinition.forID(11732));
	            itemDef.name = "Masterwork boots";
	            itemDef.maleEquip1 = 7;
	            itemDef.femaleEquip1 = 7;
	            itemDef.modelID = 7;
	            itemDef.modelZoom = 890;
	            itemDef.rotationX = 320;
	            itemDef.rotationY = 290;
	            itemDef.modelOffsetX = 100;
	            itemDef.modelOffset1 = 5;
	            itemDef.modelOffsetY = 15;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22004:
	            itemDef.copy(ItemDefinition.forID(7462));
	            itemDef.name = "Masterwork gloves";
	            itemDef.maleEquip1 = 9;
	            itemDef.femaleEquip1 = 9;
	            itemDef.modelID = 8;
	            itemDef.modelZoom = 700;
	            itemDef.modelOffset1 = 5;
	            itemDef.modelOffsetX = 180;
	            itemDef.modelOffsetY = 30;
	            itemDef.rotationX = 390;
	            itemDef.rotationY = 290;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22005:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.modelID = 10;
	            itemDef.maleEquip1 = 11;
	            itemDef.femaleEquip1 = 11;
	            itemDef.editedModelColor = new int[] { 70 };
	            itemDef.newModelColor = new int[] { 58 };
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22006:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.modelID = 14;
	            itemDef.maleEquip1 = 15;
	            itemDef.femaleEquip1 = 15;
	            itemDef.editedModelColor = new int[] { 70 };
	            itemDef.newModelColor = new int[] { 58 };
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22007:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.modelID = 12;
	            itemDef.maleEquip1 = 13;
	            itemDef.femaleEquip1 = 13;
	            itemDef.editedModelColor = new int[] { 70 };
	            itemDef.newModelColor = new int[] { 58 };
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22008:
	            itemDef.copy(ItemDefinition.forID(15000));
	            itemDef.name = "@red@Scythe of Vitur DSI";
	            itemDef.modelID = 17;
	            itemDef.maleEquip1 = 16;
	            itemDef.femaleEquip1 = 16;
	            itemDef.rotationX = 0;
	            itemDef.rotationY = 0;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = 0;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22009:
	            itemDef.copy(ItemDefinition.forID(15000));
	            itemDef.name = "Scythe of Vitur Rainbow";
	            itemDef.modelID = 19;
	            itemDef.maleEquip1 = 18;
	            itemDef.femaleEquip1 = 18;
	            itemDef.rotationX = 0;
	            itemDef.rotationY = 0;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = 0;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[] { 69 };
	            itemDef.newModelColor = new int[] { 70 };
	            break;
	
	        case 22010: // SOV DS Custom Hellraty
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Scythe of Vitur DS";
	            itemDef.description = "It is the Scythe of Vitur Dragonstone (white).";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 90, 90, 90, 90, 90, 90, 90 };
	            break;
	
	        case 17860:
	            itemDef.copy(ItemDefinition.forID(53951));
	            itemDef.name = "Halloween key";
	            itemDef.editedModelColor = new int[] { 32995, 7227, };
	            itemDef.newModelColor = new int[] { 796, 536, };
	            break;
	            
	        case 22100:
	            itemDef.copy(ItemDefinition.forID(53951));
	            itemDef.name = "@cya@Executive key";
	            itemDef.editedModelColor = new int[] { 32995, 7227, };
	            itemDef.newModelColor = new int[] { 689484, 689484, };
	            itemDef.actions = new String[5];
	            itemDef.actions[2] = "Teleport";
	            break;
	
	        case 22011: // SOV DS Inferno textured Hellraty
	            itemDef.copy(ItemDefinition.forID(15000));
	            itemDef.name = "@red@Scythe of Vitur DSI";
	            itemDef.modelID = 17;
	            itemDef.maleEquip1 = 16;
	            itemDef.femaleEquip1 = 16;
	            itemDef.rotationX = 1925;
	            itemDef.rotationY = 1260;
	            itemDef.modelZoom = 3200;
	            itemDef.modelOffset1 = 27;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = 28;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22012:
	            itemDef.copy(ItemDefinition.forID(51295));
	            itemDef.name = "Rainbow Infernal cape";
	            itemDef.editedModelColor = new int[] { 59 };
	            itemDef.newModelColor = new int[] { 71 };
	            break;
	
	        case 22035:
	            itemDef.copy(ItemDefinition.forID(51295));
	            itemDef.modelID = 49;
	            itemDef.maleEquip1 = 50;
	            itemDef.femaleEquip1 = 50;
	            itemDef.modelZoom = 2500;
	            itemDef.rotationX = 0;
	            itemDef.rotationY = 520;
	            itemDef.modelOffsetY = 9;
	            itemDef.actions = new String[] { null, "Wield", null, null, null };
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.name = "Cursed Reaver wings";
	            break;
	
	        case 22014:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.name = "Elite sirenic helm";
	            itemDef.maleEquip1 = 21;
	            itemDef.femaleEquip1 = 21;
	            itemDef.modelID = 20;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.rotationY = 50;
	            break;
	
	        case 22016:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.name = "Elite sirenic platebody";
	            itemDef.maleEquip1 = 23;
	            itemDef.femaleEquip1 = 23;
	            itemDef.modelID = 22;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.femaleZOffset = 5;
	            itemDef.femaleXScale = 120;
	            itemDef.femaleYScale = 130;
	            itemDef.femaleZScale = 105;
	            break;
	
	        case 22018:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.name = "Elite sirenic platelegs";
	            itemDef.maleEquip1 = 25;
	            itemDef.femaleEquip1 = 25;
	            itemDef.modelID = 24;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom = 1800;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.femaleYOffset = -7;
	            itemDef.femaleZOffset = 4;
	            itemDef.femaleXScale = 108;
	            itemDef.femaleYScale = 131;
	            itemDef.femaleZScale = 115;
	            //model_4.scale2(108, 130, 115);
	            break;
	
	        case 22020:
	            itemDef.copy(ItemDefinition.forID(11732));
	            itemDef.name = "Elite sirenic boots";
	            itemDef.maleEquip1 = 28;
	            itemDef.femaleEquip1 = 28;
	            itemDef.modelID = 28;
	            itemDef.modelZoom = 890;
	            itemDef.rotationX = 320;
	            itemDef.rotationY = 290;
	            itemDef.modelOffsetX = 100;
	            itemDef.modelOffset1 = 5;
	            itemDef.modelOffsetY = 15;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22022:
	            itemDef.copy(ItemDefinition.forID(7462));
	            itemDef.name = "Elite sirenic gloves";
	            itemDef.maleEquip1 = 27;
	            itemDef.femaleEquip1 = 27;
	            itemDef.modelID = 26;
	            itemDef.modelZoom = 700;
	            itemDef.modelOffset1 = 5;
	            itemDef.modelOffsetX = 180;
	            itemDef.modelOffsetY = 30;
	            itemDef.rotationX = 390;
	            itemDef.rotationY = 290;
	            itemDef.femaleXScale = 108;
	            itemDef.femaleYScale = 148;
	            itemDef.femaleZScale = 135;
	            itemDef.femaleYOffset = -2;
	            itemDef.femaleZOffset = 5;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22036:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.name = "Elite tectonic helm";
	            itemDef.maleEquip1 = 31;
	            itemDef.femaleEquip1 = 31;
	            itemDef.modelID = 30;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.rotationY = 50;
	            itemDef.modelZoom += 200;
	            break;
	
	        case 22037:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.name = "Elite tectonic platebody";
	            itemDef.maleEquip1 = 33;
	            itemDef.femaleEquip1 = 33;
	            itemDef.modelID = 32;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 30;
	            break;
	
	        case 22038:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.name = "Elite tectonic platelegs";
	            itemDef.maleEquip1 = 35;
	            itemDef.femaleEquip1 = 35;
	            itemDef.modelID = 34;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom = 1800;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 100;
	            break;
	
	        case 22039:
	            itemDef.copy(ItemDefinition.forID(11732));
	            itemDef.name = "Elite tectonic boots";
	            itemDef.maleEquip1 = 38;
	            itemDef.femaleEquip1 = 38;
	            itemDef.modelID = 38;
	            itemDef.modelZoom = 890;
	            itemDef.rotationX = 320;
	            itemDef.rotationY = 290;
	            itemDef.modelOffsetX = 100;
	            itemDef.modelOffset1 = 5;
	            itemDef.modelOffsetY = 15;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22040:
	            itemDef.copy(ItemDefinition.forID(7462));
	            itemDef.name = "Elite tectonic gloves";
	            itemDef.maleEquip1 = 37;
	            itemDef.femaleEquip1 = 37;
	            itemDef.modelID = 36;
	            itemDef.modelZoom = 700;
	            itemDef.modelOffset1 = 5;
	            itemDef.modelOffsetX = 180;
	            itemDef.modelOffsetY = 30;
	            itemDef.rotationX = 390;
	            itemDef.rotationY = 290;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22041:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.name = "Tekton's helm";
	            itemDef.maleEquip1 = 39;
	            itemDef.femaleEquip1 = 39;
	            itemDef.modelID = 44;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.rotationY = 50;
	            break;
	
	        case 22042:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.name = "Tekton's platebody";
	            itemDef.maleEquip1 = 40;
	            itemDef.femaleEquip1 = 40;
	            itemDef.modelID = 45;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            break;
	
	        case 22043:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.name = "Tekton's platelegs";
	            itemDef.maleEquip1 = 41;
	            itemDef.femaleEquip1 = 41;
	            itemDef.modelID = 46;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom = 1800;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            break;
	
	        case 22044:
	            itemDef.copy(ItemDefinition.forID(11732));
	            itemDef.name = "Tekton's boots";
	            itemDef.maleEquip1 = 43;
	            itemDef.femaleEquip1 = 43;
	            itemDef.modelID = 48;
	            itemDef.modelZoom = 890;
	            itemDef.rotationX = 320;
	            itemDef.rotationY = 290;
	            itemDef.modelOffsetX = 100;
	            itemDef.modelOffset1 = 5;
	            itemDef.modelOffsetY = 15;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom += 250;
	            break;
	
	        case 22045:
	            itemDef.copy(ItemDefinition.forID(7462));
	            itemDef.name = "Tekton's gloves";
	            itemDef.maleEquip1 = 42;
	            itemDef.femaleEquip1 = 42;
	            itemDef.modelID = 47;
	            itemDef.modelZoom = 750;
	            itemDef.rotationX = 310;
	            itemDef.rotationY = 250;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	            
            case 21223:
	            itemDef.modelID = 59611;
	            itemDef.modelZoom = 5000;
	            itemDef.rotationX = 100;
	            itemDef.rotationY = 30;
	            itemDef.dataType = DataType.REGULAR;
	            itemDef.name = "Warmi";
	            itemDef.groundActions = new String[] { null, null, "Take", null, null };
	            itemDef.actions = new String[] { null, null, null, null, "Drop" };
                break;
	
	        case 22046:
	            itemDef.modelID = 16737;
	            itemDef.modelZoom = 1300;
	            itemDef.rotationX = 100;
	            itemDef.rotationY = 30;
	            itemDef.sizeX /= 5;
	            itemDef.sizeY /= 5;
	            itemDef.sizeZ /= 5;
	            itemDef.dataType = DataType.OLDSCHOOL;
	            itemDef.name = "Pet House";
	            itemDef.groundActions = new String[] { null, null, "Take", null, null };
	            itemDef.actions = new String[] { null, null, null, null, "Drop" };
	            break;
                
	        case 21222: // 
                itemDef.name = "Mini Solak";
	            itemDef.modelID = 16589;
	            itemDef.modelZoom = 1750;
	            itemDef.groundActions = new String[]{null, null, "Take", null, null};
	            itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
	            break;

	            
	        case 22047:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.name = "Necrolord hood";
	            itemDef.maleEquip1 = 52;
	            itemDef.femaleEquip1 = 52;
	            itemDef.modelID = 51;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 50;
	            break;
	
	        case 22048:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.name = "Necrolord robe top";
	            itemDef.maleEquip1 = 54;
	            itemDef.femaleEquip1 = 54;
	            itemDef.modelID = 53;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 30;
	            break;
	
	        case 22049:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.name = "Necrolord robe bottoms";
	            itemDef.maleEquip1 = 56;
	            itemDef.femaleEquip1 = 56;
	            itemDef.modelID = 55;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom = 1800;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 350;
	            break;
	
	        case 22060:
	            itemDef.copy(ItemDefinition.forID(56000));
	            itemDef.maleEquip1 = 57;
	            itemDef.femaleEquip1 = 57;
	            itemDef.modelID = 62;
	            itemDef.editedModelColor = new int[] { 50 };
	            itemDef.newModelColor = new int[] { 59 };
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22061:
	            itemDef.copy(ItemDefinition.forID(56001));
	            itemDef.maleEquip1 = 58;
	            itemDef.femaleEquip1 = 58;
	            itemDef.modelID = 63;
	            itemDef.editedModelColor = new int[] { 50 };
	            itemDef.newModelColor = new int[] { 59 };
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22062:
	            itemDef.copy(ItemDefinition.forID(56002));
	            itemDef.maleEquip1 = 59;
	            itemDef.femaleEquip1 = 59;
	            itemDef.modelID = 64;
	            itemDef.editedModelColor = new int[] { 50 };
	            itemDef.newModelColor = new int[] { 59 };
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22063:
	            itemDef.copy(ItemDefinition.forID(56003));
	            itemDef.maleEquip1 = 60;
	            itemDef.femaleEquip1 = 60;
	            itemDef.modelID = 60;
	            itemDef.editedModelColor = new int[] { 50 };
	            itemDef.newModelColor = new int[] { 59 };
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22064:
	            itemDef.copy(ItemDefinition.forID(56004));
	            itemDef.maleEquip1 = 61;
	            itemDef.femaleEquip1 = 61;
	            itemDef.modelID = 65;
	            itemDef.editedModelColor = new int[] { 50 };
	            itemDef.newModelColor = new int[] { 59 };
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22065:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.name = "Dinosaurhide cowl";
	            itemDef.maleEquip1 = 67;
	            itemDef.femaleEquip1 = 67;
	            itemDef.modelID = 66;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.rotationY = 50;
	            break;
	
	        case 22066:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.name = "Dinosaurhide body";
	            itemDef.maleEquip1 = 69;
	            itemDef.femaleEquip1 = 69;
	            itemDef.modelID = 68;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            break;
	
	        case 22067:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.name = "Dinosaurhide chaps";
	            itemDef.maleEquip1 = 71;
	            itemDef.femaleEquip1 = 71;
	            itemDef.modelID = 70;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom = 1800;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            break;
	
	        case 22068:
	            itemDef.copy(ItemDefinition.forID(7462));
	            itemDef.name = "Dinosaurhide gloves";
	            itemDef.maleEquip1 = 73;
	            itemDef.femaleEquip1 = 73;
	            itemDef.modelID = 72;
	            itemDef.modelZoom = 700;
	            itemDef.modelOffset1 = 5;
	            itemDef.modelOffsetX = 180;
	            itemDef.modelOffsetY = 30;
	            itemDef.rotationX = 390;
	            itemDef.rotationY = 290;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22069:
	            itemDef.copy(ItemDefinition.forID(11732));
	            itemDef.name = "Dinosaurhide boots";
	            itemDef.maleEquip1 = 74;
	            itemDef.femaleEquip1 = 74;
	            itemDef.modelID = 74;
	            itemDef.modelZoom = 890;
	            itemDef.rotationX = 320;
	            itemDef.rotationY = 290;
	            itemDef.modelOffsetX = 100;
	            itemDef.modelOffset1 = 5;
	            itemDef.modelOffsetY = 15;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	
	        case 22070:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.name = "@gre@Necrolord hood";
	            itemDef.maleEquip1 = 76;
	            itemDef.femaleEquip1 = 76;
	            itemDef.modelID = 75;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 50;
	            break;
	
	        case 22071:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.name = "@gre@Necrolord robe top";
	            itemDef.maleEquip1 = 78;
	            itemDef.femaleEquip1 = 78;
	            itemDef.modelID = 77;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 30;
	            break;
	
	        case 22072:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.name = "@gre@Necrolord robe bottoms";
	            itemDef.maleEquip1 = 80;
	            itemDef.femaleEquip1 = 80;
	            itemDef.modelID = 79;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom = 1800;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 350;
	            break;
	
	        case 22073:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.name = "@red@Necrolord hood";
	            itemDef.maleEquip1 = 82;
	            itemDef.femaleEquip1 = 82;
	            itemDef.modelID = 81;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 50;
	            break;
	
	        case 22074:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.name = "@red@Necrolord robe top";
	            itemDef.maleEquip1 = 84;
	            itemDef.femaleEquip1 = 84;
	            itemDef.modelID = 83;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 30;
	            break;
	
	        case 22075:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.name = "@red@Necrolord robe bottoms";
	            itemDef.maleEquip1 = 86;
	            itemDef.femaleEquip1 = 86;
	            itemDef.modelID = 85;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom = 1800;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 350;
	            break;
	
	        case 22076:
	            itemDef.name = "TokHaar-Kal-Ket";
	            itemDef.description = "A cape made of ancient, enchanted obsidian.";
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wear";
	            itemDef.modelID = 90;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.maleEquip1 = 89;
	            itemDef.maleEquip2 = -1;
	            itemDef.maleEquip3 = -1;
	            itemDef.femaleEquip1 = 89;
	            itemDef.femaleEquip2 = -1;
	            itemDef.femaleEquip3 = -1;
	            itemDef.stackable = false;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[] { 1495 };
	            itemDef.newModelColor = new int[] { 40 };
	            break;
	            
	        case 22079:
	            itemDef.copy(ItemDefinition.forID(14008));
	            itemDef.name = "@yel@Necrolord hood";
	            itemDef.maleEquip1 = 100;
	            itemDef.femaleEquip1 = 100;
	            itemDef.modelID = 99;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 50;
	            break;
	
	        case 22080:
	            itemDef.copy(ItemDefinition.forID(14009));
	            itemDef.name = "@yel@Necrolord robe top";
	            itemDef.maleEquip1 = 102;
	            itemDef.femaleEquip1 = 102;
	            itemDef.modelID = 101;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 30;
	            break;
	
	        case 22081:
	            itemDef.copy(ItemDefinition.forID(14010));
	            itemDef.name = "@yel@Necrolord robe bottoms";
	            itemDef.maleEquip1 = 104;
	            itemDef.femaleEquip1 = 104;
	            itemDef.modelID = 103;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.modelZoom = 1800;
	            itemDef.modelOffsetY = 8;
	            itemDef.editedModelColor = new int[0];
	            itemDef.newModelColor = new int[0];
	            itemDef.modelZoom += 350;
	            break;
	
	        case 22077:
	            itemDef.name = "TokHaar-Kal-Ket";
	            itemDef.description = "A cape made of ancient, enchanted obsidian.";
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wear";
	            itemDef.modelID = 88;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.maleEquip1 = 87;
	            itemDef.maleEquip2 = -1;
	            itemDef.maleEquip3 = -1;
	            itemDef.femaleEquip1 = 87;
	            itemDef.femaleEquip2 = -1;
	            itemDef.femaleEquip3 = -1;
	            itemDef.stackable = false;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[] { 1496 };
	            itemDef.newModelColor = new int[] { 65 };
	            break;
	
	        case 22078:
	            itemDef.name = "TokHaar-Kal-Ket";
	            itemDef.description = "A cape made of ancient, enchanted obsidian.";
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wear";
	            itemDef.modelID = 92;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.maleEquip1 = 91;
	            itemDef.maleEquip2 = -1;
	            itemDef.maleEquip3 = -1;
	            itemDef.femaleEquip1 = 91;
	            itemDef.femaleEquip2 = -1;
	            itemDef.femaleEquip3 = -1;
	            itemDef.stackable = false;
	            itemDef.dataType = DataType.CUSTOM;
	            itemDef.editedModelColor = new int[] { 1497 };
	            itemDef.newModelColor = new int[] { 64 };
	            break;
	
	        case 17273:
	            itemDef.maleYOffset += 7;
	            itemDef.maleZOffset -= 6;
	            itemDef.femaleYOffset = -1;
	            itemDef.femaleXOffset = -4;
	            itemDef.femaleZOffset = -2;
	            itemDef.maleEquip1 = 56110;
	            break;
	        case 21630:
	            itemDef.copy(ItemDefinition.forID(2438));
	            itemDef.editedModelColor = new int[] { 61 };
	            itemDef.newModelColor = new int[] { 921 };
	            itemDef.name = "Prayer renewal (4)";
	            break;
	        case 21632:
	            itemDef.copy(ItemDefinition.forID(151));
	            itemDef.editedModelColor = new int[] { 61 };
	            itemDef.newModelColor = new int[] { 921 };
	            itemDef.name = "Prayer renewal (1)";
	            break;
	        case 21634:
	            itemDef.copy(ItemDefinition.forID(153));
	            itemDef.editedModelColor = new int[] { 61 };
	            itemDef.newModelColor = new int[] { 921 };
	            itemDef.name = "Prayer renewal (2)";
	            break;
	        case 21636:
	            itemDef.copy(ItemDefinition.forID(155));
	            itemDef.editedModelColor = new int[] { 61 };
	            itemDef.newModelColor = new int[] { 921 };
	            itemDef.name = "Prayer renewal (1)";
	            break;
	        case 21023:
	            itemDef.copy(ItemDefinition.forID(52323));
	            itemDef.dataType = DataType.REGULAR;
	            itemDef.maleEquip1 = 62914;
	            itemDef.femaleEquip1 = 62914;
	            itemDef.modelID = 62915;
	            itemDef.name = "Sanguinesti staff X";
	            itemDef.femaleYOffset = -8;
	            itemDef.femaleZOffset = 3;
	            break;
	        case 21027:
	            itemDef.copy(ItemDefinition.forID(52323));
	            itemDef.dataType = DataType.REGULAR;
	            itemDef.maleEquip1 = 62916;
	            itemDef.femaleEquip1 = 62916;
	            itemDef.modelID = 62917;
	            itemDef.name = "@whi@Sanguinesti staff";
	            break;
	        case 4037:
	            itemDef.maleEquip1 = 4850;
	            itemDef.femaleEquip1 = 4850;
	            break;
	        case 4039:
	            itemDef.maleEquip1 = 4851;
	            itemDef.femaleEquip1 = 4851;
	            break;
	        case 4068: // Dec sword
	            itemDef.maleEquip1 = 522;
	            itemDef.femaleEquip1 = 522;
	            itemDef.modelID = 2503;
	            itemDef.editedModelColor = new int[] {43990, 43321, 43486, 127};
	            itemDef.newModelColor = new int[] {937, 937, 937, -22429};
	            itemDef.rotationX = 408;
	            itemDef.rotationY = 224;
	            itemDef.modelOffsetX = 6;
	            itemDef.modelOffsetY = -49;
	            itemDef.modelZoom = 1530;
	            break;
	        case 4069:
	            itemDef.maleEquip1 = 306;
	            itemDef.maleEquip2 = 164;
	            itemDef.femaleEquip1 = 468;
	            itemDef.femaleEquip2 = 344;
	            itemDef.modelID = 2605;
	            itemDef.editedModelColor = new int[] {61,41};
	            itemDef.newModelColor = new int[] {937,-21591};
	            itemDef.modelOffset1 = -1;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = 0;
	            itemDef.rotationX = 0;
	            itemDef.rotationY = 488;
	            itemDef.modelZoom = 1250;
	            itemDef.name = "Decorative platebody";
	            break;
	        case 4070:
	            itemDef.maleEquip1 = 268;
	            itemDef.femaleEquip1 = 432;
	            itemDef.femaleEquip2 = 344;
	            itemDef.modelID = 2582;
	            itemDef.editedModelColor = new int[] {61,41,57};
	            itemDef.newModelColor = new int[] {-21591,937,-21591};
	            itemDef.modelOffsetY = -8;
	            itemDef.rotationY = 444;
	            itemDef.modelZoom = 1740;
	            itemDef.name = "Decorative platelegs";
	            break;
	        case 4071:
	            itemDef.maleEquip1 = 219;
	            itemDef.maleDialogueModel = 57;
	            itemDef.femaleEquip1 = 395;
	            itemDef.femaleDialogueModel = 117;
	            itemDef.editedModelColor = new int[] {926,61};
	            itemDef.newModelColor = new int[] {-21591,937};
	            itemDef.modelID = 2833;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = -4;
	            itemDef.rotationX = 156;
	            itemDef.rotationY = 108;
	            itemDef.modelZoom = 640;
	            itemDef.name = "Decorative helm";
	            break;
	        case 4072:
	            itemDef.maleEquip1 = 486;
	            itemDef.femaleEquip1 = 486;
	            itemDef.modelID = 2339;
	            itemDef.editedModelColor = new int[] {61,7054,57};
	            itemDef.newModelColor = new int[] {937,-21591,-21591};
	            itemDef.modelOffset1 = -6;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = -14;
	            itemDef.rotationX = 1104;
	            itemDef.rotationY = 344;
	            itemDef.modelZoom = 1560;
	            itemDef.name = "Decorative shield";
	            break;
	
	        case 4503:
	            itemDef.maleEquip1 = 522;
	            itemDef.femaleEquip1 = 522;
	            itemDef.modelID = 2503;
	            itemDef.editedModelColor = new int[] {43990, 43321, 43992, 43486, 127};
	            itemDef.newModelColor = new int[] {-22087, -22087, -22087, -22087, -22429};
	            itemDef.rotationX = 408;
	            itemDef.rotationY = 224;
	            itemDef.modelOffsetX = 6;
	            itemDef.modelOffsetY = -49;
	            itemDef.modelZoom = 1530;
	            break;
	        case 4504:
	            itemDef.maleEquip1 = 306;
	            itemDef.maleEquip2 = 164;
	            itemDef.femaleEquip1 = 468;
	            itemDef.femaleEquip2 = 344;
	            itemDef.modelID = 2605;
	            itemDef.editedModelColor = new int[] {61,41};
	            itemDef.newModelColor = new int[] {94,-22087};
	            itemDef.modelOffset1 = -1;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = 0;
	            itemDef.rotationX = 0;
	            itemDef.rotationY = 488;
	            itemDef.modelZoom = 1250;
	            itemDef.name = "Decorative platebody";
	            break;
	        case 4505:
	            itemDef.maleEquip1 = 268;
	            itemDef.femaleEquip1 = 432;
	            itemDef.femaleEquip2 = 344;
	            itemDef.modelID = 2582;
	            itemDef.editedModelColor = new int[] {61,41,57};
	            itemDef.newModelColor = new int[] {-22087,94,-22087};
	            itemDef.modelOffsetY = -8;
	            itemDef.rotationY = 444;
	            itemDef.modelZoom = 1740;
	            itemDef.name = "Decorative platelegs";
	            break;
	        case 4506:
	            itemDef.maleEquip1 = 219;
	            itemDef.maleDialogueModel = 57;
	            itemDef.femaleEquip1 = 395;
	            itemDef.femaleDialogueModel = 117;
	            itemDef.editedModelColor = new int[] {926,61,929};
	            itemDef.newModelColor = new int[] {-22087,94,-22087};
	            itemDef.modelID = 2833;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = -4;
	            itemDef.rotationX = 156;
	            itemDef.rotationY = 108;
	            itemDef.modelZoom = 640;
	            itemDef.name = "Decorative helm";
	            break;
	        case 4507:
	            itemDef.maleEquip1 = 486;
	            itemDef.femaleEquip1 = 486;
	            itemDef.modelID = 2339;
	            itemDef.editedModelColor = new int[] {61,7054,57};
	            itemDef.newModelColor = new int[] {94,-22087,-22087};
	            itemDef.modelOffset1 = -6;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = -14;
	            itemDef.rotationX = 1104;
	            itemDef.rotationY = 344;
	            itemDef.modelZoom = 1560;
	            itemDef.name = "Decorative shield";
	            break;
	
	        case 4508:
	            itemDef.maleEquip1 = 522;
	            itemDef.femaleEquip1 = 522;
	            itemDef.modelID = 2503;
	            itemDef.editedModelColor = new int[] {43321, 43990, 43992};
	            itemDef.newModelColor = new int[] {10929, 10929, 10929};
	            itemDef.rotationX = 408;
	            itemDef.rotationY = 224;
	            itemDef.modelOffsetX = 6;
	            itemDef.modelOffsetY = -49;
	            itemDef.modelZoom = 1530;
	            break;
	        case 4509:
	            itemDef.maleEquip1 = 306;
	            itemDef.maleEquip2 = 164;
	            itemDef.femaleEquip1 = 468;
	            itemDef.femaleEquip2 = 344;
	            itemDef.modelID = 2605;
	            itemDef.editedModelColor = new int[] {61,41};
	            itemDef.newModelColor = new int[] {10929,-22256};
	            itemDef.modelOffset1 = -1;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = 0;
	            itemDef.rotationX = 0;
	            itemDef.rotationY = 488;
	            itemDef.modelZoom = 1250;
	            itemDef.name = "Decorative platebody";
	            break;
	        case 4510:
	            itemDef.maleEquip1 = 268;
	            itemDef.femaleEquip1 = 432;
	            itemDef.femaleEquip2 = 344;
	            itemDef.modelID = 2582;
	            itemDef.editedModelColor = new int[] {61,41,57};
	            itemDef.newModelColor = new int[] {-22256,10929,-22256};
	            itemDef.modelOffsetY = -8;
	            itemDef.rotationY = 444;
	            itemDef.modelZoom = 1740;
	            itemDef.name = "Decorative platelegs";
	            break;
	        case 4511:
	            itemDef.maleEquip1 = 219;
	            itemDef.maleDialogueModel = 57;
	            itemDef.femaleEquip1 = 395;
	            itemDef.femaleDialogueModel = 117;
	            itemDef.editedModelColor = new int[] {926,61,929};
	            itemDef.newModelColor = new int[] {-22256,10929,-22256};
	            itemDef.modelID = 2833;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = -4;
	            itemDef.rotationX = 156;
	            itemDef.rotationY = 108;
	            itemDef.modelZoom = 640;
	            itemDef.name = "Decorative helm";
	            break;
	
	        case 4512:
	            itemDef.maleEquip1 = 486;
	            itemDef.femaleEquip1 = 486;
	            itemDef.modelID = 2339;
	            itemDef.editedModelColor = new int[] {61,7054,57};
	            itemDef.newModelColor = new int[] {10929,-22256,-22256};
	            itemDef.modelOffset1 = -6;
	            itemDef.modelOffsetX = 0;
	            itemDef.modelOffsetY = -14;
	            itemDef.rotationX = 1104;
	            itemDef.rotationY = 344;
	            itemDef.modelZoom = 1560;
	            itemDef.name = "Decorative shield";
	            break;
	
	        case 4513: // Saradomin team hood
	            itemDef.editedModelColor = new int[] { 8739, 8741, 7700, 8076, 11200 };
	            itemDef.newModelColor = new int[] { -24120, -24120, -24112, -26982, 10473 };
	            break;
	
	        case 4514: // Saradomin team cape
	            itemDef.editedModelColor = new int[] { 8739, 8741, 7698, 7700, 924, 926, 8076, 11196, 11200 };
	            itemDef.newModelColor = new int[] { -24120, -24120, -24112, -24112, -26982, -26982, -27735, 10473, 10473 };
	            break;
	
	        case 4515: // Zamorak team hood
	            itemDef.editedModelColor = new int[] { 8739, 8741, 7700, 8076, 11200 };
	            itemDef.newModelColor = new int[] { 2735, 2735, 2611, 2844, 2578 };
	            break;
	
	        case 4516: // Zamorak team cape
	            itemDef.editedModelColor = new int[] { 8739, 8741, 7698, 7700, 924, 926, 8076, 11196, 11200 };
	            itemDef.newModelColor = new int[] { 2735, 2735, 2611, 2611, 2844, 2844, 2844, 2578, 2578 };
	            break;
	
	        case 20910:
	            itemDef.modelID = 40920;
	            itemDef.name = "Mystical Spirit Shield";
	            itemDef.description = "It's a mystical spirit shield";
	            itemDef.newModelColor = new int[] { 32703, 33727, 34751, 35775, 36799, 37823, 38847, 39871, 43967, 40895,
	                    41919, 42943 };
	            itemDef.editedModelColor = new int[] { 44635, 44612, 44606, 44615, 44641, 44564, 44575, 44618, 105, 44603,
	                    44570, 4500 };
	            itemDef.modelZoom = 1616;
	            itemDef.rotationY = 396;
	            itemDef.rotationX = 1050;
	            itemDef.modelOffsetY = -3;
	            itemDef.modelOffset1 = 4;
	            itemDef.maleEquip1 = 40940;
	            itemDef.femaleEquip1 = 40940;
	            itemDef.groundActions = new String[5];
	            itemDef.groundActions[2] = "Take";
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wear";
	            itemDef.actions[4] = "Drop";
	            break;
	
	        case 20911:
	            itemDef.modelID = 40920;
	            itemDef.name = "Demonic Spirit Shield (e)";
	            itemDef.description = "It's one of the darkest spirit shields";
	            itemDef.editedModelColor = new int[13];
	            itemDef.newModelColor = new int[13];
	            itemDef.editedModelColor[0] = 44635;
	            itemDef.newModelColor[0] = 959;
	            itemDef.editedModelColor[1] = 44612;
	            itemDef.newModelColor[1] = 1983;
	            itemDef.editedModelColor[2] = 44606;
	            itemDef.newModelColor[2] = 3007;
	            itemDef.editedModelColor[3] = 44615;
	            itemDef.newModelColor[3] = 4031;
	            itemDef.editedModelColor[4] = 44641;
	            itemDef.newModelColor[4] = 5055;
	            itemDef.editedModelColor[5] = 44564;
	            itemDef.newModelColor[5] = 6079;
	            itemDef.editedModelColor[6] = 44575;
	            itemDef.newModelColor[6] = 7103;
	            itemDef.editedModelColor[7] = 44618;
	            itemDef.newModelColor[7] = 8127;
	            itemDef.editedModelColor[8] = 105;
	            itemDef.newModelColor[8] = 0;
	            itemDef.editedModelColor[9] = 44603;
	            itemDef.newModelColor[9] = 9151;
	            itemDef.editedModelColor[10] = 44570;
	            itemDef.newModelColor[10] = 11199;
	            itemDef.editedModelColor[11] = 4500;
	            itemDef.newModelColor[11] = 12223;
	            itemDef.modelZoom = 1616;
	            itemDef.rotationY = 396;
	            itemDef.rotationX = 1050;
	            itemDef.modelOffsetY = -3;
	            itemDef.modelOffset1 = 4;
	            itemDef.maleEquip1 = 40940;
	            itemDef.femaleEquip1 = 40940;
	            itemDef.groundActions = new String[5];
	            itemDef.groundActions[2] = "Take";
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wear";
	            itemDef.actions[4] = "Drop";
	            break;
	
	        case 20912:
	            itemDef.modelID = 65435;
	            itemDef.name = "Purple-black pony";
	            itemDef.description = "It's for the fashion.";
	            itemDef.modelZoom = 1519;
	            itemDef.rotationY = 595;
	            itemDef.rotationX = 0;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 0;
	            itemDef.maleEquip1 = 65436;
	            itemDef.femaleEquip1 = 65436;
	            itemDef.groundActions = new String[] { null, null, "Take", null, null };
	            itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
	            break;
	
	        case 15449:
	            itemDef.name = "Symbol of Combat";
	            break;
	        case 15454:
	            itemDef.name = "Symbol of Collectors";
	            break;
	        case 15459:
	            itemDef.name = "Symbol of Artisans";
	            break;
	        case 15464:
	            itemDef.name = "Symbol of Life";
	            break;
	        case 1543:
	            itemDef.name = "Santa's key";
	            break;
	        case 1544:
	            itemDef.copy(ItemDefinition.forID(1543));
	            itemDef.name = "Santa's key";
	            break;
	        case 21000:
	            ItemDefinition def2 = ItemDefinition.forID(9774);
	            itemDef.actions = def2.actions;
	            itemDef.name = "Vorkath cape";
	            itemDef.modelID = 62909;
	            itemDef.sizeX = def2.sizeX;
	            itemDef.sizeY = def2.sizeY;
	            itemDef.sizeZ = def2.sizeZ;
	            itemDef.maleEquip1 = 62908;
	            itemDef.femaleEquip1 = 62908;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.modelOffsetY = 130;
	            itemDef.editedModelColor = def2.editedModelColor;
	            itemDef.newModelColor = def2.newModelColor;
	            itemDef.maleYOffset -= 10;
	            itemDef.femaleYOffset -= 10;
	            break;
	        case 21002:
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@or2@Thaad Talon";
	            itemDef.description = "It is the Thaad Talon.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 14875, 14875, 14875, 14875, 14875, 14875, 14875 };
	            break;
	        case 21078:
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@or2@NOXPWASTE";
	            itemDef.description = "WASTE NO XP.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 14875, 14875, 14875, 14875, 14875, 14875, 14875 };
	            break;
	        case 21003:
	            def2 = ItemDefinition.forID(9774);
	            itemDef.actions = def2.actions;
	            itemDef.name = "Raids cape";
	            itemDef.modelID = 62911;
	            itemDef.sizeX = def2.sizeX;
	            itemDef.sizeY = def2.sizeY;
	            itemDef.sizeZ = def2.sizeZ;
	            itemDef.maleEquip1 = 62910;
	            itemDef.femaleEquip1 = 62910;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.modelOffsetY = 130;
	            itemDef.editedModelColor = def2.editedModelColor;
	            itemDef.newModelColor = def2.newModelColor;
	            itemDef.maleYOffset -= 7;
	            itemDef.femaleYOffset -= 7;
	            break;
	            
	        case 21076:
	            itemDef.copy(ItemDefinition.forID(4747));
	            itemDef.name = "Demonic Hammers";
	            itemDef.editedModelColor = new int[] { 10514, 10291 };
	            itemDef.newModelColor = new int[] { 20, 908, }; 
	            //itemDef.newModelColor = new int[] { 127, 127, }; if angelic
	            break;
	
	        case 21004:
	            itemDef.copy(ItemDefinition.forID(11694));
	            itemDef.modelID = 2608;
	            itemDef.maleEquip1 = 2606;
	            itemDef.femaleEquip1 = 2606;
	            itemDef.modelOffset1 = -2;
	            itemDef.name = "Onyx 2h sword";
	            break;
	        case 21006:
	            itemDef.copy(ItemDefinition.forID(4084));
	            itemDef.modelID = 62922;
	            itemDef.maleEquip1 = 62923;
	            itemDef.femaleEquip1 = 62923;
	            itemDef.name = "Dark sled";
	            break;
	        case 21066:
	            itemDef.copy(ItemDefinition.forID(4084));
	            itemDef.name = "White sled";
	            itemDef.modelID = 4937;
	            itemDef.maleEquip1 = 4946;
	            itemDef.femaleEquip1 = 4946;
	            itemDef.newModelColor = new int[] { 9583, 9583, 9583, 9583, 9583, 9583, 9583 };
	            break;
	        case 11304: // Katt Sang X
	            itemDef.copy(ItemDefinition.forID(52323));
	            itemDef.name = "@cya@Katt Sanguinesti staff X";
	        	itemDef.femaleYOffset = -11;
	            itemDef.femaleZOffset = 11;	            
	        	itemDef.femaleXOffset = 4;
	            itemDef.maleZOffset = -11;
	            itemDef.modelOffsetY = 3;
	            itemDef.maleEquip1 = 35372;
	            itemDef.femaleEquip1 = 39555;
	            itemDef.editedModelColor = new int[] { 836, 156, 3127, 142, 3140, 24, 20, 28, 836, 37, 49, 41, 57, 33, 16, 284 };
	            itemDef.newModelColor = new int[] { 54220, 54220, 32565, 32565, 54220, 54220, 54220, 54220, 54220, 54220, 32565, 32565, 32565, 54220, 54220, 54220 };
	            break;
	        case 21007:
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Scythe of Vitur X";
	            itemDef.description = "It is the Scythe of Vitur X.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { -1253, -1253, -1253, -1253, -1253, -1253, -1253 };
	            break;
	        case 21012:
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "Sky blue Vitur";
	            itemDef.description = "It is the Sky blue Vitur.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 689484, 689484, 689484, 689484, 689484, 689484, 689484 };
	            break;
	        case 21013: // Halloween edition
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Scythe of Vitur XI";
	            itemDef.description = "It is the Scythe of Vitur XI (Halloween edition).";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 8038, 4038, 8038, 4038, 8038, 4038, 8038 };
	            break;
	        case 21014: // TUBBYY
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@TUBBYY";
	            itemDef.description = "It is the TUBBYY.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 1024, 1024, 1024, 1024, 1024, 1024, 1024 };
	            break;
	        case 21015: // Turtlekun's custom Vitur XI
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Scythe of Vitur XI";
	            itemDef.description = "It is Turtlekun's Scythe of Vitur XI.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 1024, 1024, 1024, 90, 54220, 54220, 54220 };
	            break;
	
	        case 21016: // Relax's custom Vitur XI
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Relax's SOV XI";
	            itemDef.description = "It is Relax's Scythe of Vitur XI.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 1024, 1024, 1024, 1024, 1024, 1024, 1024 };
	            break;
	
	        case 21077: // Scythe of Vitur XI (Original)
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Scythe of Vitur XI";
	            itemDef.description = "It is the Scythe of Vitur XI.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 584, 590, 596, 836, 61, 78, 49 };
	            //            itemDef.editedModelColor = new int[] { 384, 390, 396, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 14573, 14573, 14573, 14573, 14573, 14573, 14573 };
	            break;
	
	        case 21087: // Scythe of Vitur DS
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Scythe of Vitur DS";
	            itemDef.description = "It is the Scythe of Vitur Dragonstone.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 56220, 56220, 56220, 56220, 685484, 685484, 685484 };
	            break;
	
	  /*  case 21079: // Water blue No custom order
	        itemDef.modelID = 35742;
	        itemDef.actions = new String[5];
	        itemDef.actions[1] = "Wield";
	        itemDef.name = "@red@Scythe of Vitur XI";
	        itemDef.description = "It is Tubbyy's Scythe of Vitur XI.";
	        itemDef.modelZoom = 2200;
	        itemDef.stackable = false;
	        itemDef.rotationX = 23;
	        itemDef.rotationY = 327;
	        itemDef.maleEquip1 = 35371;
	        itemDef.femaleEquip1 = 35371;
	        itemDef.editedModelColor = new int[] { 584, 590, 596, 836, 61, 78, 49 };
	        //            itemDef.editedModelColor = new int[] { 384, 390, 396, 536, 61, 78, 49 };
	        itemDef.newModelColor = new int[] { 46573, 46573, 46573, 688484, 688484, 688484, 688484 };
	       // itemDef.newModelColor = new int[] { 1024, 1024, 1024, 688484, 688484, 9583, 9583 };
	        break; */
	
	        case 21079: //
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Scythe of Vitur XI";
	            itemDef.description = "It is Tubbyy's Scythe of Vitur XI.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 1024, 1024, 1024, 1024, 100, 100, 100 };
	            break;
	
	        case 21080: // G-Unit's custom Vitur XI
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@G-Unit's Scythe of Vitur XI";
	            itemDef.description = "It is G-Unit's Scythe of Vitur XI.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 584, 590, 596, 836, 61, 78, 49 };
	            //            itemDef.editedModelColor = new int[] { 384, 390, 396, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 44573, 44573, 44573, 44573, 54220, 54220, 54220 };
	            break;
	
	        case 21081:
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Scythe of Vitur XI";
	            itemDef.description = "It is the Orange Scythe of Vitur XI.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 584, 590, 596, 836, 61, 78, 49 };
	            //            itemDef.editedModelColor = new int[] { 384, 390, 396, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 44573, 44573, 44573, 44573, 44573, 44573, 44573 };
	            break;
	
	        case 21082:
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@Scythe of Vitur XI";
	            itemDef.description = "It is the Orange Scythe of Vitur XI.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 584, 590, 596, 836, 61, 78, 49 };
	            //            itemDef.editedModelColor = new int[] { 384, 390, 396, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 54573, 54573, 54573, 54573, 54573, 54573, 54573 };
	            break;
	
	        case 21084: // Pb Purps XI
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@PB's Scythe of Vitur XI";
	            itemDef.description = "It is Pb Purps Scythe of Vitur XI.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 584, 590, 596, 836, 61, 78, 49 };
	            //            itemDef.editedModelColor = new int[] { 384, 390, 396, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 54220, 54220, 54220, 54220, 54220, 54220, 54220 };
	            break;
	        case 21085:
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@yel@FACES";
	            itemDef.description = "It's Scythe of Vitur X.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 11191, 11191, 11191, 11191, 11191, 11191, 11191 };
	            break;
	        case 21086:
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@red@666";
	            itemDef.description = "It's Scythe of Vitur X.";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
	            itemDef.newModelColor = new int[] { 7300, 57300, 57300, 57300, 57300, 57300, 57300 };
	            break;
	
	        case 21010:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wear";
	            itemDef.actions[4] = "Drop";
	            itemDef.modelID = 32799;
	            itemDef.name = "Fire Twisted Bow";
	            itemDef.modelZoom = 2000;
	            itemDef.rotationY = 720;
	            itemDef.rotationX = 1500;
	            itemDef.modelOffset1 = 3;
	            itemDef.modelOffsetY = 1;
	            itemDef.femaleEquip1 = 32674;
	            itemDef.maleEquip1 = 32674;
	            itemDef.description = "A mystical bow carved from a very hot place.";
	            itemDef.editedModelColor = new int[] { 16, 24, 33, 13223, 14236 };
	            itemDef.newModelColor = new int[] { 4024, 4024, 7073, 4024, 4024 };
	            break;
	
	        case 21011:
	            def2 = ItemDefinition.forID(9774);
	            itemDef.actions = def2.actions;
	            itemDef.name = "Corp cape";
	            itemDef.modelID = 62925;
	            itemDef.sizeX = def2.sizeX;
	            itemDef.sizeY = def2.sizeY;
	            itemDef.sizeZ = def2.sizeZ;
	            itemDef.maleEquip1 = 62924;
	            itemDef.femaleEquip1 = 62924;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.modelOffsetY = 130;
	            itemDef.editedModelColor = def2.editedModelColor;
	            itemDef.newModelColor = def2.newModelColor;
	            break;
	
	        // Yellow orange https://i.gyazo.com/07b471a0043616d12ecedd616f422fd3.png
	    /*case 21010:
	        itemDef.actions = new String[5];
	        itemDef.actions[1] = "Wear";
	        itemDef.actions[4] = "Drop";
	        itemDef.modelID = 32799;
	        itemDef.name = "Twisted Bow";
	        itemDef.modelZoom = 2000;
	        itemDef.rotationY = 720;
	        itemDef.rotationX = 1500;
	        itemDef.modelOffset1 = 3;
	        itemDef.modelOffsetY = 1;
	        itemDef.femaleEquip1 = 32674;
	        itemDef.maleEquip1 = 32674;
	        itemDef.description = "A mystical bow carved from a very magical place.";
	        itemDef.editedModelColor = new int[] { 16, 24, 33, 13223, 14236 };
	        itemDef.newModelColor = new int[] { 5024, 5024, 7073, 5024, 5024 };
	        break; */
	
	  /*  case 21010:
	        itemDef.actions = new String[5];
	        itemDef.actions[1] = "Wear";
	        itemDef.actions[4] = "Drop";
	        itemDef.modelID = 32799;
	        itemDef.name = "Twisted Bow V";
	        itemDef.modelZoom = 2000;
	        itemDef.rotationY = 720;
	        itemDef.rotationX = 1500;
	        itemDef.modelOffset1 = 3;
	        itemDef.modelOffsetY = 1;
	        itemDef.femaleEquip1 = 32674;
	        itemDef.maleEquip1 = 32674;
	        itemDef.description = "A mystical bow carved from a very magical place.";
	        itemDef.editedModelColor = new int[] { 16, 24, 33, 13223, 14236 }; // Dark grey colour
	        itemDef.newModelColor = new int[] { 6024, 6024, 6794, 6024, 6024 };
	
	      //  itemDef.editedModelColor = new int[] { 16, 24, 33, 13223, 14236 }; Yellow orange
	      //  itemDef.newModelColor = new int[] { 9024, 9024, 6073, 9024, 9024 };
	        break; */
	
	        case 20999:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wear";
	            itemDef.actions[4] = "Drop";
	            itemDef.modelID = 32799;
	            itemDef.name = "Dark twisted bow";
	            itemDef.modelZoom = 2000;
	            itemDef.rotationY = 720;
	            itemDef.rotationX = 1500;
	            itemDef.modelOffset1 = 3;
	            itemDef.modelOffsetY = 1;
	            itemDef.femaleEquip1 = 32674;
	            itemDef.maleEquip1 = 32674;
	            itemDef.description = "A mystical bow carved from the twisted remains of the Great Olm.";
	            itemDef.editedModelColor = new int[] { 16, 24, 33, 13223, 14236 };
	            itemDef.newModelColor = new int[] { 1024, 1024, 937, 1024, 1024 };
	            break;
	
	        case 21020:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wear";
	            itemDef.actions[4] = "Drop";
	            itemDef.modelID = 32799;
	            itemDef.name = "Light twisted bow";
	            itemDef.modelZoom = 2000;
	            itemDef.rotationY = 720;
	            itemDef.rotationX = 1500;
	            itemDef.modelOffset1 = 3;
	            itemDef.modelOffsetY = 1;
	            itemDef.femaleEquip1 = 32674;
	            itemDef.maleEquip1 = 32674;
	            itemDef.description = "A mystical bow carved from the twisted remains of the Great Olm.";
	            itemDef.editedModelColor = new int[] { 16, 24, 33, 43223, 44236 };
	            itemDef.newModelColor = new int[] { 9024, 9024, 7937, 9024, 9024 };
	            break;
	
	        case 21030:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wear";
	            itemDef.actions[4] = "Drop";
	            itemDef.modelID = 32799;
	            itemDef.name = "Ice twisted bow";
	            itemDef.modelZoom = 2000;
	            itemDef.rotationY = 720;
	            itemDef.rotationX = 1500;
	            itemDef.modelOffset1 = 3;
	            itemDef.modelOffsetY = 1;
	            itemDef.femaleEquip1 = 32674;
	            itemDef.maleEquip1 = 32674;
	            itemDef.description = "A mystical bow carved from the twisted remains of higher dimensions.";
	            itemDef.editedModelColor = new int[] { 16, 24, 33, 43223, 44236 };
	            itemDef.newModelColor = new int[] { 689484, 689484, 689484, 689484, 689484 };
	            break;
	
	        case 21008:
	            def2 = ItemDefinition.forID(9774);
	            itemDef.actions = def2.actions;
	            itemDef.name = "Nex cape";
	            itemDef.modelID = 62921;
	            itemDef.sizeX = def2.sizeX;
	            itemDef.sizeY = def2.sizeY;
	            itemDef.sizeZ = def2.sizeZ;
	            itemDef.maleEquip1 = 62920;
	            itemDef.femaleEquip1 = 62920;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.modelOffsetY = 130;
	            itemDef.editedModelColor = def2.editedModelColor;
	            itemDef.newModelColor = def2.newModelColor;
	            itemDef.maleYOffset = -4;
	            itemDef.femaleYOffset = -4;
	            break;
	        case 21019:
	            def2 = ItemDefinition.forID(9774);
	            itemDef.actions = def2.actions;
	            itemDef.name = "Nightmare cape";
	            itemDef.modelID = 106;
	            itemDef.sizeX = def2.sizeX;
	            itemDef.sizeY = def2.sizeY;
	            itemDef.sizeZ = def2.sizeZ;
	            itemDef.maleEquip1 = 107;
	            itemDef.maleEquip2 = -1;
	            itemDef.maleEquip3 = -1;
	            itemDef.femaleEquip1 = 107;
	            itemDef.femaleEquip2 = -1;
	            itemDef.femaleEquip3 = -1;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.modelOffsetY = 130;
	            itemDef.maleYOffset = -10;
	            itemDef.femaleYOffset = -10;
	            itemDef.editedModelColor = def2.editedModelColor;
	            itemDef.newModelColor = def2.newModelColor;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	        case 21021:
	            def2 = ItemDefinition.forID(9774);
	            itemDef.actions = def2.actions;
	            itemDef.name = "Hydra cape";
	            itemDef.modelID = 62927;
	            itemDef.sizeX = def2.sizeX;
	            itemDef.sizeY = def2.sizeY;
	            itemDef.sizeZ = def2.sizeZ;
	            itemDef.maleEquip1 = 62926;
	            itemDef.femaleEquip1 = 62926;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 355;
	            itemDef.modelZoom = 2000;
	            itemDef.modelOffsetY = 50;
	            itemDef.editedModelColor = def2.editedModelColor;
	            itemDef.newModelColor = def2.newModelColor;
	            itemDef.maleYOffset = -4;
	            itemDef.femaleYOffset = -4;
	            break;
	        case 21022:
	            def2 = ItemDefinition.forID(9774);
	            itemDef.actions = def2.actions;
	            itemDef.name = "Verzik's cape";
	            itemDef.modelID = 62913;
	            itemDef.sizeX = def2.sizeX;
	            itemDef.sizeY = def2.sizeY;
	            itemDef.sizeZ = def2.sizeZ;
	            itemDef.maleEquip1 = 62912;
	            itemDef.femaleEquip1 = 62912;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.modelOffsetY = 130;
	            itemDef.editedModelColor = def2.editedModelColor;
	            itemDef.newModelColor = def2.newModelColor;
	            itemDef.maleYOffset -= 7;
	            itemDef.femaleYOffset -= 7;
	            break;
	            
	        case 21090:
		    	itemDef.copy(ItemDefinition.forID(41860));
	            itemDef.name = "Angelic Boots"; // Overlord boots
	            itemDef.maleEquip1 = 115;
	            itemDef.femaleEquip1 = 115;
	            itemDef.modelID = 116;
	            itemDef.editedModelColor = new int[] { 31, 40 };
	            itemDef.newModelColor = new int[] { 62, 62 }; 
	            //itemDef.newModelColor = new int[] { 40, 40 }; Fire cape angelics
	            //itemDef.newModelColor = new int[] { 61, 61 }; Dark Infernal angelics
	            //itemDef.newModelColor = new int[] { 62, 62 }; Dark overlord angelics
	            //itemDef.newModelColor = new int[] { 64, 64 }; Ocean angelics
	            //itemDef.newModelColor = new int[] { 67, 67 }; Executive aqua angelics
	            //itemDef.newModelColor = new int[] { 69, 69 }; Dark lava angelics
	            //itemDef.newModelColor = new int[] { 70, 70 }; Rainbow angelics
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	            
	        case 21045:
	            def2 = ItemDefinition.forID(9774);
	            itemDef.actions = def2.actions;
				itemDef.actions[2] = "Teleport";
				itemDef.equippedStateActions = new String[]{ null, "Teleport", null, null, null };
	            itemDef.name = "Overlord cape";
	            itemDef.modelID = 98;
	            itemDef.sizeX = def2.sizeX;
	            itemDef.sizeY = def2.sizeY;
	            itemDef.sizeZ = def2.sizeZ;
	            itemDef.maleEquip1 = 97;
	            itemDef.maleEquip2 = -1;
	            itemDef.maleEquip3 = -1;
	            itemDef.femaleEquip1 = 97;
	            itemDef.femaleEquip2 = -1;
	            itemDef.femaleEquip3 = -1;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.modelOffsetY = 130;
	            itemDef.editedModelColor = def2.editedModelColor;
	            itemDef.newModelColor = def2.newModelColor;
	            itemDef.editedModelColor = new int[] { 40 };
	            itemDef.newModelColor = new int[] { 61 };
	            itemDef.maleYOffset = -10;
	            itemDef.femaleYOffset = -10;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	            
	        case 21075:
	            def2 = ItemDefinition.forID(9774);
	            itemDef.actions = def2.actions;
				itemDef.actions[2] = "Teleport";
	            itemDef.name = "Phoenix cape"; // WIP
	            itemDef.modelID = 98;
	            itemDef.sizeX = def2.sizeX;
	            itemDef.sizeY = def2.sizeY;
	            itemDef.sizeZ = def2.sizeZ;
	            itemDef.maleEquip1 = 97;
	            itemDef.maleEquip2 = -1;
	            itemDef.maleEquip3 = -1;
	            itemDef.femaleEquip1 = 97;
	            itemDef.femaleEquip2 = -1;
	            itemDef.femaleEquip3 = -1;
	            itemDef.rotationX = 1024;
	            itemDef.rotationY = 300;
	            itemDef.modelZoom = 2000;
	            itemDef.modelOffsetY = 130;
	            itemDef.editedModelColor = def2.editedModelColor;
	            itemDef.newModelColor = def2.newModelColor;
	            itemDef.editedModelColor = new int[] { 40 };
	            itemDef.newModelColor = new int[] { 40 };
	            itemDef.maleYOffset = -10;
	            itemDef.femaleYOffset = -10;
	            itemDef.dataType = DataType.CUSTOM;
	            break;
	           
	
	  /*  case 21520:
	        itemDef.name = "Elite Nightmare";
	    	itemDef.copy(ItemDefinition.forID(54491));
	        itemDef.editedModelColor = new int[] { 43968, 62400, 61714, 38036, 43076, 58661, 61718, 38408, 38040, 38156, 38044, 43086, };
	        itemDef.newModelColor = new int[] { 43968, 90, 61714, 38036, 90, 58661, 61718, 38408, 38040, 90, 38044, 90, };
	        break;
	    case 21521:
	        itemDef.name = "Elite Olmlet";
	    	itemDef.copy(ItemDefinition.forID(50851));
	        itemDef.editedModelColor = new int[] { 15296, 32881, 33, 33891, 32821, 24, 32889, 32858, 32875, 12, 32846,  };
	        itemDef.newModelColor = new int[] { 15296, 32881, 90, 33891, 32821, 90, 32889, 32858, 32875, 12, 61714,  };
	        break;
	    case 21522:
	        itemDef.name = "Elite Zik";
	    	itemDef.copy(ItemDefinition.forID(52473));
	        itemDef.editedModelColor = new int[] { 0, 64448, 43169, 43202, 43206, 59303, 54410, 63946, 47275, 62763, 62771, 10452, 49304, 62236, 43197,   };
	        itemDef.newModelColor = new int[] {  0, 32889, 32889, 43202, 43206, 59303, 54410, 32889, 32889, 32889, 62771, 32889, 49304, 62236, 43197,    };
	        break; */
	
	        case 18339:
	            itemDef.name = "Gold bag";
	            itemDef.actions[0] = "Check";
	            itemDef.actions[4] = "Drop";
	            break;
	
	        case 9722:
	            itemDef.name = "@gre@Prize Key";
	            break;
	
	        case 19864:
	            itemDef.name = "@gre@Halloween Token";
	            break;
	
	        case 11179:
	            itemDef.name = "Old coin";
	            break;
	
	        case 1050:
	            itemDef.maleZOffset += 4;
	            break;
	        case 19713:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Archery Box";
	            itemDef.newModelColor[0] = 15260;
	            break;
	        case 19723:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Elite Archery Box";
	            itemDef.newModelColor[0] = 25260;
	            break;
	        case 6855:
	            itemDef.name = "Arthur's Birthday Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 21560:
	            itemDef.copy(ItemDefinition.forID(76));
	            itemDef.name = "Elite chest key";
	            itemDef.editedModelColor = new int[] { 74, 12, 45, };
	            itemDef.newModelColor = new int[] { 38325, 9583, 38325, }; 
	            itemDef.actions = new String[5];
	            itemDef.actions[2] = "Teleport";
	            break;
	            
	        case 19468:
	            itemDef.copy(ItemDefinition.forID(55744));
                itemDef.name = "@whi@Sirenic ornament kit";
	            itemDef.editedModelColor = new int[] {  16, 272, 404, 6315, 796,  };
	            itemDef.newModelColor = new int[] {  90, 90, 404, 90, 796,  }; 

	        	break;
	        case 21545:
	            itemDef.copy(ItemDefinition.forID(6855));
	            itemDef.name = "Mythical Box";
	            itemDef.editedModelColor = new int[] { 35763, 62375, };
	            itemDef.newModelColor = new int[] { 38325, 9583, }; 
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 6831:
	            itemDef.name = "Sapphire Mystery Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 6832:
	            itemDef.name = "Emerald Mystery Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 6830:
	            itemDef.name = "Ruby Mystery Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 19538:
	            itemDef.name = "Halloween Skeleton Set";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 744:
	            itemDef.name = "Valentine Mystery Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 19730:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Kevin's Birthday Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            itemDef.newModelColor[0] = 10560;
	            break;
	        case 19714:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Warrior Box";
	            itemDef.newModelColor[0] = 40260;
	            break;
	        case 19724:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Elite Warrior Box";
	            itemDef.newModelColor[0] = 50260;
	            break;
	        case 6824:
	            itemDef.name = "NY LUCKY STAR";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 6825:
	            itemDef.name = "NY 2020 STAR";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 19715:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Wizard Box";
	            itemDef.newModelColor[0] = 2060;
	            break;
	        case 19725:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Elite Wizard Box";
	            itemDef.newModelColor[0] = 3060;
	            break;
	        case 19726:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Superior Combat Box";
	            itemDef.newModelColor[0] = 689484;
	            break;
	        case 19717:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Santa's Secret Box";
	            itemDef.newModelColor[0] = -1253;
	            break;
	        case 15420:
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 745:
	            itemDef.name = "Valentine's 2021 Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 8152:
	            itemDef.name = "Valentine's Owner Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	        case 8473:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Verzik's Mystery Box";
	            itemDef.newModelColor[0] = 46383;
	            break;
	        case 8421:
	            itemDef.setDefaults();
	            itemDef.name = "Demonic spirit shield";
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
	            ItemDefinition def = ItemDefinition.forID(1019);
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
	         * Model switch to OSRS
	         */
	        case 15001:
	            itemDef.copy(ItemDefinition.forID(52324));
	            break;
	
	        case 15255:
	            itemDef.copy(ItemDefinition.forID(52325));
	            break;
	
	        case 14484:
	            itemDef.copy(ItemDefinition.forID(43652));
	            break;
	
	        case 15259:
	            itemDef.copy(ItemDefinition.forID(41920));
	            itemDef.femaleYOffset = -7;
	            itemDef.femaleXOffset = 2;
	            break;
	
	        case 11726:
	            itemDef.copy(ItemDefinition.forID(53646));
	            break;
	
	     /*   case 4587:
	            itemDef.copy(ItemDefinition.forID(50406));
	            break; */
	
	        case 1333:
	            itemDef.copy(ItemDefinition.forID(50402));
	            break;
	
	        case 11924:
	            itemDef.copy(ItemDefinition.forID(41924));
	            break;
	
	        case 11926:
	            itemDef.copy(ItemDefinition.forID(41926));
	            break;
	
	        case 12282:
	            itemDef.copy(ItemDefinition.forID(42931));
	            break;
	
	        case 15486:
	            itemDef.copy(ItemDefinition.forID(52296));
	            break;
	
	        case 15492:
	            itemDef.copy(ItemDefinition.forID(41864));
	            break;
	
	        case 12284:
	            itemDef.copy(ItemDefinition.forID(42904));
	            break;
	
	        // Spirit shield models
	
	
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
	        case 42006:
	            itemDef.name = "Abyssal tentacle";
		    	itemDef.femaleYOffset = -3;
		    	itemDef.femaleXOffset = 6;
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
	            itemDef.name = "Sky Blue Santa Hat";
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
	        /* STAFF CUSTOM SANTA HATS */
	        case 11296:
	            itemDef.name = "@red@Not @whi@a @red@Custom @whi@Server";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Ace Christmas 2019!";
	            itemDef.editedModelColor = new int[1];
	            itemDef.newModelColor = new int[1];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 461770;
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
	        case 11297:
	            itemDef.name = "@whi@Pixie @red@Hoe @whi@Ho @red@Ho";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Pixie Joe Christmas 2019!";
	            itemDef.editedModelColor = new int[1];
	            itemDef.newModelColor = new int[1];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 1075;
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
	        case 11298:
	            itemDef.name = "@red@G@red@O@whi@A@red@T";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Mak3 Christmas 2019!";
	            itemDef.editedModelColor = new int[1];
	            itemDef.newModelColor = new int[1];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 127;
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
	        case 11299:
	            itemDef.name = "@red@X@whi@p@red@w@whi@a@red@s@whi@t@red@e";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Shiftynex Christmas 2019!";
	            itemDef.editedModelColor = new int[1];
	            itemDef.newModelColor = new int[1];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 4960;
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
	        case 3070:
	            itemDef.name = "@blu@Netherlands";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Dutch Christmas 2019!";
	            itemDef.editedModelColor = new int[1];
	            itemDef.newModelColor = new int[1];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 302770;
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
	        case 3071:
	            itemDef.name = "@red@B@whi@u@red@l@whi@l@red@s";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Abe Christmas 2019!";
	            itemDef.editedModelColor = new int[1];
	            itemDef.newModelColor = new int[1];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 660;
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
	        case 3072:
	            itemDef.name = "@red@D@whi@O@red@N@whi@T @red@T@whi@O@red@U@whi@C@red@H";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Lewis Christmas 2019!";
	            itemDef.editedModelColor = new int[1];
	            itemDef.newModelColor = new int[1];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 51136;
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
	        case 3073:
	            itemDef.name = "@red@BABY @whi@SHARK";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Rapiid Ags Christmas 2019!";
	            itemDef.editedModelColor = new int[2];
	            itemDef.newModelColor = new int[2];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 5652;
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
	        case 3074:
	            itemDef.name = "@red@Ya @whi@Boi";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Justin Christmas 2019!";
	            itemDef.editedModelColor = new int[2];
	            itemDef.newModelColor = new int[2];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 123456;
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
	        case 3075:
	            itemDef.name = "@red@Kevin @whi@Hates @red@Me";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Zigzy Christmas 2019!";
	            itemDef.editedModelColor = new int[2];
	            itemDef.newModelColor = new int[2];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 266770;
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
	        case 3076:
	            itemDef.name = "@red@Not @whi@A @red@Party @whi@Hat";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Bernie Christmas 2019!";
	            itemDef.editedModelColor = new int[2];
	            itemDef.newModelColor = new int[2];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 374770;
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
	        case 3077:
	            itemDef.name = "@red@Always @whi@an @red@ironman";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Athos Christmas 2019!";
	            itemDef.editedModelColor = new int[2];
	            itemDef.newModelColor = new int[2];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 7114;
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
	        case 3078:
	            itemDef.name = "@red@Codys @whi@Santa";
	            itemDef.modelID = 2537;
	            itemDef.description = "Given to Cody Christmas 2019!";
	            itemDef.editedModelColor = new int[2];
	            itemDef.newModelColor = new int[2];
	            itemDef.editedModelColor[0] = 933;
	            itemDef.newModelColor[0] = 96993;
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
	        case 8331:
	            itemDef.actions[0] = "Teleport";
	            break;
	        /* END */
	        /*case 12426:
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
	            break;*/
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
	        case 16027:
	        case 16389:
	        case 4749:
	            itemDef.femaleEquip1 = itemDef.maleEquip1;
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
	            itemDef.imitate(ItemDefinition.forID(9952));
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
	        case 18688:
	            itemDef.name = "Raids key";
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
	
	        case 6828:
	            itemDef.name = "@red@Bronze Mystery Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	
	        case 6829:
	            itemDef.name = "@red@Gold Mystery Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	
	        case 6833:
	            itemDef.name = "@red@Diamond Mystery Box";
	            itemDef.actions = new String[5];
	            itemDef.actions[0] = "Open";
	            break;
	
	        case 3157:
	            itemDef.name = "@red@Anglerfish vessel";
	            break;
	
	        case 12632:
	            itemDef.name = "100m Note";
	            itemDef.actions = new String[]{"Claim", null, null, null, "Drop"};
	            break;
	
	        case 6798:
	            itemDef.name = "Superior Scroll";
	            itemDef.actions = new String[]{"Claim", null, null, null, "Drop"};
	            break;
	
	        case 4202:
	            itemDef.name = "Ring of Coins";
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
	            itemDef.actions = new String[]{null, "Wield", "Check", "Unload", "Drop"};
	            itemDef.newModelColor = new int[]{8134, 5058, 926, 957, 3008, 1321, 86, 41, 49, 7110, 3008, 1317};
	            itemDef.editedModelColor = new int[]{48045, 49069, 48055, 49083, 50114, 33668, 29656, 29603, 33674, 33690, 33680, 33692};
	            itemDef.modelID = 19219;
	            itemDef.maleEquip1 = 14403;
	            itemDef.femaleEquip1 = 14403;
	            itemDef.modelOffset1 = -7;
	            itemDef.modelOffsetY = 4;
	            itemDef.maleYOffset = 8;
	            itemDef.femaleXOffset = -4;
	            itemDef.maleXOffset = -4;  
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
	            itemDef.maleYOffset = 8;
	            itemDef.femaleXOffset = -4;
	            itemDef.maleXOffset = -4;
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
	            itemDef.femaleYOffset = -8;
	            itemDef.femaleZOffset = 3;
	            itemDef.maleYOffset = 8;
	            itemDef.maleXOffset = -6;
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
	            
	            // Fix Dungeoneering cape models
	        case 15706:
	        case 18508:
	            itemDef.copy(ItemDefinition.forID(18509));
	            break;
	
	        case 20555:
	            itemDef.copy(ItemDefinition.forID(43576));
	            itemDef.maleYOffset = 8;
	            itemDef.femaleYOffset = -10;
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
	            itemDef.name = "Kalphite princess";
	            itemDef.groundActions = new String[]{null, null, "Take", null, null};
	            itemDef.actions = new String[]{null, null, null, null, "Drop"};
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
	
	        case 15288:
	            itemDef.copy(ItemDefinition.forID(6199));
	            itemDef.name = "Owner's Birthday Box";
	            itemDef.newModelColor[0] = 1024;
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
	        case 3135:
	            itemDef.name = "Wilderness key";
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
	
	        case 15000:
	            itemDef.modelID = 35742;
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.name = "@or2@Scythe of Vitur";
	            itemDef.description = "It is the Scythe Of Vitur";
	            itemDef.modelZoom = 2200;
	            itemDef.stackable = false;
	            itemDef.rotationX = 23;
	            itemDef.rotationY = 327;
	            itemDef.maleEquip1 = 35371;
	            itemDef.femaleEquip1 = 35371;
	            break;
	
	        case 8869:
	            itemDef.name = "@or2@Champion's key";
	            break;
	
	        case 6469:
	            itemDef.name = "@or2@Dragonfruit";
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
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65496;
	            itemDef.femaleEquip1 = 65496;
	            itemDef.stackable = false;
	            itemDef.name = "Master agility cape";
	            itemDef.description = "Master agility cape";
	            break;
	
	        case 11350:
	            ItemDefinition itemDef21 = ItemDefinition.forID(11732);
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
	            ItemDefinition itemDef2111 = ItemDefinition.forID(11732);
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
	            ItemDefinition itemDef211 = ItemDefinition.forID(11732);
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
	            ItemDefinition itemDef211111 = ItemDefinition.forID(11732);
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
	            ItemDefinition itemDef2111111 = ItemDefinition.forID(11732);
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
	            ItemDefinition itemDef21111111 = ItemDefinition.forID(11732);
	            itemDef.modelID = itemDef21111111.modelID;
	            itemDef.maleEquip1 = itemDef21111111.maleEquip1;
	            itemDef.femaleEquip1 = itemDef21111111.femaleEquip1;
	            itemDef.modelOffset1 = itemDef21111111.modelOffset1;
	            itemDef.modelOffsetX = itemDef21111111.modelOffsetX;
	            itemDef.modelOffsetY = itemDef21111111.modelOffsetY;
	            itemDef.rotationY = itemDef21111111.rotationY;
	            itemDef.rotationX = itemDef21111111.rotationX;
	            itemDef.modelZoom = itemDef21111111.modelZoom;
	            itemDef.name = "Demonic Dragon boots ";
	            itemDef.actions = itemDef21111111.actions;
	            itemDef.editedModelColor = new int[] { 926 };
	            itemDef.newModelColor  = new int[] { 266770 };
	            itemDef.stackable = false;
	            break;
	        case 11356:
	            ItemDefinition itemDef211111111 = ItemDefinition.forID(11732);
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
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65498;
	            itemDef.femaleEquip1 = 65498;
	            itemDef.stackable = false;
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
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
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
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65502;
	            itemDef.femaleEquip1 = 65502;
	            itemDef.stackable = false;
	            itemDef.name = "Master cooking cape";
	            itemDef.description = "Master cooking cape";
	            break;
	        case 13360:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65503;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65504;
	            itemDef.femaleEquip1 = 65504;
	            itemDef.stackable = false;
	            itemDef.name = "Master crafting cape";
	            itemDef.description = "Master crafting cape";
	            break;
	        case 13361:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65505;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65506;
	            itemDef.femaleEquip1 = 65506;
	            itemDef.stackable = false;
	            itemDef.name = "Master defence cape";
	            itemDef.description = "Master defence cape";
	            break;
	        case 13662:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65507;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65508;
	            itemDef.femaleEquip1 = 65508;
	            itemDef.stackable = false;
	            itemDef.name = "Master farming cape";
	            itemDef.description = "Master farming cape";
	            break;
	        case 13664:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65509;
	            ItemDefinition idef = ItemDefinition.forID(19709);
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65510;
	            itemDef.femaleEquip1 = 65510;
	            itemDef.stackable = false;
	            itemDef.name = "Master firemaking cape";
	            itemDef.description = "Master firemaking cape";
	            break;
	        case 13665:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65511;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65512;
	            itemDef.femaleEquip1 = 65512;
	            itemDef.stackable = false;
	            itemDef.name = "Master fishing cape";
	            itemDef.description = "Master fishing cape";
	            break;
	        case 13666:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65513;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65514;
	            itemDef.femaleEquip1 = 65514;
	            itemDef.stackable = false;
	            itemDef.name = "Master fletching cape";
	            itemDef.description = "Master fletching cape";
	            break;
	        case 13667:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65515;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65516;
	            itemDef.femaleEquip1 = 65516;
	            itemDef.stackable = false;
	            itemDef.name = "Master herblore cape";
	            itemDef.description = "Master herblore cape";
	            break;
	        case 13668:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65517;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65518;
	            itemDef.femaleEquip1 = 65518;
	            itemDef.stackable = false;
	            itemDef.name = "Master hitpoints cape";
	            itemDef.description = "Master hitpoints cape";
	            break;
	        case 13669:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65519;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65520;
	            itemDef.femaleEquip1 = 65520;
	            itemDef.stackable = false;
	            itemDef.name = "Master hunter cape";
	            itemDef.description = "Master hunter cape";
	            break;
	        case 13670:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65521;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65522;
	            itemDef.femaleEquip1 = 65522;
	            itemDef.stackable = false;
	            itemDef.name = "Master magic cape";
	            itemDef.description = "Master magic cape";
	            break;
	        case 13671:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65523;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65524;
	            itemDef.femaleEquip1 = 65524;
	            itemDef.stackable = false;
	            itemDef.name = "Master mining cape";
	            itemDef.description = "Master mining cape";
	            break;
	        case 13672:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65525;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65526;
	            itemDef.femaleEquip1 = 65526;
	            itemDef.stackable = false;
	            itemDef.name = "Master prayer cape";
	            itemDef.description = "Master prayer cape";
	            break;
	        case 13673:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65527;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65528;
	            itemDef.femaleEquip1 = 65528;
	            itemDef.stackable = false;
	            itemDef.name = "Master ranged cape";
	            itemDef.description = "Master ranged cape";
	            break;
	        case 13674:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65529;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65530;
	            itemDef.femaleEquip1 = 65530;
	            itemDef.stackable = false;
	            itemDef.name = "Master runecrafting cape";
	            itemDef.description = "Master runecrafting cape";
	            break;
	        case 13675:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65531;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65532;
	            itemDef.femaleEquip1 = 65532;
	            itemDef.stackable = false;
	            itemDef.name = "Master slayer cape";
	            itemDef.description = "Master slayer cape";
	            break;
	        case 13676:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65533;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 65534;
	            itemDef.femaleEquip1 = 65534;
	            itemDef.stackable = false;
	            itemDef.name = "Master smithing cape";
	            itemDef.description = "Master smithing cape";
	            break;
	        case 13690:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 65535;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
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
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 64538;
	            itemDef.femaleEquip1 = 64538;
	            itemDef.stackable = false;
	            itemDef.name = "Master summoning cape";
	            itemDef.description = "Master summoning cape";
	            break;
	        case 13679:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 64539;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 64540;
	            itemDef.femaleEquip1 = 64540;
	            itemDef.stackable = false;
	            itemDef.name = "Master thieving cape";
	            itemDef.description = "Master thieving cape";
	            break;
	        case 13680:
	            itemDef.actions = new String[5];
	            itemDef.actions[1] = "Wield";
	            itemDef.modelID = 64541;
	            itemDef.modelZoom = 2400;
	            itemDef.rotationY = 400;
	            itemDef.rotationX = 1020;
	            itemDef.modelOffset1 = 0;
	            itemDef.modelOffsetY = 30;
	            itemDef.maleEquip1 = 64542;
	            itemDef.femaleEquip1 = 64542;
	            itemDef.stackable = false;
	            itemDef.name = "Master woodcutting cape";
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
	        //start dataType pets
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
	            itemDef.actions = new String[]{"Summon", null, null, null, null, "Drop"};
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
	            itemDef.copy(ItemDefinition.forID(50851));
	            itemDef.name = "Olmlet";
	            break;
	        //end
	
	        case 16580:
	            itemDef.copy(ItemDefinition.forID(6637));
	            itemDef.name = "Halloween pet";
	            itemDef.groundActions = new String[]{null, null, "Take", null, null};
	            itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
	            itemDef.editedModelColor = new int[] { 898, 4502, 38119, 5640, 8363, 7403, 5388, 268, 4750, 9135, 65535, };
	            itemDef.newModelColor = new int[] { 3, 43, 90, 90, 90, 90, 23, 23, 27, 94, 90, };
	            break;
	            
	        case 4135:
	            itemDef.name = "@whi@Lil' Banshee";
	            itemDef.groundActions = new String[]{null, null, "Take", null, null};
	            itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
	            break;
	
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
	            itemDef.name = "Vorkath's Vine Whip";
	            itemDef.description = "A weapon from Vorkath island, interlaced with a vicious vorkath vine.";
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
	            itemDef.femaleEquip1 = 64704;
	            itemDef.femaleYOffset = -11;
	            itemDef.femaleXOffset = 6;
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
	            itemDef.actions[3] = "Toggle ROL";
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
	        case 9753:
	        case 9754:
	            itemDef.actions[3] = "Toggle ROL";
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
	            itemDef.actions[2] = "Customize";
	            itemDef.actions[3] = "Toggle ROL";
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
	        case 42873:
	        case 42875:
	        case 42877:
	        case 42879:
	        case 42881:
	        case 42883:
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
	            itemDef.femaleYOffset = -10;
	            itemDef.femaleZOffset = 4;
	            itemDef.maleYOffset = -4;
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
	            itemDef.femaleYOffset = -10;
	            itemDef.femaleZOffset = 4;
	            itemDef.maleYOffset = -4;
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
	            itemDef.femaleYOffset = -10;
	            itemDef.femaleZOffset = 4;
	            itemDef.maleYOffset = -4;
	            break;
	
	        case 20060:
	            itemDef.name = "Magic knife";
	            itemDef.description = "You can offer this knife for a chance at the magic chest.";
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
	            itemDef.femaleEquip1 = 62746;
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
	            itemDef.femaleEquip1 = 62742;
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
    }
}
