package dk.shadow.discordbot.bot.events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.bukkit.Bukkit;


public class ReadyEvent implements EventListener {

    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("Bot is now online!");
            Bukkit.broadcastMessage("Bot is now online!");

    }
}
