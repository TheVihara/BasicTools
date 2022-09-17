package org.infinityminers.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.infinityminers.modules.BasicPlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerInitializer implements Listener {
    private HashMap<UUID, BasicPlayer> basicPlayerHashMap = new HashMap<>();

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        var player = event.getPlayer();
        var basicPlayer = new BasicPlayer(player);

        basicPlayerHashMap.put(player.getUniqueId(), basicPlayer);
    }

    public HashMap<UUID, BasicPlayer> getBasicPlayerHashMap() {
        return basicPlayerHashMap;
    }
}
