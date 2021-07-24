package com.nekomaster1000.infernalexp.entities;

import com.nekomaster1000.infernalexp.init.IEItems;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class PyrnoEntity extends Hoglin {

        public static final Ingredient TEMPTATION_ITEMS = Ingredient.of(IEItems.GLOWCOAL.get(), Items.MAGMA_CREAM);

        public PyrnoEntity(EntityType<? extends Hoglin> type, Level worldIn) {
        super(type, worldIn);
        }

        //ATTRIBUTES
        public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
        .add(Attributes.MAX_HEALTH, 50.0D)
        .add(Attributes.MOVEMENT_SPEED, 0.5D)
        .add(Attributes.ATTACK_DAMAGE, 5.0D);
        }

        //BEHAVIOUR
        @Override
        protected void registerGoals() {
                super.registerGoals();
                this.goalSelector.addGoal(0, new TemptGoal(this, 0.5D, TEMPTATION_ITEMS, false));
                this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 0.5D));
                this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0f));
                this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        }

        //EXP POINTS
        @Override
        protected int getExperienceReward(Player player) {
                return 1 + this.level.random.nextInt(4);
                }

        //SOUNDS
        @Override
        protected SoundEvent getAmbientSound() { return SoundEvents.PIG_AMBIENT; }
        @Override
        protected SoundEvent getDeathSound() { return SoundEvents.PIG_DEATH; }
        @Override
        protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
                return SoundEvents.PIG_HURT;
                }
        @Override
        protected void playStepSound(BlockPos pos, BlockState blockIn) {
                this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
                }

                }
