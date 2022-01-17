package com.simplicity.client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simplicity.Configuration;
import com.simplicity.client.cache.node.Deque;
import com.simplicity.client.entity.Position;

import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.DecorativeObjectDespawned;
import net.runelite.api.events.DecorativeObjectSpawned;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GroundObjectDespawned;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.WallObjectDespawned;
import net.runelite.api.events.WallObjectSpawned;
import net.runelite.api.hooks.DrawCallbacks;
import net.runelite.client.RuneLite;

@SuppressWarnings("all")
public final class WorldController {

	public static int TILE_DRAW_DISTANCE = 25;

	public static final int DEFAULT_RENDER_DISTANCE = 3500;

	public static int MAX_RENDER_DISTANCE = DEFAULT_RENDER_DISTANCE;

	public WorldController(int ai[][][]) {
		int height = 104;// was parameter
		int width = 104;// was parameter
		int depth = 4;// was parameter
		interactableObjectCache = new InteractableObject[5000];
		anIntArray486 = new int[10000];
		anIntArray487 = new int[10000];
		zMapSize = depth;
		xMapSize = width;
		yMapSize = height;
		tileArray = new Tile[depth][width][height];
		anIntArrayArrayArray445 = new int[depth][width + 1][height + 1];
		anIntArrayArrayArray440 = ai;
		initToNull();
	}

	public WallObject fetchWallObject(int i, int j, int k) {
		Tile tile = tileArray[i][j][k];
		if (tile == null || tile.wallObject == null)
			return null;
		else
			return tile.wallObject;
	}

	public WallDecoration fetchWallDecoration(int i, int j, int l) {
		Tile tile = tileArray[i][j][l];
		if (tile == null || tile.wallDecoration == null)
			return null;
		else
			return tile.wallDecoration;
	}

	public InteractableObject fetchInteractableObject(int z, int x, int y) {
		Tile tile = tileArray[z][x][y];
		if (tile == null)
			return null;
		for (int l = 0; l < tile.entityCount; l++) {
			InteractableObject interactableObject = tile.interactableObjects[l];
			if (interactableObject.tileLeft == x && interactableObject.tileTop == y) {
				return interactableObject;
			}
		}
		return null;
	}

	public GroundDecoration fetchGroundDecoration(int i, int j, int k) {
		Tile tile = tileArray[i][j][k];
		if (tile == null || tile.groundDecoration == null)
			return null;
		else
			return tile.groundDecoration;
	}


	public int fetchWallObjectNewUID(int i, int j, int k) {
		Tile tile = tileArray[i][j][k];
		if (tile == null || tile.wallObject == null)
			return 0;
		else
			return tile.wallObject.wallObjUID;
	}

	public int fetchWallDecorationNewUID(int i, int j, int l) {
		Tile tile = tileArray[i][j][l];
		if (tile == null || tile.wallDecoration == null)
			return 0;
		else
			return tile.wallDecoration.wallDecorUID;
	}

	public int fetchObjectMeshNewUID(int z, int x, int y) {
		Tile tile = tileArray[z][x][y];
		if (tile == null)
			return 0;
		for (int l = 0; l < tile.entityCount; l++) {
			InteractableObject interactableObject = tile.interactableObjects[l];
			if (interactableObject.tileLeft == x && interactableObject.tileTop == y) {
				return interactableObject.interactiveObjUID;
			}
		}
		return 0;
	}

	public int fetchGroundDecorationNewUID(int i, int j, int k) {
		Tile tile = tileArray[i][j][k];
		if (tile == null || tile.groundDecoration == null)
			return 0;
		else
			return tile.groundDecoration.groundDecorUID;
	}

	public static void nullLoader() {
		aClass28Array462 = null;
		cullingClusterPointer = null;
		cullingClusters = null;
		tileDeque = null;
		tile_visibility_maps = null;
		tile_visibility_map = null;
	}

	public void initToNull() {
		for (int z = 0; z < zMapSize; z++) {
			for (int x = 0; x < xMapSize; x++) {
				for (int y = 0; y < yMapSize; y++)
					tileArray[z][x][y] = null;

			}

		}
		for (int l = 0; l < amountOfCullingClusters; l++) {
			for (int j1 = 0; j1 < cullingClusterPointer[l]; j1++)
				cullingClusters[l][j1] = null;

			cullingClusterPointer[l] = 0;
		}

		for (int k1 = 0; k1 < amountOfInteractableObjects; k1++)
			interactableObjectCache[k1] = null;

		amountOfInteractableObjects = 0;
		for (int l1 = 0; l1 < aClass28Array462.length; l1++)
			aClass28Array462[l1] = null;

	}

	public void initTiles(int hl) {
		currentHL = hl;
		for (int k = 0; k < xMapSize; k++) {
			for (int l = 0; l < yMapSize; l++)
				if (tileArray[hl][k][l] == null)
					tileArray[hl][k][l] = new Tile(hl, k, l);

		}

	}

	public void applyBridgeMode(int y, int x) {
		Tile tile = tileArray[0][x][y];
		for (int l = 0; l < 3; l++) {
			Tile tile_ = tileArray[l][x][y] = tileArray[l + 1][x][y];
			if (tile_ != null) {
				tile_.tileZ--;
				for (int entityPtr = 0; entityPtr < tile_.entityCount; entityPtr++) {
					InteractableObject iObject = tile_.interactableObjects[entityPtr];
					if ((iObject.uid >> 29 & 3) == 2 && iObject.tileLeft == x && iObject.tileTop == y)
						iObject.zPos--;
				}

			}
		}
		if (tileArray[0][x][y] == null)
			tileArray[0][x][y] = new Tile(0, x, y);
		tileArray[0][x][y].tileBelowThisTile = tile;
		tileArray[3][x][y] = null;
	}

	public static void createCullingCluster(int id, int tileStartX, int worldEndZ, int tileEndX, int tileEndY, int worldStartZ, int tileStartY, int searchMask) {
		CullingCluster cluster = new CullingCluster();
		cluster.tileStartX = tileStartX / 128;
		cluster.tileEndX = tileEndX / 128;
		cluster.tileStartY = tileStartY / 128;
		cluster.tileEndY = tileEndY / 128;
		cluster.searchMask = searchMask;
		cluster.worldStartX = tileStartX;
		cluster.worldEndX = tileEndX;
		cluster.worldStartY = tileStartY;
		cluster.worldEndY = tileEndY;
		cluster.worldStartZ = worldStartZ;
		cluster.worldEndZ = worldEndZ;
		cullingClusters[id][cullingClusterPointer[id]++] = cluster;
	}

	public void setVisiblePlanesFor(int z, int x, int y, int logicHeight) {
		Tile tile = tileArray[z][x][y];
		if (tile != null) {
			tileArray[z][x][y].logicHeight = logicHeight;
		}
	}

	public void addTile(int i, int j, int k, int l, int i1, int overlaytex,
						int underlaytex, int k1, int l1, int i2, int j2, int k2, int l2,
						int i3, int j3, int k3, int l3, int i4, int j4, int k4, int l4,
						boolean tex) {
		if (l == 0) {
			SceneTilePaint class43 = new SceneTilePaint(k2, l2, i3, j3, underlaytex, k4,
					false, tex);

			for (int i5 = i; i5 >= 0; i5--) {
				if (tileArray[i5][j][k] == null) {
					tileArray[i5][j][k] = new Tile(i5, j, k);
				}
			}

			tileArray[i][j][k].plainTile = class43;
			return;
		}

		if (l == 1) {
			SceneTilePaint class43_1 = new SceneTilePaint(k3, l3, i4, j4, overlaytex, l4,
					k1 == l1 && k1 == i2 && k1 == j2, tex);

			for (int j5 = i; j5 >= 0; j5--) {
				if (tileArray[j5][j][k] == null) {
					tileArray[j5][j][k] = new Tile(j5, j, k);
				}
			}

			tileArray[i][j][k].plainTile = class43_1;
			return;
		}

		SceneTileModel class40 = new SceneTileModel(k, k3, j3, i2, overlaytex, underlaytex,
				i4, i1, k2, k4, i3, j2, l1, k1, l, j4, l3, l2, j, l4, tex);

		for (int k5 = i; k5 >= 0; k5--) {
			if (tileArray[k5][j][k] == null) {
				tileArray[k5][j][k] = new Tile(k5, j, k);
			}
		}

		tileArray[i][j][k].shapedTile = class40;
	}

	public void addGroundDecoration(int plane, int zPos, int yPos, Animable animable, byte byte0, int uid, int xPos,
									int groundDecorUID) {
		if (animable == null)
			return;
		GroundDecoration decoration = new GroundDecoration();
		decoration.node = animable;
		decoration.groundDecorUID = groundDecorUID;
		decoration.xPos = xPos * 128 + 64;
		decoration.yPos = yPos * 128 + 64;
		decoration.zPos = zPos;
		decoration.uid = uid;
		decoration.objConfig = byte0;
		decoration.plane = plane;

		if (tileArray[plane][xPos][yPos] == null)
			tileArray[plane][xPos][yPos] = new Tile(plane, xPos, yPos);
		tileArray[plane][xPos][yPos].groundDecoration = decoration;

		if (Client.instance.getRuneLite() != null) {
			DecorativeObjectSpawned spawn = new DecorativeObjectSpawned();
			spawn.setTile(tileArray[plane][xPos][yPos]);
			spawn.setDecorativeObject(decoration);
			Client.instance.getCallbacks().post(spawn);
		}
	}

	public void addGroundItemTile(int xPos, int uid, Animable secondItem, int zPos, Animable thirdItem,
								  Animable firstItem, int plane, int yPos) {
		GroundItem groundItem = new GroundItem();
		groundItem.firstGroundItem = firstItem;
		groundItem.xPos = xPos * 128 + 64;
		groundItem.yPos = yPos * 128 + 64;
		groundItem.zPos = zPos;
		groundItem.uid = uid;
		groundItem.secondGroundItem = secondItem;
		groundItem.thirdGroundItem = thirdItem;
		int isHighestPriority = 0;
		Tile tile = tileArray[plane][xPos][yPos];
		if (tile != null) {
			for (int k1 = 0; k1 < tile.entityCount; k1++)
				if (tile.interactableObjects[k1].node instanceof Model) {
					int tempInt = ((Model) tile.interactableObjects[k1].node).myPriority;
					if (tempInt > isHighestPriority)
						isHighestPriority = tempInt;
				}

		}
		groundItem.topItem = isHighestPriority;
		if (tileArray[plane][xPos][yPos] == null)
			tileArray[plane][xPos][yPos] = new Tile(plane, xPos, yPos);
		tileArray[plane][xPos][yPos].groundItem = groundItem;

		if (Client.instance.getRuneLite() != null) {
			GroundObjectSpawned spawn = new GroundObjectSpawned();
			spawn.setTile(tileArray[plane][xPos][yPos]);
			spawn.setGroundObject(groundItem);
			Client.instance.getCallbacks().post(spawn);
		}
	}

	public void addWallObject(int orientation, Animable node, int uid, int yPos, byte objConfig, int xPos, Animable node2,
							  int zPos, int orientation_2, int plane, int wallObjUID) {
		if (node == null && node2 == null)
			return;
		WallObject wallObject = new WallObject();
		wallObject.uid = uid;
		wallObject.objConfig = objConfig;
		wallObject.xPos = xPos * 128 + 64;
		wallObject.yPos = yPos * 128 + 64;
		wallObject.zPos = zPos;
		wallObject.node1 = node;
		wallObject.node2 = node2;
		wallObject.wallObjUID = wallObjUID;
		wallObject.orientation = orientation;
		wallObject.orientation1 = orientation_2;
		for (int zPtr = plane; zPtr >= 0; zPtr--)
			if (tileArray[zPtr][xPos][yPos] == null)
				tileArray[zPtr][xPos][yPos] = new Tile(zPtr, xPos, yPos);

		tileArray[plane][xPos][yPos].wallObject = wallObject;

		if (Client.instance.getRuneLite() != null) {
			WallObjectSpawned spawn = new WallObjectSpawned();
			spawn.setTile(tileArray[plane][xPos][yPos]);
			spawn.setWallObject(wallObject);
			Client.instance.getCallbacks().post(spawn);
		}
	}

	public void addWallDecoration(int uid, int yPos, int rotation, int plane, int xOff, int zPos,
								  Animable node, int xPos, byte config, int yOff, int configBits, int wallDecorUID) {
		if (node == null)
			return;
		WallDecoration dec = new WallDecoration();
		dec.uid = uid;
		dec.objConfig = config;
		dec.xPos = xPos * 128 + 64 + xOff;
		dec.yPos = yPos * 128 + 64 + yOff;
		dec.zPos = zPos;
		dec.node = node;
		dec.wallDecorUID = wallDecorUID;
		dec.configurationBits = configBits;
		dec.rotation = rotation;
		for (int zPtr = plane; zPtr >= 0; zPtr--)
			if (tileArray[zPtr][xPos][yPos] == null)
				tileArray[zPtr][xPos][yPos] = new Tile(zPtr, xPos, yPos);

		tileArray[plane][xPos][yPos].wallDecoration = dec;
	}

	public boolean addInteractableEntity(int ui, byte config, int worldZ, int tileBottom, Animable node, int tileRight, int z,
										 int rotation, int tileTop, int tileLeft, int interactiveUID) {
		if (node == null) {
			return true;
		} else {
			int worldX = tileLeft * 128 + 64 * tileRight;
			int worldY = tileTop * 128 + 64 * tileBottom;
			return addEntity(z, tileLeft, tileTop, tileRight, tileBottom,
					worldX, worldY, worldZ, node, rotation, false, ui, config, interactiveUID);
		}
	}

	public boolean addMutipleTileEntity(int z, int rotation, int worldZ, int ui, int worldY, int j1, int worldX,
										Animable nodeToAdd, boolean flag) {
		if (nodeToAdd == null)
			return true;
		int tileLeft = worldX - j1;
		int tileTop = worldY - j1;
		int tileRight = worldX + j1;
		int tileBottom = worldY + j1;
		if (flag) {
			if (rotation > 640 && rotation < 1408)
				tileBottom += 128;
			if (rotation > 1152 && rotation < 1920)
				tileRight += 128;
			if (rotation > 1664 || rotation < 384)
				tileTop -= 128;
			if (rotation > 128 && rotation < 896)
				tileLeft -= 128;
		}
		tileLeft /= 128;
		tileTop /= 128;
		tileRight /= 128;
		tileBottom /= 128;
		return addEntity(z, tileLeft, tileTop, (tileRight - tileLeft) + 1, (tileBottom - tileTop) + 1,
				worldX, worldY, worldZ, nodeToAdd, rotation, true, ui, (byte) 0, 0);
	}

	public boolean addSingleTileEntity(int z, int worldY, Animable node, int rotation, int tileBottom, int worldX, int worldZ, int tileLeft, int tileRight, int ui, int tileTop) {
		return node == null || addEntity(z, tileLeft, tileTop, (tileRight - tileLeft) + 1, (tileBottom - tileTop) + 1,
				worldX, worldY, worldZ, node, rotation, true, ui, (byte) 0, 0);
	}

