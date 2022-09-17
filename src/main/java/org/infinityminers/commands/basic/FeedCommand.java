package org.infinityminers.commands.basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FeedCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0 -> {
                if (sender instanceof ConsoleCommandSender)
                    return false;

                var player = (Player) sender;

                player.setHealth(20);
                player.sendMessage(ChatColor.GREEN + "Fed.");
            }

            case 1 -> {
                var player = Bukkit.getPlayer(args[0]);

                if (player == null)
                    return false;

                player.setHealth(20);
                if (sender instanceof Player) player.sendMessage(ChatColor.GREEN + "You've been fed by " + player.getName() + "!");
                else player.sendMessage(ChatColor.GREEN + "You've been fed by CONSOLE!");

                sender.sendMessage(ChatColor.GREEN + "Fed " + player.getName() + ".");

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