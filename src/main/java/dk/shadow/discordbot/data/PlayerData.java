package dk.shadow.discordbot.data;

import java.util.UUID;

public class PlayerData {

    UUID uuid;
    String UserName;

    String DiscordUserName;
    String DiscordId;
    String kode;

    public PlayerData(UUID uuid, String Kode, String userName) {
        this.uuid = uuid;
        this.kode = Kode;
        this.UserName = userName;
    }

    public PlayerData(UUID uuid, String UserName, String DiscordUserName, String discordId) {
        this.uuid = uuid;
        this.UserName = UserName;
        this.DiscordUserName = DiscordUserName;
        this.DiscordId = discordId;
    }



    public UUID getUuid() {
        return this.uuid;
    }public String getKode() {
        return this.kode;
    }
    public String getUserName() {
        return this.UserName;
    }
    public String getDiscordUserName() {
        return this.DiscordUserName;
    }
    public String getDiscordId() {
        return this.DiscordId;
    }


    public void setDiscordUserName(String discordUserName) {
        this.DiscordUserName = discordUserName;
    }

    public void setDiscordId(String discordId) {
        this.DiscordId = discordId;
    }

}
