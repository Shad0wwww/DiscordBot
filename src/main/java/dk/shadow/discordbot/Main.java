package dk.shadow.discordbot;

import dk.shadow.discordbot.bot.events.ReadyEvent;
import dk.shadow.discordbot.utils.Config;
import lombok.Getter;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    public static Config config;
    public static FileConfiguration configYML;
    public static Main instance;

    @Getter
    private JDA discordBot;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;



        //CONFIGS -------------------------------
        //Config.yml
        if (!(new File(getDataFolder(), "config.yml")).exists())
            saveResource("config.yml", false);

        config = new Config(this, null, "config.yml");
        configYML = config.getConfig();

        String discordToken = config.getConfig().getString("DiscordBot.Token");

        if (discordToken == null) {
            getLogger().severe("Please provide a Token in the config.yml file.");
            return;
        }

        // Note: It is important to register your ReadyListener before building
        this.discordBot = JDABuilder.createDefault(discordToken)
                .addEventListeners(new ReadyEvent())
                .build();

        // optionally block until JDA is ready
        discordBot.awaitReady();

    }
    @Override
    public void onDisable() {
        discordBot.shutdownNow();
    }

    public static Main getInstace() {
        return instance;
    }
}