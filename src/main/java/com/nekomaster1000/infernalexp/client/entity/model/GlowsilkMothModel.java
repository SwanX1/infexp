package com.nekomaster1000.infernalexp.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.GlowsilkMothEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class GlowsilkMothModel<T extends GlowsilkMothEntity> extends EntityModel<T> {
	private final ModelPart all;
	private final ModelPart body;
	private final ModelPart legs3_r1;
	private final ModelPart legs2_r1;
	private final ModelPart legs1_r1;
	private final ModelPart bone_r1;
	private final ModelPart antenna;
	private final ModelPart leftwing;
	private final ModelPart rightwing;

    public GlowsilkMothModel() {
        texWidth = 64;
        texHeight = 64;

        all = new ModelPart(this);
        all.setPos(-1.0F, 14.0F, 0.0F);
        setRotationAngle(all, 0.3927F, 0.0F, 0.0F);


        body = new ModelPart(this);
        body.setPos(1.0F, 0.0F, 0.0F);
        all.addChild(body);


        legs3_r1 = new ModelPart(this);
        legs3_r1.setPos(0.0F, 3.0F, -2.0F);
        body.addChild(legs3_r1);
        setRotationAngle(legs3_r1, 0.7854F, 0.0F, 0.0F);
        legs3_r1.texOffs(48, 60).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 0.0F, 2.0F, 0.0F, false);

        legs2_r1 = new ModelPart(this);
        legs2_r1.setPos(0.0F, 0.0F, -2.0F);
        body.addChild(legs2_r1);
        setRotationAngle(legs2_r1, 0.7854F, 0.0F, 0.0F);
        legs2_r1.texOffs(48, 60).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 0.0F, 2.0F, 0.0F, false);

        legs1_r1 = new ModelPart(this);
        legs1_r1.setPos(0.0F, -3.0F, -2.0F);
        body.addChild(legs1_r1);
        setRotationAngle(legs1_r1, 0.7854F, 0.0F, 0.0F);
        legs1_r1.texOffs(48, 60).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 0.0F, 2.0F, 0.0F, false);

        bone_r1 = new ModelPart(this);
        bone_r1.setPos(0.0F, 10.0F, 0.0F);
        body.addChild(bone_r1);
        setRotationAngle(bone_r1, 0.0F, 3.1416F, 0.0F);
        bone_r1.texOffs(48, 44).addBox(-2.0F, -17.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        bone_r1.texOffs(56, 60).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        antenna = new ModelPart(this);
        antenna.setPos(1.0F, -7.0F, -1.0F);
        all.addChild(antenna);
        antenna.texOffs(51, 40).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 0.0F, 0.0F, true);
        antenna.texOffs(51, 36).addBox(-3.0F, -4.0F, 0.05F, 6.0F, 4.0F, 0.0F, 0.0F, true);

        leftwing = new ModelPart(this);
        leftwing.setPos(3.0F, 1.0F, 0.0F);
        all.addChild(leftwing);
        leftwing.texOffs(0, 41).addBox(0.0F, -14.0F, 0.0F, 13.0F, 23.0F, 0.0F, 0.0F, true);
        leftwing.texOffs(0, 17).addBox(0.0F, -14.0F, 0.05F, 13.0F, 23.0F, 0.0F, 0.0F, true);

        rightwing = new ModelPart(this);
        rightwing.setPos(-1.0F, 1.0F, 0.0F);
        all.addChild(rightwing);
        rightwing.texOffs(0, 41).addBox(-13.0F, -14.0F, 0.0F, 13.0F, 23.0F, 0.0F, 0.0F, false);
        rightwing.texOffs(0, 17).addBox(-13.0F, -14.0F, 0.05F, 13.0F, 23.0F, 0.0F, 0.0F, false);
	}

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        // Flap wings back and forth constantly
        this.leftwing.yRot = Mth.cos(0.75F * ageInTicks);
        this.rightwing.yRot = -Mth.cos(0.75F * ageInTicks);
        this.antenna.xRot = Mth.cos(0.2F * ageInTicks) * 0.2F;
        this.all.xRot = 0.3927F;
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
