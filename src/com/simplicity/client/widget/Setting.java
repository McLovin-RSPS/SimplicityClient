package com.simplicity.client.widget;

import static com.simplicity.Configuration.*;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.Rasterizer;
import com.simplicity.util.StringUtils;

/**
 * An enumerated type that represents and handles a setting under the advanced
 * options interface.
 * 
 * @author Blake
 *
 */
public enum Setting {

	PARTICLES() {

		@Override
		public void handle() {
			enableParticles = !enableParticles;
			Client.enableParticles = enableParticles;
		}

		@Override
		public boolean enabled() {
			return enableParticles;
		}

	},

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

	GROUND_ITEM_NAMES() {

		@Override
		public void handle() {
			enableGroundItemNames = !enableGroundItemNames;
		}

		@Override
		public boolean enabled() {
			return enableHDTextures;
		}
	},

	SPECIAL_BUTTON() {

		@Override
		public void handle() {
			enableSpecialButton = !enableSpecialButton;
			
            RSInterface iface2 = RSInterface.interfaceCache[41005];
            
            if (enableSpecialButton) {
                if (!Client.instance.parallelWidgetList.contains(iface2)) {
                    Client.instance.parallelWidgetList.add(iface2);
                }
            } else {
                if (Client.instance.parallelWidgetList.contains(iface2)) {
                	Client.instance.parallelWidgetList.remove(iface2);
                }
            }
		}

		@Override
		public boolean enabled() {
			return enableSpecialButton;
		}
	},

	EFFECT_TIMERS() {

		@Override
		public void handle() {
			enableTimers = !enableTimers;
			Client.timersEnabled = enableTimers;
		}

		@Override
		public boolean enabled() {
			return enableTimers;
		}
	},

	OLD_HITMARKERS() {

		@Override
		public void handle() {
			enableOldHitmarkers = !enableOldHitmarkers;
		}

		@Override
		public boolean enabled() {
			return enableOldHitmarkers;
		}
	},

	OLD_GAMEFRAME() {

		@Override
		public void handle() {
			enableOldFrame = !enableOldFrame;
			
			if (enableOldFrame) {
                if (Client.tabID == 13) {
                	Client.tabID = 15;
                }
            } else {
                if (Client.tabID == 15) {
                	Client.tabID = 13;
                }
            }
		}

		@Override
		public boolean enabled() {
			return enableOldFrame;
		}
	},
	
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

	SKILL_STATUS_BARS() {

		@Override
		public void handle() {
			enableSkillStatusBars = !enableSkillStatusBars;
		}

		@Override
		public boolean enabled() {
			return enableSkillStatusBars;
		}
	},

	XP_ORBS() {

		@Override
		public void handle() {
			enableXpOrbs = !enableXpOrbs;
		}

		@Override
		public boolean enabled() {
			return enableXpOrbs;
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
	};

	/**
	 * Handles the setting.
	 */
	public abstract void handle();

	/**
	 * Checks if the toggle is enabled.
	 * 
	 * @return <code>true</code> if enabled.
	 */
	public abstract boolean enabled();

	/**
	 * Gets the name of this setting.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return StringUtils.capitalizeWords(name().toLowerCase().replaceAll("_", " "));
	}

}
