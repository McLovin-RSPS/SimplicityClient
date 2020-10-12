package com.simplicity.util;

import java.util.HashMap;
import java.util.Map;

import com.simplicity.client.RSInterface;

/**
 * An utility class used to extract the data of dialogues.
 * 
 * @author Blake
 *
 */
public class DialogueUtil {

	/**
	 * A collection of npc dialogues.
	 */
	private static Map<Integer, int[]> npcDialogues = new HashMap<>();

	/**
	 * Determines if the specified id is of a npc dialogue.
	 * 
	 * @param dialogId The dialogue id.
	 * @return
	 */
	public static boolean isNpcDialogue(int dialogId) {
		return npcDialogues.containsKey(dialogId);
	}
	
	/**
	 * Gets the whole text from the specified npc dialogue.
	 * 
	 * @param dialogId The dialogue id.
	 * @return The text.
	 */
	public static String getNpcDialogueText(int dialogId) {
		if (!npcDialogues.containsKey(dialogId)) {
			return null;
		}

		int[] stringIds = npcDialogues.get(dialogId);

		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < stringIds.length; i++) {
			sb.append(RSInterface.interfaceCache[stringIds[i]].message + " ");
		}
		
		return sb.toString();
	}

	/**
	 * Gets the text from the specified line from a npc dialogue.
	 * 
	 * @param dialogId The dialogue id.
	 * @param line     The line.
	 * @return The text.
	 */
	public static String getNpcDialogueText(int dialogId, int line) {
		if (!npcDialogues.containsKey(dialogId)) {
			return null;
		}

		int[] stringIds = npcDialogues.get(dialogId);

		if (line >= stringIds.length) {
			return null;
		}

		return RSInterface.interfaceCache[stringIds[line]].message;
	}

	static {
		npcDialogues.put(4882, new int[] { 4885 });
		npcDialogues.put(4887, new int[] { 4890, 4891 });
		npcDialogues.put(4893, new int[] { 4896, 4897, 4898 });
		npcDialogues.put(4900, new int[] { 4903, 4904, 4905, 4906 });
	}

}
