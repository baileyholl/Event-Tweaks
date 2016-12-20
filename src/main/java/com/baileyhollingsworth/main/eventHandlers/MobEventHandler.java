package com.baileyhollingsworth.main.eventHandlers;

import com.baileyhollingsworth.main.ConfigurationFile;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Bailey Hollingsworth on 12/18/16.
 */
public class MobEventHandler extends BaseEventHandler {

    @SubscribeEvent
    public void creeperExplodesOnDeath(LivingDeathEvent e){
        if(e.getEntity() instanceof EntityCreeper && ConfigurationFile.explosionOnCreeperDeathValue > 0){
            e.getEntity().getEntityWorld().createExplosion(e.getEntity(), e.getEntity().posX, e.getEntity().posY,
                    e.getEntity().posZ, ConfigurationFile.explosionOnCreeperDeathValue, ConfigurationFile.creeperDeathExplosionIsDestructive);
        }
    }

    @SubscribeEvent
    public void revengeModeMobs(LivingHurtEvent e){
        if(e.getSource() == DamageSource.drown && e.getEntity().isWet() && e.getEntity() instanceof EntityPlayer && ((EntityPlayer) e.getEntity()).getHealth() > 0.0f
                && ConfigurationFile.hardcoreAir){
            ((EntityLivingBase)e.getEntity()).setHealth(0.0f);
        }
        else if(e.getSource() == DamageSource.starve && e.getEntity() instanceof EntityPlayer && ((EntityPlayer) e.getEntity()).getHealth() > 0.0f
                && ConfigurationFile.hardcoreHunger){
            ((EntityLivingBase)e.getEntity()).setHealth(0.0f);
        }else if(e.getSource().getEntity() instanceof EntityPlayer && ConfigurationFile.mobsRevengeMode) {
            try {
                if (!(e.getEntity() instanceof EntityMob) && !(e.getEntity() instanceof EntityPlayer)) {
                    ((EntityLiving) e.getEntity()).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100, 1));
                }
            }catch(ClassCastException e1){
                e1.printStackTrace();
            }
        }
    }
}
