package net.sebastian.tutorialmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sebastian.tutorialmod.item.customentities.BoomerangEntity;

public class BoomerangItem extends Item {
    public BoomerangItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            // Spawn the boomerang entity when the item is used
            BoomerangEntity boomerangEntity = new BoomerangEntity(world, player);

            // Set the initial position of the boomerang at the player's location
            boomerangEntity.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            // Set the velocity of the boomerang based on the player's looking direction
            boomerangEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);

            // Spawn the boomerang entity in the world
            world.spawnEntity(boomerangEntity);
        }

        // Return success and the boomerang item stack
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
