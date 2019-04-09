package com.simplicity.client.cache.definitions.custom;

import com.simplicity.client.cache.definitions.ItemDefinition;

public class CustomItems {

    public static void loadDefinition(ItemDefinition itemDef) {
        switch(itemDef.id) {
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
                itemDef.editedModelColor = new int[] { 16, 24, 33, 13223, 14236 };
                itemDef.newModelColor = new int[] { 4024, 4024, 7073, 4024, 4024 };
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
                itemDef.editedModelColor = new int[] { 16, 24, 33, 13223, 14236 };
                itemDef.newModelColor = new int[] { 1024, 1024, 937, 1024, 1024 };
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
                itemDef.editedModelColor = new int[] { 784, 790, 796, 536, 61, 78, 49 };
                itemDef.newModelColor = new int[] { -1253, -1253, -1253, -1253, -1253, -1253, -1253 };
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
                itemDef.editedModelColor = new int[] { 584, 590, 596, 836, 61, 78, 49 };
                itemDef.newModelColor = new int[] { 14573, 14573, 14573, 14573, 14573, 14573, 14573 };
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

            case 14545:
                itemDef.copy(ItemDefinition.forID(14094));
                itemDef.name = "Dark Sacred Clay";
                itemDef.editedModelColor = new int[] { 3368, 13490, 13357, 8741, 13500 , 13386, 12, 13480, 3363, 3358, 3353, 13475, 3358 };
                itemDef.newModelColor = new int[] { 14573, 1024, 1024, 1024, 14573, 14573, 1024, 1024, 1024, 1024, 1024, 1024, 1024 };
                break;
            case 14546:
                itemDef.copy(ItemDefinition.forID(14095));
                itemDef.name = "Dark Sacred Clay";
                itemDef.editedModelColor = new int[] { 3368, 13490, 13357, 8741, 13500 , 13386, 12, 13480, 3363, 3358, 3353, 13475, 3358, 4550, 908, 3373, 3363, 13465 };
                itemDef.newModelColor = new int[] { 14573, 1024, 1024, 1024, 14573, 14573, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 14573 };
                break;
            case 14547:
                itemDef.copy(ItemDefinition.forID(14096));
                itemDef.name = "Dark Sacred Clay";
                itemDef.editedModelColor = new int[] { 3368, 13490, 13357, 8741, 13500 , 13386, 12, 13480, 3363, 3358, 3353, 13475, 3358, 4550, 908, 3373, 3363, 13465 };
                itemDef.newModelColor = new int[] { 14573, 1024, 1024, 1024, 14573, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 1024, 14573 };
                break;
            case 14548:
                itemDef.copy(ItemDefinition.forID(17061));
                itemDef.name = "Light Sagittarian coif";
                itemDef.editedModelColor = new int[] { 4396, 4410, 4406, 4397, 4503, 4495, 4390, 4427, 12581, 4381, 31425, 4512, 10527, 10536, 5192, 5180, 4519, 4493, 4378, 4389, 10526, 10523, 12592, 12573, 12566, 12569, 12562, 4523, 10517, 5158 };
                itemDef.newModelColor =    new int[] { 90, 90, 90, 90, 80, 80, 90, 90, 80, 90, -1253, -1253, 80, 80, 80, 80, 80, 80, 80, 80, 80, 10523, 80, 90, 90, 90, 90, 90, 80, 80 };
                break;
            case 14549:
                itemDef.copy(ItemDefinition.forID(17193));
                itemDef.name = "Light Sagittarian body";
                itemDef.editedModelColor = new int[] { 4428, 4500, 4512, 4422, 4420, 4408, 4396, 10520, 4413, 4401, 10427, 4389, 10532, 10439, 10502, 10544, 11539, 10451, 11548, 4403, 4522, 4395, 4427, 4507, 4435, 4539, 255, 4416, 4428, 4515, 4535, 4504, 5390, 4419, 4415, 4410, 4449, 4433, 4519, 4526, 4409, 5394, 5389, 4503, 10529, 4511, 4392, 4421, 4531, 4391, 4513, 31425, 31453, 10536, 4423, 4493, 10542, 4498, 4397, 4529, 4525, 4385, 4414 };
                itemDef.newModelColor = new int[] { 90, 70, 70, 70, 70, 90, 70, 90, 70, 90, 90, 70, 90, 90, 90, 90, 90, 90, 90, 70, 90, 90, 70, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 70, 70, 70, 70, 70, 90, 90, 90, 90, 90, -1253, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80 };               
                break;
            case 14550:
                itemDef.copy(ItemDefinition.forID(17339));
                itemDef.name = "Light Sagittarian chaps";
                itemDef.editedModelColor = new int[] { 4532, 4520, 4512, 4513, 9653, 4493, 4539, 4552, 31425, 4533, 4531, 4560, 31453, 4545, 4527, 11549, 4500, 4547, 11539, 10524, 10418, 11534, 10549, 10452, 10544, 4529, 10541, 10551, 10555, 10563, 10536, 4524, 10548, 4390, 4503, 4569, 10413, 10510, 10502, 255, };
                itemDef.newModelColor =    new int[] { 70, 70, 70, 90, 90, 90, 90, 90, -1253, 90, 90, 90, -1253, 90, 90, 70, 70, 90, 90, 90, 90, 70, 70, 70, 70, 90, 90, 70, 70, 70, 90, 70, 70, 70, 70, 90, 70, 70, 90, 90};
                break;
            case 14551:
                itemDef.copy(ItemDefinition.forID(17215));
                itemDef.name = "Light Sagittarian vambs";
                itemDef.editedModelColor = new int[] { 4522, 4116, 10536, 4123 };
                itemDef.newModelColor =    new int[] { 90, 80, 90, 90 };
                break;
            case 14552:
                itemDef.copy(ItemDefinition.forID(17317));
                itemDef.name = "Light Sagittarian boots";
                itemDef.editedModelColor = new int[] { 4497, 4491, 4500, 4383, 4514, 4510, 4523, 4361, 4513, 4505, 4373 };
                itemDef.newModelColor =    new int[] { 80, 90, 80, 90, 90, 90, 90, 90, 90, 90, 90 };
                break;
        }
    }

}
