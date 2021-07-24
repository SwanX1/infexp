package com.nekomaster1000.infernalexp.mixin.common;

import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.Motive;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(Painting.class)
public class MixinPaintingEntity {

	@Shadow
	public Motive art;

	// Minecraft always wants to place the biggest painting possible, this makes sure that it doesn't think that an Infernal Expansion painting is the biggest possible
    @ModifyVariable(at = @At(value = "STORE"), method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/Direction;)V", ordinal = 1)
    public int IE_changeMaxSurfaceArea(int maxSurfaceArea) {
        return art.getRegistryName().getNamespace().equals("infernalexp") ? 0 : maxSurfaceArea;
    }

	@ModifyVariable(at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z", shift = Shift.BEFORE), method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/Direction;)V", ordinal = 0)
	public List<Motive> IE_changePaintingList(List<Motive> list) {
		list.removeIf(paintingType -> paintingType.getRegistryName().getNamespace().equals("infernalexp"));

		return list;
	}

}
