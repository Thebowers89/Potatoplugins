package PotatoShop;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LocUtils
{
    public static Location getLocation(String string)
    {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopConfig.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);

        World world = Bukkit.getServer().getWorld(myFile.getString(string + ".World"));
        double x = myFile.getDouble(string + ".X");
        double y = myFile.getDouble(string + ".Y");
        double z = myFile.getDouble(string + ".Z");
        float yaw = (float)myFile.getDouble(string + ".Yaw");
        float pitch = (float)myFile.getDouble(string + ".Pitch");

        Location location = new Location(world, x, y, z, yaw, pitch);
        return location;
    }

    public static void setDefaultLocation(Location loc, Player player)
    {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopConfig.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);

        myFile.set("Pshop.Locations.Spawn.World", loc.getWorld().getName());
        myFile.set("Pshop.Locations.Spawn.X", Double.valueOf(loc.getX()));
        myFile.set("Pshop.Locations.Spawn.Y", Double.valueOf(loc.getY() + 0.5D));
        myFile.set("Pshop.Locations.Spawn.Z", Double.valueOf(loc.getZ()));
        myFile.set("Pshop.Locations.Spawn.Yaw", Float.valueOf(loc.getYaw()));
        myFile.set("Pshop.Locations.Spawn.Pitch", Float.valueOf(loc.getPitch()));
        try
        {
            myFile.save(file);
            player.sendMessage("Configured Spawn Location!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            player.sendMessage(ChatColor.RED + "ERROR CONFIGURING SPAWN LOCATION!");
        }
    }

    public static Location getPShopLocation(String string)
    {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopData.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);

        World world = Bukkit.getServer().getWorld(myFile.getString("Player Shop." + string + ".Location.World"));
        double x = myFile.getDouble("Player Shop." + string + ".Location.X");
        double y = myFile.getDouble("Player Shop." + string + ".Location.Y");
        double z = myFile.getDouble("Player Shop." + string + ".Location.Z");
        float yaw = (float)myFile.getDouble("Player Shop." + string + ".Location.Yaw");
        float pitch = (float)myFile.getDouble("Player Shop." + string + ".Location.Pitch");

        Location location = new Location(world, x, y, z, yaw, pitch);
        return location;
    }

    public static void setPShopLocation(Location loc, String string, Player player)
    {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopData.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);

        myFile.set("Player Shop." + string + ".Location.World", loc.getWorld().getName());
        myFile.set("Player Shop." + string + ".Location.X", Double.valueOf(loc.getX()));
        myFile.set("Player Shop." + string + ".Location.Y", Double.valueOf(loc.getY() + 0.5D));
        myFile.set("Player Shop." + string + ".Location.Z", Double.valueOf(loc.getZ()));
        myFile.set("Player Shop." + string + ".Location.Yaw", Float.valueOf(loc.getYaw()));
        myFile.set("Player Shop." + string + ".Location.Pitch", Float.valueOf(loc.getPitch()));
        try
        {
            myFile.save(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            player.sendMessage(ChatColor.RED + "ERROR CONFIGURING SHOP LOCATION!");
        }
    }
}
