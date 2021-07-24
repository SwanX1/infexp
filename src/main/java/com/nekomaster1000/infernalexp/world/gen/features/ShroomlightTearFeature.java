package com.nekomaster1000.infernalexp.world.gen.features;

import com.mojang.serialization.Codec;

import com.nekomaster1000.infernalexp.blocks.ShroomlightFungusBlock;
import com.nekomaster1000.infernalexp.init.IEBlocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class ShroomlightTearFeature extends Feature<NoneFeatureConfiguration> {

    public ShroomlightTearFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator generator, Random random, BlockPos pos, NoneFeatureConfiguration config) {
        int i = 0;
        int amount = 10;
        AttachFace face;
        boolean warpedForest;

        if (world.getBiome(pos).getRegistryName().getPath().equals("warped_forest")) {
            face = AttachFace.FLOOR;
            warpedForest = true;

        } else {
            face = AttachFace.CEILING;
            warpedForest = false;
        }

        // Try to place Shroomlight Tear 128 times
        for (int j = 0; j < 64; j++) {
            // Randomize the location of the next luminous fungus to be placed
            BlockState state = IEBlocks.SHROOMLIGHT_FUNGUS.get().defaultBlockState().setValue(ShroomlightFungusBlock.FACE, face);
            BlockPos blockpos = pos.offset(random.nextInt(10) - random.nextInt(20), random.nextInt(4) - random.nextInt(8), random.nextInt(10) - random.nextInt(20));

            // If the randomly chosen location is valid, then place the fungus
            if (world.isEmptyBlock(blockpos) && state.canSurvive(world, blockpos) && (world.getBlockState(warpedForest ? blockpos.below() : blockpos.above()) == Blocks.SHROOMLIGHT.defaultBlockState())) {
                world.setBlock(blockpos, state, 2);
                i++;
            }


            // If we have placed the max amount of Shroomlight Tear, then return
            if (i >= amount) {
                return true;
            }
        }

        return false;
    }
}
