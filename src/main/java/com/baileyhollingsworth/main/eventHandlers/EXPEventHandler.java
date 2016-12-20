package com.baileyhollingsworth.main.eventHandlers;

import com.baileyhollingsworth.main.core.ConfigurationFile;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Bailey Hollingsworth on 12/17/16.
 */
public class EXPEventHandler extends BaseEventHandler {

    @SubscribeEvent
    public void healPlayerWithExp(PlayerPickupXpEvent e) {
        if(!e.getEntity().getEntityWorld().isRemote) {
            e.getOrb().xpValue *= 1.0;
            if (ConfigurationFile.xpOrbHealValue > 0.0f) {
                e.getEntityPlayer().heal(ConfigurationFile.xpOrbHealValue);
            }
        }
    }
}
