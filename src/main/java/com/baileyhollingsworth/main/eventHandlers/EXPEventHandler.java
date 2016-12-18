package com.baileyhollingsworth.main.eventHandlers;

import com.baileyhollingsworth.main.ConfigurationFile;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Bailey Hollingsworth on 12/17/16.
 */
public class EXPEventHandler {

    @SubscribeEvent
    public void onPlayerPickupXP(PlayerPickupXpEvent e) {
        if(ConfigurationFile.xpOrbsHeal){
            e.getEntityPlayer().heal(.1F);
            System.out.println("Healing player" + e.getEntityPlayer().getHealth());
        }
    }
    @SubscribeEvent
    public void PlayerWakeUpEvent(PlayerWakeUpEvent e){
        System.out.println("test");
        e.getEntityPlayer().heal(1.0f);
    }

}
