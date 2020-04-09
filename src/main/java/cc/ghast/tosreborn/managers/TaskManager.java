package cc.ghast.tosreborn.managers;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.manager.Manager;
import cc.ghast.tosreborn.tasks.MessageTask;

/**
 * @author Ghast
 * @since 09-Apr-20
 */
public class TaskManager implements Manager {

    private TOSReborn reborn;
    private MessageTask task;

    public TaskManager(TOSReborn reborn) {
        this.reborn = reborn;
    }

    @Override
    public void init() {
        if (!ConfigManager.getConfig().getBoolean("Tasks.MessageTask.Enabled")) return;
        this.task = new MessageTask(reborn);
        this.task.runTaskTimer(reborn, 10, ConfigManager.getConfig().getInt("Tasks.MessageTask.Interval") * 20);
    }

    @Override
    public void disinit() {
        this.task.cancel();
        this.task = null;
    }
}
