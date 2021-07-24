package com.nekomaster1000.infernalexp.world.gen.structures;

import com.mojang.serialization.Codec;
import com.nekomaster1000.infernalexp.init.IEBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

public abstract class IEStructure<F extends FeatureConfiguration> extends StructureFeature<F> {

    public IEStructure(Codec<F> codec) {
        super(codec);
    }

    public abstract StructureFeatureConfiguration getSeparationSettings();

    public abstract boolean shouldTransformLand();

    // Gets the y-value for where the structure will spawn
    public static int getYPos(ChunkGenerator chunkGenerator, int x, int z, WorldgenRandom random) {
        // Gets a random value between sea level and the max build height and grabs its column
        int y = chunkGenerator.getSeaLevel() + random.nextInt(chunkGenerator.getGenDepth() - 2 - chunkGenerator.getSeaLevel());
        BlockGetter blockColumn = chunkGenerator.getBaseColumn(x, z);

        BlockPos pos = new BlockPos(x, y, z);

        // Proceeds downwards through the column until it finds a space where there is a solid block with air above it and returns that height
        while (y > chunkGenerator.getSeaLevel()) {
            BlockState checkAir = blockColumn.getBlockState(pos.below(y));
            BlockState checkBlock = blockColumn.getBlockState(pos.below(y + 1));

            if (checkAir.isAir() && (checkBlock.is(IEBlocks.GLOWDUST_SAND.get()) || checkBlock.isFaceSturdy(blockColumn, pos.below(y), Direction.UP))) {
                return y;
            }

            y--;
        }

        // Returns 0 if no valid space is found
        return 0;
    }

    public static abstract class IEStart<F extends FeatureConfiguration> extends StructureStart<F> {

        public IEStart(StructureFeature<F> structure, int chunkX, int chunkY, BoundingBox mutableBoundingBox, int reference, long seed) {
            super(structure, chunkX, chunkY, mutableBoundingBox, reference, seed);
        }

        // Gets the y-value for where the structure will spawn
        public static int getYPos(ChunkGenerator chunkGenerator, int x, int z, WorldgenRandom random) {
            // Gets a random value between sea level and the max build height and grabs its column
            int y = chunkGenerator.getSeaLevel() + random.nextInt(chunkGenerator.getGenDepth() - 2 - chunkGenerator.getSeaLevel());
            BlockGetter blockColumn = chunkGenerator.getBaseColumn(x, z);

            BlockPos pos = new BlockPos(x, y, z);

            // Proceeds downwards through the column until it finds a space where there is a solid block with air above it and returns that height
            while (y > chunkGenerator.getSeaLevel()) {
                BlockState checkAir = blockColumn.getBlockState(pos.below(y));
                BlockState checkBlock = blockColumn.getBlockState(pos.below(y + 1));

                if (checkAir.isAir() && (checkBlock.is(IEBlocks.GLOWDUST_SAND.get()) || checkBlock.isFaceSturdy(blockColumn, pos.below(y), Direction.UP))) {
                    return y;
                }

                y--;
            }

            // Returns 0 if no valid space is found
            return 0;
        }
    }
}
