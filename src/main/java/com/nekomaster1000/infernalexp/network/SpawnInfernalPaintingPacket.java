package com.nekomaster1000.infernalexp.network;

import com.nekomaster1000.infernalexp.entities.InfernalPaintingEntity;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class SpawnInfernalPaintingPacket {

    private final int entityID;
    private final UUID uniqueID;
    private final BlockPos pos;
    private final Direction facing;
    private final String title;

    public SpawnInfernalPaintingPacket(InfernalPaintingEntity painting) {
        this.entityID = painting.getId();
        this.uniqueID = painting.getUUID();
        this.pos = painting.getPos();
        this.facing = painting.getDirection();
        this.title = ForgeRegistries.PAINTING_TYPES.getKey(painting.motive).toString();
    }

    public SpawnInfernalPaintingPacket(int entityID, UUID uniqueID, BlockPos pos, Direction facing, String title) {
		this.entityID = entityID;
		this.uniqueID = uniqueID;
		this.pos = pos;
		this.facing = facing;
		this.title = title;
	}

	public static void encode(SpawnInfernalPaintingPacket message, FriendlyByteBuf packetBuffer) {
		packetBuffer.writeVarInt(message.entityID);
		packetBuffer.writeUUID(message.uniqueID);
		packetBuffer.writeBlockPos(message.pos);
		packetBuffer.writeByte(message.facing.get2DDataValue());
		packetBuffer.writeUtf(message.title);
	}

    public static SpawnInfernalPaintingPacket decode(FriendlyByteBuf packetBuffer) {
        return new SpawnInfernalPaintingPacket(
            packetBuffer.readVarInt(),
            packetBuffer.readUUID(),
            packetBuffer.readBlockPos(),
            Direction.from2DDataValue(packetBuffer.readByte()),
            packetBuffer.readUtf()
        );
    }

    public static void handle(SpawnInfernalPaintingPacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Optional<Level> world = LogicalSidedProvider.CLIENTWORLD.get(ctx.get().getDirection().getReceptionSide());

            InfernalPaintingEntity paintingEntity = new InfernalPaintingEntity(world.orElse(null), message.pos, message.facing, ForgeRegistries.PAINTING_TYPES.getValue(new ResourceLocation(message.title)));
            paintingEntity.setId(message.entityID);
            paintingEntity.setUUID(message.uniqueID);

            world.filter(ClientLevel.class::isInstance).ifPresent(w -> ((ClientLevel) w).putNonPlayerEntity(message.entityID, paintingEntity));
        });

		ctx.get().setPacketHandled(true);
	}
}
