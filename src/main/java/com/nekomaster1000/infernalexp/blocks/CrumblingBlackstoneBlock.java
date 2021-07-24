package com.nekomaster1000.infernalexp.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrumblingBlackstoneBlock extends FallingBlock {
    public CrumblingBlackstoneBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState state, BlockGetter reader, BlockPos pos) {
        return -462091;
    }
}
