package com.nekomaster1000.infernalexp.world.dimension;

import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer0;

public class ModNetherMasterLayer implements AreaTransformer0 {

    private final Registry<Biome> dynamicRegistry;

    public ModNetherMasterLayer(long seed, Registry<Biome> dynamicRegistry){
        this.dynamicRegistry = dynamicRegistry;
    }

    @Override
    public int applyPixel(Context context, int x, int y) {
        return ModNetherBiomeCollector.getRandomNetherBiomes(context);
    }
}
