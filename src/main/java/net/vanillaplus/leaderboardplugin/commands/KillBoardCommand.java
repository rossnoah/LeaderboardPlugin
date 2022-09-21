package net.vanillaplus.leaderboardplugin.commands;

import net.vanillaplus.leaderboardplugin.utils.LeaderboardUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KillBoardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        LeaderboardUtils.showLeaderboard(sender);
        return true;
    }
}
