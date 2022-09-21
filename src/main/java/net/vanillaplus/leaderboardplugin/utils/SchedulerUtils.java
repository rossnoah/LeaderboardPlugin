package net.vanillaplus.leaderboardplugin.utils;


import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.util.function.Consumer;

public class SchedulerUtils {
    private static Plugin plugin = null;

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    public static void runRepeating(Runnable runnable, long l) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, runnable, 0, l);
    }

    public static void runSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(plugin, runnable);
    }

    public static void runLater(Runnable runnable, int x) {
        Bukkit.getScheduler().runTaskLater(plugin, runnable, x);
    }

    public static void setPlugin(Plugin plugin) {
        SchedulerUtils.plugin = plugin;
    }


}
