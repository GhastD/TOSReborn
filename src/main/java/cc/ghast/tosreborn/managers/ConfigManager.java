package cc.ghast.tosreborn.managers;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.manager.Manager;
import cc.ghast.tosreborn.utils.Configuration;
import lombok.Getter;

/**
 * @author Ghast
 * @since 07-Apr-20
 */


public class ConfigManager implements Manager {
    @Getter
    private static Configuration config;

    private TOSReborn plugin;

    public ConfigManager(TOSReborn plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        config = new Configuration("config.yml", plugin);
    }

    @Override
    public void disinit() {
        config = null;
    }
}
