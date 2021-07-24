package com.nekomaster1000.infernalexp.blocks;

import com.nekomaster1000.infernalexp.init.IETags;

import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;

public class GlowFireBlock extends BaseFireBlock {

    public GlowFireBlock(Properties builder) {
            super(builder, 2.0F);
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
            return this.canSurvive(stateIn, worldIn, currentPos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
            return isGlowFireBase(worldIn.getBlockState(pos.below()).getBlock());
    }

    public static boolean isGlowFireBase(Block block) {
            return IETags.Blocks.GLOW_FIRE_BASE_BLOCKS.contains(block);
    }

    protected boolean canBurn(BlockState stateIn) {
            return true;
    }

}
