package me.expdev.expcommand.events;

import me.expdev.expcommand.*;
import org.bukkit.event.player.*;
import net.md_5.bungee.api.*;
import java.text.*;
import org.bukkit.entity.*;
import org.bukkit.configuration.*;
import java.util.*;
import org.bukkit.event.*;

public class CommandPreProcessListener implements Listener
{
    ExpCommand plugin;
    
    public CommandPreProcessListener(final ExpCommand instance) {
        this.plugin = instance;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(final PlayerCommandPreprocessEvent event) {
        final Player p = event.getPlayer();
        if (p.isOp()) {
            return;
        }
        final String message = event.getMessage().toLowerCase();
        final String[] command = message.split(" ");
        String permission = "";
        int level = 0;
        final ConfigurationSection section = this.plugin.getCfg().getConfig().getConfigurationSection("commands");
        for (String s : section.getKeys(false)) {
            s = s.toLowerCase();
            if (command[0].equalsIgnoreCase("/" + s)) {
                permission = this.plugin.getCfg().getConfig().getString("commands." + s + ".permission").toLowerCase();
                if (!p.hasPermission(permission)) {
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "!" + ChatColor.DARK_GRAY + "] " + ChatColor.RED + "You don't have permission to do that!");
                    return;
                }
                level = this.plugin.getCfg().getConfig().getInt("commands." + s + ".level");
                if (p.getLevel() < level) {
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "!" + ChatColor.DARK_GRAY + "] " + ChatColor.RED + "You" + ChatColor.GRAY + " have " + ChatColor.RED + NumberFormat.getNumberInstance(Locale.US).format(p.getLevel()) + ChatColor.GRAY + " level(s) and need " + ChatColor.RED + NumberFormat.getNumberInstance(Locale.US).format(level - p.getLevel()) + ChatColor.GRAY + " more level(s) to perform that command (" + ChatColor.RED + command[0] + ChatColor.GRAY + ").");
                }
                else {
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_GREEN + ChatColor.BOLD + "!" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + "You" + ChatColor.GRAY + " paid " + ChatColor.GREEN + NumberFormat.getNumberInstance(Locale.US).format(level) + "" + ChatColor.GRAY + " level(s) to execute that command (" + ChatColor.GREEN + command[0] + ChatColor.GRAY + ").");
                    p.setLevel(p.getLevel() - level);
                }
            }
        }
    }
}
