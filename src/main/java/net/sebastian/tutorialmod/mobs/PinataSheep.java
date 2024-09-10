package net.sebastian.tutorialmod.mobs;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import net.sebastian.tutorialmod.ModEntities;
import net.sebastian.tutorialmod.TutorialMod;
import net.sebastian.tutorialmod.item.ModItems;
import net.sebastian.tutorialmod.potion.Potions2;
import org.jetbrains.annotations.Nullable;
import net.minecraft.potion.Potions;

import java.util.List;

import static net.minecraft.block.Blocks.register;

public class PinataSheep extends SheepEntity {

    public PinataSheep(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createSheepAttributes() {
        return SheepEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23);
    }


    @Override
    public void sheared(SoundCategory category) {
        if (!this.getWorld().isClient) {
            this.setSheared(true);
            ItemStack healingPotion = new ItemStack(Items.POTION);
            PotionContentsComponent healingComponent = new PotionContentsComponent(Potions.HEALING);
            healingPotion.set(DataComponentTypes.POTION_CONTENTS, healingComponent);

            this.dropStack(healingPotion);

            ItemStack cloudPotion = new ItemStack(Items.POTION);
            PotionContentsComponent cloudComponent = new PotionContentsComponent(ModItems.CLOUD_IN_A_BOTTLE);
            cloudPotion.set(DataComponentTypes.POTION_CONTENTS, cloudComponent);

            this.dropStack(cloudPotion);
        }
    }

}
