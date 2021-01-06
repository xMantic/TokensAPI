package me.xmantic.tokens;

import me.xmantic.tokens.listeners.LoginListeners;
import me.xmantic.tokens.tokenhandler.Tokens;
import me.xmantic.tokens.tokenhandler.TokensEconomey;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;


public final class TokensAPI extends JavaPlugin {

    public HashMap<UUID, Integer> tokenBalance;
    private LoginListeners loginListeners;
    public HashMap<UUID, Integer> changeCheck;

    @Override
    public void onEnable() {

        createFiles();

        this.getServer().getPluginManager().registerEvents(new LoginListeners(this), this);
        this.loginListeners = new LoginListeners(this);
        this.tokenBalance = new HashMap<>();
        this.changeCheck = new HashMap<>();

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (UUID uuid : tokenBalance.keySet()) {

                if (tokenBalance.get(uuid).equals(changeCheck.get(uuid))) {
                    continue;
                }
                try {
                    loginListeners.loadConfig(uuid);
                    loginListeners.playerData.set("tokens.balance", tokenBalance.get(uuid));
                    loginListeners.playerData.save(loginListeners.playerFile);
                    changeCheck.put(uuid, tokenBalance.get(uuid));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

        }, 0, this.getConfig().getInt("Save Timer") * 1200);

    }

    @Override
    public void onDisable() {
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {

            loginListeners.loadConfig(offlinePlayer.getUniqueId());

            if (!(loginListeners.playerFile.exists())) {
                try {
                    loginListeners.playerData.createSection("tokens");
                    loginListeners.playerData.set("tokens.balance", this.getConfig().getInt("Default Balance"));
                    loginListeners.playerData.save(loginListeners.playerFile);
                } catch (IOException exception) {
                    exception.printStackTrace();
                    continue;
                }
            }
            try {
                loginListeners.playerData.set("tokens.balance", tokenBalance.get(offlinePlayer.getUniqueId()));
                loginListeners.playerData.save(loginListeners.playerFile);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void createFiles() {
        File configf = new File(getDataFolder(), "config.yml");

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        FileConfiguration config = new YamlConfiguration();

        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
