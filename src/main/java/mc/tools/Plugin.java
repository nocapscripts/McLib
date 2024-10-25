package mc.tools;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private static Plugin instance; // Singleton instance
    private EconomySystem economySystem; // Instance of EconomySystem

    @Override
    public void onEnable() {
        instance = this; // Set singleton instance

        // Initialize the economy system
        economySystem = new EconomySystem();
        
       Logs.sendStartNotify("Plugin successfully enabled and economy system initialized.");
    }

    @Override
    public void onDisable() {
       Logs.sendInfo("Plugin disabled.");
    }

    // Singleton getter for the main plugin instance
    public static Plugin getInstance() {
        return instance;
    }

    // Getter for the EconomySystem instance
    public EconomySystem getEconomySystem() {
        return economySystem;
    }
}
