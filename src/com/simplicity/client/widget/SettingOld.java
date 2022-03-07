package com.simplicity.client.widget;

import static com.simplicity.Configuration.*;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.Rasterizer;
import com.simplicity.client.widget.dropdown.Dropdown;
import com.simplicity.client.widget.dropdown.DropdownMenu;
import com.simplicity.util.StringUtils;

/**
 * An enumerated effectType that represents and handles a setting under the advanced
 * options interface.
 * 
 * @author Blake
 *
 */
public enum SettingOld {
	
	GRAPHICS(true),
	
	HD_TEXTURES() {

		@Override
		public void handle() {
			enableHDTextures = !enableHDTextures;
		}

		@Override
		public boolean enabled() {
			return enableHDTextures;
		}
	},
	
	HD_SHADING() {

		@Override
		public void handle() {
			enableHDShading = !enableHDShading;
			Rasterizer.enableSmoothShading = enableHDShading;
		}

		@Override
		public boolean enabled() {
			return enableHDShading;
		}
	},
	
	FOG() {

		@Override
		public void handle() {
			enableFog = !enableFog;
		}

		@Override
		public boolean enabled() {
			return enableFog;
		}
	},
	
	MIP_MAPPING() {
		@Override
		public void handle() {
			enableMipmapping = !enableMipmapping;
			Rasterizer.enableMipmapping = enableMipmapping;
		}

		@Override
		public boolean enabled() {
			return enableMipmapping;
		}
	},
	
	ANTI_ALIASING() {

		@Override
		public void handle() {
			enableAntiAliasing = !enableAntiAliasing;
		}

		@Override
		public boolean enabled() {
			return enableAntiAliasing;
		}
	},
	
	OVERLAYS(true),
	
	TOOLTIP_HOVERS() {

		@Override
		public void handle() {
			enableTooltipHover = !enableTooltipHover;
		}

		@Override
		public boolean enabled() {
			return enableTooltipHover;
		}
	},

	ITEM_STATS(new DropdownMenu(75, false, 1, new String[] { "On Hover", "Hold Ctrl", "Disabled" }, Dropdown.ITEM_STATS)) { },
	
	KDR() {
		@Override
		public void handle() {
			Configuration.enableKDROverlay = !Configuration.enableKDROverlay;
		}

		@Override
		public boolean enabled() {
			return Configuration.enableKDROverlay;
		}
	},
	
	TOGGLES(true),
	
	HITMARKERS(new DropdownMenu(75, false, 0, new String[] { "562", "OSRS" }, Dropdown.HITMARKERS)) { },
	
	HP_BARS(new DropdownMenu(75, false, 0, new String[] { "562", "OSRS" }, Dropdown.HPBARS)) { },
	
	GAMEFRAME(new DropdownMenu(75, false, 1, new String[] { "500+", "600+", "OSRS" }, Dropdown.GAMEFRAME)) { },

	CONSTITUTION() {

		@Override
		public void handle() {
			enableConstitution = !enableConstitution;
		}

		@Override
		public boolean enabled() {
			return enableConstitution;
		}
	},

	CURSORS() {

		@Override
		public void handle() {
			enableCursors = !enableCursors;
			Client.instance.setCursor(Configuration.enableCursors ? 0 : -1);
		}

		@Override
		public boolean enabled() {
			return enableCursors;
		}
	},

	CENSOR() {

		@Override
		public void handle() {
			enableCensor = !enableCensor;
		}

		@Override
		public boolean enabled() {
			return enableCensor;
		}
	},

	ABSORB_DAMAGE() {

		@Override
		public void handle() {
			enableAbsorbDamage = !enableAbsorbDamage;
		}

		@Override
		public boolean enabled() {
			return enableAbsorbDamage;
		}
	},

	SAVE_INPUT() {

		@Override
		public void handle() {
			enableSaveInput = !enableSaveInput;
		}

		@Override
		public boolean enabled() {
			return enableSaveInput;
		}
	},
	
	BOUNTY_TARGET() {
		@Override
		public void handle() {
			Configuration.enableBountyTarget = !Configuration.enableBountyTarget;
		}

		@Override
		public boolean enabled() {
			return Configuration.enableBountyTarget;
		}
	};
	
	SettingOld() {
		
	}
	
	/**
	 * Constructs a new {@link SettingOld}.
	 * 
	 * @param category A flag which indicates if the setting is a category or not.
	 */
	SettingOld(boolean category) {
		this.category = category;
	}

	/**
	 * Constructs a new {@link SettingOld}.
	 * 
	 * @param dropdownMenu The drop down menu.
	 */
	SettingOld(DropdownMenu dropdownMenu) {
		this.dropdownMenu = dropdownMenu;
	}
	
	/**
	 * The drop down menu.
	 */
	public DropdownMenu dropdownMenu;
	
	/**
	 * Gets the {@link DropdownMenu}.
	 * 
	 * @return The drop down menu.
	 */
	public DropdownMenu getDropdownMenu() {
		return dropdownMenu;
	}
	
	/**
	 * A flag which indicates if this setting is a category.
	 */
	private boolean category;
	
	/**
	 * Checks if the setting is a category.
	 * @return <code>true</code> if category.
	 */
	public boolean isCategory() {
		return category;
	}
	
	/**
	 * Handles the setting.
	 */
	public void handle() {
		
	}

	/**
	 * Checks if the toggle is enabled.
	 * 
	 * @return <code>true</code> if enabled.
	 */
	public boolean enabled() {
		return false;
	}

	/**
	 * Gets the name of this setting.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return StringUtils.capitalizeWords(name().toLowerCase().replaceAll("_", " "));
	}
	
	public static int size() {
		int size = 0;
		
		for (SettingOld s : values()) {
			size += s.isCategory() ? 1 : 4;
		}
		
		return size;
	}

}
