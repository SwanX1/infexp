package com.nekomaster1000.infernalexp.mixin.common;

import com.nekomaster1000.infernalexp.init.IEItems;
import com.nekomaster1000.infernalexp.util.IBucketable;

import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Strider.class)
public abstract class MixinStriderEntity extends Animal implements ItemSteerable, Saddleable, IBucketable {
    @Shadow
    protected abstract void defineSynchedData();

    @Shadow
    public abstract void readAdditionalSaveData(CompoundTag compound);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Strider.class, EntityDataSerializers.BOOLEAN);

    protected MixinStriderEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }

    @Inject(method = "registerData", at = @At("HEAD"))
    private void IE_registerData(CallbackInfo info) {
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    public boolean isFromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean isFromBucket) {
        this.entityData.set(FROM_BUCKET, isFromBucket);
    }

    @Inject(method = "writeAdditional", at = @At("HEAD"))
    private void IE_writeAdditional(CompoundTag compound, CallbackInfo ci) {
        compound.putBoolean("FromBucket", this.isFromBucket());
    }

    @Inject(method = "readAdditional", at = @At("HEAD"))
    private void IE_readAdditional(CompoundTag compound, CallbackInfo ci) {
        this.setFromBucket(compound.getBoolean("FromBucket"));
    }

    @Inject(method = "getEntityInteractionResult", at = @At("RETURN"), cancellable = true)
    public void IE_getEntityInteractionResult(Player playerIn, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        InteractionResult resultType = cir.getReturnValue();
        if (this.isBaby()) {
            cir.setReturnValue(IBucketable.tryBucketEntity(playerIn, hand, this).orElse(super.mobInteract(playerIn, hand)));
        } else {
            cir.setReturnValue(resultType);
        }
    }

    @Inject(method = "onInitialSpawn", at = @At("HEAD"), cancellable = true)
    private void IE_onInitialSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (reason == MobSpawnType.BUCKET) {
            spawnDataIn = new AgableMob.AgableMobGroupData(true);
            this.setAge(-24000);
            cir.setReturnValue(spawnDataIn);
        }
    }

    @Override
    public void copyToStack(ItemStack stack) {
        CompoundTag compound = stack.getOrCreateTag();
        IBucketable.copyToStack(this, stack);

        compound.putInt("Age", this.getAge());
    }

    @Override
    public void copyFromAdditional(CompoundTag compound) {
        IBucketable.copyFromAdditional(this, compound);
        if (compound.contains("Age", 99)) {
            this.setAge(compound.getInt("Age"));
        }
    }

    @Override
    public SoundEvent getBucketedSound() {
        return SoundEvents.BUCKET_FILL_LAVA;
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(IEItems.STRIDER_BUCKET.get());
    }
}
