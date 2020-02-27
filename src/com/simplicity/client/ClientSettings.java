package com.simplicity.client;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.simplicity.Configuration;
import com.simplicity.client.content.Keybinding;
import com.simplicity.client.content.LoginScreen;
import com.simplicity.client.content.LoginScreen.CharacterFile;
import com.simplicity.client.entity.Position;

/**
 * A class that handles the client settings saving & loading for the player.
 * 
 * @author Blake
 *
 */
public class ClientSettings {

	/**
	 * The settings file path.
	 */
	private static final String SETTINGS_FILE_PATH = signlink.findcachedir() + "settings.json";

	/**
	 * Attempts to load the client's settings.
	 */
	public static void load() {
		try {
			File file = new File(SETTINGS_FILE_PATH);

			if (!file.exists()) {
				return;
			}

			FileReader fileReader = new FileReader(file);
			JsonParser fileParser = new JsonParser();
			Gson builder = new GsonBuilder().create();
			JsonObject reader = (JsonObject) fileParser.parse(fileReader);

			if (reader.has("characters")) {
				LoginScreen.characters = builder.fromJson(reader.get("characters"), new TypeToken<ArrayList<CharacterFile>>() { }.getType());
			}
			
			if (reader.has("last-character")) {
				Client.instance.lastCharacter = builder.fromJson(reader.get("last-character"), CharacterFile.class);
			}
			
			if (reader.has("remember-me")) {
				Client.instance.rememberMe = reader.get("remember-me").getAsBoolean();
			}
			
			if (reader.has("music-enabled")) {
				Client.instance.musicEnabled = reader.get("music-enabled").getAsBoolean();
			}
			
			if (reader.has("sound-volume")) {
				SoundPlayer.setVolume(reader.get("sound-volume").getAsInt());
			}
			
			if (reader.has("brightness")) {
				Client.instance.shadowIndex = reader.get("brightness").getAsInt();
			}
			
			if (reader.has("hd-textures")) {
				Configuration.enableHDTextures = reader.get("hd-textures").getAsBoolean();
			}
			
			if (reader.has("special-button")) {
				Configuration.enableSpecialButton = reader.get("special-button").getAsBoolean();
			}
			
			if (reader.has("veng-timer")) {
				Configuration.enableTimers = reader.get("veng-timer").getAsBoolean();
			}
			
			if (reader.has("tooltip-hover")) {
				Configuration.enableTooltipHover = reader.get("tooltip-hover").getAsBoolean();
			}
			
			if (reader.has("old-hits")) {
				Configuration.enableOldHitmarkers = reader.get("old-hits").getAsBoolean();
			}
			
			if (reader.has("constitution")) {
				Configuration.enableConstitution = reader.get("constitution").getAsBoolean();
			}
			
			if (reader.has("cursors")) {
				Configuration.enableCursors = reader.get("cursors").getAsBoolean();
			}
			
			if (reader.has("old-frame")) {
				Configuration.enableOldFrame = reader.get("old-frame").getAsBoolean();
			}
			
			if (reader.has("censor")) {
				Configuration.enableCensor = reader.get("censor").getAsBoolean();
			}
			
			if (reader.has("fog")) {
				Configuration.enableFog = reader.get("fog").getAsBoolean();
			}
			
			if (reader.has("absorb-damage")) {
				Configuration.enableAbsorbDamage = reader.get("absorb-damage").getAsBoolean();
			}
			
			if (reader.has("anti-aliasing")) {
				Configuration.enableAntiAliasing = reader.get("anti-aliasing").getAsBoolean();
			}
			
			if (reader.has("save-input")) {
				Configuration.enableSaveInput = reader.get("save-input").getAsBoolean();
			}
			
			if (reader.has("hd-shading")) {
				Configuration.enableHDShading = reader.get("hd-shading").getAsBoolean();
			}
			
			if (reader.has("particles")) {
				Configuration.enableParticles = reader.get("particles").getAsBoolean();
			}
			
			if (reader.has("skill-status-bars")) {
				Configuration.enableSkillStatusBars = reader.get("skill-status-bars").getAsBoolean();
			}
			
			if (reader.has("xp-orbs")) {
				Configuration.enableXpOrbs = reader.get("xp-orbs").getAsBoolean();
			}
			
			if (reader.has("ground-item-names")) {
				Configuration.enableGroundItemNames = reader.get("ground-item-names").getAsBoolean();
			}
			
			if (reader.has("shift-click-drop")) {
				Configuration.enableShiftClickDrop = reader.get("shift-click-drop").getAsBoolean();
			}
			
			if (reader.has("chat-effects")) {
				Client.instance.variousSettings[171] = reader.get("chat-effects").getAsBoolean() ? 1 : 0;
			}
			
			if (reader.has("split-private-chat")) {
				Client.instance.variousSettings[287] = reader.get("split-private-chat").getAsBoolean() ? 0 : 1;
			}
			
			if (reader.has("mouse-buttons")) {
				Client.instance.anInt1253 = Client.instance.variousSettings[170] = reader.get("mouse-buttons").getAsBoolean() ? 1 : 0;
			}
			
			if (reader.has("mouse-camera")) {
				Configuration.enableMouseCamera = reader.get("mouse-camera").getAsBoolean();
			}
			
			if (reader.has("esc-closes-interface")) {
				Configuration.escapeClosesInterface = reader.get("esc-closes-interface").getAsBoolean();
			}
			
			if (reader.has("item-stats")) {
				Configuration.enableItemStats = reader.get("item-stats").getAsBoolean();
			}
			
			if (reader.has("keybindings")) {
				Keybinding.KEYBINDINGS = builder.fromJson(reader.get("keybindings"), int[].class);
			}
			
			if (reader.has("quick-prayers")) {
				Client.instance.setQuickPrayers(builder.fromJson(reader.get("quick-prayers"), int[].class));
			}
			
			if (reader.has("quick-curses")) {
				Client.instance.setQuickCurses(builder.fromJson(reader.get("quick-curses"), int[].class));
			}
			
			if (reader.has("tile-markers")) {
				WorldController.markedTiles = builder.fromJson(reader.get("tile-markers"), new TypeToken<Map<Integer, List<Position>>>() { }.getType());
			}
			
			fileReader.close();
		} catch (Exception e) {
			System.out.println("An error occurred whilst trying to load client settings...");
			e.printStackTrace();
			setDefaults();
			save();
		}
	}

