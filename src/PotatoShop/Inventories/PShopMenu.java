package PotatoShop.Inventories;

import PotatoShop.ItemStacks;
import PotatoShop.LocUtils;
import PotatoShop.ShopUtils;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PShopMenu
        implements Listener
{
    private static String invName = ChatColor.DARK_GREEN + "Player Shop Menu";

    public static Inventory PShopInv()
    {
        Inventory inv = Bukkit.createInventory(null, 54, invName);
        for (int i = 0; i < 54; i++) {
            inv.setItem(i, ItemStacks.filler());
        }
        for (int i = 28; i <= 34; i++) {
            inv.setItem(i, ItemStacks.nullShop());
        }
        for (int i = 37; i <= 43; i++) {
            inv.setItem(i, ItemStacks.nullShop());
        }
        ShopUtils.sponShops(inv);
        inv.setItem(11, ItemStacks.resetSpeed());
        inv.setItem(12, ItemStacks.speed1());
        inv.setItem(13, ItemStacks.speed2());
        inv.setItem(14, ItemStacks.speed3());
        inv.setItem(15, ItemStacks.speed4());
        return inv;
    }

    @EventHandler
    public void invClickCancel(InventoryClickEvent e)
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
    {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/PlayerShopData.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        Inventory inv = e.getClickedInventory();
        Player player = (Player)e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        PotionEffectType speed = PotionEffectType.SPEED;
        if ((inv != null) &&
                (inv.getName().equals(invName))) {
            if (item.equals(ItemStacks.resetSpeed()))
            {
                player.removePotionEffect(speed);
            }
            else if (item.equals(ItemStacks.speed1()))
            {
                player.removePotionEffect(speed);
                player.addPotionEffect(new PotionEffect(speed, 10000000, 0), true);
            }
            else if (item.equals(ItemStacks.speed2()))
            {
                player.removePotionEffect(speed);
                player.addPotionEffect(new PotionEffect(speed, 10000000, 5), true);
            }
            else if (item.equals(ItemStacks.speed3()))
            {
                player.removePotionEffect(speed);
                player.addPotionEffect(new PotionEffect(speed, 10000000, 10), true);
            }
            else if (item.equals(ItemStacks.speed4()))
            {
                player.removePotionEffect(speed);
                player.addPotionEffect(new PotionEffect(speed, 10000000, 15), true);
            }
            else if (!item.equals(ItemStacks.nullShop()))
            {
                if (!item.equals(ItemStacks.filler())) {
                    if (myFile.getConfigurationSection("Player Shop").contains(item.getItemMeta().getDisplayName())) {
                        player.teleport(LocUtils.getPShopLocation(item.getItemMeta().getDisplayName()));
                    }
                }
            }
        }
    }
}