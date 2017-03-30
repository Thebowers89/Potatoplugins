package PotatoPlugin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class VotePlugin
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
    }
}
