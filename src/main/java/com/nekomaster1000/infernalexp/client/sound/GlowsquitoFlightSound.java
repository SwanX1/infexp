package com.nekomaster1000.infernalexp.client.sound;

import com.nekomaster1000.infernalexp.entities.GlowsquitoEntity;
import com.nekomaster1000.infernalexp.init.IESoundEvents;

import net.minecraft.sounds.SoundSource;

public class GlowsquitoFlightSound extends LoopingSound<GlowsquitoEntity> {

	public GlowsquitoFlightSound(GlowsquitoEntity entity) {
		super(entity, IESoundEvents.GLOWSQUITO_LOOP.get(), SoundSource.NEUTRAL);
	}

}
