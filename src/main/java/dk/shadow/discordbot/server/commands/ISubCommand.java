package dk.shadow.discordbot.server.commands;

import org.bukkit.command.CommandSender;

public abstract class ISubCommand {
    private final String name;

    private final String[] aliases;

    public ISubCommand(String name) {
        this.name = name;
        this.aliases = new String[0];
    }

    public ISubCommand(String name, String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public String getName() {
        return this.name;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public abstract void onCommand(CommandSender paramCommandSender, String[] paramArrayOfString, String paramString);
}
