package com.nekomaster1000.infernalexp.client.entity.model;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.EmbodyEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class EmbodyModel<E extends EmbodyEntity> extends EntityModel<EmbodyEntity> {
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart head;

    public EmbodyModel() {
        texWidth = 64;
        texHeight = 64;

        body = new ModelPart(this);
        body.setPos(0.0F, 24.0F, 6.0F);
        setRotationAngle(body, 0.7854F, 0.0F, 0.0F);
        body.texOffs(0, 16).addBox(-4.0F, -12.0F, 0.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        left_arm = new ModelPart(this);
        left_arm.setPos(4.0F, -11.0F, 2.0F);
        body.addChild(left_arm);
        setRotationAngle(left_arm, -1.7453F, 0.0F, 0.0F);
        left_arm.texOffs(0, 32).addBox(0.0F, -1.0F, -2.0F, 3.0F, 14.0F, 4.0F, 0.0F, false);

        right_arm = new ModelPart(this);
        right_arm.setPos(-4.0F, -11.0F, 2.0F);
        body.addChild(right_arm);
        setRotationAngle(right_arm, -1.7453F, 0.0F, 0.0F);
        right_arm.texOffs(24, 24).addBox(-3.0F, -1.0F, -2.0F, 3.0F, 14.0F, 4.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, -12.0F, 2.0F);
        body.addChild(head);
        setRotationAngle(head, -0.7854F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(EmbodyEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        right_arm.xRot = Mth.sin(limbSwing)/2 - 45;
        left_arm.xRot = Mth.sin(-limbSwing)/2 - 45;
        body.zRot = Mth.sin(limbSwing)/8;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
