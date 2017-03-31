package PotatoPlugin;

import PotatoBlocker.*;
import PotatoSwear.*;
import PotatoSwear.Commands.SwearFilterCommand;
import PotatoSwear.Handlers.FilterHandler;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {
    public void onEnable() {
        registerCommands();
        registerEvents();
    }

    public void onDisable() {

    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new FilterHandler(), this);
    }

    private void registerCommands() {
        registerBlocker();
        registerVote();
        registerSwear();
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

    private void registerSwear() {
        getCommand("swearfilter").setExecutor(new SwearFilterCommand(this));

    }
}
