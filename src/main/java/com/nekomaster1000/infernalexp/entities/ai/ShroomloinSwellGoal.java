package com.nekomaster1000.infernalexp.entities.ai;

import java.util.EnumSet;

import com.nekomaster1000.infernalexp.entities.ShroomloinEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class ShroomloinSwellGoal extends Goal{
    private final ShroomloinEntity swellingShroomloin;
    private LivingEntity shroomloinAttackTarget;

    public ShroomloinSwellGoal(ShroomloinEntity entityshroomloinIn) {
        this.swellingShroomloin = entityshroomloinIn;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        LivingEntity livingentity = this.swellingShroomloin.getTarget();
        return this.swellingShroomloin.getShroomloinState() > 0 || livingentity != null && this.swellingShroomloin.distanceToSqr(livingentity) < 9.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.swellingShroomloin.getNavigation().stop();
        this.shroomloinAttackTarget = this.swellingShroomloin.getTarget();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.shroomloinAttackTarget = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.shroomloinAttackTarget == null) {
            this.swellingShroomloin.setShroomloinState(-1);
        } else if (this.swellingShroomloin.distanceToSqr(this.shroomloinAttackTarget) > 49.0D) {
            this.swellingShroomloin.setShroomloinState(-1);
        } else if (!this.swellingShroomloin.getSensing().canSee(this.shroomloinAttackTarget)) {
            this.swellingShroomloin.setShroomloinState(-1);
        } else {
            this.swellingShroomloin.setShroomloinState(1);
        }
    }
}
