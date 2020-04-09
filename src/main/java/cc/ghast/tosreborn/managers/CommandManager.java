package cc.ghast.tosreborn.managers;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.command.ACommand;
import cc.ghast.tosreborn.api.manager.Manager;
import cc.ghast.tosreborn.commands.AgreeCommand;
import cc.ghast.tosreborn.commands.RulesCommand;
import cc.ghast.tosreborn.commands.TOSCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

/**
 * @author Ghast
 * @since 08-Apr-20
 */
public class CommandManager implements Manager {

    private TOSReborn plugin;

    public CommandManager(TOSReborn plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        registerDynamicCommands();
    }

    @Override
    public void disinit() {

    }


    private void registerDynamicCommands(){

        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                f.setAccessible(true);

                SimpleCommandMap cmdMap = (SimpleCommandMap) f.get(Bukkit.getPluginManager());
                // REGISTER HERE
                cmdMap.register("iagree", "tos::iagree", new AgreeCommand(plugin).getCommandBukkit());
                cmdMap.register("rules", "tos::rules", new RulesCommand(plugin).getCommandBukkit());
                cmdMap.register("tos", "tos::tos", new TOSCommand(plugin).getCommandBukkit());
            }
        } catch (IllegalAccessException | NoSuchFieldException e){
            e.printStackTrace();
        }

    }
}
