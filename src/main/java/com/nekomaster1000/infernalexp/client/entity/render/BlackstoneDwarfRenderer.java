package com.nekomaster1000.infernalexp.client.entity.render;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.BlackstoneDwarfModel;
import com.nekomaster1000.infernalexp.entities.BlackstoneDwarfEntity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BlackstoneDwarfRenderer extends MobRenderer<BlackstoneDwarfEntity, BlackstoneDwarfModel<BlackstoneDwarfEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
        "textures/entity/blackstone_dwarf.png");

    public BlackstoneDwarfRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new BlackstoneDwarfModel<>(), 0.7f);
        this.addLayer(new BlackstoneDwarfGlowLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(BlackstoneDwarfEntity entity) {
        return TEXTURE;
    }
}
