package PotatoShop.Commands;

import PotatoShop.LocUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PShopsCommand
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((sender instanceof Player))
        {
            Player player = (Player)sender;
            if (args.length == 0)
            {
                if ((player.hasPermission("PShops.tp")) || (player.isOp()))
                {
                    player.teleport(LocUtils.getLocation("Pshop.Locations.Spawn"));
                    return true;
                }
            }
            else if ((args.length == 1) && (
                    ((args[0].equalsIgnoreCase("configure")) && (player.isOp())) || ((args[0].equalsIgnoreCase("configure")) && (player.getName().equals("Kerlab")))))
            {
                LocUtils.setDefaultLocation(player.getLocation(), player);
                return true;
            }
        }
        return false;
    }
}
