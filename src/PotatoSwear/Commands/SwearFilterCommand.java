package PotatoSwear.Commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import PotatoPlugin.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class SwearFilterCommand
        implements CommandExecutor
{
    MainClass plugin;

    public SwearFilterCommand(MainClass instance)
    {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((commandLabel.equalsIgnoreCase("sf")) || (commandLabel.equalsIgnoreCase("swearfilter"))) {
            if (sender.hasPermission("ps.Modify"))
            {
                if (args.length == 2)
                {
                    if (args[0].equalsIgnoreCase("add"))
                    {
                        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/SwearList.yml");
                        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
                        if (myFile.contains("Swears"))
                        {
                            List<String> things = myFile.getStringList("Swears");
                            if (things.contains(args[1]))
                            {
                                sender.sendMessage("This word is already in the filter!");
                                return true;
                            }
                            String thing = args[1].toLowerCase();
                            things.add(thing);
                            myFile.set("Swears", things);
                            try
                            {
                                myFile.save(file);
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            sender.sendMessage("Successfully added the bad word.");
                            this.plugin.registerSwears();
                            return true;
                        }
                        ArrayList<String> things = new ArrayList();
                        String thing = args[1].toLowerCase();
                        things.add(thing);
                        myFile.set("Swears", things);
                        try
                        {
                            myFile.save(file);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        sender.sendMessage("Successfully added the bad word.");
                        this.plugin.registerSwears();
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("del"))
                    {
                        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PotatoPlugins").getDataFolder() + "/SwearList.yml");
                        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
                        List<String> things = myFile.getStringList("Swears");
                        if (things.contains(args[1]))
                        {
                            things.remove(args[1]);
                            myFile.set("Swears", things);
                            try
                            {
                                myFile.save(file);
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            sender.sendMessage("Successfully removed the bad word.");
                            this.plugin.registerSwears();
                            return true;
                        }
                        sender.sendMessage("The bad word is not in the filter.");
                        return true;
                    }
                }
                else
                {
                    sender.sendMessage("Invalid Syntax!");
                    return true;
                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                return true;
            }
        }
        return false;
    }
}