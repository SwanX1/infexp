package com.nekomaster1000.infernalexp.entities;

import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.init.IEEffects;
import com.nekomaster1000.infernalexp.init.IEItems;
import com.nekomaster1000.infernalexp.init.IESoundEvents;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumSet;

public class ShroomloinEntity extends PathfinderMob implements RangedAttackMob {
    private static final EntityDataAccessor<Integer> FUNGUS_TYPE = SynchedEntityData.defineId(ShroomloinEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(ShroomloinEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> IGNITED = SynchedEntityData.defineId(ShroomloinEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.defineId(ShroomloinEntity.class, EntityDataSerializers.BOOLEAN);
	private int lastActiveTime;
	private int timeSinceIgnited;
	private final int fuseTime = 59;
    private int conversionTicks;
    private int predictedFungus;
    // public static final Ingredient TEMPTATION_ITEMS =
	// Ingredient.fromItems(IEItems.DULLROCKS.get(), Items.MAGMA_CREAM);

	public ShroomloinEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
		super(type, worldIn);
	}

	// ATTRIBUTES
	public static AttributeSupplier.Builder setCustomAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.ATTACK_DAMAGE, 1.5D).add(Attributes.ATTACK_KNOCKBACK, 1.5D).add(Attributes.MOVEMENT_SPEED, 0.6D);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(STATE, -1);
		this.entityData.define(IGNITED, false);
		this.entityData.define(CONVERTING, false);
		this.entityData.define(FUNGUS_TYPE, 0);
	}

    public boolean isConverting() {
	    return this.entityData.get(CONVERTING);
    }

    public void setConverting(boolean converting) {
	    this.entityData.set(CONVERTING, converting);
    }

    public boolean isShaking() {
	    return this.isConverting();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("fungusType", this.getFungusType());
        compound.putInt("ShroomloinConversionTime", this.isConverting() ? this.conversionTicks : -1);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFungusType(compound.getInt("fungusType"));
    }

    public void setFungusType(int fungusType) {
	    this.entityData.set(FUNGUS_TYPE, fungusType);
    }

    public int getFungusType() {
	    return this.entityData.get(FUNGUS_TYPE);
    }

