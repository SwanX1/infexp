package com.nekomaster1000.infernalexp.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.ShroomloinModel;
import com.nekomaster1000.infernalexp.entities.ShroomloinEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class ShroomloinDecorLayer extends RenderLayer<ShroomloinEntity, ShroomloinModel<ShroomloinEntity>> {
    public static final ResourceLocation[] SHROOMLOIN_TEXTURES = new ResourceLocation[]{
        new ResourceLocation(InfernalExpansion.MOD_ID,"textures/entity/shroomloin/1_shroomloin.png"),
        new ResourceLocation(InfernalExpansion.MOD_ID, "textures/entity/shroomloin/2_shroomloin.png"),
        new ResourceLocation(InfernalExpansion.MOD_ID, "textures/entity/shroomloin/3_shroomloin.png"),
        new ResourceLocation(InfernalExpansion.MOD_ID, "textures/entity/shroomloin/4_shroomloin.png"),
        new ResourceLocation(InfernalExpansion.MOD_ID, "textures/entity/shroomloin/5_shroomloin.png")
    };
    private final ShroomloinModel<ShroomloinEntity> model = new ShroomloinModel<>();

    public ShroomloinDecorLayer(RenderLayerParent<ShroomloinEntity, ShroomloinModel<ShroomloinEntity>> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, ShroomloinEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ResourceLocation texture = SHROOMLOIN_TEXTURES[entitylivingbaseIn.getFungusType()];
        coloredCutoutModelCopyLayerRender(this.getParentModel(), model, texture, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
    }
}
