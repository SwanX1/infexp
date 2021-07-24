package com.nekomaster1000.infernalexp.blocks;

import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig;
import com.nekomaster1000.infernalexp.entities.BlindsightEntity;
import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.init.IEConfiguredFeatures;
import com.nekomaster1000.infernalexp.init.IEEffects;
import com.nekomaster1000.infernalexp.tileentities.LuminousFungusTileEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class LuminousFungusBlock extends HorizontalBushBlock implements BonemealableBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    protected static final VoxelShape FLOOR_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    protected static final VoxelShape CEILING_SHAPE = Block.box(5.0D, 6.0D, 5.0D, 11.0D, 16.0D, 11.0D);

    public LuminousFungusBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACE, AttachFace.FLOOR).setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected boolean isValidGround(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return
			state.is(IEBlocks.GLOWDUST_SAND.get()) || state.is(Blocks.SAND) || state.is(Blocks.RED_SAND)
				|| state.is(Blocks.GRASS) || state.is(Blocks.GRASS_BLOCK) ||
				state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.FARMLAND) ||
				state.is(Blocks.PODZOL) || state.is(Blocks.MYCELIUM) ||
				state.is(Blocks.CRIMSON_NYLIUM) || state.is(Blocks.WARPED_NYLIUM) ||
				state.is(Blocks.SOUL_SOIL) || state.is(Blocks.GLOWSTONE) || state.is(IEBlocks.DIMSTONE.get()) ||
				state.is(IEBlocks.DULLSTONE.get()) || state.is(IEBlocks.DULLTHORNS.get())
			;
    }

    public boolean canAttach(LevelReader reader, BlockPos pos, Direction direction) {
        BlockPos blockpos = pos.relative(direction);
        return isValidGround(reader.getBlockState(blockpos), reader, blockpos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return !state.getValue(FACE).equals(AttachFace.WALL) && canAttach(worldIn, pos, getConnectedDirection(state).getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Vec3 vector3d = state.getOffset(worldIn, pos);

        switch(state.getValue(FACE)){
            case FLOOR:
                return FLOOR_SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
            case CEILING:
            default:
                return CEILING_SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
        }
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isClientSide()) {
            if (entityIn instanceof LivingEntity && entityIn.isAlive() && InfernalExpansionConfig.Miscellaneous.LUMINOUS_FUNGUS_GIVES_EFFECT.getBool()) {
                LivingEntity livingEntity = (LivingEntity) entityIn;
                livingEntity.addEffect(new MobEffectInstance(IEEffects.LUMINOUS.get(), 120, 0, true, true));
            }
        }
    }


    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builderIn) {
        builderIn.add(FACING, FACE, LIT);
    }
    
	/**
	 * Whether this IGrowable can grow
	 */
	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		Block block = ((HugeFungusConfiguration) (IEConfiguredFeatures.DULLTHORN_TREE_PLANTED).config).validBaseState.getBlock();
		Block block1 = worldIn.getBlockState(pos.below()).getBlock();
		return block1 == block;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
		return (double) rand.nextFloat() < 0.4D;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
		IEConfiguredFeatures.DULLTHORN_TREE_PLANTED.place(worldIn, worldIn.getChunkSource().getGenerator(), rand, pos);
	}

    @Nullable
    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
        return new LuminousFungusTileEntity();
    }
}
