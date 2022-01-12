package net.runelite.client;

import java.awt.Canvas;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.simplicity.client.*;
import com.simplicity.client.Model;
import com.simplicity.client.NPC;
import com.simplicity.client.Player;
import com.simplicity.client.Tile;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.client.container.item.ItemContainer;

import ch.qos.logback.classic.Logger;
import net.runelite.api.*;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.hooks.Callbacks;
import net.runelite.api.hooks.DrawCallbacks;
import net.runelite.api.vars.AccountType;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.callback.Hooks;

@SuppressWarnings({ "static-access", "unused" })
public class ClientRuneLite implements Client {
	
	public static com.simplicity.client.Client client() {
		return com.simplicity.client.Client.instance;
	}
	
	@Override
	public boolean isClientThread() {
		return true;
	}
	
	@Override
	public Thread getClientThread() {
		return Thread.currentThread();
	}
	
	@Override
	public void setWidgetSpriteOverrides(Map<Integer, SpritePixels> overrides) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setVarpValue(int[] varps, int varpId, int value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setVarbitValue(int[] varps, int varbit, int value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setVar(VarClientStr varClientStr, String value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setUsername(String name) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setStringStackSize(int stackSize) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setStretchedKeepAspectRatio(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setStretchedIntegerScaling(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setStretchedFast(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setStretchedEnabled(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setSpriteOverrides(Map<Integer, SpritePixels> overrides) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setSetting(Varbits varbit, int value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setProjectilesHidden(boolean state) {
		client().projectilesHidden = state;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setPlayersHidden2D(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setPlayersHidden(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setOculusOrbState(int state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setOculusOrbNormalSpeed(int speed) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setNPCsHidden2D(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setNPCsHidden(boolean state) {
		client().npcsHidden = state;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setModIcons(IndexedSprite[] modIcons) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setMenuEntries(MenuEntry[] entries) {
		int menuActionRow = 0;
		for (int i = 0; i < client().menuActionRow; i++) {
			client().menuActionID[i] = entries[i].getType();
			client().menuActionName[i] = entries[i].getOption();
			client().menuActionCmd1[i] = entries[i].getIdentifier();
			client().menuActionCmd2[i] = entries[i].getParam0();
			client().menuActionCmd3[i] = entries[i].getParam1();
			client().menuActionTarget[i] = entries[i].getTarget();
			menuActionRow++;
		}
		if(isMenuOpen())
			client().menuActionRow = menuActionRow;
	}
	
	@Override
	public void addMenuEntry(MenuEntry entry) {
		client().addMenuEntry(entry.getOption(), entry.getTarget(), entry.getType(), entry.getIdentifier(), entry.getParam0(), entry.getParam1(), entry.isDeprioritize());
	}
	

	
	@Override
	public void setLocalPlayerHidden(boolean state) {
		// TODO Auto-generated method stub
		client().localPlayerHidden = state;
	}

	@Override
	public void setPetsHidden(boolean state) {
		client().petsHidden = state;

	}

	@Override
	public void setIgnoresHidden(boolean state) {
		client().ignoredHidden = state;

	}

	@Override
	public void setOthersHidden(boolean state) {
		client().othersHidden = state;
	}

	@Override
	public void setInventoryDragDelay(int delay) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setInterpolatePlayerAnimations(boolean interpolate) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setInterpolateObjectAnimations(boolean interpolate) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setInterpolateNpcAnimations(boolean interpolate) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setIntStackSize(int stackSize) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setHintArrow(NPC npc) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setHintArrow(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setHintArrow(WorldPoint point) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setGameDrawingMode(int gameDrawingMode) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setFriendsHidden(boolean state) {
		// TODO Auto-generated method stub
		client().friendsHidden = state;
		
	}
	
	@Override
	public void setDraggedOnWidget(Widget widget) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setCompass(SpritePixels spritePixels) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setClanMatesHidden(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setChangedSkillsCount(int i) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setCameraPitchRelaxerEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setAttackersHidden(boolean state) {
		// TODO Auto-generated method stub
		client().attackerHidden = state;
		
	}
	
	@Override
	public void runScript(int id, Object... args) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void refreshChat() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void playSoundEffect(int id, int x, int y, int range) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void playSoundEffect(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isStretchedFast() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isStretchedEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isResized() {
		return client().clientSize != 0;
	}
	
	@Override
	public boolean isPrayerActive(Prayer prayer) {
		return getVar(prayer.getVarbit()) == 1;
	}
	
	@Override
	public boolean isMenuOpen() {
		return client().isMenuOpen();
	}
	
	@Override
	public boolean isKeyPressed(int keycode) {
		return client().isKeyPressed(keycode);
	}
	
	@Override
	public boolean isInterpolatePlayerAnimations() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isInterpolateObjectAnimations() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isInterpolateNpcAnimations() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isInInstancedRegion() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isFriended(String name, boolean mustBeLoggedIn) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isDraggingWidget() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isClanMember(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean hasHintArrow() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int[][] getXteaKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public World[] getWorldList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getWorld() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Widget[] getWidgetRoots() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] getWidgetPositionsY() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] getWidgetPositionsX() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public HashTable getWidgetFlags() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Widget getWidget(int groupId, int childId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public RSInterface getWidget(int widget) {
		// TODO Auto-generated method stub
		return RSInterface.interfaceCache[widget];
	}
	@Override
	public Widget getWidget(WidgetInfo widget) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getViewportYOffset() {
		return 4;
	}
	
	@Override
	public int getViewportXOffset() {
		return 4;
	}
	
	@Override
	public int getViewportWidth() {
		return client().getGameAreaWidth();
	}
	
	@Override
	public Widget getViewportWidget() {
		return null;
	}
	
	@Override
	public int getViewportHeight() {
		return client().getGameAreaHeight();
	}
	
	@Override
	public int[] getVarps() {
		return client().variousSettings;
	}
	
	@Override
	public int getVarpValue(int[] varps, int varpId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getVarbitValue(int[] varps, int varbitId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String getVar(VarClientStr varClientStr) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getVar(VarClientInt varClientInt) {
		// TODO Auto-generated method stub
		return getVarps()[varClientInt.getIndex()];
	}
	
	@Override
	public int getVar(Varbits varbit) {
		if (varbit.getId() >= client().variousSettings.length) {
			return 0;
		}
		
		return client().variousSettings[varbit.getId()];
	}
	
	@Override
	public int getVar(VarPlayer varPlayer) {
		// TODO Auto-generated method stub
		return getVarps()[varPlayer.getId()];
	}
	
	@Override
	public String getUsername() {
		return null;
	}
	
	@Override
	public byte[][][] getTileSettings() {
		return client().getTileSettings();
	}
	
	@Override
	public int[][][] getTileHeights() {
		return client().getTileHeights();
	}
	
	@Override
	public int getTickCount() {
		return client().gameTick;
	}
	
	@Override
	public int getStringStackSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String[] getStringStack() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Dimension getStretchedDimensions() {
		// TODO Auto-generated method stub
		return new Dimension(765, 503);
	}
	
	@Override
	public String[] getStrVarcs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SpritePixels getSprite(IndexDataBase source, int archiveId, int fileId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] getSkillExperiences() {
		return client().getSkillExperience();
	}
	
	@Override
	public int getSkillExperience(Skill skill) {
		return client().getSkillExperience(skill.ordinal());
	}
	
	@Override
	public Tile getSelectedSceneTile() {
		return client().worldController.getSelectedSceneTile();
	}
	
	@Override
	public WorldController getScene() {
		return client().worldController;
	}
	
	@Override
	public int getScale() {
		return isResized() ? 1024 : 512;
	}
	
	@Override
	public int getRevision() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public RenderOverview getRenderOverview() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] getRealSkillLevels() {
		return client().currentMaxStats;
	}
	
	@Override
	public int getRealSkillLevel(Skill skill) {
		return client().currentMaxStats[skill.ordinal()];
	}
	
	@Override
	public Dimension getRealDimensions() {
		return new Dimension(client().getClientWidth(), client().getClientHeight());
	}
	
	@Override
	public List<com.simplicity.client.Projectile> getProjectiles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Preferences getPreferences() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Player> getPlayers() {
		return Arrays.asList(client().getPlayers());
	}
	
	@Override
	public boolean[] getPlayerOptionsPriorities() {
		return client().getPlayerOptionPriorities();
	}
	
	@Override
	public String[] getPlayerOptions() {
		return client().getPlayerOptions();
	}
	
	@Override
	public int[] getPlayerMenuTypes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getPlane() {
		return client().plane;
	}
	
	@Override
	public ObjectComposition getObjectDefinition(int objectId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<NPC> getNpcs() {
		List<NPC> npcs = new ArrayList<>();
		
		for (NPC n : client().getNpcs()) {
			if (n == null) {
				continue;
			}
			
			npcs.add(n);
		}
		
		return npcs;
	}
	
	@Override
	public NPCComposition getNpcDefinition(int npcId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMouseIdleTicks() {
		return client().idleTime;
	}
	
	@Override
	public int getMouseCurrentButton() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Point getMouseCanvasPosition() {
		// TODO Auto-generated method stub
		return new Point(client().mouseX, client().mouseY);
	}
	
	@Override
	public IndexedSprite[] getModIcons() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public MenuEntry[] getMenuEntries() {
		MenuEntry[] entries = new MenuEntry[client().menuActionRow];
		for (int i = 0; i < client().menuActionRow; i++) {
			String action = client().menuActionName[i];
			String target = (client().menuActionTarget[i] != null && !client().menuActionTarget[i].equalsIgnoreCase("null")) ? client().menuActionTarget[i] : null;
			
			if (action != null) {
				if(target != null && target.length() <= 0) {
					if (action.contains("@")) {
						target = action.substring(action.indexOf("@"));
						action = action.substring(0, action.indexOf("@"));
					}
				}
				
				entries[i] = new MenuEntry();
				entries[i].setParam1(client().menuActionCmd3[i]); // widget id
				entries[i].setType(client().menuActionID[i]); // action type
				entries[i].setIdentifier(client().menuActionCmd1[i]);
				entries[i].setParam0(client().menuActionCmd2[i]);
				entries[i].setParam1(client().menuActionCmd3[i]);
				entries[i].setOption(action);
				entries[i].setTarget(target);
			}
		}
		
		return entries;
	}
	
	@Override
	public IndexedSprite[] getMapScene() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] getMapRegions() {
		return client().getRegions();
	}
	
	@Override
	public SpritePixels[] getMapIcons() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SpritePixels[] getMapDots() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Area[] getMapAreas() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMapAngle() {
		return client().getMinimapRotation();
	}
	
	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Player getLocalPlayer() {
		return client().myPlayer;
	}
	
	@Override
	public LocalPoint getLocalDestinationLocation() {
		int sceneX = client().getDestX();
		int sceneY = client().getDestY();
		
		if (sceneX != 0 && sceneY != 0) {
			return LocalPoint.fromScene(sceneX, sceneY);
		}
		
		return null;
	}
	
	@Override
	public int getKeyboardIdleTicks() {
		return client().idleTime;
	}
	
	@Override
	public ItemComposition getItemDefinition(int id) {
		ItemDefinition def = ItemDefinition.forID(id);
		
		if (def == null) {
			return null;
		}
		
		return new ItemComposition() {
			
			@Override
			public void setShiftClickActionIndex(int shiftclickActionIndex) {
				
			}
			
			@Override
			public void resetShiftClickActionIndex() {
				
			}
			
			@Override
			public boolean isTradeable() {
				return !def.untradeable;
			}
			
			@Override
			public boolean isStackable() {
				return def.stackable;
			}
			
			@Override
			public boolean isMembers() {
				return def.membersObject;
			}
			
			@Override
			public int getShiftClickActionIndex() {
				return 0;
			}
			
			@Override
			public int getPrice() {
				return def.value;
			}
			
			@Override
			public int getPlaceholderTemplateId() {
				return 0;
			}
			
			@Override
			public int getPlaceholderId() {
				return 0;
			}
			
			@Override
			public int getNote() {
				return def.certID;
			}
			
			@Override
			public String getName() {
				return def.name;
			}
			
			@Override
			public int getLinkedNoteId() {
				return def.certTemplateID;
			}
			
			@Override
			public String[] getInventoryActions() {
				return def.actions;
			}
			
			@Override
			public int getId() {
				return def.id;
			}
		};
	}
	
	@Override
	public ItemContainer getEquipment() {
		return client().getEquipment();
	}
	
	@Override
	public ItemContainer getInventory() {
		return client().getInventory();
	}
	
	@Override
	public int[] getIntVarcs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getIntStackSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int[] getIntStack() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[][][] getInstanceTemplateChunks() {
		return client().getConstructRegionData();
	}
	
	@Override
	public IndexDataBase getIndexSprites() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public HintArrowType getHintArrowType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public WorldPoint getHintArrowPoint() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Player getHintArrowPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public NPC getHintArrowNpc() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<GraphicsObject> getGraphicsObjects() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GrandExchangeOffer[] getGrandExchangeOffers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GameState getGameState() {
		if (client().loggedIn) {
			return GameState.LOGGED_IN;
		}
		
		return GameState.LOGIN_SCREEN;
	}
	
	@Override
	public int getGameDrawingMode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getGameCycle() {
		return client().loopCycle;
	}
	
	@Override
	public Friend[] getFriends() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getFPS() {
		return client().fps;
	}
	
	@Override
	public int getEnergy() {
		return client().currentEnergy;
	}
	
	@Override
	public Widget getDraggedWidget() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Widget getDraggedOnWidget() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public HashTable<WidgetNode> getComponentTable() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CollisionDetection[] getCollisionMaps() {
		return client().clippingPlanes;
	}
	
	@Override
	public ClanMember[] getClanMembers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getClanChatCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Map<Integer, ChatLineBuffer> getChatLineMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getChangedSkillsCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int[] getChangedSkills() {
		return client().currentStats;
	}
	
	@Override
	public Canvas getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getCameraZ() {
		return client().zCameraPos;
	}
	
	@Override
	public int getCameraYaw() {
		return client().xCameraCurve;
	}
	
	@Override
	public int getCameraY() {
		return client().yCameraPos;
	}
	
	@Override
	public int getCameraX() {
		return client().xCameraPos;
	}
	
	@Override
	public int getCameraPitch() {
		return client().yCameraCurve;
	}
	public int getObjectRenderCutoffDistance() {
		return client().objectRenderCutoffDistance;
	}
	public void setObjectRenderCutoffDistance(int distance) {
		client().objectRenderCutoffDistance = distance;
	}
	
	@Override
	public Callbacks getCallbacks() {
		return RuneLite.getInjector().getInstance(Hooks.class);
	}
	
	@Override
	public Player[] getCachedPlayers() {
		return client().getPlayers();
	}
	
	@Override
	public NPC[] getCachedNPCs() {
		return client().getNpcs();
	}
	
	@Override
	public RSImageProducer getBufferProvider() {
		return client().getGameScreenIP();
	}
	
	@Override
	public int[] getBoostedSkillLevels() {
		return client().currentStats;
	}
	
	@Override
	public int getBoostedSkillLevel(Skill skill) {
		return client().currentStats[skill.ordinal()];
	}
	
	@Override
	public int getBaseY() {
		return com.simplicity.client.Client.getBaseY();
	}
	
	@Override
	public int getBaseX() {
		return com.simplicity.client.Client.getBaseX();
	}
	
	@Override
	public AccountType getAccountType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SpritePixels drawInstanceMap(int z) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public World createWorld() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SpritePixels createSpritePixels(int[] pixels, int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SpritePixels createItemSprite(int itemId, int quantity, int border, int shadowColor, int stackable,
			boolean noted, int scale) {
		// TODO Auto-generated method stub
		return ItemDefinition.getSprite(itemId, quantity, shadowColor);
	}
	public int[] getSidebarIds() {
		return client().tabInterfaceIDs;
	}
	@Override
	public IndexedSprite createIndexedSprite() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void clearHintArrow() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void changeWorld(World world) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void changeMemoryMode(boolean lowMemory) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addChatMessage(ChatMessageType type, String name, String message, String sender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DrawCallbacks getDrawCallbacks() {
		return client().drawCallbacks;
	}

	@Override
	public void setDrawCallbacks(DrawCallbacks drawCallbacks) {
		client().drawCallbacks = drawCallbacks;
	}

	@Override
	public int getCanvasHeight() {
		return client().getClientHeight();
	}

	@Override
	public int getCanvasWidth() {
		return client().getClientWidth();
	}

	@Override
	public boolean isGpu() {
		return false;
	}

	@Override
	public void setGpu(boolean gpu) {

	}

	@Override
	public int get3dZoom() {
		return WorldController.viewDistance;
	}

	@Override
	public int getCenterX() {
		return getViewportWidth() / 2;
	}

	@Override
	public int getCenterY() {
		return getViewportHeight() / 2;
	}

	@Override
	public int getCameraX2() {
		return getScene().cameraX2;
	}

	@Override
	public int getCameraY2() {
		return getScene().cameraY2;
	}

	@Override
	public int getCameraZ2() {
		return getScene().cameraZ2;
	}

	@Override
	public TextureProvider getTextureProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRasterizer3D_clipMidX2() {
		return DrawingArea.viewport_centerY;
	}

	@Override
	public int getRasterizer3D_clipNegativeMidX() {
		return -DrawingArea.viewport_centerY;
	}

	@Override
	public int getRasterizer3D_clipNegativeMidY() {
		return -DrawingArea.viewport_centerX;
	}

	@Override
	public int getRasterizer3D_clipMidY2() {
		return DrawingArea.viewport_centerX;
	}

	@Override
	public void checkClickbox(Model model, int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x,
							  int y, int z, long hash) {

	}

	@Override
	public void setSkyboxColor(int skyboxColor) {
		client().skyboxColor = skyboxColor;
	}

	@Override
	public int getSkyboxColor() {
		// TODO Auto-generated method stub
		return client().skyboxColor;
	}

	@Override
	public NodeCache getCachedModels2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resizeCanvas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getOverallExperience() {
		return client().getTotalXp();
	}


}
