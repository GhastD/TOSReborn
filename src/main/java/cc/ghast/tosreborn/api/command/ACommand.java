package cc.ghast.tosreborn.api.command;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.utils.chat.Chat;
import cc.ghast.tosreborn.api.utils.command.CommandUtil;
import cc.ghast.tosreborn.api.utils.string.StringUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Getter;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Ghast
 * @since 04-Apr-20
 */
public abstract class ACommand implements CommandExecutor {
    @Getter private String command, permission;
    private List<ACommand> subCommands;
    private Cache<CommandSender, Long> playerLongCache;
    private boolean playerOnly = false, consoleOnly = false;
    private TOSReborn plugin;
    @Getter
    private PluginCommand commandBukkit;

    public ACommand(String name, String description, String permission, boolean subCommand, TOSReborn plugin) {
        this.command = name;
        this.permission = permission;
        this.plugin = plugin;
        this.subCommands = new ArrayList<>();
        this.playerLongCache = CacheBuilder.newBuilder().expireAfterWrite(50, TimeUnit.MILLISECONDS).build();

        if (!subCommand) {
            this.commandBukkit = CommandUtil.getCommand(name, plugin);
            this.commandBukkit.setExecutor(this);
        }
    }

    public ACommand setPlayerOnly(){
        this.playerOnly = true;
        return this;
    }

    public ACommand setConsoleOnly(){
        this.consoleOnly = true;
        return this;
    }

    public ACommand addSubCommands(ACommand... subCommands){
        this.subCommands.addAll(Arrays.asList(subCommands));
        return this;
    }

    public ACommand setCooldown(int i){
        this.playerLongCache = CacheBuilder.newBuilder().expireAfterWrite(i, TimeUnit.SECONDS).build();
        return this;
    }



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (playerOnly && !(commandSender instanceof Player)) return notPlayer(commandSender);
        if (consoleOnly && !(commandSender instanceof ConsoleCommandSender)) return notConsole(commandSender);
        if (!commandSender.hasPermission(permission)) return noPermission(commandSender);

        if (strings.length > 0){
            for (ACommand cmd : subCommands){
                if (cmd.getCommand().equalsIgnoreCase(strings[0])){
                    return cmd.onCommand(commandSender, command, s, StringUtil.displaceByOne(strings));
                }
            }
        }
        run(commandSender, plugin, strings);
        playerLongCache.put(commandSender, System.currentTimeMillis());
        return false;
    }

    public abstract boolean run(CommandSender executor, TOSReborn artemis, String[] args);

    public boolean notPlayer(CommandSender sender){
        sender.sendMessage(Chat.translate("&4You must be a player to run this command!"));
        return false;
    }

    public boolean notConsole(CommandSender sender){
        sender.sendMessage(Chat.translate("&4This command can only be ran by console!"));
        return false;
    }

    public boolean noPermission(CommandSender sender){
        sender.sendMessage(Chat.translate("&4No permission!"));
        return false;
    }


}
