package dk.shadow.discordbot.server.commands.ingamecommands.subs;


import dk.shadow.discordbot.Main;
import dk.shadow.discordbot.server.commands.ISubCommand;
import dk.shadow.discordbot.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadSub extends ISubCommand {
    public ReloadSub() {
        super("reload");
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, String[] args, String paramString) {
        //Checks, if player have permission.
        if (!sender.hasPermission("buycraft.reload")) {
            sender.sendMessage(ColorUtils.getColored("&cDu har ikke adgang."));
            return;
        }

        sender.sendMessage(ColorUtils.getColored("&fStarted reloading yml"));
        try {
            Main.config.reloadConfig();
            sender.sendMessage(ColorUtils.getColored("&fSuccelsfull reloaded yml"));
        } catch (Exception e) {
            sender.sendMessage(ColorUtils.getColored("&cDer skete en fejl under reload"));
            e.printStackTrace();
        }

    }
}
