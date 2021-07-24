package com.nekomaster1000.infernalexp.client.entity.render;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.CerobeetleModel;
import com.nekomaster1000.infernalexp.entities.CerobeetleEntity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CerobeetleRenderer extends MobRenderer<CerobeetleEntity, CerobeetleModel<CerobeetleEntity>> {
protected static final ResourceLocation TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
    "textures/entity/cerobeetle.png");

public CerobeetleRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new CerobeetleModel<>(), 0.7f);
    }

@Override
public ResourceLocation getTextureLocation(CerobeetleEntity entity) {
        return TEXTURE;
    }
}
