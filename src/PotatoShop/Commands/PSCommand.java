package PotatoShop.Commands;

import PotatoShop.Inventories.IconEditor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PSCommand
        implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if ((sender instanceof Player)) {
            File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShops/PlayerShopData.yml");
            YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
            Player player = (Player) sender;
            if (myFile.contains("Player Shop." + player.getName())) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("icon")) {
                        player.openInventory(IconEditor.IE(player.getName()));
                        return true;
                    }
                    player.sendMessage(ChatColor.RED + "Invalid Syntax!");
                } else if (args.length > 1) {
                    if (args[0].equalsIgnoreCase("lore")) {
                        if (args.length - 3 <= 7) {
                            String lore = "";
                            for (int i = 1; i < args.length; i++) {
                                if (i == args.length - 1) {
                                    String arg = args[i];
                                    lore = lore + arg;
                                } else {
                                    String arg = args[i] + " ";
                                    lore = lore + arg;
                                }
                            }
                            ArrayList<String> lore1 = new ArrayList();
                            lore1.add(lore);
                            ItemStack item = myFile.getItemStack("Player Shop." + player.getName() + ".Item");
                            ItemMeta iMeta = item.getItemMeta();
                            iMeta.setLore(lore1);
                            item.setItemMeta(iMeta);
                            myFile.set("Player Shop." + player.getName() + ".Item", item);
                            try {
                                myFile.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            player.sendMessage("Lore changed successfully!");
                            return true;
                        }
                        player.sendMessage("Too many arguments!");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid Syntax!" + ChatColor.GREEN + "\nProper use: \n/ps icon \n/ps lore");
                }
            } else {
                player.sendMessage(ChatColor.RED + "ERROR: You do not have a sponsored shop!");
                return true;
            }
        }
        return false;
    }
}