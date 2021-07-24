package com.nekomaster1000.infernalexp.init;

import com.nekomaster1000.infernalexp.InfernalExpansion;

import net.minecraft.world.level.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.resources.ResourceLocation;

public class IETags {

    public static class Blocks {

        public static final Tag.Named<Block> BASE_STONE_CANYON = tag("base_stone_canyon");
        public static final Tag.Named<Block> BASE_STONE_SHORES = tag("base_stone_shores");
        public static final Tag.Named<Block> DULLTHORNS_GROUND = tag("dullthorns_ground");
        public static final Tag.Named<Block> GLOW_FIRE_BASE_BLOCKS = tag("glow_fire_base_blocks");
        public static final Tag.Named<Block> MAGMA_CUBE_AVOID_BLOCKS = tag("magma_cube_avoid_blocks");
        public static final Tag.Named<Block> BURIED_BONE_BASE_BLOCKS = tag("buried_bone_base_blocks");
        public static final Tag.Named<Block> PLANTED_QUARTZ_BASE_BLOCKS = tag("planted_quartz_base_blocks");
        public static final Tag.Named<Block> ANGER_CRIMSON_SHROOMLOIN_BLOCKS = tag("anger_crimson_shroomloin_blocks");
        public static final Tag.Named<Block> ANGER_WARPED_SHROOMLOIN_BLOCKS = tag("anger_warped_shroomloin_blocks");
        public static final Tag.Named<Block> ANGER_LUMINOUS_SHROOMLOIN_BLOCKS = tag("anger_luminous_shroomloin_blocks");
        public static final Tag.Named<Block> ANGER_RED_SHROOMLOIN_BLOCKS = tag("anger_red_shroomloin_blocks");
        public static final Tag.Named<Block> ANGER_BROWN_SHROOMLOIN_BLOCKS = tag("anger_brown_shroomloin_blocks");

        private static Tag.Named<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(InfernalExpansion.MOD_ID, name));
        }
    }
}
