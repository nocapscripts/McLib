package mc.lib; // Ensure your package is correctly defined

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the MCLib plugin.
 */
public class MCLib extends JavaPlugin {

    private static MCLib instance; // Singleton instance of the plugin
    private Economy economy; // Instance of Economy

    @Override
    public void onEnable() {
        instance = this; // Set the singleton instance

        // Initialize the economy system
        setupEconomy();

        // Notify that the plugin has been enabled and the economy system is initialized
        Logs.sendStartNotify("Plugin successfully enabled and economy system initialized.");
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
            Logs.sendWarning("No economy provider found.");
            return false; // Economy service not found
        }

        economy = rsp.getProvider(); // Set the economy instance
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
     * @return The Economy instance.
     */
    public Economy getEconomy() {
        return economy;
    }
}
