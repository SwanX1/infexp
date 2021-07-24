package com.nekomaster1000.infernalexp.items;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.entities.AscusBombEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

public class AscusBombItem extends Item {
    public AscusBombItem() {
        super(new Item.Properties().stacksTo(16).tab(InfernalExpansion.TAB));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);

        if (!worldIn.isClientSide) {
            AscusBombEntity ascusBombEntity = new AscusBombEntity(worldIn, playerIn);
            ascusBombEntity.setItem(itemStack);
			ascusBombEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, -20, 0.5f, 1);
			worldIn.addFreshEntity(ascusBombEntity);
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));

        if (!playerIn.abilities.instabuild) {
            itemStack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, worldIn.isClientSide());
    }
}
