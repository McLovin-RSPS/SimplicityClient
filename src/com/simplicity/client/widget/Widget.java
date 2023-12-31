package com.simplicity.client.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ComparisonChain;
import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.alchemy.AlchemyTable;
import com.simplicity.client.widget.deals.DealBoardWidget;
import com.simplicity.client.widget.deals.ItemDealBoardWidget;
import com.simplicity.client.widget.deals.MultipleItemDealBoxWidget;
import com.simplicity.client.widget.duel_arena.DuelArenaDuelConfirmationWidget;
import com.simplicity.client.widget.duel_arena.DuelArenaDuelOptionsWidget;
import com.simplicity.client.widget.duel_arena.DuelArenaDuelStakeWidget;
import com.simplicity.client.widget.duel_arena.DuelArenaEquipmentWidget;
import com.simplicity.client.widget.ge.*;
import com.simplicity.client.widget.npc_drop_checker.NpcDropChecker;
import com.simplicity.client.widget.presets.PresetsWidget;
import com.simplicity.client.widget.raids.cox.*;
import com.simplicity.client.widget.raids.cox.party.RaidingPartyContainerWidget;
import com.simplicity.client.widget.raids.cox.party.RaidingPartyWidget;
import com.simplicity.client.widget.raids.cox.tab.RaidingTab;
import com.simplicity.client.widget.raids.cox.tab.RaidingTabContainerWidget;
import com.simplicity.client.widget.raids.nightmare.HealthHud;
import com.simplicity.client.widget.raids.nightmare.TotemsOverlay;
import com.simplicity.client.widget.raids.tob.*;
import com.simplicity.client.widget.settings.SettingsWidget;

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
		init(new DuelArenaDuelStakeWidget());
		init(new DuelArenaEquipmentWidget());
		init(new DuelArenaDuelOptionsWidget());
		init(new DuelArenaDuelConfirmationWidget());
		init(new NpcDropChecker());
		init(new AlchemyTable());
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
		init(new RaidingTab());
		init(new RaidingTabContainerWidget());
		init(new HealthHud());
		init(new TotemsOverlay());

		init(new GrandExchangeMainWidget());
		init(new GrandExchangeOfferWidget());
		init(new GrandExchangeSearchWidget());
		init(new GrandExchangeStatusWidget());
		init(new GrandExchangeOverlayWidget());
		init(new GrandExchangeListingsWidget());
		init(new GrandExchangeHistoryWidget());
		
		init(new NewAchievements());
		init(new BlastFurnaceCoffer());
		init(new SlayerPartner());
		init(new PrivateStorageWidget());
		init(new SharedStorageWidget());
		init(new ShootingStarHud());
		init(new EquipmentBonuses());
		init(new PresetsWidget());
		init(new SettingsWidget());
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

		List<WidgetComponent> sorted = widget.components.stream()
				.sorted((a, b) -> ComparisonChain.start()
				.compare(a.priority, b.priority)
				.result()).collect(Collectors.toList());

        for (WidgetComponent w : sorted) {
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

	public static <T> T get(int id) {
		return (T) widgets.get(id);
	}

}
