package com.simplicity.client.widget.ge;

import com.simplicity.client.RSInterface;
import com.simplicity.client.Client;

import java.util.*;

/**
 * A class that handles the grand exchange.
 *
 * @author Blake
 *
 */
public class GrandExchange {

    /**
     * A hash collection of searchable items on the grand exchange.
     */
    public static Map<Integer, SearchItem> items = new HashMap<>();

    /**
     * A hash collection of the buy buttons.
     */
    public static List<Integer> BUY_BUTTONS = new ArrayList<>();

    /**
     * A hash collection of the sell buttons.
     */
    public static List<Integer> SELL_BUTTONS = new ArrayList<>();

    /**
     * Gets the item search container.
     *
     * @return The item search container.
     */
    public static RSInterface getSearchContainer() {
        return RSInterface.interfaceCache[GrandExchangeSearchWidget.WIDGET_ID + 2];
    }

    /**
     * The client instance.
     */
    private static Client client = Client.getClient();

    /**
     * Resets the search container.
     */
    public static void resetSearchContainer() {
        RSInterface container = getSearchContainer();

        container.inv = new int[container.inv.length];
    }

    /**
     * Shows the search dialog.
     */
    public static void showSearch() {
        client.setInputDialog(6);
        resetSearchContainer();
    }

    public static void toggleSearch(boolean visible) {
        if (visible) {
            client.setInputDialog(6);
            resetSearchContainer();
        } else {
            client.setInputDialog(0);
        }
    }

    /**
     * A comparator used to compare two {@link SearchResult}'s.
     */
    public static final Comparator<SearchItem> SEARCH_COMPARATOR = new Comparator<SearchItem>() {

        @Override
        public int compare(SearchItem r1, SearchItem r2) {
            return r1.getName().compareTo(r2.getName());
        }

    };

    /**
     * Updates the search results.
     *
     * @param input  The input.
     * @param delete A flag which states if the input has been reduced.
     */
    public static void updateSearch(String input, boolean delete) {
        if (!client.geSearchString.isEmpty()) {
            client.geSearchString = "";
        }

        RSInterface.interfaceCache[GrandExchangeSearchWidget.WIDGET_ID + 1].scrollPosition = 0;

        RSInterface container = getSearchContainer();
        container.inv = new int[container.inv.length];

        if (delete && input.isEmpty()) {
            return;
        }

        List<SearchItem> ids = GrandExchange.forKeyword(input, container.inv.length);

        if (ids.size() >= 300) {
            client.geSearchString = "Too many matches found. Please refine your search.";
            return;
        }

        if (ids.isEmpty()) {
            client.geSearchString = "No matches found.";
            return;
        }

        for (int i = 0; i < ids.size(); i++) {
            SearchItem item = ids.get(i);

            container.inv[i] = item.getItemId() + 1;
            container.invStackSizes[i] = item.isStackable() ? 1000 : 1;
        }
    }

    /**
     * Returns of a list of {@link SearchResult}'s based on the keyword.
     *
     * @param keyword The keyword.
     * @param max     The maximum amount of results.
     * @return The list of results.
     */
    public static List<SearchItem> forKeyword(String keyword, int max) {
        keyword = keyword.toLowerCase();

        List<SearchItem> results = new ArrayList<SearchItem>();

        for (Map.Entry<Integer, SearchItem> e : items.entrySet()) {
            SearchItem item = e.getValue();

            String name = item.getName().toLowerCase();

            if (name.equals(keyword) || name.contains(keyword) || name.startsWith(keyword)) {
                results.add(item);
            } else {
                continue;
            }

            if (results.size() == max) {
                break;
            }
        }

        Collections.sort(results, SEARCH_COMPARATOR);
        return results;
    }

}
