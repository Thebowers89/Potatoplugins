package PotatoBlocker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Blocktwo
        implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ((commandSender instanceof Player)) {
            Player player = (Player) commandSender;
            if ((!player.hasPermission("pblocker.plugin")) && (player.isOp())) {
            }
            player.sendMessage(ChatColor.YELLOW + "Potatoes don't like stalkers, even if you have permission.");
        }
        return false;
    }
}
