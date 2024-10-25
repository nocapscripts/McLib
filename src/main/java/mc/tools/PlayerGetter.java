package mc.tools;




import java.net.InetSocketAddress;

import org.bukkit.entity.Player;

public class PlayerGetter {
    private EconomySystem economySystem;

    // Constructor accepting the economy system
    public PlayerGetter(EconomySystem economySystem) {
        this.economySystem = economySystem;
    }

    public double getMoney(Player player) {
        return economySystem.getBalance(player);
    }

    public void setMoney(Player player, double amount) {
        economySystem.addMoney(player, amount);
    }

    public void removeMoney(Player player, double amount) {
        economySystem.removeMoney(player, amount);
    }

    public boolean hasEnough(Player player, double amount) {
        return economySystem.hasEnoughMoney(player, amount);
    }

    public String getName(Player player) {
        return player.getName();
    }

    public InetSocketAddress getIp(Player player) {
        return player.getAddress();
    }

    public float getXP(Player player) {
        return player.getExp();
    }

    public void giveXP(Player player, int xp) {
        player.giveExp(xp);
    }

    public int getPing(Player player) {
        return player.getPing();
    }

    public void savePlayer(Player player) {
        player.saveData();
    }
}

