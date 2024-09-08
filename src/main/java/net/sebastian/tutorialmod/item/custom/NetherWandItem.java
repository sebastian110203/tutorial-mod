package net.sebastian.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import javax.swing.*;
import java.util.Map;

public class NetherWandItem extends Item {

    private static final Map<Block, Block> WAND_MAP =
            Map.of(
                    Blocks.GRASS_BLOCK, Blocks.NETHER_PORTAL
            );

    public NetherWandItem(Settings settings){
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock(); //getBlock from Click

        if(WAND_MAP.containsKey(clickedBlock)) {
            if(!world.isClient()) {
                // we want this to be on the server side

                world.setBlockState(context.getBlockPos(), WAND_MAP.get(clickedBlock).getDefaultState()); //make it a portal block

                    context.getStack().damage(1,((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                            item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null,context.getBlockPos(), SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS);

            }
        }
        return ActionResult.SUCCESS;
    }

}
