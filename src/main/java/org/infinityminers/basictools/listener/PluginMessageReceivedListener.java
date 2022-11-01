package org.infinityminers.basictools.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.infinityminers.basictools.Main;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class PluginMessageReceivedListener implements PluginMessageListener {
    Main plugin;

    public PluginMessageReceivedListener(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(message));
        try {
            String subchannel = msgin.readUTF();
            System.out.println(subchannel);
            if (subchannel.equals("teronaproxy:teleport")) {
                String somedata = msgin.readUTF();
                System.out.println(somedata);
                Bukkit.getScheduler().runTaskLater(plugin,() -> teleportPlayer(somedata),20);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void teleportPlayer(String data) {
        String[] values = data.split(",");
        Player p = Bukkit.getPlayer(values[0]);
        World w = Bukkit.getWorld(values[5]);
        double x = Double.parseDouble(values[2]);
        double y =  Double.parseDouble(values[3]);
        double z =  Double.parseDouble(values[4]);
        float yaw = Float.parseFloat(values[6]);
        float pitch = Float.parseFloat(values[7]);
        Location loc = new Location(w, x, y, z, yaw, pitch);
        assert p != null;
        p.teleport(loc);
    }
}
