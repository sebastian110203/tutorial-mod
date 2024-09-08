package net.sebastian.tutorialmod.item.customentities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;


// Commented for sharing !!!!
public class BoomerangEntity extends ThrownEntity {
    private PlayerEntity owner;
    private int ticksFlying;

    public BoomerangEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
        this.ticksFlying = 0;
    }

    public BoomerangEntity(World world, PlayerEntity owner) {
        this(EntityType.SNOWBALL, world); // Placeholder EntityType, we will register a custom type later
        this.owner = owner;
        this.ticksFlying = 0;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();
        ticksFlying++;

        if (ticksFlying > 20) {  // After a certain number of ticks, return to the player
            returnToPlayer();
        }
    }

    private void returnToPlayer() {
        if (this.owner != null) {
            // Calculate the vector pointing back to the player
            double deltaX = owner.getX() - this.getX();
            double deltaY = owner.getEyeY() - this.getY();
            double deltaZ = owner.getZ() - this.getZ();
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

            // Set velocity to move back toward the player
            this.setVelocity(deltaX / distance * 0.5, deltaY / distance * 0.5, deltaZ / distance * 0.5);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        // Logic for what happens when the boomerang hits something
        if (!this.getWorld().isClient) {
            this.remove(RemovalReason.DISCARDED);  // Remove the entity after collision
        }
    }

}
