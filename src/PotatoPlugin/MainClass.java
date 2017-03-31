package PotatoPlugin;

import PotatoBlocker.*;
import PotatoSwear.Commands.SwearFilterCommand;
import PotatoSwear.Handlers.FilterHandler;
import PotatoVote.AddUrlCommand;
import PotatoVote.VoteCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainClass extends JavaPlugin {

    public void onEnable() {
        registerCommands();
        registerEvents();
        registerSwears();
    }

    public void onDisable() {

    }

    public void registerSwears() {
        File file = new File(getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/SwearList.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        if (myFile.contains("Swears"))
        {
            List<String> thing = myFile.getStringList("Swears");
            FilterHandler.swears = thing;
            System.out.println("[PotatoSwear] Swear List Loaded!");
        }
        else
        {
            ArrayList<String> thing = new ArrayList();
            FilterHandler.swears = thing;
            System.out.println("[PotatoSwear] No Swear List Found!");
        }
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
