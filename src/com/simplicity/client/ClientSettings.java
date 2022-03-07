package com.simplicity.client;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.simplicity.Configuration;
import com.simplicity.client.content.Keybinding;
import com.simplicity.client.content.login.LoginScreen;
import com.simplicity.client.content.login.LoginScreen.CharacterFile;
import com.simplicity.client.widget.settings.Setting;
import com.simplicity.client.widget.settings.Settings;

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
			
			if (reader.has("hd-textures")) {
				Configuration.enableHDTextures = reader.get("hd-textures").getAsBoolean();
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

			if (reader.has("moderation-menu-enabled")) {
				Configuration.enableModerationMenu = reader.get("moderation-menu-enabled").getAsBoolean();
			}

			if (reader.has("hd-shading")) {
				Configuration.enableHDShading = reader.get("hd-shading").getAsBoolean();
			}
			
			if (reader.has("chat-effects")) {
				Client.instance.variousSettings[171] = reader.get("chat-effects").getAsBoolean() ? 1 : 0;
				Client.sendVarbitChanged(171);
			}
			
			if (reader.has("split-private-chat")) {
				Client.instance.variousSettings[287] = reader.get("split-private-chat").getAsBoolean() ? 0 : 1;
				Client.sendVarbitChanged(287);
			}
			
			if (reader.has("mouse-buttons")) {
				Client.instance.anInt1253 = Client.instance.variousSettings[170] = reader.get("mouse-buttons").getAsBoolean() ? 1 : 0;
				Client.sendVarbitChanged(170);
			}
			
			if (reader.has("item-stats-hover")) {
				Configuration.enableItemStats = reader.get("item-stats-hover").getAsInt();
			}
			
			if (reader.has("zooming")) {
				Configuration.enableZooming = reader.get("zooming").getAsBoolean();
			}
			
			if (reader.has("bounty-target")) {
				Configuration.enableBountyTarget = reader.get("bounty-target").getAsBoolean();
			}
			
			if (reader.has("kdr-overlay")) {
				Configuration.enableKDROverlay = reader.get("kdr-overlay").getAsBoolean();
			}
			
			if (reader.has("new-hp-bars")) {
				Configuration.enableNewHpBars = reader.get("new-hp-bars").getAsBoolean();
			}
			
			if (reader.has("keybindings")) {
				Keybinding.KEYBINDINGS = builder.fromJson(reader.get("keybindings"), int[].class);
			}
			
			if (reader.has("quick-prayers")) {
				int[] prayers = builder.fromJson(reader.get("quick-prayers"), int[].class);
				if (prayers.length < Client.instance.prayerLevelRequirements.length)
					prayers = new int[Client.instance.prayerLevelRequirements.length];
				Client.instance.setQuickPrayers(prayers);
			}
			
			if (reader.has("quick-curses")) {
				Client.instance.setQuickCurses(builder.fromJson(reader.get("quick-curses"), int[].class));
			}

			for (Map.Entry<String, Object> entry : Settings.settings.entrySet()) {
				String key = entry.getKey();

				if (reader.has(key)) {
					JsonArray arr = reader.getAsJsonArray(key);
					String type = arr.get(0).getAsString();

					if (type.equals("str")) {
						Settings.settings.put(key, arr.get(1).getAsString());
					} else if (type.equals("bool")) {
						Settings.settings.put(key, arr.get(1).getAsBoolean());
					} else if (type.equals("int")) {
						Settings.settings.put(key, arr.get(1).getAsInt());
					}
				}
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
			object.addProperty("chat-effects", Client.instance.variousSettings[171] == 1);
			object.addProperty("split-private-chat", Client.instance.variousSettings[287] == 0);
			object.addProperty("mouse-buttons", Client.instance.variousSettings[170] == 1);
			object.addProperty("item-stats-hover", Configuration.enableItemStats);
			object.addProperty("moderation-menu-enabled", Configuration.enableModerationMenu);
			object.addProperty("zooming", Configuration.enableZooming);
			object.addProperty("bounty-target", Configuration.enableBountyTarget);
			object.addProperty("kdr-overlay", Configuration.enableKDROverlay);
			object.addProperty("new-hp-bars", Configuration.enableNewHpBars);
			object.add("keybindings", builder.toJsonTree(Keybinding.KEYBINDINGS));
			object.add("quick-prayers", builder.toJsonTree(Client.instance.getQuickPrayers()));
			object.add("quick-curses", builder.toJsonTree(Client.instance.getQuickCurses()));


			for (Map.Entry<String, Object> entry : Settings.settings.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				JsonArray arr = new JsonArray();

				if (value instanceof String) {
					arr.add("str");
				} else if (value instanceof Boolean) {
					arr.add("bool");
				} else if (value instanceof Integer) {
					arr.add("int");
				} else {
					System.out.println("Invalid setting value: " + value.getClass().getSimpleName());
					continue;
				}

				arr.add(String.valueOf(value));
				object.add(key, arr);
			}

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
		Configuration.enableHDTextures = false;
		Configuration.enableSpecialOrb = true;
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
		Configuration.enableMipmapping = false;
		Configuration.enableItemStats = 1;
		Configuration.enableZooming = true;
		Configuration.enableBountyTarget = true;
		Configuration.enableModerationMenu = true;
		Configuration.enableKDROverlay = false;
		Configuration.enableNewHpBars = true;
		Client.instance.musicEnabled = false;
		SoundPlayer.setVolume(4);
	}

}
