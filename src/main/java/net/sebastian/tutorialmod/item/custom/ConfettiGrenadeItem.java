package net.sebastian.tutorialmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ConfettiGrenadeItem extends Item {

    public ConfettiGrenadeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient()) {
            Vec3d playerPos = player.getPos();
            spawnConfetti(world, playerPos);

            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, player.getSoundCategory(), 1.0F, 1.0F);

            if (!player.isCreative()) {
                itemStack.decrement(1);
            }
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    private void spawnConfetti(World world, Vec3d pos) {
        for (int i = 0; i < 100; i++) {  // Adjust the number for more/less confetti
            double offsetX = world.random.nextDouble() - 0.5;
            double offsetY = world.random.nextDouble() * 2.0;
            double offsetZ = world.random.nextDouble() - 0.5;

            world.addParticle(ParticleTypes.HAPPY_VILLAGER,
                    pos.x + offsetX,
                    pos.y + offsetY,
                    pos.z + offsetZ,
                    0.0, 0.0, 0.0);
        }
    }
}
