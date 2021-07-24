package com.nekomaster1000.infernalexp.mixin.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.carver.WorldCarver;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(WorldCarver.class)
public interface WorldCarverAccessor {

    @Accessor("carvableBlocks")
    Set<Block> getCarvableBlocks();

    @Accessor("carvableBlocks")
    void setCarvableBlocks(Set<Block> carvableBlocks);

}
