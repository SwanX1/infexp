package com.nekomaster1000.infernalexp.world.dimension;

import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ModNetherBiomeCollector {
    public static final ForgeRegistry<Biome> biomeRegistry = ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES);

    public static List<ResourceKey<Biome>> netherBiomeList = new ArrayList<>();
    public static List<String> biomeList = Arrays.asList(((String) InfernalExpansionConfig.WorldGeneration.BIOMES_LIST.get()).replace(" ", "").split(","));
    public static boolean isWhitelist = (Boolean) InfernalExpansionConfig.WorldGeneration.BIOMES_LIST_IS_WHITELIST.get();

    public static List<ResourceKey<Biome>> netherBiomeCollection(Registry<Biome> biomeRegistry) {

        for (Map.Entry<ResourceKey<Biome>, Biome> entry : biomeRegistry.entrySet()) {
            if (entry.getValue().getBiomeCategory() == Biome.BiomeCategory.NETHER && !entry.getKey().location().getNamespace().equals("ultra_amplified_dimension")) {
                if (!netherBiomeList.contains(entry.getKey())) {
                    if ((isWhitelist && biomeList.contains(entry.getKey().location().toString()))
                        || (!isWhitelist && !biomeList.contains(entry.getKey().location().toString()))
                        || biomeList.isEmpty()) {

                        netherBiomeList.add(entry.getKey());

                    }
                }
            }
        }

        netherBiomeList.sort(Comparator.comparing(key -> key.location().toString()));
        return netherBiomeList;
    }

    public static int getRandomNetherBiomes(Context random) {
        return biomeRegistry.getID(netherBiomeList.get(random.nextRandom(netherBiomeList.size())).location());
    }
}
