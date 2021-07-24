package com.nekomaster1000.infernalexp.entities;

import com.nekomaster1000.infernalexp.init.IEEntityTypes;
import com.nekomaster1000.infernalexp.init.IEItems;
import com.nekomaster1000.infernalexp.network.IENetworkHandler;
import com.nekomaster1000.infernalexp.network.SpawnInfernalPaintingPacket;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.protocol.Packet;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfernalPaintingEntity extends Painting {

	public InfernalPaintingEntity(EntityType<? extends InfernalPaintingEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public InfernalPaintingEntity(Level worldIn, BlockPos pos, Direction facing) {
		this(IEEntityTypes.INFERNAL_PAINTING.get(), worldIn);
		this.pos = pos;
		setDirection(facing);

		List<Motive> paintings = new ArrayList<>();
		int maxSurfaceArea = 0;

		for (Motive paintingType : ForgeRegistries.PAINTING_TYPES) {
			motive = paintingType;
			setDirection(facing);

			if (survives() && paintingType.getRegistryName().getNamespace().equals("infernalexp")) {
				paintings.add(paintingType);

				int surfaceArea = paintingType.getWidth() * paintingType.getHeight();

				if (surfaceArea > maxSurfaceArea) {
					maxSurfaceArea = surfaceArea;
				}
			}
		}

		if (!paintings.isEmpty()) {
			Iterator<Motive> iterator = paintings.iterator();

			while (iterator.hasNext()) {
				Motive paintingType = iterator.next();

				if (paintingType.getWidth() * paintingType.getHeight() < maxSurfaceArea) {
					iterator.remove();
				}
			}

			motive = paintings.get(this.random.nextInt(paintings.size()));
		}

		setDirection(facing);
	}

	@OnlyIn(Dist.CLIENT)
	public InfernalPaintingEntity(Level world, BlockPos pos, Direction facing, Motive art) {
		this(world, pos, facing);
		this.motive = art;
		this.setDirection(facing);
	}

	@Override
	public void dropItem(@Nullable Entity brokenEntity) {
		if (level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
			playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);

			if (brokenEntity instanceof Player) {
				if (((Player) brokenEntity).abilities.instabuild) {
					return;
				}
			}

			spawnAtLocation(IEItems.INFERNAL_PAINTING.get());
		}
	}

	@Override
	public Packet<?> getAddEntityPacket() {
        return IENetworkHandler.INSTANCE.toVanillaPacket(new SpawnInfernalPaintingPacket(this), NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return IEItems.INFERNAL_PAINTING.get().getDefaultInstance();
    }
}
