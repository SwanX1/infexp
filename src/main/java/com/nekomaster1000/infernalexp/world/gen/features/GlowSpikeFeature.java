package com.nekomaster1000.infernalexp.world.gen.features;

import com.mojang.serialization.Codec;

import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.util.ShapeUtil;
import com.nekomaster1000.infernalexp.world.gen.features.config.GlowSpikeFeatureConfig;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;
import java.util.Random;

public class GlowSpikeFeature extends Feature<GlowSpikeFeatureConfig> {

	public GlowSpikeFeature(Codec<GlowSpikeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(WorldGenLevel world, ChunkGenerator generator, Random random, BlockPos pos, GlowSpikeFeatureConfig config) {

		if ((world.isEmptyBlock(pos) || world.getBlockState(pos.below()).getBlock() == IEBlocks.GLOWDUST_SAND.get()) || (world.getBlockState(pos).getBlock() == Blocks.LAVA && world.getBlockState(pos.below()).getBlock() != Blocks.LAVA)) {
			// Generate a random height, diameter and offset
			int height = config.minHeight + random.nextInt(config.maxHeight - config.minHeight);
			int diameter = config.minDiameter + random.nextInt(config.maxDiameter - config.minDiameter);
			int xOffset = -config.maxXOffset + random.nextInt(config.maxXOffset * 2);
			int zOffset = -config.maxZOffset + random.nextInt(config.maxZOffset * 2);

			List<BlockPos> points = ShapeUtil.generateSolidCircle((float) diameter / 2);

			// Check to see if the glowspike will generate on the ground instead of
			// floating, if not, move one down and try again
			for (BlockPos point : points) {
				BlockPos pointPos = new BlockPos(pos.getX() + point.getX(), pos.getY(), pos.getZ() + point.getZ());

				if (world.getBlockState(pointPos.below()).isAir()) {
					if (random.nextBoolean() && random.nextBoolean()) {
						return place(world, generator, random, pos.below(), config);
					}
					return false;
				} else if (world.getBlockState(pointPos.below()).getBlock() == Blocks.LAVA) {
					return place(world, generator, random, pos.below(), config);
				}
			}

			// Place lines from each point in base to the peak point
			for (BlockPos point : points) {
				placeGlowSpikeLine(world, pos.offset(point.getX(), 0, point.getZ()), pos.offset(xOffset, height, zOffset), random, config);
			}

			return true;
		} else {
			return false;
		}
	}

	private void placeGlowSpikeLine(WorldGenLevel world, BlockPos startPos, BlockPos endPos, Random random, GlowSpikeFeatureConfig config) {
		List<BlockPos> line = ShapeUtil.generateLine(startPos, endPos);

		for (int i = 0; i < line.size(); i++) {
			BlockPos pos = line.get(i);

			// Skip parts of the spike generating above bedrock
			if (pos.getY() >= 128 || world.getBlockState(pos).equals(Blocks.BEDROCK.defaultBlockState())) {
				continue;
			}

			// Finds what percentage of the line has been built and then adds some
			// randomness to it to make for a
			// more gradual change between blocks
			float percentage = (((float) i / line.size()) - config.blockDitheringAmount / 2) + (random.nextFloat() * config.blockDitheringAmount);

			// Generate blocks from dullstone to dimstone to glowstone, or reversed if
			// darkAtTop is true
			if (percentage <= 0.33) {
				if (config.darkAtTop)
					world.setBlock(pos, Blocks.GLOWSTONE.defaultBlockState(), 2);
				else
					world.setBlock(new BlockPos(pos), IEBlocks.DULLSTONE.get().defaultBlockState(), 2);
			} else if (percentage > 0.33 && percentage <= 0.66) {
				world.setBlock(new BlockPos(pos), IEBlocks.DIMSTONE.get().defaultBlockState(), 2);
			} else {
				if (config.darkAtTop)
					world.setBlock(new BlockPos(pos), IEBlocks.DULLSTONE.get().defaultBlockState(), 2);
				else
					world.setBlock(new BlockPos(pos), Blocks.GLOWSTONE.defaultBlockState(), 2);
			}
		}
	}
}
