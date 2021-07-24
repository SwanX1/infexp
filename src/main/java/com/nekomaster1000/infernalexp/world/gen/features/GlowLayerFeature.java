package com.nekomaster1000.infernalexp.world.gen.features;

import com.mojang.serialization.Codec;
import com.nekomaster1000.infernalexp.blocks.GlowdustBlock;
import com.nekomaster1000.infernalexp.init.IEBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class GlowLayerFeature extends Feature<NoneFeatureConfiguration> {

    public GlowLayerFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator generator, Random random, BlockPos pos, NoneFeatureConfiguration config) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos().set(pos);
        BlockPos.MutableBlockPos mutableBlockPosNeighbors = new BlockPos.MutableBlockPos().set(mutableBlockPos);
        boolean doExpandedPlacing = isMultipleBiomesInChunk(world, pos, mutableBlockPos);

        ChunkAccess cachedChunk = world.getChunk(mutableBlockPos);
        int minimumRange = doExpandedPlacing ? -12 : 0;
        int maxRange = doExpandedPlacing ? 28 : 16;
        for (int x = minimumRange; x < maxRange; x++) {
            for (int z = minimumRange; z < maxRange; z++) {

                // Only check between top land and sealevel.
                // Prevents glowdust in caves below sealevel and better performance if player removes ceiling of Nether with datapack.
                int maxY = generator.getBaseHeight(pos.getX() + x, pos.getZ() + z, Heightmap.Types.MOTION_BLOCKING);
                for (int y = maxY; y > generator.getSeaLevel(); y--) {
                    mutableBlockPos.set(pos).move(x, y, z);

                    // recache chunk if we need to. Faster performance this way when chunk scanning like we are.
                    if (cachedChunk.getPos().x != x >> 4 || cachedChunk.getPos().z != z >> 4) {
                        cachedChunk = world.getChunk(mutableBlockPos);
                    }

                    // Checks for if we are at glowdust sand and moves up to check for air space.
                    BlockState currentBlock = cachedChunk.getBlockState(mutableBlockPos);
                    if (currentBlock.is(IEBlocks.GLOWDUST_SAND.get()) &&
                        cachedChunk.getBlockState(mutableBlockPos.move(Direction.UP)).isAir()) {
                        // we are now in the air space above Glowdust sand. Check if any of the 8 blocks around it is glowdust sand
                        // maximum return is 8.
                        int glowdustLayerHeight = numberOfGlowdustSandNearby(world, mutableBlockPos, mutableBlockPosNeighbors);
                        if (glowdustLayerHeight > 0) {
                            world.setBlock(mutableBlockPos, IEBlocks.GLOWDUST.get().defaultBlockState().setValue(GlowdustBlock.LAYERS, glowdustLayerHeight), 3);
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean isMultipleBiomesInChunk(WorldGenLevel world, BlockPos pos, BlockPos.MutableBlockPos mutableBlockPos) {
        // check some edges of the chunk for invalid biomes to know when to
        // do better placement that follows biome borders better.
        Biome centerBiome = world.getBiome(mutableBlockPos.set(pos).move(8, 0, 8));
        for (int x = 0; x <= 16; x += 8) {
            for (int z = 0; z <= 16; z += 8) {
                if (x != 8 && z != 8) {
                    mutableBlockPos.set(pos);
                    mutableBlockPos.move(x, 0, z);

                    // move position back to edge of the chunk we are in instead of next chunk over
                    if (x == 16) mutableBlockPos.move(-1, 0, 0);
                    if (z == 16) mutableBlockPos.move(0, 0, -1);

                    if (centerBiome != world.getBiome(mutableBlockPos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int numberOfGlowdustSandNearby(WorldGenLevel world, BlockPos.MutableBlockPos mutableBlockPos, BlockPos.MutableBlockPos mutableBlockPosNeighbors) {
        int glowdustSandCount = 0;
        int radius = 2;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x == 0 && z == 0) continue;

                mutableBlockPosNeighbors.set(mutableBlockPos).move(x, 0, z);
                BlockState neighborBlock = world.getBlockState(mutableBlockPosNeighbors);
                // Do not use .isSolid check because Glowdust Sand is marked notSolid (cause it uses Glowstone properties)
                if (neighborBlock.is(IEBlocks.GLOWDUST_SAND.get())) {
                    glowdustSandCount++;
                }
            }
        }
        // changes the shape and height of layers. Modify the algorithm for neat effects

        // change radius to 1 and use this version for only 1 layer high glowdust layer right next to ledges
        //return Math.min(glowdustSandCount, 1);

        // change radius to 2 and use this version for 2x2 barely sloping dust that looks neat
        return Math.min((int)Math.ceil((glowdustSandCount) / 6D), 8);
    }
}
