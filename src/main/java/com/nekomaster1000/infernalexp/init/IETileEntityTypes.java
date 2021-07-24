package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.tileentities.GlowCampfireTileEntity;

import com.nekomaster1000.infernalexp.tileentities.LuminousFungusTileEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IETileEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, InfernalExpansion.MOD_ID);

    public static final RegistryObject<BlockEntityType<GlowCampfireTileEntity>> GLOW_CAMPFIRE = TILE_ENTITY_TYPES.register("glow_campfire", () -> BlockEntityType.Builder.of(GlowCampfireTileEntity::new, IEBlocks.GLOW_CAMPFIRE.get()).build(null));
    public static final RegistryObject<BlockEntityType<LuminousFungusTileEntity>> LUMINOUS_FUNGUS_TILE_ENTITY = TILE_ENTITY_TYPES.register("luminous_fungus", () -> BlockEntityType.Builder.of(LuminousFungusTileEntity::new, IEBlocks.LUMINOUS_FUNGUS.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Tile Entity Types Registered!");
    }

}
