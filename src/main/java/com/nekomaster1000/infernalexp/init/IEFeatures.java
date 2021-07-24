package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.world.gen.features.GlowLayerFeature;
import com.nekomaster1000.infernalexp.world.gen.features.GlowSpikeFeature;
import com.nekomaster1000.infernalexp.world.gen.features.GlowstoneRavineFeature;
import com.nekomaster1000.infernalexp.world.gen.features.LuminousFungusFeature;
import com.nekomaster1000.infernalexp.world.gen.features.DullthornsFeature;
import com.nekomaster1000.infernalexp.world.gen.features.BoulderFeature;
import com.nekomaster1000.infernalexp.world.gen.features.PlantedQuartzFeature;
import com.nekomaster1000.infernalexp.world.gen.features.SinkHoleFeature;
import com.nekomaster1000.infernalexp.world.gen.features.ShroomlightTearFeature;
import com.nekomaster1000.infernalexp.world.gen.features.HangingGiantBrownMushroomFeature;
import com.nekomaster1000.infernalexp.world.gen.features.config.GlowSpikeFeatureConfig;
import com.nekomaster1000.infernalexp.world.gen.features.config.PlantedQuartzFeatureConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class IEFeatures {

	public static List<Feature<?>> features = new ArrayList<>();

    public static final Feature<NoneFeatureConfiguration> GLOWDUST_LAYER = registerFeature("glowdust_layer", new GlowLayerFeature(NoneFeatureConfiguration.CODEC));
	public static final Feature<GlowSpikeFeatureConfig> GLOWSPIKE = registerFeature("glowspike", new GlowSpikeFeature(GlowSpikeFeatureConfig.CODEC));
	public static final Feature<NoneFeatureConfiguration> GLOWSTONE_RAVINE = registerFeature("glowstone_ravine", new GlowstoneRavineFeature(NoneFeatureConfiguration.CODEC));
	public static final Feature<NoneFeatureConfiguration> HANGING_GIANT_BROWN_MUSHROOM = registerFeature("hanging_giant_brown_mushroom", new HangingGiantBrownMushroomFeature(NoneFeatureConfiguration.CODEC));
	public static final Feature<NoneFeatureConfiguration> LUMINOUS_FUNGUS = registerFeature("luminous_fungus", new LuminousFungusFeature(NoneFeatureConfiguration.CODEC));
	public static final Feature<NoneFeatureConfiguration> DULLTHORNS = registerFeature("dullthorns", new DullthornsFeature(NoneFeatureConfiguration.CODEC));
	public static final Feature<BlockStateConfiguration> BOULDER = registerFeature("blackstone_boulder", new BoulderFeature(BlockStateConfiguration.CODEC));
	public static final Feature<NoneFeatureConfiguration> DULLSTONE_DEATH_PIT = registerFeature("glowdust_sink_hole", new SinkHoleFeature(NoneFeatureConfiguration.CODEC));
	public static final Feature<NoneFeatureConfiguration> SHROOMLIGHT_TEAR = registerFeature("shroomlight_tear", new ShroomlightTearFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<PlantedQuartzFeatureConfig> PATCH_PLANTED_QUARTZ = registerFeature("planted_quartz_patch", new PlantedQuartzFeature(PlantedQuartzFeatureConfig.CODEC));

	public static <C extends FeatureConfiguration, F extends Feature<C>> F registerFeature(String registryName, F feature) {
		ResourceLocation resourceLocation = new ResourceLocation(InfernalExpansion.MOD_ID, registryName);

		if (Registry.FEATURE.keySet().contains(resourceLocation))
			throw new IllegalStateException("Feature ID: \"" + resourceLocation.toString() + "\" is already in the registry!");

		feature.setRegistryName(resourceLocation);
		features.add(feature);

		return feature;
	}
}
