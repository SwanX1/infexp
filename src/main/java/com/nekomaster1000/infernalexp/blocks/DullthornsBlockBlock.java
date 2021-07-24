package com.nekomaster1000.infernalexp.blocks;

import com.nekomaster1000.infernalexp.entities.BlindsightEntity;
import com.nekomaster1000.infernalexp.init.IEEffects;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DullthornsBlockBlock extends Block {

	public DullthornsBlockBlock(Properties properties) {
		super(properties);

	}

	@Override
	public void stepOn(Level worldIn, BlockPos pos, Entity entityIn) {
		if (!worldIn.isClientSide()) {
			if (entityIn instanceof LivingEntity && entityIn.isAlive() && !(entityIn instanceof BlindsightEntity)) {
				LivingEntity livingEntity = (LivingEntity) entityIn;
				livingEntity.addEffect(new MobEffectInstance(IEEffects.LUMINOUS.get(), 300, 0));
			}
			entityIn.hurt(DamageSource.CACTUS, 1.0F);
		}
	}

}
