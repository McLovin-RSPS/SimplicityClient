package net.runelite.client.events;

import lombok.Value;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayMenuEntry;

/**
 * Event fired when an overlay menu entry is clicked.
 */
@Value
public class OverlayMenuClicked
{
    private OverlayMenuEntry entry;
    private Overlay overlay;
}