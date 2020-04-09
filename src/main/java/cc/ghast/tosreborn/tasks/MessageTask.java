package cc.ghast.tosreborn.tasks;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.utils.chat.Chat;
import cc.ghast.tosreborn.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Timer;

/**
 * @author Ghast
 * @since 09-Apr-20
 */
public class MessageTask extends BukkitRunnable {

    private TOSReborn plugin;

    public MessageTask(TOSReborn plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().parallelStream().forEach(e -> {
            if (!plugin.getStorageManager().getCachedPlayer(e.getUniqueId()).isAgreed()){
                e.sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.HasNotAgreed")));
            }
        });
    }
}
