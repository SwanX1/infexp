package com.nekomaster1000.infernalexp.world.gen.structures;

import com.mojang.serialization.Codec;
import com.nekomaster1000.infernalexp.InfernalExpansion;
import net.minecraft.world.level.block.state.BlockState;
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

public class BastionOutpostStructure extends IEStructure<NoneFeatureConfiguration> {

	public BastionOutpostStructure(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
		return BastionOutpostStructure.Start::new;
	}

	@Override
	public GenerationStep.Decoration step() {
		return GenerationStep.Decoration.SURFACE_STRUCTURES;
	}

	@Override
	public StructureFeatureConfiguration getSeparationSettings() {
		return new StructureFeatureConfiguration(2, 1, 720435943);
	}

	@Override
	public boolean shouldTransformLand() {
		return true;
	}

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeProvider, long seed, WorldgenRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration config) {
        WorldgenRandom random = new WorldgenRandom(seed + (chunkX * (chunkZ * 17)));

        // Makes cheap check first, if it passes this check, it does a more in-depth check
        if (super.isFeatureChunk(chunkGenerator, biomeProvider, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, config)) {

            int posX = chunkX << 4;
            int posZ = chunkZ << 4;
            int posY = getYPos(chunkGenerator, posX, posZ, random);

            // Checks 9 points within about a chunk of the initial location
            for (int curX = posX - 8; curX <= posX + 8; curX += 8) {
                for (int curZ = posZ - 8; curZ <= posZ + 8; curZ += 8) {

                    // Starts 5 blocks below to check for solid land in each column
                    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(curX, posY - 5, curZ);
                    BlockGetter blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ());

                    // Flag represents a block with air above it
                    boolean flag = false;

                    while (mutable.getY() <= posY + 5) {
                        BlockState state = blockView.getBlockState(mutable);
                        if (state.canOcclude()) {
                            mutable.move(Direction.UP);
                            state = blockView.getBlockState(mutable);

                            if (state.isAir()) {
                                flag = true;
                                break;
                            }

                        } else {
                            mutable.move(Direction.UP);
                        }
                    }

                    // If there is no block with air above it in this range, the structure can't spawn
                    if (!flag) {
                        return false;
                    }

                    // Checks if there are 15 blocks of air above the 5 checked for solid to spawn the structure
                    int minValidSpace = 15;
                    int maxHeight = Math.min(chunkGenerator.getGenDepth(), posY + minValidSpace);

                    while (mutable.getY() < maxHeight) {
                        BlockState state = blockView.getBlockState(mutable);
                        if (!state.isAir()) {
                            return false;
                        }
                        mutable.move(Direction.UP);
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }

	public static class Start extends IEStart<NoneFeatureConfiguration> {
	    private final long seed;

		public Start(StructureFeature<NoneFeatureConfiguration> structure, int chunkX, int chunkZ, BoundingBox mutableBoundingBox, int reference, long seed) {
			super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
			this.seed = seed;
		}

		@Override
		public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager templateManager, int chunkX, int chunkZ, Biome biome, NoneFeatureConfiguration config) {
			WorldgenRandom random = new WorldgenRandom(seed + (chunkX * (chunkZ * 17)));

		    int x = chunkX << 4;
			int z = chunkZ << 4;

			BlockPos pos = new BlockPos(x, getYPos(chunkGenerator, x, z, random), z);

			if (pos.getY() != 0) {
				JigsawPlacement.addPieces(
						dynamicRegistryManager,
						new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(new ResourceLocation(InfernalExpansion.MOD_ID, "bastion_outpost/start_pool")), 1),
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
