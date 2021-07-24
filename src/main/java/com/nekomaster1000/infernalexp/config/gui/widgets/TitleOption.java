package com.nekomaster1000.infernalexp.config.gui.widgets;

import net.minecraft.client.Option;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TitleOption extends Option {

	public TitleOption(String translationKeyIn) {
		super(translationKeyIn);
	}

	@Override
	public AbstractWidget createButton(Options options, int xIn, int yIn, int widthIn) {
		return new TitleWidget(xIn, yIn, widthIn, 20, getCaption());
	}

}
