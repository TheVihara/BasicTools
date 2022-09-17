package org.infinityminers.commands.basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.infinityminers.Main;
import org.infinityminers.modules.BasicPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FlyCommand implements TabExecutor {
    private HashMap<UUID, BasicPlayer> basicPlayerHashMap;

    public FlyCommand(Main main) {
        this.basicPlayerHashMap = main.getPlayerInit().getBasicPlayerHashMap();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0 -> {
                if (sender instanceof ConsoleCommandSender)
                    return false;

                var player = (Player) sender;
                var basicPlayer = basicPlayerHashMap.get(player.getUniqueId());

                if (basicPlayer.isFlying()) {
                    player.sendMessage("Fly mode disabled.");
                    basicPlayer.setFlying(false);
                } else {
                    player.sendMessage("Fly mode enabled.");
                    basicPlayer.setFlying(true);
                }
            }

            case 1 -> {
                var player = Bukkit.getPlayer(args[0]);

                if (player == null)
                    return false;

                var basicPlayer = basicPlayerHashMap.get(player.getUniqueId());

                if (basicPlayer.isFlying()) {
                    if (sender instanceof Player) player.sendMessage(ChatColor.GREEN + "Your Fly mode has been disabled by " + player.getName() + "!");
                    else player.sendMessage(ChatColor.GREEN + "Your Fly mode has been disabled by CONSOLE!");

                    sender.sendMessage(ChatColor.GREEN + "Set Fly mode to false for " + player.getName() + ".");

                    player.sendMessage("Fly-mode disabled.");
                    basicPlayer.setFlying(false);
                } else {
                    if (sender instanceof Player) player.sendMessage(ChatColor.GREEN + "Your Fly mode has been enabled by " + player.getName() + "!");
                    else player.sendMessage(ChatColor.GREEN + "Your Fly mode has been enabled by CONSOLE!");

                    sender.sendMessage(ChatColor.GREEN + "Set Fly mode to true for " + player.getName() + ".");

                    player.sendMessage("Fly-mode enabled.");
                    basicPlayer.setFlying(true);
                }

                return true;
            }

            default -> { return false; }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            var onlinePlayers = new ArrayList<String>();

            for (Player player : Bukkit.getOnlinePlayers()) {
                onlinePlayers.add(player.getName());
            }

            return onlinePlayers;
        }
        return List.of("");
    }
}