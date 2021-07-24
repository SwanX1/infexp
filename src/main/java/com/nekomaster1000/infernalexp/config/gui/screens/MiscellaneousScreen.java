package com.nekomaster1000.infernalexp.config.gui.screens;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.BooleanOption;
import net.minecraft.client.ProgressOption;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MiscellaneousScreen extends IESettingsScreen {

	public MiscellaneousScreen(Screen parentScreen) {
		super(parentScreen, new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.title.miscellaneous"));
	}

	@Override
	public void addSettings() {
		for (InfernalExpansionConfig.Miscellaneous miscellaneous : InfernalExpansionConfig.Miscellaneous.values()) {
			if (miscellaneous.isSlider()) {
				optionsRowList.addBig(new ProgressOption(InfernalExpansion.MOD_ID + ".config.option." + miscellaneous.getTranslationName(), miscellaneous.getMinValue(), miscellaneous.getMaxValue(), miscellaneous.getStepSize(),
					settings -> miscellaneous.getDouble(), (settings, value) -> miscellaneous.set(value),
					(settings, option) -> {
						option.setTooltip(Minecraft.getInstance().font.split(
							new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.tooltip." + miscellaneous.getTranslationName()), 200));

						return new TranslatableComponent("options.generic_value", option.getCaption(),
							new TextComponent(Double.toString((double) Math.round(option.get(settings) * 100) / 100)));
					}));
			} else {
				optionsRowList.addBig(new BooleanOption(InfernalExpansion.MOD_ID + ".config.option." + miscellaneous.getTranslationName(),
					new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.tooltip." + miscellaneous.getTranslationName()),
					settings -> miscellaneous.getBool(), (settings, value) -> miscellaneous.set(value)));
			}
		}
	}
}
