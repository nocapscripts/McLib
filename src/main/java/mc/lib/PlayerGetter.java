package mc.tools; // Add this line at the top

import java.net.InetSocketAddress;
import org.bukkit.entity.Player;

public class PlayerGetter {

    // Get the balance of a player
    public double getMoney(Player player) {
        if (EconomySystem.getEconomy() != null) {
            return EconomySystem.getEconomy().getBalance(player);
        }
        return 0.0; // Return 0.0 if economy is not available
    }

    // Set money for a player
    public boolean setMoney(Player player, double amount) {
        if (EconomySystem.getEconomy() != null) {
            EconomySystem.getEconomy().depositPlayer(player, amount);
            return true; // Indicate success
        }
        return false; // Indicate failure due to null economy
    }

    // Remove money from a player
    public boolean removeMoney(Player player, double amount) {
        if (EconomySystem.getEconomy() != null) {
            EconomySystem.getEconomy().withdrawPlayer(player, amount);
            return true; // Indicate success
        }
        return false; // Indicate failure due to null economy
    }

    // Check if the player has enough money
    public boolean hasEnough(Player player, double amount) {
        if (EconomySystem.getEconomy() != null) {
            return EconomySystem.getEconomy().getBalance(player) >= amount;
        }
        return false; // Indicate not enough money due to null economy
    }

    // Get player name
    public String getName(Player player) {
        return player.getName();
    }

    // Get player IP address
    public InetSocketAddress getIp(Player player) {
        return player.getAddress();
    }

    // Get player experience points
    public float getXP(Player player) {
        return player.getExp();
    }

    // Give experience points to a player
    public void giveXP(Player player, int xp) {
        player.giveExp(xp);
    }

    // Get player ping
    public int getPing(Player player) {
        return player.getPing();
    }

    // Save player data
    public void savePlayer(Player player) {
        player.saveData();
    }
}
