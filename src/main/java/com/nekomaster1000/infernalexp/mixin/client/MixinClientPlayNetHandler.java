package com.nekomaster1000.infernalexp.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.nekomaster1000.infernalexp.client.sound.GlowsquitoFlightSound;
import com.nekomaster1000.infernalexp.entities.GlowsquitoEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.protocol.game.ClientboundAddMobPacket;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@Mixin(ClientPacketListener.class)
public class MixinClientPlayNetHandler {

	@Shadow
	private Minecraft client;

	@Inject(method = "handleSpawnMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;addEntity(ILnet/minecraft/entity/Entity;)V", shift = Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
	private void IE_playGlowsquitoSound(ClientboundAddMobPacket packetIn, CallbackInfo ci, double d0, double d1, double d2, float f, float f1, LivingEntity livingentity) {
		if (livingentity instanceof GlowsquitoEntity) {
			this.client.getSoundManager().queueTickingSound(new GlowsquitoFlightSound((GlowsquitoEntity) livingentity));
		}
	}

}
