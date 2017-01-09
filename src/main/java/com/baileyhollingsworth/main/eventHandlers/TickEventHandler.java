package com.baileyhollingsworth.main.eventHandlers;

import com.baileyhollingsworth.main.core.ConfigurationFile;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by Bailey Hollingsworth on 12/24/16.
 */
public class TickEventHandler extends BaseEventHandler{
    private static int tickCount;
    private static int secondsInTicks;
    private static Potion potion;
    public int amplifier;

    @SubscribeEvent
    public void applyPotionWithCertainLevel(TickEvent.PlayerTickEvent e){
        tickCount++;
        if(tickCount >= secondsInTicks){
            tickCount = 0;
            if(e.player.experienceLevel >= ConfigurationFile.levelsRequiredForPotion){
                e.player.addPotionEffect(new PotionEffect(potion, secondsInTicks,
                        ConfigurationFile.potionAmplifier));
            }
        }
    }

    //Called during registration
    public void setupValues(){
        tickCount = 0;
        secondsInTicks = ConfigurationFile.secondsPerReapply * 20;
        potion = Potion.getPotionById(ConfigurationFile.potionIDToApply);
        if(potion == null){
            potion = Potion.getPotionById(1);
        }
    }
}
