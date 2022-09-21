package net.vanillaplus.leaderboardplugin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.vanillaplus.leaderboardplugin.commands.KillBoardCommand;
import net.vanillaplus.leaderboardplugin.commands.KillCountCommand;
import net.vanillaplus.leaderboardplugin.listeners.DeathListener;
import net.vanillaplus.leaderboardplugin.utils.LeaderboardUtils;
import net.vanillaplus.leaderboardplugin.utils.SchedulerUtils;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static net.vanillaplus.leaderboardplugin.utils.LeaderboardUtils.leaderboard;

public final class LeaderboardPlugin extends JavaPlugin {
public static Plugin plugin;
public static HashMap<UUID, ArrayList<PlayerKill>> playerKills = new HashMap<>();
public static HashMap<UUID, Integer> killCount = new HashMap<>();

private static FileWriter fileWriter;
private static File file = new File("plugins/LeaderboardPlugin/data.json");
 @Override
    public void onEnable() {

     if(file.exists()){
         try {
             String json = FileUtils.readFileToString(file, "UTF-8");
             JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
             jsonObject.entrySet().forEach(entry -> {
                 UUID uuid = UUID.fromString(entry.getKey());
                 Integer kills = entry.getValue().getAsInt();
                 killCount.put(uuid, kills);
             });
         }catch (Exception e){
             e.printStackTrace();
         }
     }

        plugin = this;

        //register events

        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        this.getCommand("killcount").setExecutor(new KillCountCommand());
        this.getCommand("killboard").setExecutor(new KillBoardCommand());
     SchedulerUtils.setPlugin(this);

     for (int i = 0; i < 10; i++) {
         if(leaderboard.size()<10) {
             leaderboard.add(new LeaderboardPlayer("Player", 0));
         }
     }

     SchedulerUtils.runRepeating(LeaderboardUtils::sortLeaderboard, 20*60);
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin,LeaderboardPlugin::saveData, 20*60*5);

     if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
         new PlaceholderHook().register();
     }

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic

        saveData();


    }

    private static void saveData(){
        JsonObject jsonObject = new JsonObject();
        for (UUID uuid : killCount.keySet()) {
            jsonObject.addProperty(uuid.toString(), killCount.get(uuid));
        }
        try {
            //if file doesn't exist, create it
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }
            File file = new File(plugin.getDataFolder(), "data.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter("plugins/LeaderboardPlugin/data.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.close();
            plugin.getLogger().info("Saved leaderboard data to file");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Plugin getPlugin(){
        return plugin;
    }
}
