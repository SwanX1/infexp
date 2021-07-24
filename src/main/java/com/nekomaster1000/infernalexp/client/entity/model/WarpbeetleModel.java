package com.nekomaster1000.infernalexp.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.WarpbeetleEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class WarpbeetleModel<T extends WarpbeetleEntity> extends EntityModel<T> {
    private final ModelPart warpbeetle;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart left_shield;
    private final ModelPart left_wing;
    private final ModelPart right_wing;
    private final ModelPart right_shield;
    private final ModelPart left_leg_1;
    private final ModelPart left_leg_2;
    private final ModelPart left_leg_3;
    private final ModelPart right_leg_1;
    private final ModelPart right_leg_2;
    private final ModelPart right_leg_3;

    public WarpbeetleModel() {
        texWidth = 128;
        texHeight = 128;

        warpbeetle = new ModelPart(this);
        warpbeetle.setPos(0.0F, 23.75F, 0.0F);


        body = new ModelPart(this);
        body.setPos(0.0F, -1.0F, 0.0F);
        warpbeetle.addChild(body);
        body.texOffs(0, 0).addBox(-5.0F, -5.75F, -9.0F, 10.0F, 6.0F, 17.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, -2.0F, -9.0F);
        body.addChild(head);
        head.texOffs(43, 8).addBox(-3.0F, -1.75F, -4.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
        head.texOffs(0, 15).addBox(0.0F, -8.75F, -10.0F, 0.0F, 10.0F, 8.0F, 0.0F, false);

        left_shield = new ModelPart(this);
        left_shield.setPos(5.0F, -6.0F, -6.0F);
        body.addChild(left_shield);
        left_shield.texOffs(28, 29).addBox(-5.0F, -1.75F, 0.0F, 6.0F, 6.0F, 16.0F, 0.0F, false);

        left_wing = new ModelPart(this);
        left_wing.setPos(1.0F, -6.0F, -5.0F);
        body.addChild(left_wing);
        left_wing.texOffs(41, 30).addBox(-3.0F, 0.24F, 0.0F, 6.0F, 0.0F, 15.0F, 0.0F, false);

        right_wing = new ModelPart(this);
        right_wing.setPos(-1.0F, -6.0F, -5.0F);
        body.addChild(right_wing);
        right_wing.texOffs(41, 30).addBox(-3.0F, 0.24F, 0.0F, 6.0F, 0.0F, 15.0F, 0.0F, true);

        right_shield = new ModelPart(this);
        right_shield.setPos(-5.0F, -6.0F, -6.0F);
        body.addChild(right_shield);
        right_shield.texOffs(0, 23).addBox(-1.0F, -1.75F, 0.0F, 6.0F, 6.0F, 16.0F, 0.0F, false);

        left_leg_1 = new ModelPart(this);
        left_leg_1.setPos(5.0F, -1.0F, -6.0F);
        body.addChild(left_leg_1);
        setRotationAngle(left_leg_1, 0.0F, 0.0F, 0.3927F);
        left_leg_1.texOffs(31, 6).addBox(0.0F, 0.25F, -5.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        left_leg_2 = new ModelPart(this);
        left_leg_2.setPos(5.0F, -1.0F, -2.0F);
        body.addChild(left_leg_2);
        setRotationAngle(left_leg_2, 0.0F, 0.0F, 0.3927F);
        left_leg_2.texOffs(31, 0).addBox(0.0F, 0.25F, -1.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        left_leg_3 = new ModelPart(this);
        left_leg_3.setPos(5.0F, -1.0F, 4.0F);
        body.addChild(left_leg_3);
        setRotationAngle(left_leg_3, 0.0F, 0.0F, 0.3927F);
        left_leg_3.texOffs(22, 29).addBox(0.0F, 0.25F, -1.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        right_leg_1 = new ModelPart(this);
        right_leg_1.setPos(-5.0F, -1.0F, -6.0F);
        body.addChild(right_leg_1);
        setRotationAngle(right_leg_1, 0.0F, 0.0F, -0.3927F);
        right_leg_1.texOffs(22, 23).addBox(-5.0F, 0.25F, -5.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        right_leg_2 = new ModelPart(this);
        right_leg_2.setPos(-5.0F, -1.0F, -2.0F);
        body.addChild(right_leg_2);
        setRotationAngle(right_leg_2, 0.0F, 0.0F, -0.3927F);
        right_leg_2.texOffs(0, 6).addBox(-5.0F, 0.25F, -1.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        right_leg_3 = new ModelPart(this);
        right_leg_3.setPos(-5.0F, -1.0F, 4.0F);
        body.addChild(right_leg_3);
        setRotationAngle(right_leg_3, 0.0F, 0.0F, -0.3927F);
        right_leg_3.texOffs(0, 0).addBox(-5.0F, 0.25F, -1.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.left_leg_1.zRot = -(Mth.abs(Mth.cos(limbSwing * 1.1F)) * 2.2F * limbSwingAmount) + 0.3927F;
        this.left_leg_2.zRot = -(Mth.abs(Mth.cos(Math.max(limbSwing - 4.0F, 0) * 1.1F)) * 2.2F * limbSwingAmount) + 0.3927F;
        this.left_leg_3.zRot = -(Mth.abs(Mth.cos(limbSwing * 1.1F)) * 2.2F * limbSwingAmount) + 0.3927F;
        this.right_leg_1.zRot = (Mth.abs(Mth.cos(Math.max(limbSwing - 4.0F, 0) * 1.1F)) * 2.2F * limbSwingAmount) - 0.3927F;
        this.right_leg_2.zRot = (Mth.abs(Mth.cos(limbSwing * 1.1F)) * 2.2F * limbSwingAmount) - 0.3927F;
        this.right_leg_3.zRot = (Mth.abs(Mth.cos(Math.max(limbSwing - 4.0F, 0) * 1.1F)) * 2.2F * limbSwingAmount) - 0.3927F;

        this.left_leg_1.yRot = -Mth.sin(limbSwing * 1.1F) * 0.8F * limbSwingAmount;
        this.left_leg_2.yRot = -Mth.sin(Math.max(limbSwing - 4.0F, 0) * 1.1F) * 0.9F * limbSwingAmount;
        this.left_leg_3.yRot = -Mth.sin(limbSwing * 1.1F) * 1.4F * limbSwingAmount;
        this.right_leg_1.yRot = Mth.sin(Math.max(limbSwing - 4.0F, 0) * 1.1F) * 0.8F * limbSwingAmount;
        this.right_leg_2.yRot = Mth.sin(limbSwing * 1.1F) * 0.9F * limbSwingAmount;
        this.right_leg_3.yRot = Mth.sin(Math.max(limbSwing - 4.0F, 0) * 1.1F) * 1.4F * limbSwingAmount;

        if (!entity.isOnGround()) {
            entity.shellRotationMultiplier += 0.1F;

            if (entity.shellRotationMultiplier > 1.0F) {
                entity.shellRotationMultiplier = 1.0F;
            }

            float wingRotation = Mth.cos(ageInTicks * 2.1F) * (float) Math.PI * 0.15F + 1.0F;

            setRotationAngle(left_wing, wingRotation, 0.5F, 0.3F);
            setRotationAngle(right_wing, wingRotation, -0.5F, -0.3F);
        } else {
            entity.shellRotationMultiplier -= 0.1F;

            if (entity.shellRotationMultiplier < 0.0F) {
                entity.shellRotationMultiplier = 0.0F;
            }

            setRotationAngle(left_wing, 0.0F, 0.0F, 0.0F);
            setRotationAngle(right_wing, 0.0F, 0.0F, 0.0F);
        }

        setRotationAngle(left_shield, 1.2F * entity.shellRotationMultiplier, -0.4F * entity.shellRotationMultiplier, 0.9F * entity.shellRotationMultiplier);
        setRotationAngle(right_shield, 1.2F * entity.shellRotationMultiplier, 0.4F * entity.shellRotationMultiplier, -0.9F * entity.shellRotationMultiplier);
    }


    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        warpbeetle.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
