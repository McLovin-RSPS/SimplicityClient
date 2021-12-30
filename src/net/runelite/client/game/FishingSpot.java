/*
 * Copyright (c) 2017, Seth <Sethtroll3@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.game;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lombok.Getter;
import net.runelite.api.ItemID;
import static net.runelite.api.NpcID.*;

@Getter
public enum FishingSpot
{
    SHRIMP("Shrimp, Anchovies, Monkfish", "Anchovies", ItemID.RAW_SHRIMPS,
            316
    ),
    LOBSTER("Lobster, Swordfish, Tuna", "Lobster", ItemID.RAW_LOBSTER,
            312
    ),
    SHARK("Shark, Bass", "Shark", ItemID.RAW_SHARK,
            313
    ),
    ROCKTAIL("Rocktail", 15270,
            10091),
    SALMON("Salmon, Trout", "Salmon", ItemID.RAW_SALMON,
            318
    ),
    ANGLERFISH("Anglerfish", 43439,
            10089
    ),
    ;

    private static final Map<Integer, FishingSpot> SPOTS;

    private final String name;
    private final String worldMapTooltip;
    private final int fishSpriteId;
    private final int[] ids;

    static
    {
        ImmutableMap.Builder<Integer, FishingSpot> builder = new ImmutableMap.Builder<>();

        for (FishingSpot spot : values())
        {
            for (int spotId : spot.getIds())
            {
                builder.put(spotId, spot);
            }
        }

        SPOTS = builder.build();
    }

    FishingSpot(String spot, int fishSpriteId, int... ids)
    {
        this(spot, spot, fishSpriteId, ids);
    }

    FishingSpot(String spot, String worldMapTooltip, int fishSpriteId, int... ids)
    {
        this.name = spot;
        this.worldMapTooltip = worldMapTooltip;
        this.fishSpriteId = fishSpriteId;
        this.ids = ids;
    }

    public static FishingSpot findSpot(int id)
    {
        return SPOTS.get(id);
    }
}
