package mc.lib; // Ensure your package is correctly defined

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
            Logs.sendWarning("Vault plugin not found. Economy system will not function.");
        } else {
            Logs.sendWarning("Economy system initialized successfully.");
        }
    }

    /**
     * Set up Vault's economy service.
     * 
     * @return True if setup was successful, false otherwise.
     */
    private boolean setupEconomy() {
        // Check if Vault is installed
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        
        // Get the economy service from Vault
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false; // Economy service not found
        }
        
        economy = rsp.getProvider(); // Set the economy instance
        return economy != null; // Return whether the economy instance is successfully set
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
            Logs.sendWarning("Economy system not initialized. Cannot add money.");
            return false;
        }
        economy.depositPlayer(player, amount); // Add money to the player's balance
        return true; // Indicate success
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
           Logs.sendWarning("Economy system not initialized. Cannot remove money.");
            return false;
        }
        economy.withdrawPlayer(player, amount); // Remove money from the player's balance
        return true; // Indicate success
    }

    /**
     * Check if a player has enough money.
     * 
     * @param player The player to check.
     * @param amount The amount to check against.
     * @return True if the player has enough money, false otherwise.
     */
    public boolean hasEnoughMoney(Player player, double amount) {
        return economy != null && economy.getBalance(player) >= amount; // Check if player has enough money
    }
}
