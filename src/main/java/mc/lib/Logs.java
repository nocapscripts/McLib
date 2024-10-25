package mc.lib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logs {
    // Enhanced prefix with a space and consistent formatting
    private static final String PREFIX = ChatColor.BLUE + "[ " + ChatColor.AQUA + "MCLib " + ChatColor.BLUE + "] " + ChatColor.RESET;

    // Sends a green start notification to the console
    public static void sendStartNotify(String message) {
        sendMessage(message, ChatColor.GREEN);
    }

   
    public static void sendError(String message) {
        sendMessage(message, ChatColor.RED);
    }

    // Sends a warning message (in yellow/orange) to the console
    public static void sendWarning(String message) {
        sendMessage(message, ChatColor.GOLD);
    }

    // Sends an informational message (in white) to the console
    public static void sendInfo(String message) {
        sendMessage(message, ChatColor.WHITE);
    }

    public static void sendSevere(String message) {
        sendMessage(message, ChatColor.RED);
    }

    // Private method to format and send the message
    private static void sendMessage(String message, ChatColor color) {
        String formattedMessage = PREFIX + color + message;
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', formattedMessage));
    }
}