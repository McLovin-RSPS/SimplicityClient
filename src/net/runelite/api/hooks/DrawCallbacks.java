package net.runelite.api.hooks;

import com.simplicity.client.*;

public interface DrawCallbacks
{
	void draw(Animable renderable, int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z, long hash);

	void drawScenePaint(int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z,
						SceneTilePaint paint, int tileZ, int tileX, int tileY,
						int zoom, int centerX, int centerY);


	void drawSceneModel(int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z,
						SceneTileModel model, int tileZ, int tileX, int tileY,
						int zoom, int centerX, int centerY);

	/**
	 * Called when a frame should be drawn.
	 *
	 * @param overlayColor Color of full-viewport overlays, if any
	 */
	void draw(int overlayColor);

	boolean drawFace(Model model, int face);

	void drawScene(int cameraX, int cameraY, int cameraZ, int cameraPitch, int cameraYaw, int plane);

	/**
	 * Called after the scene has been drawn
	 */
	void postDrawScene();

	void animate(Background texture, int diff);
}
