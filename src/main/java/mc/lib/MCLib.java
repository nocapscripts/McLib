package mc.lib; // Ensure your package is correctly defined

import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.Player;



/**
 * Main class for the MCLib plugin.
 * This class initializes the economy system using Vault.
 */
public class MCLib  {

    
    private Economy economy; // Instance of Economy



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

    /**mv
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
