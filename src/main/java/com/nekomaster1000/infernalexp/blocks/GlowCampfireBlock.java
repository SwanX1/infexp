package com.nekomaster1000.infernalexp.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.nekomaster1000.infernalexp.blockentities.GlowCampfireBlockEntity;

import net.minecraft.core.BlockPos;

public class GlowCampfireBlock extends CampfireBlock {
    public GlowCampfireBlock(boolean smokey, int fireDamage, BlockBehaviour.Properties properties) {
        super(smokey, fireDamage, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new GlowCampfireBlockEntity(pos, blockState);
    }
}
