package com.simplicity.client.cache.definitions.custom;

import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;

public class CustomItems {

    public static void loadDefinition(ItemDefinition itemDef) {
        switch (itemDef.id) {
            case 10033:
            case 10034:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
        	case 21024:
        		itemDef.copy(ItemDefinition.forID(14601));
        		itemDef.editedModelColor = new int[] { 940, 933, 928, 924 };
            	itemDef.newModelColor = new int[] { 37631, 37631, 37631, 37631 };
            	itemDef.name = "Sky blue santa costume top";
            	// 11237 yellow
            	// 43980 blue
            	// 54194 Purple
        		break;
        	case 21025:
        		itemDef.copy(ItemDefinition.forID(14603));
        		itemDef.editedModelColor = new int[] { 940, 933, 928, 924 };
            	itemDef.newModelColor = new int[] { 37631, 37631, 37631, 37631 };
            	itemDef.name = "Sky blue santa costume legs";
        		break;
            case 21026:
                itemDef.name = "Sky blue Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare sky blue santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 37631;
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
            case 6927:
                itemDef.copy(ItemDefinition.forID(9924));
                itemDef.editedModelColor = new int[] {127, 70, 121, 59515};
                itemDef.newModelColor = new int[] {933, 933, 933, 933};
                break;

            case 6928:
                itemDef.copy(ItemDefinition.forID(9925));
                itemDef.editedModelColor = new int[] {127, 70, 121, 59515};
                itemDef.newModelColor = new int[] {933, 933, 933, 933};
                break;

            case 6929:
                itemDef.copy(ItemDefinition.forID(9923));
                itemDef.editedModelColor = new int[] {127, 70, 121, 59515};
                itemDef.newModelColor = new int[] {933, 933, 933, 933};
                break;

            case 6930:
                itemDef.copy(ItemDefinition.forID(9922));
                itemDef.editedModelColor = new int[] {127, 70, 121, 59515};
                itemDef.newModelColor = new int[] {933, 933, 933, 933};
                break;

            case 6931:
                itemDef.copy(ItemDefinition.forID(9921));
                itemDef.editedModelColor = new int[] {127, 70, 121, 59515};
                itemDef.newModelColor = new int[] {933, 933, 933, 933};
                break;

            case 14433:
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 32799;
                itemDef.name = "Fire Twisted Bow Pet";
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 720;
                itemDef.rotationX = 1500;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 1;
                itemDef.description = "A mystical bow carved from a very hot place.";
                itemDef.editedModelColor = new int[]{16, 24, 33, 13223, 14236};
                itemDef.newModelColor = new int[]{4024, 4024, 7073, 4024, 4024};
                break;
            case 14434:
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 32799;
                itemDef.name = "Dark Twisted Bow Pet";
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 720;
                itemDef.rotationX = 1500;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 1;
                itemDef.description = "A mystical bow carved from a very hot place.";
                itemDef.editedModelColor = new int[]{16, 24, 33, 13223, 14236};
                itemDef.newModelColor = new int[]{1024, 1024, 937, 1024, 1024};
                break;
            case 14435:
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 32799;
                itemDef.name = "Twisted Bow Pet";
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 720;
                itemDef.rotationX = 1500;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 1;
                itemDef.description = "A mystical bow carved from a very hot place.";
                break;
            case 14436:
                itemDef.modelID = 35742;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.name = "@red@Scythe of Vitur X Pet";
                itemDef.description = "It is the Scythe of Vitur X.";
                itemDef.modelZoom = 2200;
                itemDef.stackable = false;
                itemDef.rotationX = 23;
                itemDef.rotationY = 327;
                itemDef.editedModelColor = new int[]{784, 790, 796, 536, 61, 78, 49};
                itemDef.newModelColor = new int[]{-1253, -1253, -1253, -1253, -1253, -1253, -1253};
                break;
            case 14437:
                itemDef.modelID = 35742;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.name = "@red@Sycthe of Vitur Y Pet";
                itemDef.description = "It is the Scythe of Vitur Y.";
                itemDef.modelZoom = 2200;
                itemDef.stackable = false;
                itemDef.rotationX = 23;
                itemDef.rotationY = 327;
                itemDef.editedModelColor = new int[]{584, 590, 596, 836, 61, 78, 49};
                itemDef.newModelColor = new int[]{14573, 14573, 14573, 14573, 14573, 14573, 14573};
                break;
            case 14438:
                itemDef.modelID = 35742;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.name = "@or2@Scythe Of Vitur Pet";
                itemDef.description = "It is the Scythe Of Vitur";
                itemDef.modelZoom = 2200;
                itemDef.stackable = false;
                itemDef.rotationX = 23;
                itemDef.rotationY = 327;
                itemDef.maleEquip1 = 35371;
                itemDef.femaleEquip1 = 35371;
                break;

            case 11848:
                itemDef.copy(ItemDefinition.forID(30_000 + 12877));
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;

            case 11850:
                itemDef.copy(ItemDefinition.forID(30_000 + 12873));
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;

            case 11854:
                itemDef.copy(ItemDefinition.forID(30_000 + 12879));
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;

            case 11856:
                itemDef.copy(ItemDefinition.forID(30_000 + 12875));
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;

            case 11846:
                itemDef.copy(ItemDefinition.forID(30_000 + 12881));
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;

            case 11852:
                itemDef.copy(ItemDefinition.forID(30_000 + 12883));
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
                
            case 14545:
                itemDef.copy(ItemDefinition.forID(14094));
                itemDef.name = "Dark Sacred Clay";
                itemDef.editedModelColor = new int[]{3368, 13490, 13357, 8741, 13500, 13386, 12, 13480, 3363, 3358, 3353, 13475, 3358};
                itemDef.newModelColor = new int[]{14573, 1024, 1024, 1024, 14573, 14573, 1024, 1024, 1024, 1024, 1024, 1024, 1024};
                break;
                
            case 14546:
                itemDef.copy(ItemDefinition.forID(14095));
                itemDef.name = "Dark Sacred Clay";
                itemDef.editedModelColor = new int[]{3368, 13490, 13357, 8741, 13500, 13386, 12, 13480, 3363, 3358, 3353, 13475, 3358, 4550, 908, 3373, 3363, 13465};
                itemDef.newModelColor = new int[]{14573, 1024, 1024, 1024, 14573, 14573, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 14573};
                break;
                
            case 14547:
                itemDef.copy(ItemDefinition.forID(14096));
                itemDef.name = "Dark Sacred Clay";
                itemDef.editedModelColor = new int[]{3368, 13490, 13357, 8741, 13500, 13386, 12, 13480, 3363, 3358, 3353, 13475, 3358, 4550, 908, 3373, 3363, 13465};
                itemDef.newModelColor = new int[]{14573, 1024, 1024, 1024, 14573, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 14573};
                break;
                
            case 17931:
                itemDef.copy(ItemDefinition.forID(22014));
                itemDef.name = "Light sirenic helm";
                itemDef.editedModelColor = new int[]{39329, 32197, 38057, 41226, 33066, 40202, 41232, 40084, 41237, 40085, 40344, 40345, 40090, 32060, 40349};
                itemDef.newModelColor = new int[]{90, 32197, 90, 41226, 90, 40202, 90, 40084, 90, 90, 90, 90, 90, 90, 90};
                break;
                
            case 17932:
                itemDef.copy(ItemDefinition.forID(22016));
                itemDef.name = "Light sirenic platebody";
                itemDef.editedModelColor = new int[]{39329, 32197, 33066, 43149, 41232, 43153, 42131, 41237, 42133, 40344, 40345, 32060, 40349, 20158};
                itemDef.newModelColor = new int[]{90, 32197, 90, 43149, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90};
                break;
                
            case 17933:
                itemDef.copy(ItemDefinition.forID(22018));
                itemDef.name = "Light sirenic platelegs";
                itemDef.editedModelColor = new int[]{39329, 32197, 33066, 43149, 41232, 43153, 42131, 41237, 42133, 40344, 40345, 32060, 40349};
                itemDef.newModelColor = new int[]{90, 32197, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90};
                break;

            case 17934:
                itemDef.copy(ItemDefinition.forID(22022));
                itemDef.name = "Light sirenic gloves";
                itemDef.editedModelColor = new int[]{40608, 33201, 43283, 40596, 43285, 43276};
                itemDef.newModelColor = new int[]{90, 90, 90, 90, 90, 90};
                break;

            case 17935:
                itemDef.copy(ItemDefinition.forID(22020));
                itemDef.name = "Light sirenic boots";
                itemDef.editedModelColor = new int[]{35104, 2, 35092, 35109, 38422, 43275, 38412};
                itemDef.newModelColor = new int[]{35104, 90, 35092, 35109, 90, 90, 90};
                break;
                
            case 14548:
                itemDef.copy(ItemDefinition.forID(17061));
                itemDef.name = "Light Sagittarian coif";
                itemDef.editedModelColor = new int[]{4396, 4410, 4406, 4397, 4503, 4495, 4390, 4427, 12581, 4381, 31425, 4512, 10527, 10536, 5192, 5180, 4519, 4493, 4378, 4389, 10526, 10523, 12592, 12573, 12566, 12569, 12562, 4523, 10517, 5158};
                itemDef.newModelColor = new int[]{90, 90, 90, 90, 80, 80, 90, 90, 80, 90, -1253, -1253, 80, 80, 80, 80, 80, 80, 80, 80, 80, 10523, 80, 90, 90, 90, 90, 90, 80, 80};
                break;
            case 14549:
                itemDef.copy(ItemDefinition.forID(17193));
                itemDef.name = "Light Sagittarian body";
                itemDef.editedModelColor = new int[]{4428, 4500, 4512, 4422, 4420, 4408, 4396, 10520, 4413, 4401, 10427, 4389, 10532, 10439, 10502, 10544, 11539, 10451, 11548, 4403, 4522, 4395, 4427, 4507, 4435, 4539, 255, 4416, 4428, 4515, 4535, 4504, 5390, 4419, 4415, 4410, 4449, 4433, 4519, 4526, 4409, 5394, 5389, 4503, 10529, 4511, 4392, 4421, 4531, 4391, 4513, 31425, 31453, 10536, 4423, 4493, 10542, 4498, 4397, 4529, 4525, 4385, 4414};
                itemDef.newModelColor = new int[]{90, 70, 70, 70, 70, 90, 70, 90, 70, 90, 90, 70, 90, 90, 90, 90, 90, 90, 90, 70, 90, 90, 70, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 70, 70, 70, 70, 70, 90, 90, 90, 90, 90, -1253, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80};
                break;
            case 14550:
                itemDef.copy(ItemDefinition.forID(17339));
                itemDef.name = "Light Sagittarian chaps";
                itemDef.editedModelColor = new int[]{4532, 4520, 4512, 4513, 9653, 4493, 4539, 4552, 31425, 4533, 4531, 4560, 31453, 4545, 4527, 11549, 4500, 4547, 11539, 10524, 10418, 11534, 10549, 10452, 10544, 4529, 10541, 10551, 10555, 10563, 10536, 4524, 10548, 4390, 4503, 4569, 10413, 10510, 10502, 255,};
                itemDef.newModelColor = new int[]{70, 70, 70, 90, 90, 90, 90, 90, -1253, 90, 90, 90, -1253, 90, 90, 70, 70, 90, 90, 90, 90, 70, 70, 70, 70, 90, 90, 70, 70, 70, 90, 70, 70, 70, 70, 90, 70, 70, 90, 90};
                break;
            case 14551:
                itemDef.copy(ItemDefinition.forID(17215));
                itemDef.name = "Light Sagittarian vambs";
                itemDef.editedModelColor = new int[]{4522, 4116, 10536, 4123};
                itemDef.newModelColor = new int[]{90, 80, 90, 90};
                break;
            case 14552:
                itemDef.copy(ItemDefinition.forID(17317));
                itemDef.name = "Light Sagittarian boots";
                itemDef.editedModelColor = new int[]{4497, 4491, 4500, 4383, 4514, 4510, 4523, 4361, 4513, 4505, 4373};
                itemDef.newModelColor = new int[]{80, 90, 80, 90, 90, 90, 90, 90, 90, 90, 90};
                break;
                
                
                // Dark saggitarian Hellraty
            case 22050:
                itemDef.copy(ItemDefinition.forID(17061));
                itemDef.name = "Dark Sagittarian coif";
                itemDef.editedModelColor = new int[]{4396, 4410, 4406, 4397, 4503, 4495, 4390, 4427, 12581, 4381, 31425, 4512, 10527, 10536, 5192, 5180, 4519, 4493, 4378, 4389, 10526, 10523, 12592, 12573, 12566, 12569, 12562, 4523, 10517, 5158};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, -1253, -1253, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 10523, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024};
                break;
            case 22051:
                itemDef.copy(ItemDefinition.forID(17193));
                itemDef.name = "Dark Sagittarian body";
                itemDef.editedModelColor = new int[]{4428, 4500, 4512, 4422, 4420, 4408, 4396, 10520, 4413, 4401, 10427, 4389, 10532, 10439, 10502, 10544, 11539, 10451, 11548, 4403, 4522, 4395, 4427, 4507, 4435, 4539, 255, 4416, 4428, 4515, 4535, 4504, 531024, 4419, 4415, 4410, 4449, 4433, 4519, 4526, 4409, 5394, 5389, 4503, 10529, 4511, 4392, 4421, 4531, 4391, 4513, 31425, 31453, 10536, 4423, 4493, 10542, 4498, 4397, 4529, 4525, 4385, 4414};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, -1253, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 80};
                break;
            case 22052:
                itemDef.copy(ItemDefinition.forID(17339));
                itemDef.name = "Dark Sagittarian chaps";
                itemDef.editedModelColor = new int[]{4532, 4520, 4512, 4513, 9653, 4493, 4539, 4552, 31425, 4533, 4531, 4560, 31453, 4545, 4527, 11549, 4500, 4547, 11539, 10524, 10418, 11534, 10549, 10452, 10544, 4529, 10541, 10551, 10555, 10563, 10536, 4524, 10548, 431024, 4503, 4569, 10413, 10510, 10502, 255,};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, -1253, 1024, 1024, 1024, -1253, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024};
                break;
            case 22053:
                itemDef.copy(ItemDefinition.forID(17215));
                itemDef.name = "Dark Sagittarian vambs";
                itemDef.editedModelColor = new int[]{4522, 4116, 10536, 4123};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 1024};
                break;
            case 22054:
                itemDef.copy(ItemDefinition.forID(17317));
                itemDef.name = "Dark Sagittarian boots";
                itemDef.editedModelColor = new int[]{4497, 4491, 4500, 4383, 4514, 4510, 4523, 4361, 4513, 4505, 4373};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024};
                break;
                
                // Halloween Light Sagittarian edition
            case 14553:
                itemDef.copy(ItemDefinition.forID(17061));
                itemDef.name = "Light Sagittarian coif (HW)";
                itemDef.editedModelColor = new int[]{4396, 4410, 4406, 4397, 4503, 4495, 4390, 4427, 12581, 4381, 31425, 4512, 10527, 10536, 5192, 5180, 4519, 4493, 4378, 4389, 10526, 10523, 12592, 12573, 12566, 12569, 12562, 4523, 10517, 5158};
                itemDef.newModelColor = new int[]{4024, 4024, 4024, 4024, 3024, 3024, 4024, 4024, 3024, 4024, -1253, -1253, 3024, 3024, 3024, 3024, 3024, 3024, 3024, 3024, 3024, 10523, 3024, 4024, 4024, 4024, 4024, 4024, 80, 80};
                break;
            case 14554:
                itemDef.copy(ItemDefinition.forID(17193));
                itemDef.name = "Light Sagittarian body (HW)";
                itemDef.editedModelColor = new int[]{4428, 4500, 4512, 4422, 4420, 4408, 4396, 10520, 4413, 4401, 10427, 4389, 10532, 10439, 10502, 10544, 11539, 10451, 11548, 4403, 4522, 4395, 4427, 4507, 4435, 4539, 255, 4416, 4428, 4515, 4535, 4504, 534024, 4419, 4415, 4410, 4449, 4433, 4519, 4526, 4409, 5394, 5389, 4503, 10529, 4511, 4392, 4421, 4531, 4391, 4513, 31425, 31453, 10536, 4423, 4493, 10542, 4498, 4397, 4529, 4525, 4385, 4414};
                itemDef.newModelColor = new int[]{4024, 3024, 3024, 3024, 3024, 4024, 3024, 4024, 3024, 4024, 4024, 3024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 3024, 4024, 4024, 3024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 3024, 3024, 3024, 3024, 3024, 4024, 4024, 4024, 4024, 4024, -1253, 3024, 3024, 3024, 3024, 3024, 3024, 3024, 3024, 3024, 3024, 3024};
                break;
            case 14555:
                itemDef.copy(ItemDefinition.forID(17339));
                itemDef.name = "Light Sagittarian chaps (HW)";
                itemDef.editedModelColor = new int[]{4532, 4520, 4512, 4513, 9653, 4493, 4539, 4552, 31425, 4533, 4531, 4560, 31453, 4545, 4527, 11549, 4500, 4547, 11539, 10524, 10418, 11534, 10549, 10452, 10544, 4529, 10541, 10551, 10555, 10563, 10536, 4524, 10548, 434024, 4503, 4569, 10413, 10510, 10502, 255,};
                itemDef.newModelColor = new int[]{3024, 3024, 3024, 4024, 4024, 4024, 4024, 4024, -1253, 4024, 4024, 4024, -1253, 4024, 4024, 3024, 3024, 4024, 4024, 4024, 4024, 3024, 3024, 3024, 3024, 4024, 4024, 3024, 3024, 3024, 4024, 3024, 3024, 3024, 3024, 4024, 3024, 3024, 4024, 4024};
                break;
            case 14556:
                itemDef.copy(ItemDefinition.forID(17215));
                itemDef.name = "Light Sagittarian vambs (HW)";
                itemDef.editedModelColor = new int[]{4522, 4116, 10536, 4123};
                itemDef.newModelColor = new int[]{4024, 3024, 4024, 4024};
                break;
            case 14557:
                itemDef.copy(ItemDefinition.forID(17317));
                itemDef.name = "Light Sagittarian boots (HW)";
                itemDef.editedModelColor = new int[]{4497, 4491, 4500, 4383, 4514, 4510, 4523, 4361, 4513, 4505, 4373};
                itemDef.newModelColor = new int[]{3024, 4024, 3024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024};
                break;
                
            case 2022:
                itemDef.copy(ItemDefinition.forID(4565));
                itemDef.name = "Evil Basket of eggs";
                itemDef.editedModelColor = new int[]{960, 0, 22464, 55232, 33728, 43968, 9672, 476, 127};
                itemDef.newModelColor = new int[]{960, 4024, 3024, 4024, 3024, 43968, 4361, 476, 127};
                break;
                
            	
            case 18620: 
            	itemDef.copy(ItemDefinition.forID(8151));
            	itemDef.name = "Prize chest";
            	itemDef.actions = new String[5];
            	itemDef.actions[0] = "Open";
            	itemDef.modelID = 114;
            	itemDef.editedModelColor =new int[]{ 40 };
            	itemDef.newModelColor = new int[]{ 65 };
            	itemDef.dataType = DataType.CUSTOM;
            	break;
                
            case 15100:
                itemDef.copy(ItemDefinition.forID(52324));
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.name = "Ghrazi rapier X";
                itemDef.description = "It is the Ghrazi Rapier X";
                itemDef.editedModelColor = new int[] { 64414, 43150, 43034, 43047, 43059, 43090, 43113, 43030, 43047 };
                itemDef.newModelColor = new int[] { 64414, 43150, 43034, 43047, 43059, -1253, -1253, -1253, 43047 };
                break;
                
            case 21031:
                itemDef.copy(ItemDefinition.forID(1481));
                itemDef.name = "Orb of ice";
                itemDef.editedModelColor = new int[] { 1500 };
                itemDef.newModelColor = new int[] { 689484 };
                break;
                
            case 21032:
                itemDef.copy(ItemDefinition.forID(4825));
                itemDef.name = "Unstrung ice bow";
                itemDef.editedModelColor = new int[] { 5681, 10580, 5797, 5557, 5673, 5561};
                itemDef.newModelColor = new int[] { 689484, 10580, 689484, 689484, 689484, 689484};
                break;
                
            case 21033:
                itemDef.copy(ItemDefinition.forID(962));
                itemDef.name = "Dynamite box";
                itemDef.actions[0] = "Open";
                itemDef.modelID = 29;
                itemDef.dataType = DataType.CUSTOM;
                break;
            case 21535:
            	itemDef.copy(ItemDefinition.forID(9850));
            	itemDef.name = "@red@CNY 2021 Owner Box";
                itemDef.actions[0] = "Open";
                itemDef.editedModelColor = new int[] { 5281, 8514, 5268, 9878, 5272, 12393, 5276, 5388 };
                itemDef.newModelColor = new int[] { 9087, 933, 9087, 933, 933, 9087, 9087, 9087 };
                break;
            case 21536:
            	itemDef.copy(ItemDefinition.forID(10834));
            	itemDef.name = "@red@CNY 2021 Mystery Bag";
                itemDef.actions[0] = "Open";
                itemDef.editedModelColor = new int[] { 8128, 7616, 4626, 7091, 6067 };
                itemDef.newModelColor = new int[] { 9087, 9087, 933, 9087, 9087 };
                break;
            case 11222:
                itemDef.name = "Frost fire arrow";
                itemDef.editedModelColor = new int[] { 929, 914, 788, 68, 918, 937, 7081, 922, 924};
                itemDef.newModelColor = new int[] {  929, 914, 788, 689484, 918, 689484, 689484, 922, 924};
                break;
                
            case 11223:
                itemDef.name = "Frost fire arrow";
                itemDef.editedModelColor = new int[] { 929, 914, 788, 68, 918, 937, 7081, 922, 924};
                itemDef.newModelColor = new int[] {  929, 914, 788, 689484, 918, 689484, 689484, 922, 924};
                break;
                
            case 11224:
                itemDef.name = "Frost fire arrow";
                itemDef.editedModelColor = new int[] { 929, 914, 788, 68, 918, 937, 7081, 922, 924};
                itemDef.newModelColor = new int[] {  929, 914, 788, 689484, 918, 689484, 689484, 922, 924};
                break;
                
            case 11225:
                itemDef.name = "Frost fire arrow";
                itemDef.editedModelColor = new int[] { 929, 914, 788, 68, 918, 937, 7081, 922, 924};
                itemDef.newModelColor = new int[] {  929, 914, 788, 689484, 918, 689484, 689484, 922, 924};
                break;
                
            case 11226:
                itemDef.name = "Frost fire arrow";
                itemDef.editedModelColor = new int[] { 929, 914, 788, 68, 918, 937, 7081, 922, 924};
                itemDef.newModelColor = new int[] {  929, 914, 788, 689484, 918, 689484, 689484, 922, 924};
                break;
                
            case 20202:
                itemDef.copy(ItemDefinition.forID(11046));
                itemDef.name = "Dark rope";
                itemDef.editedModelColor = new int[] { 6806, 6558 };
                itemDef.newModelColor = new int[] { 6806, 1024 };
                break;
                
            case 20203:
                itemDef.copy(ItemDefinition.forID(11046));
                itemDef.name = "Bandit rope";
                itemDef.editedModelColor = new int[] { 6806, 6558 };
                itemDef.newModelColor = new int[] { 6806, 32883 };
                break;
                
            case 20204:
                itemDef.copy(ItemDefinition.forID(11046));
                itemDef.name = "Kurask rope";
                itemDef.editedModelColor = new int[] { 6806, 6558 };
                itemDef.newModelColor = new int[] { 6806, -1253 };
                break;
                
            case 20205:
                itemDef.copy(ItemDefinition.forID(11046));
                itemDef.name = "Mole rope";
                itemDef.editedModelColor = new int[] { 6806, 6558 };
                itemDef.newModelColor = new int[] { 6806, -1053 };
                break;
                
            case 20206:
                itemDef.copy(ItemDefinition.forID(11046));
                itemDef.name = "Corp rope";
                itemDef.editedModelColor = new int[] { 6806, 6558 };
                itemDef.newModelColor = new int[] { -1253, 37208 };
                break;
                
            case 20207:
                itemDef.copy(ItemDefinition.forID(11046));
                itemDef.name = "Bunny rope";
                itemDef.editedModelColor = new int[] { 6806, 6558 };
                itemDef.newModelColor = new int[] { 40090, 40090 };
                break;
                
            case 14444:
                itemDef.copy(ItemDefinition.forID(19640));
                itemDef.name = "Blood orb";
                itemDef.description = "It is Verzik's blood orb";
                itemDef.editedModelColor = new int[] { 36048, 36977, 37208 };
                itemDef.newModelColor = new int[] { -1253, -1253, -1253 };
                break;
                
            case 14514:
                itemDef.copy(ItemDefinition.forID(14415));
                itemDef.name = "Super combat flask (6)";
                itemDef.editedModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 40108, 40109, 41134, 40110, 41135, 40111, 40112, 40113, 41137, 41138, 40115, 33715, 41140, 40117, 41141, 40118, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142 };
                itemDef.newModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142 };
                break;
                
            case 14515:
                itemDef.copy(ItemDefinition.forID(14514));
                itemDef.toCustomNote(14514);
                break;
                
            case 14512:
                itemDef.copy(ItemDefinition.forID(14413));
                itemDef.name = "Super combat flask (5)";
                itemDef.editedModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 40108, 40109, 41134, 40110, 41135, 40111, 40112, 40113, 41137, 41138, 40115, 33715, 41140, 40117, 41141, 40118, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42095 };
                itemDef.newModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 40108, 40109, 41134, 40110, 41135, 40111, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42095 };
                break;
                
            case 14513:
                itemDef.copy(ItemDefinition.forID(14512));
                itemDef.toCustomNote(14512);
                break;
                
            case 14510:
                itemDef.copy(ItemDefinition.forID(14411));
                itemDef.name = "Super combat flask (4)";
                itemDef.editedModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 40108, 40109, 41134, 40110, 41135, 40111, 40112, 40113, 41137, 41138, 40115, 33715, 41140, 40117, 41141, 40118, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42111 };
                itemDef.newModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 40108, 40109, 41134, 40110, 41135, 40111, 40112, 40113, 41137, 41138, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42111 };
                break;
                
            case 14511:
                itemDef.copy(ItemDefinition.forID(14510));
                itemDef.toCustomNote(14510);
                break;
                
            case 14508:
                itemDef.copy(ItemDefinition.forID(14409));
                itemDef.name = "Super combat flask (3)";
                itemDef.editedModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 40108, 40109, 41134, 40110, 41135, 40111, 40112, 40113, 41137, 41138, 33715, 40115, 41140, 40117, 41141, 40118, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42095 };
                itemDef.newModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 40108, 40109, 41134, 40110, 41135, 40111, 40112, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42095, };
                break;
                
            case 14509:
                itemDef.copy(ItemDefinition.forID(14508));
                itemDef.toCustomNote(14508);
                break;
                
            case 14506:
                itemDef.copy(ItemDefinition.forID(14407));
                itemDef.name = "Super combat flask (2)";
                itemDef.editedModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 40108, 40109, 41134, 40110, 41135, 40111, 40112, 40113, 41137, 41138, 33715, 40115, 41140, 40117, 41141, 40118, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42095 };
                itemDef.newModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 40113, 22454, 22454, 22454, 22454, 22454, 22454, 41141, 40118, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42095 };
                break;
                
            case 14507:
                itemDef.copy(ItemDefinition.forID(14506));
                itemDef.toCustomNote(14506);
                break;
                
            case 14504:
                itemDef.copy(ItemDefinition.forID(14405));
                itemDef.name = "Super combat flask (1)";
                itemDef.editedModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 40104, 681, 40105, 40107, 40108, 40109, 41134, 40110, 41135, 40111, 40112, 40113, 41137, 41138, 40115, 33715, 41140, 40117, 41141, 40118, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42095 };
                itemDef.newModelColor = new int[] { 650, 656, 663, 40090, 40091, 667, 39964, 668, 40094, 40095, 40096, 673, 674, 676, 42149, 40101, 40102, 679, 680, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 22454, 40118, 41142, 40119, 40120, 41144, 40121, 41147, 40124, 41148, 40125, 40127, 40129, 41154, 40137, 40138, 40141, 40142, 42095 };
                break;
                
            case 14505:
                itemDef.copy(ItemDefinition.forID(14504));
                itemDef.toCustomNote(14504);
                break;
                
            case 4736:
            case 17215:
                itemDef.femaleEquip1 = itemDef.maleEquip1;
                itemDef.femaleEquip2 = itemDef.femaleEquip2;
                break;
                
            case 14023:
                itemDef.copy(ItemDefinition.forID(14008));
                itemDef.editedModelColor = new int[]{12, 45, 90, 30, 40, 25, 31, 34, 35, 80, 56, 24, 18, 26, 15, 20, 37, 58, 55, 64, 50, 11, 21, 33, 43, 41, 44, 38, 79, 27, 81, 70, 32, 36, 39, 74, 46, 29, 88};
                itemDef.newModelColor = new int[]{4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024};
                break;

            case 14024:
                itemDef.copy(ItemDefinition.forID(14009));
                itemDef.editedModelColor = new int[]{48949, 47, 17, 19, 63, 6, 15, 13, 32787, 42, 18, 24, 91, 20, 255, 29, 12, 46, 55, 41, 31, 39, 32, 22, 67, 27, 32783, 32790, 547, 34, 554, 25, 100, 49, 80, 40, 81, 48724, 7, 48701, 48670, 77, 110, 48681, 48687, 68, 54};
                itemDef.newModelColor = new int[]{4024, 3024, 2024, 3024, 4024, 3024, 2024, 3024, 4024, 3024, 2024, 3024, 4024, 3024, 2024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024, 4024};
                break;

            case 14025:
                itemDef.copy(ItemDefinition.forID(14010));
                itemDef.editedModelColor = new int[]{13, 53385, 19, 22, 49, 81, 34, 25, 53387, 53378, 14, 12, 70, 100, 90, 33810, 42, 53506};
                itemDef.newModelColor = new int[]{4024, 3024, 3024, 4024, 3024, 3024, 4024, 3024, 3024, 4024, 3024, 3024, 3024, 3024, 3024, 3024, 3024, 3024};
                break;

            case 14026:
                itemDef.copy(ItemDefinition.forID(52326));
                itemDef.editedModelColor = new int[]{6709, 6736, 0, 59453, 6602, 59466, 59441};
                itemDef.newModelColor = new int[]{3024, 3024, 3024, 3024, 3024, 3024, 3024};
                itemDef.name = "HW " + itemDef.name;
                break;

            case 14027:
                itemDef.copy(ItemDefinition.forID(52327));
                itemDef.editedModelColor = new int[]{12, 0, 59466, 59453, 6602, 59441, 43294, 43414, 6709, 6699, 6736, 43299, 59474, 3346, 268, 278};
                itemDef.newModelColor = new int[]{12, 3024, 3024, 4024, 3024, 4024, 4024, 3024, 4024, 4024, 4024, 3024, 3024, 3346, 3024, 3024};
                itemDef.name = "HW " + itemDef.name;
                break;

            case 14028:
                itemDef.copy(ItemDefinition.forID(52328));
                itemDef.editedModelColor = new int[]{12, 268, 59466, 59441, 43294, 43299, 6602, 6736, 22, 16, 6699, 54307, 59445, 59453, 0, 6709, 43303};
                itemDef.newModelColor = new int[]{4024, 4024, 3024, 4024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 4024, 3024, 3024, 4024, 4024};
                itemDef.name = "HW " + itemDef.name;
                break;

            case 14029:
                itemDef.copy(ItemDefinition.forID(51018));
                itemDef.editedModelColor = new int[]{6973, 43544, 43301, 6323, 6331, 5268, 6340, 6348, 6356, 6364};
                itemDef.newModelColor = new int[]{1024, 4024, 4024, 1024, 1024, 1024, 1024, 1024, 1024, 1024};
                itemDef.name = "Dark Ancestral hat";
                break;

            case 14030:
                itemDef.copy(ItemDefinition.forID(51021));
                itemDef.editedModelColor = new int[]{12, 49218, 6348, 6331, 43311, 7108, 49197, 6973, 6323, 43305, 43301, 5268, 49209};
                itemDef.newModelColor = new int[]{1024, 4024, 1024, 1024, 4024, 1024, 4024, 1024, 1024, 4024, 4024, 1024, 4024};
                itemDef.name = "Dark " + itemDef.name;
                break;

            case 14031:
                itemDef.copy(ItemDefinition.forID(51024));
                itemDef.editedModelColor = new int[]{49197, 6348, 12, 49209, 6331, 49218, 43311, 43301, 43305, 6323};
                itemDef.newModelColor = new int[]{4024, 1024, 1024, 4024, 1024, 4024, 4024, 4024, 4024, 1024};
                itemDef.name = "HW " + itemDef.name;
                break;

            case 14032:
                itemDef.copy(ItemDefinition.forID(10604));
                itemDef.editedModelColor = new int[]{5590, 3171, 12};
                itemDef.newModelColor = new int[]{1024, 1024, 1024};
                itemDef.name = "HW " + itemDef.name;
                break;

            case 14033:
                itemDef.copy(ItemDefinition.forID(10724));
                itemDef.editedModelColor = new int[]{70, 127, 0};
                itemDef.newModelColor = new int[]{5024, 90, 5024};
                itemDef.name = "HW " + itemDef.name;
                break;
            case 14034:
                itemDef.copy(ItemDefinition.forID(10725));
                itemDef.editedModelColor = new int[]{0, 127, 70};
                itemDef.newModelColor = new int[]{5024, 90, 5024};
                itemDef.name = "HW " + itemDef.name;
                break;
            case 14035:
                itemDef.copy(ItemDefinition.forID(10726));
                itemDef.editedModelColor = new int[]{0, 127, 70};
                itemDef.newModelColor = new int[]{5024, 90, 5024};
                itemDef.name = "HW " + itemDef.name;
                break;
            case 14036:
                itemDef.copy(ItemDefinition.forID(10727));
                itemDef.editedModelColor = new int[]{0, 127, 59515, 70};
                itemDef.newModelColor = new int[]{5024, 90, 1024, 90};
                itemDef.name = "HW " + itemDef.name;
                break;
            case 14037:
                itemDef.copy(ItemDefinition.forID(10728));
                itemDef.editedModelColor = new int[]{59515, 24, 0, 70};
                itemDef.newModelColor = new int[]{5024, 1024, 4024, 4024};
                itemDef.name = "HW " + itemDef.name;
                break;

            case 14038:
                itemDef.copy(ItemDefinition.forID(10346));
                itemDef.editedModelColor = new int[]{33030, 74, 90, 86, 57, 66};
                itemDef.newModelColor = new int[]{8024, 8024, 4024, 8024, 8024, 4024};
                itemDef.name = "HW " + itemDef.name;
                break;

            case 14039:
                itemDef.copy(ItemDefinition.forID(10348));
                itemDef.editedModelColor = new int[]{74, 57, 86, 90, 66, 33030, 10262};
                itemDef.newModelColor = new int[]{8024, 8024, 8024, 8024, 8024, 8024, 10262};
                itemDef.name = "HW " + itemDef.name;
                break;

            case 14040:
                itemDef.copy(ItemDefinition.forID(10350));
                itemDef.editedModelColor = new int[]{78, 86, 94, 33030};
                itemDef.newModelColor = new int[]{4024, 4024, 8024, 8024};
                itemDef.name = "HW " + itemDef.name;
                break;
                
            case 14041:
                itemDef.copy(ItemDefinition.forID(10352));
                itemDef.editedModelColor = new int[]{28, 37, 20, 43150, 90, 43146, 21766, 43270};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 4024, 1024, 4024, 21766, 4024};
                itemDef.name = "HW " + itemDef.name;
                break;
                
            case 14042:
                itemDef.copy(ItemDefinition.forID(10346));
                itemDef.editedModelColor = new int[]{33030, 74, 90, 86, 57, 66};
                itemDef.newModelColor = new int[]{4024, 4024, 4024, 4024, 4024, 4024};
                itemDef.name = "HW " + itemDef.name;
                break;

            case 14043:
                itemDef.copy(ItemDefinition.forID(10348));
                itemDef.editedModelColor = new int[]{74, 57, 86, 90, 66, 33030, 10262};
                itemDef.newModelColor = new int[]{4024, 4024, 4024, 4024, 4024, 4024, 10262};
                itemDef.name = "HW" + itemDef.name;
                break;

           /* case 14044:
                itemDef.copy(ItemDefinition.forID(10350));
                itemDef.editedModelColor = new int[]{78, 86, 94, 33030};
                itemDef.newModelColor = new int[]{4024, 4024, 4024, 4024};
                itemDef.name = "HW " + itemDef.name;
                break; */
                
            case 14932:
                itemDef.copy(ItemDefinition.forID(6830));
                itemDef.editedModelColor = new int[]{127, 11187};
                itemDef.newModelColor = new int[]{4024, 1024};
                itemDef.name = "Halloween box 2020";
                break;
                
            case 14933:
                itemDef.copy(ItemDefinition.forID(6830));
                itemDef.editedModelColor = new int[]{127, 11187};
                itemDef.newModelColor = new int[]{689484, 687484};
                itemDef.name = "Supreme box";
                break;  
                
            case 19720:
                itemDef.copy(ItemDefinition.forID(6830));
                itemDef.editedModelColor = new int[]{127, 11187};
                itemDef.newModelColor = new int[]{689484, 689584};
                itemDef.name = "Crystal Box";
                break;
                
            case 14934:
                itemDef.copy(ItemDefinition.forID(6830));
                itemDef.editedModelColor = new int[]{127, 11187};
                itemDef.newModelColor = new int[]{1566, 32883};
                itemDef.name = "Christmas box";
                break;
                
            case 15650:
                itemDef.copy(ItemDefinition.forID(6830));
                itemDef.editedModelColor = new int[]{127, 11187};
                itemDef.newModelColor = new int[]{1666, 32883};
                itemDef.name = "Santa's Owner Box";
                break;
                
            case 14935:
                itemDef.copy(ItemDefinition.forID(6830));
                itemDef.editedModelColor = new int[]{127, 11187};
                itemDef.newModelColor = new int[]{1024, 461770};
                itemDef.name = "Owner's ultra box";
                break;
                
            case 14482:
                itemDef.copy(ItemDefinition.forID(6830));
                itemDef.editedModelColor = new int[]{127, 11187};
                itemDef.newModelColor = new int[]{689184, 689484};
                itemDef.name = "Kevin's owner box";
                break;
                
			case 6200:
				itemDef.copy(ItemDefinition.forID(6199));
				itemDef.name = "Mystery Box";
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Open";
				itemDef.animateInventory = true;
				itemDef.modelZoom = 2200;
				itemDef.rotationY = 120;
				itemDef.rotationX = 1800;
				itemDef.modelOffsetX = 0;
				itemDef.modelOffset1 = 40;
				itemDef.modelOffsetY = -20;
				itemDef.dataType = DataType.CUSTOM;
				itemDef.modelID = 93;
				break;

			case 6201:
				itemDef.copy(ItemDefinition.forID(6830));
				itemDef.name = "Mystery Box";
				itemDef.modelZoom = 3100;
				itemDef.animateInventory = true;
				itemDef.dataType = DataType.CUSTOM;
				itemDef.modelID = 94;
				break;
                
            case 6835:
                itemDef.copy(ItemDefinition.forID(6830));
                itemDef.editedModelColor = new int[]{127, 11187};
                itemDef.newModelColor = new int[]{90, 1024};
                itemDef.name = "@bla@Onyx box";
                break;

            case 14914:
                itemDef.copy(ItemDefinition.forID(50095));
                itemDef.editedModelColor = new int[]{960};
                itemDef.newModelColor = new int[]{1024};
                itemDef.name = "Dark " + itemDef.name;
                break;
            case 14915:
                itemDef.copy(ItemDefinition.forID(50098));
                itemDef.editedModelColor = new int[]{960};
                itemDef.newModelColor = new int[]{1024};
                itemDef.name = "Dark " + itemDef.name;
                break;
            case 14916:
                itemDef.copy(ItemDefinition.forID(50101));
                itemDef.editedModelColor = new int[]{960};
                itemDef.newModelColor = new int[]{1024};
                itemDef.name = "Dark " + itemDef.name;
                break;
            case 14917:
                itemDef.copy(ItemDefinition.forID(50104));
                itemDef.editedModelColor = new int[]{960};
                itemDef.newModelColor = new int[]{1024};
                itemDef.name = "Dark " + itemDef.name;
                break;
            case 14918:
                itemDef.copy(ItemDefinition.forID(50107));
                itemDef.editedModelColor = new int[]{960};
                itemDef.newModelColor = new int[]{1024};
                itemDef.name = "Dark " + itemDef.name;
                break;

            case 14919:
                itemDef.copy(ItemDefinition.forID(41863));
                itemDef.editedModelColor = new int[]{6067, 947, 55217, 11187, 17331, 43955, 27571, 38835};
                itemDef.newModelColor = new int[]{1024, 947, 1024, 11187, 1024, 43955, 1024, 38835};
                itemDef.name = "Dark " + itemDef.name;
                break;

            case 14920:
                itemDef.copy(ItemDefinition.forID(42399));
                itemDef.editedModelColor = new int[]{ 43963, 32883, 33};
                itemDef.newModelColor = new int[]{ 4024, 4024, 4024};
                itemDef.name = "Halloween " + itemDef.name;
                break;

            case 14922:
                itemDef.copy(ItemDefinition.forID(51003));
                itemDef.editedModelColor = new int[]{5056, 8125, 16, 0, 33, 20};
                itemDef.newModelColor = new int[]{32883, 32883, 0, 0, 0, 0};
                itemDef.name = "Dark " + itemDef.name;
                break;

            case 14923:
                itemDef.copy(ItemDefinition.forID(42603));
                itemDef.editedModelColor = new int[]{43177, 43105, 46249, 5281, 935, 43181, 43189, 43185, 39013, 41135, 3235};
                itemDef.newModelColor = new int[]{43177, 43105, 46249, 32883, 32883, 43181, 43189, 43185, 39013, 41135, 32883};
                itemDef.name = "Ring of Bosses (i)";
                break;
                
            case 8471:
                itemDef.copy(ItemDefinition.forID(52517));
                itemDef.editedModelColor = new int[]{51515, 51484,};
                itemDef.newModelColor = new int[]{32883, 51484};
                itemDef.name = "Verzik's crystal shard (t)";
                break;
                
            case 16510:
                itemDef.copy(ItemDefinition.forID(50911));
                itemDef.name = "Endarkened juice";
                itemDef.editedModelColor = new int[]{66, 106, 74, 82, 10335, };
                itemDef.newModelColor = new int[]{10335, 10335, 74, 10335, 10335, };
                break;

            case 14924:
                itemDef.copy(ItemDefinition.forID(13740));
                itemDef.editedModelColor = new int[]{15945, 15919, 15916, 15922, 15925, 15951, 15874, 15885, 15928, 105, 15880, 15913, 384};
                itemDef.newModelColor = new int[]{32883, 32883, 32883, 32883, 32883, 32883, 32883, 32883, 32883, 908, 32883, 32883, 908};
                itemDef.name = "Light demonic spirit shield";
                break;

            case 14925:
                itemDef.copy(ItemDefinition.forID(41860));
                itemDef.editedModelColor = new int[]{8396, 8417, 5293, 20};
                itemDef.newModelColor = new int[]{4024, 3024, 4024, 3024};
                itemDef.name = "Halloween " + itemDef.name;
                break;
                
            case 10900:
                itemDef.copy(ItemDefinition.forID(41860));
                itemDef.editedModelColor = new int[]{8396, 8417, 5293, 20};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 1024};
                itemDef.name = "8=====D";
                break;
                
            case 10904:
                itemDef.copy(ItemDefinition.forID(41860));
                itemDef.editedModelColor = new int[]{8396, 8417, 5293, 20};
                itemDef.newModelColor = new int[]{689484, 689484, 689484, 689484};
                itemDef.name = "Light " + itemDef.name;
                break;
                
            case 10901:
                itemDef.copy(ItemDefinition.forID(14008));
                itemDef.editedModelColor = new int[]{12, 45, 90, 30, 40, 25, 31, 34, 35, 80, 56, 24, 18, 26, 15, 20, 37, 58, 55, 64, 50, 11, 21, 33, 43, 41, 44, 38, 79, 27, 81, 70, 32, 36, 39, 74, 46, 29, 88};
                itemDef.newModelColor = new int[]{689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484};
                break;

            case 10902:
                itemDef.copy(ItemDefinition.forID(14009));
                itemDef.editedModelColor = new int[]{48949, 47, 17, 19, 63, 6, 15, 13, 32787, 42, 18, 24, 91, 20, 255, 29, 12, 46, 55, 41, 31, 39, 32, 22, 67, 27, 32783, 32790, 547, 34, 554, 25, 100, 49, 80, 40, 81, 48724, 7, 48701, 48670, 77, 110, 48681, 48687, 68, 54};
                itemDef.newModelColor = new int[]{689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484};
                break;

            case 10903:
                itemDef.copy(ItemDefinition.forID(14010));
                itemDef.editedModelColor = new int[]{13, 53385, 19, 22, 49, 81, 34, 25, 53387, 53378, 14, 12, 70, 100, 90, 33810, 42, 53506};
                itemDef.newModelColor = new int[]{689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484};
                break;

            case 14926:
                itemDef.copy(ItemDefinition.forID(9920));
                itemDef.editedModelColor = new int[]{6963, 6959, 7083, 7073, 2700, 7081, 21539, 10266};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 1024, 4024, 1024, 4024, 1024};
                itemDef.name = "Dark " + itemDef.name;
                break;

            case 14927:
                itemDef.copy(ItemDefinition.forID(15422));
                itemDef.editedModelColor = new int[]{37977, 37973, 37969, 37965, 37960, 37949, 37903, 43053, 43028, 43078, 43048, 43058};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 1024, 1024, 1024, 40, 1024, 1024, 40, 1024, 40};
                itemDef.name = "Dark " + itemDef.name;
                break;

            case 14928:
                itemDef.copy(ItemDefinition.forID(15423));
                itemDef.editedModelColor = new int[]{37974, 37949, 37971, 37938, 37954, 37966, 37977, 37956, 37953, 37933, 37943, 37952, 37955, 37958, 37946, 37948, 37969, 37960, 37957, 37951, 37963, 37968, 43038, 43048, 43053, 43058, 43078, 43044, 37961};
                itemDef.newModelColor = new int[]{1024, 40, 1024, 40, 1024, 40, 1024, 40, 1024, 40, 1024, 40, 1024, 40, 1024, 40, 40, 1024, 40, 1024, 40, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024};
                itemDef.name = "Dark " + itemDef.name;
                break;
            case 14929:
                itemDef.copy(ItemDefinition.forID(15425));
                itemDef.editedModelColor = new int[]{43078, 43058, 43038, 43053, 37946, 37943, 37933, 37949, 37952, 37955, 37958, 37961, 37964, 37928, 37923, 43048, 43044, 37918};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 1024, 1024, 40, 1024, 40, 1024, 40, 1024, 40, 1024, 40, 1024, 1024, 1024, 1024};
                itemDef.name = "Dark " + itemDef.name;
                break;

            case 14930:
                itemDef.copy(ItemDefinition.forID(9470));
                itemDef.editedModelColor = new int[]{119, 103};
                itemDef.newModelColor = new int[]{1024, 4024};
                itemDef.name = "Dark " + itemDef.name;
                break;

            case 14931:
                itemDef.copy(ItemDefinition.forID(1419));
                itemDef.editedModelColor = new int[]{7073, 61};
                itemDef.newModelColor = new int[]{1024, 61};
                itemDef.name = "Dark " + itemDef.name;
                break;
                
            case 10867:
                itemDef.copy(ItemDefinition.forID(1419));
                itemDef.editedModelColor = new int[]{7073, 61};
                itemDef.newModelColor = new int[]{4024, 61};
                itemDef.name = "Dark " + itemDef.name;
                break;

            case 19123:
                itemDef.copy(ItemDefinition.forID(15335));
                itemDef.toCustomNote(15335);
                break;

            case 19124:
                itemDef.copy(ItemDefinition.forID(15334));
                itemDef.toCustomNote(15334);
                break;
            case 19125:
                itemDef.copy(ItemDefinition.forID(15333));
                itemDef.toCustomNote(15333);
                break;
            case 19126:
                itemDef.copy(ItemDefinition.forID(15332));
                itemDef.toCustomNote(15332);
                break;

            case 14910:
                itemDef.copy(ItemDefinition.forID(13660));
                itemDef.name = "Flame gloves (e)";
                itemDef.editedModelColor = new int[]{5566, 5561, 803, 8128, 8119, 8114, 8124, 793, 8109};
                itemDef.newModelColor = new int[]{90, 926, 90, 90, 90, 90, 90, 926, 70};
                break;
                
            case 10905:
            itemDef.copy(ItemDefinition.forID(13660));
            itemDef.name = "Sky blue Flame gloves (e)";
            itemDef.editedModelColor = new int[]{5566, 5561, 803, 8128, 8119, 8114, 8124, 793, 8109};
            itemDef.newModelColor = new int[]{689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484, 689484};
            break;

            case 14911:
                itemDef.copy(ItemDefinition.forID(15241));
                itemDef.editedModelColor = new int[]{35868, 35895, 35912, 35865, 35882, 35875, 35888, 35905, 35856, 35873, 35866, 6303, 35840, 35862, 35855, 6317, 35886, 2566, 2566, 2566, 1097, 54, 49, 39, 32, 64, 59, 45, 31, 40, 50, 23, 85, 6303};
                itemDef.newModelColor = new int[]{1566, 1566, 1566, 1566, 1566, 1566, 1566, 1566, 1566, 2566, 2566, 2566, 2566, 2566, 2566, 2566, 2566, 2566, 2566, 2566, 2566, 54, 49, 39, 32, 64, 59, 45, 31, 40, 50, 23, 85, 6303};
                itemDef.name = "Hand cannon X";
                break;

            case 14912:
                itemDef.copy(ItemDefinition.forID(11614));
                itemDef.editedModelColor = new int[]{40023, 40036, 1822, 36, 25, 34243, 9230, 40040, 10348, 48, 34251, 0, 43335, 6218, 23, 11013};
                itemDef.newModelColor = new int[]{1024, 1024, 1024, 90, 90, 1024, 90, 1024, 1024, 90, 1024, 1024, 1024, 1024, 90, 1024};
                itemDef.name = "Death Cape";
                break;

            case 14913:
                itemDef.copy(ItemDefinition.forID(11614));
                itemDef.editedModelColor = new int[]{40023, 40036, 1822, 36, 25, 34243, 9230, 40040, 10348, 48, 34251, 0, 43335, 6218, 23, 11013};
                itemDef.newModelColor = new int[]{90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 70};
                itemDef.name = "Angelic Cape";
                break;  
                
            case 11620:
                itemDef.copy(ItemDefinition.forID(15241));
                itemDef.editedModelColor = new int[]{35868, 35895, 35912, 35865, 35882, 35875, 35888, 35905, 35856, 35873, 35866, 6303, 35840, 35862, 35855, 6317, 35886, 2566, 2566, 2566, 1097, 54, 49, 39, 32, 64, 59, 45, 31, 40, 50, 23, 85, 6303};
                itemDef.newModelColor = new int[]{90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 32, 90, 90, 45, 31, 40, 90, 90, 90, 90};
                itemDef.name = "Hand cannon XI";
                break;
                
            case 15045:
                itemDef.copy(ItemDefinition.forID(15243));
                itemDef.editedModelColor = new int[]{7223, 7467, 6430, 6798};
                itemDef.newModelColor = new int[]{7223, 90, 90, 6798};
                itemDef.name = "Hand cannon XI shot";
                break;
                
            case 10164:
                itemDef.copy(ItemDefinition.forID(50366));
                itemDef.name = "Amulet of torture (or)";
                break;
                
            case 10170:
                itemDef.copy(ItemDefinition.forID(11614));
                itemDef.editedModelColor = new int[]{40023, 40036, 1822, 36, 25, 34243, 9230, 40040, 10348, 48, 34251, 0, 43335, 6218, 23, 11013};
                itemDef.newModelColor = new int[]{685484, 685484, 685484, 685484, 685484, 685484, 685484, 685484, 685484, 685484, 685484, 685484, 685484, 685484, 685484, 685484};
                itemDef.name = "Dragonstone Cape ";
                break;

        }
    }

}
