package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.world.gen.carvers.GlowstoneRavineCarver;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class IECarvers {

	public static List<WorldCarver<CarverConfiguration>> carvers = new ArrayList<>();

	// Carvers
    // TODO: fix
	// public static final WorldCarver<CarverConfiguration> GLOWSTONE_RAVINE = registerWorldCarver("glowstone_ravine", new GlowstoneRavineCarver(ProbabilityFeatureConfiguration.CODEC));

	// Configured Carvers
    // TODO: fix
	// public static final ConfiguredWorldCarver<CarverConfiguration> CONFIGURED_GLOWSTONE_RAVINE = registerConfiguredCarver("glowstone_ravine", GLOWSTONE_RAVINE.configured(new ProbabilityFeatureConfiguration(0.1f)));

    private static WorldCarver<CarverConfiguration> registerWorldCarver(String registryName, WorldCarver<CarverConfiguration> carver) {
        ResourceLocation resourceLocation = new ResourceLocation(InfernalExpansion.MOD_ID, registryName);

		if (Registry.CARVER.keySet().contains(resourceLocation))
			throw new IllegalStateException("World Carver ID: \"" + resourceLocation.toString() + "\" is already in the registry!");

        carver.setRegistryName(resourceLocation);
        carvers.add(carver);

        return carver;
    }

    private static <WC extends CarverConfiguration> ConfiguredWorldCarver<WC> registerConfiguredCarver(String registryName, ConfiguredWorldCarver<WC> configuredCarver) {
        ResourceLocation resourceLocation = new ResourceLocation(InfernalExpansion.MOD_ID, registryName);

		if (BuiltinRegistries.CONFIGURED_FEATURE.keySet().contains(resourceLocation))
			throw new IllegalStateException("Configured Carver ID: \"" + resourceLocation.toString() + "\" is already in the registry!");

        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_CARVER, resourceLocation, configuredCarver);
    }

}
