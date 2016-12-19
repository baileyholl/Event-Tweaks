package com.baileyhollingsworth.main.eventHandlers;

import com.baileyhollingsworth.main.ConfigurationFile;
import net.minecraft.init.Blocks;
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
        }

        if(ConfigurationFile.stopBedUse){
            e.setCanceled(true);
        }
    }

}
