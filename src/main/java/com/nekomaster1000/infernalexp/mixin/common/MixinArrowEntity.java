package com.nekomaster1000.infernalexp.mixin.common;

import com.nekomaster1000.infernalexp.access.AbstractArrowEntityAccess;
import com.nekomaster1000.infernalexp.init.IEEffects;
import com.nekomaster1000.infernalexp.init.IEParticleTypes;
import com.nekomaster1000.infernalexp.init.IEPotions;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(Arrow.class)
public abstract class MixinArrowEntity {
	@Final
	@Shadow
	private Set<MobEffectInstance> customPotionEffects;

	@Shadow
	protected abstract void refreshColor();

	@Shadow
	private Potion potion;

	@Inject(at = @At("RETURN"), method = "setPotionEffect")
	private void setPotionEffectInfernalExpansion(ItemStack stack, CallbackInfo ci) {
		if (this.potion == IEPotions.INFECTION.get() || this.potion == IEPotions.LONG_INFECTION.get() || this.potion == IEPotions.STRONG_INFECTION.get()) {
			((AbstractArrowEntityAccess) this).setInfection(true);
		} else if (this.potion == IEPotions.LUMINOUS.get() || this.potion == IEPotions.LONG_LUMINOUS.get() || this.potion == IEPotions.STRONG_LUMINOUS.get()) {
			((AbstractArrowEntityAccess) this).setLuminous(true);
		}
		this.refreshColor();
	}

	@Inject(at = @At("RETURN"), method = "addEffect")
	private void addEffectInfernalExpansion(MobEffectInstance effect, CallbackInfo ci) {
		for (MobEffectInstance effectInstance : this.customPotionEffects) {
			if (effectInstance.getEffect() == IEEffects.INFECTION.get()) {
				((AbstractArrowEntityAccess) this).setInfection(true);
			} else if (effectInstance.getEffect() == IEEffects.LUMINOUS.get()) {
				((AbstractArrowEntityAccess) this).setLuminous(true);
			}
		}
		this.refreshColor();
	}

	@Inject(at = @At("HEAD"), method = "spawnPotionParticles")
	private void spawnCustomParticlesInfernalExpansion(int particleCount, CallbackInfo ci) {
		if (((AbstractArrowEntityAccess) this).getLuminous()) {
			for(int j = 0; j < particleCount; ++j) {
				((Arrow) (Object) this).level.addParticle(IEParticleTypes.GLOWSTONE_SPARKLE.get(),
					((Arrow) (Object) this).getRandomX(0.5D), ((Arrow) (Object) this).getRandomY(),
					((Arrow) (Object) this).getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
			}
		}
		if (((AbstractArrowEntityAccess) this).getInfection()) {
			for(int j = 0; j < particleCount; ++j) {
				((Arrow) (Object) this).level.addParticle(IEParticleTypes.INFECTION.get(),
					((Arrow) (Object) this).getRandomX(0.5D), ((Arrow) (Object) this).getRandomY(),
					((Arrow) (Object) this).getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Inject(at = @At("RETURN"), method = "arrowHit")
    private void onArrowHitInfernalExpansion(LivingEntity living, CallbackInfo ci) {
	    if (((AbstractArrowEntityAccess) this).getLuminous()) {
	        living.addEffect(new MobEffectInstance(IEEffects.LUMINOUS.get(), 3600));
        }
    }
}
