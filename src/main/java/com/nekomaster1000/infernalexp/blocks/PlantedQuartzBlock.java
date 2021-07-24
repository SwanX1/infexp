package com.nekomaster1000.infernalexp.blocks;

import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.init.IETags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import javax.annotation.CheckForNull;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PlantedQuartzBlock extends HorizontalBushBlock {
    protected static final VoxelShape FLOOR_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    protected static final VoxelShape CEILING_SHAPE = Block.box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape WALL_SHAPE_NORTH = Block.box(2.0D, 2.0D, 4.0D, 14.0D, 14.0D, 16.0D);
    protected static final VoxelShape WALL_SHAPE_SOUTH = Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 12.0D);
    protected static final VoxelShape WALL_SHAPE_EAST = Block.box(0.0D, 2.0D, 2.0D, 12.0D, 14.0D, 14.0D);
    protected static final VoxelShape WALL_SHAPE_WEST = Block.box(4.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);

    public PlantedQuartzBlock(Properties builder) {
        super(builder);
        this.registerDefaultState(this.defaultBlockState().setValue(FACE, AttachFace.FLOOR).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected boolean isValidGround(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.getBlock().is(IETags.Blocks.PLANTED_QUARTZ_BASE_BLOCKS);
    }

    @CheckForNull
    public BlockState getPlaceableState(Level world, BlockPos pos, Direction placeSide) {
        if (world.getBlockState(pos).getMaterial().isReplaceable() && world.getBlockState(pos).getBlock() != IEBlocks.PLANTED_QUARTZ.get()) {
            Direction attachdirection;
            if (this.isValidGround(world.getBlockState(pos.relative(placeSide.getOpposite())), world, pos)) {
                attachdirection = placeSide.getOpposite();
            } else if (this.isValidGround(world.getBlockState(pos.relative(placeSide)), world, pos)) {
                attachdirection = placeSide;
            } else {
                return null;
            }
            AttachFace attachface;
            if (attachdirection == Direction.UP) {
                attachface = AttachFace.CEILING;
            } else if (attachdirection == Direction.DOWN) {
                attachface = AttachFace.FLOOR;
            } else {
                attachface = AttachFace.WALL;
            }
            if (attachface == AttachFace.WALL) {
                return this.defaultBlockState().setValue(FACE, attachface).setValue(FACING, attachdirection.getOpposite());
            } else {
                return this.defaultBlockState().setValue(FACE, attachface);
            }
        }
        return null;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return canAttach(worldIn, pos, getConnectedDirection(state).getOpposite());
    }

    public boolean canAttach(LevelReader reader, BlockPos pos, Direction direction) {
        BlockPos blockpos = pos.relative(direction);
        return isValidGround(reader.getBlockState(blockpos), reader, blockpos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACE)) {
            case WALL:
                switch (state.getValue(FACING)) {
                    case NORTH:
                        return WALL_SHAPE_NORTH;
                    case SOUTH:
                        return WALL_SHAPE_SOUTH;
                    case EAST:
                        return WALL_SHAPE_EAST;
                    case WEST:
                    default:
                        return WALL_SHAPE_WEST;
                }
            case FLOOR :
                return FLOOR_SHAPE;
            case CEILING:
            default:
                return CEILING_SHAPE;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builderIn) {
        builderIn.add(FACING, FACE);
    }

    @Override
    public Item asItem() {
        return Items.QUARTZ;
    }
}
