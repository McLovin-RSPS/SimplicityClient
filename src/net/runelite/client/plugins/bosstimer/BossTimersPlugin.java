package net.runelite.client.plugins.bosstimer;

import javax.inject.Inject;

import com.simplicity.client.NPC;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.NpcDespawned;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

@PluginDescriptor(
        name = "Boss Timers",
        description = "Show boss spawn timer overlays",
        tags = {"combat", "pve", "overlay", "spawn"}
)
@Slf4j
public class BossTimersPlugin extends Plugin
{
    @Inject
    private InfoBoxManager infoBoxManager;

    @Inject
    private ItemManager itemManager;

    @Override
    protected void shutDown() throws Exception
    {
        infoBoxManager.removeIf(t -> t instanceof RespawnTimer);
    }

    @Subscribe
    public void onNpcDespawned(NpcDespawned npcDespawned)
    {
        NPC npc = npcDespawned.getNpc();

        if (!npc.isDead())
        {
            return;
        }

        int npcId = npc.getId();

        Boss boss = Boss.find(npcId);

        if (boss == null)
        {
            return;
        }

        // remove existing timer
        infoBoxManager.removeIf(t -> t instanceof RespawnTimer && ((RespawnTimer) t).getBoss() == boss);

        log.debug("Creating spawn timer for {} ({} seconds)", npc.getName(), boss.getSpawnTime());

        RespawnTimer timer = new RespawnTimer(boss, itemManager.getImage(boss.getItemSpriteId()), this);
        timer.setTooltip(npc.getName());
        infoBoxManager.addInfoBox(timer);
    }
}