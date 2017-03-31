package PotatoVote;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class VoteCommand
        implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (((sender instanceof Player)) &&
                (commandLabel.equalsIgnoreCase("vote"))) {//
            Player player = (Player) sender;
            File file = new File(Bukkit.getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/URLConfig.yml");
            YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
            List<String> list = myFile.getStringList("Link List");
            if (list.size() == 0) {
                player.sendMessage(ChatColor.RED + "No links have been set, bug Femo to add some.");
                return true;
            }
            player.sendMessage("List of available voting links:");
            for (String key : list) {
                player.sendMessage(ChatColor.AQUA + key);
            }
            return true;
        }
        return false;
    }
}
