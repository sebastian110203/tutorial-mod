package net.sebastian.tutorialmod.enemy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sebastian.tutorialmod.enemy.LightningBallEntity;
import net.minecraft.world.WorldEvents;

import java.util.EnumSet;

public class StormBlaze extends BlazeEntity {

    public StormBlaze(EntityType<? extends BlazeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(4, new ShootLightningBallGoal(this));  // Use custom goal
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, 1.0));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0, 0.0F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this)); // Attack if attacked
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true)); // Actively target players
    }

    public static DefaultAttributeContainer.Builder createStormBlazeAttributes() {
        return BlazeEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0);
    }

    private static class ShootLightningBallGoal extends Goal {
        private final StormBlaze blaze;
        private int fireballsFired;
        private int fireballCooldown;
        private int targetNotVisibleTicks;

        public ShootLightningBallGoal(StormBlaze blaze) {
            this.blaze = blaze;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity target = this.blaze.getTarget();
            return target != null && target.isAlive() && this.blaze.canTarget(target);
        }

        @Override
        public void start() {
            this.fireballsFired = 0;
        }

        @Override
        public void stop() {
            this.fireballsFired = 0;
            this.targetNotVisibleTicks = 0;
        }

        @Override
        public void tick() {
            this.fireballCooldown--;
            LivingEntity target = this.blaze.getTarget();

            if (target != null) {
                boolean canSeeTarget = this.blaze.getVisibilityCache().canSee(target);

                if (canSeeTarget) {
                    this.targetNotVisibleTicks = 0;
                } else {
                    this.targetNotVisibleTicks++;
                }

                double distanceToTarget = this.blaze.squaredDistanceTo(target);

                if (distanceToTarget < 4.0) {
                    if (!canSeeTarget) {
                        return;
                    }

                    if (this.fireballCooldown <= 0) {
                        this.fireballCooldown = 20;
                        this.blaze.tryAttack(target);
                    }

                    this.blaze.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.0);
                } else if (distanceToTarget < this.getFollowRange() * this.getFollowRange() && canSeeTarget) {
                    double dx = target.getX() - this.blaze.getX();
                    double dy = target.getBodyY(0.5) - this.blaze.getBodyY(0.5);
                    double dz = target.getZ() - this.blaze.getZ();

                    if (this.fireballCooldown <= 0) {
                        this.fireballsFired++;
                        if (this.fireballsFired == 1) {
                            this.fireballCooldown = 20;
                        } else if (this.fireballsFired <= 4) {
                            this.fireballCooldown = 3;
                        } else {
                            this.fireballCooldown = 50;
                            this.fireballsFired = 0;
                        }

                        if (this.fireballsFired > 1) {
                            double h = Math.sqrt(Math.sqrt(distanceToTarget)) * 0.5;
                            if (!this.blaze.isSilent()) {
                                this.blaze.getWorld().syncWorldEvent(null, WorldEvents.BLAZE_SHOOTS, this.blaze.getBlockPos(), 0);
                            }

                            for (int i = 0; i < 1; i++) {
                                Vec3d velocity = new Vec3d(this.blaze.getRandom().nextTriangular(dx, 2.297 * h), dy, this.blaze.getRandom().nextTriangular(dz, 2.297 * h));
                                LightningBallEntity lightningBallEntity = new LightningBallEntity(EntityType.FIREBALL, this.blaze.getWorld());
                                lightningBallEntity.setPosition(this.blaze.getX(), this.blaze.getBodyY(0.5) + 0.5, this.blaze.getZ());
                                lightningBallEntity.setVelocity(velocity.normalize());

                                this.blaze.getWorld().spawnEntity(lightningBallEntity);
                            }
                        }
                    }

                    this.blaze.getLookControl().lookAt(target, 10.0F, 10.0F);
                } else if (this.targetNotVisibleTicks < 5) {
                    this.blaze.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.0);
                }
            }
        }

        private double getFollowRange() {
            return this.blaze.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
        }
    }

}
