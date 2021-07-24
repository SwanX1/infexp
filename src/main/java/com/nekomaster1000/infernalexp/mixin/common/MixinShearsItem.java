package com.nekomaster1000.infernalexp.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.nekomaster1000.infernalexp.init.IEBlocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;

@Mixin(ShearsItem.class)
public class MixinShearsItem {
    @Inject(method = "getDestroySpeed", at = @At("HEAD"), cancellable = true)
    private void IE_getDestroySpeed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> ci) {
        if (state.is(IEBlocks.DULLTHORNS.get())) ci.setReturnValue(15.0F);
    }
}
