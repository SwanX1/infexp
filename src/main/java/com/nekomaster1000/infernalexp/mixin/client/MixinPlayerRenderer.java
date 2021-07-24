package com.nekomaster1000.infernalexp.mixin.client;

import com.nekomaster1000.infernalexp.init.IEItems;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class MixinPlayerRenderer {

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/item/UseAction;"), method = "getArmPose(Lnet/minecraft/client/entity/player/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/renderer/entity/model/BipedModel$ArmPose;", cancellable = true)
	private static void renderWhipInfernalExpansion(AbstractClientPlayer playerEntity, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
		if (playerEntity.getItemInHand(hand).sameItem(IEItems.BLINDSIGHT_TONGUE_WHIP.get().getDefaultInstance())) {
			cir.setReturnValue(HumanoidModel.ArmPose.THROW_SPEAR);
		}
	}
}
