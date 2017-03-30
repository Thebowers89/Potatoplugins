package PotatoPlugin;

import PotatoBlocker.*;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {
    public void onEnable() {
        registerCommands();
    }

    public void onDisable() {

    }

    private void registerCommands() {
        registerBlocker();
        registerVote();
    }

    private void registerVote() {
        getCommand("vote").setExecutor(new VoteCommand());
        getCommand("addurl").setExecutor(new AddUrlCommand());
    }

    private void registerBlocker() {
        getCommand("plugins").setExecutor(new Blockone());
        getCommand("plugin").setExecutor(new Blocktwo());
        getCommand("pl").setExecutor(new Blockthree());
        getCommand("?").setExecutor(new Blockfour());
    }

}
