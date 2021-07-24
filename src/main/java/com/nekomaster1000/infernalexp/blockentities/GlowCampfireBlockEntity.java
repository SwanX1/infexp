package com.nekomaster1000.infernalexp.blockentities;

import com.nekomaster1000.infernalexp.init.IEBlockEntityTypes;

import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class GlowCampfireBlockEntity extends CampfireBlockEntity {
    public GlowCampfireBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
    }

    public BlockEntityType<?> getType() {
        return IEBlockEntityTypes.GLOW_CAMPFIRE.get();
    }
}
