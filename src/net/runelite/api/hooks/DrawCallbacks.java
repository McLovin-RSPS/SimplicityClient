package net.runelite.api.hooks;

import net.runelite.api.Model;
import net.runelite.api.Renderable;
import net.runelite.api.SceneTileModel;
import net.runelite.api.SceneTilePaint;
import net.runelite.api.Texture;

public interface DrawCallbacks
{
	void draw(Renderable renderable, int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z, long hash);

	void drawScenePaint(int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z,
						SceneTilePaint paint, int tileZ, int tileX, int tileY,
						int zoom, int centerX, int centerY);


	void drawSceneModel(int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z,
						SceneTileModel model, int tileZ, int tileX, int tileY,
						int zoom, int centerX, int centerY);

	void draw();

	boolean drawFace(Model model, int face);

	void drawScene(int cameraX, int cameraY, int cameraZ, int cameraPitch, int cameraYaw, int plane);

	void animate(Texture texture, int diff);
}
