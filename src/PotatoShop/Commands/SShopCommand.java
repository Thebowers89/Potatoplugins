package PotatoShop.Commands;

import PotatoShop.Inventories.IconEditor;
import PotatoShop.LocUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SShopCommand
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((sender instanceof Player))
        {
            Player player = (Player)sender;
            if (player.hasPermission("pshops.sshop"))
            {
                if (args.length == 1)
                {
                    File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShops/PlayerShopData.yml");
                    YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
                    String name = args[0];

                    myFile.set("Player Shop." + name + ".Item", defaultItem(name));
                    try
                    {
                        myFile.save(file);
                        player.sendMessage("Successfully saved the player file");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    LocUtils.setPShopLocation(player.getLocation(), name, player);
                    return true;
                }
                if (args.length == 3)
                {
                    File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShops/PlayerShopData.yml");
                    YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
                    if (args[0].equalsIgnoreCase("edit"))
                    {
                        if (myFile.contains("Player Shop." + args[1]))
                        {
                            if (args[2].equalsIgnoreCase("icon"))
                            {
                                player.openInventory(IconEditor.IE(args[1]));
                                return true;
                            }
                            if (args[2].equalsIgnoreCase("setloc"))
                            {
                                LocUtils.setPShopLocation(player.getLocation(), args[1], player);
                                return true;
                            }
                            if (args[2].equalsIgnoreCase("delete"))
                            {
                                myFile.set("Player Shop." + args[1], null);
                                try
                                {
                                    myFile.save(file);
                                    player.sendMessage(args[1] + "'s shop has been deleted!");
                                }
                                catch (IOException e)
                                {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                            player.sendMessage("Invalid Syntax!");
                            return true;
                        }
                        player.sendMessage(args[1] + " does not have a shop!");
                        return true;
                    }
                    player.sendMessage("Invalid Syntax!");
                    return true;
                }
                if (args.length > 3)
                {
                    if (args[0].equalsIgnoreCase("edit"))
                    {
                        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShops/PlayerShopData.yml");
                        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
                        if (myFile.contains("Player Shop." + args[1]))
                        {
                            if (args[2].equalsIgnoreCase("lore"))
                            {
                                System.out.println("Total Length: " + args.length);
                                System.out.println("Lore length: " + (args.length - 3));
                                if (args.length - 3 <= 7)
                                {
                                    String lore = "";
                                    for (int i = 3; i < args.length; i++) {
                                        if (i == args.length - 1)
                                        {
                                            String arg = args[i];
                                            lore = lore + arg;
                                        }
                                        else
                                        {
                                            String arg = args[i] + " ";
                                            lore = lore + arg;
                                        }
                                    }
                                    ArrayList<String> lore1 = new ArrayList();
                                    lore1.add(lore);
                                    ItemStack item = myFile.getItemStack("Player Shop." + args[1] + ".Item");
                                    ItemMeta iMeta = item.getItemMeta();
                                    iMeta.setLore(lore1);
                                    item.setItemMeta(iMeta);
                                    myFile.set("Player Shop." + args[1] + ".Item", item);
                                    try
                                    {
                                        myFile.save(file);
                                    }
                                    catch (IOException e)
                                    {
                                        e.printStackTrace();
                                    }
                                    player.sendMessage("Lore changed successfully!");
                                    return true;
                                }
                                player.sendMessage("Too many arguments!");
                                return true;
                            }
                            player.sendMessage("Invalid Syntax!");
                            return true;
                        }
                    }
                }
                else if (args.length == 2)
                {
                    player.sendMessage("Invalid Syntax!");
                    return true;
                }
            }
        }
        return false;
    }

    private ItemStack defaultItem(String string)
    {
        ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
        ItemMeta pearlMeta = pearl.getItemMeta();
        pearlMeta.setDisplayName(string);
        ArrayList<String> lore = new ArrayList();
        lore.add("Default Lore, Please change!");
        pearlMeta.setLore(lore);
        pearl.setItemMeta(pearlMeta);
        return pearl;
    }
}