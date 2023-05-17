package dk.shadow.discordbot.server.commands.ingamecommands;



import dk.shadow.discordbot.Main;
import dk.shadow.discordbot.configuration.ConfigManager;
import dk.shadow.discordbot.server.commands.ISubCommand;
import dk.shadow.discordbot.utils.GenerateKode;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class DefaultCommand extends ISubCommand {

    public DefaultCommand() {
        super("default");
    }



    @Override
    public void onCommand(CommandSender sender, String[] args, String label) {
        Player player = (Player) sender;
        String kode = GenerateKode.kode();

        Main.getPlayerDataManager().createUser(player.getUniqueId(), kode, player.getName());
        //ConfigManager.send(sender, "messages.verify-message", "%kode%", kode);

        TextComponent subComponent = new TextComponent(Arrays.toString(ConfigManager.get("messages.verify-message", "%kode%", kode)));

        subComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Klik her for at få koden").create()));

        subComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, kode));

        player.spigot().sendMessage(subComponent);
    }


}
