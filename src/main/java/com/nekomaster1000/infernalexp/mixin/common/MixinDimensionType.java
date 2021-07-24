package com.nekomaster1000.infernalexp.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.nekomaster1000.infernalexp.world.dimension.ModNetherBiomeProvider;

import net.minecraft.core.Registry;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

@Mixin(DimensionType.class)
public class MixinDimensionType {

    @Inject(at = @At("HEAD"), method = "getNetherChunkGenerator(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/Registry;J)Lnet/minecraft/world/gen/ChunkGenerator;", cancellable = true)
    private static void IE_netherDimensionInfernalExpansion(Registry<Biome> registry, Registry<NoiseGeneratorSettings> dimSettings, long seed, CallbackInfoReturnable<ChunkGenerator> cir) {
        cir.setReturnValue(new NoiseBasedChunkGenerator(new ModNetherBiomeProvider(seed, registry, 6), seed, () -> dimSettings.getOrThrow(NoiseGeneratorSettings.NETHER)));
    }
}
