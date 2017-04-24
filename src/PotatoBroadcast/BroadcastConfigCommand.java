package PotatoBroadcast;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("configbroadcast") || commandLabel.equalsIgnoreCase("configb")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.isOp() || player.hasPermission("PBroadcast.Modify")) {
                    if (args.length > 1) {
                        String input = args[0];
                        if (input.equalsIgnoreCase("footer")) {
                            String footer = "";
                            for (int i = 1; i < args.length; i++) {
                                footer = footer + args[i] + " ";
                            }
                            Utils.setFooter(footer);
                            player.sendMessage(ChatColor.GREEN + "Footer set!");
                            return true;
                        } else if (input.equalsIgnoreCase("header")) {
                            String header = "";
                            for (int i = 1; i < args.length; i++) {
                                header = header + args[i] + " ";
                            }
                            Utils.setHeader(header);
                            player.sendMessage(ChatColor.GREEN + "Header set!");
                            return true;
                        } else if (input.equalsIgnoreCase("message")) {
                            String message = "";
                            for (int i = 1; i < args.length; i++) {
                                message = message + args[i] + " ";
                            }
                            Utils.setMessage(message);
                            player.sendMessage(ChatColor.GREEN + "Message Set!");
                            return true;
                        } else if (input.equalsIgnoreCase("interval")) {
                            Utils.setInterval(Integer.parseInt(args[1]));
                            player.sendMessage(ChatColor.GREEN + "Interval set!");
                            return true;
                        }
                    }
                    player.sendMessage(ChatColor.RED + "Invalid arguments!");
                    return true;
                }
                player.sendMessage(ChatColor.RED + "Insufficient Permissions!");
                return true;
            }
            sender.sendMessage("This command may only be executed by a player!");
            return true;
        }
        return false;
    }
}
