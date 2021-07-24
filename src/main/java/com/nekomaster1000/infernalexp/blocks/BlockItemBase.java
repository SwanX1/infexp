package com.nekomaster1000.infernalexp.blocks;

import com.nekomaster1000.infernalexp.InfernalExpansion;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block) {
        super(block, new Item.Properties().tab(InfernalExpansion.TAB));
    }
}
