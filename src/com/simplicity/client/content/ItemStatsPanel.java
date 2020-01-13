package com.simplicity.client.content;

import java.awt.Color;
import java.awt.Graphics2D;

import com.simplicity.client.Client;
import com.simplicity.client.DrawingArea;
import com.simplicity.client.RSFontSystem;

/**
 * Handles the item stats panel. The panel can be activated on an item in the
 * player's inventory by hovering it and by holding down the ctrl-key.
 * 
 * @author Blake
 *
 */
public class ItemStatsPanel {

	/**
	 * The text on the stats panel.
	 */
	private static final String[] TEXT = new String[] { "Stab", "Slash", "Crush", "Magic", "Range", "Strength bonus",
			"Prayer bonus" };

	/**
	 * The name of the item.
	 */
	private String name;

	/**
	 * The bonus.
	 */
	private int[] bonus = new int[12];

	/**
	 * Constructs a new {@link ItemStatsPanel}.
	 * 
	 * @param name  The name of the item.
	 * @param bonus The bonus.
	 */
	public ItemStatsPanel(String name, int[] bonus) {
		this.name = name;
		this.bonus = bonus;
	}

	/**
	 * Gets the name of the item.
	 * 
	 * @return The name of the item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the bonus.
	 * 
	 * @return The bonus.
	 */
	public int[] getBonus() {
		return bonus;
	}

	/**
	 * Draws the item's stats on the screen.
	 * 
	 * @param x         The x.
	 * @param y         The y.
	 * @param font      The font.
	 * @param itemIndex The item's index.
	 */
	public void draw(int x, int y, RSFontSystem font, int itemIndex) {
		Graphics2D g2d = DrawingArea.createGraphics(true);

		int boxWidth = 140;

		int boxHeight = 120;

		if (Client.clientSize == 0) {
			if (Client.getClient().mouseX + boxWidth >= 745) {
				x = 100;
			}

		} else {
			if (Client.getClient().mouseX + boxWidth >= Client.clientWidth - 13) {
				x = Client.clientWidth - 4 - boxWidth;
			}
		}

		if (itemIndex > 15) {
			y -= boxHeight + 15;
		}

		g2d.setColor(new Color(0, 0, 0, 55));

		g2d.drawRect(x, y + 50, boxWidth, boxHeight);

		g2d.setColor(new Color(10, 0, 0, 25));

		g2d.fillRect(x + 1, y + 50 + 1, boxWidth - 1, boxHeight - 1);

		g2d.setColor(new Color(255, 152, 0, 100));

		int textY = y + 65;

		font.drawCenteredString(name, x + boxWidth / 2, textY, 0xff9800, 0);

		textY += 16;

		int textX = x + 4;

		int yPos = 0;

		for (int i = 0; i < TEXT.length; i++) {
			font.drawBasicString(TEXT[i] + ":", textX, textY + yPos, 0xff9800, 0);
			yPos += 14;
		}

		yPos = 0;

		for (int i = 0; i < bonus.length; i++) {
			if (i < 5) {
				font.drawCenteredString(getBonusString(bonus[i]), textX + 60, textY + yPos, 0xffffff, 0);
				yPos += 14;
			} else {
				if (i == 5) {
					yPos = 0;
				}

				if (i >= 10) {
					font.drawRightAlignedString(getBonusString(bonus[i]), textX + 50 + 65 + 10, textY + yPos, 0xffffff,
							0);
				} else {
					font.drawCenteredString(getBonusString(bonus[i]), textX + 50 + 65, textY + yPos, 0xffffff, 0);
				}

				yPos += 14;
			}
		}
	}

	/**
	 * Formats the bonus string by adding the correct prefix.
	 * 
	 * @param bonus The bonus.
	 * @return The formated bonus string.
	 */
	public String getBonusString(int bonus) {
		if (bonus == 0) {
			return " " + bonus;
		}

		if (bonus > 0) {
			return "+" + bonus;
		} else {
			return "" + bonus;
		}
	}

}
