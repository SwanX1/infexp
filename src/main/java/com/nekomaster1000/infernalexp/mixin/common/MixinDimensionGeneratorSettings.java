package com.nekomaster1000.infernalexp.mixin.common;

import com.nekomaster1000.infernalexp.util.WorldSeedHolder;

import net.minecraft.core.MappedRegistry;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(WorldGenSettings.class)
public class MixinDimensionGeneratorSettings {
    /**
     * World seed for worldgen when not specified by JSON by Haven King
     * https://github.com/Hephaestus-Dev/seedy-behavior/blob/master/src/main/java/dev/hephaestus/seedy/mixin/world/gen/GeneratorOptionsMixin.java
     */

    @Inject(method = "<init>(JZZLnet/minecraft/util/registry/SimpleRegistry;Ljava/util/Optional;)V",
        at = @At(value = "RETURN"))
    private void IE_giveUsRandomSeeds(long seed, boolean generateFeatures, boolean bonusChest, MappedRegistry<LevelStem> registry, Optional<String> s, CallbackInfo ci) {
        WorldSeedHolder.setSeed(seed);
    }
}
