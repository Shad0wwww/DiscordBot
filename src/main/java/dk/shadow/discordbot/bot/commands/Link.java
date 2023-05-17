package dk.shadow.discordbot.bot.commands;

import dk.shadow.discordbot.Main;
import dk.shadow.discordbot.configuration.ConfigManager;
import dk.shadow.discordbot.data.PlayerData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;

public class Link extends ListenerAdapter {


    @Override

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild();

        String kode = event.getOption("kode").getAsString();
        if (event.getName().equals("link")) {
            PlayerData playerData = Main.getPlayerDataManager().verifyUser(
                    kode,
                    event.getMember().getUser().getAsTag(),
                    event.getMember().getUser().getId()
            );

            if (playerData == null) {
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(Color.RED);
                error.setDescription("Koden er ugyldig");
                event.replyEmbeds(error.build()).queue();
                return; // Return early to avoid further processing
            }

            Bukkit.broadcastMessage("Main.getPlayerDataManager().verifyUser(kode); -" + playerData.getDiscordId());
            Bukkit.broadcastMessage("Main.getPlayerDataManager().verifyUser(kode); -" + playerData.getDiscordUserName());
            Bukkit.broadcastMessage("Main.getPlayerDataManager().verifyUser(kode); -" + playerData.getUserName());
            //It's in a try, because if player is op, or user have the same role it's going to give an error.
            try {
                event.getMember().modifyNickname(playerData.getUserName()).queue();
            } catch (Exception e) {
                e.fillInStackTrace();
            }


            EmbedBuilder verify = new EmbedBuilder();
            verify.setColor(Color.GREEN);
            verify.setDescription("Du har nu verifyed med " + playerData.getUserName());
            event.replyEmbeds(verify.build()).queue();

            Player player = Bukkit.getPlayer(playerData.getUuid());
            try {
                ConfigManager.send(player, "messages.verify-med", "%player%", playerData.getUserName(), "%discord-username%", playerData.getDiscordUserName());
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

}
