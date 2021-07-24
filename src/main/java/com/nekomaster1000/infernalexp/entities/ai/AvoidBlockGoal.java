package com.nekomaster1000.infernalexp.entities.ai;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

public class AvoidBlockGoal extends Goal {

    protected final Slime entity;
    protected Optional<BlockPos> avoidBlockPos;
    protected final int avoidDistance;
    protected final Slime.SlimeMoveControl controller;
    protected final Tag avoidBlocks;

    public AvoidBlockGoal(Slime entityIn, Tag.Named<Block> blocksToAvoidIn, int avoidDistanceIn) {
        this(entityIn, blocksToAvoidIn, (p_200828_0_) -> {
            return true;
        }, avoidDistanceIn, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test);
    }

    private AvoidBlockGoal(Slime entityIn, Tag.Named<Block> blocksToAvoidIn, Predicate<LivingEntity> targetPredicate, int distance, Predicate<LivingEntity> p_i48859_9_) {
        this.entity = entityIn;
        this.avoidBlocks = blocksToAvoidIn;
        this.avoidDistance = distance;
        this.controller = (Slime.SlimeMoveControl) entityIn.getMoveControl();
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.avoidBlockPos = BlockPos.findClosestMatch(this.entity.blockPosition(), this.avoidDistance, 4, (pos) -> this.entity.level.getBlockState(pos).is(avoidBlocks));
        return avoidBlockPos.isPresent();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public boolean canContinueToUse() {
        this.avoidBlockPos = BlockPos.findClosestMatch(this.entity.blockPosition(), this.avoidDistance, 4, (pos) -> this.entity.level.getBlockState(pos).is(avoidBlocks));

        return this.avoidBlockPos.isPresent() && this.entity.distanceToSqr(this.avoidBlockPos.get().getX(),
            this.avoidBlockPos.get().getY(), this.avoidBlockPos.get().getZ()) <= (float) this.avoidDistance;
    }

    @Override
    public void stop() {
        this.avoidBlockPos = null;
    }

    @Override
    public void tick() {
        faceAway();
        this.controller.setWantedMovement(1.0);
    }

    private void faceAway() {
        double d0 = this.avoidBlockPos.get().getX() - this.entity.getX();
        double d2 = this.avoidBlockPos.get().getZ() - this.entity.getZ();
        double d1 = (this.avoidBlockPos.get().getY() + this.entity.getBoundingBox().maxY) / 2.0D - this.entity.getEyeY();

        double d3 = Mth.sqrt(d0 * d0 + d2 * d2);
        float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
        float f1 = (float) (-(Mth.atan2(d1, d3) * (double) (180F / (float) Math.PI)));
        this.entity.xRot = updateRotation(this.entity.xRot, f1, 10.0F);
        this.entity.yRot = updateRotation(this.entity.yRot, f + 180.0F, 10.0F);

        this.controller.setDirection(this.entity.yRot, false);
    }

    private float updateRotation(float angle, float targetAngle, float maxIncrease) {
        float f = Mth.wrapDegrees(targetAngle - angle);
        if (f > maxIncrease) {
            f = maxIncrease;
        }

        if (f < -maxIncrease) {
            f = -maxIncrease;
        }

        return angle + f;
    }

}