	private boolean addEntity(int z, int tileLeft, int tileTop, int tileRight, int tileBottom, int worldX, int worldY,
							  int worldZ, Animable node, int rotation, boolean flag, int ui, byte objConf, int interactiveObjUID) {
		/**
		 * Max entities on coord is 5 i guess
		 */
		for (int _x = tileLeft; _x < tileLeft + tileRight; _x++) {
			for (int _y = tileTop; _y < tileTop + tileBottom; _y++) {
				if (_x < 0 || _y < 0 || _x >= xMapSize || _y >= yMapSize)
					return false;
				Tile tile = tileArray[z][_x][_y];
				if (tile != null && tile.entityCount >= 5)
					return false;
			}

		}

		InteractableObject io = new InteractableObject();
		io.uid = ui;
		io.objConf = objConf;
		io.zPos = z;
		io.worldX = worldX;
		io.worldY = worldY;
		io.interactiveObjUID = interactiveObjUID;
		io.worldZ = worldZ;
		io.node = node;
		io.node.x = worldX;
		io.node.y = worldY;
		io.rotation = rotation;
		io.tileLeft = tileLeft;
		io.tileTop = tileTop;
		io.tileRight = (tileLeft + tileRight) - 1;
		io.tileBottom = (tileTop + tileBottom) - 1;
		for (int x = tileLeft; x < tileLeft + tileRight; x++) {
			for (int y = tileTop; y < tileTop + tileBottom; y++) {
				int position = 0;
				if (x > tileLeft)
					position++;
				if (x < (tileLeft + tileRight) - 1)
					position += 4;
				if (y > tileTop)
					position += 8;
				if (y < (tileTop + tileBottom) - 1)
					position += 2;
				for (int zPtr = z; zPtr >= 0; zPtr--)
					if (tileArray[zPtr][x][y] == null)
						tileArray[zPtr][x][y] = new Tile(zPtr, x, y);

				Tile tile = tileArray[z][x][y];
				tile.interactableObjects[tile.entityCount] = io;
				tile.anIntArray1319[tile.entityCount] = position;
				tile.anInt1320 |= position;
				tile.entityCount++;
			}
		}

		if (interactiveObjUID > 0) {
			if (Client.instance.getRuneLite() != null) {
				GameObjectSpawned spawn = new GameObjectSpawned();
				spawn.setTile(tileArray[z][tileLeft][tileTop]);
				spawn.setGameObject(io);
				Client.instance.getCallbacks().post(spawn);
			}
		}

		if (flag)
			interactableObjectCache[amountOfInteractableObjects++] = io;
		return true;
	}

	public void clearInteractableObjects() {
		for (int i = 0; i < amountOfInteractableObjects; i++) {
			InteractableObject iObject = interactableObjectCache[i];
			updateObjectEntities(iObject);
			interactableObjectCache[i] = null;
		}

		amountOfInteractableObjects = 0;
	}

	private void updateObjectEntities(InteractableObject iObject) {
		for (int j = iObject.tileLeft; j <= iObject.tileRight; j++) {
			for (int k = iObject.tileTop; k <= iObject.tileBottom; k++) {
				Tile tile = tileArray[iObject.zPos][j][k];
				if (tile != null) {
					for (int l = 0; l < tile.entityCount; l++) {
						if (tile.interactableObjects[l] != iObject)
							continue;
						tile.entityCount--;
						for (int entityPtr = l; entityPtr < tile.entityCount; entityPtr++) {
							tile.interactableObjects[entityPtr] = tile.interactableObjects[entityPtr + 1];
							tile.anIntArray1319[entityPtr] = tile.anIntArray1319[entityPtr + 1];
						}

						tile.interactableObjects[tile.entityCount] = null;
						break;
					}

					tile.anInt1320 = 0;
					for (int j1 = 0; j1 < tile.entityCount; j1++)
						tile.anInt1320 |= tile.anIntArray1319[j1];

				}
			}

		}

	}

	public void moveWallDec(int y, int moveAmt, int x, int z) {
		Tile tile = tileArray[z][x][y];
		if (tile == null)
			return;
		WallDecoration wallDec = tile.wallDecoration;
		if (wallDec != null) {
			int xCoord = x * 128 + 64;
			int yCoord = y * 128 + 64;
			wallDec.xPos = xCoord + ((wallDec.xPos - xCoord) * moveAmt) / 16;
			wallDec.yPos = yCoord + ((wallDec.yPos - yCoord) * moveAmt) / 16;
		}
	}

	public void removeWallObject(int x, int y, int z) {
		Tile tile = tileArray[y][x][z];
		if (tile != null) {

			if (Client.instance.getRuneLite() != null) {
				WallObjectDespawned despawn = new WallObjectDespawned();
				despawn.setWallObject(tile.wallObject);
				Client.instance.getCallbacks().post(despawn);
			}

			tile.wallObject = null;
		}
	}

	public void removeWallDecoration(int y, int z, int x) {
		Tile tile = tileArray[z][x][y];
		if (tile != null) {
			tile.wallDecoration = null;
		}
	}

	public void removeInteractableObject(int z, int x, int y) {
		Tile tile = tileArray[z][x][y];
		if (tile == null)
			return;
		for (int i = 0; i < tile.entityCount; i++) {
			InteractableObject subObject = tile.interactableObjects[i];
			if ((subObject.uid >> 29 & 3) == 2 && subObject.tileLeft == x && subObject.tileTop == y) {

				if (Client.instance.getRuneLite() != null) {
					GameObjectDespawned despawn = new GameObjectDespawned();
					despawn.setTile(tile);
					despawn.setGameObject(subObject);
					Client.instance.getCallbacks().post(despawn);
				}

				updateObjectEntities(subObject);
				return;
			}
		}

	}

	public void removeGroundDecoration(int z, int y, int x) {
		Tile tile = tileArray[z][x][y];
		if (tile == null)
			return;

		if (Client.instance.getRuneLite() != null) {
			DecorativeObjectDespawned despawn = new DecorativeObjectDespawned();
			despawn.setTile(tile);
			despawn.setDecorativeObject(tile.groundDecoration);
			Client.instance.getCallbacks().post(despawn);
		}

		tile.groundDecoration = null;
	}

	public void removeGroundItemFromTIle(int z, int x, int y) {
		Tile tile = tileArray[z][x][y];
		if (tile != null) {

			if (Client.instance.getRuneLite() != null) {
				GroundObjectDespawned despawn = new GroundObjectDespawned();
				despawn.setTile(tile);
				despawn.setGroundObject(tile.groundItem);
				Client.instance.getCallbacks().post(despawn);
			}

			tile.groundItem = null;
		}
	}

	public Tile getTile(int z, int x, int y) {
		return tileArray[z][x][y];
	}

	public WallObject getWallObject(int z, int x, int y) {
		Tile tile = tileArray[z][x][y];
		if (tile == null)
			return null;
		else
			return tile.wallObject;
	}

	public WallDecoration getWallDecoration(int x, int y, int z) {
		Tile tile = tileArray[z][x][y];
		if (tile == null)
			return null;
		else
			return tile.wallDecoration;
	}

	public InteractableObject getInteractableObject(int x, int y, int z) {
		Tile tile = tileArray[z][x][y];
		if (tile == null)
			return null;
		for (int l = 0; l < tile.entityCount; l++) {
			InteractableObject subObject = tile.interactableObjects[l];
			if ((subObject.uid >> 29 & 3) == 2 && subObject.tileLeft == x && subObject.tileTop == y)
				return subObject;
		}
		return null;
	}

	public GroundDecoration getGroundDecoration(int y, int x, int z) {
		Tile tile = tileArray[z][x][y];
		if (tile == null || tile.groundDecoration == null)
			return null;
		else
			return tile.groundDecoration;
	}

	public int getWallObjectUID(int z, int x, int y) {
		Tile tile = tileArray[z][x][y];
		if (tile == null || tile.wallObject == null)
			return 0;
		else
			return tile.wallObject.uid;
	}

	public int getWallDecorationUID(int z, int x, int y) {
		Tile tile = tileArray[z][x][y];
		if (tile == null || tile.wallDecoration == null)
			return 0;
		else
			return tile.wallDecoration.uid;
	}

	public int getInteractableObjectUID(int plane, int x, int y) {
		Tile tile = tileArray[plane][x][y];
		if (tile == null)
			return 0;
		for (int i = 0; i < tile.entityCount; i++) {
			InteractableObject iObject = tile.interactableObjects[i];
			if (iObject.tileLeft == x && iObject.tileTop == y)
				return iObject.uid;
		}

		return 0;
	}

	public int getGroundDecorationUID(int z, int x, int y) {
		Tile tile = tileArray[z][x][y];
		if (tile == null || tile.groundDecoration == null)
			return 0;
		else
			return tile.groundDecoration.uid;
	}

	public int getIDTagForXYZ(int z, int x, int y, int uidMatch) {
		Tile tile = tileArray[z][x][y];
		if (tile == null)
			return -1;
		if (tile.wallObject != null && tile.wallObject.uid == uidMatch)
			return tile.wallObject.objConfig & 0xff;
		if (tile.wallDecoration != null && tile.wallDecoration.uid == uidMatch)
			return tile.wallDecoration.objConfig & 0xff;
		if (tile.groundDecoration != null && tile.groundDecoration.uid == uidMatch)
			return tile.groundDecoration.objConfig & 0xff;
		for (int entityPtr = 0; entityPtr < tile.entityCount; entityPtr++)
			if (tile.interactableObjects[entityPtr].uid == uidMatch)
				return tile.interactableObjects[entityPtr].objConf & 0xff;

		return -1;
	}

	public void shadeModels(int i, int k, int i1) {
		int j = 100;
		int l = 5500;
		int j1 = (int) Math.sqrt(k * k + i * i + i1 * i1);
		int k1 = l >> 4;
		for (int l1 = 0; l1 < zMapSize; l1++) {
			for (int i2 = 0; i2 < xMapSize; i2++) {
				for (int j2 = 0; j2 < yMapSize; j2++) {
					Tile class30_sub3 = tileArray[l1][i2][j2];
					if (class30_sub3 != null) {
						WallObject class10 = class30_sub3.wallObject;
						if (class10 != null && class10.node1 != null && class10.node1.vertexNormals != null) {
							mergeModels(l1, 1, 1, i2, j2, (Model) class10.node1);
							if (class10.node2 != null && class10.node2.vertexNormals != null) {
								mergeModels(l1, 1, 1, i2, j2, (Model) class10.node2);
								renderModels((Model) class10.node1, (Model) class10.node2, 0, 0, 0, false);
								((Model) class10.node2).method480(j, k1, k, i, i1);
							}
							((Model) class10.node1).method480(j, k1, k, i, i1);
						}
						for (int k2 = 0; k2 < class30_sub3.entityCount; k2++) {
							InteractableObject class28 = class30_sub3.interactableObjects[k2];
							if (class28 != null && class28.node != null && class28.node.vertexNormals != null) {
								mergeModels(l1, (class28.tileRight - class28.tileLeft) + 1, (class28.tileBottom - class28.tileTop) + 1, i2, j2, (Model) class28.node);
								((Model) class28.node).method480(j, k1, k, i, i1);
							}
						}

						GroundDecoration class49 = class30_sub3.groundDecoration;
						if (class49 != null && class49.node.vertexNormals != null) {
							renderGrounDec(i2, l1, (Model) class49.node, j2);
							((Model) class49.node).method480(j, k1, k, i, i1);
						}
					}
				}

			}

		}

	}

	private void renderGrounDec(int i, int j, Model model, int k) {
		if (i < xMapSize) {
			Tile class30_sub3 = tileArray[j][i + 1][k];
			if (class30_sub3 != null && class30_sub3.groundDecoration != null && class30_sub3.groundDecoration.node.vertexNormals != null)
				renderModels(model, (Model) class30_sub3.groundDecoration.node, 128, 0, 0, true);
		}
		if (k < xMapSize) {
			Tile class30_sub3_1 = tileArray[j][i][k + 1];
			if (class30_sub3_1 != null && class30_sub3_1.groundDecoration != null && class30_sub3_1.groundDecoration.node.vertexNormals != null)
				renderModels(model, (Model) class30_sub3_1.groundDecoration.node, 0, 0, 128, true);
		}
		if (i < xMapSize && k < yMapSize) {
			Tile class30_sub3_2 = tileArray[j][i + 1][k + 1];
			if (class30_sub3_2 != null && class30_sub3_2.groundDecoration != null && class30_sub3_2.groundDecoration.node.vertexNormals != null)
				renderModels(model, (Model) class30_sub3_2.groundDecoration.node, 128, 0, 128, true);
		}
		if (i < xMapSize && k > 0) {
			Tile class30_sub3_3 = tileArray[j][i + 1][k - 1];
			if (class30_sub3_3 != null && class30_sub3_3.groundDecoration != null && class30_sub3_3.groundDecoration.node.vertexNormals != null)
				renderModels(model, (Model) class30_sub3_3.groundDecoration.node, 128, 0, -128, true);
		}
	}

	private void mergeModels(int z, int j, int k, int x, int y, Model model) {
		boolean flag = true;
		int j1 = x;
		int k1 = x + j;
		int l1 = y - 1;
		int i2 = y + k;
		for (int j2 = z; j2 <= z + 1; j2++)
			if (j2 != zMapSize) {
				for (int k2 = j1; k2 <= k1; k2++)
					if (k2 >= 0 && k2 < xMapSize) {
						for (int l2 = l1; l2 <= i2; l2++)
							if (l2 >= 0 && l2 < yMapSize && (!flag || k2 >= k1 || l2 >= i2 || l2 < y && k2 != x)) {
								Tile class30_sub3 = tileArray[j2][k2][l2];
								if (class30_sub3 != null) {
									int i3 = (anIntArrayArrayArray440[j2][k2][l2] + anIntArrayArrayArray440[j2][k2 + 1][l2] + anIntArrayArrayArray440[j2][k2][l2 + 1] + anIntArrayArrayArray440[j2][k2 + 1][l2 + 1]) / 4 - (anIntArrayArrayArray440[z][x][y] + anIntArrayArrayArray440[z][x + 1][y] + anIntArrayArrayArray440[z][x][y + 1] + anIntArrayArrayArray440[z][x + 1][y + 1]) / 4;
									WallObject class10 = class30_sub3.wallObject;
									if (class10 != null && class10.node1 != null && class10.node1.vertexNormals != null)
										renderModels(model, (Model) class10.node1, (k2 - x) * 128 + (1 - j) * 64, i3, (l2 - y) * 128 + (1 - k) * 64, flag);
									if (class10 != null && class10.node2 != null && class10.node2.vertexNormals != null)
										renderModels(model, (Model) class10.node2, (k2 - x) * 128 + (1 - j) * 64, i3, (l2 - y) * 128 + (1 - k) * 64, flag);
									for (int j3 = 0; j3 < class30_sub3.entityCount; j3++) {
										InteractableObject class28 = class30_sub3.interactableObjects[j3];
										if (class28 != null && class28.node != null && class28.node.vertexNormals != null) {
											int k3 = (class28.tileRight - class28.tileLeft) + 1;
											int l3 = (class28.tileBottom - class28.tileTop) + 1;
											renderModels(model, (Model) class28.node, (class28.tileLeft - x) * 128 + (k3 - j) * 64, i3, (class28.tileTop - y) * 128 + (l3 - k) * 64, flag);
										}
									}

								}
							}

					}

				j1--;
				flag = false;
			}

	}

