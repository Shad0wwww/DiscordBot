package dk.shadow.discordbot;

import dk.shadow.discordbot.bot.events.ReadyEvent;
import dk.shadow.discordbot.bot.events.commands.Avatar;
import dk.shadow.discordbot.utils.Config;
import lombok.Getter;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class Main extends JavaPlugin {

    public static Config config, verify;
    public static FileConfiguration configYML, verifyYML;
    public static Main instance;

    public HashMap<UUID,String>uuidCodeMap;
    public HashMap<UUID,String> uuidIdMap;
    public List<UUID> verifiedmembers;

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

        if (!(new File(getDataFolder(), "verify.yml")).exists())
            saveResource("verify.yml", false);

        config = new Config(this, null, "config.yml");
        configYML = config.getConfig();

        verify = new Config(this, null, "verify.yml");
        verifyYML = verify.getConfig();

        String discordToken = config.getConfig().getString("DiscordBot.Token");

        if (discordToken == null) {
            getLogger().severe("Please provide a Token in the config.yml file.");
            return;
        }

        // Note: It is important to register your ReadyListener before building
        this.discordBot = JDABuilder.createDefault(discordToken)
                .addEventListeners(new ReadyEvent())
                .addEventListeners(new Avatar())
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
                //.setStatus(OnlineStatus.valueOf(config.getConfig().getString("DiscordBot.Spiller", "Gaymer")))
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
                .build();

        // optionally block until JDA is ready
        discordBot.awaitReady();
        Guild guild = discordBot.getGuildById(config.getConfig().getString("DiscordBot.Guild"));
        discordBot.updateCommands().queue();

        uuidCodeMap = new HashMap<>();
        uuidIdMap = new HashMap<>();
        verifiedmembers = new ArrayList<>();

        if (guild != null) {
            guild.updateCommands()
                .addCommands(Commands.slash("avatar", "Få en persons avatar").addOption(OptionType.USER, "person", "Hvis avatar vil du have")
                )
                .queue();




        } else {
            getLogger().severe("Please provide a guild in the config.yml file.");
            return;
        }

    }
    @Override
    public void onDisable() {
        discordBot.shutdownNow();
    }

    public static Main getInstace() {
        return instance;
    }
}