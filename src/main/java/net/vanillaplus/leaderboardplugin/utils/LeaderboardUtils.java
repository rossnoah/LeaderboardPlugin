package net.vanillaplus.leaderboardplugin.utils;

import net.vanillaplus.leaderboardplugin.LeaderboardPlayer;
import net.vanillaplus.leaderboardplugin.LeaderboardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class LeaderboardUtils {

    public static ArrayList<LeaderboardPlayer> leaderboard = new ArrayList<>();

    public static void sortLeaderboard(){
        SchedulerUtils.runAsync(()->{
            ArrayList<LeaderboardPlayer> temp = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                temp.add(new LeaderboardPlayer("Player", 0));
            }
            //copy killcount to temp
            LeaderboardPlugin.killCount.forEach((uuid, integer) -> {
                temp.add(new LeaderboardPlayer(Bukkit.getOfflinePlayer(uuid).getName(), integer));
            });
            //sort temp by number of kills
            temp.sort((o1, o2) -> o2.getKills() - o1.getKills());
            //set leaderboard to temp
            leaderboard = temp;
        });


    }

    public static void showLeaderboard(CommandSender sender){

            sender.sendMessage("Leaderboard:");
            for(int i = 0; i < 10; i++){
                sender.sendMessage((i+1) + ". " + leaderboard.get(i).getName() + " - " + leaderboard.get(i).getKills());
            }


    }






    public static Integer getKills(UUID uuid){
     if(LeaderboardPlugin.killCount.containsKey(uuid)){
         return LeaderboardPlugin.killCount.get(uuid);
     }else {
         return 0;
     }}

    public static Integer getKills(Player player){
            if(LeaderboardPlugin.killCount.containsKey(player.getUniqueId())){
                return LeaderboardPlugin.killCount.get(player.getUniqueId());
            }else {
                return 0;
            }
    }

    public static Integer getKills(String name) {
        if (Bukkit.getPlayer(name) != null) {
            Player player = Bukkit.getPlayer(name);
            if (LeaderboardPlugin.killCount.containsKey(player.getUniqueId())) {
                return LeaderboardPlugin.killCount.get(player.getUniqueId());
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

}
