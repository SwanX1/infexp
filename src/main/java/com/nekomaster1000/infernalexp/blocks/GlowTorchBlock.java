package com.nekomaster1000.infernalexp.blocks;

import com.nekomaster1000.infernalexp.init.IEParticleTypes;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class GlowTorchBlock extends TorchBlock {

	public GlowTorchBlock(Properties properties) {
		super(properties, null);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		if (rand.nextInt(2) == 1) {
            worldIn.addParticle(IEParticleTypes.GLOWSTONE_SPARKLE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
	}
}
