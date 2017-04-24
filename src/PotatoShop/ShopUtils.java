package PotatoShop;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopUtils
{
    public static void sponShops(Inventory inv)
    {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShops/PlayerShopData.yml");
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
            if (size > 14) {
                size = 14;
            }
            if (size <= 7)
            {
                int slot = 28;
                int thing = slot + size;
                int counter = 0;
                for (int i = slot; i < thing; i++)
                {
                    inv.setItem(i, (ItemStack)items.get(counter));
                    counter += 1;
                }
                items.clear();
            }
            else if (size > 7)
            {
                int num1 = size - (size - 7);
                int num2 = size - 7;
                int slot = 28;
                int thing = slot + num1;
                int counter = 0;
                for (int i = slot; i < thing; i++)
                {
                    inv.setItem(i, (ItemStack)items.get(counter));
                    counter += 1;
                }
                int slot1 = 37;
                int thing1 = slot1 + num2;
                int counter1 = 0;
                for (int i = slot1; i < thing1; i++)
                {
                    inv.setItem(i, (ItemStack)items.get(num1 + counter1));
                    counter1 += 1;
                }
                items.clear();
            }
        }
    }
}
