package PotatoShop;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class TestingInventory
        implements CommandExecutor, Listener
{
    private static String invName = ChatColor.GOLD + "TESTING INVENTORY!";

    public static Inventory testInv()
    {
        Inventory inv = Bukkit.createInventory(null, 27, invName);
        fillTest2(inv);
        System.out.println("Test1");
        return inv;
    }

    private static void fillTest(Inventory inv)
    {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopData.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        System.out.println("Test2");
        if (myFile.contains("Player Shop")) {
            for (String key : myFile.getConfigurationSection("Player Shop").getKeys(false))
            {
                ItemStack item = (ItemStack)myFile.get("Player Shop." + key + ".Item");
                inv.addItem(new ItemStack[] { item });
            }
        }
    }

    private static void fillTest2(Inventory inv)
    {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopData.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        if (myFile.contains("Player Shop"))
        {
            ArrayList<ItemStack> items = new ArrayList();
            for (String key : myFile.getConfigurationSection("Player Shop").getKeys(false))
            {
                ItemStack item = myFile.getItemStack("Player Shop." + key + ".Item");
                items.add(item);
            }
            int size = items.size();
            if (size <= 9)
            {
                for (int i = 0; i < size; i++) {
                    inv.setItem(i, (ItemStack)items.get(i));
                }
                items.clear();
            }
            else if (size > 9)
            {
                int num1 = size - (size - 9);
                int num2 = size - 9;
                for (int i = 0; i < num1; i++) {
                    inv.setItem(i, (ItemStack)items.get(i));
                }
                int slot = 18;
                int thing = slot + num2;
                int counter = 0;
                for (int i = slot; i < thing; i++)
                {
                    System.out.println(i);
                    inv.setItem(i, (ItemStack)items.get(num1 + counter));
                    counter += 1;
                }
                items.clear();
            }
        }
    }

    @EventHandler
    public void test(InventoryClickEvent e)
    {
        Inventory inv = e.getClickedInventory();
        if (inv != null)
        {
            String name = inv.getName();
            if (name.equals(invName)) {
                e.setCancelled(true);
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((sender instanceof Player))
        {
            Player player = (Player)sender;
            player.openInventory(testInv());
        }
        return false;
    }
}
