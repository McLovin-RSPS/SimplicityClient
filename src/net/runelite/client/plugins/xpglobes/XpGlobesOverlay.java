/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.plugins.xpglobes;

import net.runelite.api.Client;
import net.runelite.api.GraphicsBufferType;
import net.runelite.api.Point;
import net.runelite.client.game.SkillIconManager;
import net.runelite.client.plugins.xptracker.XpActionType;
import net.runelite.client.plugins.xptracker.XpTrackerService;
import net.runelite.client.ui.SkillColor;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

import javax.inject.Inject;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;

public class XpGlobesOverlay
extends Overlay {
    private static final int MINIMUM_STEP = 10;
    private static final int PROGRESS_RADIUS_START = 90;
    private static final int PROGRESS_RADIUS_REMAINDER = 0;
    private static final int TOOLTIP_RECT_SIZE_X = 150;
    private static final Color DARK_OVERLAY_COLOR = new Color(0, 0, 0, 180);
    private final Client client;
    private final XpGlobesPlugin plugin;
    private final XpGlobesConfig config;
    private final XpTrackerService xpTrackerService;
    private final PanelComponent xpTooltip = new PanelComponent();
    private final SkillIconManager iconManager;

    @Inject
    private XpGlobesOverlay(Client client, XpGlobesPlugin plugin, XpGlobesConfig config, XpTrackerService xpTrackerService, SkillIconManager iconManager) {
        this.iconManager = iconManager;
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        this.xpTrackerService = xpTrackerService;
        this.setPosition(OverlayPosition.TOP_CENTER);
        setGraphicsBuffer(GraphicsBufferType.MAIN_GAME);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        int queueSize = this.plugin.getXpGlobesSize();
        if (queueSize == 0) {
            return null;
        }
        List<XpGlobe> sortedXpGlobes = this.plugin.getXpGlobes();
        sortedXpGlobes.sort(Comparator.comparing(XpGlobe::getSkill));
        int curDrawX = 0;
        for (XpGlobe xpGlobe : sortedXpGlobes) {
            int startXp = this.xpTrackerService.getStartGoalXp(xpGlobe.getSkill());
            int goalXp = this.xpTrackerService.getEndGoalXp(xpGlobe.getSkill());
            this.renderProgressCircle(graphics, xpGlobe, startXp, goalXp, curDrawX, 0, this.getBounds());
            curDrawX += 10 + this.config.xpOrbSize();
        }
        int markersLength = queueSize * this.config.xpOrbSize() + 10 * (queueSize - 1);
        return new Dimension(markersLength, this.config.xpOrbSize());
    }

    private double getSkillProgress(int startXp, int currentXp, int goalXp) {
        double xpGained = currentXp - startXp;
        double xpGoal = goalXp - startXp;
        return xpGained / xpGoal * 100.0;
    }

    private double getSkillProgressRadius(int startXp, int currentXp, int goalXp) {
        return -(3.6 * this.getSkillProgress(startXp, currentXp, goalXp));
    }

    private void renderProgressCircle(Graphics2D graphics, XpGlobe skillToDraw, int startXp, int goalXp, int x, int y, Rectangle bounds) {
        double radiusCurrentXp = this.getSkillProgressRadius(startXp, skillToDraw.getCurrentXp(), goalXp);
        double radiusToGoalXp = 360.0;
        Ellipse2D backgroundCircle = this.drawEllipse(graphics, x, y);
        this.drawSkillImage(graphics, skillToDraw, x, y);
        Point mouse = this.client.getMouseCanvasPosition();
        int mouseX = mouse.getX() - bounds.x;
        int mouseY = mouse.getY() - bounds.y;
        if (backgroundCircle.contains(mouseX, mouseY)) {
            graphics.setColor(DARK_OVERLAY_COLOR);
            graphics.fill(backgroundCircle);
            this.drawProgressLabel(graphics, skillToDraw, startXp, goalXp, x, y);
            if (this.config.enableTooltips()) {
                this.drawTooltip(graphics, skillToDraw, goalXp, backgroundCircle);
            }
        }
        graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        this.drawProgressArc(graphics, x, y, this.config.xpOrbSize(), this.config.xpOrbSize(), 0.0, radiusToGoalXp, 5, this.config.progressOrbOutLineColor());
        this.drawProgressArc(graphics, x, y, this.config.xpOrbSize(), this.config.xpOrbSize(), 90.0, radiusCurrentXp, this.config.progressArcStrokeWidth(), this.config.enableCustomArcColor() ? this.config.progressArcColor() : SkillColor.find(skillToDraw.getSkill()).getColor());
    }

    private void drawProgressLabel(Graphics2D graphics, XpGlobe globe, int startXp, int goalXp, int x, int y) {
        if (goalXp <= globe.getCurrentXp()) {
            return;
        }
        String progress = (int)this.getSkillProgress(startXp, globe.getCurrentXp(), goalXp) + "%";
        FontMetrics metrics = graphics.getFontMetrics();
        int drawX = x + this.config.xpOrbSize() / 2 - metrics.stringWidth(progress) / 2;
        int drawY = y + this.config.xpOrbSize() / 2 + metrics.getHeight() / 2;
        OverlayUtil.renderTextLocation(graphics, new Point(drawX, drawY), progress, Color.WHITE);
    }

    private void drawProgressArc(Graphics2D graphics, int x, int y, int w, int h, double radiusStart, double radiusEnd, int strokeWidth, Color color) {
        Stroke stroke = graphics.getStroke();
        graphics.setStroke(new BasicStroke(strokeWidth, 0, 2));
        graphics.setColor(color);
        graphics.draw(new Arc2D.Double(x, y, w, h, radiusStart, radiusEnd, 0));
        graphics.setStroke(stroke);
    }

    private Ellipse2D drawEllipse(Graphics2D graphics, int x, int y) {
        graphics.setColor(this.config.progressOrbBackgroundColor());
        Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, this.config.xpOrbSize(), this.config.xpOrbSize());
        graphics.fill(ellipse);
        graphics.draw(ellipse);
        return ellipse;
    }

    private void drawSkillImage(Graphics2D graphics, XpGlobe xpGlobe, int x, int y) {
        BufferedImage skillImage = this.iconManager.getSkillImage(xpGlobe.getSkill());
        if (skillImage == null) {
            return;
        }
        graphics.drawImage((Image)skillImage, x + this.config.xpOrbSize() / 2 - skillImage.getWidth() / 2, y + this.config.xpOrbSize() / 2 - skillImage.getHeight() / 2, null);
    }

    private void drawTooltip(Graphics2D graphics, XpGlobe mouseOverSkill, int goalXp, Ellipse2D drawnGlobe) {
        int x = (int)drawnGlobe.getX() - 75 + this.config.xpOrbSize() / 2;
        int y = (int)drawnGlobe.getY() + this.config.xpOrbSize() + 10;
        mouseOverSkill.setTime(Instant.now());
        String skillName = mouseOverSkill.getSkill().getName();
        String skillLevel = Integer.toString(mouseOverSkill.getCurrentLevel());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String skillCurrentXp = decimalFormat.format(mouseOverSkill.getCurrentXp());
        this.xpTooltip.getChildren().clear();
        this.xpTooltip.setPreferredLocation(new java.awt.Point(x, y));
        this.xpTooltip.setPreferredSize(new Dimension(150, 0));
        this.xpTooltip.getChildren().add(LineComponent.builder().left(skillName).right(skillLevel).build());
        this.xpTooltip.getChildren().add(LineComponent.builder().left("Current XP:").leftColor(Color.ORANGE).right(skillCurrentXp).build());
        if (goalXp > mouseOverSkill.getCurrentXp()) {
            XpActionType xpActionType = this.xpTrackerService.getActionType(mouseOverSkill.getSkill());
            int actionsLeft = this.xpTrackerService.getActionsLeft(mouseOverSkill.getSkill());
            if (actionsLeft != Integer.MAX_VALUE) {
                String actionsLeftString = decimalFormat.format(actionsLeft);
                this.xpTooltip.getChildren().add(LineComponent.builder().left(xpActionType.getLabel() + " left:").leftColor(Color.ORANGE).right(actionsLeftString).build());
            }
            int xpLeft = goalXp - mouseOverSkill.getCurrentXp();
            String skillXpToLvl = decimalFormat.format(xpLeft);
            this.xpTooltip.getChildren().add(LineComponent.builder().left("XP left:").leftColor(Color.ORANGE).right(skillXpToLvl).build());
            int xpHr = this.xpTrackerService.getXpHr(mouseOverSkill.getSkill());
            if (xpHr != 0) {
                String xpHrString = decimalFormat.format(xpHr);
                this.xpTooltip.getChildren().add(LineComponent.builder().left("XP per hour:").leftColor(Color.ORANGE).right(xpHrString).build());
            }
        }
        this.xpTooltip.render(graphics);
    }
}

