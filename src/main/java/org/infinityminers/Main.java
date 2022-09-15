package org.infinityminers;

import org.bukkit.plugin.java.JavaPlugin;
import org.infinityminers.commands.HealCommand;

public class Main extends JavaPlugin {
    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        this.getCommand("heal").setExecutor(new HealCommand());
    }
}