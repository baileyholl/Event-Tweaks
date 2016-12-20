package com.baileyhollingsworth.main.eventHandlers;

import com.baileyhollingsworth.main.ConfigurationFile;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Bailey Hollingsworth on 12/18/16.
 */
public class InteractEventHandler extends BaseEventHandler{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void stopBedUse(PlayerInteractEvent e){
        if(e.getWorld().getBlockState(e.getPos()).getBlock() == Blocks.BED && ConfigurationFile.clickingBedSetsSpawnPoint){
            e.getEntityPlayer().setSpawnPoint(e.getPos(), true);
            e.getEntityPlayer().bedLocation = e.getPos();
            e.getEntityPlayer().addChatComponentMessage(new TextComponentTranslation("Spawn point set!"));
        }

        if(ConfigurationFile.stopBedUse){
            e.getEntityPlayer().addChatComponentMessage(new TextComponentTranslation("You don't feel tired enough to sleep.."));
            e.setCanceled(true);
        }
    }

}
