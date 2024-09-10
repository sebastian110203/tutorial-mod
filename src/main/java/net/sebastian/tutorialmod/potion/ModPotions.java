//package net.sebastian.tutorialmod.potion;
//
//import net.minecraft.entity.effect.StatusEffectInstance;
//import net.minecraft.entity.effect.StatusEffects;
//import net.minecraft.potion.Potion;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.util.Identifier;
//import net.sebastian.tutorialmod.TutorialMod;
//
//public class ModPotions {
//
//    public static Potion CLOUD_IN_A_BOTTLE;
//
//    public static void registerPotions() {
//        CLOUD_IN_A_BOTTLE = register("cloud_in_a_bottle_potion",
//                new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 300, 1)));  // Levitation for 15 seconds
//    }
//
//    private static Potion register(String name, Potion potion) {
//        return Registry.register(Registries.POTION, Identifier.of(TutorialMod.MOD_ID, name), potion);
//    }
//}
