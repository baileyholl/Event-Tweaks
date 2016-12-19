package com.baileyhollingsworth.main.eventHandlers;

import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Bailey Hollingsworth on 12/18/16.
 */
public abstract class BaseEventHandler {
    public void registerEventHandler(){
        System.out.println("Registering: " + this.toString());
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
