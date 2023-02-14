package me.delluna.dellunabackpack.util;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackpackData {
    public static void create(String uuid) {
        File file = FileIO.getFile(uuid);
        YamlConfiguration config = FileIO.getConfig(file);
        
        config.set("0", null);
        config.set("1", null);
        config.set("2", null);
        config.set("3", null);
        config.set("4", null);
        config.set("5", null);
        config.set("6", null);
        config.set("7", null);
        config.set("8", null);
        
        FileIO.saveConfig(file, config);
    }

    public Inventory getInventory(Player player) {
        Inventory inv = Bukkit.getServer().createInventory(null, 9, "§x§0§0§b§3§b§6" + player.getName() + "§x§0§0§b§3§b§6의 가방");
        
        String uuid = player.getUniqueId().toString();
        File file = FileIO.getFile(uuid);
        YamlConfiguration config = FileIO.getConfig(file);
        
        for (int i = 0; i < 9; i++) {
            String num = Integer.toString(i);
            
            if (config.get(num) == null) continue;
            
            ItemStack item = (ItemStack) config.get(num);
            inv.setItem(i, item);
        }
        
        return inv;
    }
    
    public static boolean isBackpackItem(ItemStack item) {
        if (!item.hasItemMeta()) return false;
        
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasCustomModelData()) return false;
        if (!meta.hasLore()) return false;
        
        List<String> lore = meta.getLore();
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).contains("우클릭으로 가방을 열 수 있다.")) {
                return true;
            }
        }
        return false;
    }
}
