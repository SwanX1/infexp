package com.nekomaster1000.infernalexp.network;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class WhipReachPacket {
	private final UUID playerUUID;
	private final int targetEntityID;
	private static final Random random = new Random();


	public WhipReachPacket(UUID playerUUID, int target) {
		this.playerUUID = playerUUID;
		this.targetEntityID = target;
	}

	public static void encode(WhipReachPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerUUID);
		buffer.writeVarInt(message.targetEntityID);
	}

	public static WhipReachPacket decode(FriendlyByteBuf buffer) {
		return new WhipReachPacket(buffer.readUUID(), buffer.readVarInt());
	}

	public static void handle(WhipReachPacket message, Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			Player playerEntity = context.get().getSender();
            playerEntity.getCommandSenderWorld().playSound(playerEntity, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F));

            if (playerEntity != null && playerEntity.getServer() != null) {
				ServerPlayer player = playerEntity.getServer().getPlayerList().getPlayer(message.playerUUID);
				Entity target = playerEntity.getCommandSenderWorld().getEntity(message.targetEntityID);

                double reach = player.getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() + 1.0D;

				if (player != null && target != null) {
					if (player.distanceToSqr(target) < (reach * reach) * player.getAttackStrengthScale(0.0F)) {
						player.attack(target);
						player.getCommandSenderWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F));

						// Change the first float value to change the amount of knockback on hit
						((LivingEntity) target).knockback(1.0F, Mth.sin(player.yRot * ((float) Math.PI / 180F)), -Mth.cos(player.yRot * ((float) Math.PI / 180F)));
					}
				}
			}
		});

		context.get().setPacketHandled(true);
	}
}
