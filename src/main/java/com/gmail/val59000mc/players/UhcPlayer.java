package com.gmail.val59000mc.players;

import com.gmail.val59000mc.customitems.Craft;
import com.gmail.val59000mc.customitems.CraftsManager;
import com.gmail.val59000mc.customitems.Kit;
import com.gmail.val59000mc.events.UhcPlayerStateChangedEvent;
import com.gmail.val59000mc.exceptions.UhcPlayerNotOnlineException;
import com.gmail.val59000mc.game.GameManager;
import com.gmail.val59000mc.languages.Lang;
import com.gmail.val59000mc.scenarios.Scenario;
import com.gmail.val59000mc.utils.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public class UhcPlayer {
	private String name;
	private String displayName;
	private UUID uuid;
	private Scoreboard scoreboard;
	private UhcTeam team;
	private PlayerState state;
	private Location freezeLocation;
	private boolean globalChat;
	private Kit kit;
	private Map<String,Integer> craftedItems;
	private boolean hasBeenTeleportedToLocation;
	private Set<Scenario> scenarioVotes;

	public int kills = 0;

	private UhcPlayer compassPlayingCurrentPlayer;
	private long compassPlayingLastUpdate;

	public UhcPlayer(UUID uuid, String name){
		this.uuid = uuid;
		this.name = name;
		this.team = new UhcTeam(this);
		setState(PlayerState.WAITING);
		this.globalChat = false;
		this.kit = null;
		this.craftedItems = new HashMap<>();
		this.hasBeenTeleportedToLocation = false;
		scenarioVotes = new HashSet<>();

		compassPlayingCurrentPlayer = this;
	}

	public Player getPlayer() throws UhcPlayerNotOnlineException {
		Player player = Bukkit.getPlayer(uuid);
		if(player != null)
			return player;
		throw new UhcPlayerNotOnlineException(name);
	}

	public Boolean isOnline(){
		Player player = Bukkit.getPlayer(uuid);
		if(player == null)
			return false;
		return true;
	}

	public String getName() {
		if (displayName != null){
			return displayName;
		}
		return name;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean hasDisplayName(){
		return displayName != null;
	}

	public UUID getUuid() {
		return uuid;
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public synchronized UhcTeam getTeam(){
		return team;
	}

	public synchronized void setTeam(UhcTeam team) {
		this.team = team;
	}

	public PlayerState getState() {
		return state;
	}

	public void setState(PlayerState state) {
		if (this.state == state){
			return; // Don't change the player state when the same.
		}

		PlayerState oldState = this.state;
		this.state = state;

		// Call UhcPlayerStateChangedEvent
		Bukkit.getPluginManager().callEvent(new UhcPlayerStateChangedEvent(this, oldState, state));
	}

	public boolean isFrozen() {
		return freezeLocation != null;
	}

	public Location getFreezeLocation(){
		return freezeLocation;
	}

	public void freezePlayer(Location location){
		freezeLocation = location;
	}

	public void releasePlayer(){
		freezeLocation = null;
	}

	public synchronized Set<Scenario> getScenarioVotes() {
		return scenarioVotes;
	}

	public boolean addCraftedItem(String craftName){

		Integer quantity = 0;
		if(craftedItems.containsKey(craftName)){
			quantity = craftedItems.get(craftName);
		}

		Craft craft = CraftsManager.getCraftByName(craftName);
		if(craft != null && (craft.getLimit() == -1 || quantity+1 <= craft.getLimit())){
			craftedItems.put(craftName,	quantity+1);
			return true;
		}

		return false;
	}

	public void setUpScoreboard() {

		GameManager gm = GameManager.getGameManager();

		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

		if (gm.getConfiguration().getHeartsOnTab()) {
			Objective health = VersionUtils.getVersionUtils().registerObjective(scoreboard, "health_tab", "health");
			health.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		}

		if (gm.getConfiguration().getHeartsBelowName()) {
			Objective health = VersionUtils.getVersionUtils().registerObjective(scoreboard, ChatColor.RED + "\u2764", "health");
			health.setDisplaySlot(DisplaySlot.BELOW_NAME);
		}

		gm.getScoreboardManager().setUpPlayerScoreboard(this);

		try {
			getPlayer().setScoreboard(scoreboard);
		} catch (UhcPlayerNotOnlineException e) {
			// No scoreboard for offline players
		}
	}

	public boolean isInTeamWith(UhcPlayer player){
		return team.contains(player);
	}

	public boolean isTeamLeader(){
		return getTeam().getMembers().get(0).equals(this);
	}

	public boolean canJoinATeam(){
		return (getTeam().getMembers().get(0).equals(this) && getTeam().getMembers().size() == 1);
	}

	public boolean canLeaveTeam(){
		return (getTeam().getMembers().size() > 1);
	}

	public void sendMessage(String message){
		try {
			getPlayer().sendMessage(message);
		} catch (UhcPlayerNotOnlineException e) {
			// No message to send
		}
	}

	public boolean isGlobalChat() {
		return globalChat;
	}

	public void setGlobalChat(boolean globalChat) {
		this.globalChat = globalChat;
	}

	public void pointCompassToNextPlayer(int mode, int cooldown) {
		PlayersManager pm = GameManager.getGameManager().getPlayersManager();
		List<UhcPlayer> pointPlayers = new ArrayList<>();

		// Check cooldown
		if (cooldown != -1 && (cooldown*1000) + compassPlayingLastUpdate > System.currentTimeMillis()){
			sendMessage(Lang.ITEMS_COMPASS_PLAYING_COOLDOWN);
			return;
		}

		switch (mode){
			case 1:
				pointPlayers.addAll(team.getOnlinePlayingMembers());
				break;
			case 2:
				pointPlayers.addAll(pm.getOnlinePlayingPlayers());
				for (UhcPlayer teamMember : team.getOnlinePlayingMembers()){
					pointPlayers.remove(teamMember);
				}
				break;
			case 3:
				pointPlayers.addAll(pm.getOnlinePlayingPlayers());
				break;
		}

		if((pointPlayers.size() == 1 && pointPlayers.get(0).equals(this)) || pointPlayers.size() == 0){
			sendMessage(ChatColor.RED+ Lang.ITEMS_COMPASS_PLAYING_ERROR);
		}else{
			int currentIndice = -1;
			for(int i = 0 ; i < pointPlayers.size() ; i++){
				if(pointPlayers.get(i).equals(compassPlayingCurrentPlayer))
					currentIndice = i;
			}

			// Switching to next player
			if(currentIndice == pointPlayers.size()-1)
				currentIndice = 0;
			else
				currentIndice++;


			// Skipping player if == this
			if(pointPlayers.get(currentIndice).equals(this))
				currentIndice++;

			// Correct indice if out of bounds
			if(currentIndice == pointPlayers.size())
				currentIndice = 0;


			// Pointing compass
			compassPlayingCurrentPlayer = pointPlayers.get(currentIndice);
			compassPlayingLastUpdate = System.currentTimeMillis();
			try {
				Player bukkitPlayer = getPlayer();
				Player bukkitPlayerPointing = compassPlayingCurrentPlayer.getPlayer();

				bukkitPlayer.setCompassTarget(bukkitPlayerPointing.getLocation());

				String message = ChatColor.GREEN+ Lang.ITEMS_COMPASS_PLAYING_POINTING.replace("%player%", compassPlayingCurrentPlayer.getName());

				if (message.contains("%distance%")){
					int distance = (int) bukkitPlayer.getLocation().distance(bukkitPlayerPointing.getLocation());
					message = message.replace("%distance%", String.valueOf(distance));
				}

				sendMessage(message);
			} catch (UhcPlayerNotOnlineException e) {
				sendMessage(ChatColor.RED+ Lang.TEAM_PLAYER_NOT_ONLINE.replace("%player%", compassPlayingCurrentPlayer.getName()));
			}
		}

	}

	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

	public void selectDefaultGlobalChat() {
		if (team.getMembers().size() == 1) {
			setGlobalChat(true);
		}
	}

	public Location getStartingLocation(){
		return team.getStartingLocation();
	}

	public boolean getHasBeenTeleportedToLocation() {
		return hasBeenTeleportedToLocation;
	}

	public void setHasBeenTeleportedToLocation(boolean hasBeenTeleportedToLocation) {
		this.hasBeenTeleportedToLocation = hasBeenTeleportedToLocation;
	}

}