package me.xmantic.tokens.tokenhandler;

import me.xmantic.tokens.TokensAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TokensEconomey implements Tokens {

    private TokensAPI plugin;

    public TokensEconomey(TokensAPI plugin) {
        this.plugin = plugin;
    }

    @Override
    public int tokenBalance(UUID playerUUID) {
        return plugin.tokenBalance.get(playerUUID);
    }

    @Override
    @Deprecated
    public int tokenBalance(Player player) {
        return plugin.tokenBalance.get(player.getUniqueId());
    }

    @Override
    @Deprecated
    public int tokenBalance(OfflinePlayer offlinePlayer) {
        return plugin.tokenBalance.get(offlinePlayer.getUniqueId());
    }

    @Override
    @Deprecated
    public int tokenBalance(String playerName) {
        return plugin.tokenBalance.get(Bukkit.getOfflinePlayer(playerName).getUniqueId());
    }

    @Override
    public boolean validInputCheck(int inputValue) {
        String string = "" + inputValue;
        if (string.contains(".") || string.contains(",") || string.contains("-")) {
            return false;
        }
        return true;
    }

    @Override
    @Deprecated
    public boolean validInputCheck(String inputValue) {
        if (inputValue.contains(".") || inputValue.contains(",") || inputValue.contains("-")) {
            return false;
        }
        try {
            Integer.parseInt(inputValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean hasEnoughBalance(int attemptingToSend, UUID playerUUID) {

        if (attemptingToSend > plugin.tokenBalance.get(playerUUID)) {
            return false;
        }
        return true;
    }

    @Override
    @Deprecated
    public boolean hasEnoughBalance(int attemptingToSend, Player player) {

        if (attemptingToSend > plugin.tokenBalance.get(player.getUniqueId())) {
            return false;
        }
        return true;
    }

    @Override
    @Deprecated
    public boolean hasEnoughBalance(int attemptingToSend, OfflinePlayer offlinePlayer) {

        if (attemptingToSend > plugin.tokenBalance.get(offlinePlayer.getUniqueId())) {
            return false;
        }
        return true;
    }

    @Override
    @Deprecated
    public boolean hasEnoughBalance(int attemptingToSend, String playerName) {

        if (attemptingToSend > plugin.tokenBalance.get(Bukkit.getOfflinePlayer(playerName).getUniqueId())) {
            return false;
        }
        return true;
    }

    @Override
    @Deprecated
    public boolean hasEnoughBalance(int attemptingToSend, int cuurentBalance) {
        if (attemptingToSend > cuurentBalance) {
            return false;
        }
        return true;
    }

    @Override
    public void payTokens(UUID sender, UUID target, int amount) {
        int updatedSenderBalance = tokenBalance(sender) - amount;
        int updatedTargetBalance = tokenBalance(target) + amount;
        plugin.tokenBalance.put(sender, updatedSenderBalance);
        plugin.tokenBalance.put(target, updatedTargetBalance);
    }

    @Override
    @Deprecated
    public void payTokens(Player sender, Player target, int amount) {
        int updatedSenderBalance = tokenBalance(sender.getUniqueId()) - amount;
        int updatedTargetBalance = tokenBalance(target.getUniqueId()) + amount;
        plugin.tokenBalance.put(sender.getUniqueId(), updatedSenderBalance);
        plugin.tokenBalance.put(target.getUniqueId(), updatedTargetBalance);
    }

    @Override
    @Deprecated
    public void payTokens(OfflinePlayer sender, OfflinePlayer target, int amount) {
        int updatedSenderBalance = tokenBalance(sender.getUniqueId()) - amount;
        int updatedTargetBalance = tokenBalance(target.getUniqueId()) + amount;
        plugin.tokenBalance.put(sender.getUniqueId(), updatedSenderBalance);
        plugin.tokenBalance.put(target.getUniqueId(), updatedTargetBalance);
    }

    @Override
    @Deprecated
    public void payTokens(String sender, String target, int amount) {
        UUID senderUUID = Bukkit.getOfflinePlayer(sender).getUniqueId();
        UUID targetUUID = Bukkit.getOfflinePlayer(target).getUniqueId();
        int updatedSenderBalance = tokenBalance(senderUUID) - amount;
        int updatedTargetBalance = tokenBalance(targetUUID) + amount;
        plugin.tokenBalance.put(senderUUID, updatedSenderBalance);
        plugin.tokenBalance.put(targetUUID, updatedTargetBalance);
    }

    @Override
    public void resetPlayerTokens(UUID playerUUID) {
        plugin.tokenBalance.put(playerUUID, plugin.getConfig().getInt("Default Balance"));
    }

    @Override
    @Deprecated
    public void resetPlayerTokens(Player player) {
        plugin.tokenBalance.put(player.getUniqueId(), plugin.getConfig().getInt("Default Balance"));
    }

    @Override
    @Deprecated
    public void resetPlayerTokens(OfflinePlayer offlinePlayer) {
        plugin.tokenBalance.put(offlinePlayer.getUniqueId(), plugin.getConfig().getInt("Default Balance"));
    }

    @Override
    @Deprecated
    public void resetPlayerTokens(String playerName){
        UUID uuid = Bukkit.getOfflinePlayer(playerName).getUniqueId();
        plugin.tokenBalance.put(uuid, plugin.getConfig().getInt("Default Balance"));
    }

    @Override
    public void clearAllTokenBalances() {
        for (UUID uuid : plugin.tokenBalance.keySet()) {
            plugin.tokenBalance.put(uuid, plugin.getConfig().getInt("Default Balance"));
        }
    }

    @Override
    public void giveTokens(UUID target, int amount) {
        int updatedBalance = tokenBalance(target) + amount;
        plugin.tokenBalance.put(target, updatedBalance);
    }

    @Override
    @Deprecated
    public void giveTokens(Player target, int amount) {
        int updatedBalance = tokenBalance(target.getUniqueId()) + amount;
        plugin.tokenBalance.put(target.getUniqueId(), updatedBalance);
    }

    @Override
    @Deprecated
    public void giveTokens(OfflinePlayer target, int amount) {
        int updatedBalance = tokenBalance(target.getUniqueId()) + amount;
        plugin.tokenBalance.put(target.getUniqueId(), updatedBalance);
    }

    @Override
    @Deprecated
    public void giveTokens(String target, int amount){
        UUID uuid = Bukkit.getOfflinePlayer(target).getUniqueId();
        int updatedBalance = tokenBalance(uuid) + amount;
        plugin.tokenBalance.put(uuid, updatedBalance);
    }


    @Override
    public void removeTokens(UUID target, int amount) {
        int updatedBalance = tokenBalance(target) - amount;
        plugin.tokenBalance.put(target, updatedBalance);
    }

    @Override
    @Deprecated
    public void removeTokens(Player target, int amount) {
        int updatedBalance = tokenBalance(target.getUniqueId()) - amount;
        plugin.tokenBalance.put(target.getUniqueId(), updatedBalance);
    }

    @Override
    @Deprecated
    public void removeTokens(OfflinePlayer target, int amount) {
        int updatedBalance = tokenBalance(target.getUniqueId()) - amount;
        plugin.tokenBalance.put(target.getUniqueId(), updatedBalance);
    }

    @Override
    @Deprecated
    public void removeTokens(String target, int amount){
        UUID uuid = Bukkit.getOfflinePlayer(target).getUniqueId();
        int updatedBalance = tokenBalance(uuid) - amount;
        plugin.tokenBalance.put(uuid, updatedBalance);
    }

    @Override
    public void setTokens(UUID target, int amount){
        plugin.tokenBalance.put(target, amount);
    }

    @Override
    @Deprecated
    public void setTokens(Player target, int amount){
        plugin.tokenBalance.put(target.getUniqueId(), amount);
    }

    @Override
    @Deprecated
    public void setTokens(OfflinePlayer target, int amount){
        plugin.tokenBalance.put(target.getUniqueId(), amount);
    }

    @Override
    @Deprecated
    public void setTokens(String target, int amount){
        plugin.tokenBalance.put(Bukkit.getOfflinePlayer(target).getUniqueId(), amount);
    }
}