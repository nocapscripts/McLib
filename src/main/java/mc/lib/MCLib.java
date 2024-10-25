package mc.lib; // Ensure your package is correctly defined

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the MCLib plugin.
 * This class initializes the economy system using Vault.
 */
public class MCLib extends JavaPlugin {

    private static MCLib instance; // Singleton instance of the plugin
    private Economy economy; // Instance of Economy

    @Override
    public void onEnable() {
        instance = this; // Set the singleton instance

        // Directly initialize the economy system
        if (initializeEconomy()) {
            Logs.sendStartNotify("Plugin successfully enabled and economy system initialized.");
        } else {
            Logs.sendWarning("Economy system failed to initialize.");
        }
    }

    @Override
    public void onDisable() {
        // Log the plugin disable event
        Logs.sendInfo("Plugin disabled.");
    }

    /**
     * Initialize Vault's economy service.
     *
     * @return True if initialization was successful, false otherwise.
     */
    private boolean initializeEconomy() {
        // Get the economy service from Vault
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            Logs.sendWarning("No economy provider found. Ensure an economy plugin is installed and enabled.");
            return false; // Economy service not found
        }

        economy = rsp.getProvider(); // Set the economy instance
        if (economy == null) {
            Logs.sendWarning("Failed to set economy provider. Economy service is null.");
        }
        return economy != null; // Return whether the economy instance is successfully set
    }

    /**
     * Getter for the singleton instance of the main plugin class.
     *
     * @return The singleton instance of MCLib.
     */
    public static MCLib getInstance() {
        return instance;
    }

    /**
     * Getter for the Economy instance.
     *
     * @return The Economy instance, or null if not initialized.
     */
    public Economy getEconomy() {
        return economy;
    }

    /**
     * Add money to a player.
     *
     * @param playerName The name of the player.
     * @param amount     The amount to add.
     */
    public void addMoney(String playerName, double amount) {
        if (economy != null) {
            if (economy.depositPlayer(playerName, amount).transactionSuccess()) {
                Logs.sendInfo("Successfully added " + amount + " to " + playerName + "'s balance.");
            } else {
                Logs.sendWarning("Failed to add money to " + playerName + ".");
            }
        } else {
            Logs.sendWarning("Economy instance is not initialized. Cannot add money.");
        }
    }

    /**
     * Withdraw money from a player.
     *
     * @param playerName The name of the player.
     * @param amount     The amount to withdraw.
     */
    public void withdrawMoney(String playerName, double amount) {
        if (economy != null) {
            if (economy.withdrawPlayer(playerName, amount).transactionSuccess()) {
                Logs.sendInfo("Successfully withdrew " + amount + " from " + playerName + "'s balance.");
            } else {
                Logs.sendWarning("Failed to withdraw money from " + playerName + ".");
            }
        } else {
            Logs.sendWarning("Economy instance is not initialized. Cannot withdraw money.");
        }
    }

    /**
     * Get the balance of a player.
     *
     * @param player The player whose balance to check.
     * @return The balance of the player, or 0.0 if the economy is not initialized.
     */
    public double getMoney(Player player) {
        if (economy != null) {
            return economy.getBalance(player);
        }
        return 0.0;
    }

    /**
     * Check if a player has enough money.
     *
     * @param player The player to check.
     * @param amount The amount to check against.
     * @return True if the player has enough money, false otherwise.
     */
    public boolean hasEnough(Player player, double amount) {
        if (economy != null) {
            return economy.getBalance(player) >= amount;
        }
        return false;
    }

    /**
     * Set the balance of a player.
     *
     * @param player The player to set the balance for.
     * @param amount The amount to set as the balance.
     */
    public void setMoney(Player player, double amount) {
        if (economy != null) {
            // Withdraw the current balance and then deposit the new amount
            double currentBalance = getMoney(player);
            withdrawMoney(player.getName(), currentBalance);
            addMoney(player.getName(), amount);
            Logs.sendInfo("Set " + player.getName() + "'s balance to " + amount + ".");
        } else {
            Logs.sendWarning("Economy instance is not initialized. Cannot set money.");
        }
    }
}
