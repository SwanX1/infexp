package com.nekomaster1000.infernalexp.init;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.util.NetherTeleportCommandUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.server.level.ServerLevel;

import java.util.Collection;

public class IECommands {

    private static void netherSpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        String commandString = "setdimensionspawn";

        // First the command is defined if no player is supplied, setting the user's spawn, then if there are players supplied as targets
        LiteralCommandNode<CommandSourceStack> source = dispatcher.register(Commands.literal(commandString).requires(commandSource -> commandSource.hasPermission(2)).executes(command -> {
            ServerPlayer player = command.getSource().getPlayerOrException();
            BlockPos pos = player.blockPosition();
            Level world = player.getCommandSenderWorld();
            String dimension = world.dimension().location().toString();

            // Sets the player's spawnpoint and spawn dimension
            player.setRespawnPosition(world.dimension(), pos, 0.0F, true, false);

            // Prints out the location of the new spawnpoint, including player name, coordinates, rotation, and dimension
            command.getSource().sendSuccess(new TranslatableComponent(InfernalExpansion.MOD_ID + ".commands.setdimensionspawn.success.single", player.getDisplayName(), pos.getX(), pos.getY(), pos.getZ(), 0.0F, dimension), true);
            return 1;
        }).then(Commands.argument("players", EntityArgument.players()).executes(command -> {
            Collection<ServerPlayer> players = EntityArgument.getPlayers(command, "players");
            BlockPos pos = command.getSource().getPlayerOrException().blockPosition();
            Level world = command.getSource().getPlayerOrException().getCommandSenderWorld();
            String dimension = world.dimension().location().toString();

            // Sets each player's spawnpoint and spawn dimension
            for (ServerPlayer player : players) {
                player.setRespawnPosition(world.dimension(), pos, 0.0F, true, false);
            }

            // Prints out the location of the new spawnpoint, including player if only one player targeted or how many if multiple, coordinates, rotation, and dimension
            if (players.size() == 1) {
                command.getSource().sendSuccess(new TranslatableComponent(InfernalExpansion.MOD_ID + ".commands.setdimensionspawn.success.single", players.iterator().next().getDisplayName(), pos.getX(), pos.getY(), pos.getZ(), 0.0F, dimension), true);
            } else {
                command.getSource().sendSuccess(new TranslatableComponent(InfernalExpansion.MOD_ID + ".commands.setdimensionspawn.success.multiple", players.size(), pos.getX(), pos.getY(), pos.getZ(), 0.0F, dimension), true);
            }
            return players.size();
        })));
    }

    private static void dimensionTeleportCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        String commandString = "ntp";

        LiteralCommandNode<CommandSourceStack> source = dispatcher.register(Commands.literal(commandString).requires(commandSource -> commandSource.hasPermission(3)).executes(command -> {
            ServerPlayer player = command.getSource().getPlayerOrException();
            MinecraftServer server = command.getSource().getServer();
            ServerLevel targetWorld = player.getCommandSenderWorld().dimension() == Level.NETHER ? server.getLevel(Level.OVERWORLD) : server.getLevel(Level.NETHER);
            
            // Safeguard the teleport to not teleport outside the world border
            WorldBorder worldborder = targetWorld.getWorldBorder();
            
            // Makes sure world border is within bounds and min/max is safely within the world border
            double minX = Math.max(-2.9999872E7D, worldborder.getMinX() + 16.0D);
            double minZ = Math.max(-2.9999872E7D, worldborder.getMinZ() + 16.0D);
            double maxX = Math.min(2.9999872E7D, worldborder.getMaxX() - 16.0D);
            double maxZ = Math.min(2.9999872E7D, worldborder.getMaxZ() - 16.0D);
            
            // Adds support to modded dimensions
            double coordinateDifference = DimensionType.getTeleportationScale(player.level.dimensionType(), targetWorld.dimensionType());
            
            // MathHelper.clamp() returns the middle value (in this case, prevents teleporting outside the world border)
            BlockPos baseTeleportLocation = new BlockPos(
                    Mth.clamp(player.getX() * coordinateDifference, minX, maxX),
                    player.getY(),
                    Mth.clamp(player.getZ() * coordinateDifference, minZ, maxZ));
            
            BlockPos safeTeleportLocation = NetherTeleportCommandUtil.getSafePosition(targetWorld, baseTeleportLocation);
            
            if (safeTeleportLocation == null) {
                return 0;
            }
            
            player.teleportTo(targetWorld, safeTeleportLocation.getX(), safeTeleportLocation.getY(), safeTeleportLocation.getZ(), player.getViewYRot(0.0F), player.getViewXRot(0.0F));
            
            return 1;
        }));
    }

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dimensionTeleportCommand(dispatcher);
        netherSpawnCommand(dispatcher);
    }
}
