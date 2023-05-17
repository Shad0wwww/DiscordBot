package dk.shadow.discordbot.server.commands;




import dk.shadow.discordbot.server.commands.ingamecommands.VerifyCommands;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {
    public static void initialise(JavaPlugin instance) {
        new VerifyCommands(instance, "verify");

    }
}