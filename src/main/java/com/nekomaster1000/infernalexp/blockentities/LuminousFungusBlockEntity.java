package com.nekomaster1000.infernalexp.blockentities;

import com.nekomaster1000.infernalexp.blocks.LuminousFungusBlock;
import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig;
import com.nekomaster1000.infernalexp.init.IEBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class LuminousFungusBlockEntity extends BlockEntity {

    public int lightTime = 0;
    
    public LuminousFungusBlockEntity(BlockPos pos, BlockState blockState) {
        super(IEBlockEntityTypes.LUMINOUS_FUNGUS_TILE_ENTITY.get(), pos, blockState);
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, BlockEntity be) {
        if (!(be instanceof LuminousFungusBlockEntity blockEntity /* Note from Swan: i love java 16 */)) return;
        if (level.isClientSide()) return;
        List<Entity> nearbyEntities = level.getEntitiesOfClass(Entity.class,
                new AABB(blockPos).inflate(InfernalExpansionConfig.Miscellaneous.LUMINOUS_FUNGUS_ACTIVATE_DISTANCE.getDouble()));
        nearbyEntities.removeIf((entity) -> {
            Vec3 entityPos = new Vec3(entity.getX(), entity.getEyeY(), entity.getZ());
            Vec3 pos = new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            return level.clip(new ClipContext(pos, entityPos, ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, entity)).getType() != HitResult.Type.MISS;
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
        if (blockEntity.lightTime == 0) {
            level.setBlockAndUpdate(blockPos, blockState.setValue(LuminousFungusBlock.LIT, shouldLight));
        } else {
            blockEntity.lightTime--;
        }
        if (shouldLight) blockEntity.lightTime = 60;
    }
}
