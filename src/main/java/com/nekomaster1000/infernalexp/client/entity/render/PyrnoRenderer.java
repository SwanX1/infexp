package com.nekomaster1000.infernalexp.client.entity.render;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.client.entity.model.PyrnoModel;
import com.nekomaster1000.infernalexp.entities.PyrnoEntity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PyrnoRenderer extends MobRenderer<PyrnoEntity, PyrnoModel<PyrnoEntity>> {

protected static final ResourceLocation TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID,
    "textures/entity/pyrno.png");

public PyrnoRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new PyrnoModel<>(), 0.7f);
        }

@Override
public ResourceLocation getTextureLocation(PyrnoEntity entity) {
        return TEXTURE;
        }
        }
