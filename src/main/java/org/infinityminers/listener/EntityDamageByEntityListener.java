package org.infinityminers.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.infinityminers.Main;
import org.infinityminers.modules.BasicPlayer;

import java.util.HashMap;
import java.util.UUID;

public class EntityDamageByEntityListener implements Listener {
    private Main main;
    private HashMap<UUID, BasicPlayer> basicPlayerHashMap;

    public EntityDamageByEntityListener(Main main) {
        this.main = main;
        this.basicPlayerHashMap = main.getPlayerInit().getBasicPlayerHashMap();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        var entity = event.getEntity();

        if (entity instanceof Player player) {
            var basicPlayer = basicPlayerHashMap.get(player.getUniqueId());

            if (basicPlayer.isGod()) event.setCancelled(true);
        }
    }
}
