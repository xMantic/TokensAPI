package me.xmantic.tokens.listeners;

import me.xmantic.tokens.TokensAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class LoginListeners implements Listener {

    private TokensAPI plugin;

    public LoginListeners(TokensAPI plugin) {
        this.plugin = plugin;
    }

    public FileConfiguration playerData;
    private File serverFolder;
    public File playerFile;

    public void loadConfig(UUID uuid) {
        serverFolder = new File(plugin.getDataFolder(), File.separator + "PlayerData");
        playerFile = new File(serverFolder, File.separator + uuid + ".yml");
        playerData = YamlConfiguration.loadConfiguration(playerFile);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent event) {

        Player player = event.getPlayer();
        loadConfig(player.getUniqueId());

        if (playerFile.exists()) {
            return;
        }
        try {
            playerData.createSection("tokens");
            playerData.set("tokens.balance", plugin.getConfig().getInt("Default Balance"));
            playerData.save(playerFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @EventHandler
    public void on(PlayerLoginEvent event) {
        if (!(plugin.tokenBalance.isEmpty())) {
            return;
        }
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {

            loadConfig(offlinePlayer.getUniqueId());

            if (!(playerFile.exists())) {
                try {
                    playerData.createSection("tokens");
                    playerData.set("tokens.balance", plugin.getConfig().getInt("Default Balance"));
                    playerData.save(playerFile);
                } catch (IOException exception) {
                    exception.printStackTrace();
                    continue;
                }
            }

            plugin.tokenBalance.put(offlinePlayer.getUniqueId(), playerData.getInt("tokens.balance"));
            plugin.changeCheck.put(offlinePlayer.getUniqueId(), playerData.getInt("tokens.balance"));
        }
    }
}
