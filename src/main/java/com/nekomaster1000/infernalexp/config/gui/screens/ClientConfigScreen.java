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
public class ClientConfigScreen extends IESettingsScreen {

	public ClientConfigScreen(Screen parentScreen) {
		super(parentScreen, new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.title.clientConfig"));
	}

	@Override
	public void addSettings() {
		for (InfernalExpansionConfig.ClientConfig clientConfig : InfernalExpansionConfig.ClientConfig.values()) {
			if (clientConfig.isSlider()) {
				optionsRowList.addBig(new ProgressOption(InfernalExpansion.MOD_ID + ".config.option." + clientConfig.getTranslationName(), clientConfig.getMinValue(), clientConfig.getMaxValue(), clientConfig.getStepSize(),
					settings -> clientConfig.getDouble(), (settings, value) -> clientConfig.set(value),
					(settings, option) -> {
						option.setTooltip(Minecraft.getInstance().font.split(
							new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.tooltip." + clientConfig.getTranslationName()), 200));

						return new TranslatableComponent("options.generic_value", option.getCaption(),
							new TextComponent(Integer.toString((int) option.get(settings))));
					}));
			} else {
				optionsRowList.addBig(new BooleanOption(InfernalExpansion.MOD_ID + ".config.option." + clientConfig.getTranslationName(),
					new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.tooltip." + clientConfig.getTranslationName()),
					settings -> clientConfig.getBool(), (settings, value) -> clientConfig.set(value)));
			}
		}
	}

}
