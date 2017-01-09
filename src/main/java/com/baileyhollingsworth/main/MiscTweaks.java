package com.baileyhollingsworth.main;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.fml.common.registry.GameData;

/**
 * Created by Bailey Hollingsworth on 1/8/17.
 */
public class MiscTweaks {
    public static void stopEndermanFromGriefing(){
        for (Block b : GameData.getBlockItemMap().keySet()) {
            EntityEnderman.setCarriable(b, false);
        }
    }
}
