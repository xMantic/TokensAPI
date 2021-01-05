package me.xmantic.tokens.listeners;

import me.xmantic.tokens.TokensPlugin;
import me.xmantic.tokens.tokenhandler.TokenEconomey;
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

    private TokensPlugin plugin;
    public LoginListeners(TokensPlugin plugin) {
        this.plugin = plugin;
    }

    //------File loading method------
    public FileConfiguration playerData;
    public File serverFolder;
    public File playerFile;
    public void loadConfig(UUID uuid){
        serverFolder = new File(plugin.getDataFolder(), File.separator + "PlayerData");
        playerFile = new File(serverFolder, File.separator + uuid + ".yml");
        playerData = YamlConfiguration.loadConfiguration(playerFile);
    }

    //------Checks to see if player needs a file made------
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent event) {

        Player player = event.getPlayer();
        loadConfig(player.getUniqueId());

        if (playerFile.exists()) {
            return;
        } try {
            playerData.createSection("tokens");
            playerData.set("tokens.balance", plugin.getConfig().getInt("Default Balance"));
            playerData.save(playerFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
