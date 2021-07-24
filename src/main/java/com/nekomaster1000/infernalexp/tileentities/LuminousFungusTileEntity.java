package com.nekomaster1000.infernalexp.tileentities;

import com.nekomaster1000.infernalexp.blocks.LuminousFungusBlock;
import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig;
import com.nekomaster1000.infernalexp.init.IETileEntityTypes;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class LuminousFungusTileEntity extends BlockEntity implements TickableBlockEntity {

    private int lightTime = 0;
    
    public LuminousFungusTileEntity() {
        super(IETileEntityTypes.LUMINOUS_FUNGUS_TILE_ENTITY.get());
    }

    @Override
    public void tick() {
        if (!this.level.isClientSide()) {
            List<Entity> nearbyEntities = this.getLevel().getEntitiesOfClass(Entity.class,
                    new AABB(this.getBlockPos()).inflate(InfernalExpansionConfig.Miscellaneous.LUMINOUS_FUNGUS_ACTIVATE_DISTANCE.getDouble()));
            Vec3 blockPos = new Vec3(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ());
            nearbyEntities.removeIf((entity) -> {
                Vec3 entityPos = new Vec3(entity.getX(), entity.getEyeY(), entity.getZ());
                return this.level.clip(new ClipContext(blockPos, entityPos, ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, entity)).getType() != HitResult.Type.MISS;
            });
            boolean shouldLight = false;
            for (Entity entity : nearbyEntities) {
                if (entity.xOld != entity.getX() || entity.yOld != entity.getY() || entity.zOld != entity.getZ()) {
                    double velX = Math.abs(entity.getX() - entity.xOld);
                    double velY = Math.abs(entity.getY() - entity.yOld);
                    double velZ = Math.abs(entity.getZ() - entity.zOld);
                    if (velX >= (double)0.003F || velY >= (double)0.003F || velZ >= (double)0.003F) {
                        shouldLight = true;                                    
                        break;
                    }
                } else if (
                    entity.walkDist - entity.walkDistO > 0 ||
                    entity.getDeltaMovement().length() > 0.1D
                ) {
                    shouldLight = true;                                    
                    break;
                }
            }
            if (lightTime == 0) {
                this.level.setBlockAndUpdate(this.worldPosition, this.getBlockState().setValue(LuminousFungusBlock.LIT, shouldLight));
            } else {
                this.lightTime--;
            }
            if (shouldLight) this.lightTime = 60;
        }
    }
}
