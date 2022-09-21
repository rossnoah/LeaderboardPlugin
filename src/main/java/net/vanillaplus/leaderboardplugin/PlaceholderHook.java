package net.vanillaplus.leaderboardplugin;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.vanillaplus.leaderboardplugin.utils.LeaderboardUtils;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

public class PlaceholderHook extends PlaceholderExpansion {
    private final Plugin plugin = LeaderboardPlugin.getPlugin();


    @Override
    public String getAuthor() {
        return "someauthor";
    }

    @Override
    public String getIdentifier() {
        return "leaderboardplugin";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("position1")){
            return (ChatColor.translateAlternateColorCodes('&',"&f1. &x&F&F&D&4&0&B" + LeaderboardUtils.leaderboard.get(0).getName()+ " &7- &f"+LeaderboardUtils.leaderboard.get(0).getKills()));
        }

        if(params.equalsIgnoreCase("position2")){
            return (ChatColor.translateAlternateColorCodes('&',"&f2. &f" + LeaderboardUtils.leaderboard.get(1).getName()+ " &7- &f"+LeaderboardUtils.leaderboard.get(1).getKills()) );
        }

        if(params.equalsIgnoreCase("position3")){
            return (ChatColor.translateAlternateColorCodes('&',"&f3. &x&e&0&7&f&1&f" + LeaderboardUtils.leaderboard.get(2).getName()+ " &7- &f"+LeaderboardUtils.leaderboard.get(2).getKills()));
        }

        if(params.equalsIgnoreCase("position4")){
            return (ChatColor.translateAlternateColorCodes('&',"&f4. &7" + LeaderboardUtils.leaderboard.get(3).getName()+ " &7- &f"+LeaderboardUtils.leaderboard.get(3).getKills()));
        }

        if(params.equalsIgnoreCase("position5")){
            return (ChatColor.translateAlternateColorCodes('&',"&f5. &7" + LeaderboardUtils.leaderboard.get(4).getName()+ " &7- &f"+LeaderboardUtils.leaderboard.get(4).getKills()));
        }

        if(params.equalsIgnoreCase("self")){
            return (ChatColor.translateAlternateColorCodes('&',"&7"+player.getName()+" &7- "+LeaderboardUtils.getKills(player.getUniqueId())));
        }


        return null; // Placeholder is unknown by the Expansion
    }
}
