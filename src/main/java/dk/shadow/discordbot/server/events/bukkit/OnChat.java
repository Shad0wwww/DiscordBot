package dk.shadow.discordbot.server.events.bukkit;

import dk.shadow.discordbot.Main;
import dk.shadow.discordbot.configuration.ConfigManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class OnChat implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        TextChannel textChannel = Main.getInstace().getDiscordBot().getTextChannelById(ConfigManager.get("logs.ingame-chat-logs-channel")[0]);

        EmbedBuilder error = new EmbedBuilder();
        error.setColor(Color.RED);
        error.setDescription(ConfigManager.get("ingame-logs-message", "%player%", event.getPlayer().getDisplayName(), "%message%", event.getMessage())[0]);


        assert textChannel != null;
        textChannel.sendMessageEmbeds(error.build()).queue();

    }
}
