package com.simplicity.client;

public final class SceneTileModel {

	public final int[] vertexX;
	public final int[] vertexY;
	public final int[] vertexZ;
	public final int[] triangleColorA;
	public final int[] triangleColorB;
	public final int[] triangleColorC;
	public final int[] faceX;
	public final int[] faceY;
	public final int[] faceZ;
	public int triangleTextureId[];
	public final boolean flat;
	public final int shape;
	public final int rotation;
	public final int underlay;
	public final int overlay;
	public static final int[] anIntArray688 = new int[6];
	public static final int[] anIntArray689 = new int[6];
	public static final int[] anIntArray690 = new int[6];
	public static final int[] anIntArray691 = new int[6];
	public static final int[] anIntArray692 = new int[6];
	public static final int[] screenZ = new int[6];
	public int color61;
	public int color71;
	public int color81;
	public int color91;
	public int color62;
	public int color72;
	public int color82;
	public int color92;
	public boolean textured;

	private static final int[][] TYPES = { { 1, 3, 5, 7 },
			{ 1, 3, 5, 7 }, { 1, 3, 5, 7 }, { 1, 3, 5, 7, 6 },
			{ 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 },
			{ 1, 3, 5, 7, 2, 6 }, { 1, 3, 5, 7, 2, 8 }, { 1, 3, 5, 7, 2, 8 },
			{ 1, 3, 5, 7, 11, 12 }, { 1, 3, 5, 7, 11, 12 },
			{ 1, 3, 5, 7, 13, 14 } };
	private static final int[][] DATA = {
			{ 0, 1, 2, 3, 0, 0, 1, 3 },
			{ 1, 1, 2, 3, 1, 0, 1, 3 },
			{ 0, 1, 2, 3, 1, 0, 1, 3 },
			{ 0, 0, 1, 2, 0, 0, 2, 4, 1, 0, 4, 3 },
			{ 0, 0, 1, 4, 0, 0, 4, 3, 1, 1, 2, 4 },
			{ 0, 0, 4, 3, 1, 0, 1, 2, 1, 0, 2, 4 },
			{ 0, 1, 2, 4, 1, 0, 1, 4, 1, 0, 4, 3 },
			{ 0, 4, 1, 2, 0, 4, 2, 5, 1, 0, 4, 5, 1, 0, 5, 3 },
			{ 0, 4, 1, 2, 0, 4, 2, 3, 0, 4, 3, 5, 1, 0, 4, 5 },
			{ 0, 0, 4, 5, 1, 4, 1, 2, 1, 4, 2, 3, 1, 4, 3, 5 },
			{ 0, 0, 1, 5, 0, 1, 4, 5, 0, 1, 2, 4, 1, 0, 5, 3, 1, 5, 4, 3, 1, 4,
					2, 3 },
			{ 1, 0, 1, 5, 1, 1, 4, 5, 1, 1, 2, 4, 0, 0, 5, 3, 0, 5, 4, 3, 0, 4,
					2, 3 },
			{ 1, 0, 5, 4, 1, 0, 1, 5, 0, 0, 4, 3, 0, 4, 5, 3, 0, 5, 2, 3, 0, 1,
					2, 5 } };