	private void renderModels(Model model, Model model_1, int i, int j, int k, boolean flag) {
		anInt488++;
		int l = 0;
		int ai[] = model_1.verticesXCoordinate;
		int amtOfVertices = model_1.numberOfVerticeCoordinates;
		for (int verticeId = 0; verticeId < model.numberOfVerticeCoordinates; verticeId++) {
			VertexNormal vertexNormal = model.vertexNormals[verticeId];
			VertexNormal vertexNormalOff = model.vertexNormalOffset[verticeId];
			if (vertexNormalOff.anInt605 != 0) {
				int vertY = model.verticesYCoordinate[verticeId] - j;
				if (vertY <= model_1.anInt1651) {
					int vertX = model.verticesXCoordinate[verticeId] - i;
					if (vertX >= model_1.anInt1646 && vertX <= model_1.anInt1647) {
						int vertZ = model.verticesZCoordinate[verticeId] - k;
						if (vertZ >= model_1.anInt1649 && vertZ <= model_1.anInt1648) {
							for (int vertId_1 = 0; vertId_1 < amtOfVertices; vertId_1++) {
								VertexNormal class33_2 = model_1.vertexNormals[vertId_1];
								VertexNormal class33_3 = model_1.vertexNormalOffset[vertId_1];
								if (vertX == ai[vertId_1] && vertZ == model_1.verticesZCoordinate[vertId_1] && vertY == model_1.verticesYCoordinate[vertId_1] && class33_3.anInt605 != 0) {
									vertexNormal.anInt602 += class33_3.anInt602;
									vertexNormal.anInt603 += class33_3.anInt603;
									vertexNormal.anInt604 += class33_3.anInt604;
									vertexNormal.anInt605 += class33_3.anInt605;
									class33_2.anInt602 += vertexNormalOff.anInt602;
									class33_2.anInt603 += vertexNormalOff.anInt603;
									class33_2.anInt604 += vertexNormalOff.anInt604;
									class33_2.anInt605 += vertexNormalOff.anInt605;
									l++;
									anIntArray486[verticeId] = anInt488;
									anIntArray487[vertId_1] = anInt488;
								}
							}

						}
					}
				}
			}
		}

		if (l < 3 || !flag)
			return;
		for (int k1 = 0; k1 < model.numberOfTriangleFaces; k1++)
			if (anIntArray486[model.face_a[k1]] == anInt488 && anIntArray486[model.face_b[k1]] == anInt488 && anIntArray486[model.face_c[k1]] == anInt488)
				model.face_render_type[k1] = -1;

		for (int l1 = 0; l1 < model_1.numberOfTriangleFaces; l1++)
			if (anIntArray487[model_1.face_a[l1]] == anInt488 && anIntArray487[model_1.face_b[l1]] == anInt488 && anIntArray487[model_1.face_c[l1]] == anInt488)
				model_1.face_render_type[l1] = -1;

	}

	private boolean hdMinimap = true;

	public void method309(int pixels[], int pixelOffset, int z, int x, int y) {
		if (hdMinimap) {
			Tile class30_sub3 = tileArray[z][x][y];
			if(class30_sub3 == null) {
				return;
			}
			SceneTilePaint class43 = class30_sub3.plainTile;
			if(class43 != null) {
				if (class43.swColor != 12345678) {
					if (class43.rgb == 0) {
						return;
					}
					int hs = class43.swColor & ~0x7f;
					int l1 = class43.nwColor & 0x7f;
					int l2 = class43.neColor & 0x7f;
					int l3 = (class43.swColor & 0x7f) - l1;
					int l4 = (class43.seColor & 0x7f) - l2;
					l1 <<= 2;
					l2 <<= 2;
					for(int k1 = 0; k1 < 4; k1++) {
						if (!class43.textured) {
							pixels[pixelOffset] = Rasterizer.anIntArray1482[hs | (l1 >> 2)];
							pixels[pixelOffset + 1] = Rasterizer.anIntArray1482[hs | (l1 * 3 + l2 >> 4)];
							pixels[pixelOffset + 2] = Rasterizer.anIntArray1482[hs | (l1 + l2 >> 3)];
							pixels[pixelOffset + 3] = Rasterizer.anIntArray1482[hs | (l1 + l2 * 3 >> 4)];
						} else {
							int j1 = class43.rgb;
							int lig = 0xff - ((l1 >> 1) * (l1 >> 1) >> 8);
							pixels[pixelOffset] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							lig = 0xff - ((l1 * 3 + l2 >> 3) * (l1 * 3 + l2 >> 3) >> 8);
							pixels[pixelOffset + 1] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							lig = 0xff - ((l1 + l2 >> 2) * (l1 + l2 >> 2) >> 8);
							pixels[pixelOffset + 2] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
							lig = 0xff - ((l1 + l2 * 3 >> 3) * (l1 + l2 * 3 >> 3) >> 8);
							pixels[pixelOffset + 3] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
						}
						l1 += l3;
						l2 += l4;
						pixelOffset += 512;
					}
					return;
				}
				int mapColor = class43.rgb;
				if(mapColor == 0) {
					return;
				}
				for(int k1 = 0; k1 < 4; k1++) {
					pixels[pixelOffset] = mapColor;
					pixels[pixelOffset + 1] = mapColor;
					pixels[pixelOffset + 2] = mapColor;
					pixels[pixelOffset + 3] = mapColor;
					pixelOffset += 512;
				}
				return;
			}
			SceneTileModel class40 = class30_sub3.shapedTile;
			if(class40 == null) {
				return;
			}
			int l1 = class40.shape;
			int i2 = class40.rotation;
			int j2 = class40.underlay;
			int k2 = class40.overlay;
			int ai1[] = tileShapePoints[l1];
			int ai2[] = tileShapeIndices[i2];
			int l2 = 0;
			if (class40.color62 != 12345678) {
				int hs1 = class40.color62 & ~0x7f;
				int l11 = class40.color92 & 0x7f;
				int l21 = class40.color82 & 0x7f;
				int l31 = (class40.color62 & 0x7f) - l11;
				int l41 = (class40.color72 & 0x7f) - l21;
				l11 <<= 2;
				l21 <<= 2;
				for(int k1 = 0; k1 < 4; k1++) {
					if (!class40.textured) {
						if(ai1[ai2[l2++]] != 0) {
							pixels[pixelOffset] = Rasterizer.anIntArray1482[hs1 | (l11 >> 2)];
						}
						if(ai1[ai2[l2++]] != 0) {
							pixels[pixelOffset + 1] = Rasterizer.anIntArray1482[hs1 | (l11 * 3 + l21 >> 4)];
						}
						if(ai1[ai2[l2++]] != 0) {
							pixels[pixelOffset + 2] = Rasterizer.anIntArray1482[hs1 | (l11 + l21 >> 3)];
						}
						if(ai1[ai2[l2++]] != 0) {
							pixels[pixelOffset + 3] = Rasterizer.anIntArray1482[hs1 | (l11 + l21 * 3 >> 4)];
						}
					} else {
						int j1 = k2;
						if(ai1[ai2[l2++]] != 0) {
							int lig = 0xff - ((l11 >> 1) * (l11 >> 1) >> 8);
							pixels[pixelOffset] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
						}
						if(ai1[ai2[l2++]] != 0) {
							int lig = 0xff - ((l11 * 3 + l21 >> 3) * (l11 * 3 + l21 >> 3) >> 8);
							pixels[pixelOffset + 1] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
						}
						if(ai1[ai2[l2++]] != 0) {
							int lig = 0xff - ((l11 + l21 >> 2) * (l11 + l21 >> 2) >> 8);
							pixels[pixelOffset + 2] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
						}
						if(ai1[ai2[l2++]] != 0) {
							int lig = 0xff - ((l11 + l21 * 3 >> 3) * (l11 + l21 * 3 >> 3) >> 8);
							pixels[pixelOffset + 3] = ((j1 & 0xff00ff) * lig & ~0xff00ff) + ((j1 & 0xff00) * lig & 0xff0000) >> 8;
						}
					}
					l11 += l31;
					l21 += l41;
					pixelOffset += 512;
				}
				if (j2 != 0 && class40.color61 != 12345678) {
					pixelOffset -= 512 << 2;
					l2 -= 16;
					hs1 = class40.color61 & ~0x7f;
					l11 = class40.color91 & 0x7f;
					l21 = class40.color81 & 0x7f;
					l31 = (class40.color61 & 0x7f) - l11;
					l41 = (class40.color71 & 0x7f) - l21;
					l11 <<= 2;
					l21 <<= 2;
					for(int k1 = 0; k1 < 4; k1++) {
						if(ai1[ai2[l2++]] == 0) {
							pixels[pixelOffset] = Rasterizer.anIntArray1482[hs1 | (l11 >> 2)];
						}
						if(ai1[ai2[l2++]] == 0) {
							pixels[pixelOffset + 1] = Rasterizer.anIntArray1482[hs1 | (l11 * 3 + l21 >> 4)];
						}
						if(ai1[ai2[l2++]] == 0) {
							pixels[pixelOffset + 2] = Rasterizer.anIntArray1482[hs1 | (l11 + l21 >> 3)];
						}
						if(ai1[ai2[l2++]] == 0) {
							pixels[pixelOffset + 3] = Rasterizer.anIntArray1482[hs1 | (l11 + l21 * 3 >> 4)];
						}
						l11 += l31;
						l21 += l41;
						pixelOffset += 512;
					}
				}
				return;
			}
			if(j2 != 0) {
				for(int i3 = 0; i3 < 4; i3++) {
					pixels[pixelOffset] = ai1[ai2[l2++]] != 0 ? k2 : j2;
					pixels[pixelOffset + 1] = ai1[ai2[l2++]] != 0 ? k2 : j2;
					pixels[pixelOffset + 2] = ai1[ai2[l2++]] != 0 ? k2 : j2;
					pixels[pixelOffset + 3] = ai1[ai2[l2++]] != 0 ? k2 : j2;
					pixelOffset += 512;
				}
				return;
			}
			for(int j3 = 0; j3 < 4; j3++) {
				if(ai1[ai2[l2++]] != 0) {
					pixels[pixelOffset] = k2;
				}
				if(ai1[ai2[l2++]] != 0) {
					pixels[pixelOffset + 1] = k2;
				}
				if(ai1[ai2[l2++]] != 0) {
					pixels[pixelOffset + 2] = k2;
				}
				if(ai1[ai2[l2++]] != 0) {
					pixels[pixelOffset + 3] = k2;
				}
				pixelOffset += 512;
			}
		} else {
			int j = 512;//was parameter
			Tile class30_sub3 = tileArray[z][x][y];
			if(class30_sub3 == null)
				return;
			SceneTilePaint class43 = class30_sub3.plainTile;
			if(class43 != null)
			{
				int j1 = class43.rgb;
				if(j1 == 0)
					return;
				for(int k1 = 0; k1 < 4; k1++)
				{
					pixels[pixelOffset] = j1;
					pixels[pixelOffset + 1] = j1;
					pixels[pixelOffset + 2] = j1;
					pixels[pixelOffset + 3] = j1;
					pixelOffset += j;
				}

				return;
			}
			SceneTileModel class40 = class30_sub3.shapedTile;
			if(class40 == null)
				return;
			int l1 = class40.shape;
			int i2 = class40.rotation;
			int j2 = class40.underlay;
			int k2 = class40.overlay;
			int ai1[] = tileShapePoints[l1];
			int ai2[] = tileShapeIndices[i2];
			int l2 = 0;
			if(j2 != 0)
			{
				for(int i3 = 0; i3 < 4; i3++)
				{
					pixels[pixelOffset] = ai1[ai2[l2++]] != 0 ? k2 : j2;
					pixels[pixelOffset + 1] = ai1[ai2[l2++]] != 0 ? k2 : j2;
					pixels[pixelOffset + 2] = ai1[ai2[l2++]] != 0 ? k2 : j2;
					pixels[pixelOffset + 3] = ai1[ai2[l2++]] != 0 ? k2 : j2;
					pixelOffset += j;
				}

				return;
			}
			for(int j3 = 0; j3 < 4; j3++)
			{
				if(ai1[ai2[l2++]] != 0)
					pixels[pixelOffset] = k2;
				if(ai1[ai2[l2++]] != 0)
					pixels[pixelOffset + 1] = k2;
				if(ai1[ai2[l2++]] != 0)
					pixels[pixelOffset + 2] = k2;
				if(ai1[ai2[l2++]] != 0)
					pixels[pixelOffset + 3] = k2;
				pixelOffset += j;
			}
		}
	}


	public static void setupViewport(int minZ, int maxZ, int width, int height, int ai[]) {
		left = 0;
		top = 0;
		right = width;
		bottom = height;
		midX = width / 2;
		midY = height / 2;
		boolean isOnScreen[][][][] = new boolean[9][32][(300 * 2) + 3][(300 * 2) + 3];
		for (int yAngle = 128; yAngle <= 384; yAngle += 32) {
			for (int xAngle = 0; xAngle < 2048; xAngle += 64) {
				camUpDownY = Model.SINE[yAngle];
				camUpDownX = Model.COSINE[yAngle];
				camLeftRightY = Model.SINE[xAngle];
				camLeftRightX = Model.COSINE[xAngle];
				int l1 = (yAngle - 128) / 32;
				int j2 = xAngle / 64;
				for (int l2 = -26; l2 <= 26; l2++) {
					for (int j3 = -26; j3 <= 26; j3++) {
						int k3 = l2 * 128;
						int i4 = j3 * 128;
						boolean flag2 = false;
						for (int k4 = -minZ; k4 <= maxZ; k4 += 128) {
							if (!isOnScreen(ai[l1] + k4, i4, k3))
								continue;
							flag2 = true;
							break;
						}

						isOnScreen[l1][j2][l2 + getDrawDistance() + 1][j3 + getDrawDistance() + 1] = flag2;
					}

				}

			}

		}

		for (int k1 = 0; k1 < 8; k1++) {
			for (int i2 = 0; i2 < 32; i2++) {
				for (int k2 = -getDrawDistance(); k2 < getDrawDistance(); k2++) {
					for (int i3 = -getDrawDistance(); i3 < getDrawDistance(); i3++) {
						boolean flag1 = false;
						label0: for (int l3 = -1; l3 <= 1; l3++) {
							for (int j4 = -1; j4 <= 1; j4++) {
								if (isOnScreen[k1][i2][k2 + l3 + getDrawDistance() + 1][i3 + j4 + getDrawDistance() + 1])
									flag1 = true;
								else if (isOnScreen[k1][(i2 + 1) % 31][k2 + l3 + getDrawDistance() + 1][i3 + j4 + getDrawDistance() + 1])
									flag1 = true;
								else if (isOnScreen[k1 + 1][i2][k2 + l3 + getDrawDistance() + 1][i3 + j4 + getDrawDistance() + 1]) {
									flag1 = true;
								} else {
									if (!isOnScreen[k1 + 1][(i2 + 1) % 31][k2 + l3 + getDrawDistance() + 1][i3 + j4 + getDrawDistance() + 1])
										continue;
									flag1 = true;
								}
								break label0;
							}

						}

						tile_visibility_maps[k1][i2][k2+ getDrawDistance()][i3+ getDrawDistance()] = flag1;
					}

				}

			}

		}

	}

