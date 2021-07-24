package com.nekomaster1000.infernalexp.client.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class LoopingSound<E extends Entity> extends AbstractTickableSoundInstance {
	protected final E entity;

	public LoopingSound(E entity, SoundEvent event, SoundSource category) {
		super(event, category);
		this.entity = entity;
		this.x = entity.getX();
		this.y = entity.getY();
		this.z = entity.getZ();
		this.looping = true;
		this.delay = 0;
		this.volume = 0.0F;
	}

	public void tick() {
		if (this.entity.isAlive()) {
			this.x = (float) this.entity.getX();
			this.y = (float) this.entity.getY();
			this.z = (float) this.entity.getZ();
			float f = Mth.sqrt((float) this.entity.getDeltaMovement().horizontalDistanceSqr());
			if ((double) f >= 0.01D) {
				this.pitch = Mth.lerp(Mth.clamp(f, this.getMinPitch(), this.getMaxPitch()), this.getMinPitch(), this.getMaxPitch());
				this.volume = Mth.lerp(Mth.clamp(f, 0.0F, 0.5F), 0.0F, 1.2F);
			} else {
				this.pitch = 0.0F;
				this.volume = 0.0F;
			}

		} else {
			this.stop();
		}
	}

	private float getMinPitch() {
		return 0.7F;
	}

	private float getMaxPitch() {
		return 1.1F;
	}

	public boolean canStartSilent() {
		return true;
	}

	public boolean canPlaySound() {
		return !this.entity.isSilent();
	}
}
