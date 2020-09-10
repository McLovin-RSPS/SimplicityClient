package com.simplicity.client.widget;

import java.util.HashMap;
import java.util.Map;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.deals.DealBoardWidget;
import com.simplicity.client.widget.deals.ItemDealBoardWidget;
import com.simplicity.client.widget.deals.MultipleItemDealBoxWidget;
import com.simplicity.client.widget.raids.cox.RaidingPartiesContainerWidget;
import com.simplicity.client.widget.raids.cox.RaidingPartiesWidget;
import com.simplicity.client.widget.raids.cox.XericPointsWidget;
import com.simplicity.client.widget.raids.cox.party.RaidingPartyContainerWidget;
import com.simplicity.client.widget.raids.cox.party.RaidingPartyWidget;
import com.simplicity.client.widget.raids.tob.*;

public class Widget {
	
	/**
	 * A hash collection of widget components mapped to their parent ids.
	 */
    public static HashMap<Integer, Integer> componentForMain = new HashMap<Integer, Integer>();
    
    /**
     * A hash collection of widgets mapped to their ids.
     */
    public static Map<Integer, CustomWidget> widgets = new HashMap<>();

	/**
	 * Initializes the widgets.
	 */
	public static void init() {
		componentForMain.clear();
		init(new DealBoardWidget());
		init(new ItemDealBoardWidget());
		init(new MultipleItemDealBoxWidget());
		init(new XericPointsWidget());
		init(new TobPartyOverlayWidget());
		init(new TheatrePerformersWidget());
		init(new PerformersContainerWidget());
		init(new TheatrePartyWidget());
		init(new PartyContainerWidget());
		init(new ApplicantsContainerWidget());
		init(new PerformanceDetails());
		init(new PartyHiscores());
		init(new PartyHiscoresContainer());
		init(new TheatreRewardsWidget());
		init(new TobFadeText());
		init(new TobSuppliesWidget());
		init(new RaidingPartiesWidget());
		init(new RaidingPartiesContainerWidget());
		init(new RaidingPartyWidget());
		init(new RaidingPartyContainerWidget());
	}

	/**
	 * Initializes the specified widget.
	 * 
	 * @param widget The widget.
	 */
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
    
	/**
	 * Gets if the specified id is a widget component.
	 * 
	 * @param id The id.
	 * @return <code>true</code> if a component.
	 */
	public static boolean isWidgetComponent(int id) {
    	return id > 0 && componentForMain.containsKey(id);
    }
    
	/**
	 * Returns the parent widget for the specified component id.
	 * 
	 * @param componentId The component id.
	 * @return The parent widget.
	 */
	public static CustomWidget mainForComponent(int componentId) {
		if (!isWidgetComponent(componentId)) {
			return null;
		}
		
		int mainId = componentForMain.get(componentId);

		return widgets.get(mainId);
	}
}
