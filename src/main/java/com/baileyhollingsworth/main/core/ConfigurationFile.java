package com.baileyhollingsworth.main.core;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by Bailey Hollingsworth on 12/17/16.
 */
public class ConfigurationFile {
    private static String HANDLERS = "Handlers";

    private static String SLEEPING = "Sleeping Events. Requires Sleep Event Handler = true";
    private static String EXP = "EXP Events. Requires EXP Event Handler = true";
    private static String MOB = "Mob Events. Requires Mob Event Handler = true";
    private static String INTERACTION = "Item/Block Interaction Events. Requires Item/Block Interaction Event Handler = true";

    public static float xpOrbHealValue;
    public static float sleepingHealValue;
    public static int xpOrbMultiplier;
    public static int explosionOnCreeperDeathValue;
    public static boolean creeperDeathExplosionIsDestructive;
    public static boolean mobsRevengeMode;
    public static boolean stopBedUse;
    public static boolean clickingBedSetsSpawnPoint;
    public static boolean hardcoreAir;

    public static boolean loadForestHandlers;
    public static boolean loadSleepHandlers;
    public static boolean loadMobHandlers;
    public static boolean loadEXPHandlers;
    public static boolean loadInteractionHandlers;
    public static boolean hardcoreHunger;
    public static boolean versionChecker;

    public static void configFile(Configuration config){
        config.load();
        getHandlerRegistrationValues(config);
        getEXPEventValues(config);
        getSleepEventValues(config);
        getMobEventValues(config);
        getInteractionValues(config);
        versionChecker = config.getBoolean("Enable Version Checker",Configuration.CATEGORY_CLIENT, true, "Latest builds are fewer bugs!");
        if (config.hasChanged()) {
            config.save();
        }
    }

    private static void getInteractionValues(Configuration config){
        stopBedUse = config.getBoolean("Disable Sleeping In Bed", ConfigurationFile.INTERACTION, false, "Disables crawling into bed.");
        clickingBedSetsSpawnPoint = config.getBoolean("Clicking Bed Sets Spawn Point", ConfigurationFile.INTERACTION, false,"Sets player spawn point on top of bed. DOES NOT GO AWAY WHEN BED IS DESTROYED!");
        hardcoreAir = config.getBoolean("Hardcore Air", ConfigurationFile.INTERACTION, false,"Players will die instantly when taking drowning damage");
        hardcoreHunger = config.getBoolean("Hardcore Hunger", ConfigurationFile.INTERACTION, false, "Players will die when taking starvation damage");
    }
    private static void getEXPEventValues(Configuration config) {
        xpOrbHealValue = config.getFloat("XP Healing Value",ConfigurationFile.EXP, 0.0f, 0.0f, 20.0f, "How much each EXP orb should heal the player. Recommended: 0.1");
        xpOrbMultiplier = config.getInt("XP Value Multiplier", ConfigurationFile.EXP, 1, 0, 500, "Multiplies the value of experience for each orb. Whole numbers only.");
    }

    private static void getSleepEventValues(Configuration config){
        sleepingHealValue = config.getFloat("Health restored after sleeping",ConfigurationFile.SLEEPING, 0.0f, 0.0f, 20.0f, "How much sleeping should heal the player. 1 = half heart");
    }

    private static void getMobEventValues(Configuration config) {
        explosionOnCreeperDeathValue = config.getInt("Creeper Death Explosion Strength", ConfigurationFile.MOB, 0, 0, 9, "If larger than 0, creepers will explode when they die. Normal Creeper Explosion = 3");
        creeperDeathExplosionIsDestructive = config.getBoolean("Creeper Death Explosion Destroys Terrain", ConfigurationFile.MOB, false, "");
        mobsRevengeMode = config.getBoolean("Mobs Get Strength Boost When Damaged By Player", ConfigurationFile.MOB, false, "");

    }


    private static void getHandlerRegistrationValues(Configuration config) {
        loadForestHandlers = config.getBoolean("Sapling Replant", ConfigurationFile.HANDLERS, false, "Enables sapling auto-replant");
        loadEXPHandlers = config.getBoolean("EXP Event Handler", ConfigurationFile.HANDLERS, false, "Enables EXP Events");
        loadMobHandlers = config.getBoolean("Mob Event Handler", ConfigurationFile.HANDLERS, false,"Enables Mob Events");
        loadSleepHandlers = config.getBoolean("Sleep Event Handler", ConfigurationFile.HANDLERS, false, "Enables Sleeping Events.");
        loadInteractionHandlers = config.getBoolean("Item/Block Interaction Handler", ConfigurationFile.HANDLERS, false, "Enables Interaction Events");
    }

}
