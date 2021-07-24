package com.nekomaster1000.infernalexp.world.gen.processors;

import com.mojang.serialization.Codec;

import com.nekomaster1000.infernalexp.init.IEProcessors;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.Random;

public class LootChestProcessor extends StructureProcessor {

	/*
	To use this processor, add a structure block in place of the chest, make sure the structure block is in data mode
	Then for "Custom Data Tag Name" put in without the brackets chest-(direction facing. east, west, south, north)-(resource location of loot table)
	Make sure to add the dashes in between, as that is what separates the data
	 */

	public static final LootChestProcessor INSTANCE = new LootChestProcessor();
	public static final Codec<LootChestProcessor> CODEC = Codec.unit(() -> INSTANCE);

	@ParametersAreNonnullByDefault
	@Nullable
	@Override
	public StructureTemplate.StructureBlockInfo process(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoRelative, StructureTemplate.StructureBlockInfo structureBlockInfoGlobal, StructurePlaceSettings placementSettings, @Nullable StructureTemplate template) {
		BlockState blockState = structureBlockInfoGlobal.state;

		if (blockState.is(Blocks.STRUCTURE_BLOCK)) {
			String[] nbtData = structureBlockInfoGlobal.nbt.getString("metadata").split("-");

			if (nbtData[0].equals("chest")) {
				ChestBlockEntity tileEntity = new ChestBlockEntity();
				tileEntity.setLootTable(new ResourceLocation(nbtData[2]), new Random().nextLong());

				return new StructureTemplate.StructureBlockInfo(structureBlockInfoGlobal.pos, Blocks.CHEST.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Objects.requireNonNull(Direction.byName(nbtData[1]))), tileEntity.serializeNBT());
			}
		}

		return structureBlockInfoGlobal;
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return IEProcessors.LOOT_CHEST_PROCESSOR;
	}

}
