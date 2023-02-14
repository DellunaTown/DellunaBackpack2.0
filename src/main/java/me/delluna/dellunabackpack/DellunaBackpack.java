package me.delluna.dellunabackpack;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.delluna.dellunabackpack.event.*;


public final class DellunaBackpack extends JavaPlugin {
    @Override
    public void onEnable() {
        Plugin plugin = JavaPlugin.getPlugin(DellunaBackpack.class);
        
        Bukkit.getPluginManager().registerEvents(new onPlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerInteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onInventoryCloseEvent(), this);
        
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
    }
}
