package net.runelite.client.plugins.gpu.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UIScalingMode
{
	NEAREST("Nearest Neighbor", 0),
	LINEAR("Bilinear", 0),
	MITCHELL("Bicubic (Mitchell)", 1),
	CATMULL_ROM("Bicubic (Catmull-Rom)", 2),
	XBR("xBR", 3);

	private final String name;
	private final int mode;

	@Override
	public String toString()
	{
		return name;
	}
}
