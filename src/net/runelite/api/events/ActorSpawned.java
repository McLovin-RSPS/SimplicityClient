package net.runelite.api.events;

import com.simplicity.client.Entity;

import net.runelite.api.Actor;

/**
 * Represents the base event where an {@link Entity} has spawned.
 * <p>
 * To hook into a more focused actor type, see the {@link PlayerSpawned} or
 * {@link NpcSpawned} events.
 * <p>
 * Examples of when this event may trigger include:
 * <ul>
 * <li>Entering a new region or area with actors inside</li>
 * <li>A player logging in nearby</li>
 * <li>An actor moving into render distance</li>
 * </ul>
 */
public interface ActorSpawned {
	/**
	 * Gets the spawned player or NPC.
	 *
	 * @return spawned entity
	 */
	Entity getActor();
}
