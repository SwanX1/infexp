package com.nekomaster1000.infernalexp.mixin.common;

import com.nekomaster1000.infernalexp.access.AbstractArrowEntityAccess;
import com.nekomaster1000.infernalexp.client.DynamicLightingHandler;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public class MixinAbstractArrowEntity implements AbstractArrowEntityAccess {
	@Unique
	private static final EntityDataAccessor<Boolean> GLOW = SynchedEntityData.defineId(AbstractArrow.class, EntityDataSerializers.BOOLEAN);

	@Unique
	private static final EntityDataAccessor<Boolean> LUMINOUS = SynchedEntityData.defineId(AbstractArrow.class, EntityDataSerializers.BOOLEAN);

	@Unique
	private static final EntityDataAccessor<Boolean> INFECTION = SynchedEntityData.defineId(AbstractArrow.class, EntityDataSerializers.BOOLEAN);


	@OnlyIn(Dist.CLIENT)
	@Inject(at = @At("RETURN"), method = "tick")
	private void arrowTickInfernalExpansion(CallbackInfo ci) {
		DynamicLightingHandler.tick(((AbstractArrow) (Object) this));
	}

	@Inject(at = @At("RETURN"), method = "registerData")
	private void registerDataInfernalExpansion(CallbackInfo ci) {
		((AbstractArrow) (Object) this).getEntityData().define(LUMINOUS, false);
		((AbstractArrow) (Object) this).getEntityData().define(INFECTION, false);
		((AbstractArrow) (Object) this).getEntityData().define(GLOW, false);
	}

	@Inject(at = @At("RETURN"), method = "writeAdditional")
	private void writeAdditionalInfernalExpansion(CompoundTag compound, CallbackInfo ci){
		compound.putBoolean("Luminous", ((AbstractArrow) (Object) this).getEntityData().get(LUMINOUS));
		compound.putBoolean("Infection", ((AbstractArrow) (Object) this).getEntityData().get(INFECTION));
		compound.putBoolean("Glow", ((AbstractArrow) (Object) this).getEntityData().get(GLOW));
	}

	@Inject(at = @At("RETURN"), method = "readAdditional")
	private void readAdditionalInfernalExpansion(CompoundTag compound, CallbackInfo ci){
		setLuminous(compound.getBoolean("Luminous"));
		setInfection(compound.getBoolean("Infection"));
		setGlow(compound.getBoolean("Glow"));
	}

	@Override
	public boolean getLuminous() {
		return ((AbstractArrow) (Object) this).getEntityData().get(LUMINOUS);
	}

	@Override
	public void setLuminous(boolean isLuminous) {
		((AbstractArrow) (Object) this).getEntityData().set(LUMINOUS, isLuminous);
	}

	@Override
	public boolean getInfection() {
		return ((AbstractArrow) (Object) this).getEntityData().get(INFECTION);
	}

	@Override
	public void setInfection(boolean isInfection) {
		((AbstractArrow) (Object) this).getEntityData().set(INFECTION, isInfection);
	}

	@Override
	public boolean getGlow() {
		return ((AbstractArrow) (Object) this).getEntityData().get(GLOW);
	}

	@Override
	public void setGlow(boolean shouldGlow) {
		((AbstractArrow) (Object) this).getEntityData().set(GLOW, shouldGlow);
	}
}
