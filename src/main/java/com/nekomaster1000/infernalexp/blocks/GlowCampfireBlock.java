package com.nekomaster1000.infernalexp.blocks;

import com.nekomaster1000.infernalexp.tileentities.GlowCampfireTileEntity;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;

public class GlowCampfireBlock extends CampfireBlock {
    public GlowCampfireBlock(boolean smokey, int fireDamage, BlockBehaviour.Properties properties) {
        super(smokey, fireDamage, properties);
    }

    public BlockEntity newBlockEntity(BlockGetter worldIn) {
        return new GlowCampfireTileEntity();
    }
}
