package com.baileyhollingsworth.main.eventHandlers;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

/**
 * Created by Bailey Hollingsworth on 12/17/16.
 */
public class ForestEventHandlers extends BaseEventHandler {
    @SubscribeEvent
    public void HarvestDropsEvent(BlockEvent.HarvestDropsEvent e){
        if (e.getHarvester() == null && !e.getDrops().isEmpty() && ((e.getState().getBlock().equals(Blocks.LEAVES))
                || e.getState().getBlock().equals(Blocks.LEAVES2)) && !e.getWorld().isRemote){
            Block block = Block.getBlockFromItem(e.getDrops().get(0).getItem());
            int meta = e.getDrops().get(0).getMetadata();
            BlockPos pos = findGround(e.getWorld(), e.getPos());
            if(pos != null){
                e.getWorld().setBlockState(pos, block.getStateFromMeta(meta));
                deleteCopySapling(e.getWorld(), pos, Item.getItemFromBlock(block));
            }
        }
    }

    private boolean deleteCopySapling(World world, BlockPos pos, Item item){
        List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(
                pos.add(-4, -4, -4), pos.add(4, 4, 4)));
        for(EntityItem i: list){
            if(i.getEntityItem().getItem() == item){
                i.setDead();
                return true;
            }
        }
        return false;
    }

    private BlockPos findGround(World world, BlockPos pos){
        BlockPos pos2 = pos;
        for(int i = 0; i < 25; i++){
            if(Blocks.SAPLING.canPlaceBlockAt(world, pos2)) {
                return pos2;
            }
            pos2 = pos2.down();
        }
        return null;
    }
}
