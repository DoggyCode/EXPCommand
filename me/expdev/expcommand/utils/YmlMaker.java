package me.expdev.expcommand.utils;

import me.expdev.expcommand.*;
import org.bukkit.plugin.java.*;
import org.bukkit.configuration.file.*;
import org.bukkit.configuration.*;
import java.util.logging.*;
import java.io.*;

public class YmlMaker
{
    ExpCommand Plugin;
    public String fileName;
    private JavaPlugin plugin;
    public File ConfigFile;
    private FileConfiguration Configuration;
    
    public YmlMaker(final ExpCommand Plugin) {
        this.Plugin = Plugin;
    }
    
    public YmlMaker(final JavaPlugin plugin, final String fileName) {
        if (plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }
        this.plugin = plugin;
        this.fileName = fileName;
        final File dataFolder = plugin.getDataFolder();
        if (dataFolder == null) {
            throw new IllegalStateException();
        }
        this.ConfigFile = new File(dataFolder.toString() + File.separatorChar + this.fileName);
    }
    
    public void reloadConfig() {
        try {
            this.Configuration = (FileConfiguration)YamlConfiguration.loadConfiguration((Reader)new InputStreamReader(new FileInputStream(this.ConfigFile), "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        final InputStream defConfigStream = this.plugin.getResource(this.fileName);
        if (defConfigStream != null) {
            final YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            this.Configuration.setDefaults((Configuration)defConfig);
        }
    }
    
    public FileConfiguration getConfig() {
        if (this.Configuration == null) {
            this.reloadConfig();
        }
        return this.Configuration;
    }
    
    public void saveConfig() {
        if (this.Configuration == null || this.ConfigFile == null) {
            return;
        }
        try {
            this.getConfig().save(this.ConfigFile);
        }
        catch (IOException ex) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config.yml to " + this.ConfigFile, ex);
        }
    }
    
    public void saveDefaultConfig() {
        if (!this.ConfigFile.exists()) {
            this.plugin.saveResource(this.fileName, false);
        }
    }
}
