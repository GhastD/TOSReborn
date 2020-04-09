package cc.ghast.tosreborn.managers;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.manager.Manager;
import cc.ghast.tosreborn.listener.ActionListener;
import cc.ghast.tosreborn.listener.CacheListener;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Ghast
 * @since 08-Apr-20
 */
public class ListenerManager implements Manager {
    private ActionListener actionListener;
    private CacheListener cacheListener;
    private TOSReborn plugin;

    public ListenerManager(TOSReborn plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        this.actionListener = new ActionListener(plugin);
        this.cacheListener = new CacheListener(plugin);

        Bukkit.getPluginManager().registerEvents(actionListener, plugin);
        Bukkit.getPluginManager().registerEvents(cacheListener, plugin);
    }

    @Override
    public void disinit() {
        this.actionListener = null;
        this.cacheListener = null;
    }
}
