package PotatoShop.Inventories;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IconEditor
        implements Listener
{
    private static String invName = ChatColor.AQUA + "Icon Editor";
    private static String pName;

    public static Inventory IE(String string)
    {
        pName = string;
        Inventory inv = Bukkit.createInventory(null, 27, invName);
        inv.addItem(new ItemStack[] { new ItemStack(Material.BOOK) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.WOOD) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.COAL_ORE) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.IRON_ORE) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_ORE) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.GREEN_RECORD) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.CAKE) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.REDSTONE) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.SEEDS) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.ENDER_PEARL) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.POTION, 1, (byte) 8261) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.IRON_SWORD) });
        inv.addItem(new ItemStack[] { new ItemStack(Material.IRON_CHESTPLATE) });
        return inv;
    }

    @EventHandler
    public void clickCancel(InventoryClickEvent e)
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

    @EventHandler
    public void invInteract(InventoryClickEvent e)
            throws IOException
    {
        Inventory inv = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        Player player = (Player)e.getWhoClicked();
        if ((inv != null) &&
                (inv.getName().equalsIgnoreCase(invName)))
        {
            File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopData.yml");
            YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
            if (item != null)
            {
                ItemStack item1 = myFile.getItemStack("Player Shop." + pName + ".Item");
                myFile.set("Player Shop." + pName + ".Item", buildItem(item1, item));
                myFile.save(file);
                player.sendMessage("Successfully changed " + pName + "'s icon!");
                player.closeInventory();
            }
        }
    }

    private static ItemStack buildItem(ItemStack oldItem, ItemStack newItem)
    {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopData.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        ItemMeta oldMeta = oldItem.getItemMeta();
        newItem.setItemMeta(oldMeta);
        ItemStack item = newItem;
        return item;
    }
}
