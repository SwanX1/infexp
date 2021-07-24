package com.nekomaster1000.infernalexp.blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;

import java.util.Random;

public class BasaltIronOreBlock extends RotatedPillarBlock {

    public BasaltIronOreBlock(Properties properties) {
        super(properties);
    }

    protected int getExperience(Random rand) {
        return Mth.nextInt(rand, 0, 1);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.level.LevelReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}
