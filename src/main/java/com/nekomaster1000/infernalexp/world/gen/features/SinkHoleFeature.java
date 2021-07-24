package com.nekomaster1000.infernalexp.world.gen.features;

import com.mojang.serialization.Codec;

import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.util.ShapeUtil;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class SinkHoleFeature extends Feature<NoneFeatureConfiguration> {
    public SinkHoleFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    private static final int minRadius = 2;
    private static final int maxRadius = 4;

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator generator, Random random, BlockPos pos, NoneFeatureConfiguration config) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos().set(pos);
        if (!world.isEmptyBlock(mutableBlockPos) || world.getBlockState(mutableBlockPos.move(Direction.DOWN)).getBlock() != IEBlocks.GLOWDUST_SAND.get()) {
            return false;
        } else {
            int radius = minRadius + random.nextInt(maxRadius - minRadius);
            int depth = 10 + random.nextInt(6);

            // Check to see if we are on the bottom layer of the hole
            mutableBlockPos.set(pos);
            for (int y = 1; y < depth; y++) {
                mutableBlockPos.move(Direction.DOWN);
                if (world.getBlockState(mutableBlockPos).is(Blocks.AIR)) return false;
            }

            // Build the walls down a few blocks so if the sink hole spawns on a slope it isn't open from the side
            for (BlockPos point : ShapeUtil.generateSolidCircle(radius + 1)) {
                mutableBlockPos.set(pos);
                mutableBlockPos.move(point.getX(), point.getY(), point.getZ());
                for (int y = 0; y < 3; y++) {
                    world.setBlock(mutableBlockPos.move(Direction.DOWN), IEBlocks.GLOWDUST_SAND.get().defaultBlockState(), 2);
                }
            }

            // Dig down by depth
            for (BlockPos point : ShapeUtil.generateSolidCircle(radius)) {
                mutableBlockPos.set(pos);
                mutableBlockPos.move(point.getX(), point.getY(), point.getZ());
                mutableBlockPos.move(Direction.DOWN);
                world.setBlock(mutableBlockPos, IEBlocks.TRAPPED_GLOWDUST_SAND.get().defaultBlockState(), 2);

                for (int y = 2; y < depth; y++) {
                    mutableBlockPos.move(Direction.DOWN);
                    carveSpot(world, generator, mutableBlockPos);
                }
            }

            // Round off the bottom with a sphere
            for (BlockPos point : ShapeUtil.generateSolidSphere(radius)) {
                mutableBlockPos.set(pos);
                mutableBlockPos.move(point.getX(), point.getY(), point.getZ());
                mutableBlockPos.move(Direction.DOWN, depth + 1);
                carveSpot(world, generator, mutableBlockPos);
            }

            // Create fairy ring of luminous fungus around sinkhole
//            for (BlockPos point : ShapeUtil.generateCircle(radius + 2)) {
//                for (int y = -5; y < 5; y++) {
//                    if (IEBlocks.LUMINOUS_FUNGUS.get().getDefaultState().isValidPosition(world, pos.add(point).down(y))) {
//                        world.setBlockState(pos.add(point).down(y), IEBlocks.LUMINOUS_FUNGUS.get().getDefaultState(), 2);
//                        break;
//                    }
//                }
//            }

            return true;
        }
    }

    private void carveSpot(WorldGenLevel world, ChunkGenerator generator, BlockPos.MutableBlockPos mutableBlockPos) {
        // only carve spot if space isnt liquid and above isnt liquid
        if (world.getBlockState(mutableBlockPos).getFluidState().isEmpty()) {

            if (mutableBlockPos.getY() < generator.getSeaLevel()) {
                world.setBlock(mutableBlockPos, Blocks.LAVA.defaultBlockState(), 3);
            } else {
                world.setBlock(mutableBlockPos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }
}
