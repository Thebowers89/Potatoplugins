package PotatoBrodcast;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Broadcaster extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.getServer().broadcastMessage(Utils.getHeader() + "\n" + Utils.getMessage() + "\n" + Utils.getFooter());
    }



}
