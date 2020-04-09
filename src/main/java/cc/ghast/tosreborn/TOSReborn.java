package cc.ghast.tosreborn;

import cc.ghast.tosreborn.api.manager.Manager;
import cc.ghast.tosreborn.managers.*;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class TOSReborn extends JavaPlugin {

    private List<Manager> managers = new ArrayList<>();
    private StorageManager storageManager;
    private ConfigManager configManager;
    private ListenerManager listenerManager;
    private CommandManager commandManager;
    private TaskManager taskManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.configManager = new ConfigManager(this);
        this.storageManager = new StorageManager(this);
        this.listenerManager = new ListenerManager(this);
        this.commandManager = new CommandManager(this);
        this.taskManager = new TaskManager(this);

        this.managers.addAll(Arrays.asList(configManager, listenerManager, storageManager, commandManager, taskManager));
        this.managers.forEach(Manager::init);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.managers.forEach(Manager::disinit);
        this.managers.clear();
        this.configManager = null;
        this.storageManager = null;
        this.listenerManager = null;
        this.commandManager = null;
        this.taskManager = null;
    }
}
