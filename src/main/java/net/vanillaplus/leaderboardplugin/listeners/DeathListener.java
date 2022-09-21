package net.vanillaplus.leaderboardplugin.listeners;

import net.vanillaplus.leaderboardplugin.LeaderboardPlugin;
import net.vanillaplus.leaderboardplugin.PlayerKill;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        int points = 200;
        if(e.getEntity().getKiller() !=null){
            Player killer = e.getEntity().getKiller();
            Player victim = e.getEntity();
            LeaderboardPlugin.getPlugin().getLogger().info("Killer: " + killer.getName() + " Victim: " + victim.getName());


            //check if ip of victim and killer are the same
            //if they are the same return

            if(killer.getAddress().getAddress().equals(victim.getAddress().getAddress())){
               // LeaderboardPlugin.getPlugin().getLogger().info("ips r the same");
                return;
            }



            //if(hasArmor(killer)&&hasArmor(victim)){

                points += calcPoints(victim);
               // LeaderboardPlugin.getPlugin().getLogger().info("Both players have armor");

                if(LeaderboardPlugin.playerKills.containsKey(killer.getUniqueId())){
                    for(PlayerKill playerKill: LeaderboardPlugin.playerKills.get(killer.getUniqueId())){
                        if(playerKill.getVictim().equals(victim.getUniqueId())||playerKill.getVictimIP().equals(victim.getAddress().getAddress())){
                            if(playerKill.getTime()  > System.currentTimeMillis()){
                                //LeaderboardPlugin.getPlugin().getLogger().info("Player has killed this player/IP too recently");
                                return;
                            }

                        }
                    }
                    ArrayList<PlayerKill> playerKills = LeaderboardPlugin.playerKills.get(killer.getUniqueId());
                    playerKills.add(new PlayerKill(killer.getUniqueId(), victim.getUniqueId(), System.currentTimeMillis()+((1000*60*60)*1000/points), victim.getAddress().getAddress()));
                    LeaderboardPlugin.playerKills.put(killer.getUniqueId(),playerKills);
                    increaseKillCount(killer, points);

                        }else{

                    ArrayList<PlayerKill> playerKills = new ArrayList<>();
                    playerKills.add(new PlayerKill(killer.getUniqueId(), victim.getUniqueId(), System.currentTimeMillis()+((1000*60*60)*1000/points), victim.getAddress().getAddress()));
                    LeaderboardPlugin.playerKills.put(killer.getUniqueId(), playerKills);
                    increaseKillCount(killer, points);


                    }





            }




    }
    public void increaseKillCount(Player player, int points){
        if(LeaderboardPlugin.killCount.containsKey(player.getUniqueId())){
            LeaderboardPlugin.killCount.computeIfPresent(player.getUniqueId(), (k, v) -> v + points);
        }else {
            LeaderboardPlugin.killCount.put(player.getUniqueId(), points);
        }
    }


    public boolean hasArmor(Player player){
        //check if player has armor
        //if player has armor return
        //if player does not have armor add to leaderboard



        ItemStack[] armor = player.getInventory().getArmorContents();

        for(ItemStack item : armor){
            if(item == null){
                return false;
            }


            if(!(item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)||item.containsEnchantment(Enchantment.PROTECTION_EXPLOSIONS))){
                return false;
            }

            if(item.getType().name().toLowerCase().contains("diamond")){

                if(item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)){
                    if(item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) < 4){
                        return false;
                    }
                } else if (item.containsEnchantment(Enchantment.PROTECTION_EXPLOSIONS)){
                    if(item.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) < 4){
                        return false;
                    }
                }
            } else if (item.getType().name().toLowerCase().contains("netherite")) {

                if(item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)){
                    if(item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) < 3){
                        return false;
                    }
                } else if (item.containsEnchantment(Enchantment.PROTECTION_EXPLOSIONS)){
                    if(item.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) < 3){
                        return false;
                    }
                }

            }

        }

        return true;

    }

    public int calcPoints(Player player){
        //check if player has armor
        //if player has armor return
        //if player does not have armor add to leaderboard

        int points = 0;

        ItemStack[] armor = player.getInventory().getArmorContents();

        for(ItemStack item : armor){
            if(item == null){
                continue;
            }


            if(!(item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)||item.containsEnchantment(Enchantment.PROTECTION_EXPLOSIONS))){
                continue;
            }

            if(item.getType().name().toLowerCase().contains("diamond")){

                if(item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)){
                    if(item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) >= 4){
                        points += 200;
                    }
                } else if (item.containsEnchantment(Enchantment.PROTECTION_EXPLOSIONS)){
                    if(item.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) >= 4){
                        points += 200;
                    }
                }
            } else if (item.getType().name().toLowerCase().contains("netherite")) {

                if(item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)){
                    if(item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) >= 3){
                        points += 200;
                    }
                } else if (item.containsEnchantment(Enchantment.PROTECTION_EXPLOSIONS)){
                    if(item.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) >= 3){
                        points += 200;
                    }
                }

            }

        }

        return points;

    }


}
