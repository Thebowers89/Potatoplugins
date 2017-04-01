package PotatoShop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffectType;

public class ShopListener implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event)
    {
        Player player = event.getPlayer();
        String worldName = event.getFrom().getName();
        if (worldName.equals(LocUtils.getLocation("Pshop.Locations.Spawn").getWorld().getName())) {
            player.removePotionEffect(PotionEffectType.SPEED);
        }
    }
}
