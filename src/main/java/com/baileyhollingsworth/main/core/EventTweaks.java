package com.baileyhollingsworth.main.core;


import com.baileyhollingsworth.main.MiscTweaks;
import com.baileyhollingsworth.main.core.versionChecker.VersionChecker;
import com.baileyhollingsworth.main.eventHandlers.*;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EventTweaks.MODID, version = EventTweaks.VERSION, name = EventTweaks.MODID)
public class EventTweaks
{
    public static final String MODID = "event_tweaks";
    public static final String VERSION = "1.1.1";
    public static final String URL = "https://raw.githubusercontent.com/baileyholl/Event-Tweaks/master/Version";

    @SidedProxy
    public static CommonProxy proxy;

    @Mod.Instance
    public static EventTweaks instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

        @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit(e);
    }

    public static class CommonProxy {

        public void preInit(FMLPreInitializationEvent e){
            Configuration config = new Configuration(e.getSuggestedConfigurationFile());
            ConfigurationFile.configFile(config);
        }

        public void init(FMLInitializationEvent e){
            EXPEventHandler expEventHandler = new EXPEventHandler();
            ForestEventHandlers forestHandler = new ForestEventHandlers();
            MobEventHandler mobEventHandler = new MobEventHandler();
            SleepingEventHandler sleepEventHandler = new SleepingEventHandler();
            InteractEventHandler interactEventHandler = new InteractEventHandler();
            TickEventHandler tickEventHandler = new TickEventHandler();
            if(ConfigurationFile.loadForestHandlers){
                forestHandler.registerEventHandler();
            }
            if(ConfigurationFile.loadMobHandlers){
                mobEventHandler.registerEventHandler();
            }
            if(ConfigurationFile.loadSleepHandlers){
                sleepEventHandler.registerEventHandler();
            }
            if(ConfigurationFile.loadEXPHandlers){
                expEventHandler.registerEventHandler();
            }

            if(ConfigurationFile.loadInteractionHandlers){
                interactEventHandler.registerEventHandler();
            }
            if(ConfigurationFile.loadTickHandlers) {
                tickEventHandler.registerEventHandler();
                tickEventHandler.setupValues();
            }
        }

        public void postInit(FMLPostInitializationEvent e){
            if(ConfigurationFile.stopEndermanGriefing) {
                MiscTweaks.stopEndermanFromGriefing();
            }
        }
    }

    public static class ClientProxy extends CommonProxy{
        @Override
        public void preInit(FMLPreInitializationEvent e){
            super.preInit(e);
            new VersionChecker().init();
        }
    }
}
