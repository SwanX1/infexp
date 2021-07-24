package com.nekomaster1000.infernalexp.client.entity.model;
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.ShroomloinEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class ShroomloinModel<T extends ShroomloinEntity> extends EntityModel<T> {
	private final ModelPart all;
	private final ModelPart body;
	private final ModelPart hat;
	private final ModelPart rightleg;
	private final ModelPart leftleg;

	public ShroomloinModel() {
		texWidth = 64;
		texHeight = 64;

		texWidth = 64;
		texHeight = 64;

		all = new ModelPart(this);
		all.setPos(0.0F, 24.0F, 0.0F);


		body = new ModelPart(this);
		body.setPos(0.0F, -4.0F, 0.0F);
		all.addChild(body);
		body.texOffs(1, 23).addBox(-5.0F, -6.0F, -5.0F, 10.0F, 6.0F, 9.0F, 0.0F, false);

		hat = new ModelPart(this);
		hat.setPos(0.0F, -4.0F, -0.5F);
		body.addChild(hat);
		hat.texOffs(0, 0).addBox(-8.0F, -6.0F, -8.0F, 16.0F, 6.0F, 16.0F, 0.0F, false);

		rightleg = new ModelPart(this);
		rightleg.setPos(-2.5F, -4.0F, -0.5F);
		all.addChild(rightleg);
		rightleg.texOffs(0, 8).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, true);

		leftleg = new ModelPart(this);
		leftleg.setPos(2.5F, -4.0F, -0.5F);
		all.addChild(leftleg);
		leftleg.texOffs(0, 0).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.hat.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.5F * limbSwingAmount;
		this.body.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.25F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		all.render(matrixStack, buffer, packedLight, packedOverlay);
		/*
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		hat.render(matrixStack, buffer, packedLight, packedOverlay);
		leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
		*/
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
