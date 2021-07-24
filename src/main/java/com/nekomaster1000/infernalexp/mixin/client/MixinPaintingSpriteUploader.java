package com.nekomaster1000.infernalexp.mixin.client;

import com.nekomaster1000.infernalexp.client.entity.render.InfernalPaintingRenderer;

import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.stream.Stream;

@Mixin(PaintingTextureManager.class)
public class MixinPaintingSpriteUploader {

	@Shadow
	@Final
	private static ResourceLocation LOCATION_BACK_SPRITE;

	/**
	 * Overwrite to add custom back texture to painting texture atlas
	 *
	 * @author caelwarner
	 * @reason To add infernal_back.png to painting texture atlas
	 */
	@Overwrite
	protected Stream<ResourceLocation> getResourceLocations() {
		return Stream.concat(Registry.MOTIVE.keySet().stream(), Stream.of(LOCATION_BACK_SPRITE, InfernalPaintingRenderer.BACK_TEXTURE_ATLAS_LOCATION));
	}

}
