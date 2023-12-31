package com.simplicity.client;


import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import com.simplicity.client.cache.definitions.Animation;

import com.simplicity.client.cache.definitions.MobDefinition;
import com.simplicity.client.entity.HealthBar;
import com.simplicity.util.Direction;
import net.runelite.api.Hitsplat;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.RuneLite;

public class Entity extends Animable {

	public final void setPos(int i, int j, boolean flag) {
		if (anim != -1 && Animation.anims[anim].priority == 1) {
			anim = -1;
			if(RuneLite.getClient() != null) {
				AnimationChanged animationChanged = new AnimationChanged();
				animationChanged.setActor(this);
				Client.instance.getCallbacks().post(animationChanged);
			}
		}
		if (!flag) {
			int k = i - pathX[0];
			int l = j - pathY[0];
			if (k >= -8 && k <= 8 && l >= -8 && l <= 8) {
				if (pathLength < 9)
					pathLength++;
				for (int i1 = pathLength; i1 > 0; i1--) {
					pathX[i1] = pathX[i1 - 1];
					pathY[i1] = pathY[i1 - 1];
					aBooleanArray1553[i1] = aBooleanArray1553[i1 - 1];
				}

				pathX[0] = i;
				pathY[0] = j;
				aBooleanArray1553[0] = false;
				return;
			}
		}
		pathLength = 0;
		anInt1542 = 0;
		anInt1503 = 0;
		pathX[0] = i;
		pathY[0] = j;
		x = pathX[0] * 128 + boundDim * 64;
		y = pathY[0] * 128 + boundDim * 64;
	}

	public final void resetWalk() {
		pathLength = 0;
		anInt1542 = 0;
	}

	public int[] hitmarkMove = new int[4];
	public int[] moveTimer = new int[4];
	public int[] hitmarkTrans = new int[4];
	public int[] hitIcon = new int[4];
	public int[] soakDamage = new int[4];
	public int[] hitMarkPos = new int[4];
	public int[] indexes = new int[4];

	public final void updateHitData(int markType, int damage, int l, int icon, int soak, int entityIndex) {
		HitsplatApplied hitsplatApplied = new HitsplatApplied();
		hitsplatApplied.setActor(this);
		hitsplatApplied.setHitsplat(new Hitsplat(Hitsplat.HitsplatType.DAMAGE, damage, l + 70));
		if(RuneLite.getClient() != null)
			Client.getCallbacks().post(hitsplatApplied);
		for (int i1 = 0; i1 < 4; i1++)
			if (hitsLoopCycle[i1] <= l) {
				hitIcon[i1] = icon;
				hitmarkMove[i1] = 5;
				moveTimer[i1] = 2;
				hitmarkTrans[i1] = 255;
				soakDamage[i1] = soak;
				hitArray[i1] = damage;
				hitMarkTypes[i1] = markType;
				hitsLoopCycle[i1] = l + 70;
				hitMarkPos[i1] = 0;
				indexes[i1] = entityIndex;
				return;
			}
	}

	public final void moveInDir(boolean flag, int dir) {
		int j = pathX[0];
		int k = pathY[0];
		if (dir == 0) {
			j--;
			k++;
		}
		if (dir == 1)
			k++;
		if (dir == 2) {
			j++;
			k++;
		}
		if (dir == 3)
			j--;
		if (dir == 4)
			j++;
		if (dir == 5) {
			j--;
			k--;
		}
		if (dir == 6)
			k--;
		if (dir == 7) {
			j++;
			k--;
		}
		if (anim != -1 && Animation.anims[anim].priority == 1) {
			anim = -1;
			if(RuneLite.getClient() != null) {
				AnimationChanged animationChanged = new AnimationChanged();
				animationChanged.setActor(this);
				Client.instance.getCallbacks().post(animationChanged);
			}
		}
		if (pathLength < 9)
			pathLength++;
		for (int l = pathLength; l > 0; l--) {
			pathX[l] = pathX[l - 1];
			pathY[l] = pathY[l - 1];
			aBooleanArray1553[l] = aBooleanArray1553[l - 1];
		}
		pathX[0] = j;
		pathY[0] = k;
		aBooleanArray1553[0] = flag;
	}

	public int entScreenX;
	public int entScreenY;
	public final int index = -1;

	public boolean isVisible() {
		return false;
	}

	Entity() {
		pathX = new int[10];
		pathY = new int[10];
		interactingEntity = -1;
		degreesToTurn = 32;
		runAnimation = -1;
		height = 200;
		standAnim = -1;
		anInt1512 = -1;
		hitArray = new int[4];
		hitMarkTypes = new int[4];
		hitsLoopCycle = new int[4];
		entityAnimation = -1;
		anInt1520 = -1;
		anim = -1;
		loopCycleStatus = -1000;
		textCycle = 100;
		boundDim = 1;
		aBoolean1541 = false;
		aBooleanArray1553 = new boolean[10];
		anInt1554 = -1;
		anInt1555 = -1;
		anInt1556 = -1;
		anInt1557 = -1;
	}

