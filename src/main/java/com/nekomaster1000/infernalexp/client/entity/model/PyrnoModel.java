package com.nekomaster1000.infernalexp.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.PyrnoEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class PyrnoModel<T extends PyrnoEntity> extends EntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart left_ear;
    private final ModelPart right_ear;
    private final ModelPart leg_1;
    private final ModelPart leg_2;
    private final ModelPart leg_3;
    private final ModelPart leg_4;
    private final ModelPart tail;

    public PyrnoModel() {
        texWidth = 128;
        texHeight = 128;

        body = new ModelPart(this);
        body.setPos(0.0F, 14.0F, 1.0F);
        body.texOffs(0, 0).addBox(-8.0F, -14.0F, -14.0F, 16.0F, 14.0F, 26.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, -12.0F, -14.0F);
        body.addChild(head);
        setRotationAngle(head, 0.6109F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-2.0F, -13.0F, -19.0F, 4.0F, 16.0F, 6.0F, 0.0F, false);
        head.texOffs(0, 40).addBox(-8.0F, -3.0F, -15.0F, 16.0F, 8.0F, 15.0F, 0.0F, false);

        left_ear = new ModelPart(this);
        left_ear.setPos(7.5F, -2.4649F, 0.1563F);
        head.addChild(left_ear);
        setRotationAngle(left_ear, 0.0F, 0.0F, 1.5708F);
        left_ear.texOffs(41, 41).addBox(-4.0F, -0.5351F, -0.1563F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        right_ear = new ModelPart(this);
        right_ear.setPos(-7.35F, -2.4649F, 0.1563F);
        head.addChild(right_ear);
        setRotationAngle(right_ear, 0.0F, 0.0F, -1.5708F);
        right_ear.texOffs(8, 0).addBox(-1.0F, -0.5351F, -0.1563F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        leg_1 = new ModelPart(this);
        leg_1.setPos(5.0F, 0.0F, -11.0F);
        body.addChild(leg_1);
        leg_1.texOffs(0, 63).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        leg_2 = new ModelPart(this);
        leg_2.setPos(-5.0F, 0.0F, -11.0F);
        body.addChild(leg_2);
        leg_2.texOffs(62, 40).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        leg_3 = new ModelPart(this);
        leg_3.setPos(-5.0F, 0.0F, 9.0F);
        body.addChild(leg_3);
        leg_3.texOffs(58, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        leg_4 = new ModelPart(this);
        leg_4.setPos(5.0F, 0.0F, 9.0F);
        body.addChild(leg_4);
        leg_4.texOffs(56, 57).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPos(0.0F, -12.0F, 12.0F);
        body.addChild(tail);
        setRotationAngle(tail, 0.2618F, 0.0F, 0.0F);
        tail.texOffs(0, 40).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 12.0F, 0.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

        @Override
        public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            this.leg_1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.leg_2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.leg_3.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.leg_4.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }

        @Override
        public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
            body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        }

}
