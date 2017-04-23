package PotatoBC;

import java.util.List;

import PotatoPlugin.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoBC extends BukkitRunnable {
    private final MainClass main;
    private final List<String> messages;
    private final int interval;
    private final int size;

    public AutoBC(MainClass main) {
        this.main = main;
        this.messages = main.getConfig().getStringList("Messages");
        this.interval = main.getConfig().getInt("Interval");
        this.size = this.messages.size();
    }

    private int time = 0;
    private int id = 0;

    public void run() {
        if (this.time % this.interval == 0) {
            if (this.size == this.id) {
                this.id = 0;
            } else {
                Bukkit.broadcastMessage(Utilities.color(this.main.header));
                Bukkit.broadcastMessage(Utilities.color((String) this.messages.get(this.id)));
                Bukkit.broadcastMessage(Utilities.color(this.main.footer));
                this.id += 1;
            }
        }
        this.time += 1;
    }
}
