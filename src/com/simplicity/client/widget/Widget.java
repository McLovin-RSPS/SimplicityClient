package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.deals.DealBoardWidget;
import com.simplicity.client.widget.deals.ItemDealBoardWidget;
import com.simplicity.client.widget.deals.MultipleItemDealBoxWidget;
import com.simplicity.client.widget.raids.XericPointsWidget;

import java.util.HashMap;

public class Widget {
    public static HashMap<Integer, String> componentForMain = new HashMap<Integer, String>();

    public static void init() {
        componentForMain.clear();
        init(new DealBoardWidget());
        init(new ItemDealBoardWidget());
        init(new MultipleItemDealBoxWidget());
        init(new XericPointsWidget());
    }

    public static void init(CustomWidget widget) {

        RSInterface tab = RSInterface.addTabInterface(widget.mainId);

        int frame = 0;

        widget.init();

        tab.totalChildren(widget.components.size());

        int lastId = 0;

        for (WidgetComponent w : widget.components) {
            tab.child(frame++, w.componentId, w.point.x, w.point.y);
            lastId = w.componentId;
        }

        int difference = lastId - widget.mainId;

        System.out.println("CustomWidget: " + widget.getName() + " id: " + widget.mainId + " to " + lastId + " ("
                + difference + " interfaces ("+widget.id+")) with " + widget.components.size() + " components.");
    }
}
