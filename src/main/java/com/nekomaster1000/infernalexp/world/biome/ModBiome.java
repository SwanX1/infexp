package com.nekomaster1000.infernalexp.world.biome;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;

public abstract class ModBiome {

    /**
     * Method to configure category
     * @return Category for biome to use
     */
    protected abstract Biome.BiomeCategory configureCategory();

    /**
     * Method to configure depth value
     * @return Depth value for biome to use
     */
    protected abstract float configureDepth();

    /**
     * Method to configure scale value
     * @return Scale value for biome to use
     */
    protected abstract float configureScale();

    /**
     * Method to configure ambience settings
     */
    protected abstract void configureAmbience(BiomeSpecialEffects.Builder ambience);

    /**
     * Method to configure climate settings
     */
    protected abstract Biome.ClimateSettings configureClimate();

    /**
     * Method to configure surface builder
     */
    protected abstract ConfiguredSurfaceBuilder<?> configureSurfaceBuilder();

    /**
     * Method  to configure generation settings
     */
    protected abstract void configureGeneration(BiomeGenerationSettings.Builder generation);

    /**
     * Method to configure mob spawn settings
     */
    protected abstract void configureSpawns(MobSpawnSettings.Builder spawns);

    /**
     * Builds the biome
     * @return Returns the finished, built biome
     */
    public final Biome build() {

        Biome.BiomeBuilder builder = new Biome.BiomeBuilder();

        builder.biomeCategory(this.configureCategory());
        builder.depth(this.configureDepth());
        builder.scale(this.configureScale());

        // Configure biome ambience
        BiomeSpecialEffects.Builder ambience = new BiomeSpecialEffects.Builder();
        this.configureAmbience(ambience);
        builder.specialEffects(ambience.build());

        // Configure biome climate
        Biome.ClimateSettings climate = configureClimate();
        builder.precipitation(climate.precipitation);
        builder.temperature(climate.temperature);
        builder.temperatureAdjustment(climate.temperatureModifier);
        builder.downfall(climate.downfall);

        // Configure biome generation settings
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
        this.configureGeneration(generation);
        generation.surfaceBuilder(this.configureSurfaceBuilder());
        builder.generationSettings(generation.build());

        // Configure biome mob spawn settings
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        this.configureSpawns(spawns);
		builder.mobSpawnSettings(spawns.build());

        return builder.build();
    }
}
