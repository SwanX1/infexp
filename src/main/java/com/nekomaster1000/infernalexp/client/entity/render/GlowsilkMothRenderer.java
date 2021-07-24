package com.nekomaster1000.infernalexp.client.entity.render;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.GlowsilkMothModel;
import com.nekomaster1000.infernalexp.entities.GlowsilkMothEntity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GlowsilkMothRenderer extends MobRenderer<GlowsilkMothEntity, GlowsilkMothModel<GlowsilkMothEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
        "textures/entity/glowsilk_moth.png");

    public GlowsilkMothRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new GlowsilkMothModel<>(), 0.7f);
        this.addLayer(new GlowsilkMothGlowLayer(this));;
    }

    @Override
    public ResourceLocation getTextureLocation(GlowsilkMothEntity entity) {
        return TEXTURE;
    }

}
