package com.gmail.val59000mc.configuration;

import com.gmail.val59000mc.UhcCore;
import com.gmail.val59000mc.game.GameManager;
import com.gmail.val59000mc.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.*;

public class MainConfiguration {

	// Config options.
	private int timeBeforePvp;
	private boolean enableFriendlyFire;
	private boolean disableEnemyNametags;
	private int minimalReadyTeamsPercentageToStart;
	private int minimalReadyTeamsToStart;
	private int minPlayersToStart;
	private int maxPlayersPerTeam;
	private boolean teamColors;
	private boolean changeDisplayNames;
	private int timeBeforeStartWhenReady;
	private boolean canSpectateAfterDeath;
	private boolean canSendMessagesAfterDeath;
	private boolean enableChatPrefix;
	private String teamChatPrefix;
	private String globalChatPrefix;
	private boolean disableMotd;
	private Difficulty gameDifficulty;
	private String overworldUuid;
	private String netherUuid;
	private String theEndUuid;
	private boolean pickRandomSeedFromList;
	private List<Long> seeds;
	private boolean pickRandomWorldFromList;
	private boolean pickRandomNetherWorldFromList;
	private boolean pickRandomEndWorldFromList;
	private List<String> worldsList;
	private List<String> netherworldsList;
	private List<String> endworldsList;
	private boolean enablePlayingCompass;
	private int playingCompassMode;
	private int playingCompassCooldown;
	private boolean heartsOnTab;
	private boolean heartsBelowName;
	private boolean spectatingTeleport;
	private boolean enableKitsPermissions;
	private boolean enableCraftsPermissions;
	private boolean enableExtraHalfHearts;
	private int extraHalfHearts;
	private boolean enableGoldDrops;
	private int minGoldDrops;
	private int maxGoldDrops;
	private List<EntityType> affectedGoldDropsMobs;
	private int goldDropPercentage;
	private boolean enableEpisodeMarkers;
	private long episodeMarkersDelay;
	private boolean enableScenarioVoting;
	private int maxScenarioVotes;
	private int electedScenaroCount;
	private Set<Scenario> scenarioBlackList;
	private boolean enableExpDropOnDeath;
	private int expDropOnDeath;
	private boolean enableKillDisconnectedPlayers;
	private int maxDisconnectPlayersTime;
	private boolean enableBungeeSupport;
	private boolean enableBungeeLobbyItem;
	private String serverBungee;
	private int timeBeforeSendBungeeAfterDeath;
	private int timeBeforeSendBungeeAfterEnd;
	private long timeToShrink;
	private long timeLimit;
	private boolean enableTimeLimit;
	private int maxBuildingHeight;
	private boolean banNether;
	private boolean enableTheEnd;
	private boolean banLevelTwoPotions;
	private boolean enableDayNightCycle;
	private long timeBeforePermanentDay;
	private boolean borderIsMoving;
	private long borderTimeBeforeShrink;
	private boolean deathmatchAdvantureMode;

	// Arena deathmatch
	private int arenaPasteAtY;
	private Material arenaTeleportSpotBLock;

	// Center deathmatch
	private int deathmatchStartSize;
	private int deathmatchEndSize;
	private long deathmatchTimeToShrink;

	private boolean regenHeadDropOnPlayerDeath;
	private boolean doubleRegenHead;
	private boolean enableGoldenHeads;
	private boolean placeHeadOnFence;
	private boolean allowGhastTearsDrops;
	private Sound soundOnPlayerDeath;
	private boolean autoAssignNewPlayerTeam;
	private boolean forceAssignSoloPlayerToTeamWhenStarting;
	private boolean preventPlayerFromLeavingTeam;
	private boolean teamAlwaysReady;
	private long timeBeforeRestartAfterEnd;
	private List<PotionEffect> potionEffectOnStart;
	private boolean canJoinAsSpectator;
	private boolean endGameWhenAllPlayersHaveLeft;
	private boolean debug; // debug: true to skip map loading and load old world
	private boolean onePlayerMode;
	private int restEveryTicks;
	private int chunksPerTick;
	private int restDuraton;
	private boolean enablePregenerateWorld;

	// custom events
	private boolean enableTimeEvent;
	private double rewardTimeEvent;
	private long intervalTimeEvent;
	private boolean enableKillEvent;
	private double rewardKillEvent;
	private boolean enableWinEvent;
	private double rewardWinEnvent;

