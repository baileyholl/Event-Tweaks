package com.baileyhollingsworth.main.eventHandlers;

import com.baileyhollingsworth.main.core.ConfigurationFile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Bailey Hollingsworth on 12/18/16.
 */
public class MobEventHandler extends BaseEventHandler {

    @SubscribeEvent
    public void creeperExplodesOnDeath(LivingDeathEvent e) {
        if (!e.getEntity().getEntityWorld().isRemote) {
            if (e.getEntity() instanceof EntityCreeper && ConfigurationFile.explosionOnCreeperDeathValue > 0) {
                e.getEntity().getEntityWorld().createExplosion(e.getEntity(), e.getEntity().posX, e.getEntity().posY,
                        e.getEntity().posZ, ConfigurationFile.explosionOnCreeperDeathValue, ConfigurationFile.creeperDeathExplosionIsDestructive);
            }
        }
    }

    @SubscribeEvent
    public void hurtEvents(LivingHurtEvent e) {
        if (!e.getEntity().getEntityWorld().isRemote) {
            DamageSource source = e.getSource();
            Entity entity = e.getEntity();

            if (entity == null || entity.isDead) {
                return;
            }

            if (source == DamageSource.drown && entity.isWet() && entity instanceof EntityPlayer && ConfigurationFile.hardcoreAir) {
                ((EntityPlayer) entity).setHealth(0.0f);
            } else if (source == DamageSource.starve && entity instanceof EntityPlayer && ConfigurationFile.hardcoreHunger) {
                ((EntityPlayer) entity).setHealth(0.0f);
            } else if (source.getEntity() instanceof EntityPlayer && ConfigurationFile.mobsRevengeMode) {
                if (!(entity instanceof EntityPlayer) && entity instanceof EntityLiving) {
                    ((EntityLiving) entity).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100, 1));
                }
            }
        }
    }

    @SubscribeEvent
    public void playersKeepEXPOnDeath(PlayerEvent.Clone e) {
        if (e.isWasDeath()) {
            if(ConfigurationFile.playerKeepsExpOnDeath) {
                e.getEntityPlayer().experienceLevel = e.getOriginal().experienceLevel;
            }
            e.getEntityPlayer().getFoodStats().setFoodLevel(ConfigurationFile.hungerLevelAfterDeath);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void stopEndermanTeleporting(EnderTeleportEvent e){
        if((e.getEntity() instanceof EntityEnderman || e.getEntity() instanceof EntityShulker) && ConfigurationFile.stopMobsFromTeleporting){
            e.setCanceled(true);
        }
    }
}
