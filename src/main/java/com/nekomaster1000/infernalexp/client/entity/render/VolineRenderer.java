package com.nekomaster1000.infernalexp.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.VolineModel;
import com.nekomaster1000.infernalexp.entities.VolineEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.resources.ResourceLocation;

public class VolineRenderer extends MobRenderer<VolineEntity, VolineModel<VolineEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
        "textures/entity/voline.png");
    protected static final ResourceLocation TIRED_TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
        "textures/entity/voline_tired.png");

    public VolineRenderer(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new VolineModel<>(), 0.7F);
		this.addLayer(new VolineGlowLayer<>(this));
	}

	@Override
	public void render(VolineEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		shadowRadius = 0.25F * entityIn.getVolineSize();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	protected void scale(VolineEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
		matrixStackIn.scale(entitylivingbaseIn.getVolineSize(), entitylivingbaseIn.getVolineSize(), entitylivingbaseIn.getVolineSize());
	}

	@Override
	public ResourceLocation getTextureLocation(VolineEntity entity) {
		return entity.getAttributeValue(Attributes.MOVEMENT_SPEED) <= 0 ? TIRED_TEXTURE : TEXTURE;
	}
}
