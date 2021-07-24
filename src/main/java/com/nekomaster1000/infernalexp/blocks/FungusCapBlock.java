package com.nekomaster1000.infernalexp.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

public class FungusCapBlock extends Block {

    public FungusCapBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
       if (entityIn.isSuppressingBounce()) {
           entityIn.causeFallDamage(fallDistance, 0.75F, DamageSource.FALL);
       } else {
          entityIn.causeFallDamage(fallDistance, 0.1F, DamageSource.FALL);
       }
    }
    
    public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) {
       if (entityIn.isSuppressingBounce()) {
          super.updateEntityAfterFallOn(worldIn, entityIn);
       } else {
          bounceEntity(entityIn);
       }
    }

    private void bounceEntity(Entity entity) {
       Vec3 vector3d = entity.getDeltaMovement();
       if (vector3d.y < 0.0D) {
          double d0 = entity instanceof LivingEntity ? 0.5D : 0.3D;
          entity.setDeltaMovement(vector3d.x, -vector3d.y * d0, vector3d.z);
       }
    }

}
