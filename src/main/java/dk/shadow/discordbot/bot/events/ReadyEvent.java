package dk.shadow.discordbot.bot.events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;


public class ReadyEvent implements EventListener {

    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent) {
            System.out.println("Bot is now online!");
        }
    }
}
