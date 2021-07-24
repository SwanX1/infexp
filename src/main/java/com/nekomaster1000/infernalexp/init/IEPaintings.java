package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IEPaintings {
	public static DeferredRegister<Motive> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, InfernalExpansion.MOD_ID);

	public static RegistryObject<Motive> THE_FALLEN_ONES = PAINTING_TYPES.register("the_fallen_ones", () -> new Motive(48, 64));
	public static RegistryObject<Motive> CHILLING_ISLES = PAINTING_TYPES.register("chilling_isles", () -> new Motive(48, 48));
	public static RegistryObject<Motive> VOLINE = PAINTING_TYPES.register("voline", () -> new Motive(16, 16));
	public static RegistryObject<Motive> DA_SALT = PAINTING_TYPES.register("da_salt", () -> new Motive(64, 32));
    public static RegistryObject<Motive> GLOWSTONE_CANYON_PAINTING = PAINTING_TYPES.register("glowstone_canyon_painting", () -> new Motive(64, 64));
    public static RegistryObject<Motive> SOUL_BY_SOUL = PAINTING_TYPES.register("soul_by_soul", () -> new Motive(80, 48));
    public static RegistryObject<Motive> GARRY = PAINTING_TYPES.register("pineapple_under_the_lava_sea", () -> new Motive(64, 64));
    public static RegistryObject<Motive> SHROOM_DUALITY = PAINTING_TYPES.register("shroom_duality", () -> new Motive(16, 16));
    public static RegistryObject<Motive> PIGS_GREED = PAINTING_TYPES.register("pigs_greed", () -> new Motive(16, 16));
    public static RegistryObject<Motive> SOUL_HOLE = PAINTING_TYPES.register("soul_hole", () -> new Motive(16, 16));
    public static RegistryObject<Motive> GLOW_LIKE_HELLY = PAINTING_TYPES.register("glow_like_helly", () -> new Motive(16, 16));


	public static void register(IEventBus eventBus) {
		PAINTING_TYPES.register(eventBus);
		InfernalExpansion.LOGGER.info("Infernal Expansion: Painting Types Registered!");
	}
}
