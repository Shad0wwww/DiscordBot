package dk.shadow.discordbot.server.events;

import dk.shadow.discordbot.server.events.bukkit.OnChat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Instalize {

    public static void event(JavaPlugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new OnChat(), plugin);
    }
}
