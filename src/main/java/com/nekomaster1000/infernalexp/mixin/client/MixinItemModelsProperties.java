package com.nekomaster1000.infernalexp.mixin.client;

import com.nekomaster1000.infernalexp.init.IEBiomes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

@Mixin(targets = "net.minecraft.item.ItemModelsProperties$1")
public class MixinItemModelsProperties {

	@ModifyVariable(method = "call", at = @At(value = "STORE", ordinal = 1 /* this ordinal is when its set to Math.random(), the second time d0 is set to something */), ordinal = 0 /* this ordinal means the first double variable */)
	private double IE_daytimeInGSC(double in, ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity entity) {
	    ClientLevel clientWorld = world;
        if (entity == null) {
            return in;
        }
        if (world == null && entity.level instanceof ClientLevel) {
            clientWorld = (ClientLevel)entity.getCommandSenderWorld();
        }

        Optional<ResourceKey<Biome>> biomeKey = clientWorld.getBiomeName(entity.blockPosition());
        ResourceKey<Biome> gscKey = ResourceKey.create(Registry.BIOME_REGISTRY, IEBiomes.GLOWSTONE_CANYON.getId());

        if (biomeKey.isPresent() && biomeKey.get().equals(gscKey)) {
            return Mth.nextDouble(new Random(), 0.95, 1.05) % 1;
        }
        return in;
	}
}
