package com.nekomaster1000.infernalexp.client.entity.model;

import com.google.common.collect.ImmutableList;

import com.nekomaster1000.infernalexp.entities.GlowsquitoEntity;

import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class GlowsquitoModel<T extends GlowsquitoEntity> extends AgeableListModel<T> {
    private final ModelPart Head;
    private final ModelPart Stinger;
    private final ModelPart Body;
    private final ModelPart LeftWing;
    private final ModelPart RightWing;
    private final ModelPart Butt;
    private final ModelPart LeftArm;
    private final ModelPart RightArm;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;

    public GlowsquitoModel() {
        super(true, 6.0F, 2.0F, 2.0F, 2.3F, 24.0F);
        texWidth = 64;
        texHeight = 64;

        Head = new ModelPart(this);
        Head.setPos(0.0F, 19.6F, -4.0F);
        Head.texOffs(26, 19).addBox(-2.0F, -2.6F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        Stinger = new ModelPart(this);
        Stinger.setPos(0.0F, 19.0F, -7.0F);
        setRotationAngle(Stinger, 0.3927F, 0.0F, 0.0F);
        Stinger.texOffs(0, 0).addBox(-0.5F, 0.0F, -6.5F, 1.0F, 0.0F, 7.0F, 0.0F, false);

        Body = new ModelPart(this);
        Body.setPos(0.0F, 19.5F, -4.0F);
        setRotationAngle(Body, -0.1745F, 0.0F, 0.0F);
        Body.texOffs(0, 17).addBox(-3.0F, -4.5F, -0.636F, 6.0F, 5.0F, 6.0F, 0.0F, false);

        LeftWing = new ModelPart(this);
        LeftWing.setPos(2.3F, 14.7F, -3.0F);
        setRotationAngle(LeftWing, 0.6545F, 0.2182F, 0.0F);
        LeftWing.texOffs(27, 9).addBox(0.0F, -0.01F, -0.6F, 5.0F, 0.0F, 9.0F, 0.0F, false);

        RightWing = new ModelPart(this);
        RightWing.setPos(-2.3F, 14.7F, -3.0F);
        setRotationAngle(RightWing, 0.6545F, -0.2182F, 0.0F);
        RightWing.texOffs(17, 0).addBox(-5.0F, -0.01F, -0.6F, 5.0F, 0.0F, 9.0F, 0.0F, false);

        Butt = new ModelPart(this);
        Butt.setPos(0.0F, 18.3218F, 2.1213F);
        setRotationAngle(Butt, -0.2618F, 0.0F, 0.0F);
        Butt.texOffs(0, 0).addBox(-4.0F, -3.2218F, -1.6213F, 8.0F, 7.0F, 10.0F, 0.0F, false);

        LeftArm = new ModelPart(this);
        LeftArm.setPos(3.0F, 19.0F, -3.5F);
        LeftArm.texOffs(2, 0).addBox(0.01F, 0.0F, -0.5F, 0.0F, 8.0F, 1.0F, 0.0F, false);

        RightArm = new ModelPart(this);
        RightArm.setPos(-3.0F, 19.0F, -3.5F);
        RightArm.texOffs(4, 0).addBox(-0.01F, 0.0F, -0.5F, 0.0F, 8.0F, 1.0F, 0.0F, false);

        LeftLeg = new ModelPart(this);
        LeftLeg.setPos(3.0F, 21.0F, -1.5F);
        LeftLeg.texOffs(6, 0).addBox(0.01F, -1.5F, -0.5F, 0.0F, 8.0F, 1.0F, 0.0F, false);

        RightLeg = new ModelPart(this);
        RightLeg.setPos(-3.0F, 21.0F, -1.5F);
        RightLeg.texOffs(0, 0).addBox(-0.01F, -1.5F, -0.5F, 0.0F, 8.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        // Flap wings back and forth constantly
        this.LeftWing.zRot = Mth.cos(2.0F * ageInTicks);
        this.RightWing.zRot = -Mth.cos(2.0F * ageInTicks);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.Head, this.Stinger);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.Body, this.Butt, this.RightWing, this.LeftWing,
                this.RightArm, this.LeftArm, this.RightLeg, this.LeftLeg);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
