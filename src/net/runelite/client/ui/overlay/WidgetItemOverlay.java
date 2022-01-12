/*
 * Copyright (c) 2019, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.ui.overlay;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.simplicity.client.RSInterface;
import lombok.AccessLevel;
import lombok.Setter;
import net.runelite.api.widgets.Widget;
import static net.runelite.api.widgets.WidgetID.BANK_GROUP_ID;
import static net.runelite.api.widgets.WidgetID.BANK_INVENTORY_GROUP_ID;
import static net.runelite.api.widgets.WidgetID.DEPOSIT_BOX_GROUP_ID;
import static net.runelite.api.widgets.WidgetID.EQUIPMENT_GROUP_ID;
import static net.runelite.api.widgets.WidgetID.EQUIPMENT_INVENTORY_GROUP_ID;
import static net.runelite.api.widgets.WidgetID.GRAND_EXCHANGE_INVENTORY_GROUP_ID;
import static net.runelite.api.widgets.WidgetID.GUIDE_PRICES_INVENTORY_GROUP_ID;
import static net.runelite.api.widgets.WidgetID.INVENTORY_GROUP_ID;
//import static net.runelite.api.widgets.WidgetID.SEED_VAULT_INVENTORY_GROUP_ID;
import static net.runelite.api.widgets.WidgetID.SHOP_INVENTORY_GROUP_ID;
//import static net.runelite.api.widgets.WidgetInfo.BANK_CONTENT_CONTAINER;
//import static net.runelite.api.widgets.WidgetInfo.BANK_TAB_CONTAINER;
import static net.runelite.api.widgets.WidgetInfo.TO_GROUP;
import net.runelite.api.widgets.WidgetItem;

public abstract class WidgetItemOverlay extends Overlay
{
	@Setter(AccessLevel.PACKAGE)
	private OverlayManager overlayManager;
	/**
	 * Interfaces to draw overlay over.
	 */
	private final Set<Integer> interfaceGroups = new HashSet<>();

	protected WidgetItemOverlay()
	{
		super.setPosition(OverlayPosition.DYNAMIC);
		super.setPriority(OverlayPriority.LOW);
		super.setLayer(OverlayLayer.ABOVE_WIDGETS);
	}

	public abstract void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget);

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (overlayManager == null) {
			System.out.println("null: ");
		}
		final List<WidgetItem> itemWidgets = overlayManager.getItemWidgets();
		final Rectangle originalClipBounds = graphics.getClipBounds();
		if(itemWidgets.size() > 0) {
		}
		for (WidgetItem widgetItem : itemWidgets)
		{
			RSInterface widget = widgetItem.getWidget();
			int interfaceGroup = widget.id;

			// Don't draw if this widget isn't one of the allowed nor in tag tab/item tab
			if (!interfaceGroups.contains(interfaceGroup) ||
				(interfaceGroup == BANK_GROUP_ID
					/*&& (widget.getParentId() == BANK_CONTENT_CONTAINER.getId() || widget.getParentId() == BANK_TAB_CONTAINER.getId())*/))
			{
				System.out.println("Skipping because interfaceID not allowed: " + interfaceGroup);
				continue;
			}
				graphics.setClip(originalClipBounds);

			renderItemOverlay(graphics, widgetItem.getId() - 1, widgetItem);
		}
		return null;
	}

	protected void showOnInventory()
	{
		showOnInterfaces(3214);
	}

	protected void showOnBank()
	{
		showOnInterfaces(5385, 5062, 5064, 5382, 22042, 22041, 22035);
	}

	protected void showOnEquipment()
	{
		showOnInterfaces(18691);
	}

	protected void showOnInterfaces(int... ids)
	{
		Arrays.stream(ids).forEach(interfaceGroups::add);
	}

	// Don't allow setting position, priority, or layer

	@Override
	public void setPosition(OverlayPosition position)
	{
		throw new IllegalStateException();
	}

	@Override
	public void setPriority(OverlayPriority priority)
	{
		throw new IllegalStateException();
	}

	@Override
	public void setLayer(OverlayLayer layer)
	{
		this.layer = layer;
	}
}
