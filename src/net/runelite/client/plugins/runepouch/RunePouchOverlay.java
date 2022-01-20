/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.plugins.runepouch;


import com.simplicity.client.RSInterface;
import com.simplicity.client.Sprite;
import com.simplicity.client.cache.definitions.ItemDefinition;
import net.runelite.api.Client;
import net.runelite.api.GraphicsBufferType;
import net.runelite.api.Point;
import net.runelite.api.SpritePixels;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.ui.overlay.tooltip.Tooltip;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;
import net.runelite.client.util.AsyncBufferedImage;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RunePouchOverlay
extends WidgetItemOverlay {
    private static final int RUNE_ONE = 29908;
    private static final int RUNE_TWO = 29909;
    private static final int RUNE_THREE = 29910;
    private static final int[] WIDGETS = new int[]{49010};
    private static final Dimension IMAGE_SIZE = new Dimension(11, 11);
    private final Client client;
    private final RunePouchConfig config;
    private final TooltipManager tooltipManager;
    @Inject
    private ItemManager itemManager;


    @Inject
    RunePouchOverlay(Client client, RunePouchConfig config, TooltipManager tooltipManager) {
        this.tooltipManager = tooltipManager;
        this.client = client;
        this.config = config;
        this.showOnInventory();
        this.showOnBank();
        setGraphicsBuffer(GraphicsBufferType.ALL);
    }

    private BufferedImage getRuneImage(Runes rune) {
        AsyncBufferedImage runeImg = rune.getImage();
        if (runeImg != null) {
            return runeImg;
        }
        Sprite sprite = ItemDefinition.getSprite(rune.getItemId(), 1, 0);
        if(sprite == null)
            return null;
        runeImg = new AsyncBufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        sprite.toBufferedImage(runeImg);
        AsyncBufferedImage resizedImg = new AsyncBufferedImage(RunePouchOverlay.IMAGE_SIZE.width, RunePouchOverlay.IMAGE_SIZE.height, 2);
        Graphics2D g = resizedImg.createGraphics();
        g.drawImage(runeImg, 0, 0, RunePouchOverlay.IMAGE_SIZE.width, RunePouchOverlay.IMAGE_SIZE.height, null);
        g.dispose();
        rune.setImage(resizedImg);
        return resizedImg;
    }

    private static String formatNumber(int amount) {
        return amount < 1000 ? String.valueOf(amount) : amount / 1000 + "K";
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget) {
        if (itemId != 42791) {
            return;
        }
        graphics.setFont(FontManager.getRunescapeSmallFont());
        Point location = itemWidget.getCanvasLocation();
        StringBuilder tooltipBuilder = new StringBuilder();
            BufferedImage image;
            Runes rune;
            RSInterface widget = client.getWidget(49010);

            if(widget.inv == null)
                return;

            for(int l = 0; l < widget.inv.length; l++) {
                if(widget.inv[l] <= 0 || widget.invStackSizes[l] <= 0 || Runes.getRune(widget.inv[l] - 1) == null
                || this.config.runePouchOverlayMode() == RunePouchOverlayMode.MOUSE_HOVER || getRuneImage(Runes.getRune(widget.inv[l] - 1)) == null
                || !this.config.showIcons()) {
                    continue;
                }
                rune = Runes.getRune(widget.inv[l] - 1);
                image = getRuneImage(rune);
                int amount = widget.invStackSizes[l];
                tooltipBuilder.append(amount).append(" ").append(ColorUtil.wrapWithColorTag(rune.getName(), Color.YELLOW)).append("</br>");
                graphics.setColor(Color.black);
                graphics.drawString("" + RunePouchOverlay.formatNumber(amount), location.getX() + (this.config.showIcons() ? 13 : 6), location.getY() + 14 + (graphics.getFontMetrics().getHeight() - 1) * l);
                graphics.setColor(this.config.fontColor());
                graphics.drawString("" + RunePouchOverlay.formatNumber(amount), location.getX() + (this.config.showIcons() ? 12 : 5), location.getY() + 13 + (graphics.getFontMetrics().getHeight() - 1) * l);

                OverlayUtil.renderImageLocation(graphics, new Point(location.getX(), location.getY() + graphics.getFontMetrics().getHeight() * l), image);
            }
        String tooltip = tooltipBuilder.toString();
        Rectangle rec = new Rectangle(itemWidget.getCanvasBounds().x + 516, itemWidget.getCanvasBounds().y + 168, itemWidget.getCanvasBounds().width, itemWidget.getCanvasBounds().height);
        if (!tooltip.isEmpty() && rec.contains(this.client.getMouseCanvasPosition().getX(), this.client.getMouseCanvasPosition().getY()) && (this.config.runePouchOverlayMode() == RunePouchOverlayMode.MOUSE_HOVER || this.config.runePouchOverlayMode() == RunePouchOverlayMode.BOTH)) {
            this.tooltipManager.add(new Tooltip(tooltip));
        }
    }
}