	private static boolean isOnScreen(int z, int y, int x) {
		int l = y * camLeftRightY + x * camLeftRightX >> 16;
		int i1 = y * camLeftRightX - x * camLeftRightY >> 16;
		int dist = z * camUpDownY + i1 * camUpDownX >> 16;
		int k1 = z * camUpDownX - i1 * camUpDownY >> 16;
		if (dist < 50 || dist >= MAX_RENDER_DISTANCE && Client.drawCallbacks == null)
			return false;
		int l1 = midX + (l << viewDistance) / dist;
		int i2 = midY + (k1 << viewDistance) / dist;
		return l1 >= left && l1 <= right && i2 >= top && i2 <= bottom;
	}

	public void request2DTrace(int x, int y) {
		aBoolean467 = true;
		anInt468 = (!Client.antialiasing || Client.drawCallbacks != null) ? y : y << 1;
		anInt469 = (!Client.antialiasing || Client.drawCallbacks != null) ? x : x << 1;
		anInt470 = -1;
		anInt471 = -1;
	}

	public void requestTileTrace(int x, int y) {
		requestTileTrace = true;
		requestedTraceX = (!Client.antialiasing || Client.drawCallbacks != null) ? x : x << 1;
		requestedTraceY = (!Client.antialiasing || Client.drawCallbacks != null) ? y : y << 1;
	}

	public void render(int xCam, int yCam, int xCurve, int zCam, int plane, int yCurve) {
		final DrawCallbacks drawCallbacks = Client.drawCallbacks;

		if (drawCallbacks != null) {
			drawCallbacks.drawScene(xCam, zCam, yCam, yCurve, xCurve, plane);
		}

		if (xCam < 0)
			xCam = 0;
		else if (xCam >= xMapSize * 128)
			xCam = xMapSize * 128 - 1;
		if (yCam < 0)
			yCam = 0;
		else if (yCam >= yMapSize * 128)
			yCam = yMapSize * 128 - 1;
		anInt448++;
		camUpDownY = Model.SINE[yCurve];
		camUpDownX = Model.COSINE[yCurve];
		camLeftRightY = Model.SINE[xCurve];
		camLeftRightX = Model.COSINE[xCurve];
		tile_visibility_map = tile_visibility_maps[(yCurve - 128) / 32][xCurve / 64];
		cameraX2 = xCam;
		cameraY2 = zCam;
		cameraZ2 = yCam;
		xCamPosTile = xCam / 128;
		yCamPosTile = yCam / 128;
		plane__ = plane;
		anInt449 = xCamPosTile - getDrawDistance();
		if (anInt449 < 0)
			anInt449 = 0;
		anInt451 = yCamPosTile - getDrawDistance();
		if (anInt451 < 0)
			anInt451 = 0;
		anInt450 = xCamPosTile + getDrawDistance();
		if (anInt450 > xMapSize)
			anInt450 = xMapSize;
		anInt452 = yCamPosTile + getDrawDistance();
		if (anInt452 > yMapSize)
			anInt452 = yMapSize;
		processCulling();
		anInt446 = 0;
		boolean isGpu = Client.drawCallbacks != null;
		for (int k1 = currentHL; k1 < zMapSize; k1++) {
			Tile tiles[][] = tileArray[k1];
			for (int x_ = anInt449; x_ < anInt450; x_++) {
				for (int y_ = anInt451; y_ < anInt452; y_++) {
					Tile tile = tiles[x_][y_];
					if (tile != null) {
						if (tile.logicHeight > plane || (!isGpu && Client.instance.getRegionId() != 12611 && !tile_visibility_map[(x_ - xCamPosTile)+ getDrawDistance()][(y_ - yCamPosTile)+ getDrawDistance()] && anIntArrayArrayArray440[k1][x_][y_] - zCam < 2000)) {
							tile.aBoolean1322 = false;
							tile.aBoolean1323 = false;
							tile.anInt1325 = 0;
						} else {
							tile.aBoolean1322 = true;
							tile.aBoolean1323 = true;
							tile.aBoolean1324 = tile.entityCount > 0;
							anInt446++;
						}
					}
				}

			}

		}

		for (int l1 = currentHL; l1 < zMapSize; l1++) {
			Tile aclass30_sub3_1[][] = tileArray[l1];
			for (int l2 = -getDrawDistance(); l2 <= 0; l2++) {
				int i3 = xCamPosTile + l2;
				int k3 = xCamPosTile - l2;
				if (i3 >= anInt449 || k3 < anInt450) {
					for (int i4 = -getDrawDistance(); i4 <= 0; i4++) {
						int k4 = yCamPosTile + i4;
						int i5 = yCamPosTile - i4;
						if (i3 >= anInt449) {
							if (k4 >= anInt451) {
								Tile class30_sub3_1 = aclass30_sub3_1[i3][k4];
								if (class30_sub3_1 != null && class30_sub3_1.aBoolean1322) {
									renderTile(class30_sub3_1, true);
								}
							}
							if (i5 < anInt452) {
								Tile class30_sub3_2 = aclass30_sub3_1[i3][i5];
								if (class30_sub3_2 != null && class30_sub3_2.aBoolean1322) {
									renderTile(class30_sub3_2, true);
								}
							}
						}
						if (k3 < anInt450) {
							if (k4 >= anInt451) {
								Tile class30_sub3_3 = aclass30_sub3_1[k3][k4];
								if (class30_sub3_3 != null && class30_sub3_3.aBoolean1322) {
									renderTile(class30_sub3_3, true);
								}
							}
							if (i5 < anInt452) {
								Tile class30_sub3_4 = aclass30_sub3_1[k3][i5];
								if (class30_sub3_4 != null && class30_sub3_4.aBoolean1322) {
									renderTile(class30_sub3_4, true);
								}
							}
						}



						if (anInt446 == 0) {
							aBoolean467 = false;
							requestTileTrace = false;

							if (drawCallbacks != null) {
								drawCallbacks.postDrawScene();
							}
							return;
						}
					}

				}
			}

		}

		for (int j2 = currentHL; j2 < zMapSize; j2++) {
			Tile aclass30_sub3_2[][] = tileArray[j2];
			for (int j3 = -getDrawDistance(); j3 <= 0; j3++) {
				int l3 = xCamPosTile + j3;
				int j4 = xCamPosTile - j3;
				if (l3 >= anInt449 || j4 < anInt450) {
					for (int l4 = -getDrawDistance(); l4 <= 0; l4++) {
						int j5 = yCamPosTile + l4;
						int k5 = yCamPosTile - l4;
						if (l3 >= anInt449) {
							if (j5 >= anInt451) {
								Tile class30_sub3_5 = aclass30_sub3_2[l3][j5];
								if (class30_sub3_5 != null && class30_sub3_5.aBoolean1322) {
									renderTile(class30_sub3_5, false);
								}
							}
							if (k5 < anInt452) {
								Tile class30_sub3_6 = aclass30_sub3_2[l3][k5];
								if (class30_sub3_6 != null && class30_sub3_6.aBoolean1322) {
									renderTile(class30_sub3_6, false);
								}
							}
						}
						if (j4 < anInt450) {
							if (j5 >= anInt451) {
								Tile class30_sub3_7 = aclass30_sub3_2[j4][j5];
								if (class30_sub3_7 != null && class30_sub3_7.aBoolean1322) {
									renderTile(class30_sub3_7, false);
								}
							}
							if (k5 < anInt452) {
								Tile tile = aclass30_sub3_2[j4][k5];
								if (tile != null && tile.aBoolean1322) {
									renderTile(tile, false);
								}
							}
						}
						if (anInt446 == 0) {
							aBoolean467 = false;
							requestTileTrace = false;
							return;
						}
					}

				}
			}
		}

		aBoolean467 = false;
		requestTileTrace = false;

		if (drawCallbacks != null) {
			drawCallbacks.postDrawScene();
		}
	}

	public static Map<Integer, List<Position>> markedTiles = new HashMap<>();

	public static Map<Integer, List<Position>> highlighted = new HashMap<>();

	public boolean isMarked(Tile tile) {
		Position pos = new Position(Client.instance.getBaseX() + tile.tileX, Client.instance.getBaseY() + tile.tileY, tile.plane);

		if (!Client.instance.getPlayerPos().isWithinDistance(pos)) {
			return false;
		}

		if (!markedTiles.containsKey(pos.getRegionID())) {
			return false;
		}

		return markedTiles.get(pos.getRegionID()).contains(pos);
	}

	public boolean isHighlighted(Tile tile) {
		Position pos = new Position(Client.instance.getBaseX() + tile.tileX, Client.instance.getBaseY() + tile.tileY, tile.plane);

		if (!Client.instance.getPlayerPos().isWithinDistance(pos)) {
			return false;
		}

		if (!highlighted.containsKey(pos.getRegionID())) {
			return false;
		}

		return highlighted.get(pos.getRegionID()).contains(pos);
	}

	public void markTile(int x, int y, int z) {
		Position pos = new Position(Client.instance.getBaseX() + x, Client.instance.getBaseY() + y, z);

		if (!Client.instance.getPlayerPos().isWithinDistance(pos)) {
			Client.instance.pushMessage("You are too far away to do that.", 0, "");
			return;
		}

		if (markedTiles.containsKey(pos.getRegionID())) {
			List<Position> list = markedTiles.get(pos.getRegionID());

			if (list.contains(pos)) {
				list.remove(pos);

				if (list.isEmpty()) {
					markedTiles.remove(pos.getRegionID());
				}
			} else {
				if (list.size() >= 50) {
					Client.instance.pushMessage("You have reached the limit of marking tiles.", 0, "");
					return;
				}

				list.add(pos);
			}

			markedTiles.put(pos.getRegionID(), list);
		} else {
			markedTiles.put(pos.getRegionID(), new ArrayList<>(Arrays.asList(pos)));
		}
	}

	public void highlight(int x, int y, int z, boolean local) {
		Position pos = new Position(local ? Client.instance.getBaseX() : 0 + x, local ? Client.instance.getBaseY() : 0 + y, z);

		if (!Client.instance.getPlayerPos().isWithinDistance(pos)) {
			Client.instance.pushMessage("You are too far away to do that.", 0, "");
			return;
		}

		if (highlighted.containsKey(pos.getRegionID())) {
			List<Position> list = highlighted.get(pos.getRegionID());

			if (list.contains(pos)) {
				list.remove(pos);

				if (list.isEmpty()) {
					highlighted.remove(pos.getRegionID());
				}
			} else {
				if (list.size() >= 50) {
					Client.instance.pushMessage("You have reached the limit of marking tiles.", 0, "");
					return;
				}

				list.add(pos);
			}

			highlighted.put(pos.getRegionID(), list);
		} else {
			highlighted.put(pos.getRegionID(), new ArrayList<>(Arrays.asList(pos)));
		}
	}

	public void renderTileMarkers() {
		for (int k1 = currentHL; k1 < zMapSize; k1++) {
			Tile tiles[][] = tileArray[k1];
			for (int x_ = anInt449; x_ < anInt450; x_++) {
				for (int y_ = anInt451; y_ < anInt452; y_++) {
					Tile tile = tiles[x_][y_];

					if (tile == null) {
						continue;
					}

					if (tile.logicHeight > tile.plane || !tile_visibility_map[(x_ - xCamPosTile)+ getDrawDistance()][(y_ - yCamPosTile)+ getDrawDistance()] && anIntArrayArrayArray440[k1][x_][y_] - cameraY2 < 3000) {
						continue;
					}

					boolean highlighted = isHighlighted(tile);

					if (!isMarked(tile) && !highlighted) {
						continue;
					}

					int color = highlighted ? 0x0000ff : 0xffff00;

					drawTileMarker(tile.plane, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, tile.tileX, tile.tileY, color);
				}

			}

		}
	}

