package com.simplicity.client.particles;

import com.simplicity.client.Sprite;

import java.util.Random;

public enum ParticleDefinition {
    COMPLETION_CAPE(new ParticleVector(0, -2, 0),
            new ParticleVector(0, -2, 0),
            new ParticleVector(0, 2 / 4, 0),
            19,
            0xFFFFFF,
            4,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    TRIMMED_COMPLETION_CAPE(
            new ParticleVector(0, -2, 0),
            new ParticleVector(0, -2, 0),
            new ParticleVector(0, 2 / 4, 0),
            19,
            0xFFD900,
            4,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    MASTER_DUNGEONEERING_CAPE(
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, 2 / 4, 0),
            19,
            0x000000,
            4,
            1.25f,
            0.11f,
            0.020f,
            0x000000
    ),
    COMPLETIONIST_CAPE(
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, 2 / 4, 0),
            19,
            0xFFFFFF,
            4,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    TRIMMED_COMPLETIONIST_CAPE(
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, 2 / 4, 0),
            19,
            0xFFD900,
            4,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    MAX_CAPE(
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, 2 / 4, 0),
            19,
            0xFFFFFF,
            4,
            1.15f,
            0,
            0.095f,
            0x000000
    ),
    MASTER_DUNGEONEERING_CAPE_2(
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, -1, 0),
            new ParticleVector(0, 2 / 4, 0),
            19,
            0x000000,
            4,
            1.15f,
            0.05f,
            0.095f,
            0x000000
    ),
    TOK_HAAR_KAL(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            19,
            0xFF7F00,
            2,
            1.15f,
            0,
            0.20f,
            0x000900
    ),
    INFERNAL_MAX_CAPE(
            new ParticleVector(0, 3, 0),
            new ParticleVector(0, 0, 0),
            new ParticleVector(0, 1 / 3, 0),
            19,
            0,
            5,
            1.15f,
            0,
            0.095f,
            0xB70000
    ),
    SLED(
            new ParticleVector(0, 3, 0),
            new ParticleVector(0, -30, 0),
            new ParticleVector(0, 0, 0),
            28, // 19
            0xFFFFFF,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    ONYX_GODSWORD_1(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            19,
            0xB70000,
            2,
            1.2f,
            0,
            0.2f,
            0x000900
    ),
    ONYX_GODSWORD_2(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, 30, 0),
            new ParticleVector(0, 1 / 2, 0),
            29,
            0xB70000,
            1,
            1.0f,
            0.7f,
            0.2f,
            0x000900
    ),
    DARK_SLED(
            new ParticleVector(0, 3, 0),
            new ParticleVector(0, -30, 0),
            new ParticleVector(0, 0, 0),
            28, // 19
            0xB70000,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    TOK_HAAR_KAL_2(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            19,
            0xc020bd,
            2,
            1.15f,
            0,
            0.20f,
            0x000900
    ),
    TOK_HAAR_KAL_3(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            19,
            0x224d57,
            2,
            1.15f,
            0,
            0.30f,
            0x000900
    ),
    OVERLORD_CAPE(
            new ParticleVector(0, 3, 0),
            new ParticleVector(0, -30, 0),
            new ParticleVector(0, 0, 0),
            28, // 19
            0xB70000,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    BLUE_PARTICLE(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            28,
            0x0091ff,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    PURPLE_PARTICLE(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            28,
            0xaa00ff,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    LIME_PARTICLE(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            28,
            0x00ff00,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    PINK_PARTICLE(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            28,
            0xff0099,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    GOLD_PARTICLE(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            28,
            0xFFD700,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    DARK_PURPLE_PARTICLE(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            28,
            0x65009B,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    ),
    DARK_GREY_PARTICLE(
            new ParticleVector(0, -3, 0), // x z y
            new ParticleVector(0, -3, 0),
            new ParticleVector(0, 1 / 2, 0),
            28,
            0x2A2A2A,
            8,
            1.25f,
            0,
            0.095f,
            0x000000
    )

    ;


    ParticleDefinition(ParticleVector startVelocity, ParticleVector endVelocity, ParticleVector gravity,
                  int lifeSpan, int startColor, int spawnRate, float startSize, float endSize, float startAlpha, int colorStep) {
        this.velocityStep = startVelocity;
        this.endVelocity = endVelocity;
        this.gravity = gravity;
        this.lifeSpan = lifeSpan;
        this.startColor = startColor;
        this.spawnRate = spawnRate;
        this.startSize = startSize;
        this.endSize = endSize;
        this.startAlpha = startAlpha;
        this.updateSteps();
        this.colorStep = colorStep;
    }

    public final SpawnShape getSpawnedShape() {
        return spawnShape;
    }

    public final float getStartAlpha() {
        return startAlpha;
    }

    public final float getAlphaStep() {
        return alphaStep;
    }

    public final Sprite getSprite() {
        return sprite;
    }

    public final int getSpawnRate() {
        return this.spawnRate;
    }

    public final float getStartSize() {
        return startSize;
    }

    public float getEndSize() {
        return endSize;
    }

    public int getEndColor() {
        return endColor;
    }

    public Random getRandom() {
        return new Random(System.currentTimeMillis());
    }

    public final int getStartColor() {
        return startColor;
    }


    public int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public final ParticleVector getStartVelocity(int id) {
        switch (id) {
            default:
                return new ParticleVector(this.startVelocity.getX() + randomWithRange(-1, 1), this.startVelocity.getY() + randomWithRange(0, 0), this.startVelocity.getZ() + randomWithRange(-1, 1));
        }
    }

    public ParticleVector getGravity() {
        return gravity;
    }

    public ParticleVector getEndVelocity() {
        return endVelocity;
    }

    public final int getLifeSpan() {
        return lifeSpan;
    }

    public final float getSizeStep() {
        return sizeStep;
    }

    public final ParticleVector getVelocityStep() {
        return velocityStep;
    }

    public final int getColorStep() {
        return colorStep;
    }

    public final void updateSteps() {
        this.sizeStep = (endSize - startSize) / (lifeSpan * 1f);
        this.colorStep = (endColor - startColor) / lifeSpan;
        this.velocityStep = endVelocity.subtract(startVelocity).divide(lifeSpan);
        this.alphaStep = (endAlpha - startAlpha) / lifeSpan;
    }

    private ParticleVector gravity;

    private float startSize = 1f;
    private float endSize = 1f;

    public int particleDepth;

    private int startColor = -1;
    private int endColor = -1;

    private ParticleVector startVelocity = ParticleVector.ZERO;
    private ParticleVector endVelocity = ParticleVector.ZERO;

    private SpawnShape spawnShape = new PointSpawnShape(ParticleVector.ZERO);

    private float startAlpha = 1f;
    private float endAlpha = 0.05f;

    private int lifeSpan = 1;
    private int spawnRate = 1;
    private Sprite sprite;
    private ParticleVector velocityStep;
    private int colorStep;
    private float sizeStep;
    private float alphaStep;
}