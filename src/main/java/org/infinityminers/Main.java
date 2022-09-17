package org.infinityminers;

import org.bukkit.plugin.java.JavaPlugin;
import org.infinityminers.commands.basic.FeedCommand;
import org.infinityminers.commands.basic.FlyCommand;
import org.infinityminers.commands.basic.GodCommand;
import org.infinityminers.commands.basic.HealCommand;
import org.infinityminers.listener.EntityDamageByEntityListener;
import org.infinityminers.modules.PlayerInitializer;

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
        this.getCommand("feed").setExecutor(new FeedCommand());
        this.getCommand("god").setExecutor(new GodCommand(this));
        this.getCommand("fly").setExecutor(new FlyCommand(this));
    }

    public PlayerInitializer getPlayerInit() {
        return playerInit;
    }
}