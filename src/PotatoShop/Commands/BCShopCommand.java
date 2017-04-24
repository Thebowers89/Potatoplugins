package PotatoShop.Commands;

import PotatoShop.LocUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BCShopCommand
        implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if ((sender instanceof Player)) {
            if ((sender.hasPermission("pshops.bcshop")) || (sender.isOp())) {
                if (args.length == 1) {
                    File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShops/PlayerShopData.yml");
                    YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);

                    setLoc(args[0]);
                    return true;
                }
            } else {
                sender.sendMessage("You do not have permission to execute this command!");
                return true;
            }
        }
        if (args.length == 1) {
            File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShops/PlayerShopData.yml");
            YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
            int counter = 0;
            for (String key : myFile.getConfigurationSection("Player Shop").getKeys(false)) {
                counter += 1;
            }
            if (counter < 14) {
                setLoc(args[0]);
                return true;
            }
            System.out.println("Too Many shops!");
            return true;
        }
        return false;
    }

    private void setLoc(String string) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShops/PlayerShopData.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        Location spawn = LocUtils.getLocation("Pshop.Locations.Spawn");
        setPShopLocation(spawn, string);
        myFile.set("Player Shop." + string + ".Item", defaultItem(string));
        try {
            myFile.save(file);
            System.out.println("Created " + string + "'s shop!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPShopLocation(Location loc, String string) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShops/PlayerShopData.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);

        myFile.set("Player Shop." + string + ".Location.World", loc.getWorld().getName());
        myFile.set("Player Shop." + string + ".Location.X", Double.valueOf(loc.getX()));
        myFile.set("Player Shop." + string + ".Location.Y", Double.valueOf(loc.getY() + 0.5D));
        myFile.set("Player Shop." + string + ".Location.Z", Double.valueOf(loc.getZ()));
        myFile.set("Player Shop." + string + ".Location.Yaw", Float.valueOf(loc.getYaw()));
        myFile.set("Player Shop." + string + ".Location.Pitch", Float.valueOf(loc.getPitch()));
        try {
            myFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR CONFIGURING SHOP LOCATION, YOU DOOFUS!");
        }
    }

    private ItemStack defaultItem(String string) {
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
