package net.runelite.client;

import java.awt.Canvas;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import com.simplicity.client.NPC;
import com.simplicity.client.Player;

import ch.qos.logback.classic.Logger;
import net.runelite.api.Area;
import net.runelite.api.BufferProvider;
import net.runelite.api.ChatLineBuffer;
import net.runelite.api.ChatMessageType;
import net.runelite.api.ClanMember;
import net.runelite.api.Client;
import net.runelite.api.CollisionData;
import net.runelite.api.Friend;
import net.runelite.api.GameState;
import net.runelite.api.GrandExchangeOffer;
import net.runelite.api.GraphicsObject;
import net.runelite.api.HashTable;
import net.runelite.api.HintArrowType;
import net.runelite.api.IndexDataBase;
import net.runelite.api.IndexedSprite;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemContainer;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPCComposition;
import net.runelite.api.ObjectComposition;
import net.runelite.api.Point;
import net.runelite.api.Prayer;
import net.runelite.api.Preferences;
import net.runelite.api.Projectile;
import net.runelite.api.RenderOverview;
import net.runelite.api.Scene;
import net.runelite.api.Skill;
import net.runelite.api.SpritePixels;
import net.runelite.api.Tile;
import net.runelite.api.VarClientInt;
import net.runelite.api.VarClientStr;
import net.runelite.api.VarPlayer;
import net.runelite.api.Varbits;
import net.runelite.api.WidgetNode;
import net.runelite.api.World;
import net.runelite.api.WorldType;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.hooks.Callbacks;
import net.runelite.api.vars.AccountType;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;

public class ClientRuneLite implements Client {
	
	public static com.simplicity.client.Client client() {
		return com.simplicity.client.Client.instance;
	}
	
	@Override
	public boolean isClientThread() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Thread getClientThread() {
		return null;
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
	public void setTickCount(int tickCount) {
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setModIcons(IndexedSprite[] modIcons) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setMenuEntries(MenuEntry[] entries) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setLocalPlayerHidden2D(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setLocalPlayerHidden(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setIsHidingEntities(boolean state) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isMenuOpen() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isKeyPressed(int keycode) {
		// TODO Auto-generated method stub
		return false;
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
	public EnumSet<WorldType> getWorldType() {
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
	public Widget getWidget(WidgetInfo widget) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getViewportYOffset() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getViewportXOffset() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return null;
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
		return 0;
	}
	
	@Override
	public int getVar(Varbits varbit) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getVar(VarPlayer varPlayer) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
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
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getSkillExperience(Skill skill) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Tile getSelectedSceneTile() {
		return client().worldController.getSelectedTile();
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getScale() {
		return 512;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getRealSkillLevel(Skill skill) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Dimension getRealDimensions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Projectile> getProjectiles() {
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean[] getPlayerOptionsPriorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String[] getPlayerOptions() {
		// TODO Auto-generated method stub
		return null;
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
		return Arrays.asList(client().getNpcs());
	}
	
	@Override
	public NPCComposition getNpcDefinition(int npcId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMouseIdleTicks() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getMouseCurrentButton() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Point getMouseCanvasPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IndexedSprite[] getModIcons() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public MenuEntry[] getMenuEntries() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IndexedSprite[] getMapScene() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] getMapRegions() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getKeyboardIdleTicks() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public ItemComposition getItemDefinition(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ItemContainer getItemContainer(InventoryID inventory) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Friend[] getFriends() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getFPS() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getEnergy() {
		// TODO Auto-generated method stub
		return 0;
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
	public CollisionData[] getCollisionMaps() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
	
	@Override
	public Callbacks getCallbacks() {
		return null;
	}
	
	@Override
	public Player[] getCachedPlayers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public NPC[] getCachedNPCs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BufferProvider getBufferProvider() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] getBoostedSkillLevels() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getBoostedSkillLevel(Skill skill) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getBaseY() {
		return com.simplicity.client.Client.getBaseX();
	}
	
	@Override
	public int getBaseX() {
		return com.simplicity.client.Client.getBaseY();
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
		return null;
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


}
