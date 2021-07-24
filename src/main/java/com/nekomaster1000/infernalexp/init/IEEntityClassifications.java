package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import net.minecraft.world.entity.MobCategory;

public class IEEntityClassifications {
    public static MobCategory IECREATURE;

    public static void register() {
        IECREATURE = MobCategory.create("IE_CREATURE", "IECREATURE", 70, true, false, 128);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Entity Classifications Registered!");
    }
}
