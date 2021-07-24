package com.nekomaster1000.infernalexp.events;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig;
import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig.MobInteractions;
import com.nekomaster1000.infernalexp.data.SpawnrateManager;
import com.nekomaster1000.infernalexp.entities.BasaltGiantEntity;
import com.nekomaster1000.infernalexp.entities.BlackstoneDwarfEntity;
import com.nekomaster1000.infernalexp.entities.EmbodyEntity;
import com.nekomaster1000.infernalexp.entities.GlowsquitoEntity;
import com.nekomaster1000.infernalexp.entities.VolineEntity;
import com.nekomaster1000.infernalexp.entities.WarpbeetleEntity;
import com.nekomaster1000.infernalexp.entities.ai.AvoidBlockGoal;
import com.nekomaster1000.infernalexp.init.IETags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.ResourceLocationException;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = InfernalExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event) {

        //
        //RUN AWAY!!
        //

            //Piglins fear Warpbeetles and Embodies
            if (event.getEntity() instanceof Piglin) {
                if (MobInteractions.PIGLIN_FEAR_WARPBEETLE.getBoolean()) {
                    ((Piglin) event.getEntity()).goalSelector.addGoal(4,
                        new AvoidEntityGoal<>((Piglin) event.getEntity(),
                            WarpbeetleEntity.class, 16.0F, 1.2D, 1.2D));
                }
                if (MobInteractions.PIGLIN_FEAR_EMBODY.getBoolean()) {
                    ((Piglin) event.getEntity()).goalSelector.addGoal(4,
                        new AvoidEntityGoal<>((Piglin) event.getEntity(),
                            EmbodyEntity.class, 16.0F, 1.2D, 1.2D));
                }
                if (MobInteractions.PIGLIN_FEAR_DWARF.getBoolean()) {
                    ((Piglin) event.getEntity()).goalSelector.addGoal(4,
                        new AvoidEntityGoal<>((Piglin) event.getEntity(),
                            BlackstoneDwarfEntity.class, 16.0F, 1.2D, 1.2D));
                }
            }

            if (event.getEntity() instanceof Hoglin) {
                if (MobInteractions.HOGLIN_FEAR_WARPBEETLE.getBoolean()) {
                    ((Hoglin) event.getEntity()).goalSelector.addGoal(4,
                        new AvoidEntityGoal<>((Hoglin) event.getEntity(),
                            WarpbeetleEntity.class, 16.0F, 1.2D, 1.2D));
                }
                if (MobInteractions.HOGLIN_FEAR_EMBODY.getBoolean()) {
                    ((Hoglin) event.getEntity()).goalSelector.addGoal(4,
                        new AvoidEntityGoal<>((Hoglin) event.getEntity(),
                            EmbodyEntity.class, 16.0F, 1.2D, 1.2D));
                }
            }

            //
            //ATTACK!!
            //

            //Spiders attack Warp beetles
            if (event.getEntity() instanceof Spider && MobInteractions.SPIDER_ATTACK_WARPBEETLE.getBoolean()) {
                ((Spider) event.getEntity()).targetSelector.addGoal(4,
                    new NearestAttackableTargetGoal<>((Spider) event.getEntity(),
                        WarpbeetleEntity.class, true, false));
            }


            //Skeletons attacks Piglins, Brutes, Embodies & Basalt Giants
            if (event.getEntity() instanceof Skeleton) {
                if (MobInteractions.SKELETON_ATTACK_PIGLIN.getBoolean()) {
                    ((Skeleton) event.getEntity()).targetSelector.addGoal(2,
                        new NearestAttackableTargetGoal<>((Skeleton) event.getEntity(),
                            Piglin.class, true, false));
                }
                if (MobInteractions.SKELETON_ATTACK_BRUTE.getBoolean()) {
                    ((Skeleton) event.getEntity()).targetSelector.addGoal(2,
                        new NearestAttackableTargetGoal<>((Skeleton) event.getEntity(),
                            PiglinBrute.class, true, false));
                }
                if (MobInteractions.SKELETON_ATTACK_EMBODY.getBoolean()) {
                    ((Skeleton) event.getEntity()).targetSelector.addGoal(3,
                        new NearestAttackableTargetGoal<>((Skeleton) event.getEntity(),
                            EmbodyEntity.class, true, false));
                }
                if (MobInteractions.SKELETON_ATTACK_GIANT.getBoolean()) {
                    ((Skeleton) event.getEntity()).targetSelector.addGoal(2,
                        new NearestAttackableTargetGoal<>((Skeleton) event.getEntity(),
                            BasaltGiantEntity.class, true, false));
                }
            }

            //Piglins attack Skeletons & Voline
            if (event.getEntity() instanceof Piglin) {
                if (MobInteractions.PIGLIN_ATTACK_SKELETON.getBoolean()) {
                    ((Piglin) event.getEntity()).targetSelector.addGoal(2,
                        new NearestAttackableTargetGoal<>((Piglin) event.getEntity(),
                            Skeleton.class, true, false));
                }
                if (MobInteractions.PIGLIN_ATTACK_VOLINE.getBoolean()) {
                    ((Piglin) event.getEntity()).targetSelector.addGoal(2,
                        new NearestAttackableTargetGoal<>((Piglin) event.getEntity(),
                            VolineEntity.class, true, false));
                }
            }

            if (event.getEntity() instanceof PiglinBrute) {
                if (MobInteractions.BRUTE_ATTACK_SKELETON.getBoolean()) {
                    ((PiglinBrute) event.getEntity()).targetSelector.addGoal(2,
                        new NearestAttackableTargetGoal<>((PiglinBrute) event.getEntity(),
                            Skeleton.class, true, false));
                }
                if (MobInteractions.BRUTE_ATTACK_VOLINE.getBoolean()) {
                    ((PiglinBrute) event.getEntity()).targetSelector.addGoal(2,
                        new NearestAttackableTargetGoal<>((PiglinBrute) event.getEntity(),
                            VolineEntity.class, true, false));
                }
            }


            //Ghasts attack Voline, Embodies, Skeletons
            if (event.getEntity() instanceof Ghast) {
                if (MobInteractions.GHAST_ATTACK_GLOWSQUITO.getBoolean()) {
                    ((Ghast) event.getEntity()).targetSelector.addGoal(4,
                        new NearestAttackableTargetGoal<>((Ghast) event.getEntity(),
                            GlowsquitoEntity.class, true, false));
                }

                if (MobInteractions.GHAST_ATTACK_EMBODY.getBoolean()) {
                    ((Ghast) event.getEntity()).targetSelector.addGoal(3,
                        new NearestAttackableTargetGoal<>((Ghast) event.getEntity(),
                            EmbodyEntity.class, true, false));
                }
                if (MobInteractions.GHAST_ATTACK_VOLINE.getBoolean()) {
                    ((Ghast) event.getEntity()).targetSelector.addGoal(2,
                        new NearestAttackableTargetGoal<>((Ghast) event.getEntity(),
                            VolineEntity.class, true, false));
                }
                if (MobInteractions.GHAST_ATTACK_SKELETON.getBoolean()) {
                    ((Ghast) event.getEntity()).targetSelector.addGoal(3,
                        new NearestAttackableTargetGoal<>((Ghast) event.getEntity(),
                            Skeleton.class, true, false));
                }
            }

            if (event.getEntity() instanceof MagmaCube) {
                ((MagmaCube) event.getEntity()).goalSelector.addGoal(0,
                    new AvoidBlockGoal((MagmaCube) event.getEntity(), IETags.Blocks.MAGMA_CUBE_AVOID_BLOCKS,
                        8));
            }
	}

    private void addEntityToSpawner(BiomeLoadingEvent event, EntityType<?> entityType, SpawnrateManager.SpawnInfo spawnInfo) {
        // Check if creatureSpawnProbability is within the exclusive bounds of 0-1,
        // If so change the creatureSpawnProbability
//        if (spawnInfo.getCreatureSpawnProbability() > 0 && spawnInfo.getCreatureSpawnProbability() < 1) {
//            event.getSpawns().withCreatureSpawnProbability(spawnInfo.getCreatureSpawnProbability());
//        }

//        // Check if there are any other changes to be made to the spawner
//        Optional.ofNullable(spawnInfo.getChanges()).ifPresent(changes -> changes.forEach((changeEntity, changeSpawnInfo) -> {
//            // If so, get the entity type of the change
//            EntityType<?> changeEntityType = ForgeRegistries.ENTITIES.getValue(changeEntity);
//
//            // Check if the change entity type exists
//            if (changeEntityType == null) {
//                throw new ResourceLocationException("Invalid EntityType resource location " + entityType.toString());
//            }
//
//            // Check if the change entity type exists in the spawner
//            MobSpawnInfo.Spawners spawner = event.getSpawns().getSpawner(changeEntityType.getClassification()).stream().filter(s -> s.type == changeEntityType).findFirst().orElseThrow(
//                // If not, through an exception
//                () -> new RuntimeException("Can't make changes to " + changeEntity.toString() + " because it doesn't exist in the spawner")
//            );
//
//            // Find which data to update from SpawnrateManager
//            int spawnrate = changeSpawnInfo.getSpawnRate() != 0 ? changeSpawnInfo.getSpawnRate() : spawner.itemWeight;
//            int minCount = changeSpawnInfo.getMinCount() != 0 ? changeSpawnInfo.getMinCount() : spawner.minCount;
//            int maxCount = changeSpawnInfo.getMaxCount() != 0 ? changeSpawnInfo.getMaxCount() : spawner.maxCount;
//
//            // Remove entity from spawner to make changes
//            event.getSpawns().getSpawner(changeEntityType.getClassification()).remove(spawner);
//
//            // Add it back with changes
//            event.getSpawns().withSpawner(changeEntityType.getClassification(),
//                new MobSpawnInfo.Spawners(changeEntityType, spawnrate, minCount, maxCount));
//        }));

        // Add our entity to the spawner
        event.getSpawns().addSpawn(entityType.getCategory(),
            new MobSpawnSettings.SpawnerData(entityType, spawnInfo.getSpawnRate(), spawnInfo.getMinCount(), spawnInfo.getMaxCount()));

        // Change spawn costs
        if (spawnInfo.getSpawnCostPerEntity() != null && spawnInfo.getMaxSpawnCost() != null) {
            event.getSpawns().addMobCharge(entityType, spawnInfo.getSpawnCostPerEntity(), spawnInfo.getMaxSpawnCost());
        } else if (spawnInfo.getSpawnCostPerEntity() != null || spawnInfo.getMaxSpawnCost() != null) {
            InfernalExpansion.LOGGER.error("EntityType {} has incomplete spawn cost data. When editing spawn costs, make sure to set both \"spawn_cost_per_entity\" and \"max_spawn_cost\"", entityType.toString());
        }
    }

    //Mob Spawning in pre-existing biomes
    @SubscribeEvent
    public void onBiomeLoad(BiomeLoadingEvent event) {

        if (event.getCategory() != Biome.BiomeCategory.NETHER) {
            return;
        }

        MiscEvents.getSpawnrateManager().forEach((entity, value) -> {
            // Get the biomes the entity is allowed to spawn in from InfernalExpansionConfig
            List<String> spawnableBiomes = Arrays.asList(InfernalExpansionConfig.MobSpawning.getByName(entity.split(":")[1]).getSpawnableBiomes().replace(" ", "").split(","));

            // Check if the current biome getting loaded is in the spawnable biomes, if not, return
            if (!spawnableBiomes.contains(event.getName().toString())) {
                return;
            }

            // Get the entity type from the name
            EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entity));

            // Check if the entity type exists
            if (entityType == null) {
                throw new ResourceLocationException("Invalid EntityType resource location " + entity);
            }

            // Find either the default spawn info or the spawn info for a specific biome from SpawnrateManager
            if (value.containsKey(event.getName().toString()) && spawnableBiomes.contains(event.getName().toString())) {
                addEntityToSpawner(event, entityType, value.get(event.getName().toString()));
            } else if (value.containsKey("default") && spawnableBiomes.contains(event.getName().toString())) {
                addEntityToSpawner(event, entityType, value.get("default"));
            } else {
                InfernalExpansion.LOGGER.error("{} doesn't have a default spawn entry", entity);
            }
        });
	}

}
