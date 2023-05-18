package dk.shadow.discordbot.bot.commands;

import dk.shadow.discordbot.Main;
import dk.shadow.discordbot.configuration.ConfigManager;
import dk.shadow.discordbot.data.PlayerData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class Link extends ListenerAdapter {


    @Override

    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild();

        String kode = event.getOption("kode").getAsString();
        if (event.getName().equals("link")) {
            PlayerData playerData = Main.getPlayerDataManager().verifyUser(
                    kode,
                    event.getMember().getUser().getAsTag(),
                    event.getMember().getUser().getId()
            );

            assert guild != null;
            if (guild.equals(ConfigManager.get("verify.verify-channel")[0])) {
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(Color.RED);
                error.setDescription("Du skal være i verify #"+ ConfigManager.get("verify.verify-channel")[0]);
                event.replyEmbeds(error.build()).setEphemeral(true).queue();
                return; // Return early to avoid further processing
            }

            if (playerData == null) {
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(Color.RED);
                error.setDescription("Koden er ugyldig");
                event.replyEmbeds(error.build()).setEphemeral(true).queue();
                return; // Return early to avoid further processing
            }

            if (Main.getPlayerDataManager().isVerified(event.getId())) {
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(Color.RED);
                error.setDescription("Du er allerede verifyed");
                event.replyEmbeds(error.build()).setEphemeral(true).queue();
                return; // Return early to avoid further processing
            }

            Bukkit.broadcastMessage("Main.getPlayerDataManager().verifyUser(kode); -" + playerData.getDiscordId());
            Bukkit.broadcastMessage("Main.getPlayerDataManager().verifyUser(kode); -" + playerData.getDiscordUserName());
            Bukkit.broadcastMessage("Main.getPlayerDataManager().verifyUser(kode); -" + playerData.getUserName());
            //It's in a try, because if player is op, or user have the same role it's going to give an error.
            try {
                event.getMember().modifyNickname(playerData.getUserName()).queue();
                //ADDING THE ROLE
                Role verifyRole = Objects.requireNonNull(event.getGuild()).getRoleById(ConfigManager.get("verify.verify-role-id")[0]);
                event.getMember().getRoles().add(verifyRole);
            } catch (Exception e) {
                e.fillInStackTrace();
            }

            //VERIFY EMBED
            EmbedBuilder verify = new EmbedBuilder();
            verify.setColor(Color.GREEN);
            verify.setTitle("⚙️ | KONTO SYSTEM");
            verify.setThumbnail("https://crafatar.com/avatars/"+playerData.getUuid()+"?size=125&helm");
            verify.setDescription(":white_check_mark: Du har nu verifyed dig med **"+playerData.getUserName()+"**");
            verify.setFooter(playerData.getDiscordUserName(), event.getMember().getAvatarUrl());
            event.replyEmbeds(verify.build())
                    .setEphemeral(true)
                    .queue();

            //SENDING THE VERIFY MESSAGE INGAME
            Player player = Bukkit.getPlayer(playerData.getUuid());
            try {
                ConfigManager.send(player, "messages.verify-med", "%player%", playerData.getUserName(), "%discord-username%", playerData.getDiscordUserName());
            } catch (Exception e) {
                e.fillInStackTrace();
            }



        }
    }

}