	/**
	 * Attempts to save the client's settings.
	 */
	public static void save() {
		try {
			File file = new File(SETTINGS_FILE_PATH);

			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			FileWriter writer = new FileWriter(file);
			Gson builder = new GsonBuilder().setPrettyPrinting().create();
			JsonObject object = new JsonObject();

			object.add("characters", builder.toJsonTree(LoginScreen.characters));
			object.add("last-character", builder.toJsonTree(Client.instance.lastCharacter));
			object.addProperty("remember-me", Client.instance.rememberMe);
			object.addProperty("music-enabled", Client.instance.musicEnabled);
			object.addProperty("sound-volume", SoundPlayer.getVolume());
			object.addProperty("brightness", Client.instance.shadowIndex);
			object.addProperty("hd-textures", Configuration.enableHDTextures);
			object.addProperty("special-button", Configuration.enableSpecialButton);
			object.addProperty("veng-timer", Configuration.enableTimers);
			object.addProperty("tooltip-hover", Configuration.enableTooltipHover);
			object.addProperty("old-hits", Configuration.enableOldHitmarkers);
			object.addProperty("constitution", Configuration.enableConstitution);
			object.addProperty("cursors", Configuration.enableCursors);
			object.addProperty("old-frame", Configuration.enableOldFrame);
			object.addProperty("censor", Configuration.enableCensor);
			object.addProperty("fog", Configuration.enableFog);
			object.addProperty("absorb-damage", Configuration.enableAbsorbDamage);
			object.addProperty("anti-aliasing", Configuration.enableAntiAliasing);
			object.addProperty("save-input", Configuration.enableSaveInput);
			object.addProperty("hd-shading", Configuration.enableHDShading);
			object.addProperty("particles", Configuration.enableParticles);
			object.addProperty("skill-status-bars", Configuration.enableSkillStatusBars);
			object.addProperty("xp-orbs", Configuration.enableXpOrbs);
			object.addProperty("ground-item-names", Configuration.enableGroundItemNames);
			object.addProperty("shift-click-drop", Configuration.enableShiftClickDrop);
			object.addProperty("chat-effects", Client.instance.variousSettings[171] == 1);
			object.addProperty("split-private-chat", Client.instance.variousSettings[287] == 0);
			object.addProperty("mouse-buttons", Client.instance.variousSettings[170] == 1);
			object.addProperty("mouse-camera", Configuration.enableMouseCamera);
			object.addProperty("esc-closes-interface", Configuration.escapeClosesInterface);
			object.addProperty("item-stats", Configuration.enableItemStats);
			object.add("keybindings", builder.toJsonTree(Keybinding.KEYBINDINGS));
			object.add("quick-prayers", builder.toJsonTree(Client.instance.getQuickPrayers()));
			object.add("quick-curses", builder.toJsonTree(Client.instance.getQuickCurses()));
			object.add("tile-markers", builder.toJsonTree(WorldController.markedTiles));

			writer.write(builder.toJson(object));
			writer.close();
		} catch (Exception e) {
			System.out.println("An error occurred whilst trying to save client settings...");
			e.printStackTrace();
			setDefaults();
		}
	}

	/**
	 * Sets the default values for the toggles.
	 */
	public static void setDefaults() {
		Configuration.enableGroundItemNames = false;
		Configuration.enableHDTextures = false;
		Configuration.enableSpecialButton = false;
		Configuration.enableTimers = true;
		Configuration.enableTooltipHover = false;
		Configuration.enableOldHitmarkers = false;
		Configuration.enableConstitution = false;
		Configuration.enableCursors = true;
		Configuration.enableOldFrame = false;
		Configuration.enableCensor = false;
		Configuration.enableFog = false;
		Configuration.enableAbsorbDamage = true;
		Configuration.enableAntiAliasing = false;
		Configuration.enableSaveInput = true;
		Configuration.enableHDShading = true;
		Configuration.enableParticles = true;
		Configuration.enableSkillStatusBars = true;
		Configuration.enableXpOrbs = true;
		Configuration.enableMipmapping = false;
		Configuration.enableItemStats = true;
		Client.instance.musicEnabled = false;
		SoundPlayer.setVolume(4);
	}

}
