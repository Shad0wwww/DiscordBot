package dk.sdhadow.discordbot;

import dk.sdhadow.discordbot.utils.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;

public class Main extends JavaPlugin {

    public static Config config;
    public static FileConfiguration configYML;
    public static Main instance;
    public JDA jda;
    public void onEnable() {
        instance = this;

        //CONFIGS -------------------------------
        //Config.yml
        if (!(new File(getDataFolder(), "config.yml")).exists())
            saveResource("config.yml", false);

        config = new Config(this, null, "config.yml");
        configYML = config.getConfig();


        try {


            jda = JDABuilder.createLight(config.getConfig().getString("DiscordBot.Token"))
                    .setStatus(OnlineStatus.ONLINE)
                    .setActivity(Activity.streaming(config.getConfig().getString("DiscordBot.Spiller"), "https://www.twitch.tv/jannick_05"))
                    .setMemberCachePolicy(MemberCachePolicy.ONLINE)
                    .setChunkingFilter(ChunkingFilter.ALL)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
                    .enableCache(CacheFlag.ACTIVITY)
                    .enableCache(CacheFlag.EMOTE)
                    .build()
                    .awaitReady();


        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void onDisable() {

    }

    public static Main getInstace() {
        return instance;
    }
}
