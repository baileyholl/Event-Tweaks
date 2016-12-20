package com.baileyhollingsworth.main.core.versionChecker;

import com.baileyhollingsworth.main.core.ConfigurationFile;
import com.baileyhollingsworth.main.core.EventTweaks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

import java.io.IOException;

/**
 * Created by Bailey Hollingsworth on 12/19/16.
 */
public class VersionChecker {

    public static String version = "";
    public static boolean doneChecking = false;

    public void init() {
        new ThreadVersionChecker();
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onTick(ClientTickEvent event) throws IOException {
        if(!doneChecking && Minecraft.getMinecraft().thePlayer != null){
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if(!VersionChecker.version.equals(EventTweaks.VERSION) && ConfigurationFile.versionChecker){
                System.out.println("VC: " + VersionChecker.version + " " + "ET V: " + EventTweaks.VERSION);
                player.addChatComponentMessage(new TextComponentTranslation("There is a new version of Event Tweaks Available!"));
            }
            VersionChecker.doneChecking = true;
        }
    }
}