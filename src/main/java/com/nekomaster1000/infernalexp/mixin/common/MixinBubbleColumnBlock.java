package com.nekomaster1000.infernalexp.mixin.common;

import com.nekomaster1000.infernalexp.init.IEBlocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BubbleColumnBlock.class)
public class MixinBubbleColumnBlock {

    @Inject(at = @At("HEAD"), method = "isValidPosition", cancellable = true)
    public void IE_isValidPosition(BlockState state, LevelReader worldIn, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockstate_IE = worldIn.getBlockState(pos.below());

        if (blockstate_IE.is(IEBlocks.BASALTIC_MAGMA.get())) {
            cir.setReturnValue(true);
        }
    }
}
