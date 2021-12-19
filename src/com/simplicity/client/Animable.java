package com.simplicity.client;

import net.runelite.client.plugins.hdnew.HdPlugin;

import java.awt.Polygon;

public class Animable extends QueueNode {

	public void renderAtPoint(int i, int j, int k, int l, int i1, int j1,
							  int k1, int l1, int i2, int newuid) {
		renderAtPoint(i, j, k, l, i1, j1, k1, l1, i2, newuid, 0);
	}
	public void renderAtPoint(int i, int j, int k, int l, int i1, int j1,
			int k1, int l1, int i2, int newuid, int distance) {
		Model model = getRotatedModel();
		if (model != null) {
			modelHeight = model.modelHeight;

			if(HdPlugin.process() && (distance > Client.instance.objectRenderCutoffDistance)) {
				if(x != 0 && y != 0 && Client.clippingPlanes[Client.instance.plane].clipData[x][y] != 0) {
					return;
				}
			}
			model.renderAtPoint(i, j, k, l, i1, j1, k1, l1, i2, newuid, distance);
		}
		// System.gc();
	}

	public Model getRotatedModel() {
		return null;
	}

	public Model getRotatedModelHD() {
		return null;
	}
	
	public Polygon getConvexHull() {
		return null;
	}

	public Animable() {
		modelHeight = 1000;
	}

	VertexNormal vertexNormals[];
	public int modelHeight;
	public int x,y;
}