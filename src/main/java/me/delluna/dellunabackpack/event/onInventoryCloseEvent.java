package me.delluna.dellunabackpack.event;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delluna.dellunabackpack.util.BackpackData;
import me.delluna.dellunabackpack.util.FileIO;

public class onInventoryCloseEvent implements Listener {
    @EventHandler
    private void onInventoryCloseEvent(InventoryCloseEvent event) {
        if (!event.getView().getTitle().contains("§x§0§0§b§3§b§6의 가방")) return;
        
        ItemStack[] inv = event.getInventory().getContents();
        Player player = (Player) event.getPlayer();
        String uuid = player.getUniqueId().toString();
        File file = FileIO.getFile(uuid);
        YamlConfiguration config = FileIO.getConfig(file);
        
        for (int i = 0; i < 9; i++) {
            String num = Integer.toString(i);
            ItemStack item = inv[i];
            if (BackpackData.isBackpackItem(item)) {
                returnBackpack(player, item);
                config.set(num, null);
            }
            else {
                config.set(num, item);
            }
        }
        
        FileIO.saveConfig(file, config);
    }
    
    private void returnBackpack(Player player, ItemStack item) {
        if (player.getInventory().firstEmpty() == -1){
            player.sendMessage("가방을 떨어뜨렸습니다. 가방을 조심히 다뤄주세요...");
            player.getWorld().dropItem(player.getLocation(), item);
        }
        else {
            player.sendMessage("가방에 가방을 넣을 수 없습니다.");
            player.getInventory().addItem(item);
        }
    }
}
