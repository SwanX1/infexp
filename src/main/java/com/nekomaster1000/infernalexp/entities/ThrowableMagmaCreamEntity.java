package com.nekomaster1000.infernalexp.entities;

import com.nekomaster1000.infernalexp.init.IEEntityTypes;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;

import net.minecraftforge.fml.network.NetworkHooks;

public class ThrowableMagmaCreamEntity extends ThrowableItemProjectile {

    public ThrowableMagmaCreamEntity(EntityType<? extends ThrowableMagmaCreamEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public ThrowableMagmaCreamEntity(Level world, LivingEntity livingEntity) {
        super(IEEntityTypes.THROWABLE_MAGMA_CREAM.get(), livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.MAGMA_CREAM;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    /**
     * Called when the magma cream hits an entity
     */
    @Override
    protected void onHitEntity(EntityHitResult entityRayTraceResult) {
        super.onHitEntity(entityRayTraceResult);
        Entity entity = entityRayTraceResult.getEntity();
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60));
        }
    }

    /**
     * Called when this ThrowableMagmaCreamEntity hits a block or entity.
     */
    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);

        if (result.getType() == HitResult.Type.BLOCK) {
            ItemEntity item = new ItemEntity(level, this.getX(), this.getY(), this.getZ(), Items.MAGMA_CREAM.getDefaultInstance());
            level.addFreshEntity(item);
        }
        this.remove();
    }
}
