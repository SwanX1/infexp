package com.nekomaster1000.infernalexp.config.gui.widgets;

import net.minecraft.client.Option;
import net.minecraft.client.Options;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class TextFieldOption extends Option {

	private final Component tooltip;
	private final Function<Options, String> getter;
	private final BiConsumer<Options, String> setter;

	public TextFieldOption(String translationKey, @Nullable Component tooltip, Function<Options, String> getter, BiConsumer<Options, String> setter) {
		super(translationKey);
		this.tooltip = tooltip;
		this.getter = getter;
		this.setter = setter;
	}

	@Override
	public AbstractWidget createButton(Options options, int xIn, int yIn, int widthIn) {
		if (tooltip != null) {
			this.setTooltip(Minecraft.getInstance().font.split(tooltip, 200));
		}

		return new IETextFieldWidget(options, xIn, yIn, widthIn, getCaption(), this);
	}

	public void set(Options options, String textIn) {
		setter.accept(options, textIn);
	}

	public String get(Options options) {
		return getter.apply(options);
	}

}
