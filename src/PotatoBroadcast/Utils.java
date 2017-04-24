package PotatoBroadcast;

import PotatoPlugin.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;

public class Utils {

    private static MainClass plugin;
    public Utils(MainClass instance) {
        plugin = instance;
    }

    private static BukkitTask broadcaster;

    private static File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/Broadcaster/Config.yml");
    private static YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);

    public static String getMessage() {
        String string = ChatColor.translateAlternateColorCodes('&', myFile.getString("Part.Message"));
        return string;
    }

    public static void setMessage(String string) {
        myFile.set("Part.Message", string);
        try {
            myFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHeader() {
        return ChatColor.translateAlternateColorCodes('&', myFile.getString("Part.Header"));
    }

    public static void setHeader(String string) {
        myFile.set("Part.Header", string);
        try {
            myFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFooter() {
        return ChatColor.translateAlternateColorCodes('&', myFile.getString("Part.Footer"));
    }

    public static void setFooter(String string) {
        myFile.set("Part.Footer", string);
        try {
            myFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setInterval(int number) {
        myFile.set("Part.Interval", number);
        try {
            myFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        update();
    }

    public static int getInterval() {
        return myFile.getInt("Part.Interval");
    }

    public static void init() {
        if (!file.exists()) {
            myFile.set("Part.Header", "Default header");
            myFile.set("Part.Message", "Default message");
            myFile.set("Part.Footer", "Default footer");
            myFile.set("Part.Interval", 10);
            try {
                myFile.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        broadcaster = new Broadcaster().runTaskTimer(plugin, 1, 20 * Utils.getInterval()); //Middle is seconds
    }

    private static void update() {
        broadcaster.cancel();
        init();
    }

}
