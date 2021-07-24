package com.nekomaster1000.infernalexp.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.ShroomloinModel;
import com.nekomaster1000.infernalexp.entities.ShroomloinEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShroomloinGlowLayer extends RenderLayer<ShroomloinEntity, ShroomloinModel<ShroomloinEntity>> {
    private static final ResourceLocation[] SHROOMLOIN_GLOW_TEXTURES = new ResourceLocation[]{
        new ResourceLocation(InfernalExpansion.MOD_ID,"textures/entity/shroomloin/layer/1_shroomloin_glow.png"),
        new ResourceLocation(InfernalExpansion.MOD_ID, "textures/entity/shroomloin/layer/2_shroomloin_glow.png"),
        new ResourceLocation(InfernalExpansion.MOD_ID, "textures/entity/shroomloin/layer/3_shroomloin_glow.png"),
        new ResourceLocation(InfernalExpansion.MOD_ID, "textures/entity/shroomloin/layer/4_shroomloin_glow.png"),
        new ResourceLocation(InfernalExpansion.MOD_ID, "textures/entity/shroomloin/layer/5_shroomloin_glow.png")
    };

    public ShroomloinGlowLayer(RenderLayerParent<ShroomloinEntity, ShroomloinModel<ShroomloinEntity>> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn, ShroomloinEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ResourceLocation textures = SHROOMLOIN_GLOW_TEXTURES[entitylivingbaseIn.getFungusType()];
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.getRenderType(textures));
        this.getParentModel().renderToBuffer(matrixStack, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public RenderType getRenderType(ResourceLocation texture) {
        return RenderType.eyes(texture);
    }
}
