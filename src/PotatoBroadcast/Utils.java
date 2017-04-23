package PotatoBroadcast;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;

public class Utils {

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
    }

    public static int getInterval() {
        if (myFile.get("Part.Interval").equals(null)) {
            return 10;
        }
        return myFile.getInt("Part.Interval");
    }

}
