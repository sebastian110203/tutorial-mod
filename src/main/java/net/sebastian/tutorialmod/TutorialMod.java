package net.sebastian.tutorialmod;

import 	net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sebastian.tutorialmod.block.ModBlocks;
import net.sebastian.tutorialmod.item.ModItemGroups;
import net.sebastian.tutorialmod.item.ModItems;
import net.sebastian.tutorialmod.mobs.PinataSheep;
//import net.sebastian.tutorialmod.potion.ModPotions;
import net.sebastian.tutorialmod.potion.Potions2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorial-mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModEntities.registerAttributes();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		//ModPotions.registerModPotions();
		LOGGER.info("Hello Fabric world!");

		Registry.register(
				Registries.POTION,
				Identifier.of(TutorialMod.MOD_ID, "cloud_in_a_bottle"),
				new Potion(
						new StatusEffectInstance(
								Registries.STATUS_EFFECT.getEntry(StatusEffects.LEVITATION.value()),
								300, // how many ticks. it takes 20 ticks for one second
								0
						)
				)
		);
		Registry.register(
				Registries.POTION,
				Identifier.of(TutorialMod.MOD_ID, "lightning_in_a_bottle"),
				new Potion(
						new StatusEffectInstance(
								Registries.STATUS_EFFECT.getEntry(StatusEffects.SPEED.value()),
								3600,
								20
						)
				)
		);
	}

}