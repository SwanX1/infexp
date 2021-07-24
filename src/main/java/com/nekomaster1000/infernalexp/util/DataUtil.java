package com.nekomaster1000.infernalexp.util;

import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.ItemLike;

import net.minecraftforge.fml.ModList;

public class DataUtil {

    public static void registerCompostable(float chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
    }

    public static boolean isLoaded(String modID) {
        return ModList.get().isLoaded(modID);
    }
}
