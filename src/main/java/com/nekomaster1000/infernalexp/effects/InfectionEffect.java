package com.nekomaster1000.infernalexp.effects;

import com.nekomaster1000.infernalexp.init.IEEffects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.damagesource.DamageSource;

public class InfectionEffect extends MobEffect {

    private int initialDuration;

    public InfectionEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.getHealth() > 1.0F) {
            entityLivingBaseIn.hurt(DamageSource.MAGIC, 1.0F);
        }

        for (LivingEntity entity : entityLivingBaseIn.getCommandSenderWorld().getEntitiesOfClass(LivingEntity.class, entityLivingBaseIn.getBoundingBox().inflate(3))) {
            if (!entity.hasEffect(IEEffects.INFECTION.get()) && entity.isEffectiveAi()) {
                entity.addEffect(new MobEffectInstance(IEEffects.INFECTION.get(), entityLivingBaseIn.getEffect(IEEffects.INFECTION.get()).getDuration() / 2));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        if (this == IEEffects.INFECTION.get()) {
            // This number (50 right now) determines how often the performEffect will be called. The lower the number the more often performEffect will be called
            int j = 50 >> amplifier;
            if (j > 0) {
                return duration % j == 0;
            } else {
                return true;
            }
        }

        return false;
    }
}
