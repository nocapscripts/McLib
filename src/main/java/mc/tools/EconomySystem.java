package mc.tools;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomySystem {

    private static Economy economy = null;

    // Constructor to initialize the Economy system
    public EconomySystem() {
        if (!setupEconomy()) {
            Bukkit.getLogger().severe("VAULT pluginit ei leitud.");
        }
    }

    // Method to set up Vault's economy service
    private boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    // Get the balance of a player
    public double getBalance(Player player) {
        if (economy == null) {
            return 0.0;
        }
        return economy.getBalance(player);
    }

    // Add money to a player's balance
    public boolean addMoney(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        economy.depositPlayer(player, amount);
        return true;
    }

    // Remove money from a player's balance
    public boolean removeMoney(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        economy.withdrawPlayer(player, amount);
        return true;
    }

    // Check if a player has enough money
    public boolean hasEnoughMoney(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        return economy.getBalance(player) >= amount;
    }
}
