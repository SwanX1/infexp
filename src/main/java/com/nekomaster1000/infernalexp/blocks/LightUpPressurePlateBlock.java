package com.nekomaster1000.infernalexp.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;

public class LightUpPressurePlateBlock extends PressurePlateBlock {
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public LightUpPressurePlateBlock(Sensitivity sensitivityIn, Properties propertiesIn) {
		super(sensitivityIn, propertiesIn);
		this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false).setValue(LIT, false));
	}

	@Override
	protected BlockState setSignalForState(BlockState state, int strength) {
		return state.setValue(POWERED, strength > 0).setValue(LIT, strength > 0);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(POWERED).add(LIT);
	}

}
