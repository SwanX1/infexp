package com.nekomaster1000.infernalexp.client.entity.model;

import com.google.common.collect.ImmutableList;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.BlackstoneDwarfEntity;

import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class BlackstoneDwarfModel<T extends BlackstoneDwarfEntity> extends ListModel<T> {

    	private final ModelPart Body;
    	private final ModelPart Torso;
    	private final ModelPart Torso2;
    	private final ModelPart Head;
    	private final ModelPart LeftEar;
    	private final ModelPart RightEar;
    	private final ModelPart Jaw;
        private final ModelPart LeftArm;
        private final ModelPart LeftArmJoint;
        private final ModelPart RightArm;
        private final ModelPart RightArmJoint;
        private final ModelPart LeftLeg;
        private final ModelPart LeftLegJoint;
        private final ModelPart RightLeg;
        private final ModelPart RightLegJoint;

        public BlackstoneDwarfModel() {
            texWidth = 128;
            texHeight = 128;

            Body = new ModelPart(this);
            Body.setPos(0.0F, 24.0F, -4.0F);


            Torso = new ModelPart(this);
            Torso.setPos(0.0F, -36.0F, 4.0F);
            Body.addChild(Torso);
            Torso.texOffs(0, 50).addBox(-9.0F, 3.0F, -4.0F, 18.0F, 15.0F, 8.0F, 0.0F, false);

            Torso2 = new ModelPart(this);
            Torso2.setPos(0.0F, 7.0F, -1.0F);
            Torso.addChild(Torso2);
            Torso2.texOffs(0, 0).addBox(-10.0F, -17.0F, -7.0F, 20.0F, 17.0F, 14.0F, 0.0F, false);

            Head = new ModelPart(this);
            Head.setPos(0.0F, -6.0F, -7.0F);
            Torso2.addChild(Head);
            Head.texOffs(0, 73).addBox(-7.0F, -5.0F, -12.0F, 13.0F, 10.0F, 12.0F, 0.01F, false);
            Head.texOffs(0, 0).addBox(-3.0F, 2.0F, -13.0F, 5.0F, 3.0F, 1.0F, 0.01F, false);

            LeftEar = new ModelPart(this);
            LeftEar.setPos(5.0F, -3.0F, -6.0F);
            Head.addChild(LeftEar);
            setRotationAngle(LeftEar, 0.0F, 0.0F, -0.3927F);
            LeftEar.texOffs(100, 0).addBox(0.0F, 0.0F, -6.0F, 2.0F, 10.0F, 12.0F, 0.0F, false);

            RightEar = new ModelPart(this);
            RightEar.setPos(-6.0F, -3.0F, -6.0F);
            Head.addChild(RightEar);
            setRotationAngle(RightEar, 0.0F, 0.0F, 0.3927F);
            RightEar.texOffs(100, 0).addBox(-2.0F, 0.0F, -6.0F, 2.0F, 10.0F, 12.0F, 0.0F, true);

            Jaw = new ModelPart(this);
            Jaw.setPos(0.0F, 5.0F, 0.0F);
            Head.addChild(Jaw);
            Jaw.texOffs(11, 102).addBox(-9.0F, -6.0F, -14.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
            Jaw.texOffs(0, 119).addBox(6.0F, -6.0F, -14.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
            Jaw.texOffs(0, 31).addBox(-9.0F, 0.0F, -14.0F, 17.0F, 5.0F, 14.0F, 0.0F, false);

            LeftArm = new ModelPart(this);
            LeftArm.setPos(12.0F, -11.0F, -1.0F);
            Torso2.addChild(LeftArm);
            LeftArm.texOffs(74, 0).addBox(-2.0F, -4.0F, -3.0F, 6.0F, 17.0F, 6.0F, 0.0F, false);

            LeftArmJoint = new ModelPart(this);
            LeftArmJoint.setPos(1.0F, 14.0F, 0.0F);
            LeftArm.addChild(LeftArmJoint);
            LeftArmJoint.texOffs(70, 24).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 11.0F, 8.0F, 0.0F, false);
            LeftArmJoint.texOffs(66, 44).addBox(-5.0F, 9.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);

            RightArm = new ModelPart(this);
            RightArm.setPos(-13.0F, -11.0F, -0.9F);
            Torso2.addChild(RightArm);
            RightArm.texOffs(55, 64).addBox(-3.0F, -4.0F, -3.1F, 6.0F, 17.0F, 6.0F, 0.0F, false);

            RightArmJoint = new ModelPart(this);
            RightArmJoint.setPos(0.0F, 14.0F, 0.0F);
            RightArm.addChild(RightArmJoint);
            RightArmJoint.texOffs(51, 88).addBox(-4.0F, -2.0F, -4.1F, 8.0F, 11.0F, 8.0F, 0.0F, false);
            RightArmJoint.texOffs(47, 108).addBox(-5.0F, 9.0F, -5.1F, 10.0F, 10.0F, 10.0F, 0.0F, false);

            LeftLeg = new ModelPart(this);
            LeftLeg.setPos(-5.0F, -21.0F, 4.0F);
            Body.addChild(LeftLeg);
            LeftLeg.texOffs(100, 85).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

            LeftLegJoint = new ModelPart(this);
            LeftLegJoint.setPos(0.0F, 12.0F, 0.0F);
            LeftLeg.addChild(LeftLegJoint);
            LeftLegJoint.texOffs(96, 110).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

            RightLeg = new ModelPart(this);
            RightLeg.setPos(5.0F, -21.0F, 4.0F);
            Body.addChild(RightLeg);
            RightLeg.texOffs(100, 85).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

            RightLegJoint = new ModelPart(this);
            RightLegJoint.setPos(0.0F, 12.0F, 0.0F);
            RightLeg.addChild(RightLegJoint);
            RightLegJoint.texOffs(96, 110).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);
        }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        int i = entity.getAttackTimer();
        if(i <= 0){
            this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.Jaw.xRot = 0.0F;
        }

        this.LeftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.RightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.0F * limbSwingAmount;
        this.Torso2.xRot = Mth.cos(limbSwing * 0.3332F) * 0.25F * limbSwingAmount;
        this.Head.xRot = Mth.cos(limbSwing * 0.1550F) * 0.15F * limbSwingAmount;

		this.LeftEar.zRot = Mth.cos(limbSwing * 0.5F) * 0.35F * limbSwingAmount - 25.0F * ((float)Math.PI / 180);
		this.RightEar.zRot = -Mth.cos(limbSwing * 0.5F) * 0.35F * limbSwingAmount + 25.0F * ((float)Math.PI / 180);
        }

    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.Body, this.Head, this.LeftEar, this.RightEar, this.Jaw, this.Torso, this.Torso2, this.LeftArm, this.LeftArmJoint, this.RightArm, this.RightArmJoint, this.LeftLeg, this.LeftLegJoint, this.RightLeg, this.RightLegJoint);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Body.render(matrixStack, buffer, packedLight, packedOverlay);

    }

    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        int i = entityIn.getAttackTimer();
        if (i > 0) {
            this.LeftArm.xRot = -0.9F + 0.9F * Mth.triangleWave((float)i - partialTick, 10.0F);
            this.RightArm.xRot = -0.9F + 0.9F * Mth.triangleWave((float)i - partialTick, 10.0F);
            this.Jaw.xRot = 0.375F - 0.375F * Mth.triangleWave((float)i - partialTick, 10.0F);
        }
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
