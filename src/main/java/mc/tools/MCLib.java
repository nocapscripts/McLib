package mc.tools;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the MCLib plugin.
 */
public class MCLib extends JavaPlugin {

    private static MCLib instance; // Singleton instance of the plugin
    private EconomySystem economySystem; // Instance of EconomySystem

    @Override
    public void onEnable() {
        instance = this; // Set the singleton instance

        // Initialize the economy system
        economySystem = new EconomySystem();
        
        // Notify that the plugin has been enabled and the economy system is initialized
        Logs.sendStartNotify("Plugin successfully enabled and economy system initialized.");
    }

    @Override
    public void onDisable() {
        // Log the plugin disable event
        Logs.sendInfo("Plugin disabled.");
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
     * Getter for the EconomySystem instance.
     * 
     * @return The EconomySystem instance.
     */
    public EconomySystem getEconomySystem() {
        return economySystem;
    }
}
