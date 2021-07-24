package com.nekomaster1000.infernalexp.tileentities;

import com.nekomaster1000.infernalexp.init.IETileEntityTypes;

import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class GlowCampfireTileEntity extends CampfireBlockEntity {
    public GlowCampfireTileEntity() {
        super();
    }
    
    public BlockEntityType<?> getType() {
        return IETileEntityTypes.GLOW_CAMPFIRE.get();
    }
}