	// fast mode
	private boolean enableFinalHeal;
	private long finalHealDelay;
	private boolean enableUndergroundNether;
	private int netherPasteAtY;
	private int minOccurrencesUndergroundNether;
	private int maxOccurrencesUndergroundNether;
	private boolean enableGenerateSugarcane;
	private int generateSugarcanePercentage;
	private double appleDropPercentage;
	private boolean appleDropsFromAllTrees;
	private boolean appleDropsFromShearing;
	private boolean replaceOceanBiomes;
	private boolean enableGenerateVein;
	private Map<Material,GenerateVeinConfiguration> generateVeins;
	private boolean enableBlockLoots;
	private Map<Material,BlockLootConfiguration> blockLoots;
	private boolean enableMobLoots;
	private Map<EntityType,MobLootConfiguration> mobLoots;

	// dependencies
	private boolean worldEditLoaded;
	private boolean vaultLoaded;
	private boolean protocolLibLoaded;


	public void load(YamlFile cfg, YamlFile storage){
		minimalReadyTeamsPercentageToStart = cfg.getInt("minimal-ready-teams-percentage-to-start",50);
		minimalReadyTeamsToStart = cfg.getInt("minimal-ready-teams-to-start",2);
		minPlayersToStart = cfg.getInt("min-players-to-start",0);
		maxPlayersPerTeam = cfg.getInt("max-players-per-team",2);
		teamColors = cfg.getBoolean("use-team-colors",true);
		changeDisplayNames = cfg.getBoolean("change-display-names",false);
		timeBeforeStartWhenReady = cfg.getInt("time-to-start-when-ready",15);
		canSpectateAfterDeath = cfg.getBoolean("can-spectate-after-death",false);
		canSendMessagesAfterDeath = cfg.getBoolean("can-send-messages-after-death",true);
		enableChatPrefix = cfg.getBoolean("chat-prefix.enable",false);
		teamChatPrefix = cfg.getString("chat-prefix.team-prefix","@");
		globalChatPrefix = cfg.getString("chat-prefix.global-prefix","!");
		disableMotd = cfg.getBoolean("disable-motd", false);
		timeBeforePvp = cfg.getInt("time-before-pvp",600);
		enableFriendlyFire = cfg.getBoolean("enable-friendly-fire",false);
		disableEnemyNametags = cfg.getBoolean("disable-enemy-nametags",false);
		pickRandomSeedFromList = cfg.getBoolean("world-seeds.pick-random-seed-from-list",false);
		pickRandomWorldFromList = cfg.getBoolean("world-list.pick-random-world-from-list",false);
		pickRandomNetherWorldFromList = cfg.getBoolean("nether-world-list.pick-random-nether-world-from-list",false);
		pickRandomEndWorldFromList = cfg.getBoolean("end-world-list.pick-random-end-world-from-list",false);
		enablePlayingCompass = cfg.getBoolean("playing-compass.enable",true);
		playingCompassMode = cfg.getInt("playing-compass.mode",1);
		playingCompassCooldown = cfg.getInt("playing-compass.cooldown",-1);
		heartsOnTab = cfg.getBoolean("hearts-on-tab", true);
		heartsBelowName = cfg.getBoolean("hearts-below-name", false);
		spectatingTeleport = cfg.getBoolean("spectating-teleport",false);
		enableKitsPermissions = cfg.getBoolean("enable-kits-permissions",false);
		enableCraftsPermissions = cfg.getBoolean("customize-game-behavior.enable-crafts-permissions",false);
		enableExtraHalfHearts = cfg.getBoolean("customize-game-behavior.add-player-extra-half-hearts.enable",false);
		extraHalfHearts = cfg.getInt("customize-game-behavior.add-player-extra-half-hearts.extra-half-hearts",0);
		enableGoldDrops = cfg.getBoolean("customize-game-behavior.add-gold-drops.enable",false);
		minGoldDrops = cfg.getInt("customize-game-behavior.add-gold-drops.min",0);
		maxGoldDrops = cfg.getInt("customize-game-behavior.add-gold-drops.max",0);
		goldDropPercentage = cfg.getInt("customize-game-behavior.add-gold-drops.drop-chance-percentage",0);
		enableEpisodeMarkers = cfg.getBoolean("episode-markers.enable",false);
		episodeMarkersDelay = cfg.getLong("episode-markers.delay",900);
		enableScenarioVoting = cfg.getBoolean("customize-game-behavior.scenarios.voting.enable", false);
		maxScenarioVotes = cfg.getInt("customize-game-behavior.scenarios.voting.max-votes", 3);
		electedScenaroCount = cfg.getInt("customize-game-behavior.scenarios.voting.elected-scenarios", 3);
		enableExpDropOnDeath = cfg.getBoolean("customize-game-behavior.add-xp-drops-on-player-death.enable",false);
		expDropOnDeath = cfg.getInt("customize-game-behavior.add-xp-drops-on-player-death.quantity",0);
		enableKillDisconnectedPlayers = cfg.getBoolean("kill-disconnected-players-after-delay.enable",false);
		maxDisconnectPlayersTime = cfg.getInt("kill-disconnected-players-after-delay.delay",60);
		enableBungeeSupport = cfg.getBoolean("bungee-support.enable",false);
		enableBungeeLobbyItem = cfg.getBoolean("bungee-support.use-lobby-item",true);
		serverBungee = cfg.getString("bungee-support.send-players-to-server-after-end","lobby");
		timeBeforeSendBungeeAfterDeath = cfg.getInt("bungee-support.time-before-send-after-death",-1);
		timeBeforeSendBungeeAfterEnd = cfg.getInt("bungee-support.time-before-send-after-end",-1);
		timeToShrink = cfg.getLong("border.time-to-shrink",3600);
		enableTimeLimit = cfg.getBoolean("time-limit.enable",false);
		timeLimit = cfg.getLong("time-limit.limit",timeToShrink);
		borderIsMoving = cfg.getBoolean("border.moving",false);
		borderTimeBeforeShrink = cfg.getLong("border.time-before-shrink",0);
		deathmatchAdvantureMode = cfg.getBoolean("time-limit.deathmatch-adventure-mode",true);
		arenaPasteAtY = cfg.getInt("time-limit.arena-deathmatch.paste-at-y",100);
		deathmatchStartSize = cfg.getInt("time-limit.center-deathmatch.start-size",125);
		deathmatchEndSize = cfg.getInt("time-limit.center-deathmatch.end-size",50);
		deathmatchTimeToShrink = cfg.getInt("time-limit.center-deathmatch.time-to-shrink",600);
		regenHeadDropOnPlayerDeath = cfg.getBoolean("customize-game-behavior.add-regen-head-drop-on-player-death",true);
		doubleRegenHead = cfg.getBoolean("customize-game-behavior.double-regen-head",false);
		enableGoldenHeads = cfg.getBoolean("customize-game-behavior.enable-golden-heads",false);
		placeHeadOnFence = cfg.getBoolean("customize-game-behavior.place-head-on-fence",false);
		allowGhastTearsDrops = cfg.getBoolean("customize-game-behavior.allow-ghast-tears-drops",true);
		autoAssignNewPlayerTeam = cfg.getBoolean("auto-assign-new-player-team",false);
		forceAssignSoloPlayerToTeamWhenStarting = cfg.getBoolean("force-assign-solo-player-to-team-when-starting",false);
		preventPlayerFromLeavingTeam = cfg.getBoolean("prevent-player-from-leaving-team",false);
		teamAlwaysReady = cfg.getBoolean("team-always-ready",false);
		timeBeforeRestartAfterEnd = cfg.getLong("time-before-restart-after-end",30);
		canJoinAsSpectator = cfg.getBoolean("can-join-as-spectator",false);
		endGameWhenAllPlayersHaveLeft = cfg.getBoolean("countdown-ending-game-when-all-players-have-left",true);
		debug = cfg.getBoolean("debug",false);
		onePlayerMode = cfg.getBoolean("one-player-mode",false);
		maxBuildingHeight = cfg.getInt("customize-game-behavior.max-building-height", -1);
		banNether = cfg.getBoolean("customize-game-behavior.ban-nether",false);
		enableTheEnd = cfg.getBoolean("customize-game-behavior.enable-the-end",false);
		banLevelTwoPotions = cfg.getBoolean("customize-game-behavior.ban-level-2-potions",false);
		enableDayNightCycle = cfg.getBoolean("customize-game-behavior.day-night-cycle.enable",false);
		timeBeforePermanentDay = cfg.getLong("customize-game-behavior.day-night-cycle.time-before-permanent-day",1200);
		enablePregenerateWorld = cfg.getBoolean("pre-generate-world.enable",false);
		restEveryTicks = cfg.getInt("pre-generate-world.rest-every-ticks",20);
		chunksPerTick = UhcCore.getPlugin().getConfig().getInt("pre-generate-world.chunks-per-tick",10);
		restDuraton = UhcCore.getPlugin().getConfig().getInt("pre-generate-world.rest-duration",20);

		if (storage != null){
			overworldUuid = storage.getString("worlds.normal","NULL");
			netherUuid = storage.getString("worlds.nether","NULL");
			theEndUuid = storage.getString("worlds.the_end","NULL");
		}

		// Game difficulty
		try {
			gameDifficulty = Difficulty.valueOf(cfg.getString("game-difficulty", "HARD"));
		}catch (IllegalArgumentException e){
			Bukkit.getLogger().severe("[UhcCore] Invalid game difficulty!");
			Bukkit.getLogger().severe(e.getMessage());
			gameDifficulty = Difficulty.HARD;
		}

		// Scenarios
		if (cfg.getBoolean("customize-game-behavior.enable-default-scenarios", false)){
			GameManager.getGameManager().getScenarioManager().loadActiveScenarios(cfg.getStringList("customize-game-behavior.active-scenarios"));
		}

		// Scenario blacklist
		scenarioBlackList = new HashSet<>();
		for (String s : cfg.getStringList("customize-game-behavior.scenarios.voting.black-list")){
			try {
				scenarioBlackList.add(Scenario.valueOf(s));
			}catch (IllegalArgumentException ex){
				Bukkit.getLogger().severe("[UhcCore] Invalid scenario: " + s);
				System.out.println(ex.getMessage());
			}
		}

		// SOund on player death
		String soundDeath = cfg.getString("customize-game-behavior.sound-on-player-death","false");
		try{
			soundOnPlayerDeath = Sound.valueOf(soundDeath);
		}catch(IllegalArgumentException e){
			Bukkit.getLogger().info("[UhcCore] Invalid death sound: " + soundDeath);
			soundOnPlayerDeath = null;
		}

		// Arena spot block
		String spotBlock = cfg.getString("time-limit.arena-deathmatch.teleport-spots-block","BEDROCK");
		try{
			arenaTeleportSpotBLock = Material.valueOf(spotBlock);
		}catch(IllegalArgumentException e){
			Bukkit.getLogger().info("[UhcCore] Invalid deathmatch arena teleport block: " + spotBlock);
			arenaTeleportSpotBLock = Material.BEDROCK;
		}

		// Set remaining time
		if(enableTimeLimit){
			GameManager.getGameManager().setRemainingTime(timeLimit);
		}

		// Potions effects on start
		List<String> potionStrList = cfg.getStringList("potion-effect-on-start");
		List<PotionEffect> potionList = new ArrayList<PotionEffect>();
		if(potionStrList == null){
			potionEffectOnStart = potionList;
		}else{
			for(String potionStr : potionStrList){
				try{
					String[] potionArr = potionStr.split("/");
					int duration = Integer.parseInt(potionArr[1]);
					int amplifier = Integer.parseInt(potionArr[2]);
					PotionEffect effect = new PotionEffect(PotionEffectType.getByName(potionArr[0].toUpperCase()),duration,amplifier);
					potionList.add(effect);
				}catch(Exception e){
					Bukkit.getLogger().warning("[UhcCore] "+potionStr+" ignored, please check the syntax. It must be formated like POTION_NAME/duration/amplifier");
				}
			}
			potionEffectOnStart = potionList;
		}

		// Mobs gold drops
		List<String> mobsGoldDrop = cfg.getStringList("customize-game-behavior.add-gold-drops.affected-mobs");
		List<EntityType> mobsType = new ArrayList<EntityType>();
		if(mobsGoldDrop != null){
			for(String mobTypeString : mobsGoldDrop){
				try{
					EntityType mobType = EntityType.valueOf(mobTypeString);
					mobsType.add(mobType);
				}catch(IllegalArgumentException e){
					Bukkit.getLogger().warning("[UhcCore] " + mobTypeString+" is not a valid mob type");
				}
			}

			affectedGoldDropsMobs = mobsType;
		}

		// Seed list
		List<Long> choosenSeed = cfg.getLongList("world-seeds.list");
		if(choosenSeed == null)
			seeds = new ArrayList<Long>();
		else
			seeds = choosenSeed;


		// World list
		List<String> worldList = cfg.getStringList("world-list.list");
		worldsList = (worldList == null) ? new ArrayList<String>() : worldList;
		
		// Nether World list
		List<String> netherworldList = cfg.getStringList("nether-world-list.list");
		netherworldsList = (netherworldList == null) ? new ArrayList<String>() : netherworldList;
		
		// End World list
		List<String> endworldList = cfg.getStringList("end-world-list.list");
		endworldsList = (endworldList == null) ? new ArrayList<String>() : endworldList;


		// Fast Mode
		enableFinalHeal = cfg.getBoolean("fast-mode.final-heal.enable", false);
		finalHealDelay = cfg.getLong("fast-mode.final-heal.delay", 1200);
		enableUndergroundNether = cfg.getBoolean("fast-mode.underground-nether.enable",false);
		netherPasteAtY =  cfg.getInt("fast-mode.underground-nether.paste-nether-at-y",20);
		minOccurrencesUndergroundNether = cfg.getInt("fast-mode.underground-nether.min-ocurrences",5);
		maxOccurrencesUndergroundNether = cfg.getInt("fast-mode.underground-nether.min-ocurrences",10);
		enableGenerateSugarcane = cfg.getBoolean("fast-mode.generate-sugar-cane.enable",false);
		generateSugarcanePercentage = cfg.getInt("fast-mode.generate-sugar-cane.percentage",10);
		appleDropPercentage = cfg.getDouble("fast-mode.apple-drops.percentage", 0.5);
		appleDropsFromAllTrees = cfg.getBoolean("fast-mode.apple-drops.all-trees", false);
		appleDropsFromShearing = cfg.getBoolean("fast-mode.apple-drops.allow-shears", false);
		replaceOceanBiomes = cfg.getBoolean("fast-mode.replace-ocean-biomes", false);

		// Fast Mode, generate-vein
		enableGenerateVein = cfg.getBoolean("fast-mode.generate-vein.enable",false);
		generateVeins = new HashMap<Material,GenerateVeinConfiguration>();
		ConfigurationSection allVeinsSection = cfg.getConfigurationSection("fast-mode.generate-vein.veins");
		if(allVeinsSection != null){
			for(String veinSectionName : allVeinsSection.getKeys(false)){
				ConfigurationSection veinSection = allVeinsSection.getConfigurationSection(veinSectionName);
				GenerateVeinConfiguration veinConfig = new GenerateVeinConfiguration();
				if(veinConfig.parseConfiguration(veinSection)){
					generateVeins.put(veinConfig.getMaterial(),veinConfig);
				}
			}
		}

		// Fast Mode, block-loot
		enableBlockLoots = cfg.getBoolean("fast-mode.block-loot.enable",false);
		blockLoots = new HashMap<Material,BlockLootConfiguration>();
		ConfigurationSection allBlockLootsSection = cfg.getConfigurationSection("fast-mode.block-loot.loots");
		if(allBlockLootsSection != null){
			for(String blockLootSectionName : allBlockLootsSection.getKeys(false)){
				ConfigurationSection blockLootSection = allBlockLootsSection.getConfigurationSection(blockLootSectionName);
				BlockLootConfiguration blockLootConfig = new BlockLootConfiguration();
				if(blockLootConfig.parseConfiguration(blockLootSection)){
					blockLoots.put(blockLootConfig.getMaterial(),blockLootConfig);
				}
			}
		}

		// Fast Mode, mob-loot
		enableMobLoots = cfg.getBoolean("fast-mode.mob-loot.enable",false);
		mobLoots = new HashMap<EntityType,MobLootConfiguration>();
		ConfigurationSection allMobLootsSection = cfg.getConfigurationSection("fast-mode.mob-loot.loots");
		if(allMobLootsSection != null){
			for(String mobLootSectionName : allMobLootsSection.getKeys(false)){
				ConfigurationSection mobLootSection = allMobLootsSection.getConfigurationSection(mobLootSectionName);
				MobLootConfiguration mobLootConfig = new MobLootConfiguration();
				if(mobLootConfig.parseConfiguration(mobLootSection)){
					mobLoots.put(mobLootConfig.getEntityType(),mobLootConfig);
				}
			}
		}


		// custom events
		enableTimeEvent = cfg.getBoolean("custom-events.time.enable",false);
		rewardTimeEvent = cfg.getDouble("custom-events.time.reward",0);
		intervalTimeEvent = cfg.getLong("custom-events.time.interval",600);
		enableKillEvent = cfg.getBoolean("custom-events.kill.enable",false);
		rewardKillEvent = cfg.getDouble("custom-events.kill.reward");
		enableWinEvent = cfg.getBoolean("custom-events.win.enable",false);
		rewardWinEnvent = cfg.getDouble("custom-events.win.reward",0);

		if (cfg.addedDefaultValues()) {
			try {
				cfg.saveWithComments();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public void loadWorldEdit() {
		Plugin wePlugin = Bukkit.getPluginManager().getPlugin("WorldEdit");
		if(wePlugin == null || !wePlugin.getClass().getName().equals("com.sk89q.worldedit.bukkit.WorldEditPlugin")) {
			Bukkit.getLogger().warning("[UhcCore] WorldEdit plugin not found, there will be no support of schematics.");
			worldEditLoaded = false;
		}else {
			Bukkit.getLogger().info("[UhcCore] Hooked with WorldEdit plugin.");
			worldEditLoaded = true;
		}
	}

	public void loadVault(){
		Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
		if(vault == null || !vault.getClass().getName().equals("net.milkbowl.vault.Vault")) {
			Bukkit.getLogger().warning("[UhcCore] Vault plugin not found, there will be no support of economy rewards.");
			vaultLoaded = false;
		}else{
			Bukkit.getLogger().info("[UhcCore] Hooked with Vault plugin.");
			vaultLoaded = true;
		}
	}

	public void loadProtocolLib(){
		Plugin protocolLib = Bukkit.getPluginManager().getPlugin("ProtocolLib");
		if(protocolLib == null || !protocolLib.getClass().getName().equals("com.comphenix.protocol.ProtocolLib")) {
			Bukkit.getLogger().warning("[UhcCore] ProtocolLib plugin not found.");
			protocolLibLoaded = false;
		}else{
			Bukkit.getLogger().info("[UhcCore] Hooked with ProtocolLib plugin.");
			protocolLibLoaded = true;
		}
	}

	public boolean getForceAssignSoloPlayerToTeamWhenStarting() {
		return forceAssignSoloPlayerToTeamWhenStarting;
	}


	public int getTimeBeforeSendBungeeAfterDeath() {
		return timeBeforeSendBungeeAfterDeath;
	}

	public int getTimeBeforeSendBungeeAfterEnd() {
		return timeBeforeSendBungeeAfterEnd;
	}

	public boolean getEnableTimeEvent() {
		return enableTimeEvent;
	}


	public double getRewardTimeEvent() {
		return rewardTimeEvent;
	}


	public long getIntervalTimeEvent() {
		return intervalTimeEvent;
	}


	public boolean getEnableKillEvent() {
		return enableKillEvent;
	}


	public double getRewardKillEvent() {
		return rewardKillEvent;
	}


	public boolean getEnableWinEvent() {
		return enableWinEvent;
	}


	public double getRewardWinEnvent() {
		return rewardWinEnvent;
	}


	public int getNetherPasteAtY() {
		return netherPasteAtY;
	}

	public int getArenaPasteAtY() {
		return arenaPasteAtY;
	}

	public boolean getWorldEditLoaded() {
		return worldEditLoaded;
	}

	public boolean getVaultLoaded() {
		return vaultLoaded;
	}

	public boolean getProtocolLibLoaded(){
		return protocolLibLoaded;
	}

	public int getMinPlayersToStart() {
		return minPlayersToStart;
	}

	public boolean getEnableGenerateVein() {
		return enableGenerateVein;
	}

	public boolean getEnableBlockLoots() {
		return enableBlockLoots;
	}

	public boolean getEnableMobLoots() {
		return enableMobLoots;
	}

	public int getRestEveryTicks() {
		return restEveryTicks;
	}

	public int getChunksPerTick() {
		return chunksPerTick;
	}

	public int getRestDuraton() {
		return restDuraton;
	}

	public boolean getEnablePregenerateWorld() {
		return enablePregenerateWorld;
	}

	public Map<Material, GenerateVeinConfiguration> getGenerateVeins() {
		return generateVeins;
	}

	public Map<Material, BlockLootConfiguration> getBlockLoots() {
		return blockLoots;
	}

	public Map<EntityType, MobLootConfiguration> getMobLoots() {
		return mobLoots;
	}

	public boolean getEnableFinalHeal(){
		return enableFinalHeal;
	}

	public long getFinalHealDelay(){
		return finalHealDelay;
	}

	public boolean getEnableUndergroundNether() {
		return enableUndergroundNether;
	}

	public int getMinOccurrencesUndergroundNether() {
		return minOccurrencesUndergroundNether;
	}

	public int getMaxOccurrencesUndergroundNether() {
		return maxOccurrencesUndergroundNether;
	}

	public boolean getEnableGenerateSugarcane(){
		return enableGenerateSugarcane;
	}

	public int getGenerateSugarcanePercentage(){
		return generateSugarcanePercentage;
	}

	public double getAppleDropPercentage(){
		return appleDropPercentage;
	}

	public boolean getAppleDropsFromAllTrees(){
		return appleDropsFromAllTrees;
	}

	public boolean getAppleDropsFromShearing(){
		return appleDropsFromShearing;
	}

	public boolean getReplaceOceanBiomes(){
	    return replaceOceanBiomes;
    }

	public Map<Material, GenerateVeinConfiguration> getMoreOres() {
		return generateVeins;
	}

	public boolean getDebug() {
		return debug;
	}

	public boolean getOnePlayerMode(){
		return onePlayerMode;
	}

	public boolean getEndGameWhenAllPlayersHaveLeft() {
		return endGameWhenAllPlayersHaveLeft;
	}

	public boolean getCanJoinAsSpectator() {
		return canJoinAsSpectator;
	}

	public List<PotionEffect> getPotionEffectOnStart() {
		return potionEffectOnStart;
	}

	public long getTimeBeforeRestartAfterEnd() {
		return timeBeforeRestartAfterEnd;
	}

	public boolean getTeamAlwaysReady() {
		return teamAlwaysReady;
	}

	public boolean getAutoAssignNewPlayerTeam() {
		return autoAssignNewPlayerTeam;
	}

	public boolean getPreventPlayerFromLeavingTeam() {
		return preventPlayerFromLeavingTeam;
	}

	public int getMinimalReadyTeamsPercentageToStart() {
		return minimalReadyTeamsPercentageToStart;
	}

	public boolean getPickRandomSeedFromList() {
		return pickRandomSeedFromList;
	}

	public List<Long> getSeeds() {
		return seeds;
	}

	public String getOverworldUuid() {
		return overworldUuid;
	}


	public String getNetherUuid() {
		return netherUuid;
	}

	public String getTheEndUuid() {
		return theEndUuid;
	}

	public boolean getEnablePlayingCompass() {
		return enablePlayingCompass;
	}

	public int getPlayingCompassMode() {
		return playingCompassMode;
	}

	public int getPlayingCompassCooldown() {
		return playingCompassCooldown;
	}

	public boolean getHeartsOnTab() {
		return heartsOnTab;
	}

	public boolean getHeartsBelowName() {
		return heartsBelowName;
	}

	public boolean getSpectatingTeleport() {
		return spectatingTeleport;
	}

	public int getTimeBeforePvp() {
		return timeBeforePvp;
	}

	public boolean getEnableFriendlyFire() {
		return enableFriendlyFire;
	}

	public boolean getDisableEnemyNametags(){
		return disableEnemyNametags;
	}

	public boolean getCanSpectateAfterDeath() {
		return canSpectateAfterDeath;
	}

	public boolean getCanSendMessagesAfterDeath() {
		return canSendMessagesAfterDeath;
	}

	public boolean getEnableChatPrefix() {
		return enableChatPrefix;
	}

	public String getTeamChatPrefix() {
		return teamChatPrefix;
	}

	public String getGlobalChatPrefix() {
		return globalChatPrefix;
	}

	public boolean getDisableMotd() {
		return disableMotd;
	}

	public Difficulty getGameDifficulty() {
		return gameDifficulty;
	}

	public boolean getEnableKitsPermissions() {
		return enableKitsPermissions;
	}

	public boolean getEnableCraftsPermissions() {
		return enableCraftsPermissions;
	}
	public boolean getEnableExtraHalfHearts() {
		return enableExtraHalfHearts;
	}

	public int getExtraHalfHearts() {
		return extraHalfHearts;
	}

	public boolean getEnableGoldDrops() {
		return enableGoldDrops;
	}

	public int getMinGoldDrops() {
		return minGoldDrops;
	}

	public int getMaxGoldDrops() {
		return maxGoldDrops;
	}

	public List<EntityType> getAffectedGoldDropsMobs() {
		return affectedGoldDropsMobs;
	}

	public int getGoldDropPercentage() {
		return goldDropPercentage;
	}

	public int getMinimalReadyTeamsToStart() {
		return minimalReadyTeamsToStart;
	}

	public int getMaxPlayersPerTeam() {
		return maxPlayersPerTeam;
	}

	public boolean getUseTeamColors(){
		return teamColors;
	}

	public boolean getChangeDisplayNames() {
		return changeDisplayNames;
	}

	public boolean getEnableEpisodeMarkers() {
		return enableEpisodeMarkers;
	}

	public long getEpisodeMarkersDelay() {
		return episodeMarkersDelay;
	}

	public boolean getEnableScenarioVoting() {
		return enableScenarioVoting;
	}

	public int getMaxScenarioVotes() {
		return maxScenarioVotes;
	}

	public int getElectedScenaroCount() {
		return electedScenaroCount;
	}

	public Set<Scenario> getScenarioBlackList() {
		return scenarioBlackList;
	}

	public boolean getEnableExpDropOnDeath() {
		return enableExpDropOnDeath;
	}

	public int getExpDropOnDeath() {
		return expDropOnDeath;
	}

	public boolean getEnableKillDisconnectedPlayers() {
		return enableKillDisconnectedPlayers;
	}

	public int getMaxDisconnectPlayersTime() {
		return maxDisconnectPlayersTime;
	}

	public boolean getPickRandomWorldFromList() {
		return pickRandomWorldFromList;
	}

	public List<String> getWorldsList() {
		return worldsList;
	}
	
	public List<String> getNetherWorldsList() {
		return netherworldsList;
	}
	
	public List<String> getEndWorldsList() {
		return endworldsList;
	}

	public boolean getEnableBungeeSupport() {
		return enableBungeeSupport;
	}

	public boolean getEnableBungeeLobbyItem() {
		return enableBungeeLobbyItem;
	}

	public String getServerBungee() {
		return serverBungee;
	}

	public long getTimeToShrink() {
		return timeToShrink;
	}

	public long getTimeLimit() {
		return timeLimit;
	}

	public boolean getEnableTimeLimit(){
		return enableTimeLimit;
	}

	public int getMaxBuildingHeight() {
		return maxBuildingHeight;
	}

	public boolean getBanNether() {
		return banNether;
	}

	public boolean getEnableTheEnd() {
		return enableTheEnd;
	}

	public boolean getBanLevelTwoPotions() {
		return banLevelTwoPotions;
	}

	public boolean getEnableDayNightCycle(){
		return enableDayNightCycle;
	}

	public long getTimeBeforePermanentDay(){
		return timeBeforePermanentDay;
	}

	public boolean getBorderIsMoving() {
		return borderIsMoving;
	}

	public long getBorderTimeBeforeShrink() {
		return borderTimeBeforeShrink;
	}

	public boolean getIsDeathmatchAdvantureMode() {
		return deathmatchAdvantureMode;
	}

	public Material getArenaTeleportSpotBLock() {
		return arenaTeleportSpotBLock;
	}

	public int getDeathmatchStartSize() {
		return deathmatchStartSize;
	}

	public int getDeathmatchEndSize() {
		return deathmatchEndSize;
	}

	public long getDeathmatchTimeToShrink() {
		return deathmatchTimeToShrink;
	}

	public int getTimeBeforeStartWhenReady() {
		return timeBeforeStartWhenReady;
	}

	public boolean getRegenHeadDropOnPlayerDeath() {
		return regenHeadDropOnPlayerDeath;
	}

	public boolean getEnableDoubleRegenHead() {
		return doubleRegenHead;
	}

	public boolean getEnableGoldenHeads() {
		return enableGoldenHeads;
	}

	public boolean getPlaceHeadOnFence() {
		return placeHeadOnFence;
	}

	public boolean getAllowGhastTearsDrops() {
		return allowGhastTearsDrops;
	}

	public Sound getSoundOnPlayerDeath() {
		return soundOnPlayerDeath;
	}

	public void setOverworldUuid(String uuid) {
		overworldUuid = uuid;
	}

	public void setNetherUuid(String uuid) {
		netherUuid = uuid;
	}

	public void setTheEndUuid(String theEndUuid) {
		this.theEndUuid = theEndUuid;
	}

}
