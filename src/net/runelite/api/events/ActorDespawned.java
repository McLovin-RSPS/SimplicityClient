package net.runelite.api.events;

import com.simplicity.client.Entity;

import net.runelite.api.Actor;

/**
 * Represents the base event where an {@link Entity} has despawned.
 * <p>
 * To hook into a more focused actor type, see the {@link PlayerDespawned} or
 * {@link NpcDespawned} events.
 * <p>
 * Examples of when this event may trigger include:
 * <ul>
 * <li>An actor moving out of render distance</li>
 * <li>An actor despawning after death</li>
 * <li>Moving out of or away from a region with Actor entities in it</li>
 * </ul>
 * <p>
 * During a world change, the event is only called for Players, ie.
 * {@link PlayerDespawned} will trigger but {@link NpcDespawned} will not.
 * <p>
 * The client logging out does not trigger this event.
 */
public interface ActorDespawned {
	/**
	 * Gets the despawned player or NPC.
	 *
	 * @return despawned entity
	 */
	Entity getActor();
}
