package me.xmantic.tokens.tokenhandler;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface Tokens {

    /**
     * Gets the Token Balance of a player
     */
    int tokenBalance(UUID playerUUID);

    @Deprecated
    int tokenBalance(Player player);

    @Deprecated
    int tokenBalance(OfflinePlayer offlinePlayer);

    @Deprecated
    int tokenBalance(String playerName);


    /**
     * Checks to make sure the input is a valid number that is not
     * a decimal, is not negative, and doesn't contain a comma
     */
    boolean validInputCheck(int inputValue);

    @Deprecated
    boolean validInputCheck(String inputValue);


    /**
     * This section is used in order to make sure that a player
     * has more tokens in their balance than they are trying
     * to send
     */
    boolean hasEnoughBalance(int attemptingToSend, UUID playerUUID);

    @Deprecated
    boolean hasEnoughBalance(int attemptingToSend, Player player);

    @Deprecated
    boolean hasEnoughBalance(int attemptingToSend, OfflinePlayer offlinePlayer);

    @Deprecated
    boolean hasEnoughBalance(int attemptingToSend, String playerName);

    @Deprecated
    boolean hasEnoughBalance(int attemptingToSend, int cuurentBalance);


    /**
     * Used to pay tokens from one person to another person
     */
    void payTokens(UUID sender, UUID target, int amount);

    @Deprecated
    void payTokens(Player sender, Player target, int amount);

    @Deprecated
    void payTokens(OfflinePlayer sender, OfflinePlayer target, int amount);

    @Deprecated
    void payTokens(String sender, String target, int amount);


    /**
     * Used to reset the TokensAPI balance of a single player
     */
    void resetPlayerTokens(UUID playerUUID);

    @Deprecated
    void resetPlayerTokens(Player player);

    @Deprecated
    void resetPlayerTokens(OfflinePlayer offlinePlayer);

    @Deprecated
    void resetPlayerTokens(String playerName);


    /**
     * Used to clear the TokensAPI Balances of ALL players on the network
     */
    void clearAllTokenBalances();


    /**
     * Used to give tokens to other players
     */
    void giveTokens(UUID target, int amount);

    @Deprecated
    void giveTokens(Player target, int amount);

    @Deprecated
    void giveTokens(OfflinePlayer target, int amount);

    @Deprecated
    void giveTokens(String target, int amount);


    /**
     * Used to remove tokens from another player
     */
    void removeTokens(UUID target, int amount);

    @Deprecated
    void removeTokens(Player target, int amount);

    @Deprecated
    void removeTokens(OfflinePlayer target, int amount);

    @Deprecated
    void removeTokens(String target, int amount);


    /**
     * This is used to set a players Tokens Balance
     * to whatever you want it to be as easily as
     * possible.
     */
    void setTokens(UUID target, int amount);

    @Deprecated
    void setTokens(Player target, int amount);

    @Deprecated
    void setTokens(OfflinePlayer target, int amount);

    @Deprecated
    void setTokens(String target, int amount);
}