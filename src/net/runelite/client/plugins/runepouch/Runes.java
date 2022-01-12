/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.plugins.runepouch;

import com.google.common.collect.ImmutableMap;
import net.runelite.client.util.AsyncBufferedImage;

import java.awt.image.BufferedImage;
import java.util.Map;

public enum Runes {
    AIR(1, 556),
    WATER(2, 555),
    EARTH(3, 557),
    FIRE(4, 554),
    MIND(5, 558),
    CHAOS(6, 562),
    DEATH(7, 560),
    BLOOD(8, 565),
    COSMIC(9, 564),
    NATURE(10, 561),
    LAW(11, 563),
    BODY(12, 559),
    SOUL(13, 566),
    ASTRAL(14, 9075),
    MIST(15, 4695),
    MUD(16, 4698),
    DUST(17, 4696),
    LAVA(18, 4699),
    STEAM(19, 4694),
    SMOKE(20, 4697),
    WRATH(21, 21880);

    private final int id;
    private final int itemId;
    private AsyncBufferedImage image;
    private static final Map<Integer, Runes> runes;

    private Runes(int id, int itemId) {
        this.id = id;
        this.itemId = itemId;
    }

    public static Runes getRune(int itemId) {
        return runes.get(itemId);
    }

    public String getName() {
        String name = this.name();
        name = name.substring(0, 1) + name.substring(1).toLowerCase();
        return name;
    }

    public int getId() {
        return this.id;
    }

    public int getItemId() {
        return this.itemId;
    }

    public AsyncBufferedImage getImage() {
        return this.image;
    }

    public void setImage(AsyncBufferedImage image) {
        this.image = image;
    }

    static {
        ImmutableMap.Builder<Integer, Runes> builder = new ImmutableMap.Builder<Integer, Runes>();
        for (Runes rune : Runes.values()) {
            builder.put(rune.getItemId(), rune);
        }
        runes = builder.build();
    }
}

