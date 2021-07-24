package com.nekomaster1000.infernalexp.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.InfernalExpansion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Vector3f;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfernalPaintingRenderer extends PaintingRenderer {
	public static final ResourceLocation BACK_TEXTURE_ATLAS_LOCATION = new ResourceLocation(InfernalExpansion.MOD_ID,
        "infernal_back");

	public InfernalPaintingRenderer(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(Painting entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
		matrixStackIn.scale(0.0625F, 0.0625F, 0.0625F);

		Motive paintingType = entityIn.motive;
		VertexConsumer iVertexBuilder = bufferIn.getBuffer(RenderType.entitySolid(this.getTextureLocation(entityIn)));
		PaintingTextureManager paintingSpriteUploader = Minecraft.getInstance().getPaintingTextures();
		renderPainting(matrixStackIn, iVertexBuilder, entityIn, paintingType.getWidth(), paintingType.getHeight(), paintingSpriteUploader.get(paintingType), paintingSpriteUploader.getSprite(BACK_TEXTURE_ATLAS_LOCATION));

		matrixStackIn.popPose();

		// Can't call super because it will override the custom back texture
		net.minecraftforge.client.event.RenderNameplateEvent renderNameplateEvent = new net.minecraftforge.client.event.RenderNameplateEvent(entityIn, entityIn.getDisplayName(), this, matrixStackIn, bufferIn, packedLightIn, partialTicks);
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(renderNameplateEvent);
		if (renderNameplateEvent.getResult() != net.minecraftforge.eventbus.api.Event.Result.DENY && (renderNameplateEvent.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW || this.shouldShowName(entityIn))) {
			renderNameTag(entityIn, renderNameplateEvent.getContent(), matrixStackIn, bufferIn, packedLightIn);
		}
	}
}
