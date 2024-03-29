package com.gmail.val59000mc.scenarios;

import com.gmail.val59000mc.game.GameManager;
import com.gmail.val59000mc.players.PlayersManager;
import com.gmail.val59000mc.scoreboard.ScoreboardManager;
import org.bukkit.event.Listener;

public abstract class ScenarioListener implements Listener{

    public GameManager getGameManager(){
        return GameManager.getGameManager();
    }

    public PlayersManager getPlayersManager(){
        return getGameManager().getPlayersManager();
    }

    public ScoreboardManager getScoreboardManager(){
        return getGameManager().getScoreboardManager();
    }

    public ScenarioManager getScenarioManager(){
        return getGameManager().getScenarioManager();
    }

    public boolean isActivated(Scenario scenario){
        return getScenarioManager().isActivated(scenario);
    }

    public void onEnable(){}

    public void onDisable(){}

}