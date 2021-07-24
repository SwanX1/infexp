package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.entities.*;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IEEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, InfernalExpansion.MOD_ID);

    // Entity Types

    public static final RegistryObject<EntityType<VolineEntity>> VOLINE = ENTITY_TYPES.register("voline",
            () -> EntityType.Builder.of(VolineEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 0.8f) // Hitbox Size
                    .build(new ResourceLocation(InfernalExpansion.MOD_ID, "voline").toString()));

    public static final RegistryObject<EntityType<ShroomloinEntity>> SHROOMLOIN = ENTITY_TYPES.register("shroomloin",
            () -> EntityType.Builder.of(ShroomloinEntity::new, IEEntityClassifications.IECREATURE)
                .sized(1.0f, 1.0f) // Hitbox Size
                .build(new ResourceLocation(InfernalExpansion.MOD_ID, "shroomloin").toString()));

    public static final RegistryObject<EntityType<WarpbeetleEntity>> WARPBEETLE = ENTITY_TYPES.register("warpbeetle",
        () -> EntityType.Builder.of(WarpbeetleEntity::new, IEEntityClassifications.IECREATURE)
            .sized(0.5f, 0.5f) // Hitbox Size
            .build(new ResourceLocation(InfernalExpansion.MOD_ID, "warpbeetle").toString()));

    /*
    public static final RegistryObject<EntityType<CerobeetleEntity>> CEROBEETLE = ENTITY_TYPES.register("cerobeetle",
            () -> EntityType.Builder.create(CerobeetleEntity::new, EntityClassification.MONSTER)
            .size(2.0f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(InfernalExpansion.MOD_ID, "cerobeetle").toString()));
     */

    public static final RegistryObject<EntityType<EmbodyEntity>> EMBODY = ENTITY_TYPES.register("embody",
            () -> EntityType.Builder.of(EmbodyEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 0.9f)// Hitbox Size
                    .build(new ResourceLocation(InfernalExpansion.MOD_ID, "embody").toString()));

    public static final RegistryObject<EntityType<BasaltGiantEntity>> BASALT_GIANT = ENTITY_TYPES.register("basalt_giant",
        () -> EntityType.Builder.<BasaltGiantEntity>of(BasaltGiantEntity::new, IEEntityClassifications.IECREATURE)
            .sized(1.2f, 4.3f)// Hitbox Size
            .build(new ResourceLocation(InfernalExpansion.MOD_ID, "basalt_giant").toString()));

    public static final RegistryObject<EntityType<BlackstoneDwarfEntity>> BLACKSTONE_DWARF = ENTITY_TYPES.register("blackstone_dwarf",
            () -> EntityType.Builder.of(BlackstoneDwarfEntity::new, MobCategory.MONSTER)
                    .sized(1.2f, 2.9f)// Hitbox Size
                    .build(new ResourceLocation(InfernalExpansion.MOD_ID, "blackstone_dwarf").toString()));

    public static final RegistryObject<EntityType<GlowsquitoEntity>> GLOWSQUITO = ENTITY_TYPES.register("glowsquito",
        () -> EntityType.Builder.of(GlowsquitoEntity::new, IEEntityClassifications.IECREATURE)
            .sized(0.8f, 0.5f)// Hitbox Size
            .build(new ResourceLocation(InfernalExpansion.MOD_ID, "glowsquito").toString()));

    /*
    public static final RegistryObject<EntityType<PyrnoEntity>> PYRNO = ENTITY_TYPES.register("pyrno",
            () -> EntityType.Builder.create(PyrnoEntity::new, EntityClassification.MONSTER)
                    .size(1.5f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(InfernalExpansion.MOD_ID, "pyrno").toString()));
     */

    public static final RegistryObject<EntityType<BlindsightEntity>> BLINDSIGHT = ENTITY_TYPES.register("blindsight",
            () -> EntityType.Builder.of(BlindsightEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 0.8F) //Hitbox Size
                    .build(new ResourceLocation(InfernalExpansion.MOD_ID, "blindsight").toString()));

    public static final RegistryObject<EntityType<GlowsilkMothEntity>> GLOWSILK_MOTH = ENTITY_TYPES.register("glowsilk_moth",
        () -> EntityType.Builder.of(GlowsilkMothEntity::new, IEEntityClassifications.IECREATURE)
            .sized(1.0F, 1.5F) //Hitbox Size
            .build(new ResourceLocation(InfernalExpansion.MOD_ID, "glowsilk_moth").toString()));

    public static final RegistryObject<EntityType<AscusBombEntity>> ASCUS_BOMB = ENTITY_TYPES.register("ascus_bomb",
            () -> EntityType.Builder.<AscusBombEntity>of(AscusBombEntity::new, MobCategory.MISC)
				.sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(10)
                    .build(new ResourceLocation(InfernalExpansion.MOD_ID, "ascus_bomb").toString()));

    public static final RegistryObject<EntityType<ThrowableMagmaCreamEntity>> THROWABLE_MAGMA_CREAM = ENTITY_TYPES.register("throwable_magma_cream",
            () -> EntityType.Builder.<ThrowableMagmaCreamEntity>of(ThrowableMagmaCreamEntity::new, MobCategory.MISC)
				.sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(10)
                    .build(new ResourceLocation(InfernalExpansion.MOD_ID, "throwable_magma_cream").toString()));

    public static final RegistryObject<EntityType<ThrowableFireChargeEntity>> THROWABLE_FIRE_CHARGE = ENTITY_TYPES.register("throwable_fire_charge",
        () -> EntityType.Builder.<ThrowableFireChargeEntity>of(ThrowableFireChargeEntity::new, MobCategory.MISC)
            .sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(10)
            .build(new ResourceLocation(InfernalExpansion.MOD_ID, "throwable_fire_charge").toString()));

	public static final RegistryObject<EntityType<InfernalPaintingEntity>> INFERNAL_PAINTING = ENTITY_TYPES.register("infernal_painting",
		() -> EntityType.Builder.<InfernalPaintingEntity>of(InfernalPaintingEntity::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE)
			.build(new ResourceLocation(InfernalExpansion.MOD_ID, "infernal_painting").toString()));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String key, EntityType.EntityFactory<T> factoryIn, MobCategory classificationIn, float size1, float size2) {
		return ENTITY_TYPES.register(key, () -> EntityType.Builder.of(factoryIn, classificationIn)
			.sized(size1, size2)
			.build(new ResourceLocation(InfernalExpansion.MOD_ID, key).toString()));
	}

	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
		InfernalExpansion.LOGGER.info("Infernal Expansion: Entity Types Registered!");
    }
}
