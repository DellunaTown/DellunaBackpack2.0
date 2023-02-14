package me.delluna.dellunabackpack.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.delluna.dellunabackpack.DellunaBackpack;

public class FileIO {
    private static Plugin plugin = JavaPlugin.getPlugin(DellunaBackpack.class);
    private static String FILE_PATH = plugin.getDataFolder() + "\\BackpackData";
    
    public static boolean isFileExists(String uuid) {
        return getFile(uuid).exists();
    }
    
    public static File getFile(String uuid) {
        return new File(FILE_PATH, uuid + ".yml");
    }
    
    public static YamlConfiguration getConfig(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }
    
    public static void saveConfig(File file, YamlConfiguration config) {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