	private void renderTile(Tile mainTile, boolean flag) {
		tileDeque.insertBack(mainTile);
		do {
			Tile tempTile;
			do {
				tempTile = (Tile) tileDeque.popFront();
				if (tempTile == null)
					return;
			} while (!tempTile.aBoolean1323);
			int camera_x = tempTile.tileX;
			int camera_y = tempTile.tileY;
			int k = tempTile.tileZ;
			int plane = tempTile.plane;
			Tile tiles[][] = tileArray[k];
			if (tempTile.aBoolean1322) {
				if (flag) {
					if (k > 0) {
						Tile tile = tileArray[k - 1][camera_x][camera_y];
						if (tile != null && tile.aBoolean1323)
							continue;
					}
					if (camera_x <= xCamPosTile && camera_x > anInt449) {
						Tile tile = tiles[camera_x - 1][camera_y];
						if (tile != null && tile.aBoolean1323 && (tile.aBoolean1322 || (tempTile.anInt1320 & 1) == 0))
							continue;
					}
					if (camera_x >= xCamPosTile && camera_x < anInt450 - 1) {
						Tile tile = tiles[camera_x + 1][camera_y];
						if (tile != null && tile.aBoolean1323 && (tile.aBoolean1322 || (tempTile.anInt1320 & 4) == 0))
							continue;
					}
					if (camera_y <= yCamPosTile && camera_y > anInt451) {
						Tile class30_sub3_5 = tiles[camera_x][camera_y - 1];
						if (class30_sub3_5 != null && class30_sub3_5.aBoolean1323 && (class30_sub3_5.aBoolean1322 || (tempTile.anInt1320 & 8) == 0))
							continue;
					}
					if (camera_y >= yCamPosTile && camera_y < anInt452 - 1) {
						Tile class30_sub3_6 = tiles[camera_x][camera_y + 1];
						if (class30_sub3_6 != null && class30_sub3_6.aBoolean1323 && (class30_sub3_6.aBoolean1322 || (tempTile.anInt1320 & 2) == 0))
							continue;
					}
				} else {
					flag = true;
				}
				int xDiff = 0;
				int yDiff = 0;
				if(RuneLite.getClient() != null) {
					WorldPoint tileLocation = tempTile.getWorldLocation();
					WorldPoint playerLocation = Client.myPlayer.getWorldLocation();
					if (tileLocation.getX() > playerLocation.getX()) {
						xDiff = tileLocation.getX() - playerLocation.getX();
					} else {
						xDiff = playerLocation.getX() - tileLocation.getX();
					}
					if (tileLocation.getY() > playerLocation.getY()) {
						yDiff = tileLocation.getY() - playerLocation.getY();
					} else {
						yDiff = playerLocation.getY() - tileLocation.getY();
					}
				}
				int distance = xDiff + yDiff;
				tempTile.aBoolean1322 = false;
				if (tempTile.tileBelowThisTile != null) {
					Tile lowerTile = tempTile.tileBelowThisTile;
					if (lowerTile.plainTile != null) {
						if (!method320(0, camera_x, camera_y))
							drawPlainTile(lowerTile.plainTile, 0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, camera_x, camera_y);
					} else if (lowerTile.shapedTile != null && !method320(0, camera_x, camera_y))
						drawShapedTile(camera_x, camUpDownY, camLeftRightY, lowerTile.shapedTile, camUpDownX, camera_y, camLeftRightX);
					WallObject wallObject = lowerTile.wallObject;
					if (wallObject != null) {
						wallObject.node1.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY,
								camLeftRightX, wallObject.xPos - cameraX2, wallObject.zPos - cameraY2,
								wallObject.yPos - cameraZ2, wallObject.uid, wallObject.wallObjUID, distance);
					}
					for (int i2 = 0; i2 < lowerTile.entityCount; i2++) {
						InteractableObject iObject = lowerTile.interactableObjects[i2];
						if (iObject != null)
							iObject.node.renderAtPoint(iObject.rotation, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, iObject.worldX - cameraX2, iObject.worldZ - cameraY2, iObject.worldY - cameraZ2, iObject.uid, iObject.interactiveObjUID, distance);
					}

				}
				boolean flag1 = false;
				if (tempTile.plainTile != null) {
					if (!method320(plane, camera_x, camera_y)) {
						flag1 = true;
						drawPlainTile(tempTile.plainTile, plane, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, camera_x, camera_y);
					}
				} else if (tempTile.shapedTile != null && !method320(plane, camera_x, camera_y)) {
					flag1 = true;
					drawShapedTile(camera_x, camUpDownY, camLeftRightY, tempTile.shapedTile, camUpDownX, camera_y, camLeftRightX);
				}

				int j1 = 0;
				int j2 = 0;
				WallObject class10_3 = tempTile.wallObject;
				WallDecoration class26_1 = tempTile.wallDecoration;
				if (class10_3 != null || class26_1 != null) {
					if (xCamPosTile == camera_x)
						j1++;
					else if (xCamPosTile < camera_x)
						j1 += 2;
					if (yCamPosTile == camera_y)
						j1 += 3;
					else if (yCamPosTile > camera_y)
						j1 += 6;
					j2 = anIntArray478[j1];
					tempTile.anInt1328 = anIntArray480[j1];
				}
				if (class10_3 != null) {
					if ((class10_3.orientation & anIntArray479[j1]) != 0) {
						if (class10_3.orientation == 16) {
							tempTile.anInt1325 = 3;
							tempTile.anInt1326 = anIntArray481[j1];
							tempTile.anInt1327 = 3 - tempTile.anInt1326;
						} else if (class10_3.orientation == 32) {
							tempTile.anInt1325 = 6;
							tempTile.anInt1326 = anIntArray482[j1];
							tempTile.anInt1327 = 6 - tempTile.anInt1326;
						} else if (class10_3.orientation == 64) {
							tempTile.anInt1325 = 12;
							tempTile.anInt1326 = anIntArray483[j1];
							tempTile.anInt1327 = 12 - tempTile.anInt1326;
						} else {
							tempTile.anInt1325 = 9;
							tempTile.anInt1326 = anIntArray484[j1];
							tempTile.anInt1327 = 9 - tempTile.anInt1326;
						}
					} else {
						tempTile.anInt1325 = 0;
					}
					if ((class10_3.orientation & j2) != 0 && !method321(plane, camera_x, camera_y, class10_3.orientation))
						class10_3.node1.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, class10_3.xPos - cameraX2, class10_3.zPos - cameraY2, class10_3.yPos - cameraZ2, class10_3.uid, class10_3.wallObjUID, distance);
					if ((class10_3.orientation1 & j2) != 0 && !method321(plane, camera_x, camera_y, class10_3.orientation1))
						class10_3.node2.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, class10_3.xPos - cameraX2, class10_3.zPos - cameraY2, class10_3.yPos - cameraZ2, class10_3.uid, class10_3.wallObjUID, distance);
				}
				if (class26_1 != null && !method322(plane, camera_x, camera_y, class26_1.node.modelHeight))
					if ((class26_1.configurationBits & j2) != 0)
						class26_1.node.renderAtPoint(class26_1.rotation, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, class26_1.xPos - cameraX2, class26_1.zPos - cameraY2, class26_1.yPos - cameraZ2, class26_1.uid, class26_1.wallDecorUID, distance);
					else if ((class26_1.configurationBits & 0x300) != 0) {
						int j4 = class26_1.xPos - cameraX2;
						int l5 = class26_1.zPos - cameraY2;
						int k6 = class26_1.yPos - cameraZ2;
						int i8 = class26_1.rotation;
						int k9;
						if (i8 == 1 || i8 == 2)
							k9 = -j4;
						else
							k9 = j4;
						int k10;
						if (i8 == 2 || i8 == 3)
							k10 = -k6;
						else
							k10 = k6;
						if ((class26_1.configurationBits & 0x100) != 0 && k10 < k9) {
							int i11 = j4 + faceXoffset2[i8];
							int k11 = k6 + faceYOffset2[i8];
							class26_1.node.renderAtPoint(i8 * 512 + 256, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, i11, l5, k11, class26_1.uid, class26_1.wallDecorUID, distance);
						}
						if ((class26_1.configurationBits & 0x200) != 0 && k10 > k9) {
							int j11 = j4 + faceXOffset3[i8];
							int l11 = k6 + faceYOffset3[i8];
							class26_1.node.renderAtPoint(i8 * 512 + 1280 & 0x7ff, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, j11, l5, l11, class26_1.uid, class26_1.wallDecorUID, distance);
						}
					}
				if (flag1) {
					GroundDecoration class49 = tempTile.groundDecoration;
					if (class49 != null)
						class49.node.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, class49.xPos - cameraX2, class49.zPos - cameraY2, class49.yPos - cameraZ2, class49.uid, class49.groundDecorUID, distance);
					GroundItem object4_1 = tempTile.groundItem;
					if (object4_1 != null && object4_1.topItem == 0) {
						if (object4_1.secondGroundItem != null)
							object4_1.secondGroundItem.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, object4_1.xPos - cameraX2, object4_1.zPos - cameraY2, object4_1.yPos - cameraZ2, object4_1.uid, object4_1.newuid, distance);
						if (object4_1.thirdGroundItem != null)
							object4_1.thirdGroundItem.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, object4_1.xPos - cameraX2, object4_1.zPos - cameraY2, object4_1.yPos - cameraZ2, object4_1.uid, object4_1.newuid, distance);
						if (object4_1.firstGroundItem != null)
							object4_1.firstGroundItem.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, object4_1.xPos - cameraX2, object4_1.zPos - cameraY2, object4_1.yPos - cameraZ2, object4_1.uid, object4_1.newuid, distance);
					}
				}

				int k4 = tempTile.anInt1320;
				if (k4 != 0) {
					if (camera_x < xCamPosTile && (k4 & 4) != 0) {
						Tile class30_sub3_17 = tiles[camera_x + 1][camera_y];
						if (class30_sub3_17 != null && class30_sub3_17.aBoolean1323)
							tileDeque.insertBack(class30_sub3_17);
					}
					if (camera_y < yCamPosTile && (k4 & 2) != 0) {
						Tile class30_sub3_18 = tiles[camera_x][camera_y + 1];
						if (class30_sub3_18 != null && class30_sub3_18.aBoolean1323)
							tileDeque.insertBack(class30_sub3_18);
					}
					if (camera_x > xCamPosTile && (k4 & 1) != 0) {
						Tile class30_sub3_19 = tiles[camera_x - 1][camera_y];
						if (class30_sub3_19 != null && class30_sub3_19.aBoolean1323)
							tileDeque.insertBack(class30_sub3_19);
					}
					if (camera_y > yCamPosTile && (k4 & 8) != 0) {
						Tile class30_sub3_20 = tiles[camera_x][camera_y - 1];
						if (class30_sub3_20 != null && class30_sub3_20.aBoolean1323)
							tileDeque.insertBack(class30_sub3_20);
					}
				}
			}
			if (tempTile.anInt1325 != 0) {
				boolean flag2 = true;
				for (int k1 = 0; k1 < tempTile.entityCount; k1++) {
					if (tempTile.interactableObjects[k1].height == anInt448 || (tempTile.anIntArray1319[k1] & tempTile.anInt1325) != tempTile.anInt1326)
						continue;
					flag2 = false;
					break;
				}

				if (flag2) {
					WallObject class10_1 = tempTile.wallObject;
					if (!method321(plane, camera_x, camera_y, class10_1.orientation))
						class10_1.node1.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, class10_1.xPos - cameraX2, class10_1.zPos - cameraY2, class10_1.yPos - cameraZ2, class10_1.uid, class10_1.wallObjUID, distance);
					tempTile.anInt1325 = 0;
				}
			}
			if (tempTile.aBoolean1324)
				try {
					int i1 = tempTile.entityCount;
					tempTile.aBoolean1324 = false;
					int l1 = 0;
					label0: for (int k2 = 0; k2 < i1; k2++) {
						InteractableObject class28_1 = tempTile.interactableObjects[k2];
						if (class28_1.height == anInt448)
							continue;
						for (int k3 = class28_1.tileLeft; k3 <= class28_1.tileRight; k3++) {
							for (int l4 = class28_1.tileTop; l4 <= class28_1.tileBottom; l4++) {
								Tile class30_sub3_21 = tiles[k3][l4];
								if (class30_sub3_21.aBoolean1322) {
									tempTile.aBoolean1324 = true;
								} else {
									if (class30_sub3_21.anInt1325 == 0)
										continue;
									int l6 = 0;
									if (k3 > class28_1.tileLeft)
										l6++;
									if (k3 < class28_1.tileRight)
										l6 += 4;
									if (l4 > class28_1.tileTop)
										l6 += 8;
									if (l4 < class28_1.tileBottom)
										l6 += 2;
									if ((l6 & class30_sub3_21.anInt1325) != tempTile.anInt1327)
										continue;
									tempTile.aBoolean1324 = true;
								}
								continue label0;
							}

						}

						aClass28Array462[l1++] = class28_1;
						int i5 = xCamPosTile - class28_1.tileLeft;
						int i6 = class28_1.tileRight - xCamPosTile;
						if (i6 > i5)
							i5 = i6;
						int i7 = yCamPosTile - class28_1.tileTop;
						int j8 = class28_1.tileBottom - yCamPosTile;
						if (j8 > i7)
							class28_1.anInt527 = i5 + j8;
						else
							class28_1.anInt527 = i5 + i7;
					}

					while (l1 > 0) {
						int i3 = -50;
						int l3 = -1;
						for (int j5 = 0; j5 < l1; j5++) {
							InteractableObject class28_2 = aClass28Array462[j5];
							if (class28_2.height != anInt448)
								if (class28_2.anInt527 > i3) {
									i3 = class28_2.anInt527;
									l3 = j5;
								} else if (class28_2.anInt527 == i3) {
									int j7 = class28_2.worldX - cameraX2;
									int k8 = class28_2.worldY - cameraZ2;
									int l9 = aClass28Array462[l3].worldX - cameraX2;
									int l10 = aClass28Array462[l3].worldY - cameraZ2;
									if (j7 * j7 + k8 * k8 > l9 * l9 + l10 * l10)
										l3 = j5;
								}
						}

						if (l3 == -1)
							break;
						InteractableObject class28_3 = aClass28Array462[l3];
						class28_3.height = anInt448;
						if (!method323(plane, class28_3.tileLeft, class28_3.tileRight, class28_3.tileTop, class28_3.tileBottom, class28_3.node.modelHeight))
							class28_3.node.renderAtPoint(class28_3.rotation, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, class28_3.worldX - cameraX2, class28_3.worldZ - cameraY2, class28_3.worldY - cameraZ2, class28_3.uid, class28_3.interactiveObjUID);
						for (int k7 = class28_3.tileLeft; k7 <= class28_3.tileRight; k7++) {
							for (int l8 = class28_3.tileTop; l8 <= class28_3.tileBottom; l8++) {
								Tile class30_sub3_22 = tiles[k7][l8];
								if (class30_sub3_22.anInt1325 != 0)
									tileDeque.insertBack(class30_sub3_22);
								else if ((k7 != camera_x || l8 != camera_y) && class30_sub3_22.aBoolean1323)
									tileDeque.insertBack(class30_sub3_22);
							}

						}

					}
					if (tempTile.aBoolean1324)
						continue;
				} catch (Exception _ex) {
					_ex.printStackTrace();
					tempTile.aBoolean1324 = false;
				}
			if (!tempTile.aBoolean1323 || tempTile.anInt1325 != 0)
				continue;
			if (camera_x <= xCamPosTile && camera_x > anInt449) {
				Tile class30_sub3_8 = tiles[camera_x - 1][camera_y];
				if (class30_sub3_8 != null && class30_sub3_8.aBoolean1323)
					continue;
			}
			if (camera_x >= xCamPosTile && camera_x < anInt450 - 1) {
				Tile class30_sub3_9 = tiles[camera_x + 1][camera_y];
				if (class30_sub3_9 != null && class30_sub3_9.aBoolean1323)
					continue;
			}
			if (camera_y <= yCamPosTile && camera_y > anInt451) {
				Tile class30_sub3_10 = tiles[camera_x][camera_y - 1];
				if (class30_sub3_10 != null && class30_sub3_10.aBoolean1323)
					continue;
			}
			if (camera_y >= yCamPosTile && camera_y < anInt452 - 1) {
				Tile class30_sub3_11 = tiles[camera_x][camera_y + 1];
				if (class30_sub3_11 != null && class30_sub3_11.aBoolean1323)
					continue;
			}
			tempTile.aBoolean1323 = false;
			anInt446--;
			GroundItem object4 = tempTile.groundItem;
			if (object4 != null && object4.topItem != 0) {
				if (object4.secondGroundItem != null)
					object4.secondGroundItem.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, object4.xPos - cameraX2, object4.zPos - cameraY2 - object4.topItem, object4.yPos - cameraZ2, object4.uid, object4.newuid, distance);
				if (object4.thirdGroundItem != null)
					object4.thirdGroundItem.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, object4.xPos - cameraX2, object4.zPos - cameraY2 - object4.topItem, object4.yPos - cameraZ2, object4.uid, object4.newuid, distance);
				if (object4.firstGroundItem != null)
					object4.firstGroundItem.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, object4.xPos - cameraX2, object4.zPos - cameraY2 - object4.topItem, object4.yPos - cameraZ2, object4.uid, object4.newuid, distance);
			}
			if (tempTile.anInt1328 != 0) {
				WallDecoration class26 = tempTile.wallDecoration;
				if (class26 != null && !method322(plane, camera_x, camera_y, class26.node.modelHeight))
					if ((class26.configurationBits & tempTile.anInt1328) != 0)
						class26.node.renderAtPoint(class26.rotation, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, class26.xPos - cameraX2, class26.zPos - cameraY2, class26.yPos - cameraZ2, class26.uid, class26.wallDecorUID, distance);
					else if ((class26.configurationBits & 0x300) != 0) {
						int l2 = class26.xPos - cameraX2;
						int j3 = class26.zPos - cameraY2;
						int i4 = class26.yPos - cameraZ2;
						int k5 = class26.rotation;
						int j6;
						if (k5 == 1 || k5 == 2)
							j6 = -l2;
						else
							j6 = l2;
						int l7;
						if (k5 == 2 || k5 == 3)
							l7 = -i4;
						else
							l7 = i4;
						if ((class26.configurationBits & 0x100) != 0 && l7 >= j6) {
							int i9 = l2 + faceXoffset2[k5];
							int i10 = i4 + faceYOffset2[k5];
							class26.node.renderAtPoint(k5 * 512 + 256, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, i9, j3, i10, class26.uid, class26.wallDecorUID, distance);
						}
						if ((class26.configurationBits & 0x200) != 0 && l7 <= j6) {
							int j9 = l2 + faceXOffset3[k5];
							int j10 = i4 + faceYOffset3[k5];
							class26.node.renderAtPoint(k5 * 512 + 1280 & 0x7ff, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, j9, j3, j10, class26.uid, class26.wallDecorUID, distance);
						}
					}
				WallObject class10_2 = tempTile.wallObject;
				if (class10_2 != null) {
					if ((class10_2.orientation1 & tempTile.anInt1328) != 0 && !method321(plane, camera_x, camera_y, class10_2.orientation1))
						class10_2.node2.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, class10_2.xPos - cameraX2, class10_2.zPos - cameraY2, class10_2.yPos - cameraZ2, class10_2.uid, class10_2.wallObjUID, distance);
					if ((class10_2.orientation & tempTile.anInt1328) != 0 && !method321(plane, camera_x, camera_y, class10_2.orientation))
						class10_2.node1.renderAtPoint(0, camUpDownY, camUpDownX, camLeftRightY, camLeftRightX, class10_2.xPos - cameraX2, class10_2.zPos - cameraY2, class10_2.yPos - cameraZ2, class10_2.uid, class10_2.wallObjUID, distance);
				}
			}
			if (k < zMapSize - 1) {
				Tile class30_sub3_12 = tileArray[k + 1][camera_x][camera_y];
				if (class30_sub3_12 != null && class30_sub3_12.aBoolean1323)
					tileDeque.insertBack(class30_sub3_12);
			}
			if (camera_x < xCamPosTile) {
				Tile class30_sub3_13 = tiles[camera_x + 1][camera_y];
				if (class30_sub3_13 != null && class30_sub3_13.aBoolean1323)
					tileDeque.insertBack(class30_sub3_13);
			}
			if (camera_y < yCamPosTile) {
				Tile class30_sub3_14 = tiles[camera_x][camera_y + 1];
				if (class30_sub3_14 != null && class30_sub3_14.aBoolean1323)
					tileDeque.insertBack(class30_sub3_14);
			}
			if (camera_x > xCamPosTile) {
				Tile class30_sub3_15 = tiles[camera_x - 1][camera_y];
				if (class30_sub3_15 != null && class30_sub3_15.aBoolean1323)
					tileDeque.insertBack(class30_sub3_15);
			}
			if (camera_y > yCamPosTile) {
				Tile class30_sub3_16 = tiles[camera_x][camera_y - 1];
				if (class30_sub3_16 != null && class30_sub3_16.aBoolean1323)
					tileDeque.insertBack(class30_sub3_16);
			}
		} while (true);
	}

	public boolean opaque_floor_texture = false;
	private void drawPlainTile(SceneTilePaint class43, int i, int j, int k, int l, int i1, int j1, int k1) {
		final DrawCallbacks drawCallbacks = Client.drawCallbacks;

		if (drawCallbacks != null) {
			drawCallbacks.drawScenePaint(0, j, k, l, i1, -cameraX2, -cameraY2, -cameraZ2, class43, i, j1, k1, RuneLite.getClient().get3dZoom(), Rasterizer.textureInt1, Rasterizer.textureInt2);
		}

		int l1;
		int i2 = l1 = (j1 << 7) - cameraX2;
		int depth_b;
		int depth_a = depth_b = (k1 << 7) - cameraZ2;
		int l2;
		int i3 = l2 = i2 + 128;
		int depth_d;
		int depth_c = depth_d = depth_a + 128;
		int l3 = anIntArrayArrayArray440[i][j1][k1] - cameraY2;
		int i4 = anIntArrayArrayArray440[i][j1 + 1][k1] - cameraY2;
		int j4 = anIntArrayArrayArray440[i][j1 + 1][k1 + 1] - cameraY2;
		int k4 = anIntArrayArrayArray440[i][j1][k1 + 1] - cameraY2;
		int l4 = depth_a * l + i2 * i1 >> 16;
		depth_a = depth_a * i1 - i2 * l >> 16;
		i2 = l4;
		l4 = l3 * k - depth_a * j >> 16;
		depth_a = l3 * j + depth_a * k >> 16;
		l3 = l4;
		if (depth_a < 50) {
			return;
		}
		l4 = depth_b * l + i3 * i1 >> 16;
		depth_b = depth_b * i1 - i3 * l >> 16;
		i3 = l4;
		l4 = i4 * k - depth_b * j >> 16;
		depth_b = i4 * j + depth_b * k >> 16;
		i4 = l4;
		if (depth_b < 50) {
			return;
		}
		l4 = depth_c * l + l2 * i1 >> 16;
		depth_c = depth_c * i1 - l2 * l >> 16;
		l2 = l4;
		l4 = j4 * k - depth_c * j >> 16;
		depth_c = j4 * j + depth_c * k >> 16;
		j4 = l4;
		if (depth_c < 50) {
			return;
		}
		l4 = depth_d * l + l1 * i1 >> 16;
		depth_d = depth_d * i1 - l1 * l >> 16;
		l1 = l4;
		l4 = k4 * k - depth_d * j >> 16;
		depth_d = k4 * j + depth_d * k >> 16;
		k4 = l4;
		if (depth_d < 50) {
			return;
		}
		int x_a = Rasterizer.textureInt1 + (i2 << viewDistance) / depth_a;
		int y_a = Rasterizer.textureInt2 + (l3 << viewDistance) / depth_a;
		int x_b = Rasterizer.textureInt1 + (i3 << viewDistance) / depth_b;
		int y_b = Rasterizer.textureInt2 + (i4 << viewDistance) / depth_b;
		int x_c = Rasterizer.textureInt1 + (l2 << viewDistance) / depth_c;
		int y_c = Rasterizer.textureInt2 + (j4 << viewDistance) / depth_c;
		int x_d = Rasterizer.textureInt1 + (l1 << viewDistance) / depth_d;
		int y_d = Rasterizer.textureInt2 + (k4 << viewDistance) / depth_d;
		Rasterizer.anInt1465 = 0;
		if ((x_c - x_d) * (y_b - y_d) - (y_c - y_d) * (x_b - x_d) > 0) {
			Rasterizer.aBoolean1462 = x_c < 0 || x_d < 0 || x_b < 0 || x_c > DrawingArea.viewportRX || x_d > DrawingArea.viewportRX || x_b > DrawingArea.viewportRX;

			if (aBoolean467 && method318(anInt468, anInt469, y_c, y_d, y_b, x_c, x_d, x_b)) {
				anInt470 = j1;
				anInt471 = k1;
			}
			if (requestTileTrace && method318(requestedTraceX, requestedTraceY, y_c, y_d, y_b, x_c, x_d, x_b)) {
				tracedTileX = j1;
				tracedTileY = k1;
			}

			if (!Client.instance.isMenuOpen() && method318(Client.instance.mouseX, Client.instance.mouseY, y_c, y_d, y_b, x_c, x_d, x_b)) {
				setTargetTile(j1, k1);
			}

			if (class43.texture == -1 || class43.texture > 50) {
				if (class43.neColor != 0xbc614e) {
					if (Configuration.enableHDTextures && class43.texture != -1) {
						if (class43.flat) {
							Rasterizer.drawMaterializedTriangle(y_c, y_d, y_b, x_c, x_d, x_b, class43.neColor, class43.nwColor, class43.seColor, i2, i3, l1, l3, i4, k4, depth_a, depth_b, depth_d, class43.texture, depth_c, depth_d, depth_b);
						} else {
							Rasterizer.drawMaterializedTriangle(y_c, y_d, y_b, x_c, x_d, x_b, class43.neColor, class43.nwColor, class43.seColor, l2, l1, i3, j4, k4, i4, depth_c, depth_d, depth_b, class43.texture, depth_c, depth_d, depth_b);
						}
					} else {
						Rasterizer.drawGouraudTriangle(y_c, y_d, y_b, x_c, x_d, x_b, class43.neColor, class43.nwColor, class43.seColor, depth_c, depth_d, depth_b);
					}
				}
			} else if (!lowMem) {
				if (class43.flat) {
					Rasterizer.drawTexturedTriangle(y_c, y_d, y_b, x_c, x_d, x_b, class43.neColor, class43.nwColor, class43.seColor, i2, i3, l1, l3, i4, k4, depth_a, depth_b, depth_d, class43.texture, depth_c, depth_d, depth_b);
				} else {
					Rasterizer.drawTexturedTriangle(y_c, y_d, y_b, x_c, x_d, x_b, class43.neColor, class43.nwColor, class43.seColor, l2, l1, i3, j4, k4, i4, depth_c, depth_d, depth_b, class43.texture, depth_c, depth_d, depth_b);
				}
			} else {
				int i7 = anIntArray485[class43.texture];
				Rasterizer.drawGouraudTriangle(y_c, y_d, y_b, x_c, x_d, x_b, method317(i7, class43.neColor), method317(i7, class43.nwColor), method317(i7, class43.seColor), depth_c, depth_d, depth_b);
			}
		}
		if ((x_a - x_b) * (y_d - y_b) - (y_a - y_b) * (x_d - x_b) > 0) {
			Rasterizer.aBoolean1462 = x_a < 0 || x_b < 0 || x_d < 0 || x_a > DrawingArea.viewportRX || x_b > DrawingArea.viewportRX || x_d > DrawingArea.viewportRX;

			if (aBoolean467 && method318(anInt468, anInt469, y_a, y_b, y_d, x_a, x_b, x_d)) {
				anInt470 = j1;
				anInt471 = k1;
			}
			if (requestTileTrace && method318(requestedTraceX, requestedTraceY, y_a, y_b, y_d, x_a, x_b, x_d)) {
				tracedTileX = j1;
				tracedTileY = k1;
			}

			if (!Client.instance.isMenuOpen() && method318(Client.instance.mouseX, Client.instance.mouseY, y_a, y_b, y_d, x_a, x_b, x_d)) {
				setTargetTile(j1, k1);
			}

			if (class43.texture == -1 || class43.texture > 50) {
				if (class43.swColor != 0xbc614e) {
					if (Configuration.enableHDTextures && class43.texture != -1) {
						Rasterizer.drawMaterializedTriangle(y_a, y_b, y_d, x_a, x_b, x_d, class43.swColor, class43.seColor, class43.nwColor, i2, i3, l1, l3, i4, k4, depth_a, depth_b, depth_d, class43.texture, depth_a, depth_b, depth_d);
					} else {
						Rasterizer.drawGouraudTriangle(y_a, y_b, y_d, x_a, x_b, x_d, class43.swColor, class43.seColor, class43.nwColor, depth_a, depth_b, depth_d);
					}
				}
			} else {
				if(!lowMem) {
					Rasterizer.drawTexturedTriangle(y_a, y_b, y_d, x_a, x_b, x_d, class43.swColor, class43.seColor, class43.nwColor, i2, i3, l1, l3, i4, k4, depth_a, depth_b, depth_d, class43.texture, depth_a, depth_b, depth_d);
					return;
				}
				int j7 = anIntArray485[class43.texture];
				Rasterizer.drawGouraudTriangle(y_a, y_b, y_d, x_a, x_b, x_d, method317(j7, class43.swColor), method317(j7, class43.seColor), method317(j7, class43.nwColor), depth_a, depth_b, depth_d);
			}
		}
	}

	private void drawTileMarker(int plane, int sin_y, int cos_y, int sin_x, int cos_x, int camera_x, int camera_y, int color) {
		int l1;
		int i2 = l1 = (camera_x << 7) - cameraX2;
		int depth_b;
		int depth_a = depth_b = (camera_y << 7) - cameraZ2;
		int l2;
		int i3 = l2 = i2 + 128;
		int depth_d;
		int depth_c = depth_d = depth_a + 128;
		int l3 = anIntArrayArrayArray440[plane][camera_x][camera_y] - cameraY2;
		int i4 = anIntArrayArrayArray440[plane][camera_x + 1][camera_y] - cameraY2;
		int j4 = anIntArrayArrayArray440[plane][camera_x + 1][camera_y + 1] - cameraY2;
		int k4 = anIntArrayArrayArray440[plane][camera_x][camera_y + 1] - cameraY2;
		int l4 = depth_a * sin_x + i2 * cos_x >> 16;
		depth_a = depth_a * cos_x - i2 * sin_x >> 16;
		i2 = l4;
		l4 = l3 * cos_y - depth_a * sin_y >> 16;
		depth_a = l3 * sin_y + depth_a * cos_y >> 16;
		l3 = l4;


		l4 = depth_b * sin_x + i3 * cos_x >> 16;
		depth_b = depth_b * cos_x - i3 * sin_x >> 16;
		i3 = l4;
		l4 = i4 * cos_y - depth_b * sin_y >> 16;
		depth_b = i4 * sin_y + depth_b * cos_y >> 16;
		i4 = l4;


		l4 = depth_c * sin_x + l2 * cos_x >> 16;
		depth_c = depth_c * cos_x - l2 * sin_x >> 16;
		l2 = l4;
		l4 = j4 * cos_y - depth_c * sin_y >> 16;
		depth_c = j4 * sin_y + depth_c * cos_y >> 16;
		j4 = l4;


		l4 = depth_d * sin_x + l1 * cos_x >> 16;
		depth_d = depth_d * cos_x - l1 * sin_x >> 16;
		l1 = l4;
		l4 = k4 * cos_y - depth_d * sin_y >> 16;
		depth_d = k4 * sin_y + depth_d * cos_y >> 16;
		k4 = l4;


		int x_a = Rasterizer.textureInt1 + (i2 << viewDistance) / depth_a;
		int y_a = Rasterizer.textureInt2 + (l3 << viewDistance) / depth_a;
		int x_b = Rasterizer.textureInt1 + (i3 << viewDistance) / depth_b;
		int y_b = Rasterizer.textureInt2 + (i4 << viewDistance) / depth_b;
		int x_c = Rasterizer.textureInt1 + (l2 << viewDistance) / depth_c;
		int y_c = Rasterizer.textureInt2 + (j4 << viewDistance) / depth_c;
		int x_d = Rasterizer.textureInt1 + (l1 << viewDistance) / depth_d;
		int y_d = Rasterizer.textureInt2 + (k4 << viewDistance) / depth_d;

		Graphics2D g2d = DrawingArea.createGraphics(true);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setColor(new Color(color));

		g2d.drawLine(x_c, y_c, x_d, y_d);
		g2d.drawLine(x_c, y_c - 1, x_d, y_d - 1);

		g2d.drawLine(x_b, y_b, x_c, y_c);
		g2d.drawLine(x_b, y_b, x_c, y_c - 1);

		g2d.drawLine(x_b, y_b, x_a, y_a);
		g2d.drawLine(x_b, y_b - 1, x_a, y_a - 1);

		g2d.drawLine(x_d, y_d, x_a, y_a);
		g2d.drawLine(x_d, y_d - 1, x_a, y_a - 1);
	}

	private void drawShapedTile(int x, int pitchSin, int yawSin, SceneTileModel tile, int pitchCos, int y, int yawCos) {

		final DrawCallbacks drawCallbacks = Client.drawCallbacks;

		if (drawCallbacks != null) {
			final int cameraX2 = WorldController.cameraX2;
			final int cameraY2 = WorldController.cameraY2;
			final int cameraZ2 = WorldController.cameraZ2;
			final int zoom = RuneLite.getClient().get3dZoom();
			final int centerX = RuneLite.getClient().getCenterX();
			final int centerY = RuneLite.getClient().getCenterY();
			drawCallbacks.drawSceneModel(0, pitchSin, pitchCos, yawSin, yawCos, -cameraX2, -cameraY2, -cameraZ2, tile, RuneLite.getClient().getPlane(), x, y, zoom, centerX, centerY);
		}

		int vertices = tile.vertexX.length;
		for (int l1 = 0; l1 < vertices; l1++) {
			int i2 = tile.vertexX[l1] - cameraX2;
			int k2 = tile.vertexY[l1] - cameraY2;
			int i3 = tile.vertexZ[l1] - cameraZ2;
			int k3 = i3 * yawSin + i2 * yawCos >> 16;
			i3 = i3 * yawCos - i2 * yawSin >> 16;
			i2 = k3;
			k3 = k2 * pitchCos
					- i3 * pitchSin >> 16;
			i3 = k2 * pitchSin + i3 * pitchCos
					>> 16;
			k2 = k3;
			if (i3 < 50) {
				return;
			}
			if (Configuration.enableHDTextures || tile.triangleTextureId != null) {
				SceneTileModel.anIntArray690[l1] = i2;
				SceneTileModel.anIntArray691[l1] = k2;
				SceneTileModel.anIntArray692[l1] = i3;
			}
			SceneTileModel.anIntArray688[l1] = Rasterizer.textureInt1 + (i2 << viewDistance) / i3;
			SceneTileModel.anIntArray689[l1] = Rasterizer.textureInt2 + (k2 << viewDistance) / i3;
			SceneTileModel.screenZ[l1] = i3;
		}

		Rasterizer.anInt1465 = 0;
		vertices = tile.faceX.length;
		for (int face = 0; face < vertices; face++) {
			int tri_a = tile.faceX[face];
			int tri_b = tile.faceY[face];
			int tri_c = tile.faceZ[face];
			int x_a = SceneTileModel.anIntArray688[tri_a];
			int x_b = SceneTileModel.anIntArray688[tri_b];
			int x_c = SceneTileModel.anIntArray688[tri_c];
			int y_a = SceneTileModel.anIntArray689[tri_a];
			int y_b = SceneTileModel.anIntArray689[tri_b];
			int y_c = SceneTileModel.anIntArray689[tri_c];
			if ((x_a - x_b) * (y_c - y_b) - (y_a - y_b) * (x_c - x_b) > 0) {
				Rasterizer.aBoolean1462 = x_a < 0 || x_b < 0 || x_c < 0 || x_a > DrawingArea.viewportRX || x_b > DrawingArea.viewportRX || x_c > DrawingArea.viewportRX;

				if (aBoolean467 && method318(anInt468, anInt469, y_a, y_b, y_c, x_a, x_b, x_c)) {
					anInt470 = x;
					anInt471 = y;
				}
				if (requestTileTrace && method318(requestedTraceX, requestedTraceY, y_a, y_b, y_c, x_a, x_b, x_c)) {
					tracedTileX = x;
					tracedTileY = y;
				}

				if (!Client.instance.isMenuOpen() && method318(Client.instance.mouseX, Client.instance.mouseY, y_a, y_b, y_c, x_a, x_b, x_c)) {
					setTargetTile(y, y);
				}

				if (tile.triangleTextureId == null || tile.triangleTextureId[face] == -1 || tile.triangleTextureId[face] > 50) {
					if (tile.triangleColorA[face] != 0xbc614e) {
						if (Configuration.enableHDTextures && tile.triangleTextureId != null && tile.triangleTextureId[face] != -1) {
							if (tile.flat || tile.triangleTextureId[face] == 505) {
								Rasterizer.drawMaterializedTriangle(y_a, y_b, y_c, x_a, x_b, x_c, tile.triangleColorA[face], tile.triangleColorB[face], tile.triangleColorC[face], SceneTileModel.anIntArray690[0], SceneTileModel.anIntArray690[1], SceneTileModel.anIntArray690[3], SceneTileModel.anIntArray691[0], SceneTileModel.anIntArray691[1], SceneTileModel.anIntArray691[3], SceneTileModel.anIntArray692[0], SceneTileModel.anIntArray692[1], SceneTileModel.anIntArray692[3], tile.triangleTextureId[face], SceneTileModel.screenZ[tri_a], SceneTileModel.screenZ[tri_b], SceneTileModel.screenZ[tri_c]);
							} else {
								Rasterizer.drawMaterializedTriangle(y_a, y_b, y_c, x_a, x_b, x_c, tile.triangleColorA[face], tile.triangleColorB[face], tile.triangleColorC[face], SceneTileModel.anIntArray690[tri_a], SceneTileModel.anIntArray690[tri_b], SceneTileModel.anIntArray690[tri_c], SceneTileModel.anIntArray691[tri_a], SceneTileModel.anIntArray691[tri_b], SceneTileModel.anIntArray691[tri_c], SceneTileModel.anIntArray692[tri_a], SceneTileModel.anIntArray692[tri_b], SceneTileModel.anIntArray692[tri_c], tile.triangleTextureId[face], SceneTileModel.screenZ[tri_a], SceneTileModel.screenZ[tri_b], SceneTileModel.screenZ[tri_c]);
							}
						} else {
							Rasterizer.drawGouraudTriangle(y_a, y_b, y_c, x_a, x_b, x_c, tile.triangleColorA[face], tile.triangleColorB[face], tile.triangleColorC[face], SceneTileModel.screenZ[tri_a], SceneTileModel.screenZ[tri_b], SceneTileModel.screenZ[tri_c]);
						}
					}
				} else if (!lowMem) {
					if (tile.flat) {
						Rasterizer.drawTexturedTriangle(y_a, y_b, y_c, x_a, x_b, x_c, tile.triangleColorA[face], tile.triangleColorB[face], tile.triangleColorC[face], SceneTileModel.anIntArray690[0], SceneTileModel.anIntArray690[1], SceneTileModel.anIntArray690[3], SceneTileModel.anIntArray691[0], SceneTileModel.anIntArray691[1], SceneTileModel.anIntArray691[3], SceneTileModel.anIntArray692[0], SceneTileModel.anIntArray692[1], SceneTileModel.anIntArray692[3], tile.triangleTextureId[face], SceneTileModel.screenZ[tri_a], SceneTileModel.screenZ[tri_b], SceneTileModel.screenZ[tri_c]);
					} else {
						Rasterizer.drawTexturedTriangle(y_a, y_b, y_c, x_a, x_b, x_c, tile.triangleColorA[face], tile.triangleColorB[face], tile.triangleColorC[face], SceneTileModel.anIntArray690[tri_a], SceneTileModel.anIntArray690[tri_b], SceneTileModel.anIntArray690[tri_c], SceneTileModel.anIntArray691[tri_a], SceneTileModel.anIntArray691[tri_b], SceneTileModel.anIntArray691[tri_c], SceneTileModel.anIntArray692[tri_a], SceneTileModel.anIntArray692[tri_b], SceneTileModel.anIntArray692[tri_c], tile.triangleTextureId[face], SceneTileModel.screenZ[tri_a], SceneTileModel.screenZ[tri_b], SceneTileModel.screenZ[tri_c]);
					}
				} else {
					int k5 = anIntArray485[tile.triangleTextureId[face]];
					Rasterizer.drawGouraudTriangle(y_a, y_b, y_c, x_a, x_b, x_c, method317(k5, tile.triangleColorA[face]), method317(k5, tile.triangleColorB[face]), method317(k5, tile.triangleColorC[face]), SceneTileModel.screenZ[tri_a], SceneTileModel.screenZ[tri_b], SceneTileModel.screenZ[tri_c]);
				}
			}
		}
	}

	private int method317(int colour1, int colour2) {
		colour2 = 127 - colour2;
		colour2 = (colour2 * (colour1 & 0x7f)) / 160;
		if (colour2 < 2)
			colour2 = 2;
		else if (colour2 > 126)
			colour2 = 126;
		return (colour1 & 0xff80) + colour2;
	}

	private boolean method318(int mouseX, int mouseY, int triangleYA, int triangleYB, int triangleYC, int triangleXA, int triangleXB, int triangleXC) {
		if (mouseY < triangleYA && mouseY < triangleYB && mouseY < triangleYC)
			return false;
		if (mouseY > triangleYA && mouseY > triangleYB && mouseY > triangleYC)
			return false;
		if (mouseX < triangleXA && mouseX < triangleXB && mouseX < triangleXC)
			return false;
		if (mouseX > triangleXA && mouseX > triangleXB && mouseX > triangleXC)
			return false;
		int i2 = (mouseY - triangleYA) * (triangleXB - triangleXA) - (mouseX - triangleXA) * (triangleYB - triangleYA);
		int j2 = (mouseY - triangleYC) * (triangleXA - triangleXC) - (mouseX - triangleXC) * (triangleYA - triangleYC);
		int k2 = (mouseY - triangleYB) * (triangleXC - triangleXB) - (mouseX - triangleXB) * (triangleYC - triangleYB);
		return i2 * k2 > 0 && k2 * j2 > 0;
	}

	private void processCulling() {
		int count = cullingClusterPointer[plane__];
		CullingCluster clusters[] = cullingClusters[plane__];
		processedClusterPtr = 0;
		for (int k = 0; k < count; k++) {
			CullingCluster cluster = clusters[k];
			if (cluster.searchMask == 1) {
				int xDistFromCamStart = (cluster.tileStartX - xCamPosTile) + getDrawDistance();
				if (xDistFromCamStart < 0 || xDistFromCamStart > 50)
					continue;
				int yDistFromCamStart = (cluster.tileStartY - yCamPosTile) + getDrawDistance();
				if (yDistFromCamStart < 0)
					yDistFromCamStart = 0;
				int yDistFromCamEnd = (cluster.tileEndY - yCamPosTile) + getDrawDistance();
				if (yDistFromCamEnd > 50)
					yDistFromCamEnd = 50;
				boolean visisble = false;
				while (yDistFromCamStart <= yDistFromCamEnd)
					if (tile_visibility_map[xDistFromCamStart][yDistFromCamStart++]) {
						visisble = true;
						break;
					}
				if (!visisble)
					continue;
				int xDistFromCamStartReal = cameraX2 - cluster.worldStartX;
				if (xDistFromCamStartReal > 32) {
					cluster.tileDistanceEnum = 1;
				} else {
					if (xDistFromCamStartReal >= -32)
						continue;
					cluster.tileDistanceEnum = 2;
					xDistFromCamStartReal = -xDistFromCamStartReal;
				}
				cluster.worldDistanceFromCameraStartY = (cluster.worldStartY - cameraZ2 << 8) / xDistFromCamStartReal;
				cluster.worldDistanceFromCameraEndY = (cluster.worldEndY - cameraZ2 << 8) / xDistFromCamStartReal;
				cluster.worldDistanceFromCameraStartZ = (cluster.worldStartZ - cameraY2 << 8) / xDistFromCamStartReal;
				cluster.worldDistanceFromCameraEndZ = (cluster.worldEndZ - cameraY2 << 8) / xDistFromCamStartReal;
				processedClusters[processedClusterPtr++] = cluster;
				continue;
			}
			if (cluster.searchMask == 2) {
				int yDIstFromCamStart = (cluster.tileStartY - yCamPosTile) + getDrawDistance();
				if (yDIstFromCamStart < 0 || yDIstFromCamStart > 50)
					continue;
				int xDistFromCamStart = (cluster.tileStartX - xCamPosTile) + getDrawDistance();
				if (xDistFromCamStart < 0)
					xDistFromCamStart = 0;
				int xDistFromCamEnd = (cluster.tileEndX - xCamPosTile) + getDrawDistance();
				if (xDistFromCamEnd > 50)
					xDistFromCamEnd = 50;
				boolean visible = false;
				while (xDistFromCamStart <= xDistFromCamEnd)
					if (tile_visibility_map[xDistFromCamStart++][yDIstFromCamStart]) {
						visible = true;
						break;
					}
				if (!visible)
					continue;
				int yDistFromCamStartReal = cameraZ2 - cluster.worldStartY;
				if (yDistFromCamStartReal > 32) {
					cluster.tileDistanceEnum = 3;
				} else {
					if (yDistFromCamStartReal >= -32)
						continue;
					cluster.tileDistanceEnum = 4;
					yDistFromCamStartReal = -yDistFromCamStartReal;
				}
				cluster.worldDistanceFromCameraStartX = (cluster.worldStartX - cameraX2 << 8) / yDistFromCamStartReal;
				cluster.worldDistanceFromCameraEndX = (cluster.worldEndX - cameraX2 << 8) / yDistFromCamStartReal;
				cluster.worldDistanceFromCameraStartZ = (cluster.worldStartZ - cameraY2 << 8) / yDistFromCamStartReal;
				cluster.worldDistanceFromCameraEndZ = (cluster.worldEndZ - cameraY2 << 8) / yDistFromCamStartReal;
				processedClusters[processedClusterPtr++] = cluster;
			} else if (cluster.searchMask == 4) {
				int yDistFromCamStartReal = cluster.worldStartZ - cameraY2;
				if (yDistFromCamStartReal > 128) {
					int yDistFromCamStart = (cluster.tileStartY - yCamPosTile) + getDrawDistance();
					if (yDistFromCamStart < 0)
						yDistFromCamStart = 0;
					int yDistFromCamEnd = (cluster.tileEndY - yCamPosTile) + getDrawDistance();
					if (yDistFromCamEnd > 50)
						yDistFromCamEnd = 50;
					if (yDistFromCamStart <= yDistFromCamEnd) {
						int xDistFromCamStart = (cluster.tileStartX - xCamPosTile) + getDrawDistance();
						if (xDistFromCamStart < 0)
							xDistFromCamStart = 0;
						int xDistFromCamEnd = (cluster.tileEndX - xCamPosTile) + getDrawDistance();
						if (xDistFromCamEnd > 50)
							xDistFromCamEnd = 50;
						boolean visisble = false;
						label0: for (int _x = xDistFromCamStart; _x <= xDistFromCamEnd; _x++) {
							for (int _y = yDistFromCamStart; _y <= yDistFromCamEnd; _y++) {
								if (!tile_visibility_map[_x][_y])
									continue;
								visisble = true;
								break label0;
							}

						}

						if (visisble) {
							cluster.tileDistanceEnum = 5;
							cluster.worldDistanceFromCameraStartX = (cluster.worldStartX - cameraX2 << 8) / yDistFromCamStartReal;
							cluster.worldDistanceFromCameraEndX = (cluster.worldEndX - cameraX2 << 8) / yDistFromCamStartReal;
							cluster.worldDistanceFromCameraStartY = (cluster.worldStartY - cameraZ2 << 8) / yDistFromCamStartReal;
							cluster.worldDistanceFromCameraEndY = (cluster.worldEndY - cameraZ2 << 8) / yDistFromCamStartReal;
							processedClusters[processedClusterPtr++] = cluster;
						}
					}
				}
			}
		}

	}

	private boolean method320(int y, int x, int z) {
		int l = anIntArrayArrayArray445[y][x][z];
		if (l == -anInt448)
			return false;
		if (l == anInt448)
			return true;
		int i1 = x << 7;
		int j1 = z << 7;
		if (method324(i1 + 1, anIntArrayArrayArray440[y][x][z], j1 + 1) && method324((i1 + 128) - 1, anIntArrayArrayArray440[y][x + 1][z], j1 + 1) && method324((i1 + 128) - 1, anIntArrayArrayArray440[y][x + 1][z + 1], (j1 + 128) - 1) && method324(i1 + 1, anIntArrayArrayArray440[y][x][z + 1], (j1 + 128) - 1)) {
			anIntArrayArrayArray445[y][x][z] = anInt448;
			return true;
		} else {
			anIntArrayArrayArray445[y][x][z] = -anInt448;
			return false;
		}
	}

	private boolean method321(int z, int x, int y, int l) {
		if (!method320(z, x, y))
			return false;
		int i1 = x << 7;
		int j1 = y << 7;
		int k1 = anIntArrayArrayArray440[z][x][y] - 1;
		int l1 = k1 - 120;
		int i2 = k1 - 230;
		int j2 = k1 - 238;
		if (l < 16) {
			if (l == 1) {
				if (i1 > cameraX2) {
					if (!method324(i1, k1, j1))
						return false;
					if (!method324(i1, k1, j1 + 128))
						return false;
				}
				if (z > 0) {
					if (!method324(i1, l1, j1))
						return false;
					if (!method324(i1, l1, j1 + 128))
						return false;
				}
				return method324(i1, i2, j1) && method324(i1, i2, j1 + 128);
			}
			if (l == 2) {
				if (j1 < cameraZ2) {
					if (!method324(i1, k1, j1 + 128))
						return false;
					if (!method324(i1 + 128, k1, j1 + 128))
						return false;
				}
				if (z > 0) {
					if (!method324(i1, l1, j1 + 128))
						return false;
					if (!method324(i1 + 128, l1, j1 + 128))
						return false;
				}
				return method324(i1, i2, j1 + 128) && method324(i1 + 128, i2, j1 + 128);
			}
			if (l == 4) {
				if (i1 < cameraX2) {
					if (!method324(i1 + 128, k1, j1))
						return false;
					if (!method324(i1 + 128, k1, j1 + 128))
						return false;
				}
				if (z > 0) {
					if (!method324(i1 + 128, l1, j1))
						return false;
					if (!method324(i1 + 128, l1, j1 + 128))
						return false;
				}
				return method324(i1 + 128, i2, j1) && method324(i1 + 128, i2, j1 + 128);
			}
			if (l == 8) {
				if (j1 > cameraZ2) {
					if (!method324(i1, k1, j1))
						return false;
					if (!method324(i1 + 128, k1, j1))
						return false;
				}
				if (z > 0) {
					if (!method324(i1, l1, j1))
						return false;
					if (!method324(i1 + 128, l1, j1))
						return false;
				}
				return method324(i1, i2, j1) && method324(i1 + 128, i2, j1);
			}
		}
		if (!method324(i1 + 64, j2, j1 + 64))
			return false;
		if (l == 16)
			return method324(i1, i2, j1 + 128);
		if (l == 32)
			return method324(i1 + 128, i2, j1 + 128);
		if (l == 64)
			return method324(i1 + 128, i2, j1);
		if (l == 128) {
			return method324(i1, i2, j1);
		} else {
			System.out.println("Warning unsupported wall type");
			return true;
		}
	}

	private boolean method322(int i, int j, int k, int l) {
		if (!method320(i, j, k))
			return false;
		int i1 = j << 7;
		int j1 = k << 7;
		return method324(i1 + 1, anIntArrayArrayArray440[i][j][k] - l, j1 + 1) && method324((i1 + 128) - 1, anIntArrayArrayArray440[i][j + 1][k] - l, j1 + 1) && method324((i1 + 128) - 1, anIntArrayArrayArray440[i][j + 1][k + 1] - l, (j1 + 128) - 1) && method324(i1 + 1, anIntArrayArrayArray440[i][j][k + 1] - l, (j1 + 128) - 1);
	}

	private boolean method323(int y, int x, int k, int z, int i1, int j1) {
		if (x == k && z == i1) {
			if (!method320(y, x, z))
				return false;
			int k1 = x << 7;
			int i2 = z << 7;
			return method324(k1 + 1, anIntArrayArrayArray440[y][x][z] - j1, i2 + 1) && method324((k1 + 128) - 1, anIntArrayArrayArray440[y][x + 1][z] - j1, i2 + 1) && method324((k1 + 128) - 1, anIntArrayArrayArray440[y][x + 1][z + 1] - j1, (i2 + 128) - 1) && method324(k1 + 1, anIntArrayArrayArray440[y][x][z + 1] - j1, (i2 + 128) - 1);
		}
		for (int l1 = x; l1 <= k; l1++) {
			for (int j2 = z; j2 <= i1; j2++)
				if (anIntArrayArrayArray445[y][l1][j2] == -anInt448)
					return false;

		}

		int k2 = (x << 7) + 1;
		int l2 = (z << 7) + 2;
		int i3 = anIntArrayArrayArray440[y][x][z] - j1;
		if (!method324(k2, i3, l2))
			return false;
		int j3 = (k << 7) - 1;
		if (!method324(j3, i3, l2))
			return false;
		int k3 = (i1 << 7) - 1;
		return method324(k2, i3, k3) && method324(j3, i3, k3);
	}

	private boolean method324(int x, int y, int z) {
		for (int l = 0; l < processedClusterPtr; l++) {
			CullingCluster cluster = processedClusters[l];
			if (cluster.tileDistanceEnum == 1) {
				int i1 = cluster.worldStartX - x;
				if (i1 > 0) {
					int j2 = cluster.worldStartY + (cluster.worldDistanceFromCameraStartY * i1 >> 8);
					int k3 = cluster.worldEndY + (cluster.worldDistanceFromCameraEndY * i1 >> 8);
					int l4 = cluster.worldStartZ + (cluster.worldDistanceFromCameraStartZ * i1 >> 8);
					int i6 = cluster.worldEndZ + (cluster.worldDistanceFromCameraEndZ * i1 >> 8);
					if (z >= j2 && z <= k3 && y >= l4 && y <= i6)
						return true;
				}
			} else if (cluster.tileDistanceEnum == 2) {
				int j1 = x - cluster.worldStartX;
				if (j1 > 0) {
					int k2 = cluster.worldStartY + (cluster.worldDistanceFromCameraStartY * j1 >> 8);
					int l3 = cluster.worldEndY + (cluster.worldDistanceFromCameraEndY * j1 >> 8);
					int i5 = cluster.worldStartZ + (cluster.worldDistanceFromCameraStartZ * j1 >> 8);
					int j6 = cluster.worldEndZ + (cluster.worldDistanceFromCameraEndZ * j1 >> 8);
					if (z >= k2 && z <= l3 && y >= i5 && y <= j6)
						return true;
				}
			} else if (cluster.tileDistanceEnum == 3) {
				int k1 = cluster.worldStartY - z;
				if (k1 > 0) {
					int l2 = cluster.worldStartX + (cluster.worldDistanceFromCameraStartX * k1 >> 8);
					int i4 = cluster.worldEndX + (cluster.worldDistanceFromCameraEndX * k1 >> 8);
					int j5 = cluster.worldStartZ + (cluster.worldDistanceFromCameraStartZ * k1 >> 8);
					int k6 = cluster.worldEndZ + (cluster.worldDistanceFromCameraEndZ * k1 >> 8);
					if (x >= l2 && x <= i4 && y >= j5 && y <= k6)
						return true;
				}
			} else if (cluster.tileDistanceEnum == 4) {
				int l1 = z - cluster.worldStartY;
				if (l1 > 0) {
					int i3 = cluster.worldStartX + (cluster.worldDistanceFromCameraStartX * l1 >> 8);
					int j4 = cluster.worldEndX + (cluster.worldDistanceFromCameraEndX * l1 >> 8);
					int k5 = cluster.worldStartZ + (cluster.worldDistanceFromCameraStartZ * l1 >> 8);
					int l6 = cluster.worldEndZ + (cluster.worldDistanceFromCameraEndZ * l1 >> 8);
					if (x >= i3 && x <= j4 && y >= k5 && y <= l6)
						return true;
				}
			} else if (cluster.tileDistanceEnum == 5) {
				int i2 = y - cluster.worldStartZ;
				if (i2 > 0) {
					int j3 = cluster.worldStartX + (cluster.worldDistanceFromCameraStartX * i2 >> 8);
					int k4 = cluster.worldEndX + (cluster.worldDistanceFromCameraEndX * i2 >> 8);
					int l5 = cluster.worldStartY + (cluster.worldDistanceFromCameraStartY * i2 >> 8);
					int i7 = cluster.worldEndY + (cluster.worldDistanceFromCameraEndY * i2 >> 8);
					if (x >= j3 && x <= k4 && z >= l5 && z <= i7)
						return true;
				}
			}
		}

		return false;
	}

	public static boolean lowMem = true;
	private final int zMapSize;
	private final int xMapSize;
	private final int yMapSize;
	private final int[][][] anIntArrayArrayArray440;
	private final Tile[][][] tileArray;
	private int currentHL;
	private int amountOfInteractableObjects;
	private final InteractableObject[] interactableObjectCache;
	private final int[][][] anIntArrayArrayArray445;
	private static int anInt446;
	private static int plane__;
	private static int anInt448;
	private static int anInt449;
	private static int anInt450;
	private static int anInt451;
	private static int anInt452;
	private static int xCamPosTile;
	private static int yCamPosTile;
	public static int cameraX2;
	public static int cameraY2;
	public static int cameraZ2;
	private static int camUpDownY;
	private static int camUpDownX;
	private static int camLeftRightY;
	private static int camLeftRightX;
	private static InteractableObject[] aClass28Array462 = new InteractableObject[100];
	private static final int[] faceXoffset2 = { 53, -53, -53, 53 };
	private static final int[] faceYOffset2 = { -53, -53, 53, 53 };
	private static final int[] faceXOffset3 = { -45, 45, 45, -45 };
	private static final int[] faceYOffset3 = { 45, 45, -45, -45 };
	private static boolean aBoolean467;
	private static boolean requestTileTrace;
	private static int requestedTraceX;
	private static int requestedTraceY;
	private static int anInt468;
	private static int anInt469;
	public static int tracedTileX;
	public static int tracedTileY;
	public static int anInt470 = -1;
	public static int anInt471 = -1;
	private static final int amountOfCullingClusters;
	private static int[] cullingClusterPointer;
	private static CullingCluster[][] cullingClusters;
	private static int processedClusterPtr;
	private static final CullingCluster[] processedClusters = new CullingCluster[500];
	private static Deque tileDeque = new Deque();
	private static final int[] anIntArray478 = { 19, 55, 38, 155, 255, 110, 137, 205, 76 };
	private static final int[] anIntArray479 = { 160, 192, 80, 96, 0, 144, 80, 48, 160 };
	private static final int[] anIntArray480 = { 76, 8, 137, 4, 0, 1, 38, 2, 19 };
	private static final int[] anIntArray481 = { 0, 0, 2, 0, 0, 2, 1, 1, 0 };
	private static final int[] anIntArray482 = { 2, 0, 0, 2, 0, 0, 0, 4, 4 };
	private static final int[] anIntArray483 = { 0, 4, 4, 8, 0, 0, 8, 0, 0 };
	private static final int[] anIntArray484 = { 1, 1, 0, 0, 0, 8, 0, 0, 8 };
	private static final int[] anIntArray485 = { 41, 39248, 41, 4643, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 43086, 41, 41, 41, 41, 41, 41, 41, 8602, 41, 28992, 41, 41, 41, 41, 41, 5056, 41, 41, 41, 7079, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 3131, 41, 41, 41 };
	private final int[] anIntArray486;
	private final int[] anIntArray487;
	private int anInt488;
	private final int[][] tileShapePoints = { new int[16], { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 } };
	private final int[][] tileShapeIndices = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };
	private static boolean[][][][] tile_visibility_maps = new boolean[8][32][(300 * 2) + 1][(300 * 2) + 1];
	private static boolean[][] tile_visibility_map;
	private static int midX;
	private static int midY;
	private static int left;
	private static int top;
	private static int right;
	private static int bottom;
	public static int viewDistance = 9;
	public static int distance = 25;

	public static int getDrawDistance() {
		if(Client.drawCallbacks != null && Client.loggedIn) {
			return distance;
		}
		return TILE_DRAW_DISTANCE;
	}
	public Tile getSelectedSceneTile() {
		int tileX = selectedSceneTileX;
		int tileY = selectedSceneTileY;

		if (tileX == -1 || tileY == -1) {
			return null;
		}

		return getTile(Client.instance.plane, tileX, tileY);
	}

	private static final int MAX_TARGET_DISTANCE = 45;
	private static int selectedSceneTileX;
	private static int selectedSceneTileY;
	private static boolean checkClick;

	static void setTargetTile(int targetX, int targetY) {
		final LocalPoint current = Client.instance.myPlayer.getLocalLocation();

		// Limit walk distance - https://math.stackexchange.com/a/85582
		final int a = current.getSceneX();
		final int b = current.getSceneY();
		final int c = targetX;
		final int d = targetY;

		final int r = MAX_TARGET_DISTANCE;
		final int t = (int) Math.hypot(a - c, b - d) - r;
		int x = targetX;
		int y = targetY;

		if (t > 0) {
			x = (r * c + t * a) / (r + t);
			y = (r * d + t * b) / (r + t);
		}

		selectedSceneTileX = x;
		selectedSceneTileY = y;
	}

	static {
		amountOfCullingClusters = 4;
		cullingClusterPointer = new int[amountOfCullingClusters];
		cullingClusters = new CullingCluster[amountOfCullingClusters][500];
	}

	public Tile[][][] getTiles() {
		return tileArray;
	}

	public void setDrawDistance(int drawDistance) {
		this.distance = drawDistance;
	}

	private byte[][][] overlayIds = new byte[4][104][104];
	private byte[][][] underlayIds = new byte[4][104][104];

	public byte[][][] getOverlayIds() {
		return Client.instance.objectManager.overLay;
	}

	public byte[][][] getUnderlayIds() {
		return Client.instance.objectManager.underLay;
	}

	public void setOverlayIds(byte[][][] overlayIds) {
		this.overlayIds = overlayIds;
	}

	public void setUnderlayIds(byte[][][] underlayIds) {
		this.underlayIds = underlayIds;
	}
}
