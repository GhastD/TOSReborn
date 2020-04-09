package cc.ghast.tosreborn.commands;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.command.ACommand;
import cc.ghast.tosreborn.api.data.WrappedPlayer;
import cc.ghast.tosreborn.api.utils.chat.Chat;
import cc.ghast.tosreborn.managers.ConfigManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Ghast
 * @since 08-Apr-20
 */
public class RulesCommand extends ACommand {

    private TOSReborn plugin;

    public RulesCommand(TOSReborn plugin) {
        super("rules", "Command to view the rules", "tos.rules", false, plugin);
        setPlayerOnly();
        this.plugin = plugin;
    }

    @Override
    public boolean run(CommandSender executor, TOSReborn artemis, String[] args) {

        for (final String rule : ConfigManager.getConfig().getStringList("Rules")) {
            executor.sendMessage(Chat.translate(rule));
        }
        WrappedPlayer playerFile = plugin.getStorageManager().getCachedPlayer(((Player)executor).getUniqueId());

        if (playerFile.isAgreed()){
            executor.sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.HasAgreed")));
            return true;
        }

        if (!playerFile.isRead()) {
            plugin.getStorageManager().updatePlayerRead(((Player) executor).getUniqueId(), true);
        }


        return false;
    }
}
