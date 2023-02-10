package dk.shadow.discordbot.server.events;

import dk.shadow.discordbot.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Main.instance.verifiedmembers.remove(event.getPlayer().getUniqueId());
        Main.instance.uuidCodeMap.remove(event.getPlayer().getUniqueId());
        Main.instance.uuidIdMap.remove(event.getPlayer().getUniqueId());

    }

}
