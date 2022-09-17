package org.infinityminers.modules;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerInitializer implements Listener {
    private HashMap<UUID, BasicPlayer> basicPlayerHashMap = new HashMap<>();

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        var player = event.getPlayer();

        if (basicPlayerHashMap.get(player.getUniqueId()) == null)
            return;

        basicPlayerHashMap.put(player.getUniqueId(), new BasicPlayer(player));
    }

    public HashMap<UUID, BasicPlayer> getBasicPlayerHashMap() {
        return basicPlayerHashMap;
    }
}
