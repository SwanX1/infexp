package com.nekomaster1000.infernalexp.blocks;

import com.nekomaster1000.infernalexp.init.IEBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.item.context.BlockPlaceContext;

import javax.annotation.Nullable;

public class GlowdustBlock extends SnowLayerBlock {

	public GlowdustBlock(Properties properties) {
		super(properties);
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		if (blockstate.is(this)) {
			int i = blockstate.getValue(LAYERS);
			if (i < 7) {
				return blockstate.setValue(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
			} else {
				return IEBlocks.GLOWDUST_SAND.get().defaultBlockState();
			}
		} else {
			return super.getStateForPlacement(context);
		}
    }
}
