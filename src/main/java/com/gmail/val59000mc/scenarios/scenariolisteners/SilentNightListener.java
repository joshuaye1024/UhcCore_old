package com.gmail.val59000mc.scenarios.scenariolisteners;

import com.gmail.val59000mc.events.UhcTimeEvent;
import com.gmail.val59000mc.exceptions.UhcPlayerDoesntExistException;
import com.gmail.val59000mc.exceptions.UhcPlayerNotOnlineException;
import com.gmail.val59000mc.languages.Lang;
import com.gmail.val59000mc.players.PlayerState;
import com.gmail.val59000mc.players.UhcPlayer;
import com.gmail.val59000mc.scenarios.Scenario;
import com.gmail.val59000mc.scenarios.ScenarioListener;
import com.gmail.val59000mc.utils.ProtocolUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SilentNightListener extends ScenarioListener{

    private boolean nightMode;

    public SilentNightListener(){
        nightMode = false;
    }

    @Override
    public void onEnable() {
        // Disable Anonymous when enabled.
        if (isActivated(Scenario.ANONYMOUS)){
            getScenarioManager().removeScenario(Scenario.ANONYMOUS);
        }

        // Check for ProtocolLib
        if (!getGameManager().getConfiguration().getProtocolLibLoaded()){
            Bukkit.broadcastMessage(ChatColor.RED + "[UhcCore] For Anonymous ProtocolLib needs to be installed!");
            getScenarioManager().removeScenario(Scenario.SILENTNIGHT);
        }
    }

    @EventHandler
    public void onUhcTime(UhcTimeEvent e){
        boolean night = isNight();

        // Same mode
        if (nightMode == night){
            return;
        }

        // Change mode
        nightMode = night;
        setMode(nightMode);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPlayerDeath(PlayerDeathEvent e){
        if (nightMode){
            e.setDeathMessage(null);
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent e){
        if (nightMode){
            e.setJoinMessage(null);

            UhcPlayer uhcPlayer;

            try {
                uhcPlayer = getPlayersManager().getUhcPlayer(e.getPlayer());
            }catch (UhcPlayerDoesntExistException ex){
                ex.printStackTrace();
                return;
            }

            if (uhcPlayer.getState() == PlayerState.PLAYING){
                ProtocolUtils.setPlayerHeaderFooter(e.getPlayer(), getTabHeader(true), "");
            }
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPlayerQuit(PlayerQuitEvent e){
        if (nightMode){
            e.setQuitMessage(null);
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent e){
        if (nightMode && !e.isCancelled()){
            e.setCancelled(true);
            e.getPlayer().sendMessage(Lang.SCENARIO_SILENTNIGHT_ERROR);
        }
    }

    public boolean isNightMode() {
        return nightMode;
    }

    private void setMode(boolean night){
        if (night){
            getScenarioManager().addScenario(Scenario.ANONYMOUS);
        }else{
            getScenarioManager().removeScenario(Scenario.ANONYMOUS);
        }

        String tabHeader = getTabHeader(night);
        for (UhcPlayer uhcPlayer : getPlayersManager().getOnlinePlayingPlayers()){
            try {
                ProtocolUtils.setPlayerHeaderFooter(uhcPlayer.getPlayer(), tabHeader, "");
            }catch (UhcPlayerNotOnlineException ex){
                // Should always be online
            }
        }
    }

    private String getTabHeader(boolean night){
        if (night){
            StringBuilder sb = new StringBuilder(Lang.SCENARIO_SILENTNIGHT_ERROR);
            for (int i = 0; i < 100; i++) {
                sb.append('\n');
                sb.append(" ");
            }
            return sb.toString();
        }
        return "";
    }

    private boolean isNight(){
        long time = getGameManager().getLobby().getLoc().getWorld().getTime();
        return time > 12000;
    }

}