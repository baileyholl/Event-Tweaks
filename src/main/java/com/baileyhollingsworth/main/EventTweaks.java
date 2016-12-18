package com.baileyhollingsworth.main;


import com.baileyhollingsworth.main.eventHandlers.EXPEventHandler;
import com.baileyhollingsworth.main.eventHandlers.ForestEventHandlers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EventTweaks.MODID, version = EventTweaks.VERSION)
public class EventTweaks
{
    public static final String MODID = "event_tweaks";
    public static final String VERSION = "1.0";

    @SidedProxy
    public static CommonProxy proxy;

    @Mod.Instance
    public static EventTweaks instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //logger = event.getModLog();
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
           // Configuration config = new Configuration(e.getSuggestedConfigurationFile());
            //ConfigurationFile.configFile(config);
        }
        public void init(FMLInitializationEvent e){
            EXPEventHandler handler = new EXPEventHandler();
            ForestEventHandlers forestHandler = new ForestEventHandlers();
            MinecraftForge.EVENT_BUS.register(handler);
            MinecraftForge.EVENT_BUS.register(forestHandler);
            FMLCommonHandler.instance().bus().register(handler);
            FMLCommonHandler.instance().bus().register(forestHandler);
        }
        public void postInit(FMLPostInitializationEvent e){

        }
    }

    public static class ClientProxy extends CommonProxy{
        @Override
        public void preInit(FMLPreInitializationEvent e){
            super.preInit(e);
        }
    }

    public static class ServerProxy extends CommonProxy{

    }
}
