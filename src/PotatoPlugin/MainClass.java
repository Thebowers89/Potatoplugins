package PotatoPlugin;

import PotatoBlocker.Blockone;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass
        extends JavaPlugin
{
    public void onEnable()
    {
        registerCommands();
    }

    public void onDisable() {}

    private void registerCommands()
    {
        getCommand("vote").setExecutor(new VoteCommand());
        getCommand("addurl").setExecutor(new AddUrlCommand());
        getCommand("plugins").setExecutor(new Blockone());
    }
}
