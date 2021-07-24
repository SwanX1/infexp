package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.particle.GlowstoneSparkleParticle;
import com.nekomaster1000.infernalexp.client.particle.InfectionParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = InfernalExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IEParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, InfernalExpansion.MOD_ID);

    public static final RegistryObject<SimpleParticleType> GLOWSTONE_SPARKLE = PARTICLES.register("glowstone_sparkle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> INFECTION = PARTICLES.register("infection", () -> new SimpleParticleType(false));

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleEngine particleManager = Minecraft.getInstance().particleEngine;

        particleManager.register(IEParticleTypes.GLOWSTONE_SPARKLE.get(), GlowstoneSparkleParticle.Factory::new);
        particleManager.register(IEParticleTypes.INFECTION.get(), InfectionParticle.Factory::new);

        InfernalExpansion.LOGGER.info("Infernal Expansion: Particles Registered!");
    }
}
