package com.nekomaster1000.infernalexp.entities;

import java.util.UUID;

import com.nekomaster1000.infernalexp.init.IESoundEvents;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.IntRange;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CerobeetleEntity extends PathfinderMob {

	public float shellRotationMultiplier = 0.0F;

	public CerobeetleEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
		super(type, worldIn);
	}

	private static final IntRange RANGED_INT = TimeUtil.rangeOfSeconds(20, 39);
	private int attackTimer;
	private int angerTime;
	private UUID angerTarget;

	// ATTRIBUTES
	public static AttributeSupplier.Builder setCustomAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 60.0D).add(Attributes.MOVEMENT_SPEED, 0.4D).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.ATTACK_KNOCKBACK, 4.0D);
	}

	// BEHAVIOUR
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 0.6D, true));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MoveTowardsTargetGoal(this, 0.6D, 32.0F));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0f));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, WarpbeetleEntity.class, 8.0f));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.5d));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Spider.class, true, false));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, CaveSpider.class, true, false));
	}

	// ---
	// Retaliating

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
			this.playSound(IESoundEvents.CEROBEETLE_ROAR.get(), 1.0F, 1.0F);
		} else {
			super.handleEntityEvent(id);
		}

	}

	public void aiStep() {
		super.aiStep();
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
	}

	public boolean doHurtTarget(Entity entityIn) {
		this.attackTimer = 10;
		this.level.broadcastEntityEvent(this, (byte) 4);
		float f = this.getAttackDamage();
		float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
		float f2 = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
		boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
		if (flag) {
			((LivingEntity) entityIn).knockback(f2 * 0.5F, Mth.sin(this.yRot * ((float) Math.PI / 180F)), -Mth.cos(this.yRot * ((float) Math.PI / 180F)));
			entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(1.0D, 2.5D, 1.0D));
			this.setDeltaMovement(this.getDeltaMovement().multiply(0.6D, 1.0D, 1.6D));
			// Don't touch above line - Multiplies Vertical knock-back by 1X that of the
			// Horizontal knock-back
			this.doEnchantDamageEffects(this, entityIn);
		}

		this.playSound(IESoundEvents.CEROBEETLE_ROAR.get(), 1.0F, 1.0F);
		return flag;
	}

	private float getAttackDamage() {
		return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	}
	// ---

	// EXP POINTS
	@Override
	protected int getExperienceReward(Player player) {
		return 1 + this.level.random.nextInt(4);
	}

	// SOUNDS
	@Override
	protected SoundEvent getAmbientSound() {
		return IESoundEvents.CEROBEETLE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return IESoundEvents.CEROBEETLE_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return IESoundEvents.CEROBEETLE_HURT.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
	}

}
