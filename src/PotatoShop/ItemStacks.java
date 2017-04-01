package PotatoShop;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStacks
{
    public static ItemStack filler()
    {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(" ");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack resetSpeed()
    {
        ItemStack item = new ItemStack(Material.POTATO_ITEM);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.AQUA + "Reset Speed");
        item.setItemMeta(iMeta);
        return item;
    }

    public static ItemStack speed1()
    {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.GREEN + "Speed I");
        item.setItemMeta(iMeta);
        return item;
    }

    public static ItemStack speed2()
    {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.YELLOW + "Speed II");
        item.setItemMeta(iMeta);
        return item;
    }

    public static ItemStack speed3()
    {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.GOLD + "Speed III");
        item.setItemMeta(iMeta);
        return item;
    }

    public static ItemStack speed4()
    {
        ItemStack item = new ItemStack(Material.BAKED_POTATO);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.RED + "POTATO SPEED!");
        item.setItemMeta(iMeta);
        return item;
    }

    public static ItemStack nullShop()
    {
        ItemStack item = new ItemStack(Material.WEB);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName("Empty Slot");
        ArrayList<String> lore = new ArrayList();
        lore.add("Consider donating to get");
        lore.add("your shop sponsored here!");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
}
