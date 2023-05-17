package dk.shadow.discordbot.configuration;

import dk.shadow.discordbot.Main;
import dk.shadow.discordbot.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class ConfigManager {

    static HashMap<String, String[]> messages;

    public void loadALl() {
        messages = (HashMap)new HashMap<>();
        for (String path : Main.configYML.getKeys(true)) {
            if (!Main.configYML.isConfigurationSection(path)) {
                if (Main.configYML.getStringList(path) != null && Main.configYML.isList(path)) {
                    System.out.print("PATH: " + path);
                    List<String> stringList = ColorUtils.getColored(Main.configYML.getStringList(path));
                    messages.put(path, stringList.<String>toArray(new String[0]));
                    continue;
                }
                if (Main.configYML.getString(path) != null) {
                    System.out.print("PATH: " + path);
                    List<String> stringList = Collections.singletonList(ColorUtils.getColored(Main.configYML.getString(path)));
                    messages.put(path, stringList.<String>toArray(new String[0]));
                }
            }
        }
    }

    public static String[] get(String path) {
        if (messages.containsKey(path))
            return messages.get(path);
        return new String[] { "" };
    }

    public static String[] get(String path, String... replacements) {
        if (ConfigManager.messages.containsKey(path)) {
            String[] messages = get(path);
            List<String> messageList = new ArrayList<>();
            for (String message : messages) {
                for (int i = 0; i < replacements.length; i += 2)
                    message = message.replaceAll(replacements[i], ColorUtils.getColored(replacements[i + 1]));
                messageList.add(message);
            }
            return messageList.<String>toArray(new String[0]);
        }
        return new String[] { "" };
    }

    public static void send(CommandSender player, String path) {
        player.sendMessage(get(path));
    }

    public static void send(CommandSender player, String path, String... replacements) {
        String[] messages = get(path);
        for (String message : messages) {
            for (int i = 0; i < replacements.length; i += 2)
                message = message.replaceAll(replacements[i], ColorUtils.getColored(replacements[i + 1]));
            player.sendMessage(message);
        }
    }

}
