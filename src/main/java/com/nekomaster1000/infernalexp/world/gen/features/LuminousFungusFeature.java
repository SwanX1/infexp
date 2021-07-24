package com.nekomaster1000.infernalexp.world.gen.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import com.nekomaster1000.infernalexp.blocks.LuminousFungusBlock;
import com.nekomaster1000.infernalexp.init.IEBlocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LuminousFungusFeature extends Feature<NoneFeatureConfiguration> {
    public LuminousFungusFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator generator, Random random, BlockPos pos, NoneFeatureConfiguration config) {
        int i = 0;
        int amount;
        AttachFace face;

        int pickFacing = random.nextInt(3);

        // Pick whether the fungus will spawn on the ceiling or on the floor and set the amount to spawn appropriately
        if (pickFacing == 0) {
            face = AttachFace.CEILING;
            amount = 4;
        } else {
            face = AttachFace.FLOOR;
            amount = 10;
        }

        // Try to place luminous fungus 128 times
        for (int j = 0; j < 128; j++) {
            // Randomize the location of the next luminous fungus to be placed
            BlockState state = IEBlocks.LUMINOUS_FUNGUS.get().defaultBlockState().setValue(LuminousFungusBlock.FACE, face);
            BlockPos blockpos = pos.offset(random.nextInt(10) - random.nextInt(20), random.nextInt(4) - random.nextInt(8), random.nextInt(10) - random.nextInt(20));

            // If the randomly chosen location is valid, then place the fungus
            if (world.isEmptyBlock(blockpos) && state.canSurvive(world, blockpos) && (world.getBlockState(blockpos.above()) == IEBlocks.DULLSTONE.get().defaultBlockState() || world.getBlockState(blockpos.below()) == IEBlocks.GLOWDUST_SAND.get().defaultBlockState())) {
                world.setBlock(blockpos, state, 2);
                i++;
            }

            // If we have placed the max amount of luminous fungus, then return
            if (i >= amount) {
                return true;
            }
        }

        return false;
    }
}

