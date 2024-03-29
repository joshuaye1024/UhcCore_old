package com.gmail.val59000mc.scenarios.scenariolisteners;

import com.gmail.val59000mc.exceptions.UhcPlayerDoesntExistException;
import com.gmail.val59000mc.exceptions.UhcPlayerNotOnlineException;
import com.gmail.val59000mc.game.GameManager;
import com.gmail.val59000mc.players.UhcPlayer;
import com.gmail.val59000mc.scenarios.ScenarioListener;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class ChildrenLeftUnattended extends ScenarioListener{

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        UhcPlayer uhcPlayer;

        try {
            uhcPlayer = GameManager.getGameManager().getPlayersManager().getUhcPlayer(player);
        }catch (UhcPlayerDoesntExistException ex){
            ex.printStackTrace();
            return;
        }

        for (UhcPlayer uhcMember : uhcPlayer.getTeam().getOnlinePlayingMembers()){
            if (uhcMember == uhcPlayer) continue;

            try {
                giveTeamReward(uhcMember.getPlayer());
            }catch (UhcPlayerNotOnlineException ex){
                ex.printStackTrace();
            }


        }
    }

    private void giveTeamReward(Player player){
        Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        wolf.setTamed(true);
        wolf.setOwner(player);
        wolf.setAdult();

        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setMainEffect(PotionEffectType.SPEED);
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 8*60*20, 0);
        meta.addCustomEffect(potionEffect, true);

        meta.setDisplayName(ChatColor.WHITE + "Potion of Swiftness");

        potion.setItemMeta(meta);

        player.getWorld().dropItem(player.getLocation(), potion);
    }

}