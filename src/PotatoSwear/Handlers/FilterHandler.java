package PotatoSwear.Handlers;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FilterHandler
        implements Listener
{
    public static List<String> swears;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        String message = e.getMessage();
        ArrayList<String> newMessage = new ArrayList();
        for (String s : message.split(" ")) {
            if (swears.contains(s.toLowerCase())) {
                newMessage.add(StringUtils.repeat("*", s.length()));
            } else {
                newMessage.add(s);
            }
        }
        e.setMessage(StringUtils.join(newMessage, " "));
    }
}