	public SceneTileModel(int i, int j, int k, int l, int overlaytex, int underlaytex,
						  int j1, int k1, int l1, int i2, int j2, int k2, int l2, int i3,
						  int j3, int k3, int l3, int i4, int k4, int l4, boolean tex) {
		color61 = l1;
		color71 = i4;
		color81 = j2;
		color91 = k;
		color62 = j;
		color72 = l3;
		color82 = j1;
		color92 = k3;
		textured = tex;
		flat = !(i3 != l2 || i3 != l || i3 != k2);
		shape = j3;
		rotation = k1;
		underlay = i2;
		overlay = l4;
		char c = '\200';
		int i5 = c / 2;
		int j5 = c / 4;
		int k5 = c * 3 / 4;
		int ai[] = TYPES[j3];
		int l5 = ai.length;
		vertexX = new int[l5];
		vertexY = new int[l5];
		vertexZ = new int[l5];
		int ai1[] = new int[l5];
		int ai2[] = new int[l5];
		int i6 = k4 * c;
		int j6 = i * c;
		for (int k6 = 0; k6 < l5; k6++) {
			int l6 = ai[k6];
			if ((l6 & 1) == 0 && l6 <= 8) {
				l6 = (l6 - k1 - k1 - 1 & 7) + 1;
			}
			if (l6 > 8 && l6 <= 12) {
				l6 = (l6 - 9 - k1 & 3) + 9;
			}
			if (l6 > 12 && l6 <= 16) {
				l6 = (l6 - 13 - k1 & 3) + 13;
			}
			int i7;
			int k7;
			int i8;
			int k8;
			int j9;
			if (l6 == 1) {
				i7 = i6;
				k7 = j6;
				i8 = i3;
				k8 = l1;
				j9 = j;
			} else if (l6 == 2) {
				i7 = i6 + i5;
				k7 = j6;
				i8 = i3 + l2 >> 1;
				k8 = l1 + i4 >> 1;
				j9 = j + l3 >> 1;
			} else if (l6 == 3) {
				i7 = i6 + c;
				k7 = j6;
				i8 = l2;
				k8 = i4;
				j9 = l3;
			} else if (l6 == 4) {
				i7 = i6 + c;
				k7 = j6 + i5;
				i8 = l2 + l >> 1;
				k8 = i4 + j2 >> 1;
				j9 = l3 + j1 >> 1;
			} else if (l6 == 5) {
				i7 = i6 + c;
				k7 = j6 + c;
				i8 = l;
				k8 = j2;
				j9 = j1;
			} else if (l6 == 6) {
				i7 = i6 + i5;
				k7 = j6 + c;
				i8 = l + k2 >> 1;
				k8 = j2 + k >> 1;
				j9 = j1 + k3 >> 1;
			} else if (l6 == 7) {
				i7 = i6;
				k7 = j6 + c;
				i8 = k2;
				k8 = k;
				j9 = k3;
			} else if (l6 == 8) {
				i7 = i6;
				k7 = j6 + i5;
				i8 = k2 + i3 >> 1;
				k8 = k + l1 >> 1;
				j9 = k3 + j >> 1;
			} else if (l6 == 9) {
				i7 = i6 + i5;
				k7 = j6 + j5;
				i8 = i3 + l2 >> 1;
				k8 = l1 + i4 >> 1;
				j9 = j + l3 >> 1;
			} else if (l6 == 10) {
				i7 = i6 + k5;
				k7 = j6 + i5;
				i8 = l2 + l >> 1;
				k8 = i4 + j2 >> 1;
				j9 = l3 + j1 >> 1;
			} else if (l6 == 11) {
				i7 = i6 + i5;
				k7 = j6 + k5;
				i8 = l + k2 >> 1;
				k8 = j2 + k >> 1;
				j9 = j1 + k3 >> 1;
			} else if (l6 == 12) {
				i7 = i6 + j5;
				k7 = j6 + i5;
				i8 = k2 + i3 >> 1;
				k8 = k + l1 >> 1;
				j9 = k3 + j >> 1;
			} else if (l6 == 13) {
				i7 = i6 + j5;
				k7 = j6 + j5;
				i8 = i3;
				k8 = l1;
				j9 = j;
			} else if (l6 == 14) {
				i7 = i6 + k5;
				k7 = j6 + j5;
				i8 = l2;
				k8 = i4;
				j9 = l3;
			} else if (l6 == 15) {
				i7 = i6 + k5;
				k7 = j6 + k5;
				i8 = l;
				k8 = j2;
				j9 = j1;
			} else {
				i7 = i6 + j5;
				k7 = j6 + k5;
				i8 = k2;
				k8 = k;
				j9 = k3;
			}
			vertexX[k6] = i7;
			vertexY[k6] = i8;
			vertexZ[k6] = k7;
			ai1[k6] = k8;
			ai2[k6] = j9;
		}

		int ai3[] = DATA[j3];
		int j7 = ai3.length / 4;
		faceX = new int[j7];
		faceY = new int[j7];
		faceZ = new int[j7];
		triangleColorA = new int[j7];
		triangleColorB = new int[j7];
		triangleColorC = new int[j7];
		if (overlaytex != -1 || underlaytex != -1) {
			triangleTextureId = new int[j7];
		}
		int l7 = 0;
		for (int j8 = 0; j8 < j7; j8++) {
			int l8 = ai3[l7];
			int k9 = ai3[l7 + 1];
			int i10 = ai3[l7 + 2];
			int k10 = ai3[l7 + 3];
			l7 += 4;
			if (k9 < 4) {
				k9 = k9 - k1 & 3;
			}
			if (i10 < 4) {
				i10 = i10 - k1 & 3;
			}
			if (k10 < 4) {
				k10 = k10 - k1 & 3;
			}
			faceX[j8] = k9;
			faceY[j8] = i10;
			faceZ[j8] = k10;
			if (l8 == 0) {
				triangleColorA[j8] = ai1[k9];
				triangleColorB[j8] = ai1[i10];
				triangleColorC[j8] = ai1[k10];
				if (triangleTextureId != null) {
					triangleTextureId[j8] = underlaytex;
				}
			} else {
				triangleColorA[j8] = ai2[k9];
				triangleColorB[j8] = ai2[i10];
				triangleColorC[j8] = ai2[k10];
				if (triangleTextureId != null) {
					triangleTextureId[j8] = overlaytex;
				}
			}
		}

		int i9 = i3;
		int l9 = l2;
		if (l2 < i9) {
			i9 = l2;
		}
		if (l2 > l9) {
			l9 = l2;
		}
		if (l < i9) {
			i9 = l;
		}
		if (l > l9) {
			l9 = l;
		}
		if (k2 < i9) {
			i9 = k2;
		}
		if (k2 > l9) {
			l9 = k2;
		}
		i9 /= 14;
		l9 /= 14;
	}

	public int[] getTriangleTextureId() {
		return triangleTextureId;
	}

	public int[] getFaceX() {
		return faceX;
	}

	public int[] getFaceY() {
		return faceY;
	}

	public int[] getFaceZ() {
		return faceZ;
	}

	public int[] getVertexX() {
		return vertexX;
	}

	public int[] getVertexY() {
		return vertexY;
	}

	public int[] getVertexZ() {
		return vertexZ;
	}

	public int[] getTriangleColorA() {
		return triangleColorA;
	}

	public int[] getTriangleColorB() {
		return triangleColorB;
	}

	public int[] getTriangleColorC() {
		return triangleColorC;
	}

	public int bufferOffset = -1;

	public void setBufferOffset(final int offset) {
		bufferOffset = offset;
	}

	public int uVBufferOffset = -1;

	public void setUvBufferOffset(final int offset) {
		uVBufferOffset = offset;
	}

	public int bufferLength = -1;

	public void setBufferLen(final int length) {
		bufferLength = length;
	}

	public int getBufferLen() {
		return bufferLength;
	}

	public int getBufferOffset() {
		return bufferOffset;
	}

	public int getUvBufferOffset() {
		return uVBufferOffset;
	}
}
