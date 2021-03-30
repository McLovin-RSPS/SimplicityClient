package com.simplicity.client;

import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.client.cache.definitions.custom.CustomItems;
import com.simplicity.client.cache.definitions.custom.CustomItems2;
import com.simplicity.client.cache.definitions.custom.CustomItems3;

import static com.simplicity.client.cache.definitions.ItemDefinition.*;

public class Items {

    public static ItemDefinition forID(int itemId) {
        // System.out.println("item id: "+itemId+", OSRS_ITEMS_OFFSET="+OSRS_ITEMS_OFFSET+", OSRS_ITEMS_START="+OSRS_ITEMS_START);
        if (FORCE_OSRS_ITEMS.contains(itemId)) {
            itemId += OSRS_ITEMS_OFFSET;
        }

        if (itemId >= OSRS_ITEMS_OFFSET) {
            itemId -= OSRS_ITEMS_OFFSET;

            for (int j = 0; j < 10; j++) {
                if (cacheOSRS[j].id == itemId) {
                    return cacheOSRS[j];
                }
            }

            cacheIndexOSRS = (cacheIndexOSRS + 1) % 10;

            ItemDefinition itemDef = cacheOSRS[cacheIndexOSRS];

            if (CUSTOM_RECOLORS.containsKey(itemId + OSRS_ITEMS_OFFSET)) {
                return CUSTOM_RECOLORS.get(itemId + OSRS_ITEMS_OFFSET);
            }

            if (itemId >= streamIndicesOSRS.length) {
                itemDef.id = 1;
                itemDef.setDefaults();
                return itemDef;
            }

            streamOSRS.currentOffset = streamIndicesOSRS[itemId];
            itemDef.id = OSRS_ITEMS_OFFSET + itemId;
            itemDef.dataType = DataType.OLDSCHOOL;
            itemDef.setDefaults();
            itemDef.readValues(streamOSRS);

            if (itemDef.certTemplateID != -1) {
                itemDef.toNote();
            }

            if (itemDef.lentItemID != -1) {
                itemDef.toLend();
            }

            if (itemDef.id == itemId && itemDef.editedModelColor == null) {
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 1;
            }

            //itemDef.value = prices[itemDef.id];

            if (FORCE_OSRS_ITEMS.contains(itemId)) {
                itemDef.id -= OSRS_ITEMS_OFFSET;
            }

            postOsrsItemDef(itemDef);
            return itemDef;
        }

        ItemDefinition itemDef = new ItemDefinition();

        if (CUSTOM_RECOLORS.containsKey(itemId)) {
            return CUSTOM_RECOLORS.get(itemId);
        }

        if (itemId >= streamIndices.length || itemId < 0) {
            return forID(1);
        }

        stream.currentOffset = streamIndices[itemId];
        itemDef.id = itemId;
        itemDef.setDefaults();
        itemDef.readValues(stream);
        if (itemDef.certTemplateID != -1) {
            itemDef.toNote();
        }
        if (itemDef.lentItemID != -1) {
            itemDef.toLend();
        }
        if (itemDef.id == itemId && itemDef.editedModelColor == null) {
            itemDef.editedModelColor = new int[1];
            itemDef.newModelColor = new int[1];
            itemDef.editedModelColor[0] = 0;
            itemDef.newModelColor[0] = 1;
        }
        if (untradeableItems.contains(itemDef.id)) {
            itemDef.untradeable = true;
        }
        itemDef.value = prices[itemDef.id];
        
        CustomItems.loadDefinition(itemDef);
        CustomItems2.loadDefinition(itemDef);
        CustomItems3.loadDefinition(itemDef);
        return itemDef;
    }
}
