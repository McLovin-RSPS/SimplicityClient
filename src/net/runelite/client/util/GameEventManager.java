package net.runelite.client.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.simplicity.client.NPC;
import com.simplicity.client.Player;

import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.GameState;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.Node;
import net.runelite.api.Scene;
import net.runelite.api.Tile;
import net.runelite.api.events.DecorativeObjectSpawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.ItemSpawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.PlayerSpawned;
import net.runelite.api.events.WallObjectSpawned;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.EventBus;

@Singleton
public class GameEventManager
{
	private final EventBus eventBus = new EventBus();
	private final Client client;
	private final ClientThread clientThread;

	@Inject
	private GameEventManager(@Nullable Client client, ClientThread clientThread)
	{
		this.client = client;
		this.clientThread = clientThread;
	}

	/**
	 * Iterates over each tile in the scene if player is logged in
	 *
	 * @param consumer consumer accepting tile as parameter
	 */
	private void forEachTile(Consumer<Tile> consumer)
	{
		final Scene scene = client.getScene();
		final Tile[][][] tiles = scene.getTiles();

		for (int z = 0; z < Constants.MAX_Z; ++z)
		{
			for (int x = 0; x < Constants.SCENE_SIZE; ++x)
			{
				for (int y = 0; y < Constants.SCENE_SIZE; ++y)
				{
					Tile tile = tiles[z][x][y];

					if (tile == null)
					{
						continue;
					}

					consumer.accept(tile);
				}
			}
		}
	}

	/**
	 * Simulate game events for EventBus subscriber
	 *
	 * @param subscriber EventBus subscriber
	 */
	public void simulateGameEvents(Object subscriber)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		clientThread.invoke(() ->
		{

			eventBus.register(subscriber);

			for (final InventoryID inventory : InventoryID.values())
			{
				final ItemContainer itemContainer = client.getItemContainer(inventory);

				if (itemContainer != null)
				{
					//eventBus.post(new ItemContainerChanged(inventory.getId(), itemContainer));
				}
			}

			for (NPC npc : client.getCachedNPCs())
			{
				if (npc != null)
				{
					final NpcSpawned npcSpawned = new NpcSpawned(npc);
					eventBus.post(npcSpawned);
				}
			}

			for (Player player : client.getCachedPlayers())
			{
				if (player != null)
				{
					final PlayerSpawned playerSpawned = new PlayerSpawned(player);
					eventBus.post(playerSpawned);
				}
			}

			forEachTile((tile) ->
			{
				Optional.ofNullable(tile.getWallObject()).ifPresent(object ->
				{
					final WallObjectSpawned objectSpawned = new WallObjectSpawned();
					objectSpawned.setTile(tile);
					objectSpawned.setWallObject(object);
					eventBus.post(objectSpawned);
				});

				Optional.ofNullable(tile.getDecorativeObject()).ifPresent(object ->
				{
					final DecorativeObjectSpawned objectSpawned = new DecorativeObjectSpawned();
					objectSpawned.setTile(tile);
					objectSpawned.setDecorativeObject(object);
					eventBus.post(objectSpawned);
				});

				Optional.ofNullable(tile.getGroundObject()).ifPresent(object ->
				{
					final GroundObjectSpawned objectSpawned = new GroundObjectSpawned();
					objectSpawned.setTile(tile);
					objectSpawned.setGroundObject(object);
					eventBus.post(objectSpawned);
				});

				Arrays.stream(tile.getGameObjects())
					.filter(Objects::nonNull)
					.forEach(object ->
					{
						final GameObjectSpawned objectSpawned = new GameObjectSpawned();
						objectSpawned.setTile(tile);
						objectSpawned.setGameObject(object);
						eventBus.post(objectSpawned);
					});

				Optional.ofNullable(tile.getItemLayer()).ifPresent(itemLayer ->
				{
					Node current = itemLayer.getBottom();

					/*while (current instanceof TileItem)
					{
						final TileItem item = (TileItem) current;

						current = current.getNext();

						final ItemSpawned itemSpawned = new ItemSpawned(tile, item);
						eventBus.post(itemSpawned);
					}*/
				});
			});

			eventBus.unregister(subscriber);
		});
	}
}
