package com.nekomaster1000.infernalexp.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.BlindsightEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class BlindsightModel<T extends BlindsightEntity> extends EntityModel<T> {
	private final ModelPart all;
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart MouthRoof;
	private final ModelPart LowerJaw;
	private final ModelPart FrontLeftLeg;
	private final ModelPart FrontRightLeg;
	private final ModelPart BackLeftLeg;
	private final ModelPart BackRightLeg;

    public BlindsightModel() {
        texWidth = 64;
        texHeight = 64;

        all = new ModelPart(this);
        all.setPos(0.0F, 24.0F, -3.0F);


        Body = new ModelPart(this);
        Body.setPos(0.0F, -4.0F, 7.0F);
        all.addChild(Body);


        Head = new ModelPart(this);
        Head.setPos(0.0F, 0.0F, 0.0F);
        Body.addChild(Head);
        setRotationAngle(Head, -0.3927F, 0.0F, 0.0F);
        Head.texOffs(0, 0).addBox(-8.0F, -8.0F, -12.0F, 16.0F, 8.0F, 12.0F, 0.0F, false);

        MouthRoof = new ModelPart(this);
        MouthRoof.setPos(0.0F, 6.0F, -8.0F);
        Head.addChild(MouthRoof);
        setRotationAngle(MouthRoof, -1.5708F, 0.0F, 0.0F);
        MouthRoof.texOffs(32, 41).addBox(-8.0F, -8.0F, -7.0F, 16.0F, 12.0F, 0.0F, 0.0F, false);

        LowerJaw = new ModelPart(this);
        LowerJaw.setPos(0.0F, 0.0F, 0.0F);
        Body.addChild(LowerJaw);
        LowerJaw.texOffs(0, 20).addBox(-8.0F, -1.0F, -12.0F, 16.0F, 3.0F, 12.0F, 0.0F, false);
        LowerJaw.texOffs(4, 41).addBox(-8.0F, 0.0F, -12.0F, 16.0F, 0.0F, 12.0F, 0.0F, false);

        FrontLeftLeg = new ModelPart(this);
        FrontLeftLeg.setPos(6.0F, -2.0F, -3.0F);
        all.addChild(FrontLeftLeg);
        FrontLeftLeg.texOffs(0, 35).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.001F, false);

        FrontRightLeg = new ModelPart(this);
        FrontRightLeg.setPos(-6.0F, -2.0F, -3.0F);
        all.addChild(FrontRightLeg);
        FrontRightLeg.texOffs(8, 35).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.001F, false);

        BackLeftLeg = new ModelPart(this);
        BackLeftLeg.setPos(7.0F, -2.0F, 5.0F);
        all.addChild(BackLeftLeg);
        BackLeftLeg.texOffs(44, 56).addBox(-1.0F, 0.0F, -5.0F, 4.0F, 2.0F, 6.0F, 0.001F, true);

        BackRightLeg = new ModelPart(this);
        BackRightLeg.setPos(-7.0F, -2.0F, 5.0F);
        all.addChild(BackRightLeg);
        BackRightLeg.texOffs(44, 56).addBox(-3.0F, 0.0F, -5.0F, 4.0F, 2.0F, 6.0F, 0.001F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float partialTick = ageInTicks - (float) entityIn.tickCount;

        float jumpRotation = Mth.sin(entityIn.getJumpCompletion(partialTick) * (float) Math.PI);
        this.BackLeftLeg.xRot = jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.BackRightLeg.xRot = jumpRotation * 50.0F * ((float) Math.PI / 180F);
    }

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		this.Head.xRot = -Mth.abs(Mth.cos(0.4662F * limbSwing) * 1.2F * limbSwingAmount);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		all.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
