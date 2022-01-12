package net.runelite.client.plugins.combatstyles;

import net.runelite.api.GraphicsBufferType;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class CombatStylesOverlay extends Overlay {
    private final CombatStylesPlugin plugin;

    private final CombatStylesConfig config;

    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    private CombatStylesOverlay(CombatStylesPlugin plugin, CombatStylesConfig config) {
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        this.plugin = plugin;
        this.config = config;
        setGraphicsBuffer(GraphicsBufferType.MAIN_GAME);
    }

    public Dimension render(Graphics2D graphics) {
        this.panelComponent.getChildren().clear();
        if (this.config.alwaysShowStyle()) {
            String attackStyleString = this.plugin.getAttackStyle().getName();
            this.panelComponent.getChildren().add(TitleComponent.builder()
                    .text(attackStyleString)
                    .color(Color.WHITE)
                    .build());
            this.panelComponent.setPreferredSize(new Dimension(graphics
                    .getFontMetrics().stringWidth(attackStyleString) + 10, 0));
            return this.panelComponent.render(graphics);
        }
        return null;
    }
}
