package com.nekomaster1000.infernalexp.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.ShroomloinModel;
import com.nekomaster1000.infernalexp.entities.ShroomloinEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ShroomloinRenderer extends MobRenderer<ShroomloinEntity, ShroomloinModel<ShroomloinEntity>> {
    private static final ResourceLocation TEXTURE = ShroomloinDecorLayer.SHROOMLOIN_TEXTURES[0];

    public ShroomloinRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new ShroomloinModel<>(), 0.7f);
        this.addLayer(new ShroomloinDecorLayer(this));
        this.addLayer(new ShroomloinGlowLayer(this));
    }

    protected void scale(ShroomloinEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float f = entitylivingbaseIn.getShroomloinFlashIntensity(partialTickTime);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        matrixStackIn.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(ShroomloinEntity livingEntityIn, float partialTicks) {
        float f = livingEntityIn.getShroomloinFlashIntensity(partialTicks);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(ShroomloinEntity entity) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(ShroomloinEntity entity) {
        return super.isShaking(entity) || entity.isShaking();
    }
}
