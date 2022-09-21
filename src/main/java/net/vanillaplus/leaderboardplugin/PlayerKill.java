package net.vanillaplus.leaderboardplugin;

import java.net.InetAddress;
import java.util.UUID;

public class PlayerKill {
    private UUID killer;
    private UUID victim;
    private Long time;

    private InetAddress victimIP;

    public PlayerKill(UUID killer, UUID victim, Long time, InetAddress victimIP) {
        this.killer = killer;
        this.victim = victim;
        this.time = time;
        this.victimIP = victimIP;
    }

    public UUID getKiller() {
        return killer;
    }

    public UUID getVictim() {
        return victim;
    }

    public Long getTime() {
        return time;
    }

    public InetAddress getVictimIP() {
        return victimIP;
    }
}

