package com.nekomaster1000.infernalexp.client.entity.model;

import com.google.common.collect.ImmutableList;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.nekomaster1000.infernalexp.entities.BasaltGiantEntity;

import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

@SuppressWarnings("FieldCanBeLocal")
public class BasaltGiantModel<T extends BasaltGiantEntity> extends ListModel<T> {

    private final ModelPart Body;
    private final ModelPart Torso;
    private final ModelPart Torso2;
    private final ModelPart Head;
    private final ModelPart Jaw;
    private final ModelPart LeftArm;
    private final ModelPart LeftArmJoint;
    private final ModelPart RightArm;
    private final ModelPart RightArmJoint;
    private final ModelPart RightLeg;
    private final ModelPart RightLegJoint;
    private final ModelPart LeftLeg;
    private final ModelPart LeftLegJoint;

    public BasaltGiantModel() {
        texWidth = 128;
        texHeight = 128;

        Body = new ModelPart(this);
        Body.setPos(0.0F, 24.0F, 0.0F);


        Torso = new ModelPart(this);
        Torso.setPos(0.0F, -36.0F, 4.0F);
        Body.addChild(Torso);
        Torso.texOffs(4, 51).addBox(-9.0F, -15.0F, -4.0F, 18.0F, 15.0F, 8.0F, 0.0F, false);

        Torso2 = new ModelPart(this);
        Torso2.setPos(0.0F, -15.0F, -1.0F);
        Torso.addChild(Torso2);
        Torso2.texOffs(25, 85).addBox(-1.0F, -26.0F, 0.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);
        Torso2.texOffs(57, 43).addBox(-7.0F, -24.0F, -5.0F, 3.0F, 9.0F, 3.0F, 0.0F, false);
        Torso2.texOffs(57, 0).addBox(5.0F, -24.0F, 2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
        Torso2.texOffs(57, 23).addBox(6.0F, -26.0F, -5.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);
        Torso2.texOffs(24, 101).addBox(-9.0F, -28.0F, 1.0F, 5.0F, 11.0F, 5.0F, 0.0F, false);
        Torso2.texOffs(0, 50).addBox(-1.0F, -21.0F, -4.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        Torso2.texOffs(57, 43).addBox(-10.0F, -17.0F, -7.0F, 20.0F, 17.0F, 14.0F, 0.0F, false);

        Head = new ModelPart(this);
        Head.setPos(0.5F, -4.0F, -7.0F);
        Torso2.addChild(Head);
        Head.texOffs(57, 0).addBox(-7.0F, -8.0F, -12.0F, 13.0F, 10.0F, 12.0F, 0.0F, false);

        Jaw = new ModelPart(this);
        Jaw.setPos(0.0F, 2.0F, 0.0F);
        Head.addChild(Jaw);
        Jaw.texOffs(57, 23).addBox(-9.0F, 0.0F, -14.0F, 17.0F, 5.0F, 14.0F, 0.0F, false);

        LeftArm = new ModelPart(this);
        LeftArm.setPos(12.0F, -11.0F, -1.0F);
        Torso2.addChild(LeftArm);
        setRotationAngle(LeftArm, 0.0F, 0.0F, -0.0436F);
        LeftArm.texOffs(105, 29).addBox(-1.0F, -8.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        LeftArm.texOffs(105, 29).addBox(1.0F, -6.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        LeftArm.texOffs(104, 105).addBox(-2.0F, -4.0F, -3.0F, 6.0F, 17.0F, 6.0F, 0.0F, true);

        LeftArmJoint = new ModelPart(this);
        LeftArmJoint.setPos(1.0F, 12.0F, 0.0F);
        LeftArm.addChild(LeftArmJoint);
        LeftArmJoint.texOffs(49, 75).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 17.0F, 8.0F, 0.0F, true);
        LeftArmJoint.texOffs(88, 80).addBox(-5.0F, 17.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);

        RightArm = new ModelPart(this);
        RightArm.setPos(-13.0F, -11.0F, -0.9F);
        Torso2.addChild(RightArm);
        setRotationAngle(RightArm, 0.0F, 0.0F, 0.0436F);
        RightArm.texOffs(105, 23).addBox(0.0F, -8.0F, -0.1F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        RightArm.texOffs(105, 23).addBox(-2.0F, -6.0F, -2.1F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        RightArm.texOffs(104, 105).addBox(-3.0F, -4.0F, -3.1F, 6.0F, 17.0F, 6.0F, 0.0F, false);

        RightArmJoint = new ModelPart(this);
        RightArmJoint.setPos(0.0F, 12.0F, 0.0F);
        RightArm.addChild(RightArmJoint);
        RightArmJoint.texOffs(49, 75).addBox(-4.0F, 0.0F, -4.1F, 8.0F, 17.0F, 8.0F, 0.0F, false);
        RightArmJoint.texOffs(88, 80).addBox(-5.0F, 17.0F, -5.1F, 10.0F, 10.0F, 10.0F, 0.0F, true);

        RightLeg = new ModelPart(this);
        RightLeg.setPos(-5.0F, -36.0F, 4.0F);
        Body.addChild(RightLeg);
        RightLeg.texOffs(79, 104).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, true);

        RightLegJoint = new ModelPart(this);
        RightLegJoint.setPos(10.0F, 17.0F, 0.0F);
        RightLeg.addChild(RightLegJoint);
        RightLegJoint.texOffs(46, 101).addBox(-14.0F, 0.0F, -4.0F, 8.0F, 19.0F, 8.0F, 0.0F, true);

        LeftLeg = new ModelPart(this);
        LeftLeg.setPos(5.0F, -36.0F, 4.0F);
        Body.addChild(LeftLeg);
        LeftLeg.texOffs(79, 104).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

        LeftLegJoint = new ModelPart(this);
        LeftLegJoint.setPos(0.0F, 17.0F, 0.0F);
        LeftLeg.addChild(LeftLegJoint);
        LeftLegJoint.texOffs(46, 101).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 19.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        int i = entity.getAttackTimer();
        if(i <= 0){
            this.RightLeg.xRot = Mth.cos(limbSwing * 0.4F + (float)Math.PI) * 1.0F * limbSwingAmount;
            this.Jaw.xRot = 0.0F;
        }

        this.LeftArm.xRot = Mth.cos(limbSwing * 0.4F + (float)Math.PI) * 0.8F * limbSwingAmount;
        this.RightArm.xRot = Mth.cos(limbSwing * 0.4F) * 0.8F * limbSwingAmount;
        this.LeftLeg.xRot = Mth.cos(limbSwing * 0.4F) * 1.0F * limbSwingAmount;
        this.Torso2.xRot = Mth.cos(limbSwing * 0.3332F) * 0.25F * limbSwingAmount;
        this.Head.xRot = Mth.cos(limbSwing * 0.1551F) * 0.15F * limbSwingAmount;

        /*
        TOP SECRET FOR RELEASE 3

        this.LeftArmJoint.rotateAngleX = -Math.abs(this.LeftArm.rotateAngleX * 0.6662F);
        this.RightArmJoint.rotateAngleX = -Math.abs(this.RightArm.rotateAngleX * 0.6662F);
        this.Body.rotateAngleZ = MathHelper.cos(limbSwing * 0.3332F + (float)Math.PI) * 0.4F * limbSwingAmount;
        this.LeftLegJoint.rotateAngleX = Math.abs(this.LeftLeg.rotateAngleX * 0.75F);

        THIS ONE NEEDS TO GO UP IN THE IF STATEMENT ABOVE

        this.RightLegJoint.rotateAngleX = Math.abs(this.RightLeg.rotateAngleX * 0.75F);
         */

    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.Body, this.Head, this.Jaw, this.Torso, this.Torso2, this.LeftArm, this.LeftArmJoint, this.RightArm, this.RightArmJoint, this.LeftLeg, this.LeftLegJoint, this.RightLeg, this.RightLegJoint);
    }

    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        int i = entityIn.getAttackTimer();
        if (i > 0) {
            this.RightLeg.xRot = -0.9F + 0.9F * Mth.triangleWave((float)i - partialTick, 10.0F);
            this.Jaw.xRot = 0.375F - 0.375F * Mth.triangleWave((float)i - partialTick, 10.0F);
        }
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
