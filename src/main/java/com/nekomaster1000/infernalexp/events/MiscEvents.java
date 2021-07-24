package com.nekomaster1000.infernalexp.events;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.blocks.HorizontalBushBlock;
import com.nekomaster1000.infernalexp.config.ConfigHelper;
import com.nekomaster1000.infernalexp.config.ConfigHolder;
import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig.Miscellaneous;
import com.nekomaster1000.infernalexp.data.SpawnrateManager;
import com.nekomaster1000.infernalexp.data.VolineEatTable;
import com.nekomaster1000.infernalexp.entities.ShroomloinEntity;
import com.nekomaster1000.infernalexp.entities.ThrowableFireChargeEntity;
import com.nekomaster1000.infernalexp.entities.ThrowableMagmaCreamEntity;
import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.init.IEEffects;
import com.nekomaster1000.infernalexp.init.IEItems;
import com.nekomaster1000.infernalexp.init.IEParticleTypes;
import com.nekomaster1000.infernalexp.init.IESoundEvents;
import com.nekomaster1000.infernalexp.init.IETags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionColorCalculationEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = InfernalExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MiscEvents {

    //    Called When Config is Changed
    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
        final ModConfig config = event.getConfig();
        //Recalculates what the configs should be when changed
        if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
            ConfigHelper.bakeClient(config);
        } else if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
            ConfigHelper.bakeCommon(config);
        }
    }

    //Blocks being broken
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        List<?> list = event.getPlayer().level.getEntitiesOfClass(ShroomloinEntity.class,
            event.getPlayer().getBoundingBox().inflate(32.0D));
        for (int j = 0; j < list.size(); j++) {
            Entity entity = (Entity) list.get(j);
            if (entity instanceof ShroomloinEntity) {
                ShroomloinEntity shroomloinEntity = (ShroomloinEntity) entity;

                if (((ShroomloinEntity) entity).getFungusType() == 1) {
                    if (state.getBlock().is(IETags.Blocks.ANGER_CRIMSON_SHROOMLOIN_BLOCKS)) {
                        shroomloinEntity.becomeAngryAt(event.getPlayer());
                    }
                }

                if (((ShroomloinEntity) entity).getFungusType() == 2) {
                    if (state.getBlock().is(IETags.Blocks.ANGER_WARPED_SHROOMLOIN_BLOCKS)) {
                        shroomloinEntity.becomeAngryAt(event.getPlayer());
                    }
                }

                if (((ShroomloinEntity) entity).getFungusType() == 3) {
                    if (state.getBlock().is(IETags.Blocks.ANGER_LUMINOUS_SHROOMLOIN_BLOCKS)) {
                        shroomloinEntity.becomeAngryAt(event.getPlayer());
                    }
                }

                if (((ShroomloinEntity) entity).getFungusType() == 4) {
                    if (state.getBlock().is(IETags.Blocks.ANGER_RED_SHROOMLOIN_BLOCKS)) {
                        shroomloinEntity.becomeAngryAt(event.getPlayer());
                    }
                }

                if (((ShroomloinEntity) entity).getFungusType() == 5) {
                    if (state.getBlock().is(IETags.Blocks.ANGER_BROWN_SHROOMLOIN_BLOCKS)) {
                        shroomloinEntity.becomeAngryAt(event.getPlayer());
                    }
                }
            }
        }
    }




    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack heldItemStack = event.getItemStack();
        Level world = event.getWorld();
        BlockPos pos = event.getPos();
        Direction face = event.getFace();
        Player player = event.getPlayer();
        if (heldItemStack.getItem() == Items.BONE) {
            pos = pos.relative(face);
            BlockState blockstate = IEBlocks.BURIED_BONE.get().getPlaceableState(world, pos, face);
            if (blockstate != null) {
                player.swing(event.getHand());
                if (!world.isEmptyBlock(pos) && !world.isClientSide() && world.getBlockState(pos).getFluidState().isEmpty()) {
                    world.destroyBlock(pos, true);
                }
                world.setBlock(pos, blockstate, 3);
                world.playSound(player, pos, blockstate.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    heldItemStack.shrink(1);
                }
                ForgeEventFactory.onBlockPlace(player, BlockSnapshot.create(world.dimension(), world, pos), face);
            }
        } else if (heldItemStack.getItem() == Items.QUARTZ) {
            pos = pos.relative(face);
            BlockState blockstate = IEBlocks.PLANTED_QUARTZ.get().getPlaceableState(world, pos, face);
            if (blockstate != null) {
                player.swing(event.getHand());
                if (!world.isEmptyBlock(pos) && !world.isClientSide() && world.getBlockState(pos).getFluidState().isEmpty()) {
                    world.destroyBlock(pos, true);
                }
                world.setBlock(pos, blockstate, 3);
                world.playSound(player, pos, blockstate.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    heldItemStack.shrink(1);
                }
                ForgeEventFactory.onBlockPlace(player, BlockSnapshot.create(world.dimension(), world, pos), face);
            }
        }

        if (heldItemStack.getItem() == Items.GLOWSTONE_DUST) {
            if (world.getBlockState(pos).getBlock() == IEBlocks.DIMSTONE.get()) {
                player.swing(event.getHand());
                for (int i = 0; i < 20; i++) {
                    world.addParticle(IEParticleTypes.GLOWSTONE_SPARKLE.get(), pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.0, 0.0);
                }
                world.playSound(null, event.getPos(), IESoundEvents.GLOWSTONE_RECHARGE.get(), SoundSource.BLOCKS, 1.0F, (float) (0.75F + event.getWorld().getRandom().nextDouble() / 2));
                world.setBlockAndUpdate(pos, Blocks.GLOWSTONE.defaultBlockState());
            } else if (world.getBlockState(pos).getBlock() == IEBlocks.DULLSTONE.get()) {
                player.swing(event.getHand());
                for (int i = 0; i < 20; i++) {
                    world.addParticle(IEParticleTypes.GLOWSTONE_SPARKLE.get(), pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.0, 0.0);
                }
                world.playSound(null, event.getPos(), IESoundEvents.GLOWSTONE_RECHARGE.get(), SoundSource.BLOCKS, 1.0F, (float) (0.5F + event.getWorld().getRandom().nextDouble() / 3));
                world.setBlockAndUpdate(pos, IEBlocks.DIMSTONE.get().defaultBlockState());
            }
        }
    }

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Level world = event.getWorld();
        Player player = event.getPlayer();
        ItemStack heldItemStack = player.getItemInHand(event.getHand());

        if (heldItemStack.getItem() == Items.MAGMA_CREAM) {
            player.swing(event.getHand());

            if (!world.isClientSide) {
                ThrowableMagmaCreamEntity throwableMagmaCreamEntity = new ThrowableMagmaCreamEntity(world, player);
                throwableMagmaCreamEntity.setItem(heldItemStack);
				throwableMagmaCreamEntity.shootFromRotation(player, player.xRot, player.yRot, -20, 0.5f, 1);
				world.addFreshEntity(throwableMagmaCreamEntity);
                world.playSound(null, event.getPos(), SoundEvents.SNOWBALL_THROW, SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            player.awardStat(Stats.ITEM_USED.get(heldItemStack.getItem()));

            if (!player.abilities.instabuild) {
                heldItemStack.shrink(1);
            }
        } else if (heldItemStack.getItem() == Items.FIRE_CHARGE) {
            player.swing(event.getHand());

            if (!world.isClientSide) {
                ThrowableFireChargeEntity throwableFireChargeEntity = new ThrowableFireChargeEntity(world, player, player.getLookAngle().x(), player.getLookAngle().y(), player.getLookAngle().z());
                world.addFreshEntity(throwableFireChargeEntity);
                world.playSound(null, event.getPos(), SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            player.awardStat(Stats.ITEM_USED.get(heldItemStack.getItem()));

            if (!player.abilities.instabuild) {
                heldItemStack.shrink(1);
            }
        }
    }

    @SubscribeEvent
    public void onApplyBonemeal(BonemealEvent event) {
        Block block = event.getBlock().getBlock();
        Level world = event.getWorld();
        BlockPos pos = event.getPos();
        if (block == Blocks.SHROOMLIGHT && Miscellaneous.SHROOMLIGHT_GROWABLE.getBool()) {
            pos = pos.below();
            if (world.isEmptyBlock(pos)) {
                event.setResult(Event.Result.ALLOW);
                if (world.getRandom().nextDouble() < Miscellaneous.SHROOMLIGHT_GROW_CHANCE.getDouble() && !world.isClientSide()) {
                    world.setBlock(pos, IEBlocks.SHROOMLIGHT_FUNGUS.get().defaultBlockState().setValue(HorizontalBushBlock.FACE, AttachFace.CEILING), 3);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPotionColorCalculate(PotionColorCalculationEvent event) {
        List<MobEffectInstance> effects = new ArrayList<>(event.getEffects());
        int customEffects = 0;

        // Hide base infection effect particles
        for (MobEffectInstance effectInstance : effects) {
            if (effectInstance.getEffect() == IEEffects.INFECTION.get() || effectInstance.getEffect() == IEEffects.LUMINOUS.get()) {
                customEffects++;
            }
        }

        if (customEffects == effects.size()) {
            event.shouldHideParticles(true);
        }
    }

    @SubscribeEvent
    public void onLivingEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();

        // Make sure we are checking potion effects on the server, not client
        if (entity.isEffectiveAi() && entity.getCommandSenderWorld() instanceof ServerLevel) {
            if (entity.hasEffect(IEEffects.INFECTION.get())) {
                if ((entity.getEffect(IEEffects.INFECTION.get()).getDuration() & 10) == 0 && entity.getEffect(IEEffects.INFECTION.get()).isVisible()) {
                    // Use ServerWorld#spawnParticle instead of World#addParticle because this code is running on the server side
                    ((ServerLevel) entity.getCommandSenderWorld()).sendParticles(IEParticleTypes.INFECTION.get(), entity.getRandomX(entity.getBoundingBox().getXsize()), entity.getRandomY(), entity.getRandomZ(entity.getBoundingBox().getZsize()), 0, 0, 0, 0, 1);
                }
            }

            if (entity.hasEffect(IEEffects.LUMINOUS.get())) {
                if ((entity.getEffect(IEEffects.LUMINOUS.get()).getDuration() & 50) == 0 && entity.getEffect(IEEffects.LUMINOUS.get()).isVisible()) {
                    // Use ServerWorld#spawnParticle instead of World#addParticle because this code is running on the server side
                    ((ServerLevel) entity.getCommandSenderWorld()).sendParticles(IEParticleTypes.GLOWSTONE_SPARKLE.get(), entity.getRandomX(entity.getBoundingBox().getXsize()), entity.getRandomY(), entity.getRandomZ(entity.getBoundingBox().getZsize()), 0, 0, 0, 0, 0.2);
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingFinishUse(LivingEntityUseItemEvent.Finish event) {
        ItemStack item = event.getItem();
        LivingEntity entity = (LivingEntity) event.getEntity();

        if (item.getItem() == IEItems.CURED_JERKY.get() && item.getUseAnimation() == UseAnim.EAT) {
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * Miscellaneous.JERKY_EFFECT_DURATION.getInt(), Miscellaneous.JERKY_EFFECT_AMPLIFIER.getInt()));
        }
    }

    @SubscribeEvent
    public void onEntityJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof AreaEffectCloud) {
            for (MobEffectInstance effect : ((AreaEffectCloud) event.getEntity()).potion.getEffects()) {
                if (effect.getEffect() == IEEffects.INFECTION.get()) {
                    ((AreaEffectCloud) event.getEntity()).setParticle(IEParticleTypes.INFECTION.get());
                } else if (effect.getEffect() == IEEffects.LUMINOUS.get()) {
                    ((AreaEffectCloud) event.getEntity()).setParticle(IEParticleTypes.GLOWSTONE_SPARKLE.get());
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingEntityAttack(LivingAttackEvent event) {
        LivingEntity entity = event.getEntityLiving();

        // If entity has infection, on hit, make a splash of particles
        if (entity.isEffectiveAi() && entity.getCommandSenderWorld() instanceof ServerLevel) {
            if (entity.hasEffect(IEEffects.INFECTION.get())) {
                if (event.getSource() != DamageSource.MAGIC) {
                    for (int i = 0; i < 32; i++) {
                        ((ServerLevel) entity.getCommandSenderWorld()).sendParticles(IEParticleTypes.INFECTION.get(), entity.getRandomX(1), entity.getRandomY(), entity.getRandomZ(1), 1, 0, 0, 0, 1);
                    }
                }
            }
        }
    }

    private static VolineEatTable volineEatTable;
    private static SpawnrateManager spawnrateManager;

    @SubscribeEvent
    public void onResourceReload(AddReloadListenerEvent event) {
        volineEatTable = new VolineEatTable();
        spawnrateManager = new SpawnrateManager();

        event.addListener(volineEatTable);
        spawnrateManager.loadResources();
    }

    public static Map<Item, Map<Item, Integer>> getVolineEatTable() {
        if (volineEatTable == null) {
            throw new IllegalStateException("Can not retrieve VolineEatTable until resources have loaded once.");
        }

        return volineEatTable.getVolineEatTable();
    }

    public static Map<String, Map<String, SpawnrateManager.SpawnInfo>> getSpawnrateManager() {
        if (spawnrateManager == null) {
            spawnrateManager = new SpawnrateManager();
        }

        return spawnrateManager.getSpawnrates();
    }
}
