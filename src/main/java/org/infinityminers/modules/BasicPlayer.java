package org.infinityminers.modules;

import org.bukkit.entity.Player;

public class BasicPlayer {
    private boolean god;
    private boolean flying;

    public BasicPlayer(Player player) {
        this.god = false;
        this.flying = false;
    }

    public boolean isGod() {
        return god;
    }

    public void setGod(boolean god) {
        this.god = god;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }
}
