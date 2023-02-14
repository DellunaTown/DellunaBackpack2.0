package me.delluna.dellunabackpack.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.delluna.dellunabackpack.util.BackpackData;
import me.delluna.dellunabackpack.util.FileIO;

public class onPlayerJoinEvent implements Listener {
    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        
        if (!FileIO.isFileExists(uuid)) {
            BackpackData.create(uuid);
        }
    }
}
