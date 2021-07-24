package com.nekomaster1000.infernalexp.world.gen.features;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.nekomaster1000.infernalexp.init.IEBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DullthornsFeature extends Feature<NoneFeatureConfiguration> {
    public DullthornsFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(WorldGenLevel worldIn, ChunkGenerator generator, Random random, BlockPos pos, NoneFeatureConfiguration config) {
        int height = random.nextInt(9) + 1;

        if (!worldIn.isEmptyBlock(pos) || worldIn.getBlockState(pos.below()).getBlock() != IEBlocks.GLOWDUST_SAND.get()) {
            return false;
        } else {
            // Generate dullthorns up "height" blocks unless there is something in the way
            for (int i = 0; i < height; i++) {
                if (worldIn.isEmptyBlock(pos.above(i))) worldIn.setBlock(pos.above(i), IEBlocks.DULLTHORNS.get().defaultBlockState(), 10);
            }
            return true;
        }
    }
}