    @Override
    protected InteractionResult mobInteract(Player playerIn, InteractionHand hand) {
        ItemStack stack = playerIn.getItemInHand(hand);
        if (this.isConverting()) {
            return InteractionResult.FAIL;
        }
        if (this.getFungusType() != 0 && stack.getItem() == Items.CRIMSON_FUNGUS) {
            this.predictedFungus = 0;
            this.conversionTicks = 40;
            this.setConverting(true);
            if (!playerIn.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        } else if (this.getFungusType() != 1 && stack.getItem() == Items.WARPED_FUNGUS) {
            this.predictedFungus = 1;
            this.conversionTicks = 40;
            this.setConverting(true);
            if (!playerIn.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        } else if (this.getFungusType() != 2 && stack.getItem() == IEBlocks.LUMINOUS_FUNGUS.get().asItem()) {
            this.predictedFungus = 2;
            this.conversionTicks = 40;
            this.setConverting(true);
            if (!playerIn.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        } else if (this.getFungusType() != 3 && stack.getItem() == Items.RED_MUSHROOM) {
            this.predictedFungus = 3;
            this.conversionTicks = 40;
            this.setConverting(true);
            if (!playerIn.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        } else if (this.getFungusType() != 4 && stack.getItem() == Items.BROWN_MUSHROOM) {
            this.predictedFungus = 4;
            this.conversionTicks = 40;
            this.setConverting(true);
            if (!playerIn.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(playerIn, hand);
    }

    /**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		if (this.isAlive()) {
		    if (this.isConverting() && this.conversionTicks > 0) {
		        this.conversionTicks--;
		        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2D);
		        if (this.conversionTicks == 0) {
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		            this.setFungusType(this.predictedFungus);
		            this.setConverting(false);
                }
            }
			if (this.hasEffect(IEEffects.INFECTION.get()) || this.hasEffect(MobEffects.POISON)) {
				this.removeEffectNoUpdate(IEEffects.INFECTION.get());
				this.removeEffectNoUpdate(MobEffects.POISON);
			}
		}

		this.lastActiveTime = this.timeSinceIgnited;

		int i = this.getShroomloinState();
		if (i > 0 && this.timeSinceIgnited == 0) {
			this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
		}

		this.timeSinceIgnited += i;
		if (this.timeSinceIgnited < 0 || this.timeSinceIgnited >= this.fuseTime || i < 0) {
			this.timeSinceIgnited = 0;
		}

		super.tick();
	}

	// BEHAVIOUR
	@Override
	protected void registerGoals() {
		super.registerGoals();
		// this.goalSelector.addGoal(0, new TemptGoal(this, 0.6D, TEMPTATION_ITEMS,
		// false));
		// this.goalSelector.addGoal(0, new ShroomloinSwellGoal(this));
		this.goalSelector.addGoal(1, new RangedAttackUnInfectedGoal(this, 1, 60, 10));
		this.goalSelector.addGoal(1, new MeleeAttackInfectedGoal(this, 0.6d, true));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.5d));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0f));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

//        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
//?        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	//

	/**
	 * Returns whether execution should begin. You can also read and cache any state
	 * necessary for execution in this method as well.
	 */

	/*
	 * public boolean shouldExecute() { int i = this.goalOwner.getRevengeTimer();
	 * LivingEntity livingentity = this.goalOwner.getRevengeTarget(); if (i !=
	 * this.revengeTimerOld && livingentity != null) { if (livingentity.getType() ==
	 * EntityType.PLAYER &&
	 * this.goalOwner.world.getGameRules().getBoolean(GameRules.UNIVERSAL_ANGER)) {
	 * return false; } else { for(Class<?> oclass : this.excludedReinforcementTypes)
	 * { if (oclass.isAssignableFrom(livingentity.getClass())) { return false; } }
	 * 
	 * return this.isSuitableTarget(livingentity, HURT_BY_TARGETING); } } else { return
	 * false; } }
	 * 
	 * public HurtByTargetGoal setCallsForHelp(Class<?>... reinforcementTypes) {
	 * this.entityCallsForHelp = true; this.reinforcementTypes = reinforcementTypes;
	 * return this; }
	 * 
	 */

	@OnlyIn(Dist.CLIENT)
	public float getShroomloinFlashIntensity(float partialTicks) {
		return Mth.lerp(partialTicks, (float) this.lastActiveTime, (float) this.timeSinceIgnited) / (float) (this.fuseTime - 2);
	}

	/**
	 * Returns the current state of shroomloin, -1 is idle, 1 is 'in fuse'
	 */
	public int getShroomloinState() {
		return this.entityData.get(STATE);
	}

	/**
	 * Sets the state of shroomloin, -1 to idle and 1 to be 'in fuse'
	 */
	public void setShroomloinState(int state) {
		this.entityData.set(STATE, state);
	}

	public void becomeAngryAt(LivingEntity entity) {
		this.setTarget(entity);
	}

//    public boolean isImmuneToFire() {
//    return true;
//    }

	// EXP POINTS
	@Override
	protected int getExperienceReward(Player player) {
		return 1 + this.level.random.nextInt(4);
	}

	// SOUNDS
	@Override
	protected SoundEvent getAmbientSound() {
		return IESoundEvents.SHROOMLOIN_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return IESoundEvents.SHROOMLOIN_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return IESoundEvents.SHROOMLOIN_HURT.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		Vec3 vector3d = target.getDeltaMovement();
		double x = target.getX() + vector3d.x - this.getX();
		double y = target.getEyeY() - 1.1 - this.getY();
		double z = target.getZ() + vector3d.z - this.getZ();

		float distance = Mth.sqrt(x * x + z * z);

		AscusBombEntity ascusBombEntity = new AscusBombEntity(this.level, this);

		ascusBombEntity.setItem(new ItemStack(IEItems.ASCUS_BOMB.get()));
		ascusBombEntity.xRot -= -20;
		ascusBombEntity.shoot(x, y + (distance * 0.2), z, 0.75f, 8);

		this.setShroomloinState(-1);
		this.level.addFreshEntity(ascusBombEntity);
	}

    public boolean canConvert() {
	    return this.conversionTicks == 0;
    }

    static class MeleeAttackInfectedGoal extends MeleeAttackGoal {

		public MeleeAttackInfectedGoal(PathfinderMob creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}

		@Override
		public boolean canUse() {
			LivingEntity attackTarget = this.mob.getTarget();
			if (attackTarget != null && !attackTarget.hasEffect(IEEffects.INFECTION.get())) {
				return false;
			}
			return super.canUse();
		}

		@Override
		public boolean canContinueToUse() {
			LivingEntity attackTarget = this.mob.getTarget();
			if (attackTarget != null && !attackTarget.hasEffect(IEEffects.INFECTION.get())) {
				return false;
			}
			return super.canContinueToUse();
		}
	}

	static class RangedAttackUnInfectedGoal extends Goal {
		private final ShroomloinEntity entityHost;
		private final RangedAttackMob rangedAttackEntityHost;
		private LivingEntity attackTarget;
		private int rangedAttackTime = -1;
		private final double entityMoveSpeed;
		private int seeTime;
		private final int attackIntervalMin;
		private final int maxRangedAttackTime;
		private final float attackRadius;
		private final float maxAttackDistance;

		public RangedAttackUnInfectedGoal(RangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
			this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
		}

		public RangedAttackUnInfectedGoal(RangedAttackMob attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn) {
			if (!(attacker instanceof LivingEntity)) {
				throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
			} else {
				this.rangedAttackEntityHost = attacker;
				this.entityHost = (ShroomloinEntity) attacker;
				this.entityMoveSpeed = movespeed;
				this.attackIntervalMin = p_i1650_4_;
				this.maxRangedAttackTime = maxAttackTime;
				this.attackRadius = maxAttackDistanceIn;
				this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
				this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
			}
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state
		 * necessary for execution in this method as well.
		 */
		public boolean canUse() {
			LivingEntity attackTarget = this.entityHost.getTarget();
			if (attackTarget != null && attackTarget.isAlive() && !attackTarget.hasEffect(IEEffects.INFECTION.get())) {
				this.attackTarget = attackTarget;
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean canContinueToUse() {
			return this.canUse() || !this.entityHost.getNavigation().isDone();
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by
		 * another one
		 */
		public void stop() {
			this.attackTarget = null;
			this.seeTime = 0;
			this.rangedAttackTime = -1;
			this.entityHost.setShroomloinState(-1);
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			double d0 = this.entityHost.distanceToSqr(this.attackTarget.getX(), this.attackTarget.getY(), this.attackTarget.getZ());
			boolean flag = this.entityHost.getSensing().canSee(this.attackTarget);

			if (flag) {
				++this.seeTime;
			} else {
				this.seeTime = 0;
			}

			if (!(d0 > (double) this.maxAttackDistance) && this.seeTime >= 5) {
				this.entityHost.getNavigation().stop();
			} else {
				this.entityHost.setShroomloinState(-1);
				this.entityHost.getNavigation().moveTo(this.attackTarget, this.entityMoveSpeed);
			}

			this.entityHost.getLookControl().setLookAt(this.attackTarget, 30.0F, 30.0F);
			if (--this.rangedAttackTime == 0) {
				if (!flag) {
					return;
				}

				float f = Mth.sqrt(d0) / this.attackRadius;
				float lvt_5_1_ = Mth.clamp(f, 0.1F, 1.0F);
				this.rangedAttackEntityHost.performRangedAttack(this.attackTarget, lvt_5_1_);
				this.rangedAttackTime = Mth.floor(f * (float) (this.maxRangedAttackTime - this.attackIntervalMin) + (float) this.attackIntervalMin);
			} else if (this.rangedAttackTime < 0) {
				float f2 = Mth.sqrt(d0) / this.attackRadius;
				this.rangedAttackTime = Mth.floor(f2 * (float) (this.maxRangedAttackTime - this.attackIntervalMin) + (float) this.attackIntervalMin);
			} else {
				this.entityHost.setShroomloinState(1);
			}
		}
	}
}
