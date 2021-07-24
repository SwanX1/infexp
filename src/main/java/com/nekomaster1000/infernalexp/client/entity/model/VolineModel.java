package com.nekomaster1000.infernalexp.client.entity.model;// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.VolineEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class VolineModel<T extends VolineEntity> extends EntityModel<T> {

	private final ModelPart body;
	private final ModelPart mouth_inside2;
	private final ModelPart mouth;
	private final ModelPart mouth_inside;
	private final ModelPart leg_1;
	private final ModelPart leg_2;
	private final ModelPart leg_3;
	private final ModelPart leg_4;

	public VolineModel() {
		texWidth = 32;
		texHeight = 32;

		body = new ModelPart(this);
		body.setPos(0.0F, 20.0F, 4.0F);
		body.texOffs(0, 0).addBox(-4.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		mouth_inside2 = new ModelPart(this);
		mouth_inside2.setPos(0.0F, -2.0F, -4.0F);
		body.addChild(mouth_inside2);
		setRotationAngle(mouth_inside2, -1.5708F, 0.0F, 0.0F);
		mouth_inside2.texOffs(0, 27).addBox(-4.0F, -4.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);
		mouth_inside2.texOffs(8, 27).addBox(0.0F, -4.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);
		mouth_inside2.texOffs(16, 27).addBox(0.0F, 0.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);
		mouth_inside2.texOffs(24, 27).addBox(-4.0F, 0.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);

		mouth = new ModelPart(this);
		mouth.setPos(0.0F, 24.0F, 0.0F);
		mouth.texOffs(0, 16).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 3.0F, 8.0F, 0.0F, false);

		mouth_inside = new ModelPart(this);
		mouth_inside.setPos(0.0F, -4.0F, 0.0F);
		mouth.addChild(mouth_inside);
		setRotationAngle(mouth_inside, -1.5708F, 0.0F, 0.0F);
		mouth_inside.texOffs(0, 27).addBox(-4.0F, -4.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);
		mouth_inside.texOffs(8, 27).addBox(0.0F, -4.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);
		mouth_inside.texOffs(16, 27).addBox(0.0F, 0.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);
		mouth_inside.texOffs(24, 27).addBox(-4.0F, 0.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);

		leg_1 = new ModelPart(this);
		leg_1.setPos(-2.0F, 22.0F, -2.0F);
		leg_1.texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		leg_2 = new ModelPart(this);
		leg_2.setPos(2.0F, 22.0F, -2.0F);
		leg_2.texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		leg_3 = new ModelPart(this);
		leg_3.setPos(-2.0F, 22.0F, 2.0F);
		leg_3.texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		leg_4 = new ModelPart(this);
		leg_4.setPos(2.0F, 22.0F, 2.0F);
		leg_4.texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.leg_1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg_2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg_3.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg_4.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.body.xRot = -Mth.abs(Mth.cos(limbSwing * 0.4662F) * 1.4F * limbSwingAmount);

		if (entityIn.isEating()) {
			this.body.xRot = (Mth.cos(ageInTicks) * (float) Math.PI * 0.1F);
		}

	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		mouth.render(matrixStack, buffer, packedLight, packedOverlay);
		leg_1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg_2.render(matrixStack, buffer, packedLight, packedOverlay);
		leg_3.render(matrixStack, buffer, packedLight, packedOverlay);
		leg_4.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
