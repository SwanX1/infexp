package com.nekomaster1000.infernalexp.items;

import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import net.minecraft.world.item.Item.Properties;

public class SlurpSoupItem extends BowlFoodItem {
    public SlurpSoupItem(Properties builder) {
        super(builder);
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
