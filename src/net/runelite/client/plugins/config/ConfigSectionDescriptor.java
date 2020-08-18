package net.runelite.client.plugins.config;

import lombok.Value;
import net.runelite.client.config.ConfigSection;

@Value
public class ConfigSectionDescriptor implements ConfigObject
{
	private final String key;
	private final ConfigSection section;

	@Override
	public String key()
	{
		return key;
	}

	@Override
	public String name()
	{
		return section.name();
	}

	@Override
	public int position()
	{
		return section.position();
	}
}
