package net.sebastian.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.BlazeEntityRenderer;
import net.minecraft.client.render.entity.SheepEntityRenderer;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.CUSTOM_SHEEP, SheepEntityRenderer::new);

        // blaze entity

        EntityRendererRegistry.register(ModEntities.STORM_BLAZE_ENTITY_TYPE, BlazeEntityRenderer::new);
    }
}
