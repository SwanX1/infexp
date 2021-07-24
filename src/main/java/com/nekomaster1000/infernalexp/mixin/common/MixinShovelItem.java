package com.nekomaster1000.infernalexp.mixin.common;

import com.nekomaster1000.infernalexp.blocks.GlowCampfireBlock;
import com.nekomaster1000.infernalexp.init.IEBlocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShovelItem.class)
public class MixinShovelItem {

    @Inject(at = @At("HEAD"), method = "onItemUse", cancellable = true)
    private void IE_onItemUse(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Player playerentity = context.getPlayer();
        if (state.getBlock() instanceof GlowCampfireBlock && state.getValue(GlowCampfireBlock.LIT)) {
            if (!world.isClientSide()) {
                world.levelEvent(null, 1009, pos, 0);
            }
            GlowCampfireBlock.dowse(world, pos, state);
            world.setBlockAndUpdate(pos, state.setValue(GlowCampfireBlock.LIT, false));
            cir.setReturnValue(InteractionResult.SUCCESS);
        } else if (state.getBlock() instanceof NyliumBlock || state.getBlock().is(Blocks.SOUL_SOIL) ) {
            if (state.getBlock().is(Blocks.CRIMSON_NYLIUM)) {
                state = IEBlocks.CRIMSON_NYLIUM_PATH.get().defaultBlockState();
            } else if (state.getBlock().is(Blocks.WARPED_NYLIUM)){
                state = IEBlocks.WARPED_NYLIUM_PATH.get().defaultBlockState();
            } else {
                state = IEBlocks.SOUL_SOIL_PATH.get().defaultBlockState();
            }
            world.playSound(playerentity, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (playerentity != null) {
                context.getItemInHand().hurtAndBreak(1, playerentity, (player) -> player.broadcastBreakEvent(context.getHand()));
            }
            world.setBlockAndUpdate(pos, state);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }

}
