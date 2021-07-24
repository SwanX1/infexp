package com.nekomaster1000.infernalexp.entities.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.phys.Vec3;

public class TeleportPanicGoal extends PanicGoal {

    public TeleportPanicGoal(PathfinderMob creature, double speedIn) {
        super(creature, speedIn);
    }

    @Override
    public void start() {
        this.mob.randomTeleport(this.posX, this.posY, this.posZ, true); //Final parameter determines whether or not purple enderman particles appear on teleport
        this.mob.setDeltaMovement(0.0D, 0.0D, 0.0D);
        this.mob.setLastHurtByMob(null);
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    protected boolean findRandomPosition() {
        Vec3 vector3d = RandomPos.getPos(this.mob, 16, 6);
        if (vector3d == null) {
            return false;
        } else {
            this.posX = vector3d.x;
            this.posY = vector3d.y;
            this.posZ = vector3d.z;

            return true;
        }
    }
}
