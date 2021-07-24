package com.nekomaster1000.infernalexp.entities;

import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig;
import com.nekomaster1000.infernalexp.init.IEEntityTypes;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkHooks;

public class ThrowableFireChargeEntity extends Fireball {

    public ThrowableFireChargeEntity(EntityType<? extends ThrowableFireChargeEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public ThrowableFireChargeEntity(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(IEEntityTypes.THROWABLE_FIRE_CHARGE.get(), shooter.getX(), shooter.getEyeY(), shooter.getZ(), accelX, accelY, accelZ, worldIn);
        this.setOwner(shooter);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHit(HitResult result) {
        HitResult.Type raytraceresult$type = result.getType();
        if (raytraceresult$type == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult)result);
        } else if (raytraceresult$type == HitResult.Type.BLOCK) {
            this.onHitBlock((BlockHitResult)result);
        }

        if (!this.level.isClientSide) {
            boolean flag = InfernalExpansionConfig.Miscellaneous.FIRE_CHARGE_EXPLOSION.getBool() && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
            this.level.explode(null, this.getX(), this.getY(), this.getZ(), 1.0F, flag, flag ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE);
            this.remove();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level.isClientSide) {
            Entity entity = result.getEntity();
            Entity entity1 = this.getOwner();
            entity.hurt(DamageSource.fireball(this, entity1), 6.0F);
            if (entity1 instanceof LivingEntity) {
                this.doEnchantDamageEffects((LivingEntity)entity1, entity);
            }

        }
    }
}
