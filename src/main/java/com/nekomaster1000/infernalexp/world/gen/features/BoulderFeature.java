package com.nekomaster1000.infernalexp.world.gen.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.util.ShapeUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;

public class BoulderFeature extends Feature<BlockStateConfiguration> {

    public BoulderFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator generator, Random random, BlockPos pos, BlockStateConfiguration config) {
        int radius = new int[]{1, 1, 2, 2, 2, 2, 3}[random.nextInt(7)];

        if (!world.isEmptyBlock(pos) || world.getBlockState(pos.below()).getBlock() != IEBlocks.GLOWDUST_SAND.get() || world.isEmptyBlock(pos.below(radius)) || random.nextInt(3) == 2) {
            return false;
        } else {
            placeSphere(world, random, pos.below(Math.floorDiv(radius, 3)), radius, config);
            return true;
        }
    }

    private void placeSphere(WorldGenLevel world, Random random, BlockPos pos, int radius, BlockStateConfiguration config) {

        // Checks to see if block is within radius to the center of the sphere, if so, fill in blocks

        for (BlockPos point : ShapeUtil.generateSolidSphere(radius)) {
            world.setBlock(pos.offset(point), config.state, 2);

            // Add some randomness so that the boulders aren't perfect
            randomNoise(world, random, pos, config);
        }
    }

    private void randomNoise(WorldGenLevel world, Random random, BlockPos pos, BlockStateConfiguration config) {
        switch (random.nextInt(8)) {
            case 0:
                if (!world.getBlockState(pos.west().below()).isAir()) {
                    world.setBlock(pos.west(), config.state, 2);
                }
            case 1:
                if (!world.getBlockState(pos.north().below()).isAir()) {
                    world.setBlock(pos.north(), config.state, 2);
                }
            case 2:
                if (!world.getBlockState(pos.east().below()).isAir()) {
                    world.setBlock(pos.east(), config.state, 2);
                }
            case 3:
                if (!world.getBlockState(pos.south().below()).isAir()) {
                    world.setBlock(pos.south(), config.state, 2);
                }
            case 4:
                if (!world.getBlockState(pos.above().below()).isAir()) {
                    world.setBlock(pos.above(), config.state, 2);
                }
        }
    }
}
