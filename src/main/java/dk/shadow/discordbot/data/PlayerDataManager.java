package dk.shadow.discordbot.data;

import dk.shadow.discordbot.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {
    public HashMap<UUID, PlayerData> playerDataMap = new HashMap<>();

    public PlayerData createUser(UUID uuid, String kode, String username) {
        if (playerDataMap.containsKey(uuid)) {
            return playerDataMap.get(uuid);
        }
        PlayerData playerData = new PlayerData(uuid, kode, username);
        playerDataMap.put(uuid, playerData);
        return playerData;
    }

    public PlayerData getUser(UUID uuid) {
        if (playerDataMap.containsKey(uuid)) {
            return this.playerDataMap.get(uuid);
        }
        return null;
    }



    public PlayerData verifyUser(String kode, String user, String ID) {
        for (Map.Entry<UUID, PlayerData> entry : playerDataMap.entrySet()) {
            PlayerData playerData = entry.getValue();
            if (playerData.getKode().equals(kode)) {
                playerDataMap.remove(entry.getKey());

                playerData.setDiscordUserName(user);
                playerData.setDiscordId(ID);

                // Add the updated playerData back to the map
                playerDataMap.put(entry.getKey(), playerData);

                return playerData;
            }
        }
        return null;
    }

    //IF THE PLAYER IS VERIFIED FUNCTION
    public Boolean isVerified(UUID uuid) {
        if (playerDataMap.containsKey(uuid)) {
            String discordId = this.playerDataMap.get(uuid).getDiscordId();
            return discordId != null;
        }
        return false;
    }


    public void saveUsers() {
        Main.verifyYML.set("users", null);
        Main.verify.saveConfig();

        System.out.println("this.playerDataMap.values() - " + this.playerDataMap.values());
        System.out.println("this.playerDataMap.isEmpty() - " + this.playerDataMap.isEmpty());

        //If PLAYERDATAMAP is EMPTY
        if (this.playerDataMap.isEmpty()) return;
        ConfigurationSection usersSection = Main.verifyYML.createSection("users");
        System.out.println("usersSection - " + usersSection);
        for (Map.Entry<UUID, PlayerData> entry : playerDataMap.entrySet()) {
            UUID uuid = entry.getKey();
            PlayerData playerData = entry.getValue();
            System.out.println("uuid - " + uuid);
            ConfigurationSection userSection = usersSection.createSection(uuid.toString());
            userSection.set("username", playerData.getUserName());
            userSection.set("discord_username", playerData.getDiscordUserName());
            userSection.set("discordid", playerData.getDiscordId());

        }
        Main.verify.saveConfig();
    }
}
