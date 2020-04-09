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
 * @since 07-Apr-20
 */
public class AgreeCommand extends ACommand {

    private TOSReborn plugin;

    public AgreeCommand(TOSReborn plugin) {
        super("iagree", "Command to submit your agreement with the TOS", "tos.agree", false, plugin);
        this.plugin = plugin;
        setPlayerOnly();
    }

    @Override
    public boolean run(CommandSender sender, TOSReborn plugin, String[] args) {
        WrappedPlayer player = plugin.getStorageManager().getCachedPlayer(((Player) sender).getUniqueId());

        if (!player.isRead()){
            sender.sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.HasNotRead")));
            return true;
        }

        if (player.isAgreed()){
            sender.sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.HasAgreed")));
            return true;
        }

        sender.sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.JustAgreed")));
        plugin.getStorageManager().updatePlayerAgreed(((Player) sender).getUniqueId(), true);

        return false;
    }
}
