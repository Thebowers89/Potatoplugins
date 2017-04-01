package PotatoShop.Commands;

import PotatoShop.Inventories.PShopMenu;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PotatoShopMenuCommand
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((sender instanceof Player))
        {
            File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopConfig.yml");
            YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);

            Player player = (Player)sender;
            if (((commandLabel.equalsIgnoreCase("pshopmenu")) || (commandLabel.equalsIgnoreCase("psm"))) &&
                    (player.hasPermission("PShops.tp")))
            {
                if (player.getLocation().getWorld().getName().equals(myFile.getString("Pshop.Locations.Spawn.World")))
                {
                    player.openInventory(PShopMenu.PShopInv());
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You need to be in the Player Shop world to open the menu!");
                return true;
            }
        }
        return false;
    }
}
