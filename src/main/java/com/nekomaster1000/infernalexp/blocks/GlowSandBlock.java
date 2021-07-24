package com.nekomaster1000.infernalexp.blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class GlowSandBlock extends SandBlock {

    public GlowSandBlock(int dustColorIn, Properties properties) {
        super(dustColorIn, properties);
    }

    /**
     * Override this method to return true if the block should behave like an infinite fire source
     */
    @Override
    public boolean isFireSource(BlockState state, LevelReader world, BlockPos pos, Direction side) {
        return true;
    }
}
