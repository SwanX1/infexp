package com.nekomaster1000.infernalexp.blocks;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.network.protocol.game.ClientboundChatPacket;
import net.minecraft.core.Direction;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ChatType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

public class DullthornsBlockItem extends BlockItemBase {

    public DullthornsBlockItem(Block block) {
        super(block);
    }

    @Override
    @Nullable
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
        if (context.getClickedFace() == Direction.UP) {
            return context;
        }

        Level world = context.getLevel();
        Direction placedirection = context.isInside() ? context.getClickedFace() : context.getClickedFace().getOpposite();
        BlockPos placepos = context.getClickedPos().relative(placedirection);
        int worldHeight = world.getMaxBuildHeight();
        BlockState dullthorns = this.getBlock().defaultBlockState();

        while (world.getBlockState(placepos) == dullthorns) {
            placepos = placepos.above();

            // Prevent placing outside world
            if (!world.isClientSide && !Level.isInWorldBounds(placepos)) {
                Player player = context.getPlayer();
                if (player instanceof ServerPlayer && placepos.getY() >= worldHeight) {
                   ClientboundChatPacket schatpacket = new ClientboundChatPacket((new TranslatableComponent("build.tooHigh", worldHeight)).withStyle(ChatFormatting.RED), ChatType.GAME_INFO, Util.NIL_UUID);
                   ((ServerPlayer) player).connection.send(schatpacket);
                }
                return null;
             }
        }

        if (world.isEmptyBlock(placepos)) {
            return BlockPlaceContext.at(context, placepos, placedirection);
        } else {
            return null;
        }

    }
}
