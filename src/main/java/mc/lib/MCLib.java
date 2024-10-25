package mc.lib; // Ensure your package is correctly defined

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
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

        // Initialize the economy system
        if (setupEconomy()) {
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
     * Setup Vault's economy service.
     * 
     * @return True if setup was successful, false otherwise.
     */
    private boolean setupEconomy() {
        // Check if Vault is installed
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            Logs.sendWarning("Vault plugin not found. Economy system will not function.");
            return false;
        }

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

    // Example method to add money to a player
    public boolean addMoney(String playerName, double amount) {
        if (economy != null) {
            return economy.depositPlayer(playerName, amount).transactionSuccess();
        }
        Logs.sendWarning("Economy instance is not initialized. Cannot add money.");
        return false;
    }

    // Example method to withdraw money from a player
    public boolean withdrawMoney(String playerName, double amount) {
        if (economy != null) {
            return economy.withdrawPlayer(playerName, amount).transactionSuccess();
        }
        Logs.sendWarning("Economy instance is not initialized. Cannot withdraw money.");
        return false;
    }

    // Additional methods to handle other economy interactions can be added here
}
