package com.nekomaster1000.infernalexp.mixin.common;

import com.nekomaster1000.infernalexp.access.FireTypeAccess;
import com.nekomaster1000.infernalexp.access.FireTypeAccess.KnownFireTypes;
import com.nekomaster1000.infernalexp.blocks.GlowFireBlock;
import com.nekomaster1000.infernalexp.init.IEBlocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

import net.minecraftforge.fml.ModList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseFireBlock.class)
public abstract class MixinAbstractFireBlock extends Block {

	private MixinAbstractFireBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Inject(method = "getFireForPlacement", at = @At("HEAD"), cancellable = true)
	private static void IE_getFireForPlacement(BlockGetter reader, BlockPos pos, CallbackInfoReturnable<BlockState> info) {
		if (GlowFireBlock.isGlowFireBase(reader.getBlockState(pos.below()).getBlock())) {
			info.setReturnValue(IEBlocks.GLOW_FIRE.get().defaultBlockState());
		}
	}

	@Inject(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;forceFireTicks(I)V"))
	private void IE_setCustomFires(BlockState state, Level worldIn, BlockPos pos, Entity entityIn, CallbackInfo info) {
		FireTypeAccess access = ((FireTypeAccess) entityIn);
		if (state.is(Blocks.SOUL_FIRE)) {
			access.setFireType(KnownFireTypes.SOUL_FIRE);
		} else if (state.is(IEBlocks.GLOW_FIRE.get())) {
			access.setFireType(KnownFireTypes.GLOW_FIRE);
		} else {
			access.setFireType(KnownFireTypes.FIRE);
		}

		if (ModList.get().isLoaded("endergetic")) {
			if (state.getBlock().getRegistryName().equals(new ResourceLocation("endergetic", "ender_fire")) && state.getBlock() instanceof BaseFireBlock) {
				access.setFireType(KnownFireTypes.ENDER_FIRE);
			}
		}

	}
}
