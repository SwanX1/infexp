package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.world.gen.surfacebuilders.DeltaShoresSurfaceBuilder;
import com.nekomaster1000.infernalexp.world.gen.surfacebuilders.GlowstoneCanyonSurfaceBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

import java.util.ArrayList;
import java.util.List;

public class IESurfaceBuilders {

	public static List<SurfaceBuilder<?>> surfaceBuilders = new ArrayList<>();

	// Surface Builders
	public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> GLOWSTONE_CANYON_SURFACE_BUILDER = newSurfaceBuilder("glowstone_canyon", new GlowstoneCanyonSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC));
	public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> DELTA_SHORES_SURFACE_BUILDER = newSurfaceBuilder("delta_shores", new DeltaShoresSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC));

	// Surface Builder Configs
	public static class ModSurfaceBuilderConfig {
		public static final SurfaceBuilderBaseConfiguration GLOWSTONE_CANYON_CONFIG = new SurfaceBuilderBaseConfiguration(
            IEBlocks.GLOWDUST_SAND.get().defaultBlockState(),
            IEBlocks.GLOWDUST_STONE.get().defaultBlockState(),
            Blocks.GLOWSTONE.defaultBlockState());

		public static final SurfaceBuilderBaseConfiguration DELTA_SHORES_CONFIG = new SurfaceBuilderBaseConfiguration(
            IEBlocks.SILT.get().defaultBlockState(),
            IEBlocks.SILT.get().defaultBlockState(),
            Blocks.BASALT.defaultBlockState()
        );
    }


//    public static void register(IEventBus eventBus) {
//        //SURFACE_BUILDERS.register(eventBus);
//        InfernalExpansion.LOGGER.info("Infernal Expansion: Surface Builders Registered");
//    }

    public static SurfaceBuilder<SurfaceBuilderBaseConfiguration> newSurfaceBuilder(String id, SurfaceBuilder<SurfaceBuilderBaseConfiguration> surfaceBuilder) {
        ResourceLocation registryName = new ResourceLocation(InfernalExpansion.MOD_ID, id);

        if (Registry.SURFACE_BUILDER.keySet().contains(registryName))
			throw new IllegalStateException("Surface Builder ID: \"" + registryName.toString() + "\" is already in the registry!");

        surfaceBuilder.setRegistryName(registryName);
        surfaceBuilders.add(surfaceBuilder);

        return surfaceBuilder;
    }
}

