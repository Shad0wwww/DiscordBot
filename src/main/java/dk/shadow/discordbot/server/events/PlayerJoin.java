package dk.shadow.discordbot.server.events;

import dk.shadow.discordbot.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(Main.verify.getConfig().contains("Data."+event.getPlayer().getUniqueId().toString())){
            Main.instance.verifiedmembers.add(event.getPlayer().getUniqueId());
        }
    }


}
