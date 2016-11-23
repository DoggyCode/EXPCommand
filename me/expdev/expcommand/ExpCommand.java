package me.expdev.expcommand;

import org.bukkit.plugin.java.*;
import me.expdev.expcommand.utils.*;
import org.bukkit.*;
import me.expdev.expcommand.events.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import me.expdev.expcommand.commands.*;
import org.bukkit.command.*;

public class ExpCommand extends JavaPlugin
{
    private YmlMaker cfg;
    
    public void onEnable() {
        (this.cfg = new YmlMaker(this, "config.yml")).saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents((Listener)new CommandPreProcessListener(this), (Plugin)this);
        this.getCommand("expcommand").setExecutor((CommandExecutor)new EXPCommandCommand());
    }
    
    public YmlMaker getCfg() {
        return this.cfg;
    }
}
