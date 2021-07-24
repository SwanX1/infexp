package com.nekomaster1000.infernalexp.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.nekomaster1000.infernalexp.client.DynamicLightingHandler;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

@Mixin(value = BlockGetter.class, priority = 200)
public interface MixinIBlockReader {

	@Overwrite
	default int getLightValue(BlockPos pos) {
		if (DynamicLightingHandler.LIGHT_SOURCES.containsKey(pos) && DynamicLightingHandler.LIGHT_SOURCES.get(pos).shouldKeep) {
			return 10;
		}
		return this.getBlockState(pos).getLightValue((BlockGetter) this, pos);
	}

	@Shadow
	BlockState getBlockState(BlockPos pos);

}
