package PotatoPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class AddUrlCommand
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (((sender instanceof Player)) && (
                (commandLabel.equalsIgnoreCase("addurl")) || (sender.isOp())))
        {
            Player player = (Player)sender;
            File file = new File(Bukkit.getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/URLConfig.yml");
            YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
            List<String> list = myFile.getStringList("Link List");
            if (args.length == 1)
            {
                String link = args[0];
                list.add(link);
                myFile.set("Link List", list);
                try
                {
                    myFile.save(file);
                    player.sendMessage("Updated URL list!");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                //hi
                return true;
            }
            player.sendMessage("Usage: \n /addurl <url>");
        }
        return false;
    }
}
