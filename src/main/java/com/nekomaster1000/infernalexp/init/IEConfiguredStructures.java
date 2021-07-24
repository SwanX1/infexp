package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public class IEConfiguredStructures {

	public static ConfiguredStructureFeature<?, ?> GLOWSTONE_CANYON_RUIN = registerConfiguredStructure("glowstone_canyon_ruin", IEStructures.GLOWSTONE_CANYON_RUIN.configured(FeatureConfiguration.NONE));
	public static ConfiguredStructureFeature<?, ?> BASTION_OUTPOST = registerConfiguredStructure("bastion_outpost", IEStructures.BASTION_OUTPOST.configured(FeatureConfiguration.NONE));
	public static ConfiguredStructureFeature<?, ?> SOUL_SAND_VALLEY_RUIN = registerConfiguredStructure("soul_sand_valley_ruin", IEStructures.SOUL_SAND_VALLEY_RUIN.configured(FeatureConfiguration.NONE));
    public static ConfiguredStructureFeature<?, ?> STRIDER_ALTAR = registerConfiguredStructure("strider_altar", IEStructures.STRIDER_ALTAR.configured(FeatureConfiguration.NONE));

	public static ConfiguredStructureFeature<?, ?> registerConfiguredStructure(String registryName, ConfiguredStructureFeature<?, ?> structureFeature) {
		ResourceLocation resourceLocation = new ResourceLocation(InfernalExpansion.MOD_ID, registryName);

		if (BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.keySet().contains(resourceLocation))
			throw new IllegalStateException("Configured Feature ID: \"" + resourceLocation.toString() + "\" is already in the registry!");

		FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(structureFeature.feature.getStructure(), structureFeature);

		return Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, resourceLocation, structureFeature);
	}

}
