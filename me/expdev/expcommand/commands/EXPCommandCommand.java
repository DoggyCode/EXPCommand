package me.expdev.expcommand.commands;

import org.bukkit.command.*;
import net.md_5.bungee.api.*;

public class EXPCommandCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] args) {
        commandSender.sendMessage("" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "---------------------------------------------");
        commandSender.sendMessage(ChatColor.GRAY + "Made by: " + ChatColor.RED + "ExpDev ( http://www.mc-market.org/members/11048/ )");
        commandSender.sendMessage(ChatColor.GRAY + "Updates: " + ChatColor.RED + "Link coming soon!");
        commandSender.sendMessage(ChatColor.GRAY + "Plugin Version: " + ChatColor.RED + "v1.0");
        commandSender.sendMessage(ChatColor.GRAY + "Info: " + ChatColor.GREEN + "Pay for commands using EXP!");
        commandSender.sendMessage("" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "---------------------------------------------");
        return false;
    }
}
