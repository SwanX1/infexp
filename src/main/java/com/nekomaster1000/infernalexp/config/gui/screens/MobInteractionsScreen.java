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
public class MobInteractionsScreen extends IESettingsScreen {

	public MobInteractionsScreen(Screen parentScreen) {
		super(parentScreen, new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.title.mobInteractions"));
	}

	@Override
	public void addSettings() {
		for (InfernalExpansionConfig.MobInteractions mobInteraction : InfernalExpansionConfig.MobInteractions.values()) {
			if (mobInteraction.isSlider()) {
				optionsRowList.addBig(new ProgressOption(InfernalExpansion.MOD_ID + ".config.option." + mobInteraction.getTranslationName(),
					0.2D, 10.0D, 0.2F,
					settings -> mobInteraction.getDouble(), (settings, value) -> mobInteraction.setDouble(value),
					(settings, option) -> {
						option.setTooltip(Minecraft.getInstance().font.split(
							new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.tooltip." + mobInteraction.getTranslationName()), 200));

						return new TranslatableComponent("options.generic_value", option.getCaption(),
							new TextComponent(Double.toString((double) Math.round(option.get(settings) * 100) / 100)));
					}));
			} else {
				optionsRowList.addBig(new BooleanOption(InfernalExpansion.MOD_ID + ".config.option." + mobInteraction.getTranslationName(),
					new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.tooltip." + mobInteraction.getTranslationName()),
					settings -> mobInteraction.getBoolean(), (settings, value) -> mobInteraction.setBoolean(value)));
			}
		}
	}
}
