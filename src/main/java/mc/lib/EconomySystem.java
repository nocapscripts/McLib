package mc.tools;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Class to manage the economy system using Vault.
 */
public class EconomySystem {
    private static Economy economy = null;

    /**
     * Get the economy instance.
     * 
     * @return The Economy instance or null if not set up.
     */
    public static Economy getEconomy() {
        return economy;
    }

    /**
     * Constructor to initialize the Economy system.
     */
    public EconomySystem() {
        if (!setupEconomy()) {
            Bukkit.getLogger().severe("Vault plugin not found.");
        }
    }

    /**
     * Set up Vault's economy service.
     * 
     * @return True if setup was successful, false otherwise.
     */
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp;
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null || 
            (rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class)) == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    /**
     * Get the balance of a player.
     * 
     * @param player The player to get the balance from.
     * @return The player's balance or 0.0 if economy is not available.
     */
    public double getBalance(Player player) {
        return economy != null ? economy.getBalance(player) : 0.0;
    }

    /**
     * Add money to a player's balance.
     * 
     * @param player The player to add money to.
     * @param amount The amount to add.
     * @return True if the operation was successful, false otherwise.
     */
    public boolean addMoney(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        economy.depositPlayer(player, amount);
        return true;
    }

    /**
     * Remove money from a player's balance.
     * 
     * @param player The player to remove money from.
     * @param amount The amount to remove.
     * @return True if the operation was successful, false otherwise.
     */
    public boolean removeMoney(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        economy.withdrawPlayer(player, amount);
        return true;
    }

    /**
     * Check if a player has enough money.
     * 
     * @param player The player to check.
     * @param amount The amount to check against.
     * @return True if the player has enough money, false otherwise.
     */
    public boolean hasEnoughMoney(Player player, double amount) {
        return economy != null && economy.getBalance(player) >= amount;
    }
}
