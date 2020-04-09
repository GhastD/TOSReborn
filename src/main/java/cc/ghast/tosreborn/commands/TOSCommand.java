package cc.ghast.tosreborn.commands;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.command.ACommand;
import cc.ghast.tosreborn.api.data.WrappedPlayer;
import cc.ghast.tosreborn.api.utils.chat.Chat;
import cc.ghast.tosreborn.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Ghast
 * @since 08-Apr-20
 */
public class TOSCommand extends ACommand {

    private TOSReborn plugin;

    public TOSCommand(TOSReborn plugin) {
        super("tos", "Main TOS command", "tos.staff", false, plugin);
        this.plugin = plugin;
        this.helpMessage = Chat.translate(new String[]{
                "&1TOS version " + plugin.getDescription().getVersion()  + " by Ghast",
                "&6/ToS reload &2Reload the config.yml and tasks.",
                "&6/ToS resetconfig &2Reset your config.yml.",
                "&6/ToS resetdata &2Reset all PlayerData data",
                "&6/ToS setread <player> <true/false> &2Set read status",
                "&6/ToS setagreed <player> <true/false> &2Set agreed status"
        });
    }

    private final String[] helpMessage;
    private static final String PREFIX = "&7&l[&l&6TOS&7] ";
    private static final String NOT_FOUND = Chat.translate(PREFIX + "&cCommand not found! Use /tos to view all commands");

    @Override
    public boolean run(CommandSender sender, TOSReborn artemis, String[] args) {
        switch (args.length){
            case 0: {
                sender.sendMessage(helpMessage);
                break;
            }
            case 1: {
                switch (args[0].toLowerCase()){
                    case "reload": {
                        plugin.getConfigManager().disinit();
                        plugin.getConfigManager().init();
                        plugin.getTaskManager().disinit();
                        plugin.getTaskManager().init();
                        sender.sendMessage(Chat.translate(PREFIX + "&aSuccessfully reloaded the config"));
                        return true;
                    }
                    case "resetconfig": {
                        ConfigManager.getConfig().reset();
                        ConfigManager.getConfig().save();
                        sender.sendMessage(Chat.translate(PREFIX + "&aSuccessfully reset the config"));
                        return true;
                    }
                    case "resetdata": {
                        plugin.getStorageManager().resetAll();
                        sender.sendMessage(Chat.translate(PREFIX + "&aSuccessfully reset all players"));
                        return true;
                    }
                    case "help": {
                        sender.sendMessage(helpMessage);
                        return true;
                    }
                }
            }
            case 3: {
                switch (args[0].toLowerCase()){
                    case "setread": {
                        if (!(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")))
                            return error(sender, "&cInvalid usage! Please specify a correct boolean:" +
                                    " /tos setread <player> <true/false>");
                        boolean value = Boolean.parseBoolean(args[2]);
                        OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                        plugin.getStorageManager().updatePlayerRead(player.getUniqueId(), value);
                        sender.sendMessage(Chat.translate(PREFIX + "&aSuccessfully modified &e" + args[1]
                                + "&a's profile to setread:&c" + value + "&a!"));
                        return true;
                    }
                    case "setagreed":{
                        if (!(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")))
                            return error(sender, "&cInvalid usage! Please specify a correct boolean:" +
                                    " /tos setagree <player> <true/false>");
                        boolean value = Boolean.parseBoolean(args[2]);
                        OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                        plugin.getStorageManager().updatePlayerAgreed(player.getUniqueId(), value);
                        sender.sendMessage(Chat.translate(PREFIX + "&aSuccessfully modified &e" + args[1]
                                + "&a's profile to setagree:&c" + value + "&a!"));
                        return true;
                    }
                }
            }
            default: {
                switch (args[0].toLowerCase()){
                    // PLACE THE USAGES HERE
                    case "setread": {
                        sender.sendMessage(Chat.translate(PREFIX + "&cInvalid usage! Do /tos setread <player> <true/false>"));
                        break;
                    }
                    case "setagreed":{
                        sender.sendMessage(Chat.translate(PREFIX + "&cInvalid usage! Do /tos setagreed <player> <true/false>"));
                        break;
                    }
                    default: {
                        sender.sendMessage(NOT_FOUND);
                        break;
                    }
                }
            }
        }
        return false;
    }

    private boolean error(CommandSender sender, String message){
        sender.sendMessage(Chat.translate(PREFIX + message));
        return false;
    }
}
