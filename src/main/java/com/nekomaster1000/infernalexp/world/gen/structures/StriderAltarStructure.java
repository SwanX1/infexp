package com.nekomaster1000.infernalexp.world.gen.structures;

import com.mojang.serialization.Codec;

import com.nekomaster1000.infernalexp.InfernalExpansion;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import net.minecraft.world.level.levelgen.feature.StructureFeature.StructureStartFactory;

public class StriderAltarStructure extends IEStructure<NoneFeatureConfiguration> {
    public StriderAltarStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return StriderAltarStructure.Start::new;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public StructureFeatureConfiguration getSeparationSettings() {
        return new StructureFeatureConfiguration(10, 5, 80837592);
    }

    @Override
    public boolean shouldTransformLand() {
        return true;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeProvider, long seed, WorldgenRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration config) {

        // Makes cheap check first, if it passes this check, it does a more in-depth check
        if (super.isFeatureChunk(chunkGenerator, biomeProvider, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, config)) {

            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(chunkX << 4, chunkGenerator.getSeaLevel(), chunkZ << 4);
            BlockGetter blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ());

            // Makes sure there are at least 20 blocks of air above the lava ocean to spawn the altar
            int minValidSpace = 20;
            int maxHeight = Math.min(chunkGenerator.getGenDepth(), chunkGenerator.getSeaLevel() + minValidSpace);

            while(mutable.getY() < maxHeight){
                BlockState state = blockView.getBlockState(mutable);
                if(!state.isAir()){
                    return false;
                }
                mutable.move(Direction.UP);
            }
        } else {
            return false;
        }

        return true;
    }

    public static class Start extends IEStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structure, int chunkX, int chunkZ, BoundingBox mutableBoundingBox, int reference, long seed) {
            super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
        }

        public int getLavaY(ChunkGenerator chunkGenerator, int x, int z) {
            int y = chunkGenerator.getSeaLevel();
            BlockGetter blockColumn = chunkGenerator.getBaseColumn(x, z);

            BlockPos pos = new BlockPos(x, y, z);

            int topDown = 1;

            if (blockColumn.getBlockState(pos).canOcclude()) {
                return 0;
            }

            while (topDown <= y) {
                BlockState checkLava = blockColumn.getBlockState(pos.below(topDown));
                BlockState checkBlock = blockColumn.getBlockState(pos.below(topDown + 1));

                if (checkLava.is(Blocks.LAVA) && checkBlock.isFaceSturdy(blockColumn, pos.below(y), Direction.UP)) {
                    return y-topDown;
                }

                topDown++;
            }

            return 0;
        }



        @Override
        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager templateManager, int chunkX, int chunkZ, Biome biome, NoneFeatureConfiguration config) {
            int x = chunkX << 4;
            int z = chunkZ << 4;

            BlockPos pos = new BlockPos(x, getLavaY(chunkGenerator, x, z), z);

            if (pos.getY() != 0) {
                JigsawPlacement.addPieces(
                    dynamicRegistryManager,
                    new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(new ResourceLocation(InfernalExpansion.MOD_ID, "strider_altar/start_pool")), 1),
                    PoolElementStructurePiece::new,
                    chunkGenerator,
                    templateManager,
                    pos,
                    this.pieces,
                    this.random,
                    false,
                    false
                );

                this.calculateBoundingBox();
            }
        }
    }
}
