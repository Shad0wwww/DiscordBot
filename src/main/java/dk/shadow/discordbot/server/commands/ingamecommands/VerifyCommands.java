package dk.shadow.discordbot.server.commands.ingamecommands;



import dk.shadow.discordbot.server.commands.ICommand;
import dk.shadow.discordbot.server.commands.ISubCommand;
import dk.shadow.discordbot.server.commands.ingamecommands.subs.ReloadSub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class VerifyCommands extends ICommand {

    public VerifyCommands(JavaPlugin plugin, String command) {
        super(plugin, command);

        setDefaultCommand(new DefaultCommand());
        addSubCommands(
                new ReloadSub()
        );

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 0 && getDefaultCommand() != null) {
            execute(sender, getDefaultCommand(), args);
        } else if (args.length > 0) {
            ISubCommand subCommand = findSubCommand(args[0]);
            if (subCommand != null) {
                execute(sender, subCommand, args);
            }
            return true;
        }
        return false;
    }
}
