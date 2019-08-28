package com.simplicity.client.content.dropdownmenu;

import com.simplicity.client.Client;
import com.simplicity.client.DrawingArea;
import com.simplicity.client.RSInterface;
import com.simplicity.client.Sprite;

public class DropDownMenu extends RSInterface {

    /**
     * The background color of the drop down menu.
     */
    private final int backgroundColor;

    /**
     * The color of the hovered selected item.
     */
    private final int hoverColor;

    /**
     * Array of {@link DropDownAction}s that will be displayed when the Drop
     * Down Menu has been opened.
     */
    private final DropDownAction[] actions;

    /**
     * Determines if the drop down menu has been opened.
     */
    private boolean isOpen;

    /**
     * Determines the amount of columns the content of drop menu should be distributed into.
     */
    private final int columnAmount;

    /**
     * Determines the column width the contents should fit into.
     */
    private final int columnWidth;
    
    /**
     * The text shadow.
     */
    private final int textShadow;

    private static final int ARRROW_UP = 1035;

    private static final int ARRROW_DOWN = 1036;
    
	public DropDownMenu(int identifier, int width, int backgroundColor, int hoverColor, int columnAmount, int columnWidth, DropDownAction[] actions) {
		this(identifier, width, backgroundColor, hoverColor, -1, columnAmount, columnWidth, actions);
	}

	public DropDownMenu(int identifier, int width, int backgroundColor, int hoverColor, int textShadow, int columnAmount, int columnWidth, DropDownAction[] actions) {
        super(identifier, width, 16, 18, 18);
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.actions = actions;
        this.columnAmount = columnAmount;
        this.columnWidth = columnWidth;
        this.textShadow = textShadow;
        message = actions == null || actions.length < 1 ? "NO ACTION" : actions[0].getMessage();
        disabledSprite = Client.cacheSprite[ARRROW_UP];
        enabledSprite = Client.cacheSprite[ARRROW_DOWN];
    }

    @Override
    public boolean drawInterface(final Client client, int widgetDrawX, int widgetDrawY, int subWidgetDrawX,
                                 int subWidgetDrawY) {

        int drawX = subWidgetDrawX;
        int drawY = subWidgetDrawY;
        int parentDDMWidth = width;
        int parentDDMHeight = 20;
        int childDDMWidth = columnWidth * columnAmount;
        int rowAmount = (int) Math.ceil((float) getActions().length / columnAmount);
        int childDDMHeight = rowAmount * parentDDMHeight + 2;

        /**
         * Draw rectangle and fill rectangle for the parent DDM.
         */
        DrawingArea.drawPixels2(parentDDMHeight, drawY, drawX, getBackgroundColor(), parentDDMWidth); //Fill Rectangle
        DrawingArea.fillPixels(drawX, parentDDMWidth, parentDDMHeight, 0, drawY); //Rectangle

        /**
         * Draw arrow down/up sprite. Determined by the isOpen field.
         */
        final Sprite draw = isOpen() ? disabledSprite : enabledSprite;
        draw.drawSprite(subWidgetDrawX + width - draw.myWidth - 2, subWidgetDrawY + 2);

        /**
         * Draw the first drop down menu option text in the parent DDM.
         */
        client.newSmallFont.drawCenteredString(message, drawX + (width - parentDDMHeight) / 2, drawY + parentDDMHeight - 6, 0xFF981F, textShadow, false);

        /**
         * Handle drawing of the child DDM contents etc.
         */
        if (isOpen()) {

            if (Client.openInterfaceID == 28630 && id >= 28670 && id <= 28683) { //Hot keys
                final int tabStoneSprite = (id - 28670) + (id - 28);
                RSInterface.interfaceCache[tabStoneSprite].disabledSprite = Client.cacheSprite[12];
            }

            DrawingArea.drawPixels2(childDDMHeight, drawY + parentDDMHeight - 1, drawX, getBackgroundColor(), childDDMWidth); //Fill Rectangle
            DrawingArea.fillPixels(drawX, childDDMWidth, childDDMHeight, 0, drawY + parentDDMHeight - 1); //Rectangle

            int counter = 0;
            drawY += parentDDMHeight - 1;
            for (DropDownAction action : getActions()) {
                if (action == null) {
                    continue;
                }
                if (action.isHighlighted()) {
                    DrawingArea.drawPixels2(parentDDMHeight, subWidgetDrawY + parentDDMHeight + counter * parentDDMHeight, subWidgetDrawX + 1, getHoverColor(),
                            columnWidth - 2);
                }

                client.newSmallFont.drawCenteredString(action.getMessage(), drawX + columnWidth / 2, drawY + parentDDMHeight - 5, 0xFF981F, textShadow, false);

                action.setHighlighted(false);
                drawY += parentDDMHeight;
                counter++;
                if (counter == rowAmount) {
                    drawX += columnWidth;
                    drawY = subWidgetDrawY + parentDDMHeight - 1;
                    subWidgetDrawX += columnWidth;
                    counter = 0;
                }
            }


        }
        return true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public int getColumnAmount() {
        return columnAmount;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getHoverColor() {
        return hoverColor;
    }

    public DropDownAction[] getActions() {
        return actions;
    }
}
