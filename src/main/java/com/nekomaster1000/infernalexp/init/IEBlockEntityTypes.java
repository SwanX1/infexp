package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.blockentities.GlowCampfireBlockEntity;
import com.nekomaster1000.infernalexp.blockentities.LuminousFungusBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IEBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, InfernalExpansion.MOD_ID);

    public static final RegistryObject<BlockEntityType<GlowCampfireBlockEntity>> GLOW_CAMPFIRE = BLOCK_ENTITY_TYPES.register("glow_campfire", () -> BlockEntityType.Builder.of(GlowCampfireBlockEntity::new, IEBlocks.GLOW_CAMPFIRE.get()).build(null));
    public static final RegistryObject<BlockEntityType<LuminousFungusBlockEntity>> LUMINOUS_FUNGUS_TILE_ENTITY = BLOCK_ENTITY_TYPES.register("luminous_fungus", () -> BlockEntityType.Builder.of(LuminousFungusBlockEntity::new, IEBlocks.LUMINOUS_FUNGUS.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Block Entity Types Registered!");
    }

}
