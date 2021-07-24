package com.nekomaster1000.infernalexp.world.gen;

import com.nekomaster1000.infernalexp.entities.BasaltGiantEntity;
import com.nekomaster1000.infernalexp.entities.BlackstoneDwarfEntity;
import com.nekomaster1000.infernalexp.entities.BlindsightEntity;
import com.nekomaster1000.infernalexp.entities.EmbodyEntity;
import com.nekomaster1000.infernalexp.entities.GlowsilkMothEntity;
import com.nekomaster1000.infernalexp.entities.GlowsquitoEntity;
import com.nekomaster1000.infernalexp.entities.ShroomloinEntity;
import com.nekomaster1000.infernalexp.entities.VolineEntity;
import com.nekomaster1000.infernalexp.entities.WarpbeetleEntity;
import com.nekomaster1000.infernalexp.init.IEEntityTypes;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModEntityPlacement {

    public static void spawnPlacement() {
        SpawnPlacements.register(IEEntityTypes.VOLINE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VolineEntity::checkMobSpawnRules);
        SpawnPlacements.register(IEEntityTypes.SHROOMLOIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShroomloinEntity::checkMobSpawnRules);
        SpawnPlacements.register(IEEntityTypes.WARPBEETLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WarpbeetleEntity::checkMobSpawnRules);
        //EntitySpawnPlacementRegistry.register(IEEntityTypes.CEROBEETLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CerobeetleEntity::canSpawnOn);
        SpawnPlacements.register(IEEntityTypes.EMBODY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EmbodyEntity::checkMobSpawnRules);
        SpawnPlacements.register(IEEntityTypes.BASALT_GIANT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BasaltGiantEntity::checkMobSpawnRules);
        SpawnPlacements.register(IEEntityTypes.BLACKSTONE_DWARF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlackstoneDwarfEntity::checkMobSpawnRules);
        SpawnPlacements.register(IEEntityTypes.GLOWSQUITO.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GlowsquitoEntity::checkMobSpawnRules);
        //EntitySpawnPlacementRegistry.register(IEEntityTypes.PYRNO.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PyrnoEntity::canSpawnOn);
        SpawnPlacements.register(IEEntityTypes.BLINDSIGHT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlindsightEntity::checkMobSpawnRules);
        SpawnPlacements.register(IEEntityTypes.GLOWSILK_MOTH.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GlowsilkMothEntity::checkMobSpawnRules);
    }

}
