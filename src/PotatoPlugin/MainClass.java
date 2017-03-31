package PotatoPlugin;

import PotatoBlocker.*;
import PotatoSwear.Commands.SwearFilterCommand;
import PotatoSwear.Handlers.FilterHandler;
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
        registerSwear();
        registerEvents();
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

    private void registerEvents()
    {
        pluginmanager pm = getServer().getPluginManager();
        pm.registerEvents(new FilterHandler(), this);
    }

    private void registerSwear() {
        getCommand("swearfilter").setExecutor(new SwearFilterCommand());

        File file = new File(getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/SwearList.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        if (myFile.contains("Swears")) {
            List<String> thing = myFile.getStringList("Swears");
            FilterHandler.swears = thing;
            System.out.println("[Better Swear Filter] Swear List Loaded!");
        } else {
            ArrayList<String> thing = new ArrayList();
            FilterHandler.swears = thing;
            System.out.println("[Better Swear Filter] No Swear List Found!");
        }
    }
}
