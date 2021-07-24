package com.nekomaster1000.infernalexp.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.CerobeetleEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class CerobeetleModel<T extends CerobeetleEntity> extends EntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart left_shield;
    private final ModelPart left_wing;
    private final ModelPart left_wing2;
    private final ModelPart right_shield;
    private final ModelPart right_wing;
    private final ModelPart right_wing2;
    private final ModelPart left_leg_1;
    private final ModelPart left_leg_2;
    private final ModelPart left_leg_3;
    private final ModelPart right_leg_1;
    private final ModelPart right_leg_2;
    private final ModelPart right_leg_3;

    public CerobeetleModel() {
        texWidth = 256;
        texHeight = 256;

        body = new ModelPart(this);
        body.setPos(0.0F, 22.0F, 0.0F);
        body.texOffs(0, 0).addBox(-10.0F, -12.0F, -18.0F, 20.0F, 12.0F, 34.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, 18.0F, -18.0F);
        head.texOffs(86, 16).addBox(-6.0F, -4.0F, -8.0F, 12.0F, 8.0F, 8.0F, 0.0F, false);
        head.texOffs(0, 31).addBox(0.0F, -17.0F, -16.0F, 0.0F, 19.0F, 16.0F, 0.0F, false);

        left_shield = new ModelPart(this);
        left_shield.setPos(1.0F, 10.0F, -12.0F);
        left_shield.texOffs(56, 58).addBox(-1.0F, -3.0F, 0.0F, 12.0F, 12.0F, 32.0F, 0.0F, false);

        left_wing = new ModelPart(this);
        left_wing.setPos(3.0F, 10.0F, -11.0F);
        left_wing.texOffs(94, 41).addBox(-4.0F, 0.0F, -1.0F, 10.0F, 0.0F, 19.0F, 0.0F, false);

        left_wing2 = new ModelPart(this);
        left_wing2.setPos(4.0F, 10.0F, -10.0F);
        left_wing2.texOffs(82, 60).addBox(-5.0F, 0.0F, 0.0F, 12.0F, 0.0F, 30.0F, 0.0F, false);

        right_shield = new ModelPart(this);
        right_shield.setPos(-1.0F, 10.0F, -12.0F);
        right_shield.texOffs(0, 46).addBox(-11.0F, -3.0F, 0.0F, 12.0F, 12.0F, 32.0F, 0.0F, false);

        right_wing = new ModelPart(this);
        right_wing.setPos(-3.0F, 10.0F, -10.0F);
        right_wing.texOffs(94, 41).addBox(-6.0F, 0.0F, -2.0F, 10.0F, 0.0F, 19.0F, 0.0F, true);

        right_wing2 = new ModelPart(this);
        right_wing2.setPos(-4.0F, 10.0F, -10.0F);
        right_wing2.texOffs(82, 60).addBox(-7.0F, 0.0F, 0.0F, 12.0F, 0.0F, 30.0F, 0.0F, true);

        left_leg_1 = new ModelPart(this);
        left_leg_1.setPos(10.0F, 20.0F, -14.5F);
        setRotationAngle(left_leg_1, 0.0F, 0.0F, 0.3491F);
        left_leg_1.texOffs(62, 12).addBox(0.0F, 0.0F, -7.5F, 10.0F, 0.0F, 12.0F, 0.0F, false);

        left_leg_2 = new ModelPart(this);
        left_leg_2.setPos(10.0F, 20.0F, 1.5F);
        setRotationAngle(left_leg_2, 0.0F, 0.0F, 0.3927F);
        left_leg_2.texOffs(62, 0).addBox(0.0F, 0.0F, -7.5F, 10.0F, 0.0F, 12.0F, 0.0F, false);

        left_leg_3 = new ModelPart(this);
        left_leg_3.setPos(10.0F, 20.0F, 11.5F);
        setRotationAngle(left_leg_3, 0.0F, 0.0F, 0.3491F);
        left_leg_3.texOffs(44, 58).addBox(0.0F, 0.0F, -5.5F, 10.0F, 0.0F, 12.0F, 0.0F, false);

        right_leg_1 = new ModelPart(this);
        right_leg_1.setPos(-10.0F, 20.0F, -14.5F);
        setRotationAngle(right_leg_1, 0.0F, 0.0F, -0.3491F);
        right_leg_1.texOffs(44, 46).addBox(-10.0F, 0.0F, -7.5F, 10.0F, 0.0F, 12.0F, 0.0F, false);

        right_leg_2 = new ModelPart(this);
        right_leg_2.setPos(-10.0F, 20.0F, 1.5F);
        setRotationAngle(right_leg_2, 0.0F, 0.0F, -0.3491F);
        right_leg_2.texOffs(0, 12).addBox(-10.0F, 0.0F, -7.5F, 10.0F, 0.0F, 12.0F, 0.0F, false);

        right_leg_3 = new ModelPart(this);
        right_leg_3.setPos(-10.0F, 20.0F, 11.5F);
        setRotationAngle(right_leg_3, 0.0F, 0.0F, -0.3491F);
        right_leg_3.texOffs(0, 0).addBox(-10.0F, 0.0F, -5.5F, 10.0F, 0.0F, 12.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(CerobeetleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*
            int i = entity.getAttackTimer();
            if(i <= 0){
            this.head.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
         }
*/
        this.left_leg_1.zRot = Mth.cos(limbSwing * 1.6662F) * 1.4F * limbSwingAmount;
        this.left_leg_2.zRot = Mth.cos(limbSwing * 1.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.left_leg_3.zRot = Mth.cos(limbSwing * 1.6662F) * 1.4F * limbSwingAmount;
        this.right_leg_1.zRot = Mth.cos(limbSwing * 1.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_leg_2.zRot = Mth.cos(limbSwing * 1.6662F) * 1.4F * limbSwingAmount;
        this.right_leg_3.zRot = Mth.cos(limbSwing * 1.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        if (!entity.isOnGround()) {
            entity.shellRotationMultiplier += 0.1F;

            if (entity.shellRotationMultiplier > 1.0F) {
                entity.shellRotationMultiplier = 1.0F;
            }

            float wingRotation = Mth.cos(ageInTicks * 2.1F) * (float) Math.PI * 0.15F + 1.0F;

            setRotationAngle(left_wing, wingRotation, 0.5F, 0.3F);
            setRotationAngle(right_wing, wingRotation, -0.5F, -0.3F);
            setRotationAngle(left_wing2, wingRotation, 0.4F, 0.2F);
            setRotationAngle(right_wing2, wingRotation, -0.4F, -0.2F);


        } else {
            entity.shellRotationMultiplier -= 0.1F;

            if (entity.shellRotationMultiplier < 0.0F) {
                entity.shellRotationMultiplier = 0.0F;
            }

            setRotationAngle(left_wing, 0.0F, 0.0F, 0.0F);
            setRotationAngle(right_wing, 0.0F, 0.0F, 0.0F);
            setRotationAngle(left_wing2, 0.0F, 0.0F, 0.0F);
            setRotationAngle(right_wing2, 0.0F, -0.0F, -0.0F);

        }

        setRotationAngle(left_shield, 1.2F * entity.shellRotationMultiplier, -0.4F * entity.shellRotationMultiplier, 0.9F * entity.shellRotationMultiplier);
        setRotationAngle(right_shield, 1.2F * entity.shellRotationMultiplier, 0.4F * entity.shellRotationMultiplier, -0.9F * entity.shellRotationMultiplier);
    }


    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        left_shield.render(matrixStack, buffer, packedLight, packedOverlay);
        left_wing.render(matrixStack, buffer, packedLight, packedOverlay);
        left_wing2.render(matrixStack, buffer, packedLight, packedOverlay);
        right_shield.render(matrixStack, buffer, packedLight, packedOverlay);
        right_wing.render(matrixStack, buffer, packedLight, packedOverlay);
        right_wing2.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg_1.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg_2.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg_3.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg_1.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg_2.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg_3.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void prepareMobModel(CerobeetleEntity entity, float limbSwing, float limbSwingAmount, float partialTick) {

        int i = entity.getAttackTimer();
        if (i > 0) {
            this.head.xRot = -0.9F + 0.9F * Mth.triangleWave((float)i - partialTick, 10.0F);
        }
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
