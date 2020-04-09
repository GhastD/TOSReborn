package cc.ghast.tosreborn.listener;

import cc.ghast.tosreborn.TOSReborn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Ghast
 * @since 07-Apr-20
 */
public class CacheListener implements Listener {

    private TOSReborn plugin;

    public CacheListener(TOSReborn plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e){
        plugin.getStorageManager().cachePlayer(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        plugin.getStorageManager().unCachePlayer(e.getPlayer().getUniqueId());
    }

}
