package com.nekomaster1000.infernalexp.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.BasaltGiantModel;
import com.nekomaster1000.infernalexp.entities.BasaltGiantEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BasaltGiantRenderer extends MobRenderer<BasaltGiantEntity, BasaltGiantModel<BasaltGiantEntity>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
        "textures/entity/basalt_giant.png");

	public BasaltGiantRenderer(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new BasaltGiantModel<>(), 0.7F);
		this.addLayer(new BasaltGiantGlowLayer<>(this));
	}

	@Override
	public void render(BasaltGiantEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		shadowRadius = 0.25F * entityIn.getGiantSize();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	protected void scale(BasaltGiantEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
		matrixStackIn.scale(entitylivingbaseIn.getGiantSize(), entitylivingbaseIn.getGiantSize(), entitylivingbaseIn.getGiantSize());
	}

	@Override
	public ResourceLocation getTextureLocation(BasaltGiantEntity entity) {
		return TEXTURE;
	}
}
