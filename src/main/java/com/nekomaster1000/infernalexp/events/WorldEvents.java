package com.nekomaster1000.infernalexp.events;

import com.mojang.serialization.Codec;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.init.IECarvers;
import com.nekomaster1000.infernalexp.init.IEConfiguredFeatures;
import com.nekomaster1000.infernalexp.init.IEConfiguredStructures;
import com.nekomaster1000.infernalexp.init.IEFeatures;
import com.nekomaster1000.infernalexp.init.IEStructures;
import com.nekomaster1000.infernalexp.init.IESurfaceBuilders;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.server.level.ServerLevel;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = InfernalExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldEvents {

	// Register features, surface builders, carvers and structures
	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		IEFeatures.features.forEach(feature -> event.getRegistry().register(feature));
	}

	@SubscribeEvent
	public static void registerStructures(RegistryEvent.Register<StructureFeature<?>> event) {
		IEStructures.structures.forEach(structure -> event.getRegistry().register(structure));
	}

	@SubscribeEvent
	public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
		IESurfaceBuilders.surfaceBuilders.forEach(surfaceBuilder -> event.getRegistry().register(surfaceBuilder));
	}

	@SubscribeEvent
	public static void registerWorldCarvers(RegistryEvent.Register<WorldCarver<?>> event) {
		IECarvers.carvers.forEach(carver -> event.getRegistry().register(carver));
	}

	@SubscribeEvent
	public void addDimensionalSpacing(final WorldEvent.Load event) {
		if (event.getWorld() instanceof ServerLevel) {
			ServerLevel world = (ServerLevel) event.getWorld();

			try {
				Method GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");

				ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(world.getChunkSource().generator));

				if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
			} catch (Exception e) {
				InfernalExpansion.LOGGER.error("Was unable to check if " + world.dimension().location() + " is using Terraforged's ChunkGenerator");
			}

			if (world.getChunkSource().getGenerator() instanceof FlatLevelSource && world.dimension().equals(Level.OVERWORLD)) return;

			Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(world.getChunkSource().generator.getSettings().structureConfig());

			IEStructures.structures.forEach(structure -> tempMap.putIfAbsent(structure, StructureSettings.DEFAULTS.get(structure)));
			world.getChunkSource().generator.getSettings().structureConfig = tempMap;
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBiomeLoad(BiomeLoadingEvent event) {
	    if (event.getName() == null) {
	        return;
        }

	    ResourceLocation name = event.getName();
	    ResourceKey<Biome> biome = ResourceKey.create(Registry.BIOME_REGISTRY, name);

		if (biome == Biomes.CRIMSON_FOREST) {
			event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, IEConfiguredFeatures.ORE_GLOWSILK_COCOON);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, IEConfiguredFeatures.PATCH_CRIMSON_CAP);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, IEConfiguredFeatures.SHROOMLIGHT_TEAR);
        } else if (biome == Biomes.BASALT_DELTAS) {
			event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, IEConfiguredFeatures.ORE_GLOWSILK_COCOON);
			event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, IEConfiguredFeatures.ORE_BASALT_IRON_BASALT_DELTA);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, IEConfiguredFeatures.BASALTIC_MAGMA);
		    event.getGeneration().addStructureStart(IEConfiguredStructures.STRIDER_ALTAR);
		} else if (biome == Biomes.WARPED_FOREST) {
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, IEConfiguredFeatures.PATCH_WARPED_CAP);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, IEConfiguredFeatures.SHROOMLIGHT_TEAR);
		} else if (biome == Biomes.SOUL_SAND_VALLEY) {
			event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, IEConfiguredFeatures.ORE_BASALT_IRON_BASALT_DELTA);
            event.getGeneration().addStructureStart(IEConfiguredStructures.SOUL_SAND_VALLEY_RUIN);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, IEConfiguredFeatures.PATCH_BURIED_BONE);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, IEConfiguredFeatures.ORE_SOUL_STONE);
        } else if (biome == Biomes.NETHER_WASTES) {
		    event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, IEConfiguredFeatures.PATCH_PLANTED_QUARTZ);
        }
	}
}
