package net.sebastian.tutorialmod.enemy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class LightningBallEntity extends FireballEntity {

    public LightningBallEntity(EntityType<? extends FireballEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        if (!this.getWorld().isClient && this.getWorld() instanceof ServerWorld serverWorld) {
            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(getWorld());

            if (lightning != null) {
                lightning.refreshPositionAfterTeleport(hitResult.getPos());

                serverWorld.spawnEntity(lightning);
            }
        }

        this.remove(RemovalReason.DISCARDED);
    }

}
