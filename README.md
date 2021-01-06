# TokensAPI
This is a simple API so that people are able to work with any of my plugins that use Tokens!

If you would like to make a plugin using this Tokens API here are the following steps to do so.

1. Download this jar and mark it as a dependcy (An example of how I did it is below)
```
        <dependency>
            <groupId>me.xmantic</groupId>
            <artifactId>Tokens</artifactId>
            <version>1.0</version>
            <scope>system</scope>
            <systemPath>
                C:/Users/xMantic/Desktop/JarFiles/TokensAPI-1.0.jar
            </systemPath>
        </dependency>
```
        
2. In your Main class for the plugin that you are making make sure to include the following

```
    private Tokens tokens;

    @Override
    public void onEnable() {
        this.tokens = new TokensEconomey(TokensEco.getPlugin(TokensAPI.class));
        /*
        Anything else in your onEnable
        */
    }
```
3. If you want to call any of the methods just use tokens.<method>()

Example plugin (In one class so I dont have to paste as much)
```
package me.xmantic.exampleplugin;

import me.xmantic.tokens.TokensAPI;
import me.xmantic.tokens.tokenhandler.Tokens;
import me.xmantic.tokens.tokenhandler.TokensEconomey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Example extends JavaPlugin implements Listener {

    private Tokens tokens;

    @Override
    public void onEnable() {
        this.tokens = new TokensEconomey(TokensEco.getPlugin(TokensAPI.class));
        this.getServer().getPluginManager().registerEvents(this,this);
    }

    //This example plugin just gives players 100 tokens every time they die and DMs them the balance
    @EventHandler
    public void on(PlayerDeathEvent event){
        Player player = event.getEntity();
        tokens.giveTokens(player.getUniqueId(), 100);
        player.sendMessage("Bal: " + tokens.tokenBalance(player.getUniqueId()));
    }
}
```
