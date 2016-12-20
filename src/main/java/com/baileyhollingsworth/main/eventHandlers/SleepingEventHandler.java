package com.baileyhollingsworth.main.eventHandlers;

import com.baileyhollingsworth.main.core.ConfigurationFile;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Bailey Hollingsworth on 12/18/16.
 */
public class SleepingEventHandler extends BaseEventHandler{
    @SubscribeEvent
    public void PlayerWakeUpEvent(PlayerWakeUpEvent e){
        if(!e.getEntity().getEntityWorld().isRemote && ConfigurationFile.sleepingHealValue > 0.0f && !e.updateWorld()) {
            e.getEntityPlayer().heal(ConfigurationFile.sleepingHealValue);
            e.getEntityPlayer().addChatComponentMessage(new TextComponentTranslation("You feel refreshed..."));
        }
    }
}
