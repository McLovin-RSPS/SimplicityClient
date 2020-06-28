package com.simplicity.client.widget;

import java.util.HashMap;
import java.util.Map;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.deals.DealBoardWidget;
import com.simplicity.client.widget.deals.ItemDealBoardWidget;
import com.simplicity.client.widget.deals.MultipleItemDealBoxWidget;
import com.simplicity.client.widget.raids.TobPartyOverlayWidget;
import com.simplicity.client.widget.raids.XericPointsWidget;

public class Widget {
	
	/**
	 * A hash collection of widget components mapped to their parent ids.
	 */
    public static HashMap<Integer, Integer> componentForMain = new HashMap<Integer, Integer>();
    
    /**
     * A hash collection of widgets mapped to their ids.
     */
    public static Map<Integer, CustomWidget> widgets = new HashMap<>();

    public static void init() {
        componentForMain.clear();
        init(new DealBoardWidget());
        init(new ItemDealBoardWidget());
        init(new MultipleItemDealBoxWidget());
        init(new XericPointsWidget());
        init(new TobPartyOverlayWidget());
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
        
        widgets.put(widget.mainId, widget);
        
        System.out.println("CustomWidget: " + widget.getName() + " id: " + widget.mainId + " to " + lastId + " ("
                + difference + " interfaces ("+widget.id+")) with " + widget.components.size() + " components.");
    }
}
