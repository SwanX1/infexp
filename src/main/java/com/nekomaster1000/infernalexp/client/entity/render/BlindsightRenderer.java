package com.nekomaster1000.infernalexp.client.entity.render;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.BlindsightModel;
import com.nekomaster1000.infernalexp.entities.BlindsightEntity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BlindsightRenderer extends MobRenderer<BlindsightEntity, BlindsightModel<BlindsightEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
        "textures/entity/blindsight.png");

    public BlindsightRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new BlindsightModel<>(), 0.7f);
        this.addLayer(new BlindsightGlowLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(BlindsightEntity entity) {
        return TEXTURE;
    }
}
