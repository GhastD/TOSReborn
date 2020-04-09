package cc.ghast.tosreborn.managers;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.data.WrappedPlayer;
import cc.ghast.tosreborn.api.manager.Manager;
import cc.ghast.tosreborn.sqlite.SQLite;
import cc.ghast.tosreborn.sqlite.Schema;
import cc.ghast.tosreborn.utils.BUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Ghast
 * @since 07-Apr-20
 */
public class StorageManager implements Manager {
    private Map<UUID, WrappedPlayer> playerCache = new HashMap<>();
    private TOSReborn plugin;
    private SQLite sqLite;
    private String table;

    public StorageManager(TOSReborn plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        this.table = ConfigManager.getConfig().getString("SQLite.table-name");
        this.sqLite = new SQLite();
        this.sqLite.connect(plugin, table);
    }

    @Override
    public void disinit() {
        this.sqLite.stop();
        this.table = null;
        this.playerCache.clear();
    }

    public WrappedPlayer getCachedPlayer(UUID player){
        return playerCache.computeIfAbsent(player, this::computeIfAbsent);
    }

    public void cachePlayer(UUID player){
        playerCache.put(player, computeIfAbsent(player));
    }

    public void unCachePlayer(UUID player){
        playerCache.remove(player);
    }

    public void updatePlayerRead(UUID player, boolean value){
        playerCache.get(player).setRead(value);
        Schema.updateRead(table, player, value).execute();
    }

    public void updatePlayerAgreed(UUID player, boolean value){
        playerCache.get(player).setAgreed(value);
        Schema.updateAgreed(table, player, value).execute();
    }

    public WrappedPlayer computeIfAbsent(UUID player){
        ResultSet set = Schema.checkExist(table, player.toString()).executeQuery();
        try {
            while (set.next()){
                return new WrappedPlayer(set.getString("uuid"), set.getBoolean("has_agreed"),
                        set.getBoolean("has_read"), BUtil.toDate(set.getString("date")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        insertPlayer(player);
        return new WrappedPlayer(player.toString(), false, false, Calendar.getInstance().getTime());
    }

    public void insertPlayer(UUID player){
        Schema.insertPlayer(table, player).execute();
    }

    public void resetAll(){
        Schema.resetData(table).execute();
        playerCache.clear();
        Bukkit.getOnlinePlayers().parallelStream().forEach(e-> cachePlayer(e.getUniqueId()));
    }
}
