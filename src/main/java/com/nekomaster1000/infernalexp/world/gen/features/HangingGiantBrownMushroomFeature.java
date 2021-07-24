package com.nekomaster1000.infernalexp.world.gen.features;

import com.mojang.serialization.Codec;
import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.util.DataUtil;
import com.nekomaster1000.infernalexp.util.ShapeUtil;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class HangingGiantBrownMushroomFeature extends Feature<NoneFeatureConfiguration> {
    public HangingGiantBrownMushroomFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    private static final int minSize = 3;
    private static final int maxSize = 7;

    private static final ResourceLocation enhancedMushroomsBrownStem = new ResourceLocation("enhanced_mushrooms", "brown_mushroom_stem");

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunk, Random random, BlockPos pos, NoneFeatureConfiguration config) {
        if (!world.isEmptyBlock(pos) || world.getBlockState(pos.above()) != IEBlocks.DULLSTONE.get().defaultBlockState()) {
            return false;
        } else {
            // Generate size between minSize and maxSize
            int size = minSize + random.nextInt(maxSize - minSize);
            BlockState enhancedMushroomsBrownStemBlockState = null;

            if (DataUtil.isLoaded("enhanced_mushrooms")) {
                enhancedMushroomsBrownStemBlockState = ForgeRegistries.BLOCKS.getValue(enhancedMushroomsBrownStem).defaultBlockState();
            }

            // Generate stem
            for (int y = 0; y <= size; y++) {
                if (enhancedMushroomsBrownStemBlockState != null) {
                    world.setBlock(pos.below(y), enhancedMushroomsBrownStemBlockState, 2);
                } else {
                    world.setBlock(pos.below(y), Blocks.MUSHROOM_STEM.defaultBlockState(), 2);
                }
            }

            // Generate mushroom cap
            for (BlockPos point : ShapeUtil.generateSolidCircle((float) (size / 2) + 1)) {
                world.setBlock(pos.offset(point.getX(), point.getY() - size, point.getZ()), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState(), 2);
            }

            return true;
        }
    }
}
