package net.vanillaplus.leaderboardplugin;

public class LeaderboardPlayer{
    private String name;
    private int kills;

    public LeaderboardPlayer(String name, int kills){
        this.name = name;
        this.kills = kills;
    }

    public String getName() {
        return name;
    }

    public int getKills() {
        return kills;
    }

}
