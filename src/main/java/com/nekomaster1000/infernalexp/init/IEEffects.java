package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.effects.EffectBase;
import com.nekomaster1000.infernalexp.effects.InfectionEffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IEEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, InfernalExpansion.MOD_ID);

    public static final RegistryObject<MobEffect> LUMINOUS = EFFECTS.register("luminous", () -> new EffectBase(MobEffectCategory.NEUTRAL, 0xDCBC82));
    public static final RegistryObject<MobEffect> INFECTION = EFFECTS.register("infection", () -> new InfectionEffect(MobEffectCategory.HARMFUL, 12856114));

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
        InfernalExpansion.LOGGER.info("Infernal Expansion: Effects Registered!");
    }
}
