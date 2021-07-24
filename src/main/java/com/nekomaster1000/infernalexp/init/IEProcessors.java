package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.world.gen.processors.LootChestProcessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class IEProcessors {

	public static StructureProcessorType<LootChestProcessor> LOOT_CHEST_PROCESSOR = () -> LootChestProcessor.CODEC;

	public static void registerProcessors() {
		Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(InfernalExpansion.MOD_ID, "loot_chest_processor"), LOOT_CHEST_PROCESSOR);
	}

}
