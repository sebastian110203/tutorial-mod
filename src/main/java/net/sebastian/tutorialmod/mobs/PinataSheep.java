package net.sebastian.tutorialmod.mobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class PinataSheep extends SheepEntity {

    public PinataSheep(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public @Nullable SheepEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return super.createChild(serverWorld, passiveEntity);
    }

    public static DefaultAttributeContainer.Builder createSheepAttributes() {
        return SheepEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23);
    }

    @Override
    public void sheared(SoundCategory category) {
        if (!this.getWorld().isClient) {
            ServerWorld serverWorld = (ServerWorld) this.getWorld();

            ItemStack dropItem = new ItemStack(Items.DIAMOND);

            this.dropStack(dropItem);

            this.setSheared(true);
            wait(5000,0);
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.isOf(Items.SHEARS) && !this.isSheared() && !this.isBaby()) {
            this.sheared(SoundCategory.PLAYERS);
            return ActionResult.success(this.getWorld().isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

}
