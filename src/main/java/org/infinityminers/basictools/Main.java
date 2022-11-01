package org.infinityminers.basictools;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.infinityminers.basictools.commands.FeedCommand;
import org.infinityminers.basictools.commands.HealCommand;
import org.infinityminers.basictools.commands.admin.AdminTeleportCommand;
import org.infinityminers.basictools.commands.admin.GCCommand;
import org.infinityminers.basictools.commands.admin.GamemodeCommand;
import org.infinityminers.basictools.commands.admin.SetWarpCommand;
import org.infinityminers.basictools.commands.player.*;
import org.infinityminers.basictools.listener.ListenerHandler;
import org.infinityminers.basictools.listener.PlayerJoinListener;
import org.infinityminers.basictools.listener.PlayerQuitListener;
import org.infinityminers.basictools.listener.PluginMessageReceivedListener;
import org.infinityminers.basictools.managers.BasicTimer;
import org.infinityminers.basictools.managers.CommandHandler;
import org.infinityminers.basictools.managers.PlayerManager;
import org.infinityminers.basictools.managers.WarpManager;
import org.infinityminers.basictools.storage.SQLStorage;

public class Main extends JavaPlugin {
    ListenerHandler listenerHandler;
    CommandHandler commandHandler;
    PlayerManager playerManager;
    WarpManager warpManager;
    SQLStorage storage;
    BasicTimer timer;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        timer = new BasicTimer(this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, timer, 1000, 50); // TPS STUFF O.o

        listenerHandler = new ListenerHandler(this);

        storage = new SQLStorage(this);


        playerManager = new PlayerManager(this, storage);
        warpManager = new WarpManager(this, storage);
        listenerHandler.register(
                new PlayerJoinListener(playerManager),
                new PlayerQuitListener(playerManager)
        );

        this.getServer().getMessenger().registerIncomingPluginChannel(this, "teronaproxy:main", new PluginMessageReceivedListener(this));
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "teronaproxy:main");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "teronaproxy:teleport", new PluginMessageReceivedListener(this));
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "teronaproxy:teleport");

        commandHandler = new CommandHandler(this, playerManager);
        commandHandler.register(
                new AdminTeleportCommand(playerManager, this),
                new SuicideCommand(playerManager),
                new TeleportAcceptCommand(playerManager, this),
                new TeleportCommand(playerManager),
                new FeedCommand(playerManager),
                new HealCommand(playerManager),
                new SetHomeCommand(playerManager, this),
                new DeleteHomeCommand(playerManager),
                new HomeCommand(playerManager, this),
                new WarpCommand(playerManager, this, warpManager),
                new SetWarpCommand(playerManager, warpManager, this),
                new GCCommand(this),
                new GamemodeCommand(playerManager)
        );
    }

    @Override
    public void onDisable() {
        playerManager.save();
    }

    public BasicTimer getTimer() {
        return timer;
    }
}