	public final int[] pathX;
	public final int[] pathY;
	public int interactingEntity;
	int anInt1503;
	int degreesToTurn;
	int runAnimation;
	public String textSpoken;
	public int height;
	public int turnDirection;
	int standAnim;
	int anInt1512;
	int anInt1513;
	final int[] hitArray;
	final int[] hitMarkTypes;
	final int[] hitsLoopCycle;
	int entityAnimation;
	int currentForcedAnimFrame;
	int anInt1519;
	int anInt1520;
	int currentAnim;
	int animCycle;
	int graphicDelay;
	int graphicHeight;
	int pathLength;
	public int anim;
	int currentAnimFrame;
	int anInt1528;
	int animationDelay;
	int anInt1530;
	int anInt1531;
	public int loopCycleStatus;
	public int currentHealth;
	public int maxHealth;
	int textCycle;
	int loopCycle;
	int anInt1538;
	int anInt1539;
	int boundDim;
	boolean aBoolean1541;
	int anInt1542;
	int anInt1543;
	int anInt1544;
	int anInt1545;
	int anInt1546;
	int anInt1547;
	int anInt1548;
	int turnInfo;
	public int x;
	public int y;
	public int z;
	int currentRotation;
	final boolean[] aBooleanArray1553;
	int anInt1554;
	int anInt1555;
	int anInt1556;
	int anInt1557;
	
	/**
	 * Gets a point on the canvas of where this actors mini-map indicator
	 * should appear.
	 *
	 * @return mini-map location on canvas
	 */
	public Point getMinimapLocation() {
		return Perspective.localToMinimap(RuneLite.getClient(), getLocalLocation());
	}
	
	public LocalPoint getLocalLocation() {
		// OLD (x - 6 >> 7), (y - 6 >> 7)
		return new LocalPoint(x, y);
	}

	public WorldPoint getWorldLocation() {
		return WorldPoint.fromLocal(Client.instance,
				this.pathX[0] * Perspective.LOCAL_TILE_SIZE + Perspective.LOCAL_TILE_SIZE / 2,
				this.pathY[0] * Perspective.LOCAL_TILE_SIZE + Perspective.LOCAL_TILE_SIZE / 2,
				Client.instance.plane);
	}
	
	public Polygon getCanvasTilePoly()
	{
		return Perspective.getCanvasTilePoly(RuneLite.getClient(), getLocalLocation());
	}

	
	public Point getCanvasTextLocation(Graphics2D graphics, String text, int zOffset)
	{
		return Perspective.getCanvasTextLocation(RuneLite.getClient(), graphics, getLocalLocation(), text, zOffset);
	}
	
	public int nextAnimationFrame;
	public int nextGraphicsAnimationFrame;
	public int nextIdleAnimationFrame;
	
	public int idleGraphicId = -1;

	public String getName() {
		if (this instanceof Player) {
			return ((Player) this).name;
		} else if (this instanceof NPC) {
			NPC n = (NPC) this;
			
			if (n.desc == null) {
				return null;
			}
			
			return n.desc.name;
		}
		
		return null;
	}
	
	public boolean isDead() {
		return maxHealth > 0 && currentHealth <= 0;
	}

	public int getGraphic() {
		return anInt1520;
	}

	public int getPoseAnimation() {
		return standAnim;
	}

	public Entity getInteracting() {
		if (interactingEntity == -1) {
			return null;
		}

		if (interactingEntity < 32768) {
			return Client.instance.getNpcs()[interactingEntity];
		} else {
			return Client.instance.getPlayers()[interactingEntity - 32768];
		}
	}

	public WorldArea getWorldArea()
	{
		int size = 1;
		if (this instanceof NPC)
		{
			MobDefinition composition = ((NPC)this).desc;
			if (composition != null)
			{
				size = composition.squaresNeeded;
			}
		}

		return new WorldArea(this.getWorldLocation(), size, size);
	}

	public HealthBar getHealthBar() {
		return HealthBar.DEFAULT;
	}

	public int getHealthDimension() {
		return HealthBar.DIM_30;
	}

	/**
	 * Gets if this entity is a npc.
	 *
	 * @return <code>true</code> if this entity is a npc.
	 */
	public boolean isNPC() {
		return this instanceof NPC;
	}

	/**
	 * Casts this entity to a npc.
	 *
	 * @return this entity as npc
	 */
	public NPC asNPC() {
		return (NPC) this;
	}

	/**
	 * Sets this entity to face in the specified direction.
	 *
	 * @param direction The direction to face.
	 */
	public void setFaceDirection(Direction direction) {
		final int size = isNPC() ? asNPC().desc.squaresNeeded : 1;
		final int worldX = getWorldLocation().getX() + (direction.deltaX * (size * 2));
		final int worldY = getWorldLocation().getY() + (direction.deltaY * (size * 2));
		final int faceX = worldX * 2 + size;
		final int faceY = worldY * 2 + size;

		final int baseX = Client.getClient().getBaseX();
		final int baseY = Client.getClient().getBaseY();

		final int tx = x - (faceX - baseX - baseX) * 64;
		final int ty = y - (faceY - baseY - baseY) * 64;

		turnDirection = (int) (Math.atan2(tx, ty) * 325.94900000000001D) & 0x7ff;

		int angle = turnDirection - currentRotation & 0x7ff;

		if (degreesToTurn == 32 || angle < degreesToTurn || angle > 2048 - degreesToTurn) {
			currentRotation = turnDirection;
		} else if (angle > 1024) {
			currentRotation = turnDirection -= angle;
		} else {
			currentRotation = turnDirection += angle;
		}

		anInt1538 = 0;
		anInt1539 = 0;
	}

	public Point getCanvasImageLocation(BufferedImage fishImage, int modelHeight) {
		return Perspective.getCanvasImageLocation(RuneLite.getClient(), getLocalLocation(), fishImage, modelHeight);
	}

    public int getAnimation() {
		return anim;
    }
}
