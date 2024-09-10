package net.sebastian.tutorialmod.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class Potions2 {

   public static final RegistryEntry<Potion> LIGHTNING_IN_A_BOTTLE = register("cloud_in_a_bottle", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 3600, 20)));
    private static RegistryEntry<Potion> register(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.ofVanilla(name), potion);
    }

    public static void registerPotions() {
        System.out.println("Registering Potions");
    }

}
