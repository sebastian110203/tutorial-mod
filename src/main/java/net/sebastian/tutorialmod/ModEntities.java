package net.sebastian.tutorialmod;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeRegistry;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sebastian.tutorialmod.mobs.PinataSheep;

public class ModEntities {

    public static final EntityType<PinataSheep> CUSTOM_SHEEP = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(TutorialMod.MOD_ID, "custom_sheep"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PinataSheep::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 1.3f))
                    .build()
    );

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(CUSTOM_SHEEP, PinataSheep.createSheepAttributes());
    }
}
