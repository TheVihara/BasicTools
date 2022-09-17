package org.infinityminers;

import org.bukkit.plugin.java.JavaPlugin;
import org.infinityminers.commands.HealCommand;
import org.infinityminers.listener.EntityDamageByEntityListener;
import org.infinityminers.listener.PlayerInitializer;

public class Main extends JavaPlugin {
    private PlayerInitializer playerInit;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        var server = this.getServer();
        var pluginManager = server.getPluginManager();
        this.playerInit = new PlayerInitializer();

        // Register listeners
        pluginManager.registerEvents(playerInit, this);
        pluginManager.registerEvents(new EntityDamageByEntityListener(this), this);

        // Register commands
        this.getCommand("heal").setExecutor(new HealCommand());
    }

    public PlayerInitializer getPlayerInit() {
        return playerInit;
    }
}