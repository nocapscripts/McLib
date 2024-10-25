package mc.lib; // Ensure your package is correctly defined

import java.net.InetSocketAddress;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Class to manage player-related functionalities.
 */
public class PlayerGetter {

    // Get the balance of a player
    public double getMoney(Player player) {
        if (MCLib.getInstance().getEconomy() != null) {
            double balance = MCLib.getInstance().getEconomy().getBalance(player);
            Bukkit.getLogger().info("Player: " + player.getName() + ", Balance: " + balance);
            return balance;
        }
        Bukkit.getLogger().warning("Economy is not available.");
        return 0.0; // Return 0.0 if economy is not available
    }

    // Set money for a player
    public boolean setMoney(Player player, double amount) {
        if (MCLib.getInstance().getEconomy() != null) {
            MCLib.getInstance().getEconomy().depositPlayer(player, amount);
            return true; // Indicate success
        }
        return false; // Indicate failure due to null economy
    }

    // Remove money from a player
    public boolean removeMoney(Player player, double amount) {
        if (MCLib.getInstance().getEconomy() != null) {
            MCLib.getInstance().getEconomy().withdrawPlayer(player, amount);
            return true; // Indicate success
        }
        return false; // Indicate failure due to null economy
    }

    // Check if the player has enough money
    public boolean hasEnough(Player player, double amount) {
        return MCLib.getInstance().getEconomy() != null && MCLib.getInstance().getEconomy().getBalance(player) >= amount; // Check if player has enough money
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
